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
package org.ect.reo.reconf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;
import org.ect.reo.ReoPlugin;


/**
 * Command for performing a reconfiguration.
 * @generated NOT
 * @author Christian Krause
 */
public class ApplyRuleCommand extends AbstractEMFOperation {
	
	private Module source, target;
	private ReconfRule rule;
	private List<ReconfListener> listeners;
	
	public ApplyRuleCommand(TransactionalEditingDomain domain, ReconfRule rule, Module source, Module target) {
		super(domain, rule.getName());
		this.rule = rule;
		this.source = source;
		this.target = target;
		this.listeners = new Vector<ReconfListener>();
	}
	
	
	@Override
	protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		ReconfMatchFinder finder;
		List<ReconfMatch> matches;
		monitor.beginTask(rule.getName(), 10);
		
		monitor.subTask("Find match");

		// Create the rule network.
		Network network = ReconfElements.createRuleNetwork(rule, source);
		
		// Inactive elements in the rule network, and potential targets in the target module.
		Reconfigurable[] inactive = ReconfElements.collectRuleElements(rule, network, ReconfType.NONE);
		Reconfigurable[] targets = ReconfElements.collectRuleElements(rule, target, ReconfType.NONE);
		
		// Find matches for the inactive elements.
		if (target==null || target==source) {
			
			// Use an empty match.
			ReconfMatch match = new ReconfMatch(source, target);
			matches = new ArrayList<ReconfMatch>();
			matches.add(match);
			monitor.worked(3);
			
		} else {
			
			// Use a match finder.
			finder = new ReconfMatchFinder(source, target, inactive, targets);
			matches = finder.generate(1, new SubProgressMonitor(monitor, 3));
			
		}
		
		// 30% done.
		
		
		// Forbidden elements in the rule network.
		Reconfigurable[] forbidden = ReconfElements.collectRuleElements(rule, network, ReconfType.FORBID);
		
		// For all found matches, check if there are forbidden elements that can be matched.
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 2);
		subMonitor.beginTask("Match forbidden", matches.size());
		
		if (forbidden.length>0)	
			for (int i=0; i<matches.size(); i++) {
				
				// Compute the elements that are not reached by the current match.
				Reconfigurable[] notMatched = ReconfElements.notMatched(targets, matches.get(i));
				
				// Create a new match finder.
				finder = new ReconfMatchFinder(source, target, forbidden, notMatched);
				finder.setBaseMatch(matches.get(i));
				
				// If forbidden matches were found, we delete the current match.
				List<ReconfMatch> forbiddenMatches = finder.generate(1, new SubProgressMonitor(subMonitor,1));
				if (!forbiddenMatches.isEmpty()) {
					matches.remove(i--);
				}
		}
		else {
			subMonitor.worked(matches.size());
		}
		subMonitor.done();
		
		// 50% done.
		
		
		// Match 'delete' elements.
		Reconfigurable[] delete = ReconfElements.collectRuleElements(rule, network, ReconfType.DELETE);
		
		subMonitor = new SubProgressMonitor(monitor, 3);
		subMonitor.beginTask("Match delete", matches.size() + 1);
		
		if (delete.length>0) {
			
			List<ReconfMatch> withDelete = new ArrayList<ReconfMatch>(); 
			for (int i=0; i<matches.size(); i++) {
				
				// Compute the elements that are not reached by the current match.
				Reconfigurable[] notMatched = ReconfElements.notMatched(targets, matches.get(i));
				
				// Create a new match finder.
				finder = new ReconfMatchFinder(source, target, delete, notMatched);
				finder.setBaseMatch(matches.get(i));
				
				// If delete matches where found, we add them to the final list. 
				List<ReconfMatch> deleteMatches = finder.generate(-1, new SubProgressMonitor(subMonitor,1));
				//for (ReconfMatch current : deleteMatches) {
				//	finalMatches.add(current);
				//}
				if (!deleteMatches.isEmpty()) {
					withDelete.add(deleteMatches.get(0));					
				}
			}
			matches = withDelete;
			
			// Delete matches that overlap in the delete-parts.
			for (int i=0; i<matches.size(); i++) {
				for (int j=i+1; j<matches.size(); j++) {
					if (matches.get(i).valuesOverlap(matches.get(j), delete)) {
						matches.remove(j--);
					}
				}				
			}
			subMonitor.worked(1);
		}
		else {
			subMonitor.worked(matches.size() + 1);
		}
		subMonitor.done();
		
		// 90% done.		
		
		if (matches.isEmpty()) {
			monitor.done();
			return new Status(IStatus.ERROR, ReoPlugin.ID, 0, rule + ": no match found.", null);
		}
		
		// Apply the rule for all found matches.
		//Reo.debug("Found " + matches.size() + " matches for rule '" + rule + "'.");
		ReconfEngine engine = new DefaultReconfEngine();
		for (ReconfListener listener : listeners) {
			engine.addReconfListener(listener);
		}
		
		subMonitor = new SubProgressMonitor(monitor, 1);
		subMonitor.beginTask("Apply rule", matches.size());
		
		try {
			for (ReconfMatch match : matches) {
				//System.out.println("Match: " + match);
				engine.applyRule(rule, match, new SubProgressMonitor(subMonitor,1));
			}
		} catch (ReconfException e) {
			monitor.done();
			return new Status(IStatus.ERROR, ReoPlugin.ID, 0, rule + ": error applying rule.", e);
		}
		subMonitor.done();
		
		// 100% done.
		monitor.done();
		return Status.OK_STATUS;
		
	}
	
	
	
	/**
	 * Add a reconfiguration listener.
	 * @param listener Listener.
	 */
	public void addReconfListener(ReconfListener listener) {
		if (!listeners.contains(listener)) listeners.add(listener);
	}
	
	/**
	 * Remove a listener.
	 * @param listener Listener.
	 */
	public void removeReconfListener(ReconfListener listener) {
		if (listeners.contains(listener)) listeners.remove(listener);
	}
	
}
