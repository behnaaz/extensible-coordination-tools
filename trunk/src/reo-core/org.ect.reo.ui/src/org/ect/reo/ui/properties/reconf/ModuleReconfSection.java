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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.Module;
import org.ect.reo.ReconfRule;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.reconf.ReoReconfRegistry;
import org.ect.reo.reconf.ReoReconfRegistry.RuleEntry;
import org.ect.reo.ui.properties.helpers.ReoPropertySection;
import org.ect.reo.ui.properties.reconf.RulesGroupHelper.RulesGroup;
import org.ect.reo.ui.properties.reconf.RulesGroupHelper.RulesGroupListener;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class ModuleReconfSection extends ReoPropertySection implements IFilter {
	
	public static final String ADD = "Add";
	public static final String EDIT = "Edit";
	public static final String REMOVE = "Remove";
	public static final String DESELECT = "Deselect";
	public static final String APPLY = "Apply";
//	public static final String APPLY_ONCE = "Apply once";
//	public static final String APPLY_EVERYWHERE = "Apply everywhere";
	
	public static final String[] LOCAL = new String[] { APPLY, ADD, EDIT, REMOVE, DESELECT };
	public static final String[] GLOBAL = new String[] { APPLY /*APPLY_ONCE, APPLY_EVERYWHERE*/ };
	
	private Module module;
	private Shell shell;
	private RulesGroup local, global;
	private Button localB, globalB;
	private List<ReconfRule> localRules = new ArrayList<ReconfRule>();
	private List<RuleEntry> globalRules = new ArrayList<RuleEntry>();

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage page) {
    	
    	super.createControls(parent, page);
    	this.shell = parent.getShell();
    	
    	// Create a container.
    	final Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    	GridLayout layout = new GridLayout(1,false);
    	layout.marginWidth = ITabbedPropertyConstants.HSPACE;
        layout.marginHeight = ITabbedPropertyConstants.VSPACE;
    	composite.setLayout(layout);
    	
    	// Radio buttons.
    	Composite radio = getWidgetFactory().createComposite(composite, SWT.NONE);
    	radio.setLayout(new RowLayout(SWT.HORIZONTAL));
    	getWidgetFactory().createLabel(radio, "Rules:", SWT.NONE);
    	localB = getWidgetFactory().createButton(radio, "local", SWT.RADIO);
    	globalB = getWidgetFactory().createButton(radio, "global", SWT.RADIO);
    	SelectionListener listener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean isLocal = localB.getSelection();
				((GridData) local.group.getLayoutData()).exclude = !isLocal;
				((GridData) global.group.getLayoutData()).exclude = isLocal;
				local.group.setVisible(isLocal);
				global.group.setVisible(!isLocal);
				if (!isLocal) {
					selectLocal(-1);
				}
		    	updateViews();
				composite.layout(true);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
    	localB.addSelectionListener(listener);
    	globalB.addSelectionListener(listener);    	
    	localB.setSelection(true);
    	
    	RulesGroupHelper helper = new RulesGroupHelper(getWidgetFactory());
    	local = helper.createGroup(composite, "Local rules:", SWT.NONE, LOCAL, new RulesGroupListener() {
    		
			public void buttonPressed(String button) {
				if (button.equals(ADD)) edit(-1);
				if (button.equals(EDIT)) edit( local.rules.getSelectionIndex() );
				if (button.equals(REMOVE)) remove( local.rules.getSelectionIndex() );
				if (button.equals(DESELECT)) selectLocal(-1);
				if (button.equals(APPLY)) applyLocal( local.rules.getSelectionIndex() );
			}

			public void ruleSelected(int index) {
				selectLocal(index);
			}
    		
    	});
    	GridData data = new GridData(GridData.BEGINNING);
    	data.exclude = !localB.getSelection();
    	local.group.setLayoutData(data);
		
    	global = helper.createGroup(composite, "Global rules:", SWT.NONE, GLOBAL, new RulesGroupListener() {
    		
			public void buttonPressed(String button) {
				if (button.equals(APPLY)) applyGlobal( global.rules.getSelectionIndex() );
			}
			
			public void ruleSelected(int rule) {}
    		
    	});
    	data = new GridData(GridData.BEGINNING);
    	data.exclude = !globalB.getSelection();
    	global.group.setLayoutData(data);

    	
    }
    	
	
    
	protected void edit(int index) {
		
		final ReconfRule rule = (index>=0) ? getRule(index) : null;
		boolean export = (rule==null || rule.isExported());
		final EditReconfRuleDialog dialog = new EditReconfRuleDialog(shell, 
				rule==null ? null : rule.getName(), export);
		
		if (dialog.open()==EditReconfRuleDialog.OK) {
			
			// Nothing changed?
			if (rule!=null && dialog.getRule().equals(rule.getName()) && export==dialog.getExport()) return;
			
			if (index>=0) {
				// Rename the rule.
				executeAsCommand("Rename rule", new Runnable() {
					public void run() {
						rule.setName(dialog.getRule());
						rule.setExported(dialog.getExport());
						module.setActiveReconfRule(rule);
					}
				});
			} else {
				// Add the rule.
				executeAsCommand("Add rule", new Runnable() {
					public void run() {
						ReconfRule rule = new ReconfRule();
						rule.setName(dialog.getRule());
						rule.setExported(dialog.getExport());
						module.getReconfRules().add(rule);
						module.setActiveReconfRule(rule);
					}
				});
			}
			// Update the views again.
			updateViews();
		}
	}

	
	protected void remove(int index) {
		final ReconfRule rule = getRule(index);
		if (!MessageDialog.openConfirm(getEditor().getSite().getShell(), "Remove rule", "Really delete the rule '" + rule + "'?")) return;
		// Remove the rule.
		executeAsCommand("Remove rule", new Runnable() {
			public void run() {
				module.getReconfRules().remove(rule);
				module.setActiveReconfRule(null);
			}
		});
		updateViews();
	}
	
	
	protected void selectLocal(final int index) {	
		final ReconfRule rule = (index>=0) ? getRule(index) : null;
		executeAsCommand("Set current rule", new Runnable() {
		public void run() {
				module.setActiveReconfRule(rule);
			}
		});
		updateViews();
	}
	
	
	private ReconfRule getRule(int index) {
		if (localB.getSelection()) {
			return localRules.get(index);
		} else {
			return globalRules.get(index).getRule();
		}
	}
	
	protected void applyLocal(int index) {
		ReconfRule rule = localRules.get(index);
		Job job = new ApplyRuleJob(getReoEditor().getEditingDomain(), rule, module, module);
		job.schedule();		
	}
	
	
	protected void applyGlobal(int index) {
		RuleEntry entry = globalRules.get(index);
		Job job = new ApplyRuleJob(getReoEditor().getEditingDomain(), entry.getRule(), entry.getModule(), module);
		job.schedule();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object object) {
    	return (object instanceof ModuleEditPart);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof ModuleEditPart);
		module = (Module) ((ModuleEditPart) input).getNotationView().getElement();
	}
		
	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews() {
		
		if (localB.getSelection()) {
			
			// ----- Local rules ----- //
			localRules.clear();
			String[] items = new String[module.getReconfRules().size()];
			for (int i=0; i<module.getReconfRules().size(); i++) {
				items[i] = module.getReconfRules().get(i).getName();
				localRules.add(module.getReconfRules().get(i));
			}
			local.rules.setItems(items);
			local.rules.deselectAll();
			ReconfRule cur = module.getActiveReconfRule();
			// Select the rule in the list.
			if (cur!=null) {
				for (int i=0; i<items.length; i++) {
					if (cur==localRules.get(i)) local.rules.select(i);
				}
			}
			// Enable / disable buttons (except the ADD button).
			for (int i=0; i<local.buttons.length; i++) {
				if (!local.buttons[i].getText().equals(ADD)) {
					local.buttons[i].setEnabled(cur!=null);
				}
			}
			
		} else {

			// ----- Global rules ----- //
			globalRules.clear();
			ReoReconfRegistry.INSTANCE.update();			
			for (RuleEntry rule : ReoReconfRegistry.INSTANCE.getRules()) {
				if (rule.getModule()!=module) globalRules.add(rule);
			}
			Collections.sort(globalRules, new Comparator<RuleEntry>() {
				public int compare(RuleEntry o1, RuleEntry o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
			String[] items = new String[globalRules.size()];
			for (int i=0; i<items.length; i++) {
				items[i] = globalRules.get(i).toString();
			}
			global.rules.setItems(items);
			if (items.length>0) {
				global.rules.select(0);
			}
			
		}
	
	}
	
	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties() {
		// Not used.
	}

}
