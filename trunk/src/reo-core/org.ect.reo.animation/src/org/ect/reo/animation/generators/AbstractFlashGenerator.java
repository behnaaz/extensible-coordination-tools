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
package org.ect.reo.animation.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.AbstractAnimationInterpreter;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.animation.flash.figures.ConnectorFigure;
import org.ect.reo.animation.flash.figures.IFlashFigure;
import org.ect.reo.animation.flash.figures.NodeFigure;
import org.ect.reo.animation.flash.figures.PrimitiveFigure;
import org.ect.reo.animation.flash.figures.TokenFigure;
import org.ect.reo.animation.flash.figures.factories.IFlashFigureFactory;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.diagram.view.util.ReoViewFinder;
import org.ect.reo.diagram.view.util.ReoViewUtil;
import org.ect.reo.semantics.ReoTextualSemantics;

import com.anotherbigidea.flash.movie.Frame;
import com.anotherbigidea.flash.movie.Movie;
import com.anotherbigidea.flash.structs.Color;



public abstract class AbstractFlashGenerator extends AbstractAnimationInterpreter {
	
	// Figure factory to be used for the Flash animations.
	protected IFlashFigureFactory figureFactory;
	
	// Connector and component figures.
	protected Map<Connector,ConnectorFigure> connectorFigures = new LinkedHashMap<Connector,ConnectorFigure>();	
	protected Map<org.ect.reo.Node,NodeFigure> nodeFigures = new LinkedHashMap<org.ect.reo.Node,NodeFigure>();
	protected Map<Primitive,PrimitiveFigure> primitiveFigures = new LinkedHashMap<Primitive,PrimitiveFigure>();
	
	// Token figures.
	protected Map<PrimitiveEnd,TokenFigure> tokenFigures = new LinkedHashMap<PrimitiveEnd,TokenFigure>();	
	
	// The view for the network that should be animated.
	protected NetworkView networkView;
	
	// The resulting flash movie.
	protected Movie movie;
	
	// Speed reference value.
	private int speedReference = 1000;
	
	// Same token color?
	private boolean sameTokenColor = true;
	
	/**
	 * Create a Flash movie object.
	 */
	protected Movie createMovie(Color background, int width, int height, int framerate) {
		
		Movie movie = new Movie();
        movie.setBackColor(background);        
        movie.setWidth(width);
        movie.setHeight(height);
        movie.setFrameRate(framerate);
        
        return movie;
        
	}
	
	
	protected void removeFigures(Frame frame) {
		
		// Remove old figures.
		for (PrimitiveFigure figure : primitiveFigures.values()) {
			figure.remove(frame);
		}
		for (NodeFigure figure : nodeFigures.values()) {
			figure.remove(frame);
		}
		for (ConnectorFigure figure : connectorFigures.values()) {
			figure.remove(frame);
		}
		
        primitiveFigures.clear();
        nodeFigures.clear();
        connectorFigures.clear();
        
	}
	
	
	
	protected void createFigures(Frame frame, Animation animation) {
		
        // Set the new animation and network view.
		figureFactory.setAnimation(animation);
		figureFactory.setNetworkView(networkView);
		
		// The network:
		Network network = networkView.getNetwork();
		
        // Create figures for the edges.
		for (Diagram diagram : networkView.getDiagrams()) {
			for (int i=0; i<diagram.getEdges().size(); i++) {
				addFlashFigure((View) diagram.getEdges().get(i), network);
			}
		}
		
        // Figures for the nodes.
		for (Connector connector : network.getAllConnectors()) {
			Node compartment = ReoViewFinder.findConnectorCompartmentView(connector, null);
			for (int i=0; i<compartment.getChildren().size(); i++) {
				Node node = (Node) compartment.getChildren().get(i);
				if (ReoViewUtil.isNodeView(node)) {
					addFlashFigure(node, network);
				}
			}
		}
		
        // Figures for the components and their ends.
		for (Component component : network.getAllComponents()) {
			Node node = ReoViewFinder.findComponentView(component, null);
			addFlashFigure(node, network);
		}
		
		// Figures for connectors.
		if (ReoPreferences.drawConnectorContainers()) {
			
			// Get a sorted list of connectors.
			List<Connector> connectors = new ArrayList<Connector>(network.getAllConnectors());
			Collections.sort(connectors, new Comparator<Connector>() {
				public int compare(Connector c1, Connector c2) {
					return c1.getLevel() - c2.getLevel();
				}
			});
			
	        // Create and draw figures for the connectors.
			for (Connector connector : connectors) {
				Node node = ReoViewFinder.findConnectorView(connector, null);
				addFlashFigure(node, network);
			}
		}
				
	}
	
	
	protected void drawFigures(Frame frame) {
		
		// Note: the order of the drawing is important.
		// Especially for the connectors!!!
		
		// Draw connectors.
		if (ReoPreferences.drawConnectorContainers()) {
			for (Entry<Connector,ConnectorFigure> entry : connectorFigures.entrySet()) {
				entry.getValue().draw(frame);
			}
		}
		
        // Draw primitives.
		for (PrimitiveFigure figure : primitiveFigures.values()) {
			figure.draw(frame);
		}
		
        // Draw nodes.
		if (ReoPreferences.drawNodes()) {
			for (NodeFigure figure : nodeFigures.values()) {
				figure.draw(frame);
			}
		}
		
		// Redraw the tokens.
		for (TokenFigure figure : tokenFigures.values()) {
			figure.redraw(frame);
		}
        
	}
	
	
	protected IFlashFigure addFlashFigure(View view, Network network) {
		
		if (view==null) return null;
		EObject element = view.getElement();
		
		// Check the type of the element.
		if (!(element instanceof Connector) &&
			!(element instanceof org.ect.reo.Node) &&
			!(element instanceof Primitive)) 
			return null;
		
		// Create the figure.
		IFlashFigure figure = figureFactory.createFlashFigure(view);
    	if (figure==null) {
    		Reo.logError("Error creating figure for " + element + ".");
    		return null;
    	}
    	
		if (element instanceof Connector) {
    		connectorFigures.put((Connector) element, (ConnectorFigure) figure);
    	}
    	if (element instanceof org.ect.reo.Node) {
    		nodeFigures.put((org.ect.reo.Node) element, (NodeFigure) figure);
    	}
    	else if (element instanceof Primitive) {
    		if (network.getAllPrimitives().contains(element)) {
    			primitiveFigures.put((Primitive) element, (PrimitiveFigure) figure);
    		}
    	}
    	return figure;
    	
	}
	
