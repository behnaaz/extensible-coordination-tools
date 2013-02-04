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
package org.ect.reo.mcrl2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * @generated
 */
public class Specification extends EObjectImpl implements EObject {
	
	/**
	 * @generated NOT
	 */
	static final String INDENT = "  ";

	/**
	 * @generated NOT
	 */
	static final String LONG_INDENT = "      ";


	/**
	 * @generated NOT
	 */
	public Atom findAtom(String name) {
		if (name==null) return null;
		for (Atom atom : getAtoms()) {
			if (name.equals(atom.getName())) return atom;
		}
		return null;
	}
	
	/**
	 * @generated NOT
	 */
	public Process findProcess(String name) {
		if (name==null) return null;
		for (Process process : getProcesses()) {
			if (name.equals(process.getName())) return process;
		}
		return null;
	}
	
	/**
	 * @generated NOT
	 */
	public void join(Specification spec) {
		// Create a copy.
		spec = (Specification) EcoreUtil.copy(spec);
		// Add actions and processes to this specification.
		getAtoms().addAll(spec.getAtoms());
		getProcesses().addAll(spec.getProcesses());
	}
	
	/**
	 * @generated NOT
	 */
	public void format() {
		// Sort the actions.
		List<Atom> atoms = new ArrayList<Atom>(getAtoms());
		Collections.sort(atoms, new Comparator<Atom>() {
			public int compare(Atom a1, Atom a2) {
				if (a1.getName()==null) return 1;
				if (a2.getName()==null) return -1;
				return a1.getName().compareTo(a2.getName());
			}
		});
		getAtoms().clear();
		getAtoms().addAll(atoms);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		// Print the sorts.
		if (!getSorts().isEmpty()) {
			result.append("sort\n");
			for (Sort sort : getSorts()) {
				if (sort instanceof PrimitiveSort) continue;
				String printed = sort.toString().replaceAll("\n", "\n" + INDENT + LONG_INDENT);
				result.append(INDENT + printed + ";\n");
			}
			result.append("\n");
		}
		
		// Print the actions.
		if (!getAtoms().isEmpty()) {
			result.append("act\n" + INDENT);
			result.append(actions(getAtoms(), INDENT, 12));
			result.append(";\n\n");
		}
		
		// Print the processes.
		if (!getProcesses().isEmpty()) {
			result.append("proc\n");
			for (Process process : getProcesses()) {
				String printed = process.toString();
				if (printed.indexOf('\n')>0) {
					result.append("\n"); // Some more space.
					printed = printed.replaceAll("\n", "\n" + INDENT + LONG_INDENT);
				}
				result.append(INDENT + printed + "\n");
			}
			result.append("\n");
		}
		
		// Print initial processes.
		boolean first = true;
		for (Process process : getProcesses()) {
			if (process.isInitial()) {
				if (first) result.append("init\n" + INDENT); 
				else result.append(" || ");
				result.append(process.newInstance().toString());
				first = false;
			}
		}
		if (!first) result.append(";\n");
		
		return result.toString();
	}
	

	/**
	 * @generated NOT
	 */
	private String actions(List<? extends Atom> actions, String indent, int perline) {
		
		// Remove unnamed actions first.
		actions = new ArrayList<Atom>(actions);
		Atom.removeUnnamed(actions);
		if (actions.isEmpty()) return "";
			
		StringBuffer result = new StringBuffer();
		Sort lastType = null;

		for (int i=0; i<actions.size(); i++) {
						
			Atom atom = actions.get(i);
			if (i>0) {
				if (lastType!=null && lastType!=atom.getType()) {
					result.append(" : " + lastType.getName() + "; ");
				} else {
					result.append(", ");
				}
			}
			if (i%perline==perline-1) result.append("\n" + indent);
			
			result.append(atom.getName());
			
			lastType = atom.getType();
		}
		if (lastType!=null) {
			result.append(" : " + lastType.getName());
		}
		return result.toString();
	}
	

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The cached value of the '{@link #getAtoms() <em>Atoms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtoms()
	 * @generated
	 * @ordered
	 */
	protected EList<Atom> atoms;

	/**
	 * The cached value of the '{@link #getProcesses() <em>Processes</em>}' containment reference list.
	 * @see #getProcesses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.ect.reo.mcrl2.Process> processes;

	/**
	 * The cached value of the '{@link #getSorts() <em>Sorts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Sort> sorts;


	/**
	 * @generated
	 */
	public Specification() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.SPECIFICATION;
	}

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Atom}.
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @generated
	 */
	public EList<Atom> getAtoms() {
		if (atoms == null) {
			atoms = new EObjectContainmentEList<Atom>(Atom.class, this, MCRL2Package.SPECIFICATION__ATOMS);
		}
		return atoms;
	}

	/**
	 * Returns the value of the '<em><b>Processes</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Process}.
	 * @return the value of the '<em>Processes</em>' containment reference list.
	 * @generated
	 */
	public EList<org.ect.reo.mcrl2.Process> getProcesses() {
		if (processes == null) {
			processes = new EObjectContainmentEList<org.ect.reo.mcrl2.Process>(org.ect.reo.mcrl2.Process.class, this, MCRL2Package.SPECIFICATION__PROCESSES);
		}
		return processes;
	}

	/**
	 * Returns the value of the '<em><b>Sorts</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Sort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sorts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sorts</em>' containment reference list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSpecification_Sorts()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Sort> getSorts() {
		if (sorts == null) {
			sorts = new EObjectContainmentEList<Sort>(Sort.class, this, MCRL2Package.SPECIFICATION__SORTS);
		}
		return sorts;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.SPECIFICATION__ATOMS:
				return ((InternalEList<?>)getAtoms()).basicRemove(otherEnd, msgs);
			case MCRL2Package.SPECIFICATION__PROCESSES:
				return ((InternalEList<?>)getProcesses()).basicRemove(otherEnd, msgs);
			case MCRL2Package.SPECIFICATION__SORTS:
				return ((InternalEList<?>)getSorts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.SPECIFICATION__ATOMS:
				return getAtoms();
			case MCRL2Package.SPECIFICATION__PROCESSES:
				return getProcesses();
			case MCRL2Package.SPECIFICATION__SORTS:
				return getSorts();
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
			case MCRL2Package.SPECIFICATION__ATOMS:
				getAtoms().clear();
				getAtoms().addAll((Collection<? extends Atom>)newValue);
				return;
			case MCRL2Package.SPECIFICATION__PROCESSES:
				getProcesses().clear();
				getProcesses().addAll((Collection<? extends org.ect.reo.mcrl2.Process>)newValue);
				return;
			case MCRL2Package.SPECIFICATION__SORTS:
				getSorts().clear();
				getSorts().addAll((Collection<? extends Sort>)newValue);
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
			case MCRL2Package.SPECIFICATION__ATOMS:
				getAtoms().clear();
				return;
			case MCRL2Package.SPECIFICATION__PROCESSES:
				getProcesses().clear();
				return;
			case MCRL2Package.SPECIFICATION__SORTS:
				getSorts().clear();
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
			case MCRL2Package.SPECIFICATION__ATOMS:
				return atoms != null && !atoms.isEmpty();
			case MCRL2Package.SPECIFICATION__PROCESSES:
				return processes != null && !processes.isEmpty();
			case MCRL2Package.SPECIFICATION__SORTS:
				return sorts != null && !sorts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // Specification
