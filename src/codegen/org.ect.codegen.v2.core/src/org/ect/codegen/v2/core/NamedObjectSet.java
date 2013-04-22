package org.ect.codegen.v2.core;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NamedObjectSet<O extends NamedObjectFactory<O>.NamedObject>
		extends AbstractSet<O> implements Set<O> {

	//
	// FIELDS
	//

	/**
	 * A map from their names to the objects in this set.
	 */
	private HashMap<String, O> map = new HashMap<String, O>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an empty set.
	 */
	public NamedObjectSet() {
	}

	/**
	 * Constructs a set initially containing the objects <code>objects</code>.
	 * 
	 * @param objects
	 *            The object. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>objects==null</code> or
	 *             <code>objects.contains(null)</code>.
	 */
	public NamedObjectSet(final Collection<O> objects) {
		if (objects == null || objects.contains(null))
			throw new NullPointerException("objects");

		addAll(objects);
	}

	/**
	 * Constructs a set initially containing the objects <code>objects</code>.
	 * 
	 * @param objects
	 *            The object. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>objects==null</code> or
	 *             <code>objects[i]==null</code> for some <code>i</code>.
	 */
	public NamedObjectSet(final O... objects) {

		/* Validate arguments. */
		if (objects == null)
			throw new NullPointerException("objects");
		for (int i = 0; i < objects.length; i++)
			if (objects[i] == null)
				throw new NullPointerException("objects[" + i + "]");

		/* Add. */
		for (final O o : objects)
			map.put(o.getName(), o);
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public boolean add(final O object) {
		if (object == null)
			throw new NullPointerException("object");

		return map.put(object.getName(), object) == null;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		final NamedObjectSet<O> clone = (NamedObjectSet<O>) super.clone();
		clone.map = (HashMap<String, O>) map.clone();
		return clone;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public boolean contains(final Object object) {
		if (object == null)
			throw new NullPointerException("object");

		return map.containsValue(object);
	}

	/**
	 * Checks if this set contains an object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name of the object. Not <code>null</code>.
	 * @return A boolean.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 */
	public boolean contains(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");

		return map.containsKey(objectName);
	}

	/**
	 * Gets the object named <code>objectName</code> from this set.
	 * 
	 * @param objectName
	 *            The name of the object. Not <code>null</code>.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!contains(objectName)</code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 * 
	 * @see #contains(String)
	 */
	public O get(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (!contains(objectName))
			throw new IllegalStateException("!contains(" + objectName + ")");

		/* Get. */
		return map.get(objectName);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Iterator<O> iterator() {
		return map.values().iterator();
	}

	/**
	 * Removes the object <code>object</code> from this set.
	 * 
	 * <p>
	 * Returns <code>true</code> if this set contained <code>object</code>;
	 * <code>false</code> otherwise.
	 * </p>
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @return A boolean.
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	public boolean remove(final NamedObjectFactory<?>.NamedObject object) {
		if (object == null)
			throw new NullPointerException("object");

		return map.remove(object.getName()) == object;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public boolean remove(final Object object) {
		if (object == null)
			throw new NullPointerException("object");

		return object instanceof NamedObjectFactory<?>.NamedObject
				&& remove((NamedObjectFactory<?>.NamedObject) object);
	}

	/**
	 * Removes the object named <code>objectName</code> from this set.
	 * 
	 * <p>
	 * Returns <code>true</code> if this set contained an object named
	 * <code>objectName</code>; <code>false</code> otherwise.
	 * </p>
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @return A boolean.
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	public boolean remove(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");

		return contains(objectName) ? remove(get(objectName)) : false;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int size() {
		return map.size();
	}
}
