grammar Expression;

tokens {
	TRUE    = 'true' ;
	FALSE   = 'false' ;
	AND     = '&' ;
	OR      = '|' ;
	LPAREN  = '(' ;
	RPAREN  = ')' ;
	EQUAL   = '=' ;
	QUOTE   = '"' ;
	SEPARATOR = ',';
	SOURCE = '$s.' ;
	TARGET = '$t.' ;
	
}

constraint	:	disjunction
			;
			
disjunction	:	conjunction (OR conjunction)*
			;	

conjunction	:	first=composite (AND next=composite)*
			;
			
composite	:	LPAREN disjunction RPAREN
			|	equation
			;

equation 	:	left=parameter (EQUAL right=parameter)?
		 	;

function	:   IDENT 
				LPAREN first=parameter ( SEPARATOR next=parameter  )* RPAREN
      		;

parameter 	:	literal
			|   element
		    |   function
      		;
      		
element : 	IDENT
      	|   SOURCE IDENT
      	|   TARGET IDENT
      	|   INT
		|   QUOTE IDENT QUOTE 
      	;

literal :	TRUE 
      	|   FALSE 
      	;

IDENT	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
INT	: '0'..'9'+ ;
WS	: (' '|'\t')+ {skip();} ;
