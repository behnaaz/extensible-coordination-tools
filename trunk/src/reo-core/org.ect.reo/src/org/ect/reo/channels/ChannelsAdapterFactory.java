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
package org.ect.reo.channels;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * @see org.ect.reo.channels.ChannelsPackage
 * @generated
 */
public class ChannelsAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ChannelsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChannelsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ChannelsPackage.eINSTANCE;
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
	protected ChannelsSwitch<Adapter> modelSwitch =
		new ChannelsSwitch<Adapter>() {
			@Override
			public Adapter caseChannel(Channel object) {
				return createChannelAdapter();
			}
			@Override
			public Adapter caseDirectedChannel(DirectedChannel object) {
				return createDirectedChannelAdapter();
			}
			@Override
			public Adapter caseDrainChannel(DrainChannel object) {
				return createDrainChannelAdapter();
			}
			@Override
			public Adapter caseSpoutChannel(SpoutChannel object) {
				return createSpoutChannelAdapter();
			}
			@Override
			public Adapter caseSync(Sync object) {
				return createSyncAdapter();
			}
			@Override
			public Adapter caseLossySync(LossySync object) {
				return createLossySyncAdapter();
			}
			@Override
			public Adapter caseFIFO(FIFO object) {
				return createFIFOAdapter();
			}
			@Override
			public Adapter caseLossyFIFO(LossyFIFO object) {
				return createLossyFIFOAdapter();
			}
			@Override
			public Adapter caseSyncDrain(SyncDrain object) {
				return createSyncDrainAdapter();
			}
			@Override
			public Adapter caseAsyncDrain(AsyncDrain object) {
				return createAsyncDrainAdapter();
			}
			@Override
			public Adapter caseSyncSpout(SyncSpout object) {
				return createSyncSpoutAdapter();
			}
			@Override
			public Adapter caseAsyncSpout(AsyncSpout object) {
				return createAsyncSpoutAdapter();
			}
			@Override
			public Adapter caseFilter(Filter object) {
				return createFilterAdapter();
			}
			@Override
			public Adapter caseTransform(Transform object) {
				return createTransformAdapter();
			}
			@Override
			public Adapter caseTimer(Timer object) {
				return createTimerAdapter();
			}
			@Override
			public Adapter caseCustomDirectedChannel(CustomDirectedChannel object) {
				return createCustomDirectedChannelAdapter();
			}
			@Override
			public Adapter caseCustomDrainChannel(CustomDrainChannel object) {
				return createCustomDrainChannelAdapter();
			}
			@Override
			public Adapter caseCustomSpoutChannel(CustomSpoutChannel object) {
				return createCustomSpoutChannelAdapter();
			}
			@Override
			public Adapter casePrioritySync(PrioritySync object) {
				return createPrioritySyncAdapter();
			}
			@Override
			public Adapter caseBlockingSourceSync(BlockingSourceSync object) {
				return createBlockingSourceSyncAdapter();
			}
			@Override
			public Adapter caseBlockingSinkSync(BlockingSinkSync object) {
				return createBlockingSinkSyncAdapter();
			}
			@Override
			public Adapter caseBlockingSync(BlockingSync object) {
				return createBlockingSyncAdapter();
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
			public Adapter caseDataAware(DataAware object) {
				return createDataAwareAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.Sync <em>Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.Sync
	 * @generated
	 */
	public Adapter createSyncAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.LossySync <em>Lossy Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.LossySync
	 * @generated
	 */
	public Adapter createLossySyncAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.FIFO <em>FIFO</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.FIFO
	 * @generated
	 */
	public Adapter createFIFOAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.LossyFIFO <em>Lossy FIFO</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.LossyFIFO
	 * @generated
	 */
	public Adapter createLossyFIFOAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.SyncDrain <em>Sync Drain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.SyncDrain
	 * @generated
	 */
	public Adapter createSyncDrainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.AsyncDrain <em>Async Drain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.AsyncDrain
	 * @generated
	 */
	public Adapter createAsyncDrainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.SyncSpout <em>Sync Spout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.SyncSpout
	 * @generated
	 */
	public Adapter createSyncSpoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.AsyncSpout <em>Async Spout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.AsyncSpout
	 * @generated
	 */
	public Adapter createAsyncSpoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.Filter
	 * @generated
	 */
	public Adapter createFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.Transform <em>Transform</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.Transform
	 * @generated
	 */
	public Adapter createTransformAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.Timer
	 * @generated
	 */
	public Adapter createTimerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.CustomDirectedChannel <em>Custom Directed Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.CustomDirectedChannel
	 * @generated
	 */
	public Adapter createCustomDirectedChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.CustomDrainChannel <em>Custom Drain Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.CustomDrainChannel
	 * @generated
	 */
	public Adapter createCustomDrainChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.CustomSpoutChannel <em>Custom Spout Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.CustomSpoutChannel
	 * @generated
	 */
	public Adapter createCustomSpoutChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.PrioritySync <em>Priority Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.PrioritySync
	 * @generated
	 */
	public Adapter createPrioritySyncAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.BlockingSourceSync <em>Blocking Source Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.BlockingSourceSync
	 * @generated
	 */
	public Adapter createBlockingSourceSyncAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.BlockingSinkSync <em>Blocking Sink Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.BlockingSinkSync
	 * @generated
	 */
	public Adapter createBlockingSinkSyncAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.BlockingSync <em>Blocking Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.BlockingSync
	 * @generated
	 */
	public Adapter createBlockingSyncAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.Channel
	 * @generated
	 */
	public Adapter createChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.DirectedChannel <em>Directed Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.DirectedChannel
	 * @generated
	 */
	public Adapter createDirectedChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.DrainChannel <em>Drain Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.DrainChannel
	 * @generated
	 */
	public Adapter createDrainChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.reo.channels.SpoutChannel <em>Spout Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.reo.channels.SpoutChannel
	 * @generated
	 */
	public Adapter createSpoutChannelAdapter() {
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

} //ChannelsAdapterFactory
