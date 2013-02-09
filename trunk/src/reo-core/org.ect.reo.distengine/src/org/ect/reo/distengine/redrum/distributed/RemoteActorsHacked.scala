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
 * Manual serialization of data into byte arrays. Not used anymore, since it
 * is enough to update the classloader of each remote actor by using:
 * <pre>
 * RemoteActor.classLoader = getClass().getClassLoader()
 * </pre>
 * 
 * 
 * @author Jose Proenca
 * @date   2008/05/01
 *
 */
package org.ect.reo.distengine.redrum.distributed;

import scala.actors.Actor._
import java.io._

object RemoteActorsHacked {


  def serialize(o: AnyRef): Array[Byte] = {
    val bos = new ByteArrayOutputStream()
    val out = new ObjectOutputStream(bos)
    out.writeObject(o)
    out.flush()
    bos.toByteArray()
  }
 
  def deserialize(bytes: Array[Byte]): AnyRef = {
    val bis = new ByteArrayInputStream(bytes)
    val in = new ObjectInputStream(bis)
    in.readObject()
  }

  def reactRemote(f : PartialFunction[Any,Unit]) : PartialFunction[Any,Unit] =
    react {
    new PartialFunction[Any,Unit] {
     
    def from(a : Any) = a match {
      case x:Array[Byte] => deserialize(x)
      case _ => a }
    def apply(a : Any) = f(from(a))
    def isDefinedAt(a : Any) = f.isDefinedAt(from(a))
  }}
}
