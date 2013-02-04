parser grammar Constraint;

@parser::members{
PredicateType toSymbol(int i) {
	return PredicateType.getByName(getTokenNames()[i]);
}
}

constraint	
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
	|	relation=(EQUAL|NOT_EQUAL|GREATER|GREATER_EQUAL|LESS|LESS_EQUAL) right=parameter
						{ $eqn = new Predicate($left.parm, $right.parm, toSymbol($relation.type)); }
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
      |		QUOTE IDENT QUOTE	{ $elem = new Element($IDENT.text, ElementType.STRING); }
      |		INT					{ $elem = new Element($INT.text, ElementType.INTEGER); }
      |		SOURCE IDENT		{ $elem = new Element($IDENT.text, ElementType.SOURCE_MEMORY); }
      |		TARGET IDENT		{ $elem = new Element($IDENT.text, ElementType.TARGET_MEMORY); }
      |		ARBITRARY			{ $elem = new Element("?", ElementType.ARBITRARY); }
      ;

literal returns [Literal lit]
      :   TRUE  { $lit = new Literal(true); }
      |   FALSE { $lit = new Literal(false); }
      ;
