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

import org.ect.codegen.reo2constraints.generator.ElementConverter;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.Util;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;

/**
 * @author Behnaz Changizi
 *
 */
public class PriorityVisitor implements IVisitor{
private Preprocessing info;
private Util util = new Util();
private ElementConverter conveter;
//Check for uniqueness
public PriorityVisitor(Preprocessing info, ElementConverter converter){
	this.info = info;
	this.conveter = converter;
}

	

 protected String visitRoute(Node n) {
	 String low = "";
	 String high = "";
	 String res = "";
	 
	 for (SourceEnd k : n.getSourceEnds()){
		 if (info.getPriotizedEnds().contains(k)){
			 high += ((high.length()>0)?util.and():"")+util.noFlowGiveReason(k, info, conveter.getCntxVisitor().getReasonPairs())+util.and()+"("+util.not()+util.safeName(k, info)+")";
		 } else {
			 low += ((high.length()>0)?util.or():"")+util.safeName(k, info);
		 }
	 }
	 if (low.length()>0 && high.length()>0){
		  res = "(("+low+")"+util.impl()+"("+high+"))";
	 }
	 res = util.convertToCNF(res);
	 return res;
	 
	}
		
 protected String visitReplicator(Node n) {
	 String low = "";
	 String high = "";
	 String res = "";
	 
	 for (SinkEnd k : n.getSinkEnds()){
		 if (info.getPriotizedEnds().contains(k)){
			 high += ((high.length()>0)?util.and():"")+util.noFlowGiveReason(k, info, conveter.getCntxVisitor().getReasonPairs())+util.and()+"("+util.not()+util.safeName(k, info)+")";
		 } else {
			 low += ((high.length()>0)?util.or():"")+util.safeName(k, info);
		 }
	 }
	 if (low.length()>0 && high.length()>0){
		  res = "(("+low+")"+util.impl()+"("+high+"))";
	 }
	 res = util.convertToCNF(res);
	 return res;
	}

	@Override
	public String visit(Connector c) {
		return null;
	}

	@Override
	public String visit(Sync s) {
		return null;
	}

	@Override
	public String visit(SyncDrain d) {
		return null;
	}

