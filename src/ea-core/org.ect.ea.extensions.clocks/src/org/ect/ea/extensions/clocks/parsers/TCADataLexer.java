// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g 2011-03-21 18:26:09

package org.ect.ea.extensions.clocks.parsers; 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class TCADataLexer extends Lexer {
    public static final int SOURCE=17;
    public static final int INT=16;
    public static final int TARGET=18;
    public static final int NOT=4;
    public static final int MINUS=14;
    public static final int AND=7;
    public static final int EOF=-1;
    public static final int TRUE=11;
    public static final int LPAREN=5;
    public static final int RPAREN=6;
    public static final int WS=19;
    public static final int OR=8;
    public static final int ASSIGN=13;
    public static final int IDENT=15;
    public static final int PLUS=10;
    public static final int FALSE=12;
    public static final int LEQ=9;

    // delegates
    // delegators

    public TCADataLexer() {;} 
    public TCADataLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TCADataLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g"; }

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:7:5: ( '!' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:7:7: '!'
            {
            match('!'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:8:8: ( '(' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:8:10: '('
            {
            match('('); if (state.failed) return ;

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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:9:8: ( ')' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:9:10: ')'
            {
            match(')'); if (state.failed) return ;

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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:10:5: ( '&' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:10:7: '&'
            {
            match('&'); if (state.failed) return ;

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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:11:4: ( '|' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:11:6: '|'
            {
            match('|'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "LEQ"
    public final void mLEQ() throws RecognitionException {
        try {
            int _type = LEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:12:5: ( '<=' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:12:7: '<='
            {
            match("<="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEQ"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:13:6: ( '+' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:13:8: '+'
            {
            match('+'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:14:6: ( 'true' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:14:8: 'true'
            {
            match("true"); if (state.failed) return ;


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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:15:7: ( 'false' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:15:9: 'false'
            {
            match("false"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:299:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:299:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:299:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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
    // $ANTLR end "IDENT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:301:7: ( ( '0' .. '9' )+ )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:301:9: ( '0' .. '9' )+
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:301:9: ( '0' .. '9' )+
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
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:301:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return ;}
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

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:305:7: ( '-' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:305:9: '-'
            {
            match('-'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "SOURCE"
    public final void mSOURCE() throws RecognitionException {
        try {
            int _type = SOURCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:308:8: ( '$s.' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:308:10: '$s.'
            {
            match("$s."); if (state.failed) return ;


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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:309:8: ( '$t.' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:309:10: '$t.'
            {
            match("$t."); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGET"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:311:8: ( ( '==' )=> '==' | '=' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='=') ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='=') && (synpred1_TCAData())) {
                    alt3=1;
                }
                else {
                    alt3=2;}
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:311:10: ( '==' )=> '=='
                    {
                    match("=="); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:311:26: '='
                    {
                    match('='); if (state.failed) return ;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:313:4: ( ( ' ' | '\\t' | '\\u0000' | '\\r' | '\\n' )+ )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:313:6: ( ' ' | '\\t' | '\\u0000' | '\\r' | '\\n' )+
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:313:6: ( ' ' | '\\t' | '\\u0000' | '\\r' | '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\u0000'||(LA4_0>='\t' && LA4_0<='\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:
            	    {
            	    if ( input.LA(1)=='\u0000'||(input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            if ( state.backtracking==0 ) {
               skip(); 
            }

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:8: ( NOT | LPAREN | RPAREN | AND | OR | LEQ | PLUS | TRUE | FALSE | IDENT | INT | MINUS | SOURCE | TARGET | ASSIGN | WS )
        int alt5=16;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:10: NOT
                {
                mNOT(); if (state.failed) return ;

                }
                break;
            case 2 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:14: LPAREN
                {
                mLPAREN(); if (state.failed) return ;

                }
                break;
            case 3 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:21: RPAREN
                {
                mRPAREN(); if (state.failed) return ;

                }
                break;
            case 4 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:28: AND
                {
                mAND(); if (state.failed) return ;

                }
                break;
            case 5 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:32: OR
                {
                mOR(); if (state.failed) return ;

                }
                break;
            case 6 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:35: LEQ
                {
                mLEQ(); if (state.failed) return ;

                }
                break;
            case 7 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:39: PLUS
                {
                mPLUS(); if (state.failed) return ;

                }
                break;
            case 8 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:44: TRUE
                {
                mTRUE(); if (state.failed) return ;

                }
                break;
            case 9 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:49: FALSE
                {
                mFALSE(); if (state.failed) return ;

                }
                break;
            case 10 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:55: IDENT
                {
                mIDENT(); if (state.failed) return ;

                }
                break;
            case 11 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:61: INT
                {
                mINT(); if (state.failed) return ;

                }
                break;
            case 12 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:65: MINUS
                {
                mMINUS(); if (state.failed) return ;

                }
                break;
            case 13 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:71: SOURCE
                {
                mSOURCE(); if (state.failed) return ;

                }
                break;
            case 14 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:78: TARGET
                {
                mTARGET(); if (state.failed) return ;

                }
                break;
            case 15 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:85: ASSIGN
                {
                mASSIGN(); if (state.failed) return ;

                }
                break;
            case 16 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:1:92: WS
                {
                mWS(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_TCAData
    public final void synpred1_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:311:10: ( '==' )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:311:11: '=='
        {
        match("=="); if (state.failed) return ;


        }
    }
    // $ANTLR end synpred1_TCAData

    public final boolean synpred1_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\10\uffff\2\12\6\uffff\2\12\2\uffff\2\12\1\30\1\12\1\uffff\1\32"+
        "\1\uffff";
    static final String DFA5_eofS =
        "\33\uffff";
    static final String DFA5_minS =
        "\1\0\7\uffff\1\162\1\141\3\uffff\1\163\2\uffff\1\165\1\154\2\uffff"+
        "\1\145\1\163\1\60\1\145\1\uffff\1\60\1\uffff";
    static final String DFA5_maxS =
        "\1\174\7\uffff\1\162\1\141\3\uffff\1\164\2\uffff\1\165\1\154\2\uffff"+
        "\1\145\1\163\1\172\1\145\1\uffff\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff\1\12\1\13\1\14\1\uffff"+
        "\1\17\1\20\2\uffff\1\15\1\16\4\uffff\1\10\1\uffff\1\11";
    static final String DFA5_specialS =
        "\33\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\17\10\uffff\2\17\2\uffff\1\17\22\uffff\1\17\1\1\2\uffff\1"+
            "\15\1\uffff\1\4\1\uffff\1\2\1\3\1\uffff\1\7\1\uffff\1\14\2\uffff"+
            "\12\13\2\uffff\1\6\1\16\3\uffff\32\12\6\uffff\5\12\1\11\15\12"+
            "\1\10\6\12\1\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "\1\21",
            "",
            "",
            "",
            "\1\22\1\23",
            "",
            "",
            "\1\24",
            "\1\25",
            "",
            "",
            "\1\26",
            "\1\27",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
            "\1\31",
            "",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
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
            return "1:1: Tokens : ( NOT | LPAREN | RPAREN | AND | OR | LEQ | PLUS | TRUE | FALSE | IDENT | INT | MINUS | SOURCE | TARGET | ASSIGN | WS );";
        }
    }
 

}