// START: header
grammar LogoAST;
options {output=AST;} // we want to create ASTs
tokens { PAREN; }     // define imaginary token for vector literal
// END: header

// START: stat
prog:	stat+ ;                         // build list of stat trees

stat:	'make' REF ID expr  -> ^('make' REF ID expr)  // '=' is operator subtree root
    |	'print' expr -> ^('print' expr) // 'print' is subtree root
    ;
// END: stat

// START: expr
expr:	multExpr ('+'^ multExpr)* ;     // '+' is root node

multExpr
    :   primary (('*'^|'.'^) primary)*  // '*', '.' are roots
    ;
    
primary
    :   INT
    |   REF ID
    |   '(' expr (',' expr)* ')' -> ^(PAREN expr+)
    ;
// END: expr

REF : ':'|'"';

ID : ( '_' | CHAR )( '_' | CHAR | INT )* ;

fragment CHAR : 'a'..'z' | 'A'..'Z';

INT : '0'..'9'+ ;
WS  :   (' '|'\r'|'\n')+ {skip();} ;