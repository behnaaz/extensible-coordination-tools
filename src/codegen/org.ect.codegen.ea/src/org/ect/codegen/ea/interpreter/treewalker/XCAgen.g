tree grammar XCAgen;

options {
	tokenVocab=CATokens;
	ASTLabelType=CommonTree;
}

@header {
package cwi.ea.runtime.interpreter.treewalker;

import java.util.Map;
import java.util.HashMap;

import org.ect.ea.runtime.*;
import cwi.ea.runtime.interpreter.*;
import org.ect.ea.runtime.primitives.*;
import org.ect.ea.runtime.constraints.*;
import org.ect.ea.runtime.conditions.*;
import static org.ect.ea.runtime.conditions.Predicate.Rel;
}

@members{
Map<String, TransactionalIO> synchronisers;
Map<String, XState> states = new HashMap<String, XState>();

private ChannelOperation getFunc(String id) {
	try {
		return (ChannelOperation)Class.forName(id).newInstance();
	} catch(Exception e) {
		e.printStackTrace();
		return null;		
	}
}
}

module	returns [List<ExecutableCA> val]
@init {$val = new ArrayList<ExecutableCA>();}
			:(automaton[new HashMap()]	{$val.add($automaton.val);}
			 )+ 
			;

automaton[Map synchronisers] returns [ExecutableCA val]
@init{this.synchronisers = synchronisers;}
			:^(CA GLOB					{$val = new ExecutableCA($GLOB.text);}
			(port						{$val.addPort($port.val);}
			)* 
			(state						{$val.addState($state.val);}
			)*)
			;

port returns [TransactionalIO val]
			:^(PORT IDENT IO?) 		
{
	if (!synchronisers.containsKey($IDENT.text))
		synchronisers.put($IDENT.text, new TimeoutPort($IDENT.text));
	
	$val = synchronisers.get($IDENT.text);
}
			;

state returns [XState val]	
			:^(STATE IDENT
{
	if (!states.containsKey($IDENT.text)) 
		states.put($IDENT.text, new XState($IDENT.text));

	$val = states.get($IDENT.text);
} 			
			(INITIALSTATE				{$val.setStart(true);}
			)? 	
			(cell						{$val.addCell($cell.val);}
			)* 
			(transition					{$val.addOutgoing($transition.val);}
			)*)
			;
	
cell returns [Cell val]
			:^(CELL IDENT (init=GLOB|init=INT|init=TRUE|init=FALSE)?)
{
	if (!synchronisers.containsKey($IDENT.text)) 
		synchronisers.put($IDENT.text, new Cell($IDENT.text, $init.text));
	
	$val = (Cell)synchronisers.get($IDENT.text);	
}
			;

transition	returns [XTransition val]
@init {$val = new XTransition();}
			:^(TRANS (port				{$val.addPort($port.val);}
			)* 
			 constraint					{$val.setConstraint($constraint.val);} 
			 state						{$val.setTarget($state.val);}
			)
			;

constraint returns [TransCon val]
			:^(OR						{$val = new Or();}
			(m=constraint				{((Or)$val).add($m.val);}
			)+ )	
			|^(AND 						{$val = new And();}
			 (m=constraint				{((And)$val).add($m.val);}
			)+ )
			| equation					{$val = $equation.val;}
			| predicate					{$val = $predicate.val;}
			| ^(CONSTANT (TRUE|FALSE))	{$val = Literal.TRUE; /*XXX:FIXME*/}			
			;

equation returns [Assignment val]
			:^(ASSIGN l=element r=param)	{$val = new Assignment($l.val, $r.val);}	 
			;			

predicate returns [Predicate val]
			:^(RELOP l=param r=param)	{$val = new Predicate($l.val, $r.val, Predicate.operator($RELOP.text));}	 
			;			

param returns [TransactionalIO val]
			:function					{$val = $function.val;}	
			|element					{$val = $element.val;}
			;
			 
function returns [FunComposition val]
			:^(FUNCTION (
			 element					{$val = new FunComposition($element.val);}
			) (IDENT					{$val.addComposition(getFunc($IDENT.text));}
			)+ )
			;
			      		
element	returns [TransactionalIO val]
			:port						{$val = $port.val;}
			|cell						{$val = $cell.val;}
			|^(CONSTANT INT)			{$val = new Constant($INT.text);}
			|^(CONSTANT GLOB) 			{$val = new Constant($GLOB.text);}
			|^(CONSTANT TRUE) 			{$val = Literal.TRUE;}
			|^(CONSTANT FALSE) 			{$val = Literal.FALSE;}
			;
