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
package org.ect.codegen.reo2mc.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.plugin.ExtensionManager;
import org.ect.codegen.reo2ea.plugin.ExtensionManager.TransformExt;
import org.ect.codegen.reo2ea.qia.hiding.NewHiding;
import org.ect.codegen.reo2ea.qia.immediateAction.ImmediateAction;
import org.ect.codegen.reo2ea.qia.util.QIAUtil;
import org.ect.codegen.reo2ea.qia.wizards.HidePage;
import org.ect.codegen.reo2ea.qia.wizards.ImmediateActionPage;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.transform.Composition.TransformResult;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.codegen.ea2mc.actions.QIAintoMC;
import org.ect.reo.Connector;
import org.ect.reo.Network;

public class Reo2MCWizard extends BasicNewResourceWizard{
	public FileCreationPage newFilePage;
	public HidePage hidePage;
	public ImmediateActionPage immediateActionPage;
	public static final Composition composition;
	private Network net;
	private Module module = new Module();
	
	static {
		TransformExt qiaTransform = new ExtensionManager().getExtensions().get("org.ect.codegen.reo2ea.qia");
		try {
			qiaTransform.create();
		} catch (CoreException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			composition = qiaTransform.composition;			
		}
	}
    
	public Reo2MCWizard (Connector connector){
		super();
		net = ReoUtil.composeNet(connector); 
					
		/*Network nw = new Network(connector);
		nw.update();
		org.ect.reo.Module copy = new org.ect.reo.Module();
		org.ect.reo.Module.copyContent(nw, copy);
		this.net = copy.createNetworks().get(0);
		net.setName(connector.getName());
		*/		
		/*this.connector = (Connector) EcoreUtil.copy(connector);*/
		setWindowTitle("Stochastic Reo to MC convertor");
	}
	
	public void addPages(){
		newFilePage = new FileCreationPage("Save MC to", getSelection());//$NON-NLS-1$
		newFilePage.setAllowExistingResources(true);
        addPage(newFilePage);      
        addPage(hidePage=new HidePage(net));
        addPage(immediateActionPage = new ImmediateActionPage(net));
	}
	
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        super.init(workbench, currentSelection);
        setNeedsProgressMonitor(true);
    }

	
	@Override
	public boolean performFinish() {
		
		final String fileType = newFilePage.getFileType();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			
			public void run(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
				try{
					//====================Product=====================================
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
					//=====================Hiding=====================================
					Set<String> locals = composition.getLocalPorts();
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
					
					result = NewHiding.getResult(result, hidden);//,list);
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
/*					List<Automaton> intermediates = composition.transform(net).automata;
					monitor.subTask("translation to QIA");
					Automaton result = composition.compose(intermediates, monitor);
*/					
					String prefix = (net.getName()==null ? 
							"state": net.getName()).trim().toLowerCase();
					prefix = prefix.replaceAll("\\W", "_");
					
	    			result.setName(net.getName());
	    			
	    			QIAintoMC translation = new QIAintoMC(result);
	    			Automaton MC = translation.compute();
	    			if(fileType == "sm" || fileType == "csv"){
	    				Generation generation = new Generation(newFilePage,MC,fileType);
	    				generation.compute();
	    			}
				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e);
				}
			}

		}; 
		
        try {
            getContainer().run(false, false, op);            
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), "Error", 
            		realException.getStackTrace().toString());
            realException.printStackTrace();
            return false;
        } 
        
        return true;
	}
}
