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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.ConstraintsSwitch;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Predicate;
import org.ect.ea.extensions.constraints.PredicateType;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;

public final class Normaliser extends ConstraintsSwitch<Disjunction> {
	@Override
	public Disjunction caseConjunction(Conjunction con) {
		Disjunction result = null;
		
		for (Constraint member : con.getMembers()) {
			Disjunction dnf = (Disjunction) doSwitch(member);
			if (result==null) {
				result = dnf;
				continue;
			}
			Disjunction newResult = new Disjunction();
			for (Constraint c1 : result.getMembers()) {
				for (Constraint c2 : dnf.getMembers()) {
					newResult.getMembers().add( new Conjunction((Conjunction) c1, (Conjunction) c2) );
				}
			}
			result = newResult;
		}

		return result!=null ? result : new Disjunction();
	}

	@Override
	public Disjunction caseDisjunction(Disjunction dis) {
		Disjunction disjunction = new Disjunction();
		for (Constraint constraint : dis.getMembers()) {
			Disjunction dnf = (Disjunction) doSwitch(constraint);
			disjunction.getMembers().addAll(dnf.getMembers());
		}
		return disjunction;
	}

	@Override
	public Disjunction caseConstraint(Constraint constraint) {
			Conjunction conjunction = new Conjunction();
			conjunction.getMembers().add((Constraint) EcoreUtil.copy(constraint));
			
			Disjunction disjunction = new Disjunction();
			disjunction.getMembers().add(conjunction);

			return disjunction;

	}

	public static void main(String[] args) throws Exception{
		
		Equation eq1 = new Equation(new Element("A", ElementType.IDENTIFIER), 
				new Element("B", ElementType.IDENTIFIER)),
		eq2 = new Equation(new Element("B", ElementType.IDENTIFIER), 
				new Element("A", ElementType.IDENTIFIER)),
		eq3 = new Equation(new Element("x", ElementType.TARGET_MEMORY), 
				new Element("y", ElementType.SOURCE_MEMORY)),
		eq4 = new Predicate(new Element("y", ElementType.SOURCE_MEMORY), 
				new Element("x", ElementType.TARGET_MEMORY), PredicateType.NOT_EQUAL);

		Disjunction d1 = new Disjunction(),
		d2 = new Disjunction();
		
		d1.getMembers().add(eq1);
		d1.getMembers().add(eq2);
		d2.getMembers().add(eq3);
		d2.getMembers().add(eq4);
		
		Conjunction c1 = new Conjunction();
		c1.getMembers().add(d1);
		c1.getMembers().add(d2);
		
		System.err.println(c1);
		System.err.println(new Normaliser().doSwitch(c1));
		
		System.err.println(new Normaliser().doSwitch(eq4));
		
		Constraint c = ConstraintParserHelper.parse("(a0=b0|c0=d0)&(a1=b1|c1=d1)&true");
		System.err.println(new Normaliser().doSwitch(c));
	}
}
