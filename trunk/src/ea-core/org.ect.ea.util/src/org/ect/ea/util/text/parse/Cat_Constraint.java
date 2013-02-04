// $ANTLR 3.1 Constraint.g 2009-05-28 12:46:19

package org.ect.ea.util.text.parse;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.eclipse.emf.ecore.EReference;
import org.ect.ea.automata.*;
import org.ect.ea.extensions.constraints.*;
import org.ect.ea.util.cacompat.*;

import static org.ect.ea.util.cacompat.CA.PortType;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Cat_Constraint extends Parser {
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
    public static final int BEGIN=26;
    public static final int EQUAL=9;
    public static final int OR=4;
    public static final int ASSIGN=8;
    public static final int LESS=13;
    public static final int IDENT=15;
    public static final int LESS_EQUAL=14;
    public static final int END=27;
    public static final int FALSE=23;
    public static final int ARBITRARY=21;
    public static final int GREATER_EQUAL=12;

    // delegates
    // delegators
    public CatParser gCat;
    public CatParser gParent;


        public Cat_Constraint(TokenStream input, CatParser gCat) {
            this(input, new RecognizerSharedState(), gCat);
        }
        public Cat_Constraint(TokenStream input, RecognizerSharedState state, CatParser gCat) {
            super(input, state);
            this.gCat = gCat;
             
            gParent = gCat;
        }
        

    public String[] getTokenNames() { return CatParser.tokenNames; }
    public String getGrammarFileName() { return "Constraint.g"; }


    PredicateType toSymbol(int i) {
    	return PredicateType.getByName(getTokenNames()[i]);
    }



    // $ANTLR start "constraint"
    // Constraint.g:9:1: constraint returns [Constraint con] : disjunction ;
    public final Constraint constraint() throws RecognitionException {
        Constraint con = null;

        Constraint disjunction1 = null;


        try {
            // Constraint.g:10:25: ( disjunction )
            // Constraint.g:11:2: disjunction
            {
            pushFollow(FOLLOW_disjunction_in_constraint25);
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
    // $ANTLR end "constraint"


    // $ANTLR start "disjunction"
    // Constraint.g:14:1: disjunction returns [Constraint dis] : first= conjunction ( OR next= conjunction )* ;
    public final Constraint disjunction() throws RecognitionException {
        Constraint dis = null;

        Constraint first = null;

        Constraint next = null;



        List<Constraint> rest = new ArrayList<Constraint>();

        try {
            // Constraint.g:28:2: (first= conjunction ( OR next= conjunction )* )
            // Constraint.g:28:4: first= conjunction ( OR next= conjunction )*
            {
            pushFollow(FOLLOW_conjunction_in_disjunction58);
            first=conjunction();

            state._fsp--;

            // Constraint.g:29:3: ( OR next= conjunction )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Constraint.g:29:4: OR next= conjunction
            	    {
            	    match(input,OR,FOLLOW_OR_in_disjunction66); 
            	    pushFollow(FOLLOW_conjunction_in_disjunction70);
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
    // Constraint.g:33:1: conjunction returns [Constraint con] : first= composite ( AND next= composite )* ;
    public final Constraint conjunction() throws RecognitionException {
        Constraint con = null;

        Constraint first = null;

        Constraint next = null;


         
        List<Constraint> rest = new ArrayList<Constraint>();

        try {
            // Constraint.g:47:2: (first= composite ( AND next= composite )* )
            // Constraint.g:47:4: first= composite ( AND next= composite )*
            {
            pushFollow(FOLLOW_composite_in_conjunction108);
            first=composite();

            state._fsp--;

            // Constraint.g:48:3: ( AND next= composite )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Constraint.g:48:4: AND next= composite
            	    {
            	    match(input,AND,FOLLOW_AND_in_conjunction116); 
            	    pushFollow(FOLLOW_composite_in_conjunction120);
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
    // Constraint.g:52:1: composite returns [Constraint comp] : ( LPAREN disjunction RPAREN | equation | literal );
    public final Constraint composite() throws RecognitionException {
        Constraint comp = null;

        Constraint disjunction2 = null;

        Constraint equation3 = null;

        Literal literal4 = null;


        try {
            // Constraint.g:55:2: ( LPAREN disjunction RPAREN | equation | literal )
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

                if ( ((LA3_2>=OR && LA3_2<=AND)||LA3_2==RPAREN||LA3_2==SPACER) ) {
                    alt3=3;
                }
                else if ( ((LA3_2>=ASSIGN && LA3_2<=LESS_EQUAL)) ) {
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

                if ( ((LA3_3>=ASSIGN && LA3_3<=LESS_EQUAL)) ) {
                    alt3=2;
                }
                else if ( ((LA3_3>=OR && LA3_3<=AND)||LA3_3==RPAREN||LA3_3==SPACER) ) {
                    alt3=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;
                }
                }
                break;
            case IDENT:
            case QUOTE:
            case INT:
            case SOURCE:
            case TARGET:
            case ARBITRARY:
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
                    // Constraint.g:55:4: LPAREN disjunction RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_composite149); 
                    pushFollow(FOLLOW_disjunction_in_composite151);
                    disjunction2=disjunction();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_composite153); 
                     comp =disjunction2; 

                    }
                    break;
                case 2 :
                    // Constraint.g:56:4: equation
                    {
                    pushFollow(FOLLOW_equation_in_composite160);
                    equation3=equation();

                    state._fsp--;

                     comp =equation3; 

                    }
                    break;
                case 3 :
                    // Constraint.g:57:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_composite171);
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
    // Constraint.g:60:1: equation returns [Constraint eqn] : left= parameter ( ASSIGN right= parameter | relation= ( EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) right= parameter ) ;
    public final Constraint equation() throws RecognitionException {
        Constraint eqn = null;

        Token relation=null;
        Parameter left = null;

        Parameter right = null;


        try {
            // Constraint.g:62:2: (left= parameter ( ASSIGN right= parameter | relation= ( EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) right= parameter ) )
            // Constraint.g:62:4: left= parameter ( ASSIGN right= parameter | relation= ( EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) right= parameter )
            {
            pushFollow(FOLLOW_parameter_in_equation197);
            left=parameter();

            state._fsp--;

            // Constraint.g:63:3: ( ASSIGN right= parameter | relation= ( EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) right= parameter )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ASSIGN) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=EQUAL && LA4_0<=LESS_EQUAL)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Constraint.g:63:4: ASSIGN right= parameter
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_equation206); 
                    pushFollow(FOLLOW_parameter_in_equation210);
                    right=parameter();

                    state._fsp--;

                     eqn = new Equation(left, right);	

                    }
                    break;
                case 2 :
                    // Constraint.g:65:4: relation= ( EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) right= parameter
                    {
                    relation=(Token)input.LT(1);
                    if ( (input.LA(1)>=EQUAL && input.LA(1)<=LESS_EQUAL) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_parameter_in_equation242);
                    right=parameter();

                    state._fsp--;

                     eqn = new Predicate(left, right, toSymbol((relation!=null?relation.getType():0))); 

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
    // Constraint.g:71:1: parameter returns [Parameter parm] : ( literal | element | function );
    public final Parameter parameter() throws RecognitionException {
        Parameter parm = null;

        Literal literal5 = null;

        Element element6 = null;

        Function function7 = null;


        try {
            // Constraint.g:73:2: ( literal | element | function )
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

                if ( (LA5_2==LPAREN) ) {
                    alt5=3;
                }
                else if ( ((LA5_2>=OR && LA5_2<=AND)||(LA5_2>=RPAREN && LA5_2<=LESS_EQUAL)||LA5_2==SEPARATOR||LA5_2==SPACER) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
                }
                break;
            case QUOTE:
            case INT:
            case SOURCE:
            case TARGET:
            case ARBITRARY:
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
                    // Constraint.g:73:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_parameter274);
                    literal5=literal();

                    state._fsp--;

                     parm =literal5; 

                    }
                    break;
                case 2 :
                    // Constraint.g:74:6: element
                    {
                    pushFollow(FOLLOW_element_in_parameter286);
                    element6=element();

                    state._fsp--;

                     parm =element6; 

                    }
                    break;
                case 3 :
                    // Constraint.g:75:9: function
                    {
                    pushFollow(FOLLOW_function_in_parameter301);
                    function7=function();

                    state._fsp--;

                     parm =function7; 

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
    // Constraint.g:78:1: function returns [Function func] : IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN ;
    public final Function function() throws RecognitionException {
        Function func = null;

        Token IDENT8=null;
        Parameter first = null;

        Parameter next = null;


        try {
            // Constraint.g:80:2: ( IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN )
            // Constraint.g:80:6: IDENT LPAREN first= parameter ( SEPARATOR next= parameter )* RPAREN
            {
            IDENT8=(Token)match(input,IDENT,FOLLOW_IDENT_in_function331); 
             func = new Function((IDENT8!=null?IDENT8.getText():null)); 
            match(input,LPAREN,FOLLOW_LPAREN_in_function343); 
            pushFollow(FOLLOW_parameter_in_function347);
            first=parameter();

            state._fsp--;

             func.getParameters().add(first); 
            // Constraint.g:82:3: ( SEPARATOR next= parameter )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==SEPARATOR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Constraint.g:82:5: SEPARATOR next= parameter
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_function357); 
            	    pushFollow(FOLLOW_parameter_in_function361);
            	    next=parameter();

            	    state._fsp--;

            	     func.getParameters().add(next); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,RPAREN,FOLLOW_RPAREN_in_function371); 

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
    // Constraint.g:86:1: element returns [Element elem] : ( IDENT | QUOTE IDENT QUOTE | INT | SOURCE IDENT | TARGET IDENT | ARBITRARY );
    public final Element element() throws RecognitionException {
        Element elem = null;

        Token IDENT9=null;
        Token IDENT10=null;
        Token INT11=null;
        Token IDENT12=null;
        Token IDENT13=null;

        try {
            // Constraint.g:88:7: ( IDENT | QUOTE IDENT QUOTE | INT | SOURCE IDENT | TARGET IDENT | ARBITRARY )
            int alt7=6;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt7=1;
                }
                break;
            case QUOTE:
                {
                alt7=2;
                }
                break;
            case INT:
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
                    // Constraint.g:88:10: IDENT
                    {
                    IDENT9=(Token)match(input,IDENT,FOLLOW_IDENT_in_element393); 
                     elem = new Element((IDENT9!=null?IDENT9.getText():null), ElementType.IDENTIFIER); 

                    }
                    break;
                case 2 :
                    // Constraint.g:89:10: QUOTE IDENT QUOTE
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_element409); 
                    IDENT10=(Token)match(input,IDENT,FOLLOW_IDENT_in_element411); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_element413); 
                     elem = new Element((IDENT10!=null?IDENT10.getText():null), ElementType.STRING); 

                    }
                    break;
                case 3 :
                    // Constraint.g:90:10: INT
                    {
                    INT11=(Token)match(input,INT,FOLLOW_INT_in_element426); 
                     elem = new Element((INT11!=null?INT11.getText():null), ElementType.INTEGER); 

                    }
                    break;
                case 4 :
                    // Constraint.g:91:10: SOURCE IDENT
                    {
                    match(input,SOURCE,FOLLOW_SOURCE_in_element443); 
                    IDENT12=(Token)match(input,IDENT,FOLLOW_IDENT_in_element445); 
                     elem = new Element((IDENT12!=null?IDENT12.getText():null), ElementType.SOURCE_MEMORY); 

                    }
                    break;
                case 5 :
                    // Constraint.g:92:10: TARGET IDENT
                    {
                    match(input,TARGET,FOLLOW_TARGET_in_element459); 
                    IDENT13=(Token)match(input,IDENT,FOLLOW_IDENT_in_element461); 
                     elem = new Element((IDENT13!=null?IDENT13.getText():null), ElementType.TARGET_MEMORY); 

                    }
                    break;
                case 6 :
                    // Constraint.g:93:10: ARBITRARY
                    {
                    match(input,ARBITRARY,FOLLOW_ARBITRARY_in_element475); 
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
    // Constraint.g:96:1: literal returns [Literal lit] : ( TRUE | FALSE );
    public final Literal literal() throws RecognitionException {
        Literal lit = null;

        try {
            // Constraint.g:97:7: ( TRUE | FALSE )
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
                    // Constraint.g:97:11: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_literal506); 
                     lit = new Literal(true); 

                    }
                    break;
                case 2 :
                    // Constraint.g:98:11: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_literal521); 
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


 

    public static final BitSet FOLLOW_disjunction_in_constraint25 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conjunction_in_disjunction58 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_OR_in_disjunction66 = new BitSet(new long[]{0x0000000000FE8040L});
    public static final BitSet FOLLOW_conjunction_in_disjunction70 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_composite_in_conjunction108 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_AND_in_conjunction116 = new BitSet(new long[]{0x0000000000FE8040L});
    public static final BitSet FOLLOW_composite_in_conjunction120 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_LPAREN_in_composite149 = new BitSet(new long[]{0x0000000000FE8040L});
    public static final BitSet FOLLOW_disjunction_in_composite151 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RPAREN_in_composite153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equation_in_composite160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_composite171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_equation197 = new BitSet(new long[]{0x0000000000007F00L});
    public static final BitSet FOLLOW_ASSIGN_in_equation206 = new BitSet(new long[]{0x0000000000FE8000L});
    public static final BitSet FOLLOW_parameter_in_equation210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_equation226 = new BitSet(new long[]{0x0000000000FE8000L});
    public static final BitSet FOLLOW_parameter_in_equation242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_parameter274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_parameter286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_parameter301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_function331 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LPAREN_in_function343 = new BitSet(new long[]{0x0000000000FE8000L});
    public static final BitSet FOLLOW_parameter_in_function347 = new BitSet(new long[]{0x0000000000010080L});
    public static final BitSet FOLLOW_SEPARATOR_in_function357 = new BitSet(new long[]{0x0000000000FE8000L});
    public static final BitSet FOLLOW_parameter_in_function361 = new BitSet(new long[]{0x0000000000010080L});
    public static final BitSet FOLLOW_RPAREN_in_function371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_element393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_in_element409 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_element411 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_QUOTE_in_element413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_element426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOURCE_in_element443 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_element445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TARGET_in_element459 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENT_in_element461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARBITRARY_in_element475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal521 = new BitSet(new long[]{0x0000000000000002L});

}