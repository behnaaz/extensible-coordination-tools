package org.ect.ea.extensions.portNames.actions;

import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portNames.IntensionalPortNames;
import org.ect.ea.extensions.portNames.providers.IntensionalPortNamesProvider;
import org.ect.ea.extensions.startStates.StartStateExtensionProvider;
/*import org.ect.ea.extensions.constraints.*;
import org.ect.ea.extensions.constraints.algorithms.ConstraintComparator;
import org.ect.ea.extensions.constraints.algorithms.ConstraintSimplify;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
*/
public class ComputeRefinementCommand extends ChangeExtensionsCommand {

	private Automaton automaton;
	private IEAProductCommand command;
	
	
	public ComputeRefinementCommand(Automaton automaton, IGraphicalEditPart editpart) {
		super("Compute Refinement", editpart);
		this.automaton = automaton;
	}

	public ComputeRefinementCommand(IEAProductCommand command, IGraphicalEditPart editpart) {
		super("Compute Refinement", editpart);
		this.command = command;
	}

	
	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("Compute refinement", 3);
		
		if (command!=null) automaton = command.getResult();
		
		System.out.println("compute refinement...");
		EList<Transition> transitions = automaton.getTransitions();
		List<State> states = automaton.getStates();
		List<Transition> unnecessary = new Vector<Transition>();
		int size = transitions.size(); 
		
