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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Network;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.colouring.ColouringBisimulation;
import org.ect.reo.colouring.ColouringConverter;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.colouring.ColouringRefactoring;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.semantics.TextualSemanticsProvider;
import org.ect.reo.util.PrimitiveEndNames;
import org.ect.reo.util.PropertyHelper;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class AnimationSemanticsProvider implements TextualSemanticsProvider {
	
	/**
	 * Key used for storing animation definitions.
	 */
	public static final String KEY = "animations";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#getElementSemantics(org.ect.reo.Connectable, org.ect.reo.util.PrimitiveEndNames)
	 */
	public String getElementSemantics(Connectable element, PrimitiveEndNames names) {
		
		if (element instanceof Animatable) {
			AnimationTable table = ((Animatable) element).getAnimationTable();
			AnimationPrinter printer = new AnimationPrinter(names);
			return printer.printAllTables(table);
		}
		else {
			throw new RuntimeException(element + " is not colourable!");
		}
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#computeConnectorSemantics(org.ect.reo.Connector, org.ect.reo.util.PrimitiveEndNames, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public String computeConnectorSemantics(Connector connector, PrimitiveEndNames names, IProgressMonitor monitor) {
		
		// Compute the animation table.
		monitor.beginTask("Compute Semantics", 20);
		
		// Compute its animation table:
		connector = ReoTextualSemantics.createNormalizedConnector(connector, names);
		Network network = new Network(connector);
		ColouringEngine engine = connector.getColouringEngine();
		engine.setIgnoreNoFlow(false);
		network.setColouringEngine(engine);
		AnimationTable table = network.getAnimationTable(new SubProgressMonitor(monitor,15)); // 15

		// Hide the internal ends.
		AnimationHiding.hideEnds(table, getConnectorEnds(connector));
		monitor.worked(1); // 16

		// Flip no-flow colours.
		ColouringConverter.flipNoFlowColours(table);

		// Apply flip-rule.
		ColouringConverter.applyFlipRule(table);
		monitor.worked(1); // 17

		// Minimize the tables up to bisimilarity.
		ColouringBisimulation.minimize(table, new SubProgressMonitor(monitor,1)); // 18
		
		// Reset names of the resulting tables.
		ColouringRefactoring.resetTableNames(table);
		
		// Set the animations steps.
		createAnimationSteps(table, true);
		monitor.worked(1); // 19
		
		// Set the animation definition.
		AnimationPrinter printer = new AnimationPrinter(names);
		String definition = printer.printAllTables(table);
		monitor.worked(1); // 20
		
		monitor.done();
		return definition;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#computeNetworkSemantics(org.ect.reo.Network, org.ect.reo.util.PrimitiveEndNames, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public String computeNetworkSemantics(Network network, PrimitiveEndNames names, IProgressMonitor monitor) {
		
		// Compute the animation table.
		monitor.beginTask("Compute Semantics", 20);
		AnimationTable table = network.getAnimationTable(new SubProgressMonitor(monitor,15)); // 15
		
		// Hide the internal ends.
		List<PrimitiveEnd> ends = new ArrayList<PrimitiveEnd>();
		for (Connector connector : network.getAllConnectors()) {
			ends.addAll(getConnectorEnds(connector));
		}
		AnimationHiding.hideEnds(table, ends);
		monitor.worked(1); // 16
		
		applyFlipRule(table, false);
		monitor.worked(1); // 17
		
		// Minimize the tables up to bisimilarity.
		ColouringBisimulation.minimize(table, new SubProgressMonitor(monitor,1)); // 18
		
		// Reset names of the resulting tables.
		ColouringRefactoring.resetTableNames(table);
		
		// Set the animations steps.
		createAnimationSteps(table, false);
		monitor.worked(1); // 19
		
		// Set the animation definition.
		AnimationPrinter printer = new AnimationPrinter(names);
		String definition = printer.printAllTables(table);
		monitor.worked(1); // 20
		
		monitor.done();
		return definition;
		
	}
	
	
	/**
	 * Apply the flip rule to a table.
	 * @param table Table.
	 * @deprecated
	 */
	private void applyFlipRule(AnimationTable table, boolean flippedEnds) {
		// Flip the no-flow colours and minimize.
		ColouringConverter.flipNoFlowColours(table);
		ColouringConverter.applyFlipRule(table);
		// Flip back again?
		if (!flippedEnds) {
			ColouringConverter.flipNoFlowColours(table);			
		}		
	}
	
	/**
	 * Get a list of all ends inside of a connector.
	 * @param connector Connector.
	 * @return List of ends.
	 */
	private List<PrimitiveEnd> getConnectorEnds(Connector connector) {
		List<PrimitiveEnd> ends = new ArrayList<PrimitiveEnd>();
		for (Primitive primitive : connector.getPrimitives()) {
			ends.addAll(primitive.getAllEnds());
		}
		return ends;
	}
	
	/**
	 * Create default animation steps.
	 * @param table Animation table.
	 */
	public static void createAnimationSteps(AnimationTable table) {
		createAnimationSteps(table, false);
	}

	/*
	 * Create animation steps.
	 */
	private static void createAnimationSteps(AnimationTable table, boolean flippedEnds) {
		
		// Set the animations steps.
		for (AnimationTable current : table.getAllTables()) {
			for (int i=0; i<current.size(); i++) {
				Animation animation = current.get(i);
				animation.getSteps().clear();
				
				Animation srcAnim = new Animation();
				Animation snkAnim = new Animation();
				
				// Create new steps.
				ReceiveStep receive = new ReceiveStep();
				SendStep send = new SendStep();
				for (PrimitiveEnd end : animation.getColours().keySet()) {
					if (animation.isFlow(end)) {
						if ((end instanceof SourceEnd && !flippedEnds) ||
							(end instanceof SinkEnd && flippedEnds)) {
							receive.getActiveEnds().add(end);
						} else
						if ((end instanceof SinkEnd && !flippedEnds) ||
							(end instanceof SourceEnd && flippedEnds)) {
							send.getActiveEnds().add(end);
						}
					}
				}
				// Add the steps to the animation.
				if (!receive.getActiveEnds().isEmpty()) {
					DestroyStep destroy = new DestroyStep();
					destroy.getActiveEnds().addAll(receive.getActiveEnds());
					srcAnim.getSteps().add(receive);
					srcAnim.getSteps().add(destroy);
				}
				if (!send.getActiveEnds().isEmpty()) {
					CreateStep create = new CreateStep();
					create.getActiveEnds().addAll(send.getActiveEnds());
					snkAnim.getSteps().add(create);
					snkAnim.getSteps().add(send);
				}
				
				if (flippedEnds) {
					animation.append(srcAnim);
					animation.append(snkAnim);
				} else {
					animation.append(snkAnim);					
					animation.append(srcAnim);
				}
			}
		}

	}
	
	/* (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#getSemanticsKey()
	 */
	public String getSemanticsKey() {
		return KEY;
	}
	
	/**
	 * Get the animation definition for a custom primitve.
	 * @param custom Custom primitive.
	 * @return The animation definition.
	 */
	public static String getAnimationDefinition(CustomPrimitive custom) {
    	return PropertyHelper.getFirstValue(custom, KEY);
	}
	
	/**
	 * Set the animation definition for a custom primitive.
	 * @param custom The custom primitive.
	 * @param definition The animation definition.
	 */
	public static void setAnimationDefinition(CustomPrimitive custom, String definition) {
   		PropertyHelper.setOrRemoveHidden(custom, KEY, definition);
	}
	
}
