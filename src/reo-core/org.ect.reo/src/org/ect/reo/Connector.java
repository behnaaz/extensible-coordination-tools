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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
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
import org.ect.reo.util.ReoNames;



/**
 * Connectors are containers for {@link org.ect.reo.Node}s and {@link org.ect.reo.Primitive}s.
 * 
 * @see org.ect.reo.ReoFactory#createConnector()
 * @model kind="class"
 * @generated
 */
public class Connector extends MinimalEObjectImpl implements Nameable, Animatable, Reconfigurable, PropertyHolder {

	/**
	 * Convenience constructor.
	 * @param name Name of the connector.
	 * @generated NOT
	 */
	public Connector(String name) {
		this();
		setName(name);
	}	
	
	/**
	 * Find a node by its name. This does not check the 
	 * nodes in the subconnectors.
	 * @param name Name of the node.
	 * @return The first node that has this name.
	 */
	public Node findNode(String name) {
		for (Node node : getNodes()) {
			if ((name==null && node.getName()==null) ||
				(name!=null && name.equals(node.getName()))) return node;
		}
		return null;
	}
	
	/**
	 * Safely remove a primitive from this connector. This method
	 * ensures that the primitive is detached from its nodes before
	 * it is being removed.
	 * @generated NOT
	 */
	public void removePrimitive(Primitive primitive) {		
		// Already removed ?
		if (primitive==null || !getPrimitives().contains(primitive)) return;
		// Disconnect the primitive.
		primitive.disconnectEnds();
		// Now we can safely remove the primitive.
		getPrimitives().remove(primitive);
	}

	/**
	 * Safely remove a node from this connector. This method
	 * ensures that all coinciding primitives are also removed
	 * in a proper way.
	 * @generated NOT
	 */
	public void removeNode(Node node) {
		// Already removed ?
		if (node==null || !getNodes().contains(node)) return;
		// First remove the primitives.
		for (PrimitiveEnd end : node.getAllEnds()) {
			removePrimitive(end.getPrimitive());
		}
		// Now we can safely remove the node.
		getNodes().remove(node);
	}
	
	/**
	 * Get the source node in this connector.
	 * This returns an unmodifiable list.
	 * @generated NOT
	 */
	public EList<Node> getSourceNodes() {
		List<Node> result = new ArrayList<Node>();
		for (Node node : getNodes()) {
			if (node.isSourceNode()) result.add(node);
		}
		return new UnmodifiableEList<Node>(result.size(), result.toArray(new Node[result.size()]));
	}
	
	/**
	 * Get the sink nodes in this connector.
	 * This returns an unmodifiable list.
	 * @generated NOT
	 */
	public EList<Node> getSinkNodes() {
		List<Node> result = new ArrayList<Node>();
		for (Node node : getNodes()) {
			if (node.isSinkNode()) result.add(node);
		}
		return new UnmodifiableEList<Node>(result.size(), result.toArray(new Node[result.size()]));
	}
	
	/**
	 * Get the boundary nodes of this connector.
	 * This returns an unmodifiable list.
	 * @generated NOT
	 */
	public EList<Node> getBoundaryNodes() {
		List<Node> result = new ArrayList<Node>();
		result.addAll(getSourceNodes());
		result.addAll(getSinkNodes());
		return new UnmodifiableEList<Node>(result.size(), result.toArray(new Node[result.size()]));
	}

	/**
	 * Disconnect all boundary nodes from their components.
	 * If a boundary node is not connected to any component,
	 * the node is node touched.
	 * @generated NOT
	 */
	public void disconnectBoundaryNodes() {
		for (Node source : getSourceNodes()) {
			for (SinkEnd sink : source.getSinkEnds()) {
				sink.setNode(null);
			}
		}
		for (Node sink : getSinkNodes()) {
			for (SourceEnd source : sink.getSourceEnds()) {
				source.setNode(null);
			}
		}
	}
	
	/**
	 * Returns of all referenced nodes that do not belong 
	 * to this connector. The result is not modifiable and
	 * will not be cached.
	 * @generated NOT
	 */
	public EList<Node> getForeignNodes() {
		Set<Node> foreign = new LinkedHashSet<Node>();
		for (Primitive primitive : getPrimitives()) {
			for (PrimitiveEnd end : primitive.getAllEnds()) {
				if (end.getNode()!=null && !getNodes().contains(end.getNode())) {
					foreign.add(end.getNode());
				}
			}
		}
		return new UnmodifiableEList<Node>(foreign.size(), foreign.toArray());
	}
	
