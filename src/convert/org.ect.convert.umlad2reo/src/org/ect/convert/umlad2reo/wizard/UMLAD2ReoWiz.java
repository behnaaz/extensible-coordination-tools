package org.ect.convert.umlad2reo.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

import org.ect.convert.umlad2reo.ATLTransformation;
import org.ect.convert.umlad2reo.ReoUtil;
import org.ect.reo.Module;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;
import org.ect.reo.diagram.part.ReoDiagramEditorUtil;

public class UMLAD2ReoWiz extends BasicNewFileResourceWizard {
	
	class MyWizardNewFileCreationPage extends WizardNewFileCreationPage{
		InputStream inputStream;
			
		public MyWizardNewFileCreationPage(String pageName,
				IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected InputStream getInitialContents() {
			return inputStream;
		};
	}

	
	private  MyWizardNewFileCreationPage newFilePage;
	private ByteArrayOutputStream rawReo = new ByteArrayOutputStream();
	private IFile umlacInputFile;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		
		newFilePage =	new MyWizardNewFileCreationPage("UML2 Activity Diagrams to Reo", StructuredSelection.EMPTY);
		//???? change baraye bpel ham newFilePage.setFileName(value)
		newFilePage.setFileExtension("reo");
		
		Object first = currentSelection.getFirstElement();
		//
		
		if (first instanceof IFile){
			umlacInputFile = (IFile) first;// ((IFile) first).getLocationURI().getPath();
			/*
			 * try {
				bpmnSource = BpmnUtil.getBpmnDiagram((IFile)first);
			} catch (IOException e) {
				try {
					throw new Exception("Error: Input file does not contain any BpmnDiagram!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			 * */
		}
		//TODO else (first instanceof UMLActivity??)
		//
        
	}
	@Override
	public void addPages() {
		addPage(newFilePage);
	}

	
	
	@Override
	public boolean performFinish() {
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				try {
						translate(monitor);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
			}

			private void translate(IProgressMonitor monitor) throws Exception{
				monitor.beginTask("UMLAD to Reo", 4);
				
				monitor.subTask("preprocessing UMLAD");	
			//	ByteArrayOutputStream refinedBpmn = new BpmnStreamRefiner().refine(bpmnDiagram2ByteArrayInputStream(bpmnSource));//new BpmnPreProcessManager().preprocess(
			//	saveTempResult(refinedBpmn, "BEH_pre", "bpmn");
			 	monitor.worked(1);
			
			 	monitor.subTask("applying ATL transformation");		
			 	EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
			    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(org.eclipse.uml2.uml.resource.UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);

			 	rawReo = (ByteArrayOutputStream) new ATLTransformation().translate(umlacInputFile, rawReo);
				monitor.worked(1);
				
				//??String tempFilePath = saveTempResult(rawReo, "BEH_pre_", "reo");
				
				monitor.subTask("post-processing Reo");		
			//del	ByteArrayOutputStream finalReo = polish(rawReo);
		    	monitor.worked(1);
				
				monitor.subTask("saving results");	
			 
				newFilePage.inputStream = new ByteArrayInputStream(rawReo.toByteArray());
				monitor.done();
				
				
			//	ByteArrayOutputStream finalReo = loadAndSave(rawReo);
		    	monitor.worked(1);
				
			}

			private ByteArrayOutputStream polishh(ByteArrayOutputStream rawReo) 
			throws IOException, PartInitException {
				//Resource res = new ResourceSetImpl().createResource(fileURI);
				Resource res = new ResourceSetImpl().createResource(URI.createURI("gobbledygook"));
				String s = rawReo.toString();
				System.out.print(s.trim());
				res.load(new ByteArrayInputStream(rawReo.toByteArray()), Collections.EMPTY_MAP);//((rawReo), Collections.EMPTY_MAP);		
		
				Module module = (Module)res.getContents().get(0);
		
		ReoUtil.refine(module);
		
		Diagram diagram = ViewService.createDiagram(module, ModuleEditPart.MODEL_ID,
		                                      ReoDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		res.getContents().add(diagram);
		res.getContents().add(diagram.getElement());
		
		ByteArrayOutputStream finalReo = new ByteArrayOutputStream();
	//	res.save(finalReo, Collections.EMPTY_MAP);

		
		
	

		res.save(ReoDiagramEditorUtil.getSaveOptions());
	//////////	ReoDiagramEditorUtil.openDiagram(res);

		
		
		
		return finalReo;
		
	}
			
			

		
			private String saveTempResult(ByteArrayOutputStream baos, String fileName, String fileAppendix) throws IOException
			{
				File temp1 = File.createTempFile(fileName, new String(".").concat(fileAppendix));
				FileOutputStream outStream = new FileOutputStream(temp1);
				baos.writeTo(outStream);
				// Refresh workspace
				////////////////////////////	umlacfile.getParent().refreshLocal(IProject.DEPTH_INFINITE, null);
				outStream.close();
				//temp1.deleteOnExit();
				return temp1.getAbsolutePath();
			}
			
			
		};
		try {
            getContainer().run(true, true, op);            
    		IFile ifile = newFilePage.createNewFile();
    		ifile.getParent().refreshLocal(IProject.DEPTH_INFINITE, null);		
    		
    		//ByteArrayOutputStream finalReo = loadAndSave(rawReo, URI.createURI(ifile.getLocationURI().toString()));
	
    	//	newFilePage.inputStream = new ByteArrayInputStream(finalReo.toByteArray());
    	//chetori bazesh konam in lanatio!!!	
    //		ReoDiagramEditorUtil.openDiagram(new ResourceSetImpl().createResource(newFilePage.inputStream.));
    		
    		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}