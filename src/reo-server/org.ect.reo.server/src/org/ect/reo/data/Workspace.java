package org.ect.reo.data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.util.ReoNames;

//import cwi.reo.reconfiguration.Reconfiguration;
//import cwi.reo.reconfiguration.ReconfigurationDescription;
//import cwi.reo.reconfiguration.ReconfigurationRule;


/**
 * Workspaces serve as a container for networks and all elements of
 * these networks. They are also used for resolving network identifiers, i.e.
 * finding the element in the network list that an identifier refers to, and
 * computing network identifiers for elements in the list.
 * 
 * @author Christian Koehler
 *
 */
public class Workspace {
	
	private static int lastNetworkId = 0;
	
	private NetworkList networks;
	//private List<ReconfigurationRule> rules;
	private File resource;
	private HashMap<Module,File> containers;
	
	/**
	 * Default constructor.
	 * @param resource Folder used for this workspace.
	 */
	public Workspace(File resource) {
		this.resource = resource;
		this.networks = new NetworkList();
		//this.rules = new ArrayList<ReconfigurationRule>();
		this.containers = new HashMap<Module,File>();
		// Create folder if necessary.
		if (!resource.exists()) {
			resource.mkdirs();
		}
	}
	
	
	/**
	 * Load the contents of this workspace.
	 * @param monitor Progress monitor.
	 */
	public void reload(IProgressMonitor monitor) {
				
		// Clear network list.
		networks.clear();
		
		// Search for *.reo and *.reconf files in subdirectories.
		List<String> reoFolders = new ArrayList<String>();
		List<String> reoFiles = new ArrayList<String>();

		List<String> reconfFolders = new ArrayList<String>();
		List<String> reconfFiles = new ArrayList<String>();

		for (File dir : resource.listFiles()) {
			if (dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					String name = file.getName();
					if (name.endsWith("." + Reo.FILE_EXT)) {
						reoFolders.add(dir.getName());
						reoFiles.add(file.getName());
					}
					//else if (name.endsWith("." + Reconfiguration.FILE_EXT)) {
					//	reconfFolders.add(dir.getName());
					//	reconfFiles.add(file.getName());
					//}
				}
			}
		}
		
