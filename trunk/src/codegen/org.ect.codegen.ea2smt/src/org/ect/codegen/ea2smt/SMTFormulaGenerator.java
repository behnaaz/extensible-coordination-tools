/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.codegen.ea2smt;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.ect.codegen.AbstractCodeGenerator;
import org.ect.codegen.IGenModel;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;
import org.ect.ea.extensions.clocks.parsers.TCADataParser;
import org.ect.ea.extensions.constraints.CA;


public class SMTFormulaGenerator extends AbstractCodeGenerator {

	private final String SELECTION_SIZE = SMTFormulaGenProductWizardPage.SELECTION_SIZE;
	private final String SELECTION_NAME = SMTFormulaGenProductWizardPage.SELECTION_NAME;
	private int upperBound;
	private int lowerBound;
	private int unfoldingDepth;
	private boolean infinite = false;
	private String mathsat = "msat";
	
	
	public final String PLUGIN_ID = "org.ect.ea.extensions.clocks";
	
	/** Only role of this method here: Call generateCode in the appropriate code generator class, based on the property targetlang. */
	public void generateCode(IGenModel genModel, IProgressMonitor monitor)
			throws CoreException {
		
		//System.out.println("Calling the code generator");
		
		/* initialisation/set up */
		String result;
		EList<Automaton> automataInFile = ((Automaton) genModel.getTarget()).getModule().getAutomata();
		int numOfSelectedAutomata = Integer.valueOf(genModel.getProperty(SELECTION_SIZE));
		EList<Automaton> selectedAutomata = new BasicEList<Automaton>();
		for (int i = 1; i <= numOfSelectedAutomata; i++) {
			String name = genModel.getProperty(SELECTION_NAME + i); //initialised in SMTFormulaGenProductWizardPage
			for (Automaton auto: automataInFile) {
				if (auto.getName().equals(name)) {
					selectedAutomata.add(auto);
				}
			}
		}
		if (selectedAutomata.size() < 1) {
			throw new FormulaGenerationException("No automata selected"); //should not happen
		}
		if (selectedAutomata.size() != numOfSelectedAutomata) {
			throw new FormulaGenerationException("Selected automata numbers do not match"); //should not happen
		}
		boolean product = (numOfSelectedAutomata == 1) ? false : true;
		EList<String> namesOfSelectedAutomata = new BasicEList<String>(); //needed?
		for (int i = 1; i <= numOfSelectedAutomata; i++) {
			namesOfSelectedAutomata.add(genModel.getProperty(SELECTION_NAME + i)); //initialised in SMTFormulaGenProductWizardPage
		}
		String projectName = genModel.getProperty(IGenModel.PROJECT_NAME);
		IProject project = createProject(projectName, new SubProgressMonitor(monitor, 1));
		IFolder folder = project.getFolder("src");
		if (!folder.exists()) folder.create(true, true, new NullProgressMonitor());		
		String filename = genModel.getProperty(CodegenUtils.PROPERTY_FILENAME);
		String format = genModel.getProperty("targetlang");
		
		SMTTemplate singleAutomaton = null; //if to generate code for a single automaton
		EList<SMTTemplate> productAutomata = new BasicEList<SMTTemplate>(); //if to generate code for more than one automaton
		
		monitor.beginTask("Generating code", 2);

		//selectedAutomata.get(0).getName() + 
		
		/* initialise the template(s) */
		if (!product) { //only one automaton to generate formulas for
			//name of the state selected for simple reachability, empty string if no state was selected.
			String stateName = genModel.getProperty(selectedAutomata.get(0).getName() + SMTFormulaGenPropertyWizardPage.STATE_SELECTED_SUFFIX);
			//initialise based on desired output format (targetlang property)
			if (format.equals(mathsat)) {
				singleAutomaton = new MathSATTemplate(selectedAutomata.get(0), stateName, unfoldingDepth, upperBound);
			} //add else if (format.equals(newformat)) here for other formats
		} else {
			if (format.equals(mathsat)) {
				for (Automaton auto: selectedAutomata) {
					String stateName = genModel.getProperty(auto.getName() + SMTFormulaGenPropertyWizardPage.STATE_SELECTED_SUFFIX);
					productAutomata.add(new MathSATTemplate(auto, stateName, unfoldingDepth, upperBound));
				}
			} //add else if (format.equals(newformat)) here for other formats
		}
		
		/* generate the formulas */
		if (!product) {
			//System.out.println("Jetzt wirds interessant");
			result = singleAutomaton.toString();
		} else {
			result = productAutomata.get(0).toString(productAutomata);
		}
		
		createFileFromString(folder, filename, result, new SubProgressMonitor(monitor, 1));
		
			//generate the formulas
//			try {
//				String stateName = genModel.getProperty(automaton.getName() + 
//						SMTFormulaGenPropertyWizardPage.STATE_SELECTED_SUFFIX); //constructors correctly handle the case where stateName is empty (i.e., state does not exist)				
				//call constructor depending on data range
//				SMTTemplate template;
//				if (infinite) {
//					template = new MathSATTemplate(automaton, unfDepth, 0);
//				} else {
//					template = new MathSATTemplate(automaton, unfDepth, upperBound);
//				}
				//result = template.getMathSatFormat(false);
//			} catch (ParseException pe) {
//				StackTraceElement[] stackTrace = pe.getStackTrace();
//				temp = pe.getMessage() + "\n";
//				for (int i = 1; i < stackTrace.length; i++) {
//					temp = temp + stackTrace[i].toString() + "\n";
//				}
//			}			
//		} else { //more automata to generate formulas for
//			//get the selected automata
//			EList<Automaton> selectedAutomata = new BasicEList<Automaton>();
//			String name;
//			for (int i = 1; i <= numOfSelectedAutomata; i++) {
//				name = genModel.getProperty(SELECTION_NAME + i);
//				for (Automaton auto: automataInFile) {
//					if (auto.getName().equals(name)) {
//						selectedAutomata.add(auto);
//					}
//				}
//			}
			//actually, the following should not happen, since validateGenModel
			//already checks for duplicate automaton names (which may cause the
			//sizes to be different)
//			if (!(selectedAutomata.size() == numOfSelectedAutomata)) { 
//				throw new FormulaGenerationException("Internal error while " +
//						"determining automata to generate code for, check " +
//						"implementation!");
//			}
			//generate the formulas
//			try {
//				EList<SMTTemplate> templates = new BasicEList<SMTTemplate>();
//				EList<SMTMathSATFormulaGenResult> results = new BasicEList<SMTMathSATFormulaGenResult>();
//				SMTTemplate template;
//				SMTMathSATFormulaGenResult interResult;
//				String stateName;
//				for (Automaton auto: selectedAutomata) {
//					stateName = genModel.getProperty(auto.getName() + 
//							SMTFormulaGenPropertyWizardPage.STATE_SELECTED_SUFFIX);
//					if (infinite) {
//						template = new MathSATTemplate(auto, unfDepth, 0);
//					} else {
//						template = new MathSATTemplate(auto, unfDepth, upperBound);
//					}
//					templates.add(template);
//					//interResult = template.getMathSatFormat(true);
//					//results.add(interResult);
//				}
//			}

				//result = SMTMathSATFormulaGenResult.merge(results);
				
//				temp = SMTTemplate.getMathSatFormat(templates, unfDepth, 
//						lowerBound, upperBound);
//				EList<SMTTemplate> templates = new LinkedEList<SMTTemplate>();
//				String stateName;
//				for (Automaton auto: selectedAutomata) {
//					stateName = genModel.getProperty(auto.getName() + 
//							SMTFormulaGenPropertyWizardPage.STATE_SELECTED_SUFFIX);
//					templates.add(new SMTTemplate(auto, unfDepth, 
//							lowerBound, upperBound, stateName));
//				}
//			} catch (ParseException pe) {
//				StackTraceElement[] stackTrace = pe.getStackTrace();
//				temp = pe.getMessage() + "\n";
//				for (int i = 1; i < stackTrace.length; i++) {
//					temp = temp + stackTrace[i].toString() + "\n";
//				}
//			}
		//store to file
	}

