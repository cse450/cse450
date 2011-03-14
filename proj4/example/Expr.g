grammar Expr;
options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

tokens {BLOCK; ASSIGN='='; ADD='+'; SUB='-'; MUL='*';
PRINT='print'; IF='if'; WHILE='while'; EQ='=='; LT='<'; }

// START:stat
/** Match a series of stat rules and, for each one, print out the
 *  tree stat returns, $stat.tree.  toStringTree() prints the tree
 *  out in form: (root child1 ... childN).  ANTLR's default tree 
 *  construction mechanism will build a list (flat tree) of the stat
 *  result trees.  This tree will be the input to the tree parser.
 */
prog:   stat+ -> ^(BLOCK stat+) ;



stat:   expr NEWLINE        -> expr
    |   ID '=' expr NEWLINE -> ^('=' ID expr)
    |	'print' expr NEWLINE -> ^('print' expr)
    |   'if' boolExpr ifstmt=block ('else' elsestmt=block)?  -> ^('if' boolExpr $ifstmt $elsestmt?)
    |   'while' boolExpr block   -> ^('while' boolExpr block)
    |   NEWLINE             ->
    ;

block	:	'begin' NEWLINE stat+ 'end' NEWLINE -> ^(BLOCK stat+)
	;

// START:expr

boolExpr:	expr (('=='|'<')^ expr)
	;

expr:   multExpr (('+'^|'-'^) multExpr)*
    ; 

multExpr
    :   atom ('*'^ atom)*
    ; 

atom:   INT 
    |   ID
    |   '('! expr ')'!
    ;
// END:expr

// START:tokens
ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {$channel=HIDDEN;} ;
// END:tokens
