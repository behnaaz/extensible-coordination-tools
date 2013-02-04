/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.extensions;

import org.eclipse.emf.ecore.EObject;
import org.ect.ea.IExtensionProvider;


/**
 * This is an interface for automata extensions. Classes that 
 * implement this interface can be add to classes that 
 * implement {@link IExtendible}.
 *
 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface IExtension extends EObject {

	/**
	 * Getter method for the id of this extension.
	 * The id uniquely determines an {@link IExtensionProvider}.
	 * Not also that each {@link IExtendible} may have at most
	 * one {@link IExtension} of a specific type.
	 * 
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.IExtension#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Getter for the owner of this extension. This should
	 * never return null. So an extension should always
	 * belong to an {@link IExtendible}. However, during
	 * computations, there might be situations where a
	 * new extension is created and not immediately added
	 * to an {@link IExtendible}.
	 * 
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension_Owner()
	 * @see org.ect.ea.extensions.IExtendible#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	IExtendible getOwner();

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.IExtension#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(IExtendible value);

}

