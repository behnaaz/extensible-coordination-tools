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
package org.ect.convert.bpel2reo.util;

import org.ect.reo.Connector;

public class ConnectorInfo {

	Connector cnn;
	Pair<org.ect.reo.Node, org.ect.reo.Node> pair;

	public ConnectorInfo(Connector connector, Pair<org.ect.reo.Node, org.ect.reo.Node> startendpair) {
		cnn = connector;
		pair = startendpair;
	}

	public Connector getConnector() {
		return cnn;}

	public Pair<org.ect.reo.Node, org.ect.reo.Node> getPair() {
		return pair;}

	public void setConnector(Connector connector) {
		cnn = connector;}

	public void setPair(Pair<org.ect.reo.Node, org.ect.reo.Node> p) {
		pair = p;}
}
