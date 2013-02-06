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
package org.ect.codegen.ea2mc.providers;

public class DElmNode<E> {
	public DElmNode<E> prev = null;
	public DElmNode<E> next = null;
	public DElmNode<E> left = null;
	public DElmNode<E> right = null;
	public E data = null;
	public boolean common = false;
	public boolean dummyTail = false;
	public boolean visited = false;
	
	public DElmNode<E> getLastright(){
		DElmNode<E> temp = this;
		
		while(temp.right!=null){
			temp= temp.right;
		}
		
		return temp;
	}
	
	public void setVisited(boolean value){
		visited = value;
	}
	
	public boolean getVisited(){
		return visited; 
	}
	
	public E getData(){
		return data;
	}
	
	public void setData(E value){
		data = value;
	}
	
	public void setCommon(boolean value){
		common = value;
	}
	
	public boolean getCommon(){
		return common;
	}
	
	public boolean getDummyTail(){
		return dummyTail;
	}
	
	public void setDummyTail(boolean value){
		dummyTail = value;
	}
	
	public boolean isHead(){
		return (this.getData()==null);
	}
}
