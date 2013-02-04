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
package org.ect.reo.mcrl2.properties;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.DataAware;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.PropertyHolder;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2Preferences;
import org.ect.reo.prefs.ui.ReoPreferenceCheckButton;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.ui.properties.helpers.ReoPropertySection;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * 
 * @author Christian Krause
 * @edited Behnaz Changizi
 * @generated NOT
 */
public class MCRL2PropertySection extends ReoPropertySection implements IFilter {
	
	// We use a static field for the formula.
	private static String formulaText = "[true*]<true>true";
	
	// Colors.
	private static Color COLOR_NORMAL = new Color(null, 255, 255, 255);
	private static Color COLOR_GENERATED = new Color(null, 240, 240, 255);
	
	protected Group group;
	protected Connector connector;
	protected Connectable element;
	protected Component component;
	StyledText spec;

	//private ReoPreferenceCheckButton deriveButton;
	private Text sort, formula, expression;
	private Button hideCheckBox;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object object) {
		if (!(object instanceof IGraphicalEditPart)) return false;
		IGraphicalEditPart editpart = (IGraphicalEditPart) object;
		EObject element = editpart.getNotationView().getElement();
	    return (element instanceof Connectable || element instanceof Connector);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);
		
		// Adjust the layout data for the parent widget:
		if (parent.getLayoutData() instanceof GridData) {
			((GridData) parent.getLayoutData()).verticalAlignment = SWT.FILL;
			((GridData) parent.getLayoutData()).grabExcessVerticalSpace = true;
		}
		
		// Create a container.
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		GridLayout layout = new GridLayout(1,true);
		layout.marginWidth = ITabbedPropertyConstants.HSPACE;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;
		composite.setLayout(layout);
				
		group = getWidgetFactory().createGroup(composite, "Specification");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(3,false));
		
		addOptions(group);
		addOptimizations(group);
		addSimulationButtons(group);
		
		Label label = getWidgetFactory().createLabel(group, "Definition:");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING));
		
		// Wrap the text widget:
		final Point minSize = new Point(150,200);
		Composite wrapper = new Composite(group, SWT.NONE) {
			@Override
			public Point computeSize (int wHint, int hHint, boolean changed) {
				return minSize;
			}
		};
		wrapper.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		wrapper.setLayout(new FillLayout());
				
		spec = new StyledText(wrapper, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		new MCRL2StyledTextAdapter(spec);
		spec.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (isCustom()) {
					try {
						Reo2MCRL2.setSpecification((CustomPrimitive) element, spec.getText());
					} catch (Throwable t) {}
				}
			}
		});
		
		addActions(group);

		group = getWidgetFactory().createGroup(composite, "Element Properties");
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(3,false));
		
		sort = addTextField(group, "Datatype:");
		expression = addTextField(group, "Expression:");				
	
		hideCheckBox = getWidgetFactory().createButton(group, "Hide", SWT.CHECK);
		hideCheckBox.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) { 
				widgetSelected(e); }
			public void widgetSelected(SelectionEvent e) { 
				updateProperties(); 
			}
		});

	}
	
	
	protected Text addTextField(Composite composite, String name) {
		
		Label label = getWidgetFactory().createLabel(composite, name);
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		Text text = getWidgetFactory().createText(composite, "", SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		text.setLayoutData(data);
		addTextListener(text);
		return text;
		
	}

	
	protected void addOptions(Composite composite) {

		Label label = getWidgetFactory().createLabel(composite, "Options:");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		Composite options = getWidgetFactory().createComposite(composite);
		options.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		GridData grid = new GridData(GridData.BEGINNING);
		grid.horizontalSpan = 2;
		options.setLayoutData(grid);
		options.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		createCheckButton(options, "With components", Reo2MCRL2Preferences.NETWORK_SCOPE);
		createCheckButton(options, "With data", Reo2MCRL2Preferences.INCLUDE_DATA);
		createCheckButton(options, "With colours", Reo2MCRL2Preferences.INCLUDE_COLOUR);
		createCheckButton(options, "I/O actions", Reo2MCRL2Preferences.INTENSIONAL_ENCODING);
		createCheckButton(options, "Blocking", Reo2MCRL2Preferences.BLOCKING_ENCODING);
		createCheckButton(options, "Use CADP", Reo2MCRL2Preferences.USE_CADP);
		
	}
	
	
	protected void addOptimizations(Composite composite) {
		
		Label label = getWidgetFactory().createLabel(composite, "Traversal:");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		Composite optimizations = getWidgetFactory().createComposite(composite);
		optimizations.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		GridData grid = new GridData(GridData.BEGINNING);
		grid.horizontalSpan = 2;
		optimizations.setLayoutData(grid);
		optimizations.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		for (int i=-1; i<TraversalType.values().length; i++) {
			final TraversalType type = (i<0) ? null : TraversalType.values()[i];
			final String name = (type==null) ? "none" : type.getName();
			final Button button = getWidgetFactory().createButton(optimizations, name, SWT.RADIO);
			button.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
				public void widgetSelected(SelectionEvent e) {
					Reo2MCRL2Preferences.setTraversalType(type);
					updateViews(); 
				}
			});
			button.setSelection(type==Reo2MCRL2Preferences.traversalType());
		}
	}
	
	
	protected void addSimulationButtons(Composite composite){
		Label label = getWidgetFactory().createLabel(composite, "");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		Composite buttons = getWidgetFactory().createComposite(composite);
		buttons.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		GridData grid = new GridData(GridData.BEGINNING);
		grid.horizontalSpan = 2;
		buttons.setLayoutData(grid);
		buttons.setLayout(new RowLayout(SWT.HORIZONTAL));
		//add buttons
		Button ltsgraph = getWidgetFactory().createButton(buttons, "Show LTS", SWT.PUSH);
		ltsgraph.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Job job = new ShowLTSJob(spec.getText());
				job.schedule();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		Button simulate = getWidgetFactory().createButton(buttons, "Simulate", SWT.PUSH);
		simulate.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				SimulateJob job = new SimulateJob(spec.getText(), formula.getText());
				job.setUseCADP(Reo2MCRL2Preferences.useCADP());
				job.schedule();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		Button ltsa = getWidgetFactory().createButton(buttons, "Generate FSP", SWT.PUSH);
		ltsa.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				GenerateFSPJob job = new GenerateFSPJob(spec.getText(), formula.getText());
				job.schedule();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	}
	
	
	private ReoPreferenceCheckButton createCheckButton(Composite parent, String name, String prefKey) {
		ReoPreferenceCheckButton button = new ReoPreferenceCheckButton(parent, SWT.NONE, name, prefKey);
		getWidgetFactory().adapt(button.getControl(), true, true);
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) { updateViews(); }
			public void widgetSelected(SelectionEvent e) { updateViews(); }
		});
		return button;
	}
	
	
	protected void addActions(Composite composite) {
		
		Label label = getWidgetFactory().createLabel(composite, "Formula:");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		formula = getWidgetFactory().createText(composite, "", SWT.SINGLE | SWT.BORDER);
		formula.setText(formulaText);
		formula.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		formula.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) { 
				if (e.keyCode==SWT.CR) doVerify();
			}
		});
		
		Composite buttons = getWidgetFactory().createComposite(composite, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buttons.setLayoutData(new GridData(GridData.BEGINNING));
		buttons.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button verify = getWidgetFactory().createButton(buttons, "Check", SWT.PUSH);
		verify.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) { doVerify(); }
			public void widgetDefaultSelected(SelectionEvent e) { doVerify(); }
		});
		
	}
	
	private void doVerify() {
		// Remember the formula text.
		formulaText = formula.getText();
		VerifyFormulaJob job = new VerifyFormulaJob(spec.getText(), formula.getText(), getShell());
		job.setUseCADP(Reo2MCRL2Preferences.useCADP());
		job.schedule();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties() {		

		if (element!=null) {
			Reo2MCRL2.setSort((PropertyHolder) element, sort.getText());
			if (element instanceof DataAware) {
				((DataAware) element).setExpression(expression.getText());
			}
		}
		if (connector!=null) {
			Reo2MCRL2.setSort(connector, sort.getText());			
		}
		
		if (element instanceof Node) {
			boolean hide = hideCheckBox.getSelection();
			PropertyHelper.setOrAddHidden((Node) element, "hide", String.valueOf(hide));
		}
		
		updateSpecification();
		
	}
	
	private boolean isCustom() {
		return (element instanceof CustomPrimitive) &&
				(!(element instanceof Component) ||
 				   element.getClass()==Component.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews() {
		
		updateSpecification();

		String datatype = null;		
		if (element!=null) {	
			datatype = Reo2MCRL2.getSort((PropertyHolder) element);
		} else if (connector!=null) {
			datatype = Reo2MCRL2.getSort(connector);
		}
		sort.setText(datatype==null ? "" : datatype);
		//deriveButton.getControl().setEnabled(connector!=null);
		sort.setEnabled((connector!=null && !Reo2MCRL2Preferences.networkScope()) || 
				element instanceof Component /*&& !deriveButton.getSelection()*/);
		
		if (element instanceof DataAware) {
			String expr = ((DataAware) element).getExpression();
			expression.setText(expr==null ? "" : expr);
			expression.setEnabled(true);
		} else {
			expression.setText("");
			expression.setEnabled(false);			
		}

		if (element instanceof Node) {
	        Node node = (Node) element;
	        boolean hide = node.isMixedNode();
	        String hideValue = PropertyHelper.getFirstValue(node, "hide");
	        if (hideValue!=null) {
	            hide = Boolean.parseBoolean(hideValue);
	        }
			hideCheckBox.setEnabled(true);
	        hideCheckBox.setSelection(hide);
		} 	
		else {
			hideCheckBox.setEnabled(false);
			hideCheckBox.setSelection(false);
		}
	}
	
	
	/*
	 * Upate the specification text.
	 */
	private void updateSpecification() {		
		
		String specification = null;		
		Color color = COLOR_GENERATED;
		if (isCustom()) {
			color = COLOR_NORMAL;
		}
		if (element!=null) {
			specification = ReoTextualSemantics.getElementSemantics(element, Reo2MCRL2.SEMANTICS_KEY);
		}
		else if (connector!=null) {
			if (Reo2MCRL2Preferences.networkScope()) {
				Network network = new Network(connector);
				specification = ReoTextualSemantics.computeNetworkSemantics(network, Reo2MCRL2.SEMANTICS_KEY, new NullProgressMonitor());	
			} else {
				specification = ReoTextualSemantics.computeConnectorSemantics(connector, Reo2MCRL2.SEMANTICS_KEY, new NullProgressMonitor());
			}
		}
		spec.setText(specification==null ? "" : specification);
		spec.setBackground(color);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		element = null;
		connector = null;
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof IGraphicalEditPart);
		IGraphicalEditPart editpart = (IGraphicalEditPart) input;
		EObject element = editpart.getNotationView().getElement();
		if (element instanceof Connectable) this.element = (Connectable) element; else
		if (element instanceof Connector) this.connector = (Connector) element;
	}
	
}
