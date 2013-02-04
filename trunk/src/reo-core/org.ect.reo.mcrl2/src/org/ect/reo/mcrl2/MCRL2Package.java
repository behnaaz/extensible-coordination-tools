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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ect.reo.mcrl2.MCRL2Factory
 * @model kind="package"
 * @generated
 */
public class MCRL2Package extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "mcrl2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/mcrl2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "mcrl2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final MCRL2Package eINSTANCE = org.ect.reo.mcrl2.MCRL2Package.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Specification <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Specification
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSpecification()
	 * @generated
	 */
	public static final int SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Atoms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPECIFICATION__ATOMS = 0;

	/**
	 * The feature id for the '<em><b>Processes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPECIFICATION__PROCESSES = 1;

	/**
	 * The feature id for the '<em><b>Sorts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPECIFICATION__SORTS = 2;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPECIFICATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Nameable <em>Nameable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Nameable
	 * @see org.ect.reo.mcrl2.MCRL2Package#getNameable()
	 * @generated
	 */
	public static final int NAMEABLE = 22;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Sort <em>Sort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Sort
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSort()
	 * @generated
	 */
	public static final int SORT = 1;

	/**
	 * The number of structural features of the '<em>Sort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SORT_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NAMEABLE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Nameable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NAMEABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.PrimitiveSort <em>Primitive Sort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.PrimitiveSort
	 * @see org.ect.reo.mcrl2.MCRL2Package#getPrimitiveSort()
	 * @generated
	 */
	public static final int PRIMITIVE_SORT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_SORT__NAME = NAMEABLE__NAME;

	/**
	 * The number of structural features of the '<em>Primitive Sort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_SORT_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.UserSort <em>User Sort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.UserSort
	 * @see org.ect.reo.mcrl2.MCRL2Package#getUserSort()
	 * @generated
	 */
	public static final int USER_SORT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_SORT__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_SORT__DEFINITION = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Sort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_SORT_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Structure <em>Structure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Structure
	 * @see org.ect.reo.mcrl2.MCRL2Package#getStructure()
	 * @generated
	 */
	public static final int STRUCTURE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRUCTURE__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRUCTURE__ELEMENTS = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Structure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRUCTURE_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Element <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Element
	 * @see org.ect.reo.mcrl2.MCRL2Package#getElement()
	 * @generated
	 */
	public static final int ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT__PARAMETERS = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Discriminator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT__DISCRIMINATOR_NAME = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Action <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Action
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAction()
	 * @generated
	 */
	public static final int ACTION = 9;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Process <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Process
	 * @see org.ect.reo.mcrl2.MCRL2Package#getProcess()
	 * @generated
	 */
	public static final int PROCESS = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS__PARAMETERS = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS__INITIAL = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS__ACTION = NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.CustomProcess <em>Custom Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.CustomProcess
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCustomProcess()
	 * @generated
	 */
	public static final int CUSTOM_PROCESS = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS__PARAMETERS = PROCESS__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS__INITIAL = PROCESS__INITIAL;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS__ACTION = PROCESS__ACTION;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS__DEFINITION = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PROCESS_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.AtomicAction <em>Atomic Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.AtomicAction
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtomicAction()
	 * @generated
	 */
	public static final int ATOMIC_ACTION = 10;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.CompositeAction <em>Composite Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.CompositeAction
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCompositeAction()
	 * @generated
	 */
	public static final int COMPOSITE_ACTION = 11;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Atom <em>Atom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Atom
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtom()
	 * @generated
	 */
	public static final int ATOM = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOM__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOM__TYPE = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOM__INITIAL = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Atom</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOM_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Atom</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOMIC_ACTION__ATOM = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOMIC_ACTION__ARGUMENTS = ACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOMIC_ACTION__TIME = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Atomic Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ATOMIC_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE_ACTION__ACTIONS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Synchronization <em>Synchronization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Synchronization
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSynchronization()
	 * @generated
	 */
	public static final int SYNCHRONIZATION = 12;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNCHRONIZATION__ACTIONS = COMPOSITE_ACTION__ACTIONS;

	/**
	 * The number of structural features of the '<em>Synchronization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNCHRONIZATION_FEATURE_COUNT = COMPOSITE_ACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Parallelism <em>Parallelism</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Parallelism
	 * @see org.ect.reo.mcrl2.MCRL2Package#getParallelism()
	 * @generated
	 */
	public static final int PARALLELISM = 13;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARALLELISM__ACTIONS = COMPOSITE_ACTION__ACTIONS;

	/**
	 * The number of structural features of the '<em>Parallelism</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARALLELISM_FEATURE_COUNT = COMPOSITE_ACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Sequence <em>Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Sequence
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSequence()
	 * @generated
	 */
	public static final int SEQUENCE = 14;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE__ACTIONS = COMPOSITE_ACTION__ACTIONS;

	/**
	 * The number of structural features of the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_FEATURE_COUNT = COMPOSITE_ACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Choice <em>Choice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Choice
	 * @see org.ect.reo.mcrl2.MCRL2Package#getChoice()
	 * @generated
	 */
	public static final int CHOICE = 15;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHOICE__ACTIONS = COMPOSITE_ACTION__ACTIONS;

	/**
	 * The number of structural features of the '<em>Choice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHOICE_FEATURE_COUNT = COMPOSITE_ACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Instance <em>Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Instance
	 * @see org.ect.reo.mcrl2.MCRL2Package#getInstance()
	 * @generated
	 */
	public static final int INSTANCE = 16;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INSTANCE__ARGUMENTS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INSTANCE__PROCESS = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INSTANCE_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Implication <em>Implication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Implication
	 * @see org.ect.reo.mcrl2.MCRL2Package#getImplication()
	 * @generated
	 */
	public static final int IMPLICATION = 17;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICATION__CONDITION = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICATION__ACTION = ACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICATION__ELSE_ACTION = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Implication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICATION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Communication <em>Communication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Communication
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCommunication()
	 * @generated
	 */
	public static final int COMMUNICATION = 18;

	/**
	 * The feature id for the '<em><b>Implications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMMUNICATION__IMPLICATIONS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parallelism</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMMUNICATION__PARALLELISM = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Communication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMMUNICATION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Allow <em>Allow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Allow
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAllow()
	 * @generated
	 */
	public static final int ALLOW = 19;

	/**
	 * The feature id for the '<em><b>Child</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ALLOW__CHILD = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Atoms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ALLOW__ATOMS = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Allow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ALLOW_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Block <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Block
	 * @see org.ect.reo.mcrl2.MCRL2Package#getBlock()
	 * @generated
	 */
	public static final int BLOCK = 20;

	/**
	 * The feature id for the '<em><b>Child</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK__CHILD = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Atoms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK__ATOMS = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Hide <em>Hide</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Hide
	 * @see org.ect.reo.mcrl2.MCRL2Package#getHide()
	 * @generated
	 */
	public static final int HIDE = 21;

	/**
	 * The feature id for the '<em><b>Child</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HIDE__CHILD = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Atoms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HIDE__ATOMS = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Hide</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HIDE_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.mcrl2.Summation <em>Summation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.mcrl2.Summation
	 * @see org.ect.reo.mcrl2.MCRL2Package#getSummation()
	 * @generated
	 */
	public static final int SUMMATION = 23;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SUMMATION__PARAMETERS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Summation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SUMMATION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveSortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userSortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parallelismEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass choiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass communicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hideEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass summationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customProcessEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.ect.reo.mcrl2.MCRL2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MCRL2Package() {
		super(eNS_URI, MCRL2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MCRL2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MCRL2Package init() {
		if (isInited) return (MCRL2Package)EPackage.Registry.INSTANCE.getEPackage(MCRL2Package.eNS_URI);

		// Obtain or create and register package
		MCRL2Package theMCRL2Package = (MCRL2Package)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MCRL2Package ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MCRL2Package());

		isInited = true;

		// Create package meta-data objects
		theMCRL2Package.createPackageContents();

		// Initialize created meta-data
		theMCRL2Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMCRL2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MCRL2Package.eNS_URI, theMCRL2Package);
		return theMCRL2Package;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see org.ect.reo.mcrl2.Specification
	 * @generated
	 */
	public EClass getSpecification() {
		return specificationEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Specification#getAtoms <em>Atoms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Atoms</em>'.
	 * @see org.ect.reo.mcrl2.Specification#getAtoms()
	 * @see #getSpecification()
	 * @generated
	 */
	public EReference getSpecification_Atoms() {
		return (EReference)specificationEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Specification#getProcesses <em>Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Processes</em>'.
	 * @see org.ect.reo.mcrl2.Specification#getProcesses()
	 * @see #getSpecification()
	 * @generated
	 */
	public EReference getSpecification_Processes() {
		return (EReference)specificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Specification#getSorts <em>Sorts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sorts</em>'.
	 * @see org.ect.reo.mcrl2.Specification#getSorts()
	 * @see #getSpecification()
	 * @generated
	 */
	public EReference getSpecification_Sorts() {
		return (EReference)specificationEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Sort <em>Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sort</em>'.
	 * @see org.ect.reo.mcrl2.Sort
	 * @generated
	 */
	public EClass getSort() {
		return sortEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.PrimitiveSort <em>Primitive Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Sort</em>'.
	 * @see org.ect.reo.mcrl2.PrimitiveSort
	 * @generated
	 */
	public EClass getPrimitiveSort() {
		return primitiveSortEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.UserSort <em>User Sort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Sort</em>'.
	 * @see org.ect.reo.mcrl2.UserSort
	 * @generated
	 */
	public EClass getUserSort() {
		return userSortEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.UserSort#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.ect.reo.mcrl2.UserSort#getDefinition()
	 * @see #getUserSort()
	 * @generated
	 */
	public EAttribute getUserSort_Definition() {
		return (EAttribute)userSortEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Structure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structure</em>'.
	 * @see org.ect.reo.mcrl2.Structure
	 * @generated
	 */
	public EClass getStructure() {
		return structureEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Structure#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.ect.reo.mcrl2.Structure#getElements()
	 * @see #getStructure()
	 * @generated
	 */
	public EReference getStructure_Elements() {
		return (EReference)structureEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.ect.reo.mcrl2.Element
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Element#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.ect.reo.mcrl2.Element#getParameters()
	 * @see #getElement()
	 * @generated
	 */
	public EReference getElement_Parameters() {
		return (EReference)elementEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.Element#getDiscriminatorName <em>Discriminator Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Discriminator Name</em>'.
	 * @see org.ect.reo.mcrl2.Element#getDiscriminatorName()
	 * @see #getElement()
	 * @generated
	 */
	public EAttribute getElement_DiscriminatorName() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.ect.reo.mcrl2.Action
	 * @generated
	 */
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.AtomicAction <em>Atomic Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atomic Action</em>'.
	 * @see org.ect.reo.mcrl2.AtomicAction
	 * @generated
	 */
	public EClass getAtomicAction() {
		return atomicActionEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.mcrl2.AtomicAction#getAtom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Atom</em>'.
	 * @see org.ect.reo.mcrl2.AtomicAction#getAtom()
	 * @see #getAtomicAction()
	 * @generated
	 */
	public EReference getAtomicAction_Atom() {
		return (EReference)atomicActionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.reo.mcrl2.AtomicAction#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Arguments</em>'.
	 * @see org.ect.reo.mcrl2.AtomicAction#getArguments()
	 * @see #getAtomicAction()
	 * @generated
	 */
	public EAttribute getAtomicAction_Arguments() {
		return (EAttribute)atomicActionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.AtomicAction#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.ect.reo.mcrl2.AtomicAction#getTime()
	 * @see #getAtomicAction()
	 * @generated
	 */
	public EAttribute getAtomicAction_Time() {
		return (EAttribute)atomicActionEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.CompositeAction <em>Composite Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Action</em>'.
	 * @see org.ect.reo.mcrl2.CompositeAction
	 * @generated
	 */
	public EClass getCompositeAction() {
		return compositeActionEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.CompositeAction#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see org.ect.reo.mcrl2.CompositeAction#getActions()
	 * @see #getCompositeAction()
	 * @generated
	 */
	public EReference getCompositeAction_Actions() {
		return (EReference)compositeActionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Atom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atom</em>'.
	 * @see org.ect.reo.mcrl2.Atom
	 * @generated
	 */
	public EClass getAtom() {
		return atomEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.mcrl2.Atom#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.ect.reo.mcrl2.Atom#getType()
	 * @see #getAtom()
	 * @generated
	 */
	public EReference getAtom_Type() {
		return (EReference)atomEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.Atom#getInitial <em>Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial</em>'.
	 * @see org.ect.reo.mcrl2.Atom#getInitial()
	 * @see #getAtom()
	 * @generated
	 */
	public EAttribute getAtom_Initial() {
		return (EAttribute)atomEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Synchronization <em>Synchronization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronization</em>'.
	 * @see org.ect.reo.mcrl2.Synchronization
	 * @generated
	 */
	public EClass getSynchronization() {
		return synchronizationEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Parallelism <em>Parallelism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parallelism</em>'.
	 * @see org.ect.reo.mcrl2.Parallelism
	 * @generated
	 */
	public EClass getParallelism() {
		return parallelismEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Sequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see org.ect.reo.mcrl2.Sequence
	 * @generated
	 */
	public EClass getSequence() {
		return sequenceEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Choice <em>Choice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice</em>'.
	 * @see org.ect.reo.mcrl2.Choice
	 * @generated
	 */
	public EClass getChoice() {
		return choiceEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Instance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance</em>'.
	 * @see org.ect.reo.mcrl2.Instance
	 * @generated
	 */
	public EClass getInstance() {
		return instanceEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.reo.mcrl2.Instance#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Arguments</em>'.
	 * @see org.ect.reo.mcrl2.Instance#getArguments()
	 * @see #getInstance()
	 * @generated
	 */
	public EAttribute getInstance_Arguments() {
		return (EAttribute)instanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.mcrl2.Instance#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Process</em>'.
	 * @see org.ect.reo.mcrl2.Instance#getProcess()
	 * @see #getInstance()
	 * @generated
	 */
	public EReference getInstance_Process() {
		return (EReference)instanceEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Implication <em>Implication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implication</em>'.
	 * @see org.ect.reo.mcrl2.Implication
	 * @generated
	 */
	public EClass getImplication() {
		return implicationEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.Implication#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see org.ect.reo.mcrl2.Implication#getCondition()
	 * @see #getImplication()
	 * @generated
	 */
	public EAttribute getImplication_Condition() {
		return (EAttribute)implicationEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Implication#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.ect.reo.mcrl2.Implication#getAction()
	 * @see #getImplication()
	 * @generated
	 */
	public EReference getImplication_Action() {
		return (EReference)implicationEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Implication#getElseAction <em>Else Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Action</em>'.
	 * @see org.ect.reo.mcrl2.Implication#getElseAction()
	 * @see #getImplication()
	 * @generated
	 */
	public EReference getImplication_ElseAction() {
		return (EReference)implicationEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Communication <em>Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Communication</em>'.
	 * @see org.ect.reo.mcrl2.Communication
	 * @generated
	 */
	public EClass getCommunication() {
		return communicationEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Communication#getImplications <em>Implications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Implications</em>'.
	 * @see org.ect.reo.mcrl2.Communication#getImplications()
	 * @see #getCommunication()
	 * @generated
	 */
	public EReference getCommunication_Implications() {
		return (EReference)communicationEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Communication#getParallelism <em>Parallelism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parallelism</em>'.
	 * @see org.ect.reo.mcrl2.Communication#getParallelism()
	 * @see #getCommunication()
	 * @generated
	 */
	public EReference getCommunication_Parallelism() {
		return (EReference)communicationEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Allow <em>Allow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Allow</em>'.
	 * @see org.ect.reo.mcrl2.Allow
	 * @generated
	 */
	public EClass getAllow() {
		return allowEClass;
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Allow#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Child</em>'.
	 * @see org.ect.reo.mcrl2.Allow#getChild()
	 * @see #getAllow()
	 * @generated
	 */
	public EReference getAllow_Child() {
		return (EReference)allowEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.mcrl2.Allow#getAtoms <em>Atoms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Atoms</em>'.
	 * @see org.ect.reo.mcrl2.Allow#getAtoms()
	 * @see #getAllow()
	 * @generated
	 */
	public EReference getAllow_Atoms() {
		return (EReference)allowEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see org.ect.reo.mcrl2.Block
	 * @generated
	 */
	public EClass getBlock() {
		return blockEClass;
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Block#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Child</em>'.
	 * @see org.ect.reo.mcrl2.Block#getChild()
	 * @see #getBlock()
	 * @generated
	 */
	public EReference getBlock_Child() {
		return (EReference)blockEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.mcrl2.Block#getAtoms <em>Atoms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Atoms</em>'.
	 * @see org.ect.reo.mcrl2.Block#getAtoms()
	 * @see #getBlock()
	 * @generated
	 */
	public EReference getBlock_Atoms() {
		return (EReference)blockEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Hide <em>Hide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hide</em>'.
	 * @see org.ect.reo.mcrl2.Hide
	 * @generated
	 */
	public EClass getHide() {
		return hideEClass;
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Hide#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Child</em>'.
	 * @see org.ect.reo.mcrl2.Hide#getChild()
	 * @see #getHide()
	 * @generated
	 */
	public EReference getHide_Child() {
		return (EReference)hideEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.mcrl2.Hide#getAtoms <em>Atoms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Atoms</em>'.
	 * @see org.ect.reo.mcrl2.Hide#getAtoms()
	 * @see #getHide()
	 * @generated
	 */
	public EReference getHide_Atoms() {
		return (EReference)hideEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nameable</em>'.
	 * @see org.ect.reo.mcrl2.Nameable
	 * @generated
	 */
	public EClass getNameable() {
		return nameableEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.Nameable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ect.reo.mcrl2.Nameable#getName()
	 * @see #getNameable()
	 * @generated
	 */
	public EAttribute getNameable_Name() {
		return (EAttribute)nameableEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Summation <em>Summation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Summation</em>'.
	 * @see org.ect.reo.mcrl2.Summation
	 * @generated
	 */
	public EClass getSummation() {
		return summationEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Summation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.ect.reo.mcrl2.Summation#getParameters()
	 * @see #getSummation()
	 * @generated
	 */
	public EReference getSummation_Parameters() {
		return (EReference)summationEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see org.ect.reo.mcrl2.Process
	 * @generated
	 */
	public EClass getProcess() {
		return processEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.mcrl2.Process#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.ect.reo.mcrl2.Process#getParameters()
	 * @see #getProcess()
	 * @generated
	 */
	public EReference getProcess_Parameters() {
		return (EReference)processEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.Process#isInitial <em>Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial</em>'.
	 * @see org.ect.reo.mcrl2.Process#isInitial()
	 * @see #getProcess()
	 * @generated
	 */
	public EAttribute getProcess_Initial() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.mcrl2.Process#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.ect.reo.mcrl2.Process#getAction()
	 * @see #getProcess()
	 * @generated
	 */
	public EReference getProcess_Action() {
		return (EReference)processEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.mcrl2.CustomProcess <em>Custom Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Process</em>'.
	 * @see org.ect.reo.mcrl2.CustomProcess
	 * @generated
	 */
	public EClass getCustomProcess() {
		return customProcessEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.mcrl2.CustomProcess#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.ect.reo.mcrl2.CustomProcess#getDefinition()
	 * @see #getCustomProcess()
	 * @generated
	 */
	public EAttribute getCustomProcess_Definition() {
		return (EAttribute)customProcessEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public MCRL2Factory getMCRL2Factory() {
		return (MCRL2Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		specificationEClass = createEClass(SPECIFICATION);
		createEReference(specificationEClass, SPECIFICATION__ATOMS);
		createEReference(specificationEClass, SPECIFICATION__PROCESSES);
		createEReference(specificationEClass, SPECIFICATION__SORTS);

		sortEClass = createEClass(SORT);

		primitiveSortEClass = createEClass(PRIMITIVE_SORT);

		userSortEClass = createEClass(USER_SORT);
		createEAttribute(userSortEClass, USER_SORT__DEFINITION);

		structureEClass = createEClass(STRUCTURE);
		createEReference(structureEClass, STRUCTURE__ELEMENTS);

		elementEClass = createEClass(ELEMENT);
		createEReference(elementEClass, ELEMENT__PARAMETERS);
		createEAttribute(elementEClass, ELEMENT__DISCRIMINATOR_NAME);

		processEClass = createEClass(PROCESS);
		createEReference(processEClass, PROCESS__PARAMETERS);
		createEAttribute(processEClass, PROCESS__INITIAL);
		createEReference(processEClass, PROCESS__ACTION);

		customProcessEClass = createEClass(CUSTOM_PROCESS);
		createEAttribute(customProcessEClass, CUSTOM_PROCESS__DEFINITION);

		atomEClass = createEClass(ATOM);
		createEReference(atomEClass, ATOM__TYPE);
		createEAttribute(atomEClass, ATOM__INITIAL);

		actionEClass = createEClass(ACTION);

		atomicActionEClass = createEClass(ATOMIC_ACTION);
		createEReference(atomicActionEClass, ATOMIC_ACTION__ATOM);
		createEAttribute(atomicActionEClass, ATOMIC_ACTION__ARGUMENTS);
		createEAttribute(atomicActionEClass, ATOMIC_ACTION__TIME);

		compositeActionEClass = createEClass(COMPOSITE_ACTION);
		createEReference(compositeActionEClass, COMPOSITE_ACTION__ACTIONS);

		synchronizationEClass = createEClass(SYNCHRONIZATION);

		parallelismEClass = createEClass(PARALLELISM);

		sequenceEClass = createEClass(SEQUENCE);

		choiceEClass = createEClass(CHOICE);

		instanceEClass = createEClass(INSTANCE);
		createEAttribute(instanceEClass, INSTANCE__ARGUMENTS);
		createEReference(instanceEClass, INSTANCE__PROCESS);

		implicationEClass = createEClass(IMPLICATION);
		createEAttribute(implicationEClass, IMPLICATION__CONDITION);
		createEReference(implicationEClass, IMPLICATION__ACTION);
		createEReference(implicationEClass, IMPLICATION__ELSE_ACTION);

		communicationEClass = createEClass(COMMUNICATION);
		createEReference(communicationEClass, COMMUNICATION__IMPLICATIONS);
		createEReference(communicationEClass, COMMUNICATION__PARALLELISM);

		allowEClass = createEClass(ALLOW);
		createEReference(allowEClass, ALLOW__CHILD);
		createEReference(allowEClass, ALLOW__ATOMS);

		blockEClass = createEClass(BLOCK);
		createEReference(blockEClass, BLOCK__CHILD);
		createEReference(blockEClass, BLOCK__ATOMS);

		hideEClass = createEClass(HIDE);
		createEReference(hideEClass, HIDE__CHILD);
		createEReference(hideEClass, HIDE__ATOMS);

		nameableEClass = createEClass(NAMEABLE);
		createEAttribute(nameableEClass, NAMEABLE__NAME);

		summationEClass = createEClass(SUMMATION);
		createEReference(summationEClass, SUMMATION__PARAMETERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		primitiveSortEClass.getESuperTypes().add(this.getNameable());
		primitiveSortEClass.getESuperTypes().add(this.getSort());
		userSortEClass.getESuperTypes().add(this.getNameable());
		userSortEClass.getESuperTypes().add(this.getSort());
		structureEClass.getESuperTypes().add(this.getNameable());
		structureEClass.getESuperTypes().add(this.getSort());
		elementEClass.getESuperTypes().add(this.getNameable());
		processEClass.getESuperTypes().add(this.getNameable());
		customProcessEClass.getESuperTypes().add(this.getProcess());
		atomEClass.getESuperTypes().add(this.getNameable());
		atomicActionEClass.getESuperTypes().add(this.getAction());
		compositeActionEClass.getESuperTypes().add(this.getAction());
		synchronizationEClass.getESuperTypes().add(this.getCompositeAction());
		parallelismEClass.getESuperTypes().add(this.getCompositeAction());
		sequenceEClass.getESuperTypes().add(this.getCompositeAction());
		choiceEClass.getESuperTypes().add(this.getCompositeAction());
		instanceEClass.getESuperTypes().add(this.getAction());
		implicationEClass.getESuperTypes().add(this.getAction());
		communicationEClass.getESuperTypes().add(this.getAction());
		allowEClass.getESuperTypes().add(this.getAction());
		blockEClass.getESuperTypes().add(this.getAction());
		hideEClass.getESuperTypes().add(this.getAction());
		summationEClass.getESuperTypes().add(this.getAction());

		// Initialize classes and features; add operations and parameters
		initEClass(specificationEClass, Specification.class, "Specification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecification_Atoms(), this.getAtom(), null, "atoms", null, 0, -1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecification_Processes(), this.getProcess(), null, "processes", null, 0, -1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecification_Sorts(), this.getSort(), null, "sorts", null, 0, -1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortEClass, Sort.class, "Sort", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(sortEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(primitiveSortEClass, PrimitiveSort.class, "PrimitiveSort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userSortEClass, UserSort.class, "UserSort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserSort_Definition(), ecorePackage.getEString(), "definition", null, 0, 1, UserSort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structureEClass, Structure.class, "Structure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructure_Elements(), this.getElement(), null, "elements", null, 0, -1, Structure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementEClass, Element.class, "Element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElement_Parameters(), this.getAtom(), null, "parameters", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElement_DiscriminatorName(), ecorePackage.getEString(), "discriminatorName", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processEClass, org.ect.reo.mcrl2.Process.class, "Process", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcess_Parameters(), this.getAtom(), null, "parameters", null, 0, -1, org.ect.reo.mcrl2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_Initial(), ecorePackage.getEBoolean(), "initial", null, 0, 1, org.ect.reo.mcrl2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Action(), this.getAction(), null, "action", null, 0, 1, org.ect.reo.mcrl2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customProcessEClass, CustomProcess.class, "CustomProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomProcess_Definition(), ecorePackage.getEString(), "definition", null, 0, 1, CustomProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(atomEClass, Atom.class, "Atom", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAtom_Type(), this.getSort(), null, "type", null, 0, 1, Atom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAtom_Initial(), ecorePackage.getEString(), "initial", null, 0, 1, Atom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(atomicActionEClass, AtomicAction.class, "AtomicAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAtomicAction_Atom(), this.getAtom(), null, "atom", null, 0, 1, AtomicAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAtomicAction_Arguments(), ecorePackage.getEString(), "arguments", null, 0, -1, AtomicAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAtomicAction_Time(), ecorePackage.getEString(), "time", null, 0, 1, AtomicAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeActionEClass, CompositeAction.class, "CompositeAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeAction_Actions(), this.getAction(), null, "actions", null, 0, -1, CompositeAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(compositeActionEClass, ecorePackage.getEString(), "operator", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(synchronizationEClass, Synchronization.class, "Synchronization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parallelismEClass, Parallelism.class, "Parallelism", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequenceEClass, Sequence.class, "Sequence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(choiceEClass, Choice.class, "Choice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(instanceEClass, Instance.class, "Instance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstance_Arguments(), ecorePackage.getEString(), "arguments", null, 0, -1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstance_Process(), this.getProcess(), null, "process", null, 0, 1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(implicationEClass, Implication.class, "Implication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImplication_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, Implication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplication_Action(), this.getAction(), null, "action", null, 0, 1, Implication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplication_ElseAction(), this.getAction(), null, "elseAction", null, 0, 1, Implication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(communicationEClass, Communication.class, "Communication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCommunication_Implications(), this.getImplication(), null, "implications", null, 0, -1, Communication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommunication_Parallelism(), this.getParallelism(), null, "parallelism", null, 0, 1, Communication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(allowEClass, Allow.class, "Allow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAllow_Child(), this.getAction(), null, "child", null, 0, 1, Allow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAllow_Atoms(), this.getAtom(), null, "atoms", null, 0, -1, Allow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlock_Child(), this.getAction(), null, "child", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlock_Atoms(), this.getAtom(), null, "atoms", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hideEClass, Hide.class, "Hide", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHide_Child(), this.getAction(), null, "child", null, 0, 1, Hide.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHide_Atoms(), this.getAtom(), null, "atoms", null, 0, -1, Hide.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameableEClass, Nameable.class, "Nameable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNameable_Name(), ecorePackage.getEString(), "name", null, 0, 1, Nameable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(summationEClass, Summation.class, "Summation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSummation_Parameters(), this.getAtom(), null, "parameters", null, 0, -1, Summation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Specification <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Specification
		 * @see org.ect.reo.mcrl2.MCRL2Package#getSpecification()
		 * @generated
		 */
		public static final EClass SPECIFICATION = eINSTANCE.getSpecification();

		/**
		 * The meta object literal for the '<em><b>Atoms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SPECIFICATION__ATOMS = eINSTANCE.getSpecification_Atoms();

		/**
		 * The meta object literal for the '<em><b>Processes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SPECIFICATION__PROCESSES = eINSTANCE.getSpecification_Processes();

		/**
		 * The meta object literal for the '<em><b>Sorts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SPECIFICATION__SORTS = eINSTANCE.getSpecification_Sorts();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Sort <em>Sort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Sort
		 * @see org.ect.reo.mcrl2.MCRL2Package#getSort()
		 * @generated
		 */
		public static final EClass SORT = eINSTANCE.getSort();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.PrimitiveSort <em>Primitive Sort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.PrimitiveSort
		 * @see org.ect.reo.mcrl2.MCRL2Package#getPrimitiveSort()
		 * @generated
		 */
		public static final EClass PRIMITIVE_SORT = eINSTANCE.getPrimitiveSort();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.UserSort <em>User Sort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.UserSort
		 * @see org.ect.reo.mcrl2.MCRL2Package#getUserSort()
		 * @generated
		 */
		public static final EClass USER_SORT = eINSTANCE.getUserSort();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute USER_SORT__DEFINITION = eINSTANCE.getUserSort_Definition();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Structure <em>Structure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Structure
		 * @see org.ect.reo.mcrl2.MCRL2Package#getStructure()
		 * @generated
		 */
		public static final EClass STRUCTURE = eINSTANCE.getStructure();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference STRUCTURE__ELEMENTS = eINSTANCE.getStructure_Elements();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Element <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Element
		 * @see org.ect.reo.mcrl2.MCRL2Package#getElement()
		 * @generated
		 */
		public static final EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ELEMENT__PARAMETERS = eINSTANCE.getElement_Parameters();

		/**
		 * The meta object literal for the '<em><b>Discriminator Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ELEMENT__DISCRIMINATOR_NAME = eINSTANCE.getElement_DiscriminatorName();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Action <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Action
		 * @see org.ect.reo.mcrl2.MCRL2Package#getAction()
		 * @generated
		 */
		public static final EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.AtomicAction <em>Atomic Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.AtomicAction
		 * @see org.ect.reo.mcrl2.MCRL2Package#getAtomicAction()
		 * @generated
		 */
		public static final EClass ATOMIC_ACTION = eINSTANCE.getAtomicAction();

		/**
		 * The meta object literal for the '<em><b>Atom</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ATOMIC_ACTION__ATOM = eINSTANCE.getAtomicAction_Atom();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ATOMIC_ACTION__ARGUMENTS = eINSTANCE.getAtomicAction_Arguments();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ATOMIC_ACTION__TIME = eINSTANCE.getAtomicAction_Time();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.CompositeAction <em>Composite Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.CompositeAction
		 * @see org.ect.reo.mcrl2.MCRL2Package#getCompositeAction()
		 * @generated
		 */
		public static final EClass COMPOSITE_ACTION = eINSTANCE.getCompositeAction();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COMPOSITE_ACTION__ACTIONS = eINSTANCE.getCompositeAction_Actions();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Atom <em>Atom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Atom
		 * @see org.ect.reo.mcrl2.MCRL2Package#getAtom()
		 * @generated
		 */
		public static final EClass ATOM = eINSTANCE.getAtom();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ATOM__TYPE = eINSTANCE.getAtom_Type();

		/**
		 * The meta object literal for the '<em><b>Initial</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ATOM__INITIAL = eINSTANCE.getAtom_Initial();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Synchronization <em>Synchronization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Synchronization
		 * @see org.ect.reo.mcrl2.MCRL2Package#getSynchronization()
		 * @generated
		 */
		public static final EClass SYNCHRONIZATION = eINSTANCE.getSynchronization();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Parallelism <em>Parallelism</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Parallelism
		 * @see org.ect.reo.mcrl2.MCRL2Package#getParallelism()
		 * @generated
		 */
		public static final EClass PARALLELISM = eINSTANCE.getParallelism();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Sequence <em>Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Sequence
		 * @see org.ect.reo.mcrl2.MCRL2Package#getSequence()
		 * @generated
		 */
		public static final EClass SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Choice <em>Choice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Choice
		 * @see org.ect.reo.mcrl2.MCRL2Package#getChoice()
		 * @generated
		 */
		public static final EClass CHOICE = eINSTANCE.getChoice();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Instance <em>Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Instance
		 * @see org.ect.reo.mcrl2.MCRL2Package#getInstance()
		 * @generated
		 */
		public static final EClass INSTANCE = eINSTANCE.getInstance();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INSTANCE__ARGUMENTS = eINSTANCE.getInstance_Arguments();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference INSTANCE__PROCESS = eINSTANCE.getInstance_Process();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Implication <em>Implication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Implication
		 * @see org.ect.reo.mcrl2.MCRL2Package#getImplication()
		 * @generated
		 */
		public static final EClass IMPLICATION = eINSTANCE.getImplication();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute IMPLICATION__CONDITION = eINSTANCE.getImplication_Condition();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IMPLICATION__ACTION = eINSTANCE.getImplication_Action();

		/**
		 * The meta object literal for the '<em><b>Else Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IMPLICATION__ELSE_ACTION = eINSTANCE.getImplication_ElseAction();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Communication <em>Communication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Communication
		 * @see org.ect.reo.mcrl2.MCRL2Package#getCommunication()
		 * @generated
		 */
		public static final EClass COMMUNICATION = eINSTANCE.getCommunication();

		/**
		 * The meta object literal for the '<em><b>Implications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COMMUNICATION__IMPLICATIONS = eINSTANCE.getCommunication_Implications();

		/**
		 * The meta object literal for the '<em><b>Parallelism</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COMMUNICATION__PARALLELISM = eINSTANCE.getCommunication_Parallelism();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Allow <em>Allow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Allow
		 * @see org.ect.reo.mcrl2.MCRL2Package#getAllow()
		 * @generated
		 */
		public static final EClass ALLOW = eINSTANCE.getAllow();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ALLOW__CHILD = eINSTANCE.getAllow_Child();

		/**
		 * The meta object literal for the '<em><b>Atoms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ALLOW__ATOMS = eINSTANCE.getAllow_Atoms();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Block <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Block
		 * @see org.ect.reo.mcrl2.MCRL2Package#getBlock()
		 * @generated
		 */
		public static final EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference BLOCK__CHILD = eINSTANCE.getBlock_Child();

		/**
		 * The meta object literal for the '<em><b>Atoms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference BLOCK__ATOMS = eINSTANCE.getBlock_Atoms();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Hide <em>Hide</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Hide
		 * @see org.ect.reo.mcrl2.MCRL2Package#getHide()
		 * @generated
		 */
		public static final EClass HIDE = eINSTANCE.getHide();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference HIDE__CHILD = eINSTANCE.getHide_Child();

		/**
		 * The meta object literal for the '<em><b>Atoms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference HIDE__ATOMS = eINSTANCE.getHide_Atoms();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Nameable <em>Nameable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Nameable
		 * @see org.ect.reo.mcrl2.MCRL2Package#getNameable()
		 * @generated
		 */
		public static final EClass NAMEABLE = eINSTANCE.getNameable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute NAMEABLE__NAME = eINSTANCE.getNameable_Name();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Summation <em>Summation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Summation
		 * @see org.ect.reo.mcrl2.MCRL2Package#getSummation()
		 * @generated
		 */
		public static final EClass SUMMATION = eINSTANCE.getSummation();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SUMMATION__PARAMETERS = eINSTANCE.getSummation_Parameters();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.Process <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.Process
		 * @see org.ect.reo.mcrl2.MCRL2Package#getProcess()
		 * @generated
		 */
		public static final EClass PROCESS = eINSTANCE.getProcess();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PROCESS__PARAMETERS = eINSTANCE.getProcess_Parameters();

		/**
		 * The meta object literal for the '<em><b>Initial</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PROCESS__INITIAL = eINSTANCE.getProcess_Initial();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PROCESS__ACTION = eINSTANCE.getProcess_Action();

		/**
		 * The meta object literal for the '{@link org.ect.reo.mcrl2.CustomProcess <em>Custom Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.mcrl2.CustomProcess
		 * @see org.ect.reo.mcrl2.MCRL2Package#getCustomProcess()
		 * @generated
		 */
		public static final EClass CUSTOM_PROCESS = eINSTANCE.getCustomProcess();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CUSTOM_PROCESS__DEFINITION = eINSTANCE.getCustomProcess_Definition();

	}

} //MCRL2Package
