#!/usr/bin/env python3
"""Prehistoric Tribes phase 1: rename the Vineflower decompilation into tribes/src/.

Renames at the class-file level: Vineflower's --user-renamer-class hook is
given tools/ch_rename/TribesRenamer.java, which reads a rename map, so every
reference -- including inherited members, and the same single letter reused
in different classes and with different types -- is rewritten correctly.

    python tools/rename_tribes.py                         # regenerate + compile-check
    python tools/rename_tribes.py --no-check              # skip the javac check
    python tools/rename_tribes.py --extracted <dir> --map <file> --out <dir> \
        --stubs <dir> --vineflower <jar>                  # explicit paths

Steps: compile TribesRenamer, build the effective map (human names.map plus
auto-generated disambiguation for colliding members the human map does not
cover yet), run Vineflower over the extracted classes with that map, apply
PATCHES (decompiler artifacts that do not compile), write tribes/src/*.java,
verify every map entry was used, then compile them against the MIDP stub
jars. The compiler is the final arbiter: src/ must build with zero errors.

names.map format (one entry per line, '#' comments):
    class  <old> <New>
    field  <oldClass>.<oldField>:<javaType> <newName>
    method <oldClass>.<oldMethod>(<paramTypes>):<returnType> <newName>
The field type and the method return type are part of the key: this
obfuscator reuses one letter for many members in the same class (23 fields
named `a` in class f; 49 method name+params groups that differ only by
return type). A key without the type would be ambiguous.

Auto entries: the raw decompile cannot compile as Java source, because
same-name members with different types are legal bytecode but illegal Java
(185 collision groups, 542 members). The human map is the real rename work;
for any colliding member it does not cover yet, this script generates a
mechanical type-derived name (e.g. `a_int`, `a_byteArr`, `a_void`) into
tribes/docs/names-auto.map so the tree still compiles. Auto entries are
scaffolding: they are replaced by meaningful names as the human map grows,
and the counts are reported at the end.
"""
import argparse
import collections
import os
import re
import shutil
import subprocess
import sys
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent

# Decompiler artifacts that are not valid Java, fixed textually after the
# rename. (file, old, new); every entry must match exactly once.
# Empty for now: add entries here as the compile check shows them.
PATCHES: list[tuple[str, str, str]] = []


def find_java() -> Path:
    home = os.environ.get("JAVA_HOME")
    if home:
        cand = Path(home) / "bin" / "java"
        if cand.exists():
            return cand
    found = shutil.which("java")
    if found:
        return Path(found)
    cand = Path("/home/hatch/.local/jdk-17/bin/java")
    if cand.exists():
        return cand
    sys.exit("no Java found: set JAVA_HOME or put java on PATH")


def run(cmd, **kw):
    return subprocess.run([str(c) for c in cmd], check=True, **kw)


def parse_map(map_path: Path) -> list[tuple[str, str, str]]:
    """Return (kind, key, newName) for every entry in a map file."""
    entries = []
    for raw in map_path.read_text(encoding="utf-8").splitlines():
        line = raw.strip()
        if not line or line.startswith("#"):
            continue
        p = line.split()
        if len(p) != 3:
            sys.exit(f"bad map line: {raw}")
        entries.append((p[0], p[1], p[2]))
    return entries


def normalize_type(t: str) -> str:
    """'java.lang.String[]' -> 'String[]'; 'javax.microedition.lcdui.Graphics' -> 'Graphics'."""
    t = t.strip()
    dims = 0
    while t.endswith("[]"):
        dims += 1
        t = t[:-2]
    base = t.split(".")[-1]
    return base + "[]" * dims


