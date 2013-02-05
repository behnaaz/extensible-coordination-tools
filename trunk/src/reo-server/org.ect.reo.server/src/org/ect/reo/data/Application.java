package org.ect.reo.data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Reo;
import org.ect.reo.actions.PageHelper;
import org.ect.reo.animation.flash.figures.NamedContainerFigure;
import org.ect.reo.animation.flash.figures.NamedContainerFigure.IContainerNameCustomizer;
import org.ect.reo.jobapi.JobRunnable;
import org.ect.reo.jobapi.Log;
import org.ect.reo.jobapi.Scheduler;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo2ea.ca.Reo2CA;
import org.ect.reo2ea.ca.transform.channels.ChannelTransform;
import org.ect.reo2ea.util.XMIWrangler;

import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.ConstraintsPackage;
import org.ect.ea.extensions.portnames.PortNamesPackage;
import org.ect.ea.extensions.statememory.StateMemoryPackage;


public class Application {
	
	// Name of the workspace folder.
	public static final String WORKSPACE_NAME = "reo-live";
	
	// Workspace used in this application.
	private Workspace workspace;
	
	// Scheduler.
	private Scheduler scheduler;
	
	// Servlet context.
	private ServletContext context;
	
	/**
	 * Default constructor.
	 * @param context Servlet context.
	 */
	public Application(ServletContext context) {
		this.context = context;
	}
	
	
	/**
	 * Get the workspace of this application.
	 * @return The workspace.
	 */
	public Workspace getWorkspace() {
		
		if (workspace==null) {
			
			// Initialize packages and providers:
			Reo.initStandalone();
			CA.initStandalone();
			PortNamesPackage.init();
			ConstraintsPackage.init();
			StateMemoryPackage.init();			
			
			File tmp;
			try {
				tmp = File.createTempFile("reo", "live");
			} catch (IOException e) {
				tmp = new File("/tmp/test");
			}
    		File container = new File(tmp.getParent() + File.separator + WORKSPACE_NAME);
    		tmp.delete();
    		
    		// Create a workspace instance.
    		workspace = new Workspace(container);
    		
    		loadFont();
    		loadPrimitives();
    		initNameCustomizer(workspace);
			
    		getScheduler().runAsyncJob("Loading workspace...", new JobRunnable() {
				public void run(IProgressMonitor monitor, Log log) {
					workspace.reload(monitor);					
				}
    		}, PageHelper.PAGE_NETWORKS, PageHelper.PAGE_COMPONENTS, PageHelper.PAGE_CONNECTORS);
		}
		
		return workspace;
	}
	
	
	/**
	 * Get the scheduler of this application.
	 * @return The scheduler.
	 */
	public Scheduler getScheduler() {
		if (scheduler==null) {
			scheduler = new Scheduler();
		}
		return scheduler;
	}
	
	
	/*
	 *  Register the font to be used in SWFs.
	 */
    private void loadFont() {
		try {
    		String font = context.getInitParameter("font");
    		InputStream in = context.getResourceAsStream(font);
    		File target = File.createTempFile("font", ".swf");
    		target.deleteOnExit();
    		OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
    		IOUtils.copy(in, out);
    		in.close();
    		out.close();
    		ReoPreferences.setAnimationFont(target.getAbsolutePath());
		} catch (IOException e) {
			context.log("Error loading SWF font.", e);
		}
    }
    
    
    /*
     * Register a name customizer for the SWFs. This customizer adds the network
     * identifiers to connector and component names. 
     */
	private void initNameCustomizer(final Workspace workspace) {
		NamedContainerFigure.setNameCustomizer(new IContainerNameCustomizer() {

			public String getCustomizedName(View view, String name) {
				NetworkIdentifier identifier = null;
				if (view.getElement() instanceof Connector) {
					identifier = workspace.getIdentifier((Connector) view.getElement());
				} else if (view.getElement() instanceof Component) {
					identifier = workspace.getIdentifier((Component) view.getElement());
				}
				return (identifier!=null) ? workspace.getResolvedName(identifier) : name;
			}
		});	
	}

    
    /*
     * Register the built-in primitives for the Reo2EA conversion.
     */
    private void loadPrimitives() {
		try {
    		InputStream in = Reo2CA.class.getResourceAsStream("/primitives/builtins.ea");
    		File target = File.createTempFile("builtins", ".ea");
    		target.deleteOnExit();
    		OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
    		IOUtils.copy(in, out);
    		in.close();
    		out.close();
    		
    		Collection<Automaton> automata = new ArrayList<Automaton>();
   			automata.addAll(XMIWrangler.loadAutomata(target.getAbsolutePath()).getAutomata());    				
    		ChannelTransform.setTemplates(automata);
    		//PrimitiveLibrary.setLibraryFile(target.getAbsolutePath());
		}
		catch (IOException e) {
			context.log("Error loading built-in primitives for Reo2EA conversion.", e);
		}
    }
    
    

}
