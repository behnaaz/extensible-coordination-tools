package org.ect.ea.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.diagram.edit.policies.AutomatonItemSemanticEditPolicy;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;


/**
 * @generated
 */
public class AutomatonEditPart extends ShapeNodeEditPart {

	/**
	 * @generated NOT
	 */
	public static final Dimension DEFAULT_SIZE = new Dimension(340, 260);

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1001;

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
	public AutomatonEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new AutomatonItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
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
	 * @generated
	 */
	protected IFigure createNodeShape() {
		AutomatonFigure figure = new AutomatonFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public AutomatonFigure getPrimaryShape() {
		return (AutomatonFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof AutomatonNameEditPart) {
			((AutomatonNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getAutomatonNameLabel());
			return true;
		}
		if (childEditPart instanceof AutomatonExtensionEditPart) {
			((AutomatonExtensionEditPart) childEditPart)
					.setLabel(getPrimaryShape().getAutomatonExtensionLabel());
			return true;
		}
		if (childEditPart instanceof AutomatonCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureAutomatonCompartment();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane
					.add(((AutomatonCompartmentEditPart) childEditPart)
							.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

		if (childEditPart instanceof AutomatonCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureAutomatonCompartment();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((AutomatonCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(DEFAULT_SIZE.width), getMapMode().DPtoLP(
				DEFAULT_SIZE.height));
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
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
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
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(AutomataVisualIDRegistry
				.getType(AutomatonNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		if (editPart instanceof AutomatonCompartmentEditPart) {
			return getPrimaryShape().getFigureAutomatonCompartment();
		}
		return super.getContentPaneFor(editPart);
	}

	/**
	 * @generated
	 */
	public class AutomatonFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fAutomatonNameLabel;
		/**
		 * @generated
		 */
		private WrappingLabel fAutomatonExtensionLabel;

		/**
		 * @generated
		 */
		private RectangleFigure fFigureAutomatonCompartment;

		/**
		 * @generated
		 */
		public AutomatonFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(8),
					getMapMode().DPtoLP(8)));
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fAutomatonNameLabel = new WrappingLabel();
			fAutomatonNameLabel.setText("");

			GridData constraintFAutomatonNameLabel = new GridData();
			constraintFAutomatonNameLabel.verticalAlignment = GridData.CENTER;
			constraintFAutomatonNameLabel.horizontalAlignment = GridData.CENTER;
			constraintFAutomatonNameLabel.horizontalIndent = 0;
			constraintFAutomatonNameLabel.horizontalSpan = 1;
			constraintFAutomatonNameLabel.verticalSpan = 1;
			constraintFAutomatonNameLabel.grabExcessHorizontalSpace = true;
			constraintFAutomatonNameLabel.grabExcessVerticalSpace = false;
			this.add(fAutomatonNameLabel, constraintFAutomatonNameLabel);

			fAutomatonExtensionLabel = new WrappingLabel();
			fAutomatonExtensionLabel.setText("");

			GridData constraintFAutomatonExtensionLabel = new GridData();
			constraintFAutomatonExtensionLabel.verticalAlignment = GridData.CENTER;
			constraintFAutomatonExtensionLabel.horizontalAlignment = GridData.CENTER;
			constraintFAutomatonExtensionLabel.horizontalIndent = 0;
			constraintFAutomatonExtensionLabel.horizontalSpan = 1;
			constraintFAutomatonExtensionLabel.verticalSpan = 1;
			constraintFAutomatonExtensionLabel.grabExcessHorizontalSpace = true;
			constraintFAutomatonExtensionLabel.grabExcessVerticalSpace = false;
			this.add(fAutomatonExtensionLabel,
					constraintFAutomatonExtensionLabel);

			fFigureAutomatonCompartment = new RectangleFigure();
			fFigureAutomatonCompartment.setOutline(false);

			GridData constraintFFigureAutomatonCompartment = new GridData();
			constraintFFigureAutomatonCompartment.verticalAlignment = GridData.FILL;
			constraintFFigureAutomatonCompartment.horizontalAlignment = GridData.FILL;
			constraintFFigureAutomatonCompartment.horizontalIndent = 0;
			constraintFFigureAutomatonCompartment.horizontalSpan = 1;
			constraintFFigureAutomatonCompartment.verticalSpan = 1;
			constraintFFigureAutomatonCompartment.grabExcessHorizontalSpace = true;
			constraintFFigureAutomatonCompartment.grabExcessVerticalSpace = true;
			this.add(fFigureAutomatonCompartment,
					constraintFFigureAutomatonCompartment);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getAutomatonNameLabel() {
			return fAutomatonNameLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getAutomatonExtensionLabel() {
			return fAutomatonExtensionLabel;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureAutomatonCompartment() {
			return fFigureAutomatonCompartment;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 222, 232, 242);

	/**
	 * @generated NOT
	 */
	public static final Color BACKGROUND_COLOR = THIS_BACK;

}
