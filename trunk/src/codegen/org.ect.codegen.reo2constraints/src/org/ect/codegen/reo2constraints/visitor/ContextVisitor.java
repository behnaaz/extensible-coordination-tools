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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.codegen.reo2constraints.generator.Util;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;

/**
 * @author Behnaz Changizi
 *
 */
public class ContextVisitor implements IVisitor {
	
	private StateManager statemgr;
	private Preprocessing preprocessingInfo = null;
	Map<String, String> reasonPairs = new HashMap<String, String>();
	List<PrimitiveEnd> visited = new ArrayList<PrimitiveEnd>();
	boolean coarse = true;
	Util util = new Util();
	
	public ContextVisitor(StateManager statemgr, Preprocessing preprocessor){
		this.statemgr = statemgr;
		this.preprocessingInfo = preprocessor;		
	}
	
	//private String addAxiom(PrimitiveEnd end){
	
	//}
	
	@Override
	public String visit(Connector c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Sync s) {
		String res = null;
		final String src = util.safeName(s.getSourceEnd(), preprocessingInfo);
		final String snk = util.safeName(s.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		
		if (coarse){
			//(ce = 1 or cesrcnoflowg = 1 or nksnknoflowg = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			// and (ce = 1 or nk = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// and (ce = 1 or cesrcnoflowg = 0 or nksnknoflowg = 0) and
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snkgive, false)+")"+Keyword.SYM_AND;
			// (ce = 0 or nk = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(snk, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 1 or nk = 1 or nksnknoflowg = 1)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 0 or nk = 1 or nksnknoflowg = 0)
			res += "("+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, false)+")";
		}
		else{
			Util.addAxiom(encodeColors(s.getAnimationTable(), s.getAllEnds()), s.getAllEnds(), coarse);
		}
		Reo.logInfo("#Constraint encoding for Sync: "+res);
		return res;
	}

	@Override
	public String visit(FIFO f) {
		final String src = util.safeName(f.getSourceEnd(), preprocessingInfo);
		final String snk = util.safeName(f.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		//empty -> !a
		String res = null;
		if (coarse){
			//(dowom = 1 or nextstateisfull0 = 0 or seyom = 1 or stateisfull0 = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, true, StateManager.FULL), false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), true)+")"+Keyword.SYM_AND; 
			// and (dowom = 1 or nextstateisfull0 = 1 or seyom = 1 or stateisfull0 = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, true, StateManager.FULL), true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), false)+")"+Keyword.SYM_AND;		
			// and (nextstateisfull0 = 0 or seyom = 0) and
			res += "("+setVal(statemgr.makeStateExpression(f, true, StateManager.FULL), false)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// (dowom = 0 or nextstateisfull0 = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, true, StateManager.FULL), true)+")"+Keyword.SYM_AND;
			// and (seyom = 1 or seyomsnknoflowg = 0 or stateisfull0 = 0)
			res += "("+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive,false)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), false)+")"+Keyword.SYM_AND;
			// and (dowomsrcnoflowg = 1 or stateisfull0 = 0)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), false)+")"+Keyword.SYM_AND;
			// 	 and (dowom = 0 or stateisfull0 = 0)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), false)+")"+Keyword.SYM_AND;
			// 	 and (seyomsnknoflowg = 1 or stateisfull0 = 1)
			res += "("+setVal(snkgive, true)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), true)+")"+Keyword.SYM_AND;
			// and (seyom = 0 or stateisfull0 = 1)
			res += "("+setVal(snk, false)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), true)+")"+Keyword.SYM_AND;
			//and (dowom = 1 or dowomsrcnoflowg = 0 or stateisfull0 = 1)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(statemgr.makeStateExpression(f, false, StateManager.FULL), true)+")";
		}
		else {
		res = "(("+Keyword.NOT+statemgr.makeStateExpression(f, false, StateManager.FULL)+")"+Keyword.IMPL+"("+encodeColors(f.getAnimationTable(false), f.getAllEnds())+"))"+Keyword.AND;
						//full->!b
		res +=		"(("+statemgr.makeStateExpression(f, false, StateManager.FULL)+")"+Keyword.IMPL+"("+encodeColors(f.getAnimationTable(true), f.getAllEnds())+"))"+Keyword.AND;
						//a->next full   
		res +=		"("+src+Keyword.IMPL+statemgr.makeStateExpression(f, true, StateManager.FULL)+")"+Keyword.AND;
						//b->next empty
		res +=		"("+snk+Keyword.IMPL+"("+Keyword.NOT+statemgr.makeStateExpression(f, true, StateManager.FULL)+"))"+Keyword.AND;
						//!(a or b)->state = nextstate
		res +=		"(("+Keyword.NOT+"("+src+Keyword.OR+snk+"))"+Keyword.EQUIV+"("+statemgr.makeStateExpression(f, false, StateManager.FULL)+Keyword.EQUIV+statemgr.makeStateExpression(f, true, StateManager.FULL)+"))"; 
		res = Util.addAxiom(res, f.getAllEnds(), coarse);
		Reo.logInfo("Context encoding for FIFO:\n"+res);
		}
		return res;
	}

	private String setVal(String src, boolean b) {
		if (b)
			return src;
		else
			return Keyword.SYM_NOT+src;
		//return src+" = "+((b)?"1":"0");
	}

	@Override
	public String visit(Filter f) {
		final String src =util.safeName(f.getSourceEnd(), preprocessingInfo);
		final String snk =util.safeName(f.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		String res = null;
		if (coarse){
			//(snk = 1 or snknoflowg = 1) 
			res = "("+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (snk = 0 or src = 1)
			res += "("+setVal(snk, false)+Keyword.SYM_OR+setVal(src, true)+")"+Keyword.SYM_AND;
			// and (snknoflowg = 1 or src = 1) 
			res += "("+setVal(snkgive, true)+Keyword.SYM_OR+setVal(src, true)+")"+Keyword.SYM_AND;
			//and (src = 1 or srcnoflowg = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+")";
		}
		else {
			res = Util.addAxiom(encodeColors(f.getAnimationTable(), f.getAllEnds()), f.getAllEnds(), coarse);
		}
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(SyncDrain d) {
		String res = Util.addAxiom(encodeColors(d.getAnimationTable(), d.getAllEnds()), d.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		} 
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(LossySync l) {
		String res = Util.addAxiom(encodeColors(l.getAnimationTable(), l.getAllEnds()), l.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		}
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(SyncSpout s) {
		String res = Util.addAxiom(encodeColors(s.getAnimationTable(), s.getAllEnds()), s.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		}
		return res;
	}

	@Override
	public String visit(AsyncDrain a) {
		String res = Util.addAxiom(encodeColors(a.getAnimationTable(), a.getAllEnds()), a.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		}
		return res;
	}

	@Override
	public String visit(AsyncSpout a) {
		String res = Util.addAxiom(encodeColors(a.getAnimationTable(), a.getAllEnds()), a.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		}
		return res;
	}

	private String encodeColors(AnimationTable animationTable, EList<PrimitiveEnd> ends) {
		String res = "";
		for (Colouring ct:animationTable.getColourings()){
			String newcolor = "("+colorMe(ct, ends)+")";
			if (res.length()>0){
				res += ((res.length()==0)?"":((coarse)?Keyword.SYM_OR:Keyword.OR))+newcolor;
			}
			else 
				res = newcolor;			
		}		
		System.out.println(res);
		return res;
	}

	private String colorMe(Colouring ct, EList<PrimitiveEnd> ends) {
		String res = "";
		for (PrimitiveEnd end : ends) {
			if (res.length() > 0){
				res += (coarse)?Keyword.SYM_AND:Keyword.AND; 
			}
            FlowColour c = ct.getColour(end);
            if (c == FlowColour.FLOW_LITERAL){
            	String tmp =util.safeName(end, preprocessingInfo);
            	if (tmp==null || tmp.trim().length()==0)
            		tmp = this.preprocessingInfo.getNotNullName(end);
            	if (tmp.contains(" "))
            		tmp = "("+tmp+")";
            	res += tmp;
            }else if (c == FlowColour.NO_FLOW_GIVE_REASON_LITERAL){
            	res += "(("+((coarse)?Keyword.SYM_NOT:Keyword.NOT)+util.safeName(end, preprocessingInfo)+")"+((coarse)?Keyword.SYM_AND:Keyword.AND)+util.noFlowGiveReason(end, preprocessingInfo, reasonPairs)+")";
            }else if (c == FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL){
            	res += "("+((coarse)?Keyword.SYM_NOT:Keyword.NOT)+"("+util.safeName(end, preprocessingInfo)+((coarse)?Keyword.SYM_OR:Keyword.OR)+util.noFlowGiveReason(end, preprocessingInfo, reasonPairs)+"))";
            }
		}
		return res;
	}
	

	@Override
	public String visit(Timer t) {
		String res = Util.addAxiom(encodeColors(t.getAnimationTable(), t.getAllEnds()), t.getAllEnds(), coarse);
		if (coarse){
			res = util.convertToCNF(res);
		}
		return res;
	}

	@Override
	public String visit(Node n) {
		//final String src =util.safeName(n.getSourceEnd().get);
	//	final String snk =util.safeName(n.getSinkEnd());
		//final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		//final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		String res = null;
		if (coarse){
			res = encodeColors(n.getAnimationTable(), n.getAllEnds());
			res = util.convertToCNF(res);
		}else{
			res = Util.addAxiom(encodeColors(n.getAnimationTable(), n.getAllEnds()), n.getAllEnds(), coarse);
		}
		Reo.logInfo(res);
		
		return res;
	}

	@Override
	public String visit(Reader r) {
		String res = "";//TODO Util.addAxiom(r.getEnd());
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(Writer w) {
		String res = "";//TODO Util.addAxiom(w.getEnd());
		Reo.logInfo(res);
		return res;
	}

	@Override
	public String visit(PrioritySync p) {
		String res = null;
		final String src =util.safeName(p.getSourceEnd(), preprocessingInfo);
		final String snk =util.safeName(p.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		assert(coarse);
		if (coarse){
			//(ce = 1 or cesrcnoflowg = 1 or nksnknoflowg = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			// and (ce = 1 or nk = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// and (ce = 1 or cesrcnoflowg = 0 or nksnknoflowg = 0) and
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snkgive, false)+")"+Keyword.SYM_AND;
			// (ce = 0 or nk = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(snk, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 1 or nk = 1 or nksnknoflowg = 1)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 0 or nk = 1 or nksnknoflowg = 0)
			res += "("+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, false)+")";
		}
		else{
			Util.addAxiom(encodeColors(p.getAnimationTable(), p.getAllEnds()), p.getAllEnds(), coarse);
		}
		Reo.logInfo("#Constraint encoding for PrioritySync: "+res);
		return res;
}

	@Override
	public String visit(BlockingSync p) {
		String res = null;
		final String src =util.safeName(p.getSourceEnd(), preprocessingInfo);
		final String snk =util.safeName(p.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		assert(coarse);
		if (coarse){
			//(ce = 1 or cesrcnoflowg = 1 or nksnknoflowg = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			// and (ce = 1 or nk = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// and (ce = 1 or cesrcnoflowg = 0 or nksnknoflowg = 0) and
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snkgive, false)+")"+Keyword.SYM_AND;
			// (ce = 0 or nk = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(snk, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 1 or nk = 1 or nksnknoflowg = 1)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 0 or nk = 1 or nksnknoflowg = 0)
			res += "("+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, false)+")";
		}
		else{
			Util.addAxiom(encodeColors(p.getAnimationTable(), p.getAllEnds()), p.getAllEnds(), coarse);
		}
		Reo.logInfo("#Constraint encoding for BlockingSync: "+res);
		return res;	
	}

	@Override
	public String visit(BlockingSinkSync p) {
		String res = null;
		final String src =util.safeName(p.getSourceEnd(), preprocessingInfo);
		final String snk =util.safeName(p.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		assert(coarse);
		if (coarse){
			//(ce = 1 or cesrcnoflowg = 1 or nksnknoflowg = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			// and (ce = 1 or nk = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// and (ce = 1 or cesrcnoflowg = 0 or nksnknoflowg = 0) and
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snkgive, false)+")"+Keyword.SYM_AND;
			// (ce = 0 or nk = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(snk, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 1 or nk = 1 or nksnknoflowg = 1)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 0 or nk = 1 or nksnknoflowg = 0)
			res += "("+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, false)+")";
		}
		else{
			Util.addAxiom(encodeColors(p.getAnimationTable(), p.getAllEnds()), p.getAllEnds(), coarse);
		}
		Reo.logInfo("#Constraint encoding for BlockingSinkSync: "+res);
		return res;
	}

	@Override
	public String visit(BlockingSourceSync p) {
		String res = null;
		final String src =util.safeName(p.getSourceEnd(), preprocessingInfo);
		final String snk =util.safeName(p.getSinkEnd(), preprocessingInfo);
		final String srcgive = src + Keyword.SRC + Keyword.GIVRSN;
		final String snkgive = snk + Keyword.SNK + Keyword.GIVRSN;
		assert(coarse);
		if (coarse){
			//(ce = 1 or cesrcnoflowg = 1 or nksnknoflowg = 1)
			res = "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			// and (ce = 1 or nk = 0)
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(snk, false)+")"+Keyword.SYM_AND;
			// and (ce = 1 or cesrcnoflowg = 0 or nksnknoflowg = 0) and
			res += "("+setVal(src, true)+Keyword.SYM_OR+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snkgive, false)+")"+Keyword.SYM_AND;
			// (ce = 0 or nk = 1)
			res += "("+setVal(src, false)+Keyword.SYM_OR+setVal(snk, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 1 or nk = 1 or nksnknoflowg = 1)
			res += "("+setVal(srcgive, true)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, true)+")"+Keyword.SYM_AND;
			//and (cesrcnoflowg = 0 or nk = 1 or nksnknoflowg = 0)
			res += "("+setVal(srcgive, false)+Keyword.SYM_OR+setVal(snk, true)+Keyword.SYM_OR+setVal(snkgive, false)+")";
		}
		else{
			Util.addAxiom(encodeColors(p.getAnimationTable(), p.getAllEnds()), p.getAllEnds(), coarse);
		}
		Reo.logInfo("#Constraint encoding for BlockingSourceSync: "+res);
		return res;
	}

	public Map<String, String> getReasonPairs() {
		return reasonPairs;
	}
}