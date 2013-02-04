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
package org.ect.ea.diagram.contributions.commands;

import java.text.ParseException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.AutomataPlugin;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.diagram.contributions.TextualExtensionsUpdater;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;

public class ParseExtensionsCommand extends AbstractTransactionalCommand {

	private String parseString;
	private IExtendible owner;
	private ITextualExtensionProvider provider;
	private String extensionId;
	private View view;

	public ParseExtensionsCommand(TransactionalEditingDomain domain) {
		super(domain, "parse extension", null);
	}		

	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		try {
			
			// Use the provider to parse the extension.
			IExtension extension = provider.parseExtension(parseString, owner);
			
			// Set the id of the extension.
			extension.setId(extensionId);
			
			// Notify the owner of the extension.
			owner.updateExtension(extension);
			
			// Update the view.
			TextualExtensionsUpdater.updateTextualExtension(extension, view);
			
		} catch (ParseException e) {
			AutomataPlugin.getInstance().logInfo("String '" + parseString + "' could not be parsed.");
		}
		
		return CommandResult.newOKCommandResult();
	
	}

	
	public void setParseString(String parseString) {
		this.parseString = parseString;
	}
	
	
	public void setOwner(IExtendible owner) {
		this.owner = owner;
	}
	
	
	public void setProvider(ITextualExtensionProvider provider, String extensionId) {
		this.provider = provider;
		this.extensionId = extensionId;
	}

	
	public void setView(View view) {
		this.view = view;
	}
	
}