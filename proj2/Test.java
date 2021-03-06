/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
***/
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
        CharStream input = new ANTLRInputStream(System.in);

        // Create lexer/parser to build trees from stdin
        LogoASTLexer lex = new LogoASTLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        LogoASTParser p = new LogoASTParser(tokens);
	RuleReturnScope r = p.prog();
        CommonTree t = (CommonTree)r.getTree();

        System.out.println("Original tree: "+t.toStringTree()); // print the tree

    }
}
