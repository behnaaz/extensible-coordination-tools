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

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.reo.animation.Animatable;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationStep;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.CreateStep;
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.channels.Channel;
import org.ect.reo.colouring.Colourable;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.util.PropertyHelper;



/**
 * Nodes are the connectable elements that form the counterpart of {@link org.ect.reo.Primitive}s.
 * 
 * @see org.ect.reo.ReoFactory#createNode()
 * @model kind="class"
 * @generated
 */
public class Node extends MinimalEObjectImpl implements Connectable, Nameable, Animatable, Delayable, Reconfigurable, PropertyHolder {
	
	/**
	 * @generated NOT
	 */
	public final static String HIDE_PROPERTY_KEY = "hide";
	
	/**
	 * Constructor that sets the name of the node. 
	 * @generated NOT
	 */
	public Node(String name) {
		this();
		setName(name);
	}
	
	/**
	 * Check if this node is a source node.
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated NOT
	 */
	public boolean isSourceNode() {
		for (SinkEnd end : getSinkEnds()) {
			if (!isForeignEnd(end)) return false;
		}
		return true;
	}
	
	/**
	 * Check if this node is a sink node.
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated NOT
	 */
	public boolean isSinkNode() {
		for (SourceEnd end : getSourceEnds()) {
			if (!isForeignEnd(end)) return false;
		}
		return true;
	}
	
	/**
	 * Returns <code>true</code>, if this node is neither a source nor a sink node is.
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated NOT
	 */
	public boolean isMixedNode() {
		return (!isSinkNode() && !isSourceNode());
	}
	
	/**
	 * Returns <code>true</code>, if there are no primitives attached to this node.
	 * @generated NOT
	 */
	public boolean isSingleton() {
		return getSinkEnds().isEmpty() && getSourceEnds().isEmpty();
	}

	
	/*
	 * Check if an end of this node belongs to a foreign primitive.
	 */
	private boolean isForeignEnd(PrimitiveEnd end) {
		Primitive primitive = end.getPrimitive();
		if (primitive instanceof Component) {
			if (primitive.getConnector()==getConnector()) return false;
		}
		else if (primitive instanceof Channel) {
			Channel channel = (Channel) primitive;
			Node opposite = channel.getOppositeEnd(end).getNode();
			if (opposite!=null && getConnector().getNodes().contains(opposite)) return false;
		}
		return true;
	}
	
