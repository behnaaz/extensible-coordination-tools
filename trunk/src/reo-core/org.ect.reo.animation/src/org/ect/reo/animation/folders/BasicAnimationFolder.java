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

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Vector;

import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.templates.AnimationFrameTemplate;
import org.ect.reo.animation.templates.AnimationIndexTemplate;
import org.ect.reo.animation.templates.MenuFrameTemplate;
import org.ect.reo.animation.templates.TitleFrameTemplate;
import org.ect.reo.prefs.ReoPreferences;

import com.anotherbigidea.flash.movie.Movie;



public class BasicAnimationFolder extends TemporaryFolder {
	
	public static final String INDEX = "index.html";
	public static final String TITLE = "title.html";
	public static final String MENU = "menu.html";
	public static final String ANIMATION_SUBFOLDER = "animations";
	public static final String ANIMATION_SWF = "animation{0,number,integer}.swf";
	public static final String ANIMATION_HTML = "animation{0,number,integer}.html";
	
	// Name of the animations and the folder.
	private String name;
	
	// Sub-folder for the actual Flash animations.
	private TemporaryFolder data;
	
	// Name of the style sheet file.
	private String styleSheet;
	
	// The animations.
	private List<AnimationList> animations;
	
	// Scale factor for the Flash animations. 
	private double scaleFactor = 1.0;
	
	// List of movie descriptions.
	private List<MovieDescription> movieDescriptions;
	
	// Animation generation finished?
	private boolean finished = true;
	
	
	/**
	 * Default constructor.
	 * @param name Name of the connector.
	 */
	public BasicAnimationFolder(String name) {
		
		// Create the folder.
		super(checkFolderName(name));
		this.name = name==null || name.equals("") ? "Unnamed connector" : name;
		this.animations = new Vector<AnimationList>();
		this.movieDescriptions = new Vector<MovieDescription>();
		
		// Create a folder for the animations.
		String animPath = getAbsolutePath() + File.separator + "animations";
		data = new TemporaryFolder(new File(animPath));
		
	}
	
	
	/* ------ Generator methods for HTML templates. ------ */
	
	/**
	 * Generate the index. Subclasses may override this method.
	 */
	protected String generateIndex() {
		AnimationIndexTemplate index = new AnimationIndexTemplate();
		return index.generate(this);
	}
	
	/**
	 * Generate the title frame. Subclasses may override this method.
	 */
	protected String generateTitleFrame() {
		TitleFrameTemplate title = new TitleFrameTemplate();
		return title.generate(this);
	}
	
	/**
	 * Generate the animation frame. Subclasses may override this method.
	 */
	protected String generateAnimationFrame() {
		AnimationFrameTemplate animation = new AnimationFrameTemplate();
		return animation.generate(this);
	}
	
	
	/**
	 * Generate the animation frame. Subclasses may override this method.
	 */
	protected String generateMenuFrame() {
		MenuFrameTemplate menu = new MenuFrameTemplate();
		return menu.generate(this);
	}
	
	
	/* ------ The main actions. ------ */
	
	/**
	 * Start the generation of animations.
	 */
	public void start(Movie movie, File styleSheet) throws IOException {
		
		// Begin with the animation generation.
		finished = false;
		animations.clear();
		movieDescriptions.clear();
		
		// Copy the style sheet file.
		this.styleSheet = copyFile(styleSheet).getName();
				
		// Add the empty animation (and update the index).
		addAnimation(new AnimationList(), movie);
		
		// Create title frame and index.
		createFile(TITLE, stringToInputStream(generateTitleFrame()));		
		createFile(INDEX, stringToInputStream(generateIndex()));
		
	}
	
	
	
	/**
	 * Add an animation.
	 */
	public void addAnimation(AnimationList animation, Movie movie) throws IOException {
		
		if (finished) return;
		
		// Create a new movie description.
		int width = (int) (movie.getWidth() * scaleFactor);
		int height = (int) (movie.getHeight() * scaleFactor);
		int index = animations.size();
		String html = getHTML(index);
		String swf = getSWF(index);
		MovieDescription description = new MovieDescription(index, html, swf, width, height, ReoPreferences.loopAnimations());
		
		// Add the animation and the movie description.
		animations.add(animation);
		movieDescriptions.add(description);
		
		
		// Write the SWF file.
		File swfFile = new File(getSWF(index));
		createFile(data.getName() + File.separator + swfFile.getName(), stringToInputStream(""));
		movie.write(getAbsolutePath() + File.separator + swfFile.getPath());
		
		// Create the HTML frame.
		File htmlFile = new File(getHTML(index));
		createFile(data.getName() + File.separator + htmlFile.getName(), stringToInputStream(generateAnimationFrame()));
		
		// Update the menu.
		createFile(MENU, stringToInputStream(generateMenuFrame()));
		
	}
	
	
	/**
	 * This confirms that all animations have been generated.
	 * After invoking this method {@link #addAnimation(Movie)}
	 * should not be called anymore, but {@link #start(Movie, File)}
	 * can be called to regenerate everything.
	 */
	public void finish() throws IOException {
		finished = true;
		createFile(MENU, stringToInputStream(generateMenuFrame()));
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public String getIndex() {
		return INDEX;
	}
	
	public String getTitle() {
		return TITLE;
	}
	
	public String getMenu() {
		return MENU;
	}
	
	
	public String getStyleSheet() {
		return styleSheet;
	}
	
	
	public MovieDescription getMovieDescription(int index) {
		if (movieDescriptions.size()<=index) return null;
		return movieDescriptions.get(index);
	}
	
	
	protected String getSWF(int index) {
		MessageFormat format = new MessageFormat(ANIMATION_SWF);
		String filename = format.format(new Object[] { index });
		return data.getName() + '/' + filename;
	}
	
	protected String getHTML(int index) {
		MessageFormat format = new MessageFormat(ANIMATION_HTML);
		String filename = format.format(new Object[] { index });
		return data.getName() + '/' + filename;
	}
	
	public AnimationList getAnimation(int index) {
		if (index>=animations.size()) return null;
		return animations.get(index);
	}
	
	/**
	 * Get the number of animations.
	 */
	public int getLength() {
		return animations.size();
	}
	
	/**
	 * Get the name of the animations / the connector.
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;;
	}

	/**
	 * Get the scale factor for the Flash animations.
	 */
	public double getScaleFactor() {
		return scaleFactor;
	}
	
	/**
	 * Set the scale factor for the Flash animations.
	 */
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	/*
	 * Make sure the folder name is valid.
	 */
	private static String checkFolderName(String name) {
		if (name==null || name.equals("")) {
			name = "reo-animation";
		}
		name = name.replaceAll("\\.", "_");
		name = name.replaceAll("\\\\", "_");
		name = name.replaceAll("/", "_");
        name = name.replaceAll(" ", "_");
        name = name.replaceAll(":", "_");
        name = name.replaceAll("'", "");
        name = name.replaceAll("\"", "");
		return name;
	}
	
	// Generated serial id.
	private static final long serialVersionUID = -7558380733577464948L;

}
