package org.ect.ea.extensions.portnames.providers;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.util.IValidationResult;



public class AutomatonPortNamesProvider implements ITextualExtensionProvider {

	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.automatonPortNames";
	
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Port Names (Automata)";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.AUTOMATA, new AutomatonPortNamesProvider());

	
	/**
	 * Create a default AutomatonPortNames extension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new AutomatonPortNames();
	}
	
	/**
	 * Create a silent extension. Only needed for transitions.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return null;
	}

	/**
	 * Not needed here.
	 */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}
	
	
	public String editExtension(IExtension extension) {
		AutomatonPortNames portNames = (AutomatonPortNames) extension;
		return portNames.toString();
	}
	
	public String printExtension(IExtension extension) {
		return "{" + editExtension(extension) + "}";
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		AutomatonPortNames portNames = AutomatonPortNames.parse(value);
		portNames.trimAll();
		return portNames;
	}

	
	/**
	 * Compute the product of two port name extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>();
		AutomatonPortNames p1 = (AutomatonPortNames) x1;
		AutomatonPortNames p2 = (AutomatonPortNames) x2;
		
		// Join the PortNames.
		AutomatonPortNames joined = AutomatonPortNames.join(p1, p2);
		result.add(joined);		
		
		return result;		
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IExtensionProvider#validateExtension(org.ect.ea.extensions.IExtension)
	 */
	public IValidationResult validateExtension(IExtension extension) {
		return ((AutomatonPortNames) extension).validate();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#getLabelColor()
	 */
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#isReadOnly(org.ect.ea.extensions.IExtension)
	 */
	public boolean isReadOnly(IExtension extension) {
		return false;
	}

}
