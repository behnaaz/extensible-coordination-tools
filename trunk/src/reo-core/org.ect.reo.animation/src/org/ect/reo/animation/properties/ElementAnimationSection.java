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
package org.ect.reo.animation.properties;

import java.text.ParseException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.animation.Animatable;
import org.ect.reo.animation.AnimationParser;
import org.ect.reo.animation.AnimationPrinter;
import org.ect.reo.animation.AnimationSemanticsProvider;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.ColouringBisimulation;
import org.ect.reo.colouring.ColouringConverter;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.ui.properties.helpers.ConnectablePropertySection;


public class ElementAnimationSection extends ConnectablePropertySection {

	protected Text text;
	protected Button validate, format, minimize;
	protected Group group;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);

		// Create a container.
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		GridLayout layout = new GridLayout(1, true);
		layout.marginWidth = ITabbedPropertyConstants.HSPACE;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;
		composite.setLayout(layout);

		// Create a group inside of the container.
		group = getWidgetFactory().createGroup(composite, "Animation definition:");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(1, false));

		// A text widget inside of the group.
		text = getWidgetFactory()
				.createText(group, "", SWT.WRAP | SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.minimumHeight = 100;
		text.setLayoutData(data);

		// Important: add a listener to the text widget.
		addTextListener(text);

		// Buttons inside of the group.
		Composite buttons = getWidgetFactory().createComposite(group);
		buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		buttons.setLayout(new RowLayout());
		addButtons(buttons);

	}

	protected void addButtons(Composite composite) {

		validate = getWidgetFactory().createButton(composite, "Validate", SWT.PUSH);
		validate.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (isCustomPrimitive()) validate();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		format = getWidgetFactory().createButton(composite, "Format", SWT.PUSH);
		format.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (isCustomPrimitive()) printAndCommit(parse());
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		minimize = getWidgetFactory().createButton(composite, "Minimize", SWT.PUSH);
		minimize.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (isCustomPrimitive()) minimize();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

	}

	/**
	 * Validate the currently entered animation text.
	 */
	protected void validate() {
		if (parse() != null) {
			MessageDialog.openInformation(text.getShell(), "Validation", "Validation successful.");
		}
	}

	protected void printAndCommit(AnimationTable table) {
		if (table != null) {
			AnimationPrinter printer = new AnimationPrinter(getConnectable());
			text.setText(printer.printAllTables(table)); // Print the table.
		} else {
			text.setText("");
		}
		commit(); // Commit the change to the model.
	}

	/**
	 * Format the text.
	 */
	protected void minimize() {
		AnimationTable table = parse();
		if (table != null) {
			int rem1 = ColouringBisimulation.minimize(table, new NullProgressMonitor());
			int rem2 = ColouringConverter.applyFlipRule(table);
			printAndCommit(table);
			MessageDialog.openInformation(text.getShell(), "Minimize", "Removed " + rem2 + " colourings " + "and "
					+ rem1 + " colouring tables.");
		}
	}

	protected AnimationTable parse() {
		AnimationParser parser = new AnimationParser(getConnectable());
		try {
			return parser.parseAllTables(text.getText());
		} catch (ParseException e) {
			MessageDialog.openError(text.getShell(), "Validation", e.getMessage());
			text.setSelection(e.getErrorOffset());
			return null;
		}
	}

	@Override
	public void updateViews() {

		// Get the animation definition:
		String semantics = ReoTextualSemantics.getElementSemantics(getConnectable(), AnimationSemanticsProvider.KEY);

		// If empty, try to generate it:
		if (semantics == null || semantics.trim().equals("")) {
			try {
				AnimationTable table = ((Animatable) getConnectable()).getAnimationTable();
				semantics = new AnimationPrinter(getConnectable()).printAllTables(table);
			} catch (Throwable t) {
			}
		}

		// Refresh the text.
		text.setText(semantics == null ? "" : semantics);
		setEditable(isCustomPrimitive());

	}

	private void setEditable(boolean editable) {
		text.setEditable(editable);
		validate.setEnabled(editable);
		format.setEnabled(editable);
		minimize.setEnabled(editable);
	}

	@Override
	protected void updateProperties() {
		if (isCustomPrimitive()) {
			AnimationSemanticsProvider.setAnimationDefinition((CustomPrimitive) getConnectable(), text.getText());
		}		
	}

}
