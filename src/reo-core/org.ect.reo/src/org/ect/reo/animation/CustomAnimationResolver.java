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

import java.text.ParseException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.libraries.CustomPrimitiveResolver;
import org.ect.reo.util.PropertyHelper;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class CustomAnimationResolver {
	
	/**
	 * If the animation property is set for a custom primitive, we try
	 * to parse it and generate an animation table out of it.
	 * If this doesn't work we try to use an animation
	 * table of one of the resolved primitives. 
	 * @generated NOT
	 */
	public static AnimationTable getAnimationTable(CustomPrimitive custom) {
		
		// Try to use the animation definition.
		String definition = AnimationSemanticsProvider.getAnimationDefinition(custom);
		if (definition!=null) {
			
			// Parse the animation definition.
			AnimationParser parser = new AnimationParser(custom);
			try {
				return parser.parseAllTables(definition);
			} catch (ParseException e) {
				throw new RuntimeException("Error parsing animation definition of " + custom, e);
			}
			
		}

		// Try to use a cached version:
		if (custom.getResolved()==null) {
			CustomPrimitive cached = CustomPrimitiveResolver.getCached(custom);
			if (cached!=null) {
				cached = EcoreUtil.copy(cached);
				custom.setResolved(cached);
			}
		}

		// Otherwise try to use the resolved components.
		if (custom.getResolved()==null || shouldRefresh(custom)) {
			try {
				CustomPrimitiveResolver.resolve(custom, new NullProgressMonitor());
			}
			catch (CoreException e) {
				throw new RuntimeException("Error resolving component: " + e.getMessage(), e);
			}
		}
		
		CustomPrimitive resolved = custom.getResolved();
		while (resolved!=null) {
			if (AnimationSemanticsProvider.getAnimationDefinition(resolved)!=null) {

				// Load the animation table.
				AnimationTable table = getAnimationTable(resolved);
				
				// Check if the interfaces match.
				if (resolved.getSourceEnds().size()!=custom.getSourceEnds().size() ||
					resolved.getSinkEnds().size()!=custom.getSinkEnds().size()) {	
					throw new RuntimeException("Resolved component has a different interface!", null);
					
				} else {
					// Replace the ends in the animation tables.
					for (int i=0; i<resolved.getSourceEnds().size(); i++) {
						AnimationRefactoring.replaceEnd(table, resolved.getSourceEnds().get(i), custom.getSourceEnds().get(i));
					}
					for (int i=0; i<resolved.getSinkEnds().size(); i++) {
						AnimationRefactoring.replaceEnd(table, resolved.getSinkEnds().get(i), custom.getSinkEnds().get(i));
					}
					// Done.
					return table;
				}
				
			} else {
				resolved = resolved.getResolved();
			}
		}
		
		// No definition found: use no data flow as default.
		AnimationTable table = new AnimationTable();
		if (!custom.getAllEnds().isEmpty()) {
			Animation noFlow = new Animation();
			noFlow.setColours(custom.getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			noFlow.setNextAnimationTable(table);
			table.add(noFlow);
		}
		
		return table;
		
	}
	
	private static boolean shouldRefresh(CustomPrimitive custom) {
		return "true".equalsIgnoreCase(PropertyHelper.getFirstValue(custom, "refresh"));
	}
	
}
