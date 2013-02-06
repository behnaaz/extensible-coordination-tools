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
/**
 * @author Behnaz Changizi
 *
 */
public class Constraint {
	String syncCons="";
	boolean useOrbitalFormat = true;
	public boolean isUseOrbitalFormat() {
		return useOrbitalFormat;
	}
	public void setUseOrbitalFormat(boolean useOrbitalFormat) {
		this.useOrbitalFormat = useOrbitalFormat;
	}
	public String getSyncCons() {
		return syncCons;
	}
	public void setSyncCons(String syncCons) {
		this.syncCons = (syncCons!=null)?syncCons:"";
	}
	public String getDataCons() {
		return dataCons;
	}
	public void setDataCons(String dataCons) {
		this.dataCons = (dataCons!=null)?dataCons:"";
	}
	public String getCntxCons() {
		return cntxCons;
	}
	public void setCntxCons(String cntxCons) {
		this.cntxCons = (cntxCons!=null)?cntxCons:"";
	}
	public String getPrioCons() {
		return prioCons;
	}
	public void setPrioCons(String prioCons) {
		this.prioCons = (prioCons!=null)?prioCons:"";
	}
	String dataCons="";
	String cntxCons="";
	String prioCons="";
	
	//TODO
	public String toString(){
		String res="";
		String and = (useOrbitalFormat)?Keyword.SYM_AND:Keyword.AND;
		if (syncCons.length()>0)
			res+="("+syncCons+")";
		if (dataCons.length()>0){
			if (res.length()>0)
				res += and;
			res += "("+dataCons+")";
		}
		if (cntxCons.length()>0){
			if (res.length()>0)
				res += and;
			res += "("+cntxCons+")";
		}
		if (prioCons.length()>0){
			if (res.length()>0)
				res += and;
			res += "("+prioCons+")";
		}
		System.out.println("Constraint.toString() output: "+res);
		return res;
	}
	
}
