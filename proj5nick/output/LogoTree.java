// $ANTLR 3.3 Nov 30, 2010 12:45:30 LogoTree.g 2011-04-11 15:38:51

import java.util.HashMap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
public class LogoTree extends TreeParser {
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


        public LogoTree(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public LogoTree(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected StringTemplateGroup templateLib =
      new StringTemplateGroup("LogoTreeTemplates", AngleBracketTemplateLexer.class);

    public void setTemplateLib(StringTemplateGroup templateLib) {
      this.templateLib = templateLib;
    }
    public StringTemplateGroup getTemplateLib() {
      return templateLib;
    }
    /** allows convenient multi-value initialization:
     *  "new STAttrMap().put(...).put(...)"
     */
    public static class STAttrMap extends HashMap {
      public STAttrMap put(String attrName, Object value) {
        super.put(attrName, value);
        return this;
      }
      public STAttrMap put(String attrName, int value) {
        super.put(attrName, new Integer(value));
        return this;
      }
    }

    public String[] getTokenNames() { return LogoTree.tokenNames; }
    public String getGrammarFileName() { return "LogoTree.g"; }


    /** Points at locals table built by the parser */
    HashMap locals;


    public static class prog_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "prog"
    // LogoTree.g:21:1: prog[int numOps, HashMap locals] : (s+= stat )+ -> jasminFile(instructions=$smaxStackDepth=numOps+1+1maxLocals=locals.size()+1);
    public final LogoTree.prog_return prog(int numOps, HashMap locals) throws RecognitionException {
        LogoTree.prog_return retval = new LogoTree.prog_return();
        retval.start = input.LT(1);

        List list_s=null;
        RuleReturnScope s = null;

        this.locals = locals; // point at map created in parser

        try {
            // LogoTree.g:31:5: ( (s+= stat )+ -> jasminFile(instructions=$smaxStackDepth=numOps+1+1maxLocals=locals.size()+1))
            // LogoTree.g:31:9: (s+= stat )+
            {
            // LogoTree.g:31:9: (s+= stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=ID && LA1_0<=INT)||(LA1_0>=11 && LA1_0<=14)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // LogoTree.g:31:10: s+= stat
            	    {
            	    pushFollow(FOLLOW_stat_in_prog79);
            	    s=stat();

            	    state._fsp--;

            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTemplate());


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



            // TEMPLATE REWRITE
            // 31:20: -> jasminFile(instructions=$smaxStackDepth=numOps+1+1maxLocals=locals.size()+1)
            {
                retval.st = templateLib.getInstanceOf("jasminFile",
              new STAttrMap().put("instructions", list_s).put("maxStackDepth", numOps+1+1).put("maxLocals", locals.size()+1));
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "prog"

    public static class stat_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "stat"
    // LogoTree.g:38:1: stat : ( expr -> exprStat(v=$expr.stdescr=$expr.text) | ^( 'make' ID expr ) -> assign(id=$ID.textdescr=$textvarNum=locals.get($ID.text)v=$expr.st));
    public final LogoTree.stat_return stat() throws RecognitionException {
        LogoTree.stat_return retval = new LogoTree.stat_return();
        retval.start = input.LT(1);

        CommonTree ID2=null;
        LogoTree.expr_return expr1 = null;

        LogoTree.expr_return expr3 = null;


        try {
            // LogoTree.g:38:5: ( expr -> exprStat(v=$expr.stdescr=$expr.text) | ^( 'make' ID expr ) -> assign(id=$ID.textdescr=$textvarNum=locals.get($ID.text)v=$expr.st))
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=ID && LA2_0<=INT)||(LA2_0>=12 && LA2_0<=14)) ) {
                alt2=1;
            }
            else if ( (LA2_0==11) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // LogoTree.g:38:9: expr
                    {
                    pushFollow(FOLLOW_expr_in_stat182);
                    expr1=expr();

                    state._fsp--;



                    // TEMPLATE REWRITE
                    // 38:14: -> exprStat(v=$expr.stdescr=$expr.text)
                    {
                        retval.st = templateLib.getInstanceOf("exprStat",
                      new STAttrMap().put("v", (expr1!=null?expr1.st:null)).put("descr", (expr1!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(expr1.start),
                      input.getTreeAdaptor().getTokenStopIndex(expr1.start))):null)));
                    }


                    }
                    break;
                case 2 :
                    // LogoTree.g:39:9: ^( 'make' ID expr )
                    {
                    match(input,11,FOLLOW_11_in_stat207); 

                    match(input, Token.DOWN, null); 
                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_stat209); 
                    pushFollow(FOLLOW_expr_in_stat211);
                    expr3=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 40:9: -> assign(id=$ID.textdescr=$textvarNum=locals.get($ID.text)v=$expr.st)
                    {
                        retval.st = templateLib.getInstanceOf("assign",
                      new STAttrMap().put("id", (ID2!=null?ID2.getText():null)).put("descr", input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(retval.start),
                      input.getTreeAdaptor().getTokenStopIndex(retval.start))).put("varNum", locals.get((ID2!=null?ID2.getText():null))).put("v", (expr3!=null?expr3.st:null)));
                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stat"

    public static class expr_return extends TreeRuleReturnScope {
        public int value;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "expr"
    // LogoTree.g:48:1: expr returns [int value] : ( ^( '+' a= expr b= expr ) -> add(a=$a.stb=$b.st) | ^( '-' a= expr b= expr ) -> sub(a=$a.stb=$b.st) | ^( '*' a= expr b= expr ) -> mult(a=$a.stb=$b.st) | INT -> int(v=$INT.text) | ID -> var(id=$ID.textvarNum=locals.get($ID.text)));
    public final LogoTree.expr_return expr() throws RecognitionException {
        LogoTree.expr_return retval = new LogoTree.expr_return();
        retval.start = input.LT(1);

        CommonTree INT4=null;
        CommonTree ID5=null;
        LogoTree.expr_return a = null;

        LogoTree.expr_return b = null;


        try {
            // LogoTree.g:49:5: ( ^( '+' a= expr b= expr ) -> add(a=$a.stb=$b.st) | ^( '-' a= expr b= expr ) -> sub(a=$a.stb=$b.st) | ^( '*' a= expr b= expr ) -> mult(a=$a.stb=$b.st) | INT -> int(v=$INT.text) | ID -> var(id=$ID.textvarNum=locals.get($ID.text)))
            int alt3=5;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 13:
                {
                alt3=2;
                }
                break;
            case 14:
                {
                alt3=3;
                }
                break;
            case INT:
                {
                alt3=4;
                }
                break;
            case ID:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // LogoTree.g:49:9: ^( '+' a= expr b= expr )
                    {
                    match(input,12,FOLLOW_12_in_expr324); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr328);
                    a=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr332);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 49:30: -> add(a=$a.stb=$b.st)
                    {
                        retval.st = templateLib.getInstanceOf("add",
                      new STAttrMap().put("a", (a!=null?a.st:null)).put("b", (b!=null?b.st:null)));
                    }


                    }
                    break;
                case 2 :
                    // LogoTree.g:50:9: ^( '-' a= expr b= expr )
                    {
                    match(input,13,FOLLOW_13_in_expr357); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr361);
                    a=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr365);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 50:30: -> sub(a=$a.stb=$b.st)
                    {
                        retval.st = templateLib.getInstanceOf("sub",
                      new STAttrMap().put("a", (a!=null?a.st:null)).put("b", (b!=null?b.st:null)));
                    }


                    }
                    break;
                case 3 :
                    // LogoTree.g:51:9: ^( '*' a= expr b= expr )
                    {
                    match(input,14,FOLLOW_14_in_expr390); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr394);
                    a=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr398);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 51:30: -> mult(a=$a.stb=$b.st)
                    {
                        retval.st = templateLib.getInstanceOf("mult",
                      new STAttrMap().put("a", (a!=null?a.st:null)).put("b", (b!=null?b.st:null)));
                    }


                    }
                    break;
                case 4 :
                    // LogoTree.g:52:7: INT
                    {
                    INT4=(CommonTree)match(input,INT,FOLLOW_INT_in_expr420); 


                    // TEMPLATE REWRITE
                    // 52:11: -> int(v=$INT.text)
                    {
                        retval.st = templateLib.getInstanceOf("int",
                      new STAttrMap().put("v", (INT4!=null?INT4.getText():null)));
                    }


                    }
                    break;
                case 5 :
                    // LogoTree.g:53:9: ID
                    {
                    ID5=(CommonTree)match(input,ID,FOLLOW_ID_in_expr439); 


                    // TEMPLATE REWRITE
                    // 53:13: -> var(id=$ID.textvarNum=locals.get($ID.text))
                    {
                        retval.st = templateLib.getInstanceOf("var",
                      new STAttrMap().put("id", (ID5!=null?ID5.getText():null)).put("varNum", locals.get((ID5!=null?ID5.getText():null))));
                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog79 = new BitSet(new long[]{0x00000000000078C2L});
    public static final BitSet FOLLOW_expr_in_stat182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat207 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_stat209 = new BitSet(new long[]{0x00000000000070C0L});
    public static final BitSet FOLLOW_expr_in_stat211 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_12_in_expr324 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr328 = new BitSet(new long[]{0x00000000000070C0L});
    public static final BitSet FOLLOW_expr_in_expr332 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_13_in_expr357 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr361 = new BitSet(new long[]{0x00000000000070C0L});
    public static final BitSet FOLLOW_expr_in_expr365 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_14_in_expr390 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr394 = new BitSet(new long[]{0x00000000000070C0L});
    public static final BitSet FOLLOW_expr_in_expr398 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_expr420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr439 = new BitSet(new long[]{0x0000000000000002L});

}