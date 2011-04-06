
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import msu.cse.turtlegraphics.Turtle;
import msu.cse.turtlegraphics.TurtleDisplayFrame;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.tree.CommonTree;

public class Interpreter {
    
    CommonTree root;               // the AST represents our code memory
    TokenRewriteStream tokens;
    LogoJVM1Lexer lex;              // lexer/parser are part of the processor
    LogoJVM1Parser parser;

    ArrayDeque <MemorySpace> scopeStack = new ArrayDeque <MemorySpace> ();
	Iterator <MemorySpace> scopeIter; 

	ArrayDeque <Value> retStack = new ArrayDeque <Value> ();

	public boolean isdebugging = false;

////// turtle:
	Turtle turtle = new Turtle();
	TurtleDisplayFrame frame = new TurtleDisplayFrame();
////// :turtle

	public Interpreter()
	{
		scopeStack.add( new MemorySpace("main") );
	}
	
	private void debug (String st)
	{ 
		if(isdebugging){
			System.out.println(st);
		}
	}

	public void interp(InputStream input) throws RecognitionException, IOException {
			lex = new LogoJVM1Lexer(new ANTLRInputStream(input));
			tokens = new TokenRewriteStream(lex);
			parser = new LogoJVM1Parser(tokens);

			LogoJVM1Parser.prog_return r = parser.prog();
			if ( parser.getNumberOfSyntaxErrors()==0 ) {
					root = (CommonTree)r.getTree();
					debug("tree: "+root.toStringTree());
////// turtle:
	frame.setVisible(true);
	turtle.setCurrentTurtleDisplayCanvas(frame.getCurrentCanvas());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////// :turtle
				try {
					block(root);
				}
				catch ( FunctionReturnException ex ) {
					//do nothing here.
				}
////// turtle:
	frame.dispose();
	frame = null;
////// :turtle
			}
	}

	/** visitor dispatch according to node token type */
	public Object exec(CommonTree t) throws FunctionReturnException { 
		try {
				switch ( t.getType() ) {
						case LogoJVM1Parser.BLOCK : 		block(t); 		break;
						case LogoJVM1Parser.ASSIGN : 		assign(t); 		break;
						case LogoJVM1Parser.PRINT : 		print(t); 		break;
						case LogoJVM1Parser.IF : 			ifstat(t); 		break;
						case LogoJVM1Parser.IFELSE : 		ifelsestat(t); 	break;
						case LogoJVM1Parser.WHILE : 		whileloop(t); 	break;
						case LogoJVM1Parser.TO :			fndef(t); 		break;

						case LogoJVM1Parser.ADD : 		return op(t);
						case LogoJVM1Parser.SUB : 		return op(t);
						case LogoJVM1Parser.MUL : 		return op(t);
						case LogoJVM1Parser.DIV : 		return op(t);
						case LogoJVM1Parser.MOD :     	return op(t);
						case LogoJVM1Parser.EQ : 			return eq(t); 
						case LogoJVM1Parser.LT : 			return lt(t);
						case LogoJVM1Parser.GT :    		return gt(t);
						case LogoJVM1Parser.LTE :    		return lte(t);
						case LogoJVM1Parser.GTE :   		return gte(t);
						case LogoJVM1Parser.NOT : 	  	return not(t);
						case LogoJVM1Parser.INT : 		return new Value( Integer.parseInt(t.getText()), LogoJVM1Parser.INT );
						case LogoJVM1Parser.FLOAT :    	return new Value( Float.parseFloat(t.getText()), LogoJVM1Parser.FLOAT );
						case LogoJVM1Parser.PAREN : 		return paren(t);
						case LogoJVM1Parser.REF :			return ref(t);
						case LogoJVM1Parser.VAL : 		return load(t);

						case LogoJVM1Parser.VALIST:		return valist(t);

						case LogoJVM1Parser.FNCALL:		return fncall(t);
						case LogoJVM1Parser.OUTPUT:		output(t); break;

						////// turtle:
						case LogoJVM1Parser.PD :	pd(t); break;
						case LogoJVM1Parser.PU :	pu(t); break;
						case LogoJVM1Parser.FD :	fd(t); break;
						case LogoJVM1Parser.BK :	bk(t); break;
						case LogoJVM1Parser.LT2 :	lt2(t); break;
						case LogoJVM1Parser.RT :	rt(t); break;
						case LogoJVM1Parser.SETH :	seth(t); break;
						case LogoJVM1Parser.SETP :	setp(t); break;
						case LogoJVM1Parser.CIRC :	circ(t); break;
						case LogoJVM1Parser.SPC :	spc(t); break;
						case LogoJVM1Parser.BEGF :	begf(t); break;
						case LogoJVM1Parser.ENDF :	endf(t); break;
						////// :turtle

						default : // catch unhandled node types
								throw new UnsupportedOperationException("Node "+
										t.getText()+"<"+t.getType()+"> not handled");
				}
		}
    catch (ClassCastException e) {
       DisplayError(t,e);
    }
    catch (ParseException e) {
       DisplayError(t,e);
    }
    catch (UnsupportedOperationException e) {
			DisplayError(t,e);
		}
			return null;
	}

