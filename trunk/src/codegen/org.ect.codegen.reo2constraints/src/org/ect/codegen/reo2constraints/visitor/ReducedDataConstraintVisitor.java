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

import java.util.Collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;

/**
 * @author Behnaz Changizi
 */
public class ReducedDataConstraintVisitor implements IVisitor {
	
	boolean prefix = true;
	private StateManager statemgr;
	
	public void initialize(StateManager statemgr) {
		this.statemgr = statemgr;
		//this.infocntr = info;
	}	

	public boolean isPrefix() {
		return prefix;
	}
	public void setPrefix(boolean prefix) {
		this.prefix = prefix;
	}
	public Map<String, HashSet<String>> getNamesBuffer() {
		return namesBuffer;
	}
	boolean secondTime2Visit = false;
	Map<String, HashSet<String>> namesBuffer = new HashMap<String, HashSet<String>>();
	Set<String> dataGeneratorSources = new HashSet<String>();
	private String equalityClassPrefix = "C";
	private void markAsDataSource(String datagenerator) {
		dataGeneratorSources.add(getBufferedNameIfAny(datagenerator));
	}
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
		return equalityClassPrefix  +(namesBuffer.size()+1);
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
	public ReducedDataConstraintVisitor(StateManager statemgr){
		initialize(statemgr);
	}
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

	//1)(tuple all sink[i]) <-> (equiv source[j]) AND 
	//2)equiv all Source  	
	protected String visitJoin(Node n) {
		String res = "";
		System.out.println("Join: "+n.getName());
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		Set<String> ends = new HashSet<String>();
		if (!secondTime2Visit){
			for (int i=0; i<n.getSourceEnds().size(); i++) {
				ends.add(n.getSourceEnds().get(i).getName());
			}
			HashSet<String> tmp = new HashSet<String>();
			tmp.addAll(ends);
			add2Buffer(tmp);
			add2Buffer(tmp);
		}else{
			for (int i=0; i<n.getSinkEnds().size(); i++) {
				ends.add(getBufferedNameIfAny(n.getSinkEnds().get(i).getName()));
			}
		/*	if (prefix)
				res = Keyword.DEQUIV+makeTuple(ends)+" "+getBufferedNameIfAny(n.getSourceEnds().get(0).getName());
			else
				res = makeTuple(ends)+Keyword.DEQUIV+getBufferedNameIfAny(n.getSourceEnds().get(0).getName());
		*/}		
		System.out.println(res);
		return res;	
	}

	protected String visitRoute(Node n) {//for i for j if i<>j
		assert(secondTime2Visit);
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
        String res="";
        for (int k=0; k<n.getSinkEnds().size(); k++) {
        	if (!prefix)
        		res+=res.length()>0?Keyword.OR:"";
        	else
        		res=(res.length()>0?Keyword.OR:"")+res;
        	for (int i=0; i<n.getSourceEnds().size(); i++) {
        		for (int j=0; j<n.getSourceEnds().size(); j++) {
        			if (!prefix)
        				res+=res.length()>0?Keyword.AND:"";
        			else	
        				res=(res.length()>0?Keyword.AND:"")+res;	
        			if (i==j){
        				if (!prefix)
        					res+="("+getBufferedNameIfAny(n.getSourceEnds().get(j).getName())+Keyword.DEQUIV+
								getBufferedNameIfAny(n.getSinkEnds().get(k).getName())+")";
        				else
        					res="("+Keyword.DEQUIV+getBufferedNameIfAny(n.getSourceEnds().get(j).getName())+" "+
							getBufferedNameIfAny(n.getSinkEnds().get(k).getName())+")"+res;
        			}else{
        				if (!prefix)
        					res+="("+getBufferedNameIfAny(n.getSourceEnds().get(j).getName())+Keyword.ISNULL+")";
        				else
        					res="("+Keyword.ISNULL+getBufferedNameIfAny(n.getSourceEnds().get(j).getName())+")"+res;
        			}            		
        		}
        	}
			for (int l=0; l<n.getSinkEnds().size(); l++) {
				if (k!=l){
					if (!prefix)
						res+=(res.length()>0?Keyword.AND:"")+"("+getBufferedNameIfAny(n.getSinkEnds().get(l).getName())+Keyword.ISNULL+")";
					else
						res=(res.length()>0?Keyword.AND:"")+"("+Keyword.ISNULL+getBufferedNameIfAny(n.getSinkEnds().get(l).getName())+")"+res;
				}
			}
		}		
		System.out.println("Route Node " + n.getName() + " : " + res);
		return res;
	}

