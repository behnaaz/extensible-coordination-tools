package org.ect.codegen.v2.core.ui;

import java.io.File;

import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.reo.Module;
import org.ect.reo.diagram.part.ReoDiagramEditor;

public class ActiveReoDiagramEditor {

	public static Module extractModule() throws Exception {
		return (Module) get().getDiagram().getElement();
	}

	public static String extractCanonicalPath() throws Exception {
		return new File(Files.tryCanonize(((IFileEditorInput) get()
				.getEditorInput()).getFile().getRawLocation().makeAbsolute()
				.toString())).getCanonicalPath();
	}

	public static ReoDiagramEditor get() throws Exception {
		return (ReoDiagramEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}
}
