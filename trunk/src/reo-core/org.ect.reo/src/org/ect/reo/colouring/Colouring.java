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
package org.ect.reo.colouring;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.AnimationPrinter;
import org.ect.reo.channels.Channel;


/**
 * Instances of this class model colourings. A colouring is a function
 * from {@link PrimitiveEnd}s to {@link FlowColour}. This function is
 * implemented using a hash map in the field {@link #colours}. Further
 * a colouring has a pointer to a next coloring table, in the field
 * {@link #nextColouringTable}.
 * 
 * @model kind="class"
 * @generated
 */
public class Colouring extends MinimalEObjectImpl implements EObject {
	
	/**
	 * Unmodifiable empty colouring. 
	 * @generated NOT
	 */
	public static final Colouring EMPTY = new Colouring() {
		
		@Override
		public EMap<PrimitiveEnd, FlowColour> getColours() {
			return ECollections.emptyEMap();
		}
		
		@Override
		public EList<Colouring> getParts() {
			return ECollections.emptyEList();
		}
		
	};
	
	
	/**
	 * Default constructor.
	 * @generated NOT
	 */
	public Colouring() {
		super();
	}
	
	/**
	 * Alternative constructor, that creates a coloring from 
	 * a given channel and two colors, that will be used as
	 * colors of the two ends of the channel.
	 * @generated NOT
	 */
	public Colouring(Channel channel, FlowColour c1, FlowColour c2) {
		this();
		getColours().put(channel.getChannelEndOne(), c1);
		getColours().put(channel.getChannelEndTwo(), c2);
	}
	
			
	/**
	 * Join two coloring functions. The original functions stay unchanged.
	 * This does NOT change the {@link #nextColouringTable} attribute.
	 * If the colorings are not compatible the method returns <code>null</code>.
	 * 
	 * @generated NOT
	 * @model
	 */
	public Colouring join(Colouring colouring) {
			
		// Create a new coloring and copy the entries.
		Colouring joined = new Colouring();		
		joined.copyColouring(colouring);
		
		// Try to merge in this coloring.
		for (PrimitiveEnd end : getColours().keySet()) {

			FlowColour c1 = getColours().get(end);
			FlowColour c2 = joined.getColours().get(end);
			
			// Check if this entry breaks compatibility.
			if (c2!=null && c1.getValue()!=c2.getValue()) {
				return null;
			}
			
			joined.getColours().put(end, c1);
		}
		
		// Add the original parts of the argument.
		if (colouring.getParts().isEmpty()) {
			joined.getParts().add(colouring);
		} else {
			joined.getParts().addAll(colouring.getParts());
		}
		
		// Add the original parts of this coloring.
		if (getParts().isEmpty()) {
			joined.getParts().add(this);
		} else {
			joined.getParts().addAll(getParts());
		}
		
		return joined;
	
	}
	
	
	/**
	 * Copy a coloring into this one.
	 * @generated NOT
	 */
	public void copyColouring(Colouring colouring) {
		// Copy the color entries.
		for (PrimitiveEnd end2 : colouring.getColours().keySet()) {
			getColours().put(end2, colouring.getColours().get(end2));
		}
		// Copy the parts.
		getParts().addAll(colouring.getParts());
	}
	
	
	/**
	 * Convenience getter method.
	 * @generated NOT
	 */
	public FlowColour getColour(PrimitiveEnd end) {
		return getColours().get(end);
	}
	
	
	/**
	 * Convenience setter method.
	 * @generated NOT
	 */
	public void setColour(PrimitiveEnd end, FlowColour colour) {
		getColours().put(end, colour);
	}
	
	
	/**
	 * Convenience setter method for multiple keys.
	 * @generated NOT
	 */
	public void setColours(EList<? extends PrimitiveEnd> ends, FlowColour flowColour) {
		for (PrimitiveEnd end : ends) {
			getColours().put(end, flowColour);
		}
	}
	
	
	/**
	 * Returns all primitive ends of this coloring that have flow.
	 * @generated NOT 
	 */
	public Set<PrimitiveEnd> getFlowEnds() {
		Set<PrimitiveEnd> result = new LinkedHashSet<PrimitiveEnd>();		
		for (PrimitiveEnd end : getColours().keySet()) {
			if (isFlow(end)) result.add(end);
		}
		return result;
	}
	
	
	/**
	 * Checks if there is any flow in this coloring.
	 * @generated NOT
	 */
	public boolean hasFlow() {
		return !getFlowEnds().isEmpty();
	}

	
	/**
	 * Checks if the argument coloring function has the same
	 * flow ends as this. The no-flow ends are not considered.
	 * @generated NOT
	 */
	public boolean hasEqualFlow(Colouring colouring) {
		
		Set<PrimitiveEnd> flow1 = getFlowEnds();
		Set<PrimitiveEnd> flow2 = colouring.getFlowEnds();
		
		if (flow1.size()!=flow2.size()) return false;
		if (!flow1.containsAll(flow2)) return false;
		
		return true;
	}
	
	
	/**
	 * Check if an element has initial data flow according to this coloring. 
	 * @generated NOT
	 */
	public boolean hasInitialFlow(Connectable element, List<Connectable> border) {
		
		if (element instanceof Primitive) {
			return hasInitialFlow(element.getSinkEnds(), border) && 
				   !hasInitialFlow(element.getSourceEnds(), border);
		}
		else if (element instanceof Node) {
			return hasInitialFlow(element.getSourceEnds(), border) && 
				   !hasInitialFlow(element.getSinkEnds(), border);
		}
		else {
			throw new RuntimeException("Unknown element type: " + element.getClass());
		}
		
	}
	
