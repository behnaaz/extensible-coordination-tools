package org.ect.ea.extensions.startstates.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.internal.figures.CircleFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;



public class InvisibleNodeEditPart extends ShapeNodeEditPart {

	public static final String VISUAL_ID = StartStateExtensionProvider.EXTENSION_ID + "_invisibleNode";

	protected IFigure primaryShape;

	public InvisibleNodeEditPart(View view) {
		super(view);
	}
	
	
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}
	
	
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {
			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}
			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}
	
	
	/**
	 * Make the node unresizable.
	 */
	@Override
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
		return result;
	}
	
	
	/**
	 * Creates figure for this edit part.
	 */
	protected NodeFigure createNodeFigure() {
		primaryShape = new CircleFigure(12);
		primaryShape.setBackgroundColor(AutomatonEditPart.BACKGROUND_COLOR);
		primaryShape.setForegroundColor(AutomatonEditPart.BACKGROUND_COLOR);
		return (NodeFigure) primaryShape;
	}
	
}