/*
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

package org.ect.reo.distengine.sat


//import org.ect.reo.distengine.colouring.ColouringTable
import org.ect.reo.distengine.common.{EndUID, AbstractBehaviour}

class SAT(constraints: ConstraintList) extends AbstractBehaviour {
  type Step = ConstraintSol
  type Beh = ConstraintList
  var value = constraints
  def empty = ConstraintList.zero
  def unit = ConstraintList.unit
  def nostep =
    new ConstraintSol(scala.collection.immutable.HashSet[EndUID]())
  def join(c1:ConstraintList,c2:ConstraintList) = {
    c1 ++ c2
  }
}

