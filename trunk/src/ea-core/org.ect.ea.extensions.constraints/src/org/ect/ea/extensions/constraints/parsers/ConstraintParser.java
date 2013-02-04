// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g 2010-02-23 15:18:53

package org.ect.ea.extensions.constraints.parsers;

import org.eclipse.emf.ecore.EReference;
import org.ect.ea.extensions.constraints.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ConstraintParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "LPAREN", "RPAREN", "AND", "OR", "ASSIGN", "TRUE", "FALSE", "ARBITRARY", "SOURCE", "TARGET", "QUOTE", "SEPARATOR", "RELOP", "IDENT", "INT", "GLOB", "TYPE", "WS"
    };
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
    public static final int FALSE=10;
    public static final int ARBITRARY=11;
    public static final int RELOP=16;

    // delegates
    // delegators


        public ConstraintParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ConstraintParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ConstraintParser.tokenNames; }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g"; }



    // $ANTLR start "start"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:32:1: start returns [Constraint con] : disjunction ;
    public final Constraint start() throws RecognitionException {
        Constraint con = null;

        Constraint disjunction1 = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:33:25: ( disjunction )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:34:2: disjunction
            {
            pushFollow(FOLLOW_disjunction_in_start176);
            disjunction1=disjunction();

            state._fsp--;

            con =disjunction1;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return con;
    }
    // $ANTLR end "start"


    // $ANTLR start "disjunction"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:37:1: disjunction returns [Constraint dis] : first= conjunction ( OR next= conjunction )* ;
    public final Constraint disjunction() throws RecognitionException {
        Constraint dis = null;

        Constraint first = null;

        Constraint next = null;



        List<Constraint> rest = new ArrayList<Constraint>();

        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:51:2: (first= conjunction ( OR next= conjunction )* )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:51:4: first= conjunction ( OR next= conjunction )*
            {
            pushFollow(FOLLOW_conjunction_in_disjunction209);
            first=conjunction();

            state._fsp--;

            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:52:3: ( OR next= conjunction )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:52:4: OR next= conjunction
            	    {
            	    match(input,OR,FOLLOW_OR_in_disjunction217); 
            	    pushFollow(FOLLOW_conjunction_in_disjunction221);
            	    next=conjunction();

            	    state._fsp--;

            	     rest.add(next); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             
            if (rest.isEmpty()) 
            	dis =first; 
            else {  
            	dis = new Disjunction();
            	((Composite) dis).getMembers().add(first); 
            	((Composite) dis).getMembers().addAll(rest);
            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return dis;
    }
    // $ANTLR end "disjunction"


    // $ANTLR start "conjunction"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:56:1: conjunction returns [Constraint con] : first= composite ( AND next= composite )* ;
    public final Constraint conjunction() throws RecognitionException {
        Constraint con = null;

        Constraint first = null;

        Constraint next = null;


         
        List<Constraint> rest = new ArrayList<Constraint>();

        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:70:2: (first= composite ( AND next= composite )* )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:70:4: first= composite ( AND next= composite )*
            {
            pushFollow(FOLLOW_composite_in_conjunction259);
            first=composite();

            state._fsp--;

            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:71:3: ( AND next= composite )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:71:4: AND next= composite
            	    {
            	    match(input,AND,FOLLOW_AND_in_conjunction267); 
            	    pushFollow(FOLLOW_composite_in_conjunction271);
            	    next=composite();

            	    state._fsp--;

            	     rest.add(next); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

             
            if (rest.isEmpty()) 
            	con =first; 
            else {  
            	con = new Conjunction();
            	((Composite) con).getMembers().add(first); 
            	((Composite) con).getMembers().addAll(rest);
            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return con;
    }
    // $ANTLR end "conjunction"


    // $ANTLR start "composite"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:75:1: composite returns [Constraint comp] : ( LPAREN disjunction RPAREN | equation | literal );
    public final Constraint composite() throws RecognitionException {
        Constraint comp = null;

        Constraint disjunction2 = null;

        Constraint equation3 = null;

        Literal literal4 = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:78:2: ( LPAREN disjunction RPAREN | equation | literal )
            int alt3=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt3=1;
                }
                break;
            case TRUE:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==EOF||(LA3_2>=RPAREN && LA3_2<=OR)) ) {
                    alt3=3;
                }
                else if ( (LA3_2==ASSIGN||LA3_2==RELOP) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA3_3 = input.LA(2);

                if ( (LA3_3==ASSIGN||LA3_3==RELOP) ) {
                    alt3=2;
                }
                else if ( (LA3_3==EOF||(LA3_3>=RPAREN && LA3_3<=OR)) ) {
                    alt3=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;
                }
                }
                break;
            case ARBITRARY:
            case SOURCE:
            case TARGET:
            case IDENT:
            case INT:
            case GLOB:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:78:4: LPAREN disjunction RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_composite300); 
                    pushFollow(FOLLOW_disjunction_in_composite302);
                    disjunction2=disjunction();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_composite304); 
                     comp =disjunction2; 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:79:4: equation
                    {
                    pushFollow(FOLLOW_equation_in_composite311);
                    equation3=equation();

                    state._fsp--;

                     comp =equation3; 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:80:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_composite322);
                    literal4=literal();

                    state._fsp--;

                     comp =literal4; 

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
        return comp;
    }
    // $ANTLR end "composite"


    // $ANTLR start "equation"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:83:1: equation returns [Constraint eqn] : left= parameter ( ASSIGN right= parameter | RELOP right= parameter ) ;
    public final Constraint equation() throws RecognitionException {
        Constraint eqn = null;

        Token RELOP5=null;
        Parameter left = null;

        Parameter right = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:85:2: (left= parameter ( ASSIGN right= parameter | RELOP right= parameter ) )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:85:4: left= parameter ( ASSIGN right= parameter | RELOP right= parameter )
            {
            pushFollow(FOLLOW_parameter_in_equation348);
            left=parameter();

            state._fsp--;

            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:86:3: ( ASSIGN right= parameter | RELOP right= parameter )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ASSIGN) ) {
                alt4=1;
            }
            else if ( (LA4_0==RELOP) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:86:4: ASSIGN right= parameter
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_equation357); 
                    pushFollow(FOLLOW_parameter_in_equation361);
                    right=parameter();

                    state._fsp--;

                     eqn = new Equation(left, right);	

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:88:4: RELOP right= parameter
                    {
                    RELOP5=(Token)match(input,RELOP,FOLLOW_RELOP_in_equation375); 
                    pushFollow(FOLLOW_parameter_in_equation379);
                    right=parameter();

                    state._fsp--;

                     eqn = new Predicate(left, right, PredicateType.get((RELOP5!=null?RELOP5.getText():null))); 

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
        return eqn;
    }
    // $ANTLR end "equation"


    // $ANTLR start "parameter"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:94:1: parameter returns [Parameter parm] : ( literal | element | function );
    public final Parameter parameter() throws RecognitionException {
        Parameter parm = null;

        Literal literal6 = null;

        Element element7 = null;

        Function function8 = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:96:2: ( literal | element | function )
            int alt5=3;
            switch ( input.LA(1) ) {
            case TRUE:
            case FALSE:
                {
                alt5=1;
                }
                break;
            case IDENT:
                {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==EOF||(LA5_2>=RPAREN && LA5_2<=ASSIGN)||(LA5_2>=SEPARATOR && LA5_2<=RELOP)) ) {
                    alt5=2;
                }
                else if ( (LA5_2==LPAREN) ) {
                    alt5=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
                }
                break;
            case ARBITRARY:
            case SOURCE:
            case TARGET:
            case INT:
            case GLOB:
                {
                alt5=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:96:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_parameter411);
                    literal6=literal();

                    state._fsp--;

                     parm =literal6; 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:97:6: element
                    {
                    pushFollow(FOLLOW_element_in_parameter423);
                    element7=element();

                    state._fsp--;

                     parm =element7; 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:98:9: function
                    {
                    pushFollow(FOLLOW_function_in_parameter438);
                    function8=function();

                    state._fsp--;

                     parm =function8; 

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
        return parm;
    }
    // $ANTLR end "parameter"


    // $ANTLR start "function"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:101:1: function returns [Function func] : IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN ;
    public final Function function() throws RecognitionException {
        Function func = null;

        Token IDENT9=null;
        Parameter first = null;

        Parameter next = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:103:2: ( IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN )
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:103:6: IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN
            {
            IDENT9=(Token)match(input,IDENT,FOLLOW_IDENT_in_function468); 
             func = new Function((IDENT9!=null?IDENT9.getText():null)); 
            match(input,LPAREN,FOLLOW_LPAREN_in_function480); 
            pushFollow(FOLLOW_parameter_in_function484);
            first=parameter();

            state._fsp--;

             func.getParameters().add(first); 
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:105:3: ( SEPARATOR next= parameter )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==SEPARATOR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:105:5: SEPARATOR next= parameter
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_function494); 
            	    pushFollow(FOLLOW_parameter_in_function498);
            	    next=parameter();

            	    state._fsp--;

            	     func.getParameters().add(next); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,RPAREN,FOLLOW_RPAREN_in_function508); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return func;
    }
    // $ANTLR end "function"


    // $ANTLR start "element"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:109:1: element returns [Element elem] : ( IDENT | INT | GLOB | SOURCE IDENT | TARGET IDENT | ARBITRARY );
    public final Element element() throws RecognitionException {
        Element elem = null;

        Token IDENT10=null;
        Token INT11=null;
        Token GLOB12=null;
        Token IDENT13=null;
        Token IDENT14=null;

        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:111:7: ( IDENT | INT | GLOB | SOURCE IDENT | TARGET IDENT | ARBITRARY )
            int alt7=6;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt7=1;
                }
                break;
            case INT:
                {
                alt7=2;
                }
                break;
            case GLOB:
                {
                alt7=3;
                }
                break;
            case SOURCE:
                {
                alt7=4;
                }
                break;
            case TARGET:
                {
                alt7=5;
                }
                break;
            case ARBITRARY:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:111:10: IDENT
                    {
                    IDENT10=(Token)match(input,IDENT,FOLLOW_IDENT_in_element530); 
                     elem = new Element((IDENT10!=null?IDENT10.getText():null), ElementType.IDENTIFIER); 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:112:10: INT
                    {
                    INT11=(Token)match(input,INT,FOLLOW_INT_in_element546); 
                     elem = new Element((INT11!=null?INT11.getText():null), ElementType.INTEGER); 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:113:10: GLOB
                    {
                    GLOB12=(Token)match(input,GLOB,FOLLOW_GLOB_in_element563); 
                     elem = new Element((GLOB12!=null?GLOB12.getText():null), ElementType.STRING); 

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:114:10: SOURCE IDENT
                    {
                    match(input,SOURCE,FOLLOW_SOURCE_in_element579); 
                    IDENT13=(Token)match(input,IDENT,FOLLOW_IDENT_in_element581); 
                     elem = new Element((IDENT13!=null?IDENT13.getText():null), ElementType.SOURCE_MEMORY); 

                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:115:10: TARGET IDENT
                    {
                    match(input,TARGET,FOLLOW_TARGET_in_element595); 
                    IDENT14=(Token)match(input,IDENT,FOLLOW_IDENT_in_element597); 
                     elem = new Element((IDENT14!=null?IDENT14.getText():null), ElementType.TARGET_MEMORY); 

                    }
                    break;
                case 6 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:116:10: ARBITRARY
                    {
                    match(input,ARBITRARY,FOLLOW_ARBITRARY_in_element611); 
                     elem = new Element("?", ElementType.ARBITRARY); 

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


    // $ANTLR start "literal"
    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:119:1: literal returns [Literal lit] : ( TRUE | FALSE );
    public final Literal literal() throws RecognitionException {
        Literal lit = null;

        try {
            // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:120:7: ( TRUE | FALSE )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==TRUE) ) {
                alt8=1;
            }
            else if ( (LA8_0==FALSE) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:120:11: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_literal642); 
                     lit = new Literal(true); 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/org.ect.ea.extensions.constraints/grammar/Constraint.g:121:11: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_literal657); 
                     lit = new Literal(false); 

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
        return lit;
    }
    // $ANTLR end "literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_disjunction_in_start176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conjunction_in_disjunction209 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_OR_in_disjunction217 = new BitSet(new long[]{0x00000000000E3E10L});
    public static final BitSet FOLLOW_conjunction_in_disjunction221 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_composite_in_conjunction259 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_AND_in_conjunction267 = new BitSet(new long[]{0x00000000000E3E10L});
    public static final BitSet FOLLOW_composite_in_conjunction271 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_LPAREN_in_composite300 = new BitSet(new long[]{0x00000000000E3E10L});
    public static final BitSet FOLLOW_disjunction_in_composite302 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_composite304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equation_in_composite311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_composite322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_equation348 = new BitSet(new long[]{0x0000000000010100L});
    public static final BitSet FOLLOW_ASSIGN_in_equation357 = new BitSet(new long[]{0x00000000000E3E00L});
    public static final BitSet FOLLOW_parameter_in_equation361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELOP_in_equation375 = new BitSet(new long[]{0x00000000000E3E00L});
    public static final BitSet FOLLOW_parameter_in_equation379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_parameter411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_parameter423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_parameter438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_function468 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_LPAREN_in_function480 = new BitSet(new long[]{0x00000000000E3E00L});
    public static final BitSet FOLLOW_parameter_in_function484 = new BitSet(new long[]{0x0000000000008020L});
    public static final BitSet FOLLOW_SEPARATOR_in_function494 = new BitSet(new long[]{0x00000000000E3E00L});
    public static final BitSet FOLLOW_parameter_in_function498 = new BitSet(new long[]{0x0000000000008020L});
    public static final BitSet FOLLOW_RPAREN_in_function508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_element530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_element546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GLOB_in_element563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOURCE_in_element579 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENT_in_element581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TARGET_in_element595 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENT_in_element597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARBITRARY_in_element611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal657 = new BitSet(new long[]{0x0000000000000002L});

}