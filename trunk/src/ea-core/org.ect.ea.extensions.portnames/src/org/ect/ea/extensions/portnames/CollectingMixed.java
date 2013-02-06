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
package org.ect.ea.extensions.portnames;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.ExtensionElement;

public class CollectingMixed extends ExtensionElement{
	
	protected List<Transition> result = new Vector<Transition>();
	protected boolean collectable = false;
	protected Transition transition = new Transition();
	
	public CollectingMixed(Transition t, List<String> newMixed){
		super();
		collectingMixed(t, newMixed);
	}
		
	public List<Transition> getCollected() 
	{
		return result;
	}

	public boolean getCollectable(){
		return collectable;
	}
	
	public void setCollectable(boolean value){
		collectable = value;
	}
	
	public void collectingMixed(Transition t, List<String> newMixed)
	{
		// Find consecutive transitions with relevant mixed nodes
		Stack<Transition> temp = new Stack<Transition>();
		Stack<Transition> collected = new Stack<Transition>();
		String id = IntensionalPortNamesProvider.EXTENSION_ID;
		boolean readyforCollection = false;
		for(String a : ((IntensionalPortNames)t.findExtension(id)).getRequests()){
			if(newMixed.contains(a)){
				readyforCollection = true;
				break;
			}
		}
		
		if(readyforCollection)	temp.push(t);
		while(!temp.isEmpty()){
			Transition concern = temp.pop();  
			IntensionalPortNames ports = (IntensionalPortNames)concern.findExtension(id);
			
			//if collecting processing is done
			if(!ports.getFirings().isEmpty() && hasCommon(ports.getRequests(),ports.getFirings(),newMixed)){//mark if it is already checked or not
//				System.out.println("firings: "+ports.getFirings().toString());
//				System.out.println("requests:"+ports.getRequests().toString());
//				System.out.println("mixed: "+newMixed.toString());
				
				setCollectable(true);
			}
			else if(!ports.getFirings().isEmpty() && !hasCommon(ports.getRequests(), ports.getFirings(), newMixed)){
				result.remove(concern);	
			}
				
			else{
				State tempState = concern.getTarget(); 
				EList<Transition> outgoings = tempState.getOutgoing();
				Stack<Transition> outstack = new Stack<Transition>();
				for(Transition a : outgoings){
					if(!collected.contains(a) && outstack.search(a)==-1 && !((IntensionalPortNames)a.findExtension(id)).getMcollections()){
						outstack.push(a);
					} 
				}
				while(!outstack.isEmpty()){
					Transition b = outstack.pop();
					if(!b.getSource().equals(b.getTarget())){
						IntensionalPortNames checkIports = ((IntensionalPortNames) b.findExtension(id));
						
						boolean Continue = false;
						boolean Last = false;
						boolean addable = false;
						if(!checkIports.getFirings().isEmpty() && hasCommon(checkIports.getFirings(), newMixed))	
							Last = true;
						else if(checkIports.getFirings().isEmpty() && !checkIports.getRequests().isEmpty() && hasCommon(checkIports.getRequests(),newMixed))	
							Continue = true;
											
						if(Last || Continue){
							Transition tempcollect = new Transition(); 
							tempcollect.setSource(concern.getSource()); 
							tempcollect.setTarget(b.getTarget()); 
							
							//To collect intentional ports
							IntensionalPortNames Iports = new IntensionalPortNames();
							Iports.setOwner(tempcollect); 
							Iports.setId(id);
							Iports.setMcollections(true);
							Iports.getRequests().addAll(((IntensionalPortNames) concern.findExtension(id)).getRequests());
							Iports.getRequests().addAll(((IntensionalPortNames) b.findExtension(id)).getRequests());
							if(Last){
								Iports.getFirings().addAll(((IntensionalPortNames) concern.findExtension(id)).getFirings());
								Iports.getFirings().addAll(((IntensionalPortNames) b.findExtension(id)).getFirings());
								
								if(hasCommon(Iports.getRequests(), Iports.getFirings(), newMixed)){
									addable = true;
									
									DelayInformation DI = new DelayInformation();
									DI.setOwner(tempcollect);
									DI.setId(DelayInfoProvider.EXTENSION_ID);
									
									List<DelayElement> DE2 = (List<DelayElement>) EcoreUtil.copyAll(((DelayInformation)b.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements());
									DI.getElements().addAll(DE2);
								}
							}
							
							/*if(Last){
								//To collect delay elements
								DelayInformation DI = new DelayInformation();
								DI.setOwner(tempcollect);
								DI.setId(DelayInfoProvider.EXTENSION_ID);
								
								List<DelayElement> DE2 = (List<DelayElement>) EcoreUtil.copyAll(((DelayInformation)b.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements());
								DI.getElements().addAll(DE2);
							}*/
													
							//To check the collected one already exists. If it does, remove it.
							if(!search(collected,tempcollect)){
								/*System.out.println("Redundant");
							}
							if(collected.search(tempcollect)==-1){*/// && !existingTransition(t,tempcollect)){
								collected.push(tempcollect);
								if(addable){
									/*IntensionalPortNames possibleCollecting = (IntensionalPortNames) tempcollect.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
									if(hasCommon(possibleCollecting.getRequests(), possibleCollecting.getFirings(), newMixed)){
										DelayInformation lastDI = (DelayInformation) tempcollect.findExtension(DelayInfoProvider.EXTENSION_ID);
										List<DelayElement> remove = new Vector<DelayElement>();
										for(DelayElement lastDE : lastDI.getElements()){
											if(lastDE.getDelay()==-1)	remove.add(lastDE);
										}
										lastDI.getElements().removeAll(remove);
									}*/
									result.add(tempcollect); 
								}
							}
						}
					}
				}
				if(temp.isEmpty()){
					while(!collected.isEmpty())	temp.push(collected.pop()); 
				}
			}		
		}
	}
	
	public boolean search(Stack<Transition> list, Transition t){
		boolean includes = false;
		for(int i=0;i<list.size();i++){
			Transition a = list.get(i);
			
			if(a.getSource()==t.getSource() && 
					a.getTarget()==t.getTarget()){
				IntensionalPortNames ip1 = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				IntensionalPortNames ip2 = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				
				if(ip1.getRequests().containsAll(ip2.getRequests()) &&
						ip2.getRequests().containsAll(ip1.getRequests())){
					if(ip1.getFirings()!=null && ip2.getFirings()!=null && ip1.getFirings().containsAll(ip2.getFirings()) &&
							ip2.getFirings().containsAll(ip1.getRequests())){
						includes = true;
					}
					else if((ip1.getFirings()==null || ip2.getFirings().isEmpty()) && (ip2.getFirings()==null || ip2.getFirings().isEmpty())){
						includes = true;
					}
					//if(includes)	System.out.println("Redundant in independent search function");
				}
			}
		}
		
		return includes;
	}
		
	public boolean existingTransition(Transition t1, Transition t2){
	    boolean result = false;
	    boolean commonST = false;
	    boolean M1subM2 = true;
	    boolean M2subM1 = true;
	    boolean N1subN2 = true;
	    boolean N2subN1 =true;
	    String id = IntensionalPortNamesProvider.EXTENSION_ID;
	    EList<Transition> transitions = t1.getAutomaton().getTransitions();
	    IntensionalPortNames check = (IntensionalPortNames) t2.findExtension(id);
	    State source = t2.getSource();
	    State target = t2.getTarget();
	    EList<String> M2 = check.getRequests();
	    EList<String> N2 = check.getFirings();
	    for(Transition a : transitions){
	         if(a.getSource()==source && a.getTarget() == target){
	             commonST=true;
	             IntensionalPortNames temp = (IntensionalPortNames) a.findExtension(id);
	             EList<String> M1 = temp.getRequests();
	             EList<String> N1 = temp.getFirings();
				 if(!M2.containsAll(M1))		M1subM2 = false;
				 if(!M1.containsAll(M2))		M2subM1 = false;
				 if(!N2.containsAll(N1))		N1subN2 = false;
				 if(!N1.containsAll(N2))		N2subN1 = false;
	        }
	        result = commonST && M1subM2 && M2subM1 && N1subN2 && N2subN1;
	        if(result==true){
	        	System.out.println("Existing the same transition");
	        	break;
	        }
	    }
	    
	    return result;
	}
	
	public boolean hasCommon(EList<String> list, List<String> newMixed){
		boolean common = false;
		for(String a : list){
			if(newMixed.contains(a)){
				common = true;
				break;
			}
		}
		
		return common;
	}
	
	public boolean hasCommon(EList<String> requests ,EList<String> firings, List<String> newMixed)
	{
		boolean common = false; 
		
		if(!firings.isEmpty()){
			HashSet<String> Rcommon = new HashSet<String> (requests); 
			HashSet<String> Fcommon = new HashSet<String> (firings); 
			
			Rcommon.retainAll(newMixed);
			Fcommon.retainAll(newMixed);
			
			if(Rcommon.equals(Fcommon))	common = true;
		}
		return common;
	}
	
	
	
	/*public boolean alreadyExists(Stack<Transition> collection, Transition element)
	{
		boolean exists = false; 
		Stack<Transition> temp = new Stack<Transition>();
		Stack<Transition> compare = new Stack<Transition>();
		while(!collection.isEmpty()){
			Transition a = new Transition();
			a=collection.pop();
			temp.push(a);
			compare.push(a);
		}
		while(!temp.isEmpty()){
			collection.push(temp.pop());
		}
		
		while(!compare.isEmpty()){
			Transition b = compare.pop();
			if(b.getSource()==element.getSource() && b.getTarget()==element.getTarget()){
				boolean sameST = true;
				String id = IntensionalPortNamesProvider.EXTENSION_ID; 
				IntensionalPortNames ports1 = (IntensionalPortNames) b.findExtension(id);
				IntensionalPortNames ports2 = (IntensionalPortNames) element.findExtension(id);
				HashSet<String> M1 = new HashSet<String>(ports1.getRequests());
				HashSet<String> M2 = new HashSet<String>(ports2.getRequests());
				HashSet<String> N1 = new HashSet<String>(ports1.getFirings());
				HashSet<String> N2 = new HashSet<String>(ports2.getFirings());
				boolean M1subM2 = true;
				boolean M2subM1 = true;
				boolean N1subN2 = true;
				boolean N2subN1 = true;
				
				//M1 \subseteq M2
				if(!M2.containsAll(M1))		M1subM2 = false;
				//M2 \subseteq M1
				if(!M1.containsAll(M2))		M2subM1 = false;
				//N1 \subseteq N2
				if(!N2.containsAll(N1))		N1subN2 = false;
				//N2 \subseteq N1 
				if(!N1.containsAll(N2))		N2subN1 = false;
				
				exists = (sameST && M1subM2 && M2subM1 && N1subN2 && N2subN1);
			}
		}		
		return exists;
	}*/
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.INTENSIONAL_PORT_NAMES;
	}
}
