/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/

package org.ect.reo.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.Node;
import org.ect.reo.diagram.edit.policies.NodeItemSemanticEditPolicy;
import org.ect.reo.diagram.figures.NodeFigure;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class NodeEditPart extends AbstractBorderedShapeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public NodeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPoliciesGen() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new NodeItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * We remove connection handles for these diagram elements.
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies() {
		// Use generated implementation.
		createDefaultEditPoliciesGen();
		// Remove edit policy for connector handles.
		removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				View childView = (View) child.getModel();
				switch (ReoVisualIDRegistry.getVisualID(childView)) {
				case NodeNameEditPart.VISUAL_ID:
					return new BorderItemSelectionEditPolicy() {

						protected List createSelectionHandles() {
							MoveHandle mh = new MoveHandle(
									(GraphicalEditPart) getHost());
							mh.setBorder(null);
							return Collections.singletonList(mh);
						}
					};
				}
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
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
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		Node node = (Node) getNotationView().getElement();
		return primaryShape = new NodeFigure(node);
	}

	/**
	 * @generated
	 */
	public NodeFigure getPrimaryShape() {
		return (NodeFigure) primaryShape;
	}

	/**
	 * We tweak the position of the label a bit.
	 * @generated NOT
	 */
	@Override
	protected void addBorderItem(IFigure borderItemContainer,
			IBorderItemEditPart borderItemEditPart) {
		if (borderItemEditPart instanceof NodeNameEditPart) {
			BorderItemLocator locator = new BorderItemLocator(getMainFigure(),
					PositionConstants.SOUTH_EAST);
			locator.setBorderItemOffset(new Dimension(-10, -10));
			borderItemContainer.add(borderItemEditPart.getFigure(), locator);
		} else {
			super.addBorderItem(borderItemContainer, borderItemEditPart);
		}
	}

	/**
	 * @generated NOT
	 */
	protected org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure createNodePlate() {
		Size size = ReoFigureSizes.NODE;
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(size.getWidth()), getMapMode().DPtoLP(size.getHeight()));
		return result;
	}

	/**
	 * @generated
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
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	@Override
	protected org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure createMainFigure() {
		org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	@Override
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(ReoVisualIDRegistry
				.getType(NodeNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(16);
		types.add(ReoElementTypes.Sync_3001);
		types.add(ReoElementTypes.LossySync_3002);
		types.add(ReoElementTypes.FIFO_3003);
		types.add(ReoElementTypes.SyncDrain_3005);
		types.add(ReoElementTypes.SyncSpout_3006);
		types.add(ReoElementTypes.AsyncDrain_3007);
		types.add(ReoElementTypes.AsyncSpout_3008);
		types.add(ReoElementTypes.Filter_3011);
		types.add(ReoElementTypes.Transform_3012);
		types.add(ReoElementTypes.Timer_3015);
		types.add(ReoElementTypes.NodeSourceEnds_3013);
		types.add(ReoElementTypes.CustomDirectedChannel_3016);
		types.add(ReoElementTypes.CustomDrainChannel_3017);
		types.add(ReoElementTypes.CustomSpoutChannel_3018);
		types.add(ReoElementTypes.PrioritySync_3019);
		types.add(ReoElementTypes.BlockingSync_3020);
		types.add(ReoElementTypes.BlockingSinkSync_3021);
		types.add(ReoElementTypes.BlockingSourceSync_3022);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.Sync_3001);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.LossySync_3002);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.FIFO_3003);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.SyncDrain_3005);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.SyncSpout_3006);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.AsyncDrain_3007);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.AsyncSpout_3008);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.Filter_3011);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.Transform_3012);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.Timer_3015);
		}
		if (targetEditPart instanceof SourceEndEditPart) {
			types.add(ReoElementTypes.NodeSourceEnds_3013);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.CustomDirectedChannel_3016);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.CustomDrainChannel_3017);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.CustomSpoutChannel_3018);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.PrioritySync_3019);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.BlockingSync_3020);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.BlockingSinkSync_3021);
		}
		if (targetEditPart instanceof org.ect.reo.diagram.edit.parts.NodeEditPart) {
			types.add(ReoElementTypes.BlockingSourceSync_3022);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == ReoElementTypes.Sync_3001) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.LossySync_3002) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.FIFO_3003) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.SyncDrain_3005) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.SyncSpout_3006) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.AsyncDrain_3007) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.AsyncSpout_3008) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Filter_3011) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Transform_3012) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Timer_3015) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.NodeSourceEnds_3013) {
			types.add(ReoElementTypes.SourceEnd_2006);
		} else if (relationshipType == ReoElementTypes.CustomDirectedChannel_3016) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.CustomDrainChannel_3017) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.CustomSpoutChannel_3018) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.PrioritySync_3019) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSync_3020) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSinkSync_3021) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSourceSync_3022) {
			types.add(ReoElementTypes.Node_2001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(16);
		types.add(ReoElementTypes.Sync_3001);
		types.add(ReoElementTypes.LossySync_3002);
		types.add(ReoElementTypes.FIFO_3003);
		types.add(ReoElementTypes.SyncDrain_3005);
		types.add(ReoElementTypes.SyncSpout_3006);
		types.add(ReoElementTypes.AsyncDrain_3007);
		types.add(ReoElementTypes.AsyncSpout_3008);
		types.add(ReoElementTypes.Filter_3011);
		types.add(ReoElementTypes.Transform_3012);
		types.add(ReoElementTypes.Timer_3015);
		types.add(ReoElementTypes.SinkEndNode_3014);
		types.add(ReoElementTypes.CustomDirectedChannel_3016);
		types.add(ReoElementTypes.CustomDrainChannel_3017);
		types.add(ReoElementTypes.CustomSpoutChannel_3018);
		types.add(ReoElementTypes.PrioritySync_3019);
		types.add(ReoElementTypes.BlockingSync_3020);
		types.add(ReoElementTypes.BlockingSinkSync_3021);
		types.add(ReoElementTypes.BlockingSourceSync_3022);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == ReoElementTypes.Sync_3001) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.LossySync_3002) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.FIFO_3003) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.SyncDrain_3005) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.SyncSpout_3006) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.AsyncDrain_3007) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.AsyncSpout_3008) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Filter_3011) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Transform_3012) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.Timer_3015) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.SinkEndNode_3014) {
			types.add(ReoElementTypes.SinkEnd_2007);
		} else if (relationshipType == ReoElementTypes.CustomDirectedChannel_3016) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.CustomDrainChannel_3017) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.CustomSpoutChannel_3018) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.PrioritySync_3019) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSync_3020) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSinkSync_3021) {
			types.add(ReoElementTypes.Node_2001);
		} else if (relationshipType == ReoElementTypes.BlockingSourceSync_3022) {
			types.add(ReoElementTypes.Node_2001);
		}		
		return types;
	}

	/**
	 * Update the color and the text when properties of the node change.
	 * @generated NOT
	 */
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		updatePrimaryShape();
	}

	/**
	 * Update the primary shape.
	 * @generated NOT
	 */
	public void updatePrimaryShape() {
		if (getPrimaryShape() != null) {
			getPrimaryShape().update();
		}
	}
}
