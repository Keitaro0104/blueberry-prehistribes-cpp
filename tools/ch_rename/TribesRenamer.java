import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.java.decompiler.main.extern.IIdentifierRenamer;

/**
 * Vineflower identifier renamer for the Prehistoric Tribes port, driven by
 * tribes/docs/names.map (path in the system property ch.map).
 *
 * Map format (one entry per line, '#' comments):
 *   class  <old> <New>
 *   field  <oldClass>.<oldField>:<javaType> <newName>
 *   method <oldClass>.<oldMethod>(<paramTypes>):<returnType> <newName>
 *
 * The field type and the method return type are part of the key. This
 * obfuscator reuses one letter for many members in the same class: 23 fields
 * named `a` in class f (distinguished only by type), and 49 method name+params
 * groups that differ only by return type. A key without the type would be
 * ambiguous and would produce Java that does not compile.
 *
 * Renaming happens on the class files' own symbol tables, so every reference
 * -- including inherited members reached through a subclass, and the same
 * single letter reused in different classes -- is rewritten consistently.
 * That is also what makes the output compilable at all: the raw decompile
 * cannot compile as Java source, because same-name/different-descriptor
 * members are legal bytecode but illegal Java.
 */
public class TribesRenamer implements IIdentifierRenamer {
    private final Map<String, String> classes = new HashMap<>();
    private final Map<String, String> fields = new HashMap<>();
    private final Map<String, String> methods = new HashMap<>();
    // Vineflower re-asks for a name until it is unique in its scope. Answering
    // the same name forever would hang it, so a repeated request for the same
    // key gets a numeric suffix (and is reported on stderr).
    private final Map<String, Integer> asked = new HashMap<>();

    private String unique(String key, String name) {
        int n = asked.merge(key, 1, Integer::sum);
        if (n == 1) return name;
        System.err.println("tribes_rename: name clash for " + key + " -> " + name + " (retry " + n + ")");
        return name + "_" + n;
    }

    // Some obfuscators name members after Java keywords, which is legal
    // bytecode but not legal source. Those are renamed automatically to
    // <keyword>_, whether or not names.map mentions them.
    private static final Set<String> KEYWORDS = Set.of(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while", "true", "false", "null");

    public TribesRenamer() {
        String path = System.getProperty("ch.map");
        if (path == null) throw new IllegalStateException("-Dch.map=<file> not set");
        try {
            for (String raw : Files.readAllLines(Path.of(path), StandardCharsets.UTF_8)) {
                String line = raw.strip();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\s+");
                if (p.length != 3) throw new IllegalStateException("bad map line: " + raw);
                switch (p[0]) {
                    case "class" -> {
                        if (classes.putIfAbsent(p[1], p[2]) != null)
                            throw new IllegalStateException("duplicate class entry: " + raw);
                    }
                    case "field" -> {
                        if (fields.putIfAbsent(p[1], p[2]) != null)
                            throw new IllegalStateException("duplicate field entry: " + raw);
                    }
                    case "method" -> {
                        if (methods.putIfAbsent(p[1], p[2]) != null)
                            throw new IllegalStateException("duplicate method entry: " + raw);
                    }
                    default -> throw new IllegalStateException("bad map line: " + raw);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        System.err.println("tribes_rename: loaded " + classes.size() + " classes, "
                + fields.size() + " fields, " + methods.size() + " methods from " + path);
    }

    @Override
    public boolean toBeRenamed(Type type, String className, String element, String descriptor) {
        return switch (type) {
            case ELEMENT_CLASS -> classes.containsKey(className);
            case ELEMENT_FIELD -> fields.containsKey(fieldKey(className, element, descriptor))
                    || KEYWORDS.contains(element);
            case ELEMENT_METHOD -> methods.containsKey(methodKey(className, element, descriptor))
                    || KEYWORDS.contains(element);
        };
    }

    @Override
    public String getNextClassName(String fullName, String shortName) {
        String n = classes.get(fullName);
        return n != null ? unique("class " + fullName, n) : fullName;
    }

    @Override
    public String getNextFieldName(String className, String field, String descriptor) {
        String k = fieldKey(className, field, descriptor);
        String n = fields.get(k);
        return unique("field " + k, n != null ? n : field + "_");
    }

    @Override
    public String getNextMethodName(String className, String method, String descriptor) {
        String k = methodKey(className, method, descriptor);
        String n = methods.get(k);
        return unique("method " + k, n != null ? n : method + "_");
    }

    /** "f" + "a" + "I" -> "f.a:int"; "b" + "a" + "[B" -> "b.a:byte[]". */
    private static String fieldKey(String className, String field, String descriptor) {
        int[] pos = {0};
        return className + "." + field + ":" + readType(descriptor, pos);
    }

    /** "f" + "a" + "(II)Z" -> "f.a(int,int):boolean". */
    private static String methodKey(String className, String method, String descriptor) {
        int[] pos = {1}; // skip '('
        StringBuilder out = new StringBuilder(className).append('.').append(method).append('(');
        boolean first = true;
        while (descriptor.charAt(pos[0]) != ')') {
            if (!first) out.append(',');
            first = false;
            out.append(readType(descriptor, pos));
        }
        pos[0]++; // skip ')'
        return out.append("):").append(readType(descriptor, pos)).toString();
    }

    /** Reads one JVM type at pos[0] (advancing it) and returns the Java-style name. */
    private static String readType(String descriptor, int[] pos) {
        int dims = 0;
        while (descriptor.charAt(pos[0]) == '[') {
            dims++;
            pos[0]++;
        }
        String base;
        char c = descriptor.charAt(pos[0]);
        if (c == 'L') {
            int end = descriptor.indexOf(';', pos[0]);
            String full = descriptor.substring(pos[0] + 1, end);
            base = full.substring(full.lastIndexOf('/') + 1);
            pos[0] = end + 1;
        } else {
            base = switch (c) {
                case 'I' -> "int";
                case 'Z' -> "boolean";
                case 'B' -> "byte";
                case 'C' -> "char";
                case 'S' -> "short";
                case 'J' -> "long";
                case 'F' -> "float";
                case 'D' -> "double";
                case 'V' -> "void";
                default -> throw new IllegalArgumentException("bad descriptor: " + descriptor);
            };
            pos[0]++;
        }
        StringBuilder out = new StringBuilder(base);
        for (int d = 0; d < dims; d++) out.append("[]");
        return out.toString();
    }
}
