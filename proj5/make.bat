@echo off
del /q output\*.*
java -cp ..\..\antlrworks-1.4.2.jar org.antlr.Tool -o ./output *.g
javac -cp  ..\..\antlrworks-1.4.2.jar;.;output output/*.java *.java
echo Compilation complete.
echo on 
