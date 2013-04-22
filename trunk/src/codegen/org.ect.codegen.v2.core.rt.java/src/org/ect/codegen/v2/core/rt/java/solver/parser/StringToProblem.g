grammar StringToProblem;

tokens {

  // Symbols
  EQUAL                = '==';
  ASSIGNMENT           = ':=';
  DOLLAR               = '$';
  UNDERSCORE           = '_';
  PAR_LEFT             = '(';
  PAR_RIGHT            = ')';
  BEGIN_CODE           = '[[';
  END_CODE             = ']]';
  COMMA                = ',';
  AND                  = '&&';
  DOT                  = '.';
  COLON                = ':';
  QUOTE                = '\'';

  // Constraints
  CONSTRAINT_EQUAL     = 'equal';
  CONSTRAINT_FILTER    = 'filter';
  CONSTRAINT_RANDOMINT = 'randomInt';
  CONSTRAINT_TRUE      = 'true';
}

@parser::header {
package cwi.reo.solver.parser;

import cwi.reo.solver.problem.Constraint;
import cwi.reo.solver.problem.Problem;
import cwi.reo.solver.problem.constraints.Equality;
import cwi.reo.solver.problem.constraints.Filter;
import cwi.reo.solver.problem.constraints.True;
import cwi.reo.solver.problem.constraints.RandomInt;
}

@lexer::header {
package cwi.reo.solver.parser;
}

@parser::members {

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
}

@lexer::members {
public static void main(final String[] args) throws Exception {
	// Begin code to avoid "unused import" warnings for 
	// classes List, ArrayList and Stack.
	final List<Object> list = new ArrayList<Object>();
	list.add(new Stack<Object>());
	// End.
}
}

//
// PARSER RULES
//

private
problem returns [Problem problem]
@init {
ArrayList<Constraint> constraints = new ArrayList<Constraint>();
}
@after {
Constraint[] array = new Constraint[constraints.size()];
constraints.toArray(array);
problem = new Problem(array);
}
  :
  c=constraint 
              {
               constraints.add(c);
              }
  (AND c=constraint 
                   {
                    constraints.add(c);
                   })*
  ;

private
constraint returns [Constraint constraint]
  :
  equal=equalConstraint 
                       {
                        constraint = equal;
                       }
  | filter=filterConstraint 
                           {
                            constraint = filter;
                           }
  | randomInt=randomIntConstraint 
                                 {
                                  constraint = randomInt;
                                 }
  | tru=trueConstraint 
                      {
                       constraint = tru;
                      }
  ;

private
equalConstraint returns [Equality equal]
@init {
ArrayList<String> variableNames = new ArrayList<String>();
}
@after {
String[] array = new String[variableNames.size()];
variableNames.toArray(array);
equal = new Equality(array);
}
  :
  (v=variable 
             {
              variableNames.add(v);
             }
    (EQUAL v=variable 
                     {
                      variableNames.add(v);
                     })+)
  | (CONSTRAINT_EQUAL PAR_LEFT v=variable 
                                         {
                                          variableNames.add(v);
                                         }
    (COMMA v=variable 
                     {
                      variableNames.add(v);
                     })+ PAR_RIGHT)
  | (lhs=variable 
                 {
                  variableNames.add(lhs + "'");
                 }
    QUOTE EQUAL rhs=variable 
                            {
                             variableNames.add(rhs);
                            })
  | (lhs=variable 
                 {
                  variableNames.add(lhs + "'");
                 }
    ASSIGNMENT rhs=variable 
                           {
                            variableNames.add(rhs);
                           })
  ;

private
filterConstraint returns [Filter filter]
@init {

}
@after {

}
  :
  (CONSTRAINT_FILTER PAR_LEFT v=variable COMMA code=CODE PAR_RIGHT) 
                                                                   {
                                                                    try {
                                                                    	filter = new Filter(v, code.getText().substring(2,
                                                                    			code.getText().length() - 2));
                                                                    } catch (final Exception e) {
                                                                    	throw new StringToProblemException("I failed to parse.", e);
                                                                    }
                                                                   }
  ;

private
randomIntConstraint returns [RandomInt randomInt]
@init {

}
@after {

}
  :
  (
    CONSTRAINT_RANDOMINT PAR_LEFT v=variable COMMA min=INTEGER COMMA max=INTEGER PAR_RIGHT
    | v=variable EQUAL CONSTRAINT_RANDOMINT PAR_LEFT min=INTEGER COMMA max=INTEGER PAR_RIGHT
    | CONSTRAINT_RANDOMINT PAR_LEFT min=INTEGER COMMA max=INTEGER PAR_RIGHT EQUAL v=variable
  )
  
  {
   randomInt = new RandomInt(v, toInt(min), toInt(max));
  }
  ;

private
trueConstraint returns [True tru]
@init {
tru = new True();
}
@after {

}
  :
  CONSTRAINT_TRUE
  ;

private
variable returns [String string]
  :
  DOLLAR i=IDENTIFIER 
                     {
                      string = i.getText();
                     }
  ;

//
// LEXER RULES
//

CODE
  :
  '[[' (options {greedy=false;}: .)* ']]'
  ;

WHITESPACE
  :
  (
    ' '
    | '\r'
    | '\t'
    | '\u000C'
    | '\n'
  )+
  
  {
   $channel = HIDDEN;
  }
  ;

IDENTIFIER
  :
  LETTER_OR_UNDERSCORE (LETTER_OR_DIGIT_OR_UNDERSCORE)*
  ;

INTEGER
  :
  (DIGIT)+
  ;

fragment
LETTER_OR_UNDERSCORE
  :
  LETTER
  | UNDERSCORE
  ;

fragment
LETTER_OR_DIGIT_OR_UNDERSCORE
  :
  LETTER
  | DIGIT
  | UNDERSCORE
  | DOT
  | COLON
  ;

fragment
LETTER
  :
  LOWER_CASE_LETTER
  | UPPER_CASE_LETTER
  ;

fragment
LOWER_CASE_LETTER
  :
  'a'..'z'
  ;

fragment
UPPER_CASE_LETTER
  :
  'A'..'Z'
  ;

fragment
DIGIT
  :
  '0'..'9'
  ;
