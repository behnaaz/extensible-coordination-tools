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
package org.ect.reo.distengine.sat

import org.ect.reo.distengine.common.EndUID
import org.ect.reo.distengine.common.StepRep
import collection.immutable.HashSet


class ConstraintSol(val flowset: Set[EndUID]) extends StepRep {
    def hasFlow(end:EndUID) : Boolean =
      flowset contains end
    
    override def toString() = {
      flowset.mkString("<",",",">")
    }
}
