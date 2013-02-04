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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.constraints.Composite#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Composite extends Constraint {
	
	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.ea.extensions.constraints.Constraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Constraint> getMembers();

} // Composite