	/**
	 * @see #setHide(boolean)
	 * @see org.ect.reo.ReoPackage#getNode_Hide()
	 * @model transient="true" volatile="true"
	 * @generated NOT
	 */
	public boolean isHide() {
		String value = PropertyHelper.getFirstValue(this, HIDE_PROPERTY_KEY);
		boolean def = isMixedNode();
		if (value==null) {
			return def;
		}
		try {
			return Boolean.parseBoolean(value);
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * @see #isHide()
	 * @generated NOT
	 */
	public void setHide(boolean hide) {
		if (hide!=isHide()) {
			boolean def = isMixedNode();
			if (hide==def) {
				PropertyHelper.removeAll(this, HIDE_PROPERTY_KEY);
			} else {
				PropertyHelper.setOrAdd(this, HIDE_PROPERTY_KEY, String.valueOf(hide));
			}
		}
	}
	
	/**
	 * Get the animation table for this node.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		switch (getType().getValue()) {
			case NodeType.REPLICATE_VALUE : return computeReplicateTable();
			case NodeType.ROUTE_VALUE : return computeRouteTable();
			case NodeType.JOIN_VALUE : return computeJoinTable();
			default	: return null;
		}
	}
	
	/**
	 * Semantics of a Replicate-Node (XOR-AND). This is the default
	 * behavior of a Reo node.
	 * @generated NOT
	 */
	private AnimationTable computeReplicateTable() {
		
		AnimationTable table = new AnimationTable();
		AnimationStep step;
		Animation animation;
		
		// Flow from a sink end:
		for (SinkEnd sinkEnd : getSinkEnds()) {
			animation = new Animation();
			animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
			animation.setColours(getSourceEnds(), FlowColour.FLOW_LITERAL);
			animation.setColour(sinkEnd, FlowColour.FLOW_LITERAL);
			animation.setNextAnimationTable(table);
			if (!getSourceEnds().isEmpty()) {
				step = new ReplicateStep();
			} else {
				step = new DestroyStep();
			}
			step.getActiveEnds().add(sinkEnd);				
			animation.getSteps().add(step);
			table.add(animation);
		}
		
		// Spontaneous creation of tokens:
		if (getSinkEnds().isEmpty() && !getSourceEnds().isEmpty()) {
			animation = new Animation();
			animation.setColours(getSourceEnds(), FlowColour.FLOW_LITERAL);
			animation.setNextAnimationTable(table);
			step = new CreateStep();
			step.getActiveEnds().addAll(getSourceEnds());
			animation.getSteps().add(step);
			table.add(animation);
		}
		
		// No flow, because one source end cannot accept data.
		if (!getSourceEnds().isEmpty()) {
			for (SourceEnd sourceEnd : getSourceEnds()) {
				animation = new Animation();
				animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
				animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
				animation.setColour(sourceEnd, FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
				animation.setNextAnimationTable(table);
				table.add(animation);
			}
		} else {
			animation = new Animation();
			animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
			animation.setNextAnimationTable(table);
			table.add(animation);			
		}
		
		// No flow, because all of the sink ends cannot provide data:
		animation = new Animation();
		animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		animation.setNextAnimationTable(table);
		table.add(animation);
		
		return table;
		
	}
	
	/**
	 * Semantics of a Route-Node (XOR-XOR). It routes exactly one input to 
	 * one of the output ends, without any replication or joining.
	 * @generated NOT
	 */
	private AnimationTable computeRouteTable() {
		
		AnimationTable table = new AnimationTable();
		ReplicateStep step;
		Animation animation;
		
		// Flow
		for (SinkEnd sinkEnd : getSinkEnds()) {			
			for (SourceEnd sourceEnd : getSourceEnds()) {
				
				animation = new Animation();
				animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
				animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
				animation.setColour(sourceEnd, FlowColour.FLOW_LITERAL);
				animation.setColour(sinkEnd, FlowColour.FLOW_LITERAL);
				animation.setNextAnimationTable(table);
				
				step = new ReplicateStep();
				step.getActiveEnds().add(sinkEnd);
				animation.getSteps().add(step);
				
				table.add(animation);
			}
		}
				
		// No flow, because none of the inputs has data available.
		animation = new Animation();
		animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		animation.setNextAnimationTable(table);
		table.add(animation);
		
		// No flow, because none of the outputs can accept any data.
		animation = new Animation();
		animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		animation.setNextAnimationTable(table);
		table.add(animation);
		
		return table;
		
	}
	
	/**
	 * Semantics of a Join-Node (AND-XOR). It requires token on all
	 * input ends and produces a token on exactly one output end.
	 * @generated NOT
	 */
	private AnimationTable computeJoinTable() {
		
		AnimationTable table = new AnimationTable();
		Animation animation;
		
		if (!getSinkEnds().isEmpty() && !getSourceEnds().isEmpty()) {
			
			// Flow
			for (SourceEnd sourceEnd : getSourceEnds()) {
			
				animation = new Animation();
				animation.setColours(getSinkEnds(), FlowColour.FLOW_LITERAL);
				animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
				animation.setColour(sourceEnd, FlowColour.FLOW_LITERAL);
				animation.setNextAnimationTable(table);
			
				if (getSinkEnds().size()==1) {
			
					ReplicateStep replicate = new ReplicateStep();
					replicate.getActiveEnds().add(getSinkEnds().get(0));
				
					animation.getSteps().add(replicate);
			
				} else {
				
					DestroyStep destroy = new DestroyStep();
					destroy.getActiveEnds().addAll(getSinkEnds());
					CreateStep create = new CreateStep();
					create.getActiveEnds().add(sourceEnd);
			
					animation.getSteps().add(destroy);
					animation.getSteps().add(create);
				}
				table.add(animation);
			}
		}
		
		// No flow, because one sink end has no data available.
		for (SinkEnd sinkEnd : getSinkEnds()) {
			animation = new Animation();
			animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
			animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
			animation.setColour(sinkEnd, FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			animation.setNextAnimationTable(table);
			
			table.add(animation);
		}
		
		// No flow, because none of the outputs can accept any data.
		animation = new Animation();
		animation.setColours(getSinkEnds(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		animation.setColours(getSourceEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		animation.setNextAnimationTable(table);
		table.add(animation);
		
		return table;
		
		
	}
	
	/**
	 * Getter method that returns a list of all ends that are 
	 * attached to this node. This is a derived value and can
	 * therefore not be modified.
	 * @model kind="operation" type="org.ect.reo.PrimitiveEnd"
	 * @generated NOT
	 */
	public EList<PrimitiveEnd> getAllEnds() {
		List<PrimitiveEnd> allEnds = new Vector<PrimitiveEnd>();
		allEnds.addAll(getSourceEnds());
		allEnds.addAll(getSinkEnds());
		return new UnmodifiableEList<PrimitiveEnd>(allEnds.size(), allEnds.toArray());
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
	 * Get the colouring table for this network.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public ColouringTable getColouringTable() {
		return getAnimationTable();
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return (name!=null) ? "Node " + name + " (type: " + type + ")" :
		"Node@" + Integer.toHexString(hashCode()) + " (type: " + type + ")";
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public Node() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.NODE;
	}

	/**
	 * Returns the value of the '<em><b>Arrival Rate</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * @return the value of the '<em>Arrival Rate</em>' attribute list.
	 * @see org.ect.reo.ReoPackage#getDelayable_ArrivalRate()
	 * @model
	 * @generated
	 */
	public EList<Double> getArrivalRate() {
		if (arrivalRate == null) {
			arrivalRate = new EDataTypeEList<Double>(Double.class, this, ReoPackage.NODE__ARRIVAL_RATE);
		}
		return arrivalRate;
	}

	/**
	 * Returns the value of the '<em><b>Processing Delay</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processing Delay</em>' attribute list.
	 * @see org.ect.reo.ReoPackage#getDelayable_ProcessingDelay()
	 * @model
	 * @generated
	 */
	public EList<Double> getProcessingDelay() {
		if (processingDelay == null) {
			processingDelay = new EDataTypeEList<Double>(Double.class, this, ReoPackage.NODE__PROCESSING_DELAY);
		}
		return processingDelay;
	}

	/**
	 * Returns the value of the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.ReconfAction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reconf Actions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reconf Actions</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getReconfigurable_ReconfActions()
	 * @model containment="true"
	 * @generated
	 */
	public EList<ReconfAction> getReconfActions() {
		if (reconfActions == null) {
			reconfActions = new EObjectContainmentEList<ReconfAction>(ReconfAction.class, this, ReoPackage.NODE__RECONF_ACTIONS);
		}
		return reconfActions;
	}


	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getPropertyHolder_Properties()
	 * @model containment="true" keys="key"
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, ReoPackage.NODE__PROPERTIES);
		}
		return properties;
	}


	/**
	 * @see #getName()
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
	 * The cached value of the '{@link #getArrivalRate() <em>Arrival Rate</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrivalRate()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> arrivalRate;

	/**
	 * The cached value of the '{@link #getProcessingDelay() <em>Processing Delay</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessingDelay()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> processingDelay;

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
	 * @see #getSourceEnds()
	 * @generated
	 * @ordered
	 */
	protected EList<SourceEnd> sourceEnds;

	/**
	 * @see #getSinkEnds()
	 * @generated
	 * @ordered
	 */
	protected EList<SinkEnd> sinkEnds;
	

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final NodeType TYPE_EDEFAULT = NodeType.REPLICATE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected NodeType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHide() <em>Hide</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHide()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDE_EDEFAULT = false;

	/**
	 * Getter for the name of the node.
	 * @see #setName(String)
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the node.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.NODE__NAME, oldName, name));
	}

	/**
	 * Getter method for the connector that contains this node.
	 * @see #setConnector(Connector)
	 * @see org.ect.reo.Connector#getNodes
	 * @model opposite="nodes"
	 * @generated
	 */
	public Connector getConnector() {
		if (eContainerFeatureID() != ReoPackage.NODE__CONNECTOR) return null;
		return (Connector)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetConnector(Connector newConnector, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newConnector, ReoPackage.NODE__CONNECTOR, msgs);
		return msgs;
	}

	/**
	 * Setter for the connector that contains will contain the node.
	 * @see #getConnector()
	 * @generated
	 */
	public void setConnector(Connector newConnector) {
		if (newConnector != eInternalContainer() || (eContainerFeatureID() != ReoPackage.NODE__CONNECTOR && newConnector != null)) {
			if (EcoreUtil.isAncestor(this, newConnector))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConnector != null)
				msgs = ((InternalEObject)newConnector).eInverseAdd(this, ReoPackage.CONNECTOR__NODES, Connector.class, msgs);
			msgs = basicSetConnector(newConnector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.NODE__CONNECTOR, newConnector, newConnector));
	}

	/**
	 * Getter for the list of source ends.
	 * @see org.ect.reo.SourceEnd#getNode
	 * @model type="org.ect.reo.SourceEnd" opposite="node"
	 * @generated
	 */
	public EList<SourceEnd> getSourceEnds() {
		if (sourceEnds == null) {
			sourceEnds = new EObjectWithInverseResolvingEList<SourceEnd>(SourceEnd.class, this, ReoPackage.NODE__SOURCE_ENDS, ReoPackage.SOURCE_END__NODE);
		}
		return sourceEnds;
	}

	/**
	 * Getter for the list of sink ends.
	 * @see org.ect.reo.SinkEnd#getNode
	 * @model type="org.ect.reo.SinkEnd" opposite="node"
	 * @generated
	 */
	public EList<SinkEnd> getSinkEnds() {
		if (sinkEnds == null) {
			sinkEnds = new EObjectWithInverseResolvingEList<SinkEnd>(SinkEnd.class, this, ReoPackage.NODE__SINK_ENDS, ReoPackage.SINK_END__NODE);
		}
		return sinkEnds;
	}

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.ect.reo.NodeType}.
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.ect.reo.NodeType
	 * @see #setType(NodeType)
	 * @see org.ect.reo.ReoPackage#getNode_Type()
	 * @model
	 * @generated
	 */
	public NodeType getType() {
		return type;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.Node#getType <em>Type</em>}' attribute.
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.ect.reo.NodeType
	 * @see #getType()
	 * @generated
	 */
	public void setType(NodeType newType) {
		NodeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.NODE__TYPE, oldType, type));
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.NODE__CONNECTOR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetConnector((Connector)otherEnd, msgs);
			case ReoPackage.NODE__SOURCE_ENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSourceEnds()).basicAdd(otherEnd, msgs);
			case ReoPackage.NODE__SINK_ENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSinkEnds()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.NODE__RECONF_ACTIONS:
				return ((InternalEList<?>)getReconfActions()).basicRemove(otherEnd, msgs);
			case ReoPackage.NODE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ReoPackage.NODE__CONNECTOR:
				return basicSetConnector(null, msgs);
			case ReoPackage.NODE__SOURCE_ENDS:
				return ((InternalEList<?>)getSourceEnds()).basicRemove(otherEnd, msgs);
			case ReoPackage.NODE__SINK_ENDS:
				return ((InternalEList<?>)getSinkEnds()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ReoPackage.NODE__CONNECTOR:
				return eInternalContainer().eInverseRemove(this, ReoPackage.CONNECTOR__NODES, Connector.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.NODE__NAME:
				return getName();
			case ReoPackage.NODE__ARRIVAL_RATE:
				return getArrivalRate();
			case ReoPackage.NODE__PROCESSING_DELAY:
				return getProcessingDelay();
			case ReoPackage.NODE__RECONF_ACTIONS:
				return getReconfActions();
			case ReoPackage.NODE__PROPERTIES:
				return getProperties();
			case ReoPackage.NODE__CONNECTOR:
				return getConnector();
			case ReoPackage.NODE__SOURCE_ENDS:
				return getSourceEnds();
			case ReoPackage.NODE__SINK_ENDS:
				return getSinkEnds();
			case ReoPackage.NODE__TYPE:
				return getType();
			case ReoPackage.NODE__HIDE:
				return isHide();
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
			case ReoPackage.NODE__NAME:
				setName((String)newValue);
				return;
			case ReoPackage.NODE__ARRIVAL_RATE:
				getArrivalRate().clear();
				getArrivalRate().addAll((Collection<? extends Double>)newValue);
				return;
			case ReoPackage.NODE__PROCESSING_DELAY:
				getProcessingDelay().clear();
				getProcessingDelay().addAll((Collection<? extends Double>)newValue);
				return;
			case ReoPackage.NODE__RECONF_ACTIONS:
				getReconfActions().clear();
				getReconfActions().addAll((Collection<? extends ReconfAction>)newValue);
				return;
			case ReoPackage.NODE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case ReoPackage.NODE__CONNECTOR:
				setConnector((Connector)newValue);
				return;
			case ReoPackage.NODE__SOURCE_ENDS:
				getSourceEnds().clear();
				getSourceEnds().addAll((Collection<? extends SourceEnd>)newValue);
				return;
			case ReoPackage.NODE__SINK_ENDS:
				getSinkEnds().clear();
				getSinkEnds().addAll((Collection<? extends SinkEnd>)newValue);
				return;
			case ReoPackage.NODE__TYPE:
				setType((NodeType)newValue);
				return;
			case ReoPackage.NODE__HIDE:
				setHide((Boolean)newValue);
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
			case ReoPackage.NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ReoPackage.NODE__ARRIVAL_RATE:
				getArrivalRate().clear();
				return;
			case ReoPackage.NODE__PROCESSING_DELAY:
				getProcessingDelay().clear();
				return;
			case ReoPackage.NODE__RECONF_ACTIONS:
				getReconfActions().clear();
				return;
			case ReoPackage.NODE__PROPERTIES:
				getProperties().clear();
				return;
			case ReoPackage.NODE__CONNECTOR:
				setConnector((Connector)null);
				return;
			case ReoPackage.NODE__SOURCE_ENDS:
				getSourceEnds().clear();
				return;
			case ReoPackage.NODE__SINK_ENDS:
				getSinkEnds().clear();
				return;
			case ReoPackage.NODE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ReoPackage.NODE__HIDE:
				setHide(HIDE_EDEFAULT);
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
			case ReoPackage.NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ReoPackage.NODE__ARRIVAL_RATE:
				return arrivalRate != null && !arrivalRate.isEmpty();
			case ReoPackage.NODE__PROCESSING_DELAY:
				return processingDelay != null && !processingDelay.isEmpty();
			case ReoPackage.NODE__RECONF_ACTIONS:
				return reconfActions != null && !reconfActions.isEmpty();
			case ReoPackage.NODE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ReoPackage.NODE__CONNECTOR:
				return getConnector() != null;
			case ReoPackage.NODE__SOURCE_ENDS:
				return sourceEnds != null && !sourceEnds.isEmpty();
			case ReoPackage.NODE__SINK_ENDS:
				return sinkEnds != null && !sinkEnds.isEmpty();
			case ReoPackage.NODE__TYPE:
				return type != TYPE_EDEFAULT;
			case ReoPackage.NODE__HIDE:
				return isHide() != HIDE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.NODE__NAME: return ReoPackage.NAMEABLE__NAME;
				default: return -1;
			}
		}
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
		if (baseClass == Delayable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.NODE__ARRIVAL_RATE: return ReoPackage.DELAYABLE__ARRIVAL_RATE;
				case ReoPackage.NODE__PROCESSING_DELAY: return ReoPackage.DELAYABLE__PROCESSING_DELAY;
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.NODE__RECONF_ACTIONS: return ReoPackage.RECONFIGURABLE__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (derivedFeatureID) {
				case ReoPackage.NODE__PROPERTIES: return ReoPackage.PROPERTY_HOLDER__PROPERTIES;
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
		if (baseClass == Nameable.class) {
			switch (baseFeatureID) {
				case ReoPackage.NAMEABLE__NAME: return ReoPackage.NODE__NAME;
				default: return -1;
			}
		}
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
		if (baseClass == Delayable.class) {
			switch (baseFeatureID) {
				case ReoPackage.DELAYABLE__ARRIVAL_RATE: return ReoPackage.NODE__ARRIVAL_RATE;
				case ReoPackage.DELAYABLE__PROCESSING_DELAY: return ReoPackage.NODE__PROCESSING_DELAY;
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (baseFeatureID) {
				case ReoPackage.RECONFIGURABLE__RECONF_ACTIONS: return ReoPackage.NODE__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (baseFeatureID) {
				case ReoPackage.PROPERTY_HOLDER__PROPERTIES: return ReoPackage.NODE__PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

}
