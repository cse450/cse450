grammar LogoJVM1;

options{output=AST; ASTLabelType=CommonTree;}
tokens{BLOCK; PAREN; REF; VAR;}

prog: stat+;
stat: 'make' ref expr -> ^('make' ref expr);

expr: mexpr (('+'|'-')^ mexpr)*;
mexpr: atom (('*'|'/')^ atom)*;
atom: INT|var|'(' expr ')' -> ^(PAREN expr+);

ref: '"' ID -> ^(REF ID);
var: ':' ID -> ^(VAR ID);

ID: CHAR (CHAR|DIGIT)*;
INT: DIGIT+;

fragment CHAR: 'a'..'z'|'A'..'Z'|'_';
fragment DIGIT: '0'..'9';

WS: (' '|'\t'|'\r'|'\n')+ {skip();};