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
package org.ect.codegen.ea.catparser;

import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;


public class ConstraintParserTest {

	public static void main(String[] args) {

		String[] tests = new String[] {
				"true",
				"A=B & C=D & E=F",
				"(A=B & C=D)",
				"(A=B & C=D & E=F)",
				"(A=B & C=D) & (D=E & F=G)",
				"(A=B | C=D) & (E=F | G=H)",
				"(A=B | C=D) | (D=E | F=G)",
				"(A=B & C=D) | (D=E)",
				"(A=B & C=D) | false",
				"(A=B & C=D) | false | ((E=F | G=H) & true)",
				"$s.name=A",
				"$s.name=$t.id",
				"$s.name=A & B=$t.id & $s.age=C",
				"($s.name=A & $t.id=B) | $s.age=C",
				"B>20",
				"D==\"hello!\"",
				"f(D)>=10",
				"f(0)<B",
				"f(B)==g(D)",
				"f(B)>=f(3)",
				"f(g(B))==true",
				"g($s.a) == $s.b ",
				"f(D)==true & g(B)==false",
				"f(\"test\")<=\"dntdntthn\"",
				"$s.name==1234 & 189==\"test\"",
//				"f(A,$s.x,g(true))=false",
//				"foo(A)=true & C=A & bar(C)=true & B=C",
		};

		Set<String> outp = new HashSet<String>(){{add("A");add("C");add("E");add("G");}},
		inp = new HashSet<String>(){{add("B");add("D");add("F");add("H");}};

		for (String test : tests) {
			ANTLRStringStream input = new ANTLRStringStream("::"+test+"::");

			Lexer lexer = new CATokens(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CAT_Constraint parser = new CAT_Constraint(tokens, null);

			try {
				System.out.println(test);
				Tree t = (Tree) parser.constraint(inp, outp).getTree();
				System.out.println(t.toStringTree());
				System.out.println();
  			} catch (RecognitionException e) {
  				e.printStackTrace();
  				return;
  			}
		}
	}
	
	
}
