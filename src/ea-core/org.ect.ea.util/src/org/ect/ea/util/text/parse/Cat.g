grammar Cat;
import Constraint;

tokens {
	QUOTE   = '"' ;
	SEPARATOR = ',';
	SPACER = '::';
	INITIALSTATE = '*';
	BEGIN = '[';
	END = ']';
	
	ASSIGN  	= '=' ;
	EQUAL   	= '==' ;
	NOT_EQUAL	= '!=' ;
	GREATER 	= '>';
	GREATER_EQUAL= '>=';
	LESS		= '<';
	LESS_EQUAL	= '<=';
	
	LPAREN  	= '(' ;
	RPAREN  	= ')' ;
	AND     	= '&' ;
	OR      	= '|' ;
	
	TRUE    	= 'true' ;
	FALSE   	= 'false' ;
	ARBITRARY	= '?';
	SOURCE  	= '$s.' ;
	TARGET  	= '$t.' ;
}

@lexer::header {
package org.ect.ea.util.text.parse;
}
@lexer::members {
public static void main(String[] args) throws Exception {
	CatLexer lexer = new CatLexer(new ANTLRFileStream(args[0]));
	Token token;
	while ((token = lexer.nextToken())!=Token.EOF_TOKEN) {
		System.out.println("Token: "+token.getText()+" type: "+token.getType());
	}
}
}
 
@parser::header {
package org.ect.ea.util.text.parse;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.eclipse.emf.ecore.EReference;

import org.ect.ea.automata.*;
import org.ect.ea.extensions.constraints.*;
import org.ect.ea.util.cacompat.*;
import org.ect.ea.util.cacompat.CA;
import static org.ect.ea.util.cacompat.CA.PortType;
}
@parser::members {
Map<String, CAState> stateNames = new HashMap<String, CAState>();

private String stripQuotes(String s) {
	return s.substring(1,s.length()-1);
}

public static void main(String[] args) throws Exception {
	CatLexer lex = new CatLexer(new ANTLRFileStream(args[0]));
	CatParser parser = new CatParser( new CommonTokenStream(lex));
	CA ca = parser.automaton();
	System.out.println(ca);
}
}

module
returns [Module value]
@init{$value = new Module();}:
	(automaton {$value.getAutomata().add($automaton.value);} )+
	;
	
automaton 
returns [CA value]	
@init{$value = new CA();}:  
	GLOB {$value.setName(stripQuotes($GLOB.text));} 
	( portlist {
		for (String p: (Collection<String>)$portlist.value.keySet())
			switch ((PortType) $portlist.value.get(p)) { 							 
			case IN: 
				$value.getPortNames().getInPorts().add(p);
				break;
			case OUT: 
				$value.getPortNames().getOutPorts().add(p);
				break;
			default://$portlist.value.get(p)==null 
				$value.getPortNames().getValues().add(p);
			}
		
	} )?
	( statelist[ $value.getStates() ] )? 
	;

portlist 
returns [Map value] 	
@init {$value = new HashMap();} :
	BEGIN 
	first=port {$value.put($first.name, $first.type);}   
	(SEPARATOR next=port {$value.put($next.name, $next.type);} )*
	END
	;

port 
returns [String name, PortType type]:
	IDENT {$name=$IDENT.text;}
	(SPACER TYPE {$type=PortType.valueOf($TYPE.text);} )?	
	;
	
statelist 
[List<State> states]:	
	first=state {$states.add($first.value);}  
	(SEPARATOR next=state {$states.add($next.value);} )* 
	;

state	
returns [CAState value]:	
	IDENT {
		if (stateNames.containsKey($IDENT.text))
			$value = stateNames.get($IDENT.text);
		else {
			$value = new CAState();
			$value.setName($IDENT.text);
			stateNames.put($IDENT.text,$value);
		}  
	} 
	(INITIALSTATE {$value.setStartState(true);})?
	(celllist[ $value.getMemoryCells().getInitializations().map() ] {
		$value.getMemoryCells().getValues().addAll(
			$value.getMemoryCells().getInitializations().keySet());
	})?	
	(transitionlist[ $value.getOutgoing() ])?	
	;

celllist 
[Map value]: 	
	BEGIN 
	(first=cell {$value.put($first.text, $first.value);} ) 
	(SEPARATOR next=cell {$value.put($first.text, $first.value);})* 
	END	
	;

cell
returns [String name, String value]:
	IDENT {$name=$IDENT.text;}
	(SPACER (val=GLOB|val=INT|val=TRUE|val=FALSE) {$value=$val.text;})?								
	;
	
transitionlist 
[List<Transition> value]:
	BEGIN 
	(first=transition {$value.add($first.value);} ) 
	(SEPARATOR next=transition {$value.add($next.value);} )* 
	END	
	;

transition 
returns [CATransition value]	
@init {$value = new CATransition();} : 
	portlist {$value.getPortNames().getValues().addAll($portlist.value.keySet());}	
	SPACER constraint { $value.setConstraint($constraint.con); } 
	SPACER tgt=state { $value.setTarget($tgt.value); }	
	;
 
TYPE	:	'IN'|'OUT';

IDENT	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

INT	: '0'..'9'+ ;

WS  :   (' '|'\t'|'\n'|'\r')+ {skip();} ;

GLOB	:	QUOTE (options {greedy=false;} :.)+ QUOTE;

