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
package org.ect.reo.mcrl2.converters;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Block;
import org.ect.reo.mcrl2.Communication;
import org.ect.reo.mcrl2.Hide;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Nameable;
import org.ect.reo.mcrl2.Parallelism;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Specification;
import org.ect.reo.mcrl2.Synchronization;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.semantics.ReoScope;
import org.ect.reo.util.PrimitiveEndNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ElementConverter {
	
	// Node converter.
	private Converter<Node> nodes;
	
	// Channel converter.
	private Converter<Channel> channels;
	
	// Component converter.
	private Converter<Component> components;

	// The resulting mCRL2 specification.
	private Specification specification;
	
	// Map for remembering the created processes.
	private Map<Connectable,Process> processes;
	
	// Primitive end names.
	private PrimitiveEndNames names;
	
	// Data types of the elements.
	private Map<Connectable,Sort> elementTypes = new HashMap<Connectable,Sort>();
	
	// Datatype manager.
	private DataTypeManager dataTypeManager;
	
	
	/**
	 * Default constructor.
	 * @param nodes Node converter. 
	 * @param channels Channel converter.
	 */
	public ElementConverter(Converter<Node> nodes, Converter<Channel> channels, Converter<Component> components, PrimitiveEndNames names) {
		
		this.nodes = nodes;
		this.channels = channels;
		this.components = components;
		this.names = names;
		this.specification = new Specification();
		this.processes = new HashMap<Connectable,Process>();
		
		// Create data type manager instance:
		dataTypeManager = new DataTypeManager();
		nodes.setDataTypeManager(dataTypeManager);
		channels.setDataTypeManager(dataTypeManager);
		components.setDataTypeManager(dataTypeManager);
		
	}
	
	/**
	 * Convert an element to a process and add it to the specification.
	 * @param element The element.
	 * @param name Name of the process to be created.
	 * @return The created process.
	 */
	public Process convert(Connectable element, String name) {
		
		// Initialize atoms.
		for (PrimitiveEnd end : element.getAllEnds()) {
			specification.getAtoms().addAll(addAtoms(end, element));
		}
		
		// Convert the element itself.
		Process process = null;
		
		if (element instanceof Node) {
			process = nodes.convert((Node) element);
		}
		else if (element instanceof Channel) {
			process = channels.convert((Channel) element);
		}
		else if (element instanceof Component) {
			process = components.convert((Component) element);			
		}
		
		
		// Check if it is null.
		if (process==null) return null;
		
		// Set the process name and add it to the specification.
		process.setName(name);
		specification.getProcesses().add(process);
		
		// Update local data type:
		Sort localData = dataTypeManager.getLocalDataType();
		if (localData!=null) {
			if (localData instanceof Nameable && localData.getName()==null) {
				((Nameable) localData).setName("Data" + name);
			}
			specification.getSorts().add(localData);
			elementTypes.put(element, localData);
		}
		
		// Remember the created process.
		processes.put(element, process);
		return process;
		
	}
	
	/**
	 * Create atoms for a primitive end. It is usually not necessary to invoke this from outside.
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, Connectable context) {
		if (context instanceof Node) {
			return nodes.addAtoms(end, names.get(end));
		}
		else if (context instanceof Channel) {
			return channels.addAtoms(end, names.get(end));
		}
		else if (context instanceof Component) {
			return components.addAtoms(end, names.get(end));
		}
		else {
			throw new RuntimeException("Unknown context type: " + context);
		}
	}
	
	
	/**
	 * Create atoms for a primitive end. It is usually not necessary to invoke this from outside.
	 */
	public PrimitiveEndAtoms getAtoms(Connectable context) {
		if (context instanceof Node) {
			return nodes.getAtoms();
		}
		else if (context instanceof Channel) {
			return channels.getAtoms();
		}
		else if (context instanceof Component) {
			return components.getAtoms();
		}
		else {
			throw new RuntimeException("Unknown context type: " + context);
		}
	}
	
	
	/**
	 * Synchronize actions at ends. This creates a new process.
	 * @param name Name of the process to be created.
	 * @param next Next process to call. Can be <code>null</code>.
	 * @param elements Elements whose processes should be started by the resulting process.
	 * @return Created process (is added to the specification).
	 */
	public Process synchronize(String name, Process next, PrimitiveEndAtoms synchronizations, 
							List<Connectable> elements, List<PrimitiveEnd> hidden) {
		
		// Create operators.
		Parallelism par = new Parallelism();
		
		Communication comm = new Communication();
		comm.setParallelism(par);
		
		Block block = new Block();
		block.setChild(comm);

		Hide hide = new Hide();
		
		// Create process.
		Process process = new Process(name);
		
		// Set up parallelism.
		for (Connectable element : elements) {
			if (processes.containsKey(element)) {
				par.getActions().add( processes.get(element).newInstance() );
			}
		}
		if (next!=null) {
			par.getActions().add( next.newInstance() );
		}
		
		for (PrimitiveEnd end : synchronizations.keySet()) {
			
			// Node vs. channel atoms.
			List<Atom> nodeAtoms = nodes.getAtoms().get(end);
			List<Atom> primitiveAtoms = (end.getPrimitive() instanceof Channel) ? 
										channels.getAtoms().get(end) : components.getAtoms().get(end);
			
			if (nodeAtoms==null || primitiveAtoms==null) {
				continue;
			}
			
			// Do the synchronization:
			boolean shouldHide = (hidden!=null && hidden.contains(end));
			if (channels instanceof BlockingChannelConverter)
				blockingSynchronize(end, nodeAtoms, primitiveAtoms, synchronizations.get(end), comm, block, hide, shouldHide);
			else
				synchronize(end, nodeAtoms, primitiveAtoms, synchronizations.get(end), comm, block, hide, shouldHide);
			
		}
		
		// Construct the final process:
		if (!block.getAtoms().isEmpty()) {
			if (!hide.getAtoms().isEmpty()) {
				hide.setChild(block);
				process.setAction(hide);
			} else {
				process.setAction(block);
			}
		} else {
			process.setAction(par);
		}
		
		//removing unused s/f actions
		if (channels instanceof BlockingChannelConverter) {
			AtomicAction implac = null;
			for (PrimitiveEnd end : synchronizations.keySet()) {
				for (Atom syncatom : synchronizations.get(end)) {
					boolean remove = true;
					for (Implication impl : comm.getImplications()) {
						if (impl.getAction() instanceof AtomicAction) {				
							implac = (AtomicAction)impl.getAction();
							if (implac.getAtom().equals(syncatom)) 
								remove = false;
					    }
					}				
					if (implac != null && remove) {
						hide.getAtoms().remove(syncatom);
						specification.getAtoms().remove(syncatom);
					}
				}
			}
		}
		
		specification.getProcesses().add(process);
		return process;
	}
	
	/*
	 * Perform the synchronization.
	 */
	private void synchronize(PrimitiveEnd end, 
							List<Atom> nodeAtoms, List<Atom> primitiveAtoms, List<Atom> targetAtoms, 
							Communication comm, Block block, Hide hide, 
							boolean shouldHide) {
		
		// Add implications.
		int count = Math.min(nodeAtoms.size(),primitiveAtoms.size());
		
		for (int i=0; i<count; i++) {
			
			Action sync = new Synchronization(
				new AtomicAction(nodeAtoms.get(i)), new AtomicAction(primitiveAtoms.get(i)) );
			
			Atom targetAction = targetAtoms.get(i);
			
			Implication impl = new Implication();
			impl.setCondition(sync.toString());
			impl.setAction(new AtomicAction(targetAction));
			comm.getImplications().add(impl);
			
			// Update block / hide command.
			block.getAtoms().add(nodeAtoms.get(i));
			block.getAtoms().add(primitiveAtoms.get(i));
			
			if (shouldHide) {
				hide.getAtoms().add(targetAction);
			}
			
		}

	}

	/*
	 * Perform the synchronization.
	 */
	private void blockingSynchronize(PrimitiveEnd end, 
							List<Atom> nodeAtoms, List<Atom> primitiveAtoms, List<Atom> targetAtoms, 
							Communication comm, Block block, Hide hide, 
							boolean shouldHide) {
		
		// Add implications.
		int count = Math.min(nodeAtoms.size(),primitiveAtoms.size());
		
		for (int i=0; i<count; i++) {
			Action sync = null;
			
			if (i==1) {
				if (end instanceof SourceEnd) {
					sync = new Synchronization(
						new AtomicAction(nodeAtoms.get(i+1)), new AtomicAction(primitiveAtoms.get(i)) );
					
					// Update block / hide command.
					block.getAtoms().add(nodeAtoms.get(i+1));
					block.getAtoms().add(primitiveAtoms.get(i));
					
					if (shouldHide) {
						hide.getAtoms().add(nodeAtoms.get(i));
						hide.getAtoms().add(primitiveAtoms.get(i+1));
					}
				}
			}
			else if (i==2) {
				if (end instanceof SinkEnd) {
					sync = new Synchronization(
						new AtomicAction(nodeAtoms.get(i-1)), new AtomicAction(primitiveAtoms.get(i)) );
					
					// Update block / hide command.
					block.getAtoms().add(nodeAtoms.get(i-1));
					block.getAtoms().add(primitiveAtoms.get(i));
					
					if (shouldHide) {
						hide.getAtoms().add(primitiveAtoms.get(i-1));
						hide.getAtoms().add(nodeAtoms.get(i));
					}
				}
			}			
			else {
				   sync = new Synchronization(
						new AtomicAction(nodeAtoms.get(i)), new AtomicAction(primitiveAtoms.get(i)) );
				// Update block / hide command.
					block.getAtoms().add(nodeAtoms.get(i));
					block.getAtoms().add(primitiveAtoms.get(i));
			}
			
			Atom targetAction = targetAtoms.get(i);
					
			if (sync != null) {
				Implication impl = new Implication();
				impl.setCondition(sync.toString());
				impl.setAction(new AtomicAction(targetAction));
				comm.getImplications().add(impl);
			}
			
			// Update block / hide command.
			
			if (shouldHide) {
				hide.getAtoms().add(targetAction);
			}
			
		}
	}
		
	/*
	 * Ask the converters whether the atom is visible.
	 */
/*	private boolean isVisible(PrimitiveEnd end, int index) {
		// All converters must agree that it is visible.
		if (!nodes.isVisible(end, index)) return false;		
		if (end.getPrimitive() instanceof Channel &&
				!channels.isVisible(end, index)) return false;
		if (end.getPrimitive() instanceof Component &&
				!channels.isVisible(end, index)) return false;
		return true;
	}
*/
	
	
	/**
	 * Get the specification created by this converter.
	 * @return The specification.
	 */
	public Specification getSpecification() {
		return specification;
	}
	
	/**
	 * Get the created process for an element.
	 * @param element The element.
	 * @return Created process.
	 */
	public Process getProcess(Connectable element) {
		return processes.get(element);
	}
	
	/**
	 * Get the set of elements which have been converted already.
	 * @return Set of converted elements.
	 */
	public Collection<Connectable> getElements() {
		return processes.keySet();
	}
	
	/**
	 * Get the name of a primitive end according to this converter.
	 * @param end Primitive end.
	 * @return The name.
	 */
	public String getEndName(PrimitiveEnd end) {
		return names.get(end);
	}

	public DataTypeManager getDataTypeManager() {
		return dataTypeManager;
	}

	public void setDataTypeManager(DataTypeManager manager) {
		channels.setDataTypeManager(manager);
		nodes.setDataTypeManager(manager);
		components.setDataTypeManager(manager);
		this.dataTypeManager = manager;
	}
		
	public void setScope(ReoScope scope) {
		channels.setScope(scope);
		nodes.setScope(scope);
		components.setScope(scope);
	}
	
	public String getSyncName(PrimitiveEnd end, int index) {
		// This is hacky. Need to fix that.
		Atom atom = getAtoms(end.getNode()).get(end).get(index);
		return atom.getName().substring(0, atom.getName().length()-1);
	}

}
