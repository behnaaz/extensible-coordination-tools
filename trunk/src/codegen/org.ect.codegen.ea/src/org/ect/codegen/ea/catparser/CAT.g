grammar CAT;

options {
	output = AST;
}

import CATokens,Constraint;


@parser::header {
package cwi.ea.runtime.catparser;

import java.util.Set;
import java.util.HashSet;
}

@parser::members {
private void categorisePort(Token ident, Token io) {
	if (io==null || ident==null) throw new RuntimeException();
	
	String id = ident.getText(), type = io.getText(); 
	if (type.equals("IN")) $ca::inports.add(id); 
	else if (type.equals("OUT")) $ca::outports.add(id);
}
}
module	: ca+ ;

ca
scope {Set<String> inports; Set<String> outports}
@init {$ca::inports= new HashSet<String>(); $ca::outports= new HashSet<String>();}
		:	GLOB ports		
			(state)? (SEPARATOR state)*
								-> ^(CA GLOB  ports state*)
		;
			
ports	:	BEGIN (port)? (SEPARATOR port)* END							
								->  port*
		;

port	:	ident=IDENT (SPACER io=IO {categorisePort($ident, $io);} )?
								-> ^(PORT IDENT IO?)
		;

state 	: 	IDENT (INITIALSTATE)? 
			(BEGIN cell (SEPARATOR cell)* END)?
			(BEGIN trans (SEPARATOR trans)*	END)?
								-> 	^(STATE IDENT INITIALSTATE? cell* trans*)
		;
	
cell	:	IDENT (SPACER (val=GLOB|val=INT|val=TRUE|val=FALSE))?								
								-> ^(CELL IDENT $val?)	
		;

trans	: ports constraint[$ca::inports,$ca::outports] state
								-> ^(TRANS ports constraint state) 
		;
