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
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.ect.reo.Module;
import org.ect.reo.ReconfRule;


/**
 * Registry for exported reconfiguration rules.
 * @generated NOT
 * @author Christian Krause
 */
public class ReoReconfRegistry {
	
	// Global instance.
	public static final ReoReconfRegistry INSTANCE = new ReoReconfRegistry();
	
	/**
	 * Rule entry class.
	 * @generated NOT
	 * @author Christian Krause
	 */
	public static class RuleEntry {
		
		private ReconfRule rule;
		private Module module;
		
		/**
		 * Constructor for a rule entry.
		 * @param rule Rule.
		 * @param module Module.
		 */
		public RuleEntry(ReconfRule rule, Module module) {
			this.rule = rule;
			this.module = module;
		}
		
		public ReconfRule getRule() {
			return rule;
		}
		
		public Module getModule() {
			return module;
		}
		
		@Override
		public int hashCode() {
			return rule.hashCode() + 2 * module.hashCode();
		}
		
		@Override
		public boolean equals(Object object) {
			if (object instanceof RuleEntry) {
				RuleEntry entry = (RuleEntry) object;
				return (module==entry.getModule() && rule==entry.getRule());
			} else return false;
		}
		
		@Override
		public String toString() {
			return rule.getName() + " (" + module.eResource().getURI().lastSegment() + ")";
		}

	}
	
	// Registered rules.
	private Collection<RuleEntry> rules = new LinkedHashSet<RuleEntry>();
	
	/*
	 * Private constructor.
	 */
	private ReoReconfRegistry() {	
	}
	
	/**
	 * Get the registered rules.
	 * @return Collection of rules.
	 */
	public Collection<RuleEntry> getRules() {
		return rules;
	}
	
	/**
	 * Add all exported rules of a module.
	 * @param module Module.
	 */
	public void addRules(Module module) {
		for (ReconfRule rule : module.getReconfRules()) {
			if (rule.isExported()) rules.add(new RuleEntry(rule,module));
		}
	}
	
	/**
	 * Remove all exported rules of a module.
	 * @param module Module.
	 */
	public void removeRules(Module module) {
		for (RuleEntry entry : new ArrayList<RuleEntry>(rules)) {
			if (entry.getModule()==module) rules.remove(entry);
		}
	}
	
	/**
	 * Update the registry.
	 */
	public void update() {
		Set<Module> modules = new HashSet<Module>();
		for (RuleEntry rule : rules) {
			modules.add(rule.getModule());
		}
		rules.clear();
		for (Module module : modules) {
			addRules(module);
		}
	}
	
}
