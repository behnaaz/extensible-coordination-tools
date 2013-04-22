package org.ect.codegen.v2.core.ui;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;

public class Workspace {

	public static String getCanonicalPath() throws Exception {
		return new File(ResourcesPlugin.getWorkspace().getRoot().getLocation()
				.toOSString()).getCanonicalPath();
	}
}
