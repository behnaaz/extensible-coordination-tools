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

import org.eclipse.core.runtime.IProgressMonitor;

import org.ect.codegen.reo2constraints.generator.Constraint;
import org.ect.codegen.reo2constraints.generator.ElementConverter;
import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.codegen.reo2constraints.generator.Util;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.util.*;

public class ConstraintsTraversalWorker extends AbstractTraversalWorker {
	
	boolean useSymbolaris = true;
	public boolean isUseSymbolaris() {
		return useSymbolaris;
	}

	public void setUseSymbolaris(boolean useSymbolaris) {
		this.useSymbolaris = useSymbolaris;
	}

	private Connectable start = null;
	private ElementConverter converter = null;
	private Constraint finalcons = new Constraint();
	private Connectable pre = null;
	private Preprocessing info;
	private List<PrimitiveEnd> prioritized = new ArrayList<PrimitiveEnd>();
	Util util = new Util();
	
	/**
	 * TODO 1 connector =
	 * ReoTextualSemantics.createNormalizedConnector(connector, names); //TODO 2
	 * 
	 * @param start
	 *            Start element.
	 */
	public ConstraintsTraversalWorker(Connector connector, Preprocessing info,
			StateManager statemgr) {
		this.info = info;
		this.converter = new ElementConverter(info, statemgr);
		for (Node node : connector.getNodes()) {
			if (node.isSourceNode() && !node.isSingleton()) {
				this.start = node;
				break;
			}
		}
		if (start == null) {
			this.start = connector.getNodes().get(0);
		}
		if (info.isPrioritySensitive()){
			for (Primitive p : connector.getPrimitives()){
				if (p instanceof PrioritySync){
					prioritized .add(((PrioritySync) p).getSinkEnd());
					prioritized.add(((PrioritySync) p).getSourceEnd());
				}
			}
		}
	}

	public ConstraintsTraversalWorker(Network network, Preprocessing info,
			StateManager statemgr) {
		this.converter = new ElementConverter(info, statemgr);
	}

	@Override
	public Connectable getStart() {
		return start;
	}

	@Override
	protected boolean visitElement(Connectable element, IProgressMonitor monitor) {
		if (element instanceof Primitive) {
			mergeWithOthers(converter.generateConstraints((Primitive) element),
					element);
			return true;
		} else if (element instanceof Node) {
			mergeWithOthers(converter.generateConstraints((Node) element),
					element);
			return true;
		} else {
			Reo.logError("Constraints are not defined for " + element);
			return false;
		}
	}

	private void mergeWithOthers(Constraint cons, Connectable cur) {
		// Conjuncting the constraints
		if (cons.getCntxCons() != null
				&& cons.getCntxCons().trim().length() > 0) {
			if (finalcons.getCntxCons() == null
					|| finalcons.getCntxCons().trim().length() == 0) {
				finalcons.setCntxCons(cons.getCntxCons());
			} else {
				String tmp = finalcons.getCntxCons() + ((useSymbolaris)?Keyword.SYM_AND:Keyword.AND)
						+ cons.getCntxCons();
				finalcons.setCntxCons(tmp);
			}
		} else if (cons.getSyncCons() != null
				&& cons.getSyncCons().trim().length() > 0) {
			String cnf = new Util().convertToCNF(cons.getSyncCons());//TODO remove!!!!!!!!!!!
			if (finalcons.getSyncCons() == null
					|| finalcons.getSyncCons().trim().length() == 0) {
				finalcons.setSyncCons(cnf);
			} else {
				String tmp = finalcons.getSyncCons() + ((useSymbolaris)?(" "+Keyword.SYM_AND+" "):Keyword.AND) + cnf;
				finalcons.setSyncCons(tmp);
			}
		} else if (cons.getDataCons() != null
				&& cons.getDataCons().trim().length() > 0) {
			if (finalcons.getDataCons() == null
					|| finalcons.getDataCons().trim().length() == 0) {
				finalcons.setDataCons(cons.getDataCons());
			} else {
				String tmp = "(" + finalcons.getDataCons() + ")" + ((useSymbolaris)?Keyword.SYM_AND:Keyword.AND)
						+ "(" + cons.getDataCons() + ")";
				finalcons.setDataCons(tmp);
			}
		} else if (cons.getPrioCons() != null
				&& cons.getPrioCons().trim().length() > 0) {
			if (finalcons.getPrioCons() == null
					|| finalcons.getPrioCons().trim().length() == 0) {
				finalcons.setPrioCons(cons.getPrioCons());
			} else {
				String tmp = "(" + finalcons.getPrioCons() + ")" + ((useSymbolaris)?Keyword.SYM_AND:Keyword.AND)
						+ "(" + cons.getPrioCons() + ")";
				finalcons.setPrioCons(tmp);
			}

			assert (pre != null && cur != null);
			hideCommonEnd(pre, cur, finalcons);
			pre = cur;

		} 
		// Hiding TODO
		// finalcons if hidden hide else connect

	}

