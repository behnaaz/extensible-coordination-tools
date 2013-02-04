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
package org.ect.reo.animation.folders;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;


public class TemporaryFolder extends File {
	
	/**
	 * Default constructor.
	 */
	public TemporaryFolder(File folder) {
		super((folder.exists() && !folder.canWrite()) ? 
				folder.getAbsolutePath()+"_"+((int) (Math.random()*1000)) : 
				folder.getAbsolutePath());
		_mkdirs();
		deleteOnExit();
	}
	
	public TemporaryFolder(String name) {
		this(new File(TMP_ROOT.getAbsolutePath() + File.separator + name));
	}
	
	public File getChild(String name) {
		return new File(getAbsolutePath() + File.separator + name);
	}
	
	/*
	 * The original implementation seems to not work on some 
	 * 64 bit linux systems. See {@link java.io.File#mkdirs()}. 
	 */
	private void _mkdirs() {
		
		List<File> dirs = new Vector<File>();
		File current = this;
		while (current!=null) {
			dirs.add(current.getAbsoluteFile());
			current = current.getParentFile();
		}
		try {
			for (int i=dirs.size()-1; i>=0; i--) {
				dirs.get(i).mkdir();
			}
		} catch (Throwable t) {
			System.err.println("Error creating temporary folder:\n" + t);
		}
		
	}
	
	/**
	 * Create a new file inside of this folder. Returns the created file.
	 */
	public File createFile(String name, InputStream in) throws IOException {
		
		File target = getChild(name);
		target.createNewFile();
		
		OutputStream out = new BufferedOutputStream(new FileOutputStream(target));
		copy(in, out);
		
		return target;
	}
	
	
	/**
	 * Create a new file.
	 */
	public File createNewFile(String prefix, String suffix) throws IOException {
		File file = File.createTempFile(prefix, suffix, this);
		return file;
	}
	
	
	/**
	 * Copy a file into this folder.
	 */
	public File copyFile(File file) throws IOException {		
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		return createFile(file.getName(), in);
	}
	
	
	/**
	 * Copy a Java resource into this folder.
	 */
	public File copyJavaResource(Class<?> clazz, String resource) throws IOException {
		InputStream in = new BufferedInputStream(clazz.getResourceAsStream("/" + resource));
		return createFile(resource, in);
	}
	
	
	/**
	 * Copy an Eclipse resource into this folder.
	 */
	public File copyEclipseResource(String pluginId, String resource) throws IOException {
		return copyFile(findEclipseResource(pluginId, resource));
	}

	
	
	/**
	 * Find an Eclipse resource.
	 */
	public static File findEclipseResource(String pluginId, String resource) throws IOException {
		try {
			URL bundleURL = FileLocator.find(Platform.getBundle(pluginId), Path.fromOSString(resource), null);
			URL fileURL = FileLocator.toFileURL(bundleURL);
			return new File(fileURL.getPath());
		} catch (Throwable t) {
			try {
				return new File(TemporaryFolder.class.getResource(resource).toURI());
			} catch (URISyntaxException e) {
				throw new IOException(e.getMessage());
			}
		}
	}
	
	
	/*
	 * Convert a string into an InputStream.
	 */
	protected static InputStream stringToInputStream(String data) {
		return new ByteArrayInputStream(data.getBytes());
	}
	
	/*
	 * Copy data.
	 */
	protected static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[1024];
	    int i = 0;
	    while ((i = in.read(buf)) != -1) {
	    	out.write(buf, 0, i);
	    }
	    in.close();
	    out.close();
	}
	
	// Init the root tmp dir.
	static {
		try {
			File tmp = File.createTempFile("test", "me");
			TMP_ROOT = tmp.getParentFile();
			tmp.delete();
		} catch (IOException e) {
			TMP_ROOT = new File("/tmp");
		}
	}	
	
	// Root folder for all temporary folders.
	private static File TMP_ROOT;
	
	// Generated serial id.
	private static final long serialVersionUID = -3502146060123886403L;
	
}
