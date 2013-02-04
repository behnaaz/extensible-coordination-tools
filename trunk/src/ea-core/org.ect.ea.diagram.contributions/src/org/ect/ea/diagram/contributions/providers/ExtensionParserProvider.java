package org.ect.ea.diagram.contributions.providers;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.ect.ea.diagram.contributions.parsers.ExtensionParser;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;
import org.ect.ea.diagram.providers.AutomataParserProvider;



public class ExtensionParserProvider extends AutomataParserProvider {

	private IParser extensionParser;

	public ExtensionParserProvider() {
		this.extensionParser = new ExtensionParser();
	}
	
	protected IParser getParser(int visualID) {
		if (visualID==AutomatonExtensionEditPart.VISUAL_ID ||
			visualID==StateExtensionEditPart.VISUAL_ID ||
			visualID==TransitionExtensionEditPart.VISUAL_ID) {
			return extensionParser;
		}
		return super.getParser(visualID);
	}

}
