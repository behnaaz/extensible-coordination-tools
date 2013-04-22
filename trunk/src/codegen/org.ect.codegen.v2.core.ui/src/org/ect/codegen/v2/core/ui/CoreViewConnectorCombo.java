package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class CoreViewConnectorCombo extends AbstractWidgetWrapper<Combo> implements
		IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this connector-combo
	 * belongs.
	 */
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector-combo for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the connector-combo to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewConnectorCombo(final CoreViewWidgets widgets,
			final CoreViewSimpleSettingsCanvas parent) {

		super(new Combo(parent.get(), SWT.DROP_DOWN | SWT.READ_ONLY));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		/* Initialize items. */
		for (final Item i : Item.values())
			super.get().add(i.getDescription());

		super.get().select(0);
	}

	//
	// METHODS
	//

	/**
	 * Gets the item currently selected.
	 * 
	 * @return An item. Never <code>null</code>
	 */
	public Item getSelectedItem() {
		final String text = CoreViewConnectorCombo.this.get().getText();
		return Item.newItemFromDescription(text);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void initializeListeners() throws ListenerWidgetException {
		super.get().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent event) {
				switch (getSelectedItem()) {
				case EDITOR:
					widgets.connectorFileCanvas.get().setEnabled(false);
					break;
				case FILE_SYSTEM:
					widgets.connectorFileCanvas.get().setEnabled(true);
					break;
				}
			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a connector-combo for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A connector-combo. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static CoreViewConnectorCombo newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new CoreViewConnectorCombo(widgets,
				widgets.simpleSettingsCanvas);
	}

	//
	// STATIC - ENUMS
	//

	public static enum Item {
		EDITOR, FILE_SYSTEM;

		/**
		 * Gets a description of this item.
		 * 
		 * @return A string. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If this item has no description.
		 */
		public String getDescription() {
			switch (this) {
			case EDITOR:
				return "From editor";
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
