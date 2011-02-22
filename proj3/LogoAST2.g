// START: header
grammar LogoAST2;
options {
	output=AST;
	ASTLabelType=CommonTree;
	}
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
ASSIGN = 'make';

REF = '"';
VAL = ':';
}
// END: header

// START: stat
prog:	stat+ ->^(BLOCK stat+);

stat:	'make' ref expr NL*  -> ^('make' ref expr)
    |	'print' expr NL* -> ^('print' expr)
    |	'(print' expr+ ')' NL* -> ^('print' expr+)
    |   'if' negateablecondition '[' stat+ ']' NL* -> ^( 'if' negateablecondition ^(BLOCK stat+) )
    |   'while' '[' negateablecondition ']' '[' stat+ ']' NL* -> ^( 'while' negateablecondition ^(BLOCK stat+) )
    ;
// END: stat

negateablecondition : 'not'^? condition;

condition: expr ( '<'^|'>'^|'<='^|'>='^|'='^ ) expr;

expr: multExpr (('+'^|'-'^) multExpr)*
    | 'modulo' expr expr -> ^('modulo' expr expr);

multExpr
    :   primary (('*'^|'/'^) primary)*
    ;
    
primary
    :   INT
    |   val
    |   '(' expr ')' -> ^(PAREN expr+)
    ;
    
ref : ('"' ID) -> ^(REF ID);
val : (':' ID) -> ^(VAL ID);


ID : ( '_' | CHAR )( '_' | CHAR | INT )* ;

fragment CHAR : 'a'..'z' | 'A'..'Z';

NL: '\r'? '\n';

COMMENT : ';' .* NL { skip(); };

INT : '0'..'9'+ ;
WS  :   (' '|'\t')+ {skip();} ;