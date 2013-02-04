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
package org.ect.ea;

import org.eclipse.jface.resource.ImageDescriptor;



/**
 * Interface for automata extension definitions.
 * @author Christian Koehler
 * @generated NOT
 */
public interface IExtensionDefinition {
	
	/**
	 * Type identifier for textual extension providers.
	 * @see #getType()
	 * @see ITextualExtensionProvider
	 */
	public static final String TEXTUAL_EXTENSION = "textual";
	
	/**
	 * Type identifier for customs extension providers.
	 * @see #getType()
	 * @see ITextualExtensionProvider
	 */
	public static final String CUSTOM_EXTENSION = "custom";
	
	/**
	 * Get the id of this extension definition.
	 */
	public String getId();
	
	/**
	 * Get the name of the extension.
	 */
	public String getName();

	/**
	 * Get the type of the extensions.
	 */
	public String getType();
	
	/**
	 * Returns a {@link IExtensionProvider} for this extension
	 * definition. This method must not return <code>null</code>.
	 */
	public IExtensionProvider getProvider();
	
	/**
	 * Returns a {@link IEditPartProvider} for this extension
	 * definition. This method may return <code>null</code>.
	 */
	public IEditPartProvider getEditPartProvider();

	/**
	 * Check whether this extension can be added to automata.
	 * @return <code>True</code>, if they are automata extensions. 
	 */
	public boolean isAutomatonExtension();	
	
	/**
	 * Check whether this extension can be added to states.
	 * @return <code>True</code>, if they are automata states. 
	 */
	public boolean isStateExtension();
	
	/**
	 * Check whether this extension can be added to transitions.
	 * @return <code>True</code>, if they are automata transitions. 
	 */
	public boolean isTransitionExtension();
	
	/**
	 * Get the list of extension types that this definition depends on.
	 * @return Array of extension ids.
	 */
	public String[] getDependencies();
	
	/**
	 * Get the list of mutually excluded extension definitions. 
	 * @param global Flag determining whether the exclusion is global or local.
	 * @return Array of extension ids.
	 */
	public String[] getMutualExclusions(boolean global);
	
	/**
	 * If there is an icon associated to this action, this
	 * method returns a corresponding {@link ImageDescriptor}.
	 * @return Icon for this extension.
	 */
	public ImageDescriptor getIcon();
	
	/**
	 * Validate the properties of this extension definition.
	 * @return <code>True</code>, if the validation was successful.
	 */
	public boolean isValid();
	
}
