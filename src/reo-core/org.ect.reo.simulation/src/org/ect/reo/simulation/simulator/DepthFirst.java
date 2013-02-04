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
package org.ect.reo.simulation.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.colouring.Colouring;


public class DepthFirst {
	
	public static ColouringEvent getColouringEvent(Colouring colouring, ReoSimulator sim) {
		
		// draw from all involved channels, because when you traverse the connector you always want the same draw for the same channel
		// when multiple paths uses the same channel
		HashMap<PrimitiveEnd, Double> draws = getDraws(colouring, sim);
		
		// build up a list of ports which are involved in this colouring
		List<Node> nodes = getActivePorts(colouring, sim);
		
		// build up a list of starting points in this colouring, these are all boundary nodes and primitives which have initial flow
		List<Connectable> startingPoints = getStartingPoints(colouring, sim);
		
		List<FIFO> filledBuffers = new ArrayList<FIFO>();
		List<FIFO> emptiedBuffers = new ArrayList<FIFO>();
		
		double endOfColouring = sim.getTime();
		
		// loop over all starting points and check which path is the longest (which will mark the end of the colouring)
		for (Connectable connectable : startingPoints) {
			
			// check if there already is a token at this connectable, this can be the case when you are in a FIFO buffer
			Token token = sim.getActiveTokenMap().get(connectable);
			
			// if there is no token in the activeTokenMap, create a new token; else remove the active token from the map
			if(token == null) {
				token = new Token(connectable, sim.getTime());
			} else {
				sim.getActiveTokenMap().remove(connectable);
			}
			
			// get the max duration for this starting point, note that this is a recursive function and it will check all paths
			// which will start at this starting point
			double temp = getDuration(connectable, colouring, sim.getTime(), token, sim, draws, filledBuffers, emptiedBuffers);
			
			// if the ending time of this starting point is larger than any previous starting points, use this point as the end of the colouring
			if (temp > endOfColouring) {
				endOfColouring = temp;
			}
		}
		
		// return the event
		return new ColouringEvent(endOfColouring, colouring, nodes, filledBuffers, emptiedBuffers);
	}	
	
	
	private static List<Node> getActivePorts(Colouring colouring, ReoSimulator sim) {
		
		// get all the boundary nodes with flow
		List<Node> result = new ArrayList<Node>();
		for (PrimitiveEnd end : sim.getBoundaryPrimitiveEnds()) {
            if (colouring.isFlow(end)) {
                result.add(end.getNode());
            }
        }
		
		return result;
	}
	
	
	private static List<Connectable> getStartingPoints(Colouring colouring, ReoSimulator sim) {
		
		// build up a list of all starting points of this colouring
		List<Connectable> result = new ArrayList<Connectable>();
		
		for (Node boundary : sim.getBoundaryNodes()) {
			// add all boundary nodes which starts with flow
			if (colouring.hasInitialFlow(boundary, sim.getBorder())) {
				result.add(boundary);
			}
		}
		
		for (Primitive prim : sim.getConnector().getPrimitives()) {
			// add all primitives which starts with flow
			if (colouring.hasInitialFlow(prim, sim.getBorder())) {
				result.add(prim);
			}
		}
		
		return result;
	}
	
