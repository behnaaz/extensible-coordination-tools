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
package org.ect.reo.aut2fsp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.ect.reo.aut2fsp.aut.AUT;
import org.ect.reo.aut2fsp.aut.Parser;
import org.ect.reo.aut2fsp.aut.Transition;
import org.ect.reo.aut2fsp.aut.TransitionComparable;
import org.ect.reo.aut2fsp.fsp.Action;
import org.ect.reo.aut2fsp.fsp.ActionProcess;
import org.ect.reo.aut2fsp.fsp.FSP;
import org.ect.reo.aut2fsp.fsp.Process;

/**
 * @generated NOT
 * @author Behnaz Changizi
 */
public class AUT2FSP {

	/**
	 * Returns a FSP specification generated from an aut file.                 
	 *  	@param  aut  the aut specification
	 *                         
	@return  the generated FSP specification
	 */
	public FSP convert(AUT aut) {
		FSP lts = new FSP();
		Collections.sort(aut.getTransitions(), new TransitionComparable());
		int idx = 0;
		while (idx < aut.getTransitions().size()) {
			int src = aut.getTransitions().get(idx).getSource().getName();
			int tgt;
			Process p = new Process(src);
			ArrayList<Transition> groupedTrans = aut.getTransStringFrom(src);
			//int cnt = 1;
			for (Transition trns : groupedTrans) {
				tgt = aut.getTransitions().get(idx).getTarget().getName();
				Action action = new Action(trns.getName());
				p.getActions().add(action);
				ActionProcess subterm = new ActionProcess(action, new Process(
						tgt));
				p.getSubterms().add(subterm);
				//cnt++;
				idx++;
				lts.AddProcess(p);
			}
		}
		return lts;
	}

	public ArrayList<String> convert(String autFileName) {
		ArrayList<String> autSpec = new ArrayList<String>();
		File file = new File(autFileName);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			System.out.println("Opening file:"+autFileName);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			while (dis.available() != 0) {
				@SuppressWarnings("deprecation")
				String line = dis.readLine();
				System.out.println(line);
				autSpec.add(line);
			}
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convert(autSpec);
	}

	public ArrayList<String> convert(ArrayList<String> autSpec) {
		ArrayList<String> res = new ArrayList<String>(); 
		FSP lts = new FSP();
		AUT2FSP a2l = new AUT2FSP();
		Parser p = new Parser();
		AUT aut = new AUT();
		for (String s : autSpec)
			p.parse(s, aut);
		lts = a2l.convert(aut);
		if (lts.processes.size() > 0)
			res = lts.serialize();
		return res;
	}

	//test
	private static final String inputfile = "/tmp/reo2mcrl28856083507804373333.aut";	
	public static void main(String args[]){
		System.out.println("*************");
		ArrayList<String> s = new AUT2FSP().convert(inputfile);
		System.out.println("***************"+s.toString());
	}
}
