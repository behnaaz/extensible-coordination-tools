tree grammar CATgen;

options {
	tokenVocab=CATokens;
	ASTLabelType=CommonTree;
	output=template;
}

@header {
package cwi.ea.runtime.codegen.treewalker;

import java.util.Set;
import java.util.HashSet;
}

@members{
	int eq, fn, pred, con, dis;
	Set<String> cells = new HashSet<String>();
}

module    : automaton+ 
          ;

automaton :^(CA GLOB  p+=port* s+=state*)
							-> automaton(id={$GLOB}, ports={$p}, states={$s})
          ;

port      :^(PORT IDENT IO?) 		
							-> port(id={$IDENT}, type={$IO})
          ;

state     :^(STATE IDENT INITIALSTATE? (c+=cell)* (t+=transition)*)
							-> state(name={$IDENT}, isstart={$INITIALSTATE}, cells={$c}, trans={$t})
          ;
	
cell      :^(CELL IDENT (val=GLOB|val=INT|val=TRUE|val=FALSE)?)								
							-> {cells.add($IDENT.text)}? cell(id={$IDENT}, init={$val})
							-> {$st=null;}
          ;

transition:^(TRANS (p+=port)* constraint state)	
							-> transition(dest={$state.st}, ports={$p}, cons={$constraint.st})
          ;

constraint:^(OR  (m+=constraint)+)	
							->	disjunction(id={"dis"+(++dis)},members={$m})
		  |^(AND (m+=constraint)+)	
							->	conjunction(id={"con"+(++con)},members={$m})
          |^(ASSIGN l=parameter r=parameter)
							-> equation(id={"eqn"+(++eq)},lhs={$l.st}, rhs={$r.st})
          |^(RELOP l=parameter r=parameter)		
          					-> predicate(id={"pred"+(++pred)}, lhs={$l.st}, rhs={$r.st}, op={$RELOP.getText()}) 
          | literal			-> {$literal.st}	
          ;

function  :^(FUNCTION element (f+=IDENT)+ )
							-> function(id={"func"+(++fn)}, arg={$element.st}, funcs={$f})
          |^(FUNCTION literal (f+=IDENT)+ )
							-> function(id={"func"+(++fn)}, arg={$literal.st}, funcs={$f})
          ;
			
parameter :literal			-> {$literal.st}								
          |element			-> {$element.st}
          |function			-> {$function.st}						
      	  ;
      		
element	  :^(PORT IDENT)	-> filler(id={$IDENT}) 
          |^(CELL IDENT)	-> filler(id={$IDENT}) 
          |^(CONSTANT INT)	-> const(id={"new Constant("+$INT.token.getText()+")"})  		
          |^(CONSTANT GLOB)	-> const(id={"new Constant("+$GLOB.token.getText()+")"}) 	
          ;

literal	  :^(CONSTANT TRUE)	-> filler(id={"Literal.TRUE"}) 		
          |^(CONSTANT FALSE)-> filler(id={"Literal.FALSE"}) 
          ;
