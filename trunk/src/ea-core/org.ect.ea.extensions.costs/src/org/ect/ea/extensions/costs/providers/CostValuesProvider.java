package org.ect.ea.extensions.costs.providers;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.costs.UnsupportedCostsTypeException;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.costs.CostsAlgebraExtension;
import org.ect.ea.extensions.costs.CostsObjectExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;



public class CostValuesProvider  implements ITextualExtensionProvider {

	// The ID of the extensions. 
	public static final String EXTENSION_ID = "cwi.ea.extensions.costValues";
	
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Cost Values";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new CostValuesProvider());

	/**
	 * Create a new CostsObjectExtension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		if (getAlgebras(owner)!=null) {
			return getAlgebras(owner).createNulls();
		} else {
			return new CostsObjectExtension();
		}
	}
	
	/**
	 * Create silent extension. This is the same as the default one.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return createDefaultExtension(transition);
	}
	
	/**
	 * Check whether the extension has only null values.
	 */
	public boolean isSilentExtension(IExtension extension) {

		CostsObjectExtension costs = (CostsObjectExtension) extension;
		CostsAlgebraExtension algebras = getAlgebras(extension.getOwner());
		
		for (int i=0; i<costs.getCostsObjects().size(); i++) {
			CostsObject object = costs.getCostsObjects().get(i);
			CostsAlgebra algebra = algebras.getCostsAlgebras().get(i);
			if (!object.getValue().equals( algebra.getNull().getValue() )) return false;
		}

		return true;
	}


	
	public String printExtension(IExtension extension) {
		CostsObjectExtension costs = (CostsObjectExtension) extension;
		return getAlgebras(extension.getOwner()).print(costs);
	}

	public String editExtension(IExtension extension) {
		return printExtension(extension);
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		return getAlgebras(owner).parse(value);
	}


	/**
	 * Compute the product of to cost algebras / cost objects.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {

		EList<IExtension> result = new BasicEList<IExtension>();
		
		CostsAlgebraExtension algebras1 = getAlgebras(x1.getOwner());
		CostsAlgebraExtension algebras2 = getAlgebras(x2.getOwner());
		
		if (algebras1.equals( algebras2 )) {

			CostsObjectExtension c1 = (CostsObjectExtension) x1;
			CostsObjectExtension c2 = (CostsObjectExtension) x2;
			CostsObjectExtension c1x2 = new CostsObjectExtension();
			
			for (int i=0; i<algebras1.getCostsAlgebras().size(); i++) {
				CostsAlgebra algebra = algebras1.getCostsAlgebras().get(i);
				try {
					c1x2.getCostsObjects().add( algebra.combineParallel(
													c1.getCostsObjects().get(i), 
													c2.getCostsObjects().get(i)) );
				} catch (UnsupportedCostsTypeException e) {
					CostsExtensionPlugin.getInstance().logError("Error in costs product.", e);
				}
			}
			
			result.add( c1x2 );
		}
		
		else {
			result.add( new CostsObjectExtension() );
		}

		return result;
	}


	
	private CostsAlgebraExtension getAlgebras(IExtendible owner) {
		return (CostsAlgebraExtension) ((Transition) owner).getAutomaton().findExtension(CostAlgebrasProvider.EXTENSION_ID);
	}
	
	
	public IValidationResult validateExtension(IExtension x) {
		return ValidationResult.newOKResult();
	}

	
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkGreen;
	}
	
	
	public boolean isReadOnly(IExtension extension) {
		return false;
	}
	
}
