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

import collection.mutable.{ Set => MutSet }

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import org.ect.reo.distengine.common.EndUID

object Solver {
  def main(args : Array[String]) : Unit = {
  }
  
  def solve(cnf: CNFModel) : Option[Set[EndUID]] = {
     val MAXVAR: Int = cnf.nvars
     val NBCLAUSES: Int = cnf.nclauses
     var flowset: MutSet[EndUID] = MutSet()
     
     val solver: ISolver = SolverFactory.newDefault()
     
     solver.newVar(MAXVAR)
     solver.setExpectedNumberOfClauses(NBCLAUSES)
     try {
       for (cl <- cnf.clauses) {
	      solver.addClause(new VecInt(cl))
	    }

        // Solving stage 
	    val model = solver.findModel
	    val res = Map[EndUID,Boolean]()
        
	    if (model != null) {
//	      println("Problem is satisfiable!!")
          for (i <- model) {
            if (i>0) flowset += cnf.varname(i-1)
            //else     noflowset += cnf.varname(i-1)
          }
	    } else {
	      return None
	      //println("Not satisfiable...")
	    }
	  } catch {
	    case (e: ContradictionException) =>
	      return None
//	      println("adding contraditory clause...")
//          println("Not satisfiable...")
////	      e.printStackTrace()
	    case (e: TimeoutException) =>
	      return None
//	      System.out.println("Timed out....")
//	      e.printStackTrace()
	  }
      Some(Set() ++ flowset)
   }
  
  
   def time(cnf:CNFModel) : Unit = {
     val MAXVAR: Int = cnf.nvars
     val NBCLAUSES: Int = cnf.nclauses
     
     // START TIMER BEFORE ADDNG CLAUSES TO THE MODEL!
     val start: Long = System.currentTimeMillis();        
     
     val solver: ISolver = SolverFactory.newDefault()
     
     solver.newVar(MAXVAR)
     solver.setExpectedNumberOfClauses(NBCLAUSES)
     try {
       for (cl <- cnf.clauses) {
	      solver.addClause(new VecInt(cl))
	    }
	  } catch {
	    case (e: ContradictionException) =>
	      println("adding contraditory clause...")
	      e.printStackTrace()
	  }
      // Solving stage 
	  try {
//	    val start: Long = System.currentTimeMillis();        
        solver.isSatisfiable()
	    val stop: Long = System.currentTimeMillis()
        print("" + (stop - start))
	  } catch {
	    case (e: TimeoutException) =>
	      println("Timed out....")
	      e.printStackTrace()
          false
	  }
   }

}
