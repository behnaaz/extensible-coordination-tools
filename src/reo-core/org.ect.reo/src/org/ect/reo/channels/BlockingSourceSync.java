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
import org.ect.reo.animation.AnimationTable;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Blocking Source Sync</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSourceSync()
 * @model kind="class"
 * @generated
 */
//TODO add animation.  defaultFigureFactory  Describe your new note here.

public class BlockingSourceSync extends DirectedChannel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockingSourceSync() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.BLOCKING_SOURCE_SYNC;
	}

	@Override
	public boolean isSynchronous() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AnimationTable getAnimationTable() {
		// TODO Auto-generated method stub
		return null;
	}

} // BlockingSourceSync
