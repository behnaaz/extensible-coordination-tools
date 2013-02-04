/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.configuration;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ect.ea.EA;
import org.ect.ea.IEditPartProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;


/**
 * Extension definition.
 * @author Christian Krause
 * @generated NOT
 */
public class ExtensionDefinition implements IExtensionDefinition {
	
	// Possible values for the enabledFor-attribute.
	public static final String AUTOMATA = "automata";
	public static final String STATES = "states";
	public static final String TRANSITIONS = "transitions";
	
	// Extension types.
	public static final String TEXTUAL = TEXTUAL_EXTENSION;
	public static final String CUSTOM = CUSTOM_EXTENSION;
	
	// Definition element.
	private IConfigurationElement element;
	
	// Id, name, type, enabledFor.
	private String id, name, type, enabledFor;
	
	// ExtensionProvider.
	private IExtensionProvider provider;
	
	// An optional edit part provider.
	private IEditPartProvider editParts;
	
	// Extension dependencies.
	private String[] dependencies = new String[0];

	// Mutual exclusions.
	private String[] localExclusions = new String[0];
	private String[] globalExclusions = new String[0];
	
	// Icon of the extension definition.
	private ImageDescriptor icon;
	
	
	/**
	 * Default constructor.
	 * @param element Configuration element with type "org.ect.ea.extensions.extensionDefinition".
	 */
	public ExtensionDefinition(IConfigurationElement element) {  
		
		this.element = element;
		this.id = element.getAttribute("id");
		this.name = element.getAttribute("name");
		this.type = element.getAttribute("type");
		this.enabledFor = element.getAttribute("enabledFor");
		
		// The extension provider.
		try {
			provider = (IExtensionProvider) element.createExecutableExtension("providerClass");
		} catch (Exception e) {
			EA.logError("Error loading extension provider: " + element.getAttribute("providerClass"), e);
		}
		
		// The edit part provider.
		try {
			editParts = (IEditPartProvider) element.createExecutableExtension("editParts");
		} catch (Exception e) {
			// Optional.
		}
		
		// The icon.
		String path = element.getAttribute("icon");
		if (path!=null) { 
			String plugin = element.getContributor().getName();
			this.icon = AbstractUIPlugin.imageDescriptorFromPlugin(plugin, path);
		}
		
		// Dependencies.
		IConfigurationElement[] children = element.getChildren("dependency");
		dependencies = new String[children.length];
		for (int i=0; i<children.length; i++) {
			dependencies[i] = children[i].getAttribute("id");
		}
		
		// Mutual exclusions.
		children = element.getChildren("mutualExclusion");
		List<String> globals = new Vector<String>();
		List<String> locals = new Vector<String>();

		for (int i=0; i<children.length; i++) {
			if ("true".equals(children[i].getAttribute("global"))) {
				globals.add(children[i].getAttribute("id"));
			} else {
				locals.add(children[i].getAttribute("id"));					
			}
		}
		
		globalExclusions = new String[ globals.size() ];
		localExclusions = new String[ locals.size() ];
		
		globals.toArray( globalExclusions );
		locals.toArray( localExclusions );
		
	}
	
	public ExtensionDefinition(String name, String id, String type, String enabledFor, IExtensionProvider provider) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.enabledFor = enabledFor;
		this.provider = provider;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	
	public boolean isAutomatonExtension() {
		return AUTOMATA.equalsIgnoreCase(enabledFor);
	}

	public boolean isStateExtension() {
		return STATES.equalsIgnoreCase(enabledFor);
	}

	public boolean isTransitionExtension() {
		return TRANSITIONS.equalsIgnoreCase(enabledFor);
	}

	
	public ImageDescriptor getIcon() {
		return icon;	
	}
	
	public IExtensionProvider getProvider() {
		return provider;
	}

	public IEditPartProvider getEditPartProvider() {
		return editParts;
	}

	public void setEditPartProvider(IEditPartProvider editParts) {
		this.editParts = editParts;
	}
	
	public String[] getDependencies() {
		return dependencies;
	}

	public void setDependencies(String[] dependencies) {
		this.dependencies = dependencies;
	}

	
	public String[] getMutualExclusions(boolean global) {
		if (global) return globalExclusions;
		else return localExclusions;
	}
	
	public void setMutualExclusions(String[] exclusions, boolean global) {
		if (global) this.globalExclusions = exclusions;
		else this.localExclusions = exclusions;
	}

	
	public boolean isValid() {
		return getId()!=null && !getId().trim().equals("") && getProvider()!=null;
	}

		
	public IConfigurationElement getElement() {
		return element;
	}

}
