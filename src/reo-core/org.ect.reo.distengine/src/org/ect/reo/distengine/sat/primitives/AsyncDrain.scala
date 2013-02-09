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


/**
 * Definition of an asynchronous drain channel, using the semantics
 * provided by <code>org.ect.reo.distengine.colouring.CC</code>.
 */
class AsyncDrain(prim:PrimID,logger:Logger) extends Primitive[SAT](logger) {
   
  val name = prim
  val endl = prim + "-l"
  val endr = prim + "-r"
  val ends : HashMap[EndLID,AbstractEnd] = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new SAT(new ConstraintList(Nil))
  
  val a : AbstractEnd = new End(endl,Input,this)
  val b : AbstractEnd = new End(endr,Input,this)
  val auid = EndUID(prim,endl)
  val buid = EndUID(prim,endr)


  def source(sol:ConstraintSol,e:EndUID) : DataSource = {
    val ret : HashSet[EndUID] = new HashSet
    return new Left(ret)
  }
  
  def updateStep(c:ConstraintSol) = {}
  
  ends(endl) = a
  ends(endr) = b
  
  // the constraints of the asyncdrain channel
  val asr = EndUID(name,endl+"$sr")
  val ask = EndUID(name,endl+"$sk")
  val bsr = EndUID(name,endr+"$sr")
  val bsk = EndUID(name,endr+"$sk")
  // not(a and b) and (a -> bsr) and (b -> asr) and
  // (not(a or b) -> ((asr and not(bsr)) or (not(asr) and bsr)))
  val constr = new CNF( 
    List(List(-3,-1),List(-3,4),List(-1,2),List(3,1,-4,-2),List(3,1,-4,4),List(3,1,2,-2),
         List(3,1,2,4)),
    List(buid,asr,auid,bsr))
  
  behaviour.value = new ConstraintList(List(constr))  
}
