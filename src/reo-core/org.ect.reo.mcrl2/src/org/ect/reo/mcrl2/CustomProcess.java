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
package org.ect.reo.mcrl2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * @see org.ect.reo.mcrl2.MCRL2Package#getCustomProcess()
 * @model kind="class"
 * @generated
 */
public class CustomProcess extends org.ect.reo.mcrl2.Process {
	
	/**
	 * Variable replacements.
	 * @generated NOT
	 */
	private Map<String,String> replacements = new HashMap<String,String>();

	/**
	 * Precomp statements.
	 * @generated NOT
	 */
	private Map<String,String> statements = new LinkedHashMap<String,String>();

	
	/**
	 * @generated NOT
	 */
	public CustomProcess(String definition) {
		setDefinition(definition);
	}
	
	/**
	 * @generated NOT
	 */
	public Map<String,String> getVariableReplacements() {
		return replacements;
	}

	/**
	 * @generated NOT
	 */
	public Map<String,String> getPrecompStatements() {
		return statements;
	}	

	/**
	 * @generated NOT
	 */
	public Map<String,String> parsePrecompStatements() {
		generateDefinition();
		return statements;
	}	
	
	/*
	 * Render the definition.
	 */
	private String generateDefinition() {
		
		String code = definition.trim();
				
		// Replace variables.
		Pattern pattern = Pattern.compile("\\$(\\w*)");
		Matcher matcher = pattern.matcher(code);
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while (matcher.find()) {
		    String replacement = replacements.get(matcher.group(1));
		    builder.append(code.substring(i, matcher.start()));
		    if (replacement == null) {
		    	builder.append(matcher.group(0));
		    } else{
		    	builder.append(replacement);
		    }
		    i = matcher.end();
		}
		builder.append(code.substring(i, code.length()));
		code = builder.toString().trim();
		
		// Parse and remove precomp statements.
		statements.clear();
		pattern = Pattern.compile("\\#(\\w*)\\s*(.*)$", Pattern.MULTILINE);
		matcher = pattern.matcher(code);
		builder = new StringBuilder();
		i = 0;
		while (matcher.find()) {
			String name = matcher.group(1);
			String def = matcher.group(2);
			statements.put(name, def);
		    builder.append(code.substring(i, matcher.start()));
		    i = matcher.end();
		}
		builder.append(code.substring(i, code.length()));
		code = builder.toString().trim();

		// Add semicolon if necessary.
		if (!code.endsWith(";")) {
			code = code + ";";
		}
		return code;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void setName(String newName) {
		super.setName(newName);
		replacements.put("proc", newName);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.Process#toString()
	 */
	@Override
	public String toString() {
		return definition!=null ? generateDefinition() : "";
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected String definition = DEFINITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomProcess() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.CUSTOM_PROCESS;
	}

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see #setDefinition(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCustomProcess_Definition()
	 * @model
	 * @generated
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.CustomProcess#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	public void setDefinition(String newDefinition) {
		String oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.CUSTOM_PROCESS__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.CUSTOM_PROCESS__DEFINITION:
				return getDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.CUSTOM_PROCESS__DEFINITION:
				setDefinition((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MCRL2Package.CUSTOM_PROCESS__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MCRL2Package.CUSTOM_PROCESS__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
		}
		return super.eIsSet(featureID);
	}

} // CustomProcess
