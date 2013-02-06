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
package org.ect.codegen.reo2ea.ca.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.util.XMIWrangler;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.reo.PropertyHolder;
import org.ect.reo.ui.properties.helpers.ComponentPropertySection;
import org.ect.reo.util.PropertyHelper;

public class CAPropertySection extends ComponentPropertySection {

	private Text fileField;
	private CCombo caCombo;
	
	@Override
	public void createControls(final Composite parent, TabbedPropertySheetPage page) {
    	super.createControls(parent, page);
    	
    	TabbedPropertySheetWidgetFactory wf = getWidgetFactory();
		Group group = wf.createGroup(parent, "Constraint Automata Semantics");		
		group.setLayout(new GridLayout(3, false));

		wf.createLabel(group, "File path");				
		fileField = wf.createText(group, null, SWT.BORDER);
		fileField.setMessage("Choose CA file");

    	Button button = wf.createButton(group, "Browse", SWT.PUSH);
		button.setSelection(true);
		button.addListener(SWT.Selection, new Listener() {			
			public void handleEvent(Event event) {
				IFile[] file;
				if ((file= createWiz()).length>0) 
					try {
						String fileName = file[0].getFullPath().toString();
						fileField.setText(fileName);
						caCombo.setItems(getContents(fileName));
						caCombo.select(0);
					} catch (IOException e) {
						Reo2EAPlugin.log("error", e);
					}
			}
		});
    	
		wf.createLabel(group, "Automaton");		
		caCombo = wf.createCCombo(group, SWT.DROP_DOWN);
//		caCombo.setSize(800, 800);
		caCombo.setItems(new String[]{"			"});
		caCombo.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event event) {
				commit();
			}
		});
	}

	@Override
	protected void updateProperties() {
		if (!(getComponent() instanceof PropertyHolder) ||
				fileField.getText()==null ||
				caCombo.getSelectionIndex()<0) 
			return;

		String sem = fileField.getText() + ":" + caCombo.getItem(caCombo.getSelectionIndex());
		PropertyHelper.setOrAdd((PropertyHolder) getComponent(), "CA", sem);
		
	}

	@Override
	protected void updateViews() {
		if (!(getComponent() instanceof PropertyHolder)) 
			return;

		String str = PropertyHelper.getFirstValue((PropertyHolder) getComponent(), "CA");
		String[] semantics;
		if (str==null || (semantics = str.split(":")).length<2) {
			fileField.setText("");
			caCombo.setItems(new String[]{"			"});
			caCombo.select(0);
			return;
		}
		
		String name = semantics[0];
		fileField.setText(name);
		try {
			String[] contents = getContents(name);
			caCombo.setItems(contents);
			caCombo.select(Arrays.asList(contents).indexOf(semantics[1]));
		} catch (IOException e) {
			Reo2EAPlugin.log("error", e);
			return;
		}
		
//		System.err.println( getConnectable()+" "+semantics[1]);
	}

	private IFile[] createWiz() {
		List<ViewerFilter> vf = Arrays.asList(new ViewerFilter[]{new FileExtensionFilter("ea")});
		return WorkspaceResourceDialog.openFileSelection(
				getPart().getSite().getShell(), "Choose CA", "", false, null, vf);
		
	}
	
	private String[] getContents(String pathName) throws IOException { 
		Module module = XMIWrangler.loadAutomata(URI.createPlatformResourceURI(pathName, true));
		List<String> names = new ArrayList<String>();
		for (Automaton a: module.getAutomata()) 
			names.add(a.getName());
		
		return names.toArray(new String[0]);
	}
/*	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (getConnectable()!=null)
			commit();
		super.setInput(part, selection);
	}*/
}

class FileExtensionFilter extends ViewerFilter {

	private String fTargetExtension;

	public FileExtensionFilter(String targetExtension) {
		fTargetExtension = targetExtension;
	}

	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IFile) {
			return ((IFile) element).getName().toLowerCase(Locale.ENGLISH).endsWith("." + fTargetExtension); //$NON-NLS-1$
		}

		if (element instanceof IProject && !((IProject) element).isOpen())
			return false;

		if (element instanceof IContainer) { // i.e. IProject, IFolder
			try {
				IResource[] resources = ((IContainer) element).members();
				for (int i = 0; i < resources.length; i++) {
					if (select(viewer, parent, resources[i]))
						return true;
				}
			} catch (CoreException e) {
			}
		}
		return false;
	}

}