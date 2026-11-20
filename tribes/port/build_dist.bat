@echo off
REM build_dist.bat - build Release, then assemble a self-contained dist
REM folder (tribes.exe + tribes-launcher.exe + README) that can be zipped
REM and shared. No installer; the user runs tribes-launcher.exe first.
setlocal
call "%~dp0build.bat" || exit /b 1
set "DIST=%~dp0dist"
if exist "%DIST%" rmdir /s /q "%DIST%"
mkdir "%DIST%"
copy "%~dp0build\Release\tribes.exe" "%DIST%\" || exit /b 1
copy "%~dp0build\Release\tribes-launcher.exe" "%DIST%\" || exit /b 1
copy "%~dp0..\..\README.md" "%DIST%\README.md" >nul 2>&1
echo Dist assembled in %DIST%
echo Run check_dist.ps1 to verify it, then zip the folder for release.
endlocal
