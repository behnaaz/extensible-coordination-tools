// $ANTLR 3.3 Nov 30, 2010 12:50:56 C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g 2012-09-21 19:55:31

package org.ect.codegen.v2.core.rt.java.solver.parser;



import org.antlr.runtime.*;
import org.ect.codegen.v2.core.rt.java.solver.Constraint;
import org.ect.codegen.v2.core.rt.java.solver.Problem;
import org.ect.codegen.v2.core.rt.java.solver.constraints.Equality;
import org.ect.codegen.v2.core.rt.java.solver.constraints.Filter;
import org.ect.codegen.v2.core.rt.java.solver.constraints.RandomInt;
import org.ect.codegen.v2.core.rt.java.solver.constraints.True;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StringToProblemParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "EQUAL", "ASSIGNMENT", "DOLLAR", "UNDERSCORE", "PAR_LEFT", "PAR_RIGHT", "BEGIN_CODE", "END_CODE", "COMMA", "AND", "DOT", "COLON", "QUOTE", "CONSTRAINT_EQUAL", "CONSTRAINT_FILTER", "CONSTRAINT_RANDOMINT", "CONSTRAINT_TRUE", "CODE", "INTEGER", "IDENTIFIER", "WHITESPACE", "LETTER_OR_UNDERSCORE", "LETTER_OR_DIGIT_OR_UNDERSCORE", "DIGIT", "LETTER", "LOWER_CASE_LETTER", "UPPER_CASE_LETTER"
    };
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

    // delegates
    // delegators


        public StringToProblemParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public StringToProblemParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return StringToProblemParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g"; }



    public class StringToProblemException extends RuntimeException {
      private static final long serialVersionUID = 1L;

      private StringToProblemException(final String message, final Throwable cause) {
        super(message, cause);
      }
    }

    public static void main(final String[] args) throws Exception {
    	// Begin code to avoid "unused import" warnings for 
    	// classes List, ArrayList and Stack.
    	final List<Object> list = new ArrayList<Object>();
    	list.add(new Stack<Object>());
    	// End.
    	final StringToProblemLexer lexer = new StringToProblemLexer(
    			new ANTLRFileStream(args[0]));
    	final CommonTokenStream tokens = new CommonTokenStream(lexer);
    	final StringToProblemParser parser = new StringToProblemParser(tokens);
    	try {
    		parser.constraint();
    	} catch (final Exception e) {
    		e.printStackTrace();
    	}
    }

    public static int toInt(final Token token) {
    	try {
    		return Integer.parseInt(token.getText());
    	} catch (final Exception e) {
    		return 0;
    	}
    }



    // $ANTLR start "problem"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:92:1: private problem returns [Problem problem] : c= constraint ( AND c= constraint )* ;
    public final Problem problem() throws RecognitionException {
        Problem problem = null;

        Constraint c = null;



        ArrayList<Constraint> constraints = new ArrayList<Constraint>();

        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:102:3: (c= constraint ( AND c= constraint )* )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:103:3: c= constraint ( AND c= constraint )*
            {
            pushFollow(FOLLOW_constraint_in_problem423);
            c=constraint();

            state._fsp--;


                           constraints.add(c);
                          
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:107:3: ( AND c= constraint )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==AND) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:107:4: AND c= constraint
            	    {
            	    match(input,AND,FOLLOW_AND_in_problem445); 
            	    pushFollow(FOLLOW_constraint_in_problem449);
            	    c=constraint();

            	    state._fsp--;


            	                        constraints.add(c);
            	                       

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            Constraint[] array = new Constraint[constraints.size()];
            constraints.toArray(array);
            problem = new Problem(array);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return problem;
    }
    // $ANTLR end "problem"


    // $ANTLR start "constraint"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:113:1: private constraint returns [Constraint constraint] : (equal= equalConstraint | filter= filterConstraint | randomInt= randomIntConstraint | tru= trueConstraint );
    public final Constraint constraint() throws RecognitionException {
        Constraint constraint = null;

        Equality equal = null;

        Filter filter = null;

        RandomInt randomInt = null;

        True tru = null;


        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:115:3: (equal= equalConstraint | filter= filterConstraint | randomInt= randomIntConstraint | tru= trueConstraint )
            int alt2=4;
            switch ( input.LA(1) ) {
            case DOLLAR:
                {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==IDENTIFIER) ) {
                    int LA2_6 = input.LA(3);

                    if ( (LA2_6==EQUAL) ) {
                        int LA2_7 = input.LA(4);

                        if ( (LA2_7==CONSTRAINT_RANDOMINT) ) {
                            alt2=3;
                        }
                        else if ( (LA2_7==DOLLAR) ) {
                            alt2=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 2, 7, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA2_6==ASSIGNMENT||LA2_6==QUOTE) ) {
                        alt2=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 6, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
                }
                break;
            case CONSTRAINT_EQUAL:
                {
                alt2=1;
                }
                break;
            case CONSTRAINT_FILTER:
                {
                alt2=2;
                }
                break;
            case CONSTRAINT_RANDOMINT:
                {
                alt2=3;
                }
                break;
            case CONSTRAINT_TRUE:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:116:3: equal= equalConstraint
                    {
                    pushFollow(FOLLOW_equalConstraint_in_constraint496);
                    equal=equalConstraint();

                    state._fsp--;


                                            constraint = equal;
                                           

                    }
                    break;
                case 2 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:120:5: filter= filterConstraint
                    {
                    pushFollow(FOLLOW_filterConstraint_in_constraint530);
                    filter=filterConstraint();

                    state._fsp--;


                                                constraint = filter;
                                               

                    }
                    break;
                case 3 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:124:5: randomInt= randomIntConstraint
                    {
                    pushFollow(FOLLOW_randomIntConstraint_in_constraint568);
                    randomInt=randomIntConstraint();

                    state._fsp--;


                                                      constraint = randomInt;
                                                     

                    }
                    break;
                case 4 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:128:5: tru= trueConstraint
                    {
                    pushFollow(FOLLOW_trueConstraint_in_constraint612);
                    tru=trueConstraint();

                    state._fsp--;


                                           constraint = tru;
                                          

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
        return constraint;
    }
    // $ANTLR end "constraint"


    // $ANTLR start "equalConstraint"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:134:1: private equalConstraint returns [Equality equal] : ( (v= variable ( EQUAL v= variable )+ ) | ( CONSTRAINT_EQUAL PAR_LEFT v= variable ( COMMA v= variable )+ PAR_RIGHT ) | (lhs= variable QUOTE EQUAL rhs= variable ) | (lhs= variable ASSIGNMENT rhs= variable ) );
    public final Equality equalConstraint() throws RecognitionException {
        Equality equal = null;

        String v = null;

        String lhs = null;

        String rhs = null;



        ArrayList<String> variableNames = new ArrayList<String>();

        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:144:3: ( (v= variable ( EQUAL v= variable )+ ) | ( CONSTRAINT_EQUAL PAR_LEFT v= variable ( COMMA v= variable )+ PAR_RIGHT ) | (lhs= variable QUOTE EQUAL rhs= variable ) | (lhs= variable ASSIGNMENT rhs= variable ) )
            int alt5=4;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==DOLLAR) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==IDENTIFIER) ) {
                    switch ( input.LA(3) ) {
                    case EQUAL:
                        {
                        alt5=1;
                        }
                        break;
                    case QUOTE:
                        {
                        alt5=3;
                        }
                        break;
                    case ASSIGNMENT:
                        {
                        alt5=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==CONSTRAINT_EQUAL) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:145:3: (v= variable ( EQUAL v= variable )+ )
                    {
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:145:3: (v= variable ( EQUAL v= variable )+ )
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:145:4: v= variable ( EQUAL v= variable )+
                    {
                    pushFollow(FOLLOW_variable_in_equalConstraint671);
                    v=variable();

                    state._fsp--;


                                  variableNames.add(v);
                                 
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:149:5: ( EQUAL v= variable )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==EQUAL) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:149:6: EQUAL v= variable
                    	    {
                    	    match(input,EQUAL,FOLLOW_EQUAL_in_equalConstraint694); 
                    	    pushFollow(FOLLOW_variable_in_equalConstraint698);
                    	    v=variable();

                    	    state._fsp--;


                    	                          variableNames.add(v);
                    	                         

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


                    }
                    break;
                case 2 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:153:5: ( CONSTRAINT_EQUAL PAR_LEFT v= variable ( COMMA v= variable )+ PAR_RIGHT )
                    {
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:153:5: ( CONSTRAINT_EQUAL PAR_LEFT v= variable ( COMMA v= variable )+ PAR_RIGHT )
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:153:6: CONSTRAINT_EQUAL PAR_LEFT v= variable ( COMMA v= variable )+ PAR_RIGHT
                    {
                    match(input,CONSTRAINT_EQUAL,FOLLOW_CONSTRAINT_EQUAL_in_equalConstraint732); 
                    match(input,PAR_LEFT,FOLLOW_PAR_LEFT_in_equalConstraint734); 
                    pushFollow(FOLLOW_variable_in_equalConstraint738);
                    v=variable();

                    state._fsp--;


                                                              variableNames.add(v);
                                                             
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:157:5: ( COMMA v= variable )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:157:6: COMMA v= variable
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_equalConstraint789); 
                    	    pushFollow(FOLLOW_variable_in_equalConstraint793);
                    	    v=variable();

                    	    state._fsp--;


                    	                          variableNames.add(v);
                    	                         

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

                    match(input,PAR_RIGHT,FOLLOW_PAR_RIGHT_in_equalConstraint821); 

                    }


                    }
                    break;
                case 3 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:161:5: (lhs= variable QUOTE EQUAL rhs= variable )
                    {
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:161:5: (lhs= variable QUOTE EQUAL rhs= variable )
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:161:6: lhs= variable QUOTE EQUAL rhs= variable
                    {
                    pushFollow(FOLLOW_variable_in_equalConstraint831);
                    lhs=variable();

                    state._fsp--;


                                      variableNames.add(lhs + "'");
                                     
                    match(input,QUOTE,FOLLOW_QUOTE_in_equalConstraint857); 
                    match(input,EQUAL,FOLLOW_EQUAL_in_equalConstraint859); 
                    pushFollow(FOLLOW_variable_in_equalConstraint863);
                    rhs=variable();

                    state._fsp--;


                                                 variableNames.add(rhs);
                                                

                    }


                    }
                    break;
                case 4 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:169:5: (lhs= variable ASSIGNMENT rhs= variable )
                    {
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:169:5: (lhs= variable ASSIGNMENT rhs= variable )
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:169:6: lhs= variable ASSIGNMENT rhs= variable
                    {
                    pushFollow(FOLLOW_variable_in_equalConstraint904);
                    lhs=variable();

                    state._fsp--;


                                      variableNames.add(lhs + "'");
                                     
                    match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_equalConstraint930); 
                    pushFollow(FOLLOW_variable_in_equalConstraint934);
                    rhs=variable();

                    state._fsp--;


                                                variableNames.add(rhs);
                                               

                    }


                    }
                    break;

            }

            String[] array = new String[variableNames.size()];
            variableNames.toArray(array);
            equal = new Equality(array);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return equal;
    }
    // $ANTLR end "equalConstraint"


    // $ANTLR start "filterConstraint"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:179:1: private filterConstraint returns [Filter filter] : ( CONSTRAINT_FILTER PAR_LEFT v= variable COMMA code= CODE PAR_RIGHT ) ;
    public final Filter filterConstraint() throws RecognitionException {
        Filter filter = null;

        Token code=null;
        String v = null;





        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:187:3: ( ( CONSTRAINT_FILTER PAR_LEFT v= variable COMMA code= CODE PAR_RIGHT ) )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:188:3: ( CONSTRAINT_FILTER PAR_LEFT v= variable COMMA code= CODE PAR_RIGHT )
            {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:188:3: ( CONSTRAINT_FILTER PAR_LEFT v= variable COMMA code= CODE PAR_RIGHT )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:188:4: CONSTRAINT_FILTER PAR_LEFT v= variable COMMA code= CODE PAR_RIGHT
            {
            match(input,CONSTRAINT_FILTER,FOLLOW_CONSTRAINT_FILTER_in_filterConstraint997); 
            match(input,PAR_LEFT,FOLLOW_PAR_LEFT_in_filterConstraint999); 
            pushFollow(FOLLOW_variable_in_filterConstraint1003);
            v=variable();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_filterConstraint1005); 
            code=(Token)match(input,CODE,FOLLOW_CODE_in_filterConstraint1009); 
            match(input,PAR_RIGHT,FOLLOW_PAR_RIGHT_in_filterConstraint1011); 

            }


                                                                                try {
                                                                                	filter = new Filter(v, code.getText().substring(2,
                                                                                			code.getText().length() - 2));
                                                                                } catch (final Exception e) {
                                                                                	throw new StringToProblemException("I failed to parse.", e);
                                                                                }
                                                                               

            }




        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return filter;
    }
    // $ANTLR end "filterConstraint"


    // $ANTLR start "randomIntConstraint"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:199:1: private randomIntConstraint returns [RandomInt randomInt] : ( CONSTRAINT_RANDOMINT PAR_LEFT v= variable COMMA min= INTEGER COMMA max= INTEGER PAR_RIGHT | v= variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT | CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT EQUAL v= variable ) ;
    public final RandomInt randomIntConstraint() throws RecognitionException {
        RandomInt randomInt = null;

        Token min=null;
        Token max=null;
        String v = null;





        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:207:3: ( ( CONSTRAINT_RANDOMINT PAR_LEFT v= variable COMMA min= INTEGER COMMA max= INTEGER PAR_RIGHT | v= variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT | CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT EQUAL v= variable ) )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:208:3: ( CONSTRAINT_RANDOMINT PAR_LEFT v= variable COMMA min= INTEGER COMMA max= INTEGER PAR_RIGHT | v= variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT | CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT EQUAL v= variable )
            {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:208:3: ( CONSTRAINT_RANDOMINT PAR_LEFT v= variable COMMA min= INTEGER COMMA max= INTEGER PAR_RIGHT | v= variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT | CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT EQUAL v= variable )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==CONSTRAINT_RANDOMINT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==PAR_LEFT) ) {
                    int LA6_3 = input.LA(3);

                    if ( (LA6_3==INTEGER) ) {
                        alt6=3;
                    }
                    else if ( (LA6_3==DOLLAR) ) {
                        alt6=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA6_0==DOLLAR) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:209:5: CONSTRAINT_RANDOMINT PAR_LEFT v= variable COMMA min= INTEGER COMMA max= INTEGER PAR_RIGHT
                    {
                    match(input,CONSTRAINT_RANDOMINT,FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1119); 
                    match(input,PAR_LEFT,FOLLOW_PAR_LEFT_in_randomIntConstraint1121); 
                    pushFollow(FOLLOW_variable_in_randomIntConstraint1125);
                    v=variable();

                    state._fsp--;

                    match(input,COMMA,FOLLOW_COMMA_in_randomIntConstraint1127); 
                    min=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1131); 
                    match(input,COMMA,FOLLOW_COMMA_in_randomIntConstraint1133); 
                    max=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1137); 
                    match(input,PAR_RIGHT,FOLLOW_PAR_RIGHT_in_randomIntConstraint1139); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:210:7: v= variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT
                    {
                    pushFollow(FOLLOW_variable_in_randomIntConstraint1149);
                    v=variable();

                    state._fsp--;

                    match(input,EQUAL,FOLLOW_EQUAL_in_randomIntConstraint1151); 
                    match(input,CONSTRAINT_RANDOMINT,FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1153); 
                    match(input,PAR_LEFT,FOLLOW_PAR_LEFT_in_randomIntConstraint1155); 
                    min=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1159); 
                    match(input,COMMA,FOLLOW_COMMA_in_randomIntConstraint1161); 
                    max=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1165); 
                    match(input,PAR_RIGHT,FOLLOW_PAR_RIGHT_in_randomIntConstraint1167); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:211:7: CONSTRAINT_RANDOMINT PAR_LEFT min= INTEGER COMMA max= INTEGER PAR_RIGHT EQUAL v= variable
                    {
                    match(input,CONSTRAINT_RANDOMINT,FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1175); 
                    match(input,PAR_LEFT,FOLLOW_PAR_LEFT_in_randomIntConstraint1177); 
                    min=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1181); 
                    match(input,COMMA,FOLLOW_COMMA_in_randomIntConstraint1183); 
                    max=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_randomIntConstraint1187); 
                    match(input,PAR_RIGHT,FOLLOW_PAR_RIGHT_in_randomIntConstraint1189); 
                    match(input,EQUAL,FOLLOW_EQUAL_in_randomIntConstraint1191); 
                    pushFollow(FOLLOW_variable_in_randomIntConstraint1195);
                    v=variable();

                    state._fsp--;


                    }
                    break;

            }


               randomInt = new RandomInt(v, toInt(min), toInt(max));
              

            }




        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return randomInt;
    }
    // $ANTLR end "randomIntConstraint"


    // $ANTLR start "trueConstraint"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:219:1: private trueConstraint returns [True tru] : CONSTRAINT_TRUE ;
    public final True trueConstraint() throws RecognitionException {
        True tru = null;


        tru = new True();

        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:227:3: ( CONSTRAINT_TRUE )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:228:3: CONSTRAINT_TRUE
            {
            match(input,CONSTRAINT_TRUE,FOLLOW_CONSTRAINT_TRUE_in_trueConstraint1237); 

            }




        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return tru;
    }
    // $ANTLR end "trueConstraint"


    // $ANTLR start "variable"
    // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:231:1: private variable returns [String string] : DOLLAR i= IDENTIFIER ;
    public final String variable() throws RecognitionException {
        String string = null;

        Token i=null;

        try {
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:233:3: ( DOLLAR i= IDENTIFIER )
            // C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.solver\\src\\cwi\\reo\\solver\\parser\\StringToProblem.g:234:3: DOLLAR i= IDENTIFIER
            {
            match(input,DOLLAR,FOLLOW_DOLLAR_in_variable1258); 
            i=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variable1262); 

                                  string = i.getText();
                                 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return string;
    }
    // $ANTLR end "variable"

    // Delegated rules


 

    public static final BitSet FOLLOW_constraint_in_problem423 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_AND_in_problem445 = new BitSet(new long[]{0x00000000001E0040L});
    public static final BitSet FOLLOW_constraint_in_problem449 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_equalConstraint_in_constraint496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_filterConstraint_in_constraint530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_randomIntConstraint_in_constraint568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trueConstraint_in_constraint612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_equalConstraint671 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EQUAL_in_equalConstraint694 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_equalConstraint698 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_CONSTRAINT_EQUAL_in_equalConstraint732 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_PAR_LEFT_in_equalConstraint734 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_equalConstraint738 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_equalConstraint789 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_equalConstraint793 = new BitSet(new long[]{0x0000000000001200L});
    public static final BitSet FOLLOW_PAR_RIGHT_in_equalConstraint821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_equalConstraint831 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_equalConstraint857 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EQUAL_in_equalConstraint859 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_equalConstraint863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_equalConstraint904 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_equalConstraint930 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_equalConstraint934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTRAINT_FILTER_in_filterConstraint997 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_PAR_LEFT_in_filterConstraint999 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_filterConstraint1003 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_filterConstraint1005 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_CODE_in_filterConstraint1009 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PAR_RIGHT_in_filterConstraint1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1119 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_PAR_LEFT_in_randomIntConstraint1121 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_randomIntConstraint1125 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_randomIntConstraint1127 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1131 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_randomIntConstraint1133 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1137 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PAR_RIGHT_in_randomIntConstraint1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_randomIntConstraint1149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EQUAL_in_randomIntConstraint1151 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1153 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_PAR_LEFT_in_randomIntConstraint1155 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1159 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_randomIntConstraint1161 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1165 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PAR_RIGHT_in_randomIntConstraint1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTRAINT_RANDOMINT_in_randomIntConstraint1175 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_PAR_LEFT_in_randomIntConstraint1177 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1181 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_randomIntConstraint1183 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_INTEGER_in_randomIntConstraint1187 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PAR_RIGHT_in_randomIntConstraint1189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EQUAL_in_randomIntConstraint1191 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_variable_in_randomIntConstraint1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTRAINT_TRUE_in_trueConstraint1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLAR_in_variable1258 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variable1262 = new BitSet(new long[]{0x0000000000000002L});

}