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
package org.ect.reo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.ect.reo.animation.Animatable;
import org.ect.reo.animation.AnimationCompiler;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.Colourable;
import org.ect.reo.colouring.ColouringCache;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.colouring.ColouringFactory;
import org.ect.reo.colouring.ColouringPackage;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.StepwiseColouringEngine;

/**
 * A network is a collection of linked connectors and components. 
 * In contrast to modules, networks do not serve as a container of 
 * connectors or components. A module may consist of several networks
 * and networks can also be distributed over multiple modules.
 * 
 * @see org.ect.reo.ReoPackage#getNetwork()
 * @model kind="class"
 * @generated
 */
public class Network extends Composite implements Animatable {
	
	/**
	 * Default constructor.
	 * @generated NOT
	 */
	public Network() {
		super();
	}
	
	/**
	 * Alternative constructor.
	 * @param connector A connector of the network.
	 * @generated NOT
	 */
	public Network(Connector connector) {
		this();
		getConnectors().add(connector);
		update();
		sort();   // Sort only once in the beginning.
	}
	
	/**
	 * Alternative constructor.
	 * @param conmponent A component of the network.
	 * @generated NOT
	 */
	public Network(Component component) {
		this();
		getComponents().add(component);
		update();
		sort();   // Sort only once in the beginning.
	}
	
	/**
	 * Alternative constructor.
	 * @param module Module.
	 * @generated NOT
	 */
	public Network(Module module) {
		this();
		getComponents().addAll(module.getComponents());
		getConnectors().addAll(module.getConnectors());
		update();
		sort();   // Sort only once in the beginning.
	}
	
	
	/**
	 * Alternative constructor.
	 * @param content content of the network.
	 * @generated NOT
	 */
	public Network(Collection<?> content) {
		this();
		for (Object item : content) {
			if (item instanceof Connector) getConnectors().add((Connector) item); else
			if (item instanceof Component) getComponents().add((Component) item);
		}
		update();
		sort();   // Sort only once in the beginning.
	}

	
	/**
	 * Update this network. Adds all referenced components and
	 * connectors. It computes the transitive closure of the connectors
	 * and components.
	 * @model
	 * @generated NOT
	 */
	public void update() {
		
		// Check for internal components.
		for (int i=0; i<getComponents().size(); i++) {
			Connector connector = getComponents().get(i).getConnector();
			if (connector!=null) {
				if (!getConnectors().contains(connector)) getConnectors().add(connector);
				getComponents().remove(i--);
			}			
		}
		
		// Make sure that we only keep a list of top-level connectors.
		for (int i=0; i<getConnectors().size(); i++) {
			Connector current = getConnectors().get(i);
			while (current.getParent()!=null) current = current.getParent();
			if (getConnectors().get(i)!=current) {
				getConnectors().remove(i--);
				if (!getConnectors().contains(current)) {
					getConnectors().add(i++, current);
				}
			}
		}
		
		// Connectors and components to be added.
		Set<Connector> newConnectors = new LinkedHashSet<Connector>();
		Set<Component> newComponents = new LinkedHashSet<Component>();
		
		while (true) {
			
			for (Connector connector : getConnectors()) {
				
				// Check for foreign primitives
				for (Primitive primitive : connector.getForeignPrimitives()) {					
					Connector container = primitive.getConnector();
					
					// Does the primitive belong to a new top-level connector?
					if (container!=null && !container.isSubConnector() && !getConnectors().contains(container)) {
						newConnectors.add(container);
					} 
					// Or is it maybe an unknown component?
					else if (primitive instanceof Component && !((Component) primitive).isInternal() && !getComponents().contains(primitive)) {
						newComponents.add((Component) primitive);
					}
				}
				
				// Check for foreign nodes.
				for (Node node : connector.getForeignNodes()) {
					Connector container = node.getConnector();
					
					// Does the node belong to a new connector?
					if (container!=null && container.getParent()==null && !getConnectors().contains(container)) {
						newConnectors.add((Connector) node.eContainer());
					}
				}
			}
			
			for (Component component : getComponents()) {
				for (PrimitiveEnd end : component.getAllEnds()) {
					// Is the current end connected to an unknown connector?
					if (end.getNode()!=null && end.getNode().eContainer() instanceof Connector &&
						((Connector) end.getNode().eContainer()).getParent()==null &&
						!getConnectors().contains(end.getNode().eContainer())) {
						newConnectors.add((Connector) end.getNode().eContainer());
					}
				}
			}
			
			// If there are new connectors or components we add them now.
			if (!newConnectors.isEmpty() || !newComponents.isEmpty()) {
				
				getConnectors().addAll(newConnectors);
				getComponents().addAll(newComponents);
				
				// Clear the new connectors / components.
				newConnectors.clear();
				newComponents.clear();
			}
			// Otherwise we are done.
			else {
				break;
			}
		}
	}	
	
	/**
	 * Compute the animation table for this network.
	 */
	public AnimationTable getAnimationTable(IProgressMonitor monitor) {
		return AnimationCompiler.computeAnimations(this, getColouringEngine(), new ColouringCache(), monitor);
	}
	
