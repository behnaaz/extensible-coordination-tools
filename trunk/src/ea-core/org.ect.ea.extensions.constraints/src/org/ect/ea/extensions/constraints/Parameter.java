/**
 *  * Constraint Extension, Copyright (C) 2007 SEN3 group at CWI, Amsterdam.
 *  * 
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version
 *  * 2 of the License, or (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *  * 02110-1301, USA.
 * 
 *
 * $Id$
 */
package org.ect.ea.extensions.constraints;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.ect.ea.util.IValidationResult;



/**
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getParameter()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Parameter extends EObject {
	
	/**
	 * Get the value (name) of this parameter.
	 * @model kind="operation"
	 * @generated
	 */
	String getValue();

	/**
	 * Set the value (name) of this parameter.
	 * @model
	 * @generated
	 */
	void setValue(String value);


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Equation getEquation();

	/**
	 * Validate this parameter.
	 * @generated NOT
	 */
	public IValidationResult validate();
	
	
	/**
	 * Get all elements used in this parameter.
	 * @generated NOT
	 */
	public EList<Element> getAllElements();
	
} // Parameter
