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
package org.ect.codegen.reo2ea.ca.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.ect.codegen.reo2ea.ca.hiding.Hiding;
import org.ect.codegen.reo2ea.core.IComposeAction;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.preferences.PreferenceConstants;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.transform.Composition.TransformResult;
import org.ect.codegen.reo2ea.util.CAUtil;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.reo.Connector;
import org.ect.reo.Network;

public class Reo2CaWizard extends Wizard implements IComposeAction {
	private HidePage hidePage;
	protected Composition composition;
	protected Module module = new Module();
	protected Network net;


	public void setComposition(Composition composition) {
		this.composition = composition;		
	}

	public void setConnector(Connector connector) {
		net = ReoUtil.composeNet(connector);
	}

	public Module getResult() {
		return module;
	}
	
	@Override
	public void addPages() {
		addPage(hidePage=new HidePage(net));
	}

	@Override
	public boolean performFinish() {
    	final IPreferenceStore prefs = Reo2EAPlugin.getDefault().getPreferenceStore();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
				monitor.beginTask("Progress: ", 7);

				monitor.subTask("composing primitive automata");
				TransformResult intermediates;
				try {
					intermediates = composition.transform(net);
				} catch (TransformationException e) {
					throw new InvocationTargetException(e);
				}
				monitor.worked(1);

				monitor.subTask("calculating product");
				Automaton result = composition.compose(intermediates.automata, monitor);				
				monitor.worked(2);
				String prefix = prefs.getString(PreferenceConstants.PREFIX);

				Collection<String> hidden;
				if (hidePage.hideSynth) {
					hidden = new HashSet<String>(((StringListExtension)
							result.findExtension(AutomatonPortNamesProvider.EXTENSION_ID)).getValues());
					hidden.removeAll(hidePage.getChoice(true));
				} else 
					hidden = hidePage.getChoice(false);
				
				result = Hiding.getResult(result, hidden);
				monitor.worked(1);											

				CAUtil.mergeTrans(result);
				if(prefs.getBoolean(PreferenceConstants.TRIM))
					CAUtil.trim(result);				
				if(prefs.getBoolean(PreferenceConstants.PRETTY))
					CAUtil.beautify(result, prefix);  

				monitor.worked(1);											
				result.setName(net.getName());
				module.getAutomata().add(result);

				if(prefs.getBoolean(PreferenceConstants.TUTOR))
					module.getAutomata().addAll(intermediates.automata);
			}
		};
		
	   	try {
    		getContainer().run(true, true, op);    

    	} catch (InterruptedException e) {
    		return false;
    	} catch (InvocationTargetException e) {    		
    		MessageDialog.openError(getShell(), "Reo2EA Error", 
    				Reo2EAPlugin.handleError(e));
    		return false;
    	} 

    	return true;
	}
}
