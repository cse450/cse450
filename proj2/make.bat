@echo off
if exist output\__Test__.java del /q output\__Test__.java
javac Test.java output/*.java
echo on 
