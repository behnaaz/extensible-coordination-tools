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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Parameter;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;

public class SubTest {
	private static Parameter tr(String s) {
		if (s.equals("true"))
			return new Literal(true);
		if (s.equals("false"))
			return new Literal(false);		
		if (s.startsWith("$s."))
			return new Element(s.substring(3), ElementType.SOURCE_MEMORY);
		if (s.startsWith("$t."))
			return new Element(s.substring(3), ElementType.TARGET_MEMORY);
		if (s.endsWith("()"))
			return new Function(s.substring(0, s.length()-2));
		if (s.startsWith("\"") && s.endsWith("\""))
			return new Element(s.substring(3), ElementType.STRING);
		try {
			Integer.parseInt(s);
			return new Element(s, ElementType.INTEGER);
		} catch (NumberFormatException n) {};
		
		return new Element(s, ElementType.IDENTIFIER);
	}
	
	private static Collection<Parameter> tr(String[] ss) {
		Collection<Parameter> ret = new HashSet<Parameter>();
		for (String s : ss) {
			ret.add(tr(s));
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		String[] constraints = new String[]{
				"F=X & Z=W & X=M & M=W & M=E",
				"F=X & Z=U & X=N & N=U & N=B",
				"C=$s.ac & xxx=C & B=xxx",
				"FD=xxx & FB=FD & B=FB & OUTPUT=B & $t.bc=B & D=$s.ad & xxx=D",
				"Y=B & B=A & A!=10 & A=X",
				"f(g(X))==true & X=Y",
				"AX=AP & S=X & AP=P & R=X & X=foo(AX)"
		};
		String [][] hide = new String [][] {
				{"X","Z","M","W"},
				{"X","Z","N","U"},
				{"xxx","C"},
				{"FB","FD","D","B","xxx"},
				{"A","B"},
				{"X"},
				{"AX","AP","X"}
		};
		String [][] keep = new String [][] {
				{"F","E"},
				{"F","B"},
				{"B","$s.ac"},
				{"OUTPUT","$s.ad","$t.bc"},
				{"X","Y","10"},
				{"Y","f()","g()", "true"},
				{"P","Q","R","S"}
		};
		
		for (int i=0; i<constraints.length; i++){
			Constraint c = ConstraintParserHelper.parse(constraints[i]);
			System.out.println(c+" \\ "+Arrays.toString(hide[i]));
			Substitutor1 s1 = new Substitutor1(true, tr(hide[i]));
			System.out.println(	"\t"+s1.doSwitch( c));
			
//			Substitutor1 s2 = new Substitutor1(false, tr(keep[i]));
//			System.out.println(	"\t"+s2.doSwitch( c));
		}
	}
}