	/**
	 * Create and draw the token figures.
	 */
	protected void createTokens(AnimationStep step, Frame frame) {
		if (sameTokenColor) {
			TokenFigure.resetColor();
		}
		// Bugfix: there is a special case that we need to
		// check here. If the tokens where created by the same
		// node, we need to make sure they have the same color.
		Map<org.ect.reo.Node,Color> nodeColors = new HashMap<org.ect.reo.Node,Color>();		
		for (PrimitiveEnd end : step.getActiveEnds()) {
			Color color;
			if (end instanceof SourceEnd && end.getNode()!=null) {
				if (nodeColors.containsKey(end.getNode())) {
					color = nodeColors.get(end.getNode());
				} else {
					color = nextTokenColor();
					nodeColors.put(end.getNode(), color);
				}
			} else {
				color = nextTokenColor();
			}
			createTokenFigure(end, color).draw(frame);
		}
	}
	
	protected Color nextTokenColor() {
		return sameTokenColor ? TokenFigure.currentColor() : TokenFigure.nextColor();
	}
	
	/**
	 * Create the token figures for a particular end.
	 */
	protected TokenFigure createTokenFigure(PrimitiveEnd end, Color color) {
		
		// Get the primitive figure:
		PrimitiveFigure figure = (PrimitiveFigure) primitiveFigures.get(end.getPrimitive());
		if (figure.getPosition(end)!=null) {
			
			// Create the token figure:
			TokenFigure tokenFigure = new TokenFigure(figure.getPosition(end), figure.getFlowPath(end), color);
			tokenFigures.put(end, tokenFigure);
			return tokenFigure;
		
		} else {
			logError("No position defined for new token at " + endName(end) + ".");
			return null;
		}
		
	}

	
	/**
	 * Fade in/out the tokens.
	 */
	protected void fadeTokens(AnimationStep step, boolean fadein, int speed) {
		for (int j=0; j<=getSpeedReference(speed); j=j+speed) {
			doFadeTokens(step, fadein, speed, j);
		}
	}
	
	/**
	 * Move the tokens.
	 */
	protected void moveTokens(AnimationStep step, int speed, boolean reverse) {
		for (int j=0; j<=getSpeedReference(speed); j=j+speed) {
			doMoveTokens(step, speed, reverse, j);
		}	
	}
	

	/**
	 * Move and fade the tokens.
	 */
	protected void moveAndFadeTokens(AnimationStep move, AnimationStep fade, boolean fadein, boolean reverse, int speed) {
		for (int j=0; j<=getSpeedReference(speed); j=j+speed) {
			doMoveTokens(move, speed, reverse, j);
			doFadeTokens(fade, fadein, speed, j);
		}
	}
	
	/**
	 * (Un)highlight the colouring of the current animation.
	 * @param fadein
	 */
	protected void highlightColouring(boolean fadein, int speed) {
		for (int j=0; j<=getSpeedReference(speed); j=j+speed) {
			doHighlightColouring(fadein, speed, j);
    	}		
	}
	
	/**
	 * Remove the tokens from a frame.
	 */
	protected void removeTokens(AnimationStep step, Frame frame) {
		for (PrimitiveEnd end : step.getActiveEnds()) {
			TokenFigure tokenFigure = tokenFigures.get(end);
			if (tokenFigure!=null) tokenFigure.remove(frame);
			tokenFigures.remove(end);
		}
	}
		
