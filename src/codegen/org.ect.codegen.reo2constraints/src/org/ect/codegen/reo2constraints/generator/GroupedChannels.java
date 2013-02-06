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
package org.ect.codegen.reo2constraints.generator;

import java.util.Set;

import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;

public class GroupedChannels {
	private Set<String> channelIds;
	private SourceEnd src;
	private int state;
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setSource(SourceEnd src) {
		this.src = src;
	}

	public void setSink(SinkEnd snk) {
		this.snk = snk;
	}

	private SinkEnd snk;
	public GroupedChannels(SourceEnd src, SinkEnd snk, Set<String> channelIds, int state)
	  { 
	    this.src = src;
	    this.snk = snk;
	    this.channelIds= channelIds;
	    this.state = state;
	  }

	  public SourceEnd getSourceEnd()
	  {
	    return src;
	  }

	  public SinkEnd getSinkEnd() 
	  {
	    return snk;
	  }

	  public Set<String> getChannelIds()
	  {
	    return channelIds;
	  }
	  
	  public String toString()
	  { 
	    return "(" + src.getName() + ", " + snk.getName() + ")"; 
	  }	 
}
