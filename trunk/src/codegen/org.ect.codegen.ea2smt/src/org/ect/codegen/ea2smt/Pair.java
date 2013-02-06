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
package org.ect.codegen.ea2smt;

class Pair<A, B> {
	
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    @SuppressWarnings("rawtypes")
	public boolean equals(Object other) {
        if (other instanceof Pair) {
                Pair<?,?> otherPair = (Pair) other;
                return 
                ((  this.first == otherPair.first ||
                        ( this.first != null && otherPair.first != null &&
                          this.first.equals(otherPair.first))) &&
                 (      this.second == otherPair.second ||
                        ( this.second != null && otherPair.second != null &&
                          this.second.equals(otherPair.second))) );
        }

        return false;
    }

    @Override
    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}

