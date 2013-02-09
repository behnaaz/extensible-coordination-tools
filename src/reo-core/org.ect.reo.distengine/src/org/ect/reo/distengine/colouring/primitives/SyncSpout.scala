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
import collection.mutable.HashMap
import collection.mutable.HashSet
import scala.collection.Set
import org.ect.reo.distengine.colouring._


/**
   * Definition of a synchronous spout channel, using the semantics
   * provided by <code>org.ect.reo.distengine.colouring.CC</code>.
   */
class SyncSpout(prim:PrimID,logger:Logger) extends Primitive[org.ect.reo.distengine.colouring.CC](logger) {
  val name = prim
  val endl = prim + "-l"
  val endr = prim + "-r"
  val ends : HashMap[EndLID,AbstractEnd] = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)
  
  val a : AbstractEnd = new End(endl,Output,this)
  val b : AbstractEnd = new End(endr,Output,this)
  val auid = EndUID(prim,endl)
  val buid = EndUID(prim,endr)


  def source(ct:Colouring,e:EndUID) : DataSource = {
    return new Right(null)
  }
  
  def updateStep(c:Colouring) = {}
  
  ends(endl) = a
  ends(endr) = b
  
  // the ColTable of the sspout channel
  val col1 = new Colouring
  col1(auid) = Flow
  col1(buid) = Flow
  behaviour.value += col1
  val col2 = new Colouring
  col2(auid) = FromOut
  col2(buid) = ToOut
  behaviour.value += col2
  val col3 = new Colouring
  col3(auid) = ToOut
  col3(buid) = FromOut
  behaviour.value += col3  
  
}
