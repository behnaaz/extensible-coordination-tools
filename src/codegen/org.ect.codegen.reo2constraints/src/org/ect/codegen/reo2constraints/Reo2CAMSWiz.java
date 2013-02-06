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
package org.ect.codegen.reo2constraints;

 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import org.ect.codegen.reo2constraints.cons2casm.OutputType;
import org.ect.codegen.reo2constraints.cons2casm.ReoSemanticsGenerator;
import org.ect.codegen.reo2constraints.generator.Constraint;
import org.ect.codegen.reo2constraints.visitor.ConstraintSemanticsGenerator;
import org.ect.ea.EA;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Reo;
import org.ect.reo.channels.FIFO;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class Reo2CAMSWiz extends BasicNewFileResourceWizard {
	class MyWizardNewFileCreationPage extends WizardNewFileCreationPage{
		InputStream inputStream;
		
		public MyWizardNewFileCreationPage(String pageName,
				IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected InputStream getInitialContents() {
			return inputStream;
		};
	}

	private  MyWizardNewFileCreationPage newFilePage;
	private ConnectorEditPart connectorEditPart;
	protected Object result;
	protected OutputType targetOperationalSemantics = OutputType.CASM;
	private String fileName = "default";
	private boolean test = true;
	
	private Object generateOperationalSemantics(org.ect.reo.Module mod, OutputType outputType, IProgressMonitor monitor) {
		String ts, taftercons, tall;
		monitor.setTaskName("Generating CASM");
        monitor.beginTask("Generating CASM", 2);
		// Compute the definition.
    	Object result;
		try {
    		if (mod == null || mod.getConnectors().size()==0){
			System.out.println("reo2constraints "+"Empty!!!!!");
    			return null;
    		}
    		ts = getTime();
    		ConstraintSemanticsGenerator csg = new ConstraintSemanticsGenerator(mod);
    		
    		//task 1
    		Constraint constraints = csg.computeConnectorSemantics(mod.getAllConnectors().get(0), monitor);
    		taftercons =  getTime();
    		monitor.worked(1);
    		//task 2
    		//calculate CASM
    		/*
    		 * 		// Check if all the ends have valid names
		String problems = dcs.getProblems();
		if (problems != null && problems.trim().length() > 0) {
					System.err.println("There are some errors in the Reo network:\r\n "+problems);
					return "";
				}

    		 *///new File("alakiiiii.ea")
    		 ReoSemanticsGenerator rg = new ReoSemanticsGenerator(constraints, "x", new File((mod.getName()==null || mod.getName().isEmpty()?fileName:mod.getName().trim())+".ea"), true, true, false, false, csg.getPreprocssinfo(), csg.getStatemgr());
    		 result = rg.generateConstraintSemantics(outputType);
    		 tall = getTime();
    		 monitor.worked(1);
		} catch (Throwable e) {
			System.err.println(e);
    		//return new Status(IStatus.ERROR, ReoUIPlugin.PLUGIN_ID, IStatus.ERROR, "Computing animation definition failed: " + e, e);
    	return null;
    	}
		System.out.println(ts+" - "+taftercons+" - "+tall);
		return result;
	}

	private String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
		   //get current date time with Date()
		   Date date = new Date();
		   System.out.println(dateFormat.format(date));
	 
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   System.out.println(dateFormat.format(cal.getTime()));
		   return dateFormat.format(cal.getTime());
	}

	public void saveToFile(Automaton automaton) {
		try {
		File tfile = File.createTempFile("tmp", "tmp");
		URI uri = URI.createFileURI(tfile.getAbsolutePath());
		Resource res = new ResourceSetImpl().createResource(uri);
	
		Module module = AutomataFactory.eINSTANCE.createModule();
	    module.getAutomata().add(automaton);
	    res.getContents().add(module);
	    res.getContents().add(EA.createDiagram(module));
	    res.save(Collections.EMPTY_MAP);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        res.save(out, null);
        
		newFilePage.inputStream = new ByteArrayInputStream(out.toByteArray()); 
		newFilePage.createNewFile();		
		}
		catch (Exception e) {
		        System.err.println(e);
		} 	
	}	
	
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		
		newFilePage =	new MyWizardNewFileCreationPage("Reo to CASM", StructuredSelection.EMPTY);
		newFilePage.setFileExtension("ea");
	
		Object first = ((IStructuredSelection)selection).getFirstElement();
		if (first instanceof ConnectorEditPart) {
			connectorEditPart = (ConnectorEditPart) first;
		}    
	}
	
	@Override
	public void addPages() {
		addPage(newFilePage);
	}
	
	@Override
	public boolean performFinish() {
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
	        	try {
	        		if (test){
	        			performTest(25, monitor);
	        		} else {
	        		Connector cnn = (Connector)connectorEditPart.getNotationView().getElement(); 
	        		result = generateOperationalSemantics(cnn.getModule(), targetOperationalSemantics, monitor );
	        		}
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}				
			}

		};
		  
		try {
			getContainer().run(true, true, op);			
			if (!test){
			if (targetOperationalSemantics == OutputType.CASM){
				saveToFile((Automaton)result);
			}}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}

	public boolean performTest(final int num, IProgressMonitor monitor) {
				try {
	        		for (int i=1; i<=num; i++){
	        			org.ect.reo.Module mod = makeFIFOs(i); 
	        			String st = getTime();
	        			result = generateOperationalSemantics(mod, targetOperationalSemantics, monitor );
	        			saveToFile((Automaton)result);
	        			String ft = getTime();
	        			report("C:\\Users\\Balali\\Dropbox\\MyThesis\\05RCSP\\exampless\\fifo"+i+"eval.txt",st,ft);
	        		}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;				
			}

			private void report(String nam,String st, String ft) {
				try{ 
				FileWriter outFile = new FileWriter(nam);
				         PrintWriter out = new PrintWriter(outFile);
				             
				            // Also could be written as follows on one line
				            // Printwriter out = new PrintWriter(new FileWriter(args[0]));
				         
				             // Write text to file
				             out.println("starttime is "+st);
				             out.println("endtime is "+ft);
				             out.close();
				         } catch (IOException e){
				             e.printStackTrace();
				      } 
				
			}

			private org.ect.reo.Module makeFIFOs(int n) {
				org.ect.reo.Module mod=null;
				try {
					mod = Reo.createModule(URI.createURI("testfifos"+n));
					Connector cnn = new Connector("testfifos"+n);
					mod.getConnectors().add(cnn);
					Node src = new Node(new Character('a').toString());
					cnn.getNodes().add(src);
					for (int i=0; i<n;i++){
						Node snk = new Node(Character.toChars(Character.getNumericValue('a')+1).toString());
						cnn.getNodes().add(snk);
						FIFO f = new FIFO(src, snk);
						f.getSourceEnd().setName(f.getName()+"c");
						f.getSinkEnd().setName(f.getName()+"k");
						cnn.getPrimitives().add(f);
						src=snk;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return mod;
			}

			  
	}