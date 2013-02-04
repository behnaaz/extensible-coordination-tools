/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class AutomatonType implements IAutomatonType {
	
	// Definition element.
	private IConfigurationElement element;
	
	// Referenced extension ids.
	private String[] extensionIds;

	// Resolved and unresolved dependencies.
	private String[] resolved, unresolved;
	
	// Icon to be used.
	private ImageDescriptor icon;
	
	
	/**
	 * Default constructor.
	 * @param element Configuration element with type "org.ect.ea.extensions.automatonType".
	 */
	public AutomatonType(IConfigurationElement element) {  
		this.element = element;
	}
   	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonType#getExtensionIds()
	 */
	public String[] getExtensionIds() {
		if (extensionIds==null) {
			List<String> ids = new Vector<String>();
			for (IConfigurationElement ref : element.getChildren("extensionReference")) {
				String id = ref.getAttribute("id");
				if (id!=null) ids.add(id);
			}
			extensionIds = new String[ids.size()];
			ids.toArray(extensionIds);
		}
		return extensionIds;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonType#getIcon()
	 */
	public ImageDescriptor getIcon() {
		if (icon==null) {
			String path = element.getAttribute("icon");
			if (path==null) return null;
			String plugin = element.getContributor().getName();
			icon = AbstractUIPlugin.imageDescriptorFromPlugin(plugin, path);
		}
		return icon;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonType#getName()
	 */
	public String getName() {
		return element.getAttribute("name");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonType#getResolvedDependencies()
	 */
	public String[] getResolvedDependencies() {
		if (resolved==null) computeDependencies();
		return resolved;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonType#getUnresolvedDependencies()
	 */
	public String[] getUnresolvedDependencies() {
		if (unresolved==null) computeDependencies();
		return unresolved;
	}
	
	
	private void computeDependencies() {
		
		HashSet<String> resolved = new HashSet<String>();
		HashSet<String> unresolved = new HashSet<String>();
		
		IExtensionRegistry registry = EA.getExtensionRegistry();
		
		// Dependencies.
		for (String id : getExtensionIds()) {
			
			IExtensionDefinition definition = registry.findExtensionDefinition(id);
			if (definition==null) {
				unresolved.add(id);
				continue;
			}
			for (IExtensionDefinition dependency : registry.getResolvedDependencies(definition)) {
				resolved.add(dependency.getId());
			}
			for (String dependency : registry.getUnresolvedDependencies(definition)) {
				unresolved.add(dependency);
			}			
		}
		
		this.resolved = new String[ resolved.size() ];
		resolved.toArray(this.resolved);

		this.unresolved = new String[ unresolved.size() ];
		unresolved.toArray(this.unresolved);

	}

}
