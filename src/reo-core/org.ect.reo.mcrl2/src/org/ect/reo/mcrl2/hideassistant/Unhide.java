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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Unhide {
	String infilePath="";
	String outfilePath="";
	public ArrayList<String> findHiddenActions(String inpath){
		System.out.print(inpath);
		ArrayList<String> res = new  ArrayList<String>();
		try{
			System.out.println("findhiddenactions"+inpath);
			    // Open the file that is the first 
			    // command line parameter
			    FileInputStream fstream = new FileInputStream(inpath);
			    System.out.println("fstream"+fstream.available());
			    // Get the object of DataInputStream
			    DataInputStream in = new DataInputStream(fstream);
			     BufferedReader br = new BufferedReader(new InputStreamReader(in));
			     System.out.println( br.ready());
			    String strLine;
			    //Read File Line By Line
			    while ((strLine = br.readLine()) != null)   {
			    	    getActions2Hide(res, strLine);			
			            System.out.println(strLine);
				}
			    in.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		return res;			
	}

private void getActions2Hide(ArrayList<String> res, String strLine) {
	String[] actions;
	int loc = strLine.indexOf("hide({");
	  if (loc>=0){
		  int lloc = strLine.indexOf("}");
		  if (lloc>0){
			  String s = (String) strLine.subSequence(loc+"hide({".length(), lloc);
			  actions = s.split(",");
			  for (String o : actions)
				  res.add(o);			    		  
		  }
	  }
}
	//only hides actions that are mentioned in tohide array
	public void doHide(ArrayList<String> tohide, String outpath, String inpath){
		try{
			    FileOutputStream fstream = new FileOutputStream(outpath);
			    DataOutputStream out = new DataOutputStream(fstream);
			    OutputStreamWriter oss = new OutputStreamWriter(out);
			    BufferedWriter bw = new BufferedWriter(oss);
			    // Open the file that is the first 
				    // command line parameter
				 FileInputStream istream = new FileInputStream(inpath);
				    // Get the object of DataInputStream
				    DataInputStream in = new DataInputStream(istream);
				    BufferedReader br = new BufferedReader(new InputStreamReader(in));
				    String strLine;
				    //Read File Line By Line
				    while ((strLine = br.readLine()) != null)   {
				      String nstrLine = "";
				      int loc = strLine.indexOf("hide({");
				      if (loc>0){
				    	          nstrLine += strLine.substring(0, loc+"hide({".length());  
				       	  		  ArrayList<String> original = new ArrayList<String>();
				    	  		  getActions2Hide(original, strLine);
				    	  		  boolean first = true;
				    	  		  for (String s : original){
				    	  	           //if marked to hide then add it to new file
				    	  			   if (tohide.contains(s)){
				    	  				   nstrLine += ((first)?"":",") + s; 
				    	  				   first = false;
				    	  			   }				       
				    	  		  }
				    	  		  nstrLine+="},";
				    	  		  
				      }
				      else nstrLine=strLine;
				      bw.write(nstrLine);				     
					}
				    bw.flush();
				    oss.flush();
				    oss.close();
				    out.close();
				    in.close();
				    }catch (Exception e){//Catch exception if any
				      System.err.println("Error: " + e.getMessage());
				    }
	}
	
}
