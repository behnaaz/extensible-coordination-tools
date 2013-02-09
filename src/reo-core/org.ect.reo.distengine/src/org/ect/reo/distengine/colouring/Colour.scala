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
 * Colour.scala
 *
 * Based on Colour.java, part of ReoLite,
 * Created on June 17, 2005, 11:12 AM
 *
 * @author dave
 */

package org.ect.reo.distengine.colouring;


trait Colour {
  def compatible(other: Colour): Boolean 
  def join(other: Colour): Colour = 
  /*  assert(compatible(other)) */
    if (this == other) return this
    else return NoFlow
  
  def toString(nodeOnLeft: Boolean): String
}


@serializable object Flow extends Colour {
  override def compatible(other: Colour): Boolean =
     (other == Flow)
  
  override def hashCode: Int = 37
  override def toString: String = "______" 
  override def toString(nodeOnLeft: Boolean): String =
    return toString();
 
}

@serializable object ToIn extends Colour {
  override def compatible(other: Colour): Boolean =
    (other == FromOut) || (other == ToOut) || (other == NoFlow) || (other == ToIn)
  override def hashCode: Int = 41 
  override def toString: String = "I..<..." 
  override def toString(nodeOnLeft: Boolean): String =
    if (nodeOnLeft) {  "I..<..." } else { "...>..I" }
}

@serializable object ToOut extends Colour {
  override def compatible(other: Colour): Boolean =
    (other == FromIn) || (other == ToIn) || (other == NoFlow) || (other == ToOut)
  override def hashCode: Int = 17
  override def toString: String = "...>..O" 
  override def toString(nodeOnLeft: Boolean): String =
    if (nodeOnLeft) {  "O..<..." } 
    else { "...>..O" }
}

@serializable object FromIn extends Colour {
  override def compatible(other: Colour): Boolean =
    (other == ToOut) || (other == NoFlow) || (other == FromIn)
  override def hashCode: Int = 101 
  override def toString: String = "...<..I"
  override def toString(nodeOnLeft: Boolean): String =
    if (nodeOnLeft) { "I..>..." } 
    else { "...<..I" }
}

@serializable object FromOut extends Colour {
  override def compatible(other: Colour): Boolean =
    return (other == ToIn) || (other == NoFlow) || (other == FromOut)
  override def hashCode: Int = 103 
  override def toString: String = "O..>..." 
  override def toString(nodeOnLeft: Boolean): String = 
    if (nodeOnLeft) { "O..>..." } 
    else { "...<..O" }
}


// used internally only
@serializable object NoFlow extends Colour {
  override def compatible(other: Colour): Boolean =
    (other != Flow)
  override def hashCode: Int = 67
  override def toString: String = "......" 
  override def toString(nodeOnLeft: Boolean): String =
    return toString()
}
