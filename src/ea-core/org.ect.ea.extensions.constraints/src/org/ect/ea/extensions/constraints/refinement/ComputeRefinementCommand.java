package org.ect.ea.extensions.constraints.refinement;

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
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.operations.Comparator;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.extensions.portnames.IntentionalPortNames;
import org.ect.ea.extensions.portnames.providers.IntensionalPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;



public class ComputeRefinementCommand extends ChangeExtensionsCommand {

	private Automaton automaton;
	
	public ComputeRefinementCommand(Automaton automaton, IGraphicalEditPart editpart) {
		super("Compute Refinement", editpart);
		this.automaton = automaton;
	}
	
	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("Compute refinement", 3);
		
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
					IntentionalPortNames ip1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					List<String> M1 = tokenize(ip1.getRequests());
					List<String> N1 = tokenize(ip1.getFirings());
					State target1 = t1.getTarget();
					for(int j=0;j<tempTransitions.size();j++){
						if(i!=j){
							Transition t2 = tempTransitions.get(j);
							IntentionalPortNames ip2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
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
									Constraint g1 = (Constraint) t1.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
									Constraint g2 = (Constraint) t2.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
									if(g1!=null && g2!=null){
										ConstraintExtensionProvider provider = new ConstraintExtensionProvider();
										EList<IExtension> list = provider.computeProductExtensions(g1, g2);
										Conjunction g = new Conjunction();
										for(int k=0;k<list.size();k++){
											Constraint b = (Constraint) list.get(k);
											g.getMembers().add(b);
										}
										if(!Comparator.isEquivalent((Constraint)g,new Literal(false)))	
											unnecessary.add(t1);
									}
									else	unnecessary.add(t1);
								}
							}
							else if((N1.isEmpty() || N1.get(0).length()==0) && 
									(!M2.isEmpty() && M2.get(0).length()>0) && 
									N2.containsAll(M2)){
								boolean contains = false;
								for(Transition t3 : transitions){
									if(!t3.equals(t1) && !t3.equals(t2)){
										IntentionalPortNames ip3 = (IntentionalPortNames) t3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
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
				
		for(Transition temp : unnecessary)	
			System.out.println("removed Transition : "+temp.getSource()+" , "+temp.getTarget()+" , "
					+temp.findExtension(IntensionalPortNamesProvider.EXTENSION_ID).toString());
		
		automaton.getTransitions().removeAll(unnecessary);

		for(Transition c : unnecessary){
			c.setSource(null);
			c.setTarget(null);
		}
		
		
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
