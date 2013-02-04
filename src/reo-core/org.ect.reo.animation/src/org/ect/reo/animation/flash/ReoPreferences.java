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
package org.ect.reo.animation.flash;

import java.io.File;

import org.ect.reo.Reo;
import org.ect.reo.animation.folders.TemporaryFolder;
import org.ect.reo.animation.parts.AnimationPlugin;
import org.ect.reo.prefs.ReoPreferenceStore;

import com.anotherbigidea.flash.movie.FontDefinition;
import com.anotherbigidea.flash.movie.FontLoader;
import com.anotherbigidea.flash.structs.Color;



public class ReoPreferences extends org.ect.reo.prefs.ReoPreferences {
	
	public static Color getFlashColor(String id) {
		org.eclipse.swt.graphics.Color color = ReoPreferenceStore.getColor(id);
		Color flashColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
		return flashColor;
	}
	
	//public static Color getFlashBackgroundColor() {
	//	return getFlashColor(ReoPreferenceConstants.ANIMATION_BACKGROUND_COLOR);
	//}
	
	
	// Default font.
	public static final String VERDANA_FONT_SWT = "VerdanaFont.swf";
	private static FontDefinition fontDefinition;
	private static String fontPath;
	
	
	public static FontDefinition getFontDefinition() {
		
		// Current path.
		String currentPath = getAnimationFont();
		if (currentPath==null || currentPath.trim().length()==0) {
			currentPath = VERDANA_FONT_SWT;
		}
		
		if (!currentPath.equals(fontPath)) {
			fontPath = currentPath;
			try {
				File file = new File(fontPath);
				if (!file.isAbsolute() && !file.exists()) {
					file = TemporaryFolder.findEclipseResource(AnimationPlugin.ID,fontPath);
				}
				fontDefinition = FontLoader.loadFont(file.getAbsolutePath());
			} catch (Exception e) {
				Reo.logError("Cannot find font: " + fontPath, e);
			}
		}
		return fontDefinition;
		
	}

}
