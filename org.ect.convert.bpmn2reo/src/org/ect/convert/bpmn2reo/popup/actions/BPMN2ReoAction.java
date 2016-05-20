package org.ect.convert.bpmn2reo.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.convert.bpmn2reo.converter.BPMN2toReoConverter;
import org.ect.convert.bpmn2reo.converter.Messages;

public class BPMN2ReoAction implements IObjectActionDelegate {

	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public BPMN2ReoAction() {
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
		BPMN2toReoConverter c = new BPMN2toReoConverter();
		c.convert(Messages.BPMN2ReoAction_inputFile, Messages.BPMN2ReoAction_outputFile);
		MessageDialog.openInformation(
			shell,
			Messages.BPMN2ReoAction_2,
			Messages.BPMN2ReoAction_3);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
