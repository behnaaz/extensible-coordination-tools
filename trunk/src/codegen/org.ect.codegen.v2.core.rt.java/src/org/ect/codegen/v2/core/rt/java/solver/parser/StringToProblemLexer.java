// $ANTLR 3.3 Nov 30, 2010 12:50:56 C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g 2012-09-21 19:55:31

package org.ect.codegen.v2.core.rt.java.solver.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StringToProblemLexer extends Lexer {
    public static final int EOF=-1;
    public static final int EQUAL=4;
    public static final int ASSIGNMENT=5;
    public static final int DOLLAR=6;
    public static final int UNDERSCORE=7;
    public static final int PAR_LEFT=8;
    public static final int PAR_RIGHT=9;
    public static final int BEGIN_CODE=10;
    public static final int END_CODE=11;
    public static final int COMMA=12;
    public static final int AND=13;
    public static final int DOT=14;
    public static final int COLON=15;
    public static final int QUOTE=16;
    public static final int CONSTRAINT_EQUAL=17;
    public static final int CONSTRAINT_FILTER=18;
    public static final int CONSTRAINT_RANDOMINT=19;
    public static final int CONSTRAINT_TRUE=20;
    public static final int CODE=21;
    public static final int INTEGER=22;
    public static final int IDENTIFIER=23;
    public static final int WHITESPACE=24;
    public static final int LETTER_OR_UNDERSCORE=25;
    public static final int LETTER_OR_DIGIT_OR_UNDERSCORE=26;
    public static final int DIGIT=27;
    public static final int LETTER=28;
    public static final int LOWER_CASE_LETTER=29;
    public static final int UPPER_CASE_LETTER=30;

    public static void main(final String[] args) throws Exception {
    	// Begin code to avoid "unused import" warnings for 
    	// classes List, ArrayList and Stack.
    	final List<Object> list = new ArrayList<Object>();
    	list.add(new Stack<Object>());
    	// End.
    }


    // delegates
    // delegators

    public StringToProblemLexer() {;} 
    public StringToProblemLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public StringToProblemLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g"; }

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:16:7: ( '==' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:16:9: '=='
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

    // $ANTLR start "ASSIGNMENT"
    public final void mASSIGNMENT() throws RecognitionException {
        try {
            int _type = ASSIGNMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:17:12: ( ':=' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:17:14: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGNMENT"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:18:8: ( '$' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:18:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "UNDERSCORE"
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            int _type = UNDERSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:19:12: ( '_' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:19:14: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNDERSCORE"

    // $ANTLR start "PAR_LEFT"
    public final void mPAR_LEFT() throws RecognitionException {
        try {
            int _type = PAR_LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:20:10: ( '(' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:20:12: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PAR_LEFT"

    // $ANTLR start "PAR_RIGHT"
    public final void mPAR_RIGHT() throws RecognitionException {
        try {
            int _type = PAR_RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:21:11: ( ')' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:21:13: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PAR_RIGHT"

    // $ANTLR start "BEGIN_CODE"
    public final void mBEGIN_CODE() throws RecognitionException {
        try {
            int _type = BEGIN_CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:22:12: ( '[[' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:22:14: '[['
            {
            match("[["); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEGIN_CODE"

    // $ANTLR start "END_CODE"
    public final void mEND_CODE() throws RecognitionException {
        try {
            int _type = END_CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:23:10: ( ']]' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:23:12: ']]'
            {
            match("]]"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_CODE"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:24:7: ( ',' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:24:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:25:5: ( '&&' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:25:7: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:26:5: ( '.' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:26:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:27:7: ( ':' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:27:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:28:7: ( '\\'' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:28:9: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "CONSTRAINT_EQUAL"
    public final void mCONSTRAINT_EQUAL() throws RecognitionException {
        try {
            int _type = CONSTRAINT_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:29:18: ( 'equal' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:29:20: 'equal'
            {
            match("equal"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT_EQUAL"

    // $ANTLR start "CONSTRAINT_FILTER"
    public final void mCONSTRAINT_FILTER() throws RecognitionException {
        try {
            int _type = CONSTRAINT_FILTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:30:19: ( 'filter' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:30:21: 'filter'
            {
            match("filter"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT_FILTER"

    // $ANTLR start "CONSTRAINT_RANDOMINT"
    public final void mCONSTRAINT_RANDOMINT() throws RecognitionException {
        try {
            int _type = CONSTRAINT_RANDOMINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:31:22: ( 'randomInt' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:31:24: 'randomInt'
            {
            match("randomInt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT_RANDOMINT"

    // $ANTLR start "CONSTRAINT_TRUE"
    public final void mCONSTRAINT_TRUE() throws RecognitionException {
        try {
            int _type = CONSTRAINT_TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:32:17: ( 'true' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:32:19: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT_TRUE"

    // $ANTLR start "CODE"
    public final void mCODE() throws RecognitionException {
        try {
            int _type = CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:245:3: ( '[[' ( options {greedy=false; } : . )* ']]' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:246:3: '[[' ( options {greedy=false; } : . )* ']]'
            {
            match("[["); 

            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:246:8: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==']') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==']') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1>='\u0000' && LA1_1<='\\')||(LA1_1>='^' && LA1_1<='\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0>='\u0000' && LA1_0<='\\')||(LA1_0>='^' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:246:34: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match("]]"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CODE"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:250:3: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )+ )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:251:3: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )+
            {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:251:3: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


               _channel = HIDDEN;
              

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:265:3: ( LETTER_OR_UNDERSCORE ( LETTER_OR_DIGIT_OR_UNDERSCORE )* )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:266:3: LETTER_OR_UNDERSCORE ( LETTER_OR_DIGIT_OR_UNDERSCORE )*
            {
            mLETTER_OR_UNDERSCORE(); 
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:266:24: ( LETTER_OR_DIGIT_OR_UNDERSCORE )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='.'||(LA3_0>='0' && LA3_0<=':')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:266:25: LETTER_OR_DIGIT_OR_UNDERSCORE
            	    {
            	    mLETTER_OR_DIGIT_OR_UNDERSCORE(); 

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
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:270:3: ( ( DIGIT )+ )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:271:3: ( DIGIT )+
            {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:271:3: ( DIGIT )+
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
            	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:271:4: DIGIT
            	    {
            	    mDIGIT(); 

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
    // $ANTLR end "INTEGER"

    // $ANTLR start "LETTER_OR_UNDERSCORE"
    public final void mLETTER_OR_UNDERSCORE() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:276:3: ( LETTER | UNDERSCORE )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
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
    // $ANTLR end "LETTER_OR_UNDERSCORE"

    // $ANTLR start "LETTER_OR_DIGIT_OR_UNDERSCORE"
    public final void mLETTER_OR_DIGIT_OR_UNDERSCORE() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:283:3: ( LETTER | DIGIT | UNDERSCORE | DOT | COLON )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:
            {
            if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
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
    // $ANTLR end "LETTER_OR_DIGIT_OR_UNDERSCORE"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:293:3: ( LOWER_CASE_LETTER | UPPER_CASE_LETTER )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:
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
    // $ANTLR end "LETTER"

    // $ANTLR start "LOWER_CASE_LETTER"
    public final void mLOWER_CASE_LETTER() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:300:3: ( 'a' .. 'z' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:301:3: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "LOWER_CASE_LETTER"

    // $ANTLR start "UPPER_CASE_LETTER"
    public final void mUPPER_CASE_LETTER() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:306:3: ( 'A' .. 'Z' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:307:3: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UPPER_CASE_LETTER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:312:3: ( '0' .. '9' )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:313:3: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:8: ( EQUAL | ASSIGNMENT | DOLLAR | UNDERSCORE | PAR_LEFT | PAR_RIGHT | BEGIN_CODE | END_CODE | COMMA | AND | DOT | COLON | QUOTE | CONSTRAINT_EQUAL | CONSTRAINT_FILTER | CONSTRAINT_RANDOMINT | CONSTRAINT_TRUE | CODE | WHITESPACE | IDENTIFIER | INTEGER )
        int alt5=21;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:10: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 2 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:16: ASSIGNMENT
                {
                mASSIGNMENT(); 

                }
                break;
            case 3 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:27: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 4 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:34: UNDERSCORE
                {
                mUNDERSCORE(); 

                }
                break;
            case 5 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:45: PAR_LEFT
                {
                mPAR_LEFT(); 

                }
                break;
            case 6 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:54: PAR_RIGHT
                {
                mPAR_RIGHT(); 

                }
                break;
            case 7 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:64: BEGIN_CODE
                {
                mBEGIN_CODE(); 

                }
                break;
            case 8 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:75: END_CODE
                {
                mEND_CODE(); 

                }
                break;
            case 9 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:84: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 10 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:90: AND
                {
                mAND(); 

                }
                break;
            case 11 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:94: DOT
                {
                mDOT(); 

                }
                break;
            case 12 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:98: COLON
                {
                mCOLON(); 

                }
                break;
            case 13 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:104: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 14 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:110: CONSTRAINT_EQUAL
                {
                mCONSTRAINT_EQUAL(); 

                }
                break;
            case 15 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:127: CONSTRAINT_FILTER
                {
                mCONSTRAINT_FILTER(); 

                }
                break;
            case 16 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:145: CONSTRAINT_RANDOMINT
                {
                mCONSTRAINT_RANDOMINT(); 

                }
                break;
            case 17 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:166: CONSTRAINT_TRUE
                {
                mCONSTRAINT_TRUE(); 

                }
                break;
            case 18 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:182: CODE
                {
                mCODE(); 

                }
                break;
            case 19 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:187: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 20 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:198: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 21 :
                // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:1:209: INTEGER
                {
                mINTEGER(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\2\uffff\1\25\1\uffff\1\26\10\uffff\4\22\6\uffff\1\34\4\22\2\uffff"+
        "\7\22\1\51\1\52\2\22\2\uffff\1\55\1\22\1\uffff\2\22\1\61\1\uffff";
    static final String DFA5_eofS =
        "\62\uffff";
    static final String DFA5_minS =
        "\1\11\1\uffff\1\75\1\uffff\1\56\2\uffff\1\133\5\uffff\1\161\1\151"+
        "\1\141\1\162\6\uffff\1\0\1\165\1\154\1\156\1\165\2\uffff\1\141\1"+
        "\164\1\144\1\145\1\154\1\145\1\157\2\56\1\162\1\155\2\uffff\1\56"+
        "\1\111\1\uffff\1\156\1\164\1\56\1\uffff";
    static final String DFA5_maxS =
        "\1\172\1\uffff\1\75\1\uffff\1\172\2\uffff\1\133\5\uffff\1\161\1"+
        "\151\1\141\1\162\6\uffff\1\uffff\1\165\1\154\1\156\1\165\2\uffff"+
        "\1\141\1\164\1\144\1\145\1\154\1\145\1\157\2\172\1\162\1\155\2\uffff"+
        "\1\172\1\111\1\uffff\1\156\1\164\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\5\1\6\1\uffff\1\10\1\11\1\12"+
        "\1\13\1\15\4\uffff\1\23\1\24\1\25\1\2\1\14\1\4\5\uffff\1\7\1\22"+
        "\13\uffff\1\21\1\16\2\uffff\1\17\3\uffff\1\20";
    static final String DFA5_specialS =
        "\27\uffff\1\0\32\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\21\1\uffff\2\21\22\uffff\1\21\3\uffff\1\3\1\uffff\1\12\1"+
            "\14\1\5\1\6\2\uffff\1\11\1\uffff\1\13\1\uffff\12\23\1\2\2\uffff"+
            "\1\1\3\uffff\32\22\1\7\1\uffff\1\10\1\uffff\1\4\1\uffff\4\22"+
            "\1\15\1\16\13\22\1\17\1\22\1\20\6\22",
            "",
            "\1\24",
            "",
            "\1\22\1\uffff\13\22\6\uffff\32\22\4\uffff\1\22\1\uffff\32"+
            "\22",
            "",
            "",
            "\1\27",
            "",
            "",
            "",
            "",
            "",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "",
            "",
            "",
            "",
            "",
            "",
            "\0\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "",
            "",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\22\1\uffff\13\22\6\uffff\32\22\4\uffff\1\22\1\uffff\32"+
            "\22",
            "\1\22\1\uffff\13\22\6\uffff\32\22\4\uffff\1\22\1\uffff\32"+
            "\22",
            "\1\53",
            "\1\54",
            "",
            "",
            "\1\22\1\uffff\13\22\6\uffff\32\22\4\uffff\1\22\1\uffff\32"+
            "\22",
            "\1\56",
            "",
            "\1\57",
            "\1\60",
            "\1\22\1\uffff\13\22\6\uffff\32\22\4\uffff\1\22\1\uffff\32"+
            "\22",
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
            return "1:1: Tokens : ( EQUAL | ASSIGNMENT | DOLLAR | UNDERSCORE | PAR_LEFT | PAR_RIGHT | BEGIN_CODE | END_CODE | COMMA | AND | DOT | COLON | QUOTE | CONSTRAINT_EQUAL | CONSTRAINT_FILTER | CONSTRAINT_RANDOMINT | CONSTRAINT_TRUE | CODE | WHITESPACE | IDENTIFIER | INTEGER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_23 = input.LA(1);

                        s = -1;
                        if ( ((LA5_23>='\u0000' && LA5_23<='\uFFFF')) ) {s = 29;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}