		for(State a : states){
			if(a.getOutgoing().size()>1){
				List<Transition> tempTransitions = a.getOutgoing();
				for(int i=0;i<tempTransitions.size();i++){
					Transition t1 = tempTransitions.get(i);
					IntensionalPortNames ip1 = (IntensionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					List<String> M1 = tokenize(ip1.getRequests());
					List<String> N1 = tokenize(ip1.getFirings());
					State target1 = t1.getTarget();
					for(int j=0;j<tempTransitions.size();j++){
						if(i!=j){
							Transition t2 = tempTransitions.get(j);
							IntensionalPortNames ip2 = (IntensionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
							List<String> M2 = tokenize(ip2.getRequests());
							List<String> N2 = tokenize(ip2.getFirings());
							State target2 = t2.getTarget();
							if(!target1.equals(target2) && 
									M2.containsAll(M1) && N2.containsAll(N1)){
								HashSet<String> pureN1 = new HashSet<String>(N1);
								HashSet<String> pureN2 = new HashSet<String>(N2);
								pureN1.removeAll(M1);
								pureN2.removeAll(M2);
								if(pureN2.containsAll(pureN1)){
/*									Conjunction g1 = (Conjunction) t1.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
									Conjunction g2 = (Conjunction) t2.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
									if(g1!=null && g2!=null){
										Conjunction g = new Conjunction(g1,g2);
										ConstraintSimplify con = new ConstraintSimplify();
										Constraint FALSE = con.visit(new Literal(false));
										if(ConstraintComparator.isEquivalent(g,(Conjunction)FALSE))	
											unnecessary.add(t1);
									}
									else*/ 
										unnecessary.add(t1);
								}
							}
							else if((N1.isEmpty() || N1.get(0).length()==0) && 
									(!M2.isEmpty() && M2.get(0).length()>0) && 
									N2.containsAll(M2)){
								boolean contains = false;
								for(Transition t3 : transitions){
									if(!t3.equals(t1) && !t3.equals(t2)){
										IntensionalPortNames ip3 = (IntensionalPortNames) t3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
										List<String> N = tokenize(ip3.getFirings());
										if(N.containsAll(M1) && N.containsAll(N2)){
											contains = true;
											break;
										}
									}
								}
								if(contains)	unnecessary.add(t2);
							}
						}
					}
				}
			}
		}
		
		
		
		/*for(int i=0;i<transitions.size();i++){
			Transition a = transitions.get(i);
			IntensionalPortNames ip1 = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			List<String> M1 = ip1.getRequests();
			List<String> N1 = ip1.getFirings();
			State source1 = a.getSource();
			State target1 = a.getTarget();
			for(int j=0;j<transitions.size();j++){
				if(i!=j){
					Transition b = transitions.get(j);
					IntensionalPortNames ip2 = (IntensionalPortNames) b.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					List<String> M2 = ip2.getRequests();
					List<String> N2 = ip2.getFirings();
					State source2 = b.getSource();
					State target2 = b.getTarget();
					if(source1.equals(source2) && !N1.isEmpty() && !N2.isEmpty() && M2.containsAll(M1) && N2.containsAll(N1)){ 
							//&& !M1.containsAll(M2)&& !N1.containsAll(N2)){
						HashSet<String> pureN1 = new HashSet<String>(N1);
						HashSet<String> pureN2 = new HashSet<String>(N2);
						pureN1.removeAll(M1);
						pureN2.removeAll(M2);
						if(!target1.equals(target2) && pureN2.containsAll(pureN1)){
							//Constraint g1 = (Constraint) a.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
							//Constraint g2 = (Constraint) b.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
							//if(computeConjuction(g1, g2)a!=IValidationResult.WRONG)	
							unnecessary.add(a);
						}
						else if(target1.equals(target2) && pureN2.equals(pureN1))	
							if(!isFiltered(a,b,pureN2)){
								unnecessary.add(b);
							}
					}
				}
			}
		}*/
		for(Transition temp : unnecessary)	
			System.out.println("removed Transition : "+temp.getSource()+" , "+temp.getTarget()+" , "
					+temp.findExtension(IntensionalPortNamesProvider.EXTENSION_ID).toString());
		
		for(Transition c : unnecessary){
			c.setSource(null);
			c.setTarget(null);
		}
		
		automaton.getTransitions().removeAll(unnecessary);
		
		System.out.println("After refinement : "+(size-transitions.size())+" removed");
		monitor.worked(1);
		
		removeUnreachableStates(automaton);
		monitor.worked(1);
		
		// Add / remove the edges.
		updateNormalViews();
		monitor.worked(1);
		monitor.done();
	}
	
	public List<String> tokenize(List<String> ports){
		List<String> result = new Vector<String>();
		for(String a : ports){
			StringTokenizer token = new StringTokenizer(a,",");
			while(token.hasMoreTokens()){
				String next = token.nextToken();
				if(!result.contains(next))	result.add(next);
			}
		}
		
		return result;
	}
	
	//Check if losing data occurs because of a filter or not. 
	public boolean isFiltered(Transition a, Transition b, HashSet<String> pureN){
		boolean result = false;
		State commonSource = a.getSource();
		List<Transition> outgoings = commonSource.getOutgoing();
		for(Transition t : outgoings){
			if(!t.equals(a) && !t.equals(b)){
				IntensionalPortNames Iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				List<String> M3 = Iports.getRequests();
				List<String> N3 = Iports.getFirings();
				HashSet<String> pureN3 = new HashSet<String>(N3);
				pureN3.removeAll(M3);
				if(!pureN3.equals(pureN) && pureN3.containsAll(pureN))	result = true;
			}
		}
	
		return result;
	}
	
	protected void removeUnreachableStates(Automaton automaton){
		List<State> removeStates = new Vector<State>();
		List<Transition> removeTransitions = new Vector<Transition>();
		
		for(State a : automaton.getStates()){
			boolean initial = ((BooleanExtension) a.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue();
			if(initial!=true && a.getIncoming().isEmpty()){
				removeStates.add(a);
				removeTransitions.addAll(a.getOutgoing());	
			}
		}
		
		for(Transition b : removeTransitions){
			b.setSource(null);
			b.setTarget(null);
		}
		automaton.getStates().removeAll(removeStates);
		automaton.getTransitions().removeAll(removeTransitions);
		if(!removeStates.isEmpty())	removeUnreachableStates(automaton);
		else return; 
	}
	
}
