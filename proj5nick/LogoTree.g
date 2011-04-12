// START:header
tree grammar LogoTree;

options {
    tokenVocab=LogoJVM1; // use the vocabulary from the parser
    ASTLabelType=CommonTree; // what kind of trees are we walking?
    output=template; // generate templates
}

@header {
import java.util.HashMap;
}

@members {
/** Points at locals table built by the parser */
HashMap locals;
}
// END:header

// START:prog
/** Match entire tree representing the arithmetic expressions. Pass in
 *  the number of operations and the locals table that the parser computed.
 *  Number of elements on stack is roughly number of operations + 1 and
 *  add one for the address of the System.out object. Number of locals =
 *  number of locals + parameters plus 'this' if non-static method.
 */
prog[int numOps, HashMap locals]
@init {
this.locals = locals; // point at map created in parser
}
    :   (s+=stat)+ -> jasminFile(instructions={$s},
                                 maxStackDepth={numOps+1+1},
                                 maxLocals={locals.size()+1})
    ;
// END:prog

// START:stat
stat:   expr -> exprStat(v={$expr.st}, descr={$expr.text})
    |   ^('make' ID expr)
        -> assign(id={$ID.text},
                  descr={$text},
                  varNum={locals.get($ID.text)},
                  v={$expr.st})
    ;
// END:stat

// START:expr
expr returns [int value]
    :   ^('+' a=expr b=expr) -> add(a={$a.st},b={$b.st})
    |   ^('-' a=expr b=expr) -> sub(a={$a.st},b={$b.st})
    |   ^('*' a=expr b=expr) -> mult(a={$a.st},b={$b.st})
    |	INT -> int(v={$INT.text})
    |   ID  -> var(id={$ID.text}, varNum={locals.get($ID.text)})
    ;
// END:expr
