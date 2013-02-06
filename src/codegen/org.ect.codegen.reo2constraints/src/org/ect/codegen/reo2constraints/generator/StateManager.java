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
package org.ect.codegen.reo2constraints.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Timer;

public class StateManager {

	public static String NOFLOW = "";
	private final List<GroupedChannels> fifoGroupIds;
	private List<String> timerIds;
	private List<String> fifoIds;
	public final static String RESET = "reset";
	public final static String CMDOFF = "cmdoff";
	public final static String CMDON = "cmdon";
	public final String NEXT = "next";
	public final static String ON = "stateon";
	public final static String TIMEOUT = "timeout";
	public final static String FULL = "stateisfull";
	public final static String DATAVAR = "d";
	public final static String OPENDATA = "[";	
	public final static String CLOSDATA = "]";
	private State currState;
	
	public int getCurrStateIdx() {
		return states.indexOf(currState);
	}

	public List<State> getStates() {
		return states;
	}

	private List<Integer> visitedStates = new ArrayList<Integer>();
	private List<State> states = new ArrayList<State>();
	
		public StateManager(ArrayList<GroupedChannels> fifoGroupIds, ArrayList<String> fifoIds, ArrayList<String> timerIds, State initstate){
		this.fifoGroupIds = fifoGroupIds;
		this.fifoIds = fifoIds;
		this.timerIds = timerIds;
		this.currState = initstate;
		this.states.add(initstate);
	}
	
	public String addNetworkState2Formula(String bf) {
	/*	if (fifoGroupIds != null){//TODO todo fifocontain1 2 3
			for (int i=0; i<fifoGroupIds.size(); i++){
				String var = makeStateExpression(i, false, FULL, FIFO.class);
				String stateval = (currState.getFifosState().charAt(i)=='1')?"true":"false";
				bf = bf.replaceAll("([^t])"+var, "$1"+stateval);
			}
		}TODO*/
		if (fifoIds != null){
			for (int i=0; i<fifoIds.size(); i++){
				String var = makeStateExpression(i, false, FULL, FIFO.class);
				String stateval = (currState.getFifosState().charAt(i)=='1')?"true":"false";
				bf = bf.replaceAll("([^t])"+var, "$1"+stateval);
			}
		}
		if (timerIds != null){
			for (int i=0; i<timerIds.size(); i++){
			//TODO
				//res = res.replaceAll(ConstraintVisitor.$STATEON+i, timerStateOn.get(i)?"true":"false");
				//res = res.replaceAll(ConstraintVisitor.$TIMEOUT+i, timerTimeout.get(i)?"true":"false");
			}
		}
		return bf;
	}

	public static String encode(ArrayList<GroupedChannels> statefulChannels) {
		String tmp = "";
		for (int i=0; i<statefulChannels.size(); i++){
			tmp += statefulChannels.get(i).toString();
		}
		return tmp;
	}
	
	/***
	 * 
	 * @param solution
	 * @return nextstate
	 */
	public State makeNextSate(String solution) {
		if (isNOFlow(solution))
			return null;
		String fifoState = "";
		String timerState = "";
		/*TODO if (fifoGroupIds != null) {
			for (int i = 0; i < fifoGroupIds.size(); i++) {
				String name = makeStateExpression(i, true, FULL, FIFO.class);				
				boolean isfull=getValueOf(name, solution);
				fifoGroupState+=(isfull)?"1":"0";
			}
		}*/
		if (fifoIds != null) {
			for (int i = 0; i < fifoIds.size(); i++) {
				String name = makeStateExpression(i, true, FULL, FIFO.class);
				boolean isfull = getValueOf(name, solution);
				fifoState += (isfull)?"1":"0";
			}
		}
		if (timerIds != null) {
			for (int i = 0; i < timerIds.size(); i++) {
				String name = makeStateExpression(i, true, ON, Timer.class);
				boolean ison = getValueOf(name, solution);
				if (!ison){
					timerState+="0";
				} else{
					name = makeStateExpression(i, true, TIMEOUT, Timer.class);
					boolean isto = getValueOf(name, solution);
					timerState+=(isto)?"2":"1";
				}
				
			}
		}		 //TODO
		return new State("TODO", fifoState, timerState);
	}	

