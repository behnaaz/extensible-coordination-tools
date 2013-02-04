/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.extensions;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.util.ExtensionRefactoring;
import org.ect.ea.util.ExtensionRefactoring.IRefactoringListener;



/**
 * @see org.ect.ea.extensions.ExtensionsPackage#getStringListExtension()
 * @model kind="class"
 * @generated
 */
public class StringListExtension extends ExtensionElement {

	/**
	 * @generated NOT
	 */
	public static final char SEPARATOR = ',';
	
	/**
	 * @generated NOT
	 */
	public StringListExtension() {
		super();
	}

	/**
	 * @generated NOT
	 */
	public StringListExtension(Collection<String> initial) {
		super();
		getValues().addAll(initial);
	}
	
	
	/**
	 * Parse a string list. Overrides the old data.
	 * @param parseString Parse string.
	 * @generated NOT
	 */
	public static StringListExtension parse(String parseString) throws ParseException {

		StringListExtension result = new StringListExtension();
		if (parseString==null || parseString.length()==0) return result;
		
		result.getValues().add(new String());
		for (int i=0; i<parseString.length(); i++) {
			char current = parseString.charAt(i);
			if (current==SEPARATOR) result.getValues().add(new String());
			else {
				int last = result.getValues().size()-1;
				String oldString = result.getValues().get(last);
				String newString = oldString.concat(current+"");
				result.getValues().set(last, newString);
			}
		}
		return result;
	}

	
	/**
	 * Convert the list back to a string representation.
	 * @generated NOT
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<getValues().size(); i++) {
			result.append(getValues().get(i));
			if (i<getValues().size()-1) result.append(SEPARATOR);
		}
		return result.toString();
	}
	
	
	/**
	 * Get a copy of this string list extension.
	 * @generated NOT
	 */
	public StringListExtension getCopy() {
		return (StringListExtension) EcoreUtil.copy(this);
	}
	
	
	/**
	 * Append a StringListExtension to this one.
	 * @param list StringListExtension to be appended.
	 * @param onlyNewEntries Flag indicating whether only the new entries should be added.
	 */
	public void append(StringListExtension list, boolean onlyNewEntries) {
		for (String value : list.getValues()) {
			if (onlyNewEntries) {
				if (!getValues().contains(value)) getValues().add(value);				
			} else {
				getValues().add(value);
			}
		}		
	}
	
	/**
	 * Append a StringListExtension to this one. If entries exist in both
	 * lists they are renamed so that every entry is unique. 
	 * @param list StringListExtension to be appended.
	 * @see ExtensionRefactoring#disjointAppend(java.util.List, java.util.List)
	 */
	public void disjointAppend(StringListExtension list, IRefactoringListener listener) {
		ExtensionRefactoring.disjointAppend(getValues(), list.getValues(), listener);
	}

		
	/**
	 * Trim all elements in the list.
	 * @see String#trim()
	 * @generated NOT
	 */
	public void trimAll() {
		for (int i=0; i<getValues().size(); i++) {
			String value = getValues().get(i);
			getValues().set(i, value.trim());
		}
	}
	
	
	/**
	 * Returns a list of all duplicate entries in this extension.
	 * @generated NOT
	 */
	public EList<String> getDuplicateEntries() {
		HashSet<String> entries = new HashSet<String>();
		HashSet<String> duplicates = new HashSet<String>();
		for (String value : getValues()) {
			if (entries.contains(value)) duplicates.add(value);
			else entries.add(value);
		}
		return new BasicEList<String>(duplicates);
	}

	
	/**
	 * Removes all duplicate entries in this string list.
	 * The first occurence of a duplicate entry will not be deleted.
	 * @generated NOT
	 */
	public void removeDuplicateEntries() {
		HashSet<String> entries = new HashSet<String>();
		for (int i=0; i<getValues().size(); i++) {
			String entry = getValues().get(i);
			if (entries.contains(entry)) getValues().remove(i--);
			else entries.add(entry);
		}
	}

	
		
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<String> values;

	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.STRING_LIST_EXTENSION;
	}

	/**
	 * @return the value of the '<em>Values</em>' attribute list.
	 * @see org.ect.ea.extensions.ExtensionsPackage#getStringListExtension_Values()
	 * @model
	 * @generated
	 */
	public EList<String> getValues() {
		if (values == null) {
			values = new EDataTypeEList<String>(String.class, this, ExtensionsPackage.STRING_LIST_EXTENSION__VALUES);
		}
		return values;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.STRING_LIST_EXTENSION__VALUES:
				return getValues();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExtensionsPackage.STRING_LIST_EXTENSION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExtensionsPackage.STRING_LIST_EXTENSION__VALUES:
				getValues().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExtensionsPackage.STRING_LIST_EXTENSION__VALUES:
				return values != null && !values.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}
