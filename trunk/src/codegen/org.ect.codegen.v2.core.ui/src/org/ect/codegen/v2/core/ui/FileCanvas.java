package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FileCanvas extends Canvas {

	//
	// FIELDS
	//

	/**
	 * The browse-button of this canvas.
	 */
	private final Button browseButton;

	/**
	 * The browse-dialog of this canvas.
	 */
	private Dialog browseDialog;

	/**
	 * Flag indicating whether this dialog is enabled.
	 */
	private boolean isEnabled = true;

	/**
	 * The text of this canvas.
	 */
	private final Text text;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a canvas for selecting a file or directory.
	 * 
	 * @param parent
	 *            The parent of the canvas to construct. Not <code>null</code>.
	 * @param style
	 *            The SWT style.
	 * @param type
	 *            The type of the canvas to construct. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>parent==null</code> or <code>dialog==null</code>.
	 */
	public FileCanvas(final Composite parent, final int style, final Type type) {
		super(parent, style);
		if (type == null)
			throw new NullPointerException();

		/* Initialize $dialog. */
		this.browseDialog = type.newDialog();
		this.browseDialog.setText("Browse");

		/* Initialize the layout. */
		final GridLayout layout = new GridLayout(2, false);
		layout.marginBottom = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;
		layout.horizontalSpacing = 10;
		layout.marginWidth = 0;
		this.setLayout(layout);
		this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

		/* Initialize $text. */
		this.text = new Text(this, SWT.BORDER);
		this.text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

		/* Initialize $button. */
		this.browseButton = new Button(this, SWT.NULL);
		browseButton.setText("Browse");
		browseButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				if (event.widget == browseButton) {

					/* Update $dialog if possible. */
					try {

						/* Get extensions if available. */
						String[] extensions = null;
						if (FileCanvas.this.browseDialog instanceof FileDialog)
							extensions = ((FileDialog) FileCanvas.this.browseDialog)
									.getFilterExtensions();

						/* Update $dialog. */
						final Shell shell = FileCanvas.this.browseDialog
								.getParent();
						FileCanvas.this.browseDialog = FileCanvas.this.browseDialog
								.getClass().getConstructor(shell.getClass())
								.newInstance(shell);

						/* Set extensions if available. */
						if (FileCanvas.this.browseDialog instanceof FileDialog)
							((FileDialog) FileCanvas.this.browseDialog)
									.setFilterExtensions(extensions);

					} catch (final Exception e) {
						/* Ignore $e. */
					}

					/* Open the dialog. */
					String result = null;
					if (FileCanvas.this.browseDialog instanceof FileDialog)
						result = ((FileDialog) FileCanvas.this.browseDialog)
								.open();
					else if (FileCanvas.this.browseDialog instanceof DirectoryDialog)
						result = ((DirectoryDialog) FileCanvas.this.browseDialog)
								.open();

					/* Process the results. */
					if (result == null)
						return;

					text.setText(result);
					text.notifyListeners(SWT.CHANGED, new Event());
				}
			}
		});
	}

	//
	// METHODS
	//

	/**
	 * Adds a modify listener to this canvas.
	 * 
	 * @param runnable
	 *            The implementation of the event handler. Not <code>null</code>
	 *            .
	 * @throws NullPointerException
	 *             If <code>runnable==null</code>.
	 */
	public void addModifyListener(final Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();

		text.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				runnable.run();
			}
		});
	}

	/**
	 * Gets the text of this canvas.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getText() {
		return text.getText();
	}

	/**
	 * Checks if this canvas is enabled.
	 * 
	 * @return <code>true</code> if this canvas is enabled; <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Notifies the listeners of this canvas about the event <code>event</code>.
	 * 
	 * @param eventType
	 *            The type of the event.
	 * @param event
	 *            The event. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>event==null</code>.
	 */
	@Override
	public void notifyListeners(final int eventType, final Event event) {
		if (event == null)
			throw new NullPointerException();

		text.notifyListeners(eventType, event);
	}

	/**
	 * Sets the flag indicating whether this canvas is enabled to the value
	 * <code>isEnabled</code>.
	 * 
	 * @param isEnabled
	 *            The value.
	 */
	@Override
	public void setEnabled(boolean isEnabled) {
		super.setEnabled(isEnabled);
		text.setEnabled(isEnabled);
		browseButton.setEnabled(isEnabled);
		this.isEnabled = isEnabled;
	}

	/**
	 * Sets the filter extensions of this canvas if possible.
	 * 
	 * @param extensions
	 *            The filter extensions. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>extensions==null</code> or
	 *             <code>extensions[i]==null</code> for some <code>i</code>.
	 */
	public void setFilterExtensions(final String[] extensions) {
		if (extensions == null)
			throw new NullPointerException();
		for (final String s : extensions)
			if (s == null)
				throw new NullPointerException();

		if (browseDialog instanceof FileDialog)
			((FileDialog) browseDialog).setFilterExtensions(extensions);
	}

	/**
	 * Sets the text of this canvas.
	 * 
	 * @param string
	 *            The text. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public void setText(final String string) {
		if (string == null)
			throw new NullPointerException();

		text.setText(string);
	}

	//
	// ENUMS
	//

	public static enum Type {
		FILE, DIRECTORY;

		/**
		 * Constructs a dialog of this type in a new shell.
		 * 
		 * @return A dialog. Never <code>null</code>.
		 */
		private Dialog newDialog() {
			switch (this) {
			case FILE:
				return new FileDialog(new Shell());
			case DIRECTORY:
				return new DirectoryDialog(new Shell());
			default:
				throw new IllegalStateException();
			}
		}
	}
}