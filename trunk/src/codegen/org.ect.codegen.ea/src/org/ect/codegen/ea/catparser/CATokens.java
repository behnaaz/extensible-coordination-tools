/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g 2010-01-28 16:57:42

package org.ect.codegen.ea.catparser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CATokens extends Lexer {
    public static final int FUNCTION=26;
    public static final int STATE=21;
    public static final int GLOB=32;
    public static final int CA=19;
    public static final int PORT=20;
    public static final int SOURCE=17;
    public static final int CELL=23;
    public static final int INT=30;
    public static final int SEPARATOR=16;
    public static final int INITIALSTATE=4;
    public static final int TARGET=18;
    public static final int AND=10;
    public static final int EOF=-1;
    public static final int TRUE=9;
    public static final int LPAREN=12;
    public static final int TRANS=22;
    public static final int RPAREN=13;
    public static final int QUOTE=15;
    public static final int WS=33;
    public static final int IO=29;
    public static final int SPACER=7;
    public static final int BEGIN=5;
    public static final int OR=11;
    public static final int ASSIGN=14;
    public static final int CONSTANT=27;
    public static final int IDENT=31;
    public static final int PTYPE=24;
    public static final int ISSTART=25;
    public static final int END=6;
    public static final int FALSE=8;
    public static final int RELOP=28;

    // delegates
    // delegators

    public CATokens() {;} 
    public CATokens(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CATokens(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g"; }

    // $ANTLR start "INITIALSTATE"
    public final void mINITIALSTATE() throws RecognitionException {
        try {
            int _type = INITIALSTATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:7:13: ( '*' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:7:14: '*'
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:8:8: ( '[' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:8:11: '['
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:9:6: ( ']' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:9:9: ']'
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

    // $ANTLR start "SPACER"
    public final void mSPACER() throws RecognitionException {
        try {
            int _type = SPACER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:10:8: ( '::' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:10:11: '::'
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

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:13:9: ( 'false' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:13:11: 'false'
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

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:14:6: ( 'true' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:14:8: 'true'
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

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:15:6: ( '&' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:15:8: '&'
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:16:5: ( '|' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:16:7: '|'
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

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:17:8: ( '(' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:17:10: '('
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:18:8: ( ')' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:18:10: ')'
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

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:19:8: ( '=' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:19:10: '='
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

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:20:7: ( '\"' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:20:9: '\"'
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:21:11: ( ',' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:21:13: ','
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

    // $ANTLR start "SOURCE"
    public final void mSOURCE() throws RecognitionException {
        try {
            int _type = SOURCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:22:8: ( '$s.' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:22:10: '$s.'
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
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:23:8: ( '$t.' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:23:10: '$t.'
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

    // $ANTLR start "CA"
    public final void mCA() throws RecognitionException {
        try {
            int _type = CA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:26:5: ( 'CA' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:26:7: 'CA'
            {
            match("CA"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CA"

    // $ANTLR start "PORT"
    public final void mPORT() throws RecognitionException {
        try {
            int _type = PORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:27:6: ( 'PORT' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:27:8: 'PORT'
            {
            match("PORT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PORT"

    // $ANTLR start "STATE"
    public final void mSTATE() throws RecognitionException {
        try {
            int _type = STATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:28:7: ( 'STATE' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:28:9: 'STATE'
            {
            match("STATE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATE"

    // $ANTLR start "TRANS"
    public final void mTRANS() throws RecognitionException {
        try {
            int _type = TRANS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:29:8: ( 'TRANS' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:29:10: 'TRANS'
            {
            match("TRANS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRANS"

    // $ANTLR start "CELL"
    public final void mCELL() throws RecognitionException {
        try {
            int _type = CELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:30:6: ( 'CELL' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:30:8: 'CELL'
            {
            match("CELL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CELL"

    // $ANTLR start "PTYPE"
    public final void mPTYPE() throws RecognitionException {
        try {
            int _type = PTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:31:7: ( 'I/O' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:31:9: 'I/O'
            {
            match("I/O"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PTYPE"

    // $ANTLR start "ISSTART"
    public final void mISSTART() throws RecognitionException {
        try {
            int _type = ISSTART;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:32:9: ( 'START?' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:32:11: 'START?'
            {
            match("START?"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ISSTART"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:34:9: ( 'FUNCTION' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:34:11: 'FUNCTION'
            {
            match("FUNCTION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUNCTION"

    // $ANTLR start "CONSTANT"
    public final void mCONSTANT() throws RecognitionException {
        try {
            int _type = CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:35:9: ( 'CONSTANT' )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:35:11: 'CONSTANT'
            {
            match("CONSTANT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTANT"

    // $ANTLR start "RELOP"
    public final void mRELOP() throws RecognitionException {
        try {
            int _type = RELOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:7: ( '==' | '!=' | '<' | '<=' | '>' | '>=' )
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
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:9: '=='
                    {
                    match("=="); 


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:14: '!='
                    {
                    match("!="); 


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:19: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:23: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:28: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 6 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:38:32: '>='
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

    // $ANTLR start "IO"
    public final void mIO() throws RecognitionException {
        try {
            int _type = IO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:40:5: ( 'IN' | 'OUT' | 'UNKNOWN' )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 'I':
                {
                alt2=1;
                }
                break;
            case 'O':
                {
                alt2=2;
                }
                break;
            case 'U':
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
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:40:7: 'IN'
                    {
                    match("IN"); 


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:40:12: 'OUT'
                    {
                    match("OUT"); 


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:40:18: 'UNKNOWN'
                    {
                    match("UNKNOWN"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IO"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:42:6: ( ( '0' .. '9' )+ )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:42:8: ( '0' .. '9' )+
            {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:42:8: ( '0' .. '9' )+
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
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:42:8: '0' .. '9'
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

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:44:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:44:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:44:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:
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
            	    break loop4;
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

    // $ANTLR start "GLOB"
    public final void mGLOB() throws RecognitionException {
        try {
            int _type = GLOB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:46:6: ( QUOTE ( options {greedy=false; } : . )+ QUOTE )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:46:8: QUOTE ( options {greedy=false; } : . )+ QUOTE
            {
            mQUOTE(); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:46:14: ( options {greedy=false; } : . )+
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
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:46:40: .
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

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:48:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:48:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:48:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
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
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:
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
        // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:8: ( INITIALSTATE | BEGIN | END | SPACER | FALSE | TRUE | AND | OR | LPAREN | RPAREN | ASSIGN | QUOTE | SEPARATOR | SOURCE | TARGET | CA | PORT | STATE | TRANS | CELL | PTYPE | ISSTART | FUNCTION | CONSTANT | RELOP | IO | INT | IDENT | GLOB | WS )
        int alt7=30;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:10: INITIALSTATE
                {
                mINITIALSTATE(); 

                }
                break;
            case 2 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:23: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 3 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:29: END
                {
                mEND(); 

                }
                break;
            case 4 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:33: SPACER
                {
                mSPACER(); 

                }
                break;
            case 5 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:40: FALSE
                {
                mFALSE(); 

                }
                break;
            case 6 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:46: TRUE
                {
                mTRUE(); 

                }
                break;
            case 7 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:51: AND
                {
                mAND(); 

                }
                break;
            case 8 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:55: OR
                {
                mOR(); 

                }
                break;
            case 9 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:58: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 10 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:65: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 11 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:72: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 12 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:79: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 13 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:85: SEPARATOR
                {
                mSEPARATOR(); 

                }
                break;
            case 14 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:95: SOURCE
                {
                mSOURCE(); 

                }
                break;
            case 15 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:102: TARGET
                {
                mTARGET(); 

                }
                break;
            case 16 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:109: CA
                {
                mCA(); 

                }
                break;
            case 17 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:112: PORT
                {
                mPORT(); 

                }
                break;
            case 18 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:117: STATE
                {
                mSTATE(); 

                }
                break;
            case 19 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:123: TRANS
                {
                mTRANS(); 

                }
                break;
            case 20 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:129: CELL
                {
                mCELL(); 

                }
                break;
            case 21 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:134: PTYPE
                {
                mPTYPE(); 

                }
                break;
            case 22 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:140: ISSTART
                {
                mISSTART(); 

                }
                break;
            case 23 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:148: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 24 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:157: CONSTANT
                {
                mCONSTANT(); 

                }
                break;
            case 25 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:166: RELOP
                {
                mRELOP(); 

                }
                break;
            case 26 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:172: IO
                {
                mIO(); 

                }
                break;
            case 27 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:175: INT
                {
                mINT(); 

                }
                break;
            case 28 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:179: IDENT
                {
                mIDENT(); 

                }
                break;
            case 29 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:185: GLOB
                {
                mGLOB(); 

                }
                break;
            case 30 :
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CATokens.g:1:190: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\5\uffff\2\31\4\uffff\1\35\1\36\2\uffff\6\31\1\uffff\2\31\3\uffff"+
        "\2\31\5\uffff\1\57\5\31\1\uffff\1\65\5\31\1\uffff\5\31\1\uffff\1"+
        "\31\1\65\2\31\1\104\1\105\1\31\1\107\5\31\1\115\2\uffff\1\31\1\uffff"+
        "\1\117\1\31\1\121\2\31\1\uffff\1\31\3\uffff\4\31\1\65\1\131\1\132"+
        "\2\uffff";
    static final String DFA7_eofS =
        "\133\uffff";
    static final String DFA7_minS =
        "\1\11\4\uffff\1\141\1\162\4\uffff\1\75\1\0\1\uffff\1\163\1\101\1"+
        "\117\1\124\1\122\1\57\1\125\1\uffff\1\125\1\116\3\uffff\1\154\1"+
        "\165\5\uffff\1\60\1\114\1\116\1\122\2\101\1\uffff\1\60\1\116\1\124"+
        "\1\113\1\163\1\145\1\uffff\1\114\1\123\1\124\1\122\1\116\1\uffff"+
        "\1\103\1\60\1\116\1\145\2\60\1\124\1\60\1\105\1\124\1\123\1\124"+
        "\1\117\1\60\2\uffff\1\101\1\uffff\1\60\1\77\1\60\1\111\1\127\1\uffff"+
        "\1\116\3\uffff\1\117\1\116\1\124\1\116\3\60\2\uffff";
    static final String DFA7_maxS =
        "\1\174\4\uffff\1\141\1\162\4\uffff\1\75\1\uffff\1\uffff\1\164\2"+
        "\117\1\124\1\122\1\116\1\125\1\uffff\1\125\1\116\3\uffff\1\154\1"+
        "\165\5\uffff\1\172\1\114\1\116\1\122\2\101\1\uffff\1\172\1\116\1"+
        "\124\1\113\1\163\1\145\1\uffff\1\114\1\123\2\124\1\116\1\uffff\1"+
        "\103\1\172\1\116\1\145\2\172\1\124\1\172\1\105\1\124\1\123\1\124"+
        "\1\117\1\172\2\uffff\1\101\1\uffff\1\172\1\77\1\172\1\111\1\127"+
        "\1\uffff\1\116\3\uffff\1\117\1\116\1\124\1\116\3\172\2\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\7\1\10\1\11\1\12\2\uffff\1\15"+
        "\7\uffff\1\31\2\uffff\1\33\1\34\1\36\2\uffff\1\13\1\14\1\35\1\16"+
        "\1\17\6\uffff\1\25\6\uffff\1\20\5\uffff\1\32\16\uffff\1\6\1\24\1"+
        "\uffff\1\21\5\uffff\1\5\1\uffff\1\22\1\26\1\23\7\uffff\1\30\1\27";
    static final String DFA7_specialS =
        "\14\uffff\1\0\116\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\32\2\uffff\1\32\22\uffff\1\32\1\25\1\14\1\uffff\1\16\1\uffff"+
            "\1\7\1\uffff\1\11\1\12\1\1\1\uffff\1\15\3\uffff\12\30\1\4\1"+
            "\uffff\1\25\1\13\1\25\2\uffff\2\31\1\17\2\31\1\24\2\31\1\23"+
            "\5\31\1\26\1\20\2\31\1\21\1\22\1\27\5\31\1\2\1\uffff\1\3\3\uffff"+
            "\5\31\1\5\15\31\1\6\6\31\1\uffff\1\10",
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
            "\1\25",
            "\0\37",
            "",
            "\1\40\1\41",
            "\1\42\3\uffff\1\43\11\uffff\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50\36\uffff\1\51",
            "\1\52",
            "",
            "\1\53",
            "\1\54",
            "",
            "",
            "",
            "\1\55",
            "\1\56",
            "",
            "",
            "",
            "",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\77\1\uffff\1\76",
            "\1\100",
            "",
            "\1\101",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\102",
            "\1\103",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\106",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "",
            "\1\116",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\120",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\122",
            "\1\123",
            "",
            "\1\124",
            "",
            "",
            "",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
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
            return "1:1: Tokens : ( INITIALSTATE | BEGIN | END | SPACER | FALSE | TRUE | AND | OR | LPAREN | RPAREN | ASSIGN | QUOTE | SEPARATOR | SOURCE | TARGET | CA | PORT | STATE | TRANS | CELL | PTYPE | ISSTART | FUNCTION | CONSTANT | RELOP | IO | INT | IDENT | GLOB | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_12 = input.LA(1);

                        s = -1;
                        if ( ((LA7_12>='\u0000' && LA7_12<='\uFFFF')) ) {s = 31;}

                        else s = 30;

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