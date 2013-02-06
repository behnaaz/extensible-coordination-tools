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
package org.ect.codegen.reo2ea.qia.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.ect.codegen.reo2ea.core.IComposeAction;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.preferences.PreferenceConstants;
import org.ect.codegen.reo2ea.qia.hiding.NewHiding;
import org.ect.codegen.reo2ea.qia.immediateAction.ImmediateAction;
import org.ect.codegen.reo2ea.qia.util.QIAUtil;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.transform.Composition.TransformResult;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.reo.Connector;
import org.ect.reo.Network;

public class Reo2QiaWizard extends Wizard implements IComposeAction{
	private HidePage hidePage;
	private ImmediateActionPage immediateActionPage;
	private Composition composition;
	private Module module = new Module();
	private Network net;

	public Reo2QiaWizard() {
		setWindowTitle("Reo to Extendible automata convertor");
	}

	public void setComposition(Composition composition) {
		this.composition = composition;
	}

	public void setConnector(Connector con) {
		net = ReoUtil.composeNet(con);
	}

	public Module getResult() {
		return module;
	}

	@Override
	public void addPages() {
		addPage(hidePage = new HidePage(net));
		addPage(immediateActionPage = new ImmediateActionPage(net));
		
		/*CheckboxPage page = new CheckboxPage("Blank");
		page.setButtonType(SWT.RADIO);
		addPage(page);*/
	}

	@Override
	public boolean performFinish() {
		final IPreferenceStore prefs = Reo2EAPlugin.getDefault().getPreferenceStore();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
				monitor.beginTask("Progress: ",  6);

				/*for (Component c: net.getComponents()) {
					for (PrimitiveEnd p : c.getAllEnds()) {
//						compute rewards
					}
				}*/

				monitor.subTask("composing primitive automata");
				//==========================================================
				/*TransformResult res;
				try {
					res = composition.transform(net);
				} catch (TransformationException e) {
					throw new InvocationTargetException(e);
				}
				List<Automaton> intermediates = res.automata;
				EndNamingPolicy names = res.namingPolicy;*/
				//==========================================================
				TransformResult res;
				try{
					res = composition.transform(net);
				} catch(TransformationException e){
					throw new InvocationTargetException(e);
				}
				List<Automaton> intermediates = res.automata;
				monitor.worked(1);

				monitor.subTask("calculating product");
				Automaton result = composition.compose(intermediates, monitor);
				Set<String> locals = composition.getLocalPorts();
				String prefix = prefs.getString(PreferenceConstants.PREFIX);
				//=================Hiding part===========================
				Collection<String> hidden;
				if (hidePage.hideSynth) {
					hidden = new HashSet<String>(((StringListExtension)
							result.findExtension(AutomatonPortNamesProvider.EXTENSION_ID)).getValues());
					//hidden.removeAll(hidePage.getChoice(true));
					//remove indexed port names
					Collection<String> list = hidePage.getChoice(true);
					hidden.removeAll(locals);
					for(String a: list){
						if(hidden.contains(a))	hidden.remove(a);
						String name = a.concat("0");
						if(hidden.contains(name)){
							hidden.remove(name);
							boolean cont = true;
							Integer index = 1;
							while(cont){
								name = a.concat(index.toString());
								if(hidden.contains(name)){
									hidden.remove(name);
									index++;
								}
								else	cont = false;
							}
						}
						
					}
					
				} else 
					hidden = hidePage.getChoice(false);
				
				result = NewHiding.getResult(result, hidden);
				//result = Hiding.getResult(result, hidden);
				monitor.worked(1);											
		
				//================Immediate-action part=====================
				if(immediateActionPage.immediate){	
					Collection<String> immediate;
					immediate = immediateActionPage.getChoice(false);
					result = ImmediateAction.getResult(result,immediate);
				}
				monitor.worked(1);
				//=======================================================
				QIAUtil.mergeTrans(result);
				if(prefs.getBoolean(PreferenceConstants.TRIM))
					QIAUtil.trim(result);				
				if(prefs.getBoolean(PreferenceConstants.PRETTY))
					QIAUtil.beautify(result, prefix);  

				
				result.setName(net.getName());
				module.getAutomata().add(result);
				
				//========================================================
				/*for (Component c: net.getComponents()) {
					for (PrimitiveEnd p : c.getAllEnds()) {
						names.getName(p);
					}
				}*/
				//=========================================================

				if(prefs.getBoolean(PreferenceConstants.TUTOR))
					module.getAutomata().addAll(intermediates);
			}
		};

		try {
			getContainer().run(true, true, op);    

		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
    		MessageDialog.openError(getShell(), "Reo2EA Error", Reo2EAPlugin.handleError(e));
			return false;
		} 

		return true;
	}
}