	public void initGenModel(Object target, IGenModel genModel) {
				
		IGraphicalEditPart editpart = (IGraphicalEditPart) target;
		Automaton automaton = (Automaton) editpart.getNotationView().getElement();
		
		
		// The automaton name is used as a default project name.
		String projectName = automaton.getName()==null ? "myautomaton" : automaton.getName();
		
		genModel.setProperty(IGenModel.PROJECT_NAME, projectName);
		genModel.setProperty(CodegenUtils.PROPERTY_DEPTH, CodegenUtils.DEFAULT_DEPTH);
		genModel.setProperty(CodegenUtils.PROPERTY_RANGE, CodegenUtils.DEFAULT_RANGE);
		genModel.setProperty(CodegenUtils.PROPERTY_FILENAME, projectName + ".msat");
		genModel.setProperty("targetlang", mathsat); //mathsat is default
		genModel.setTarget(automaton);		

	}

	/** Validate the model. All checks are performed for all automata in the file. 
	 * This method checks that
	 * <ul>
	 * <li>only allowed extension are used. Allowed extension are 
	 * {@link org.ect.ea.extensions.clocks.AutomatonClocksProvider}, 
	 * {@link org.ect.ea.extensions.clocks.TCADataConstraintsProvider},
	 * {@link org.ect.ea.extensions.constraints.ConstraintExtensionProvider},
	 * {@link org.ect.ea.extensions.startstates.StartStateExtensionProvider}, 
	 * {@link org.ect.ea.extensions.portnames.AutomatonPortNamesProvider}, 
	 * {@link org.ect.ea.extensions.portnames.TransitionPortNamesProvider}, 
	 * {@link org.ect.ea.extensions.clocks.TransitionUpdateProvider}, 
	 * {@link org.ect.ea.extensions.clocks.TransitionGuardProvider},
	 * {@link org.ect.ea.extension.stateMemory.StateMemoryExtensionProvider} and 
	 * {@link org.ect.ea.extensions.clocks.StateInvariantProvider}. </li>
	 * <li>all required extensions are used. Required extensions are
	 * {@link org.ect.ea.extensions.startstates.StartStateExtensionProvider}, 
	 * {@link org.ect.ea.extensions.portnames.AutomatonPortNamesProvider} and 
	 * {@link org.ect.ea.extensions.portnames.TransitionPortNamesProvider}. </li>
	 * <li> depending on whether the automaton is a CA or a TCA, some other 
	 * extensions are required and forbidden:
	 * <ul> 
	 * <li>Required extensions of TCA are 
	 * {@link org.ect.ea.extensions.clocks.TCADataConstraintsProvider},
	 * {@link org.ect.ea.extensions.clocks.AutomatonClocksProvider},
	 * {@link org.ect.ea.extensions.clocks.TransitionGuardProvider},
	 * {@link org.ect.ea.extensions.clocks.TransitionUpdateProvider} and 
	 * {@link org.ect.ea.extensions.clocks.StateInvariantProvider}. These are also the
	 * forbidden extensions for CA. </li>
	 * <li>Forbidden extensions of TCA are 
	 * {@link org.ect.ea.extensions.constraints.ConstraintExtensionProvider}.
	 * These are also the required extensions for CA. 
	 * </li>
	 * </ul>
	 * <li>all names (except for memory cells and ports) are unique within the 
	 * system.</li>
	 * <li>memory cell names may occur more than once in one automaton, but must
	 * be unique between different automata, and must not equal any other name. </li>
	 * <li>port names may be shared between automata, but must be different from 
	 * all other names</li>
	 * <li>there exists exactly one start state per automaton.</li>
	 * <li>untimed CA (that means without clock extensions) do not have 
	 * transitions with empty name set</li>
	 * <li>TCA transition with empty name set do not have an associated data 
	 * constraint</li>
	 * <li>there exist no names {@link org.ect.ea.extensions.clocks.ClockUtils#GLOBAL_CLOCK} or 
	 * {@link org.ect.ea.extensions.clocks.ClockUtils#NO_FLOW_NAME}</li>
	 * <li>the range of data values is well-formed, i.e. either on the form 
	 * <tt>lowerBound..upperBound</tt>, with <tt>lowerBound=0</tt>,  
	 * <tt>upperBound>=1</tt>, or empty (for infinite data domain)</li>
	 * <li>integer values in the data constraints lie within the given range</li>
	 * <li>unfolding depth is at least 1</li>
	 * <li>the target language is one of [mathsat,msat]</li>
	 * <li>the output file name is not empty</li>
	 * </ul>*/
	public IStatus validateGenModel(IGenModel genModel) {
		
		EList<Automaton> automata = ((Automaton) genModel.getTarget()).getModule().getAutomata();

		String projectName = genModel.getProperty(IGenModel.PROJECT_NAME);
		if (projectName==null || projectName.equals("")) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Project name cannot be empty.");
		}
		
