@echo off
if exist output\__Test__.java del /q output\__Test__.java
javac output/*.java *.java
echo on 