	/**
	 * Returns of all referenced primitives that do not belong 
	 * to this connector. The result is not modifiable and
	 * will not be cached.
	 * @generated NOT
	 */
	public EList<Primitive> getForeignPrimitives() {
		Set<Primitive> foreign = new LinkedHashSet<Primitive>();
		for (Node node : getNodes()) {
			for (PrimitiveEnd end : node.getAllEnds()) {
				if (end.getPrimitive()!=null && !getPrimitives().contains(end.getPrimitive())) {
					foreign.add(end.getPrimitive());
				}
			}
		}
		return new UnmodifiableEList<Primitive>(foreign.size(), foreign.toArray());
	}
	
	/**
	 * Compute a list of all subconnectors recursively contained in this connector.
	 * This returns an unmodifiable list which is not cached.
	 * @return List of subconnectors.
	 * @generated NOT
	 */
	public EList<Connector> getAllSubConnectors() {
		List<Connector> result = new ArrayList<Connector>();
		result.addAll(getSubConnectors());		
		for (int i=0; i<result.size(); i++) {
			for (Connector sub : result.get(i).getSubConnectors()) {
				if (!result.contains(sub)) {
					result.add(sub);
					i--;
					break;
				}
			}
		}
		return new UnmodifiableEList<Connector>(result.size(), result.toArray());
	}
	
	/**
	 * Get the level of this connector. The level is the number
	 * of parent connectors that this connector is contained in
	 * as a subconnector. Top-level connectors have are in level 0.
	 * @return Level of this connector.
	 * @generated NOT
	 */
	public int getLevel() {
		int level = 0;
		Connector connector = this;
		while (connector.getParent()!=null) {
			connector = connector.getParent();
			level++;
		}
		return level;
	}
	
	/**
	 * Check if this connector is a subconnector, i.e. if its
	 * parent is not <code>null</code>.
	 * @generated NOT
	 */
	public boolean isSubConnector() {
		return getParent()!=null;
	}
	
	/**
	 * Compute the animation table for this connector.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable(IProgressMonitor monitor) {
		return AnimationCompiler.computeAnimations(this, getColouringEngine(), new ColouringCache(), monitor);
	}
	
	/**
	 * Get the animation table for this connector.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		return getAnimationTable(new NullProgressMonitor());
	}
	
	/**
	 * Get the colouring table for this connector.
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
	
	/**
	 * @see java.lang.Object#toString()
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (getName()!=null) return ReoNames.CONNECTOR + " " + getName();
		return ReoNames.CONNECTOR + "@" + Integer.toHexString(hashCode()) + " (name: " + name + ")";
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public Connector() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.CONNECTOR;
	}

	/**
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReconfActions() <em>Reconf Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReconfActions()
	 * @generated
	 * @ordered
	 */
	protected EList<ReconfAction> reconfActions;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> nodes;

	/**
	 * @see #getPrimitives()
	 * @generated
	 * @ordered
	 */
	protected EList<Primitive> primitives;
	
