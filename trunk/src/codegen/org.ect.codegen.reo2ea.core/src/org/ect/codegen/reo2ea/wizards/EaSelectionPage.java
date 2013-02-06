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
package org.ect.codegen.reo2ea.wizards;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.ect.codegen.reo2ea.core.IComposeAction;
import org.ect.codegen.reo2ea.plugin.ExtensionManager.TransformExt;
import org.ect.reo.Connector;

public class EaSelectionPage extends WizardSelectionPage {
	private Collection<TransformExt> extensions;
	private Collection<Button> buttons = new ArrayList<Button>();
	private Connector connector;
	TransformExt selection;
	
	public EaSelectionPage(Collection<TransformExt> extensions, Connector connector) {
		super("Automaton Type");
		this.extensions = extensions;
		this.connector = connector;
	}

	public void createControl(Composite parent) {
		Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setLayout(new RowLayout(SWT.VERTICAL));
//		group.setText(title);
		
		for (TransformExt ext : extensions) {
			Button b = new Button(group, SWT.RADIO);
			b.setData(ext);
			b.setText(ext.description + "(" + ext.propKey + ")");
			buttons.add( b);
			
			b.addSelectionListener(new SelectionAdapter() {		
				@Override
				public void widgetSelected(SelectionEvent e) {					
					selection = (TransformExt) e.widget.getData();
					setSelectedNode(
							getNode(selection
							));
				}
			});			
		}
		
		setControl(group);
	}
	
	@Override 
	public boolean canFlipToNextPage() {
		return getSelectedNode()!=null;
	};
	
	private IWizardNode getNode(final TransformExt transformExt) {
		setPageComplete(true);
		return new IWizardNode() {

			public void dispose() {
				if (transformExt.wizard!=null)
					transformExt.wizard.dispose();
			}

			public Point getExtent() {
				return new Point(-1, -1);
			}

			public IWizard getWizard() {
				if (transformExt.wizard==null)
					try {
						transformExt.create();
						if (transformExt.wizard instanceof IComposeAction) { 
							IComposeAction wizard = (IComposeAction)transformExt.wizard;
							wizard.setComposition(transformExt.composition);
							wizard.setConnector(connector);
						} else
							throw new RuntimeException("Can't create wizard!");
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				return transformExt.wizard;
			}

			public boolean isContentCreated() {
				return transformExt.wizard!=null;
			}

		};
	}

}
