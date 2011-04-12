grammar LogoJVM1;

options{output=AST; ASTLabelType=CommonTree;}
tokens{BLOCK; PAREN; REF; VAR;}

@header {
	import java.util.HashMap;
}

@members {
	int numOps = 0;
	HashMap locals = new HashMap();
	int localVarNum = 1;
}

prog: stat+;
stat: 'make' ref expr {
	if( locals.get($ref.id) == null ){
		locals.put( $ref.id, new Integer(localVarNum++) );
	}
} -> ^('make' ref expr);

expr: mexpr (('+'|'-')^ mexpr { numOps++; } )*;
mexpr: atom (('*'|'/')^ atom { numOps++; } )*;
atom: INT|var|'(' expr ')' -> ^(PAREN expr+);

ref returns [String id]: '"' ID {$id = $ID.text;} -> ^(REF ID);
var: ':' ID -> ^(VAR ID);

ID: CHAR (CHAR|DIGIT)*;
INT: DIGIT+;

fragment CHAR: 'a'..'z'|'A'..'Z'|'_';
fragment DIGIT: '0'..'9';

WS: (' '|'\t'|'\r'|'\n')+ {skip();};