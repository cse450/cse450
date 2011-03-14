@echo off
del /q output\*.*
java -cp ..\..\antlrworks-1.4.2.jar org.antlr.Tool -o ./output LogoTurtle.g
javac output/*.java *.java
echo Compilation complete.
echo on 
