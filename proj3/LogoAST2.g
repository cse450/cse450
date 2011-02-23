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
    |   'if' NL* negateablecondition NL* '[' NL* stat+ NL* ']' NL* -> ^( 'if' negateablecondition ^(BLOCK stat+) )
    |   'while' NL* '[' NL* negateablecondition NL* ']' NL* '[' NL* stat+ NL* ']' NL* -> ^( 'while' negateablecondition ^(BLOCK stat+) )
    |	'ifelse' NL* negateablecondition NL* '[' NL* stat+ NL* ']' NL* '[' NL* stat+ NL* ']' NL* -> ^( 'ifelse' negateablecondition ^(BLOCK stat+) )
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
    |	ref
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