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
package org.ect.reo.semantics;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.util.PrimitiveEndNames;


/**
 * Interface for textual semantics providers.
 * @generated NOT
 * @author Christian Krause
 *
 */
public interface TextualSemanticsProvider {
	
	/**
	 * Get the textual semantics for a connectable element.
	 * 
	 * @param element Element.
	 * @param Names of the element's ends.
	 * @return Textual representation of the semantics. 
	 */
	public String getElementSemantics(Connectable element, PrimitiveEndNames names);
	
	/**
	 * Compute the semantics of a given connector. The semantics 
	 * should be expressed in terms of the component ends.
	 * 
	 * @param connector Connector.
	 * @param names Names to be used for the reader's / writer's ends.
	 * @param monitor Progress monitor.
	 * @return Textual representation of the semantics. 
	 */
	public String computeConnectorSemantics(Connector connector, PrimitiveEndNames names, IProgressMonitor monitor);
	
	/**
	 * Compute the semantics of a network.
	 * @return Textual representation of the semantics. 
	 */
	public String computeNetworkSemantics(Network network, PrimitiveEndNames names, IProgressMonitor monitor);

	
	/**
	 * Get the key that is used to store the textual semantics
	 * as a property in a component.
	 * @return The key.
	 */
	public String getSemanticsKey();
	
}