	public boolean isNOFlow(String solution) {
		return (solution.trim().length()==0 || solution.compareToIgnoreCase(NOFLOW)==0);
	}

	private boolean getValueOf(String var, String solution) {
		boolean not = false;
		int idx = solution.indexOf(var);
		if (idx > -1){
			int j=idx-1;
			boolean stop = false;
			while(j>3 && !stop){
				if (solution.charAt(j)=='t' && solution.charAt(j-1)=='o' && 
						solution.charAt(j-2)=='n' && !Character.isLetter(solution.charAt(j-3))){
					stop = not = true;					
				}
				else if (Character.isLetter(solution.charAt(j))){
					stop = true;
				}					
				j--;
			}
		}	
		else {
			not=true;
		}
		return !not;
	}
	public int addNextState(State nxtState){
		int idx = indexOfState(nxtState);
		if (idx<0){
			states.add(nxtState);
			System.out.println("State"+(states.size()-1)+" added : "+nxtState.toString());
			return states.size()-1;
		}
		return idx;
	}

	public int indexOfState(State currState) {
		for (State s : states){
			if (s.toString().compareTo(currState.toString())==0)
				return states.indexOf(s);
		}
		return -1;
	}

	public boolean getAnUnexploredState() {
		if ((currState.getFifosState() == null || currState.getFifosState().trim().length()==0)&&
				(currState.getTimersState() == null || currState.getTimersState().trim().length()==0)){
			boolean visited = !visitedStates.contains(indexOfState(currState));
			visitedStates.add(0);
			return visited;			
		} else {
			int idx = states.size() - 1;
			while (idx>-1 && visitedStates.contains(idx)){
				idx--;
			}
			if (idx>-1){
				currState = states.get(idx);
				visitedStates.add(idx);
			}
			return (idx>-1);
		}			
	}	
	
	//Output format: (next)? state number
	private String makeStateExpression(int idx, boolean next, String state,
			Class type) {
		String res = "";
		if (type == FIFO.class){
			if (state == FULL){
				res+=state+idx;
			}
			else
				return null;
		} else if (type == Timer.class){
			if (state == TIMEOUT || state == CMDOFF || state == CMDON || state == RESET || state==ON){
				res+=state+idx;
			}
			else
				return null;
		}		
		return ((next)?NEXT:"")+res;
	}

	//Format: (next)? state number
	public String makeStateExpression(Channel ch, boolean next, String state) {
		if (!check(ch, next, state))
			return null;
		int no = 0;
		String id = EcoreUtil.getIdentification(ch);
		if (ch instanceof FIFO){
		    no = fifoIds.indexOf(id); //TODO	
		} else if (ch instanceof Timer){
			no = timerIds.indexOf(id);			
		}
		assert (no>-1);
		return ((next)?NEXT:"")+state+no;
	}

	//Format: (next)? state number
		public String makeDataStateExpression(Channel ch, boolean next) {
			int no = 0;
			String id = EcoreUtil.getIdentification(ch);
			if (ch instanceof FIFO){
				no = fifoIds.indexOf(id); //TODO			
			} else if (ch instanceof Timer){//??????????????????????????????????????????????????????????
				no = timerIds.indexOf(id);			
			}
			return getDataVar(ch, ((next)?NEXT:"")+no);
		}


	private String getDataVar(Channel ch, String string) {
		//?????
		// TODO Auto-generated method stub
		return "data"+string;
	}

	private boolean check(Channel ch, boolean next, String state) {
		if (ch instanceof FIFO){
			return (state==FULL);
		} else if (ch instanceof Timer){
			if (next){
				return (state==ON || state==TIMEOUT);
			}
			else
				return (state==RESET || state==CMDOFF || state==CMDON);
		}
		return false;
	}
}
