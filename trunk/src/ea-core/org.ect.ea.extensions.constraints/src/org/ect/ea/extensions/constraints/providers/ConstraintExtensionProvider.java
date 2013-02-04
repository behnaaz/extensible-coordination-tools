package org.ect.ea.extensions.constraints.providers;

import java.text.ParseException;
import java.util.HashSet;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Parameter;
import org.ect.ea.extensions.constraints.operations.Normaliser;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ExtensionRefactoring.IRefactoringListener;



public class ConstraintExtensionProvider implements ITextualExtensionProvider {

	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.constraints";
	
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Constraints";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new ConstraintExtensionProvider());
	
	
	/**
	 * Default constraint is the <code>true</code> literal.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new Literal(true);
	}

	/**
	 * Silent constraint copies all memory cells to itself.
	 */
	public IExtension createSilentExtension(Transition transition) {
		
		State state = transition.getSource();
		Conjunction conjunction = new Conjunction();
		
		StringListExtension cells = CA.getMemoryCells(state);
		
		if (cells!=null) {
			for (String cell : cells.getValues()) {
				Equation equation = new Equation();
				Element left  = new Element(cell, ElementType.TARGET_MEMORY);
				Element right = new Element(cell, ElementType.SOURCE_MEMORY); 
				equation.setLeft(left);
				equation.setRight(right);
				conjunction.getMembers().add(equation);
			}
		}
		
		if (!conjunction.getMembers().isEmpty()) return conjunction;
		else return new Literal(true);
	}

	
	/**
	 * Check whether a constraint belongs to a silent transition.
	 * It is safe to assume here that the transition is a loop on some state.
	 */
	public boolean isSilentExtension(IExtension extension) {
		
		State state = ((Transition) extension.getOwner()).getSource();
		Disjunction dnf = new Normaliser().doSwitch((Constraint) extension);
		
		for (Constraint member : dnf.getMembers()) {
			if (!isSilent((Conjunction) member, state)) return false;
		}
		return true;
	}
	
	
	/**
	 * Check if a conjunction is a silent constraint.
	 * The conjunction must be a member of a DNF!
	 */
	private boolean isSilent(Conjunction conjunction, State state) {
		
		HashSet<String> correct = new HashSet<String>();
		for (Constraint member : conjunction.getMembers()) {

			if (member instanceof Literal) {
				if (((Literal) member).booleanValue()) continue;
				else return false;
			}
			else if (member instanceof Equation) {
				Parameter left = ((Equation) member).getLeft();
				Parameter right = ((Equation) member).getRight();
				if (left.equals(right)) continue;
				if (left instanceof Function || right instanceof Function) return false;
				Element l = (Element) left;
				Element r = (Element) right;
				if (!l.isMemory() || !r.isMemory()) return false;
				if (!(l.isSourceMemory() && r.isTargetMemory()) &&
					!(l.isTargetMemory() && r.isSourceMemory())) return false;
				if (l.getValue()==null || !l.getValue().equals(r.getValue())) return false;
				// OK.
				correct.add(l.getValue());
			}
			else {
				// Should not happen.
				return false;
			}
		}
		
		HashSet<String> cells = new HashSet<String>();
		StringListExtension original = CA.getMemoryCells(state);
		if (original!=null) cells.addAll(original.getValues());
		
		return cells.equals(correct);
		
	}


	/**
	 * Compute the product of two constraints.
	 * This simply creates a new {@link Conjunction}
	 * with the two original constraints as its parts. 
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>(1);
		
		// Get the transitions.
		Transition tr1 = (Transition) x1.getOwner();
		Transition tr2 = (Transition) x2.getOwner();

		// Make copies.		
		Constraint c1 = (Constraint) EcoreUtil.copy(x1);
		Constraint c2 = (Constraint) EcoreUtil.copy(x2);
				
		if (c1 instanceof Literal && ((Literal) c1).booleanValue()) {
			result.add(c2);
		} else if (c2 instanceof Literal && ((Literal) c2).booleanValue()) {
			result.add(c1);
		} else {
			// Rename duplicate memory cells.
			renameMemoryCells(c1, tr1, c2, tr2);

			result.add(conjunct(c1,c2));
		}
		
		return result;
	}
	
	private Conjunction conjunct(Constraint c1, Constraint c2) {
		Conjunction conjunction;

		if (c1 instanceof Conjunction) {
			if (c2 instanceof Conjunction) {
				conjunction = new Conjunction();
				conjunction.getMembers().addAll(((Conjunction) c1).getMembers());
				conjunction.getMembers().addAll(((Conjunction) c2).getMembers());
			} else {
				conjunction =(Conjunction) c1; 
				conjunction.getMembers().add(c2);
			}
		} else if (c2 instanceof Conjunction) {
			conjunction =(Conjunction) c2; 
			conjunction.getMembers().add(c1);				
		} else {
			conjunction = new Conjunction();
			conjunction.getMembers().add(c1);
			conjunction.getMembers().add(c2);				
		}
		return conjunction;
	}
	
	/*
	 * TODO: this could be nicer.
	 * We rely on the fact that the product of state memory is
	 * computed with StringListExtension.disjointAppend();
	 */
	private void renameMemoryCells(final Constraint c1, Transition tr1, final Constraint c2, Transition tr2) {
				
		// Rename the source cells.
		StringListExtension s1 = (StringListExtension) tr1.getSource().findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
		StringListExtension s2 = (StringListExtension) tr2.getSource().findExtension(StateMemoryExtensionProvider.EXTENSION_ID);

		if (s1!=null && s2!=null) {
			final StringListExtension renamed = (StringListExtension) EcoreUtil.copy(s1);
			renamed.disjointAppend(s2, new IRefactoringListener() {
				public void renamed(String oldId, String newId) {
					for (Element elem : c2.getAllElements()) {
						if (elem.isSourceMemory() && elem.getValue().equals(oldId)) elem.setValue(newId);
					}					
				}
			});
		}

		// Rename the target cells.
		StringListExtension t1 = (StringListExtension) tr1.getTarget().findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
		StringListExtension t2 = (StringListExtension) tr2.getTarget().findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
		
		if (t1!=null && t2!=null) {
			final StringListExtension renamed = (StringListExtension) EcoreUtil.copy(t1);
			renamed.disjointAppend(t2, new IRefactoringListener() {
				public void renamed(String oldId, String newId) {
					for (Element elem : c2.getAllElements()) {
						if (elem.isTargetMemory() && elem.getValue().equals(oldId)) elem.setValue(newId);
					}					
				}
			});
		}

	}
	
	
	public String printExtension(IExtension extension) {
		return ((Constraint) extension).toString();
	}
	
	public String editExtension(IExtension extension) {
		return printExtension(extension);
	}

	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		return ConstraintParserHelper.parse(value);		
	}
	
	public IValidationResult validateExtension(IExtension extension) {
		return ((Constraint) extension).validate();
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
