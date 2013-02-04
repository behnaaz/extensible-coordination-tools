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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.constraints.Composite;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.ConstraintsFactory;
import org.ect.ea.extensions.constraints.ConstraintsSwitch;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Predicate;
import org.ect.ea.extensions.constraints.PredicateType;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;

public final class Simplifier extends ConstraintsSwitch<Constraint> {
//	private static final Literal FALSE = new Literal(false);
//	private static final Literal TRUE = new Literal(true);

	private Constraint caseComposite(Composite com, boolean trivial, boolean ignore) {
		Literal simpleLit = new Literal(trivial), ignoreLit = new Literal(ignore);
		
		if (com.getMembers().isEmpty())
			return ignoreLit;

		List<Constraint> children = new ArrayList<Constraint>();
		for(Constraint c: com.getMembers()) {
			Constraint simplified = doSwitch(c);
			if (simplified.equals(simpleLit))
				return simpleLit;
			
			if (!simplified.equals(ignoreLit)) 
				children.add( simplified );
		}
		if (children.isEmpty())
			return ignoreLit;
		else if (children.size()==1)
			return children.get(0);

		EClass clazz = com.eClass();
		Composite newCom = (Composite) ConstraintsFactory.eINSTANCE.create(clazz);
		for (Constraint c: children) {
			if (clazz.isInstance(c)) 
				newCom.getMembers().addAll(
						((Composite)c).getMembers());
			else
				newCom.getMembers().add(c);
		}
		return (Constraint) newCom;
	}

	@Override
	public Constraint caseConjunction(Conjunction con) {
		return caseComposite(con, false, true);
	}
	
	@Override
	public Constraint caseDisjunction(Disjunction dis) {
		return caseComposite(dis, true, false);
	}

	@Override
	public Constraint caseConstraint(Constraint eqn) {
//		return new Equation(visit((Constraint)eqn.getLeft()), 
//				visit((Constraint)eqn.getRight()));
//		if (eqn.eContents().contains(TRUE) && eqn.eContents().contains(FALSE) )
//			return new Literal(false);
		
		return (Constraint) EcoreUtil.copy(eqn);
	}
	

	private void simplify(Predicate pred, List<Predicate> preds){
		ListIterator<Predicate>  it = preds.listIterator();		
		boolean add;
		while (it.hasNext()) {
			Predicate pred1 = it.next();
			Predicate pred2 = (Predicate) EcoreUtil.copy(pred1);
			pred2.flip();
			if (EcoreUtil.equals(pred, pred2) || EcoreUtil.equals(pred, pred1) )
				add = false;
		}		
	}
	
	private boolean isTrivial(List<Predicate> preds){
		int i=0;
		for (Predicate p: preds) {		
			Predicate flipped = (Predicate) EcoreUtil.copy(p);
			flipped.flip();
			Predicate negated = (Predicate) EcoreUtil.copy(p);
			negated.negate();			
			Predicate negatedFlipped = (Predicate) EcoreUtil.copy(negated);
			negatedFlipped.flip();
			
			Iterator<Predicate>  it = preds.listIterator(++i);
			while (it.hasNext()) {
				Predicate next =  it.next();			
				if (EcoreUtil.equals(next, negated) || EcoreUtil.equals(next, negatedFlipped) )
					return true;
				else if (EcoreUtil.equals(next, p) || EcoreUtil.equals(next, flipped) )
					it.remove();
			}
		}
		return false;
	}
	
	@SuppressWarnings("all")
	public static void main(String[] args) throws ParseException {
		String[] constraints = new String[]{
				"true==false",
				"((A=B & B=A & ($t.x=$s.y & $s.y=$t.x & true)) | (foo(C)>10))",
				"(a>10 & a<=10)",
				"C=$s.ac & xxx=C & B=xxx",
				"FD=xxx & FB=FD & B=FB & OUTPUT=B & $t.bc=B & D=$s.ad & xxx=D",
				"Y=B & B=A & A!=10 & A=X",
				"f(g(X))==true & X=Y",
				"AX=AP & S=X & AP=P & R=X & X=foo(AX)"
		};
		for (int i=0; i<constraints.length; i++){
			Constraint c = ConstraintParserHelper.parse(constraints[i]);

			System.out.println(c+"\n=>"+new Simplifier().doSwitch(c));
		}

		
		Predicate p2 = new Predicate(new Element("A", ElementType.IDENTIFIER), new Element("10",ElementType.INTEGER), PredicateType.NOT_EQUAL);
		Predicate p3 = new Predicate(new Element("B", ElementType.IDENTIFIER), new Element("10",ElementType.INTEGER), PredicateType.GREATER);
		Predicate p4 = new Predicate(new Element("B", ElementType.IDENTIFIER), new Element("10",ElementType.INTEGER), PredicateType.GREATER_EQUAL);
		Predicate p5 = new Predicate(new Element("A", ElementType.IDENTIFIER), new Element("10",ElementType.INTEGER), PredicateType.EQUAL);
		List<Predicate> lp = new ArrayList<Predicate>();
		lp.add(p2);
		lp.add(p3);
		System.out.println(new Simplifier().isTrivial(lp));
		System.out.println(new Simplifier().isTrivial(lp));
	}
}
