parser grammar Constraint;

options {
	output=AST;
}

constraint
			:	SPACER! disjunction SPACER! 			
			;

disjunction	:	conjunction (
				(OR conjunction)+	-> ^(OR conjunction+)
			|							-> conjunction
			);

conjunction	:	composite (
				(AND composite)+	-> ^(AND composite+)
			|						-> composite
			);
			
composite	:	LPAREN! disjunction RPAREN!	
			|	equation
			;

equation 	:	source ASSIGN^ value
			|	(l=value 				-> ^(EQUIV $l ^(CONSTANT TRUE))
				)(ASSIGN ( 
					source			-> ^(ASSIGN source $l)
				|	r=value			-> ^(EQUIV $l $r)
				))?
			;

value		:	function			-> ^(FUNCTION function)
			|	arg
			;
						
function	:	IDENT LPAREN (
					arg				-> arg IDENT 
				|	function		-> function IDENT 
			)	RPAREN
			;

arg			:	sink
			|	constant 
			|	literal 
			;
			
sink		: 	{gParent.sinks.contains(input.LT(1).getText())}? IDENT				
									-> ^(PORT IDENT)
			|   SOURCE IDENT		-> ^(CELL IDENT)
			;
						
source		: 	{gParent.sources.contains(input.LT(1).getText())}? IDENT
									-> ^(PORT IDENT)
			|   TARGET IDENT		-> ^(CELL IDENT)
			;
			
constant	:   INT					-> ^(CONSTANT INT)
			|   GLOB				-> ^(CONSTANT GLOB)
			;

literal		:	TRUE	 			-> ^(CONSTANT TRUE)
			|   FALSE 				-> ^(CONSTANT FALSE)
			;

			