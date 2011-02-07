// START: header
grammar LogoAST;
options {output=AST;}
tokens { PAREN; }
// END: header

// START: stat
prog:	stat+ ;

stat:	'make' ref expr NL*  -> ^('make' ref expr)
    |	'print' expr NL* -> ^('print' expr)
    |   'if' condition '[' stat+ ']' NL* -> ^( 'if' condition stat+ )
    |   'while' '[' condition ']' '[' stat+ ']' NL* -> ^( 'while' condition stat+ )
    ;
// END: stat

condition: expr ( '<'^|'>'^|'<='^|'>='^ ) expr;

expr: multExpr (('+'^|'-'^) multExpr)* ;

multExpr
    :   primary (('*'^|'/'^) primary)*
    ;
    
primary
    :   INT
    |   ref
    |   '(' expr (',' expr)* ')' -> ^(PAREN expr+)
    ;
    
ref : ((':'^|'"'^) ID);


ID : ( '_' | CHAR )( '_' | CHAR | INT )* ;

fragment CHAR : 'a'..'z' | 'A'..'Z';

NL: '\r'? '\n';

COMMENT : ';' .* NL { skip(); };

INT : '0'..'9'+ ;
WS  :   (' '|'\t')+ {skip();} ;