	/**
	 * Get the animation table of this network.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		return getAnimationTable(new NullProgressMonitor());
	}
	
	/**
	 * Get the colouring table for this network.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public ColouringTable getColouringTable() {
		return getAnimationTable();
	}
	
	/**
	 * @see Colourable#usesFlipRule()
	 * @generated NOT
	 */
	public boolean usesFlipRule() {
		// We do not use the flip rule.
		return false;
	}
	
	/**
	 * Set the colouring engine.
	 * @generated NOT
	 */
	public void setColouringEngine(ColouringEngine engine) {
		// We do not deliver any notifications, because this property is transient.
		this.colouringEngine = engine;
	}
	
	/*
	 * Sort the connectors and components by their name.
	 * This should only be done in the beginning since
	 * the indexes of the connectors and components may
	 * change, which is not always desired.
	 */
	private void sort() {
		Comparator<Component> cmp1 = new Comparator<Component>() {
			public int compare(Component c1, Component c2) {
				if (c1.getName()!=null && c2.getName()!=null) {
					return c1.getName().compareTo(c2.getName());
				} else return 0;
			}
		};
		Comparator<Connector> cmp2 = new Comparator<Connector>() {
			public int compare(Connector c1, Connector c2) {
				if (c1.getName()!=null && c2.getName()!=null) {
					return c1.getName().compareTo(c2.getName());
				} else return 0;
			}
		};
		List<Component> components = new ArrayList<Component>(getComponents());
		List<Connector> connectors = new ArrayList<Connector>(getConnectors());
		Collections.sort(components, cmp1);
		Collections.sort(connectors, cmp2);
		getComponents().clear();
		getComponents().addAll(components);
		getConnectors().clear();
		getConnectors().addAll(connectors);
	}

	
	/**
	 * @see java.lang.Object#toString()
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("Network@" + Integer.toHexString(hashCode()));
		return result.toString() + ")";
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> components;

	/**
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * The default value of the '{@link #getColouringEngine() <em>Colouring Engine</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColouringEngine()
	 * @generated
	 * @ordered
	 */
	protected static final ColouringEngine COLOURING_ENGINE_EDEFAULT = (ColouringEngine)ColouringFactory.eINSTANCE.createFromString(ColouringPackage.eINSTANCE.getColouringEngine(), "");

	/**
	 * Colouring engine to be used. We need to override the default.
	 * @generated NOT
	 */
	protected ColouringEngine colouringEngine = new StepwiseColouringEngine();

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.NETWORK;
	}

	/**
	 * Get the list of connectors in this network. This does not include
	 * subconnectors. All connectors in this list are top-level connectors.
	 * 
	 * @see org.ect.reo.ReoPackage#getNetwork_Connectors()
	 * @model
	 * @generated
	 */
	public EList<Connector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectResolvingEList<Connector>(Connector.class, this, ReoPackage.NETWORK__CONNECTORS);
		}
		return connectors;
	}

	/**
	 * Returns the value of the '<em><b>Colouring Engine</b></em>' attribute.
	 * @return the value of the '<em>Colouring Engine</em>' attribute.
	 * @see #setColouringEngine(ColouringEngine)
	 * @see org.ect.reo.ReoPackage#getNetwork_ColouringEngine()
	 * @model default="" dataType="org.ect.reo.colouring.ColouringEngine" transient="true"
	 * @generated
	 */
	public ColouringEngine getColouringEngine() {
		return colouringEngine;
	}

	/**
	 * @see org.ect.reo.ReoPackage#getNetwork_Components()
	 * @model
	 * @generated
	 */
	public EList<Component> getComponents() {
		if (components == null) {
			components = new EObjectResolvingEList<Component>(Component.class, this, ReoPackage.NETWORK__COMPONENTS);
		}
		return components;
	}


	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.NETWORK__COMPONENTS:
				return getComponents();
			case ReoPackage.NETWORK__CONNECTORS:
				return getConnectors();
			case ReoPackage.NETWORK__COLOURING_ENGINE:
				return getColouringEngine();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.NETWORK__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends Component>)newValue);
				return;
			case ReoPackage.NETWORK__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ReoPackage.NETWORK__COLOURING_ENGINE:
				setColouringEngine((ColouringEngine)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ReoPackage.NETWORK__COMPONENTS:
				getComponents().clear();
				return;
			case ReoPackage.NETWORK__CONNECTORS:
				getConnectors().clear();
				return;
			case ReoPackage.NETWORK__COLOURING_ENGINE:
				setColouringEngine(COLOURING_ENGINE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ReoPackage.NETWORK__COMPONENTS:
				return components != null && !components.isEmpty();
			case ReoPackage.NETWORK__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case ReoPackage.NETWORK__COLOURING_ENGINE:
				return COLOURING_ENGINE_EDEFAULT == null ? colouringEngine != null : !COLOURING_ENGINE_EDEFAULT.equals(colouringEngine);
		}
		return super.eIsSet(featureID);
	}
	
}
