; public class Calc extends Object { ...}
.class public Calc
.super java/lang/Object

; public Calc() { super(); } // calls java.lang.Object()
.method public ()V
   aload_0
   invokenonvirtual java/lang/Object/()V
   return
.end method

; main(): Expr.g will generate bytecode in this method
.method public static main([Ljava/lang/String;)V
   .limit stack 4 ; how much stack space do we need?
   .limit locals 2 ; how many locals do we need?
   getstatic java/lang/System/out Ljava/io/PrintStream;
   ; code translated from input stream
   ; compute make"test_num3*(7+12)

   ldc 3
   ldc 7
   ldc 12
   iadd
   imul
   istore 1 ; test_num
   return
.end method
