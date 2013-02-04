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
import org.eclipse.emf.common.notify.Notification;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;


/**
 * Modules are containers for connectors and components.
 * The components and connectors are not necessarily connected.
 * A module can also 'contain' a number of networks. The networks
 * in a module can be derived using {@link #createNetworks()}.
 * 
 * @see org.ect.reo.ReoFactory#createModule()
 * @model kind="class"
 * @generated
 */
public class Module extends Composite implements PropertyHolder {
	
	/**
	 * Alternative constructor.
	 * @param name Name of the module.
	 */
	public Module(String name) {
		this();
		setName(name);
	}

	
	/**
	 * Add a network to this module. Warning: this moves all connectors
	 * and components from their original container into this module.
	 * 
	 * @param network The network.
	 * @generated NOT
	 */
	public void addNetwork(Network network) {
		// Update the network first.
		network.update();
		getConnectors().addAll(network.getConnectors());
		getComponents().addAll(network.getComponents());
	}
	
	
	/**
	 * Create a list of networks from the contents of this module.
	 * This does not change the content of this module.
	 * 
	 * @return List of networks.
	 * @generated NOT
	 */
	public EList<Network> createNetworks() {
		
		EList<Network> networks = new BasicEList<Network>();
		List<Connector> connectors = new ArrayList<Connector>(getConnectors());
		List<Component> components = new ArrayList<Component>(getComponents());
		
		while (!connectors.isEmpty() || !components.isEmpty()) {
			// Compute network.
			Network network = new Network();
			if (!connectors.isEmpty()) network.getConnectors().add(connectors.get(0));
			else network.getComponents().add(components.get(0));
			network.update();
			networks.add(network);
			
			connectors.removeAll(network.getConnectors());
			components.removeAll(network.getComponents());
		}
		return networks;
	}
	
	
	/**
	 * Copy the content of a module or network to a module. This does not
	 * affect the source module / network or any of its content. Diagram information
	 * are not copied.
	 * 
	 * @param source The source module or network.
	 * @param target The target module.
	 * @return A reference to the used copier.
	 * @generated NOT
	 */
	public static Copier copyContent(Composite source, Module target) {
		
		// Create a new copier.
		Copier copier = new Copier();
		
		// Collect a list of all contained objects.
		Collection<EObject> content = new ArrayList<EObject>();
		content.addAll(source.getConnectors());
		content.addAll(source.getComponents());
				
		// Copy everything.
		copier.copyAll(content);
	    copier.copyReferences();
		
		for (EObject item : content) {
			if (item instanceof Connector) {
				Connector copy = (Connector) copier.get(item);
				target.getConnectors().add(copy);
			}
			if (item instanceof Component) {
				Component copy = (Component) copier.get(item);
				target.getComponents().add(copy);
			}
		}
		
		// This has to be done manually, because we were copying only the content:
		copier.put(source, target);
		
		return copier;
		
	}
	

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> components;

	/**
	 * The cached value of the '{@link #getReconfRules() <em>Reconf Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReconfRules()
	 * @generated
	 * @ordered
	 */
	protected EList<ReconfRule> reconfRules;

	/**
	 * The cached value of the '{@link #getActiveReconfRule() <em>Active Reconf Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveReconfRule()
	 * @generated
	 * @ordered
	 */
	protected ReconfRule activeReconfRule;