	private static double getDuration(Connectable connectable, Colouring colouring, double curTime, Token token, ReoSimulator sim, 
									  HashMap<PrimitiveEnd, Double> draws, List<FIFO> filledBuffers, List<FIFO> emptiedBuffers) {
		
		// do the actual traversal to determine the ending time of this connectable. This connectable can either be a node or a primitive.
		// we are at the end of traversal when:
		//		1. we are in a sink node
		//		2. there are no sink ends in primitive (for example a SyncDrain)
		//		3. the sink end has no flow (for example in a FIFO or lossy sync)

		
		// initialise the result to the current time
		double result = curTime;
		
		// set the location of the token
		token.setLocation(connectable);
		
		if (connectable instanceof Node) {
			Node node = (Node) connectable;
			
			if (node.isSinkNode()) {
				// if we are at a sink node, the token is finished and we can finish it (for the end to end delay statistics)
				finishToken(token, node, result, sim);
				return result;
			}
			
			for (PrimitiveEnd end : node.getSourceEnds()) {
				// we are not in a sink node, so loop over all source ends and check if these ends has flow
				if (colouring.isFlow(end)) {
					if (end.getPrimitive() instanceof FIFO) {
						// if the primitive of the source end is a FIFO buffer, the buffer will be full when the colouring is finished
						filledBuffers.add((FIFO) end.getPrimitive());
					}
					
					// (***) get the max duration of all flows which go through this source end, by recalling getDuration. This will transfer
					// the current token to the primitive of the source end, however if the token is already moved to another source end 
					// of this primitive which can be noticed by looking at the current location of the token; We will have to make a 
					// copy of the token, this copy will only copy the starting point and time (so not the current position).
					double temp = getDuration(end.getPrimitive(), colouring, curTime + draws.get(end), (token.getLocation().equals(connectable) ? token : token.getCopy()), sim, draws, filledBuffers, emptiedBuffers);
					
					// use the largest duration
					if (temp > result) { 
						result = temp; 
					}
				}
			}
		} else {
			// connectable is a primitive
			Primitive prim = (Primitive) connectable;
			
			// if the primitive doesn't have sink ends we are at the end of a traversal.
			if (prim.getSinkEnds().isEmpty()) {
				return handlePrimitiveNoSinkEnds(prim, token, result, sim);
			}
			
			for (PrimitiveEnd end : prim.getSinkEnds()) {
				if (colouring.isFlow(end)) {
					
					// if the sink end has flow and the primitive is a FIFO buffer, the buffer is emptied after this colouring
					if (end.getPrimitive() instanceof FIFO) {
						emptiedBuffers.add((FIFO) end.getPrimitive());
					}
					
					// move the token to the node of the sink end, also see (***) for a comment about the token
					double temp = getDuration(end.getNode(), colouring, curTime + draws.get(end), (token.getLocation().equals(connectable) ? token : token.getCopy()), sim, draws, filledBuffers, emptiedBuffers);
					
					// use the largest duration
					if (temp > result) {
						result = temp;
					}
				} else if (prim instanceof FIFO) {
					// if we are in a FIFO buffer, the current token has to be added to the activeTokenMap,
					// so it can be used when another colouring will move the token out of the buffer
					sim.getActiveTokenMap().put(connectable, token);
				} else {
					// the sink end doesn't has flow, so we can finish the token (happens when we have a lossy sync)
					finishToken(token, prim, result, sim);
					return result;
				}
			}
		}
		
		return result;
	}
	
	private static double handlePrimitiveNoSinkEnds(Primitive prim, Token token, double curTime, ReoSimulator sim) {
		
		double result = curTime;
		if (prim instanceof SyncDrain) {
			
			// when the primitive is a SyncDrain, we have to do some extra work. Because the SyncDrain will only work,
			// when both ends are available, we have to let the first arriving end wait for the other end.
			
			if (sim.getActiveTokenMap().containsKey(prim)) {
				
				// the other end is already available, so get and remove it from the active tokens
				Token token2 = sim.getActiveTokenMap().get(prim);
				sim.getActiveTokenMap().remove(prim);
				
				// finish the first token, the finishing time will be the maximum finishing time of both tokens
				finishToken(token, prim, Math.max(token2.getEndTime(), result), sim);
				
				// if the second token started from a different location, also finish that token. Else just ignore that token.
				// For example, in a XOR connector we have two tokens into the drain from the same location
				if (token.getStartingPoint() != token2.getStartingPoint()) {
					finishToken(token2, prim, Math.max(token2.getEndTime(), result), sim);
				}
			} else {
				
				// this is the first token in this drain, so set the time for this token and add it to the active token map
				token.setEndingPointAndTime(prim, result);
				sim.getActiveTokenMap().put(prim, token);
			}
			
		} else {
			
			// if the primitive is not a SyncDrain, just finish the token
			finishToken(token, prim, result, sim);
		}
		return result;		
	}
	