def javap_inventory(javap: Path, extracted: Path) -> list[dict]:
    """Member inventory from `javap -p`: fields and methods with their types.

    Returns dicts: {kind: 'field'|'method', cls, name, type} for fields and
    {kind: 'method', cls, name, params, ret} for methods. Constructors are
    skipped (they follow the class rename).
    """
    classes = sorted(p.stem for p in extracted.glob("*.class"))
    members = []
    modifiers = ("public", "private", "protected", "static", "final", "abstract",
                 "synchronized", "native", "transient", "volatile", "strictfp")
    method_re = re.compile(r"^(\S+)\s+(\w+)\(([^)]*)\)\s*;$")
    field_re = re.compile(r"^(\S+)\s+(\w+)\s*;$")
    ctor_re = re.compile(r"^(\w+)\(([^)]*)\)\s*;$")
    for cls in classes:
        out = subprocess.run([str(javap), "-p", "-classpath", str(extracted), cls],
                             check=True, capture_output=True, text=True).stdout
        for line in out.splitlines():
            line = line.strip()
            if not line or line.startswith("public final class") or line.startswith("public class") \
                    or line.startswith("class ") or line in ("{", "}", "static {};"):
                continue
            # Strip leading modifiers; what remains decides the member kind.
            rest = line
            changed = True
            while changed:
                changed = False
                for mod in modifiers:
                    if rest.startswith(mod + " "):
                        rest = rest[len(mod) + 1:]
                        changed = True
                        break
            if ctor_re.match(rest) and not method_re.match(rest):
                continue  # constructor: single token before '('; follows the class rename
            m = method_re.match(rest)
            if m:
                ret, name, params = m.group(1), m.group(2), m.group(3)
                params = ",".join(normalize_type(p) for p in params.split(",") if p.strip())
                members.append({"kind": "method", "cls": cls, "name": name,
                                "params": params, "ret": normalize_type(ret)})
                continue
            f = field_re.match(rest)
            if f:
                members.append({"kind": "field", "cls": cls, "name": f.group(2),
                                "type": normalize_type(f.group(1))})
                continue
            sys.exit(f"unparsable javap line for {cls}: {line!r}")
    return members


def type_tag(t: str, class_renames: dict) -> str:
    """Mechanical tag for an auto name: 'byte[]' -> 'byteArr', 'b' -> 'world'."""
    dims = 0
    while t.endswith("[]"):
        dims += 1
        t = t[:-2]
    base = class_renames.get(t, t)
    if t in class_renames:
        base = base[0].lower() + base[1:]
    if dims:
        base += "Arr" + (str(dims) if dims > 1 else "")
    return base


def auto_entries(inventory: list[dict], human: list[tuple[str, str, str]],
                 class_renames: dict) -> list[tuple[str, str, str]]:
    """Mechanical entries for colliding members the human map does not cover.

    Fields: same name with different types in one class (illegal Java).
    Methods: same name shared by more than one method in one class -- even
    with different params, because call sites that widen arguments
    (byte -> int) can become ambiguous between two overloads, so every
    member of a shared-name group needs a distinct name. The auto name
    encodes the full signature, because Vineflower does NOT re-ask when
    two different map keys produce the same name -- the renamer itself must
    keep new names distinct within a class.
    """
    human_keys = {(k, key) for k, key, _ in human}
    field_groups: dict[tuple, set] = collections.defaultdict(set)
    method_groups: dict[tuple, set] = collections.defaultdict(set)
    for m in inventory:
        if m["kind"] == "field":
            field_groups[(m["cls"], m["name"])].add(m["type"])
        else:
            method_groups[(m["cls"], m["name"])].add((m["params"], m["ret"]))
    auto = []
    for m in inventory:
        if m["kind"] == "field":
            if len(field_groups[(m["cls"], m["name"])]) < 2:
                continue
            key = f"{m['cls']}.{m['name']}:{m['type']}"
            if ("field", key) in human_keys:
                continue
            auto.append(("field", key, f"{m['name']}_{type_tag(m['type'], class_renames)}"))
        else:
            if len(method_groups[(m["cls"], m["name"])]) < 2:
                continue
            key = f"{m['cls']}.{m['name']}({m['params']}):{m['ret']}"
            if ("method", key) in human_keys:
                continue
            params = m["params"]
            tag = m["name"]
            if params:
                tag += "_" + "_".join(type_tag(p, class_renames) for p in params.split(","))
            tag += "_" + type_tag(m["ret"], class_renames)
            auto.append(("method", key, tag))
    return auto


def check_distinct_names(combined: list[tuple[str, str, str]]) -> None:
    """New names must be distinct within a class (methods/fields) and globally
    (classes): Vineflower does not re-ask when two different map keys produce
    the same name, so a duplicate would silently emit uncompilable code."""
    seen: dict[tuple, str] = {}
    for kind, key, new in combined:
        if kind == "class":
            scope = ("class",)
        else:
            scope = (kind, key.split(".")[0])
        if (scope, new) in seen:
            sys.exit(f"duplicate new name {new!r} in {scope}: {seen[(scope, new)]} and {key}")
        seen[(scope, new)] = key


