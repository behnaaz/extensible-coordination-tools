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

import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.codegen.reo2constraints.generator.Util;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;

/**
 * @author Behnaz Changizi
 *
 */
public class SyncVisitor implements IVisitor {
	private StateManager statemgr;
	private Preprocessing info;
	public boolean useOrbital = true;
	private Util util = new Util();
	public boolean isUseOrbital() {
		return useOrbital;
	}

	public void setUseOrbital(boolean useOrbital) {
		this.useOrbital = useOrbital;
	}

	//check nameha ro ham ers bari konam TODO
	public SyncVisitor(StateManager statemgr, Preprocessing info){
		this.statemgr = statemgr;
		this.info = info;
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

	//1)(and all sink[i]) <-> (and all source[j]) AND 
	//2)equiv all sink AND
	//3)equiv all Source
	protected String visitJoin(Node n) {
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		boolean sourcParentheses = false;
		boolean sinkParentheses = false;
		String andAllSources = "";
		String equivSources = "";
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			SourceEnd cend = n.getSourceEnds().get(i);
			if (andAllSources.compareTo("")!=0){
				andAllSources+=util.and();
				sourcParentheses = true;
			}
			andAllSources += util.safeName(cend, info);
			//compute (2) in equivSources
			for (int j=i+1; j<n.getSourceEnds().size(); j++){
				equivSources += (equivSources.compareTo("")!=0)?util.and():""; 
				equivSources += "("+util.safeName(cend, info) + util.equiv() + util.safeName(n.getSourceEnds().get(j), info)+")";
			}
		}

		String equivSinks = "";
		//compute first part of (1) in equivs
		String andAllSinks = "";
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			SinkEnd kend = n.getSinkEnds().get(i);
			if (andAllSinks.compareTo("")!=0){
				andAllSinks +=util.and();
				sinkParentheses = true;
			}
			andAllSinks += util.safeName(kend, info);

			//compute (3) in equivSinks
			for (int j=i+1; j<n.getSinkEnds().size(); j++){
				equivSinks += (equivSinks.compareTo("")!=0)?util.and():""; 
				equivSinks += "("+util.safeName(kend, info) + util.equiv() + util.safeName(n.getSinkEnds().get(j), info)+")";
			}
		}
		//compute (1)
		String equivs = "("+(sinkParentheses?"(":"")+andAllSinks+(sinkParentheses?")":"")+util.equiv()+(sourcParentheses?"(":"")+andAllSources+(sourcParentheses?")":"")+")";

