// START: header
grammar LogoTurtle;
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

TO = 'to';
IF = 'if';
IFELSE = 'ifelse';
WHILE = 'while';
PRINT = 'print';
ASSIGN = 'make';

REF = '"';
VAL = ':';

PD;
PU = 'pu';
FD = 'fd';
BK = 'bk';
LT2 = 'lt2';
RT = 'rt';
SETH = 'seth';
SETP = 'setp';
CIRC = 'circ';
SPC = 'spc';
BEGF = 'begf';
ENDF = 'endf';

}
// END: header

// START: stat
prog: multistat;

multistat : stat+ -> ^(BLOCK stat+); 

stat:	'to' ID val* NL* multistat 'end' -> ^('to' ID val* multistat)
    |	'make' ref expr NL*  -> ^('make' ref expr)
    |	'print' printablestat NL* -> ^('print' printablestat)
    |	'(print' printablestat+ ')' NL* -> ^('print' printablestat+)
    |   'if' NL* negateablecondition NL* '[' NL* multistat NL* ']' NL* -> ^( 'if' negateablecondition multistat )
    |	'ifelse' NL* negateablecondition NL* '[' NL* ifcode=multistat NL* ']' NL* '[' NL* elsecode=multistat NL* ']' NL* -> ^( 'ifelse' negateablecondition $ifcode $elsecode )
    |   'while' NL* '[' NL* negateablecondition NL* ']' NL* '[' NL* multistat NL* ']' NL* -> ^( 'while' negateablecondition multistat )
	|	('pd'|'pendown') NL* -> ^(PD)
	|	('pu'|'penup') NL* -> ^(PU)
	|	('fd'|'forward') expr NL* -> ^(FD expr)
	|	('bk'|'back') expr NL* -> ^(BK expr)
	|	('lt'|'left') expr-> ^(LT2 expr)
	|	('rt'|'right') expr NL* -> ^(RT expr)
	|	('seth'|'setheading') expr NL* -> ^(SETH expr)
	|	'setpos' '[' expr expr ']' NL* -> ^(SETP expr expr)
	|	'circle' expr expr NL* -> ^(CIRC expr expr)
	|	'setpencolor' '[' expr expr expr ']' NL* -> ^(SPC expr expr expr)
	|	'beginfill' NL* -> ^(BEGF)
	|	'endfill' NL* -> ^(ENDF)
    ;
// END: stat

printablestat: expr;//|ref;

returnstat
	:	'output' expr NL*;

negateablecondition : 'not'^? condition;

condition: expr ( '<'^|'>'^|'<='^|'>='^|'='^ ) expr;

expr: multExpr (('+'^|'-'^) multExpr)*
    | 'modulo' expr expr -> ^('modulo' expr expr);

multExpr
    :   primary (('*'^|'/'^) primary)*
    ;
    
primary
    :   INT
    |   FLOAT
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
FLOAT : INT '.' INT;
WS  :   (' '|'\t')+ {skip();} ;
