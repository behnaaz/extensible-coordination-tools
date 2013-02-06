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
import java.util.List;
import java.util.Set;

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
public class DataConstraintVisitor implements IVisitor {

	private StateManager statemgr;
	private Preprocessing info;
	//check nameha ro ham ers bari konam TODO
	public DataConstraintVisitor(StateManager statemgr, Preprocessing info){
		this.statemgr = statemgr;
		this.info = info;
	}

	private String safeName(PrimitiveEnd end){
		String tmp = end.getName();
		if (tmp==null || tmp.trim().length()==0)
			tmp = info.getNotNullName(end);
		return tmp;
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
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		String equivSources = "";
		SourceEnd cend = null;
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			cend = n.getSourceEnds().get(i);
			//compute (2) in equivSources
			for (int j=i+1; j<n.getSourceEnds().size(); j++){
				equivSources += (equivSources.compareTo("")!=0)?Keyword.AND:""; 
				equivSources += "("+getDataDependentName(cend) + Keyword.DEQUIV + getDataDependentName(n.getSourceEnds().get(j))+")";
			}
		}

		String equivSinks = "";
		//make tupl of all sink sources
		SinkEnd kend = null;
		List<PrimitiveEnd> sinks = new ArrayList<PrimitiveEnd>();
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			kend = n.getSinkEnds().get(i);
			sinks.add(kend);
		}
		//compute first part of (1) in equivs
		String sinkseqsourcej = "("+makeTuple(sinks) + Keyword.DEQUIV + getDataDependentName(kend)+")";

		String res = sinkseqsourcej + Keyword.AND + "("+equivSources+")";
		res += (equivSources.compareTo("")!=0)?(Keyword.AND+equivSources):"";
		res += (equivSinks.compareTo("")!=0)?(Keyword.AND+equivSinks):"";
		System.out.println("Join Node " + n.getName() + " : " + res);
		return res;	
	}
	//1)(sink[i] and source[j]) <-> (datasink[i] equiv datasource[j])
	protected String visitRoute(Node n) {
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		String res = "";
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			SourceEnd cend = n.getSourceEnds().get(i);
			for (int j=0; j<n.getSinkEnds().size(); j++) {
				SinkEnd kend = n.getSinkEnds().get(j);
				res += ((res.length()>0)?Keyword.AND:"")+"(("+safeName(cend)+Keyword.AND+safeName(kend)+")"+Keyword.IMPL+"("+getDataDependentName(cend)+Keyword.DEQUIV+getDataDependentName(cend)+"))";
			}
		}
		System.out.println("Route Node " + n.getName() + " : " + res);
		return res;
	}


	//1)(sink[i]) <-> (sink[i] = source[j]) AND 
	//2)equiv all Source
	protected String visitReplicator(Node n) {
		String andAllSources = "";
		String equivSources = "";
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		SourceEnd cend= null;
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			cend = n.getSourceEnds().get(i);
			if (andAllSources.compareTo("")!=0){
				andAllSources+=Keyword.AND;
			}
			andAllSources += safeName(cend);
			//compute (2) in equivSources
			for (int j=i+1; j<n.getSourceEnds().size(); j++){
				equivSources += (equivSources.compareTo("")!=0)?Keyword.AND:""; 
				equivSources += "("+getDataDependentName(cend) + Keyword.DEQUIV + getDataDependentName(n.getSourceEnds().get(j))+")";
			}
		}

		//compute first part of (1) in orAllSinks
		String orAllSinks = "";
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			SinkEnd kend = n.getSinkEnds().get(i);
			if (orAllSinks.compareTo("")!=0){
				orAllSinks +=Keyword.AND;
			}
			orAllSinks += "(("+safeName(kend)+")"+Keyword.IMPL+"("+getDataDependentName(cend)+Keyword.DEQUIV+getDataDependentName(cend)+"))";
		}
		String res = equivSources+Keyword.AND+orAllSinks;
		System.out.println("Replicate Node " + n.getName() + " : " + res);
		return res;
	}

	@Override
	public String visit(Connector c) {
		return "";
		// TODO Auto-generated method stub

	}
	@Override
	public String visit(Sync s) {
		System.out.println("Sync " + s.getName());
		String res = "("+getDataDependentName(s.getChannelEndOne())+Keyword.DEQUIV+getDataDependentName(s.getChannelEndTwo())+")";
		System.out.println(res);
		return res;
	}

	@Override
	public String visit(SyncDrain d) {
		System.out.println("SyncDrain " + d.getName());
		String res = "("+getDataDependentName(d.getChannelEndOne())+Keyword.EQUIV+getDataDependentName(d.getChannelEndTwo())+")";
		System.out.println(res);
		return res;
	}

	@Override
	public String visit(LossySync l) {
		String res = "("+l.getChannelEndNameTwo()+Keyword.IMPL+"("+getDataDependentName(l.getChannelEndOne())+Keyword.DEQUIV+getDataDependentName(l.getChannelEndTwo())+"))"+
				Keyword.AND+"("+Keyword.NOT+l.getChannelEndNameTwo()+Keyword.IMPL+"("+getDataDependentName(l.getChannelEndTwo())+Keyword.DEQUIV+Keyword.NULL+"))";
		System.out.println(res);
		return res;
	}
	//coments todo
	//linea 1: empty -> !bANDa->state'=full AND!a->state'=state
	//linea 2: full  -> !aANDb->state'=emptyAND!b->state'=state
	//linea 3:!aAND!b -> state'=state
	@Override 
	public String visit(FIFO f) {
		String res = "";		
		//linea 1
		res = "("+f.getChannelEndNameOne()+Keyword.EQUIV+"("+getDataDependentName(f.getChannelEndOne())+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, true)+"))"+Keyword.AND+
				"(("+Keyword.NOT+f.getChannelEndNameOne()+")"+Keyword.EQUIV+"("+getDataDependentName(f.getChannelEndOne())+Keyword.DEQUIV+Keyword.NULL+"))"+Keyword.AND+
				"("+f.getChannelEndNameTwo()+Keyword.EQUIV+"("+getDataDependentName(f.getChannelEndTwo())+Keyword.DEQUIV+statemgr.makeDataStateExpression(f, false)+"))"+Keyword.AND+
				"(("+Keyword.NOT+f.getChannelEndNameTwo()+")"+Keyword.EQUIV+"("+getDataDependentName(f.getChannelEndTwo())+Keyword.DEQUIV+Keyword.NULL+"))"+Keyword.AND+
				"((("+Keyword.NOT+f.getChannelEndNameTwo()+")"+Keyword.AND+"("+Keyword.NOT+f.getChannelEndNameTwo()+"))"+Keyword.EQUIV+"("+statemgr.makeDataStateExpression(f, false)+Keyword.DEQUIV+
				statemgr.makeDataStateExpression(f, true)+"))";
		;
		System.out.println("FIFO: "+res);
		return res;
	}

	@Override
	public String visit(Filter f) {
		System.out.println("Filter" + f.getName());
		String res = "("+safeName(f.getSinkEnd()) + Keyword.EQUIV+"(" + getDataDependentName(f.getSinkEnd()) + Keyword.DEQUIV + getDataDependentName(f.getSourceEnd()) + "))"+Keyword.AND+
				"(("+Keyword.NOT+safeName(f.getSinkEnd())+")"+Keyword.EQUIV+"("+getDataDependentName(f.getSinkEnd())+Keyword.DEQUIV+Keyword.NULL+"))";
		System.out.println("Filter "+res);
		return res;
	}
	@Override
	public String visit(SyncSpout s) {
		String res = "( "+s.getChannelEndOne()+Keyword.DEQUIV+s.getChannelEndTwo()+" ) ";
		System.out.println(res);
		return res;
	}
	@Override
	public String visit(AsyncDrain a) {
		System.out.println("AsyncDrain" + a.getName());
		String res = "(("+Keyword.NOT+a.getChannelEndNameOne()+")"+Keyword.IMPL+"("+getDataDependentName(a.getChannelEndOne())+Keyword.DEQUIV+Keyword.NULL+"))" + Keyword.AND 
				+"(("+Keyword.NOT+a.getChannelEndNameTwo()+")"+Keyword.IMPL+"("+getDataDependentName(a.getChannelEndTwo())+Keyword.DEQUIV+Keyword.NULL+"))";
		System.out.println(res);
		return res;
	}
	@Override
	public String visit(AsyncSpout a) {
		System.out.println("AsyncSpout" + a.getName());
		String res = "(("+Keyword.NOT+a.getChannelEndNameOne()+")"+Keyword.IMPL+"("+getDataDependentName(a.getChannelEndOne())+Keyword.DEQUIV+Keyword.NULL+"))" + Keyword.AND 
				+"(("+Keyword.NOT+a.getChannelEndNameTwo()+")"+Keyword.IMPL+"("+getDataDependentName(a.getChannelEndTwo())+Keyword.DEQUIV+Keyword.NULL+"))";
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
		res = "("+statemgr.makeStateExpression(t, true, StateManager.TIMEOUT)+Keyword.IMPL+"(("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.TIMEOUT)+")"+Keyword.AND+"("+Keyword.NOT+t.getChannelEndNameOne() +")"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.ON)+"))"+Keyword.AND+
				" ("+statemgr.makeStateExpression(t, true, StateManager.ON)+Keyword.EQUIV+"((("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.ON)+" )"+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+")"+Keyword.OR+"("+statemgr.makeStateExpression(t, false, StateManager.ON)+Keyword.AND+"("+Keyword.NOT+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+" ))))"+Keyword.AND+ 
				" ("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDON)+"))"+Keyword.AND+"("+Keyword.NOT+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+Keyword.AND+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+Keyword.AND+
				"(("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+Keyword.OR+statemgr.makeStateExpression(t, false, StateManager.RESET)+")"+Keyword.IMPL+t.getChannelEndNameOne()+")";
		System.out.println("Timer: "+res);
		return res;
	}
	@Override
	public String visit(PrioritySync p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String visit(BlockingSync p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String visit(BlockingSinkSync p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String visit(BlockingSourceSync p) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDataDependentName(PrimitiveEnd end) {
		return safeName(end)+"dta";
	}
	public String makeTuple(List<PrimitiveEnd> sinks) {
		String res = Keyword.TUPLE+"(";
		for (PrimitiveEnd s : sinks){
			res+=getDataDependentName(s)+" ";
		}
		res+=")";
		return res;
	}

	public static String makeTuple(Set<String> ports) {
		String res = Keyword.TUPLE+"(";
		boolean first=true;
		for (String port : ports){
			first=false;
			res+=(first?"":",")+port;
		}
		res+=")";
		return res;
	}
}