		String rng = genModel.getProperty(CodegenUtils.PROPERTY_RANGE);
		//rng = rng.replace("..", ",");
		
		String targetlang = genModel.getProperty("targetlang");
		
		//for temporary usage
		//String tempS = ""; 
		EList<String> tempL = new BasicEList<String>();
		boolean tempB;
		 
		/* check extensions */
		/* Every automaton must support the automaton port names, transition 
		 * port names, and start state extension, and may support the state 
		 * memory extension. 
		 * If the automaton is a CA (untimed), it must support the constraint 
		 * extension, and must not support the TCA data constraint, 
		 * automaton clocks, transition guard, transition update and
		 * invariant extension.
		 * If the automaton is a TCA (timed), it must support the TCA data 
		 * constraint, automaton clocks, transition guard, transition update and
		 * invariant extension, and must not support the constraint extension. 
		 * */
		
		//check that only allowed extensions are used
		//note that not all these extensions can be used at the same time 
		final EList<String> allowedExtensions = new BasicEList<String>();
		allowedExtensions.add(ClockUtils.STATE_MEMORY_ID);
		allowedExtensions.add(ClockUtils.AUTOMATON_CLOCKS_ID);
		allowedExtensions.add(ClockUtils.TRANSITION_GUARD_ID);
		allowedExtensions.add(ClockUtils.TRANSITION_UPDATE_ID);
		allowedExtensions.add(ClockUtils.INVARIANT_ID);
		allowedExtensions.add(ClockUtils.DATA_CONSTRAINT_ID);
		allowedExtensions.add(ClockUtils.CONSTRAINT_ID);
		allowedExtensions.add(ClockUtils.AUTOMATON_PORT_NAMES_ID);
		allowedExtensions.add(ClockUtils.TRANSITION_PORT_NAMES_ID);
		allowedExtensions.add(ClockUtils.START_STATE_ID);
				
