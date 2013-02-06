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
package org.ect.codegen.ea.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.ect.codegen.ea.catparser.CATParser;
import org.ect.codegen.ea.catparser.CATokens;
import org.ect.codegen.ea.generator.treewalker.CATgen;


@SuppressWarnings("unchecked")
public class CATDriver {
	public static String
		CLASS = "connectorClass", PACKAGE = "package", IMPORTS = "staticImports", PORTS = "ports"  
	;
	StringTemplateGroup fieldGroup, stateMachineGroup, constructorGroup;		
	private Map attributes = new HashMap();
	private List<String> imports = new ArrayList<String> ();
	
	public CATDriver(TemplateLoader tl) {
		fieldGroup = tl.getGroup("fields");
		constructorGroup = tl.getGroup("constructor");
		stateMachineGroup = tl.getGroup("statemachine");
		
		attributes.put(CLASS, "MyCoordinator");
		attributes.put(PACKAGE,"coordinators");
	}

	public Map getAttributes() {
		return attributes;
	}
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public StringTemplate generate(ANTLRStringStream ass) throws RecognitionException  {		
		CATokens lexer = new CATokens (ass);
/*
	    // create the lexer attached to stdin
	    ANTLRInputStream input = new ANTLRInputStream(System.in);
	    CATLexer lexer = new CATLexer(input);
	    
	    Token token;
		while ((token = lexer.nextToken())!=Token.EOF_TOKEN) {
			System.out.println("Token: "+token.getText()+" type: "+token.getType());
		}
*/		
	    // create the buffer of tokens between the lexer and parser
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    // create the parser attached to the token buffer
	    CATParser parser = new CATParser(tokens);
	    // launch the parser starting at rule r, get return object
	    Tree t= (Tree) parser.ca().getTree();
//	    new FileWriter(TESTFILE+".out").write(t.toStringTree());
	    System.err.println(t.toStringTree()+"\n");

	    StringTemplate fields = walk(t, tokens, fieldGroup);
	    attributes.putAll(fields.getAttributes());
	    StringTemplate constructor = walk(t, tokens, constructorGroup);
	    attributes.putAll(constructor.getAttributes());
	    StringTemplate methods = walk(t, tokens, stateMachineGroup);
	    attributes.putAll(methods.getAttributes());
	    
	    StringTemplate top = fieldGroup.getInstanceOf("top");
	    top.setAttributes(attributes);

	    top.setAttribute("constructor", constructor);
	    top.setAttribute("methods", methods);
	    top.setAttribute("fields", fields);
	    
	    return top; 
	}
	
	private StringTemplate walk(Tree t, CommonTokenStream tokens, StringTemplateGroup stg) 
	throws RecognitionException {
	    // Walk resulting tree; create treenode stream first
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
	    // AST nodes have payloads that point into token stream
	    nodes.setTokenStream(tokens);
	    // Create a tree Walker attached to the nodes stream
	    CATgen walker = new CATgen(nodes);
	    // give parser templates
	    walker.setTemplateLib(stg); 
	    
	    return (StringTemplate) walker.automaton().getTemplate();		
	}


	public static void main(String[] args) throws Exception {
		String TESTFILE = args.length>0? args[0]:"testdata/mrg.cat";
		CATDriver driver = new CATDriver(
				new TemplateLoader("templates"));
		System.out.println(driver.generate(new ANTLRFileStream(TESTFILE))
				.toString(30));
		
/*		for (Object o: (List)driver.attributes.get("states"))
			System.err.println(o);
		for (Map.Entry a: (Collection<Map.Entry>)driver.attributes.entrySet()) 
			System.err.println(a.getKey() +":"+a.getValue().getClass());
*/
	}	
}
