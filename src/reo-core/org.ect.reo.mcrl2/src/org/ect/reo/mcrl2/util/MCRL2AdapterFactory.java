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
package org.ect.reo.mcrl2.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Allow;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Block;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Communication;
import org.ect.reo.mcrl2.CompositeAction;
import org.ect.reo.mcrl2.CustomProcess;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Hide;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.MCRL2Package;
import org.ect.reo.mcrl2.Nameable;
import org.ect.reo.mcrl2.Parallelism;
import org.ect.reo.mcrl2.PrimitiveSort;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Specification;
import org.ect.reo.mcrl2.Structure;
import org.ect.reo.mcrl2.Summation;
import org.ect.reo.mcrl2.Synchronization;
import org.ect.reo.mcrl2.UserSort;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.reo.mcrl2.MCRL2Package
 * @generated
 */
public class MCRL2AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MCRL2Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MCRL2AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MCRL2Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MCRL2Switch<Adapter> modelSwitch =
		new MCRL2Switch<Adapter>() {
			@Override
			public Adapter caseSpecification(Specification object) {
				return createSpecificationAdapter();
			}
			@Override
			public Adapter caseSort(Sort object) {
				return createSortAdapter();
			}
			@Override
			public Adapter casePrimitiveSort(PrimitiveSort object) {
				return createPrimitiveSortAdapter();
			}
			@Override
			public Adapter caseUserSort(UserSort object) {
				return createUserSortAdapter();
			}
			@Override
			public Adapter caseStructure(Structure object) {
				return createStructureAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseProcess(org.ect.reo.mcrl2.Process object) {
				return createProcessAdapter();
			}
			@Override
			public Adapter caseCustomProcess(CustomProcess object) {
				return createCustomProcessAdapter();
			}
			@Override
			public Adapter caseAtom(Atom object) {
				return createAtomAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseAtomicAction(AtomicAction object) {
				return createAtomicActionAdapter();
			}
			@Override
			public Adapter caseCompositeAction(CompositeAction object) {
				return createCompositeActionAdapter();
			}
			@Override
			public Adapter caseSynchronization(Synchronization object) {
				return createSynchronizationAdapter();
			}
			@Override
			public Adapter caseParallelism(Parallelism object) {
				return createParallelismAdapter();
			}
			@Override
			public Adapter caseSequence(Sequence object) {
				return createSequenceAdapter();
			}
			@Override
			public Adapter caseChoice(Choice object) {
				return createChoiceAdapter();
			}
			@Override
			public Adapter caseInstance(Instance object) {
				return createInstanceAdapter();
			}
			@Override
			public Adapter caseImplication(Implication object) {
				return createImplicationAdapter();
			}
			@Override
			public Adapter caseCommunication(Communication object) {
				return createCommunicationAdapter();
			}
			@Override
			public Adapter caseAllow(Allow object) {
				return createAllowAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseHide(Hide object) {
				return createHideAdapter();
			}
			@Override
			public Adapter caseNameable(Nameable object) {
				return createNameableAdapter();
			}
			@Override
			public Adapter caseSummation(Summation object) {
				return createSummationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Nameable
	 * @generated
	 */
	public Adapter createNameableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Summation <em>Summation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Summation
	 * @generated
	 */
	public Adapter createSummationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Specification
	 * @generated
	 */
	public Adapter createSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Sort <em>Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Sort
	 * @generated
	 */
	public Adapter createSortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.PrimitiveSort <em>Primitive Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.PrimitiveSort
	 * @generated
	 */
	public Adapter createPrimitiveSortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.UserSort <em>User Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.UserSort
	 * @generated
	 */
	public Adapter createUserSortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Structure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Structure
	 * @generated
	 */
	public Adapter createStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.AtomicAction <em>Atomic Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.AtomicAction
	 * @generated
	 */
	public Adapter createAtomicActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.CompositeAction <em>Composite Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.CompositeAction
	 * @generated
	 */
	public Adapter createCompositeActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Atom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Atom
	 * @generated
	 */
	public Adapter createAtomAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Synchronization <em>Synchronization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Synchronization
	 * @generated
	 */
	public Adapter createSynchronizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Parallelism <em>Parallelism</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Parallelism
	 * @generated
	 */
	public Adapter createParallelismAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Sequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Sequence
	 * @generated
	 */
	public Adapter createSequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Choice <em>Choice</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Choice
	 * @generated
	 */
	public Adapter createChoiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Instance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Instance
	 * @generated
	 */
	public Adapter createInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Implication <em>Implication</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Implication
	 * @generated
	 */
	public Adapter createImplicationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Communication <em>Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Communication
	 * @generated
	 */
	public Adapter createCommunicationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Allow <em>Allow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Allow
	 * @generated
	 */
	public Adapter createAllowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Hide <em>Hide</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Hide
	 * @generated
	 */
	public Adapter createHideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.Process
	 * @generated
	 */
	public Adapter createProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.mcrl2.CustomProcess <em>Custom Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.mcrl2.CustomProcess
	 * @generated
	 */
	public Adapter createCustomProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //MCRL2AdapterFactory
