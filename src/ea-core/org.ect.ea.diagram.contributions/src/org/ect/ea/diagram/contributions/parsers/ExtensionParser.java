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
package org.ect.ea.diagram.contributions.parsers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.diagram.contributions.commands.ParseExtensionsCommand;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;

public class ExtensionParser implements IParser {
	
	private IExtensionRegistry registry;
	
	/**
	 * Default constructor.
	 */
	public ExtensionParser() {
		this.registry = EA.getExtensionRegistry();
	}

	/**
	 * Compute a string representation of given extension.
	 * This is used for printing the label.
	 */
	public String getPrintString(IAdaptable adapter, int flags) {
		
		if (!(((EObjectAdapter) adapter).resolve() instanceof IExtension)) return "?";
		
		IExtension extension = (IExtension) ((EObjectAdapter) adapter).resolve();
		ITextualExtensionProvider provider = getTextualProvider(extension);

		if (provider==null || extension==null) return "?";
		String printString = provider.printExtension(extension);
		return printString;

	}

	
	/**
	 * Compute a string representation of given extension.
	 * This is used for editing the label.
	 */
	public String getEditString(IAdaptable adapter, int flags) {

		if (!(((EObjectAdapter) adapter).resolve() instanceof IExtension)) return "?";

		IExtension extension = (IExtension) ((EObjectAdapter) adapter).resolve();
		ITextualExtensionProvider provider = getTextualProvider(extension);
		
		if (provider==null || extension==null) return "?";
		String editString = provider.editExtension(extension);
		return editString;
	
	}

	
	
	/**
	 * Get the parse command.
	 */
	public ICommand getParseCommand(IAdaptable adapter, String parseString, int flags) {

		IExtension extension = (IExtension) ((EObjectAdapter) adapter).resolve();
		IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());
		ITextualExtensionProvider provider = getTextualProvider(extension);
		
		View view = (View) adapter.getAdapter(View.class);
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(extension); 		

		if (provider==null || domain==null || view==null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		ParseExtensionsCommand command = new ParseExtensionsCommand(domain);
		
		command.setProvider(provider, definition.getId());
		command.setParseString(parseString);
		command.setOwner(extension.getOwner());
		command.setView(view);
		
		return command;

	}

	
	/**
	 * Get the textual extension provider of an extension.
	 */
	protected ITextualExtensionProvider getTextualProvider(IExtension extension) {
		
		// Find the extension definition and the provider.
		IExtensionProvider provider = registry.findExtensionDefinition(extension.getId()).getProvider();

		// Check if it is a textual provider.
		if (provider instanceof ITextualExtensionProvider) {
			return (ITextualExtensionProvider) provider;
		} else {
			return null;
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.parser.IParser#isAffectingEvent(java.lang.Object, int)
	 */
	public boolean isAffectingEvent(Object event, int flags) {
		
		if (!(event instanceof ENotificationImpl)) {
			return false;
		}
		Object notifier = ((ENotificationImpl) event).getNotifier();
		
		return (notifier instanceof IExtendible);
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.parser.IParser#isValidEditString(org.eclipse.core.runtime.IAdaptable, java.lang.String)
	 */
	public IParserEditStatus isValidEditString(IAdaptable arg0, String arg1) {
		return ParserEditStatus.EDITABLE_STATUS;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.parser.IParser#getCompletionProcessor(org.eclipse.core.runtime.IAdaptable)
	 */
	public IContentAssistProcessor getCompletionProcessor(IAdaptable arg0) {
		return null;
	}
		
}

