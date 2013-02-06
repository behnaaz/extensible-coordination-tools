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
package org.ect.codegen.ea.interpreter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

import org.ect.codegen.ea.catparser.CATParser;
import org.ect.codegen.ea.catparser.CATokens;
import org.ect.codegen.ea.interpreter.treewalker.XCAgen;
import org.ect.codegen.ea.runtime.TransactionalIO;

public class XCADriver {
	static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static <T extends TransactionalIO> 
	ExecutableCA<T> loadString(String cat, Map<String, T> ports)
	throws RecognitionException{
		return load(new ANTLRStringStream(cat), ports);		
	}	

	public static <T extends TransactionalIO> 
	ExecutableCA<T> loadFile(String fileName, Map<String, T> ports) 
	throws RecognitionException, IOException  {
		return load(new ANTLRFileStream(fileName), ports);
	}
	
	public static <T extends TransactionalIO> 
	ExecutableCA<T>  load(CharStream input, Map<String, T>  ports) 
	throws RecognitionException {
		Lexer lexer = new CATokens(input);
	    // create the buffer of tokens between the lexer and parser
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    // create the parser attached to the token buffer
	    CATParser parser = new CATParser(tokens);
	    // launch the parser starting at rule r, get return object
	    Tree t= (Tree) parser.ca().getTree();
//	    new FileWriter(TESTFILE+".out").write(t.toStringTree());
	    logger.info("Loaded CAT parse tree:\n" + t.toStringTree()+"\n");
	    
	    // Walk resulting tree; create treenode stream first
	    CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
	    // AST nodes have payloads that point into token stream
	    nodes.setTokenStream(tokens);
	    // Create a tree Walker attached to the nodes stream
	    XCAgen walker = new XCAgen(nodes);

	    // give parser templates	    
	    return walker.automaton(ports);
	    
//	    System.out.println(xca.toString()); 
	}
	
	public static void main(String[] args) throws Exception {
		String TESTFILE = "testdata/lofi.cat";
		System.err.println(loadFile(TESTFILE, new HashMap()));
	}
}
