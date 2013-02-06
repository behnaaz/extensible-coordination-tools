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

import org.ect.codegen.reo2constraints.visitor.ContextVisitor;
import org.ect.codegen.reo2constraints.visitor.DataConstraintVisitor;
import org.ect.codegen.reo2constraints.visitor.PriorityVisitor;
import org.ect.codegen.reo2constraints.visitor.SyncVisitor;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;

import org.ect.reo.semantics.ReoScope;
import org.ect.reo.util.PrimitiveEndNames;

public class ElementConverter {
	private SyncVisitor syncVisitor = null;
	private ContextVisitor cntxVisitor = null;
	public ContextVisitor getCntxVisitor() {
		return cntxVisitor;
	}


	private DataConstraintVisitor dataVisitor = null;
	private PriorityVisitor pritVisitor = null;
	private StateManager statemgr;
	private Preprocessing info = null;
	
	
	public ElementConverter(Preprocessing info, StateManager statemgr) {
		this.info  = info;
		this.statemgr = statemgr;
		initialize();				
	}
	

	public ElementConverter(PrimitiveEndNames names) {
		// TODO Auto-generated constructor stub
	}

	public void setScope(ReoScope element) {
		// TODO Auto-generated method stub
		
	}

	public Process convert(Connectable element, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initialize(){
		if (info.isContextSensitive())
			cntxVisitor = new ContextVisitor(statemgr, info);
		else
			syncVisitor = new SyncVisitor(statemgr, info);
		if (info.isDataSensitive())
			dataVisitor = new DataConstraintVisitor(statemgr, info);
		if (info.isPrioritySensitive())
			pritVisitor = new PriorityVisitor(info, this);
	}

	
	public Constraint generateConstraints(Node n) {
		Constraint semantics = new Constraint();
		if (n.getName() == null) {
			System.out.println("***WARNING! " + n + " has no name!"); 
		}
		if (info.isContextSensitive()){
			semantics.setCntxCons(cntxVisitor.visit(n));
		}else{
			String cnf = syncVisitor.visit(n);
			semantics.setSyncCons(cnf);
		}
		if (info.isDataSensitive()){
			semantics.setDataCons(dataVisitor.visit(n));
		}
		if (info.isPrioritySensitive()){
			semantics.setPrioCons(pritVisitor.visit(n));
		}
		if (info.isTimeSensitive()){
			
		}
		return semantics;
	}
	
	
	public Constraint generateConstraints(Primitive p) {
		Constraint semantics = new Constraint();
		if (p.getName() == null) {
			System.out.println("***WARNING! " + p + " has no name!");
		}
		if (p instanceof Sync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((Sync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((Sync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((Sync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((Sync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof SyncDrain){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((SyncDrain) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((SyncDrain) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((SyncDrain) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((SyncDrain) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof LossySync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((LossySync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((LossySync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((LossySync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((LossySync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof Writer){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((Writer) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((Writer) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((Writer) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((Writer) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof Reader){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((Reader) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((Reader) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((Reader) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((Reader) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof FIFO) {
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((FIFO) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((FIFO) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((FIFO) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((FIFO) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof Filter){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((Filter) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((Filter) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((Filter) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((Filter) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof SyncSpout){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((SyncSpout) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((SyncSpout) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((SyncSpout) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((SyncSpout) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof AsyncDrain){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((AsyncDrain) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((AsyncDrain) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((AsyncDrain) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((AsyncDrain) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof AsyncSpout){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((AsyncSpout) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((AsyncSpout) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((AsyncSpout) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((AsyncSpout) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof Timer){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((Timer) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((Timer) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((Timer) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((Timer) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof PrioritySync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((PrioritySync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((PrioritySync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((PrioritySync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((PrioritySync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof BlockingSync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((BlockingSync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((BlockingSync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((BlockingSync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((BlockingSync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof BlockingSinkSync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((BlockingSinkSync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((BlockingSinkSync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((BlockingSinkSync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((BlockingSinkSync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		else if (p instanceof BlockingSourceSync){
			if (info.isContextSensitive()){
				semantics.setCntxCons(cntxVisitor.visit((BlockingSourceSync) p));
			}else{
				semantics.setSyncCons(syncVisitor.visit((BlockingSourceSync) p));
			}
			if (info.isDataSensitive()){
				semantics.setDataCons(dataVisitor.visit((BlockingSourceSync) p));
			}
			if (info.isPrioritySensitive()){
				semantics.setPrioCons(pritVisitor.visit((BlockingSourceSync) p));
			}
			if (info.isTimeSensitive()){
				
			}
		}
		return semantics;
	}
}
