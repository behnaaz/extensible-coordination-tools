/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.util;

import java.util.Collection;
import java.util.List;

/**
 * This class provides methods for general refactoring of extensions.
 * @author Christian Koehler
 * @generated NOT
 */
public class ExtensionRefactoring {
	
	public interface IRefactoringListener {
		public void renamed(String oldId, String newId);
	}
	
	/**
	 * Generates a unique identifier.
	 */
	public static String uniqueIdentifier(String name, Collection<String> notAllowed) {
		if (notAllowed==null) return name;
		int index = 0;
		while (notAllowed.contains(name)) {
			name = name + (index++);
		}
		return name;
	}
	
	/**
	 * Append a String list to another one. If entries exist in both
	 * lists they are renamed so that every entry is unique. Only the
	 * first list is modified. 
	 */
	public static void disjointAppend(List<String> l1, List<String> l2, IRefactoringListener listener) {
		for (String value : l2) {
			String newValue = uniqueIdentifier(value, l1);
			l1.add(newValue);
			if (!value.equals(newValue) && listener!=null) {
				listener.renamed(value, newValue);
			}
		}	
	}
	
}
