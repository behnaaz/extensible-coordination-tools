// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g 2011-03-26 15:51:16

  package org.ect.ea.extensions.clocks.parsers;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class TCAClocksLexer extends Lexer {
    public static final int LT=8;
    public static final int MEMCELL=22;
    public static final int LETTER=17;
    public static final int NUMBER=13;
    public static final int EQUALS=10;
    public static final int NOT=14;
    public static final int MINUS=20;
    public static final int AND=7;
    public static final int EOF=-1;
    public static final int TRUE=4;
    public static final int WS=23;
    public static final int LPARAN=15;
    public static final int COMMA=5;
    public static final int OR=19;
    public static final int RPARAN=16;
    public static final int IDENT=6;
    public static final int GT=12;
    public static final int DIGIT=18;
    public static final int GEQ=11;
    public static final int DOT=21;
    public static final int LEQ=9;

    // delegates
    // delegators

    public TCAClocksLexer() {;} 
    public TCAClocksLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TCAClocksLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g"; }

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:7:6: ( 'true' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:7:8: 'true'
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

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:254:7: ( LETTER ( LETTER | DIGIT )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:254:9: LETTER ( LETTER | DIGIT )*
            {
            mLETTER(); if (state.failed) return ;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:254:16: ( LETTER | DIGIT )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }
                else if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:254:17: LETTER
            	    {
            	    mLETTER(); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:254:26: DIGIT
            	    {
            	    mDIGIT(); if (state.failed) return ;

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

    // $ANTLR start "GEQ"
    public final void mGEQ() throws RecognitionException {
        try {
            int _type = GEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:255:5: ( '>=' | '=>' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='>') ) {
                alt2=1;
            }
            else if ( (LA2_0=='=') ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:255:7: '>='
                    {
                    match(">="); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:255:14: '=>'
                    {
                    match("=>"); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GEQ"

    // $ANTLR start "LEQ"
    public final void mLEQ() throws RecognitionException {
        try {
            int _type = LEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:256:5: ( '<=' | '=<' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='<') ) {
                alt3=1;
            }
            else if ( (LA3_0=='=') ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:256:7: '<='
                    {
                    match("<="); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:256:14: '=<'
                    {
                    match("=<"); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEQ"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:257:8: ( ( '==' )=> '==' | '=' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='=') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='=') && (synpred1_TCAClocks())) {
                    alt4=1;
                }
                else {
                    alt4=2;}
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:257:10: ( '==' )=> '=='
                    {
                    match("=="); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:257:26: '='
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
    // $ANTLR end "EQUALS"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:258:4: ( '>' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:258:6: '>'
            {
            match('>'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:259:4: ( '<' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:259:6: '<'
            {
            match('<'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:260:5: ( ( '&&' )=> '&&' | '&' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='&') ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1=='&') && (synpred2_TCAClocks())) {
                    alt5=1;
                }
                else {
                    alt5=2;}
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:260:7: ( '&&' )=> '&&'
                    {
                    match("&&"); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:260:22: '&'
                    {
                    match('&'); if (state.failed) return ;

                    }
                    break;

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
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:261:5: ( ( '||' )=> '||' | '|' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='|') ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='|') && (synpred3_TCAClocks())) {
                    alt6=1;
                }
                else {
                    alt6=2;}
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:261:7: ( '||' )=> '||'
                    {
                    match("||"); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:261:23: '|'
                    {
                    match('|'); if (state.failed) return ;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:262:9: ( DIGIT ( DIGIT )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:262:11: DIGIT ( DIGIT )*
            {
            mDIGIT(); if (state.failed) return ;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:262:17: ( DIGIT )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:262:18: DIGIT
            	    {
            	    mDIGIT(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:263:9: ( ',' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:263:11: ','
            {
            match(','); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "RPARAN"
    public final void mRPARAN() throws RecognitionException {
        try {
            int _type = RPARAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:264:9: ( ')' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:264:11: ')'
            {
            match(')'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPARAN"

    // $ANTLR start "LPARAN"
    public final void mLPARAN() throws RecognitionException {
        try {
            int _type = LPARAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:265:9: ( '(' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:265:11: '('
            {
            match('('); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPARAN"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:266:9: ( '!' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:266:11: '!'
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

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:267:9: ( '-' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:267:11: '-'
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

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:268:9: ( '.' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:268:11: '.'
            {
            match('.'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "MEMCELL"
    public final void mMEMCELL() throws RecognitionException {
        try {
            int _type = MEMCELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:269:9: ( '$' ( 's' | 't' ) DOT LETTER ( LETTER | DIGIT )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:269:11: '$' ( 's' | 't' ) DOT LETTER ( LETTER | DIGIT )*
            {
            match('$'); if (state.failed) return ;
            if ( (input.LA(1)>='s' && input.LA(1)<='t') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            mDOT(); if (state.failed) return ;
            mLETTER(); if (state.failed) return ;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:269:36: ( LETTER | DIGIT )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='A' && LA8_0<='Z')||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }
                else if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:269:37: LETTER
            	    {
            	    mLETTER(); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:269:46: DIGIT
            	    {
            	    mDIGIT(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MEMCELL"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:273:4: ( ( ' ' | '\\t' | '\\u0000' | '\\r' )+ )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:273:6: ( ' ' | '\\t' | '\\u0000' | '\\r' )+
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:273:6: ( ' ' | '\\t' | '\\u0000' | '\\r' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\u0000'||LA9_0=='\t'||LA9_0=='\r'||LA9_0==' ') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:
            	    {
            	    if ( input.LA(1)=='\u0000'||input.LA(1)=='\t'||input.LA(1)=='\r'||input.LA(1)==' ' ) {
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
            	    if ( cnt9 >= 1 ) break loop9;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
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

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:280:16: ( ( '0' .. '9' ) )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:280:18: ( '0' .. '9' )
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:280:18: ( '0' .. '9' )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:280:19: '0' .. '9'
            {
            matchRange('0','9'); if (state.failed) return ;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:281:17: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:281:19: ( 'a' .. 'z' | 'A' .. 'Z' )
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


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    public void mTokens() throws RecognitionException {
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:8: ( TRUE | IDENT | GEQ | LEQ | EQUALS | GT | LT | AND | OR | NUMBER | COMMA | RPARAN | LPARAN | NOT | MINUS | DOT | MEMCELL | WS )
        int alt10=18;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:10: TRUE
                {
                mTRUE(); if (state.failed) return ;

                }
                break;
            case 2 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:15: IDENT
                {
                mIDENT(); if (state.failed) return ;

                }
                break;
            case 3 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:21: GEQ
                {
                mGEQ(); if (state.failed) return ;

                }
                break;
            case 4 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:25: LEQ
                {
                mLEQ(); if (state.failed) return ;

                }
                break;
            case 5 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:29: EQUALS
                {
                mEQUALS(); if (state.failed) return ;

                }
                break;
            case 6 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:36: GT
                {
                mGT(); if (state.failed) return ;

                }
                break;
            case 7 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:39: LT
                {
                mLT(); if (state.failed) return ;

                }
                break;
            case 8 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:42: AND
                {
                mAND(); if (state.failed) return ;

                }
                break;
            case 9 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:46: OR
                {
                mOR(); if (state.failed) return ;

                }
                break;
            case 10 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:49: NUMBER
                {
                mNUMBER(); if (state.failed) return ;

                }
                break;
            case 11 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:56: COMMA
                {
                mCOMMA(); if (state.failed) return ;

                }
                break;
            case 12 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:62: RPARAN
                {
                mRPARAN(); if (state.failed) return ;

                }
                break;
            case 13 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:69: LPARAN
                {
                mLPARAN(); if (state.failed) return ;

                }
                break;
            case 14 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:76: NOT
                {
                mNOT(); if (state.failed) return ;

                }
                break;
            case 15 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:80: MINUS
                {
                mMINUS(); if (state.failed) return ;

                }
                break;
            case 16 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:86: DOT
                {
                mDOT(); if (state.failed) return ;

                }
                break;
            case 17 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:90: MEMCELL
                {
                mMEMCELL(); if (state.failed) return ;

                }
                break;
            case 18 :
                // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:1:98: WS
                {
                mWS(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_TCAClocks
    public final void synpred1_TCAClocks_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:257:10: ( '==' )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:257:11: '=='
        {
        match("=="); if (state.failed) return ;


        }
    }
    // $ANTLR end synpred1_TCAClocks

    // $ANTLR start synpred2_TCAClocks
    public final void synpred2_TCAClocks_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:260:7: ( '&&' )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:260:8: '&&'
        {
        match("&&"); if (state.failed) return ;


        }
    }
    // $ANTLR end synpred2_TCAClocks

    // $ANTLR start synpred3_TCAClocks
    public final void synpred3_TCAClocks_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:261:7: ( '||' )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:261:8: '||'
        {
        match("||"); if (state.failed) return ;


        }
    }
    // $ANTLR end synpred3_TCAClocks

    public final boolean synpred2_TCAClocks() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_TCAClocks_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_TCAClocks() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_TCAClocks_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_TCAClocks() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_TCAClocks_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\1\uffff\1\2\1\uffff\1\23\1\25\1\26\13\uffff\1\2\5\uffff\1\2\1\31"+
        "\1\uffff";
    static final String DFA10_eofS =
        "\32\uffff";
    static final String DFA10_minS =
        "\1\0\1\162\1\uffff\1\75\1\74\1\75\13\uffff\1\165\5\uffff\1\145\1"+
        "\60\1\uffff";
    static final String DFA10_maxS =
        "\1\174\1\162\1\uffff\1\75\1\76\1\75\13\uffff\1\165\5\uffff\1\145"+
        "\1\172\1\uffff";
    static final String DFA10_acceptS =
        "\2\uffff\1\2\3\uffff\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
        "\1\21\1\22\1\uffff\1\3\1\6\1\4\1\5\1\7\2\uffff\1\1";
    static final String DFA10_specialS =
        "\32\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\20\10\uffff\1\20\3\uffff\1\20\22\uffff\1\20\1\14\2\uffff"+
            "\1\17\1\uffff\1\6\1\uffff\1\13\1\12\2\uffff\1\11\1\15\1\16\1"+
            "\uffff\12\10\2\uffff\1\5\1\4\1\3\2\uffff\32\2\6\uffff\23\2\1"+
            "\1\6\2\1\uffff\1\7",
            "\1\21",
            "",
            "\1\22",
            "\1\24\1\uffff\1\22",
            "\1\24",
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
            "\1\27",
            "",
            "",
            "",
            "",
            "",
            "\1\30",
            "\12\2\7\uffff\32\2\6\uffff\32\2",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( TRUE | IDENT | GEQ | LEQ | EQUALS | GT | LT | AND | OR | NUMBER | COMMA | RPARAN | LPARAN | NOT | MINUS | DOT | MEMCELL | WS );";
        }
    }
 

}