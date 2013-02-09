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

import collection.mutable.{ Map => MutMap }
import collection.mutable.{ Set => MutSet }
import collection.mutable.ArrayBuffer

import org.ect.reo.distengine.common.EndUID

object CNF {
  type CNF = Iterable[List[Int]]
//  type Solution = Map[common.EndUID,Boolean]
  
  def fv(cnf: CNF.CNF): Collection[Int] = {
    var s: MutSet[Int] = MutSet()
    for (l <- cnf; v <- l)
      if (v<0) s += (v * (-1))
      else     s += v
    s
  }
  
  def not(s:Any) = (-1) * s.hashCode
  //def v(s:String) = s.hashCode
}

class CNF(val cnf:CNF.CNF, val vars: List[EndUID]) {
  override def toString: String = {
    cnf.map(_.mkString("[",",","] ")).mkString(" ") +
    "- " + vars.mkString(",")
  }

}

// store the model and the variables.
class CNFModel {
  val clauses: ArrayBuffer[Array[Int]] = new ArrayBuffer // normalized
  var varnumber: MutMap[EndUID,Int] = MutMap()
  var varname: ArrayBuffer[EndUID] = new ArrayBuffer
  private var seed = 0
  
  def nvars = seed
  def nclauses = clauses.size
  
  def +=(other:CNF) : CNFModel = {
    def update(i: Int): Int = {
      val sign = if (i<0) -1 else 1
      var n = sign * i
      val v = other.vars(n-1)
      if (!(varnumber contains v)) {
        varname += v
        seed = seed+1
        varnumber(v) = seed
      }
      varnumber(v) * sign
    }
      
    for (clause <- other.cnf) {
    	var newclause: List[Int] = Nil
//      for (i <- 0 until clause.size) {
//        clause(i) = update(clause(i))
//      }
// 		clauses += clause
//		for (i <- (clause.size-1) until -1 by -1)
        for (i <- 0 until clause.size) // inverting order, but it's ok
          newclause = update(clause(i)) :: newclause
        clauses += ((Array(): Array[Int]) ++ newclause)
    }
    this
  }
  
//  /* Does not clone single clauses, but everything else it clones. */
//  def softclone = {
//    val res = new CNFModel
//    for (cl <- clauses)
//      res.clauses += cl
//    res.varnumber = varnumber
//    res.varname = varname
//    res
//  } 
    
  def show : String = {
    clauses.map(_.mkString("[",",","] ")).mkString(" ") +
    "-- " + varname.mkString(",")
  }
  
  def addSomeFlow = {
    clauses += Array.range(1,seed+1)
    this
  }
  
  override def toString = {
    show
  }
}
