/**
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *
 * Connector Colouring Semantics<br>
 *
 * Import (and adaptation to Scala) of the code developed by
 * Dave Clarke, in Reolite-0.0.4
 *
 * @author Jose Proenca and Dave Clarke
 * @date   2007/11/09
 *
 */
  
package org.ect.reo.distengine.colouring;

//import org.ect.reo.distengine.colouring.ColouringTable
import org.ect.reo.distengine.common.AbstractBehaviour

class CC(tb:ColouringTable) extends AbstractBehaviour {
  type Step = Colouring
  type Beh = ColouringTable
  var value = tb
  def empty = ColouringTable.zero
  def unit = ColouringTable.unit
  def nostep = new Colouring
  def join(tb1:ColouringTable,tb2:ColouringTable) = {
    val res = tb1 ++ tb2
    //if (tb1.size * tb2.size != 0 && res.size == 0)
    //  println("ERROR: joining tables gives no behaviour!\n"+res.deepToString)
    res
  }
  /*def step : ColouringTable = {
    val ct = new ColouringTable()
    tb.pickone match {
      case Some(col) => ct += col
      case _ => {}
    }
    return ct
  }*/
  
  // evolution of the CT
  //var ns: Colouring => ColouringTable = { x:Colouring => value }
  //def updateStep(ct:ColouringTable) = ns(ct.pickone)
//  def +++(tb:ColTable) : ColTable = table ++ tb
  //def deepToString = tb.table.deepToString
}

