package org.ect.convert.bpmn2reo.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;
import org.eclipse.ui.PartInitException;
import org.ect.convert.bpmn2reo.commons.Constants;
import org.ect.reo.Module;
import org.ect.reo.Reo;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;
import org.ect.reo.diagram.part.ReoDiagramEditorUtil;

//import org.ect.reo.Module;
/**
 * 
 * @author Hugo Bruneliere
 */
public class BPMN2toReoConverter {

	public String convert(String inputFile, String outputFile) {
		Reo.initStandalone();
		//EPackage.Registry.INSTANCE.put("http://www.eclipse.org/gmf/runtime/1.0.2/notation", GMFGenPackage.eINSTANCE);
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("notation", new GMFGenFactoryImpl());
		try {
			//ByteArrayOutputStream baos = readAllFileContent(inputFile);
			ByteArrayOutputStream rawReoModel = translate(inputFile);
			addDiagramInfo(rawReoModel, outputFile);
			//addDiagramInfo(baos, outputFile);
		} catch (IOException | PartInitException e) {
			//org.eclipse.emf.ecore.resource.Resource$IOWrappedException: Package with uri 'http://www.eclipse.org/gmf/runtime/1.0.2/notation' not found. (file:///C:/Users/Balali/workspaces/bpmn2reo/trans/counter.reo, 101, 142)
			e.printStackTrace();
		}
		//translate();
		return outputFile;
	}
/*
	private ByteArrayOutputStream readAllFileContent(String path) throws IOException {
		byte[] content = Files.readAllBytes(Paths.get(path));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(content);
		return baos;
	}
*/
	 ByteArrayOutputStream translate(final String inputModel) {
		 ByteArrayOutputStream rawout = null;
		 try {
			/*
			 * Initializations
			 */
			ILauncher transformationLauncher = new EMFVMLauncher();
			ModelFactory modelFactory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			IExtractor extractor = new EMFExtractor();

			/*
			 * Load metamodels
			 */
			IReferenceModel bpmn2Metamodel = modelFactory.newReferenceModel();
			injector.inject(bpmn2Metamodel, Constants.BPMN2toReoConverter_BPMNEcore);//"BPMN20.ecore");// "Metamodels/Company.ecore");
			IReferenceModel reoMetamodel = modelFactory.newReferenceModel();
			injector.inject(reoMetamodel, Constants.BPMN2toReoConverter_ReoEcore);

			/*
			 * Run "Cut" transformation
			 */
			IModel bpmn2Model = modelFactory.newModel(bpmn2Metamodel);
			injector.inject(bpmn2Model, inputModel);// "Models/sampleCompany.xmi");

			// transformationLauncher.initialize(new HashMap<String,Object>());
			// transformationLauncher.addInOutModel(bpmn2Model, "IN", "BPMN20");
			// IReferenceModel refiningTraceMetamodel =
			// modelFactory.getBuiltInResource("RefiningTrace.ecore");
			// IModel refiningTraceModel =
			// modelFactory.newModel(refiningTraceMetamodel);
			// transformationLauncher.addOutModel(refiningTraceModel,
			// "refiningTrace", "RefiningTrace");
			// transformationLauncher.launch(ILauncher.RUN_MODE, new
			// NullProgressMonitor(), new HashMap<String,Object>(),
			// new FileInputStream("test.asm"));//"Transformations/Cut.asm"));

			// IModel companyModel_Cut = bpmn2Model;
			// extractor.extract(companyModel_Cut,
			// "out.reo");//"Models/Java/sampleCompany_Cut.xmi");

			/*
			 * Run "ComputeTotal" transformation
			 */
			IModel reoModel_Total = modelFactory.newModel(reoMetamodel);
			
			FileInputStream fin = new FileInputStream(Constants.BPMN2toReoConverter_ASMFile);
			
			transformationLauncher.initialize(new HashMap<String, Object>());
			transformationLauncher.addInModel(bpmn2Model, Constants.BPMN2toReoConverter_2, Constants.BPMN2toReoConverter_3);
			transformationLauncher.addOutModel(reoModel_Total, Constants.BPMN2toReoConverter_4, Constants.BPMN2toReoConverter_5);
			transformationLauncher.launch(ILauncher.RUN_MODE,
					new NullProgressMonitor(), new HashMap<String, Object>(),
					fin);// Transformations/ComputeTotal.asm"));

			rawout = new ByteArrayOutputStream();
			extractor.extract(reoModel_Total, rawout,
					Collections.<String, Object> emptyMap());// "o1.reo");//"Models/Java/sampleCompany_Total.xmi");

			// Saving model
			// extractor.extract(reoModel, rawReoOutput, Collections.<String,
			// Object> emptyMap());

			/*
			 * Unload all models and metamodels (EMF-specific)
			 */
			EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
			// emfModelFactory.unload((EMFModel) companyModel_Cut);
			emfModelFactory.unload((EMFModel) reoModel_Total);
			emfModelFactory.unload((EMFReferenceModel) bpmn2Metamodel);
			emfModelFactory.unload((EMFReferenceModel) reoMetamodel);

			//addDiagramInfo(rawout, "o.reo");
			
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rawout;
	}
	
	/**
	 * Add diagram information to the given Reo model, saves it in a file, and open the model in the Reo editor
	 *
	 * @param reoModel the Reo model
	 * @param path     the file name in which the model will be stored
	 */
	private static void addDiagramInfo(
			ByteArrayOutputStream reoModel, String filePath) throws IOException, PartInitException {
		  /*Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().
		    put("abc", resourceFactoryForURIWithAbcProtocol);
		  Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().
		    put("xyz", resourceFactoryForURIWithXyzFileExtension);
		*/
		  //Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("reo", 
			//		new XMIResourceFactoryImpl());
		  
	//	ReoFactory rf = new ReoFactory();
//		Network n = rf.createNetwork();
		URI u = URI.createFileURI(filePath);//metamodels/reo
	//	Module m = Reo.loadModule(u);
		
		/*
		 * 1) Make a new resource
		 * 2) Load the Reo model into the resource 
		 */
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		//resourceSet.getPackageRegistry().
		//resourceSet.getPackageRegistry().put(ReoPackage.eNS_URI, ReoPackage.eINSTANCE); 
		//Registering the resource factory
		//resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("reo", 
			//	new XMIResourceFactoryImpl());
//resourceSet.getPackageRegistry().put("http://www.eclipse.org/uml2/2.0.0/UML", UMLPackage.eINSTANCE)
//URI fileURI = URI.createFileURI(new File("c:/response.xml").getAbsolutePath());

		
		Resource res = resourceSet.createResource(u);
		ByteArrayInputStream bais = new ByteArrayInputStream(reoModel.toByteArray());
		res.load(bais,
				Collections.EMPTY_MAP);

		Module module = (Module) res.getContents().get(0);

		// ReoUtil.refine(module);
		Diagram diagram = ViewService.createDiagram(module, ModuleEditPart.MODEL_ID,
				ReoDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		
				//ReoDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		res.getContents().add(diagram);
		res.getContents().add(diagram.getElement());

		//ByteArrayOutputStream finalReo = new ByteArrayOutputStream();
		// res.save(finalReo, Collections.EMPTY_MAP);

		res.save(ReoDiagramEditorUtil.getSaveOptions());
		ReoDiagramEditorUtil.openDiagram(res);

		//return finalReo;
	}

}