/*
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.ect.reo.distengine.sat

import collection.mutable.ArrayBuffer

import org.ect.reo.distengine.common.BehaviourRep
import org.ect.reo.distengine.common.EndUID

object ConstraintList {
  def apply(c: List[CNF]) = new ConstraintList(c)
  
  def unit: ConstraintList = new ConstraintList(Nil)
  
  def zero: ConstraintList = new ConstraintList(List(new CNF( 
    List(List(-1,1)),List(EndUID("")))))
 }

class ConstraintList(val constraints: List[CNF]) extends BehaviourRep[ConstraintSol] {
    def join(other:ConstraintList): ConstraintList =
      	ConstraintList(constraints ::: other.constraints)
        
    def ++ (other:ConstraintList) : ConstraintList =
      join(other)
    
    def pickone() : Option[ConstraintSol] = {
      val model = new CNFModel
      for (cnf <- constraints) {
//        println("about to add "+cnf) 
        model += cnf
      }
//      println("THE MODEL: "+model)
      for (sol <- Solver.solve(model))
        yield new ConstraintSol(sol)
    }
    
    def hasFlow(end:EndUID) : Boolean = {
      pickone match {
        case Some(tb) => tb.hasFlow(end)
        case None     => false
      }
    }
    
    override def  toString() : String = {
      "["+constraints.size+" conjunction(s)]"
//      deepToString
    }
    
    def deepToString: String = {
      constraints.mkString(""," || ","")
    }     
}