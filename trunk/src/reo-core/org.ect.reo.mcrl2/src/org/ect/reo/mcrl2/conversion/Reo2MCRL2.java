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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.PropertyHolder;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Specification;
import org.ect.reo.mcrl2.converters.BasicNodeConverter;
import org.ect.reo.mcrl2.converters.ElementConverter;
import org.ect.reo.mcrl2.datatypes.ColouredDataType;
import org.ect.reo.mcrl2.datatypes.GlobalDataType;
import org.ect.reo.mcrl2.datatypes.MCRL2SortUtil;
import org.ect.reo.semantics.ReoScope;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.semantics.TextualSemanticsProvider;
import org.ect.reo.util.PrimitiveEndNames;
import org.ect.reo.util.PropertyHelper;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class Reo2MCRL2 implements TextualSemanticsProvider {
	
	// Key used to store the specification in components.
	public static final String SEMANTICS_KEY = "mcrl2Def";
	
	// Key used to store sorts in components or connectors.
	public static final String DATA_KEY = "mcrl2Data";
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#getElementSemantics(org.ect.reo.Connectable, org.ect.reo.util.PrimitiveEndNames)
	 */
	public String getElementSemantics(Connectable element, PrimitiveEndNames names) {
		
		// Special case: user-defined components:
		if (element instanceof Component) {
			String spec = getSpecification((Component) element);
			if (spec!=null) return spec;
		}
		
		// Use a converter for computing the output.
		ElementConverter converter = ElementConverters.createConverter(names);
		converter.setScope(ReoScope.ELEMENT);
		
		// Create a global data type and initialize it.
		createGlobalDataType(converter, null);
		initGlobalDataType(converter);
		
		// Convert the element.
		Process process = converter.convert(element, ElementNames.getName(element, null, null));
		process.setInitial(true);
		
		// Format and print the computed specification.
		Specification spec = converter.getSpecification();
		simplifyNames(spec);
		spec.format();
		return spec.toString();
		
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#computeNetworkSemantics(org.ect.reo.Network, org.ect.reo.util.PrimitiveEndNames, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public String computeNetworkSemantics(Network network, final PrimitiveEndNames names, IProgressMonitor monitor) {
		
		// Check if the network is empty.
		if (network.getConnectors().isEmpty()) return "";
		
		// Create the element converter.
		ElementConverter converter = ElementConverters.createConverter(names);
		converter.setScope(ReoScope.NETWORK);
		
		// Create a global data type.
		createGlobalDataType(converter, null);
		
		// First convert all components.
		for (Component component : network.getComponents()) {
			converter.convert(component, ElementNames.getName(component, null, network));
		}
		
		// Now compute the definition of the global data type.
		initGlobalDataType(converter);
		
		// Now convert primitives and nodes.
		for (Primitive primitive : network.getAllPrimitives()) {
			if (!network.getComponents().contains(primitive)) {
				converter.convert(primitive, ElementNames.getName(primitive, null, network));
			}
		}
		for (Node node : network.getAllNodes()) {
			converter.convert(node, ElementNames.getName(node, null, network));
		}
		
		// Move the global data type to the end.
		//if (converter.getGlobalDataType()!=null) {
		//	spec.getSorts().move(spec.getSorts().size()-1, converter.getGlobalDataType());
		//}
		
		// Add the communications.
		PrimitiveEndCommunications.addCommunications(network, converter, monitor);
		
		// Set the initial process.
		Specification spec = converter.getSpecification();
		setInitialProcess(spec);
		spec.format();		
		return spec.toString();
		
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#computeConnectorSemantics(org.ect.reo.Connector, org.ect.reo.util.PrimitiveEndNames, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public String computeConnectorSemantics(Connector connector, final PrimitiveEndNames names, IProgressMonitor monitor) {
		
		// Check if the connector is empty.
		if (connector.getNodes().isEmpty() || connector.getPrimitives().isEmpty()) return "";
		
		// Create a normalized copy of the connector:
		connector = ReoTextualSemantics.createNormalizedConnector(connector, names);
		
		// Create the element converter.
		ElementConverter converter = ElementConverters.createConverter(names);
		converter.setScope(ReoScope.CONNECTOR);
		
		// Check if there is data involved.
		createGlobalDataType(converter, connector);
		initGlobalDataType(converter);
		
		// Convert primitives and nodes.
		for (Primitive primitive : connector.getPrimitives()) {
			converter.convert(primitive, ElementNames.getName(primitive, connector, null));
		}
		for (Node node : connector.getNodes()) {
			converter.convert(node, ElementNames.getName(node, connector, null));			
			
			// Rename ends at the boundary.
			for (PrimitiveEnd end : node.getAllEnds()) {
				if (!connector.getPrimitives().contains(end.getPrimitive())) {
					List<Atom> atoms = converter.getAtoms(node).get(end);
					for (Atom atom : atoms) {
						String name = atom.getName();
						if (name.endsWith( BasicNodeConverter.NODE_SUFFIX )) {
							atom.setName( name.substring(0, name.length() - BasicNodeConverter.NODE_SUFFIX.length()) );
						}
					}
				}
			}
		}
		
		// Add the communications.
		PrimitiveEndCommunications.addCommunications(connector, converter, monitor);
		
		// Set the initial process.
		Specification spec = converter.getSpecification();
		setInitialProcess(spec);
		spec.format();		
		return spec.toString();
		
	}
	
	/**
	 * Mark the last process in a specification as the (only) initial one.
	 */
	private void setInitialProcess(Specification spec) {
		// The last process will be the initial one.
		for (int i=0; i<spec.getProcesses().size(); i++) {
			spec.getProcesses().get(i).setInitial( i==spec.getProcesses().size()-1 );
		}
	}	
	
	/* (non-Javadoc)
	 * @see org.ect.reo.util.TextualSemanticsProvider#getSemanticsKey()
	 */
	public String getSemanticsKey() {
		return SEMANTICS_KEY;
	}
	
	/**
	 * Get the mCRL2 specification for a component.
	 * @param component The component.
	 * @return The specification.
	 */
	public static String getSpecification(CustomPrimitive element) {
    	return PropertyHelper.getFirstValue(element, SEMANTICS_KEY);
	}
	
	/**
	 * Set the mCRL2 specification for a component.
	 * @param element The component.
	 * @param specification The specification.
	 */
	public static void setSpecification(CustomPrimitive element, String specification) {
    	PropertyHelper.setOrRemoveHidden(element, SEMANTICS_KEY, specification);
	}
	
	
	/**
	 * Get the mCRL2 sort for an arbitrary element.
	 */
	public static String getSort(PropertyHolder holder) {
    	return PropertyHelper.getFirstValue(holder, DATA_KEY);
	}
	
	/**
	 * Set the mCRL2 sort to be used.
	 */
	public static void setSort(PropertyHolder holder, String sort) {
    	PropertyHelper.setOrRemoveHidden(holder, DATA_KEY, sort);
	}
	
	/*
	 * Removes all quotes at the end of all atoms.
	 * To be used only in single element cases.
	 */
	private void simplifyNames(Specification specification) {
		for (Atom atom : specification.getAtoms()) {
			String name = atom.getName();
			while (name.endsWith("'")) name = name.substring(0,name.length()-1);
			atom.setName(name);
		}
	}
	
	/*
	 * Create a global data type.
	 */
	private Sort createGlobalDataType(ElementConverter converter, PropertyHolder owner) {
		Sort data = null;
		if (Reo2MCRL2Preferences.withData()) {
			if (owner!=null) {
				data = MCRL2SortUtil.getSort(owner, "Data");
			}
			if (data==null) {
				data = new GlobalDataType("Data");
			}
			converter.getSpecification().getSorts().add(data);
		}
		if (Reo2MCRL2Preferences.withColour()) {
			ColouredDataType coloured = new ColouredDataType("Coloured", data);
			converter.getDataTypeManager().setGlobalDataType(coloured);
			converter.getSpecification().getSorts().add(coloured);
			return coloured;
		}
		converter.getDataTypeManager().setGlobalDataType(data);
		return data;
	}
	
	/*
	 * Initialize global data type.
	 */
	private void initGlobalDataType(ElementConverter converter) {

		Specification spec = converter.getSpecification();

		Sort global = converter.getDataTypeManager().getGlobalDataType();
		if (global instanceof GlobalDataType) {
			initGlobalDataType((GlobalDataType) global, spec);
		}
		
		else if (global instanceof ColouredDataType) {
			ColouredDataType coloured = (ColouredDataType) global;
			if (coloured.getRealDataType() instanceof GlobalDataType) {
				initGlobalDataType((GlobalDataType) coloured.getRealDataType(), spec);
			}
		}
	}
	
	private void initGlobalDataType(GlobalDataType global, Specification spec) {
		global.initialize(spec);
		if (global.getElements().isEmpty()) {
			global.getElements().add(new Element("x"));
		}		
	}
	
}
