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
package org.ect.codegen.reo2constraints.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;
/**
 * @author Behnaz Changizi
 *
 */
public class ReducedSyncVisitor implements IVisitor {
	String equalityClassPrefix = "t";
	private StateManager statemgr;
	private Preprocessing info;

	public ReducedSyncVisitor(StateManager statemgr, Preprocessing info){
		this.statemgr = statemgr;
		this.info = info;
	}	
	
	private String safeName(PrimitiveEnd end){
		String tmp = end.getName();
		if (tmp==null || tmp.trim().length()==0)
    		tmp = this.info.getNotNullName(end);
		return tmp;
	}

	public String getEqualityClassPrefix() {
		return equalityClassPrefix;
	}

	public void setEqualityClassPrefix(String equalityClassPrefix) {
		this.equalityClassPrefix = equalityClassPrefix;
	}

	public Map<String, HashSet<String>> getNamesBuffer() {
		return namesBuffer;
	}
	boolean secondTime2Visit = false;
	Map<String, HashSet<String>> namesBuffer = new HashMap<String, HashSet<String>>();
	private List<Integer> visitedFifos = new ArrayList<Integer>();
		
	private String add2Buffer(HashSet<String> names2add) {
		String loc = "";
		for (String key: namesBuffer.keySet()){
			Set<String> names = namesBuffer.get(key); 
			for (String s : names2add){
				if (names.contains(s)){
					assert(loc.length()==0 || loc==key);
					//entry to add is found
					loc = key;
				}
			}
		}
		if (loc.length()==0){
			loc = makeNewKey();
		}
		//to keep the old entry
		else {
			Set<String> toCombine = namesBuffer.get(loc);
			names2add.addAll(toCombine);
		}
		namesBuffer.put(loc, names2add);
		return loc;
	}

	private String makeNewKey() {
		return equalityClassPrefix +(namesBuffer.size()+1);
	}

	private boolean isSyncronized(Primitive p) {
	return (p instanceof Sync)||(p instanceof SyncDrain)||(p instanceof SyncSpout)||(p instanceof Transform)||(p instanceof PrioritySync)||
			(p instanceof BlockingSinkSync)||(p instanceof BlockingSourceSync)||(p instanceof BlockingSync);
}

private String getBufferedNameIfAny(String name) {
	for (String key:namesBuffer.keySet()){
		if (namesBuffer.get(key).contains(name))
			return key;
	}
	return name;
}
/*
	public String generateReducedConstraints() {
		String res = "";
		for (Primitive p : mod.getAllPrimitives()) {
				if (isSyncronized(p)){
					String tempres = doSwitch(p, this);
					if (tempres != null && tempres.length() > 0) {
						if (res.length() > 0)
							res += AND;
						res += "(" + tempres + ")";
					}
				}
		}
		for (Node n : mod.getAllNodes()) {
			if (n.getType() == NodeType.JOIN || n.getType() == NodeType.REPLICATE){
				String tempres = visit(n);
			    if (tempres != null && tempres.length() > 0) {
				    if (res.length() > 0)
					    res += AND;
				    res += "(" + tempres + ")";
			     }
			}
		}
		for (Primitive p : mod.getAllPrimitives()) {
			if (!isSyncronized(p)){
				String tempres = doSwitch(p, this);
				if (tempres != null && tempres.length() > 0) {
					if (res.length() > 0)
						res += AND;
					res += "(" + tempres + ")";
				}
			}
		}
		secondTime2Visit = true;
		for (Node n : mod.getAllNodes()) {
			if (n.getType() == NodeType.REPLICATE || n.getType() == NodeType.ROUTE){			
				String tempres = visit(n);
				if (tempres != null && tempres.length() > 0) {
					if (res.length() > 0)
						res += AND;
					res += "(" + tempres + ")";
				}
			}
		}
		System.out.println(this.getClass().getSimpleName() + " Final: " + res);
		System.out.println(namesBuffer.toString());
		return res;
	}
*/
	@Override
	public String visit(Node n) {
		if (n.getSourceEnds().size()==0 || n.getSinkEnds().size()==0)
			return "";
		if (n.getType() == NodeType.REPLICATE)
			return visitReplicator(n);
		else if (n.getType() == NodeType.ROUTE)
			return visitRoute(n);
		else 	if (n.getType() == NodeType.JOIN)
			return visitJoin(n);
		return "";
	}

