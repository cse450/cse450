@echo off

set jasFile=%1

echo Compiling:
java -jar ..\jasmin.jar %jasFile%
echo Running:
java %jasFile:~0,-2%
@echo on