	@Override
	public String visit(LossySync l) {
		return null;
		//String res = visit(l);
		//if (res.length()>0){
		//	res += " and ";
		//}	
	//	res+="("+contxtPrefix+s.getSinkEnd().getName()+" equiv "+contxtPrefix+s.getSourceEnd().getName()+")";
		//System.out.println("Sync "+s+" : "+res);
		//return res;
	
		//String res = "("+l.getChannelEndNameTwo()+" impl "+l.getChannelEndNameOne()+")";
		//System.out.println(res);
	//	return res;
	}
	//linea 1: empty -> !b and a->state'=full  and !a->state'=state
	//linea 2: full  -> !a and b->state'=empty and !b->state'=state
	//linea 3:!a and !b -> state'=state
//	@Override
	//	public String visit(FIFO f) {
	//		//int no = 0;
	//		//String id = EcoreUtil.getIdentification(f);
	//		//no = FIFOIds.indexOf(id);
	//		if (no < 0){
	//			FIFOIds.add(id);
	//			FIFOStates.add(false);
	//			no = FIFOIds.size()-1;
	//		}
	//		String res = "";		
	//		//linea 1
	//		res = "((not "+$STATEISFULL+no +") impl ((not "+ f.getChannelEndNameTwo() +") and ";
	//		res += "("+f.getChannelEndNameOne()+" impl "+$NXTSTATEISFULL+no+") and ";
	//		res += "((not "+f.getChannelEndNameOne()+") impl ("+$NXTSTATEISFULL+no+" equiv "+$STATEISFULL+no+")))) and ";
	//		//linea 2
	//		res += "("+$STATEISFULL+no +" impl ((not "+ f.getChannelEndNameOne() +") and ";
	//		res += "("+f.getChannelEndNameTwo()+" impl (not "+$NXTSTATEISFULL+no+")) and ";
	//		res += "((not "+f.getChannelEndNameTwo()+") impl ("+$NXTSTATEISFULL+no+" equiv "+$STATEISFULL+no+")))) and ";
	//		//linea 3
	//		res += "(((not "+f.getChannelEndNameOne()+") and (not "+f.getChannelEndNameTwo()+")) impl ("+ $NXTSTATEISFULL+no+" equiv "+$NXTSTATEISFULL+no+"))";
	//		System.out.println("FIFO: "+res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(Filter f) {
	//		System.out.println("Filter" + f.getName());
	//		String res = "("+f.getSinkEnd().getName() + " equiv (" + generateIdAndSave(f.getExpression()) + " and " + f.getSourceEnd().getName() + "))";
	//		System.out.println("Filter "+res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(SyncSpout s) {
	//		String res = "( "+s.getChannelEndNameOne()+" equiv "+s.getChannelEndNameTwo()+" ) ";
	//		System.out.println(res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(AsyncDrain a) {
	//		System.out.println("AsyncDrain" + a.getName());
	//		String res = "not (" + a.getChannelEndNameOne() + " and " + a.getChannelEndNameTwo() + " ) ";
	//		System.out.println(res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(AsyncSpout a) {
	//		String res = super.visit(a);
	//		if (res.length()>0){
	//			res += " and ";
	//		}	
	//		res+="("+contxtPrefix+w.getEnd().getName()+")";
	//		System.out.println("Writer "+w+" : "+res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(Reader r) {
	//		String res = super.visit(r);
	//		if (res.length()>0){
	//			res += " and ";
	//		}	
	//		res+="("+contxtPrefix+r.getEnd().getName()+")";
	//		System.out.println("Reader "+r+" : "+res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(Writer w) {
	//		String res = super.visit(w);
	//		if (res.length()>0){
	//			res += " and ";
	//		}	
	//		res+="("+contxtPrefix+w.getEnd().getName()+")";
	//		System.out.println("Writer "+w+" : "+res);
	//		return res;
	//	}
	//	@Override
	//	public String visit(Timer t) {
	//		String res = super.visit(t);
	//		if (res.length()>0){
	//			res += " and ";
	//		}	
	//		res+="("+contxtPrefix+w.getEnd().getName()+")";
	//		System.out.println("Writer "+w+" : "+res);
	//		return res;
	//	
	//		int no = 0;
	//		String id = EcoreUtil.getIdentification(t);
	//		no = TimerIds.indexOf(id);
	//		if (no < 0){
	//			TimerIds.add(id);
	//			no = TimerIds.size()-1;
	//		}
	//		String res = "";		
	//		res = "("+$NEXTTIMEOUT+no+" impl ((not "+$TIMEOUT+no+") and (not "+t.getChannelEndNameOne() +") and "+$STATEON+no+")) and "+
	//		      " ("+$NEXTSTATEON+no+" equiv (((not "+$STATEON+no+" ) and "+$CMDON+no+") or ("+$STATEON+no+" and (not "+$CMDOFF+" )))) and "+ 
	//			  " (not("+$CMDON+no+" and "+$CMDOFF+no+")) and (not("+$RESET+no+" and "+$CMDON+no+")) and (not("+$RESET+no+" and "+$CMDOFF+no+")) and "+
	//		      "(("+$CMDON+no+" or "+$CMDOFF+no+" or "+$RESET+no+") impl "+t.getChannelEndNameOne()+")";
	//		System.out.println("Timer: "+res);
	//		return res;
	//	}
	
	@Override
	public String visit(FIFO f) {
		return null;
	}
	@Override
	public String visit(Filter f) {
		return null;
	}
	@Override
	public String visit(SyncSpout s) {
		return null;
	}
	@Override
	public String visit(AsyncDrain a) {
		return null;
	}
	@Override
	public String visit(AsyncSpout a) {
		return null;
	}
	@Override
	public String visit(Timer t) {
		return null;
	}
	@Override
	public String visit(Node n) {
		if (n.getType() == NodeType.REPLICATE){
			return visitReplicator(n);
		} else if (n.getType() == NodeType.ROUTE){
			return visitRoute(n);
		} 
		return null;
	}
	@Override
	public String visit(Reader r) {
		return null;
	}
	@Override
	public String visit(Writer w) {
		return null;
	}
	@Override
	public String visit(PrioritySync p) {
		return null;
	}
	@Override
	public String visit(BlockingSync p) {
		return null;
	}
	@Override
	public String visit(BlockingSinkSync p) {
		return null;
	}
	@Override
	public String visit(BlockingSourceSync p) {
		return null;
	}	
	
}

