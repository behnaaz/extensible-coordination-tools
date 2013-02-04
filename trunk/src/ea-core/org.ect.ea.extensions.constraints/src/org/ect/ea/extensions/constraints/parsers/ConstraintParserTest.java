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
package org.ect.ea.extensions.constraints.parsers;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.operations.Normaliser;

public class ConstraintParserTest {

	public static void main(String[] args) {

		String[] tests = new String[] {
				"D=\"hello\"",
				"D==\"hello\"",
				"true",
				"A_1=B_2",
				"A=? & C=D",
				"A=B & C=D & E=F",
				"(A=B & C=D)",
				"(A=B & C=D & E=F)",
				"(A=B & C=D) & (D=E & F=G)",
				"(A=B | C=D) & (D=E | F=G)",
				"(A=B | C=D) | (D=E | F=G)",
				"(A=B & C=D) | (D=E)",
				"(A=B & C=D) | false",
				"(A=B & C=D) | false | ((X=Y | Y=Z) & true)",
				"$s.name=A",
				"$s.name=$t.id",
				"$s.name=AA & $t.id=BB & $s.age=CC",
				"($s.name=AA & $t.id=BB) | $s.age=CC",
				"$s.name=1234 & 189=\"test\"",
				"A>20",
				"D==\"hello!\"",
				"f(\"foo\", ?)==false",
				"f(?)>=10",
				"f(A)=f(3)",
				"func (8, g(\"test\", A, $s.a, B), C) = $s.a ",
				"f(Abc)=true & g(Bcd)=false",
				"f(A,$s.x,g(true))=false",
				"f(A,3,\"test\")<=\"dntdntthn\""
//				"foo(A)=true & C=A & bar(C)=true & B=C",
		};


		for (String test : tests) {
			ANTLRStringStream input = new ANTLRStringStream(test);

			ConstraintLexer lexer = new ConstraintLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ConstraintParser parser = new ConstraintParser(tokens);

			try {
				System.out.println(test);
				Constraint constraint = parser.start();
				System.out.println("<"+constraint.getClass().getSimpleName() +">\t" 
						+ constraint  + "\t\t\t  ->  DNF: " + new Normaliser().doSwitch(constraint) );
				System.out.println();
  			} catch (RecognitionException e) {
  				e.printStackTrace();
  				return;
  			}
		}
	}
	
	
}
