parser grammar Constraint;

options {
	output=AST;
	tokenVocab=CATokens;
}

constraint[Set<String> inports, Set<String> outports]
scope {Set<String> inp; Set<String> outp}
@init {$constraint::inp=inports; $constraint::outp=outports;}	
			:	SPACER! disjunction SPACER! 			
			;

disjunction	:	conjunction (
				(OR conjunction)+	-> ^(OR conjunction+)
			|						-> conjunction
			);

conjunction	:	composite (
				(AND composite)+	-> ^(AND composite+)
			|						-> composite
			);
			
composite	:	LPAREN! disjunction RPAREN!	
			|	literal
			|	eq_pred
			;

eq_pred 	:	output ASSIGN^ value
			|	value ((ASSIGN^ output)| (RELOP^ value))
			;

value		:	function			-> ^(FUNCTION function)
			|	arg
			;
						
function	:	IDENT LPAREN (
					arg				-> arg IDENT 
				|	function		-> function IDENT 
			)	RPAREN
			;

arg			:	input
			|	constant 
			|	literal 
			;
			
input		: 	{$constraint::inp.contains(input.LT(1).getText())}? IDENT				
									-> ^(PORT IDENT)
			|   SOURCE IDENT		-> ^(CELL IDENT)
			;
						
output		: 	{$constraint::outp.contains(input.LT(1).getText())}? IDENT
									-> ^(PORT IDENT)
			|   TARGET IDENT		-> ^(CELL IDENT)
			;
			
constant	:   INT					-> ^(CONSTANT INT)
			|   GLOB				-> ^(CONSTANT GLOB)
			;

literal		:	TRUE	 			-> ^(CONSTANT TRUE)
			|   FALSE 				-> ^(CONSTANT FALSE)
			;
			