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
package org.ect.reo.components;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.DataAware;
import org.ect.reo.Delayable;
import org.ect.reo.Nameable;
import org.ect.reo.Primitive;
import org.ect.reo.PropertyHolder;
import org.ect.reo.Reconfigurable;
import org.ect.reo.animation.Animatable;
import org.ect.reo.colouring.Colourable;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.reo.components.ComponentsPackage
 * @generated
 */
public class ComponentsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ComponentsPackage.eINSTANCE;
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
	protected ComponentsSwitch<Adapter> modelSwitch =
		new ComponentsSwitch<Adapter>() {
			@Override
			public Adapter caseReader(Reader object) {
				return createReaderAdapter();
			}
			@Override
			public Adapter caseWriter(Writer object) {
				return createWriterAdapter();
			}
			@Override
			public Adapter caseSingleEndComponent(SingleEndComponent object) {
				return createSingleEndComponentAdapter();
			}
			@Override
			public Adapter caseConnectable(Connectable object) {
				return createConnectableAdapter();
			}
			@Override
			public Adapter caseColourable(Colourable object) {
				return createColourableAdapter();
			}
			@Override
			public Adapter caseAnimatable(Animatable object) {
				return createAnimatableAdapter();
			}
			@Override
			public Adapter caseDelayable(Delayable object) {
				return createDelayableAdapter();
			}
			@Override
			public Adapter caseReconfigurable(Reconfigurable object) {
				return createReconfigurableAdapter();
			}
			@Override
			public Adapter casePropertyHolder(PropertyHolder object) {
				return createPropertyHolderAdapter();
			}
			@Override
			public Adapter casePrimitive(Primitive object) {
				return createPrimitiveAdapter();
			}
			@Override
			public Adapter caseNameable(Nameable object) {
				return createNameableAdapter();
			}
			@Override
			public Adapter caseCustomPrimitive(CustomPrimitive object) {
				return createCustomPrimitiveAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseDataAware(DataAware object) {
				return createDataAwareAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.ect.reo.components.Reader <em>Reader</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.components.Reader
	 * @generated
	 */
	public Adapter createReaderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.components.Writer <em>Writer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.components.Writer
	 * @generated
	 */
	public Adapter createWriterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.components.SingleEndComponent <em>Single End Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.components.SingleEndComponent
	 * @generated
	 */
	public Adapter createSingleEndComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Connectable <em>Connectable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Connectable
	 * @generated
	 */
	public Adapter createConnectableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.colouring.Colourable <em>Colourable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.colouring.Colourable
	 * @generated
	 */
	public Adapter createColourableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.animation.Animatable <em>Animatable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.animation.Animatable
	 * @generated
	 */
	public Adapter createAnimatableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Delayable <em>Delayable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Delayable
	 * @generated
	 */
	public Adapter createDelayableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Reconfigurable <em>Reconfigurable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Reconfigurable
	 * @generated
	 */
	public Adapter createReconfigurableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.PropertyHolder <em>Property Holder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.PropertyHolder
	 * @generated
	 */
	public Adapter createPropertyHolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Primitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Primitive
	 * @generated
	 */
	public Adapter createPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Nameable
	 * @generated
	 */
	public Adapter createNameableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.CustomPrimitive <em>Custom Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.CustomPrimitive
	 * @generated
	 */
	public Adapter createCustomPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.DataAware <em>Data Aware</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.DataAware
	 * @generated
	 */
	public Adapter createDataAwareAdapter() {
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

} //ComponentsAdapterFactory
