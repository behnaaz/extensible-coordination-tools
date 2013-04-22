package org.ect.codegen.v2.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class NamedObjectFactory<O extends NamedObjectFactory<O>.NamedObject> {

	//
	// FIELDS
	//

	/**
	 * A map from their names to the objects constructed by this factory (and
	 * not yet destructed).
	 */
	public final Map<String, O> namesToObjects = new HashMap<String, O>();

	/**
	 * The serial number of the next object to construct.
	 */
	private long nextSerialNumber = 1;

	/**
	 * A map from names of the objects constructed by this factory (and not yet
	 * destructed) to their serial numbers.
	 */
	private final Map<String, Long> namesToSerialNumbers = new HashMap<String, Long>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Checks if this factory can construct an object named
	 * <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code> or <code>empty</code>.
	 * @return <code>true</code> if this factory can construct an object named
	 *         <code>string</code>; <code>false</code> otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>objectName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public abstract boolean canConstruct(final String objectName);

	/**
	 * Constructs an object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code> or empty.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>objectName.isEmpty()</code> or
	 *             <code>!canConstruct(objectName)</code>.
	 * @throws IllegalStateException
	 *             If <code>hasConstructed(objectName)</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see #canConstruct(String)
	 * @see #hasConstructed()
	 * @see String#isEmpty()
	 */
	public final O construct(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty() || !canConstruct(objectName))
			throw new IllegalArgumentException(objectName);
		if (hasConstructed(objectName))
			throw new IllegalStateException("hasConstructed(" + objectName
					+ ")");

		/* Construct. */
		final O object = newNamedObject(objectName);
		namesToObjects.put(objectName, object);
		namesToSerialNumbers.put(objectName, nextSerialNumber++);
		return object;
	}

	/**
	 * Constructs an object named <code>objectName</code>, or gets an existing
	 * such object if this factory constructed one before (and has not yet
	 * destructed it).
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code> or empty.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>objectName.isEmpty()</code> or
	 *             <code>!canConstruct(objectName)</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see #canConstruct(String)
	 * @see String#isEmpty()
	 */
	public final O constructOrGet(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty() || !canConstruct(objectName))
			throw new IllegalArgumentException(objectName);

		/* Construct or get. */
		return hasConstructed(objectName) ? get(objectName)
				: construct(objectName);
	}

	/**
	 * Counts the objects constructed by this factory (and not yet destructed).
	 * 
	 * @return A nonnegative integer.
	 */
	public final int count() {
		return namesToObjects.size();
	}

	/**
	 * Destructs the object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasConstructed(objectName)<code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null<code>.
	 * 
	 * @see #hasConstructed(String)
	 */
	public final void destruct(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (!hasConstructed(objectName))
			throw new IllegalStateException("!hasConstructed(" + objectName
					+ ")");

		/* Destruct. */
		namesToObjects.remove(objectName);
		namesToSerialNumbers.remove(objectName);
	}

	/**
	 * Gets the object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code>.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasConstructed(objectName)</code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 * 
	 * @see #hasConstructed(String)
	 * @see String#isEmpty()
	 */
	public final O get(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException(objectName);
		if (!hasConstructed(objectName))
			throw new IllegalStateException("!hasConstructed(" + objectName
					+ ")");

		/* Get. */
		return namesToObjects.get(objectName);
	}

	/**
	 * Gets the objects constructed by this factory (and not yet destructed).
	 * 
	 * @param sort
	 *            Flag indicating if this method should return a sorted
	 *            collection.
	 * @return A list of objects if <code>sort</code>; a set of objects if
	 *         <code>!sort</code>. Never <code>null</code>.
	 */
	public final Collection<O> getAll(final boolean sort) {
		if (sort) {
			final List<O> list = new ArrayList<O>(namesToObjects.values());
			Collections.sort(list);
			return list;
		} else
			return new HashSet<O>(namesToObjects.values());
	}

	/**
	 * Gets the serial number of the object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code>.
	 * @return An integer.
	 * @throws IllegalStateException
	 *             If <code>!hasConstructed(objectName)</code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 * 
	 * @see #hasConstructed(NamedObject)
	 */
	public final long getSerialNumberOf(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (!hasConstructed(objectName))
			throw new IllegalStateException("!hasConstructed(" + objectName
					+ ")");

		/* Get. */
		return namesToSerialNumbers.get(objectName);
	}

	/**
	 * Checks if this factory has constructed an object named
	 * <code>objectName</code> (and has not yet destructed it).
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code>.
	 * @return A boolean.
	 * @throws IllegalArgumentException
	 *             If <code>objectName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public final boolean hasConstructed(final String objectName) {
		if (objectName == null)
			throw new NullPointerException();

		return namesToObjects.containsKey(objectName);
	}

	/**
	 * Renames the object named <code>oldObjectName</code> to
	 * <code>newObjectName</code>.
	 * 
	 * <p>
	 * Neither the serial number of the object named <code>
	 * oldObjectName</code> nor its hash code change.
	 * </p>
	 * 
	 * @param oldObjectName
	 *            The old name. Not <code>null</code> or empty.
	 * @param newObjectName
	 *            The new name. Not <code>null</code> or empty.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>oldObjectName.isEmpty()</code> or
	 *             <code>newObjectName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasConstructed(oldObjectName)</code> or <code>
	 *             hasConstructed(newObjectName)</code>.
	 * @throws NullPointerException
	 *             If <code>oldObjectName==null</code> or
	 *             <code>newObjectName==null</code>.
	 * 
	 * @see #hasConstructed(String)
	 * @see String#isEmpty()
	 */
	public final void rename(final String oldObjectName,
			final String newObjectName) {

		/* Validate arguments. */
		if (oldObjectName == null)
			throw new NullPointerException("oldObjectName");
		if (newObjectName == null)
			throw new NullPointerException("newObjectName");
		if (oldObjectName.isEmpty())
			throw new IllegalArgumentException(oldObjectName);
		if (newObjectName.isEmpty())
			throw new IllegalArgumentException(newObjectName);
		if (!hasConstructed(oldObjectName))
			throw new IllegalStateException("!hasConstructed(" + oldObjectName
					+ ")");
		if (hasConstructed(newObjectName))
			throw new IllegalStateException("hasConstructed(" + newObjectName
					+ ")");

		/* Remove the object to rename and its serial number. */
		final O object = namesToObjects.remove(oldObjectName);
		final long serialNumber = namesToSerialNumbers.remove(oldObjectName);

		/* Rename $object. */
		object.name = newObjectName;

		/* Add $object and $serialNumber. */
		namesToObjects.put(newObjectName, object);
		namesToSerialNumbers.put(newObjectName, serialNumber);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return namesToObjects.values().toString();
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Constructs an object named <code>objectName</code>.
	 * 
	 * @param objectName
	 *            The name. Not <code>null</code> or empty.
	 * @throws IllegalArgumentException
	 *             If <code>objectName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>objectName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	protected abstract O newNamedObject(final String objectName);

	//
	// CLASSES
	//

	public class NamedObject implements Comparable<NamedObject> {

		//
		// FIELDS - DEFAULT
		//

		/**
		 * The name of this object.
		 */
		private volatile String name;

		//
		// FIELDS - PRIVATE
		//

		/**
		 * The hash code of this object.
		 */
		private final int hashCode;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs an object named <code>name</code>.
		 * 
		 * @param name
		 *            The name of the object to construct. Not <code>null</code>
		 *            or empty.
		 * @throws IllegalArgumentException
		 *             If <code>name.isEmpty()</code>.
		 * @throws NullPointerException
		 *             If <code>name==null</code>.
		 * 
		 * @see String#isEmpty()
		 * 
		 */
		public NamedObject(final String name) {
			if (name == null)
				throw new NullPointerException("name");
			if (name.isEmpty())
				throw new IllegalArgumentException(name);

			this.name = name;
			this.hashCode = name.hashCode();
		}

		//
		// METHODS
		//

		/**
		 * Checks if this object can rename itself to <code>newName</code>.
		 * 
		 * @param newName
		 *            The new name. Not <code>null</code> or empty.
		 * @return A boolean.
		 */
		public final boolean canRename(final String newName) {

			/* Validate arguments. */
			if (newName == null)
				throw new NullPointerException("newName");
			if (newName.isEmpty())
				throw new IllegalArgumentException(newName);

			/* Check. */
			return !NamedObjectFactory.this.hasConstructed(newName);
		}

		/**
		 * Compares two objects based on their names.
		 * 
		 * <p>
		 * <em>Inherited documentation:</em>
		 * </p>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 * 
		 * @throws NullPointerException
		 *             If <code>object==null</code>.
		 */
		@Override
		public final int compareTo(final NamedObject object) {
			if (object == null)
				throw new NullPointerException("object");

			return name.compareTo(object.name);
		}

		/**
		 * Checks if this object equals the object <code>namedObject</code>. In
		 * that case, they have the same name.
		 * 
		 * @return A boolean.
		 */
		public final boolean equals(
				final NamedObjectFactory<?>.NamedObject object) {

			return name.equals(object.name);
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public final boolean equals(final Object object) {
			return object instanceof NamedObjectFactory.NamedObject
					&& equals((NamedObjectFactory<?>.NamedObject) object);
		}

		/**
		 * Gets the name of this object.
		 * 
		 * @return A nonempty string. Never <code>null</code>.
		 */
		public final String getName() {
			return name;
		}

		/**
		 * Gets the serial number of this object.
		 * 
		 * @return A positive long.
		 */
		public long getSerialNumber() {
			return NamedObjectFactory.this.getSerialNumberOf(name);
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public final int hashCode() {
			return hashCode;
		}

		/**
		 * Renames this object to <code>newName</code>.
		 * 
		 * @param newName
		 *            The new name. Not <code>null</code> or empty.
		 * @throws IllegalArgumentException
		 *             If <code>newName.isEmpty()</code>.
		 * @throws IllegalStateException
		 *             If <code>!canRename(newName)</code>.
		 * @throws NullPointerException
		 *             If <code>newName==null</code>.
		 * 
		 * @see #canRename(String)
		 * @see String#isEmpty()
		 */
		public final void rename(final String newName) {

			/* Validate arguments. */
			if (newName == null)
				throw new NullPointerException("newName");
			if (newName.isEmpty())
				throw new IllegalArgumentException(newName);
			if (!canRename(newName))
				throw new IllegalStateException("!canRename(" + newName + ")");

			/* Rename. */
			NamedObjectFactory.this.rename(name, newName);
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public String toString() {
			return getName();
		}
	}
}
