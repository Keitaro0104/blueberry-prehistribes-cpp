@echo off
REM build.bat - configure + build the port (MSVC, Release, x64, static CRT).
REM Requires Visual Studio with the C++ workload (see vcvars.bat).
setlocal
call "%~dp0vcvars.bat" || exit /b 1
cmake -S "%~dp0" -B "%~dp0build" -A x64 || exit /b 1
cmake --build "%~dp0build" --config Release || exit /b 1
echo Build OK: %~dp0build\Release\tribes.exe
echo          %~dp0build\Release\tribes-launcher.exe
endlocal
