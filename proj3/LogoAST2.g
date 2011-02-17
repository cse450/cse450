// START: header
grammar LogoAST2;
options {output=AST;}
tokens {
BLOCK;
PAREN;

ADD = '+';
SUB = '-';
MUL = '*';
DIV = '/';
MOD = 'modulo';

EQ = '=';
LT = '<';
LTE = '<=';
GT = '>';
GTE = '>=';
NOT = 'not';

IF = 'if';
IFELSE = 'ifelse';
WHILE = 'while';
PRINT = 'print';
MAKE = 'make';

REF = '"';
VAL = ':';
}
// END: header

// START: stat
prog:	stat+ ;

stat:	'make' ref expr NL*  -> ^('make' ref expr)
    |	'print' expr NL* -> ^('print' expr)
    |   'if' negateablecondition '[' stat+ ']' NL* -> ^( 'if' negateablecondition stat+ )
    |   'while' '[' negateablecondition ']' '[' stat+ ']' NL* -> ^( 'while' negateablecondition stat+ )
    ;
// END: stat

negateablecondition : 'not'^? condition;

condition: expr ( '<'^|'>'^|'<='^|'>='^|'='^ ) expr;

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