	/**
	 * @see #getSubConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> subConnectors;


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
	 * Getter for the nodes. Returns a list that can be modified.
	 * @see org.ect.reo.Node#getConnector
	 * @model type="org.ect.reo.Node" opposite="connector" containment="true"
	 * @generated
	 */
	public EList<Node> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList<Node>(Node.class, this, ReoPackage.CONNECTOR__NODES, ReoPackage.NODE__CONNECTOR);
		}
		return nodes;
	}

	/**
	 * Getter for the primitives. Returns a list that can be modified.
	 * @see org.ect.reo.Primitive#getConnector
	 * @model type="org.ect.reo.Primitive" opposite="connector" containment="true"
	 * @generated
	 */
	public EList<Primitive> getPrimitives() {
		if (primitives == null) {
			primitives = new EObjectContainmentEList<Primitive>(Primitive.class, this, ReoPackage.CONNECTOR__PRIMITIVES);
		}
		return primitives;
	}

	/**
	 * Returns a list of subconnectors of this connector.
	 * The list contents are of type {@link org.ect.reo.Connector}.
	 * @see org.ect.reo.ReoPackage#getConnector_SubConnectors()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Connector> getSubConnectors() {
		if (subConnectors == null) {
			subConnectors = new EObjectContainmentWithInverseEList<Connector>(Connector.class, this, ReoPackage.CONNECTOR__SUB_CONNECTORS, ReoPackage.CONNECTOR__PARENT);
		}
		return subConnectors;
	}


	/**
	 * Get the module where this connector is contained in.
	 * @see #setModule(Module)
	 * @see org.ect.reo.ReoPackage#getConnector_Module()
	 * @see org.ect.reo.Module#getConnectors
	 * @model opposite="connectors" transient="false"
	 * @generated
	 */
	public Module getModule() {
		if (eContainerFeatureID() != ReoPackage.CONNECTOR__MODULE) return null;
		return (Module)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetModule(Module newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, ReoPackage.CONNECTOR__MODULE, msgs);
		return msgs;
	}

	/**
	 * Set the module where this connector is contained in.
	 * @see #getModule()
	 * @generated
	 */
	public void setModule(Module newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != ReoPackage.CONNECTOR__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, ReoPackage.MODULE__CONNECTORS, Module.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.CONNECTOR__MODULE, newModule, newModule));
	}

	/**
	 * Get the parent connector that contains this connector.
	 * Returns <code>null</null> if this is a top-level connector.
	 * 
	 * @return the parent connector.
	 * @see #setParent(Connector)
	 * @see org.ect.reo.ReoPackage#getConnector_Parent()
	 * @see org.ect.reo.Connector#getSubConnectors
	 * @model opposite="subConnectors" transient="false"
	 * @generated
	 */
	public Connector getParent() {
		if (eContainerFeatureID() != ReoPackage.CONNECTOR__PARENT) return null;
		return (Connector)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetParent(Connector newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, ReoPackage.CONNECTOR__PARENT, msgs);
		return msgs;
	}

	/**
	 * Sets the parent connector of this connector. This effectively moves
	 * this connector into another connector.
	 * @param value the new parent connector.
	 * @see #getParent()
	 * @generated
	 */
	public void setParent(Connector newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ReoPackage.CONNECTOR__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ReoPackage.CONNECTOR__SUB_CONNECTORS, Connector.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.CONNECTOR__PARENT, newParent, newParent));
	}

	/**
	 * Returns the value of the '<em><b>Colouring Engine</b></em>' attribute.
	 * @return the value of the '<em>Colouring Engine</em>' attribute.
	 * @see #setColouringEngine(ColouringEngine)
	 * @see org.ect.reo.ReoPackage#getConnector_ColouringEngine()
	 * @model default="" dataType="org.ect.reo.colouring.ColouringEngine" transient="true"
	 * @generated
	 */
	public ColouringEngine getColouringEngine() {
		return colouringEngine;
	}

	/**
	 * Getter for the name of the connector.
	 * @see #setName(String)
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the connector.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.CONNECTOR__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.ReconfAction}.
	 * @return the value of the '<em>Reconf Actions</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getReconfigurable_ReconfActions()
	 * @model containment="true"
	 * @generated
	 */
	public EList<ReconfAction> getReconfActions() {
		if (reconfActions == null) {
			reconfActions = new EObjectContainmentEList<ReconfAction>(ReconfAction.class, this, ReoPackage.CONNECTOR__RECONF_ACTIONS);
		}
		return reconfActions;
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
			properties = new EObjectContainmentEList<Property>(Property.class, this, ReoPackage.CONNECTOR__PROPERTIES);
		}
		return properties;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.CONNECTOR__NODES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodes()).basicAdd(otherEnd, msgs);
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubConnectors()).basicAdd(otherEnd, msgs);
			case ReoPackage.CONNECTOR__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((Module)otherEnd, msgs);
			case ReoPackage.CONNECTOR__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((Connector)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
				return ((InternalEList<?>)getReconfActions()).basicRemove(otherEnd, msgs);
			case ReoPackage.CONNECTOR__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ReoPackage.CONNECTOR__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case ReoPackage.CONNECTOR__PRIMITIVES:
				return ((InternalEList<?>)getPrimitives()).basicRemove(otherEnd, msgs);
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				return ((InternalEList<?>)getSubConnectors()).basicRemove(otherEnd, msgs);
			case ReoPackage.CONNECTOR__MODULE:
				return basicSetModule(null, msgs);
			case ReoPackage.CONNECTOR__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ReoPackage.CONNECTOR__MODULE:
				return eInternalContainer().eInverseRemove(this, ReoPackage.MODULE__CONNECTORS, Module.class, msgs);
			case ReoPackage.CONNECTOR__PARENT:
				return eInternalContainer().eInverseRemove(this, ReoPackage.CONNECTOR__SUB_CONNECTORS, Connector.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.CONNECTOR__NAME:
				return getName();
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
				return getReconfActions();
			case ReoPackage.CONNECTOR__PROPERTIES:
				return getProperties();
			case ReoPackage.CONNECTOR__NODES:
				return getNodes();
			case ReoPackage.CONNECTOR__PRIMITIVES:
				return getPrimitives();
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				return getSubConnectors();
			case ReoPackage.CONNECTOR__MODULE:
				return getModule();
			case ReoPackage.CONNECTOR__PARENT:
				return getParent();
			case ReoPackage.CONNECTOR__COLOURING_ENGINE:
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
			case ReoPackage.CONNECTOR__NAME:
				setName((String)newValue);
				return;
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
				getReconfActions().clear();
				getReconfActions().addAll((Collection<? extends ReconfAction>)newValue);
				return;
			case ReoPackage.CONNECTOR__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case ReoPackage.CONNECTOR__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Node>)newValue);
				return;
			case ReoPackage.CONNECTOR__PRIMITIVES:
				getPrimitives().clear();
				getPrimitives().addAll((Collection<? extends Primitive>)newValue);
				return;
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				getSubConnectors().clear();
				getSubConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ReoPackage.CONNECTOR__MODULE:
				setModule((Module)newValue);
				return;
			case ReoPackage.CONNECTOR__PARENT:
				setParent((Connector)newValue);
				return;
			case ReoPackage.CONNECTOR__COLOURING_ENGINE:
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
			case ReoPackage.CONNECTOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
				getReconfActions().clear();
				return;
			case ReoPackage.CONNECTOR__PROPERTIES:
				getProperties().clear();
				return;
			case ReoPackage.CONNECTOR__NODES:
				getNodes().clear();
				return;
			case ReoPackage.CONNECTOR__PRIMITIVES:
				getPrimitives().clear();
				return;
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				getSubConnectors().clear();
				return;
			case ReoPackage.CONNECTOR__MODULE:
				setModule((Module)null);
				return;
			case ReoPackage.CONNECTOR__PARENT:
				setParent((Connector)null);
				return;
			case ReoPackage.CONNECTOR__COLOURING_ENGINE:
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
			case ReoPackage.CONNECTOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
				return reconfActions != null && !reconfActions.isEmpty();
			case ReoPackage.CONNECTOR__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ReoPackage.CONNECTOR__NODES:
				return nodes != null && !nodes.isEmpty();
			case ReoPackage.CONNECTOR__PRIMITIVES:
				return primitives != null && !primitives.isEmpty();
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				return subConnectors != null && !subConnectors.isEmpty();
			case ReoPackage.CONNECTOR__MODULE:
				return getModule() != null;
			case ReoPackage.CONNECTOR__PARENT:
				return getParent() != null;
			case ReoPackage.CONNECTOR__COLOURING_ENGINE:
				return COLOURING_ENGINE_EDEFAULT == null ? colouringEngine != null : !COLOURING_ENGINE_EDEFAULT.equals(colouringEngine);
		}
		return super.eIsSet(featureID);
	}


	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Colourable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Animatable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.CONNECTOR__RECONF_ACTIONS: return ReoPackage.RECONFIGURABLE__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (derivedFeatureID) {
				case ReoPackage.CONNECTOR__PROPERTIES: return ReoPackage.PROPERTY_HOLDER__PROPERTIES;
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
		if (baseClass == Colourable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Animatable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (baseFeatureID) {
				case ReoPackage.RECONFIGURABLE__RECONF_ACTIONS: return ReoPackage.CONNECTOR__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (baseFeatureID) {
				case ReoPackage.PROPERTY_HOLDER__PROPERTIES: return ReoPackage.CONNECTOR__PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

}
