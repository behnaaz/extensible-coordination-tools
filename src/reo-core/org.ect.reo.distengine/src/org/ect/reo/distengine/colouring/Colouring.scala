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
 * org.ect.reo.distengine.colouring.scala
 *
 * Based on org.ect.reo.distengine.colouring.java, in ReoLite,
 * Created on June 17, 2005, 11:26 AM
 *
 * @author dave
 */

package org.ect.reo.distengine.colouring;

import org.ect.reo.distengine.common.EndUID;
import org.ect.reo.distengine.common.StepRep;
import org.ect.reo.distengine.common.Utils.DataSource;
import collection.mutable.HashMap;

class Colouring extends HashMap[EndUID,Colour] with StepRep {
            
    def compatible(other:Colouring) : Boolean = {
        // get one colouring as an enumeration
        // for each end, check whether it is in other
        // org.ect.reo.distengine.colouring. if it is, check that colours are the same.
        // if an overlapping colour does not match, then
        // they are not compatible
        
        for (key <- this.keys) {
          if (other.contains(key) && !this(key).compatible(other(key)))
            return false
        }
        return true
    }
    
    // assumes that the present colouring and the added one
    // are compatible
    
    def union(other:Colouring) : Colouring = {
      // assert(compatible(other))
      var result = new Colouring
          
      val domain = this.keys ++ other.keys
          
      for (key <- domain) {
        if (this contains key) {
          if (other contains key) {
            result(key) = this(key) join other(key)
          }
          else { 
            result(key) = this(key)
          }
        } else {
          if (other contains key) { 
            result(key) = other(key)
          }
          else { 
            System.out.println("Can't happen. Key '"+key+"' not found in any org.ect.reo.distengine.colouring. "+
              "Error when combining the colourings("+
              this.size+"|"+other.size+") with keys: -"+this.keys.mkString(",")+"-\n-"+other.keys.mkString(",")+"-"+
              "\n - this/other contains key: "+this.contains(key)+"/"+other.contains(key));
          }
        }
      }
      return result
    }

    // assumes that the present colouring and the added one
    // are compatible
    
   def union_hacked_to_avoid_old_hashcodes(other:Colouring) : Colouring = {
      // assert(compatible(other))
      var result = new Colouring
      val newthis = new Colouring
      val newother = new Colouring
          
      val domain = this.keys ++ other.keys
          
      for (key <- domain) {
        var findthis = false
        var findother = false
        for ((thiskey,value) <- this) {
          newthis(thiskey) = value
          if (key == thiskey) {
            findthis = true;
//          println("found key "+key+" in THIS!")            
          }
        }
        for ((otherkey,value) <- other) {
          newother(otherkey) = value
          if (key == otherkey) {
            findother = true; 
            // println("found key "+key+" in OTHER!")
          }
        }
        result(key) =
          if (findthis && findother) newthis(key) join newother(key)
          else if (findthis)         newthis(key)
          else if (findother)        newother(key)
          else {
            System.out.println("Can't happen. Key '"+key+"' not found in any org.ect.reo.distengine.colouring. "+
              "Error when combining the colourings("+
              this.size+"|"+other.size+") with keys: -"+this.keys.mkString(",")+"-\n-"+other.keys.mkString(",")+"-"+
              "\n - this/other contains key: "+this.contains(key)+"/"+other.contains(key));
            NoFlow
          }
        this.clear()
        this ++= newthis
      }
      return result
    }

    
    /** @return true iff a given end is flowing data in the current org.ect.reo.distengine.colouring. */
    def hasFlow(end:EndUID) : Boolean = {
      if (this contains end)
        this(end) == Flow
      else
        false
    }
    
    override def toString() : String = {
        var result = ""
        for ((k,v) <- this)
         result = result + "| " + k.toString() + v.toString() + "\n"   
        return result;
    }
    
    override def hashCode(): Int = {
        var hc : Int = 0;
        for ((k,v) <- this)
         hc = hc + k.hashCode * v.hashCode    
        return hc;
    }
    
//    override def equals(other:Any) : Boolean = {
//       other match {
//        case cother:Colouring => return map.equals(cother.map)
//        case _ => false
//       }
//    }
}


