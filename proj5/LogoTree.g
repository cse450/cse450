tree grammar LogoTree;

options {
	tokenVocab=LogoJVM1;
	ASTLabelType=CommonTree;
	output=template;
}

prog: (s+=stat)+ -> Output(instructions={$s});

stat: ^('make' ref expr) -> assign(a={$ref.st}, b={$expr.st});

ref: ^(REF ID) -> var(a={$ID.text});

expr:	^('+' a=expr b=expr) -> add(a={$a.st}, b={$b.st})
	|	^('-' a=expr b=expr) -> sub(a={$a.st}, b={$b.st})
	|	^('*' a=expr b=expr) -> mul(a={$a.st}, b={$b.st})
	|	^('/' a=expr b=expr) -> div(a={$a.st}, b={$b.st})
	|	INT -> int(a={$INT.text})
	|	VAR
	;

