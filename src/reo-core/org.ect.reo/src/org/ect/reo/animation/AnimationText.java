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
package org.ect.reo.animation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.ect.reo.colouring.FlowColour;


/**
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class AnimationText {
	
	// Textual representations of the flow / no-flow colors.
	public static final String FLOW = FlowColour.FLOW_LITERAL.getLiteral();
	public static final String NO_FLOW_G = FlowColour.NO_FLOW_GIVE_REASON_LITERAL.getLiteral();
	public static final String NO_FLOW_R = FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL.getLiteral();
	public static final String NO_FLOW = FlowColour.NO_FLOW_LITERAL.getLiteral();
	
	// Names of the animation steps.
	public static final String CREATE = "create";
	public static final String DESTROY = "destroy";
	public static final String SEND = "send";
	public static final String RECEIVE = "receive";
	public static final String TRANSFER = "transfer";
	
	// The 'next' keyword.
	public static final String NEXT = "next";
	
	// Delimiter characters.
	public static final char PAREN_L = '(';
	public static final char PAREN_R = ')';
	public static final char CPAREN_L = '{';
	public static final char CPAREN_R = '}';
	public static final char COMMA = ',';
	public static final char COLON = ':';
	public static final char SEMICOLON = ';';
	public static final char EQUAL = '=';
	
	// Sets of all valid delimiters.
	protected static final Set<Character> DELIMITERS = 
		new HashSet<Character>(Arrays.asList(new Character[] { PAREN_L, PAREN_R, CPAREN_L, CPAREN_R, COMMA, COLON, SEMICOLON, EQUAL }));
	
}