  public void DisplayError( CommonTree t, Exception e ){
    System.out.print("Error: Interpretation failed at '");
    System.out.print(t);
		System.out.println("'");
		System.out.print( "    Exception thrown: " );
		System.out.println(e);
		System.exit(1);
  }

	public void output(CommonTree t) throws FunctionReturnException {
		debug("OUTPUT");
		throw new FunctionReturnException(t);
	}

	public ArrayList<String> valist(CommonTree t) {
		debug("Entered VALIST");

		ArrayList<String> a = new ArrayList<String>();

		@SuppressWarnings("unchecked")
		List<CommonTree> stats = t.getChildren();
		for (CommonTree x : stats) {
			a.add(x.getText());
		}
		return a;
	}


	public void fndef(CommonTree t) throws FunctionReturnException {
		debug("Entered TO");

		@SuppressWarnings("unchecked")
		ArrayList<String> params = (ArrayList)exec((CommonTree)t.getChild(1));
		String name = t.getChild(0).getText();

		scopeStack.peekLast().put(name,new Function(name,
											   		(CommonTree)t.getChild(2),
											   		params)
								);		
	}

	public Object fncall(CommonTree t) throws FunctionReturnException {
		debug("Entered CALL");

		// Get Function from memory space
		Function tmp = (Function)stload(t.getChild(0).getText());

		if(tmp != null){
			// Push new memoryspace to scope stack
			scopeStack.add(new MemorySpace(
								t.getChild(0).getText()
							));

			// Assign arguments
			
			int i = 1;
			for (String p : tmp.getParams()){
				debug(p);
				debug(t.getChild(i).getText());
                scopeStack.peekLast().put(p,exec((CommonTree)t.getChild(i)));
				i = i+1;
			}
			
			try {
				debug("Entered EXEC");
				exec(tmp.mBlock);
			}
			catch (FunctionReturnException e) {
				debug("Entered RETURN");
				return ((Value)exec((CommonTree)e.getRetval()));
			}
			finally 
			{
				scopeStack.pop();
			}


			//return null;
			
			// Catch a FunctionFinishedException, or something like that
			 
			// Return statement will push value to stack,
			// So we pop it

			// TODO: pop returnvar from stack

			//		we might not be able to make this void?
			
		}
		return null;
	}

	public void block(CommonTree t) throws FunctionReturnException {
		debug("Entered BLOCK");
		if ( t.getType()!=LogoJVM1Parser.BLOCK ) {
			debug("Problem with BLOCK");
		}
		@SuppressWarnings("unchecked")
		List<CommonTree> stats = t.getChildren();
		for (CommonTree x : stats) {
			// System.out.println("Running expr"+x.toStringTree());
			exec(x);
		}
	}

	public void print(CommonTree t) throws FunctionReturnException {
		debug("PRINT: ");
		//CommonTree expr = (CommonTree)t.getChild(0);
		//System.out.println( exec(expr) );
		// Extended for expression lists! //
		@SuppressWarnings("unchecked")
		List<CommonTree> exprs = t.getChildren();
		
		debug( exprs.toString() );
		for (CommonTree x : exprs) {
			if ( x.getType() == LogoJVM1Parser.REF )
			{
				System.out.print( x.getChild(0).getText() + " " );
			}
			else
			{
				Number val = ((Value)exec(x)).getValueBasedOnType();
				System.out.print( val + " ");
			}
		};
		System.out.println("");
	}


	public void whileloop(CommonTree t) throws FunctionReturnException {
		debug("Entered WHILE:");
		CommonTree condStart = (CommonTree)t.getChild(0);
		CommonTree codeStart = (CommonTree)t.getChild(1);
		Boolean c = (Boolean)exec(condStart);
		while ( c ) {
			exec(codeStart);
			c = (Boolean)exec(condStart);
		}
	}