	//returns "" bcoz it only adds a reduced equality relation in buffer
		protected String visitJoin(Node n) {
			System.out.println("Join: "+n.getName());
			assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
			assert(!secondTime2Visit);
			List<String> ends = new ArrayList<String>();
			for (int i=0; i<n.getSourceEnds().size(); i++) {
				ends.add(safeName(n.getSourceEnds().get(i)));
			}	
			for (int i=0; i<n.getSinkEnds().size(); i++) {
				ends.add(safeName(n.getSinkEnds().get(i)));
			}	
			HashSet<String> tmp = new HashSet<String>();
			tmp.addAll(ends);
			String res = add2Buffer(tmp);
			add2Buffer(tmp);
			System.out.println(res);
			return "";	
		}

//1)(or all sink[i]) <-> (or all source[j]) AND 
//2)nand all source AND
//3)nand all sinks
	protected String visitRoute(Node n) {
		assert(secondTime2Visit);
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
        String res="";
        String tmp1="";
        String tmp2="";
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			tmp1+=(tmp1.length()>0?Keyword.OR:"")+getBufferedNameIfAny(safeName(n.getSourceEnds().get(i)));
			for (int j=i+1; j<n.getSourceEnds().size(); j++) {
				if (i!=j){
					res+=(res.length()>0?Keyword.AND:"")+"("+Keyword.NOT+"("+getBufferedNameIfAny(safeName(n.getSourceEnds().get(i)))+Keyword.AND+
							getBufferedNameIfAny(safeName(n.getSourceEnds().get(j)))+"))";
				}
			}
		}
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			tmp2+=(tmp2.length()>0?Keyword.OR:"")+getBufferedNameIfAny(safeName(n.getSinkEnds().get(i)));
			for (int j=i+1; j<n.getSinkEnds().size(); j++) {
				if (i!=j){
					res+=(res.length()>0?Keyword.AND:"")+"("+Keyword.NOT+"("+getBufferedNameIfAny(safeName(n.getSinkEnds().get(i)))+Keyword.AND+
							getBufferedNameIfAny(safeName(n.getSinkEnds().get(j)))+"))";
				}
			}
		}		
		res+=Keyword.AND+"(("+tmp1+")"+Keyword.EQUIV+"("+tmp2+"))";
		System.out.println("Route Node " + n.getName() + " : " + res);
		return res;
	}
	
	protected String visitReplicator(Node n) {
		String res = "";
		if (!secondTime2Visit){
			List<String> sources=new ArrayList<String>();
			for (int i=0; i<n.getSourceEnds().size(); i++) {
				sources.add(safeName(n.getSourceEnds().get(i)));				
			}
			HashSet<String> tmp = new HashSet<String>();
			tmp.addAll(sources);
			res = add2Buffer(tmp);
			add2Buffer(tmp);
		}else{
			String src = getBufferedNameIfAny(safeName(n.getSourceEnds().get(0)));
			for (int i=0; i<n.getSinkEnds().size(); i++){
				for (int j=i+1; j<n.getSinkEnds().size(); j++){
					if (i!=j){
						res += (res.length()>0?Keyword.AND:"")+"("+Keyword.NOT+"("+getBufferedNameIfAny(safeName(n.getSinkEnds().get(i)))+Keyword.AND+getBufferedNameIfAny(safeName(n.getSinkEnds().get(j)))+"))";
					}
				}		
				res += (res.length()>0?Keyword.AND:"")+"("+getBufferedNameIfAny(safeName(n.getSinkEnds().get(i)))+Keyword.EQUIV+src+")";
			}
		}
		System.out.println("Replicate Node " + n.getName() + " : " + res);
		return (n.getSinkEnds().size()==1 && !secondTime2Visit)?"":res;
	}

	@Override
	public String visit(Connector c) {
		return "";
		// TODO Auto-generated method stub

	}
	@Override
	public String visit(Sync s) {
		System.out.println("Sync " + s.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{s.getChannelEndNameOne(), s.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}


	@Override
	public String visit(SyncDrain d) {
		System.out.println("SyncDrain " + d.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{d.getChannelEndNameOne(), d.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}

	@Override
	public String visit(LossySync l) {
		System.out.println("LossySync " + l.getName());
		String res = getBufferedNameIfAny(l.getChannelEndNameTwo())+Keyword.IMPL+getBufferedNameIfAny(l.getChannelEndNameOne());
		System.out.println(res);
		return res;
	}
	@Override
	public String visit(FIFO f) {
		String res = "";		
		String snk = safeName(f.getSinkEnd());//null;
		String src = safeName(f.getSourceEnd());//null;
		//making sure it has not been visited coz of the member of its group 
		int idx = info.getFIFOIds().indexOf(EcoreUtil.getIdentification(f));
				//info.indexOf(info.getFIFOIds(), EcoreUtil.getIdentification(f));
/*		if (idx > -1 && !visitedFifos.contains(idx)){
			GroupedChannels group = info.getFIFOIds().get(idx);
			assert(group.getChannelIds().size()>0);
			src = safeName(group.getSourceEnd().getName();
			snk = safeName(group.getSinkEnd().getName();
		} *///not in a group
//		 else
			 if (idx < 0){
			 System.err.println("Error: "+EcoreUtil.getIdentification(f)+" is not in the groupedFIFOLists!!!!");
		}
		//empty -> !a
		res = "("+Keyword.NOT+statemgr.makeStateExpression(f, false, StateManager.FULL)+")"+Keyword.IMPL+"("+Keyword.NOT+getBufferedNameIfAny(snk)+"))"+Keyword.AND+
				//full->!b
				"("+statemgr.makeStateExpression(f, false, StateManager.FULL)+Keyword.IMPL+"("+Keyword.NOT+getBufferedNameIfAny(src)+"))"+Keyword.AND+
				//a->next full   
				"("+getBufferedNameIfAny(src)+Keyword.IMPL+statemgr.makeStateExpression(f, true, StateManager.FULL)+")"+Keyword.AND+
				//b->next empty
				"("+getBufferedNameIfAny(snk)+Keyword.IMPL+"("+Keyword.NOT+statemgr.makeStateExpression(f, true, StateManager.FULL)+"))"+Keyword.AND+
				//!(a or b)->state = nextstate
				"(("+Keyword.NOT+"("+getBufferedNameIfAny(src)+Keyword.OR+getBufferedNameIfAny(snk)+"))"+Keyword.EQUIV+"("+statemgr.makeStateExpression(f, false, StateManager.FULL)+Keyword.EQUIV+statemgr.makeStateExpression(f, true, StateManager.FULL)+")"; 
		
		visitedFifos.add(idx);
		System.out.println("FIFO "+f.getName()+res);
		return res; 
	}
	
	@Override
	public String visit(Filter f) {
		System.out.println("Filter" + f.getName());
		String res = getBufferedNameIfAny(safeName(f.getSinkEnd())) + Keyword.EQUIV+"(" + info.getExpressionId(f) + Keyword.AND + getBufferedNameIfAny(safeName(f.getSourceEnd())) + ")";
		System.out.println("Filter "+res);
		return res;
	}
	@Override
	public String visit(SyncSpout s) {
		System.out.println("SyncSpout" + s.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{s.getChannelEndNameOne(), s.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(AsyncDrain a) {
		System.out.println("AsyncDrain" + a.getName());
		String res = Keyword.NOT+"(" + getBufferedNameIfAny(a.getChannelEndNameOne()) + Keyword.AND + getBufferedNameIfAny(a.getChannelEndNameTwo()) + " ) ";
		System.out.println(res);
		return res;
	}
	@Override
	public String visit(AsyncSpout a) {
		System.out.println("AsyncSpout" + a.getName());
		String res = Keyword.NOT+"(" + getBufferedNameIfAny(a.getChannelEndNameOne()) + Keyword.AND + getBufferedNameIfAny(a.getChannelEndNameTwo()) + " ) ";
		System.out.println(res);
		return res;
	}

	@Override
	public String visit(Reader r) {
		return "";
	}
	@Override
	public String visit(Writer w) {
		return "";
	}
	@Override
	public String visit(Timer t) {
		String res = "";		
		res = "("+statemgr.makeStateExpression(t, true, StateManager.TIMEOUT)+Keyword.IMPL+"(("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.TIMEOUT)+")"+Keyword.AND+"("+Keyword.NOT+getBufferedNameIfAny(t.getChannelEndNameOne()) +")"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.ON)+"))"+Keyword.AND+
		      " ("+statemgr.makeStateExpression(t, true, StateManager.ON)+Keyword.EQUIV+"((("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.ON)+" )"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+")"+Keyword.OR+"("+statemgr.makeStateExpression(t, false, StateManager.ON)+Keyword.AND+"("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+" ))))"+Keyword.AND+ 
			  " ("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+
		      "(("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.RESET)+")"+Keyword.IMPL+getBufferedNameIfAny(t.getChannelEndNameOne())+")";
		System.out.println("Timer: "+res);
		return res;
	}
	//TODO Transform
	@Override
	public String visit(PrioritySync p) {
		System.out.println("PrioritySync" + p.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{p.getChannelEndNameOne(), p.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSync b) {
		System.out.println("BlockingSync" + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSinkSync b) {
		System.out.println("BlockingSync" + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSourceSync b) {
		System.out.println("BlockingSync" + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
}
