package org.ect.ea.diagram.contributions.edit;

import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.ect.ea.diagram.contributions.ExtensionLabelHelper;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;



public class AutomatonExtensionProxyEditPart extends AutomatonExtensionEditPart {

	private ExtensionLabelHelper helper;
	private Label tooltip;

	/**
	 * Default constructor.
	 * @param view Automaton extension label node.
	 */
	public AutomatonExtensionProxyEditPart(View view) {
		super(view);
		helper = new ExtensionLabelHelper(this);
	}
	

	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		if (event.getNotifier()==getNotationView().getElement()) refreshVisuals();
	}
	
	
	@Override
	protected void refreshLabel() {
		super.refreshLabel();
		if (tooltip==null) {
			tooltip = new Label("");
			getFigure().setToolTip(tooltip);
		}
		tooltip.setText(helper.getToolTip());
	}

	
	@Override
	protected Image getLabelIcon() {
		return helper.getLabelIcon();
	}

    
	@Override
	protected void refreshFontColor() {
		setFontColor(helper.getFontColor());
	}

	@Override
	protected boolean isEditable() {
		return super.isEditable() && helper.isEditable();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		// We have to make sure that the parent is not null.
		return (getParent()!=null) ? super.getAdapter(key) : null;
	}
	
}
