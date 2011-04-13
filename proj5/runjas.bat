@echo off

set jasFile=%1

echo Compiling:
java -jar ..\jasmin.jar %jasFile%
echo Running:
java -cp .;output/*;..\turtle.jar %jasFile:~0,-2%
@echo on
