package org.ect.ea.extensions.clocks;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.parsers.TCAClocksParser;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

import org.antlr.runtime.RecognitionException;
//import antlr.TokenStreamException;



public class TransitionGuardProvider implements ITextualExtensionProvider {
	
	/** The ID of the extension. */
	public static final String EXTENSION_ID = "cwi.ea.extensions.clocks.transitionGuards";
	
	/** Create the default guard constraint. */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StringExtension("true");
	}

	/** Edit the guard. */
	public String editExtension(IExtension extension) {
		StringExtension guard = (StringExtension) extension;
		return guard.toString();
	}

	/** Print the guard. */
	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}
	
	/** Parse the guard.
	 * @return A StringExtension containing the standardized guard. In case the guard could not be
	 * parsed (completely), only a part of the guard - which can also be the empty string - is returned.  
	 */	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		String ret = "";
		if (value.equals("")) {
			ret = "true";
		} else {
			try {
				value = ClockUtils.simplifyCC(value);
				//ClocksLexer lexer = new ClocksLexer(new StringReader(value));
				TCAClocksParser parser = new TCAClocksParser(value);
				ret = parser.clock_constraint();
			} catch (RecognitionException re) {
				//do nothing
			//} catch (TokenStreamException tse) {
			//	//do nothing
			}
		}
		return new StringExtension(ret);
	}
	
	
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		BasicEList<IExtension> result = new BasicEList<IExtension>();
		StringExtension product = new StringExtension();
		
		//minor simplification for default extension
		if (x1.toString().equals("true")) {
			product.setValue(x2.toString());
		} else if (x2.toString().equals("true")) {
			product.setValue(x1.toString());
		} else {
			product.setValue("(" + x1.toString() + ") & (" + x2.toString() + ")");
		}
		result.add(product);
		
		return result;
	}
	
	/** There must not be silent extensions. */
	public IExtension createSilentExtension(Transition owner) {
		return null;
	}
	
	/** There must not be silent extensions. */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}
	
	public IValidationResult validateExtension(IExtension x) {
		StringExtension guard = (StringExtension) x;
		boolean result = false;
		TCAClocksParser parser;
		EList<String> aNames = null;
		EList<String> gNames = null;

		try {
			/*Check that all clocks used in the guard are known to the automaton*/		
			//get list of clocks from the automaton
			aNames = (StringListExtension.parse((
					(StringExtension) ((Automaton) ((Transition) 
							x.getOwner()).getAutomaton()).findExtension("org.ect.ea.extensions.clocks.automatonClocks")).toString())).getValues();
			
			//get list of clocks from the guard
			//lexer = new ClocksLexer(new StringReader(guard.getValue()));
			parser = new TCAClocksParser(guard.getValue());
			gNames = parser.clock_names();
		} catch (RecognitionException re) {
			//do nothing
		//} catch (TokenStreamException tse) {
		//	//do nothing
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//compare
		result = aNames.containsAll(gNames);
		if (result == false) {
			gNames.removeAll(aNames);
			return ValidationResult.newErrorResult("clocks used in guards must be declared on automaton level, declare " + gNames);
		}
		return ValidationResult.newOKResult();
	}

	public boolean isReadOnly(IExtension extension) {
		return false;
	}

	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

}
