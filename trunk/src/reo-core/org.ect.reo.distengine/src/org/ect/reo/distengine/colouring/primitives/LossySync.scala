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
package org.ect.reo.distengine.colouring.primitives;

//import org.ect.reo.distengine.colouring._;
//import org.ect.reo.distengine.colouring.
import org.ect.reo.distengine.common._;
import org.ect.reo.distengine.common.Utils._;
import org.ect.reo.distengine.redrum.{Primitive, End}
import org.ect.reo.distengine.colouring._
import collection.mutable.HashMap
import collection.mutable.HashSet
import scala.collection.Set



class LossySync(prim:PrimID,logger:Logger) extends Primitive[org.ect.reo.distengine.colouring.CC](logger) {
  val name = prim
  val endin  = prim + "-i"
  val endout = prim + "-o"
  val ends : HashMap[EndLID,AbstractEnd] = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)
  
  val a : AbstractEnd = new End(endin,Input,this)
  val b : AbstractEnd = new End(endout,Output,this)
  val auid = EndUID(prim,endin)
  val buid = EndUID(prim,endout)


  def source(ct:Colouring,e:EndUID) : DataSource = {
    val ret : HashSet[EndUID] = new HashSet
    if ((e == buid) && (ct hasFlow e)) ret += auid
    return new Left(ret)
  }

  def updateStep(c:Colouring) = {}

  ends(endin) = a
  ends(endout) = b
  
  // the ColTable of the lossy sync channel
  val col1 = new Colouring
  col1(auid) = Flow
  col1(buid) = Flow
  behaviour.value += col1
  val col2 = new Colouring
  col2(auid) = FromIn
  col2(buid) = ToOut
  behaviour.value += col2
  val col3 = new Colouring
  col3(auid) = Flow
  col3(buid) = FromOut
  behaviour.value += col3  
}
