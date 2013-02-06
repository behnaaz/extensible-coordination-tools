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
package org.ect.codegen;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.ect.codegen.parts.CodeGenPlugin;

public abstract class AbstractCodeGenerator implements ICodeGenerator {

	// Shell, set by the code generation wizard.
	private Shell shell;
	
	/**
	 * Create a project with the given name. If the project
	 * exists already, it will be returned. Further, the
	 * project is opened automatically.
	 * 
	 * @param name Name of the project.
	 * @param monitor Progress monitor.
	 * @return The open project.
	 * @throws CoreException Exception, if project creation fails.
	 */
	protected IProject createProject(String name, IProgressMonitor monitor) throws CoreException {
		
		monitor.beginTask("Creating project", 2);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);		

		if (!project.exists()) {
			project.create(new SubProgressMonitor(monitor, 1));
			project.open(new SubProgressMonitor(monitor, 1));
		}
		
		monitor.done();
		
		return project;
	
	}

	
	/**
	 * Create a file from a given string content. The argument folder
	 * has to exist already. No check is performed. 
	 * 
	 * @param folder Folder where the file is created.
	 * @param name Name of the file.
	 * @param content Content of the file.
	 * @param monitor ProgressMonitor to be used.
	 * @return The created file.
	 * @throws CoreException If any kind of IO exception occurs.
	 */
	protected IFile createFileFromString(IContainer container, String name, String content, IProgressMonitor monitor) throws CoreException {
		
		if (content==null) content = "";
		monitor.beginTask("Creating " + name, 1);
		
		IFile file = container.getFile(new Path(name));
		InputStream input = new ByteArrayInputStream(content.getBytes());
		
		if (file.exists()) {
			file.delete(true, new NullProgressMonitor());
		}
		file.create(input, true, new SubProgressMonitor(monitor, 1));	
		try { input.close(); } catch (IOException e) {}
		
		monitor.done();
		
		return file;
	}
	
	
	/**
	 * Copy a plug-in file into a project. This is useful
	 * for libraries, JAR-files etc.
	 * 
	 * @param srcPlugin Plug-in containing the source file.
	 * @param srcPath Relative path of the source file.
	 * @param destProject Destination project.
	 * @param destPath Relative path of the destination.
	 * @param monitor Progress monitor to be used.
	 * @return The destination file.
	 * @throws CoreException If an IOException occurs.
	 */
	protected IFile copyFile(String srcPlugin, String srcPath, IProject destProject, String destPath, IProgressMonitor monitor) throws CoreException {

		monitor.beginTask("Copying " + srcPath, 1);
		IFile destination = destProject.getFile(srcPath);
		
		if (destination.exists()) {
			monitor.done();
			return destination;
		}		
		
		try {
			// Find the file.
			URL bundleURL = FileLocator.find(Platform.getBundle(srcPlugin), Path.fromOSString(srcPath), null);
			URL fileURL = FileLocator.toFileURL(bundleURL);
			String absolutePath = fileURL.toString().replaceFirst(fileURL.getProtocol()+":", "").replace('/', java.io.File.separatorChar);
			
			// Copy the file.
			FileInputStream in = new FileInputStream(new java.io.File(absolutePath));
			destination.create(in, true, new SubProgressMonitor(monitor, 1));
		}
		catch (IOException e) {
			Status status = new Status(IStatus.ERROR, srcPlugin, 1, "Error copying file: " + srcPath, e);
			throw new CoreException(status);
		}
		finally {
			monitor.done();
		}
		
		return destination;
	}
	
	
	/**
	 * Creates a new error status for a given error message. This is intended  
	 * to be used in {@link ICodeGenerator#validateGenModel(IGenModel)}.
	 * 
	 * @param message The error message.
	 * @return A new error status.
	 */
	protected IStatus newErrorMessage(String message) {
		return new Status(IStatus.ERROR, CodeGenPlugin.ID, IStatus.ERROR, message, null);
	}

	
	/**
	 * Creates a new warning status for a given warning message. This is intended  
	 * to be used in {@link ICodeGenerator#validateGenModel(IGenModel)}.
	 * 
	 * @param message The error message.
	 * @return A new error status.
	 */
	protected IStatus newWarningMessage(String message) {
		return new Status(IStatus.WARNING, CodeGenPlugin.ID, IStatus.WARNING, message, null);
	}


	/**
	 * Get currently used shell. For displaying dialogs etc.
	 * @return The shell.
	 */
	public Shell getShell() {
		return shell;
	}


	/**
	 * Set the shell. This is invoked by the code generation wizard.
	 * @param shell The new shell to be used.
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}

	
	// ----- Confirm dialog ----- //
	
    private boolean confirmed;
    private boolean finished;

    /**
     * Display a confirm dialog.
     * @param title Title of the dialog.
     * @param message Message of the dialog.
     * @return True, if the user confirmed.
     */
    protected boolean confirm(final String title, final String message) {

        final Shell sh = getShell();
        final AbstractCodeGenerator s_this = this;
        finished = false;
       
        sh.getDisplay().asyncExec(new Runnable() {
            public void run() {
                confirmed = MessageDialog.openConfirm(sh, title, message);
                //System.out.println("test:"+confirmed);
                synchronized (s_this) {
                    s_this.notify();
                    finished = true;
                }
            }
        });
       
        synchronized (this) {
            try {
                if (!finished) wait();
            } catch (Exception e) {
            	CodeGenPlugin.getInstance().logError("Error in code generator.", e);
                return false;
            }
        }
        return confirmed;
    }

}
