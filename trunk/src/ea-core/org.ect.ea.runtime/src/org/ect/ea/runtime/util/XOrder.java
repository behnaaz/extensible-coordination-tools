package org.ect.ea.runtime.util;

import java.util.Comparator;

import org.ect.ea.runtime.TransCon;
import org.ect.ea.runtime.conditions.Predicate;
import org.ect.ea.runtime.constraints.And;
import org.ect.ea.runtime.constraints.Assignment;
import org.ect.ea.runtime.constraints.Literal;
import org.ect.ea.runtime.primitives.Cell;
import org.ect.ea.runtime.primitives.Constant;
import org.ect.ea.runtime.primitives.TimeoutPort;


/** 
 * Imposes execution order in composite TransCons: 
 * 1) Boolean literals 2) Predicates 3) Cell to Cell assignments  
 * */
public class XOrder implements Comparator<TransCon> {

	public int compare(TransCon t1, TransCon t2) {
		if (t1==Literal.TRUE || t1==Literal.FALSE)
			return -1;
		if (t2==Literal.TRUE || t2==Literal.FALSE)
			return 1;
		
		if (t1 instanceof Predicate) 
			return -1;
		if (t2 instanceof Predicate) 
			return 1;
		
		if (t1 instanceof Assignment 
				&& ((Assignment) t1).isInternal())
			return -1;			
//		if (t2 instanceof Assignment 
//				&& ((Assignment) t2).lhs instanceof Cell 
//				&& ((Assignment) t2).rhs.argument instanceof Cell)
			return 1;					
	}
	
	public static void main(String[] args) {
		Predicate pred = new Predicate(new TimeoutPort("A"), new Constant<Integer>(2), Predicate.operator("!=")); 
		Assignment	eq1 = new Assignment(new TimeoutPort("A"), new TimeoutPort("B")), 
			eq2= new Assignment(new Cell("x"), new Cell("y"));
		And and = new And();
		and.add(eq1);
//		and.add(pred);
		and.add(eq2);
		and.add(eq2);
//		and.add(Literal.FALSE);
		and.add(new And());
		
		System.err.println(and);
	}
}
