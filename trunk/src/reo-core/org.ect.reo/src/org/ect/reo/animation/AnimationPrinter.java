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
import static org.ect.reo.animation.AnimationText.DESTROY;
import static org.ect.reo.animation.AnimationText.EQUAL;
import static org.ect.reo.animation.AnimationText.NEXT;
import static org.ect.reo.animation.AnimationText.PAREN_L;
import static org.ect.reo.animation.AnimationText.PAREN_R;
import static org.ect.reo.animation.AnimationText.RECEIVE;
import static org.ect.reo.animation.AnimationText.SEMICOLON;
import static org.ect.reo.animation.AnimationText.SEND;
import static org.ect.reo.animation.AnimationText.TRANSFER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.util.PrimitiveEndNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class AnimationPrinter {
	
	// End names map.
	private PrimitiveEndNames names;
	
	/**
	 * Default constructor.
	 */
	public AnimationPrinter() {
	}
	
	/**
	 * Alternative constructor.
	 * @param names End names map.
	 */
	public AnimationPrinter(PrimitiveEndNames names) {
		this.names = names;
	}
	
	/**
	 * Alternative constructor.
	 * @param element Context element.
	 */
	public AnimationPrinter(Connectable element) {
		names = new PrimitiveEndNames();
		names.generate(element);
	}
	
	/**
	 * Alternative constructor.
	 * @param Connector connector.
	 */
	public AnimationPrinter(Connector connector) {
		names = new PrimitiveEndNames();
		names.generate(connector);
	}

	/**
	 * Alternative constructor.
	 * @param network Network.
	 */
	public AnimationPrinter(Network network) {
		names = new PrimitiveEndNames();
		names.generate(network);
	}


	
	public String printAllTables(ColouringTable table) {
		StringBuffer result = new StringBuffer();
		result.append(printTable(table));
		for (ColouringTable current : table.getAllTables()) {
			if (current!=table) result.append(printTable(current));
		}
		return result.toString();
	}

	
	public String printTable(ColouringTable table) {
		StringBuffer result = new StringBuffer();
		result.append(getTableName(table) + " " + CPAREN_L + "\n");
		for (Colouring colouring : table.getColourings()) {
			result.append("  " + internalPrintAnimation(colouring) + "\n");
		}
		result.append(CPAREN_R + "\n");
		return result.toString();
	}
	
	
	public String printColouring(Colouring colouring) {
		return internalPrintAnimation(colouring);
	}

	public String printAnimation(Animation animation) {
		return internalPrintAnimation(animation);		
	}
	
	public String printColouringEntry(PrimitiveEnd end, FlowColour colour) {
		return getEndName(end) + COLON + colour.getLiteral();
	}
	
	private String internalPrintAnimation(Colouring colouring) {
		
		StringBuffer result = new StringBuffer();
		
		// List of all ends.
		List<PrimitiveEnd> ends = new ArrayList<PrimitiveEnd>();
		ends.addAll(colouring.getColours().keySet());
		
		// Sort the ends by name.
		Collections.sort(ends, new Comparator<PrimitiveEnd>() {
			public int compare(PrimitiveEnd end1, PrimitiveEnd end2) {
				return getEndName(end1).compareTo(getEndName(end2));
			}
		});
		
		for (PrimitiveEnd end : ends) {
			result.append(printColouringEntry(end, colouring.getColour(end)) + " ");
		}
		
		if (colouring instanceof Animation) {
			Animation animation = (Animation) colouring;
			for (AnimationStep step : animation.getSteps()) {
				result.append(getStepName(step));
				result.append(PAREN_L);
				for (int i=0; i<step.getActiveEnds().size(); i++) {
					result.append(getEndName(step.getActiveEnds().get(i)));
					if (i<step.getActiveEnds().size()-1) result.append(COMMA);
				}	
				result.append(PAREN_R + " ");
			}		
		}
		
		ColouringTable next = colouring.getNextColouringTable();
		if (next!=null) {
			result.append(NEXT + EQUAL + getTableName(next));
		}
		
		// Trim and add a semi-colon.
		return result.toString().trim() + SEMICOLON;
		
	}
	

	private String getTableName(ColouringTable table) {
		String name = table.getName();
		if (name==null || name.equals("")) name = "unknown_table";
		return name;
	}

	private String getStepName(AnimationStep step) {
		if (step instanceof CreateStep) return CREATE;
		if (step instanceof DestroyStep) return DESTROY;
		if (step instanceof SendStep) return SEND;
		if (step instanceof ReceiveStep) return RECEIVE;
		if (step instanceof ReplicateStep) return TRANSFER;
		else return "unknown_action";
	}
	
	private String getEndName(PrimitiveEnd end) {
		if (names!=null && names.containsKey(end)) {
			return names.get(end);
		} else {
			return end.toString();
		}
	}
	
}
