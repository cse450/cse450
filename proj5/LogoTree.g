tree grammar LogoTree;

options {
	tokenVocab=LogoJVM1;
	ASTLabelType=CommonTree;
	output=template;
}

@header{
	import java.util.HashMap;
}

@members{
	HashMap locals;
}

prog [int numOps, HashMap locals]
	@init{ this.locals = locals; } 
	:(s+=stat)+ -> Output(instructions={$s},maxStackDepth={numOps+1+2-1},maxLocals={locals.size()+1-2+2});

stat: ^('make' ref expr) -> assign(expression={$expr.st}, varNum={locals.get($ref.id)});

ref returns [String id] : ^(REF ID) { $id = $ID.text; };
var returns [String id] : ^(VAR ID) { $id = $ID.text; };

expr:	^('+' a=expr b=expr) -> add(a={$a.st}, b={$b.st})
	|	^('-' a=expr b=expr) -> sub(a={$a.st}, b={$b.st})
	|	^('*' a=expr b=expr) -> mul(a={$a.st}, b={$b.st})
	|	^('/' a=expr b=expr) -> div(a={$a.st}, b={$b.st})
	|	INT -> int(i={$INT.text})
	|	var -> var(varNum = {locals.get($var.id)})
	|       paren -> paren(expr={$paren.enclosedExprs})
	;

paren returns [List<CommonTree> enclosedExprs]: ^(PAREN (exprs+=expr)+) { $enclosedExprs = $exprs; };
