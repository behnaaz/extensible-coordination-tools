package org.ect.ea.extensions.statememory;

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
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;


public class StateMemoryExtensionProvider implements ITextualExtensionProvider {

	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.stateMemory";	
		
	// The name of the extensions.
	public static final String EXTENSION_NAME = "State Memory";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.STATES, new StateMemoryExtensionProvider());
	
	/**
	 * Create a default list memory cell names.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StateMemory();
	}
	
	/**
	 * Silent extension. Not needed for a state extension.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return null;
	}

	/**
	 * Not needed.
	 */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}
	
	
	public String editExtension(IExtension extension) {
		return extension.toString();
	}
	
	public String printExtension(IExtension extension) {
		return "[" + editExtension(extension) + "]"; 
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		return StateMemory.parse(value);
	}

	
	/**
	 * Compute the product of two port name extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>();
		StringListExtension p1 = (StringListExtension) x1;
		StringListExtension p2 = (StringListExtension) x2;
		
		// This is actually a StateMemory object, but we need it for backward compatibility.
		StringListExtension joined = p1.getCopy();		
		joined.disjointAppend(p2, null);
		
		if (joined instanceof StateMemory && p2 instanceof StateMemory) {
			StateMemory joined_ = (StateMemory) joined;
			StateMemory p2_ = (StateMemory) p2;
			
			for (int i=0; i<p2_.getValues().size(); i++) {
				String oldname = p2_.getValues().get(i);
				String newname = joined_.getValues().get(p1.getValues().size() + i);
				String init = p2_.getInitializations().get(oldname);
				if (init!=null) joined_.getInitializations().put(newname, init); 
			}
		}

		result.add(joined);
		return result;
		
	}


	/**
	 * Validate a memory cells definition.
	 */
	public IValidationResult validateExtension(IExtension x) {
		
		StringListExtension extension = (StringListExtension) x;
		EList<String> duplicates = extension.getDuplicateEntries();
		
		// Check for duplicate memory cells.
		if (!duplicates.isEmpty()) {
			String list = new StringListExtension(duplicates).toString();
			String message = "Duplicate memory cells: " + list;
			return ValidationResult.newErrorResult(message);
		}
				
		return ValidationResult.newOKResult();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#getLabelColor(org.ect.ea.extensions.IExtension)
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
