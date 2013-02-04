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
package org.ect.reo.mcrl2.properties;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class MCRL2StyledTextAdapter implements ModifyListener {
	
	// mCRL2 keywords:
	private static final String[] KEYWORDS = new String[] { "sort", "act", "proc", "init" };

	// mCRL2 operators:
	private static final String[] OPERATORS = new String[] { "comm", "allow", "block", "hide", "sum" };

	// Colors:
	private static final Color COLOR_KEYWORD;
	private static final Color COLOR_NUMBER;
	private static final Color COLOR_STRING;
	private static final Color COLOR_METHOD;
	
	private static final Color COLOR_VARIABLE;
	private static final Color COLOR_PRECOMP;
	
	// Initialize the colors:
	static {

		Color color = JFaceResources.getColorRegistry().get("org.eclipse.jdt.ui.java_keyword");
		COLOR_KEYWORD = (color!=null) ? color : Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA);

		color = JFaceResources.getColorRegistry().get("org.eclipse.jdt.ui.java_string");
		COLOR_STRING = (color!=null) ? color : Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		
		//color = JFaceResources.getColorRegistry().get("org.eclipse.jdt.ui.numberHighlighting");
		//COLOR_NUMBER = (color!=null) ? color : Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		COLOR_NUMBER = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
		
		color = JFaceResources.getColorRegistry().get("org.eclipse.jdt.ui.methodHighlighting");
		COLOR_METHOD = (color!=null) ? color : Display.getDefault().getSystemColor(SWT.COLOR_BLACK);

		COLOR_VARIABLE = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		COLOR_PRECOMP = Display.getDefault().getSystemColor(SWT.COLOR_RED);
		
	}
	
	private StyledText text;
	
	/**
	 * Default constructor.
	 * @param text Text with mCRL2 code.
	 */
	public MCRL2StyledTextAdapter(StyledText text) {
		this.text = text;
		
		// Try to use the Java Editor font:
		Font font = JFaceResources.getFont("org.eclipse.jdt.ui.editors.textfont");
		if (font!=null) text.setFont(font);
		
		// Listen to modify events:
		text.addModifyListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
		
		String code = text.getText();
		
		// Reset the style:
		StyleRange none = new StyleRange();
		none.start = 0;
		none.length = code.length();
		none.fontStyle = SWT.NORMAL;
		text.setStyleRange(none);
		
		// Parse the code:
		CustomReader reader = new CustomReader(code); 
		StreamTokenizer st = new StreamTokenizer(reader);
		st.parseNumbers();
		st.ordinaryChar('.');
		st.wordChars('_','_');
		st.wordChars('$','$');
		st.wordChars('#','#');
		st.eolIsSignificant(false);
		st.slashSlashComments(true);
		st.slashStarComments(true);

		// Parse the file
		int token;
		try {
			token = st.nextToken();
			while (token != StreamTokenizer.TT_EOF) {				
				int length;
				switch (token) {
					
				case StreamTokenizer.TT_WORD:
					
					if (isKeyword(st.sval) || isOperator(st.sval) 
						|| isVariable(st.sval) || isPrecomp(st.sval)) {
						
						length = st.sval.length();
						StyleRange word = new StyleRange();
						word.start = Math.max(0, reader.position - length - 1);
						word.length = length;
						
						if (isKeyword(st.sval)) {
							word.fontStyle = SWT.BOLD;
							word.foreground = COLOR_KEYWORD;
						} else if (isOperator(st.sval)) {
							word.fontStyle = SWT.ITALIC;
							word.foreground = COLOR_METHOD;
						} else if (isVariable(st.sval)) {
							word.fontStyle = SWT.NORMAL;
							word.foreground = COLOR_VARIABLE;
						} else if (isPrecomp(st.sval)) {
							word.fontStyle = SWT.NORMAL;
							word.foreground = COLOR_PRECOMP;
						}
						text.setStyleRange(word);
					}
					break;

				case StreamTokenizer.TT_NUMBER:
					
					length = String.valueOf(st.nval).length();
					StyleRange number = new StyleRange();
					number.start = reader.position - length + 1;
					number.length = length-2;
					number.foreground = COLOR_NUMBER;
					text.setStyleRange(number);
					break;

				case '"':
					
					StyleRange string = new StyleRange();
					length = st.sval.length() + 2;
					string.start = reader.position - length;
					string.length = length;
					string.foreground = COLOR_STRING;
					text.setStyleRange(string);
					break;
					
				}	

				token = st.nextToken();
				
			}

		} catch (IOException io) {
			io.printStackTrace();
		}

	}
		
	private boolean isOperator(String string) {
		for (String operators : OPERATORS) {
			if (operators.equals(string)) return true;
		}
		return false;
	}

	private boolean isKeyword(String string) {
		for (String keyword : KEYWORDS) {
			if (keyword.equals(string)) return true;
		}
		return false;
	}
	
	private boolean isVariable(String string) {
		return string.startsWith("$");
	}

	private boolean isPrecomp(String string) {
		return string.startsWith("#");
	}
	
	class CustomReader extends StringReader {		
		
		public int position = 0;
//		private int length;
		
		public CustomReader(String s) {
			super(s);
//			length = s.length();
		}
		
		@Override
		public int read() throws IOException {
			int value = super.read();
			if (value>=0) position++;
//			if (position==length-1) position++;
			return value;
		}
		
		@Override
		public int read(char cbuf[], int off, int len) throws IOException {
			int read = super.read(cbuf, off, len);
			position += read;
//			if (position==length-1) position++;
			return read;
		}
		
	}
	
}