	public void hideCommonEnd(Connectable pre, Connectable cur,
			Constraint mrgd) {
		PrimitiveEnd com = findCommonEnd(pre, cur);
		if (com == null)  return;
		String end2hide = info.getNotNullName(com); 
		String cntxt = mrgd.getCntxCons();
		String sync = mrgd.getSyncCons();
		String data = mrgd.getDataCons();
		String prio = mrgd.getPrioCons();
		
		if (cntxt != null && cntxt.length()>0){
			 String x = util.noFlowGiveReason(com, info, converter.getCntxVisitor().getReasonPairs());
			 //x == end2hide+Keyword.GIVRSN?
			 String tmptr = cntxt.replaceAll(x, Keyword.TRUE);
			 String tmpfr = cntxt.replaceAll(x, Keyword.FALSE);
			 String tmpr = "("+tmptr + Keyword.OR + tmpfr + ")";
			 
			 String coloredendpair = util.noFlowPairGiveReason(com, info);
			 String tmptrp = tmpr.replaceAll(coloredendpair, Keyword.TRUE);
			 String tmpfrp = tmpr.replaceAll(coloredendpair, Keyword.FALSE);
			 String tmprp = "("+tmptrp + Keyword.OR + tmpfrp + ")";
			 
			
			 String tmpt = tmprp.replaceAll(end2hide, Keyword.TRUE);
			 String tmpf = tmprp.replaceAll(end2hide, Keyword.FALSE);
			 cntxt = "("+tmpt + Keyword.OR + tmpf + ")";
			 finalcons.setCntxCons(cntxt);
			 Reo.logInfo("hideCommonEnd: new context constrint after hiding "+end2hide+" = "+cntxt);
		}else if (sync != null && sync.length()>0){
				 String tmpt = sync.replaceAll(end2hide, Keyword.TRUE);
				 String tmpf = sync.replaceAll(end2hide, Keyword.FALSE);
				 sync = "("+tmpt + Keyword.OR + tmpf + ")";
				 finalcons.setSyncCons(sync);
				 Reo.logInfo("hideCommonEnd: new sync constrint after hiding "+end2hide+" = "+sync);
		}
		if (data != null && data.length()>0){//TODO
			/* String tmpt = sync.replaceAll(end2hide, Keyword.TRUE);
			 String tmpf = sync.replaceAll(end2hide, Keyword.FALSE);
			 sync = "("+tmpt + Keyword.OR + tmpf + ")";
			 finalcons.setSyncCons(sync);*/
			 //Reo.logInfo("hideCommonEnd: new sync constrint after hiding "+end2hide+" = "+sync);
			System.err.println("TODO: hiding data vars");
		}
		if (prio != null && prio.length()>0){
			 
		}
		//if (flatFinal.trim().length()==0) 
			//finalcons = "";
		//Constraint semantics = new Constraint();
		// Step 1: Synchronization Constraint
		
		/*
		 * if (!(v instanceof PriorityVisitor)) { for (Primitive p :
		 * mod.getAllPrimitives()) { Constraint tempres =
		 * generateConstraints(p); if (tempres != null && tempres.length() > 0)
		 * { if (res.length() > 0) res += Keyword.AND; res += "(" + tempres +
		 * ")"; } } System.out.println("Constraints of primitives: " + res); }
		 * for (Node n : mod.getAllNodes()) { String tempres =
		 * generateConstraints(p); if (tempres != null && tempres.length() > 0)
		 * { if (res.length() > 0) res += Keyword.AND; res += "(" + tempres +
		 * ")"; } }
		 */
		// System.out.println(this.getClass().getSimpleName() + " Final: " +
		// res);
		//return res;
	}

	private PrimitiveEnd findCommonEnd(Connectable prev, Connectable curr) {
		String name1 = "";
		List<PrimitiveEnd> coms = new ArrayList<PrimitiveEnd>();
		for (PrimitiveEnd end1 : prev.getAllEnds()) {
			for (PrimitiveEnd end2 : curr.getAllEnds()) {
				name1 = info.getNotNullName(end1);
				if (name1.compareTo(info.getNotNullName(end2)) == 0) {
					coms.add(end1);
				}
			}
		}
		if (coms.size() != 1)
			Reo.logInfo("Shared ends# " + " between " + pre + " and " + curr
					+ " = " + coms.size());
		else {
			Reo.logInfo("Shared end " + " between " + pre + " and " + curr
					+ " = " + name1);
			return coms.get(0);
		}
		return null;
	}

	public Constraint getFinalEncoding() {
		return finalcons;
	}
}