	//1)(sink[i]) <-> (sink[i] = source[j]) AND 
	//2)equiv all Source
	protected String visitReplicator(Node n) {
		String res = "";
		if (!secondTime2Visit){
			HashSet<String> sources=new HashSet<String>();
			for (int i=0; i<n.getSourceEnds().size(); i++) {
				sources.add(n.getSourceEnds().get(i).getName());				
			}
			res=add2Buffer(sources);
			System.out.println("Replicate Node " + n.getName() + " : " +sources+" -> "+ res);
		}else{
			String src = getBufferedNameIfAny(n.getSourceEnds().get(0).getName());
			for (int i=0; i<n.getSinkEnds().size(); i++){
				for (int j=i+1; j<n.getSinkEnds().size(); j++){
					if (i!=j){
						if (!prefix)
							res += (res.length()>0?Keyword.AND:"")+"("+Keyword.NOT+"("+getBufferedNameIfAny(n.getSinkEnds().get(i).getName())+Keyword.AND+getBufferedNameIfAny(n.getSinkEnds().get(j).getName())+"))";
						else
							res = (res.length()>0?Keyword.AND:"")+"("+Keyword.AND+"("+Keyword.NOT+"("+getBufferedNameIfAny(n.getSinkEnds().get(i).getName())+") "+getBufferedNameIfAny(n.getSinkEnds().get(j).getName())+"))"+res;
					}
				}		
				if (!prefix)
					res += (res.length()>0?Keyword.AND:"")+"("+getBufferedNameIfAny(n.getSinkEnds().get(i).getName())+Keyword.DEQUIV+src+")";
				else
					res = (res.length()>0?Keyword.AND:"")+"("+" "+Keyword.DEQUIV+src+getBufferedNameIfAny(n.getSinkEnds().get(i).getName())+")"+res;
			}
			System.out.println("Replicate Node " + n.getName() + " : " + res);
		}		
		return (n.getSinkEnds().size()==1 && !secondTime2Visit)?"":res;
	}

	@Override
	public String visit(Connector c) {
		return "";
		// TODO Auto-generated method stub

	}
	@Override
	public String visit(Sync s) {
		System.out.print("Sync " + s.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{s.getSourceEnd().getName(), s.getSinkEnd().getName()});
		String res = add2Buffer(tmp);
		System.out.println(" : "+tmp+" -> "+res);
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
		String res;
		if (!prefix)
			res = "("+getBufferedNameIfAny(l.getSourceEnd().getName())+Keyword.DEQUIV+getBufferedNameIfAny(l.getSinkEnd().getName())+")"+
				Keyword.OR+"("+getBufferedNameIfAny(l.getSinkEnd().getName())+Keyword.DEQUIV+Keyword.NULL+")";
		else
			res = Keyword.OR+"("+Keyword.DEQUIV+getBufferedNameIfAny(l.getSourceEnd().getName())+" "+getBufferedNameIfAny(l.getSinkEnd().getName())+")"+
					"("+Keyword.DEQUIV+Keyword.NULL+getBufferedNameIfAny(l.getSinkEnd().getName())+")";
		System.out.println(res);
		return res;
	}
	
