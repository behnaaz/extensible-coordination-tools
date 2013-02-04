// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g 2010-02-23 15:18:53

package org.ect.ea.extensions.constraints.parsers;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ConstraintLexer extends Lexer {
    public static final int GLOB=19;
    public static final int SOURCE=12;
    public static final int INT=18;
    public static final int SEPARATOR=15;
    public static final int TARGET=13;
    public static final int AND=6;
    public static final int EOF=-1;
    public static final int TRUE=9;
    public static final int LPAREN=4;
    public static final int TYPE=20;
    public static final int RPAREN=5;
    public static final int QUOTE=14;
    public static final int WS=21;
    public static final int OR=7;
    public static final int ASSIGN=8;
    public static final int IDENT=17;
    public static final int ARBITRARY=11;
    public static final int FALSE=10;
    public static final int RELOP=16;

    // delegates
    // delegators

    public ConstraintLexer() {;} 
    public ConstraintLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ConstraintLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g"; }

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:7:8: ( '(' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:7:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:8:8: ( ')' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:8:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:9:5: ( '&' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:9:7: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:10:4: ( '|' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:10:6: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:11:8: ( '=' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:11:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:12:6: ( 'true' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:12:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:13:7: ( 'false' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:13:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "ARBITRARY"
    public final void mARBITRARY() throws RecognitionException {
        try {
            int _type = ARBITRARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:14:11: ( '?' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:14:13: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARBITRARY"

    // $ANTLR start "SOURCE"
    public final void mSOURCE() throws RecognitionException {
        try {
            int _type = SOURCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:15:8: ( '$s.' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:15:10: '$s.'
            {
            match("$s."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOURCE"

    // $ANTLR start "TARGET"
    public final void mTARGET() throws RecognitionException {
        try {
            int _type = TARGET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:16:8: ( '$t.' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:16:10: '$t.'
            {
            match("$t."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGET"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:17:7: ( '\"' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:17:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "SEPARATOR"
    public final void mSEPARATOR() throws RecognitionException {
        try {
            int _type = SEPARATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:18:11: ( ',' )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:18:13: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEPARATOR"

    // $ANTLR start "RELOP"
    public final void mRELOP() throws RecognitionException {
        try {
            int _type = RELOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:7: ( '==' | '!=' | '<' | '<=' | '>' | '>=' )
            int alt1=6;
            switch ( input.LA(1) ) {
            case '=':
                {
                alt1=1;
                }
                break;
            case '!':
                {
                alt1=2;
                }
                break;
            case '<':
                {
                int LA1_3 = input.LA(2);

                if ( (LA1_3=='=') ) {
                    alt1=4;
                }
                else {
                    alt1=3;}
                }
                break;
            case '>':
                {
                int LA1_4 = input.LA(2);

                if ( (LA1_4=='=') ) {
                    alt1=6;
                }
                else {
                    alt1=5;}
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:9: '=='
                    {
                    match("=="); 


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:14: '!='
                    {
                    match("!="); 


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:19: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:23: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:28: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 6 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:124:32: '>='
                    {
                    match(">="); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RELOP"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:126:6: ( 'IN' | 'OUT' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='I') ) {
                alt2=1;
            }
            else if ( (LA2_0=='O') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:126:8: 'IN'
                    {
                    match("IN"); 


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:126:13: 'OUT'
                    {
                    match("OUT"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:128:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:128:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:128:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:130:6: ( ( '0' .. '9' )+ )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:130:8: ( '0' .. '9' )+
            {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:130:8: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:130:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "GLOB"
    public final void mGLOB() throws RecognitionException {
        try {
            int _type = GLOB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:132:6: ( QUOTE ( options {greedy=false; } : . )+ QUOTE )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:132:8: QUOTE ( options {greedy=false; } : . )+ QUOTE
            {
            mQUOTE(); 
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:132:14: ( options {greedy=false; } : . )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\"') ) {
                    alt5=2;
                }
                else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:132:40: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            mQUOTE(); 
            setText(getText().substring(1, getText().length()-1));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GLOB"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:135:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:135:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:135:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\t' && LA6_0<='\n')||LA6_0=='\r'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:
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
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
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

    public void mTokens() throws RecognitionException {
        // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:8: ( LPAREN | RPAREN | AND | OR | ASSIGN | TRUE | FALSE | ARBITRARY | SOURCE | TARGET | QUOTE | SEPARATOR | RELOP | TYPE | IDENT | INT | GLOB | WS )
        int alt7=18;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:10: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 2 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:17: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 3 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:24: AND
                {
                mAND(); 

                }
                break;
            case 4 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:28: OR
                {
                mOR(); 

                }
                break;
            case 5 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:31: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 6 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:38: TRUE
                {
                mTRUE(); 

                }
                break;
            case 7 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:43: FALSE
                {
                mFALSE(); 

                }
                break;
            case 8 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:49: ARBITRARY
                {
                mARBITRARY(); 

                }
                break;
            case 9 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:59: SOURCE
                {
                mSOURCE(); 

                }
                break;
            case 10 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:66: TARGET
                {
                mTARGET(); 

                }
                break;
            case 11 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:73: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 12 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:79: SEPARATOR
                {
                mSEPARATOR(); 

                }
                break;
            case 13 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:89: RELOP
                {
                mRELOP(); 

                }
                break;
            case 14 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:95: TYPE
                {
                mTYPE(); 

                }
                break;
            case 15 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:100: IDENT
                {
                mIDENT(); 

                }
                break;
            case 16 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:106: INT
                {
                mINT(); 

                }
                break;
            case 17 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:110: GLOB
                {
                mGLOB(); 

                }
                break;
            case 18 :
                // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:1:115: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\5\uffff\1\22\2\17\2\uffff\1\27\2\uffff\2\17\4\uffff\2\17\4\uffff"+
        "\1\35\3\17\1\uffff\1\35\1\41\1\17\1\uffff\1\43\1\uffff";
    static final String DFA7_eofS =
        "\44\uffff";
    static final String DFA7_minS =
        "\1\11\4\uffff\1\75\1\162\1\141\1\uffff\1\163\1\0\2\uffff\1\116\1"+
        "\125\4\uffff\1\165\1\154\4\uffff\1\60\1\124\1\145\1\163\1\uffff"+
        "\2\60\1\145\1\uffff\1\60\1\uffff";
    static final String DFA7_maxS =
        "\1\174\4\uffff\1\75\1\162\1\141\1\uffff\1\164\1\uffff\2\uffff\1"+
        "\116\1\125\4\uffff\1\165\1\154\4\uffff\1\172\1\124\1\145\1\163\1"+
        "\uffff\2\172\1\145\1\uffff\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\3\uffff\1\10\2\uffff\1\14\1\15\2\uffff"+
        "\1\17\1\20\1\22\1\5\2\uffff\1\11\1\12\1\13\1\21\4\uffff\1\16\3\uffff"+
        "\1\6\1\uffff\1\7";
    static final String DFA7_specialS =
        "\12\uffff\1\0\31\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\21\2\uffff\1\21\22\uffff\1\21\1\14\1\12\1\uffff\1\11\1\uffff"+
            "\1\3\1\uffff\1\1\1\2\2\uffff\1\13\3\uffff\12\20\2\uffff\1\14"+
            "\1\5\1\14\1\10\1\uffff\10\17\1\15\5\17\1\16\13\17\6\uffff\5"+
            "\17\1\7\15\17\1\6\6\17\1\uffff\1\4",
            "",
            "",
            "",
            "",
            "\1\14",
            "\1\23",
            "\1\24",
            "",
            "\1\25\1\26",
            "\0\30",
            "",
            "",
            "\1\31",
            "\1\32",
            "",
            "",
            "",
            "",
            "\1\33",
            "\1\34",
            "",
            "",
            "",
            "",
            "\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
            "\1\36",
            "\1\37",
            "\1\40",
            "",
            "\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
            "\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
            "\1\42",
            "",
            "\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( LPAREN | RPAREN | AND | OR | ASSIGN | TRUE | FALSE | ARBITRARY | SOURCE | TARGET | QUOTE | SEPARATOR | RELOP | TYPE | IDENT | INT | GLOB | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_10 = input.LA(1);

                        s = -1;
                        if ( ((LA7_10>='\u0000' && LA7_10<='\uFFFF')) ) {s = 24;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}