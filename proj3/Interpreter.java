
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class Interpreter {
    
    CommonTree root;               // the AST represents our code memory
    TokenRewriteStream tokens;
    LogoAST2Lexer lex;              // lexer/parser are part of the processor
    LogoAST2Parser parser;
    MemorySpace space = new MemorySpace("main");

    public void interp(InputStream input) throws RecognitionException, IOException {
        lex = new LogoAST2Lexer(new ANTLRInputStream(input));
        tokens = new TokenRewriteStream(lex);
        parser = new LogoAST2Parser(tokens);

        LogoAST2Parser.prog_return r = parser.prog();
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
                case LogoAST2Parser.BLOCK : 	block(t); break;
                case LogoAST2Parser.ASSIGN : 	assign(t); break;
                case LogoAST2Parser.PRINT : 	print(t); break;
                case LogoAST2Parser.IF : 		ifstat(t); break;
                case LogoAST2Parser.WHILE : 	whileloop(t); break;
                case LogoAST2Parser.ADD : 		return op(t);
                case LogoAST2Parser.SUB : 		return op(t);
                case LogoAST2Parser.MUL : 		return op(t);
                case LogoAST2Parser.DIV : 		return op(t);
                case LogoAST2Parser.EQ : 		return eq(t); 
                case LogoAST2Parser.LT : 		return lt(t); 
                case LogoAST2Parser.INT : 		return Integer.parseInt(t.getText()); 
				case LogoAST2Parser.PAREN : 	return paren(t);
                case LogoAST2Parser.VAL : 		return load(t);
				
                default : // catch unhandled node types
                    throw new UnsupportedOperationException("Node "+
                        t.getText()+"<"+t.getType()+"> not handled");
            }
        }
        catch (Exception e) {
	    System.out.print("Caught an error in the switch: ");
		System.out.println(e);
		System.out.println(t);
        }
        return null;
    }

    public void block(CommonTree t) {
	System.out.println("Entered BLOCK");
        if ( t.getType()!=LogoAST2Parser.BLOCK ) {
	    System.out.println("Problem with BLOCK");
        }
        List<CommonTree> stats = t.getChildren();
        for (CommonTree x : stats) {
	    // System.out.println("Running expr"+x.toStringTree());
	    exec(x);
	}
    }

    public void print(CommonTree t) {
		System.out.print("PRINT: ");
        CommonTree expr = (CommonTree)t.getChild(0);
        System.out.println( exec(expr) );
    }

    public void assign(CommonTree t) {
	System.out.print("Entered ASSIGN: ");
	System.out.println(t.getChild(0).getChild(0).getText());

        CommonTree lhs = (CommonTree)t.getChild(0).getChild(0);   // get operands
        CommonTree expr = (CommonTree)t.getChild(1);
        Object value = exec(expr);            // walk/evaluate expr
        space.put(lhs.getText(), value);         // store
		System.out.println(lhs.getText());
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
		System.out.println("Entered OP");
        Object a = exec( (CommonTree)t.getChild(0) );
        Object b = exec( (CommonTree)t.getChild(1) );
        if ( a instanceof Float || b instanceof Float ) {
            float x = ((Number)a).floatValue();
            float y = ((Number)b).floatValue();
            switch (t.getType()) {
                case LogoAST2Parser.ADD : return x + y;
                case LogoAST2Parser.SUB : return x - y;
                case LogoAST2Parser.MUL : return x * y;
                case LogoAST2Parser.DIV : return x / y;
            }
        }
        if ( a instanceof Integer || b instanceof Integer ) {
	    System.out.println("Operating on Ints");
	    System.out.println(t.getType());
            int x = ((Number)a).intValue();
            int y = ((Number)b).intValue();
            switch (t.getType()) {
                case LogoAST2Parser.ADD : return x + y;
                case LogoAST2Parser.SUB : return x - y;
                case LogoAST2Parser.MUL : return x * y;
                case LogoAST2Parser.DIV : return x / y;
            }
        }
        return 0;
    }

	public Object paren(CommonTree t) {
		System.out.println("Entered PAREN");
		return exec((CommonTree)t.getChild(0));
	}
		
    public Object load(CommonTree t) {
		return space.get(t.getChild(0).getText());
    }


}
