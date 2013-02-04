grammar CAT;

options {
	output = AST;
}

import Expression;

tokens {
	INITIALSTATE = '*';
	BEGIN = '[';
	END = ']';
	SPACER = '::';
	
	CELLSHEADER = 'CELLS';
	
	PORT = 'PORT';
	STATE = 'STATE';
	TRANS = 'TRANS';
	CELL = 'CELL';
	PTYPE  = 'I/O';
	ISSTART = 'START?';	
}

@lexer::header {
package org.ect.ea.runtime.codegen;
}

@parser::header {
package org.ect.ea.runtime.codegen;
}

module		: automaton+ ;

automaton:	GLOB ports		
			(state)? (SEPARATOR state)*
								-> ^(GLOB  ports state*)
		;
			
ports	:	BEGIN (port)? (SEPARATOR port)* END							
								->  port*
		;

port	:	IDENT (SPACER IO)?	-> ^(PORT IDENT IO?) 		
		;


state 	: 	IDENT (INITIALSTATE)? cells?
			BEGIN (transition)? (SEPARATOR transition)*	END
								-> 	^(STATE IDENT INITIALSTATE? cells? transition*)
		;
	
cells	:	BEGIN (IDENT ) (SEPARATOR IDENT)* END							
								-> ^(CELLSHEADER IDENT*)	
		;

transition: ports constraint IDENT
								-> ^(TRANS ports constraint IDENT) 
			;

IO		:	'IN'|'OUT'|'UNKNOWN';

IDENT	:	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

GLOB	:	QUOTE! (options {greedy=false;} :.)+ QUOTE!; 

WS  	:   (' '|'\t'|'\n'|'\r')+ {skip();} ;