	public void ifstat(CommonTree t) throws FunctionReturnException {
		debug("Entered IF");
		CommonTree condStart = (CommonTree)t.getChild(0);
		CommonTree codeStart = (CommonTree)t.getChild(1);
		Boolean c = (Boolean)exec(condStart);
		if ( ((Boolean)c).booleanValue() ) exec(codeStart);
	}

	public void ifelsestat(CommonTree t) throws FunctionReturnException {
		debug("Entered IFELSE");
		CommonTree condStart = (CommonTree)t.getChild(0);
		CommonTree codeStart = (CommonTree)t.getChild(1);
		CommonTree elseStart = (CommonTree)t.getChild(2);
		
		Boolean c = (Boolean)exec(condStart);
		if ( ((Boolean)c).booleanValue() )
		{
			debug( "in if codeblock" );
			exec(codeStart);
		}
		else
		{
			debug( "in else codeblock" );
			exec( elseStart );
		}
	}
	
	public boolean eq(CommonTree t) throws FunctionReturnException {
		debug("Entered EQ");
		Value a = (Value)exec( (CommonTree)t.getChild(0) );
		Value b = (Value)exec( (CommonTree)t.getChild(1) );
		return a.equals(b);
	}

	public boolean lt(CommonTree t) throws FunctionReturnException {
		debug("Entered LT");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		if ( a instanceof Value && b instanceof Value ) {
			Value x = (Value)a;
			Value y = (Value)b;
			return x.floatValue() < y.floatValue();
		}
		return false;
	}

	public boolean gt(CommonTree t) throws FunctionReturnException {
		debug("Entered GT");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		if ( a instanceof Value && b instanceof Value ) {
			Value x = (Value)a;
			Value y = (Value)b;
			return x.floatValue() > y.floatValue();
		}
		return false;
	}

	public boolean lte(CommonTree t) throws FunctionReturnException {
		debug("Entered LTE");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		if ( a instanceof Value && b instanceof Value ) {
			Value x = (Value)a;
			Value y = (Value)b;
			return x.floatValue() <= y.floatValue();
		}
		return false;
	}

	public boolean gte(CommonTree t) throws FunctionReturnException {
		debug("Entered GTE");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		if ( a instanceof Value && b instanceof Value ) {
			Value x = (Value)a;
			Value y = (Value)b;
			return x.floatValue() >= y.floatValue();
		}
		return false;
	}
	
	public boolean not(CommonTree t) throws FunctionReturnException {
		debug( "Entered NOT" );
		return !(Boolean)exec((CommonTree)t.getChild(0));
	}
	
	public Value op(CommonTree oper) throws ParseException, FunctionReturnException {
		debug("Entered OP");
		
		Value a = null;
		Value b = null;
		
		try {
			a = (Value)exec( (CommonTree)(oper.getChild(0) ) );
		  b = (Value)exec( (CommonTree)(oper.getChild(1) ) );
		}
		catch ( ClassCastException ex ) {
			throw new ParseException( "Cannot perform arithmetic operations on non-value types.", 0 );
		}
		
		Value retVal = null;
		
		if ( isInt( a ) && isInt( b ) ) {
	    
			int x = a.intValue();
			int y = b.intValue();
			
			retVal = new Value( performOperation( oper, x, y ), LogoJVM1Parser.INT );
		}
		else if ( isFloat( a ) && isFloat( b ) ) {
			float x = a.floatValue();
			float y = b.floatValue();

		  retVal = new Value( performOperation( oper, x, y ), LogoJVM1Parser.FLOAT );
		}
		else if ( ( isFloat( a ) && isInt( b ) ) || 
		          ( isInt( a ) && isFloat( b ) ) ) {
			System.out.println( "Warning: Promoting integer to float." );
			
			float x = a.floatValue();
			float y = b.floatValue();
			
			retVal =  new Value( performOperation( oper, x, y ), LogoJVM1Parser.FLOAT );
		}
		
		return retVal;
	}

	public Integer performOperation( CommonTree node, Integer x, Integer y ) {
		debug( "performing op on integers" );
		
		switch (node.getType()) {
			case LogoJVM1Parser.ADD : return x + y;
			case LogoJVM1Parser.SUB : return x - y;
			case LogoJVM1Parser.MUL : return x * y;
			case LogoJVM1Parser.DIV : return x / y;
			case LogoJVM1Parser.MOD : return x % y;
			default: throw new UnsupportedOperationException("Node "+ node.getText()+"<"+node.getType()+"> not handled");
		}
	}
	
