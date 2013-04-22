package org.ect.codegen.v2.proxy.rt.java;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Resource {

	//
	// FIELDS
	//

	/**
	 * The size used to crop the textual representation of this resource in the
	 * method <code>getCroppedResourceText()</code>.
	 * 
	 * @see #getCroppedResourceText()
	 */
	private int cropSize = 80;

	/**
	 * The textual representation of this resource: a local location, a remote
	 * location, or "raw" content.
	 */
	private final String resourceText;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a resource for its textual representation
	 * <code>resourceText</code>: a local location, a remote location, or raw
	 * data.
	 * 
	 * @param resourceText
	 *            The textual respresentation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resourceText==null</code>.
	 */
	public Resource(final String resourceText) {
		if (resourceText == null)
			throw new NullPointerException();

		this.resourceText = resourceText;
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		return (object instanceof Resource)
				&& (((Resource) object).resourceText.equals(resourceText));
	}
	
	/**
	 * <em>Inherited documentation</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return resourceText.hashCode();
	}

	/**
	 * Gets the textual representation of this resource, cropped to
	 * <code>getCropSize()</code>.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getCroppedResourceText() {
		final int size = resourceText.length();
		if (size < cropSize)
			return resourceText;

		final int prefixSize = (size / 2) - 2;
		final int postfixSize = size - prefixSize - 4;

		final String prefix = resourceText.substring(0, prefixSize);
		final String infix = "....";
		final String postfix = resourceText.substring(resourceText.length()
				- postfixSize);

		return prefix + infix + postfix;
	}

	/**
	 * Gets the size used to crop the textual representation of this resource in
	 * the method <code>getCroppedResourceText()</code>.
	 * 
	 * @param size
	 *            The nonnegative size. Default: 80.
	 * @return A nonnegative integer.
	 * 
	 * @see #getCroppedResourceText()
	 */
	public int getCropSize() {
		return cropSize;
	}

	/**
	 * Constructs a new input stream for this resource.
	 * 
	 * @return An input stream. Never <code>null</code>.
	 */
	public InputStream newInputStream() {
		return getInputStreamFor(resourceText);
	}

	/**
	 * Gets the textual representation of this resource.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getResourceText() {
		return resourceText;
	}

	/**
	 * Checks if this resource has content.
	 * 
	 * @return <code>true</code> if this resource has content;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasContent() {
		final InputStream stream = newInputStream();
		final InputStreamReader reader = new InputStreamReader(stream);
		try {
			return !(stream instanceof ByteArrayInputStream)
					&& reader.read() != -1;
		} catch (final IOException e) {
			return false;
		}
	}

	/**
	 * Sets the size used to crop the textual representation of this resource in
	 * the method <code>getCroppedResourceText()</code>.
	 * 
	 * @param size
	 *            The nonnegative size.
	 * @throws IllegalArgumentException
	 *             If <code>size&lt;0</code>
	 * 
	 * @see #getCroppedResourceText()
	 */
	public void setCropSize(final int size) {
		if (size < 0)
			throw new IllegalArgumentException();

		this.cropSize = size;
	}
	
	/**
	 * <em>Inherited documentation</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return resourceText;
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Gets an input stream for the textual representation
	 * <code>resourceText</code>: a local location, a remote location, or raw
	 * data.
	 * 
	 * @param resourceText
	 *            The textual representation. Not <code>null</code>.
	 * @return An input stream. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resource==null</code>.
	 */
	public static InputStream getInputStreamFor(final String resourceText) {
		if (resourceText == null)
			throw new NullPointerException();

		/* Check if $resourceText references a local location. */
		try {
			return new FileInputStream(resourceText);
		} catch (final Exception e) {
		}

		/* Check if $resourceText references a remote location. */
		try {
			return new URL(resourceText).openStream();
		}

		/* Assume that $resourceText contains raw data. */
		catch (final Exception e) {
			return new ByteArrayInputStream(resourceText.getBytes());
		}
	}
}
