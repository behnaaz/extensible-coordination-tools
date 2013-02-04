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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.reo.mcrl2.MCRL2Package
 * @generated
 */
public class MCRL2Factory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final MCRL2Factory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MCRL2Factory init() {
		try {
			MCRL2Factory theMCRL2Factory = (MCRL2Factory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/mcrl2"); 
			if (theMCRL2Factory != null) {
				return theMCRL2Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MCRL2Factory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MCRL2Factory() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MCRL2Package.SPECIFICATION: return createSpecification();
			case MCRL2Package.PRIMITIVE_SORT: return createPrimitiveSort();
			case MCRL2Package.USER_SORT: return createUserSort();
			case MCRL2Package.STRUCTURE: return createStructure();
			case MCRL2Package.ELEMENT: return createElement();
			case MCRL2Package.PROCESS: return createProcess();
			case MCRL2Package.CUSTOM_PROCESS: return createCustomProcess();
			case MCRL2Package.ATOM: return createAtom();
			case MCRL2Package.ATOMIC_ACTION: return createAtomicAction();
			case MCRL2Package.SYNCHRONIZATION: return createSynchronization();
			case MCRL2Package.PARALLELISM: return createParallelism();
			case MCRL2Package.SEQUENCE: return createSequence();
			case MCRL2Package.CHOICE: return createChoice();
			case MCRL2Package.INSTANCE: return createInstance();
			case MCRL2Package.IMPLICATION: return createImplication();
			case MCRL2Package.COMMUNICATION: return createCommunication();
			case MCRL2Package.ALLOW: return createAllow();
			case MCRL2Package.BLOCK: return createBlock();
			case MCRL2Package.HIDE: return createHide();
			case MCRL2Package.SUMMATION: return createSummation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Specification createSpecification() {
		Specification specification = new Specification();
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveSort createPrimitiveSort() {
		PrimitiveSort primitiveSort = new PrimitiveSort();
		return primitiveSort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserSort createUserSort() {
		UserSort userSort = new UserSort();
		return userSort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Structure createStructure() {
		Structure structure = new Structure();
		return structure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element createElement() {
		Element element = new Element();
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Process createProcess() {
		Process process = new Process();
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomProcess createCustomProcess() {
		CustomProcess customProcess = new CustomProcess();
		return customProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Atom createAtom() {
		Atom atom = new Atom();
		return atom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicAction createAtomicAction() {
		AtomicAction atomicAction = new AtomicAction();
		return atomicAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Synchronization createSynchronization() {
		Synchronization synchronization = new Synchronization();
		return synchronization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parallelism createParallelism() {
		Parallelism parallelism = new Parallelism();
		return parallelism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence createSequence() {
		Sequence sequence = new Sequence();
		return sequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Choice createChoice() {
		Choice choice = new Choice();
		return choice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instance createInstance() {
		Instance instance = new Instance();
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implication createImplication() {
		Implication implication = new Implication();
		return implication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Communication createCommunication() {
		Communication communication = new Communication();
		return communication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Allow createAllow() {
		Allow allow = new Allow();
		return allow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		Block block = new Block();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hide createHide() {
		Hide hide = new Hide();
		return hide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Summation createSummation() {
		Summation summation = new Summation();
		return summation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MCRL2Package getMCRL2Package() {
		return (MCRL2Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MCRL2Package getPackage() {
		return MCRL2Package.eINSTANCE;
	}

} //MCRL2Factory
