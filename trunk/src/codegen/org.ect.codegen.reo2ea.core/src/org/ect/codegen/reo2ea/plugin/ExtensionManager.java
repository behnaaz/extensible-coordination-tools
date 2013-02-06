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
package org.ect.codegen.reo2ea.plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizard;

import org.ect.codegen.reo2ea.core.INamingPolicyFactory;
import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.transform.Transformation;
import org.ect.ea.IProductProvider;

public class ExtensionManager {
	public static boolean GUI = true;
	@SuppressWarnings("unchecked")
	public static class TransformExt {
		private static final String CLASS = "class",
			TRANSFORM = "transform", 
			NAMINGPOLICY = "end_naming_policy", 
			PRODUCT= "product_class", 
			WIZARD ="wizard_class",
			DESCRIPTION="description",
			PROPKEY="property_key"; 

		private IConfigurationElement ce;
		public final String description, propKey;
		public Composition composition;
		public IWizard wizard;
		IProductProvider productProvider;
		Transformation transformation = new Transformation();
		INamingPolicyFactory namingPolicyFactory;		

		public TransformExt(IConfigurationElement ce)  {
			this.ce = ce;
			description = ce.getAttribute(DESCRIPTION);
			propKey = ce.getAttribute(PROPKEY);
		}
		
		public void create() throws CoreException { 
			Object o;
			if	((o = ce.createExecutableExtension(NAMINGPOLICY)) instanceof INamingPolicyFactory) 
				namingPolicyFactory = (INamingPolicyFactory) o;
			else
				throw new CoreException(Status.OK_STATUS);

			if	((o = ce.createExecutableExtension(PRODUCT)) instanceof IProductProvider) 
				productProvider = (IProductProvider) o;
			else
				throw new CoreException(Status.OK_STATUS);

			if (GUI & ce.getAttribute(WIZARD) != null 
					&& (o = ce.createExecutableExtension(WIZARD)) instanceof IWizard )
				wizard = (IWizard) o;			

			for (IConfigurationElement ch : ce.getChildren(TRANSFORM)) 
				if ((o = ch.createExecutableExtension(CLASS)) instanceof ITransformation)
					transformation.addDelegate(ch.getAttribute("target"), (ITransformation) o);

			composition = new Composition(transformation, productProvider, namingPolicyFactory);
		}
	}
	
	private  Map<String, TransformExt> elements = new HashMap<String, TransformExt>();
	
	public ExtensionManager() {
		IExtensionPoint  ep = Platform.getExtensionRegistry().getExtensionPoint(ITransformation.EXTENSION_ID);
		for (IExtension ext: ep.getExtensions()) 
			if (ext.getConfigurationElements().length > 0){
				elements.put(ext.getNamespaceIdentifier(), 
						new TransformExt(ext.getConfigurationElements()[0]));
			}
	}
	
	public   Map<String, TransformExt> getExtensions() {
		return Collections.unmodifiableMap(elements);
	}
}
