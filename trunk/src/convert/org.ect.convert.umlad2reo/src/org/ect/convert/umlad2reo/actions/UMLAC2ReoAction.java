package org.ect.convert.umlad2reo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.ect.convert.umlad2reo.wizard.UMLAD2ReoWiz;


public class UMLAC2ReoAction implements IObjectActionDelegate {
	
	private ISelection currentSelection;
	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public UMLAC2ReoAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// Getting files from selection
		IStructuredSelection iss = (IStructuredSelection)currentSelection;
		
		INewWizard wizard = new UMLAD2ReoWiz();
		wizard.init(PlatformUI.getWorkbench(), iss);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
		
		/////////////
		//for (Iterator<?> iterator = iss.iterator(); iterator.hasNext();) {
		//try {
			//translate((IFile)iterator.next());
		//} catch (Exception e) {
		//throw new RuntimeException(e);
	//	}
		//}
		MessageDialog.openInformation(
			shell,
			"Atl",
			"UML2 Activity Diagram to Reo transformation was executed.");
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.currentSelection = selection;
	}

}
