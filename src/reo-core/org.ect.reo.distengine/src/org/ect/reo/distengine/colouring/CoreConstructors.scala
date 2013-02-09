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
package org.ect.reo.distengine.colouring;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.redrum._

import collection.mutable.HashMap


object End {
  /** Creates an end of type 'End' whose behaviour is given
   * by a concrete behaviour (CC) */
  def apply(ident:String,io:IOType,primitive:Primitive[CC]) : End[CC] = {
    //new InitEnd[CC](ident,io,primitive)
    new End[CC](ident,io,primitive)
  }
}

object Primitive {
  /**
   * create a primitive given a CT, using an extensible primitive with initiators.
   */
  def apply(ident: String, allEnds: Ends, ct: ColouringTable,
            sr: Colouring => EndUID => DataSource,upSt: Colouring=> Unit,logger:Logger)
           : Primitive[CC] = {
    //new InitPrimitive[CC] {
    new Primitive[CC](logger) {
      val name = ident
      val ends = allEnds
      val behaviour = new CC(ct)
      def source (ct:Colouring,end:EndUID) = sr(ct)(end)
      def updateStep (x:Colouring) = upSt(x)
    }
  }
}
