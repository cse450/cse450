@echo off
del /q output\*.*
java -cp ..\..\antlrworks-1.4.2.jar org.antlr.Tool -o ./output *.g
javac -cp  ..\..\antlrworks-1.4.2.jar;..\turtle.jar;.;output;..\turtle.jar output/*.java *.java
echo Compilation complete.
echo on 
