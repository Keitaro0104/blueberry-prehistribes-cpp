# check_dist.ps1 - verify a built dist folder (run after build_dist.bat).
# Checks the executables exist, are 64-bit, and that the headless smoke
# tests pass: --dump writes a non-blank frame, --bench runs the loop.
# Usage: powershell -ExecutionPolicy Bypass -File check_dist.ps1 [-Dist <path>]
param([string]$Dist = "$PSScriptRoot\dist")

$ErrorActionPreference = "Stop"

function Fail($msg) { Write-Error $msg; exit 1 }

if (-not (Test-Path "$Dist\tribes.exe")) { Fail "missing $Dist\tribes.exe (run build_dist.bat first)" }
if (-not (Test-Path "$Dist\tribes-launcher.exe")) { Fail "missing $Dist\tribes-launcher.exe (run build_dist.bat first)" }

# 64-bit PE check (machine type 0x8664 at offset 4 of the COFF header).
foreach ($exe in "tribes.exe", "tribes-launcher.exe") {
    $bytes = [System.IO.File]::ReadAllBytes("$Dist\$exe")
    $peOff = [BitConverter]::ToInt32($bytes, 0x3C)
    $machine = [BitConverter]::ToUInt16($bytes, $peOff + 4)
    if ($machine -ne 0x8664) { Fail "$exe is not a 64-bit PE (machine 0x$($machine.ToString('X4')))" }
    Write-Host "$exe OK (64-bit PE)"
}

# The smoke tests need game data. If there is no data folder yet, create
# one from the local extracted JAR contents (CI does the same from its
# own checkout - the data is never committed to the repo).
$dataDir = "$Dist\data"
if (-not (Test-Path "$dataDir\ma")) {
    Write-Host "no game data in $dataDir - skipping --dump/--bench (run the launcher first, or copy extracted JAR files into $dataDir)"
    Write-Host "check_dist.ps1: PARTIAL (binaries OK, smoke tests skipped)"
    exit 0
}

# --dump: render a frame and check the BMP is non-blank.
$bmp = "$env:TEMP\tribes-smoke.bmp"
if (Test-Path $bmp) { Remove-Item $bmp }
& "$Dist\tribes.exe" --dump $bmp 8 --trace
if ($LASTEXITCODE -ne 0) { Fail "--dump exited with code $LASTEXITCODE" }
if (-not (Test-Path $bmp)) { Fail "--dump did not write $bmp" }
$size = (Get-Item $bmp).Length
if ($size -lt 1000) { Fail "--dump BMP is suspiciously small ($size bytes)" }
# A blank frame is all one colour: sample the BMP pixel data for variety.
$bytes = [System.IO.File]::ReadAllBytes($bmp)
$header = 54  # 24-bit BMP written by Surface::writeBmp
$distinct = @{}
for ($i = $header; $i -lt $bytes.Length - 2; $i += 997) {
    $distinct["$($bytes[$i]),$($bytes[$i+1]),$($bytes[$i+2])"] = $true
}
if ($distinct.Count -lt 4) { Fail "--dump BMP looks blank (only $($distinct.Count) distinct sampled colours)" }
Write-Host "--dump OK ($size bytes, $($distinct.Count) distinct sampled colours)"

# --bench: the loop must actually run (frames presented > 0).
$bench = & "$Dist\tribes.exe" --bench 5 2>&1
Write-Host $bench
if ($bench -notmatch "(\d+) frames presented") { Fail "--bench printed no frame count" }
if ([int]$Matches[1] -le 0) { Fail "--bench presented 0 frames - the game loop did not run" }
Write-Host "--bench OK ($($Matches[1]) frames in 5s)"

Write-Host "check_dist.ps1: PASS"
