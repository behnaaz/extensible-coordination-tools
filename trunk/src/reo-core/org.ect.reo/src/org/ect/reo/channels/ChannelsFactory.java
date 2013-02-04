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
 * @see org.ect.reo.channels.ChannelsPackage
 * @generated
 */
public class ChannelsFactory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ChannelsFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChannelsFactory init() {
		try {
			ChannelsFactory theChannelsFactory = (ChannelsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/reo/channels"); 
			if (theChannelsFactory != null) {
				return theChannelsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ChannelsFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChannelsFactory() {
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
			case ChannelsPackage.SYNC: return createSync();
			case ChannelsPackage.LOSSY_SYNC: return createLossySync();
			case ChannelsPackage.FIFO: return createFIFO();
			case ChannelsPackage.LOSSY_FIFO: return createLossyFIFO();
			case ChannelsPackage.SYNC_DRAIN: return createSyncDrain();
			case ChannelsPackage.ASYNC_DRAIN: return createAsyncDrain();
			case ChannelsPackage.SYNC_SPOUT: return createSyncSpout();
			case ChannelsPackage.ASYNC_SPOUT: return createAsyncSpout();
			case ChannelsPackage.FILTER: return createFilter();
			case ChannelsPackage.TRANSFORM: return createTransform();
			case ChannelsPackage.TIMER: return createTimer();
			case ChannelsPackage.CUSTOM_DIRECTED_CHANNEL: return createCustomDirectedChannel();
			case ChannelsPackage.CUSTOM_DRAIN_CHANNEL: return createCustomDrainChannel();
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL: return createCustomSpoutChannel();
			case ChannelsPackage.PRIORITY_SYNC: return createPrioritySync();
			case ChannelsPackage.BLOCKING_SOURCE_SYNC: return createBlockingSourceSync();
			case ChannelsPackage.BLOCKING_SINK_SYNC: return createBlockingSinkSync();
			case ChannelsPackage.BLOCKING_SYNC: return createBlockingSync();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sync createSync() {
		Sync sync = new Sync();
		return sync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LossySync createLossySync() {
		LossySync lossySync = new LossySync();
		return lossySync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FIFO createFIFO() {
		FIFO fifo = new FIFO();
		return fifo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyncDrain createSyncDrain() {
		SyncDrain syncDrain = new SyncDrain();
		return syncDrain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsyncDrain createAsyncDrain() {
		AsyncDrain asyncDrain = new AsyncDrain();
		return asyncDrain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyncSpout createSyncSpout() {
		SyncSpout syncSpout = new SyncSpout();
		return syncSpout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsyncSpout createAsyncSpout() {
		AsyncSpout asyncSpout = new AsyncSpout();
		return asyncSpout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filter createFilter() {
		Filter filter = new Filter();
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transform createTransform() {
		Transform transform = new Transform();
		return transform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Timer createTimer() {
		Timer timer = new Timer();
		return timer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomDirectedChannel createCustomDirectedChannel() {
		CustomDirectedChannel customDirectedChannel = new CustomDirectedChannel();
		return customDirectedChannel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomDrainChannel createCustomDrainChannel() {
		CustomDrainChannel customDrainChannel = new CustomDrainChannel();
		return customDrainChannel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomSpoutChannel createCustomSpoutChannel() {
		CustomSpoutChannel customSpoutChannel = new CustomSpoutChannel();
		return customSpoutChannel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrioritySync createPrioritySync() {
		PrioritySync prioritySync = new PrioritySync();
		return prioritySync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockingSourceSync createBlockingSourceSync() {
		BlockingSourceSync blockingSourceSync = new BlockingSourceSync();
		return blockingSourceSync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockingSinkSync createBlockingSinkSync() {
		BlockingSinkSync blockingSinkSync = new BlockingSinkSync();
		return blockingSinkSync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockingSync createBlockingSync() {
		BlockingSync blockingSync = new BlockingSync();
		return blockingSync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LossyFIFO createLossyFIFO() {
		LossyFIFO lossyFIFO = new LossyFIFO();
		return lossyFIFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChannelsPackage getChannelsPackage() {
		return (ChannelsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ChannelsPackage getPackage() {
		return ChannelsPackage.eINSTANCE;
	}

} //ChannelsFactory
