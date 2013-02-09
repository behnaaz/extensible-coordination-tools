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


class ChnTestCC(prim:PrimID, endin:EndLID, endout:EndLID) extends Primitive[TestCC](new ConsoleLogger) {
  val name = prim
  val ends : HashMap[EndLID,AbstractEnd] = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new TestCC(ColTable(""))
  def source (x:TestColouring,end:EndUID) : DataSource = new Left(new HashSet)
  def updateStep(c:TestColouring) = {}

  val a : AbstractEnd = new End(endin,Input,this)
  val b : AbstractEnd = new End(endout,Output,this)

  ends(endin) = a
  ends(endout) = b
  
  behaviour.value = ColTable("<"+endin+","+endout+">")  
}
