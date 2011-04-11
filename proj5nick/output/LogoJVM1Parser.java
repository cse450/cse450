// $ANTLR 3.3 Nov 30, 2010 12:45:30 LogoJVM1.g 2011-04-11 15:38:51

import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

/** Create AST and compute ID -> local variable number map */
public class LogoJVM1Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "REF", "ID", "INT", "CHAR", "WS", "VAL", "'make'", "'+'", "'-'", "'*'", "'('", "')'"
    };
    public static final int EOF=-1;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int NEWLINE=4;
    public static final int REF=5;
    public static final int ID=6;
    public static final int INT=7;
    public static final int CHAR=8;
    public static final int WS=9;
    public static final int VAL=10;

    // delegates
    // delegators


        public LogoJVM1Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public LogoJVM1Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return LogoJVM1Parser.tokenNames; }
    public String getGrammarFileName() { return "LogoJVM1.g"; }


    int numOps = 0; // track operations for stack size purposes
    HashMap locals = new HashMap(); // map ID to local var number
    /* Count local variables, but don't use 0, which in this case
     * is the String[] args parameter of the main method.
     */
    int localVarNum = 1; 


    public static class prog_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "prog"
    // LogoJVM1.g:24:1: prog : ( stat )+ ;
    public final LogoJVM1Parser.prog_return prog() throws RecognitionException {
        LogoJVM1Parser.prog_return retval = new LogoJVM1Parser.prog_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LogoJVM1Parser.stat_return stat1 = null;



        try {
            // LogoJVM1.g:24:5: ( ( stat )+ )
            // LogoJVM1.g:24:9: ( stat )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // LogoJVM1.g:24:9: ( stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE||(LA1_0>=ID && LA1_0<=INT)||LA1_0==11||LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // LogoJVM1.g:24:9: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_prog51);
            	    stat1=stat();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stat1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "prog"

    public static class stat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stat"
    // LogoJVM1.g:26:1: stat : ( expr NEWLINE -> expr | 'make' REF ID expr NEWLINE -> ^( 'make' ID expr ) | NEWLINE ->);
    public final LogoJVM1Parser.stat_return stat() throws RecognitionException {
        LogoJVM1Parser.stat_return retval = new LogoJVM1Parser.stat_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NEWLINE3=null;
        Token string_literal4=null;
        Token REF5=null;
        Token ID6=null;
        Token NEWLINE8=null;
        Token NEWLINE9=null;
        LogoJVM1Parser.expr_return expr2 = null;

        LogoJVM1Parser.expr_return expr7 = null;


        CommonTree NEWLINE3_tree=null;
        CommonTree string_literal4_tree=null;
        CommonTree REF5_tree=null;
        CommonTree ID6_tree=null;
        CommonTree NEWLINE8_tree=null;
        CommonTree NEWLINE9_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_REF=new RewriteRuleTokenStream(adaptor,"token REF");
        RewriteRuleTokenStream stream_11=new RewriteRuleTokenStream(adaptor,"token 11");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // LogoJVM1.g:26:5: ( expr NEWLINE -> expr | 'make' REF ID expr NEWLINE -> ^( 'make' ID expr ) | NEWLINE ->)
            int alt2=3;
            switch ( input.LA(1) ) {
            case ID:
            case INT:
            case 15:
                {
                alt2=1;
                }
                break;
            case 11:
                {
                alt2=2;
                }
                break;
            case NEWLINE:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // LogoJVM1.g:26:9: expr NEWLINE
                    {
                    pushFollow(FOLLOW_expr_in_stat62);
                    expr2=expr();

                    state._fsp--;

                    stream_expr.add(expr2.getTree());
                    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat64);  
                    stream_NEWLINE.add(NEWLINE3);



                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 26:29: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // LogoJVM1.g:27:9: 'make' REF ID expr NEWLINE
                    {
                    string_literal4=(Token)match(input,11,FOLLOW_11_in_stat85);  
                    stream_11.add(string_literal4);

                    REF5=(Token)match(input,REF,FOLLOW_REF_in_stat87);  
                    stream_REF.add(REF5);

                    ID6=(Token)match(input,ID,FOLLOW_ID_in_stat89);  
                    stream_ID.add(ID6);

                    pushFollow(FOLLOW_expr_in_stat91);
                    expr7=expr();

                    state._fsp--;

                    stream_expr.add(expr7.getTree());
                    NEWLINE8=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat93);  
                    stream_NEWLINE.add(NEWLINE8);


                            if ( locals.get((ID6!=null?ID6.getText():null))==null ) {
                                locals.put((ID6!=null?ID6.getText():null), new Integer(localVarNum++));
                            }
                            


                    // AST REWRITE
                    // elements: 11, ID, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 33:9: -> ^( 'make' ID expr )
                    {
                        // LogoJVM1.g:33:12: ^( 'make' ID expr )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_11.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // LogoJVM1.g:34:9: NEWLINE
                    {
                    NEWLINE9=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat131);  
                    stream_NEWLINE.add(NEWLINE9);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 34:29: ->
                    {
                        root_0 = null;
                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stat"

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // LogoJVM1.g:39:1: expr : multExpr ( ( '+' | '-' ) multExpr )* ;
    public final LogoJVM1Parser.expr_return expr() throws RecognitionException {
        LogoJVM1Parser.expr_return retval = new LogoJVM1Parser.expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal11=null;
        Token char_literal12=null;
        LogoJVM1Parser.multExpr_return multExpr10 = null;

        LogoJVM1Parser.multExpr_return multExpr13 = null;


        CommonTree char_literal11_tree=null;
        CommonTree char_literal12_tree=null;

        try {
            // LogoJVM1.g:39:5: ( multExpr ( ( '+' | '-' ) multExpr )* )
            // LogoJVM1.g:39:9: multExpr ( ( '+' | '-' ) multExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multExpr_in_expr161);
            multExpr10=multExpr();

            state._fsp--;

            adaptor.addChild(root_0, multExpr10.getTree());
            // LogoJVM1.g:39:18: ( ( '+' | '-' ) multExpr )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=12 && LA4_0<=13)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // LogoJVM1.g:39:19: ( '+' | '-' ) multExpr
            	    {
            	    // LogoJVM1.g:39:19: ( '+' | '-' )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==12) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==13) ) {
            	        alt3=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // LogoJVM1.g:39:20: '+'
            	            {
            	            char_literal11=(Token)match(input,12,FOLLOW_12_in_expr165); 
            	            char_literal11_tree = (CommonTree)adaptor.create(char_literal11);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal11_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // LogoJVM1.g:39:25: '-'
            	            {
            	            char_literal12=(Token)match(input,13,FOLLOW_13_in_expr168); 
            	            char_literal12_tree = (CommonTree)adaptor.create(char_literal12);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal12_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multExpr_in_expr172);
            	    multExpr13=multExpr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multExpr13.getTree());
            	    numOps++;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class multExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multExpr"
    // LogoJVM1.g:42:1: multExpr : atom ( '*' atom )* ;
    public final LogoJVM1Parser.multExpr_return multExpr() throws RecognitionException {
        LogoJVM1Parser.multExpr_return retval = new LogoJVM1Parser.multExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal15=null;
        LogoJVM1Parser.atom_return atom14 = null;

        LogoJVM1Parser.atom_return atom16 = null;


        CommonTree char_literal15_tree=null;

        try {
            // LogoJVM1.g:42:9: ( atom ( '*' atom )* )
            // LogoJVM1.g:42:13: atom ( '*' atom )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_multExpr191);
            atom14=atom();

            state._fsp--;

            adaptor.addChild(root_0, atom14.getTree());
            // LogoJVM1.g:42:18: ( '*' atom )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==14) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // LogoJVM1.g:42:19: '*' atom
            	    {
            	    char_literal15=(Token)match(input,14,FOLLOW_14_in_multExpr194); 
            	    char_literal15_tree = (CommonTree)adaptor.create(char_literal15);
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal15_tree, root_0);

            	    pushFollow(FOLLOW_atom_in_multExpr197);
            	    atom16=atom();

            	    state._fsp--;

            	    adaptor.addChild(root_0, atom16.getTree());
            	    numOps++;

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "multExpr"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // LogoJVM1.g:45:1: atom : ( INT | ID | '(' expr ')' );
    public final LogoJVM1Parser.atom_return atom() throws RecognitionException {
        LogoJVM1Parser.atom_return retval = new LogoJVM1Parser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INT17=null;
        Token ID18=null;
        Token char_literal19=null;
        Token char_literal21=null;
        LogoJVM1Parser.expr_return expr20 = null;


        CommonTree INT17_tree=null;
        CommonTree ID18_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree char_literal21_tree=null;

        try {
            // LogoJVM1.g:45:5: ( INT | ID | '(' expr ')' )
            int alt6=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt6=1;
                }
                break;
            case ID:
                {
                alt6=2;
                }
                break;
            case 15:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // LogoJVM1.g:45:9: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INT17=(Token)match(input,INT,FOLLOW_INT_in_atom216); 
                    INT17_tree = (CommonTree)adaptor.create(INT17);
                    adaptor.addChild(root_0, INT17_tree);


                    }
                    break;
                case 2 :
                    // LogoJVM1.g:46:9: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ID18=(Token)match(input,ID,FOLLOW_ID_in_atom227); 
                    ID18_tree = (CommonTree)adaptor.create(ID18);
                    adaptor.addChild(root_0, ID18_tree);


                    }
                    break;
                case 3 :
                    // LogoJVM1.g:47:9: '(' expr ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal19=(Token)match(input,15,FOLLOW_15_in_atom237); 
                    pushFollow(FOLLOW_expr_in_atom240);
                    expr20=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr20.getTree());
                    char_literal21=(Token)match(input,16,FOLLOW_16_in_atom242); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog51 = new BitSet(new long[]{0x00000000000088D2L});
    public static final BitSet FOLLOW_expr_in_stat62 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_stat64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat85 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_REF_in_stat87 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_stat89 = new BitSet(new long[]{0x00000000000080C0L});
    public static final BitSet FOLLOW_expr_in_stat91 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_stat93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr161 = new BitSet(new long[]{0x0000000000003002L});
    public static final BitSet FOLLOW_12_in_expr165 = new BitSet(new long[]{0x00000000000080C0L});
    public static final BitSet FOLLOW_13_in_expr168 = new BitSet(new long[]{0x00000000000080C0L});
    public static final BitSet FOLLOW_multExpr_in_expr172 = new BitSet(new long[]{0x0000000000003002L});
    public static final BitSet FOLLOW_atom_in_multExpr191 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multExpr194 = new BitSet(new long[]{0x00000000000080C0L});
    public static final BitSet FOLLOW_atom_in_multExpr197 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_INT_in_atom216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_atom237 = new BitSet(new long[]{0x00000000000080C0L});
    public static final BitSet FOLLOW_expr_in_atom240 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_atom242 = new BitSet(new long[]{0x0000000000000002L});

}