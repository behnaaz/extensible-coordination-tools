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
package org.ect.reo.distengine.redrum.deployment

trait ManagerTrait {
  
  def updateStatus(reply:String)
  def updatePrimitives(enginek:String, reply:Array[String])
  def updateEngines(reply:Array[String])
  def updateTimeOut()
  def updateError(msg:String)

}
