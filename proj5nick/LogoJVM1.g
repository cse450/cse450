// START:header
/** Create AST and compute ID -> local variable number map */
grammar LogoJVM1;
options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

@header {
import java.util.HashMap;
}

@members {
int numOps = 0; // track operations for stack size purposes
HashMap locals = new HashMap(); // map ID to local var number
/* Count local variables, but don't use 0, which in this case
 * is the String[] args parameter of the main method.
 */
int localVarNum = 1; 
}
// END:header

// START:stat
prog:   stat+ ;

stat:   expr NEWLINE        -> expr
    |   'make' REF ID expr NEWLINE
        {
        if ( locals.get($ID.text)==null ) {
            locals.put($ID.text, new Integer(localVarNum++));
        }
        }
        -> ^('make' ID expr)
    |   NEWLINE             ->
    ;
// END:stat

// START:expr
expr:   multExpr (('+'^|'-'^) multExpr {numOps++;})*
    ; 

multExpr:   atom ('*'^ atom {numOps++;})*
    ; 

atom:   INT 
    |   ID
    |   '('! expr ')'!
    ;
// END:expr

// START:tokens
ID : ( '_' | CHAR )( '_' | CHAR | INT )* ;
fragment CHAR : 'a'..'z' | 'A'..'Z';

INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t'|'\n'|'\r')+ {skip();} ;
REF :	'"';
VAL :  	':';         

COMMENT : ';' .* NL { skip(); };
// END:tokens