		final EList<String> requiredExtensions = new BasicEList<String>();
		requiredExtensions.add(ClockUtils.START_STATE_ID);
		requiredExtensions.add(ClockUtils.AUTOMATON_PORT_NAMES_ID);
		requiredExtensions.add(ClockUtils.TRANSITION_PORT_NAMES_ID);
		
		final EList<String> requiredCAExtensions = new BasicEList<String>();
		requiredCAExtensions.add(ClockUtils.CONSTRAINT_ID);
		
		final EList<String> forbiddenCAExtensions = new BasicEList<String>();
		forbiddenCAExtensions.add(ClockUtils.DATA_CONSTRAINT_ID);
		forbiddenCAExtensions.add(ClockUtils.AUTOMATON_CLOCKS_ID);
		forbiddenCAExtensions.add(ClockUtils.TRANSITION_GUARD_ID);
		forbiddenCAExtensions.add(ClockUtils.TRANSITION_UPDATE_ID);
		forbiddenCAExtensions.add(ClockUtils.INVARIANT_ID);
		
		final EList<String> requiredTCAExtensions = new BasicEList<String>(forbiddenCAExtensions);
		final EList<String> forbiddenTCAExtensions = new BasicEList<String>(requiredCAExtensions);
		
		EList<String> names = new BasicEList<String>(); //to check uniqueness of names
		Set<String> portNames = new HashSet<String>(); //port names can be shared, but must be different from other names, use set to automatically remove duplicates
		
