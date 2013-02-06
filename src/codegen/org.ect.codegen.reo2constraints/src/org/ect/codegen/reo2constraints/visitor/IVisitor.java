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
package org.ect.codegen.reo2constraints.visitor;

import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;

public interface IVisitor {
	public String visit(Sync s);
    public String visit(FIFO f);
    public String visit(Filter f);
    public String visit(SyncDrain d);
    public String visit(LossySync l);
    public String visit(SyncSpout s);
    public String visit(AsyncDrain a);
    public String visit(AsyncSpout a);
    public String visit(Timer t);
    public String visit(Node n);
    public String visit(Connector c);
    public String visit(Reader r);
    public String visit(Writer w);
	public String visit(PrioritySync p);
	public String visit(BlockingSync p);
	public String visit(BlockingSinkSync p);
	public String visit(BlockingSourceSync p);    
}
 