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


/** A transition update (name adopted from Uppaal) is a clock assignment assiciated with a transition, 
 * thus, the action of resetting a clock to zero.
 */
public class TransitionUpdateProvider implements ITextualExtensionProvider {

	/** The ID of the extension. */
	public static final String EXTENSION_ID = "cwi.ea.extensions.clocks.transitionUpdates";
	
	/** Create a default update. */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StringExtension("");
	}

	/** Edit the update. */
	public String editExtension(IExtension extension) {
		StringExtension update = (StringExtension) extension;
		return update.toString(); 
	}

	/** Print the update. */
	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}

	/** Parse the update. */
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		String ret = "";
		try {
			//ClocksLexer lexer = new ClocksLexer(new StringReader(value));
			TCAClocksParser parser = new TCAClocksParser(value);
			ret = parser.update();
		} catch (RecognitionException re) {
			//do nothing
		//} catch (TokenStreamException tse) {
		//	//do nothing
		}
		return new StringExtension(ret);
	}

	/**
	 * Compute the product of two updates. As clocks may only by updated to zero, the product is given by the union.
	 * @internal Need to adapt to new (assignment) form of updates? 
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		BasicEList<IExtension> result = new BasicEList<IExtension>();
		
		try {
			StringListExtension s1 = StringListExtension.parse(x1.toString());
			StringListExtension s2 = StringListExtension.parse(x2.toString());
		
			s1.append(s2, true);
			StringExtension s = new StringExtension(s1.toString());
			result.add(s);
		} catch (ParseException e) {
			System.err.println("Error during clocks-update product.");
		}
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
		
		StringExtension update = (StringExtension) x;
		boolean result = false;
		TCAClocksParser parser;
		EList<String> aNames = null; //all names of clocks in the automaton
		EList<String> uNames = null; //names of clocks used in the update
		EList<String> lNames = null; //names of clocks used on the left side of the update
		
		try {
			//get list of clocks from the update
			//lexer = new ClocksLexer(new StringReader(update.getValue()));
			parser = new TCAClocksParser(update.getValue());
			uNames = parser.clock_names();
			
			//Check that all clocks used in updates are known to the automaton
			//get list of clocks from the automaton
			aNames = (StringListExtension.parse((
					(StringExtension) ((Automaton) ((Transition) 
							x.getOwner()).getAutomaton()).findExtension("org.ect.ea.extensions.clocks.automatonClocks")).toString())).getValues();

			//get the list of clocks on the left, i.e., which get assigned a value
			parser = new TCAClocksParser(update.getValue());
			lNames = parser.clock_names_left();
		} catch (RecognitionException re) {
			//do nothing
		//} catch (TokenStreamException tse) {
		//	//do nothing
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//check that every clock is assinged only once
		EList<String> checkL = new StringListExtension(lNames).getDuplicateEntries();
		if (!checkL.isEmpty()) {
			return ValidationResult.newErrorResult("clocks may be assigned only once: " + checkL);
		}
		
		//check that every clock in the guard is defined on automaton level
		result = aNames.containsAll(uNames);
		if (result == false) {
			uNames.removeAll(aNames);
			return ValidationResult.newErrorResult("clocks used in updates must be declared on automaton level, declare " + uNames);
		}	
		
		return ValidationResult.newOKResult();
	}

	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	public boolean isReadOnly(IExtension extension) {
		return false;
	}

}
