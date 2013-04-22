package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.IListenerWidget;

public class ProxyViewTechnologyCombo extends AbstractWidgetWrapper<Combo>
		implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this technology-combo
	 * belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a technology-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the technology-combo to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewTechnologyCombo(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

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
		final String text = ProxyViewTechnologyCombo.this.get().getText();
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
			@Override
			public void widgetSelected(final SelectionEvent event) {
				switch (getSelectedItem()) {
				case CORBA:
					widgets.interfaceLabel.setText("IDL file:");
					widgets.interfaceFileCanvas.get().setFilterExtensions(
							new String[] { "*.idl", "*.*" });

					widgets.partyLabel.setText("Interface name:");
					break;
				case WSDL:
					widgets.interfaceLabel.setText("WSDL file:");
					widgets.interfaceFileCanvas.get().setFilterExtensions(
							new String[] { "*.wsdl", "*.*" });

					widgets.partyLabel.setText("Service name:");
					break;
				}

				/* Update other widgets. */
				widgets.interfaceLabel.resize();
				widgets.interfaceFileCanvas.clear();
				widgets.interfaceFileCanvas.enable();

				widgets.partyLabel.resize();
				widgets.partyCombo.clear();
				widgets.partyCombo.disable();

				widgets.simAutCombo.disable();
				widgets.simAutFileCanvas.disable();
			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a technology-combo for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A technology-combo. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewTechnologyCombo newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewTechnologyCombo(widgets,
				widgets.simpleSettingsCanvas);
	}

	//
	// STATIC - ENUMS
	//

	public static enum Item {
		CORBA, WSDL;

		/**
		 * Gets a description of this item.
		 * 
		 * @return A string. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If this item has no description.
		 */
		public String getDescription() {
			switch (this) {
			case CORBA:
				return "CORBA";
			case WSDL:
				return "WSDL";
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
