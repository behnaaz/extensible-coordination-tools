package org.ect.codegen.v2.core.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;

import org.ect.codegen.v2.core.NamedObjectFactory;

public class FileLeafFactory extends
		NamedObjectFactory<FileLeafFactory.FileLeaf> {

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean canConstruct(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		return true;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected FileLeaf newNamedObject(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		return new FileLeaf(objectName);
	}

	//
	// CLASSES
	//

	public class FileLeaf extends NamedObjectFactory<FileLeaf>.NamedObject {

		//
		// FIELDS
		//

		/**
		 * The content of this leaf.
		 */
		private Object content = "";

		//
		// CONSTRUCTORS
		//

		/**
		 * Invokes: <code>super(name)</code>.
		 * 
		 * @see NamedObject#NamedObject(String)
		 */
		private FileLeaf(final String name) {
			super(name);
		}

		//
		// METHODS
		//

		/**
		 * Checks if this leaf can write its content.
		 * 
		 * <p>
		 * Returns <code>true</code> if this leaf can write; <code>false</code>
		 * otherwise.
		 * </p>
		 * 
		 * @return A boolean.
		 */
		public boolean canWrite() {
			return content instanceof File || content instanceof String;
		}

		/**
		 * Gets the content of this leaf.
		 * 
		 * @return An object. Never <code>null</code>.
		 */
		public Object getContent() {
			return content;
		}

		/**
		 * Sets the content of this leaf to the object <code>content</code>.
		 * 
		 * @param content
		 *            The content. Not <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>content==null</code>.
		 */
		public void setContent(final Object content) {
			if (content == null)
				throw new NullPointerException("content");

			this.content = content;
		}

		/**
		 * Tries to write the content of this leaf to a file named as this leaf
		 * in the directory at the location <code>parentLocation</code>.
		 * 
		 * @param parentLocation
		 *            The location. Not <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>!canWrite()</code>.
		 * @throws NullPointerException
		 *             If <code>parentLocation==null</code>.
		 * @throws FileLeafException
		 *             If something goes wrong before or while writing.
		 * 
		 * @see String#isEmpty()
		 */
		public void tryWrite(final String parentLocation)
				throws FileLeafException {

			/* Validate arguments. */
			if (parentLocation == null)
				throw new NullPointerException("parentLocation");
			if (!canWrite())
				throw new IllegalStateException("!canWrite()");

			/* Compute location. */
			final String location = parentLocation + File.separator
					+ super.getName();

			/* Write. */
			final Writer writer = new Writer(new File(location));
			if (content instanceof File)
				writer.tryWrite((File) content);
			else if (content instanceof String)
				writer.tryWrite((String) content);
		}

		//
		// CLASSES
		//

		private class Writer {

			//
			// FIELDS
			//

			/**
			 * The file to write to.
			 */
			private final File file;

			//
			// CONSTRUCTORS
			//

			/**
			 * Constructs a writer for the file <code>file</code>.
			 * 
			 * @param file
			 *            The file. Not <code>null</code>.
			 * @throws NullPointerException
			 *             If <code>destination==null</code>.
			 */
			public Writer(final File file) {
				if (file == null)
					throw new NullPointerException("file");

				this.file = file;
			}

			//
			// METHODS
			//

			/**
			 * Tries to write the content of the file <code>contentFile</code>
			 * to the location of the file of this writer, creating or
			 * overwriting it if necessary.
			 * 
			 * @param contentFile
			 *            The file. Not <code>null</code>.
			 * @throws NullPointerException
			 *             If <code>contentFile==null</code>.
			 * @throws WriterException
			 *             If something goes wrong.
			 */
			public final void tryWrite(final File contentFile)
					throws FileLeafException {

				/* Validate arguments. */
				if (contentFile == null)
					throw new NullPointerException("contentFile");

				/* Try. */
				try {

					/* Create a new file. */
					tryWriteEmptyFile();

					/* Open streams. */
					final FileInputStream inStream = new FileInputStream(
							contentFile);
					final FileOutputStream outStream = new FileOutputStream(
							file);

					/* Transfer data. */
					final FileChannel inChannel = inStream.getChannel();
					final FileChannel outChannel = outStream.getChannel();

					inChannel.transferTo(0, inChannel.size(), outChannel);

					/* Close streams. */
					inStream.close();
					outStream.close();
				}

				/* Catch. */
				catch (final Exception e) {
					throw new FileLeafException(
							"I failed to write the content of the file at the location \""
									+ contentFile.getAbsolutePath()
									+ "\" to the location \""
									+ file.getAbsolutePath() + "\".", e);
				}
			}

			/**
			 * Tries to write the content <code>content</code> to the location
			 * of the file of this writer, creating or overwriting it if
			 * necessary.
			 * 
			 * @param content
			 *            The content. Not <code>null</code>.
			 * @throws NullPointerException
			 *             If <code>content==null</code>.
			 * @throws WriterException
			 *             If something goes wrong.
			 */
			public final void tryWrite(final String content)
					throws FileLeafException {

				/* Validate arguments. */
				if (content == null)
					throw new NullPointerException("content");

				/* Try. */
				try {

					/* Create a new file. */
					tryWriteEmptyFile();

					/* Write content. */
					final FileWriter fileWriter = new FileWriter(file);
					final BufferedWriter bufferedWriter = new BufferedWriter(
							fileWriter);

					bufferedWriter.write(content);
					bufferedWriter.close();
				}

				/* Catch. */
				catch (final Exception e) {
					throw new FileLeafException(
							"I failed to write the content \"" + content
									+ "\" to the location \""
									+ file.getAbsolutePath() + "\".", e);
				}
			}

			//
			// METHODS
			//

			/**
			 * Tries to write an empty file at the location of the file of this
			 * writer. If such a file already exists, this method overwrites the
			 * existing file.
			 * 
			 * @throws FileLeafException
			 *             If something goes wrong.
			 */
			public final void tryWriteEmptyFile() throws FileLeafException {

				/* Try. */
				try {

					/* Delete an existing file. */
					if (file.exists())
						if (!file.delete())
							throw new FileLeafException(
									"I failed to delete the existing file at location \""
											+ file.getAbsolutePath() + "\".");

					/* Create a new file. */
					if (!file.createNewFile())
						throw new FileLeafException(
								"I failed to create a new file at the location \""
										+ file.getAbsolutePath() + "\".");

				}

				/* Catch. */
				catch (final Exception e) {
					throw new FileLeafException(
							"I failed to write an empty file at the location \""
									+ file.getAbsolutePath() + "\".", e);
				}
			}
		}

		//
		// EXCEPTIONS
		//

		public class FileLeafException extends Exception {
			private static final long serialVersionUID = 1L;

			protected FileLeafException(final String message) {
				super(message);
			}

			protected FileLeafException(final String message,
					final Throwable cause) {
				super(message, cause);
			}
		}
	}
}