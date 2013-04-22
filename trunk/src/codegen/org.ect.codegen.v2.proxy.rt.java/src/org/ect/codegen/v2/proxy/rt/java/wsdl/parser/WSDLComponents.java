package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import java.util.Collection;
import java.util.LinkedHashMap;

import javax.xml.namespace.QName;

public class WSDLComponents<T extends AbstractWSDLTopLevelComponent> {

	//
	// FIELDS
	//

	/**
	 * The <code>description</code> component containing the components in this
	 * collection.
	 */
	final WSDLDescription description;

	/**
	 * A map from their qualified names to the components in this collection.
	 */
	final LinkedHashMap<QName, T> qNamesToComponents = new LinkedHashMap<QName, T>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an empty collection of components contained in the
	 * <code>description</code> component <code>description</code>.
	 * 
	 * @param description
	 *            The <code>description</code> component.
	 * @throws NullPointerException
	 *             If <code>description==null</code>.
	 */
	public WSDLComponents(final WSDLDescription description) {
		if (description == null)
			throw new NullPointerException();
		this.description = description;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Checks if this collection contains a component with the qualified name
	 * <code>qName</code>.
	 * 
	 * @return <code>true</code> collection contains a component with the
	 *         qualified name <code>qName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>qName==null</code>.
	 */
	public boolean contains(final QName qName) {
		if (qName == null)
			throw new NullPointerException();

		return qNamesToComponents.containsKey(qName);
	}

	/**
	 * Gets from this collection the component with the (non-colonized) name
	 * <code>name</code>. Shorthand for <code>get(qName)</code> where
	 * <code>qName.equals(getDescription().convertToQName(name))</code>.
	 * 
	 * @param name
	 *            The (non-colonized) name. Not <code>null</code>.
	 * @return A component. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!contains(qName)</code>.
	 * 
	 * @see #contains(QName)
	 * @see #get(QName)
	 * @see #getDescription()
	 * @see WSDLDescription#convertToQName(String)
	 */
	public T get(final String name) {
		if (name == null)
			throw new NullPointerException();

		final QName qName = getDescription().convertToQName(name);
		return get(qName);
	}

	/**
	 * Gets from this collection the component with the qualified name
	 * <code>qName</code>.
	 * 
	 * @param qName
	 *            The qualified name. Not <code>null</code>.
	 * @return A component. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!contains(qName)</code>.
	 * @throws NullPointerException
	 *             If <code>qName==null</code>.
	 * 
	 * @see #contains(QName)
	 */
	public T get(final QName qName) {
		if (qName == null)
			throw new NullPointerException();
		if (!contains(qName))
			throw new IllegalArgumentException();

		return qNamesToComponents.get(qName);
	}

	/**
	 * Gets the components in this collection.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	public Collection<T> getAll() {
		return qNamesToComponents.values();
	}

	/**
	 * Gets the <code>description</code> component containing the components in
	 * this collection.
	 * 
	 * @return A <code>description</code> component. Never <code>null</code>.
	 */
	public WSDLDescription getDescription() {
		return description;
	}

	/**
	 * Gets the first component added to this collection.
	 * 
	 * @return A component. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>isEmpty()</code>.
	 * 
	 * @see #isEmpty()
	 */
	public T getFirst() {
		if (isEmpty())
			throw new IllegalStateException();

		return getAll().iterator().next();
	}

	/**
	 * Checks if this collection is empty.
	 * 
	 * @return <code>true</code> if this collection is empty; <code>false</code>
	 *         otherwise.
	 */
	public boolean isEmpty() {
		return qNamesToComponents.isEmpty();
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Adds the component <code>component</code> to this collection.
	 * 
	 * @param component
	 *            The component. Not <code>null</code>>
	 * @throws NullPointerException
	 *             If <code>component==null</code>.
	 */
	void add(final T component) {
		if (component == null)
			throw new NullPointerException();
		qNamesToComponents.put(component.getQName(), component);
	}
}
