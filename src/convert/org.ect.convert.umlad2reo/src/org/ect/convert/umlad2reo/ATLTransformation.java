package org.ect.convert.umlad2reo;
//
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.core.service.CoreService;
import org.osgi.framework.Bundle;

public class ATLTransformation {
	
	private static URL asmURL;
	private static IInjector injector;
	private static IExtractor extractor;
	private static IReferenceModel umlMetamodel;
	private static IReferenceModel reoMetamodel;
	private static String reoEcorePath;
	private static String umlEcorePath;
	
	public ATLTransformation()
	{	
		Bundle bundle = Platform.getBundle("cwi.umlad2reo.atl"); 		
		asmURL = bundle.getEntry("src/transformation/ac2reo.asm");///src/???
		if (asmURL == null)
			System.out.print("ac2reo.asm not found");
		try {
			injector = CoreService.getInjector("EMF"); 
			extractor = CoreService.getExtractor("EMF"); 	
			reoEcorePath = bundle.getEntry("src/model/reo.ecore").toString();//tocheck	
			umlEcorePath = bundle.getEntry("src/model/UML_21.ecore").toString();
			if (reoEcorePath == null)
				System.out.print("reo.ecore not found");
		} catch (ATLCoreException e) {
			e.printStackTrace();
		}
	}
	
	public OutputStream translate(IFile umlacfile, OutputStream rawReoOutput) throws Exception {
		// Defaults
		ModelFactory factory = CoreService.createModelFactory("EMF"); //$NON-NLS-1$

		// Metamodels
		umlMetamodel = factory.newReferenceModel();
		injector.inject(umlMetamodel, "http://www.eclipse.org/uml2/2.1.0/UML"); //$NON-NLS-1$
		reoMetamodel = factory.newReferenceModel();
		injector.inject(reoMetamodel, reoEcorePath); 

		// Getting launcher
		ILauncher launcher = null;
		launcher = CoreService.getLauncher("EMF-specific VM"); //$NON-NLS-1$
		launcher.initialize(Collections.<String, Object> emptyMap());

		// Creating models
		IModel reoModel = factory.newModel(reoMetamodel);
		IModel umlModel = factory.newModel(umlMetamodel);

		// Loading existing model
		injector.inject(umlModel, umlacfile.getFullPath().toString());

		// Launching
		launcher.addOutModel(reoModel, "OUT", "Reo"); //$NON-NLS-1$ //$NON-NLS-2$
		launcher.addInOutModel(umlModel, "IN", "UML"); //$NON-NLS-1$ //$NON-NLS-2$

		launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), Collections
		.<String, Object> emptyMap(), asmURL.openStream());

		// Saving model
		extractor.extract(reoModel, rawReoOutput, Collections.<String, Object> emptyMap());
	
		// Refresh workspace
	//	umlacfile.getParent().refreshLocal(IProject.DEPTH_INFINITE, null);
	
		return rawReoOutput;
		
	}

}

	