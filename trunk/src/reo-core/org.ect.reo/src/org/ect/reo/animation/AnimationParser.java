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

import static org.ect.reo.animation.AnimationText.COLON;
import static org.ect.reo.animation.AnimationText.COMMA;
import static org.ect.reo.animation.AnimationText.CPAREN_L;
import static org.ect.reo.animation.AnimationText.CPAREN_R;
import static org.ect.reo.animation.AnimationText.CREATE;
import static org.ect.reo.animation.AnimationText.DELIMITERS;
import static org.ect.reo.animation.AnimationText.DESTROY;
import static org.ect.reo.animation.AnimationText.EQUAL;
import static org.ect.reo.animation.AnimationText.FLOW;
import static org.ect.reo.animation.AnimationText.NEXT;
import static org.ect.reo.animation.AnimationText.NO_FLOW;
import static org.ect.reo.animation.AnimationText.NO_FLOW_G;
import static org.ect.reo.animation.AnimationText.NO_FLOW_R;
import static org.ect.reo.animation.AnimationText.PAREN_L;
import static org.ect.reo.animation.AnimationText.PAREN_R;
import static org.ect.reo.animation.AnimationText.RECEIVE;
import static org.ect.reo.animation.AnimationText.SEMICOLON;
import static org.ect.reo.animation.AnimationText.SEND;
import static org.ect.reo.animation.AnimationText.TRANSFER;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.ect.reo.Connectable;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.semantics.ReoTextualSemantics;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class AnimationParser {
	
	/**
	 * An extension of {@link org.ect.reo.animation.Animation}
	 * that has an additional name attribute for a next-table.
	 */
	class NamedAnimation extends Animation {
		public String next;
	}
	
	// The connectable element to be used.
	private Connectable element;
	
	// Text to be parsed or used for pretty printing.
	private String text;
	
	// The current position in the text. Should not be set directly.
	private int position;
	
	// The current character. Should not be set directly.
	private char current;
	
	
	/**
	 * Default constructor.
	 * @param element Connectable to be used.
	 */
	public AnimationParser(Connectable element) {
		this.element = element;
	}
	
	public synchronized AnimationTable parseAllTables(String text) throws ParseException {
		
		// Reset.
		this.text = text;
		reset();
		
		// Start the parsing.
		AnimationTable result = parseAllTables();
		
		// Reset again.
		this.text = "";
		reset();
		
		// Done.
		return result;
	}
	
	
	protected AnimationTable parseAllTables() throws ParseException {
		
		// Read all tables into a hash-map.
		AnimationTable first = null;
		Map<String,AnimationTable> tables = new HashMap<String,AnimationTable>();
		do {
			// Parse the table.
			AnimationTable table = parseTable();
			if (first==null) first = table;
			
			// Put it into the hash-map.
			if (tables.containsKey(table.getName())) {
				throwException("Duplicate table: '" + table.getName() + "'");
			} else {
				tables.put(table.getName(), table);
			}
			
			readWhiteSpace();
		} while (!isEnd());
		
		// Set the next references.
		for (AnimationTable table : tables.values()) {
			for (int i=0; i<table.size(); i++) {				
				NamedAnimation anim = (NamedAnimation) table.get(i);
				if (anim.next!=null) {
					AnimationTable next = tables.get(anim.next);
					if (next==null) throwException("Unknown table: '" + anim.next + "'");
					anim.setNextAnimationTable(next);
				} else {
					// Use the first table as default next.
					anim.setNextAnimationTable(first);
				}
			}
		}
		
		return first;
	}
	
	
	protected AnimationTable parseTable() throws ParseException {
		
		// Check for empty strings.
		readWhiteSpace();
		checkUnexpectedEnd();
		
		// Read name and create the table.
		String name = readDelimitedIdentifier();
		AnimationTable table = new AnimationTable(name);
		
		readDelimiter(CPAREN_L);
		readWhiteSpace();
		
		while (!isEnd() && current!=CPAREN_R) {
			Animation animation = parseAnimation();
			if (animation!=null) table.add(animation);
			readWhiteSpace();
		}
		
		readDelimiter(CPAREN_R);
		
		return table;
	}

		
	protected NamedAnimation parseAnimation() throws ParseException {
		
		NamedAnimation animation = new NamedAnimation();
		readWhiteSpace();
		checkUnexpectedEnd();
		
		// Parse the entries.
		while (!isEnd() && current!=SEMICOLON) {
			parseEntry(animation);
			readWhiteSpace();
			// It is ok to read a comma, but it is not required.
			if (current==COMMA) {
				readDelimiter();
				continue;
			} else if (DELIMITERS.contains(current) && current!=SEMICOLON) {
				throwException("Unexpected delimiter '" + current + "'");
			}
		}
		
		// If we have not reached the end, we must read the last semicolon.
		if (!isEnd()) readDelimiter();
		
		// Validate or fix the animation.
		
		// There must be at least one colour entry.
		if (animation.getColours().keySet().isEmpty()) {
			return null;
		}
		
		// We add  no-flow colours for the missing ends.
		for (PrimitiveEnd end : element.getAllEnds()) {
			if (!animation.getColours().containsKey(end)) {
				animation.setColour(end, FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			}
		}
		
		return animation;
	}
	
	
	protected void parseEntry(NamedAnimation animation) throws ParseException {
		
		String ident = readDelimitedIdentifier();
		char delim = readDelimiter();

		// A colour entry?
		if (delim == COLON) {
			FlowColour colour = parseColour();
			PrimitiveEnd end = findEnd(ident);
			if (animation.getColours().containsKey(end)) {
				throwException("Duplicate colour entry for '" + ident + "'");
			} else {
				animation.setColour(end, colour);
			}
		}
		// An action?
		else if (delim == PAREN_L) {
			
			AnimationStep step = createStep(ident);
			readWhiteSpace();
			char next = COMMA;
			
			while (!isEnd() && next!=PAREN_R) {
				
				// Read end name.
				String endname = readDelimitedIdentifier();
				checkUnexpectedEnd();
				
				// Read next delimiter.
				next = readDelimiter();
				if (next!=PAREN_R && next!=COMMA) {
					throwException("Expected either '" + PAREN_R + "' or '" + COMMA + "'");
				}
				
				// Find and add the end.
				PrimitiveEnd end = findEnd(endname);
				if (!step.getActiveEnds().contains(end)) {
					step.getActiveEnds().add(end);
				}
			}
			
			// Must not be the end.
			checkUnexpectedEnd();
			
			if (!step.getActiveEnds().isEmpty()) {
				animation.getSteps().add(step);
			}
		}
		// 'next' keyword?
		else if (delim == EQUAL) {
			if (!ident.equals(NEXT)) throwException("Expected '" + NEXT +"' keyword instead of '" + ident + "'");
			if (animation.next!=null) throwException("Duplicate '" + NEXT + "' keyword");
			animation.next = readDelimitedIdentifier();
		}
		// An error!
		else {
			throwException("Expected was either '" + COLON + "' or '" + PAREN_L + "'");
		}
	}
	
	
	
	/**
	 * Parse a flow colour.
	 * @return a flow colour.
	 */
	protected FlowColour parseColour() throws ParseException {
		String literal = readDelimitedString(FLOW, NO_FLOW_G, NO_FLOW_R, NO_FLOW);
		return FlowColour.get(literal);
	}
	
	
	/**
	 * Find a primitive end.
	 * @param name Name of the end.
	 * @return The primitive end.
	 * @throws ParseException If not found.
	 */
	protected PrimitiveEnd findEnd(String name) throws ParseException {
		PrimitiveEnd end = ReoTextualSemantics.getEnds(element).get(name);
		if (end==null) throwException("Unknown primitive end: '" + name + "'");
		return end;
	}
	
	
	/**
	 * Create an animation step, given an action name. 
	 * @param name Action name.
	 * @return The animation step.
	 * @throws ParseException If it was an illegal action.
	 */
	protected AnimationStep createStep(String name) throws ParseException {
		if (CREATE.equals(name)) return new CreateStep();
		if (DESTROY.equals(name)) return new DestroyStep();
		if (SEND.equals(name)) return new SendStep();
		if (RECEIVE.equals(name)) return new ReceiveStep();
		if (TRANSFER.equals(name)) return new ReplicateStep();
		throwException("Unknown action: '" + name + "'");
		return null;
	}
	
	
	// ----- Parsing helper methods ------ //
	
	protected void checkUnexpectedEnd() throws ParseException {
		if (isEnd()) throwException("Unexpected end");
	}
		
	protected void readDelimiter(char delim) throws ParseException {
		char read = readDelimiter();
		if (read!=delim) throwException("Expected '" + delim + "'");
	}

	protected char readDelimiter() throws ParseException {
		readWhiteSpace();
		checkUnexpectedEnd();
		if (DELIMITERS.contains(current)) {
			char read = current;
			next();
			return read;
		} else {
			throwException("Expected a delimiter");
			return ' ';	// Never reached.
		}
	}
	
	protected String readDelimitedString(String... alternatives) throws ParseException {
		
		// Read white spaces and make sure we are not at the end.
		readWhiteSpace();
		checkUnexpectedEnd();
		
		// Check if the current position starts with one of the alternatives.
		String cut = text.substring(position);
		for (String test : alternatives) {
			if (cut.startsWith(test)) {
				
				// Check if we have not reached the end yet and a non-delimiter character is following.
				if (position + test.length() < text.length()) {
					char next = text.charAt(position + test.length());
					if (!Character.isWhitespace(next) && !DELIMITERS.contains(next)) {
						break;
					}
				}
				
				// Now move forward and return the read string.
				move(test.length());
				return test;
			}
		}
		// Unable to read any of the alternatives.
		throwException("Expected one of: " + Arrays.toString(alternatives));
		return null;
	}
	
	
	protected String readDelimitedIdentifier() throws ParseException {
		readWhiteSpace();
		if (isEnd() || !Character.isJavaIdentifierStart(current)) {
			throwException("Invalid identifier start: '" + current + "'");
		}
		int first = position;
		next();
		while (!isEnd() && Character.isJavaIdentifierPart(current)) {
			next();
		}
		// Check if a valid delimiter or whitespace is following.
		if (!isEnd() && !DELIMITERS.contains(current) && !Character.isWhitespace(current)) {
			throwException("Unexpected delimiter: '" + current + "'");
		}
		return text.substring(first, position);
	}
	
	protected boolean readWhiteSpace() {
		boolean found = false;
		while (!isEnd() && (Character.isWhitespace(current))) {
			next();
			found = true;
		}
		return found;
	}
	
	protected void move(int length) {
		position = position + length;
		if (!isEnd()) current = text.charAt(position);
	}
	
	protected void reset() {
		position = 0;
		if (!isEnd()) current = text.charAt(position);
	}
	
	protected void next() {
		move(1);
	}
	
	protected boolean isEnd() {
		return position>=text.length();
	}
	
	protected void throwException(String message) throws ParseException {
		int row = 1, col = 1;
		for (int i=0; i<Math.max(position,text.length()); i++) {
			if (text.charAt(i)=='\r') {
				col = 1;
				row++;
			} else col++;
		}
		throw new ParseException(message + " at line " + row + ", column " + col + ".", position);
	}
}
