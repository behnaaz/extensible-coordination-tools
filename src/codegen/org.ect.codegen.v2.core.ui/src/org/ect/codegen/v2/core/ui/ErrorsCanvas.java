package org.ect.codegen.v2.core.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.ect.codegen.v2.core.gen.Printer;

public class ErrorsCanvas extends Canvas {

	//
	// FIELDS
	//

	/**
	 * The messages added to this canvas.
	 */
	private final List<String> messages = new ArrayList<String>();

	/**
	 * The times on which messages have been added to this canvas.
	 */
	private final List<String> times = new ArrayList<String>();

	/**
	 * The text shown on this canvas.
	 */
	private final StyledText styledText;

	/**
	 * The text of the error dialog shown when a throwable is added to this
	 * canvas.
	 */
	private String dialogText;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a canvas dedicated to show error messages.
	 * 
	 * @param parent
	 *            The parent of the canvas to construct. Not <code>null</code>.
	 * @param style
	 *            The SWT style.
	 * @throws ErrorsCanvasException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>parent==null</code>.
	 */
	public ErrorsCanvas(final Composite parent, final int style)
			throws ErrorsCanvasException {

		super(parent, style);
		if (parent == null)
			throw new NullPointerException();

		try {
			/* Set layout. */
			final GridLayout layout = new GridLayout(1, false);
			layout.marginBottom = 5;
			layout.verticalSpacing = 10;
			layout.horizontalSpacing = 10;
			super.setLayout(layout);

			/* Set styled text. */
			this.styledText = new StyledText(this, SWT.MULTI | SWT.READ_ONLY
					| SWT.WRAP | SWT.V_SCROLL);
			this.styledText.setLayoutData(new GridData(GridData.FILL_BOTH));
		} catch (final Exception e) {
			throw new ErrorsCanvasException(
					"I failed to construct an errors canvas.", e);
		}

	}

	//
	// METHODS
	//

	/**
	 * Adds the throwable <code>throwable</code> to this canvas.
	 * 
	 * @throws ErrorsCanvasException
	 *             If something goes wrong while adding.
	 * @throws NullPointerException
	 *             If <code>throwable==null</code>
	 */
	public synchronized void addThrowable(final Throwable throwable,
			final boolean showDialog) throws ErrorsCanvasException {

		if (throwable == null)
			throw new NullPointerException();

		try {
			/* Get the current time. */
			String time = "Unknown time.";
			try {
				time = Calendar.getInstance().getTime().toString();
			} catch (final Exception e) {
			}

			/* Get the message. */
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Printer.println(throwable, new PrintStream(stream), false);
			final String message = stream.toString();

			/* Add to $messages, and show. */
			times.add(time);
			messages.add(message);
			showMessages();

			/* Show dialog? */
			if (showDialog)
				MessageDialog.openError(this.getShell(), "Error",
						"An error occurred."
								+ (dialogText == null ? "" : " " + dialogText));

			// See the \"Error Messages\" tab item of the \"Code Generator\"
			// view for more information.
		} catch (final Exception e) {
			throw new ErrorsCanvasException("I failed to add the throwable \""
					+ throwable + "\" to this errors canvas.", e);
		}
	}

	/**
	 * Sets the text of the error dialog shown when a throwable is added to this
	 * canvas.
	 * 
	 * <p>
	 * This method synchronizes on this widget.
	 * 
	 * @param dialogText
	 *            The text; <code>null</code> means "show no text".
	 */
	public synchronized void setDialogText(final String dialogText) {
		this.dialogText = dialogText;
	}

	/**
	 * Deprecates this method.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	@Override
	public void setLayout(final Layout layout) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Shows the messages added to this canvas.
	 * 
	 * <p>
	 * This method synchronizes on this widget.
	 * 
	 * @throws ErrorsCanvasException
	 *             If something goes wrong before or while showing.
	 */
	public synchronized void showMessages() throws ErrorsCanvasException {
		try {
			final StringBuilder builder = new StringBuilder();
			for (int i = times.size() - 1; i >= 0; i--) {
				final String time = times.get(i);
				final String message = messages.get(i);
				builder.append("\n");
				builder.append(time);
				builder.append("\n");
				builder.append(message);
			}

			final String text = builder.toString();
			styledText.setText(text);

			for (int i = times.size() - 1; i >= 0; i--) {
				final String time = times.get(i);
				int index = text.indexOf(time);
				while (index != -1) {
					final StyleRange styleRange = new StyleRange();
					styleRange.start = index;
					styleRange.length = time.length();
					styleRange.fontStyle = SWT.BOLD;
					styleRange.underline = true;
					styledText.setStyleRange(styleRange);

					index = text.indexOf(time, index + 1);
				}
			}
		} catch (final Exception e) {
			throw new ErrorsCanvasException(
					"I failed to show the messages added to this errors canvas.",
					e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ErrorsCanvasException extends Exception {
		private static final long serialVersionUID = 1L;

		public ErrorsCanvasException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}
