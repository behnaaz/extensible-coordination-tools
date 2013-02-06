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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * <p>
 * Code generator interface. This interface is used by the 
 * extension point <code>org.ect.codegen.codeGenerators</code>.
 * Providing a code generator is done by implementing this
 * interface and instantiating the extension point with it.
 * </p>
 * 
 * @see org.ect.codegen.IGenModel
 * @author Christian Krause
 * 
 */
public interface ICodeGenerator {

	/**
	 * <p>
	 * Perform the code generation. All the necessary information
	 * needed to do the code generation should be accessible through
	 * the parameter <code>genModel</code>.
	 * </p>
	 * 
	 * @param genModel Code generation model to be used.
	 * @param monitor Progress monitor for the code generation.
	 * @exception If any kind of error happens.
	 */
	public void generateCode(IGenModel genModel, IProgressMonitor monitor) throws CoreException;
	
	
	/**
	 * <p>
	 * Initialize a <code>genModel</code> for the given code
	 * generation target. This genModel should include all 
	 * information required for the code generation. In particular, 
	 * the target model and the project name should be set.
	 * </p>
	 * 
	 * <p>
	 * All <code>genProperties</code> defined in the 
	 * <code>org.ect.codegen.codeGenerators</code> extension should 
	 * be filled with default values here. Ideally, the
	 * initial genModel should always pass the
	 * {@link #validateGenModel(IGenModel)} check.
	 * </p>
	 * 
	 * @param target Target of the code generation.
	 * @return Initial <code>genModel</code>
	 */
	public void initGenModel(Object target, IGenModel genModel);
	
	
	/**
	 * <p>
	 * Validate the argument <code>genModel</code>.
	 * All <code>genProperties</code> defined in the 
	 * <code>org.ect.codegen.codeGenerators</code> extension 
	 * should be checked here.
	 * </p>
	 * 
	 * @param genModel GenModel to be validated.
	 * @return Status with detailed information about errors, if existing.
	 */
	public IStatus validateGenModel(IGenModel genModel);
	
}

