// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g 2011-03-26 15:51:15

  package org.ect.ea.extensions.clocks.parsers;

	import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;
	
	 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TCAClocksParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "TRUE", "COMMA", "IDENT", "AND", "LT", "LEQ", "EQUALS", "GEQ", "GT", "NUMBER", "NOT", "LPARAN", "RPARAN", "LETTER", "DIGIT", "OR", "MINUS", "DOT", "MEMCELL", "WS"
    };
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
    public static final int GEQ=11;
    public static final int DIGIT=18;
    public static final int DOT=21;
    public static final int LEQ=9;

    // delegates
    // delegators


        public TCAClocksParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TCAClocksParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TCAClocksParser.tokenNames; }
    public String getGrammarFileName() { return "/ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g"; }



      /** Convenience Constructor. Construct a new TCAClocksParser on the given 
        * String. The new TCAClocksParser is built using classes CommonTokenStream, 
        * TCAClocksLexer and ANTLRStringStream.
        */
      public TCAClocksParser(String input) {
        this(new CommonTokenStream(new TCAClocksLexer(new ANTLRStringStream(input))));
      }



    // $ANTLR start "automaton_clocks"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:37:1: automaton_clocks returns [String ret = \"\"] : s1= clock ( COMMA s1= clock )* ;
    public final String automaton_clocks() throws RecognitionException {
        String ret =  "";

        String s1 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:45:44: (s1= clock ( COMMA s1= clock )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:46:5: s1= clock ( COMMA s1= clock )*
            {
            pushFollow(FOLLOW_clock_in_automaton_clocks68);
            s1=clock();

            state._fsp--;

             if (s1 != "") ret = s1; 
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:48:5: ( COMMA s1= clock )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==COMMA) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:48:6: COMMA s1= clock
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_automaton_clocks84); 
            	    pushFollow(FOLLOW_clock_in_automaton_clocks88);
            	    s1=clock();

            	    state._fsp--;

            	     if (s1 != "") ret = ret.concat(",").concat(s1); 

            	    }
            	    break;

            	default :
            	    break loop1;
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
    // $ANTLR end "automaton_clocks"


    // $ANTLR start "clock"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:53:9: private clock returns [String ret = \"\"] : i= IDENT ;
    public final String clock() throws RecognitionException {
        String ret =  "";

        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:53:41: (i= IDENT )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:54:5: i= IDENT
            {
            i=(Token)match(input,IDENT,FOLLOW_IDENT_in_clock130); 
             ret = i.getText(); 

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
    // $ANTLR end "clock"


    // $ANTLR start "clock_constraint"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:58:1: clock_constraint returns [String ret = \"\"] : c= cc ( AND s1= cc )* ;
    public final String clock_constraint() throws RecognitionException {
        String ret =  "";

        String c = null;

        String s1 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:63:44: (c= cc ( AND s1= cc )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:64:5: c= cc ( AND s1= cc )*
            {
            pushFollow(FOLLOW_cc_in_clock_constraint164);
            c=cc();

            state._fsp--;

             ret = c; 
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:66:5: ( AND s1= cc )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:66:6: AND s1= cc
            	    {
            	    match(input,AND,FOLLOW_AND_in_clock_constraint180); 
            	    pushFollow(FOLLOW_cc_in_clock_constraint184);
            	    s1=cc();

            	    state._fsp--;

            	     if (s1!="") ret = ret + "&" + s1; 

            	    }
            	    break;

            	default :
            	    break loop2;
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
    // $ANTLR end "clock_constraint"


    // $ANTLR start "shape_clock_constraint_interstep"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:71:1: shape_clock_constraint_interstep[String index] returns [String ret = \"\"] : c= shape_cc_interstep[index] ( AND s1= shape_cc_interstep[index] )* ;
    public final String shape_clock_constraint_interstep(String index) throws RecognitionException {
        String ret =  "";

        String c = null;

        String s1 = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:75:75: (c= shape_cc_interstep[index] ( AND s1= shape_cc_interstep[index] )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:76:5: c= shape_cc_interstep[index] ( AND s1= shape_cc_interstep[index] )*
            {
            pushFollow(FOLLOW_shape_cc_interstep_in_shape_clock_constraint_interstep225);
            c=shape_cc_interstep(index);

            state._fsp--;

             ret = c;
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:78:5: ( AND s1= shape_cc_interstep[index] )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==AND) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:78:6: AND s1= shape_cc_interstep[index]
            	    {
            	    match(input,AND,FOLLOW_AND_in_shape_clock_constraint_interstep241); 
            	    pushFollow(FOLLOW_shape_cc_interstep_in_shape_clock_constraint_interstep245);
            	    s1=shape_cc_interstep(index);

            	    state._fsp--;

            	     if (s1!="") ret = ret + "&" + s1; 

            	    }
            	    break;

            	default :
            	    break loop3;
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
    // $ANTLR end "shape_clock_constraint_interstep"


    // $ANTLR start "shape_cc_interstep"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:83:9: private shape_cc_interstep[String index] returns [String ret = \"\"] : ( (c= shape_comparison_interstep[index] ) | (t= TRUE ) );
    public final String shape_cc_interstep(String index) throws RecognitionException {
        String ret =  "";

        Token t=null;
        String c = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:87:69: ( (c= shape_comparison_interstep[index] ) | (t= TRUE ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENT) ) {
                alt4=1;
            }
            else if ( (LA4_0==TRUE) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:88:5: (c= shape_comparison_interstep[index] )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:88:5: (c= shape_comparison_interstep[index] )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:88:6: c= shape_comparison_interstep[index]
                    {
                    pushFollow(FOLLOW_shape_comparison_interstep_in_shape_cc_interstep290);
                    c=shape_comparison_interstep(index);

                    state._fsp--;

                     ret = "(" + c + ")";

                    }


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:91:5: (t= TRUE )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:91:5: (t= TRUE )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:91:6: t= TRUE
                    {
                    t=(Token)match(input,TRUE,FOLLOW_TRUE_in_shape_cc_interstep316); 
                     ret =t.getText();

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
        return ret;
    }
    // $ANTLR end "shape_cc_interstep"


    // $ANTLR start "shape_comparison_interstep"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:96:9: private shape_comparison_interstep[String index] returns [String ret = \"\"] : c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER ;
    public final String shape_comparison_interstep(String index) throws RecognitionException {
        String ret =  "";

        Token n=null;
        String c = null;


        String add = (index.equals("")) ? "" : "_" + index; 
        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:102:61: (c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:103:9: c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER
            {
            pushFollow(FOLLOW_clock_in_shape_comparison_interstep370);
            c=clock();

            state._fsp--;

             ret = "(" + CodegenUtils.GLOBAL_CLOCK + add + "+1 - " + c + add + ")"; 
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:105:9: ( LT | LEQ | EQUALS | GEQ | GT )
            int alt5=5;
            switch ( input.LA(1) ) {
            case LT:
                {
                alt5=1;
                }
                break;
            case LEQ:
                {
                alt5=2;
                }
                break;
            case EQUALS:
                {
                alt5=3;
                }
                break;
            case GEQ:
                {
                alt5=4;
                }
                break;
            case GT:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:105:10: LT
                    {
                    match(input,LT,FOLLOW_LT_in_shape_comparison_interstep394); 
                     ret = ret.concat(" < "); 

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:107:10: LEQ
                    {
                    match(input,LEQ,FOLLOW_LEQ_in_shape_comparison_interstep418); 
                     ret = ret.concat(" <= "); 

                    }
                    break;
                case 3 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:109:10: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_shape_comparison_interstep442); 
                     ret = ret.concat(" = "); 

                    }
                    break;
                case 4 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:111:10: GEQ
                    {
                    match(input,GEQ,FOLLOW_GEQ_in_shape_comparison_interstep466); 
                     ret = ret.concat(" >= "); 

                    }
                    break;
                case 5 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:113:10: GT
                    {
                    match(input,GT,FOLLOW_GT_in_shape_comparison_interstep490); 
                     ret = ret.concat(" > "); 

                    }
                    break;

            }

            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_shape_comparison_interstep526); 
             ret = ret.concat(n.getText()); 

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
    // $ANTLR end "shape_comparison_interstep"


    // $ANTLR start "shape_clock_constraint"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:119:1: shape_clock_constraint[String index] returns [String ret = \"\"] : c= shape_cc[index] ( AND s= shape_cc[index] )* ;
    public final String shape_clock_constraint(String index) throws RecognitionException {
        String ret =  "";

        String c = null;

        String s = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:122:65: (c= shape_cc[index] ( AND s= shape_cc[index] )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:123:5: c= shape_cc[index] ( AND s= shape_cc[index] )*
            {
            pushFollow(FOLLOW_shape_cc_in_shape_clock_constraint559);
            c=shape_cc(index);

            state._fsp--;

             ret = c; 
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:125:5: ( AND s= shape_cc[index] )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==AND) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:125:6: AND s= shape_cc[index]
            	    {
            	    match(input,AND,FOLLOW_AND_in_shape_clock_constraint575); 
            	    pushFollow(FOLLOW_shape_cc_in_shape_clock_constraint579);
            	    s=shape_cc(index);

            	    state._fsp--;

            	     if (!s.equals("")) ret = ret + "&" + s; 

            	    }
            	    break;

            	default :
            	    break loop6;
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
    // $ANTLR end "shape_clock_constraint"


    // $ANTLR start "shape_cc"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:130:9: private shape_cc[String index] returns [String ret = \"\"] : ( (c= shape_comparison[index] ) | (t= TRUE ) );
    public final String shape_cc(String index) throws RecognitionException {
        String ret =  "";

        Token t=null;
        String c = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:133:58: ( (c= shape_comparison[index] ) | (t= TRUE ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==IDENT) ) {
                alt7=1;
            }
            else if ( (LA7_0==TRUE) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:134:5: (c= shape_comparison[index] )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:134:5: (c= shape_comparison[index] )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:134:6: c= shape_comparison[index]
                    {
                    pushFollow(FOLLOW_shape_comparison_in_shape_cc623);
                    c=shape_comparison(index);

                    state._fsp--;

                     ret = "(" + c + ")";

                    }


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:137:5: (t= TRUE )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:137:5: (t= TRUE )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:137:6: t= TRUE
                    {
                    t=(Token)match(input,TRUE,FOLLOW_TRUE_in_shape_cc649); 
                     ret =t.getText(); 

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
        return ret;
    }
    // $ANTLR end "shape_cc"


    // $ANTLR start "shape_comparison"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:142:9: private shape_comparison[String index] returns [String ret = \"\"] : c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER ;
    public final String shape_comparison(String index) throws RecognitionException {
        String ret =  "";

        Token n=null;
        String c = null;


        String add = (index.equals("")) ? "" : "_" + index; 
        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:147:62: (c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:148:9: c= clock ( LT | LEQ | EQUALS | GEQ | GT ) n= NUMBER
            {
            pushFollow(FOLLOW_clock_in_shape_comparison703);
            c=clock();

            state._fsp--;

             ret = "(" + CodegenUtils.GLOBAL_CLOCK + add + " - " + c + add + ")";
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:150:9: ( LT | LEQ | EQUALS | GEQ | GT )
            int alt8=5;
            switch ( input.LA(1) ) {
            case LT:
                {
                alt8=1;
                }
                break;
            case LEQ:
                {
                alt8=2;
                }
                break;
            case EQUALS:
                {
                alt8=3;
                }
                break;
            case GEQ:
                {
                alt8=4;
                }
                break;
            case GT:
                {
                alt8=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:150:10: LT
                    {
                    match(input,LT,FOLLOW_LT_in_shape_comparison727); 
                     ret = ret.concat(" < "); 

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:152:10: LEQ
                    {
                    match(input,LEQ,FOLLOW_LEQ_in_shape_comparison751); 
                     ret = ret.concat(" <= "); 

                    }
                    break;
                case 3 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:154:10: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_shape_comparison775); 
                     ret = ret.concat(" = "); 

                    }
                    break;
                case 4 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:156:10: GEQ
                    {
                    match(input,GEQ,FOLLOW_GEQ_in_shape_comparison799); 
                     ret = ret.concat(" >= "); 

                    }
                    break;
                case 5 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:158:10: GT
                    {
                    match(input,GT,FOLLOW_GT_in_shape_comparison823); 
                     ret = ret.concat(" > "); 

                    }
                    break;

            }

            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_shape_comparison859); 
             ret = ret.concat(n.getText()); 

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
    // $ANTLR end "shape_comparison"


    // $ANTLR start "cc"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:164:9: private cc returns [String ret = \"\"] : ( (c= comparison ) | ( TRUE ) );
    public final String cc() throws RecognitionException {
        String ret =  "";

        String c = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:164:38: ( (c= comparison ) | ( TRUE ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENT) ) {
                alt9=1;
            }
            else if ( (LA9_0==TRUE) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:165:5: (c= comparison )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:165:5: (c= comparison )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:165:6: c= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_cc887);
                    c=comparison();

                    state._fsp--;

                     if (c == "") ret = "true"; else ret = "(" + c + ")";

                    }


                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:168:5: ( TRUE )
                    {
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:168:5: ( TRUE )
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:168:6: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_cc910); 
                     ret = "true"; 

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
        return ret;
    }
    // $ANTLR end "cc"


    // $ANTLR start "comparison"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:173:9: private comparison returns [String ret = \"\"] : c= clock (o= LT | o= LEQ | o= EQUALS | o= GEQ | o= GT ) n= NUMBER ;
    public final String comparison() throws RecognitionException {
        String ret =  "";

        Token o=null;
        Token n=null;
        String c = null;


        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:174:46: (c= clock (o= LT | o= LEQ | o= EQUALS | o= GEQ | o= GT ) n= NUMBER )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:175:9: c= clock (o= LT | o= LEQ | o= EQUALS | o= GEQ | o= GT ) n= NUMBER
            {
            pushFollow(FOLLOW_clock_in_comparison956);
            c=clock();

            state._fsp--;

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:176:9: (o= LT | o= LEQ | o= EQUALS | o= GEQ | o= GT )
            int alt10=5;
            switch ( input.LA(1) ) {
            case LT:
                {
                alt10=1;
                }
                break;
            case LEQ:
                {
                alt10=2;
                }
                break;
            case EQUALS:
                {
                alt10=3;
                }
                break;
            case GEQ:
                {
                alt10=4;
                }
                break;
            case GT:
                {
                alt10=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:176:10: o= LT
                    {
                    o=(Token)match(input,LT,FOLLOW_LT_in_comparison969); 

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:177:10: o= LEQ
                    {
                    o=(Token)match(input,LEQ,FOLLOW_LEQ_in_comparison983); 

                    }
                    break;
                case 3 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:178:10: o= EQUALS
                    {
                    o=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_comparison996); 

                    }
                    break;
                case 4 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:179:10: o= GEQ
                    {
                    o=(Token)match(input,GEQ,FOLLOW_GEQ_in_comparison1009); 

                    }
                    break;
                case 5 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:180:10: o= GT
                    {
                    o=(Token)match(input,GT,FOLLOW_GT_in_comparison1023); 

                    }
                    break;

            }

            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_comparison1047); 
             ret = c + o.getText() + n.getText(); 

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
    // $ANTLR end "comparison"


    // $ANTLR start "clock_names"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:190:1: clock_names returns [EList<String> names = new BasicEList<String>()] : (~ IDENT )* (i= IDENT (~ IDENT )* )* ;
    public final EList<String> clock_names() throws RecognitionException {
        EList<String> names =  new BasicEList<String>();

        Token i=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:191:70: ( (~ IDENT )* (i= IDENT (~ IDENT )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:192:3: (~ IDENT )* (i= IDENT (~ IDENT )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:192:3: (~ IDENT )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=TRUE && LA11_0<=COMMA)||(LA11_0>=AND && LA11_0<=WS)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:192:4: ~ IDENT
            	    {
            	    if ( (input.LA(1)>=TRUE && input.LA(1)<=COMMA)||(input.LA(1)>=AND && input.LA(1)<=WS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:193:3: (i= IDENT (~ IDENT )* )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==IDENT) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:193:5: i= IDENT (~ IDENT )*
            	    {
            	    i=(Token)match(input,IDENT,FOLLOW_IDENT_in_clock_names1093); 
            	     names.add(i.getText()); 
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:194:5: (~ IDENT )*
            	    loop12:
            	    do {
            	        int alt12=2;
            	        int LA12_0 = input.LA(1);

            	        if ( ((LA12_0>=TRUE && LA12_0<=COMMA)||(LA12_0>=AND && LA12_0<=WS)) ) {
            	            alt12=1;
            	        }


            	        switch (alt12) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:194:6: ~ IDENT
            	    	    {
            	    	    if ( (input.LA(1)>=TRUE && input.LA(1)<=COMMA)||(input.LA(1)>=AND && input.LA(1)<=WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
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
    // $ANTLR end "clock_names"


    // $ANTLR start "clock_names_left"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:198:1: clock_names_left returns [EList<String> names = new BasicEList<String>()] : c= IDENT EQUALS ( IDENT | NUMBER ) ( COMMA c= IDENT EQUALS ( IDENT | NUMBER ) )* ;
    public final EList<String> clock_names_left() throws RecognitionException {
        EList<String> names =  new BasicEList<String>();

        Token c=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:199:75: (c= IDENT EQUALS ( IDENT | NUMBER ) ( COMMA c= IDENT EQUALS ( IDENT | NUMBER ) )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:200:5: c= IDENT EQUALS ( IDENT | NUMBER ) ( COMMA c= IDENT EQUALS ( IDENT | NUMBER ) )*
            {
            c=(Token)match(input,IDENT,FOLLOW_IDENT_in_clock_names_left1134); 
            match(input,EQUALS,FOLLOW_EQUALS_in_clock_names_left1136); 
            if ( input.LA(1)==IDENT||input.LA(1)==NUMBER ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            names.add(c.getText());
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:202:5: ( COMMA c= IDENT EQUALS ( IDENT | NUMBER ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:202:6: COMMA c= IDENT EQUALS ( IDENT | NUMBER )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_clock_names_left1160); 
            	    c=(Token)match(input,IDENT,FOLLOW_IDENT_in_clock_names_left1164); 
            	    match(input,EQUALS,FOLLOW_EQUALS_in_clock_names_left1166); 
            	    if ( input.LA(1)==IDENT||input.LA(1)==NUMBER ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    names.add(c.getText());

            	    }
            	    break;

            	default :
            	    break loop14;
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
    // $ANTLR end "clock_names_left"


    // $ANTLR start "update"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:207:1: update returns [String ret = \"\"] : c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) ( COMMA c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) )* ;
    public final String update() throws RecognitionException {
        String ret =  "";

        Token c1=null;
        Token e=null;
        Token c2=null;
        Token COMMA1=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:215:34: (c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) ( COMMA c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:216:5: c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) ( COMMA c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) )*
            {
            c1=(Token)match(input,IDENT,FOLLOW_IDENT_in_update1219); 
            e=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_update1223); 
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:216:23: (c2= IDENT | c2= NUMBER )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==IDENT) ) {
                alt15=1;
            }
            else if ( (LA15_0==NUMBER) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:216:24: c2= IDENT
                    {
                    c2=(Token)match(input,IDENT,FOLLOW_IDENT_in_update1228); 

                    }
                    break;
                case 2 :
                    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:216:35: c2= NUMBER
                    {
                    c2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_update1234); 

                    }
                    break;

            }

             ret = c1.getText() + e.getText() + c2.getText();
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:218:5: ( COMMA c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==COMMA) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:218:6: COMMA c1= IDENT e= EQUALS (c2= IDENT | c2= NUMBER )
            	    {
            	    COMMA1=(Token)match(input,COMMA,FOLLOW_COMMA_in_update1251); 
            	    c1=(Token)match(input,IDENT,FOLLOW_IDENT_in_update1255); 
            	    e=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_update1259); 
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:218:30: (c2= IDENT | c2= NUMBER )
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==IDENT) ) {
            	        alt16=1;
            	    }
            	    else if ( (LA16_0==NUMBER) ) {
            	        alt16=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 16, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:218:31: c2= IDENT
            	            {
            	            c2=(Token)match(input,IDENT,FOLLOW_IDENT_in_update1264); 

            	            }
            	            break;
            	        case 2 :
            	            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:218:42: c2= NUMBER
            	            {
            	            c2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_update1270); 

            	            }
            	            break;

            	    }

            	     ret = ret + (COMMA1!=null?COMMA1.getText():null) + c1.getText() + e.getText() + c2.getText();

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
        return ret;
    }
    // $ANTLR end "update"


    // $ANTLR start "subscript"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:225:1: subscript[final String index] returns [String result = \"\"] : (ni=~ IDENT )* (i= IDENT (nii=~ IDENT )* )* ;
    public final String subscript(final String index) throws RecognitionException {
        String result =  "";

        Token ni=null;
        Token i=null;
        Token nii=null;

        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:225:61: ( (ni=~ IDENT )* (i= IDENT (nii=~ IDENT )* )* )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:226:5: (ni=~ IDENT )* (i= IDENT (nii=~ IDENT )* )*
            {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:226:5: (ni=~ IDENT )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=TRUE && LA18_0<=COMMA)||(LA18_0>=AND && LA18_0<=WS)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:226:6: ni=~ IDENT
            	    {
            	    ni=(Token)input.LT(1);
            	    if ( (input.LA(1)>=TRUE && input.LA(1)<=COMMA)||(input.LA(1)>=AND && input.LA(1)<=WS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	          	result = result + " " + ni.getText();
            	          

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:231:5: (i= IDENT (nii=~ IDENT )* )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==IDENT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:231:6: i= IDENT (nii=~ IDENT )*
            	    {
            	    i=(Token)match(input,IDENT,FOLLOW_IDENT_in_subscript1347); 

            	          	result = result + " " + i.getText();
            	          	if (!(i.getText().equals(CodegenUtils.NO_FLOW_NAME))) result = result + "_" + index;
            	          
            	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:236:7: (nii=~ IDENT )*
            	    loop19:
            	    do {
            	        int alt19=2;
            	        int LA19_0 = input.LA(1);

            	        if ( ((LA19_0>=TRUE && LA19_0<=COMMA)||(LA19_0>=AND && LA19_0<=WS)) ) {
            	            alt19=1;
            	        }


            	        switch (alt19) {
            	    	case 1 :
            	    	    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:236:8: nii=~ IDENT
            	    	    {
            	    	    nii=(Token)input.LT(1);
            	    	    if ( (input.LA(1)>=TRUE && input.LA(1)<=COMMA)||(input.LA(1)>=AND && input.LA(1)<=WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	              result = result + " " + nii.getText();
            	    	            

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop19;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop20;
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
        return result;
    }
    // $ANTLR end "subscript"


    // $ANTLR start "getToKnowTokens"
    // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:245:9: private getToKnowTokens : ( NOT | LPARAN | RPARAN );
    public final void getToKnowTokens() throws RecognitionException {
        try {
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:245:25: ( NOT | LPARAN | RPARAN )
            // /ufs/kemper/repos/reo/ea/org.ect.ea.extensions.clocks/src/org.ect/ea/extensions/clocks/parsers/TCAClocks.g:
            {
            if ( (input.LA(1)>=NOT && input.LA(1)<=RPARAN) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "getToKnowTokens"

    // Delegated rules


 

    public static final BitSet FOLLOW_clock_in_automaton_clocks68 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_automaton_clocks84 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_clock_in_automaton_clocks88 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_clock130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cc_in_clock_constraint164 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_clock_constraint180 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_cc_in_clock_constraint184 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_shape_cc_interstep_in_shape_clock_constraint_interstep225 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_shape_clock_constraint_interstep241 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_shape_cc_interstep_in_shape_clock_constraint_interstep245 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_shape_comparison_interstep_in_shape_cc_interstep290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_shape_cc_interstep316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clock_in_shape_comparison_interstep370 = new BitSet(new long[]{0x0000000000001F00L});
    public static final BitSet FOLLOW_LT_in_shape_comparison_interstep394 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LEQ_in_shape_comparison_interstep418 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_EQUALS_in_shape_comparison_interstep442 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GEQ_in_shape_comparison_interstep466 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GT_in_shape_comparison_interstep490 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NUMBER_in_shape_comparison_interstep526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_cc_in_shape_clock_constraint559 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_shape_clock_constraint575 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_shape_cc_in_shape_clock_constraint579 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_shape_comparison_in_shape_cc623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_shape_cc649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clock_in_shape_comparison703 = new BitSet(new long[]{0x0000000000001F00L});
    public static final BitSet FOLLOW_LT_in_shape_comparison727 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LEQ_in_shape_comparison751 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_EQUALS_in_shape_comparison775 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GEQ_in_shape_comparison799 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GT_in_shape_comparison823 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NUMBER_in_shape_comparison859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_cc887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_cc910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clock_in_comparison956 = new BitSet(new long[]{0x0000000000001F00L});
    public static final BitSet FOLLOW_LT_in_comparison969 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LEQ_in_comparison983 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_EQUALS_in_comparison996 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GEQ_in_comparison1009 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_GT_in_comparison1023 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NUMBER_in_comparison1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_clock_names1081 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_IDENT_in_clock_names1093 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_set_in_clock_names1103 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_IDENT_in_clock_names_left1134 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQUALS_in_clock_names_left1136 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_set_in_clock_names_left1138 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_clock_names_left1160 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_IDENT_in_clock_names_left1164 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQUALS_in_clock_names_left1166 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_set_in_clock_names_left1168 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_update1219 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQUALS_in_update1223 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_IDENT_in_update1228 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_NUMBER_in_update1234 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_update1251 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_IDENT_in_update1255 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQUALS_in_update1259 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_IDENT_in_update1264 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_NUMBER_in_update1270 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_set_in_subscript1322 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_IDENT_in_subscript1347 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_set_in_subscript1367 = new BitSet(new long[]{0x0000000000FFFFF2L});
    public static final BitSet FOLLOW_set_in_getToKnowTokens0 = new BitSet(new long[]{0x0000000000000002L});

}