@echo off
REM vcvars.bat - locate the Visual Studio C++ environment and load it.
REM The environment changes must persist to the caller, so the search
REM runs inside a setlocal block and only the found path escapes it
REM (the classic "endlocal & set" trick).
setlocal
set "VSCMD="
for %%E in (Community Professional Enterprise BuildTools) do (
  if exist "%ProgramFiles%\Microsoft Visual Studio\2022\%%E\VC\Auxiliary\Build\vcvars64.bat" set "VSCMD=%ProgramFiles%\Microsoft Visual Studio\2022\%%E\VC\Auxiliary\Build\vcvars64.bat"
)
if "%VSCMD%"=="" for %%E in (Community Professional Enterprise BuildTools) do (
  if exist "%ProgramFiles(x86)%\Microsoft Visual Studio\2019\%%E\VC\Auxiliary\Build\vcvars64.bat" set "VSCMD=%ProgramFiles(x86)%\Microsoft Visual Studio\2019\%%E\VC\Auxiliary\Build\vcvars64.bat"
)
if "%VSCMD%"=="" (
  where vswhere >nul 2>&1 && (
    for /f "usebackq delims=" %%P in (`vswhere -latest -products * -requires Microsoft.VisualStudio.Component.VC.Tools.x86.x64 -property installationPath`) do set "VSCMD=%%P\VC\Auxiliary\Build\vcvars64.bat"
  )
)
if "%VSCMD%"=="" (
  echo vcvars.bat: no Visual Studio with the C++ workload found.
  echo Install Visual Studio 2022 Desktop development with C++ and retry.
  exit /b 1
)
endlocal & set "VSCMD=%VSCMD%"
call "%VSCMD%"
