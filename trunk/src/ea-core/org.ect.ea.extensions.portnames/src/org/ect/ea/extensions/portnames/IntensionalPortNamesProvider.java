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
package org.ect.ea.extensions.portnames;

import java.text.ParseException;
import java.util.StringTokenizer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.util.IValidationResult;

public class IntensionalPortNamesProvider  implements ITextualExtensionProvider {

	// The ID of this extension.
	public static final String EXTENSION_ID = "cwi.ea.extensions.intensionalPortNames";
	
	// Name of this extension.
	public static final String EXTENSION_NAME = "Intensional Port Names";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new IntensionalPortNamesProvider());
		
	/**
	 * Create a default IntensionalPortNames extension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		// Create an empty intentional port names.
		return new IntensionalPortNames();
	}
	
	/**
	 * Create a silent extension. This is the same as the default one.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return createDefaultExtension(transition);
	}

	/**
	 * Check whether a port names extension is silent, i.e. empty.
	 */
	public boolean isSilentExtension(IExtension extension) {
		IntensionalPortNames names = (IntensionalPortNames) extension;
		return names.getRequests().isEmpty() && names.getFirings().isEmpty();
	}


	
	public String editExtension(IExtension extension) {
		IntensionalPortNames portNames = (IntensionalPortNames) extension;
		String Temp1 = portNames.getRequests().toString();
		String Temp2 = portNames.getFirings().toString();
		
		String RequestStr = Temp1.substring(1,Temp1.length()-1);
		String FiringStr = Temp2.substring(1, Temp2.length()-1);
		
		
		return "{" + RequestStr + "} | {" + FiringStr +"}"; 
	}
	
	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		IntensionalPortNames portNames = new IntensionalPortNames();
		StringListExtension requests = null;
		StringListExtension firings = null;
		StringListExtension requestTemp;
		StringListExtension firingTemp;
		int modeSetting = -1;
		
		
		int bar = value.indexOf('|');
		requestTemp = StringListExtension. parse(value.substring(0, bar)); 
		firingTemp = StringListExtension.parse(value.substring(bar+1)); 
		requestTemp.trimAll();
		firingTemp.trimAll();
		String Rtemp = requestTemp.toString();
		String Ftemp = firingTemp.toString();
		
		/**
		 *	mode 1 : 				  { data arrivals } | {}  
		 *  mode 2 :       							 {} | { data-flows }
		 *  mode 3 : { synchronization of mixed nodes } | { data-flows }
		 */
		StringTokenizer token = new StringTokenizer(value,"|");
		if(token.countTokens()==1 && bar==value.length()-1)	modeSetting = 1;
		else if(token.countTokens()==1 && bar==0)	modeSetting = 2;
		else	modeSetting = 3;
		
		
		if(modeSetting==1 || modeSetting==3){
			boolean wellDefined = true;
			if(Rtemp.charAt(0)=='{' && Rtemp.charAt(Rtemp.length()-1)=='}'){
				if(Rtemp.substring(1, Rtemp.length()-1).length()>=1)	
					requests = StringListExtension.parse(Rtemp.substring(1, Rtemp.length()-1));
				else wellDefined = false;
			} 
			else {
				if(Rtemp.substring(0).length()>=1)	
					requests = StringListExtension.parse(Rtemp.substring(0)); 
				else wellDefined = false;
			}
			
			if(wellDefined){
				requests.trimAll();
				if(requests.toString().length()>=1)	
					portNames.getRequests().addAll(requests.getValues());
			}
		}
		
		if(modeSetting==2 || modeSetting==3){
			boolean wellDefined=true;
			if(Ftemp.charAt(0)=='{' && Ftemp.charAt(Ftemp.length()-1)=='}'){
				if(Ftemp.substring(1, Ftemp.length()-1).length()>=1)	
					firings = StringListExtension.parse(Ftemp.substring(1, Ftemp.length()-1));
				else	wellDefined = false;
			}
			else{
				if(Ftemp.substring(0).length()>=1)	
					firings  = StringListExtension.parse(Ftemp.substring(0));
				else wellDefined=false;
			}
			
			if(wellDefined){
				firings.trimAll();
				if(firings.toString().length()>=1)	
					portNames.getFirings().addAll(firings.getValues());
			}
		}
		
		return portNames;
	}

		
	/**
	 * Compute the product of two port name extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>();
		IntensionalPortNames p1 = (IntensionalPortNames) x1;
		IntensionalPortNames p2 = (IntensionalPortNames) x2;
		
		Transition t1 = (Transition) x1.getOwner();
		Transition t2 = (Transition) x2.getOwner();			
						
		if (PortNamesProductConditions.canIJoin(t1, t2)) {
			IntensionalPortNames joined = IntensionalPortNames.join(p1, p2);
			result.add(joined);	
		}
		else{
			EList<IntensionalPortNames> transitions = PortNamesProductConditions.canMJoin(t1, t2);
			
			if(!transitions.isEmpty()){
				for(int i=0;i<transitions.size()-1;i++){
					IntensionalPortNames n1 = transitions.get(i++);
					IntensionalPortNames n2 = transitions.get(i);
					
					IntensionalPortNames joined = IntensionalPortNames.join(n1, n2);
					result.add(joined);
				}
			}
		}
				
		return result;
		
	}

	

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IExtensionProvider#validateExtension(org.ect.ea.extensions.IExtension)
	 */
	public IValidationResult validateExtension(IExtension extension) {
		return ((IntensionalPortNames) extension).validate();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#getLabelColor()
	 */
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#isReadOnly(org.ect.ea.extensions.IExtension)
	 */
	public boolean isReadOnly(IExtension extension) {
		return false;
	}

}