	/**
	 * @generated NOT
	 */
	private boolean hasInitialFlow(EList<? extends PrimitiveEnd> ends, List<Connectable> border) {
		for (PrimitiveEnd end : ends) {
			if (isFlow(end) && !belongsToBorder(end,border)) return true;
		}
		return false;
	}
	
	/**
	 * @generated NOT
	 */
	private boolean belongsToBorder(PrimitiveEnd end, List<Connectable> border) {
		for (Connectable element : border) {
			if (element.getAllEnds().contains(end)) return true;
		}
		return false;
	}
	
	/**
	 * Check if a end is colored as {@link org.ect.reo.colouring.FlowColour#FLOW}.
	 * @generated NOT 
	 */
	public boolean isFlow(PrimitiveEnd end) {
		return (getColours().get(end)!=null && 
				getColours().get(end).getValue()==FlowColour.FLOW);
	}
	
	
	/**
	 * Check if a end is colored as {@link org.ect.reo.colouring.FlowColour#NO_FLOW_GIVE_REASON}.
	 * @generated NOT 
	 */
	public boolean isNoFlowGiveReason(PrimitiveEnd end) {
		return (getColours().get(end)!=null && 
				getColours().get(end).getValue()==FlowColour.NO_FLOW_GIVE_REASON);
	}
	
	
	/**
	 * Check if a end is colored as {@link org.ect.reo.colouring.FlowColour#NO_FLOW_REQUIRE_REASON}.
	 * @generated NOT 
	 */
	public boolean isNoFlowRequireReason(PrimitiveEnd end) {
		return (getColours().get(end)!=null && 
				getColours().get(end).getValue()==FlowColour.NO_FLOW_REQUIRE_REASON);
	}
	
	/**
	 * Get a copy of this colouring.
	 * @generated NOT
	 */
	public Colouring getCopy() {
		Colouring colouring = new Colouring();
		colouring.copyColouring(this);
		colouring.setNextColouringTable(nextColouringTable);
		colouring.getParts().addAll(getParts());
		return colouring;
	}
	
	/**
	 * This compares the entries of the coloring, 
	 * but not the next table or the parts.
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object obj) {
		
		// Same object?
		if (obj==this) return true;
		if (!(obj instanceof Colouring)) return false;
		
		Colouring colouring = (Colouring) obj;
		
		// Different number of entries?
		if (colouring.getColours().size()!=getColours().size()) return false;
		
		// Get all primitive ends that are used as keys.
		Set<PrimitiveEnd> ends = new HashSet<PrimitiveEnd>();
		ends.addAll(getColours().keySet());
		ends.addAll(colouring.getColours().keySet());
		
		// Compare the colors of the ends.
		for (PrimitiveEnd end : ends) {
			
			FlowColour c1 = getColours().get(end);
			FlowColour c2 = colouring.getColours().get(end);
			
			if (c1==null || c2==null) return false;
			if (c1.getValue()!=c2.getValue()) return false; 
		
		}
		
		return true;
		
	}
	
	/**
	 * Check if this colouring contains the argument colouring.
	 * A colouring is contained in another if all its ends are also
	 * present have the same color in the other coloring.
	 * 
	 * @generated NOT
	 * @param colouring Colouring.
	 * @return <code>true</code> if this contains the argument colourin.
	 */
	public boolean contains(Colouring colouring) {
		for (PrimitiveEnd end : colouring.getColours().keySet()) {
			if (getColour(end)!=colouring.getColour(end)) return false;
		}
		return true;
	}
	
