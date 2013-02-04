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
package org.ect.ea.util.text.serialize;

import java.io.FileReader;
import java.util.Collection;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.extensions.statememory.StateMemory;
import org.ect.ea.util.cacompat.CA;
import org.ect.ea.util.text.parse.CatLexer;
import org.ect.ea.util.text.parse.CatParser;

public class CatPrinter extends EaVisitor<String> {
	public final static String TAB = "\t";	
	public final static String BR = "\n";	
	public final static String QUOTE = "\"";	
	public final static String SPACER = "::";
	public final static String SEP = ",";
	public final static String BEGIN = "[";
	public final static String END = "]";
	
	@Override
	public String visit(Automaton a) {
		return new StringBuilder(QUOTE+a.getName()+QUOTE)
		.append(visit(a.findExtension(AutomatonPortNamesProvider.EXTENSION_ID)))
		.append(BR)
		.append(stringify(a.getStates(), SEP+BR, "",""))
		.toString();
	}
	
	@Override
	public String visit(State s) {
		StringBuilder ret = new StringBuilder();
		ret.append(s.getName());
		
		for (IExtension e: s.getExtensions())
			ret.append(visit(e));

		if (s.getOutgoing().isEmpty())
			return ret.toString();
			
		ret.append(
				stringify(s.getOutgoing(), SEP+BR+TAB, BEGIN+BR+TAB, END));
		
		return ret.toString();
	}

	@Override
	public String visit(Transition t) {
		return
		new StringBuilder(visit(t.findExtension(TransitionPortNamesProvider.EXTENSION_ID)))
		.append(SPACER)
		.append(t.findExtension(ConstraintExtensionProvider.EXTENSION_ID))
		.append(SPACER)
		.append(t.getTarget().getName())
//		.append(BR)
		.toString();
	}

	public String visit(AutomatonPortNames ap) {
		StringBuilder ret = new StringBuilder(BEGIN);

		for (String s: ap.getInPorts())
			ret.append(s).append(SPACER+"IN"+SEP);
		
		for (String s: ap.getOutPorts())
			ret.append(s).append(SPACER+"OUT"+SEP);
		
		ret.deleteCharAt(ret.length()-1);
		
		return ret.append(END).toString(); 
	}
	
	public String visit(TransitionPortNames tp) {
		return stringify(tp.getValues()); 
	}
	
	public String visit(Constraint con) {
		return con.toString(); 
	}

	public String visit(BooleanExtension be) {
		return be.getValue() ? "*":"";
	}
	
	public String visit(StateMemory sm) {
		return stringify(sm.getValues());
	}
	
	private String stringify(Collection c, String sep, String begin, String end) {
		if (c.isEmpty())
			return "";
		
		StringBuilder ret = new StringBuilder(begin);
		for (Object o: c) 
			ret.append(
					o instanceof String ? o: visit(o)
			).append(sep);
		
		ret.delete(ret.length()-sep.length(), ret.length());
		ret.append(end);
		return ret.toString();
	}
	
	private String stringify(Collection c) {
		return stringify(c, SEP, BEGIN, END);
	}
	
	public static void main(String[] args) throws Exception {
		FileReader groupFile = new FileReader("templates/cat.stg");
		StringTemplateGroup ca2txt = new StringTemplateGroup(groupFile);
		StringTemplate top = ca2txt.getInstanceOf("automaton");
		
		CatLexer lexer = new CatLexer(new ANTLRFileStream("testdata/valve.cat"));
		CatParser parser = new CatParser(new CommonTokenStream(lexer));
		CA ca = parser.automaton();
		System.err.println(ca);

		System.out.println(
				new CatPrinter().visit(ca));
	}
}
