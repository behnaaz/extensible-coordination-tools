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


package org.ect.reo.prefs.ui;

import java.text.ParseException;
import java.text.ParsePosition;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.text.NumberFormat;


public class DoubleFieldEditor extends StringFieldEditor {
		
	private double minValidValue = 00.009;
	private double maxValidValue = 99.999;
	
	public DoubleFieldEditor(String pref, String label, Composite parent, double min, double max) {
		super(pref,label,parent);
		this.minValidValue = min;
		this.maxValidValue = max;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.StringFieldEditor#doCheckState()
	 */
	protected boolean doCheckState() {
			
		Text text = getTextControl();
		if (text == null) return false;
			
		try {
			NumberFormat numberFormatter = NumberFormat.getInstance();
			ParsePosition parsePosition = new ParsePosition(0);
			Number parsedNumber = numberFormatter.parse(text.getText(), parsePosition);
			
			if (parsedNumber == null) {
				showErrorMessage();
				return false;
			}
				
			double number = forceDouble(parsedNumber).doubleValue();
				
			if (number >= minValidValue && number <= maxValidValue && parsePosition.getIndex() == text.getText().length()) {
				clearErrorMessage();
				return true;
			} else {
				showErrorMessage();
				return false;
			}
				
		} catch (NumberFormatException e1) {
			showErrorMessage();
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.StringFieldEditor#doLoadDefault()
	 */
	protected void doLoadDefault() {
		Text text = getTextControl();
		if (text != null) {
			double value = getPreferenceStore().getDefaultDouble(getPreferenceName());
			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			text.setText(numberFormatter.format(value));
		}
		valueChanged();
	}
		
	/* (non-Javadoc)
	 * Method declared on FieldEditor.
	 */
	protected void doLoad() {
		Text text = getTextControl();			
		if (text != null) {
			double value = getPreferenceStore().getDouble(getPreferenceName());
			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			text.setText(numberFormatter.format(value));				
		}
	}		
		
	protected void doStore() {
		NumberFormat numberFormatter = NumberFormat.getInstance();				
		Double value;
		try {
			value = forceDouble(numberFormatter.parse(getTextControl().getText()));
			getPreferenceStore().setValue(getPreferenceName(), value.doubleValue());				
		} catch (ParseException e) {
			showErrorMessage();
		}
			
	}
		
	/**
	 * The NumberFormatter.parse() could return a Long or Double
	 * We are storing all values related to the page setup as doubles
	 * so we call this function when ever we are getting values from
	 * the dialog.
	 * @param number
	 * @return
	 */
	private Double forceDouble(Number number) {
		if (!(number instanceof Double))
			return new Double(number.doubleValue());			
		return (Double) number;
	}	

}