	/**
	 * Calculate the hash code of this colouring.
	 * Note again that the next-tables are not considered!!!
	 * This is to avoid non-determinism.
	 * @generated NOT
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		// The order of the entries and the next table are not relevant.
		for (PrimitiveEnd end : getColours().keySet()) {
			int key = end==null ? 1 : end.hashCode();
			int colour = getColours().get(end)==null ? 0 : getColours().get(end).getValue()+1;
			hash += key+1 >> colour;
		}
		return hash;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		AnimationPrinter printer = new AnimationPrinter();
		return printer.printColouring(this);
	}
		
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ColouringPackage.Literals.COLOURING;
	}

	/**
	 * @generated
	 */
	protected EMap<PrimitiveEnd, FlowColour> colours;

	/**
	 * @generated
	 * @ordered
	 */
	protected ColouringTable nextColouringTable;

	/**
	 * @generated
	 */
	protected EList<Colouring> parts;
	
	/**
	 * @model mapType="org.ect.reo.colouring.ColouringEntry" keyType="org.ect.reo.PrimitiveEnd" valueType="org.ect.reo.colouring.FlowColour"
	 * @generated
	 */
	public EMap<PrimitiveEnd, FlowColour> getColours() {
		if (colours == null) {
			colours = new EcoreEMap<PrimitiveEnd,FlowColour>(ColouringPackage.Literals.COLOURING_ENTRY, ColouringEntry.class, this, ColouringPackage.COLOURING__COLOURS);
		}
		return colours;
	}
	
	/**
	 * @model
	 * @generated
	 */
	public ColouringTable getNextColouringTable() {
		if (nextColouringTable != null && nextColouringTable.eIsProxy()) {
			InternalEObject oldNextColouringTable = (InternalEObject)nextColouringTable;
			nextColouringTable = (ColouringTable)eResolveProxy(oldNextColouringTable);
			if (nextColouringTable != oldNextColouringTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ColouringPackage.COLOURING__NEXT_COLOURING_TABLE, oldNextColouringTable, nextColouringTable));
			}
		}
		return nextColouringTable;
	}
	
	/**
	 * @generated
	 */
	public ColouringTable basicGetNextColouringTable() {
		return nextColouringTable;
	}
	
	/**
	 * @generated
	 */
	public void setNextColouringTable(ColouringTable newNextColouringTable) {
		ColouringTable oldNextColouringTable = nextColouringTable;
		nextColouringTable = newNextColouringTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ColouringPackage.COLOURING__NEXT_COLOURING_TABLE, oldNextColouringTable, nextColouringTable));
	}
	
	/**
	 * @model transient="true" ordered="false"
	 * @generated
	 */
	public EList<Colouring> getParts() {
		if (parts == null) {
			parts = new EObjectResolvingEList<Colouring>(Colouring.class, this, ColouringPackage.COLOURING__PARTS);
		}
		return parts;
	}
	
	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ColouringPackage.COLOURING__COLOURS:
				return ((InternalEList<?>)getColours()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ColouringPackage.COLOURING__COLOURS:
				if (coreType) return getColours();
				else return getColours().map();
			case ColouringPackage.COLOURING__NEXT_COLOURING_TABLE:
				if (resolve) return getNextColouringTable();
				return basicGetNextColouringTable();
			case ColouringPackage.COLOURING__PARTS:
				return getParts();
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
			case ColouringPackage.COLOURING__COLOURS:
				((EStructuralFeature.Setting)getColours()).set(newValue);
				return;
			case ColouringPackage.COLOURING__NEXT_COLOURING_TABLE:
				setNextColouringTable((ColouringTable)newValue);
				return;
			case ColouringPackage.COLOURING__PARTS:
				getParts().clear();
				getParts().addAll((Collection<? extends Colouring>)newValue);
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
			case ColouringPackage.COLOURING__COLOURS:
				getColours().clear();
				return;
			case ColouringPackage.COLOURING__NEXT_COLOURING_TABLE:
				setNextColouringTable((ColouringTable)null);
				return;
			case ColouringPackage.COLOURING__PARTS:
				getParts().clear();
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
			case ColouringPackage.COLOURING__COLOURS:
				return colours != null && !colours.isEmpty();
			case ColouringPackage.COLOURING__NEXT_COLOURING_TABLE:
				return nextColouringTable != null;
			case ColouringPackage.COLOURING__PARTS:
				return parts != null && !parts.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
}
