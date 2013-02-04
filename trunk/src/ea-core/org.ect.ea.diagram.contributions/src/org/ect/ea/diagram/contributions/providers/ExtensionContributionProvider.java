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
package org.ect.ea.diagram.contributions.providers;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionGroup;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.IAutomatonTypeRegistry;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.IProductDefinition;
import org.ect.ea.IProductRegistry;
import org.ect.ea.diagram.contributions.actions.ActivateExtensionAction;
import org.ect.ea.diagram.contributions.actions.ActivateTypeAction;
import org.ect.ea.diagram.contributions.actions.AddSilentTransitionsAction;
import org.ect.ea.diagram.contributions.actions.ComputeProductAction;
import org.ect.ea.diagram.contributions.actions.DefaultMenuManager;
import org.ect.ea.diagram.contributions.actions.ExtensionProviderActionGroup;

/**
 * This class contributes a menu with all registered automata extension. 
 * 
 * @author Christian Koehler
 *
 */
public class ExtensionContributionProvider extends AbstractContributionItemProvider {
	
	public static String EXTENSIONS_MENU_ID = "extensionMenu";
	public static String EXTENSIONS_MENU_NAME = "Extensions";
	
	public static String TYPE_MENU_ID = "typeMenu";
	public static String TYPE_MENU_NAME = "Automaton Type";

	public static String PRODUCT_MENU_ID = "productMenu";
	public static String PRODUCT_MENU_NAME = "Compute Product";

	
	/**
	 * Default constructor.
	 */
	public ExtensionContributionProvider() {		
	}
	
	/**
	 * Create the "Extensions"-menu.
	 */
	@Override
	protected IMenuManager createMenuManager(String menuId, IWorkbenchPartDescriptor partDescriptor) {

		IWorkbenchPage page = partDescriptor.getPartPage();
		IDiagramWorkbenchPart editor = (IDiagramWorkbenchPart) page.getActiveEditor();
		
		// Get the extension registries.
		IExtensionRegistry extensionRegistry = EA.getExtensionRegistry();
		IAutomatonTypeRegistry typeRegistry = EA.getAutomatonTypeRegistry();
		IProductRegistry products = EA.getProductRegistry();
		
		if (menuId.equals(EXTENSIONS_MENU_ID)) {
			DefaultMenuManager menu = new DefaultMenuManager(EXTENSIONS_MENU_ID, EXTENSIONS_MENU_NAME);
			for (IExtensionDefinition definition : extensionRegistry.getExtensionDefinitions()) {
				menu.add(new ActivateExtensionAction(editor, definition));
			}
			return menu;
		}
		
		if (menuId.equals(TYPE_MENU_ID)) {
			DefaultMenuManager menu = new DefaultMenuManager(TYPE_MENU_ID, TYPE_MENU_NAME);
			for (IAutomatonType type : typeRegistry.getAutomatonTypes()) {
				menu.add(new ActivateTypeAction(editor, type));
			}
			return menu;
		}
		
		if (menuId.equals(PRODUCT_MENU_ID)) {
			DefaultMenuManager menu = new DefaultMenuManager(PRODUCT_MENU_ID, PRODUCT_MENU_NAME);
			for (IProductDefinition product : products.getProductDefinitions()) {
				menu.add(new ComputeProductAction(editor, product));
			}
			return menu;
		}

		return super.createMenuManager(menuId, partDescriptor);

	}
	
	
	@Override
	protected ActionGroup createActionGroup(String groupId, IWorkbenchPartDescriptor descriptor) {
		
		// Check if its the correct group.
		if (groupId.equals(ExtensionProviderActionGroup.EXTENSIONS_GROUP_ID)) {
			// Create a new extension provider group.
			IWorkbenchPage page = descriptor.getPartPage();
			IDiagramWorkbenchPart editor = (IDiagramWorkbenchPart) page.getActiveEditor();
			return new ExtensionProviderActionGroup(editor);
		}
		
		return super.createActionGroup(groupId, descriptor);
	}

	
	
	@Override
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor descriptor) {
		
		if (actionId.equals(AddSilentTransitionsAction.ADD_SILENT_ACTION_ID)) {
			return new AddSilentTransitionsAction(descriptor.getPartPage(), true);
		}
		if (actionId.equals(AddSilentTransitionsAction.REMOVE_SILENT_ACTION_ID)) {
			return new AddSilentTransitionsAction(descriptor.getPartPage(), false);
		}
		return super.createAction(actionId, descriptor);
	}

}
