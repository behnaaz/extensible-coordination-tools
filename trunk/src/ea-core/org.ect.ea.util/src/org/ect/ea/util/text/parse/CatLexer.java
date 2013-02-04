// $ANTLR 3.1 /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g 2009-05-28 12:46:19

package org.ect.ea.util.text.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CatLexer extends Lexer {
    public static final int GLOB=28;
    public static final int SOURCE=19;
    public static final int INT=18;
    public static final int SEPARATOR=16;
    public static final int INITIALSTATE=25;
    public static final int TARGET=20;
    public static final int AND=5;
    public static final int EOF=-1;
    public static final int TRUE=22;
    public static final int LPAREN=6;
    public static final int TYPE=29;
    public static final int RPAREN=7;
    public static final int QUOTE=17;
    public static final int WS=30;
    public static final int GREATER=11;
    public static final int NOT_EQUAL=10;
    public static final int SPACER=24;
    public static final int EQUAL=9;
    public static final int BEGIN=26;
    public static final int OR=4;
    public static final int LESS=13;
    public static final int ASSIGN=8;
    public static final int IDENT=15;
    public static final int LESS_EQUAL=14;
    public static final int END=27;
    public static final int ARBITRARY=21;
    public static final int FALSE=23;
    public static final int GREATER_EQUAL=12;

    public static void main(String[] args) throws Exception {
    	CatLexer lexer = new CatLexer(new ANTLRFileStream(args[0]));
    	Token token;
    	while ((token = lexer.nextToken())!=Token.EOF_TOKEN) {
    		System.out.println("Token: "+token.getText()+" type: "+token.getType());
    	}
    }


    // delegates
    // delegators

    public CatLexer() {;} 
    public CatLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CatLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g"; }

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:16:7: ( '\"' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:16:9: '\"'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:17:11: ( ',' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:17:13: ','
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

    // $ANTLR start "SPACER"
    public final void mSPACER() throws RecognitionException {
        try {
            int _type = SPACER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:18:8: ( '::' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:18:10: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SPACER"

    // $ANTLR start "INITIALSTATE"
    public final void mINITIALSTATE() throws RecognitionException {
        try {
            int _type = INITIALSTATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:19:14: ( '*' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:19:16: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INITIALSTATE"

    // $ANTLR start "BEGIN"
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:20:7: ( '[' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:20:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEGIN"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:21:5: ( ']' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:21:7: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:22:8: ( '=' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:22:10: '='
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

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:23:7: ( '==' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:23:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "NOT_EQUAL"
    public final void mNOT_EQUAL() throws RecognitionException {
        try {
            int _type = NOT_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:24:11: ( '!=' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:24:13: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT_EQUAL"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:25:9: ( '>' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:25:11: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "GREATER_EQUAL"
    public final void mGREATER_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:26:15: ( '>=' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:26:17: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_EQUAL"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:27:6: ( '<' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:27:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "LESS_EQUAL"
    public final void mLESS_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:28:12: ( '<=' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:28:14: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS_EQUAL"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:29:8: ( '(' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:29:10: '('
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:30:8: ( ')' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:30:10: ')'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:31:5: ( '&' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:31:7: '&'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:32:4: ( '|' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:32:6: '|'
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

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:33:6: ( 'true' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:33:8: 'true'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:34:7: ( 'false' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:34:9: 'false'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:35:11: ( '?' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:35:13: '?'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:36:8: ( '$s.' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:36:10: '$s.'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:37:8: ( '$t.' )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:37:10: '$t.'
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

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:172:6: ( 'IN' | 'OUT' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='I') ) {
                alt1=1;
            }
            else if ( (LA1_0=='O') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:172:8: 'IN'
                    {
                    match("IN"); 


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:172:13: 'OUT'
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:174:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:174:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:174:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:
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
            	    break loop2;
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
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:176:5: ( ( '0' .. '9' )+ )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:176:7: ( '0' .. '9' )+
            {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:176:7: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:176:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:178:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:178:9: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:178:9: ( ' ' | '\\t' | '\\n' | '\\r' )+
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
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:
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

    // $ANTLR start "GLOB"
    public final void mGLOB() throws RecognitionException {
        try {
            int _type = GLOB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:180:6: ( QUOTE ( options {greedy=false; } : . )+ QUOTE )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:180:8: QUOTE ( options {greedy=false; } : . )+ QUOTE
            {
            mQUOTE(); 
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:180:14: ( options {greedy=false; } : . )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\"') ) {
                    alt5=2;
                }
                else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\uFFFE')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:180:40: .
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

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GLOB"

    public void mTokens() throws RecognitionException {
        // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:8: ( QUOTE | SEPARATOR | SPACER | INITIALSTATE | BEGIN | END | ASSIGN | EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | LPAREN | RPAREN | AND | OR | TRUE | FALSE | ARBITRARY | SOURCE | TARGET | TYPE | IDENT | INT | WS | GLOB )
        int alt6=27;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:10: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 2 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:16: SEPARATOR
                {
                mSEPARATOR(); 

                }
                break;
            case 3 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:26: SPACER
                {
                mSPACER(); 

                }
                break;
            case 4 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:33: INITIALSTATE
                {
                mINITIALSTATE(); 

                }
                break;
            case 5 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:46: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 6 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:52: END
                {
                mEND(); 

                }
                break;
            case 7 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:56: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 8 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:63: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 9 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:69: NOT_EQUAL
                {
                mNOT_EQUAL(); 

                }
                break;
            case 10 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:79: GREATER
                {
                mGREATER(); 

                }
                break;
            case 11 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:87: GREATER_EQUAL
                {
                mGREATER_EQUAL(); 

                }
                break;
            case 12 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:101: LESS
                {
                mLESS(); 

                }
                break;
            case 13 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:106: LESS_EQUAL
                {
                mLESS_EQUAL(); 

                }
                break;
            case 14 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:117: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 15 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:124: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 16 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:131: AND
                {
                mAND(); 

                }
                break;
            case 17 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:135: OR
                {
                mOR(); 

                }
                break;
            case 18 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:138: TRUE
                {
                mTRUE(); 

                }
                break;
            case 19 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:143: FALSE
                {
                mFALSE(); 

                }
                break;
            case 20 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:149: ARBITRARY
                {
                mARBITRARY(); 

                }
                break;
            case 21 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:159: SOURCE
                {
                mSOURCE(); 

                }
                break;
            case 22 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:166: TARGET
                {
                mTARGET(); 

                }
                break;
            case 23 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:173: TYPE
                {
                mTYPE(); 

                }
                break;
            case 24 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:178: IDENT
                {
                mIDENT(); 

                }
                break;
            case 25 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:184: INT
                {
                mINT(); 

                }
                break;
            case 26 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:188: WS
                {
                mWS(); 

                }
                break;
            case 27 :
                // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:1:191: GLOB
                {
                mGLOB(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\1\30\5\uffff\1\33\1\uffff\1\35\1\37\4\uffff\2\25\2\uffff"+
        "\2\25\13\uffff\2\25\2\uffff\1\50\3\25\1\uffff\1\50\1\54\1\25\1\uffff"+
        "\1\56\1\uffff";
    static final String DFA6_eofS =
        "\57\uffff";
    static final String DFA6_minS =
        "\1\11\1\0\5\uffff\1\75\1\uffff\2\75\4\uffff\1\162\1\141\1\uffff"+
        "\1\163\1\116\1\125\13\uffff\1\165\1\154\2\uffff\1\60\1\124\1\145"+
        "\1\163\1\uffff\2\60\1\145\1\uffff\1\60\1\uffff";
    static final String DFA6_maxS =
        "\1\174\1\ufffe\5\uffff\1\75\1\uffff\2\75\4\uffff\1\162\1\141\1\uffff"+
        "\1\164\1\116\1\125\13\uffff\1\165\1\154\2\uffff\1\172\1\124\1\145"+
        "\1\163\1\uffff\2\172\1\145\1\uffff\1\172\1\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\2\uffff\1\16\1\17\1\20"+
        "\1\21\2\uffff\1\24\3\uffff\1\30\1\31\1\32\1\1\1\33\1\10\1\7\1\13"+
        "\1\12\1\15\1\14\2\uffff\1\25\1\26\4\uffff\1\27\3\uffff\1\22\1\uffff"+
        "\1\23";
    static final String DFA6_specialS =
        "\57\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\27\2\uffff\1\27\22\uffff\1\27\1\10\1\1\1\uffff\1\22\1\uffff"+
            "\1\15\1\uffff\1\13\1\14\1\4\1\uffff\1\2\3\uffff\12\26\1\3\1"+
            "\uffff\1\12\1\7\1\11\1\21\1\uffff\10\25\1\23\5\25\1\24\13\25"+
            "\1\5\1\uffff\1\6\3\uffff\5\25\1\20\15\25\1\17\6\25\1\uffff\1"+
            "\16",
            "\uffff\31",
            "",
            "",
            "",
            "",
            "",
            "\1\32",
            "",
            "\1\34",
            "\1\36",
            "",
            "",
            "",
            "",
            "\1\40",
            "\1\41",
            "",
            "\1\42\1\43",
            "\1\44",
            "\1\45",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\46",
            "\1\47",
            "",
            "",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\51",
            "\1\52",
            "\1\53",
            "",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\55",
            "",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( QUOTE | SEPARATOR | SPACER | INITIALSTATE | BEGIN | END | ASSIGN | EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | LPAREN | RPAREN | AND | OR | TRUE | FALSE | ARBITRARY | SOURCE | TARGET | TYPE | IDENT | INT | WS | GLOB );";
        }
    }
 

}