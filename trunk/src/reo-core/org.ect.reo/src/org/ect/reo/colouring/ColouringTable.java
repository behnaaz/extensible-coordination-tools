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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.reo.Nameable;
import org.ect.reo.Reo;
import org.ect.reo.animation.AnimationPrinter;
import org.ect.reo.util.HashContainmentEList;

/**
 * A coloring table consists of a list of colorings. 
 * Two tables can be joined to a new one.
 * 
 * @see org.ect.reo.colouring.ColouringPackage#getColouringTable()
 * @model kind="class"
 * @generated
 */
public class ColouringTable extends MinimalEObjectImpl implements Nameable {
	
	/**
	 * Default constructor.
	 * @generated NOT
	 */
	public ColouringTable() {
		this("table");
	}
	
	/**
	 * Alternative constructor.
	 * @generated NOT
	 */
	public ColouringTable(String name) {
		super();
		setName(name);
	}
	
	/**
	 * Getter for the colorings. This uses an performance optimized list.
	 * @model type="org.ect.reo.colouring.Colouring" containment="true"
	 * @generated NOT
	 */
	public EList<Colouring> getColourings() {
		if (colourings == null) {
			//colourings = 
			//new EObjectContainmentEList<Colouring>(Colouring.class, this, ColouringPackage.COLOURING_TABLE__COLOURINGS);
			// Use hash containment list and set the initial capacity.
			colourings = new HashContainmentEList<Colouring>(Colouring.class, this, ColouringPackage.COLOURING_TABLE__COLOURINGS);
		}
		return colourings;
	}
	
	
	/**
	 * Join two coloring tables.
	 * @generated NOT
	 */
	public ColouringTable join(ColouringTable table2, int steps, IProgressMonitor monitor) {
		return join(table2, new JoinedTablesCache(), steps, monitor);
	}
	
	/**
	 * Join two coloring tables.
	 * @generated NOT
	 */
	public ColouringTable join(ColouringTable table2) {
		return join(table2, new JoinedTablesCache(), -1, new NullProgressMonitor());
	}
	
	/*
	 * Internal join method. Uses a cache.
	 */
	private ColouringTable join(ColouringTable t2, JoinedTablesCache tables, int steps, IProgressMonitor monitor) {
				
		if (steps==0) return new ColouringTable();
		ColouringTable t1 = this;
		
		// Create a new coloring table.
		ColouringTable joined = new ColouringTable();
		joined.setName("table" + ((int) (Math.random()*10000)));
		joined.setColours(Math.max(t1.getColours(), t2.getColours()));
		
		// Add the table to the cache.
		tables.putJoined(t1, t2, joined);
		
		int colourings = t1.getColourings().size() * t2.getColourings().size();
		monitor.beginTask("Joining colouring tables", colourings*2);
		
		// Iterate over all colourings.
		for (Colouring c1 : t1.getColourings()) {
			for (Colouring c2 : t2.getColourings()) {
				
				// Check if the job was canceled.
				if (monitor.isCanceled()) return joined;
				
				// Join the two functions if possible.
				Colouring joinedColouring = c1.join(c2);
				monitor.worked(1);
				
				if (joinedColouring==null) {
					monitor.worked(1);
					continue;
				}
				
				if (c1.getNextColouringTable()==null) {
					Reo.logError("Colouring has null as next table:\n" + c1);
					c1.setNextColouringTable(t1);
				}
				if (c2.getNextColouringTable()==null) {
					Reo.logError("Colouring has null as next table:\n" + c2);
					c2.setNextColouringTable(t2);
				}
				
				ColouringTable next1 = c1.getNextColouringTable();
				ColouringTable next2 = c2.getNextColouringTable();
				
				// Join the next tables.
				ColouringTable joinedNext = tables.getJoined(next1, next2);
				if (joinedNext==null && steps!=1) {
					joinedNext = next1.join(next2, tables, steps-1, new SubProgressMonitor(monitor,1));
					tables.putJoined(next1, next2, joinedNext);
				} else {
					monitor.worked(1);
				}
				
				if (joinedNext==null) joinedNext = new ColouringTable();
				joinedColouring.setNextColouringTable(joinedNext);
				
				// Add it to the table.
				joined.getColourings().add(joinedColouring);
			}
			
		}
		
		monitor.done();
		
		return joined;
		
	}
	
	/**
	 * Returns a filtered list of coloring functions. Only colorings
	 * with at least one part colored as 'flow' will be used. Further
	 * the 'no flow' colors are not differentiated any more. This filtering
	 * is especially useful for displaying coloring in animations.
	 * 
	 * @generated NOT
	 */
	public List<Colouring> getFlowColourings() {

		List<Colouring> original = getColourings();
		List<Colouring> filtered = new Vector<Colouring>();
		
		for (int i=0; i<original.size(); i++) {

			Colouring colouring = original.get(i);
			
			// Check if there is no flow.
			if (colouring.getFlowEnds().isEmpty()) continue;
			
			// Check if it exists already.
			boolean existsAlready = false;
			for (int j=0; j<filtered.size(); j++) {
				if (colouring.hasEqualFlow( filtered.get(j) )) {
					existsAlready = true;
					break;
				}
			}
			// Add if it doesn't exist yet.
			if (!existsAlready) filtered.add(colouring);
			
		}
		
		return filtered;
		
	}
	
