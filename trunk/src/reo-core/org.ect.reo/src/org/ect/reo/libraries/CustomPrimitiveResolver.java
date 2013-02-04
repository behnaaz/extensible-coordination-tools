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
package org.ect.reo.libraries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.ReoPlugin;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class CustomPrimitiveResolver {
	
	// A hashmap for tracking URIs in resolution calls.
	private static HashSet<URI> resolutionTracker = new HashSet<URI>();
	
	// Cached resolved primitives.
	private static Map<URI,CustomPrimitive> cache = new HashMap<URI,CustomPrimitive>();
		
	/**
	 * Resolve a custom primitive. This tries to set the {@link #resolved}
	 * attribute by evaluating the type URI property. This is done recursively.
	 * @generated NOT
	 */
	public static CustomPrimitive resolve(CustomPrimitive custom, IProgressMonitor monitor) throws CoreException {
				
		// Get the URI of the resolution target:
		URI uri = ReoLibraryUtil.getAbsoluteTypeURI(custom);
		if (uri==null) {
			custom.setResolved(null);
			monitor.done();
			return null;
		}
		
		// Did we try to resolve this URI already?
		synchronized (resolutionTracker) {
			if (resolutionTracker.contains(uri)) {
				resolutionTracker.clear();
				custom.setResolved(null);
				throw new CoreException(new Status(IStatus.ERROR, ReoPlugin.ID, 0, "Cycle detected while trying to resolve " + uri, null));
			}
			resolutionTracker.add(uri);
		}
		
		// Resolve it:
		monitor.beginTask("Resolve " + uri, 2);
		CustomPrimitive resolved = ReoLibraryRegistry.INSTANCE.createPrimitive(custom, new SubProgressMonitor(monitor,1));
		custom.setResolved(resolved);
		cache.put(uri,resolved);
		
		// Resolve the rest recursively:
		if (resolved!=null) {
			resolve(resolved, new SubProgressMonitor(monitor,1));
		}
		
		// Remove it from the tracker again:
		synchronized (resolutionTracker) {
			resolutionTracker.remove(uri);
		}
		
		return resolved;
		
	}
	
	public static CustomPrimitive getCached(CustomPrimitive custom) {
		URI uri = ReoLibraryUtil.getAbsoluteTypeURI(custom);
		if (uri!=null) {
			return cache.get(uri);
		} else {
			return null;
		}
	}
	

}
