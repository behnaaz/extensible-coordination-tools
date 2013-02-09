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
package org.ect.reo.distengine.common;

import collection.mutable.HashMap
import org.ect.reo.distengine.common.Utils.DataSource


// subclasses: ColTable
/** Representation of the possible behaviour of a connector:
 * all possible solutions for a synchronous step.
 */
trait BehaviourRep[+Step] {
  //def hasFlow(end:EndUID) : Boolean
  /** Returns a possible solution (Step) from the current description of the behaviour.*/
  def pickone : Option[Step]
  def deepToString : String  
}

/** Representation of a single solution for a synchronous step. */
trait StepRep {
  /** Returns true if the current solution allows data to flow on the given end. */
  def hasFlow(end:EndUID) : Boolean
  //def source (outputend:EndUID) : DataSource
}

// subclasses: 

// subclasses: Connector Colouring (CC)
//abstract class Behaviour[+Rep <: BehRep] {
/** Abstract definition of a semantical model for the behaviour.
 * Also includes a value field for an instance of a behaviour representation.
 * Maybe this last part should not be part of this class.

 * @see BehaviourRep
 * @see StepRep
 */
abstract class AbstractBehaviour {
  type Step <: StepRep
  type Beh  <: BehaviourRep[Step]
  def join (x:Beh,y:Beh) : Beh
  def empty : Beh
  def unit  : Beh
  def nostep: Step
  var value : Beh
  def step  : Option[Step] = value.pickone          // returns the current step (1st entry on the CT)
  //def updateStep(r:Rep) : Unit // updates the behaviour
  def +++ (rep:Beh) : Beh = {
    join(value,rep) 
  }
}




 
/////////////////////////////////////
/////////////////////////////////////

// Constructors for colouring tables
class TestColouring(val value:Array[String]) extends StepRep {
  def hasFlow(e:EndUID) = false
  def deepToString = value.deepToString
  def source(e:EndUID) = Left(Set())
}

object ColTable {
  def apply() = new ColTable { var table = new TestColouring(Array():Array[String]) }
  def apply(t:Array[String]) = new ColTable { var table = new TestColouring(t)}
  def apply(t:String*) = new ColTable { var table = new TestColouring(t.toArray)}
}

abstract class ColTable extends BehaviourRep[TestColouring] {
  var table : TestColouring //Array[String]
  def hasFlow(e:EndUID) = false
  def pickone = Some(table)
  def ++(c:ColTable) : ColTable = ColTable(table.value++c.table.value)
  def deepToString : String = table.deepToString
  override def toString = table.toString
}

class TestCC(tb:ColTable) extends AbstractBehaviour {
  type Step = TestColouring
  type Beh = ColTable
  var value = tb
  def empty = ColTable()
  def unit = ColTable(Array(""))
  def nostep = new TestColouring(Array():Array[String])
  //def updateStep(ct:ColTable) = {}
  def join(tb1:ColTable,tb2:ColTable) = ColTable(tb1.table.value ++ tb2.table.value)
  //def deepToString = tb.table.deepToString
}