	/**
	 * @generated
	 */
	public Module() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.MODULE;
	}

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.Property}.
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getPropertyHolder_Properties()
	 * @model containment="true" keys="key"
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, ReoPackage.MODULE__PROPERTIES);
		}
		return properties;
	}


	/**
	 * @model type="org.ect.reo.Connector" containment="true"
	 * @generated
	 */
	public EList<Connector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectContainmentWithInverseEList<Connector>(Connector.class, this, ReoPackage.MODULE__CONNECTORS, ReoPackage.CONNECTOR__MODULE);
		}
		return connectors;
	}

	/**
	 * @see org.ect.reo.ReoPackage#getModule_Components()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Component> getComponents() {
		if (components == null) {
			components = new EObjectContainmentWithInverseEList<Component>(Component.class, this, ReoPackage.MODULE__COMPONENTS, ReoPackage.COMPONENT__MODULE);
		}
		return components;
	}

	/**
	 * Returns the value of the '<em><b>Reconf Rules</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.ReconfRule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reconf Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reconf Rules</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getModule_ReconfRules()
	 * @model containment="true"
	 * @generated
	 */
	public EList<ReconfRule> getReconfRules() {
		if (reconfRules == null) {
			reconfRules = new EObjectContainmentEList<ReconfRule>(ReconfRule.class, this, ReoPackage.MODULE__RECONF_RULES);
		}
		return reconfRules;
	}


	/**
	 * Returns the value of the '<em><b>Active Reconf Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Reconf Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Reconf Rule</em>' reference.
	 * @see #setActiveReconfRule(ReconfRule)
	 * @see org.ect.reo.ReoPackage#getModule_ActiveReconfRule()
	 * @model
	 * @generated
	 */
	public ReconfRule getActiveReconfRule() {
		if (activeReconfRule != null && activeReconfRule.eIsProxy()) {
			InternalEObject oldActiveReconfRule = (InternalEObject)activeReconfRule;
			activeReconfRule = (ReconfRule)eResolveProxy(oldActiveReconfRule);
			if (activeReconfRule != oldActiveReconfRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReoPackage.MODULE__ACTIVE_RECONF_RULE, oldActiveReconfRule, activeReconfRule));
			}
		}
		return activeReconfRule;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfRule basicGetActiveReconfRule() {
		return activeReconfRule;
	}


	/**
	 * Sets the value of the '{@link org.ect.reo.Module#getActiveReconfRule <em>Active Reconf Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Reconf Rule</em>' reference.
	 * @see #getActiveReconfRule()
	 * @generated
	 */
	public void setActiveReconfRule(ReconfRule newActiveReconfRule) {
		ReconfRule oldActiveReconfRule = activeReconfRule;
		activeReconfRule = newActiveReconfRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.MODULE__ACTIVE_RECONF_RULE, oldActiveReconfRule, activeReconfRule));
	}


	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.MODULE__CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnectors()).basicAdd(otherEnd, msgs);
			case ReoPackage.MODULE__COMPONENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getComponents()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.MODULE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ReoPackage.MODULE__CONNECTORS:
				return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
			case ReoPackage.MODULE__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
			case ReoPackage.MODULE__RECONF_RULES:
				return ((InternalEList<?>)getReconfRules()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.MODULE__PROPERTIES:
				return getProperties();
			case ReoPackage.MODULE__CONNECTORS:
				return getConnectors();
			case ReoPackage.MODULE__COMPONENTS:
				return getComponents();
			case ReoPackage.MODULE__RECONF_RULES:
				return getReconfRules();
			case ReoPackage.MODULE__ACTIVE_RECONF_RULE:
				if (resolve) return getActiveReconfRule();
				return basicGetActiveReconfRule();
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
			case ReoPackage.MODULE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case ReoPackage.MODULE__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ReoPackage.MODULE__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends Component>)newValue);
				return;
			case ReoPackage.MODULE__RECONF_RULES:
				getReconfRules().clear();
				getReconfRules().addAll((Collection<? extends ReconfRule>)newValue);
				return;
			case ReoPackage.MODULE__ACTIVE_RECONF_RULE:
				setActiveReconfRule((ReconfRule)newValue);
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
			case ReoPackage.MODULE__PROPERTIES:
				getProperties().clear();
				return;
			case ReoPackage.MODULE__CONNECTORS:
				getConnectors().clear();
				return;
			case ReoPackage.MODULE__COMPONENTS:
				getComponents().clear();
				return;
			case ReoPackage.MODULE__RECONF_RULES:
				getReconfRules().clear();
				return;
			case ReoPackage.MODULE__ACTIVE_RECONF_RULE:
				setActiveReconfRule((ReconfRule)null);
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
			case ReoPackage.MODULE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ReoPackage.MODULE__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case ReoPackage.MODULE__COMPONENTS:
				return components != null && !components.isEmpty();
			case ReoPackage.MODULE__RECONF_RULES:
				return reconfRules != null && !reconfRules.isEmpty();
			case ReoPackage.MODULE__ACTIVE_RECONF_RULE:
				return activeReconfRule != null;
		}
		return super.eIsSet(featureID);
	}


	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyHolder.class) {
			switch (derivedFeatureID) {
				case ReoPackage.MODULE__PROPERTIES: return ReoPackage.PROPERTY_HOLDER__PROPERTIES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}


	/**
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyHolder.class) {
			switch (baseFeatureID) {
				case ReoPackage.PROPERTY_HOLDER__PROPERTIES: return ReoPackage.MODULE__PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

}
