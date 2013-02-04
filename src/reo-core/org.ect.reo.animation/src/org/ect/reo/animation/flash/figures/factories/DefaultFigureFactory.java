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
package org.ect.reo.animation.flash.figures.factories;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.figures.AsyncDrainFigure;
import org.ect.reo.animation.flash.figures.AsyncSpoutFigure;
import org.ect.reo.animation.flash.figures.BlockingSinkSyncFigure;
import org.ect.reo.animation.flash.figures.BlockingSourceSyncFigure;
import org.ect.reo.animation.flash.figures.BlockingSyncFigure;
import org.ect.reo.animation.flash.figures.ComponentFigure;
import org.ect.reo.animation.flash.figures.ConnectorFigure;
import org.ect.reo.animation.flash.figures.CustomChannelFigure;
import org.ect.reo.animation.flash.figures.FIFOFigure;
import org.ect.reo.animation.flash.figures.FilterFigure;
import org.ect.reo.animation.flash.figures.IFlashFigure;
import org.ect.reo.animation.flash.figures.LossyFIFOFigure;
import org.ect.reo.animation.flash.figures.LossySyncFigure;
import org.ect.reo.animation.flash.figures.NetworkAwareFigure;
import org.ect.reo.animation.flash.figures.NodeFigure;
import org.ect.reo.animation.flash.figures.PrioritySyncFigure;
import org.ect.reo.animation.flash.figures.SyncDrainFigure;
import org.ect.reo.animation.flash.figures.SyncFigure;
import org.ect.reo.animation.flash.figures.SyncSpoutFigure;
import org.ect.reo.animation.flash.figures.TimerFigure;
import org.ect.reo.animation.flash.figures.TransformFigure;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossyFIFO;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.diagram.view.util.NetworkView;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class DefaultFigureFactory implements IFlashFigureFactory {

	// Current animation.
	private Animation animation;

	// NetworkView to be used.
	private NetworkView networkView;

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.factories.IFlashFigureFactory#createFlashFigure(org.eclipse.gmf.runtime.notation.View)
	 */
	public IFlashFigure createFlashFigure(View view) {
		IFlashFigure figure = createFigure(view);
		return figure;
	}

	private IFlashFigure createFigure(View view) {

		EObject element = view.getElement();
		IFlashFigure figure = null;

		// Nodes
		if (view instanceof Node) {

			Node node = (Node) view;

			if (element instanceof org.ect.reo.Node) {
				figure = new NodeFigure(node, animation);
			} else if (element instanceof Connector) {
				figure = new ConnectorFigure(node, animation);
			} else if (element instanceof Component) {
				figure = new ComponentFigure(node, animation);
			}
		}

		// Edges
		else if (view instanceof Edge) {

			Edge edge = (Edge) view;

			// Channels
			if (element instanceof Sync) {
				figure = new SyncFigure(edge, animation);
			} else if (element instanceof LossySync) {
				figure = new LossySyncFigure(edge, animation);
			} else if (element instanceof FIFO) {
				figure = new FIFOFigure(edge, animation);
			} else if (element instanceof LossyFIFO) {
				figure = new LossyFIFOFigure(edge, animation);
			} else if (element instanceof SyncDrain) {
				figure = new SyncDrainFigure(edge, animation);
			} else if (element instanceof SyncSpout) {
				figure = new SyncSpoutFigure(edge, animation);
			} else if (element instanceof AsyncDrain) {
				figure = new AsyncDrainFigure(edge, animation);
			} else if (element instanceof AsyncSpout) {
				figure = new AsyncSpoutFigure(edge, animation);
			} else if (element instanceof Filter) {
				figure = new FilterFigure(edge, animation);
			} else if (element instanceof Transform) {
				figure = new TransformFigure(edge, animation);
			} else if (element instanceof Timer) {
				figure = new TimerFigure(edge, animation);
			} else if (element instanceof CustomDirectedChannel
					|| element instanceof CustomSpoutChannel
					|| element instanceof CustomDrainChannel) {
				figure = new CustomChannelFigure(edge, animation);
			} else if (element instanceof PrioritySync) {
				figure = new PrioritySyncFigure(edge, animation);
			} else if (element instanceof BlockingSinkSync) {
				figure = new BlockingSinkSyncFigure(edge, animation);
			}else if (element instanceof BlockingSourceSync) {
				figure = new BlockingSourceSyncFigure(edge, animation);
			}else if (element instanceof BlockingSync) {
				figure = new BlockingSyncFigure(edge, animation);
			}
		}

		// Set the network view if possible.
		if (networkView != null && figure instanceof NetworkAwareFigure) {
			((NetworkAwareFigure) figure).setNetworkView(networkView);
		}

		return figure;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ect.reo.animation.flash.figures.IFlashFigureFactory#setAnimation(org.ect.
	 * reo.animation.Animation)
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ect.reo.animation.flash.figures.factories.IFlashFigureFactory#setNetworkView
	 * (org.ect.reo.diagram.view.util.NetworkView)
	 */
	public void setNetworkView(NetworkView networkView) {
		this.networkView = networkView;
	}

}
