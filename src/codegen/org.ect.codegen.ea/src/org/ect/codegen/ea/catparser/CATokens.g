lexer grammar CATokens;

@lexer::header {
package cwi.ea.runtime.catparser;
}

INITIALSTATE:'*';
BEGIN 	:	 '[';
END 	:	 ']';
SPACER	:	 '::';

//	**** constraint tokens **** 	
FALSE   :	'false' ;
TRUE	:	'true' ;
AND		:	'&' ;
OR		:	'|' ;
LPAREN	:	'(' ;
RPAREN	:	')' ;
ASSIGN	:	'=' ;
QUOTE	:	'"' ;
SEPARATOR :	',';
SOURCE	:	'$s.' ;
TARGET	:	'$t.' ;

//	**** imaginary tokens nodes in tree ***	
CA		:	'CA';
PORT	:	'PORT';
STATE	:	'STATE';
TRANS 	:	'TRANS';
CELL	:	'CELL';
PTYPE	:	'I/O';
ISSTART	:	'START?';	
//	constraint tokens		
FUNCTION:	'FUNCTION';
CONSTANT:	'CONSTANT';


RELOP	:	'=='|'!='|'<'|'<='|'>'|'>=';
	
IO		:	'IN'|'OUT'|'UNKNOWN';

INT		:	'0'..'9'+ ;

IDENT	:	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

GLOB	:	QUOTE (options {greedy=false;} :.)+ QUOTE; 

WS		:	(' '|'\t'|'\n'|'\r')+ {skip();} ;