def main() -> None:
    ap = argparse.ArgumentParser(description=__doc__)
    ap.add_argument("--no-check", action="store_true")
    ap.add_argument("--extracted", type=Path, default=ROOT / "tribes" / "extracted")
    ap.add_argument("--map", type=Path, default=ROOT / "tribes" / "docs" / "names.map")
    ap.add_argument("--out", type=Path, default=ROOT / "tribes" / "src")
    ap.add_argument("--stubs", type=Path, default=ROOT / "tools" / "midp-stubs")
    ap.add_argument("--vineflower", type=Path, default=ROOT / "tools" / "vineflower.jar")
    args = ap.parse_args()

    java = find_java()
    javap = java.with_name("javap.exe" if java.suffix == ".exe" else "javap")
    javac = java.with_name("javac.exe" if java.suffix == ".exe" else "javac")
    if not args.vineflower.exists():
        sys.exit(f"Vineflower jar not found: {args.vineflower} (fetch 1.12.0, see tools/README.md)")
    if not args.extracted.is_dir():
        sys.exit(f"extracted classes not found: {args.extracted}")
    if not args.map.exists():
        sys.exit(f"names.map not found: {args.map}")

    human = parse_map(args.map)
    class_renames = {key: new for kind, key, new in human if kind == "class"}
    inventory = javap_inventory(javap, args.extracted)
    auto = auto_entries(inventory, human, class_renames)
    check_distinct_names(human + auto)

    auto_path = args.map.parent / "names-auto.map"
    auto_path.write_text(
        "# GENERATED — do not edit by hand. Regenerated by tools/rename_tribes.py.\n"
        "# Mechanical type-derived names for colliding members the human names.map\n"
        "# does not cover yet. As names.map grows, entries here disappear.\n"
        + "".join(f"{k} {key} {new}\n" for k, key, new in auto),
        encoding="utf-8", newline="\n")

    with tempfile.TemporaryDirectory(prefix="tribes_rename_") as tmp:
        tmp = Path(tmp)
        combined_map = tmp / "combined.map"
        combined_map.write_text(
            args.map.read_text(encoding="utf-8")
            + "".join(f"{k} {key} {new}\n" for k, key, new in auto),
            encoding="utf-8")
        cls_dir, out_dir = tmp / "cls", tmp / "out"
        cls_dir.mkdir()
        out_dir.mkdir()

        run([javac, "-d", cls_dir, "-cp", args.vineflower,
             ROOT / "tools" / "ch_rename" / "TribesRenamer.java"])
        sep = ";" if sys.platform == "win32" else ":"
        run([
            java, f"-Dch.map={combined_map}", "-cp", f"{args.vineflower}{sep}{cls_dir}",
            "org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler",
            "-log=WARN", "-ren=1", "-urc=TribesRenamer",
            args.extracted, out_dir,
        ])

        # Vineflower mirrors non-class files into the output; keep only sources.
        for extra in out_dir.iterdir():
            if extra.is_file() and extra.suffix != ".java":
                extra.unlink()
        sources = sorted(out_dir.glob("*.java"))
        if not sources:
            sys.exit("Vineflower produced no sources")

        args.out.mkdir(parents=True, exist_ok=True)
        for old in args.out.glob("*.java"):
            old.unlink()
        texts = {p.name: p.read_text(encoding="utf-8") for p in sources}
        for fname, old, new in PATCHES:
            if fname not in texts:
                sys.exit(f"patch target missing: {fname}")
            if texts[fname].count(old) != 1:
                sys.exit(f"patch does not match exactly once in {fname}: {old[:60]!r}")
            texts[fname] = texts[fname].replace(old, new)
        for name, text in texts.items():
            (args.out / name).write_text(text, encoding="utf-8", newline="\n")
        print(f"wrote {len(texts)} files -> {args.out}")

        # Every map entry must have been used: its new name appears in the output.
        joined = "\n".join(texts.values())
        combined = human + auto
        unused = [(k, n) for k, _, n in combined
                  if not re.search(r"\b" + re.escape(n) + r"\b", joined)]
        if unused:
            sys.exit("map entries never used (typo or stale entry?): "
                     + ", ".join(f"{k} {n}" for k, n in unused))
        print(f"map check: OK ({len(human)} human + {len(auto)} auto entries used)")

        if not args.no_check:
            classpath = sep.join(str(j) for j in sorted(args.stubs.glob("*.jar")))
            if not classpath:
                sys.exit(f"no stub jars in {args.stubs}")
            build = tmp / "build"
            build.mkdir()
            run([javac, "-nowarn", "-proc:none", "-encoding", "UTF-8",
                 "--release", "8", "-cp", classpath, "-d", build,
                 *sorted(args.out.glob("*.java"))])
            print("compile check: OK (0 errors)")


if __name__ == "__main__":
    main()
