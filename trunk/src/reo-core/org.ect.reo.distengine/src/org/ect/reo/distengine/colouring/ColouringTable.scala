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
 * ColouringTable.java
 *
 * Based on Colour.java, part of ReoLite,
 * Created on June 17, 2005, 11:35 AM
 *
 * @author dave
 */

package org.ect.reo.distengine.colouring;


import collection.mutable.HashSet

import org.ect.reo.distengine.common.BehaviourRep

object ColouringTable {
  def zero: ColouringTable = new ColouringTable

  def unit: ColouringTable = {
    val t = new ColouringTable
    t += new Colouring
    return t
  }
}

class ColouringTable extends HashSet[Colouring] with BehaviourRep[Colouring] {
            
    def join(other:ColouringTable) : ColouringTable = {
        val result = new ColouringTable();        
        // for each pair of colourings, check compatibility
        // if compatible, combine and put in new table
        // return new table
        for (myc <- this)
          for (oc <- other)
            if (myc compatible oc)
              result += myc.union(oc);
        result
    }
        
    def ++ (other:ColouringTable) : ColouringTable = {
      join(other)
    }
    
    def pickone() : Option[Colouring] = {
      var returnval : Option[Colouring] = None
      // to do, make random (not sure if consistency is require... check first!)
      // So far, returning the first behaviour with some flow, if any.
      for (colouring:Colouring <- this)
        if (colouring.valuesIterator contains Flow)
          return Some(colouring)
        else returnval = Some(colouring)
      return returnval
    }
    
    def getSingleton : ColouringTable = {
      val ct = new ColouringTable()
      pickone match {
        case Some(col) => ct += col
        case _ => {}
      }
      return ct
    }
    
    def hasFlow(end:org.ect.reo.distengine.common.EndUID) : Boolean = {
      pickone match {
        case Some(tb) => {
          assert(tb contains end, "End '"+end+"' not found in step '"+tb+"'")
          return (tb(end) == Flow)
        }
        case None     => false
      }
    }
    
    override def  toString() : String = {
      "CT<"+this.size+" cols> - "+(
      if (exists(_.exists(_._2 == Flow))) "[[ FLOW ]]"
      else "[[ NO-FLOW ]]")
    }
    
    def deepToString: String = {
        var result = "Colouring Table ("+this.size+")\n/++++++++++\\\n";
        for (col <- this) {
          result = result + col.toString() + "|++++++++++|\n"
        }
        result = result + "\\++++++++++/\n"
        return result;
    }
      
}

// SAVE HISTORY OF COLOURING TABLES -- used for debugging only
object ColouringTableHistory {
  def zero: ColouringTableHistory = new ColouringTableHistory

  def unit: ColouringTableHistory = {
    val t = new ColouringTableHistory
    t += new Colouring
    return t
  }
}


class ColouringTableHistory extends ColouringTable {
  var left: Option[ColouringTable] = None
  var right: Option[ColouringTable] = None
  var level: Int = 0
  
  def join(other:ColouringTableHistory) : ColouringTableHistory = {
    val res = new ColouringTableHistory();        
    
    for (myc <- this)
      for (oc <- other)
        if (myc compatible oc)
          res += myc.union(oc);

    res.left = Some(this)
    res.right = Some(other)
    res.level = (level max other.level)+1
    res
  }

  
  override def deepToString = 
    if (level == 0) toString
    else {
      if (left.isDefined && right.isDefined) "( "+left.get.deepToString+" + "+right.get.deepToString+" )"
      else "bug bug bug bug bug bug bug bug"
    }
    //(List.make(level,"==")./:("")(_+_)) + toString +
    //(if (left.isDefined) "<"+left.get.deepToString else "<"+(List.make(level,"--")./:("")(_+_))+"\n") +
    //(if (right.isDefined) ">"+right.get.deepToString else ">"+(List.make(level,"--")./:("")(_+_))+"\n")

  
}
