package org.ect.reo.data;

import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationExpander;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.flash.ReoAnimations;
import org.ect.reo.data.CoordinatorThread.Status;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo2ea.ca.Reo2CAStandalone;
import org.ect.reo2ea.core.TransformationException;
import org.ect.reo2ea.transform.Composition.TransformResult;
import org.ect.reo2ea.util.CAUtil;

import com.anotherbigidea.flash.movie.Movie;

import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
//import cwi.reo.reconfiguration.ReconfigurationRule;
//import cwi.reo2ea.Reo2EA;


public class Network extends org.ect.reo.Network {
	
	public static final String ANIMATION_FOLDER = "animations";
		
	// Animation resources.
	private AnimationTable table;
	private AnimationMatrix animations;
	private NetworkView view;
	
	// Size of the animations.
	private Size swfSize;
	
	// Folder where we can put temporary files for this network.
	private File resource;
	
	// Files associated to this network.
	private File reoFile;
	private File eaFile;
	
	// Reo2CA conversion instance.
	private MyReo2CA reo2ea;
	private Automaton automaton;
	
	// Coordinator thread.
	private CoordinatorThread<String> coordinator;
	
	// Optional network identifier.
	private NetworkIdentifier identifier;
	
	// Reconfiguration history.
	//private List<ReconfigurationRule> reconfHistory = new ArrayList<ReconfigurationRule>();
	
	/**
	 * Default constructor.
	 */
	public Network() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param component Component in the network.
	 */
	public Network(Component component) {
		super(component);
	}
	
	/**
	 * Constructor.
	 * @param connector Connector in the network.
	 */
	public Network(Connector connector) {
		super(connector);
	}
	
	public Network(Module module) {
		super(module);
	}
	
	public Network(Collection<?> content) {
		super(content);
	}

	/**
	 * Initialize this network.
	 * @param monitor Progress monitor.
	 */
	public synchronized void init(IProgressMonitor monitor) {
		
		monitor.beginTask("Initializing network...", 12);
		
		// Update the network.
		super.update();
		
		// Create network view.
		view = new NetworkView(this);
		//view.performUpgrades();
		
		// Validate names and save the changes.
		validateNames();
		monitor.worked(1);
		
		// Let's do the real work...
		
		// Compute the animations if no reconfiguration has happened yet.
		
		//if (reconfHistory.isEmpty()) 
		try {
			
			monitor.subTask("Computing colouring table...");
			table = super.getAnimationTable(new SubProgressMonitor(monitor, 7));
		
			monitor.subTask("Computing animations...");
			AnimationExpander expander = new AnimationExpander();
			animations = expander.expand(table, new SubProgressMonitor(monitor, 1));		
		
			// Add an empty animation.
			AnimationList list = new AnimationList();
			list.add(new Animation());
			animations.add(0, list);
			
			// Render animations.
			if (resource!=null && resource.isDirectory() && resource.canWrite()) {
			
				File folder = new File(resource.getAbsolutePath() + File.separator + ANIMATION_FOLDER);
				if (!folder.exists()) folder.mkdir();
			
				List<Movie> movies = ReoAnimations.generate(view, animations, new SubProgressMonitor(monitor, 1));
				swfSize = NotationFactory.eINSTANCE.createSize();
				swfSize.setWidth((int) (movies.get(0).getWidth() * ReoPreferences.getScaleFactor()));
				swfSize.setHeight((int) (movies.get(0).getHeight() * ReoPreferences.getScaleFactor()));
			
				for (int i=0; i<movies.size(); i++) {
					File swf = getSWF(i);
					try {
						movies.get(i).write(swf.getAbsolutePath());
					} catch (IOException e) {
						// TODO: use JobProgress to output error messages to the log.
						e.printStackTrace();
					}
				}
			} else {
				monitor.worked(1);
			}
		
		} catch (Throwable t) {
			System.err.println("Error generating animations:");
			t.printStackTrace();
		}
		
		
		// Convert to constraint automaton.
		if (getConnectors().size()>1) {
			System.out.println("Warning: more then one connector per network not support yet.");
		}
				
		// Generate the automaton.
		try {
			reo2ea = new MyReo2CA(getConnectors().get(0));
			automaton = reo2ea.getResult();
			
			eaFile = new File(resource.getAbsolutePath() + File.separator + reoFile.getName().replaceAll("."+Reo.FILE_EXT, "."+EA.FILE_EXT));
			cwi.ea.automata.Module module = EA.create(eaFile.getAbsolutePath());
			module.getAutomata().add(automaton);
			EA.save(module);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitor.worked(1);
		}
		
		// Create a new coordinator.
		coordinator = new CoordinatorThread<String>(this);

		monitor.done();
		
	}
	
