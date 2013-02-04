package org.ect.ea.extensions.constraints.operations;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.ConstraintsSwitch;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Parameter;
import org.ect.ea.extensions.constraints.Predicate;

/**
 * Unifies Equations i.e. <pre>a=b & b=c => a=c</pre>
 */
public class Substitutor1 extends ConstraintsSwitch<Constraint>{
	private Set<Parameter> names;
	private Map<Parameter, Parameter> eqns = new HashMap<Parameter, Parameter> ();		
	private boolean remove;

	public Substitutor1(boolean remove, Collection<Parameter> names) {
		this.names = new HashSet<Parameter>(names);
		this.remove = remove;
	}

	@Override
	public Constraint caseDisjunction(Disjunction con) {
		final Deque<Constraint> ret = new LinkedList<Constraint>();
		for (Constraint c : con.getMembers())
			ret.add(doSwitch(c));
		
		if (ret.isEmpty())
			return new Literal(true);
		else if (ret.size()==1)
			return ret.getFirst();
		else {
			Disjunction d = new Disjunction();
			d.getMembers().addAll(ret);
			return d;
		}
	}

	@Override
	public Constraint caseConjunction(Conjunction con) {				
		final Deque<Constraint> ret = new LinkedList<Constraint>();
		Set<Parameter> done = new HashSet<Parameter>(names);
		init(con);
		
		for (Constraint c : con.getMembers())
			if (c instanceof Equation) {
				Parameter left = ((Equation) c).getLeft();
				Parameter right = ((Equation) c).getRight();

				if (c instanceof Predicate) {
					ret.addFirst( new Predicate(findSub(left), findSub(right),
							((Predicate) c).getType()));					
				}
				else if (done.contains(left) ^ remove) {					
					ret.addLast( new Equation(
							(Parameter)EcoreUtil.copy(left),
							findSub(right)));
				}						
			} else
				throw new IllegalArgumentException("nested constraints unsupported "+c);
		
		if (ret.isEmpty())
			return new Literal(false);
		else if (ret.size()==1)
			return ret.getFirst();
		else {
			Conjunction c = new Conjunction();
			c.getMembers().addAll(ret);
			return c;
		}
	}

	private void init(Conjunction con) {
		for (Constraint c : con.getMembers()) 
			if (c.getClass()==Equation.class) { //ignore predicates
				Equation eq = (Equation) c;				
				eqns.put(eq.getLeft(), eq.getRight());
			}
	}
	
	private Parameter findSub(Parameter p) {
		Parameter sub = p;

		while (!names.contains(sub) ^ remove)	{
//			System.err.println(sub);
			sub = eqns.get(sub);
			if (sub==null || EcoreUtil.equals(p,sub))	//Cycle check
				throw new IllegalStateException("no substitution for "+p + " in "+eqns);
		}

		if (sub instanceof Function) {
			Function ret = new Function(sub.getValue());
			for (Parameter p1:((Function) sub).getParameters()) 
				ret.getParameters().add(findSub(p1));
			
			return ret;
		}
		
		return (Parameter)EcoreUtil.copy(sub);
	}

}
