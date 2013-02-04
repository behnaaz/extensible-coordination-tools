// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g 2011-03-21 18:26:09

package org.ect.ea.extensions.clocks.parsers;

import java.lang.Integer;
import java.lang.Math;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList;
import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class TCADataParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NOT", "LPAREN", "RPAREN", "AND", "OR", "LEQ", "PLUS", "TRUE", "FALSE", "ASSIGN", "MINUS", "IDENT", "INT", "SOURCE", "TARGET", "WS"
    };
    public static final int SOURCE=17;
    public static final int INT=16;
    public static final int NOT=4;
    public static final int TARGET=18;
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


        public TCADataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TCADataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TCADataParser.tokenNames; }
    public String getGrammarFileName() { return "/ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g"; }



      /** Convenience constructor. Construct a new TCADataParser on the given 
        * String. The new TCADataParser is built using classes CommonTokenStream,
        * TCADataLexer and ANTLRStringStream. Parsing is done to check syntax. 
        */ 
        public TCADataParser(String input) {
          this(new CommonTokenStream(new TCADataLexer(new ANTLRStringStream(input))));
        }
        
        /** Convenience constructor. Construct a new TCADataParser on the given 
        * String. The new TCADataParser is built using classes CommonTokenStream,
        * TCADataLexer and ANTLRStringStream. 
        * @param codegen Whether parsing is done with the aim of code generation 
        * (as opposed to parsing with the aim of editing or syntax checking, for 
        * example). If this is false, the parser just checks for syntactical 
        * wellformedness. If true, the output may be different, for example, 
        * additional information/constraints may be added. 
        */
        public TCADataParser(String input, boolean codegen) {
          this(input);
          this.parseForCodeGen = codegen;
        }

        private boolean parseForCodeGen = false;

        private final static String DATA_PREFIX = CodegenUtils.DATA_PREFIX;  



    // $ANTLR start "data_constraint"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:69:1: data_constraint returns [String dc = \"\"] : (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) ( (op= AND | op= OR ) (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) )* ;
    public final String data_constraint() throws RecognitionException {
        String dc =  "";

        Token n=null;
        Token l=null;
        Token r=null;
        Token op=null;
        String b = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:71:5: ( (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) ( (op= AND | op= OR ) (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:71:7: (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) ( (op= AND | op= OR ) (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:71:7: (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==LPAREN||LA1_0==TRUE||(LA1_0>=MINUS && LA1_0<=TARGET)) ) {
                alt1=1;
            }
            else if ( (LA1_0==NOT) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return dc;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:72:9: b= boolatom
                    {
                    pushFollow(FOLLOW_boolatom_in_data_constraint207);
                    b=boolatom();

                    state._fsp--;
                    if (state.failed) return dc;
                    if ( state.backtracking==0 ) {
                      if (b != "") dc = b;
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:73:11: n= NOT l= LPAREN b= boolatom r= RPAREN
                    {
                    n=(Token)match(input,NOT,FOLLOW_NOT_in_data_constraint224); if (state.failed) return dc;
                    l=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_data_constraint228); if (state.failed) return dc;
                    pushFollow(FOLLOW_boolatom_in_data_constraint232);
                    b=boolatom();

                    state._fsp--;
                    if (state.failed) return dc;
                    r=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_data_constraint236); if (state.failed) return dc;
                    if ( state.backtracking==0 ) {
                      if (b != "") dc = dc + n.getText() + l.getText() + b + r.getText();
                    }

                    }
                    break;

            }

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:75:7: ( (op= AND | op= OR ) (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=AND && LA4_0<=OR)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:9: (op= AND | op= OR ) (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN )
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:9: (op= AND | op= OR )
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==AND) ) {
            	        alt2=1;
            	    }
            	    else if ( (LA2_0==OR) ) {
            	        alt2=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return dc;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 2, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:10: op= AND
            	            {
            	            op=(Token)match(input,AND,FOLLOW_AND_in_data_constraint269); if (state.failed) return dc;

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:17: op= OR
            	            {
            	            op=(Token)match(input,OR,FOLLOW_OR_in_data_constraint273); if (state.failed) return dc;

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:24: (b= boolatom | n= NOT l= LPAREN b= boolatom r= RPAREN )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==LPAREN||LA3_0==TRUE||(LA3_0>=MINUS && LA3_0<=TARGET)) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==NOT) ) {
            	        alt3=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return dc;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:76:25: b= boolatom
            	            {
            	            pushFollow(FOLLOW_boolatom_in_data_constraint279);
            	            b=boolatom();

            	            state._fsp--;
            	            if (state.failed) return dc;
            	            if ( state.backtracking==0 ) {
            	              if (b != "") dc = dc + op.getText() + b;
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:77:11: n= NOT l= LPAREN b= boolatom r= RPAREN
            	            {
            	            n=(Token)match(input,NOT,FOLLOW_NOT_in_data_constraint295); if (state.failed) return dc;
            	            l=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_data_constraint299); if (state.failed) return dc;
            	            pushFollow(FOLLOW_boolatom_in_data_constraint303);
            	            b=boolatom();

            	            state._fsp--;
            	            if (state.failed) return dc;
            	            r=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_data_constraint307); if (state.failed) return dc;
            	            if ( state.backtracking==0 ) {
            	              if (b != "") dc = dc + op.getText() + n.getText() + l.getText() + b + r.getText();
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return dc;
    }
    // $ANTLR end "data_constraint"


    // $ANTLR start "boolatom"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:81:1: boolatom returns [String at =\"\"] : ( TRUE | comparison );
    public final String boolatom() throws RecognitionException {
        String at = "";

        Token TRUE1=null;
        String comparison2 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:83:5: ( TRUE | comparison )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TRUE) ) {
                alt5=1;
            }
            else if ( (LA5_0==LPAREN||(LA5_0>=MINUS && LA5_0<=TARGET)) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return at;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:83:7: TRUE
                    {
                    TRUE1=(Token)match(input,TRUE,FOLLOW_TRUE_in_boolatom344); if (state.failed) return at;
                    if ( state.backtracking==0 ) {
                      at = (TRUE1!=null?TRUE1.getText():null);
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:84:7: comparison
                    {
                    pushFollow(FOLLOW_comparison_in_boolatom355);
                    comparison2=comparison();

                    state._fsp--;
                    if (state.failed) return at;
                    if ( state.backtracking==0 ) {
                      if (comparison2 != "") at = comparison2;
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
        return at;
    }
    // $ANTLR end "boolatom"


    // $ANTLR start "comparison"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:87:1: comparison returns [String comp = \"\"] : a1= expression ( ASSIGN a2= expression | LEQ a2= expression ) ;
    public final String comparison() throws RecognitionException {
        String comp =  "";

        Token ASSIGN3=null;
        Token LEQ4=null;
        String a1 = null;

        String a2 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:89:5: (a1= expression ( ASSIGN a2= expression | LEQ a2= expression ) )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:89:7: a1= expression ( ASSIGN a2= expression | LEQ a2= expression )
            {
            pushFollow(FOLLOW_expression_in_comparison385);
            a1=expression();

            state._fsp--;
            if (state.failed) return comp;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:90:7: ( ASSIGN a2= expression | LEQ a2= expression )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ASSIGN) ) {
                alt6=1;
            }
            else if ( (LA6_0==LEQ) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return comp;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:90:8: ASSIGN a2= expression
                    {
                    ASSIGN3=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_comparison394); if (state.failed) return comp;
                    pushFollow(FOLLOW_expression_in_comparison398);
                    a2=expression();

                    state._fsp--;
                    if (state.failed) return comp;
                    if ( state.backtracking==0 ) {
                      if (a1 != "") {comp = a1; if (a2 != "") comp = comp + (ASSIGN3!=null?ASSIGN3.getText():null) + a2;}
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:91:9: LEQ a2= expression
                    {
                    LEQ4=(Token)match(input,LEQ,FOLLOW_LEQ_in_comparison410); if (state.failed) return comp;
                    pushFollow(FOLLOW_expression_in_comparison414);
                    a2=expression();

                    state._fsp--;
                    if (state.failed) return comp;
                    if ( state.backtracking==0 ) {
                      if (a1 != "") {comp = a1; if (a2 != "") comp = comp + (LEQ4!=null?LEQ4.getText():null) + a2;}
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return comp;
    }
    // $ANTLR end "comparison"


    // $ANTLR start "expression"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:94:1: expression returns [String arith = \"\"] : ( ( MINUS element exp )=> MINUS element exp | ( element exp )=> element exp | ( MINUS element )=> MINUS element | ( element )=> element | ( LPAREN expression MINUS expression RPAREN exp )=> LPAREN e1= expression MINUS e2= expression RPAREN exp | LPAREN e1= expression MINUS e2= expression RPAREN );
    public final String expression() throws RecognitionException {
        String arith =  "";

        Token MINUS6=null;
        Token MINUS11=null;
        Token LPAREN13=null;
        Token MINUS14=null;
        Token RPAREN15=null;
        Token LPAREN17=null;
        Token MINUS18=null;
        Token RPAREN19=null;
        String e1 = null;

        String e2 = null;

        String element5 = null;

        String exp7 = null;

        String element8 = null;

        String exp9 = null;

        String element10 = null;

        String element12 = null;

        String exp16 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:96:5: ( ( MINUS element exp )=> MINUS element exp | ( element exp )=> element exp | ( MINUS element )=> MINUS element | ( element )=> element | ( LPAREN expression MINUS expression RPAREN exp )=> LPAREN e1= expression MINUS e2= expression RPAREN exp | LPAREN e1= expression MINUS e2= expression RPAREN )
            int alt7=6;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:96:7: ( MINUS element exp )=> MINUS element exp
                    {
                    MINUS6=(Token)match(input,MINUS,FOLLOW_MINUS_in_expression457); if (state.failed) return arith;
                    pushFollow(FOLLOW_element_in_expression459);
                    element5=element();

                    state._fsp--;
                    if (state.failed) return arith;
                    pushFollow(FOLLOW_exp_in_expression461);
                    exp7=exp();

                    state._fsp--;
                    if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (element5 != "") 
                              {arith = (MINUS6!=null?MINUS6.getText():null) + element5; 
                               if (exp7 != "") 
                                arith = arith + exp7;
                              }
                            
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:103:7: ( element exp )=> element exp
                    {
                    pushFollow(FOLLOW_element_in_expression519);
                    element8=element();

                    state._fsp--;
                    if (state.failed) return arith;
                    pushFollow(FOLLOW_exp_in_expression521);
                    exp9=exp();

                    state._fsp--;
                    if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (element8 != "") 
                              {arith = element8; 
                               if (exp9 != "") 
                                 arith = arith + exp9;
                               }
                            
                    }

                    }
                    break;
                case 3 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:110:7: ( MINUS element )=> MINUS element
                    {
                    MINUS11=(Token)match(input,MINUS,FOLLOW_MINUS_in_expression591); if (state.failed) return arith;
                    pushFollow(FOLLOW_element_in_expression593);
                    element10=element();

                    state._fsp--;
                    if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (element10 != "") 
                              arith = (MINUS11!=null?MINUS11.getText():null) + element10;
                            
                    }

                    }
                    break;
                case 4 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:114:7: ( element )=> element
                    {
                    pushFollow(FOLLOW_element_in_expression657);
                    element12=element();

                    state._fsp--;
                    if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (element12 != "") 
                              arith = element12;
                            
                    }

                    }
                    break;
                case 5 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:118:7: ( LPAREN expression MINUS expression RPAREN exp )=> LPAREN e1= expression MINUS e2= expression RPAREN exp
                    {
                    LPAREN13=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expression747); if (state.failed) return arith;
                    pushFollow(FOLLOW_expression_in_expression751);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return arith;
                    MINUS14=(Token)match(input,MINUS,FOLLOW_MINUS_in_expression753); if (state.failed) return arith;
                    pushFollow(FOLLOW_expression_in_expression757);
                    e2=expression();

                    state._fsp--;
                    if (state.failed) return arith;
                    RPAREN15=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expression759); if (state.failed) return arith;
                    pushFollow(FOLLOW_exp_in_expression761);
                    exp16=exp();

                    state._fsp--;
                    if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (e1 != "") { 
                              arith = (LPAREN13!=null?LPAREN13.getText():null) + e1; 
                              if (e2 != "") {
                                arith = arith + (MINUS14!=null?MINUS14.getText():null) + e2;
                              } 
                              arith = arith + (RPAREN15!=null?RPAREN15.getText():null); 
                              if (exp16 != "") {
                                arith = arith + exp16;
                              }
                            }
                            
                    }

                    }
                    break;
                case 6 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:130:7: LPAREN e1= expression MINUS e2= expression RPAREN
                    {
                    LPAREN17=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expression778); if (state.failed) return arith;
                    pushFollow(FOLLOW_expression_in_expression782);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return arith;
                    MINUS18=(Token)match(input,MINUS,FOLLOW_MINUS_in_expression784); if (state.failed) return arith;
                    pushFollow(FOLLOW_expression_in_expression788);
                    e2=expression();

                    state._fsp--;
                    if (state.failed) return arith;
                    RPAREN19=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expression790); if (state.failed) return arith;
                    if ( state.backtracking==0 ) {
                      if (e1 != "") { 
                              arith = (LPAREN17!=null?LPAREN17.getText():null) + e1; 
                              if (e2 != "") {
                                arith = arith + (MINUS18!=null?MINUS18.getText():null) + e2;
                              } 
                              arith = arith + (RPAREN19!=null?RPAREN19.getText():null); 
                            }
                            
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
        return arith;
    }
    // $ANTLR end "expression"


    // $ANTLR start "exp"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:141:1: exp returns [String e = \"\"] : ( ( PLUS expression exp )=> PLUS expression e1= exp | PLUS expression );
    public final String exp() throws RecognitionException {
        String e =  "";

        Token PLUS21=null;
        Token PLUS23=null;
        String e1 = null;

        String expression20 = null;

        String expression22 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:143:5: ( ( PLUS expression exp )=> PLUS expression e1= exp | PLUS expression )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==PLUS) ) {
                int LA8_1 = input.LA(2);

                if ( (synpred6_TCAData()) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return e;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return e;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:143:7: ( PLUS expression exp )=> PLUS expression e1= exp
                    {
                    PLUS21=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp831); if (state.failed) return e;
                    pushFollow(FOLLOW_expression_in_exp833);
                    expression20=expression();

                    state._fsp--;
                    if (state.failed) return e;
                    pushFollow(FOLLOW_exp_in_exp837);
                    e1=exp();

                    state._fsp--;
                    if (state.failed) return e;
                    if ( state.backtracking==0 ) {
                      if (expression20 != "") {e = (PLUS21!=null?PLUS21.getText():null) + expression20; if (e1 != "") e = e + e1;}
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:144:7: PLUS expression
                    {
                    PLUS23=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp847); if (state.failed) return e;
                    pushFollow(FOLLOW_expression_in_exp849);
                    expression22=expression();

                    state._fsp--;
                    if (state.failed) return e;
                    if ( state.backtracking==0 ) {
                      if (expression22 != "") e = (PLUS23!=null?PLUS23.getText():null) + expression22;
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
        return e;
    }
    // $ANTLR end "exp"


    // $ANTLR start "element"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:147:1: element returns [String elem = \"\"] : ( IDENT | ( MINUS INT )=> MINUS INT | INT | ( SOURCE IDENT ) | ( TARGET IDENT ) );
    public final String element() throws RecognitionException {
        String elem =  "";

        Token IDENT24=null;
        Token INT25=null;
        Token INT26=null;
        Token IDENT27=null;
        Token SOURCE28=null;
        Token IDENT29=null;
        Token TARGET30=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:155:5: ( IDENT | ( MINUS INT )=> MINUS INT | INT | ( SOURCE IDENT ) | ( TARGET IDENT ) )
            int alt9=5;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENT) ) {
                alt9=1;
            }
            else if ( (LA9_0==MINUS) && (synpred7_TCAData())) {
                alt9=2;
            }
            else if ( (LA9_0==INT) ) {
                alt9=3;
            }
            else if ( (LA9_0==SOURCE) ) {
                alt9=4;
            }
            else if ( (LA9_0==TARGET) ) {
                alt9=5;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return elem;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:155:7: IDENT
                    {
                    IDENT24=(Token)match(input,IDENT,FOLLOW_IDENT_in_element875); if (state.failed) return elem;
                    if ( state.backtracking==0 ) {
                      if ((IDENT24!=null?IDENT24.getText():null) != "") {elem = (IDENT24!=null?IDENT24.getText():null);}
                    }

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:156:7: ( MINUS INT )=> MINUS INT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_element892); if (state.failed) return elem;
                    INT25=(Token)match(input,INT,FOLLOW_INT_in_element894); if (state.failed) return elem;
                    if ( state.backtracking==0 ) {
                      if ((INT25!=null?INT25.getText():null) != "") elem = '-' + (INT25!=null?INT25.getText():null);
                    }

                    }
                    break;
                case 3 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:157:7: INT
                    {
                    INT26=(Token)match(input,INT,FOLLOW_INT_in_element905); if (state.failed) return elem;
                    if ( state.backtracking==0 ) {
                      if ((INT26!=null?INT26.getText():null) != "") elem = (INT26!=null?INT26.getText():null); 
                    }

                    }
                    break;
                case 4 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:158:7: ( SOURCE IDENT )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:158:7: ( SOURCE IDENT )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:158:8: SOURCE IDENT
                    {
                    SOURCE28=(Token)match(input,SOURCE,FOLLOW_SOURCE_in_element916); if (state.failed) return elem;
                    IDENT27=(Token)match(input,IDENT,FOLLOW_IDENT_in_element918); if (state.failed) return elem;

                    }

                    if ( state.backtracking==0 ) {
                      if (((IDENT27!=null?IDENT27.getText():null) != "") && ((SOURCE28!=null?SOURCE28.getText():null) != "")) elem = (SOURCE28!=null?SOURCE28.getText():null) + (IDENT27!=null?IDENT27.getText():null);
                    }

                    }
                    break;
                case 5 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:159:7: ( TARGET IDENT )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:159:7: ( TARGET IDENT )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:159:8: TARGET IDENT
                    {
                    TARGET30=(Token)match(input,TARGET,FOLLOW_TARGET_in_element930); if (state.failed) return elem;
                    IDENT29=(Token)match(input,IDENT,FOLLOW_IDENT_in_element932); if (state.failed) return elem;

                    }

                    if ( state.backtracking==0 ) {
                      if (((IDENT29!=null?IDENT29.getText():null) != "") && ((TARGET30!=null?TARGET30.getText():null) != "")) elem = (TARGET30!=null?TARGET30.getText():null) + (IDENT29!=null?IDENT29.getText():null);
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
        return elem;
    }
    // $ANTLR end "element"


    // $ANTLR start "get_port_names"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:167:1: get_port_names returns [EList<String> names = new BasicEList<String>()] : (~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* ;
    public final EList<String> get_port_names() throws RecognitionException {
        EList<String> names =  new BasicEList<String>();

        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:170:73: ( (~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:171:5: (~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:171:5: (~ ( IDENT | SOURCE | TARGET ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=NOT && LA10_0<=MINUS)||LA10_0==INT||LA10_0==WS) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:171:6: ~ ( IDENT | SOURCE | TARGET )
            	    {
            	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:172:5: ( (i= IDENT | SOURCE IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==IDENT||(LA13_0>=SOURCE && LA13_0<=TARGET)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:172:7: (i= IDENT | SOURCE IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )*
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:172:7: (i= IDENT | SOURCE IDENT | TARGET IDENT )
            	    int alt11=3;
            	    switch ( input.LA(1) ) {
            	    case IDENT:
            	        {
            	        alt11=1;
            	        }
            	        break;
            	    case SOURCE:
            	        {
            	        alt11=2;
            	        }
            	        break;
            	    case TARGET:
            	        {
            	        alt11=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 11, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt11) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:172:8: i= IDENT
            	            {
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_get_port_names987); if (state.failed) return names;
            	            if ( state.backtracking==0 ) {
            	               names.add(i.getText()); 
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:173:9: SOURCE IDENT
            	            {
            	            match(input,SOURCE,FOLLOW_SOURCE_in_get_port_names999); if (state.failed) return names;
            	            match(input,IDENT,FOLLOW_IDENT_in_get_port_names1001); if (state.failed) return names;

            	            }
            	            break;
            	        case 3 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:174:9: TARGET IDENT
            	            {
            	            match(input,TARGET,FOLLOW_TARGET_in_get_port_names1011); if (state.failed) return names;
            	            match(input,IDENT,FOLLOW_IDENT_in_get_port_names1013); if (state.failed) return names;

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:176:7: (~ ( IDENT | SOURCE | TARGET ) )*
            	    loop12:
            	    do {
            	        int alt12=2;
            	        int LA12_0 = input.LA(1);

            	        if ( ((LA12_0>=NOT && LA12_0<=MINUS)||LA12_0==INT||LA12_0==WS) ) {
            	            alt12=1;
            	        }


            	        switch (alt12) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:176:8: ~ ( IDENT | SOURCE | TARGET )
            	    	    {
            	    	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;state.failed=false;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return names;}
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop12;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return names;
    }
    // $ANTLR end "get_port_names"


    // $ANTLR start "get_sourceloc_memcell_names"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:180:1: get_sourceloc_memcell_names returns [EList<String> names = new BasicEList<String>()] : (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE i= IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* ;
    public final EList<String> get_sourceloc_memcell_names() throws RecognitionException {
        EList<String> names =  new BasicEList<String>();

        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:182:86: ( (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE i= IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:183:5: (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE i= IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:183:5: (~ ( IDENT | SOURCE | TARGET ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=NOT && LA14_0<=MINUS)||LA14_0==INT||LA14_0==WS) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:183:6: ~ ( IDENT | SOURCE | TARGET )
            	    {
            	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:184:5: ( ( IDENT | SOURCE i= IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==IDENT||(LA17_0>=SOURCE && LA17_0<=TARGET)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:184:7: ( IDENT | SOURCE i= IDENT | TARGET IDENT ) (~ ( IDENT | SOURCE | TARGET ) )*
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:184:7: ( IDENT | SOURCE i= IDENT | TARGET IDENT )
            	    int alt15=3;
            	    switch ( input.LA(1) ) {
            	    case IDENT:
            	        {
            	        alt15=1;
            	        }
            	        break;
            	    case SOURCE:
            	        {
            	        alt15=2;
            	        }
            	        break;
            	    case TARGET:
            	        {
            	        alt15=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 15, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt15) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:184:8: IDENT
            	            {
            	            match(input,IDENT,FOLLOW_IDENT_in_get_sourceloc_memcell_names1091); if (state.failed) return names;

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:185:9: SOURCE i= IDENT
            	            {
            	            match(input,SOURCE,FOLLOW_SOURCE_in_get_sourceloc_memcell_names1102); if (state.failed) return names;
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_get_sourceloc_memcell_names1106); if (state.failed) return names;
            	            if ( state.backtracking==0 ) {
            	               names.add(i.getText()); 
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:186:9: TARGET IDENT
            	            {
            	            match(input,TARGET,FOLLOW_TARGET_in_get_sourceloc_memcell_names1118); if (state.failed) return names;
            	            match(input,IDENT,FOLLOW_IDENT_in_get_sourceloc_memcell_names1120); if (state.failed) return names;

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:188:7: (~ ( IDENT | SOURCE | TARGET ) )*
            	    loop16:
            	    do {
            	        int alt16=2;
            	        int LA16_0 = input.LA(1);

            	        if ( ((LA16_0>=NOT && LA16_0<=MINUS)||LA16_0==INT||LA16_0==WS) ) {
            	            alt16=1;
            	        }


            	        switch (alt16) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:188:8: ~ ( IDENT | SOURCE | TARGET )
            	    	    {
            	    	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;state.failed=false;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return names;}
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop16;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return names;
    }
    // $ANTLR end "get_sourceloc_memcell_names"


    // $ANTLR start "get_targetloc_memcell_names"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:192:1: get_targetloc_memcell_names returns [EList<String> names = new BasicEList<String>()] : (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE IDENT | TARGET i= IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* ;
    public final EList<String> get_targetloc_memcell_names() throws RecognitionException {
        EList<String> names =  new BasicEList<String>();

        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:194:86: ( (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE IDENT | TARGET i= IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:195:5: (~ ( IDENT | SOURCE | TARGET ) )* ( ( IDENT | SOURCE IDENT | TARGET i= IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:195:5: (~ ( IDENT | SOURCE | TARGET ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=NOT && LA18_0<=MINUS)||LA18_0==INT||LA18_0==WS) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:195:6: ~ ( IDENT | SOURCE | TARGET )
            	    {
            	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:196:5: ( ( IDENT | SOURCE IDENT | TARGET i= IDENT ) (~ ( IDENT | SOURCE | TARGET ) )* )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==IDENT||(LA21_0>=SOURCE && LA21_0<=TARGET)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:196:7: ( IDENT | SOURCE IDENT | TARGET i= IDENT ) (~ ( IDENT | SOURCE | TARGET ) )*
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:196:7: ( IDENT | SOURCE IDENT | TARGET i= IDENT )
            	    int alt19=3;
            	    switch ( input.LA(1) ) {
            	    case IDENT:
            	        {
            	        alt19=1;
            	        }
            	        break;
            	    case SOURCE:
            	        {
            	        alt19=2;
            	        }
            	        break;
            	    case TARGET:
            	        {
            	        alt19=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return names;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 19, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt19) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:196:8: IDENT
            	            {
            	            match(input,IDENT,FOLLOW_IDENT_in_get_targetloc_memcell_names1198); if (state.failed) return names;

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:197:9: SOURCE IDENT
            	            {
            	            match(input,SOURCE,FOLLOW_SOURCE_in_get_targetloc_memcell_names1208); if (state.failed) return names;
            	            match(input,IDENT,FOLLOW_IDENT_in_get_targetloc_memcell_names1210); if (state.failed) return names;

            	            }
            	            break;
            	        case 3 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:198:9: TARGET i= IDENT
            	            {
            	            match(input,TARGET,FOLLOW_TARGET_in_get_targetloc_memcell_names1220); if (state.failed) return names;
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_get_targetloc_memcell_names1224); if (state.failed) return names;
            	            if ( state.backtracking==0 ) {
            	               names.add(i.getText()); 
            	            }

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:200:7: (~ ( IDENT | SOURCE | TARGET ) )*
            	    loop20:
            	    do {
            	        int alt20=2;
            	        int LA20_0 = input.LA(1);

            	        if ( ((LA20_0>=NOT && LA20_0<=MINUS)||LA20_0==INT||LA20_0==WS) ) {
            	            alt20=1;
            	        }


            	        switch (alt20) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:200:8: ~ ( IDENT | SOURCE | TARGET )
            	    	    {
            	    	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;state.failed=false;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return names;}
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop20;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return names;
    }
    // $ANTLR end "get_targetloc_memcell_names"


    // $ANTLR start "get_minmax_int_values"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:204:1: get_minmax_int_values returns [EList<String> result] : (~ ( INT | MINUS ) )* ( ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS ) (~ ( INT | MINUS ) )* )* ;
    public final EList<String> get_minmax_int_values() throws RecognitionException {
        EList<String> result = null;

        Token m=null;
        Token i=null;


          int min = -1;
          int max = 0;
          int temp;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:216:3: ( (~ ( INT | MINUS ) )* ( ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS ) (~ ( INT | MINUS ) )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:217:5: (~ ( INT | MINUS ) )* ( ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS ) (~ ( INT | MINUS ) )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:217:5: (~ ( INT | MINUS ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=NOT && LA22_0<=ASSIGN)||LA22_0==IDENT||(LA22_0>=SOURCE && LA22_0<=WS)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:217:6: ~ ( INT | MINUS )
            	    {
            	    if ( (input.LA(1)>=NOT && input.LA(1)<=ASSIGN)||input.LA(1)==IDENT||(input.LA(1)>=SOURCE && input.LA(1)<=WS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:5: ( ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS ) (~ ( INT | MINUS ) )* )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==MINUS||LA25_0==INT) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:7: ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS ) (~ ( INT | MINUS ) )*
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:7: ( ( MINUS INT )=>m= MINUS i= INT | i= INT | MINUS )
            	    int alt23=3;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0==MINUS) ) {
            	        int LA23_1 = input.LA(2);

            	        if ( (LA23_1==INT) ) {
            	            int LA23_3 = input.LA(3);

            	            if ( (synpred8_TCAData()) ) {
            	                alt23=1;
            	            }
            	            else if ( (true) ) {
            	                alt23=3;
            	            }
            	            else {
            	                if (state.backtracking>0) {state.failed=true; return result;}
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 23, 3, input);

            	                throw nvae;
            	            }
            	        }
            	        else if ( (LA23_1==EOF||(LA23_1>=NOT && LA23_1<=IDENT)||(LA23_1>=SOURCE && LA23_1<=WS)) ) {
            	            alt23=3;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return result;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 23, 1, input);

            	            throw nvae;
            	        }
            	    }
            	    else if ( (LA23_0==INT) ) {
            	        alt23=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:9: ( MINUS INT )=>m= MINUS i= INT
            	            {
            	            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_get_minmax_int_values1320); if (state.failed) return result;
            	            i=(Token)match(input,INT,FOLLOW_INT_in_get_minmax_int_values1324); if (state.failed) return result;
            	            if ( state.backtracking==0 ) {
            	               //MINUS is unary if input.LT(-3) is any of <=, =, +, -, (, &, !, null
            	                        if (input.LT(-3) == null) {                                           //unary minus at the beginning
            	                          temp = Integer.parseInt(m.getText() + i.getText());
            	                          min = Math.min(min,temp);
            	                          max = Math.max(max,temp);
            	                        } else if ((input.LT(-3).getType() == LEQ) ||
            	                                   (input.LT(-3).getType() == ASSIGN) ||
            	                                   (input.LT(-3).getType() == PLUS) ||
            	                                   (input.LT(-3).getType() == MINUS) || 
            	                                   (input.LT(-3).getType() == LPAREN) ||
            	                                   (input.LT(-3).getType() == AND) ||
            	                                   (input.LT(-3).getType() == NOT)) {                         //unary minus elsewhere
            	                          temp = Integer.parseInt(m.getText() + i.getText());
            	                          min = Math.min(min,temp);
            	                          max = Math.max(max,temp);
            	                        } else {                                                              //binary minus
            	                          temp = Integer.parseInt(i.getText());
            	                          min = Math.min(min,temp);
            	                          max = Math.max(max,temp);
            	                        }
            	                      
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:241:9: i= INT
            	            {
            	            i=(Token)match(input,INT,FOLLOW_INT_in_get_minmax_int_values1357); if (state.failed) return result;
            	            if ( state.backtracking==0 ) {

            	                        temp = Integer.parseInt(i.getText());
            	                        min = Math.min(min,temp);
            	                        max = Math.max(max,temp);
            	                      
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:247:9: MINUS
            	            {
            	            match(input,MINUS,FOLLOW_MINUS_in_get_minmax_int_values1378); if (state.failed) return result;

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:249:5: (~ ( INT | MINUS ) )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( ((LA24_0>=NOT && LA24_0<=ASSIGN)||LA24_0==IDENT||(LA24_0>=SOURCE && LA24_0<=WS)) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:249:6: ~ ( INT | MINUS )
            	    	    {
            	    	    if ( (input.LA(1)>=NOT && input.LA(1)<=ASSIGN)||input.LA(1)==IDENT||(input.LA(1)>=SOURCE && input.LA(1)<=WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;state.failed=false;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return result;}
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop24;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

                result = new BasicEList();
                result.add(String.valueOf(min));
                result.add(String.valueOf(max));

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "get_minmax_int_values"


    // $ANTLR start "addIndexRemoveMemcellPrefix"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:259:1: addIndexRemoveMemcellPrefix[String index, String nextindex] returns [String ret = \"\"] : (s=~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT ) (s=~ ( IDENT | SOURCE | TARGET ) )* )* ;
    public final String addIndexRemoveMemcellPrefix(String index, String nextindex) throws RecognitionException {
        String ret =  "";

        Token s=null;
        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:275:88: ( (s=~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT ) (s=~ ( IDENT | SOURCE | TARGET ) )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:276:5: (s=~ ( IDENT | SOURCE | TARGET ) )* ( (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT ) (s=~ ( IDENT | SOURCE | TARGET ) )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:276:5: (s=~ ( IDENT | SOURCE | TARGET ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=NOT && LA26_0<=MINUS)||LA26_0==INT||LA26_0==WS) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:276:6: s=~ ( IDENT | SOURCE | TARGET )
            	    {
            	    s=(Token)input.LT(1);
            	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ret;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      ret = ret + s.getText();
            	    }

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:278:5: ( (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT ) (s=~ ( IDENT | SOURCE | TARGET ) )* )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==IDENT||(LA29_0>=SOURCE && LA29_0<=TARGET)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:279:7: (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT ) (s=~ ( IDENT | SOURCE | TARGET ) )*
            	    {
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:279:7: (i= IDENT | SOURCE i= IDENT | TARGET i= IDENT )
            	    int alt27=3;
            	    switch ( input.LA(1) ) {
            	    case IDENT:
            	        {
            	        alt27=1;
            	        }
            	        break;
            	    case SOURCE:
            	        {
            	        alt27=2;
            	        }
            	        break;
            	    case TARGET:
            	        {
            	        alt27=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return ret;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 27, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt27) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:279:8: i= IDENT
            	            {
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1485); if (state.failed) return ret;
            	            if ( state.backtracking==0 ) {

            	                      if (!i.getText().equals(CodegenUtils.NO_FLOW_NAME)) {
            	                        ret = ret + DATA_PREFIX + i.getText() + "_" + nextindex + " "; //add space after name, since otherwise idents followed by arithmetic addition are not parsed/translated correctly
            	                      } else {
            	                        ret = ret + i.getText();
            	                      }
            	                    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:286:9: SOURCE i= IDENT
            	            {
            	            match(input,SOURCE,FOLLOW_SOURCE_in_addIndexRemoveMemcellPrefix1497); if (state.failed) return ret;
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1501); if (state.failed) return ret;
            	            if ( state.backtracking==0 ) {
            	              ret = ret + i.getText() + "_" + index + " ";
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:287:9: TARGET i= IDENT
            	            {
            	            match(input,TARGET,FOLLOW_TARGET_in_addIndexRemoveMemcellPrefix1513); if (state.failed) return ret;
            	            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1517); if (state.failed) return ret;
            	            if ( state.backtracking==0 ) {
            	              ret = ret + i.getText() + "_" + nextindex + " ";
            	            }

            	            }
            	            break;

            	    }

            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:289:7: (s=~ ( IDENT | SOURCE | TARGET ) )*
            	    loop28:
            	    do {
            	        int alt28=2;
            	        int LA28_0 = input.LA(1);

            	        if ( ((LA28_0>=NOT && LA28_0<=MINUS)||LA28_0==INT||LA28_0==WS) ) {
            	            alt28=1;
            	        }


            	        switch (alt28) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:289:8: s=~ ( IDENT | SOURCE | TARGET )
            	    	    {
            	    	    s=(Token)input.LT(1);
            	    	    if ( (input.LA(1)>=NOT && input.LA(1)<=MINUS)||input.LA(1)==INT||input.LA(1)==WS ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;state.failed=false;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return ret;}
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }

            	    	    if ( state.backtracking==0 ) {
            	    	      ret = ret + s.getText();
            	    	    }

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop28;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "addIndexRemoveMemcellPrefix"

    // $ANTLR start synpred1_TCAData
    public final void synpred1_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:96:7: ( MINUS element exp )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:96:8: MINUS element exp
        {
        match(input,MINUS,FOLLOW_MINUS_in_synpred1_TCAData449); if (state.failed) return ;
        pushFollow(FOLLOW_element_in_synpred1_TCAData451);
        element();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_exp_in_synpred1_TCAData453);
        exp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_TCAData

    // $ANTLR start synpred2_TCAData
    public final void synpred2_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:103:7: ( element exp )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:103:8: element exp
        {
        pushFollow(FOLLOW_element_in_synpred2_TCAData513);
        element();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_exp_in_synpred2_TCAData515);
        exp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_TCAData

    // $ANTLR start synpred3_TCAData
    public final void synpred3_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:110:7: ( MINUS element )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:110:8: MINUS element
        {
        match(input,MINUS,FOLLOW_MINUS_in_synpred3_TCAData585); if (state.failed) return ;
        pushFollow(FOLLOW_element_in_synpred3_TCAData587);
        element();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_TCAData

    // $ANTLR start synpred4_TCAData
    public final void synpred4_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:114:7: ( element )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:114:8: element
        {
        pushFollow(FOLLOW_element_in_synpred4_TCAData653);
        element();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_TCAData

    // $ANTLR start synpred5_TCAData
    public final void synpred5_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:118:7: ( LPAREN expression MINUS expression RPAREN exp )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:118:8: LPAREN expression MINUS expression RPAREN exp
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred5_TCAData733); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred5_TCAData735);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,MINUS,FOLLOW_MINUS_in_synpred5_TCAData737); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred5_TCAData739);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred5_TCAData741); if (state.failed) return ;
        pushFollow(FOLLOW_exp_in_synpred5_TCAData743);
        exp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_TCAData

    // $ANTLR start synpred6_TCAData
    public final void synpred6_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:143:7: ( PLUS expression exp )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:143:8: PLUS expression exp
        {
        match(input,PLUS,FOLLOW_PLUS_in_synpred6_TCAData823); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred6_TCAData825);
        expression();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_exp_in_synpred6_TCAData827);
        exp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_TCAData

    // $ANTLR start synpred7_TCAData
    public final void synpred7_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:156:7: ( MINUS INT )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:156:8: MINUS INT
        {
        match(input,MINUS,FOLLOW_MINUS_in_synpred7_TCAData886); if (state.failed) return ;
        match(input,INT,FOLLOW_INT_in_synpred7_TCAData888); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_TCAData

    // $ANTLR start synpred8_TCAData
    public final void synpred8_TCAData_fragment() throws RecognitionException {   
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:9: ( MINUS INT )
        // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAData.g:218:10: MINUS INT
        {
        match(input,MINUS,FOLLOW_MINUS_in_synpred8_TCAData1312); if (state.failed) return ;
        match(input,INT,FOLLOW_INT_in_synpred8_TCAData1314); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_TCAData

    // Delegated rules

    public final boolean synpred4_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred7_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_TCAData() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_TCAData_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\15\uffff";
    static final String DFA7_eofS =
        "\15\uffff";
    static final String DFA7_minS =
        "\1\5\6\0\6\uffff";
    static final String DFA7_maxS =
        "\1\22\6\0\6\uffff";
    static final String DFA7_acceptS =
        "\7\uffff\1\1\1\2\1\3\1\4\1\5\1\6";
    static final String DFA7_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\6\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\6\10\uffff\1\1\1\2\1\3\1\4\1\5",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
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
            return "94:1: expression returns [String arith = \"\"] : ( ( MINUS element exp )=> MINUS element exp | ( element exp )=> element exp | ( MINUS element )=> MINUS element | ( element )=> element | ( LPAREN expression MINUS expression RPAREN exp )=> LPAREN e1= expression MINUS e2= expression RPAREN exp | LPAREN e1= expression MINUS e2= expression RPAREN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_1 = input.LA(1);

                         
                        int index7_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_TCAData()) ) {s = 7;}

                        else if ( (synpred2_TCAData()) ) {s = 8;}

                        else if ( (synpred3_TCAData()) ) {s = 9;}

                        else if ( (synpred4_TCAData()) ) {s = 10;}

                         
                        input.seek(index7_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA7_2 = input.LA(1);

                         
                        int index7_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_TCAData()) ) {s = 8;}

                        else if ( (synpred4_TCAData()) ) {s = 10;}

                         
                        input.seek(index7_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA7_3 = input.LA(1);

                         
                        int index7_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_TCAData()) ) {s = 8;}

                        else if ( (synpred4_TCAData()) ) {s = 10;}

                         
                        input.seek(index7_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA7_4 = input.LA(1);

                         
                        int index7_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_TCAData()) ) {s = 8;}

                        else if ( (synpred4_TCAData()) ) {s = 10;}

                         
                        input.seek(index7_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA7_5 = input.LA(1);

                         
                        int index7_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_TCAData()) ) {s = 8;}

                        else if ( (synpred4_TCAData()) ) {s = 10;}

                         
                        input.seek(index7_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA7_6 = input.LA(1);

                         
                        int index7_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_TCAData()) ) {s = 11;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index7_6);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_boolatom_in_data_constraint207 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_NOT_in_data_constraint224 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_data_constraint228 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_boolatom_in_data_constraint232 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_data_constraint236 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_AND_in_data_constraint269 = new BitSet(new long[]{0x000000000007C830L});
    public static final BitSet FOLLOW_OR_in_data_constraint273 = new BitSet(new long[]{0x000000000007C830L});
    public static final BitSet FOLLOW_boolatom_in_data_constraint279 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_NOT_in_data_constraint295 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_data_constraint299 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_boolatom_in_data_constraint303 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_data_constraint307 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_TRUE_in_boolatom344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_boolatom355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison385 = new BitSet(new long[]{0x0000000000002200L});
    public static final BitSet FOLLOW_ASSIGN_in_comparison394 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_comparison398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQ_in_comparison410 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_comparison414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression457 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_element_in_expression459 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_expression461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_expression519 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_expression521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression591 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_element_in_expression593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_expression657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_expression747 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_expression751 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_MINUS_in_expression753 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_expression757 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_expression759 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_expression761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_expression778 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_expression782 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_MINUS_in_expression784 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_expression788 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_expression790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp831 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_exp833 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_exp837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp847 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_exp849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_element875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_element892 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_INT_in_element894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_element905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOURCE_in_element916 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_element918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TARGET_in_element930 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_element932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_get_port_names966 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_IDENT_in_get_port_names987 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_SOURCE_in_get_port_names999 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_port_names1001 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_TARGET_in_get_port_names1011 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_port_names1013 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_port_names1032 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_sourceloc_memcell_names1072 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_IDENT_in_get_sourceloc_memcell_names1091 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_SOURCE_in_get_sourceloc_memcell_names1102 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_sourceloc_memcell_names1106 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_TARGET_in_get_sourceloc_memcell_names1118 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_sourceloc_memcell_names1120 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_sourceloc_memcell_names1139 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_targetloc_memcell_names1179 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_IDENT_in_get_targetloc_memcell_names1198 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_SOURCE_in_get_targetloc_memcell_names1208 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_targetloc_memcell_names1210 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_TARGET_in_get_targetloc_memcell_names1220 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_get_targetloc_memcell_names1224 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_targetloc_memcell_names1244 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_minmax_int_values1294 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_MINUS_in_get_minmax_int_values1320 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_INT_in_get_minmax_int_values1324 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_INT_in_get_minmax_int_values1357 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_MINUS_in_get_minmax_int_values1378 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_get_minmax_int_values1394 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_addIndexRemoveMemcellPrefix1448 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1485 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_SOURCE_in_addIndexRemoveMemcellPrefix1497 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1501 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_TARGET_in_addIndexRemoveMemcellPrefix1513 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_addIndexRemoveMemcellPrefix1517 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_set_in_addIndexRemoveMemcellPrefix1539 = new BitSet(new long[]{0x00000000000FFFF2L});
    public static final BitSet FOLLOW_MINUS_in_synpred1_TCAData449 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_element_in_synpred1_TCAData451 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_synpred1_TCAData453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_synpred2_TCAData513 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_synpred2_TCAData515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_synpred3_TCAData585 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_element_in_synpred3_TCAData587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_synpred4_TCAData653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred5_TCAData733 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_synpred5_TCAData735 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_MINUS_in_synpred5_TCAData737 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_synpred5_TCAData739 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_synpred5_TCAData741 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_synpred5_TCAData743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_synpred6_TCAData823 = new BitSet(new long[]{0x000000000007C820L});
    public static final BitSet FOLLOW_expression_in_synpred6_TCAData825 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_exp_in_synpred6_TCAData827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_synpred7_TCAData886 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_INT_in_synpred7_TCAData888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_synpred8_TCAData1312 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_INT_in_synpred8_TCAData1314 = new BitSet(new long[]{0x0000000000000002L});

}