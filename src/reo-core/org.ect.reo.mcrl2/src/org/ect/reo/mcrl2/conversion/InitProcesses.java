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
package org.ect.reo.mcrl2.conversion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.converters.ElementConverter;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.util.AbstractTraversalWorker;
import org.ect.reo.util.PropertyHelper;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class InitProcesses extends AbstractTraversalWorker {
	
	public static final String INIT_NAME = "Init";
	
	private Connector connector;
	private Connectable start;
	private ElementConverter converter;
	private int index = 0;
	
	// Last process:
	private Process last;
	
	private boolean first;
	
	/**
	 * Private constructor.
	 * @param converter Element converter.
	 */
	private InitProcesses(ElementConverter converter) {
		this.converter = converter;
		this.first = true;
	}
	
	/**
	 * Public constructor.
	 * @param network Network.
	 * @param converter Element converter.
	 */
	public InitProcesses(Network network, ElementConverter converter) {
		this(converter);
		
		// Compute the start.
		if (!network.getComponents().isEmpty()) {
			start = network.getComponents().get(0);
		} else for (Connector connector : network.getConnectors()) {
			if (!connector.getNodes().isEmpty()) {
				start = network.getConnectors().get(0).getNodes().get(0);
			}
		}
	}
	
	/**
	 * Public constructor.
	 * @param connector Connector.
	 * @param converter Element converter.
	 */
	public InitProcesses(Connector connector, ElementConverter converter) {
		this(converter);
		this.connector = connector;
		
		// Compute the start.
		for (Node node : connector.getNodes()) {
			if (node.isSourceNode() && !node.isSingleton()) {
				start = node; break;
			}
		}
		if (start==null && !connector.getNodes().isEmpty()) {
			start = connector.getNodes().get(0);
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.AbstractTraversalWorker#visitElement(org.ect.reo.Connectable, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected boolean visitElement(Connectable element, IProgressMonitor monitor) {
		
		// Skip the first element:
		if (element==start) return true;
		
		// Determine the target atoms for the synchronization.
		List<PrimitiveEnd> ends = new ArrayList<PrimitiveEnd>();
		List<PrimitiveEnd> hidden = new ArrayList<PrimitiveEnd>();
		
		for (PrimitiveEnd end : element.getAllEnds()) {
			if (getVisitedEnds().contains(end)) ends.add(end);
			if (isInternal(end)) hidden.add(end);
		}
		PrimitiveEndAtoms synchronizations = getSynchronizations(ends);
		
		// List of connected primitives.
		List<Connectable> elements = new ArrayList<Connectable>();
		if (first) elements.add(start);
		elements.add(element);
		
		if (!synchronizations.isEmpty() || !elements.isEmpty()) {
			last = converter.synchronize(initName(element), last, synchronizations, elements, hidden);
		}
		
		first = false;
		return true;
		
	}
	
	
	public PrimitiveEndAtoms getSynchronizations(List<PrimitiveEnd> ends) {
		
		PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
		
		for (PrimitiveEnd end : ends) {	
			List<Atom> result = new ArrayList<Atom>();
			PrimitiveEndAtoms existing = converter.getAtoms(end.getNode());
			for (int i=0; i<existing.get(end).size(); i++) {
				Atom atom = new Atom(converter.getSyncName(end, i), converter.getDataTypeManager().getGlobalDataType());
				converter.getSpecification().getAtoms().add(atom);
				result.add(atom);
			}
			atoms.put(end, result);
		}
		return atoms;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#getStart()
	 */
	public Connectable getStart() {
		return start;
	}
	
	private String initName(Connectable element) {
		return INIT_NAME + index++;
	}
	
	private boolean isInternal(PrimitiveEnd end) {
		boolean hasHideProperty = (null!=PropertyHelper.getFirstValue(end.getNode(), Node.HIDE_PROPERTY_KEY));
		if (hasHideProperty) {
			return end.getNode().isHide();
		}
		if (connector==null) {
			return (!end.isComponentEnd());
		}
		return connector.getPrimitives().contains(end.getPrimitive()) &&
			   connector.getNodes().contains(end.getNode());
	}
	
	public void reset() {
		index = 0;
		last = null;
		first = true;
	}
	
}