		// Load files.
		monitor.beginTask("Loading workspace...", reoFiles.size() + reconfFiles.size());
		for (int i=0; i<reoFiles.size(); i++) {
			internalLoadNetworks(reoFolders.get(i), reoFiles.get(i), new SubProgressMonitor(monitor,1));
		}
		for (int i=0; i<reconfFiles.size(); i++) {
			internalLoadRules(reconfFolders.get(i), reconfFiles.get(i), new SubProgressMonitor(monitor,1));
		}
		monitor.done();
	}
	
	
	/*
	 * Internal method for loading reconfiguration files.
	 */
	private void internalLoadRules(String folder, String file, SubProgressMonitor monitor) {
		monitor.beginTask("Loading reconfiguration rules", 1);
		
		//File container = new File(resource.getAbsolutePath() + File.separator + folder);
		//File reconfFile = new File(container.getAbsolutePath() + File.separator + file);
		
		//ReconfigurationDescription description = Reconfiguration.load(reconfFile.getAbsolutePath());
		//rules.addAll(description.getRules());
		
		monitor.worked(1);
		monitor.done();
	}


	/*
	 * Internal load method. Expects an existing reo file in the workspace.
	 * The file must exist under resource/folder/file.
	 */
	private void internalLoadNetworks(String folder, String file, IProgressMonitor monitor) {
		
		monitor.beginTask("Loading file...", 10);
		
		File container = new File(resource.getAbsolutePath() + File.separator + folder);
		File reoFile = new File(container.getAbsolutePath() + File.separator + file);
		
		// Load the *.reo file
		System.out.println("Loading Reo file " + reoFile.getAbsolutePath());
		URI uri = URI.createFileURI(reoFile.getAbsolutePath());
		Module module = Reo.loadModule(uri);
		containers.put(module, container);
		
		// Load networks.
		List<Network> loaded = new ArrayList<Network>();
		for (Connector connector : module.getConnectors()) {
			// Check if this connector is already loaded as a part of another network.
			boolean exists = false;
			for (Network network : networks) {
				if (network.getConnectors().contains(connector)) {
					exists = true;
					break;
				}
			}
			// Create a new network.
			if (!exists) {
				Network network = new Network(connector);
				loaded.add(network);
			}
		}
				
		// Load file "resource.ini"
		File propFile = new File(container.getAbsolutePath() + File.separator + "resource.ini");
		List<Integer> ids = new ArrayList<Integer>();
		Properties properties = new Properties();
		if (propFile.exists()) {
			try {
				properties.load(new FileInputStream(propFile));
			} catch (Exception e) {
				propFile.delete();
			}
		}
		// Load network ids.
		if (properties.containsKey("networks")) {
			StringTokenizer tokenizer = new StringTokenizer((String) properties.get("networks"), ",");
			while (tokenizer.hasMoreElements()) {
				String next = tokenizer.nextToken();
				int id;
				try {
					id = Integer.parseInt(next);
				} catch (NumberFormatException e) {
					id = ++lastNetworkId;
				}
				ids.add(id);
			}
		}
		while (loaded.size()>ids.size()) {
			ids.add(++lastNetworkId);
		}
		monitor.worked(1);
		
		// Register the networks.
		IProgressMonitor sub = new SubProgressMonitor(monitor, 9);
		sub.beginTask("Processing networks", networks.size());
		
		for (int i=0; i<loaded.size(); i++) {
			
			Network network = loaded.get(i);
			int id = ids.get(i);
			
			// Last check whether the id is really unique.
			synchronized (networks) {
				while (networks.get(id)!=null) {
					id = ++lastNetworkId;
				}
				networks.put(id, network);
				ids.set(i, id);
			}
			
			// Create a folder for the network.
			File networkFolder = new File(container.getAbsolutePath() + File.separator + "networks" + File.separator + id);
			networkFolder.mkdirs();
			network.setResource(networkFolder, file);
			network.init(new SubProgressMonitor(sub,1));
			
		}
		
		// Save "resource.ini".
		String idsValue = "";
		for (int i=0; i<ids.size(); i++) {
			idsValue = idsValue + ids.get(i);
			if (i!=ids.size()-1) idsValue = idsValue + ",";
		}
		properties.put("resource", reoFile.getName());
		properties.put("networks", idsValue);
		
		try {
			if (!propFile.exists()) propFile.createNewFile();
			properties.store(new FileOutputStream(propFile), "Networks configuration file for " + reoFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		monitor.done();
		
	}

	
	
	/*
	 * Accessing and modifying the networks in this workspace.
	 */


	/**
	 * Get the networks in this workspace.
	 * @return Networks.
	 */
	public Iterable<Network> getNetworks() {
		return networks;
	}
	
	/*
	public List<ReconfigurationRule> getReconfigurationRules() {
		return rules;
	}
	*/
	
	/**
	 * Get the network with the given id.
	 * @param id Network id.
	 * @return The network.
	 */
	public Network getNetwork(int id) {
		return networks.get(id);
	}
	
	/**
	 * Change the id of a network. This does not check whether
	 * the network is running or whether it overrides an existing network.
	 * @param network The network.
	 * @param id The new id.
	 */
	public void setNetworkId(Network network, int id) {
		int oldId = getId(network);
		networks.remove(oldId);
		networks.put(id, network);
		network.setIdentifier(new NetworkIdentifier(id));
	}
	
	
	/**
	 * Get the id of a network.
	 * @param The network.
	 * @return id Network id.
	 */
	public int getId(Network network) {
		for (Integer id : networks.keySet()) {
			if (networks.get(id)==network) return id;
		}
		return -1;
	}

	
	/**
	 * Add networks to this workspace.
	 * @param file A *.reo file.
	 * @return True on success.
	 * @throws IOException IOException.
	 */
	public boolean loadNetworks(File file, IProgressMonitor monitor) throws IOException {
		
		if (!file.getName().endsWith("." + Reo.FILE_EXT)) {
			monitor.done();
			return false;
		}
		
		monitor.beginTask("Loading networks...", 10);
		
		// Copy the file into the workspace.
		File dir = File.createTempFile(file.getName()+"-", "0", resource);
		dir.delete();
		dir.mkdirs();
		
		File target = new File(dir.getAbsolutePath() + File.separator + file.getName());
		target.createNewFile();
		
		// Copy the file.
		InputStream in = new FileInputStream(file);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
		IOUtils.copy(in, out);
		in.close();
		out.close();
		
		monitor.worked(1);
		
		// Load the file.
		internalLoadNetworks(dir.getName(), file.getName(), new SubProgressMonitor(monitor, 9));
		monitor.done();
		return true;
		
	}
	
	
	/**
	 * Add reconfiguration rules to this workspace.
	 * @param file A *.reconf file.
	 * @return True on success.
	 * @throws IOException IOException.
	 */
	/*public boolean loadReconfigurationRules(File file, IProgressMonitor monitor) throws IOException {

		monitor.beginTask("Loading networks...", 10);

		if (!file.getName().endsWith("." + Reconfiguration.FILE_EXT)) {
			monitor.done();
			return false;
		}
		
		// Copy the file into the workspace.
		File dir = File.createTempFile(file.getName()+"-", "0", resource);
		dir.delete();
		dir.mkdirs();
		
		File target = new File(dir.getAbsolutePath() + File.separator + file.getName());
		target.createNewFile();
		
		// Copy the file.
		InputStream in = new FileInputStream(file);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
		IOUtils.copy(in, out);
		in.close();
		out.close();
		
		monitor.worked(1);
		
		// Load the file.
		internalLoadRules(dir.getName(), file.getName(), new SubProgressMonitor(monitor, 9));
		monitor.done();
		return true;
		
	}
	*/
	
	/**
	 * Remove a network from this workspace.
	 * @param network Network to be removed.
	 */
	public void removeNetwork(Network network) {
		
		// Unregister the network.
		networks.remove(getId(network));

		// Remove connectors / components from the modules.
		Set<Module> modules = new HashSet<Module>();
		for (Connector connector : network.getConnectors()) {
			Module module = (Module) connector.eContainer();
			if (module!=null) {
				module.getConnectors().remove(connector);
				modules.add(module);
			}
		}
		for (Component component : network.getComponents()) {
			Module module = (Module) component.eContainer();
			if (module!=null) {
				module.getComponents().remove(component);
				modules.add(module);
			}
		}
		
		// Save the changes.
		for (Module module : modules) {
			try {
				Reo.saveModule(module);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// If the module is empty we can remove its folder.
		for (Module module : modules) {
			if (module.getComponents().isEmpty() && module.getConnectors().isEmpty()) {
				try {
					FileUtils.deleteDirectory(containers.get(module));
					containers.remove(module);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
		
	
	/*
	 * Network identifier resolving. 
	 */
	
	/**
	 * Get the associated network for a network identifier.
	 * Returns <code>null</code> if the identifier does not 
	 * refer to a valid network.
	 */
	public Network getNetwork(NetworkIdentifier identifier) {
		if (identifier.ids().size()<1) return null;
		return networks.get(identifier.ids().get(0));
	}
			
	/**
	 * Get the associated connector for a network identifier.
	 * Returns <code>null</code> if the identifier does not 
	 * refer to a valid connector.
	 */
	public Connector getConnector(NetworkIdentifier identifier) {
		
		// At least two elements in the identifier.
		if (identifier.ids().size()<2) return null;		
		
		// Resolve the network.
		Network network = getNetwork(identifier);
		if (network==null) return null;
		
		// Check if it is a valid index.
		int index = identifier.ids().get(1);
		if (index>=0 && index<network.getConnectors().size()) {
			return network.getConnectors().get(index);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the associated component for a network identifier.
	 * Returns <code>null</code> if the identifier does not 
	 * refer to a valid component.
	 */
	public Component getComponent(NetworkIdentifier identifier) {
		
		// At least two elements in the identifier.
		if (identifier.ids().size()<2) return null;		
		
		// Resolve the network.
		Network network = getNetwork(identifier);
		if (network==null) return null;
		
		// Check if it is a valid index.
		int index = identifier.ids().get(1) - network.getConnectors().size();
		if (index>=0 && index<network.getComponents().size()) {
			return network.getComponents().get(index);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the associated PrimitiveEnd for a network identifier.
	 * Returns <code>null</code> if the identifier does not 
	 * refer to a valid end.
	 */
	public PrimitiveEnd getPrimitiveEnd(NetworkIdentifier identifier) {
		
		// At least three elements in the identifier.
		if (identifier.ids().size()<3) return null;		
		
		// Resolve the component.
		Component component = getComponent(identifier);
		if (component==null) return null;
		
		// Check if it is a valid index.
		int index = identifier.ids().get(2);
		if (index>=0 && index<component.getAllEnds().size()) {
			return component.getAllEnds().get(index);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Resolve a network identifier. Returns <code>null</code>
	 * if the network identifier is not valid in this context.
	 * @param identifier The network identifier.
	 * @return The resolved element.
	 */
	public EObject resolve(NetworkIdentifier identifier) {	
		if (getPrimitiveEnd(identifier)!=null) return getPrimitiveEnd(identifier);
		if (getConnector(identifier)!=null) return getConnector(identifier);
		if (getComponent(identifier)!=null) return getComponent(identifier);
		if (getNetwork(identifier)!=null) return getNetwork(identifier);
		return null;
	}
	
	/**
	 * Get the name of a resolved network identifier.
	 * @param identifier The network identifier.
	 * @return The name of the resolved element.
	 */
	public String getResolvedName(NetworkIdentifier identifier) {
		
		// Resolve the identifier.
		EObject target = resolve(identifier);
		String name = "?";
		
		// Check the type.
		if (target instanceof Network) name = "Network"; else
		if (target instanceof Component) name = ReoNames.getName((Component) target); else
		if (target instanceof Connector) name = ReoNames.getName((Connector) target); else
		if (target instanceof SinkEnd) name = "Sink"; else
		if (target instanceof SourceEnd) name = "Source"; 
		
		name = name + " " + identifier.toString();
		if (target instanceof PrimitiveEnd) {
			PrimitiveEnd end = (PrimitiveEnd) target;
			if (end.getName()!=null && !end.getName().equals("")) {
				name = name + " (" + end.getName() + ")";
			}
		}
		return name;
	}

	
	/*
	 * Computing network identifiers for given elements of the network list.
	 */
	
	/**
	 * Get the network identifier for a network in this network list.
	 */
	public NetworkIdentifier getIdentifier(Network network) {
		int id = getId(network);
		if (id==-1) return null;
		return new NetworkIdentifier(id);
	}
	
	/**
	 * Get the network identifier for a connector in this network list.
	 */
	public NetworkIdentifier getIdentifier(Connector connector) {
		
		for (Network network : networks) {
			if (network.getConnectors().contains(connector)) {
				NetworkIdentifier identifier = getIdentifier(network);
				identifier.ids().add( network.getConnectors().indexOf(connector) );				
				return identifier;				
			}
		}
		return null;
	}

	/**
	 * Get the network identifier for a component in this network list.
	 */
	public NetworkIdentifier getIdentifier(Component component) {
		
		for (Network network : networks) {
			if (network.getComponents().contains(component)) {
				NetworkIdentifier identifier = getIdentifier(network);
				identifier.ids().add( network.getConnectors().size() + network.getComponents().indexOf(component) );				
				return identifier;
			}
		}
		return null;
	}
	
	/**
	 * Get the network identifier for a primitive end (port) in this network list.
	 */
	public NetworkIdentifier getIdentifier(PrimitiveEnd end) {
		
		// End must belong to a component.
		if (!(end.getPrimitive() instanceof Component)) return null;
		Component component = (Component) end.getPrimitive();
		
		NetworkIdentifier identifier = getIdentifier(component);
		identifier.ids().add( component.getAllEnds().indexOf(end) );
		
		return identifier;
	}

	
}
