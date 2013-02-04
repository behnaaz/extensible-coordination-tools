package org.ect.ea.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.ect.ea.diagram.edit.policies.ModuleCanonicalEditPolicy;
import org.ect.ea.diagram.edit.policies.ModuleItemSemanticEditPolicy;

/**
 * @generated
 */
public class ModuleEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public static final String MODEL_ID = "Automata"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;

	/**
	 * @generated
	 */
	public ModuleEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModuleItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModuleCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}
}