	/*
	 *  Make sure that all boundary nodes in the network have unique names.
	 */
	private void validateNames() {
		HashSet<String> names = new HashSet<String>();
		char last = 'A';
		int index = 0;
		for (Connector connector : getConnectors()) {
			for (Node node : connector.getNodes()) {
				
				if (node.isMixedNode()) continue;
				String name = node.getName();
								
				while (name==null || name.trim().equals("") || names.contains(name)) {
					if (last=='Z') {
						last='A';
						index++;
					}
					name = String.valueOf(last++);
					if (index>0) name = name + index;
				}
				node.setName(name);
				names.add(name);
			}
		}				
	}

	/**
	 * Save all resources in this network.
	 * @throws IOException IOException.
	 */
	public void save() throws IOException {
		Set<Module> modules = new HashSet<Module>();
		for (Connector connector : getConnectors()) {
			Module module = (Module) connector.eContainer();
			if (module!=null) modules.add(module);
		}
		for (Component component : getComponents()) {
			Module module = (Module) component.eContainer();
			if (module!=null) modules.add(module);
		}
		for (Module module : modules) {
			Reo.saveModule(module);
		}
	}
	
	
	public File getSWF(int index) {
		if (resource==null) return null;
		File folder = new File(resource.getAbsolutePath() + File.separator + ANIMATION_FOLDER);
		File swf = new File(folder.getAbsolutePath() + File.separator + index + ".swf");
		return swf;
	}
	
	public File getReoFile() {
		return reoFile;
	}
	
	public File getEAFile() {
		return eaFile;
	}
		
	public Automaton getAutomaton() {
		return automaton;
	}
	
	public String getPortName(PrimitiveEnd end) {
		return reo2ea.getEndName(end);
	}
	
	public Size getSWFSize() {
		return swfSize;
	}
	
	public NetworkView getView() {
		return view;
	}
	
	public Status getStatus() {
		return coordinator==null ? Status.STOPPED : coordinator.getStatus();
	}
	
	@Override
	public AnimationTable getAnimationTable() {
		return table!=null ? table : super.getAnimationTable();
	}

	@Override
	public AnimationTable getAnimationTable(IProgressMonitor monitor) {
		if (table!=null) {
			monitor.beginTask("Get animation table", 0);
			monitor.done();
			return table;
		} else {
			return super.getAnimationTable(monitor);
		}
	}
	
	public AnimationMatrix getAnimations() {
		return animations;
	}
	
	
	public void setResource(File folder, String file) {
		this.resource = folder;
		this.reoFile = new File(folder.getAbsolutePath() + File.separator + file);
	}
	
	public CoordinatorThread<String> getCoordinatorThread() {
		return coordinator;
	}
	
	public void setIdentifier(NetworkIdentifier identifier) {
		this.identifier = identifier;
	}
	
	/*public List<ReconfigurationRule> getReconfigurationHistory() {
		return reconfHistory;
	}
	*/
	
	@Override
	public String toString() {
		if (identifier!=null) return "Network " + identifier;
		else return super.toString();
	}
	
	class MyReo2CA extends Reo2CAStandalone {
		
		TransformResult transformed;
		
		public MyReo2CA(Connector c) throws Exception {
			super(c);
		}
		
		@Override
		public Automaton getResult() throws TransformationException {
			transformed = composition.transform(connector);
			Automaton ca= composition.compose(transformed.automata, new NullProgressMonitor());
//			beautify(ca, "S");
//			System.out.println(new cwi.ea.util.cacompat.CA(ca));
			Automaton hidden = hideInternals(ca , transformed.namingPolicy);
			setPortTypes(hidden);		
			CAUtil.trim(hidden);
			beautify(hidden, null);
			CAUtil.mergeTrans(hidden);
			return hidden;
		}	
		
		public String getEndName(PrimitiveEnd end) {
			return transformed.namingPolicy.getName(end);
		}
		
	}
	
}
