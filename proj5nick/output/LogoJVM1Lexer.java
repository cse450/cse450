// $ANTLR 3.3 Nov 30, 2010 12:45:30 LogoJVM1.g 2011-04-11 15:38:51

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LogoJVM1Lexer extends Lexer {
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

    public LogoJVM1Lexer() {;} 
    public LogoJVM1Lexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public LogoJVM1Lexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "LogoJVM1.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:3:7: ( 'make' )
            // LogoJVM1.g:3:9: 'make'
            {
            match("make"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:4:7: ( '+' )
            // LogoJVM1.g:4:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:5:7: ( '-' )
            // LogoJVM1.g:5:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:6:7: ( '*' )
            // LogoJVM1.g:6:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:7:7: ( '(' )
            // LogoJVM1.g:7:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:8:7: ( ')' )
            // LogoJVM1.g:8:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:52:4: ( ( '_' | CHAR ) ( '_' | CHAR | INT )* )
            // LogoJVM1.g:52:6: ( '_' | CHAR ) ( '_' | CHAR | INT )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // LogoJVM1.g:52:20: ( '_' | CHAR | INT )*
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case '_':
                    {
                    alt1=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt1=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt1=3;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // LogoJVM1.g:52:22: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 2 :
            	    // LogoJVM1.g:52:28: CHAR
            	    {
            	    mCHAR(); 

            	    }
            	    break;
            	case 3 :
            	    // LogoJVM1.g:52:35: INT
            	    {
            	    mINT(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            // LogoJVM1.g:53:15: ( 'a' .. 'z' | 'A' .. 'Z' )
            // LogoJVM1.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:55:5: ( ( '0' .. '9' )+ )
            // LogoJVM1.g:55:9: ( '0' .. '9' )+
            {
            // LogoJVM1.g:55:9: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // LogoJVM1.g:55:9: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:56:8: ( ( '\\r' )? '\\n' )
            // LogoJVM1.g:56:9: ( '\\r' )? '\\n'
            {
            // LogoJVM1.g:56:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // LogoJVM1.g:56:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:57:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // LogoJVM1.g:57:9: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // LogoJVM1.g:57:9: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // LogoJVM1.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "REF"
    public final void mREF() throws RecognitionException {
        try {
            int _type = REF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:58:5: ( '\"' )
            // LogoJVM1.g:58:7: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REF"

    // $ANTLR start "VAL"
    public final void mVAL() throws RecognitionException {
        try {
            int _type = VAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LogoJVM1.g:59:5: ( ':' )
            // LogoJVM1.g:59:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAL"

    public void mTokens() throws RecognitionException {
        // LogoJVM1.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | ID | INT | NEWLINE | WS | REF | VAL )
        int alt5=12;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // LogoJVM1.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // LogoJVM1.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // LogoJVM1.g:1:22: T__13
                {
                mT__13(); 

                }
                break;
            case 4 :
                // LogoJVM1.g:1:28: T__14
                {
                mT__14(); 

                }
                break;
            case 5 :
                // LogoJVM1.g:1:34: T__15
                {
                mT__15(); 

                }
                break;
            case 6 :
                // LogoJVM1.g:1:40: T__16
                {
                mT__16(); 

                }
                break;
            case 7 :
                // LogoJVM1.g:1:46: ID
                {
                mID(); 

                }
                break;
            case 8 :
                // LogoJVM1.g:1:49: INT
                {
                mINT(); 

                }
                break;
            case 9 :
                // LogoJVM1.g:1:53: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 10 :
                // LogoJVM1.g:1:61: WS
                {
                mWS(); 

                }
                break;
            case 11 :
                // LogoJVM1.g:1:64: REF
                {
                mREF(); 

                }
                break;
            case 12 :
                // LogoJVM1.g:1:68: VAL
                {
                mVAL(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\1\7\7\uffff\1\13\1\17\3\uffff\1\7\1\uffff\1\7\1\22\1\uffff";
    static final String DFA5_eofS =
        "\23\uffff";
    static final String DFA5_minS =
        "\1\11\1\141\7\uffff\1\12\1\11\3\uffff\1\153\1\uffff\1\145\1\60\1"+
        "\uffff";
    static final String DFA5_maxS =
        "\1\172\1\141\7\uffff\1\12\1\40\3\uffff\1\153\1\uffff\1\145\1\172"+
        "\1\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\uffff\1\12\1\13\1\14\1\uffff"+
        "\1\11\2\uffff\1\1";
    static final String DFA5_specialS =
        "\23\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\13\1\12\2\uffff\1\11\22\uffff\1\13\1\uffff\1\14\5\uffff\1"+
            "\5\1\6\1\4\1\2\1\uffff\1\3\2\uffff\12\10\1\15\6\uffff\32\7\4"+
            "\uffff\1\7\1\uffff\14\7\1\1\15\7",
            "\1\16",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\12",
            "\2\13\2\uffff\1\13\22\uffff\1\13",
            "",
            "",
            "",
            "\1\20",
            "",
            "\1\21",
            "\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | ID | INT | NEWLINE | WS | REF | VAL );";
        }
    }
 

}