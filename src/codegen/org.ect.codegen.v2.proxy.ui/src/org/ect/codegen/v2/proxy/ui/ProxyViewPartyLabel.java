package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewPartyLabel extends AbstractWidgetWrapper<Label> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this party-label
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a party-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the party-label to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewPartyLabel(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new Label(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("");
	}

	//
	// METHODS
	//

	/**
	 * Resizes this label.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * 
	 * <p>
	 * <center>
	 * <code>super.get().setSize(super.get().computeSize(SWT.DEFAULT,SWT.DEFAULT))</code>
	 * </center>
	 * 
	 * @see Control#computeSize(int, int)
	 * @see Control#setSize(Point)
	 * @see AbstractWidgetWrapper#get()
	 */
	public void resize() {
		super.get().setSize(super.get().computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	/**
	 * Sets the text of this label.
	 * 
	 * @param text
	 *            The text. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>text==null</code>.
	 */
	public void setText(final String text) {
		if (text == null)
			throw new NullPointerException();

		super.get().setText(text);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a party-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A party-label. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewPartyLabel newInstance(
			final ProxyViewWidgets widgets) {
		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewPartyLabel(widgets, widgets.simpleSettingsCanvas);
	}
}