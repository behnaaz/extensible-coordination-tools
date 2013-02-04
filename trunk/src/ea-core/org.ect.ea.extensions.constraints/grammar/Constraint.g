grammar Constraint;

tokens {
	LPAREN  	= '(' ;
	RPAREN  	= ')' ;
	AND     	= '&' ;
	OR      	= '|' ;
	ASSIGN  	= '=' ;
	
	TRUE    	= 'true' ;
	FALSE   	= 'false' ;
	ARBITRARY	= '?';
	SOURCE  	= '$s.' ;
	TARGET  	= '$t.' ;

	QUOTE   	= '"' ;
	SEPARATOR	= ',';
}

@lexer::header{
package org.ect.ea.extensions.constraints.parsers;
}

@parser::header{
package org.ect.ea.extensions.constraints.parsers;

import org.eclipse.emf.ecore.EReference;

import org.ect.ea.extensions.constraints.*;
}

start	
returns [Constraint con]:
	disjunction {$con=$disjunction.dis;}
	;
			
disjunction	
returns [Constraint dis]
@init {
List<Constraint> rest = new ArrayList<Constraint>();
}
@after { 
if (rest.isEmpty()) 
	$dis=$first.con; 
else {  
	$dis = new Disjunction();
	((Composite) $dis).getMembers().add(first); 
	((Composite) $dis).getMembers().addAll(rest);
}
}
	:	first=conjunction 		
		(OR next=conjunction 	{ rest.add($next.con); } 
		)*
	;	

conjunction	
returns [Constraint con]
@init { 
List<Constraint> rest = new ArrayList<Constraint>();
}
@after { 
if (rest.isEmpty()) 
	$con=$first.comp; 
else {  
	$con = new Conjunction();
	((Composite) $con).getMembers().add(first); 
	((Composite) $con).getMembers().addAll(rest);
}
}
	:	first=composite 		
		(AND next=composite		{ rest.add($next.comp); } 
		)*
	;
			
composite	
returns [Constraint comp]
//@after { System.err.println("Conjunction Parsed "+$comp); } 
	:	LPAREN disjunction RPAREN	{ $comp=$disjunction.dis; }
	|	equation					{ $comp=$equation.eqn; }
	|	literal						{ $comp=$literal.lit; }
	;

equation 	
returns [Constraint eqn]
	:	left=parameter 			
		(ASSIGN right=parameter	
						{ $eqn = new Equation($left.parm, $right.parm);	}
	|	RELOP right=parameter
						{ $eqn = new Predicate($left.parm, $right.parm, PredicateType.get($RELOP.text)); }
		) 
	;

	
parameter 	
returns [Parameter parm]
	:	literal				{ $parm=$literal.lit; }
	|   element				{ $parm=$element.elem; }
    |   function			{ $parm=$function.func; }
	;
      		
function	
returns [Function func]
	:  	IDENT						{ $func = new Function($IDENT.text); } 
		LPAREN first=parameter		{ $func.getParameters().add($first.parm); } 
		( SEPARATOR next=parameter	{ $func.getParameters().add($next.parm); } 
		)* RPAREN
	;

element 
returns [Element elem]
      :		IDENT				{ $elem = new Element($IDENT.text, ElementType.IDENTIFIER); }
      |		INT					{ $elem = new Element($INT.text, ElementType.INTEGER); }
      |		GLOB				{ $elem = new Element($GLOB.text, ElementType.STRING); }
      |		SOURCE IDENT		{ $elem = new Element($IDENT.text, ElementType.SOURCE_MEMORY); }
      |		TARGET IDENT		{ $elem = new Element($IDENT.text, ElementType.TARGET_MEMORY); }
      |		ARBITRARY			{ $elem = new Element("?", ElementType.ARBITRARY); }
      ;

literal returns [Literal lit]
      :   TRUE  { $lit = new Literal(true); }
      |   FALSE { $lit = new Literal(false); }
      ;

RELOP	:	'=='|'!='|'<'|'<='|'>'|'>=';

TYPE	:	'IN'|'OUT';

IDENT	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
	
INT		: '0'..'9'+ ;

GLOB	: QUOTE (options {greedy=false;} :.)+ QUOTE
								{setText(getText().substring(1, getText().length()-1));} ;

WS		: (' '|'\t'|'\n'|'\r')+ {skip();} ;
