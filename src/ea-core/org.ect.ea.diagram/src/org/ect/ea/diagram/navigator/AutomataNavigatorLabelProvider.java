package org.ect.ea.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonNameEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateNameEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.diagram.providers.AutomataElementTypes;
import org.ect.ea.diagram.providers.AutomataParserProvider;


/**
 * @generated
 */
public class AutomataNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		AutomataDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		AutomataDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof AutomataNavigatorItem
				&& !isOwnView(((AutomataNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof AutomataNavigatorGroup) {
			AutomataNavigatorGroup group = (AutomataNavigatorGroup) element;
			return AutomataDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof AutomataNavigatorItem) {
			AutomataNavigatorItem navigatorItem = (AutomataNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case ModuleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.cwi.nl/ea/automata?Module", AutomataElementTypes.Module_1000); //$NON-NLS-1$
		case AutomatonEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.cwi.nl/ea/automata?Automaton", AutomataElementTypes.Automaton_1001); //$NON-NLS-1$
		case StateEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/ea/automata?State", AutomataElementTypes.State_2001); //$NON-NLS-1$
		case TransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/ea/automata?Transition", AutomataElementTypes.Transition_3001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = AutomataDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& AutomataElementTypes.isKnownElementType(elementType)) {
			image = AutomataElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof AutomataNavigatorGroup) {
			AutomataNavigatorGroup group = (AutomataNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof AutomataNavigatorItem) {
			AutomataNavigatorItem navigatorItem = (AutomataNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000Text(view);
		case AutomatonEditPart.VISUAL_ID:
			return getAutomaton_1001Text(view);
		case StateEditPart.VISUAL_ID:
			return getState_2001Text(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_3001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getModule_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getAutomaton_1001Text(View view) {
		IAdaptable hintAdapter = new AutomataParserProvider.HintAdapter(
				AutomataElementTypes.Automaton_1001,
				(view.getElement() != null ? view.getElement() : view),
				AutomataVisualIDRegistry
						.getType(AutomatonNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			AutomataDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated
	 */
	private String getState_2001Text(View view) {
		IAdaptable hintAdapter = new AutomataParserProvider.HintAdapter(
				AutomataElementTypes.State_2001,
				(view.getElement() != null ? view.getElement() : view),
				AutomataVisualIDRegistry.getType(StateNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE
					.intValue());
		} else {
			AutomataDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

	}

	/**
	 * @generated NOT
	 */
	private String getTransition_3001Text(View view) {
		return "?";
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return ModuleEditPart.MODEL_ID.equals(AutomataVisualIDRegistry
				.getModelID(view));
	}

}