		String res = equivs;
		res += (equivSources.compareTo("")!=0)?(util.and()+equivSources):"";
		res += (equivSinks.compareTo("")!=0)?(util.and()+equivSinks):"";
		Reo.logInfo("Join Node " + n + " : " + res);
		return res;	
	}
	//1)(or all sink[i]) <-> (or all source[j]) AND 
	//2)nand all source AND
	//3)nand all sinks
	protected String visitRoute(Node n) {
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		boolean sourcParentheses = false;
		boolean sinkParentheses = false;
		String orAllSources = "";
		String nandSources = "";
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			SourceEnd cend = n.getSourceEnds().get(i);
			if (orAllSources.compareTo("")!=0){
				orAllSources+=util.or();
				sourcParentheses = true;
			}
			orAllSources += util.safeName(cend, info);
			//compute (2) in nandSources
			for (int j=i+1; j<n.getSourceEnds().size(); j++){
				nandSources += (nandSources.compareTo("")!=0)?util.and():""; 
				nandSources += "("+util.not()+"("+util.safeName(cend, info) + util.and() + util.safeName(n.getSourceEnds().get(j), info)+"))";
			}
		}

		String nandSinks = "";
		//compute first part of (1) in equivs
		String orAllSinks = "";
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			SinkEnd kend = n.getSinkEnds().get(i);
			if (orAllSinks.compareTo("")!=0){
				orAllSinks +=util.or();;
				sinkParentheses = true;
			}
			orAllSinks += util.safeName(kend, info);

			//compute (3) in nandSinks
			for (int j=i+1; j<n.getSinkEnds().size(); j++){
				nandSinks += (nandSinks.compareTo("")!=0)?util.and():""; 
				nandSinks += "("+util.not()+"("+util.safeName(kend, info) + util.and() + util.safeName(n.getSinkEnds().get(j), info)+"))";
			}
		}
		//compute (1)
		String equivs = "("+(sinkParentheses?"(":"")+orAllSinks+(sinkParentheses?")":"")+util.equiv()+(sourcParentheses?"(":"")+orAllSources+(sourcParentheses?")":"")+")";

		String res = equivs;
		res += (res.compareTo("")!=0 && nandSources.compareTo("")!=0)?(util.and()+nandSources):"";
		res += (res.compareTo("")!=0 && nandSinks.compareTo("")!=0)?(util.and()+nandSinks):"";
		Reo.logInfo("Route Node " + n.getName() + " : " + res);
		return res;
	}


	//1)(or all sink[i]) <-> (and all source[j]) AND 
	//2)nand all sink AND
	//3)equiv all Source
	protected String visitReplicator(Node n) {
		boolean sourcParentheses = false;
		boolean sinkParentheses = false;
		String andAllSources = "";
		String nandSinks = "";
		String equivSources = "";
		assert(n.getSinkEnds().size()>0 && n.getSourceEnds().size()>0);
		for (int i=0; i<n.getSourceEnds().size(); i++) {
			//compute second part of (1) in orAllSources
			SourceEnd cend = n.getSourceEnds().get(i);
			if (andAllSources.compareTo("")!=0){
				andAllSources+=util.and();
				sourcParentheses = true;
			}
			andAllSources += util.safeName(cend, info);
			//compute (3) in equivSources
			for (int j=i+1; j<n.getSourceEnds().size(); j++){
				equivSources += (equivSources.compareTo("")!=0)?util.and():""; 
				equivSources += "("+util.safeName(cend, info) + util.equiv() + util.safeName(n.getSourceEnds().get(j), info)+")";
			}
		}

		//compute first part of (1) in orAllSinks
		String orAllSinks = "";
		for (int i=0; i<n.getSinkEnds().size(); i++) {
			SinkEnd kend = n.getSinkEnds().get(i);
			if (orAllSinks.compareTo("")!=0){
				orAllSinks +=util.or();
				sinkParentheses = true;
			}
			orAllSinks += util.safeName(kend, info);
			//compute (2) in nandSinks
			for (int j=i+1; j<n.getSinkEnds().size(); j++){
				nandSinks += (nandSinks.compareTo("")!=0)?util.and():""; 
				nandSinks += "("+util.not()+"("+util.safeName(kend, info) + util.and() + util.safeName(n.getSinkEnds().get(j), info)+"))";
			}
		}
		//compute (1)
		String equivs = "("+(sinkParentheses?"(":"")+orAllSinks+(sinkParentheses?")":"")+util.equiv()+(sourcParentheses?"(":"")+andAllSources+(sourcParentheses?")":"")+")";

		String res = equivs;
		res += (equivSources.compareTo("")!=0)?(util.and()+equivSources):"";
		res += (nandSinks.compareTo("")!=0)?(util.and()+nandSinks):"";
		Reo.logInfo("Replicate Node " + n.getName() + " : " + res);
		return res;
	}

	@Override
	public String visit(Connector c) {
		return "";
		// TODO Auto-generated method stub

	}
	@Override
	public String visit(Sync s) {
		Reo.logInfo("Sync " + s.getName());
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(SyncDrain d) {
		Reo.logInfo("SyncDrain " + d.getName());
		String res = "("+util.safeName(d.getChannelEndOne(), info)+util.equiv()+util.safeName(d.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(LossySync l) {
		String res = "("+util.safeName(l.getChannelEndTwo(), info)+util.impl()+util.safeName(l.getChannelEndOne(), info)+")";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(FIFO f) {
		String res = "";		
		//empty -> !a
		res = "(("+util.not()+statemgr.makeStateExpression(f, false, StateManager.FULL)+")"+util.impl()+"("+util.not()+util.safeName(f.getChannelEndTwo(), info)+"))"+util.and();
		//full->!b
		res +=	"(("+statemgr.makeStateExpression(f, false, StateManager.FULL)+")"+util.impl()+"("+util.not()+util.safeName(f.getChannelEndOne(), info)+"))"+util.and();
		//a->next full   
		res +=	"("+util.safeName(f.getChannelEndOne(), info)+util.impl()+statemgr.makeStateExpression(f, true, StateManager.FULL)+")"+util.and();
		//b->next empty
		res +=	"("+util.safeName(f.getChannelEndTwo(), info)+util.impl()+"("+util.not()+statemgr.makeStateExpression(f, true, StateManager.FULL)+"))"+util.and();
		//!(a or b)->state = nextstate
		res +=	"(("+util.not()+"("+util.safeName(f.getChannelEndOne(), info)+util.or()+util.safeName(f.getChannelEndTwo(), info)+"))"+util.equiv()+"("+statemgr.makeStateExpression(f, false, StateManager.FULL)+util.equiv()+statemgr.makeStateExpression(f, true, StateManager.FULL)+"))"; 
		Reo.logInfo("FIFO"+f+" \n"+res);
		return res;
	}

	@Override
	public String visit(Filter f) {
		Reo.logInfo("Filter" + f.getName());
		String res = "("+util.safeName(f.getSinkEnd(), info) + util.equiv()+"(" + info.getExpressionId(f) + util.and() + util.safeName(f.getSourceEnd(), info) + "))";
		Reo.logInfo("Filter "+res);
		return res;
	}
	@Override
	public String visit(SyncSpout s) {
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(AsyncDrain a) {
		Reo.logInfo("AsyncDrain" + a.getName());
		String res = "(" + util.not()+"(" + util.safeName(a.getChannelEndOne(), info) + util.and() + util.safeName(a.getChannelEndTwo(), info) + " ))";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(AsyncSpout a) {
		Reo.logInfo("AsyncSpout" + a.getName());
		String res = "(" + util.not()+"(" + util.safeName(a.getChannelEndOne(), info) + util.and() + util.safeName(a.getChannelEndTwo(), info) + " ))";
		Reo.logInfo(res);
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
		res = "("+statemgr.makeStateExpression(t, true, StateManager.TIMEOUT)+util.impl()+"(("+util.not()+statemgr.makeStateExpression(t, false, StateManager.TIMEOUT)+")"+util.and()+"("+util.not()+util.safeName(t.getChannelEndOne(), info) +")"+util.and()+statemgr.makeStateExpression(t, false, StateManager.ON)+"))"+util.and()+
				" ("+statemgr.makeStateExpression(t, true, StateManager.ON)+util.equiv()+"((("+util.not()+statemgr.makeStateExpression(t, false, StateManager.ON)+" )"+util.and()+statemgr.makeStateExpression(t, false, StateManager.CMDON)+")"+util.or()+"("+statemgr.makeStateExpression(t, false, StateManager.ON)+util.and()+"("+util.not()+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+" ))))"+util.and()+ 
				" ("+util.not()+"("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+util.and()+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+util.and()+"("+util.not()+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+util.and()+statemgr.makeStateExpression(t, false, StateManager.CMDON)+"))"+util.and()+"("+util.not()+"("+statemgr.makeStateExpression(t, false, StateManager.RESET)+util.and()+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+"))"+util.and()+
				"(("+statemgr.makeStateExpression(t, false, StateManager.CMDON)+util.or()+statemgr.makeStateExpression(t, false, StateManager.CMDOFF)+util.or()+statemgr.makeStateExpression(t, false, StateManager.RESET)+")"+util.impl()+util.safeName(t.getChannelEndOne(), info)+")";
		Reo.logInfo("Timer: "+res);
		return res;
	}
	@Override
	public String visit(PrioritySync s) {
		Reo.logInfo("PrioritySync " + s.getName());
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(BlockingSync s) {
		Reo.logInfo("BlockingSync " + s.getName());
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(BlockingSinkSync s) {
		Reo.logInfo("PrioritySync " + s.getName());
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}
	@Override
	public String visit(BlockingSourceSync s) {
		Reo.logInfo("PrioritySync " + s.getName());
		String res = "("+util.safeName(s.getChannelEndOne(), info)+util.equiv()+util.safeName(s.getChannelEndTwo(), info)+")";
		Reo.logInfo(res);
		return res;
	}
}
