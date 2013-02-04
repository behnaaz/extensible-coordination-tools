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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.reo.animation.Animatable;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.Colourable;
import org.ect.reo.colouring.ColouringTable;



/**
 * Primitives are connectable elements which form the counterpart of
 * {@link org.ect.reo.Node}s. Implementing {@link org.ect.reo.Connectable}, they have
 * a number of {@link SinkEnd}s and {@link SourceEnd}s. In contrast to nodes,
 * primitives serve as containers for primitive ends.
 * 
 * @see org.ect.reo.ReoFactory#createPrimitive()
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class Primitive extends MinimalEObjectImpl implements Connectable, Animatable, Delayable, Reconfigurable, PropertyHolder {
	
	/**
	 * Get the name of the primitive. Subclasses may override this method.
	 * @model
	 * @generated NOT
	 */
	public String getName() {
		return eClass().getName();
	}
		
	/**
	 * Check whether this primitive is synchronous.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract boolean isSynchronous();

	/**
	 * Returns the connector this primitive is contained in, or <code>null</code>
	 * is it is not contained in a connector.
	 * @return {@link Connector}
	 * @generated NOT
	 */
	public Connector getConnector() {
		if (eContainer() instanceof Connector) {
			return (Connector) eContainer();
		}
		return null;
	}
	
	/**
	 * Initialize the PrimitiveEnds of the primitive.
	 * This creates the default number of SourceEnds
	 * and SinkEnds for this primitive.
	 * @model
	 * @generated NOT
	 */
	public abstract void initializeEnds();
	
	
	/**
	 * Disconnects all sink ends and all source ends
	 * of this primitive from their nodes.
	 * @model
	 * @generated NOT
	 */
	public void disconnectEnds() {
		
		for (int i=0; i<getSinkEnds().size(); i++)
			getSinkEnd(i).setNode(null);
		
		for (int i=0; i<getSourceEnds().size(); i++)
			getSourceEnd(i).setNode(null);
	
	}
	
	
	/**
	 * Returns <code>true</code>, if all sink ends and all
	 * source ends of this primitive are connected to a node.
	 * @model
	 * @generated NOT
	 */
	public boolean isConnected() {

		for (int j=0; j<getSinkEnds().size(); j++) 
			if (getSinkEnd(j).getNode()==null) return false;
	
		for (int j=0; j<getSourceEnds().size(); j++) 
			if (getSourceEnd(j).getNode()==null) return false;
		
		return true;
		
	}
	
	
	/**
	 * Get the source end of the given index.
	 * @generated NOT
	 */
	public SourceEnd getSourceEnd(int index) {
		if (getSourceEnds().size()<=index) return null;
		else return (SourceEnd) getSourceEnds().get(index);
	}

	
	/**
	 * Get the sink end of the given index.
	 * @generated NOT
	 */
	public SinkEnd getSinkEnd(int index) {
		if (getSinkEnds().size()<=index) return null;
		else return (SinkEnd) getSinkEnds().get(index);
	}
	
	
	/**
	 * Find a source end using its name.
	 * @param name Name of the source end.
	 * @return The source end, if found.
	 * @generated NOT
	 */
	public SourceEnd findSourceEnd(String name) {
		for (SourceEnd end : getSourceEnds()) {
			if (name.equals(end.getName())) return end;
		}
		return null;
	}
	
	
	/**
	 * Find a sink end using its name.
	 * @param name Name of the sink end.
	 * @return The sink end, if found.
	 * @generated NOT
	 */
	public SinkEnd findSinkEnd(String name) {
		for (SinkEnd end : getSinkEnds()) {
			if (name.equals(end.getName())) return end;
		}
		return null;
	}

	
	/**
	 * Set the source end of the given index.
	 * @generated NOT
	 */
	protected void setSourceEnd(int index, SourceEnd sourceEnd) {
		
		// Index must be a positive integer.
		if (index<0) return;
		
		// Check if it should be deleted.
		if (sourceEnd==null && index<getSourceEnds().size()) {
			getSourceEnds().remove(index);
			return;
		}
		
		// Check if it is already in the list.
		int oldIndex = getSourceEnds().indexOf(sourceEnd);
		
		// Already at the correct position.
		if (oldIndex==index) return;
		
		// Already in the list, but at the wrong position.
		if (oldIndex!=-1) {
			// So we create a new end at the old position.
			getSourceEnds().set(oldIndex, new SourceEnd());
		} 
		
		// Ensure that there are enough ends, so that we can 
		// insert the new source end at the correct position.
		while (getSourceEnds().size()<=index) {
			getSourceEnds().add(new SourceEnd());			
		}
		
		// Now we can set the end.
		getSourceEnds().set(index, sourceEnd);
		
	}
	
	/**
	 * Set the sink end of the given index.
	 * @generated NOT
	 */
	protected void setSinkEnd(int index, SinkEnd sinkEnd) {
		
		// Index must be a positive integer.
		if (index<0) return;

		// Check if it should be deleted.
		if (sinkEnd==null && index<getSinkEnds().size()) {
			getSinkEnds().remove(index);
			return;
		}
		
		// Check if it is already in the list.
		int oldIndex = getSinkEnds().indexOf(sinkEnd);
		
		// Already at the correct position.
		if (oldIndex==index) return;
		
		// Already in the list, but at the wrong position.
		if (oldIndex!=-1) {
			// So we create a new end at the old position.
			getSinkEnds().set(oldIndex, new SinkEnd());
		} 
		
		// Ensure that there are enough ends, so that we can 
		// insert the new source end at the correct position.
		while (getSinkEnds().size()<=index) {
			getSinkEnds().add(new SinkEnd());			
		}
		
		// Now we can set the end.
		getSinkEnds().set(index, sinkEnd);
		
	}
	
	
	/**
	 * Getter method that returns a list of all ends that are 
	 * attached to this primitive. This is a derived value and can
	 * therefore not be modified.
	 * @generated NOT
	 */
	public EList<PrimitiveEnd> getAllEnds() {
		List<PrimitiveEnd> allEnds = new Vector<PrimitiveEnd>();
		allEnds.addAll(getSourceEnds());
		allEnds.addAll(getSinkEnds());
		return new UnmodifiableEList<PrimitiveEnd>(allEnds.size(), allEnds.toArray());
	}
	
	
	/**
	 * Check if the argument primitive has the same 'type' as
	 * this one. Subclasses may override this method.
	 * @generated NOT
	 */
	public boolean hasSameType(Primitive primitive) {
		
		// Cannot be null.
		if (primitive==null) return false;
		
		// Should be the same eClass and have the same number of ends.
		return eClass().equals(primitive.eClass()) &&
			   getSourceEnds().size()==primitive.getSourceEnds().size() &&
			   getSinkEnds().size()==primitive.getSinkEnds().size();
	}
	
	
	/**
	 * By default, we assume that subclasses use the flip rule.
	 * Subclasses should override this if that is not the case.
	 * @model
	 * @generated NOT
	 * @see Colourable#usesFlipRule()
	 */
	public boolean usesFlipRule() {
		return true;
	}
	
	/**
	 * Subclasses have to provide the animation table.
	 * @see org.ect.reo.ReoPackage#getAnimatable_AnimationTable()
	 * @generated NOT
	 */
	public abstract AnimationTable getAnimationTable();
	
	/**
	 * Get the colouring table for this network.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public ColouringTable getColouringTable() {
		return getAnimationTable();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String name = getName()==null || getName().equals("") ? "Primitive" : getName();
		return name + "@" + Integer.toHexString(hashCode()); 
	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The cached value of the '{@link #getArrivalRate() <em>Arrival Rate</em>}' attribute list.
	 * @see #getArrivalRate()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> arrivalRate;

	/**
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
	 * @generated
	 */
	public Primitive() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.PRIMITIVE;
	}
	

	/**
	 * @return the value of the '<em>Delay</em>' attribute list.
	 * @see org.ect.reo.ReoPackage#getDelayable_Delay()
	 * @model
	 * @generated
	 */
	public EList<Double> getArrivalRate() {
		if (arrivalRate == null) {
			arrivalRate = new EDataTypeEList<Double>(Double.class, this, ReoPackage.PRIMITIVE__ARRIVAL_RATE);
		}
		return arrivalRate;
	}


	/**
	 * @return the value of the '<em>Processing Delay</em>' attribute list.
	 * @see org.ect.reo.ReoPackage#getDelayable_ProcessingDelay()
	 * @model
	 * @generated
	 */
	public EList<Double> getProcessingDelay() {
		if (processingDelay == null) {
			processingDelay = new EDataTypeEList<Double>(Double.class, this, ReoPackage.PRIMITIVE__PROCESSING_DELAY);
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
			reconfActions = new EObjectContainmentEList<ReconfAction>(ReconfAction.class, this, ReoPackage.PRIMITIVE__RECONF_ACTIONS);
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
			properties = new EObjectContainmentEList<Property>(Property.class, this, ReoPackage.PRIMITIVE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * @see org.ect.reo.SourceEnd#getPrimitive
	 * @model type="org.ect.reo.SourceEnd" opposite="primitive" containment="true"
	 * @generated
	 */
	public EList<SourceEnd> getSourceEnds() {
		if (sourceEnds == null) {
			sourceEnds = new EObjectContainmentWithInverseEList<SourceEnd>(SourceEnd.class, this, ReoPackage.PRIMITIVE__SOURCE_ENDS, ReoPackage.SOURCE_END__PRIMITIVE);
		}
		return sourceEnds;
	}

	/**
	 * @see org.ect.reo.SinkEnd#getPrimitive
	 * @model type="org.ect.reo.SinkEnd" opposite="primitive" containment="true"
	 * @generated
	 */
	public EList<SinkEnd> getSinkEnds() {
		if (sinkEnds == null) {
			sinkEnds = new EObjectContainmentWithInverseEList<SinkEnd>(SinkEnd.class, this, ReoPackage.PRIMITIVE__SINK_ENDS, ReoPackage.SINK_END__PRIMITIVE);
		}
		return sinkEnds;
	}
		
	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSourceEnds()).basicAdd(otherEnd, msgs);
			case ReoPackage.PRIMITIVE__SINK_ENDS:
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
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
				return ((InternalEList<?>)getReconfActions()).basicRemove(otherEnd, msgs);
			case ReoPackage.PRIMITIVE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				return ((InternalEList<?>)getSourceEnds()).basicRemove(otherEnd, msgs);
			case ReoPackage.PRIMITIVE__SINK_ENDS:
				return ((InternalEList<?>)getSinkEnds()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.PRIMITIVE__ARRIVAL_RATE:
				return getArrivalRate();
			case ReoPackage.PRIMITIVE__PROCESSING_DELAY:
				return getProcessingDelay();
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
				return getReconfActions();
			case ReoPackage.PRIMITIVE__PROPERTIES:
				return getProperties();
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				return getSourceEnds();
			case ReoPackage.PRIMITIVE__SINK_ENDS:
				return getSinkEnds();
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
			case ReoPackage.PRIMITIVE__ARRIVAL_RATE:
				getArrivalRate().clear();
				getArrivalRate().addAll((Collection<? extends Double>)newValue);
				return;
			case ReoPackage.PRIMITIVE__PROCESSING_DELAY:
				getProcessingDelay().clear();
				getProcessingDelay().addAll((Collection<? extends Double>)newValue);
				return;
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
				getReconfActions().clear();
				getReconfActions().addAll((Collection<? extends ReconfAction>)newValue);
				return;
			case ReoPackage.PRIMITIVE__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				getSourceEnds().clear();
				getSourceEnds().addAll((Collection<? extends SourceEnd>)newValue);
				return;
			case ReoPackage.PRIMITIVE__SINK_ENDS:
				getSinkEnds().clear();
				getSinkEnds().addAll((Collection<? extends SinkEnd>)newValue);
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
			case ReoPackage.PRIMITIVE__ARRIVAL_RATE:
				getArrivalRate().clear();
				return;
			case ReoPackage.PRIMITIVE__PROCESSING_DELAY:
				getProcessingDelay().clear();
				return;
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
				getReconfActions().clear();
				return;
			case ReoPackage.PRIMITIVE__PROPERTIES:
				getProperties().clear();
				return;
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				getSourceEnds().clear();
				return;
			case ReoPackage.PRIMITIVE__SINK_ENDS:
				getSinkEnds().clear();
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
			case ReoPackage.PRIMITIVE__ARRIVAL_RATE:
				return arrivalRate != null && !arrivalRate.isEmpty();
			case ReoPackage.PRIMITIVE__PROCESSING_DELAY:
				return processingDelay != null && !processingDelay.isEmpty();
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
				return reconfActions != null && !reconfActions.isEmpty();
			case ReoPackage.PRIMITIVE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
				return sourceEnds != null && !sourceEnds.isEmpty();
			case ReoPackage.PRIMITIVE__SINK_ENDS:
				return sinkEnds != null && !sinkEnds.isEmpty();
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
		if (baseClass == Delayable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.PRIMITIVE__ARRIVAL_RATE: return ReoPackage.DELAYABLE__ARRIVAL_RATE;
				case ReoPackage.PRIMITIVE__PROCESSING_DELAY: return ReoPackage.DELAYABLE__PROCESSING_DELAY;
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (derivedFeatureID) {
				case ReoPackage.PRIMITIVE__RECONF_ACTIONS: return ReoPackage.RECONFIGURABLE__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (derivedFeatureID) {
				case ReoPackage.PRIMITIVE__PROPERTIES: return ReoPackage.PROPERTY_HOLDER__PROPERTIES;
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
		if (baseClass == Delayable.class) {
			switch (baseFeatureID) {
				case ReoPackage.DELAYABLE__ARRIVAL_RATE: return ReoPackage.PRIMITIVE__ARRIVAL_RATE;
				case ReoPackage.DELAYABLE__PROCESSING_DELAY: return ReoPackage.PRIMITIVE__PROCESSING_DELAY;
				default: return -1;
			}
		}
		if (baseClass == Reconfigurable.class) {
			switch (baseFeatureID) {
				case ReoPackage.RECONFIGURABLE__RECONF_ACTIONS: return ReoPackage.PRIMITIVE__RECONF_ACTIONS;
				default: return -1;
			}
		}
		if (baseClass == PropertyHolder.class) {
			switch (baseFeatureID) {
				case ReoPackage.PROPERTY_HOLDER__PROPERTIES: return ReoPackage.PRIMITIVE__PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

}
