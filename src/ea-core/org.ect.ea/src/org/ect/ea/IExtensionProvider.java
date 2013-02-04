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

import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;



/**
 * Common interface for extension providers. Implementations
 * should not only implement this interface, but one of the following:
 * <ul>
 * <li>{@link ITextualExtensionProvider}</li>
 * <li>{@link ICustomExtensionProvider}</li>
 * </ul>
 * 
 * @generated NOT
 */
public interface IExtensionProvider {

	/**
	 * Create a default extension for the given owner.
	 * This method must not return <code>null</code>.
	 */
	public IExtension createDefaultExtension(IExtendible owner);
	
	/**
	 * Create a silent extension for the given transition.
	 * A silent transition is always a loop on a single state.
	 * This method should return <code>null</code> if the
	 * provider is not a transition extension provider.
	 */
	public IExtension createSilentExtension(Transition owner);
	
	/**
	 * Returns <code>True</code> if the extension is silent.
	 * Such an extension is not necessarily identical with
	 * the one created with this provider. This method is
	 * only used for transition extensions.
	 */
	public boolean isSilentExtension(IExtension extension);
	
	/**
	 * Compute the product extension(s). To ensure a valid automata product,
	 * the method must return a list with <b>exactly one</b> extension for 
	 * {@link State}s and {@link Automaton}s. For {@link Transition}s the
	 * method may return an arbitrary number of product extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2);
	
	/**
	 * Validate an extension. This should not return <code>null</code>.
	 */
	public IValidationResult validateExtension(IExtension x);

}