		boolean timed = false;
		boolean untimed = false;
		boolean allMem = false;
		boolean noMem = false;
		
		for (Automaton automaton: automata) {

			//add automaton name to global name set name
			names.add(automaton.getName());
			
			//check that only allowed extensions are used
			final EList<String> usedExtensions = automaton.getUsedExtensionIds();
			if (!allowedExtensions.containsAll(usedExtensions)) {
				tempL.clear();
				tempL.addAll(usedExtensions);
				tempL.removeAll(allowedExtensions);
				return new Status(IStatus.ERROR, PLUGIN_ID, "Extension(s) used but not supported: " + tempL);
			}
		
			//check that all required extensions are used
			if (!usedExtensions.containsAll(requiredExtensions)) {
				tempL.clear();
				tempL.addAll(requiredExtensions);
				tempL.removeAll(usedExtensions);
				return new Status(IStatus.ERROR, PLUGIN_ID, automaton.getName() + "must support extension(s): " + tempL);
			}
		
			//check that exactly one of the constraint extensions is used, infer CA or TCA from that
			if (usedExtensions.contains(ClockUtils.DATA_CONSTRAINT_ID)) {
				timed = true;
				if (untimed == true) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "All automata must support same constraint extension, either " + ClockUtils.DATA_CONSTRAINT_ID + " or " + ClockUtils.CONSTRAINT_ID);
				}
			}
			if (usedExtensions.contains(ClockUtils.CONSTRAINT_ID)) {
				untimed = true;
				if (timed == true) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "All automata must support same constraint extension, either " + ClockUtils.DATA_CONSTRAINT_ID + " or " + ClockUtils.CONSTRAINT_ID);
				}
			}
			if (!(timed || untimed)) { //no constraint extension is used
				return new Status(IStatus.ERROR, PLUGIN_ID, "All automata must support same constraint extension, either " + ClockUtils.DATA_CONSTRAINT_ID + " or " + ClockUtils.CONSTRAINT_ID);
			}
			
			//if CA: check for required and forbidden extensions
			if (untimed) {
				if (!usedExtensions.containsAll(requiredCAExtensions)) {
					tempL.clear();
					tempL.addAll(requiredCAExtensions);
					tempL.removeAll(usedExtensions);
					return new Status(IStatus.ERROR, PLUGIN_ID, "Untimed CA must support extension(s): " + tempL);
				}
				tempL.clear();
				tempL.addAll(usedExtensions);
				tempL.addAll(forbiddenCAExtensions);
				tempL = new StringListExtension(tempL).getDuplicateEntries();
				if (!tempL.isEmpty()) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Untimed CA must not support extension(s): " + tempL);
				}
			}
			//if TCA: check for required and forbidden extensions
			if (timed) {

				if (!usedExtensions.containsAll(requiredTCAExtensions)) {
					tempL.clear();
					tempL.addAll(requiredTCAExtensions);
					tempL.removeAll(usedExtensions);
					return new Status(IStatus.ERROR, PLUGIN_ID, "TCA must support extension(s): " + tempL);
				}
				tempL.clear();
				tempL.addAll(usedExtensions);
				tempL.addAll(forbiddenTCAExtensions);
				tempL = new StringListExtension(tempL).getDuplicateEntries();
				if (!tempL.isEmpty()) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "TCA must not support extension(s): " + tempL);
				}
			}
				
			//check that the automaton has states (otherwise, code generation does not make sense)
			if (automaton.getStates().isEmpty()) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Automaton does not have states, code generation does not make sense.");
			}
			
			//check that the automaton has transitions (otherwise, code generation does not make sense)
			if (automaton.getTransitions().isEmpty()) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Automaton does not have transitions, code generation does not make sense.");
			}
			
			//check for state memory extension
			if (usedExtensions.contains(ClockUtils.STATE_MEMORY_ID)) {
				allMem = true;
				if (noMem) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "State memory must be support by all automata or not at all.");
				}
			} else {
				noMem = true;
				if (allMem) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "State memory must be support by all automata or not at all.");
				}
			}
			
			//create EList of memory cell names (if memory cells are used)
			if (allMem) { 
				EList<String> thisAutoMemCells = new BasicEList<String>();
				for (State s: automaton.getStates()) {
					try {
						tempL = StringListExtension.parse(s.findExtension(ClockUtils.STATE_MEMORY_ID).toString()).getValues(); //memory cells of current state
						if (!tempL.isEmpty()) { //empty if state does not have memory cells
							thisAutoMemCells.addAll(tempL);
						}
					} catch (ParseException pe) {
						return new Status(IStatus.ERROR, PLUGIN_ID, "Unexpected error while trying to parse memory cells of " + s.getName());
					}
				}
				//remove duplicate cells from current automaton
				StringListExtension sle = new StringListExtension(thisAutoMemCells);
				sle.removeDuplicateEntries();
				//add names to global EList (checked later)
				tempL = sle.getValues();
				for (String s: tempL) {
					names.add(s);
				}
			}
			
			//add state names to global name set (checked later), and check for initial states
			tempB = false; //for initial states
			for (State s: automaton.getStates()) {
				if (s.getName() == null || s.getName().equals("")) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "States must have a name.");
				}
				names.add(s.getName());
				if (CA.isStartState(s)) {
					if (tempB == true) {
						return new Status(IStatus.ERROR, PLUGIN_ID, "Each automaton must have exactly one initial location");
					}
					tempB = true;
				}
				if (!tempB) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Each automaton must have exactly one initial location");
				}
			}
			
			//untimed CA transitions must not have empty name set
			if (untimed) {
				for (Transition t: automaton.getTransitions()) {
					if (((StringListExtension) t.findExtension(ClockUtils.TRANSITION_PORT_NAMES_ID)).getValues().isEmpty()) {
						return new Status(IStatus.ERROR, PLUGIN_ID, "Transitions of untimed CA must not have empty name set");
					}
				}	
			}
		
			//TCA transitions with the empty name set must not have a data 
			//constraint on ports (only memory cells)
			//actually this check should not be necessary, it is checked during editing (by the parser) already
			if (timed) {
				for (Transition t: automaton.getTransitions()) {
					if (((StringListExtension) t.findExtension(ClockUtils.TRANSITION_PORT_NAMES_ID)).getValues().isEmpty()) { //found transition with empty port set					
						//get the constraint
						String cons = t.findExtension(ClockUtils.DATA_CONSTRAINT_ID).toString();
						TCADataParser parser = new TCADataParser(cons);
						try {
							tempL = parser.get_port_names();
							if (!tempL.isEmpty()) {
								return new Status(IStatus.ERROR, PLUGIN_ID, "Invisible transitions (with empty port set) must not have a data constraint on ports.");
							}
						} catch (RecognitionException pe) {
							return new Status(IStatus.ERROR, PLUGIN_ID, "Unexpected error while trying to parse " + cons);
						}
					}
				}
			}

			//add clock names to global name set (checked later)
			if (timed) {
				for (String c: ClockUtils.getClocks(automaton)) {
					names.add(c);
				}
			}
		
			//add port names to global port name set (checked later)
			for (String p: ((StringListExtension) automaton.findExtension(ClockUtils.AUTOMATON_PORT_NAMES_ID)).getValues()) {
				portNames.add(p);
			}

		}

		//check range of data values
		//either 'lowerBound..upperBound' or empty (=infinite)
		if (rng.trim().equals("")) {
			infinite = true;
		} else {
			infinite = false;
			if (!rng.contains("..")) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Finite range must be given in the form '0..upperBound'.\nupperBound must be an integer value strictly greater than 0.");
			}
			try {
				lowerBound = Integer.parseInt(rng.substring(0, rng.indexOf(".")));
				if (lowerBound != 0) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Finite range must be given in the form '0..upperBound'.\nLower bounds other than 0 are not (yet) supported.");
				}
			} catch (NumberFormatException nfe) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Finite range must be given in the form '0..upperBound'.\nLower bounds other than 0 are not (yet) supported.");
			}
			try {
				upperBound = Integer.parseInt(rng.substring(rng.lastIndexOf(".")+1, rng.length()));
				if (upperBound < 1) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Finite range must be given in the form '0..upperBound'.\nupperBound must be an integer value strictly greater than 0.");
				}
			} catch (NumberFormatException nfe) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Finite range must be given in the form '0..upperBound'.\nupperBound must be an integer value strictly greater than 0.");
			}
		} 
		
		//check that integer values used in data constraints are within the range (if finite)
		if (!infinite) {
			int min = -1;
			int max = 0;
			for (Automaton automaton: automata) {
				for (Transition t: automaton.getTransitions()) {
					String cons = t.findExtension(ClockUtils.DATA_CONSTRAINT_ID).toString();
					TCADataParser parser = new TCADataParser(cons);
					try {
						tempL = parser.get_minmax_int_values();
						min = Math.min(min,Integer.parseInt(tempL.get(0)));
						max = Math.max(max,Integer.parseInt(tempL.get(1)));
					} catch (RecognitionException re) {
						return new Status(IStatus.ERROR, PLUGIN_ID, "Unexpected error while trying to parse " + cons);
					}
				}
				if ((min < -1) || (max > this.upperBound)) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Integer values in data constraints are outside declared range.");
				}
			}
		}
		
		//check for spaces in all but port names
		for (String s:names) {
			if (s.contains(" ")) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Names must not contain spaces: '" + s + "'");
			}
		}
		
		//check for duplicate names in all but port names
		tempL = new StringListExtension(names).getDuplicateEntries();
		if (!tempL.isEmpty()) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Names (except for port names) must not be shared between automata: " + tempL.toString().replace("[", "").replace("]", ""));
		}
		
		//check for spaces in port names
		for (String p:portNames) {
			if (p.contains(" ")) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Names must not contain spaces: '" + p + "'");
			}
		}
		
		//compare port names with all other names, check for duplicates between the two sets
		StringListExtension sle = new StringListExtension(portNames);
		sle.removeDuplicateEntries();
		tempL = sle.getValues();
		tempL.addAll(names);
		tempL = new StringListExtension(tempL).getDuplicateEntries();
		if (!tempL.isEmpty()) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Names must be unique: " + tempL.toString().replace("[", "").replace("]", ""));
		}

		//check for reserved names CodegenUtils.NO_FLOW_NAME and CodegenUtils.GLOBAL_CLOCK
		if (names.contains(CodegenUtils.NO_FLOW_NAME) || portNames.contains(CodegenUtils.NO_FLOW_NAME)) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "'" + CodegenUtils.NO_FLOW_NAME + "' is a reserved name. ");
		}
		if (names.contains(CodegenUtils.GLOBAL_CLOCK) || portNames.contains(CodegenUtils.GLOBAL_CLOCK)) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "'" + CodegenUtils.GLOBAL_CLOCK + "' is a reserved name. ");
		}

		//check that the unfolding depth is at least 1
		try {
			unfoldingDepth = Integer.valueOf(genModel.getProperty(CodegenUtils.PROPERTY_DEPTH));
		} catch (NumberFormatException nfe) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Not a valid unfolding depth: chose an integer > 0");
		}
		if (!(unfoldingDepth > 0)) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Not a valid unfolding depth: chose an integer > 0");
		}
		
		//check that target language is one of the supported languages, hard code for the moment
		if (!targetlang.equalsIgnoreCase("mathsat") && !targetlang.equalsIgnoreCase("msat")) {
				return new Status(IStatus.ERROR, PLUGIN_ID, "Target language must be one of [mathsat|msat]");
		} else { //to standardise the property contents
			genModel.setProperty("targetlang", mathsat);
		}

		//check that output file name is not empty
		if (genModel.getProperty(CodegenUtils.PROPERTY_FILENAME).isEmpty()) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Output file name cannot be empty");
		}
		
		/* If none of the above occurs, the model is ok */ 
		return Status.OK_STATUS;
	}
}