	/**
	 * Replicate tokens.
	 */
	protected void replicateTokens(ReplicateStep step, Frame frame) {
		
		for (PrimitiveEnd end : step.getActiveEnds()) {

			TokenFigure tokenFigure = tokenFigures.get(end);
			if (tokenFigure==null) {
				logError("Cannot replicate token at " + endName(end) + ".");
				continue;
			}
			
			// Find out where to replicate the tokens.
			List<PrimitiveEnd> cloneEnds = step.getTargets(end);
			
			for (PrimitiveEnd clone : cloneEnds) {
				
				PrimitiveFigure figure = primitiveFigures.get(clone.getPrimitive());
				if (figure==null) {
					logError("Missing Flash figure for " + endName(clone));
					continue;
				}
				TokenFigure cloneFigure = new TokenFigure(
												figure.getPosition(clone), 
												figure.getFlowPath(clone), 
												tokenFigure.getColor());
				cloneFigure.draw(frame);
				cloneFigure.move(frame, 0.0);
				cloneFigure.highlight(frame, 1.0);
				
				tokenFigures.put(clone, cloneFigure);
			}
			
			tokenFigure.remove(frame);
			tokenFigures.remove(end);

		}

	}
	
	/**
	 * Remove all token figures.
	 */
	public void clearTokens() {
		tokenFigures.clear();
		TokenFigure.resetColor();
	}
	
	/*
	 * Private helper method for moving tokens.
	 */
	private void doMoveTokens(AnimationStep step, int speed, boolean reverse, int j) {
		double value = ((double) j) / ((double) getSpeedReference(speed));
		if (reverse) value = 1.0 - value;
		Frame frame = movie.appendFrame();	
		for (PrimitiveEnd end : step.getActiveEnds()) {
			TokenFigure tokenFigure = tokenFigures.get(end);
			if (tokenFigure!=null) tokenFigure.move(frame, value);
			else logError("Cannot move token at " + endName(end) + ".");
		}		
	}

	/*
	 * Private helper method for fading tokens.
	 */
	private void doFadeTokens(AnimationStep step, boolean fadein, int speed, int j) {
		double value = ((double) j) / ((double) getSpeedReference(speed));
		if (!fadein) value = 1.0 - value;
		Frame frame = movie.appendFrame();
		
		for (PrimitiveEnd end : step.getActiveEnds()) {
			TokenFigure tokenFigure = tokenFigures.get(end);
			if (tokenFigure!=null) tokenFigure.highlight(frame, value);
			else logError("Cannot fade token at " + endName(end) + ".");
		}		
	}
	
	/*
	 * Private helper method for doing the highlighting.
	 */
	private void doHighlightColouring(boolean fadein, int speed, int j) {
		double value = ((double) j) / ((double) getSpeedReference(speed));
		if (!fadein) value = 1.0 - value;
		Frame frame = movie.appendFrame();
		for (PrimitiveFigure figure : primitiveFigures.values()) {
			figure.highlight(frame, value);
		}
		for (NodeFigure figure : nodeFigures.values()) {
			figure.highlight(frame, value);
		}		
	}
	
	protected int getSpeedReference(int speed) {
		int reference = speedReference;
		if (reference % speed!=0) reference = reference - (reference % speed);
		return reference;
	}
	
	protected void setSpeedReference(int speedReference) {
		this.speedReference = speedReference;
	}

	/**
	 * Set the network view.
	 */
	public void setNetworkView(NetworkView networkView) {
		this.networkView = networkView;
	}
	
	/**
	 * Get the figure factory to be used.
	 */
	public IFlashFigureFactory getFigureFactory() {
		return figureFactory;
	}
	
	/**
	 * Set the figure factory to be used.
	 */
	public void setFigureFactory(IFlashFigureFactory factory) {
		this.figureFactory = factory;
	}
	
	protected void setSameTokenColor(boolean sameColor) {
		this.sameTokenColor = sameColor;
	}
	
	/**
	 * Get the last generated movie.
	 * @return Movie.
	 */
	public Movie getLastMovie() {
		return movie;
	}
	
	
	private String endName(PrimitiveEnd end) {
		if (end.getPrimitive()!=null) {
			return ReoTextualSemantics.getEndNames(end.getPrimitive()).get(end);
		} else {
			return end.toString();
		}
	}
	
	
	/* ------ Notification ------ */
	
	// Listener interface
	public interface IGeneratorListener {
		void movieGenerated(Movie movie, int index);
	}
	
	// Listeners.
	private List<IGeneratorListener> listeners = new Vector<IGeneratorListener>();
	
	
	public void addListener(IGeneratorListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(IGeneratorListener listener) {
		listeners.remove(listener);
	}
	
	protected void notifyListeners(int index) {
        for (int i=0; i<listeners.size(); i++) {
        	listeners.get(i).movieGenerated(movie, index);
        }
	}
	
	
	/* ------- Animation errors ------ */
	
	private String lastError = null;
	
	protected void logError(String message) {
		if (lastError==null || !lastError.equals(message)) {
			Reo.logError(message);
			lastError = message;
		}
	}

}
