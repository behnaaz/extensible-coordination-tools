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


/**
 * This enum type is a uniform interface for textual {@link ReoTextualSemantics}.
 * @author Christian Krause
 * @generated NOT
 */
public enum ReoScope {
	
	ELEMENT, CONNECTOR, NETWORK;
	
	/**
	 * Compute the semantics of something for the current scope type.
	 * @param target Target element / connector / network.
	 * @param key Semantics provider key.
	 * @param monitor Progress monitor.
	 * @return The computed semantics.
	 */
	public String computeSemantics(Object target, String key, IProgressMonitor monitor) {
		
		switch (this) {
		
		case ELEMENT:
			if (target instanceof Connectable) {
				if (monitor!=null) monitor.done();
				return ReoTextualSemantics.getElementSemantics((Connectable) target, key);
			} else {
				throw new IllegalArgumentException("Cannot compute element semantics of " + target);
			}

		case CONNECTOR:
			if (target instanceof Connector) {
				return ReoTextualSemantics.computeConnectorSemantics((Connector) target, key, monitor);
			} else {
				throw new IllegalArgumentException("Cannot compute connector semantics of " + target);
			}

		case NETWORK:
			if (target instanceof Network) {
				return ReoTextualSemantics.computeNetworkSemantics((Network) target, key, monitor);
			} else {
				throw new IllegalArgumentException("Cannot compute network semantics of " + target);
			}
			
		default:
			return null;
			
		}
	}
	
}