	public Float performOperation( CommonTree node, Float x, Float y ) {
		debug( "performing op on floats" );
		
		switch (node.getType()) {
			case LogoJVM1Parser.ADD : return x + y;
			case LogoJVM1Parser.SUB : return x - y;
			case LogoJVM1Parser.MUL : return x * y;
			case LogoJVM1Parser.DIV : return x / y;
			case LogoJVM1Parser.MOD : return x % y;
			default: throw new UnsupportedOperationException("Node "+ node.getText()+"<"+node.getType()+"> not handled");
		}
	}
		
	public boolean isInt( Value val ) {
		return val.getType() == LogoJVM1Parser.INT;
	}
	
	public boolean isFloat( Value val ) {
		return val.getType() == LogoJVM1Parser.FLOAT;
	}
	
	public Object paren(CommonTree t) throws FunctionReturnException {
		debug("Entered PAREN");
		return exec((CommonTree)t.getChild(0));
	}
		
	private Object stload(String t) {
		debug("Entered LOAD");
		scopeIter = scopeStack.descendingIterator();
		while( scopeIter.hasNext() )
		{
			MemorySpace tmp = scopeIter.next();
			if (tmp.has(t))
				return tmp.get(t);
		}
		return null;
	}

	public Object load(CommonTree t) {
		return stload( t.getChild(0).getText() );
	}

	public void assign(CommonTree t) throws FunctionReturnException {
		debug("Entered ASSIGN: ");

		CommonTree lhs = (CommonTree)t.getChild(0).getChild(0);   // get operands
		CommonTree expr = (CommonTree)t.getChild(1);
		Object value = exec(expr);

		debug( t.getChild(0).getChild(0).getText() + " = " + ((Value)value).getValueBasedOnType() );
		
		scopeStack.peekLast().put(lhs.getText(), value);         // store
	}


	public Object ref(CommonTree t) {
		debug("Entered REF");
		return t.getChild(0).getText();
	}
	
	///// turtle:
	
	public void pd(CommonTree t) {
		debug("Entered PD");
		turtle.turtlePenDown();
	}

	public void pu(CommonTree t) {
		debug("Entered PU");		
		turtle.turtlePenUp();
	}

	public void fd(CommonTree t) throws FunctionReturnException {
		debug("Entered FD");
		Object a = exec( (CommonTree)t.getChild(0) );
		turtle.turtleForward(((Value)a).intValue());
	}

	public void bk(CommonTree t) throws FunctionReturnException {
		debug("Entered BK");
		Object a = exec( (CommonTree)t.getChild(0) );
		turtle.turtleBackward(((Value)a).intValue());
	}

	public void lt2(CommonTree t) throws FunctionReturnException {
		debug("Entered LT2");
		Object a = exec( (CommonTree)t.getChild(0) );
		turtle.turtleLeft(((Value)a).intValue());
	}

	public void rt(CommonTree t) throws FunctionReturnException {
		debug("Entered RT");
		Object a = exec( (CommonTree)t.getChild(0) );
		turtle.turtleRight(((Value)a).intValue());
	}

	public void seth(CommonTree t) throws FunctionReturnException {
		debug("Entered SETH");
		Object a = exec( (CommonTree)t.getChild(0) );
		turtle.turtleSetHeading(((Value)a).doubleValue());
	}

	public void setp(CommonTree t) throws FunctionReturnException {
		debug("Entered SETP");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		turtle.turtleGoto(new Point(((Value)a).intValue(), ((Value)b).intValue()));
	}

	public void circ(CommonTree t) throws FunctionReturnException {
		debug("Entered CIRC");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		turtle.turtleCircle(((Value)a).intValue(), ((Value)b).intValue());
	}

	public void spc(CommonTree t) throws FunctionReturnException {
		debug("Entered SPC");
		Object a = exec( (CommonTree)t.getChild(0) );
		Object b = exec( (CommonTree)t.getChild(1) );
		Object c = exec( (CommonTree)t.getChild(2) );
		turtle.turtleSetColor(((Value)a).intValue(), 
			((Value)b).intValue(), ((Value)c).intValue());
	}

	public void begf(CommonTree t) {
		debug("Entered BEGF");
		turtle.turtleBeginFillPolygon();
	}

	public void endf(CommonTree t) {
		debug("Entered ENDF");
		turtle.turtleEndFillPolygon();
	}
	////// :turtle
}
