tree grammar LogoTree;

options {
	tokenVocab=LogoJVM1;
	ASTLabelType=CommonTree;
	output=template;
}

@header{
	import java.util.HashMap;
	import java.io.*;
}

@members{
	HashMap locals;
}

prog [int numOps, HashMap locals]
	@init{ this.locals = locals; } 
	:(s+=stat)+ -> Output(instructions={$s},maxStackDepth={numOps+2},maxLocals={locals.size()+3});

stat: ^('make' ref expr) -> assign(expression={$expr.st}, varNum={locals.get($ref.id)})
    | PD -> pendown()
    | PU -> penup()
    | ^(FD expr) -> fwd(dist={$expr.st})
    | ^(BK expr) -> back(dist={$expr.st})
    | ^(LT2 expr) -> left(dist={$expr.st})
    | ^(RT expr) -> right(dist={$expr.st})
    | ^(SETH expr) -> seth(dist={$expr.st})
    | ^(SETP x=expr y=expr) -> setp(x={$x.st}, y={$y.st})
    | ^(CIRC r=expr angle=expr) -> circ(r={$r.st}, angle={$angle.st})
    | ^(SPC r=expr g=expr b=expr) -> spc(r={$r.st}, g={$g.st}, b={$b.st})
    | BEGF -> begf()
    | ENDF -> endf()
    ;

ref returns [String id] : ^(REF ID) { $id = $ID.text; };
var returns [String id] : ^(VAR ID) { $id = $ID.text; };

expr:	^('+' a=expr b=expr) -> add(a={$a.st}, b={$b.st})
    |	^('-' a=expr b=expr) -> sub(a={$a.st}, b={$b.st})
    |	^('*' a=expr b=expr) -> mul(a={$a.st}, b={$b.st})
    |	^('/' a=expr b=expr) -> div(a={$a.st}, b={$b.st})
    |	INT -> int(i={$INT.text})
    |	DBL -> dbl(d={$DBL.text})
    |	var -> var(varNum = {locals.get($var.id)})
    |   paren -> paren(exprs={$paren.enclosedExprs})
    ;

paren returns [List<CommonTree> enclosedExprs]: ^(PAREN (exprs+=expr)+) { $enclosedExprs = $exprs; };
