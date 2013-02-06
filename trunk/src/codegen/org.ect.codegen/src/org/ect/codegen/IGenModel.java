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
package org.ect.codegen;

import java.util.Map;

import org.eclipse.core.resources.IFile;

/**
 * <p>
 * Generic code generation model, carrying all information
 * needed to perform a code generation for a given target 
 * model.
 * </p>
 * 
 * <p>
 * <code>IGenModel</code>s can be saved to a file. 
 * For that, the method {@link #getResource()} must return
 * an instance of {@link IFile} (not <code>null</code>).
 * </p>
 * 
 * @author Christian Koehler
 *
 */
public interface IGenModel {

	/**
	 * Key of the project name property. All code 
	 * generators should support this property.
	 */
	public static final String PROJECT_NAME = "projectName";

	/**
	 * Key of the project location property. All code 
	 * generators should support this property.
	 */
	public static final String PROJECT_LOCATION = "projectLocation";

	
	
	/**
	 * Get the target of the code generation.
	 * @return Target of the code generation.
	 */
	public Object getTarget();

	/**
	 * Set the target of the code generation.
	 * @param target Target of the code generation.
	 */
	public void setTarget(Object target);

	
	/**
	 * Get the value of the property with the given key.
	 * @param key The case-sensitive key of the property.
	 * @return The value of the property if exists, <code>null</code> otherwise.
	 */
	public String getProperty(String key);

	/**
	 * Set the value of the property associated to the given key.
	 * @param key The case-sensitive key of the property.
	 * @param value The value of the property. Might be <code>null</code>.
	 */
	public void setProperty(String key, String value);

	/**
	 * Get the properties of this genmodel.
	 * @return
	 */
	public Map<String,String> getProperties();
	
	
	
	// ----- Loading / saving GenModels ----- //
	
	/**
	 * <p>
	 * Get the Id of the code generation target. This is
	 * required for saving the <code>genModel</code> to
	 * a file, especially if there is more than one just
	 * one <code>genModel</code> accociated to the same 
	 * resource.
	 * </p>
	 * <p>
	 * This Id should <b>not</b> be the hashcode of the
	 * target. It should be rather a unique name or an
	 * index, which ideally does not change in time. 
	 * </p>
	 * @return Unique Id of the target.
	 */
	public String getTargetId();
	
	/**
	 * 
	 */
	public IFile getResource();
	
}
