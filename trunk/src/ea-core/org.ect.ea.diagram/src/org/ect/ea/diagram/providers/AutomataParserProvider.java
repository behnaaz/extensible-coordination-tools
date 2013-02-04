package org.ect.ea.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.diagram.edit.parts.AutomatonNameEditPart;
import org.ect.ea.diagram.edit.parts.StateNameEditPart;
import org.ect.ea.diagram.parsers.MessageFormatParser;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;


/**
 * @generated
 */
public class AutomataParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser automatonName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getAutomatonName_4003Parser() {
		if (automatonName_4003Parser == null) {
			automatonName_4003Parser = createAutomatonName_4003Parser();
		}
		return automatonName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createAutomatonName_4003Parser() {
		EAttribute[] features = new EAttribute[] { AutomataPackage.eINSTANCE
				.getAutomaton_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser stateName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getStateName_4001Parser() {
		if (stateName_4001Parser == null) {
			stateName_4001Parser = createStateName_4001Parser();
		}
		return stateName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStateName_4001Parser() {
		EAttribute[] features = new EAttribute[] { AutomataPackage.eINSTANCE
				.getState_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case AutomatonNameEditPart.VISUAL_ID:
			return getAutomatonName_4003Parser();
		case StateNameEditPart.VISUAL_ID:
			return getStateName_4001Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(AutomataVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(AutomataVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (AutomataElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
