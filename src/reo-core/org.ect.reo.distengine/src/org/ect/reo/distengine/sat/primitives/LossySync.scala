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
package org.ect.reo.distengine.sat.primitives;

//import org.ect.reo.distengine.colouring._;
//import org.ect.reo.distengine.colouring.
import org.ect.reo.distengine.common._;
import org.ect.reo.distengine.common.Utils._;
import org.ect.reo.distengine.redrum.{Primitive, End}
import collection.mutable.HashMap
import collection.mutable.HashSet
import scala.collection.Set
import org.ect.reo.distengine.sat.{CNF, ConstraintSol, ConstraintList, SAT}


class LossySync(prim:PrimID,logger:Logger) extends Primitive[SAT](logger) {
  val name = prim
  val endin  = prim + "-i"
  val endout = prim + "-o"
  val ends : HashMap[EndLID,AbstractEnd] = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new SAT(new ConstraintList(Nil))
  
  val a : AbstractEnd = new End(endin,Input,this)
  val b : AbstractEnd = new End(endout,Output,this)
  val auid = EndUID(prim,endin)
  val buid = EndUID(prim,endout)


  def source(sol:ConstraintSol,e:EndUID) : DataSource = {
    val ret : HashSet[EndUID] = new HashSet
    if ((e == buid) && (sol hasFlow e)) ret += auid
    return new Left(ret)
  }

  def updateStep(c:ConstraintSol) = {}

  ends(endin) = a
  ends(endout) = b
  
  // the constraints of the sync channel
  val asr = EndUID(name,endin+"$sr")
  val ask = EndUID(name,endin+"$sk")
  val bsr = EndUID(name,endout+"$sr")
  val bsk = EndUID(name,endout+"$sk")
  val constr = new CNF(
    List(List(-3,1), List(1,-3), List(1,-2), List(1,4), List(3,-4,-1), List(3,1,-1))
    ,List(auid,asr,buid,bsk))

  behaviour.value = new ConstraintList(List(constr))  
}
