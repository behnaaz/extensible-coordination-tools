package org.ect.codegen.v2.core.gen;

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
	 * The length used by <code>getCroppedResourceText()</code> to crop the
	 * resource text of this resource.
	 * 
	 * @see #getCroppedResourceText()
	 */
	private int cropLength = 80;

	/**
	 * The resource text of this resource (a local location, a remote location,
	 * or "raw" content).
	 */
	private final String resourceText;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a resource for the resource text <code>resourceText</code>.
	 * 
	 * @param resourceText
	 *            The resource text (a local location, a remote location, or raw
	 *            data). Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resourceText==null</code>.
	 */
	public Resource(final String resourceText) {
		if (resourceText == null)
			throw new NullPointerException("resourceText");

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
	 * </p>
	 */
	@Override
	public boolean equals(final Object object) {
		return object instanceof Resource && equals((Resource) object);
	}

	/**
	 * Checks if this resource equals the resource <code>resource</code>. In
	 * that case, it has the same resource text.
	 * 
	 * @param resource
	 *            The resource. Not <code>null</code>.
	 * @return A boolean.
	 * @throws NullPointerException
	 *             If <code>resource==null</code>.
	 */
	public boolean equals(final Resource resource) {
		if (resource == null)
			throw new NullPointerException("resource");

		return resourceText.equals(resource.resourceText);
	}

	/**
	 * <em>Inherited documentation</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int hashCode() {
		return resourceText.hashCode();
	}

	/**
	 * Gets the resource text of this resource, cropped to
	 * <code>getCropLength()</code> characters.
	 * 
	 * @return A string. Never <code>null</code>.
	 * 
	 * @see #getCropLength()
	 */
	public String getCroppedResourceText() {
		final int length = resourceText.length();
		if (length < cropLength)
			return resourceText;

		final int prefixLength = (length / 2) - 2;
		final int postfixLength = length - prefixLength - 4;

		final String prefix = resourceText.substring(0, prefixLength);
		final String infix = "....";
		final String postfix = resourceText.substring(resourceText.length()
				- postfixLength);

		return prefix + infix + postfix;
	}

	/**
	 * Gets the length used by <code>getCroppedResourceText()</code> to crop the
	 * resource text of this resource.
	 * 
	 * @return A nonnegative integer.
	 * 
	 * @see #getCroppedResourceText()
	 */
	public int getCropLength() {
		return cropLength;
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
	 * Gets the resource text of this resource.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getResourceText() {
		return resourceText;
	}

	/**
	 * Checks if this resource has content.
	 * 
	 * @return A boolean.
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
	 * Sets the length used by <code>getCroppedResourceText()</code> to crop the
	 * resource text of this resource.
	 * 
	 * @param length
	 *            The nonnegative length.
	 * @throws IllegalArgumentException
	 *             If <code>length&lt;0</code>
	 * 
	 * @see #getCroppedResourceText()
	 */
	public void setCropLength(final int length) {
		if (length < 0)
			throw new IllegalArgumentException(Integer.toString(length));

		this.cropLength = length;
	}

	/**
	 * <em>Inherited documentation</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return resourceText;
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Gets an input stream for the resource text <code>resourceText</code>.
	 * 
	 * @param resourceText
	 *            The resource text (a local location, a remote location, or raw
	 *            data). Not <code>null</code>.
	 * @return An input stream. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resource==null</code>.
	 */
	public static InputStream getInputStreamFor(final String resourceText) {

		/* Validate arguments. */
		if (resourceText == null)
			throw new NullPointerException("resourceText");

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
