grammar LogoJVM1;

options{output=AST; ASTLabelType=CommonTree;}
tokens{BLOCK; PAREN; REF; VAR;
       PD = 'pd'; PU = 'pu'; FD = 'fd'; 
       BK = 'bk'; LT2 = 'lt2'; RT = 'rt';
       SETH = 'seth'; SETP = 'setp'; CIRC = 'circ'; 
       SPC = 'spc'; BEGF = 'begf'; ENDF = 'endf'; }

@header {
	import java.util.HashMap;
	import java.io.*;
}

@members {
	int numOps = 0;
	HashMap locals = new HashMap();
	int localVarNum = 3;
}

prog: stat+;
stat : 'make' ref expr { if( locals.get($ref.id) == null ){
			     locals.put( $ref.id, new Integer(localVarNum++) );
			 }
	               } -> ^('make' ref expr)
     |	('pd'|'pendown') -> ^(PD)
     |	('pu'|'penup') -> ^(PU)
     |	('fd'|'forward') expr -> ^(FD expr)
     |	('bk'|'back') expr -> ^(BK expr)
     |	('lt'|'left') expr -> ^(LT2 expr)
     |	('rt'|'right') expr -> ^(RT expr)
     |	('seth'|'setheading') expr -> ^(SETH expr)
     |	'setpos' '[' expr expr ']' -> ^(SETP expr expr)
     |	'circle' expr expr -> ^(CIRC expr expr)
     |	'setpencolor' '[' expr expr expr ']' -> ^(SPC expr expr expr)
     |	'beginfill' -> ^(BEGF)
     |	'endfill' -> ^(ENDF)
     ;

expr: mexpr (('+'|'-')^ mexpr { numOps++; } )*;
mexpr: atom (('*'|'/')^ atom { numOps++; } )*;
atom: INT|var|'(' expr ')' -> ^(PAREN expr+);

ref returns [String id]: '"' ID {$id = $ID.text;} -> ^(REF ID);
var returns [String id]: ':' ID {$id = $ID.text;} -> ^(VAR ID);

ID: CHAR (CHAR|DIGIT)*;
INT: DIGIT+;

fragment CHAR: 'a'..'z'|'A'..'Z'|'_';
fragment DIGIT: '0'..'9';

WS: (' '|'\t')+ {skip();};

NL: '\r'?'\n' {skip();};

COMMENT: ';' .* NL { skip(); };

