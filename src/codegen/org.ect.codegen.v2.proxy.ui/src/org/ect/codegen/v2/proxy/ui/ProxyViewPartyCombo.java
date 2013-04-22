package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.IListenerWidget;

public class ProxyViewPartyCombo extends AbstractWidgetWrapper<Combo>
		implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this party-combo
	 * belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a party-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the party-combo to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewPartyCombo(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new Combo(parent.get(), SWT.DROP_DOWN | SWT.READ_ONLY));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setLayoutData(
				new GridData(SWT.FILL, SWT.DEFAULT, true, true));
		disable();
	}

	//
	// METHODS
	//

	/**
	 * Adds the items <code>items</code> to this combo.
	 * 
	 * @param items
	 *            The items. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>items==null</code> or <code>items[i]==null</code>
	 *             for some <code>i</code>.
	 */
	public void addAll(final Object[] items) {
		if (items == null)
			throw new NullPointerException();
		for (final Object o : items)
			if (o == null)
				throw new NullPointerException();

		for (final Object o : items)
			super.get().add(o.toString());
	}

	/**
	 * Clears this combo.
	 */
	public void clear() {
		super.get().removeAll();
	}

	/**
	 * Disables this combo.
	 */
	public void disable() {
		super.get().setEnabled(false);
	}

	/**
	 * Enables this combo.
	 */
	public void enable() {
		super.get().setEnabled(true);
		super.get().select(0);
	}

	/**
	 * Gets the text of this combo.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getText() {
		return super.get().getText();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void initializeListeners() {
		super.get().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ProxyViewPartyCombo.this.widgets.simAutCombo.enable();
			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a party-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A party-combo. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewPartyCombo newInstance(
			final ProxyViewWidgets widgets) {
		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewPartyCombo(widgets, widgets.simpleSettingsCanvas);
	}
}