package org.ect.ea.extensions.guards;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;



public class GuardsProvider  implements ITextualExtensionProvider {
	
	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.guards";
	
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Guards";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new GuardsProvider());
	
	
	/**
	 * Create the default guard.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new BooleanExtension(true);
	}
	
	/**
	 * Create a silent extension. This is the same as the default one here.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return createDefaultExtension(transition);
	}
	
	/**
	 * Check whether a guard is silent.
	 */
	public boolean isSilentExtension(IExtension extension) {
		return (extension instanceof BooleanExtension) && (((BooleanExtension) extension).getValue()==true);
	}
	
	
	public String editExtension(IExtension extension) {
		return extension.toString();
	}
	
	public String printExtension(IExtension extension) {
		return editExtension(extension);
//		return "{" + editExtension(extension) + "}";
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		return Guard.parseGuard(value);
	}

	
	/**
	 * Compute the product of guards.
	 */
	@SuppressWarnings("unused")
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>();
		IExtension ext = null;
		
		// Check if the first parameter is a boolean.
		if (x1 instanceof BooleanExtension) {
			boolean value = ((BooleanExtension) x1).getValue();
			ext = ((value==true) ? (IExtension) EcoreUtil.copy(x2) : new BooleanExtension(false));
		}

		// Check if the second parameter is a boolean.
		if (x2 instanceof BooleanExtension) {
			boolean value = ((BooleanExtension) x2).getValue();
			ext = ((value==true) ? (IExtension) EcoreUtil.copy(x1) : new BooleanExtension(false));
		}
		
		if (ext!=null) {
			result.add(ext);
			return result;
		}
		
		// Otherwise we have real guards.
		Guard g1 = (Guard) EcoreUtil.copy(x1);
		Guard g2 = (Guard) EcoreUtil.copy(x2);
		Guard guard = new Guard();
		
		result.add(guard);
		
		return result;
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IExtensionProvider#validateExtension(org.ect.ea.extensions.IExtension)
	 */
	public IValidationResult validateExtension(IExtension extension) {
		if (extension instanceof Guard) {
			return ((Guard) extension).validate();
		} else if (extension instanceof BooleanExtension) {
			return ValidationResult.newOKResult();			
		} else {
			// Should never happen.
			return ValidationResult.newErrorResult("Unexpected type of guard.");
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#getLabelColor()
	 */
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkGreen;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#isReadOnly(org.ect.ea.extensions.IExtension)
	 */
	public boolean isReadOnly(IExtension extension) {
		return false;
	}

}
