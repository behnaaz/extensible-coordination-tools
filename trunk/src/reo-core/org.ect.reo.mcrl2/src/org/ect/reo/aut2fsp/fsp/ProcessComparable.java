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
package org.ect.reo.aut2fsp.fsp;
import java.util.Comparator;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class ProcessComparable implements Comparator<Process>{
 
    public int compare(Process o1, Process o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
