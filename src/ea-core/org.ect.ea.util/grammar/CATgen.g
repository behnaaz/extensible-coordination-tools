tree grammar CATgen;

options {
	tokenVocab=CAT;
	ASTLabelType=CommonTree;
	output=template;
}

@header {
package org.ect.ea.runtime.codegen;
}

@members{
	int eq, fn, con, dis;
}

module		: automaton+ 
			;

automaton	:^(GLOB  p+=port* s+=state*)
							-> automaton(id={$GLOB}, ports={$p}, states={$s})
			;

port		:^(PORT IDENT IO?) 		
							-> port(id={$IDENT}, type={$IO})
			;

state 		:^(STATE IDENT INITIALSTATE? (c+=cell)* (t+=transition)*)
							-> state(source={$IDENT}, isstart={$INITIALSTATE?}, cells={$c*}, trans={$t})
			;
	
cell		:^(CELL IDENT (val=GLOB|val=INT|val=TRUE|val=FALSE)?)	
							-> cell(id={$IDENT}, init={$val})
			;

transition	:^(TRANS (p+=port)* constraint IDENT)	
							-> transition(dest={$IDENT}, ports={$p}, cons={$constraint.st})
			;

constraint	:^(OR  (m+=constraint)+)	
							->	disjunction(id={"Or"+(++dis)},members={$m})
			|^(AND (m+=constraint)+)	
							->	conjunction(id={"And"+(++con)},members={$m})
			|^(EQUAL l=parameter r=parameter)
							-> equation(id={"Eq"+(++eq)},lhs={$l.st}, rhs={$r.st})
			| function		-> {$function.st} 
			| literal		-> {$literal.st}	
			;

function	:^(FUNCTION (element|literal) (f+=IDENT)* )
							-> function(id={"Fn"+(++fn)}, lit={$literal.st}, elm={$element.st}, params={$f})
			;
			
parameter 	:literal		-> {$literal.st}								
			|element		-> {$element.st}
		    |function		-> {$function.st}							
      		;
      		
element		:^(PORT IDENT)	-> filler(id={$IDENT}) 
			|^(CELL IDENT)	-> filler(id={$IDENT}) 
			|^(CONSTANT INT)-> filler(id={$INT})  		
			|^(CONSTANT GLOB)-> filler(id={$GLOB}) 	
			;

literal		:^(CONSTANT TRUE)-> filler(id={"true"}) 		
			|^(CONSTANT FALSE)-> filler(id={"false"}) 
			;
