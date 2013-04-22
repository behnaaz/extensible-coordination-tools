package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.IListenerWidget;

public class ProxyViewSimAutCombo extends AbstractWidgetWrapper<Combo>
		implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this sim-aut-combo
	 * belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a sim-aut-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the sim-aut-combo to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewSimAutCombo(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new Combo(parent.get(), SWT.DROP_DOWN | SWT.READ_ONLY));
		if (widgets == null)
			new NullPointerException();

		this.widgets = widgets;

		/* Initialize items. */
		for (final Item i : Item.values())
			super.get().add(i.getDescription());

		super.get().select(0);
		disable();
	}

	//
	// METHODS
	//

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
	 * Gets the item currently selected.
	 * 
	 * @return An item. Never <code>null</code>
	 */
	public Item getSelectedItem() {
		final String text = ProxyViewSimAutCombo.this.get().getText();
		return Item.newItemFromDescription(text);
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
			public void widgetSelected(SelectionEvent e) {
				switch (getSelectedItem()) {
				case DEFAULT:
					widgets.simAutFileCanvas.disable();
					break;
				case FILE_SYSTEM:
					widgets.simAutFileCanvas.enable();
					break;
				}
			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a sim-aut-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A sim-aut-combo. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewSimAutCombo newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewSimAutCombo(widgets,
				widgets.simpleSettingsCanvas);
	}

	//
	// STATIC - ENUMS
	//

	public static enum Item {
		DEFAULT, FILE_SYSTEM;

		/**
		 * Gets a description of this item.
		 * 
		 * @return A string. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If this item has no description.
		 */
		public String getDescription() {
			switch (this) {
			case DEFAULT:
				return "Default";
			case FILE_SYSTEM:
				return "From file system";
			default:
				throw new IllegalStateException();
			}
		}

		/**
		 * Constructs a new item from the description <code>description</code>.
		 * 
		 * @param description
		 *            The description. Not <code>null</code>.
		 * @return An item. Never <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>description==null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>description</code> describes no item.
		 */
		public static Item newItemFromDescription(final String description) {
			if (description == null)
				throw new NullPointerException();

			for (final Item i : Item.values())
				try {
					if (i.getDescription().equals(description))
						return i;
				} catch (final Exception e) {
					continue;
				}

			throw new IllegalArgumentException();
		}
	}
}
