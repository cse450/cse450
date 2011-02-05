// START: header
grammar LogoAST;
options {output=AST;} // we want to create ASTs
tokens { PAREN; }     // define imaginary token for vector literal
// END: header

// START: stat
prog:	stat+ ;                         // build list of stat trees

stat:	'make' REF ID expr (NL|COMMENT)  -> ^('make' ID expr)
    |	'print' expr (NL|COMMENT) -> ^('print' expr) // 'print' is subtree root
    ;
// END: stat

// START: expr
expr:	multExpr ('+'^ multExpr)* ;     // '+' is root node

multExpr
    :   primary (('*'^|'/'^) primary)*  // '*', '.' are roots
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

NL: '\n';

COMMENT : ';' .* NL { skip(); };

INT : '0'..'9'+ ;
WS  :   (' '|'\t'|'\r')+ {skip();} ;