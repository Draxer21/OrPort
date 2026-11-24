@echo off
setlocal
set "JAVA_CMD=java"
if defined JAVA_HOME set "JAVA_CMD=%JAVA_HOME%\bin\java.exe"
"%JAVA_CMD%" -jar "%~dp0gradle\wrapper\gradle-wrapper.jar" %*
exit /b %ERRORLEVEL%
