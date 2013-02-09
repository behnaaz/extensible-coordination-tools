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

//import org.ect.reo.distengine.common.Utils._

case class EndUID(val l:List[String]) {
  def ==(other:EndUID) = {
    val n = length - (other.length)
    if (n > 0) 
      (l drop n) == (other.l)
    else
      l == (other drop (-n))  
  }
  
  def length = l.length
  private def drop(n:Int) = l.drop(n)
  def endname = l.last
  def primname = {
    assert(length >= 2,"Primitive name not defined in EndUID")
    l.takeRight(2).head
  }
  def renameend(s:String => String): EndUID = {
    if (!l.isEmpty) EndUID(l.init ++ List(s(l.last)))
    else this
  }
  
  override def toString = l.mkString("_")
}

object EndUID {
  def apply[A](xs: String*): EndUID = new EndUID(xs.toList) 
}
