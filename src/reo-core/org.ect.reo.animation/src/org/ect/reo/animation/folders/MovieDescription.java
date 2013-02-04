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
package org.ect.reo.animation.folders;


public class MovieDescription {
	
	private int width, height, index;
	private String html, swf;
	private boolean loop;
	
	public MovieDescription(int index, String html, String swf, int width, int height, boolean loop) {
		this.html = html;
		this.swf = swf;
		this.width = width;
		this.height = height;
		this.index = index;
		this.loop = loop;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getSWF() {
		return swf;
	}
	
	public String getHTML() {
		return html;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isLoop() {
		return loop;
	}
	
}
