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
package org.ect.reo.mcrl2.hideassistant;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author behnaz TODO: Showing the list of actions to hide, actions connected
 *         to the components are missing
 * 
 *         It only extracts the actions which are already hidden it is an unhide
 *         action should let undidden one be hidden too.
 * 
 *         The fullmap
 */
@SuppressWarnings("serial")
public class HideActionAssistant extends JDialog implements ActionListener {
	JButton bOk;
	JFrame frame;

	class MyTCompare implements Comparator<String> {
		public int compare(String arg0, String arg1) {
			return (arg0.trim().compareToIgnoreCase(arg1.trim()));
		}

	}

	StringBuffer choices;
	JLabel pictureLabel;
	protected static String outPath;
	protected static String inPath;
	static Unhide u;

	public String getOutputFileName() {
		return outPath;
	}

	public String getHideResult() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(outPath));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			reader.close();
			System.out.print(stringBuilder.toString());
			return stringBuilder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public HideActionAssistant(String originalmCRL2) {
		super(new JFrame(), "Actions to Hide", true);
		ArrayList<String> actions = new ArrayList<String>();

		File originalmCRL2File;
		try {
			originalmCRL2File = File.createTempFile("reo2mcrl2", ".mcrl2");
			FileWriter writer = new FileWriter(originalmCRL2File);
			writer.write(originalmCRL2);
			writer.close();
			inPath = originalmCRL2File.getAbsolutePath();
			File refinedmCRL2File = File.createTempFile("reo2mcrl2Refined",
					".mcrl2");
			outPath = refinedmCRL2File.getAbsolutePath();
			final Unhide u = new Unhide();
			actions = u.findHiddenActions(inPath);

			Collections.sort(actions, new MyTCompare());

			final JPanel checkPanel = new JPanel(new GridLayout(15, 2));
			for (String s : actions) {
				// Create the check boxes.
				JCheckBox button = new JCheckBox(s);
				button.setSelected(s.trim().length() < 2
						|| !Character.isLetter(s.charAt(s.length() - 1)));
				// Register a listener for the check boxes.
				button.addActionListener(this);
				// Put the check boxes in a column in a panel
				checkPanel.add(button);
			}
			getContentPane().add(checkPanel, BorderLayout.EAST);
			bOk = new JButton("OK");
			// Use the default text position of CENTER, TRAILING (RIGHT).

			JPanel buttonPane = new JPanel();
			buttonPane.add(bOk);
			bOk.addActionListener(this);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// Listen for actions on buttons 1 and 3.
			bOk.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// Execute when button is pressed
					ArrayList<String> tohide = getChecked(checkPanel);
					System.out.println("tohide" + tohide);
					u.doHide(tohide, outPath, inPath);
					System.out.println(inPath);
					System.out.println(outPath);
					// frame.setVisible(false);
					checkPanel.setVisible(false);
					dispose();
				}
			});
			add(checkPanel, BorderLayout.LINE_START);
			// ///// setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
			setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String getInputPath() {
		JFileChooser chooser = new JFileChooser();
		// Note: source for ExampleFileFilter can be found in FileChooserDemo,
		// under the demo/jfc directory in the Java 2 SDK, Standard Edition.
		int returnVal = chooser.showOpenDialog(HideActionAssistant.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}

	protected ArrayList<String> getChecked(Container checkPanel) {
		ArrayList<String> actions = new ArrayList<String>();
		for (Component c : checkPanel.getComponents()) {
			if (c instanceof JCheckBox) {
				JCheckBox chb = (JCheckBox) c;
				if (chb.isSelected()) {
					actions.add(chb.getText());
				}
			}
		}
		return actions;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("...");

		if ("ok".equals(e.getActionCommand())) {

		}
	}

	public void itemStateChanged(ItemEvent arg0) {

	}
}