	@Override 
	public String visit(FIFO f) {
		//null = null or sink=buffer and source=null or source=buffer and sink=null)
		String res = "";		
		String src = getBufferedNameIfAny(f.getSourceEnd().getName());
		String snk = getBufferedNameIfAny(f.getSinkEnd().getName());
		if (prefix)
			res = Keyword.OR+Keyword.OR+"("+Keyword.AND+Keyword.AND+Keyword.ISNULL+src+" "+Keyword.ISNULL+snk+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, false)+statemgr.makeDataStateExpression(f, true)+")"+
			  "("+Keyword.AND+Keyword.AND+Keyword.AND+Keyword.ISNULL+src+Keyword.DEQUIV+snk+statemgr.makeDataStateExpression(f, false)+Keyword.ISNULL+statemgr.makeDataStateExpression(f, true)+Keyword.ISNULL+src+")"+
			  "("+Keyword.AND+Keyword.AND+Keyword.AND+Keyword.ISNULL+snk+Keyword.DEQUIV+src+statemgr.makeDataStateExpression(f, true)+Keyword.ISNULL+statemgr.makeDataStateExpression(f, false)+Keyword.ISNULL+src+")";			 
		else	
		    res = "("+src+Keyword.ISNULL+Keyword.AND+snk+Keyword.ISNULL+Keyword.AND+statemgr.makeDataStateExpression(f, false)+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, true)+")"+Keyword.OR+
			  "("+src+Keyword.ISNULL+Keyword.AND+snk+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, false)+Keyword.AND+statemgr.makeDataStateExpression(f, true)+Keyword.ISNULL+Keyword.AND+src+Keyword.ISNULL+")"+Keyword.OR+
			  "("+snk+Keyword.ISNULL+Keyword.AND+src+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, true)+Keyword.AND+statemgr.makeDataStateExpression(f, false)+Keyword.ISNULL+Keyword.AND+src+Keyword.ISNULL+")";			 
		System.out.println("FIFO: "+f.getName()+" : "+src+", "+snk+" -> "+res);
		return res;
	}
	
	@Override
	public String visit(Filter f) {
		System.out.print("Filter :" + f.getName()+" : ");
		String snk=getBufferedNameIfAny(f.getSinkEnd().getName());
		String src=getBufferedNameIfAny(f.getSourceEnd().getName());
		String cond=setVariable(f.getExpression(), src);
		String res;
		if (!prefix)
			res = "(("+cond+")"+Keyword.IMPL+"("+src+Keyword.DEQUIV+snk+"))"+
				Keyword.AND+"(("+Keyword.NOT+"("+cond+"))"+Keyword.IMPL+"("+snk+Keyword.DEQUIV+Keyword.NULL+"))";
		else
			res = Keyword.AND+"("+Keyword.IMPL+"("+cond+")"+"("+Keyword.DEQUIV+src+snk+"))"+
				"("+Keyword.IMPL+"("+Keyword.NOT+"("+cond+"))"+"("+Keyword.DEQUIV+Keyword.NULL+snk+"))";
		System.out.println(snk+", "+src+" : "+res);
		return res;
	}
	private String setVariable(String expression, String val) {
		int close = -1;
		assert(Character.isLetter(expression.charAt(0)));
		assert(expression.startsWith(StateManager.DATAVAR));
		if (expression.contains(StateManager.OPENDATA)){
			close = expression.indexOf(StateManager.CLOSDATA);
			assert(close>0);			
		}
		int op = 0;
		int i = 1;
		while (op==0 && i<expression.length()){
			Set<Character> operators = new HashSet<Character>();
			operators.add('=');
			operators.add('!');
			operators.add('<');
			operators.add('>');
			if (operators.contains(expression.charAt(i)))
				op = i;
			i++;
		}
		assert(op>0 && op>close);
		String toRepl = expression.substring(0, op);
		return expression.replaceAll(toRepl, val);
	}
	@Override
	public String visit(SyncSpout s) {
		System.out.println("SyncSpout" + s.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{s.getChannelEndNameOne(), s.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		markAsDataSource(res);
 		System.out.println(res+" (data generator)");
		return "";
	}
	
	@Override
	public String visit(AsyncDrain a) {
		System.out.println("AsyncDrain" + a.getName());
		String res;
		if (prefix)
			res = Keyword.OR+"(" +Keyword.ISNULL+ getBufferedNameIfAny(a.getChannelEndNameOne()) + ")"+"("+Keyword.ISNULL + getBufferedNameIfAny(a.getChannelEndNameTwo()) + " ) ";
		else 
			res = "(" + getBufferedNameIfAny(a.getChannelEndNameOne()) + Keyword.ISNULL +")"+Keyword.OR+"("+ getBufferedNameIfAny(a.getChannelEndNameTwo()) + Keyword.ISNULL + " ) ";
		System.out.println(res);
		return res;
	}

	@Override
	public String visit(AsyncSpout a) {
		assert(secondTime2Visit);
		System.out.println("AsyncSpout" + a.getName());
		String res;
		if (prefix)
			res = Keyword.OR+"(" +Keyword.ISNULL+ getBufferedNameIfAny(a.getChannelEndNameOne()) + ")"+"("+Keyword.ISNULL + getBufferedNameIfAny(a.getChannelEndNameTwo()) + " ) ";
		else 
			res = "(" + getBufferedNameIfAny(a.getChannelEndNameOne()) + Keyword.ISNULL +")"+Keyword.OR+"("+ getBufferedNameIfAny(a.getChannelEndNameTwo()) + Keyword.ISNULL + " ) ";
		System.out.println(res);
		markAsDataSource(getBufferedNameIfAny(a.getChannelEndNameOne()));
		markAsDataSource(getBufferedNameIfAny(a.getChannelEndNameTwo()));
		System.out.println(res+" (data generator)");
		return res;		
	}
	@Override
	public String visit(Reader r) {		
		return "";
	}
	@Override
	public String visit(Writer w) {
		assert(secondTime2Visit);
		String port = w.getEnd().getName();
		markAsDataSource(port);
		return "";
	}
	@Override
	public String visit(Timer t) {//TODO
		//sink=source=null
		//src=on and timer=0 and timer'=0 and snk=null
		//src=null and timer=nontimeout and timer'=nontimeout and snk=null
		//src=null and timer=nontimeout and timer'=timeout and snk=null
		//src=timeout and timer=tiemout and timer'=0 and snk=timeout
		String res = "";		
		if (prefix);
		else
			res = "("+statemgr.makeStateExpression(t, true, StateManager.TIMEOUT)+Keyword.IMPL+"(("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.TIMEOUT)+")"+Keyword.AND+"("+Keyword.NOT+t.getChannelEndNameOne() +")"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.ON)+"))"+Keyword.AND+
		      " ("+statemgr.makeStateExpression(t, true, StateManager.ON)+Keyword.EQUIV+"((("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.ON)+" )"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+")"+Keyword.OR+"("+statemgr.makeStateExpression(t, false, StateManager.ON)+Keyword.AND+"("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+" ))))"+Keyword.AND+ 
			  " ("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+
		      "(("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.RESET)+")"+Keyword.IMPL+t.getChannelEndNameOne()+")";
		System.out.println("Timer: "+res);
		return res;
	}//TODO paaaaaaaaaaaaaaaaaaaaaak she
	@Override
	public String visit(PrioritySync p) {
		System.out.println("BlockingSync " + p.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{p.getChannelEndNameOne(), p.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSync b) {
		System.out.println("BlockingSync " + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSinkSync b) {
		System.out.println("BlockingSinkSync " + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
	@Override
	public String visit(BlockingSourceSync b) {
		System.out.println("BlockingSourceSync " + b.getName());
		HashSet<String> tmp = new HashSet<String>();
		Collections.addAll(tmp, new String[]{b.getChannelEndNameOne(), b.getChannelEndNameTwo()});
		String res = add2Buffer(tmp);
		System.out.println(res);
		return "";
	}
/*	public String generateReducedConstraints() {
		String res = "";
		for (Primitive p : mod.getAllPrimitives()) {
				if (isSyncronized(p)){
					String tempres = doSwitch(p, this);
					if (tempres != null && tempres.length() > 0) {
						if (res.length() > 0){
							if (prefix)
								res = AND + res;
							else
								res += AND;
						}
						res += "(" + tempres + ")";
					}
				}
		}
		for (Node n : mod.getAllNodes()) {
			if (n.getType() == NodeType.JOIN || n.getType() == NodeType.REPLICATE){
				String tempres = visit(n);
			    if (tempres != null && tempres.length() > 0) {
				    if (res.length() > 0){
						if (prefix)
							res = AND + res;
						else
							res += AND;
					}
				    res += "(" + tempres + ")";
			     }
			}
		}
		for (Primitive p : mod.getAllPrimitives()) {
			if (!isSyncronized(p)){
				String tempres = doSwitch(p, this);
				if (tempres != null && tempres.length() > 0) {
					if (res.length() > 0){
						if (prefix)
							res = AND + res;
						else
							res += AND;
					}
					res += "(" + tempres + ")";
				}
			}
		}
		secondTime2Visit = true;
		for (Node n : mod.getAllNodes()) {
			String tempres = visit(n);
			if (tempres != null && tempres.length() > 0) {
				if (res.length() > 0){
					if (prefix)
						res = AND + res;
					else
						res += AND;
				}
				res += "(" + tempres + ")";
			}			
		}
		System.out.println(this.getClass().getSimpleName() + " Final: " + res);
		System.out.println(namesBuffer.toString());
		return res;
	}*/
}
