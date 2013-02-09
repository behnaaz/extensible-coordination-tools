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
 * Distributed Reo
 *
 * @author Jose Proenca
 */

package org.ect.reo.distengine.common;
//import scala.actors.AbstractActor


import org.ect.reo.distengine.common.Utils._
import scala.collection.Set
import scala.actors.Actor

trait AbstractPrimitive[+Behaviour <: AbstractBehaviour] extends scala.actors.AbstractActor {
  val name : PrimID
  val ends : Ends
  val behaviour : Behaviour
  type Beh  = behaviour.Beh
  type Step = behaviour.Step
    
  // data dependencies -> Part of step now!
  def source (step:Step,outputend:EndUID) : DataSource
  
  /**
   * This function is executed after the step, and should call the updateStep
   * from the behaviour.
   */
  def updateStep (b:Step) : Unit
  
  def status : String


  ///// ACTOR METHODS /////
  def ! (msg : Any) : Unit
  
  def start() : Any

  // PREDEFINED METHODS/VARS //
  def state : PrimState // = Idle //Suspended(HashSet(""))
  
}
