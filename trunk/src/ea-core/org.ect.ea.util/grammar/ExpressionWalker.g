tree grammar ExpressionWalker;

options {
	tokenVocab=Expression;
	ASTLabelType=CommonTree;
	output=template;
}

@header {
package org.ect.ea.runtime.codegen;

import java.util.Map;
import java.util.HashMap;
}

@members{
	private static final String EQ="Eq";
	private static final String CON="And";
	private static final String DIS="Or";
//Map symbols = new HashMap();
	int eq, con, dis;
	String prefix="cons";
}

expr[String expPrefix]
@init {
prefix=expPrefix;
eq=con=dis=0;
}			:constraint
			;
			
constraint	: disjunction	-> {$disjunction.st} 
			| conjunction 	-> {$conjunction.st}
			| function		-> {$function.st} 
			| equation		-> {$equation.st}
			| literal		-> {$literal.st}	//should really be removed		
			;
						
disjunction	:^(OR  (m+=constraint)+)	
							->	disjunction(id={prefix+DIS+(++dis)},members={$m})
			;	

conjunction	:^(AND (m+=constraint)+)	
							->	conjunction(id={prefix+CON+(++con)},members={$m})
			;
			
equation	:^(EQUAL l=parameter r=parameter)
							-> equation(id={prefix+EQ+(++eq)},lhs={$l.st}, rhs={$r.st})	
		 	;	
		 	
function	:^(FUNCTION IDENT (p+=parameter)+)
							-> function(id={$IDENT}, params={$p})
			;
			
parameter 	:literal		-> {$literal.st}								
			|element		-> {$element.st}
		    |function		-> {$function.st}							
      		;
      		
element		:^(PORT IDENT)	-> template(id={$IDENT}) " "
			|^(CELL IDENT)	-> template(id={$IDENT}) " "
			|^(CONSTANT INT)-> template(id={$INT}) " "		
			|^(CONSTANT IDENT)-> template(id={$IDENT}) " "	
			;

literal		:^(CONSTANT TRUE)-> template(id={"true"}) " "		
			|^(CONSTANT FALSE)-> template(id={"false"}) " "
			;
