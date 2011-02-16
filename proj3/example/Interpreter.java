
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class Interpreter {
    
    CommonTree root;               // the AST represents our code memory
    TokenRewriteStream tokens;
    ExprLexer lex;              // lexer/parser are part of the processor
    ExprParser parser;
    MemorySpace space = new MemorySpace("main");

    public void interp(InputStream input) throws RecognitionException, IOException {
        lex = new ExprLexer(new ANTLRInputStream(input));
        tokens = new TokenRewriteStream(lex);
        parser = new ExprParser(tokens);

        ExprParser.prog_return r = parser.prog();
        if ( parser.getNumberOfSyntaxErrors()==0 ) {
            root = (CommonTree)r.getTree();
            System.out.println("tree: "+root.toStringTree());
            block(root);
        }
    }

    /** visitor dispatch according to node token type */
    public Object exec(CommonTree t) {
        try {
            switch ( t.getType() ) {
                case ExprParser.BLOCK : block(t); break;
                case ExprParser.ASSIGN : assign(t); break;
                case ExprParser.PRINT : print(t); break;
                case ExprParser.IF : ifstat(t); break;
                case ExprParser.WHILE : whileloop(t); break;
                case ExprParser.ADD : return add(t);
                case ExprParser.SUB : return op(t);
                case ExprParser.MUL : return op(t);
                case ExprParser.EQ : return eq(t);
                case ExprParser.LT : return lt(t);
                case ExprParser.INT : return Integer.parseInt(t.getText());
                case ExprParser.ID :
                    return load(t);
                default : // catch unhandled node types
                    throw new UnsupportedOperationException("Node "+
                        t.getText()+"<"+t.getType()+"> not handled");
            }
        }
        catch (Exception e) {
	    System.out.println("Caught an error in the switch");
        }
        return null;
    }

    public void block(CommonTree t) {
	System.out.println("Entered BLOCK");
        if ( t.getType()!=ExprParser.BLOCK ) {
	    System.out.println("Problem with BLOCK");
        }
        List<CommonTree> stats = t.getChildren();
        for (CommonTree x : stats) {
	    // System.out.println("Running expr"+x.toStringTree());
	    exec(x);
	}
    }

    public void print(CommonTree t) {
        CommonTree expr = (CommonTree)t.getChild(0);
        System.out.println( exec(expr) );
    }

    public void assign(CommonTree t) {
	System.out.println("Entered ASSIGN");
        CommonTree lhs = (CommonTree)t.getChild(0);   // get operands
        CommonTree expr = (CommonTree)t.getChild(1);
        Object value = exec(expr);            // walk/evaluate expr
        space.put(lhs.getText(), value);         // store
    }

    public void whileloop(CommonTree t) {
        CommonTree condStart = (CommonTree)t.getChild(0);
        CommonTree codeStart = (CommonTree)t.getChild(1);
        Boolean c = (Boolean)exec(condStart);
        while ( c ) {
            exec(codeStart);
            c = (Boolean)exec(condStart);
        }
    }

    public void ifstat(CommonTree t) {
        CommonTree condStart = (CommonTree)t.getChild(0);
        CommonTree codeStart = (CommonTree)t.getChild(1);
        CommonTree elseCodeStart = null;
        if ( t.getChildCount()==3 ) elseCodeStart = (CommonTree)t.getChild(2);
        Boolean c = (Boolean)exec(condStart);
        if ( ((Boolean)c).booleanValue() ) exec(codeStart);
        else if ( elseCodeStart!=null ) exec(elseCodeStart);
    }

    public boolean eq(CommonTree t) {
        Object a = exec( (CommonTree)t.getChild(0) );
        Object b = exec( (CommonTree)t.getChild(1) );
        return a.equals(b);
    }

    public boolean lt(CommonTree t) {
        Object a = exec( (CommonTree)t.getChild(0) );
        Object b = exec( (CommonTree)t.getChild(1) );
        if ( a instanceof Number && b instanceof Number ) {
            Number x = (Number)a;
            Number y = (Number)b;
            return x.floatValue() < y.floatValue();
        }
        return false;
    }

    public Object op(CommonTree t) {
	System.out.println("Entered op");
        Object a = exec( (CommonTree)t.getChild(0) );
        Object b = exec( (CommonTree)t.getChild(1) );
        if ( a instanceof Float || b instanceof Float ) {
            float x = ((Number)a).floatValue();
            float y = ((Number)b).floatValue();
            switch (t.getType()) {
                case ExprParser.ADD : return x + y;
                case ExprParser.SUB : return x - y;
                case ExprParser.MUL : return x * y;
            }
        }
        if ( a instanceof Integer || b instanceof Integer ) {
	    System.out.println("Adding ints");
            int x = ((Number)a).intValue();
            int y = ((Number)b).intValue();
            switch (t.getType()) {
                case ExprParser.ADD : return x + y;
                case ExprParser.SUB : return x - y;
                case ExprParser.MUL : return x * y;
            }
        }
        return 0;
    }

    public Object add(CommonTree t) {
	System.out.println("Entered ADD");
        Object a = exec( (CommonTree)t.getChild(0) );
        Object b = exec( (CommonTree)t.getChild(1) );
        if ( a instanceof String || b instanceof String ) {
            return a.toString() + b.toString();
        }
        return op(t);
    }

    public Object load(CommonTree t) {
	return space.get(t.getText());
    }


}
