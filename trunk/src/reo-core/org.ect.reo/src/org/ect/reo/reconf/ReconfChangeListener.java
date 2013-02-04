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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.Module;
import org.ect.reo.ReconfAction;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;
import org.ect.reo.ReoPackage;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class ReconfChangeListener {
		
	// The adapter for modules.
	private Adapter moduleAdapter = new AdapterImpl() {		
		public void notifyChanged(Notification event) {
			
			int type = event.getEventType();
			int feature = event.getFeatureID(Module.class);
			
			if (feature==ReoPackage.MODULE__RECONF_RULES) {
				if (type==Notification.ADD) {
					Module module = (Module) event.getNotifier();
					ReconfRule rule = (ReconfRule) event.getNewValue();
					ruleAdded(rule, module);
				}
				if (type==Notification.REMOVE) {
					Module module = (Module) event.getNotifier();
					ReconfRule rule = (ReconfRule) event.getOldValue();
					ruleRemoved(rule, module);
				}
			}
			
			if (feature==ReoPackage.MODULE__ACTIVE_RECONF_RULE) {
				Module module = (Module) event.getNotifier();
				activeRuleChanged(module);
			}
		}
	};
	
	// The adapter for elements.
	private Adapter elementAdapter = new AdapterImpl() {		
		public void notifyChanged(Notification event) {
			
			int type = event.getEventType();
			int feature = event.getFeatureID(Reconfigurable.class);
			
			if (feature==ReoPackage.RECONFIGURABLE__RECONF_ACTIONS) {
				Reconfigurable element = (Reconfigurable) event.getNotifier();

				if (type==Notification.ADD) {
					ReconfAction action = (ReconfAction) event.getNewValue();
					actionChanged(element, action);
					// Add the action adapter.
					action.eAdapters().add(actionAdapter);
				}
				
				else if (type==Notification.SET) {
					ReconfAction action = (ReconfAction) event.getNewValue();
					actionChanged(element, action);
					// Add the action adapter.
					action.eAdapters().add(actionAdapter);
					// Remove the adapter from the old action.
					((ReconfAction) event.getOldValue()).eAdapters().remove(actionAdapter);
				}
				
				else if (type==Notification.REMOVE) {
					ReconfAction action = (ReconfAction) EcoreUtil.copy((EObject) event.getOldValue());
					action.setType(ReconfType.NONE);
					actionChanged(element, action);
					
					// Remove the action adapter again.
					action.eAdapters().remove(actionAdapter);
				}
			}
		}
	};

	// The adapter for actions.
	private Adapter actionAdapter = new AdapterImpl() {		
		public void notifyChanged(Notification event) {			
			int type = event.getEventType();
			if (type==Notification.SET) {
				ReconfAction action = (ReconfAction) event.getNotifier();
				Reconfigurable owner = (Reconfigurable) action.eContainer();
				actionChanged(owner, action);
			}
		}
	};
	
	
	protected abstract void ruleAdded(ReconfRule rule, Module module);
	
	protected abstract void ruleRemoved(ReconfRule rule, Module module);	
	
	protected abstract void activeRuleChanged(Module module);
	
	protected abstract void actionChanged(Reconfigurable element, ReconfAction action);
	
	
	public void monitor(Module module, boolean monitor) {
		if (monitor) module.eAdapters().add(moduleAdapter);
		else module.eAdapters().remove(moduleAdapter);
	}
	
	public void monitor(Reconfigurable element, boolean monitor) {
		if (monitor) {
			element.eAdapters().add(elementAdapter);
			for (ReconfAction action : element.getReconfActions()) {
				action.eAdapters().add(actionAdapter);
			}
		} else {
			element.eAdapters().remove(elementAdapter);
			for (ReconfAction action : element.getReconfActions()) {
				action.eAdapters().remove(actionAdapter);
			}
		}
	}
	
}
