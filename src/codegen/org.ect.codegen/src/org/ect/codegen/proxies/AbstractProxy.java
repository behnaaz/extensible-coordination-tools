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
package org.ect.codegen.proxies;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.ect.codegen.parts.CodeGenPlugin;
import org.osgi.framework.Bundle;

public abstract class AbstractProxy implements IConfigurationElement {

	private IConfigurationElement realElement;

	public AbstractProxy(IConfigurationElement realElement) {
		this.realElement = realElement;
	}

	/**
	 * Subclasses should override this one.
	 * @see IConfigurationElement#getAttribute(String)
	 */
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		CodeGenPlugin.getInstance().logError("Unknown attribute '" + name + "' for element '" + getName() + "'.");
		throw new InvalidRegistryObjectException();
	}


	public Object createExecutableExtension(String propertyName) throws CoreException {
		try {
			Bundle bundle = Platform.getBundle(getContributor().getName());
			return bundle.loadClass(getAttribute(propertyName)).newInstance();
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, CodeGenPlugin.ID, IStatus.ERROR, "Error loading class: " + getAttribute(propertyName), e));
		}
	}


	/*
	 * This is deprecated and hence should not be used.
	 * @deprecated
	 * @see org.eclipse.core.runtime.IConfigurationElement#getAttributeAsIs(java.lang.String)
	 */
	public String getAttributeAsIs(String name) throws InvalidRegistryObjectException {
		return getAttribute(name);
	}


	public IContributor getContributor() throws InvalidRegistryObjectException {
		return realElement.getContributor();
	}


	public IExtension getDeclaringExtension() throws InvalidRegistryObjectException {
		return realElement.getDeclaringExtension();
	}


	public String getNamespace() throws InvalidRegistryObjectException {
		return realElement.getNamespace();
	}


	public String getNamespaceIdentifier() throws InvalidRegistryObjectException {
		return realElement.getNamespaceIdentifier();
	}


	public Object getParent() throws InvalidRegistryObjectException {
		return realElement.getParent();
	}


	public String getValue() throws InvalidRegistryObjectException {
		return realElement.getValue();
	}


	public String getValueAsIs() throws InvalidRegistryObjectException {
		return realElement.getValueAsIs();
	}

	public String getAttribute(String attrName, String locale)
	throws InvalidRegistryObjectException {
		return getAttribute(attrName);
	}


	public String getValue(String locale) throws InvalidRegistryObjectException {
		return getValue();
	}
}
