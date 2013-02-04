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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * An empty implementation of ChangeExtensionsCommand.
 * This can be used to validate the extension views 
 * of an automaton.
 * 
 * @author Christian Krause
 *
 */
public final class UpdateExtensionsCommand extends ChangeExtensionsCommand {

	public UpdateExtensionsCommand(IGraphicalEditPart selected) {
		super("Update extensions", selected);
	}

	
	protected final void performExtensionsChange(IProgressMonitor monitor) {
		// Do nothing. The extensions are automatically updated.
	}
	
}

