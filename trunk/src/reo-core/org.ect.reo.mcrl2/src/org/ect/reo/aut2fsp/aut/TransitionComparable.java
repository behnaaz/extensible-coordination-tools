/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.reo.aut2fsp.aut;

import java.util.Comparator;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class TransitionComparable implements Comparator<Transition>{
     public int compare(Transition o1, Transition o2) {
        return (o1.getSource().getName()<o2.getSource().getName() ? -1 : (o1.getSource().getName()==o2.getSource().getName() ? 0 : 1));
    }

}
