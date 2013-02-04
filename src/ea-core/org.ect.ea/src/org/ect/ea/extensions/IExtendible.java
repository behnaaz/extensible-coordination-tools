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
package org.ect.ea.extensions;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <code>IExtendible</code> is an interface that enforces
 * the classes that implement it to provide a list
 * where extensions can be registered. This interface
 * is part of the Extendible Automata model. Automata,
 * States and Transitions implement this interface and
 * hence, support so-called automata extensions.
 * 
 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtendible()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface IExtendible extends EObject {

	/**
	 * Get all extensions of this {@link IExtendible}.
	 * There should be no extensions with duplicate 
	 * ids for a specific {@link IExtendible}. 
	 * So {@link #findExtension(String)} should be 
	 * always well-defined.
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	EList<IExtension> getExtensions();

	/**
	 * Find a registered extension by its id.
	 * The method {@link IExtension#getId()}
	 * invoked of the result of this method, should
	 * return the parameter id.
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	IExtension findExtension(String id);

	/**
	 * Checks whether the given extension is already
	 * registered and adds it if not. If an extension
	 * with the same id already exists, it will be
	 * overridden by the new one.
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	void updateExtension(IExtension extension);

}

