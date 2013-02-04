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
package org.ect.reo.ui.properties.reconf;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.ect.reo.Module;
import org.ect.reo.ReconfRule;
import org.ect.reo.diagram.view.util.GenericViewUtil;
import org.ect.reo.reconf.ApplyRuleCommand;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ApplyRuleJob extends Job {

	private ReconfRule rule;
	private Module module, target;
	private TransactionalEditingDomain domain;

	/**
	 * Default constructor.
	 */
	public ApplyRuleJob(TransactionalEditingDomain domain, ReconfRule rule, Module module, Module target) {
		super(rule.getName());
		this.domain = domain;
		this.rule = rule;
		this.module = module;
		this.target = target;
		setUser(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		Diagram sourceDiagram = GenericViewUtil.findDiagram(module);
		Diagram targetDiagram = (target==null) ? sourceDiagram : GenericViewUtil.findDiagram(target);
		
		if (sourceDiagram==null || targetDiagram==null) {
			return new Status(IStatus.ERROR, "org.ect.reo.ui", "Cannot find source or target diagram.");			
		}
		
		final ApplyRuleCommand command = new ApplyRuleCommand(domain, rule, module, target);
		command.addReconfListener(new ReconfViewUpdater(sourceDiagram, targetDiagram));
		
		try {
			return command.execute(monitor, null);
		} catch (ExecutionException e) {
			return new Status(IStatus.ERROR, "org.ect.reo.ui", "Error applying rule.", e);
		}
		
	}

}
