/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.ea.extensions.constraints.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.constraints.Composite;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Parameter;
import org.ect.ea.extensions.constraints.Predicate;
import org.ect.ea.extensions.constraints.PredicateType;

/**
 * Constraint equivalence-checking
 * @author maraikar
 * XXX:This is class is a buggy miserable hack!
 */
public final class Comparator  {
	public static boolean isEquivalent(Constraint c1, Constraint c2)  {
		Simplifier cs = new Simplifier();
		Constraint cs1 = cs.doSwitch(c1),
		cs2 = cs.doSwitch(c2);
		try {
			Class<? extends Constraint> cl1 = cs1.getClass(),
			cl2 = cs2.getClass();
			//A bit of reflection to avoid nasty instanceofs
			return cl1!=cl2 ? false
					:(Boolean)Comparator.class.getMethod("isEquivalent", cl1, cl2)
					.invoke(null, cs1, cs2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
		
	public static boolean isEquivalent(Literal lit1, Literal lit2) {
		return lit1.equals(lit2);
	}
	
	public static boolean isEquivalent(Predicate pred1, Predicate pred2) {
		if (EcoreUtil.equals(pred1.getLeft(), pred2.getLeft()) &&
				EcoreUtil.equals(pred1.getRight(), pred2.getRight())  &&
				pred1.getType()==pred2.getType())			
			return true;
		
		Predicate pred3 = (Predicate) EcoreUtil.copy(pred1);
		pred3.flip();
		
		if (EcoreUtil.equals(pred3.getLeft(), pred2.getLeft()) &&
				EcoreUtil.equals(pred3.getRight(), pred2.getRight())  &&
				pred3.getType()==pred2.getType())			
			return true;
			
		return false;
	}	
	
	public static boolean isEquivalent(Equation eq1, Equation eq2) {		
		return EcoreUtil.equals(eq1.getLeft(), eq2.getLeft()) &&
		EcoreUtil.equals(eq1.getRight(), eq2.getRight());
	}	

	public static boolean isEquivalent(Function fn1, Function fn2) {
		//EList.equals() uses == which screws things up so use an ArrayList
		return fn1.getValue().equals(fn2.getValue()) &&
			new ArrayList<Parameter>(fn1.getParameters()).equals(fn2.getParameters());
	}
	
	public static boolean isEquivalent(Conjunction con1, Conjunction con2) {
		return isEquivalent(Collections.unmodifiableCollection(con1.getMembers()),
				Collections.unmodifiableCollection(con2.getMembers()));
	}
	
	public static boolean isEquivalent(Disjunction dis1, Disjunction dis2) {
		return isEquivalent(Collections.unmodifiableCollection(dis1.getMembers()), 
				Collections.unmodifiableCollection(dis2.getMembers()));		
	}

	private static boolean isEquivalent(Collection<Constraint> con1, Collection<Constraint> con2) {
		if (con1.size()!=con2.size())
			return false;
		
		Set<Constraint> s1 = new HashSet<Constraint>(con1);

top:	for (Constraint c2: con2) {
			Iterator<Constraint> it = s1.iterator();
			while (it.hasNext()) {
				Constraint c1= it.next();
				if (isEquivalent(c1, c2)) {
					it.remove();
					continue top;
				}
			}			
			return false; //if no match
		}
		return true;		
	}
	
	@SuppressWarnings("unchecked")
	public static boolean contains(Constraint cs1, Constraint cs2)  {
		if (cs2 instanceof Composite)
			return contains((Collection)((Composite) cs2).getMembers(), cs1);
		else if (cs1 instanceof Composite) 
			return contains((Collection)((Composite) cs1).getMembers(), cs2);			
		else 
			return isEquivalent(cs1, cs2);
	}

	public static boolean contains(Collection<Constraint> set, Constraint element) {
		for (Constraint c: set)
			if (isEquivalent(c, element))
				return true;
		
		return false;		
	}
/*	
	@SuppressWarnings("unchecked")
	private static final List<Class> hierarchy = Arrays.asList(new Class[] {
		Disjunction.class, Conjunction.class, Equation.class, Function.class, Literal.class
	});
	public static boolean contains0(Constraint cs1, Constraint cs2)  {
		try {
			Class<? extends Constraint> cl1 = cs1.getClass(),
			cl2 = cs2.getClass();
			
			//A bit of reflection to avoid nasty instanceofs
			if (cl1==cl2) 
					return (Boolean)Comparison.class.getMethod("isEquivalent", cl1, cl2)
							.invoke(null, cs1, cs2);
			else //swap args if necessary to conform to DNF
				if (hierarchy.indexOf(cl1) < hierarchy.indexOf(cl2))
					return (Boolean)Comparison.class.getMethod("contains", cl1, cl2)
					.invoke(null, cs1, cs2);
				else
					return (Boolean)Comparison.class.getMethod("contains", cl2, cl1)
					.invoke(null, cs2, cs1);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean contains0(Disjunction dis, Conjunction con) {
		for (Constraint c: dis.getMembers())
			if (isEquivalent(c, con))
				return true;
		
		return false;
	}
	*/
	public static void main(String[] args) {
		Equation eq1 = new Equation(new Element("A", ElementType.IDENTIFIER), 
				new Element("B", ElementType.IDENTIFIER)),
			eq2 = new Equation(new Element("A", ElementType.IDENTIFIER), 
					new Element("B", ElementType.IDENTIFIER)),
//		eq2 = new Equation(new Element("B", ElementType.IDENTIFIER), 
//				new Element("A", ElementType.IDENTIFIER)),
//		eq3 = new Equation(new Element("x", ElementType.TARGET_MEMORY), 
//				new Element("y", ElementType.SOURCE_MEMORY)),
		eq3 = new Equation(new Element("y", ElementType.SOURCE_MEMORY), 
						new Element("x", ElementType.TARGET_MEMORY)),
		eq4 = new Equation(new Element("y", ElementType.SOURCE_MEMORY), 
				new Element("x", ElementType.TARGET_MEMORY));

		Function fn1 = new Function("foo"),
		fn2 = new Function("foo");
		fn1.getParameters().add(new Element("C", ElementType.IDENTIFIER));
		fn2.getParameters().add(new Element("C", ElementType.IDENTIFIER));
		Predicate p1 = new Predicate(fn1, new Element("10",ElementType.INTEGER), PredicateType.GREATER);
		Predicate p2 = new Predicate(new Element("10",ElementType.INTEGER), fn2, PredicateType.LESS);
		
		Conjunction c1 = new Conjunction(),
		c2 = new Conjunction();
		c1.getMembers().add(p1);
		c1.getMembers().add(eq1);
		c1.getMembers().add(eq3);
		c2.getMembers().add(eq4);
		c2.getMembers().add(eq2);
		c2.getMembers().add(p2);

		System.err.println(c1);
		System.err.println(c2);

		System.err.println(
				isEquivalent(c1, c2));
		System.err.println(
				contains(c2, p1));
	}
}
