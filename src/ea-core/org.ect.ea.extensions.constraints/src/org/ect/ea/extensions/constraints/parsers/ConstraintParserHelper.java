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

import java.text.ParseException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.ect.ea.extensions.constraints.Constraint;

/**
 * Parse a constraint using the generated ANTLR parser.
 */

public class ConstraintParserHelper {		
	public static Constraint parse(String value) throws ParseException {
		try {
			// Create the lexer and the parser and parse the string
			return new ConstraintParser(
					new CommonTokenStream( 
							new ConstraintLexer(
									new ANTLRStringStream(value)))
			).start();
		} catch (RecognitionException e) {
			throw new ParseException(e.getMessage(), e.charPositionInLine);
		}
	}		
}