	/**
	 * Create a copy of this colouring table.
	 * @generated NOT
	 */
	public ColouringTable getCopy() {
		ColouringTable table = new ColouringTable(name);
		table.setColours(colours);
		for (Colouring colouring : getColourings()) {
			table.getColourings().add(colouring.getCopy());
		}
		return table;
	}
	
	/**
	 * @generated NOT
	 */
	public int size() {
		return getColourings().size();
	}
	
	/**
	 * Compute the total number of colorings in this table.
	 * This traverses all referenced coloring tables.
	 * @generated NOT
	 */
	public int totalColourings() {
		
		int size = 0;
		for (ColouringTable table : getAllTables()) {
			size = size + table.getColourings().size();
		}
		return size;
	}
	
	/**
	 * @generated NOT
	 */
	public int complexity() {
		
		Set<? extends ColouringTable> tables = getAllTables();
		int complexity = 0;
		int weight = (tables.size()+1)*2;
		
		for (ColouringTable table : tables) {
			complexity = complexity + (table.getColourings().size() * weight);
			weight = weight - 2;
		}
		
		return complexity;
	}

	/**
	 * Returns a list of all referenced coloring tables.
	 * @generated NOT
	 */
	public Set<? extends ColouringTable> getAllTables() {
		
		Set<ColouringTable> allTables = new LinkedHashSet<ColouringTable>();
		Set<ColouringTable> newTables = new LinkedHashSet<ColouringTable>();
		newTables.add(this);
		
		// Recursively add all colouring tables.
		while (!newTables.isEmpty()) {
			
			allTables.addAll(newTables);
			Set<ColouringTable> nextTables = new LinkedHashSet<ColouringTable>();
			
			for (ColouringTable table : newTables) {
				for (Colouring colouring : table.getColourings()) {
					ColouringTable next = colouring.getNextColouringTable();
					if (next!=null && !allTables.contains(next)) nextTables.add(next);
				}
			}
			
			newTables = nextTables;	
		}
		
		return allTables;
		
	}
		
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		AnimationPrinter printer = new AnimationPrinter();
		return printer.printAllTables(this);
	}	

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */	

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * @see #getColourings()
	 * @generated
	 * @ordered
	 */
	protected EList<Colouring> colourings;

	/**
	 * @see #getColours()
	 * @generated
	 * @ordered
	 */
	protected static final int COLOURS_EDEFAULT = 3;

	/**
	 * @see #getColours()
	 * @generated
	 * @ordered
	 */
	protected int colours = COLOURS_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ColouringPackage.Literals.COLOURING_TABLE;
	}
	
	/**
	 * @see #setName(String)
	 * @see org.ect.reo.colouring.ColouringPackage#getNameable_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ColouringPackage.COLOURING_TABLE__NAME, oldName, name));
	}

	/**
	 * @see #setColours(int)
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringTable_Colours()
	 * @model default="3"
	 * @generated
	 */
	public int getColours() {
		return colours;
	}

	/**
	 * @see #getColours()
	 * @generated
	 */
	public void setColours(int newColours) {
		int oldColours = colours;
		colours = newColours;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ColouringPackage.COLOURING_TABLE__COLOURS, oldColours, colours));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ColouringPackage.COLOURING_TABLE__COLOURINGS:
				return ((InternalEList<?>)getColourings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ColouringPackage.COLOURING_TABLE__NAME:
				return getName();
			case ColouringPackage.COLOURING_TABLE__COLOURINGS:
				return getColourings();
			case ColouringPackage.COLOURING_TABLE__COLOURS:
				return getColours();
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
			case ColouringPackage.COLOURING_TABLE__NAME:
				setName((String)newValue);
				return;
			case ColouringPackage.COLOURING_TABLE__COLOURINGS:
				getColourings().clear();
				getColourings().addAll((Collection<? extends Colouring>)newValue);
				return;
			case ColouringPackage.COLOURING_TABLE__COLOURS:
				setColours((Integer)newValue);
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
			case ColouringPackage.COLOURING_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ColouringPackage.COLOURING_TABLE__COLOURINGS:
				getColourings().clear();
				return;
			case ColouringPackage.COLOURING_TABLE__COLOURS:
				setColours(COLOURS_EDEFAULT);
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
			case ColouringPackage.COLOURING_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ColouringPackage.COLOURING_TABLE__COLOURINGS:
				return colourings != null && !colourings.isEmpty();
			case ColouringPackage.COLOURING_TABLE__COLOURS:
				return colours != COLOURS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

}
