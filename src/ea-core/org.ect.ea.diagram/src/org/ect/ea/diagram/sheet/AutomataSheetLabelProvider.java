package org.ect.ea.diagram.sheet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.ect.ea.diagram.navigator.AutomataNavigatorGroup;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;


/**
 * @generated
 */
public class AutomataSheetLabelProvider extends DecoratingLabelProvider {

	/**
	 * @generated
	 */
	public AutomataSheetLabelProvider() {
		super(new AdapterFactoryLabelProvider(AutomataDiagramEditorPlugin
				.getInstance().getItemProvidersAdapterFactory()), null);
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		Object selected = unwrap(element);
		if (selected instanceof AutomataNavigatorGroup) {
			return ((AutomataNavigatorGroup) selected).getGroupName();
		}
		return super.getText(selected);
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		return super.getImage(unwrap(element));
	}

	/**
	 * @generated
	 */
	private Object unwrap(Object element) {
		if (element instanceof IStructuredSelection) {
			return unwrap(((IStructuredSelection) element).getFirstElement());
		}
		if (element instanceof EditPart) {
			return unwrapEditPart((EditPart) element);
		}
		if (element instanceof IAdaptable) {
			View view = (View) ((IAdaptable) element).getAdapter(View.class);
			if (view != null) {
				return unwrapView(view);
			}
		}
		return element;
	}

	/**
	 * @generated
	 */
	private Object unwrapEditPart(EditPart p) {
		if (p.getModel() instanceof View) {
			return unwrapView((View) p.getModel());
		}
		return p.getModel();
	}

	/**
	 * @generated
	 */
	private Object unwrapView(View view) {
		return view.getElement() == null ? view : view.getElement();
	}

}