	private static void finishToken(Token token, Connectable endPoint, double endTime, ReoSimulator sim) {
		
		// set the ending time and location
		token.setEndingPointAndTime(endPoint, endTime);
		
		// get the right end to end delay statistic from the map, if it doesn't exists yet, create it
		Statistic endToEndDelayStat = sim.getEndToEndCategory().getStatistic(token.getPath());
		if (endToEndDelayStat == null) {
			endToEndDelayStat = new Statistic(sim, token.getPath(), false, 2, null);
			sim.getEndToEndCategory().addStatistic(token.getPath(), endToEndDelayStat);
		}
		
		// add the end to end delay to this statistic
		endToEndDelayStat.addTime(token.getBeginTime(), endTime, sim.getEventCount(), sim.getEventCount());
	}
	
	
	public static HashMap<PrimitiveEnd, Double> getDraws(Colouring colouring, ReoSimulator sim) {
		HashMap<PrimitiveEnd, Double> result = new HashMap<PrimitiveEnd, Double>();
		
		boolean drawn;
		double draw, lastDraw = 0;
		
		for (Primitive prim : sim.getConnector().getPrimitives()) {
			drawn = false;
			for (PrimitiveEnd end : prim.getAllEnds()) {
				if (colouring.isFlow(end)) {
					
					if ((prim instanceof LossySync) && (end instanceof SourceEnd)){
						// if we are at the source end of a LossySync, we have to check if the sink end also has flow,
						// if the this is the case, the channel behaves as a Sync channel and we only want to use the
						// distribution on the sink end (so the draw will be set to zero). 
						// If the sink end has no flow, it loses it's data and we will use the distribution on the source end.
						// Also see ReoSimulator.initDistribution
						
						if (colouring.isFlow(prim.getSinkEnd(0))) {
							draw = 0;
						} else {
							draw = sim.getDistributionMap().get(end).inverse(sim.getRandom().nextDouble());
						}
						
					} else if (drawn && ((prim instanceof SyncDrain) || (prim instanceof SyncSpout))) {
						
						// if the primitive is a SyncDrain or SyncSpout and we already drawn from the channel (on the other end)
						// we will use this draw again (also the channel statistics doesn't have to be updated again
						draw = lastDraw;
						result.put(end, new Double(draw));
						continue;
						
					} else {
						
						// just draw from the distribution and mark that we have drawn (in the case we are dealing with a SyncDrain or SyncSpout)
						draw = sim.getDistributionMap().get(end).inverse(sim.getRandom().nextDouble());
						lastDraw = draw;
						drawn = true;
					}
					
					if ((prim instanceof FIFO) || (prim instanceof LossySync) || (prim instanceof AsyncDrain) || (prim instanceof AsyncSpout)) {
						sim.getChannelStatCategory().getStateStatistics(end).changeState(ReoSimulator.STATE_BUSY, sim.getTime(), sim.getEventCount());
						sim.getChannelStatCategory().getStateStatistics(end).changeState(ReoSimulator.STATE_EMPTY, sim.getTime() + draw, sim.getEventCount());
					} else {
						// change the state of the channel to busy for 'draw' time units
						sim.getChannelStatCategory().getStateStatistics(prim).changeState(ReoSimulator.STATE_BUSY, sim.getTime(), sim.getEventCount());
						sim.getChannelStatCategory().getStateStatistics(prim).changeState(ReoSimulator.STATE_EMPTY, sim.getTime() + draw, sim.getEventCount());
					}
    				
					// add the draw to the map
					result.put(end, new Double(draw));
				}				
			}
		}
		
		return result;
	}
}
