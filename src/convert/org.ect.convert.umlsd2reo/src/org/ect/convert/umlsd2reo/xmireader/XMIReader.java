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
package org.ect.convert.umlsd2reo.xmireader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;

public class XMIReader{
	String modelname = "";
	public static String newline = System.getProperty("line.separator");
	public static class Constants
	{
		public static class In
		{
			public static String CombinedFragment = "umlCombinedFragment";
			public static String LostFound = "null";
		}
		public static class Out
		{
			public static class MessageType
			{
				public static String SYNCRONIZED = "SYNCRONIZED";
				public static String ASYNCRONIZED = "ASYNCRONIZED";
				public static String LOST = "LOST";
				public static String FOUND = "FOUND";
			}
			public static class DiagramType
			{
				public static String SIMPLE = "simple";
				public static String COMPOSED = "composed";
			}
			public static class OperatorType
			{
				public static String WEAK = "WEAK";
				public static String STRICT = "STRICT";
				public static String PARALLEL = "PARALLEL";
				public static String OPTION = "OPTION";
				public static String LOOP = "LOOP";
				public static String ALTERNATIVE = "ALTERNATIVE";
				public static String REF = "REF";
				public static String getOperatorType(String boumloperatorname)
				{
					if (boumloperatorname.compareTo("weak") == 0) return WEAK;
					else if (boumloperatorname.compareTo("strict") == 0) return STRICT;
					else if (boumloperatorname.compareTo("par") == 0) 	return PARALLEL;
					else if (boumloperatorname.compareTo("opt") == 0) 	return OPTION;
					else if (boumloperatorname.compareTo("loop") == 0) 	return LOOP;
					else if (boumloperatorname.compareTo("alt") == 0) 	return ALTERNATIVE;
					else if (boumloperatorname.compareTo("ref") == 0) 	return REF;
					else 	
					{
						System.out.println("+++Undefined boumloperatorname type: "+boumloperatorname);
						return "?";
					}
				}
			}
		}		   
	}

	public class Specification
	{
		String xmitype, xmiid, body;
		public Specification(String xmityp, String xmid, String bdy)
		{   
			xmitype = xmityp; xmiid = xmid; body = ""; 
			for (int i = 0; i < bdy.length(); i++)
			 	 if (bdy.charAt(i) != '[' && bdy.charAt(i) != ']')
					 body = body.concat(bdy.substring(i, i+1)); 
		}
	}
	public class Guard
	{
		Specification spec;
		String xmitype, xmiid;
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		public Guard(String xmityp,String xmid)
		{  xmitype = xmityp; xmiid = xmid; }
		public void addSpecification(Specification spc){spec = spc;}
		public Specification getSpecification(){return spec;}
	}
	public class Operand extends Fragment
	{
		Guard guard;
		String xmitype, xmiid;
		int order;
		public Operand(String name, String xmityp, String xmid, int ord, Fragment owner)
		{   super(name, "","","", owner); xmitype = xmityp; xmiid = xmid; order = ord; }
	}
	public class LifeLine
	{
		String xmitype, xmiid, name, represents;
		public LifeLine(String xmityp, String xmid, String nam, String reprsents)
		{	xmitype = xmityp; xmiid = xmid; name = nam; represents = reprsents;  }
	}
	public abstract class Fragment 
	{
		String name, xmitype, xmiid, covered;
		Fragment owner;
		Map<String, Fragment> innerfragments = new TreeMap<String, Fragment>();

		public Fragment(String nam, String xmityp, String xmid, String coverd, Fragment ownr)
		{	name = nam;	xmitype = xmityp; xmiid = xmid; covered = coverd; owner = ownr;	}
	
		public void addFragment(Fragment fg)
		{	innerfragments.put(fg.xmiid, fg); }

		public boolean hasCombined()
		{	 
			for (Fragment f : innerfragments.values())
				if (f instanceof CombinedFragment)
					return true;
			return false;
		}
		public int getCombinedFragmentNumber()
		{
			int cnt = 0;
			for (Fragment f : innerfragments.values())
				 if (f instanceof CombinedFragment)
					 cnt++;
			return cnt;
		}
	}
	public class CombinedFragment extends Fragment
	{
		String interactionOperator;
		ArrayList<Operand> Operands = new ArrayList<Operand>(); 
		public CombinedFragment(String nam, String xmitype, String xmiid, String commaseparatorcovereds, String interactionoprerator, Fragment owner)
		{
			super(nam, xmitype, xmiid, commaseparatorcovereds, owner);
			interactionOperator = interactionoprerator;
		}    
		public int getGuardsNumber()
		{
			int cnt = 0;
			for (Operand op : Operands)
				 if (op.guard != null)
					 cnt++;
			return cnt;
		}
	}
	public class MessageOccurrenceSpecification extends Fragment
	{
		String event, message;
		public MessageOccurrenceSpecification(String xmityp, String xmid, String coverd, String evnt, String messag, Fragment owner)
		{
			super("", xmityp, xmid, coverd, owner);
			event = evnt;
			message = messag;
		}
	}
	public class BehaviorOccurrenceSpecification extends Fragment
	{
		String _start, _finish;
		public BehaviorOccurrenceSpecification(String xmitype, String xmiid, String covered, String start, String finish, Fragment owner)
		{
			super("", xmitype, xmiid, covered, owner);
			_start = start;
			_finish = finish;
		}
	}
	public class ExecutionOccurrenceSpecification extends Fragment
	{
		String _event, _execution;
		public ExecutionOccurrenceSpecification(String xmitype, String xmiid, String covered, String event, String execution, Fragment owner)
		{
			super("", xmitype, xmiid, covered, owner);
			_event = event;
			_execution = execution;
		}    	   
	}
	public class Message
	{
		private String xmiid, name, messagesort, sendevent, receiveevent;
		public Message(String xmid, String nam, String messagesrt, String sendevnt, String receiveevnt, String connctor)
		{
			xmiid = xmid; name = nam; messagesort = messagesrt; sendevent = sendevnt; receiveevent = receiveevnt; 
		}
		public String getType()
		{
			return (messagesort.compareTo("synchCall")==0)?"SYNCRONIZED":"ASYNCRONIZED";
		}
	}
	public class SequenceDiagram extends Fragment{
		Map<String, LifeLine> LifeLines = new HashMap<String, LifeLine>(); 
		Map<String, Message> Messages = new TreeMap<String, Message>();
		public SequenceDiagram(String name){ super(name, "??","","", null); }
		public Message getMessageById(String msgid)
		{
			for (Message msg : Messages.values())
			{
				if (msg.xmiid.compareTo(msgid) == 0)
					return msg;
			}
			System.out.println("**--*--**Message " + msgid + " not found!");
			return null;
		}
		public String findMessageSourceTarget(String msgoccurenceid, Fragment fo)
		{
			MessageOccurrenceSpecification spec =  findMessageOccurance(msgoccurenceid, fo);
			if (spec != null)
			{ 
				LifeLine lifeline = sd.LifeLines.get(spec.covered);
				if (lifeline != null)
					return lifeline.name;
			}
			else
				System.out.println("**MessageOccurrenceSpecification with id = " + msgoccurenceid + " not found!");
			return Constants.In.LostFound;
		}
	}

	/* Generated by TOM (version 2.6): Do not edit this file */   public static org.w3c.dom.Node tom_mapping_buildAttribute(org.w3c.dom.Document dom,           String t0,             String v) {  org.w3c.dom.Attr res = dom.createAttribute(t0);   res.setValue(v);  return res; }  public static org.w3c.dom.Node tom_mapping_buildElement(org.w3c.dom.Document dom,         String name,          java.util.ArrayList attr,          java.util.ArrayList child) {   org.w3c.dom.Element res = dom.createElement(name);  for (int it=0;it<child.size();it++)   res.appendChild((org.w3c.dom.Node)child.get(it));   for (int it=0;it<attr.size();it++) {   org.w3c.dom.Node n = (org.w3c.dom.Node) attr.get(it);   res.setAttribute(n.getNodeName(),n.getNodeValue());  }  return res; }  public static org.w3c.dom.Node tom_mapping_buildEntityReference(org.w3c.dom.Document dom,         String name,           java.util.ArrayList child) {   org.w3c.dom.EntityReference res = dom.createEntityReference(name);  for (int it=0;it<child.size();it++)   res.appendChild((org.w3c.dom.Node)child.get(it));  return res; }  public static java.util.ArrayList tom_mapping_buildTNodeList(org.w3c.dom.NodeList n) {  java.util.ArrayList res = new java.util.ArrayList();  for (int it=0;it<n.getLength();it++)   res.add(n.item(it));  return res; }  public static java.util.ArrayList tom_mapping_buildNamedNodeMap(org.w3c.dom.NamedNodeMap n) {  java.util.ArrayList res = new java.util.ArrayList();  String str_name;  int ins;  for (int it=0;it<n.getLength();it++) {   str_name = n.item(it).getNodeName();   ins = res.size();   for(int jt=0;jt<res.size();jt++) {    if (((org.w3c.dom.Attr)res.get(jt)).getNodeName().compareTo(str_name)>0) {     ins = jt;     break;    }   }   res.add(ins,n.item(it));  }    return res; }  public static java.util.ArrayList tom_mapping_add(Object e, java.util.ArrayList l) {  l.add(e);  return l; }  public static java.util.ArrayList tom_mapping_newTNodeList() {  return new java.util.ArrayList(); }  /* Generated by TOM (version 2.6): Do not edit this file */private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static boolean tom_equal_term_TNode(Object t1, Object t2) {return t1.equals(t2);}private static boolean tom_is_sort_TNode(Object t) {return t instanceof org.w3c.dom.Node;}private static boolean tom_equal_term_TDocument(Object t1, Object t2) {return t1.equals(t2);}private static boolean tom_is_sort_TDocument(Object t) {return t instanceof org.w3c.dom.Document;}private static boolean tom_equal_term_TNodeList(Object l1, Object l2) {return  l1.equals(l2) ;}private static boolean tom_is_sort_TNodeList(Object t) {return  t instanceof java.util.ArrayList ;}private static boolean tom_is_fun_sym_concTNode( java.util.ArrayList  t) {return  (t!= null) && (t instanceof java.util.ArrayList) ;}private static  java.util.ArrayList  tom_empty_array_concTNode(int n) { return  new java.util.ArrayList(n) ;}private static  java.util.ArrayList  tom_cons_array_concTNode(org.w3c.dom.Node e,  java.util.ArrayList  l) { return  tom_mapping_add(e,(java.util.ArrayList)l)  ;}private static org.w3c.dom.Node tom_get_element_concTNode_TNodeList( java.util.ArrayList  l, int n) {return  ((org.w3c.dom.Node)(l.get(n))) ;}private static int tom_get_size_concTNode_TNodeList( java.util.ArrayList  l) {return  l.size() ;}   private static   java.util.ArrayList  tom_get_slice_concTNode( java.util.ArrayList  subject, int begin, int end) {      java.util.ArrayList  result =  new java.util.ArrayList(end-begin) ;     while(begin!=end) {       result =  tom_mapping_add( ((org.w3c.dom.Node)(subject.get(begin))) ,(java.util.ArrayList)result)  ;       begin++;     }     return result;   }    private static   java.util.ArrayList  tom_append_array_concTNode( java.util.ArrayList  l2,  java.util.ArrayList  l1) {     int size1 =  l1.size() ;     int size2 =  l2.size() ;     int index;      java.util.ArrayList  result =  new java.util.ArrayList(size1+size2) ;     index=size1;     while(index >0) {       result =  tom_mapping_add( ((org.w3c.dom.Node)(l1.get(size1-index))) ,(java.util.ArrayList)result)  ;       index--;     }      index=size2;     while(index > 0) {       result =  tom_mapping_add( ((org.w3c.dom.Node)(l2.get(size2-index))) ,(java.util.ArrayList)result)  ;       index--;     }     return result;   }private static boolean tom_is_fun_sym_ElementNode(org.w3c.dom.Node t) {return  (t!= null) && (t.getNodeType()==org.w3c.dom.Node.ELEMENT_NODE);}private static org.w3c.dom.Document tom_get_slot_ElementNode_Doc(org.w3c.dom.Node t) {return  ((org.w3c.dom.Element)t).getOwnerDocument();}private static  String  tom_get_slot_ElementNode_Name(org.w3c.dom.Node t) {return  ((org.w3c.dom.Element)t).getNodeName();}private static  java.util.ArrayList  tom_get_slot_ElementNode_AttrList(org.w3c.dom.Node t) {return  tom_mapping_buildNamedNodeMap(((org.w3c.dom.Element)t).getAttributes());}private static  java.util.ArrayList  tom_get_slot_ElementNode_ChildList(org.w3c.dom.Node t) {return  tom_mapping_buildTNodeList(((org.w3c.dom.Element)t).getChildNodes());}private static boolean tom_is_fun_sym_AttributeNode(org.w3c.dom.Node t) {return  (t!= null) && (t.getNodeType()==org.w3c.dom.Node.ATTRIBUTE_NODE);}private static org.w3c.dom.Document tom_get_slot_AttributeNode_Doc(org.w3c.dom.Node t) {return  ((org.w3c.dom.Attr)t).getOwnerDocument();}private static  String  tom_get_slot_AttributeNode_Name(org.w3c.dom.Node t) {return  ((org.w3c.dom.Attr)t).getName();}private static  String  tom_get_slot_AttributeNode_Specified(org.w3c.dom.Node t) {return  (((org.w3c.dom.Attr)t).getSpecified()?"true":"false") ;}private static  String  tom_get_slot_AttributeNode_Value(org.w3c.dom.Node t) {return  ((org.w3c.dom.Attr)t).getValue() ;} 
	static Document dom;
	SequenceDiagram sd = new SequenceDiagram("");
	
	public java.io.InputStream parseStringToIS(String str){
		   if(str==null) return null;
		   str = str.trim();
		   java.io.InputStream in = null;
		   try{
		     in = new java.io.ByteArrayInputStream(str.getBytes("UTF-8"));
		   }catch(Exception ex){}
		   return in;      
		 }
	
	public String transform(String in)
	{
		String result = "";
		String refinedinput = refine(in);
		DataInputStream temp = new DataInputStream(parseStringToIS(refinedinput));
		try 
		{	   
			dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(temp);
			start(dom.getDocumentElement());
			result = serialize(sd);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	

	MessageOccurrenceSpecification findMessageOccurance(String id, Fragment fo)
	{
		MessageOccurrenceSpecification spec = (MessageOccurrenceSpecification)fo.innerfragments.get(id);
		if (spec == null)
		{ 
			if (fo.innerfragments.size() > 0)
			{	
				for (Fragment cf : fo.innerfragments.values())
				{
					if (cf instanceof CombinedFragment)
					{
						spec = findMessageOccurance(id, cf);
						if (spec != null)
							return spec;
					}
				}
			}
			else
				System.out.println("MessageOccurrenceSpecification not found key: " + id);
		}
		return spec;
	}

	private static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
	
	String serialize(Fragment fo)
	{
		String result = "";
		if (fo instanceof CombinedFragment || fo instanceof SequenceDiagram && fo.hasCombined())
		{	
			if (fo instanceof CombinedFragment)
			{
				for (Operand op : ((CombinedFragment) fo).Operands)
				{
					if (!op.hasCombined())
						result = result + reportSimple(op); 
					else for (Fragment fop : op.innerfragments.values())
						if (fop instanceof CombinedFragment)
							if (!(fop instanceof MessageOccurrenceSpecification || fop instanceof BehaviorOccurrenceSpecification || fop instanceof ExecutionOccurrenceSpecification))
								result = result + serialize(fop);
				}
				result = result + reportComposed((CombinedFragment)fo);	
			}
			else
			{	
				for (Fragment f : fo.innerfragments.values())
				{
					if (f instanceof CombinedFragment)
					{	
						for (Operand op : ((CombinedFragment) f).Operands)
						{
							if (!op.hasCombined())
								result = result + reportSimple(op); 
							else for (Fragment fop : op.innerfragments.values())
								if (!(fop instanceof MessageOccurrenceSpecification || fop instanceof BehaviorOccurrenceSpecification || fop instanceof ExecutionOccurrenceSpecification))
									result = result + serialize(fop); 
						}
						result = result + reportComposed((CombinedFragment)f);
					}
					else if (f.hasCombined())
						if (!(f instanceof MessageOccurrenceSpecification || f instanceof BehaviorOccurrenceSpecification || f instanceof ExecutionOccurrenceSpecification))
							result = result + serialize(f);
				}
			}
		}
		else if ((fo instanceof SequenceDiagram) && !fo.hasCombined())
			result = result + reportSimple(fo);			
		return result;	
	}

	ArrayList<String> getParticipants(final Fragment fo)
	{	
		ArrayList<String> result = new ArrayList<String>();
		if (fo instanceof SequenceDiagram || fo instanceof Operand)
		{
			for (Fragment f : fo.innerfragments.values())
			{	
				if (f instanceof MessageOccurrenceSpecification)
				{
					MessageOccurrenceSpecification spec = (MessageOccurrenceSpecification)f;
					LifeLine lifeline = this.sd.LifeLines.get(spec.covered);
					if (lifeline != null && !result.contains(lifeline.name))
						result.add(lifeline.name);
				}
			}
		}
		return result;		
	}

	ArrayList<Message> getMessages(final Fragment fo) 
	{
		ArrayList<Message> result = new ArrayList<Message>();
		if (fo instanceof SequenceDiagram || fo instanceof Operand)
		{
			for (Fragment f : fo.innerfragments.values())
			{	
				if (f instanceof MessageOccurrenceSpecification)
				{
					MessageOccurrenceSpecification spec = (MessageOccurrenceSpecification)f;
					Message msg = sd.getMessageById(spec.message);
					if (msg != null && !result.contains(msg))
						result.add(msg);					
				}
			}
		}
		return result;
	}

	String reportSimple(Fragment fo) 
	{
		String result = newline + "SD name = \"" + fo.name + "\" type = \"" + Constants.Out.DiagramType.SIMPLE + "\"";
		result = result + newline + "\tSD.PARTICIPANTS";
		result = result + newline + newline;
		for (String p : getParticipants(fo))
			 result = result + "\t\tPARTICIPANT name = \"" + p + "\"" + newline;
		result = result + newline;
		result = result + "\t/SD.PARTICIPANTS";
		result = result + newline + newline;
		result = result + "\tSD.ACTIONS";
		result = result + newline + newline;
		for (Message msg : getMessages(fo))
		{
			String sender, receiver;
			sender = sd.findMessageSourceTarget(msg.sendevent, fo); 
			receiver = sd.findMessageSourceTarget(msg.receiveevent, fo);
			if (sender.compareTo(Constants.In.LostFound) == 0)
				result = result + "\t\tMESSAGE.FOUND name = \"" + msg.name + "\" receiver = \"" + receiver + "\"" + newline;
			else if (receiver.compareTo(Constants.In.LostFound) == 0)
				result = result + "\t\tMESSAGE.LOST name = \"" + msg.name + "\""+ " sender = \"" + sender + "\"" + newline;
			else
				result = result + "\t\tMESSAGE." + msg.getType() + " name = \"" + msg.name + "\""+ " sender = \"" + sender + "\""+" receiver = \"" + receiver + "\"" + newline;
		}
		result = result + newline;
		result = result + "\t/SD.ACTIONS" + newline;
		result = result + newline + "/SD" + newline;
		return result;
	}

	String reportComposed(CombinedFragment fo)
	{	
		String result = newline + "SD name = \"" + fo.name + "\" type = \"" + Constants.Out.DiagramType.COMPOSED + "\"";
		result = result + newline + "\tSD.OPERATORS";
		result = result + newline + newline + "\t\tOPERATOR." + Constants.Out.OperatorType.getOperatorType(fo.interactionOperator);
		if (fo.Operands.size() == 1)
			result = result + " member = \""+ fo.Operands.get(0).name + "\"";
		int i = 1;
		for (Operand op : fo.Operands)
		{
			result = result + " member" + ((((CombinedFragment)fo).Operands.size()>1)? Integer.toString(i):"") + " = \""+ op.name + "\"";
			i++;				
		}
		i = 1;
		for (Operand op : fo.Operands)
		{
			if (op.guard != null && op.guard.spec != null && op.guard.spec.body != null && op.guard.spec.body.length() > 0)
				result = result + " guard" + ((fo.getGuardsNumber()>1)? Integer.toString(i):"") + " = \"" + op.guard.spec.body + "\""; 
				i++;				
		}
		result = result + newline + newline;
		result = result + "\t/SD.OPERATORS" + newline;
		result = result + "/SD" + newline;
		return result;
	}

	String process(String str)
	{	
		return str.replaceAll("uml:", "uml").replaceAll("xmi:", "xmi");	
	}

	String refine(String infilename)
	{
		String out = new String();
		try {
			BufferedReader in = new BufferedReader(new FileReader(infilename));
			String str;
			while ((str = in.readLine()) != null) {            
				out += process(str) + newline;                     
			}
			in.close();
		} catch (IOException e) {System.out.println(e.getMessage());
		}
		return out;
	}

	private void start(Node doc) {
		{{ Object tomMatch199NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch199NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch199NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch199NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch199NameNumber_freshVar_4=tomMatch199NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch199NameNumber_freshVar_4)) {{  String  tomMatch199NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch199NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch199NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch199NameNumber_freshVar_4);{  String  tomMatch199NameNumber_freshVar_5=tomMatch199NameNumber_freshVar_1;if (tom_equal_term_String("xmiXMI", tomMatch199NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch199NameNumber_freshVar_6=tomMatch199NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_6)) {{ int tomMatch199NameNumber_freshVar_7=0;{  java.util.ArrayList  tomMatch199NameNumber_freshVar_9=tomMatch199NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_9)) {{ int tomMatch199NameNumber_freshVar_10=0;{ int tomMatch199NameNumber_begin_12=tomMatch199NameNumber_freshVar_10;{ int tomMatch199NameNumber_end_13=tomMatch199NameNumber_freshVar_10;do {{{ int tomMatch199NameNumber_freshVar_11=tomMatch199NameNumber_end_13;if (!(tomMatch199NameNumber_freshVar_11 >= tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_9))) {{ org.w3c.dom.Node tomMatch199NameNumber_freshVar_19=tom_get_element_concTNode_TNodeList(tomMatch199NameNumber_freshVar_9,tomMatch199NameNumber_freshVar_11);if (tom_is_fun_sym_ElementNode(tomMatch199NameNumber_freshVar_19)) {{  String  tomMatch199NameNumber_freshVar_16=tom_get_slot_ElementNode_Name(tomMatch199NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_17=tom_get_slot_ElementNode_AttrList(tomMatch199NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_18=tom_get_slot_ElementNode_ChildList(tomMatch199NameNumber_freshVar_19);{  String  tomMatch199NameNumber_freshVar_20=tomMatch199NameNumber_freshVar_16;if (tom_equal_term_String("umlModel", tomMatch199NameNumber_freshVar_20)) {{  java.util.ArrayList  tomMatch199NameNumber_freshVar_21=tomMatch199NameNumber_freshVar_17;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_21)) {{ int tomMatch199NameNumber_freshVar_22=0;{  java.util.ArrayList  tomMatch199NameNumber_freshVar_24=tomMatch199NameNumber_freshVar_18;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_24)) {{ int tomMatch199NameNumber_freshVar_25=0;{ int tomMatch199NameNumber_begin_27=tomMatch199NameNumber_freshVar_25;{ int tomMatch199NameNumber_end_28=tomMatch199NameNumber_freshVar_25;do {{{ int tomMatch199NameNumber_freshVar_26=tomMatch199NameNumber_end_28;if (!(tomMatch199NameNumber_freshVar_26 >= tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_24))) {{ org.w3c.dom.Node tomMatch199NameNumber_freshVar_34=tom_get_element_concTNode_TNodeList(tomMatch199NameNumber_freshVar_24,tomMatch199NameNumber_freshVar_26);if (tom_is_fun_sym_ElementNode(tomMatch199NameNumber_freshVar_34)) {{  String  tomMatch199NameNumber_freshVar_31=tom_get_slot_ElementNode_Name(tomMatch199NameNumber_freshVar_34);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_32=tom_get_slot_ElementNode_AttrList(tomMatch199NameNumber_freshVar_34);{  java.util.ArrayList  tomMatch199NameNumber_freshVar_33=tom_get_slot_ElementNode_ChildList(tomMatch199NameNumber_freshVar_34);{  String  tomMatch199NameNumber_freshVar_35=tomMatch199NameNumber_freshVar_31;if (tom_equal_term_String("packagedElement", tomMatch199NameNumber_freshVar_35)) {{  java.util.ArrayList  tomMatch199NameNumber_freshVar_36=tomMatch199NameNumber_freshVar_32;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_36)) {{ int tomMatch199NameNumber_freshVar_37=0;{ int tomMatch199NameNumber_begin_39=tomMatch199NameNumber_freshVar_37;{ int tomMatch199NameNumber_end_40=tomMatch199NameNumber_freshVar_37;do {{{ int tomMatch199NameNumber_freshVar_38=tomMatch199NameNumber_end_40;if (!(tomMatch199NameNumber_freshVar_38 >= tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_36))) {{ org.w3c.dom.Node tomMatch199NameNumber_freshVar_49=tom_get_element_concTNode_TNodeList(tomMatch199NameNumber_freshVar_36,tomMatch199NameNumber_freshVar_38);if (tom_is_fun_sym_AttributeNode(tomMatch199NameNumber_freshVar_49)) {{  String  tomMatch199NameNumber_freshVar_46=tom_get_slot_AttributeNode_Name(tomMatch199NameNumber_freshVar_49);{  String  tomMatch199NameNumber_freshVar_47=tom_get_slot_AttributeNode_Specified(tomMatch199NameNumber_freshVar_49);{  String  tomMatch199NameNumber_freshVar_48=tom_get_slot_AttributeNode_Value(tomMatch199NameNumber_freshVar_49);{  String  tomMatch199NameNumber_freshVar_50=tomMatch199NameNumber_freshVar_46;if (tom_equal_term_String("xmitype", tomMatch199NameNumber_freshVar_50)) {{  String  tom_t=tomMatch199NameNumber_freshVar_48;{ int tomMatch199NameNumber_freshVar_41=tomMatch199NameNumber_freshVar_38 + 1;{  java.util.ArrayList  tomMatch199NameNumber_freshVar_43=tomMatch199NameNumber_freshVar_33;if (tom_is_fun_sym_concTNode(tomMatch199NameNumber_freshVar_43)) {{ int tomMatch199NameNumber_freshVar_44=0;{ org.w3c.dom.Node tom_a=tom_get_element_concTNode_TNodeList(tomMatch199NameNumber_freshVar_24,tomMatch199NameNumber_freshVar_26);{ int tomMatch199NameNumber_freshVar_29=tomMatch199NameNumber_freshVar_26 + 1;{ int tomMatch199NameNumber_freshVar_14=tomMatch199NameNumber_freshVar_11 + 1;if ( true ) {


 
				if (tom_t.compareTo("umlPackage") == 0)
					extractNestedInfo(tom_a); 
			}}}}}}}}}}}}}}}}}}tomMatch199NameNumber_end_40=tomMatch199NameNumber_end_40 + 1;}} while(!(tomMatch199NameNumber_end_40 > tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_36)));}}}}}}}}}}}}}}tomMatch199NameNumber_end_28=tomMatch199NameNumber_end_28 + 1;}} while(!(tomMatch199NameNumber_end_28 > tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_24)));}}}}}}}}}}}}}}}}}tomMatch199NameNumber_end_13=tomMatch199NameNumber_end_13 + 1;}} while(!(tomMatch199NameNumber_end_13 > tom_get_size_concTNode_TNodeList(tomMatch199NameNumber_freshVar_9)));}}}}}}}}}}}}}}}}}}}
   	 
	}

	private void extractCollaboration(Node doc)
	{
		{{ Object tomMatch200NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch200NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch200NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch200NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch200NameNumber_freshVar_4=tomMatch200NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch200NameNumber_freshVar_4)) {{  String  tomMatch200NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch200NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch200NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch200NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch200NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch200NameNumber_freshVar_4);{  String  tomMatch200NameNumber_freshVar_5=tomMatch200NameNumber_freshVar_1;if (tom_equal_term_String("packagedElement", tomMatch200NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch200NameNumber_freshVar_6=tomMatch200NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch200NameNumber_freshVar_6)) {{ int tomMatch200NameNumber_freshVar_7=0;{  java.util.ArrayList  tomMatch200NameNumber_freshVar_9=tomMatch200NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch200NameNumber_freshVar_9)) {{ int tomMatch200NameNumber_freshVar_10=0;{ int tomMatch200NameNumber_begin_12=tomMatch200NameNumber_freshVar_10;{ int tomMatch200NameNumber_end_13=tomMatch200NameNumber_freshVar_10;do {{{ int tomMatch200NameNumber_freshVar_11=tomMatch200NameNumber_end_13;if (!(tomMatch200NameNumber_freshVar_11 >= tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_9))) {{ org.w3c.dom.Node tomMatch200NameNumber_freshVar_19=tom_get_element_concTNode_TNodeList(tomMatch200NameNumber_freshVar_9,tomMatch200NameNumber_freshVar_11);if (tom_is_fun_sym_ElementNode(tomMatch200NameNumber_freshVar_19)) {{  String  tomMatch200NameNumber_freshVar_16=tom_get_slot_ElementNode_Name(tomMatch200NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch200NameNumber_freshVar_17=tom_get_slot_ElementNode_AttrList(tomMatch200NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch200NameNumber_freshVar_18=tom_get_slot_ElementNode_ChildList(tomMatch200NameNumber_freshVar_19);{  String  tomMatch200NameNumber_freshVar_20=tomMatch200NameNumber_freshVar_16;if (tom_equal_term_String("ownedBehavior", tomMatch200NameNumber_freshVar_20)) {{  java.util.ArrayList  tomMatch200NameNumber_freshVar_21=tomMatch200NameNumber_freshVar_17;if (tom_is_fun_sym_concTNode(tomMatch200NameNumber_freshVar_21)) {{ int tomMatch200NameNumber_freshVar_22=0;{ int tomMatch200NameNumber_begin_24=tomMatch200NameNumber_freshVar_22;{ int tomMatch200NameNumber_end_25=tomMatch200NameNumber_freshVar_22;do {{{ int tomMatch200NameNumber_freshVar_23=tomMatch200NameNumber_end_25;if (!(tomMatch200NameNumber_freshVar_23 >= tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch200NameNumber_freshVar_38=tom_get_element_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21,tomMatch200NameNumber_freshVar_23);if (tom_is_fun_sym_AttributeNode(tomMatch200NameNumber_freshVar_38)) {{  String  tomMatch200NameNumber_freshVar_35=tom_get_slot_AttributeNode_Name(tomMatch200NameNumber_freshVar_38);{  String  tomMatch200NameNumber_freshVar_36=tom_get_slot_AttributeNode_Specified(tomMatch200NameNumber_freshVar_38);{  String  tomMatch200NameNumber_freshVar_37=tom_get_slot_AttributeNode_Value(tomMatch200NameNumber_freshVar_38);{  String  tomMatch200NameNumber_freshVar_39=tomMatch200NameNumber_freshVar_35;if (tom_equal_term_String("name", tomMatch200NameNumber_freshVar_39)) {{  String  tom_m=tomMatch200NameNumber_freshVar_37;{ int tomMatch200NameNumber_freshVar_26=tomMatch200NameNumber_freshVar_23 + 1;{ int tomMatch200NameNumber_begin_28=tomMatch200NameNumber_freshVar_26;{ int tomMatch200NameNumber_end_29=tomMatch200NameNumber_freshVar_26;do {{{ int tomMatch200NameNumber_freshVar_27=tomMatch200NameNumber_end_29;if (!(tomMatch200NameNumber_freshVar_27 >= tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch200NameNumber_freshVar_43=tom_get_element_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21,tomMatch200NameNumber_freshVar_27);if (tom_is_fun_sym_AttributeNode(tomMatch200NameNumber_freshVar_43)) {{  String  tomMatch200NameNumber_freshVar_40=tom_get_slot_AttributeNode_Name(tomMatch200NameNumber_freshVar_43);{  String  tomMatch200NameNumber_freshVar_41=tom_get_slot_AttributeNode_Specified(tomMatch200NameNumber_freshVar_43);{  String  tomMatch200NameNumber_freshVar_42=tom_get_slot_AttributeNode_Value(tomMatch200NameNumber_freshVar_43);{  String  tomMatch200NameNumber_freshVar_44=tomMatch200NameNumber_freshVar_40;if (tom_equal_term_String("xmitype", tomMatch200NameNumber_freshVar_44)) {{  String  tom_t=tomMatch200NameNumber_freshVar_42;{ int tomMatch200NameNumber_freshVar_30=tomMatch200NameNumber_freshVar_27 + 1;{  java.util.ArrayList  tomMatch200NameNumber_freshVar_32=tomMatch200NameNumber_freshVar_18;if (tom_is_fun_sym_concTNode(tomMatch200NameNumber_freshVar_32)) {{ int tomMatch200NameNumber_freshVar_33=0;{ org.w3c.dom.Node tom_a=tom_get_element_concTNode_TNodeList(tomMatch200NameNumber_freshVar_9,tomMatch200NameNumber_freshVar_11);{ int tomMatch200NameNumber_freshVar_14=tomMatch200NameNumber_freshVar_11 + 1;if ( true ) {

	
					modelname = tom_m;
					if (tom_t.compareTo("umlInteraction") == 0)
						extractOwnedBehavior(tom_a);
				}}}}}}}}}}}}}}}}}tomMatch200NameNumber_end_29=tomMatch200NameNumber_end_29 + 1;}} while(!(tomMatch200NameNumber_end_29 > tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21)));}}}}}}}}}}}}}tomMatch200NameNumber_end_25=tomMatch200NameNumber_end_25 + 1;}} while(!(tomMatch200NameNumber_end_25 > tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_21)));}}}}}}}}}}}}}}tomMatch200NameNumber_end_13=tomMatch200NameNumber_end_13 + 1;}} while(!(tomMatch200NameNumber_end_13 > tom_get_size_concTNode_TNodeList(tomMatch200NameNumber_freshVar_9)));}}}}}}}}}}}}}}}}}}}

		}

		private void extractNestedInfo(Node doc) {		
			{{ Object tomMatch201NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch201NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch201NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch201NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch201NameNumber_freshVar_4=tomMatch201NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch201NameNumber_freshVar_4)) {{  String  tomMatch201NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch201NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch201NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch201NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch201NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch201NameNumber_freshVar_4);{  String  tomMatch201NameNumber_freshVar_5=tomMatch201NameNumber_freshVar_1;if (tom_equal_term_String("packagedElement", tomMatch201NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch201NameNumber_freshVar_6=tomMatch201NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch201NameNumber_freshVar_6)) {{ int tomMatch201NameNumber_freshVar_7=0;{  java.util.ArrayList  tomMatch201NameNumber_freshVar_9=tomMatch201NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch201NameNumber_freshVar_9)) {{ int tomMatch201NameNumber_freshVar_10=0;{ int tomMatch201NameNumber_begin_12=tomMatch201NameNumber_freshVar_10;{ int tomMatch201NameNumber_end_13=tomMatch201NameNumber_freshVar_10;do {{{ int tomMatch201NameNumber_freshVar_11=tomMatch201NameNumber_end_13;if (!(tomMatch201NameNumber_freshVar_11 >= tom_get_size_concTNode_TNodeList(tomMatch201NameNumber_freshVar_9))) {{ org.w3c.dom.Node tomMatch201NameNumber_freshVar_19=tom_get_element_concTNode_TNodeList(tomMatch201NameNumber_freshVar_9,tomMatch201NameNumber_freshVar_11);if (tom_is_fun_sym_ElementNode(tomMatch201NameNumber_freshVar_19)) {{  String  tomMatch201NameNumber_freshVar_16=tom_get_slot_ElementNode_Name(tomMatch201NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch201NameNumber_freshVar_17=tom_get_slot_ElementNode_AttrList(tomMatch201NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch201NameNumber_freshVar_18=tom_get_slot_ElementNode_ChildList(tomMatch201NameNumber_freshVar_19);{  String  tomMatch201NameNumber_freshVar_20=tomMatch201NameNumber_freshVar_16;if (tom_equal_term_String("packagedElement", tomMatch201NameNumber_freshVar_20)) {{  java.util.ArrayList  tomMatch201NameNumber_freshVar_21=tomMatch201NameNumber_freshVar_17;if (tom_is_fun_sym_concTNode(tomMatch201NameNumber_freshVar_21)) {{ int tomMatch201NameNumber_freshVar_22=0;{ int tomMatch201NameNumber_begin_24=tomMatch201NameNumber_freshVar_22;{ int tomMatch201NameNumber_end_25=tomMatch201NameNumber_freshVar_22;do {{{ int tomMatch201NameNumber_freshVar_23=tomMatch201NameNumber_end_25;if (!(tomMatch201NameNumber_freshVar_23 >= tom_get_size_concTNode_TNodeList(tomMatch201NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch201NameNumber_freshVar_34=tom_get_element_concTNode_TNodeList(tomMatch201NameNumber_freshVar_21,tomMatch201NameNumber_freshVar_23);if (tom_is_fun_sym_AttributeNode(tomMatch201NameNumber_freshVar_34)) {{  String  tomMatch201NameNumber_freshVar_31=tom_get_slot_AttributeNode_Name(tomMatch201NameNumber_freshVar_34);{  String  tomMatch201NameNumber_freshVar_32=tom_get_slot_AttributeNode_Specified(tomMatch201NameNumber_freshVar_34);{  String  tomMatch201NameNumber_freshVar_33=tom_get_slot_AttributeNode_Value(tomMatch201NameNumber_freshVar_34);{  String  tomMatch201NameNumber_freshVar_35=tomMatch201NameNumber_freshVar_31;if (tom_equal_term_String("xmitype", tomMatch201NameNumber_freshVar_35)) {{  String  tom_t=tomMatch201NameNumber_freshVar_33;{ int tomMatch201NameNumber_freshVar_26=tomMatch201NameNumber_freshVar_23 + 1;{  java.util.ArrayList  tomMatch201NameNumber_freshVar_28=tomMatch201NameNumber_freshVar_18;if (tom_is_fun_sym_concTNode(tomMatch201NameNumber_freshVar_28)) {{ int tomMatch201NameNumber_freshVar_29=0;{ org.w3c.dom.Node tom_a=tom_get_element_concTNode_TNodeList(tomMatch201NameNumber_freshVar_9,tomMatch201NameNumber_freshVar_11);{ int tomMatch201NameNumber_freshVar_14=tomMatch201NameNumber_freshVar_11 + 1;if ( true ) {


					if (tom_t.compareTo("umlCollaboration") == 0)
						extractCollaboration(tom_a);
				}}}}}}}}}}}}}}}}}tomMatch201NameNumber_end_25=tomMatch201NameNumber_end_25 + 1;}} while(!(tomMatch201NameNumber_end_25 > tom_get_size_concTNode_TNodeList(tomMatch201NameNumber_freshVar_21)));}}}}}}}}}}}}}}tomMatch201NameNumber_end_13=tomMatch201NameNumber_end_13 + 1;}} while(!(tomMatch201NameNumber_end_13 > tom_get_size_concTNode_TNodeList(tomMatch201NameNumber_freshVar_9)));}}}}}}}}}}}}}}}}}}}

		}

		private void extractLifeLine(String xmitype, String xmiid, String name, String represents)
		{
			if (name.compareTo(Constants.In.LostFound) == 0)
				return;
			LifeLine lifeline = new LifeLine(xmitype, xmiid, name, represents);
			sd.LifeLines.put(xmiid, lifeline);
		}

		private void extractMessage(String xmiid, String name, String messagesort, String sendevent, String receiveevent, String connector)
		{
			Message msg = new Message(xmiid, name, messagesort, sendevent, receiveevent, connector);
			String nameparts[] = name.split(":");
			if (nameparts.length < 2)
			{
				System.out.println("Error: Message name " + name + " should begin with number following by :");///////ERRORRRRRRRRRRRRRR
			}
			sd.Messages.put(nameparts[0], msg);
		}

		private void extractFragment(Node doc, String name, Fragment owner)
		{
			{{ Object tomMatch202NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch202NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch202NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch202NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch202NameNumber_freshVar_4=tomMatch202NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch202NameNumber_freshVar_4)) {{  String  tomMatch202NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch202NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch202NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch202NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch202NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch202NameNumber_freshVar_4);{  String  tomMatch202NameNumber_freshVar_5=tomMatch202NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch202NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch202NameNumber_freshVar_6=tomMatch202NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch202NameNumber_freshVar_6)) {{ int tomMatch202NameNumber_freshVar_7=0;{ int tomMatch202NameNumber_begin_9=tomMatch202NameNumber_freshVar_7;{ int tomMatch202NameNumber_end_10=tomMatch202NameNumber_freshVar_7;do {{{ int tomMatch202NameNumber_freshVar_8=tomMatch202NameNumber_end_10;if (!(tomMatch202NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch202NameNumber_freshVar_23=tom_get_element_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6,tomMatch202NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch202NameNumber_freshVar_23)) {{  String  tomMatch202NameNumber_freshVar_20=tom_get_slot_AttributeNode_Name(tomMatch202NameNumber_freshVar_23);{  String  tomMatch202NameNumber_freshVar_21=tom_get_slot_AttributeNode_Specified(tomMatch202NameNumber_freshVar_23);{  String  tomMatch202NameNumber_freshVar_22=tom_get_slot_AttributeNode_Value(tomMatch202NameNumber_freshVar_23);{  String  tomMatch202NameNumber_freshVar_24=tomMatch202NameNumber_freshVar_20;if (tom_equal_term_String("xmiid", tomMatch202NameNumber_freshVar_24)) {{  String  tom_i=tomMatch202NameNumber_freshVar_22;{ int tomMatch202NameNumber_freshVar_11=tomMatch202NameNumber_freshVar_8 + 1;{ int tomMatch202NameNumber_begin_13=tomMatch202NameNumber_freshVar_11;{ int tomMatch202NameNumber_end_14=tomMatch202NameNumber_freshVar_11;do {{{ int tomMatch202NameNumber_freshVar_12=tomMatch202NameNumber_end_14;if (!(tomMatch202NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch202NameNumber_freshVar_28=tom_get_element_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6,tomMatch202NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch202NameNumber_freshVar_28)) {{  String  tomMatch202NameNumber_freshVar_25=tom_get_slot_AttributeNode_Name(tomMatch202NameNumber_freshVar_28);{  String  tomMatch202NameNumber_freshVar_26=tom_get_slot_AttributeNode_Specified(tomMatch202NameNumber_freshVar_28);{  String  tomMatch202NameNumber_freshVar_27=tom_get_slot_AttributeNode_Value(tomMatch202NameNumber_freshVar_28);{  String  tomMatch202NameNumber_freshVar_29=tomMatch202NameNumber_freshVar_25;if (tom_equal_term_String("xmitype", tomMatch202NameNumber_freshVar_29)) {{  String  tom_t=tomMatch202NameNumber_freshVar_27;{ int tomMatch202NameNumber_freshVar_15=tomMatch202NameNumber_freshVar_12 + 1;{  java.util.ArrayList  tomMatch202NameNumber_freshVar_17=tomMatch202NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch202NameNumber_freshVar_17)) {{ int tomMatch202NameNumber_freshVar_18=0;{ org.w3c.dom.Node tom_a=tomMatch202NameNumberfreshSubject_1;if ( true ) {



					if (tom_t.compareTo("umlExecutionOccurrenceSpecification") == 0)
						extractExecutionOccurrenceSpecification(tom_a, owner);	  	
					else if (tom_t.compareTo("umlBehaviorExecutionSpecification") == 0)
						extractBehaviorExecutionSpecification(tom_a, owner);	  	
					else if (tom_t.compareTo("umlMessageOccurrenceSpecification") == 0)
						extractMessageOccurrenceSpecification(tom_a, owner);	  	
					else if (tom_t.compareTo("umlCombinedFragment") == 0)
						extractCombinedFragment(tom_a, owner);					
				}}}}}}}}}}}}}}}}tomMatch202NameNumber_end_14=tomMatch202NameNumber_end_14 + 1;}} while(!(tomMatch202NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch202NameNumber_end_10=tomMatch202NameNumber_end_10 + 1;}} while(!(tomMatch202NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch202NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}

		}

		private void extractExecutionOccurrenceSpecification(Node doc, Fragment owner) {		
			{{ Object tomMatch203NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch203NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch203NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch203NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_4=tomMatch203NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch203NameNumber_freshVar_4)) {{  String  tomMatch203NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch203NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch203NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch203NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch203NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch203NameNumber_freshVar_4);{  String  tomMatch203NameNumber_freshVar_5=tomMatch203NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch203NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch203NameNumber_freshVar_6=tomMatch203NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch203NameNumber_freshVar_6)) {{ int tomMatch203NameNumber_freshVar_7=0;{ int tomMatch203NameNumber_begin_9=tomMatch203NameNumber_freshVar_7;{ int tomMatch203NameNumber_end_10=tomMatch203NameNumber_freshVar_7;do {{{ int tomMatch203NameNumber_freshVar_8=tomMatch203NameNumber_end_10;if (!(tomMatch203NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_34=tom_get_element_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6,tomMatch203NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch203NameNumber_freshVar_34)) {{  String  tomMatch203NameNumber_freshVar_31=tom_get_slot_AttributeNode_Name(tomMatch203NameNumber_freshVar_34);{  String  tomMatch203NameNumber_freshVar_32=tom_get_slot_AttributeNode_Specified(tomMatch203NameNumber_freshVar_34);{  String  tomMatch203NameNumber_freshVar_33=tom_get_slot_AttributeNode_Value(tomMatch203NameNumber_freshVar_34);{  String  tomMatch203NameNumber_freshVar_35=tomMatch203NameNumber_freshVar_31;if (tom_equal_term_String("covered", tomMatch203NameNumber_freshVar_35)) {{  String  tom_c=tomMatch203NameNumber_freshVar_33;{ int tomMatch203NameNumber_freshVar_11=tomMatch203NameNumber_freshVar_8 + 1;{ int tomMatch203NameNumber_begin_13=tomMatch203NameNumber_freshVar_11;{ int tomMatch203NameNumber_end_14=tomMatch203NameNumber_freshVar_11;do {{{ int tomMatch203NameNumber_freshVar_12=tomMatch203NameNumber_end_14;if (!(tomMatch203NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_39=tom_get_element_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6,tomMatch203NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch203NameNumber_freshVar_39)) {{  String  tomMatch203NameNumber_freshVar_36=tom_get_slot_AttributeNode_Name(tomMatch203NameNumber_freshVar_39);{  String  tomMatch203NameNumber_freshVar_37=tom_get_slot_AttributeNode_Specified(tomMatch203NameNumber_freshVar_39);{  String  tomMatch203NameNumber_freshVar_38=tom_get_slot_AttributeNode_Value(tomMatch203NameNumber_freshVar_39);{  String  tomMatch203NameNumber_freshVar_40=tomMatch203NameNumber_freshVar_36;if (tom_equal_term_String("event", tomMatch203NameNumber_freshVar_40)) {{  String  tom_e=tomMatch203NameNumber_freshVar_38;{ int tomMatch203NameNumber_freshVar_15=tomMatch203NameNumber_freshVar_12 + 1;{ int tomMatch203NameNumber_begin_17=tomMatch203NameNumber_freshVar_15;{ int tomMatch203NameNumber_end_18=tomMatch203NameNumber_freshVar_15;do {{{ int tomMatch203NameNumber_freshVar_16=tomMatch203NameNumber_end_18;if (!(tomMatch203NameNumber_freshVar_16 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_44=tom_get_element_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6,tomMatch203NameNumber_freshVar_16);if (tom_is_fun_sym_AttributeNode(tomMatch203NameNumber_freshVar_44)) {{  String  tomMatch203NameNumber_freshVar_41=tom_get_slot_AttributeNode_Name(tomMatch203NameNumber_freshVar_44);{  String  tomMatch203NameNumber_freshVar_42=tom_get_slot_AttributeNode_Specified(tomMatch203NameNumber_freshVar_44);{  String  tomMatch203NameNumber_freshVar_43=tom_get_slot_AttributeNode_Value(tomMatch203NameNumber_freshVar_44);{  String  tomMatch203NameNumber_freshVar_45=tomMatch203NameNumber_freshVar_41;if (tom_equal_term_String("execution", tomMatch203NameNumber_freshVar_45)) {{  String  tom_x=tomMatch203NameNumber_freshVar_43;{ int tomMatch203NameNumber_freshVar_19=tomMatch203NameNumber_freshVar_16 + 1;{ int tomMatch203NameNumber_begin_21=tomMatch203NameNumber_freshVar_19;{ int tomMatch203NameNumber_end_22=tomMatch203NameNumber_freshVar_19;do {{{ int tomMatch203NameNumber_freshVar_20=tomMatch203NameNumber_end_22;if (!(tomMatch203NameNumber_freshVar_20 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_49=tom_get_element_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6,tomMatch203NameNumber_freshVar_20);if (tom_is_fun_sym_AttributeNode(tomMatch203NameNumber_freshVar_49)) {{  String  tomMatch203NameNumber_freshVar_46=tom_get_slot_AttributeNode_Name(tomMatch203NameNumber_freshVar_49);{  String  tomMatch203NameNumber_freshVar_47=tom_get_slot_AttributeNode_Specified(tomMatch203NameNumber_freshVar_49);{  String  tomMatch203NameNumber_freshVar_48=tom_get_slot_AttributeNode_Value(tomMatch203NameNumber_freshVar_49);{  String  tomMatch203NameNumber_freshVar_50=tomMatch203NameNumber_freshVar_46;if (tom_equal_term_String("xmiid", tomMatch203NameNumber_freshVar_50)) {{  String  tom_i=tomMatch203NameNumber_freshVar_48;{ int tomMatch203NameNumber_freshVar_23=tomMatch203NameNumber_freshVar_20 + 1;{ int tomMatch203NameNumber_begin_25=tomMatch203NameNumber_freshVar_23;{ int tomMatch203NameNumber_end_26=tomMatch203NameNumber_freshVar_23;do {{{ int tomMatch203NameNumber_freshVar_24=tomMatch203NameNumber_end_26;if (!(tomMatch203NameNumber_freshVar_24 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch203NameNumber_freshVar_54=tom_get_element_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6,tomMatch203NameNumber_freshVar_24);if (tom_is_fun_sym_AttributeNode(tomMatch203NameNumber_freshVar_54)) {{  String  tomMatch203NameNumber_freshVar_51=tom_get_slot_AttributeNode_Name(tomMatch203NameNumber_freshVar_54);{  String  tomMatch203NameNumber_freshVar_52=tom_get_slot_AttributeNode_Specified(tomMatch203NameNumber_freshVar_54);{  String  tomMatch203NameNumber_freshVar_53=tom_get_slot_AttributeNode_Value(tomMatch203NameNumber_freshVar_54);{  String  tomMatch203NameNumber_freshVar_55=tomMatch203NameNumber_freshVar_51;if (tom_equal_term_String("xmitype", tomMatch203NameNumber_freshVar_55)) {{  String  tom_t=tomMatch203NameNumber_freshVar_53;{ int tomMatch203NameNumber_freshVar_27=tomMatch203NameNumber_freshVar_24 + 1;{  java.util.ArrayList  tomMatch203NameNumber_freshVar_29=tomMatch203NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch203NameNumber_freshVar_29)) {{ int tomMatch203NameNumber_freshVar_30=0;if (tomMatch203NameNumber_freshVar_30 >= tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_29)) {if ( true ) {

	owner.addFragment(new ExecutionOccurrenceSpecification(tom_t, tom_i, tom_c, tom_e, tom_x, owner));  }}}}}}}}}}}}}}}}tomMatch203NameNumber_end_26=tomMatch203NameNumber_end_26 + 1;}} while(!(tomMatch203NameNumber_end_26 > tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch203NameNumber_end_22=tomMatch203NameNumber_end_22 + 1;}} while(!(tomMatch203NameNumber_end_22 > tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch203NameNumber_end_18=tomMatch203NameNumber_end_18 + 1;}} while(!(tomMatch203NameNumber_end_18 > tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch203NameNumber_end_14=tomMatch203NameNumber_end_14 + 1;}} while(!(tomMatch203NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch203NameNumber_end_10=tomMatch203NameNumber_end_10 + 1;}} while(!(tomMatch203NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch203NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}

		}

		private void extractBehaviorExecutionSpecification(Node doc, Fragment owner) {		
			{{ Object tomMatch204NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch204NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch204NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch204NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_4=tomMatch204NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch204NameNumber_freshVar_4)) {{  String  tomMatch204NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch204NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch204NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch204NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch204NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch204NameNumber_freshVar_4);{  String  tomMatch204NameNumber_freshVar_5=tomMatch204NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch204NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch204NameNumber_freshVar_6=tomMatch204NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch204NameNumber_freshVar_6)) {{ int tomMatch204NameNumber_freshVar_7=0;{ int tomMatch204NameNumber_begin_9=tomMatch204NameNumber_freshVar_7;{ int tomMatch204NameNumber_end_10=tomMatch204NameNumber_freshVar_7;do {{{ int tomMatch204NameNumber_freshVar_8=tomMatch204NameNumber_end_10;if (!(tomMatch204NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_34=tom_get_element_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6,tomMatch204NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch204NameNumber_freshVar_34)) {{  String  tomMatch204NameNumber_freshVar_31=tom_get_slot_AttributeNode_Name(tomMatch204NameNumber_freshVar_34);{  String  tomMatch204NameNumber_freshVar_32=tom_get_slot_AttributeNode_Specified(tomMatch204NameNumber_freshVar_34);{  String  tomMatch204NameNumber_freshVar_33=tom_get_slot_AttributeNode_Value(tomMatch204NameNumber_freshVar_34);{  String  tomMatch204NameNumber_freshVar_35=tomMatch204NameNumber_freshVar_31;if (tom_equal_term_String("covered", tomMatch204NameNumber_freshVar_35)) {{  String  tom_c=tomMatch204NameNumber_freshVar_33;{ int tomMatch204NameNumber_freshVar_11=tomMatch204NameNumber_freshVar_8 + 1;{ int tomMatch204NameNumber_begin_13=tomMatch204NameNumber_freshVar_11;{ int tomMatch204NameNumber_end_14=tomMatch204NameNumber_freshVar_11;do {{{ int tomMatch204NameNumber_freshVar_12=tomMatch204NameNumber_end_14;if (!(tomMatch204NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_39=tom_get_element_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6,tomMatch204NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch204NameNumber_freshVar_39)) {{  String  tomMatch204NameNumber_freshVar_36=tom_get_slot_AttributeNode_Name(tomMatch204NameNumber_freshVar_39);{  String  tomMatch204NameNumber_freshVar_37=tom_get_slot_AttributeNode_Specified(tomMatch204NameNumber_freshVar_39);{  String  tomMatch204NameNumber_freshVar_38=tom_get_slot_AttributeNode_Value(tomMatch204NameNumber_freshVar_39);{  String  tomMatch204NameNumber_freshVar_40=tomMatch204NameNumber_freshVar_36;if (tom_equal_term_String("finish", tomMatch204NameNumber_freshVar_40)) {{  String  tom_f=tomMatch204NameNumber_freshVar_38;{ int tomMatch204NameNumber_freshVar_15=tomMatch204NameNumber_freshVar_12 + 1;{ int tomMatch204NameNumber_begin_17=tomMatch204NameNumber_freshVar_15;{ int tomMatch204NameNumber_end_18=tomMatch204NameNumber_freshVar_15;do {{{ int tomMatch204NameNumber_freshVar_16=tomMatch204NameNumber_end_18;if (!(tomMatch204NameNumber_freshVar_16 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_44=tom_get_element_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6,tomMatch204NameNumber_freshVar_16);if (tom_is_fun_sym_AttributeNode(tomMatch204NameNumber_freshVar_44)) {{  String  tomMatch204NameNumber_freshVar_41=tom_get_slot_AttributeNode_Name(tomMatch204NameNumber_freshVar_44);{  String  tomMatch204NameNumber_freshVar_42=tom_get_slot_AttributeNode_Specified(tomMatch204NameNumber_freshVar_44);{  String  tomMatch204NameNumber_freshVar_43=tom_get_slot_AttributeNode_Value(tomMatch204NameNumber_freshVar_44);{  String  tomMatch204NameNumber_freshVar_45=tomMatch204NameNumber_freshVar_41;if (tom_equal_term_String("start", tomMatch204NameNumber_freshVar_45)) {{  String  tom_s=tomMatch204NameNumber_freshVar_43;{ int tomMatch204NameNumber_freshVar_19=tomMatch204NameNumber_freshVar_16 + 1;{ int tomMatch204NameNumber_begin_21=tomMatch204NameNumber_freshVar_19;{ int tomMatch204NameNumber_end_22=tomMatch204NameNumber_freshVar_19;do {{{ int tomMatch204NameNumber_freshVar_20=tomMatch204NameNumber_end_22;if (!(tomMatch204NameNumber_freshVar_20 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_49=tom_get_element_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6,tomMatch204NameNumber_freshVar_20);if (tom_is_fun_sym_AttributeNode(tomMatch204NameNumber_freshVar_49)) {{  String  tomMatch204NameNumber_freshVar_46=tom_get_slot_AttributeNode_Name(tomMatch204NameNumber_freshVar_49);{  String  tomMatch204NameNumber_freshVar_47=tom_get_slot_AttributeNode_Specified(tomMatch204NameNumber_freshVar_49);{  String  tomMatch204NameNumber_freshVar_48=tom_get_slot_AttributeNode_Value(tomMatch204NameNumber_freshVar_49);{  String  tomMatch204NameNumber_freshVar_50=tomMatch204NameNumber_freshVar_46;if (tom_equal_term_String("xmiid", tomMatch204NameNumber_freshVar_50)) {{  String  tom_i=tomMatch204NameNumber_freshVar_48;{ int tomMatch204NameNumber_freshVar_23=tomMatch204NameNumber_freshVar_20 + 1;{ int tomMatch204NameNumber_begin_25=tomMatch204NameNumber_freshVar_23;{ int tomMatch204NameNumber_end_26=tomMatch204NameNumber_freshVar_23;do {{{ int tomMatch204NameNumber_freshVar_24=tomMatch204NameNumber_end_26;if (!(tomMatch204NameNumber_freshVar_24 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch204NameNumber_freshVar_54=tom_get_element_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6,tomMatch204NameNumber_freshVar_24);if (tom_is_fun_sym_AttributeNode(tomMatch204NameNumber_freshVar_54)) {{  String  tomMatch204NameNumber_freshVar_51=tom_get_slot_AttributeNode_Name(tomMatch204NameNumber_freshVar_54);{  String  tomMatch204NameNumber_freshVar_52=tom_get_slot_AttributeNode_Specified(tomMatch204NameNumber_freshVar_54);{  String  tomMatch204NameNumber_freshVar_53=tom_get_slot_AttributeNode_Value(tomMatch204NameNumber_freshVar_54);{  String  tomMatch204NameNumber_freshVar_55=tomMatch204NameNumber_freshVar_51;if (tom_equal_term_String("xmitype", tomMatch204NameNumber_freshVar_55)) {{  String  tom_t=tomMatch204NameNumber_freshVar_53;{ int tomMatch204NameNumber_freshVar_27=tomMatch204NameNumber_freshVar_24 + 1;{  java.util.ArrayList  tomMatch204NameNumber_freshVar_29=tomMatch204NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch204NameNumber_freshVar_29)) {{ int tomMatch204NameNumber_freshVar_30=0;if (tomMatch204NameNumber_freshVar_30 >= tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_29)) {if ( true ) {

 	owner.addFragment(new BehaviorOccurrenceSpecification(tom_t, tom_i, tom_c, tom_s, tom_f, owner));	}}}}}}}}}}}}}}}}tomMatch204NameNumber_end_26=tomMatch204NameNumber_end_26 + 1;}} while(!(tomMatch204NameNumber_end_26 > tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch204NameNumber_end_22=tomMatch204NameNumber_end_22 + 1;}} while(!(tomMatch204NameNumber_end_22 > tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch204NameNumber_end_18=tomMatch204NameNumber_end_18 + 1;}} while(!(tomMatch204NameNumber_end_18 > tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch204NameNumber_end_14=tomMatch204NameNumber_end_14 + 1;}} while(!(tomMatch204NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch204NameNumber_end_10=tomMatch204NameNumber_end_10 + 1;}} while(!(tomMatch204NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch204NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}

		}

		private void extractMessageOccurrenceSpecification(Node doc, Fragment owner) {		
			{{ Object tomMatch205NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch205NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch205NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch205NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_4=tomMatch205NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch205NameNumber_freshVar_4)) {{  String  tomMatch205NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch205NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch205NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch205NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch205NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch205NameNumber_freshVar_4);{  String  tomMatch205NameNumber_freshVar_5=tomMatch205NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch205NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch205NameNumber_freshVar_6=tomMatch205NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch205NameNumber_freshVar_6)) {{ int tomMatch205NameNumber_freshVar_7=0;{ int tomMatch205NameNumber_begin_9=tomMatch205NameNumber_freshVar_7;{ int tomMatch205NameNumber_end_10=tomMatch205NameNumber_freshVar_7;do {{{ int tomMatch205NameNumber_freshVar_8=tomMatch205NameNumber_end_10;if (!(tomMatch205NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_34=tom_get_element_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6,tomMatch205NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch205NameNumber_freshVar_34)) {{  String  tomMatch205NameNumber_freshVar_31=tom_get_slot_AttributeNode_Name(tomMatch205NameNumber_freshVar_34);{  String  tomMatch205NameNumber_freshVar_32=tom_get_slot_AttributeNode_Specified(tomMatch205NameNumber_freshVar_34);{  String  tomMatch205NameNumber_freshVar_33=tom_get_slot_AttributeNode_Value(tomMatch205NameNumber_freshVar_34);{  String  tomMatch205NameNumber_freshVar_35=tomMatch205NameNumber_freshVar_31;if (tom_equal_term_String("covered", tomMatch205NameNumber_freshVar_35)) {{  String  tom_c=tomMatch205NameNumber_freshVar_33;{ int tomMatch205NameNumber_freshVar_11=tomMatch205NameNumber_freshVar_8 + 1;{ int tomMatch205NameNumber_begin_13=tomMatch205NameNumber_freshVar_11;{ int tomMatch205NameNumber_end_14=tomMatch205NameNumber_freshVar_11;do {{{ int tomMatch205NameNumber_freshVar_12=tomMatch205NameNumber_end_14;if (!(tomMatch205NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_39=tom_get_element_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6,tomMatch205NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch205NameNumber_freshVar_39)) {{  String  tomMatch205NameNumber_freshVar_36=tom_get_slot_AttributeNode_Name(tomMatch205NameNumber_freshVar_39);{  String  tomMatch205NameNumber_freshVar_37=tom_get_slot_AttributeNode_Specified(tomMatch205NameNumber_freshVar_39);{  String  tomMatch205NameNumber_freshVar_38=tom_get_slot_AttributeNode_Value(tomMatch205NameNumber_freshVar_39);{  String  tomMatch205NameNumber_freshVar_40=tomMatch205NameNumber_freshVar_36;if (tom_equal_term_String("event", tomMatch205NameNumber_freshVar_40)) {{  String  tom_e=tomMatch205NameNumber_freshVar_38;{ int tomMatch205NameNumber_freshVar_15=tomMatch205NameNumber_freshVar_12 + 1;{ int tomMatch205NameNumber_begin_17=tomMatch205NameNumber_freshVar_15;{ int tomMatch205NameNumber_end_18=tomMatch205NameNumber_freshVar_15;do {{{ int tomMatch205NameNumber_freshVar_16=tomMatch205NameNumber_end_18;if (!(tomMatch205NameNumber_freshVar_16 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_44=tom_get_element_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6,tomMatch205NameNumber_freshVar_16);if (tom_is_fun_sym_AttributeNode(tomMatch205NameNumber_freshVar_44)) {{  String  tomMatch205NameNumber_freshVar_41=tom_get_slot_AttributeNode_Name(tomMatch205NameNumber_freshVar_44);{  String  tomMatch205NameNumber_freshVar_42=tom_get_slot_AttributeNode_Specified(tomMatch205NameNumber_freshVar_44);{  String  tomMatch205NameNumber_freshVar_43=tom_get_slot_AttributeNode_Value(tomMatch205NameNumber_freshVar_44);{  String  tomMatch205NameNumber_freshVar_45=tomMatch205NameNumber_freshVar_41;if (tom_equal_term_String("message", tomMatch205NameNumber_freshVar_45)) {{  String  tom_m=tomMatch205NameNumber_freshVar_43;{ int tomMatch205NameNumber_freshVar_19=tomMatch205NameNumber_freshVar_16 + 1;{ int tomMatch205NameNumber_begin_21=tomMatch205NameNumber_freshVar_19;{ int tomMatch205NameNumber_end_22=tomMatch205NameNumber_freshVar_19;do {{{ int tomMatch205NameNumber_freshVar_20=tomMatch205NameNumber_end_22;if (!(tomMatch205NameNumber_freshVar_20 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_49=tom_get_element_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6,tomMatch205NameNumber_freshVar_20);if (tom_is_fun_sym_AttributeNode(tomMatch205NameNumber_freshVar_49)) {{  String  tomMatch205NameNumber_freshVar_46=tom_get_slot_AttributeNode_Name(tomMatch205NameNumber_freshVar_49);{  String  tomMatch205NameNumber_freshVar_47=tom_get_slot_AttributeNode_Specified(tomMatch205NameNumber_freshVar_49);{  String  tomMatch205NameNumber_freshVar_48=tom_get_slot_AttributeNode_Value(tomMatch205NameNumber_freshVar_49);{  String  tomMatch205NameNumber_freshVar_50=tomMatch205NameNumber_freshVar_46;if (tom_equal_term_String("xmiid", tomMatch205NameNumber_freshVar_50)) {{  String  tom_i=tomMatch205NameNumber_freshVar_48;{ int tomMatch205NameNumber_freshVar_23=tomMatch205NameNumber_freshVar_20 + 1;{ int tomMatch205NameNumber_begin_25=tomMatch205NameNumber_freshVar_23;{ int tomMatch205NameNumber_end_26=tomMatch205NameNumber_freshVar_23;do {{{ int tomMatch205NameNumber_freshVar_24=tomMatch205NameNumber_end_26;if (!(tomMatch205NameNumber_freshVar_24 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch205NameNumber_freshVar_54=tom_get_element_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6,tomMatch205NameNumber_freshVar_24);if (tom_is_fun_sym_AttributeNode(tomMatch205NameNumber_freshVar_54)) {{  String  tomMatch205NameNumber_freshVar_51=tom_get_slot_AttributeNode_Name(tomMatch205NameNumber_freshVar_54);{  String  tomMatch205NameNumber_freshVar_52=tom_get_slot_AttributeNode_Specified(tomMatch205NameNumber_freshVar_54);{  String  tomMatch205NameNumber_freshVar_53=tom_get_slot_AttributeNode_Value(tomMatch205NameNumber_freshVar_54);{  String  tomMatch205NameNumber_freshVar_55=tomMatch205NameNumber_freshVar_51;if (tom_equal_term_String("xmitype", tomMatch205NameNumber_freshVar_55)) {{  String  tom_t=tomMatch205NameNumber_freshVar_53;{ int tomMatch205NameNumber_freshVar_27=tomMatch205NameNumber_freshVar_24 + 1;{  java.util.ArrayList  tomMatch205NameNumber_freshVar_29=tomMatch205NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch205NameNumber_freshVar_29)) {{ int tomMatch205NameNumber_freshVar_30=0;if (tomMatch205NameNumber_freshVar_30 >= tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_29)) {if ( true ) {

 	owner.addFragment(new MessageOccurrenceSpecification(tom_t, tom_i, tom_c, tom_e, tom_m, owner)); 	}}}}}}}}}}}}}}}}tomMatch205NameNumber_end_26=tomMatch205NameNumber_end_26 + 1;}} while(!(tomMatch205NameNumber_end_26 > tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch205NameNumber_end_22=tomMatch205NameNumber_end_22 + 1;}} while(!(tomMatch205NameNumber_end_22 > tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch205NameNumber_end_18=tomMatch205NameNumber_end_18 + 1;}} while(!(tomMatch205NameNumber_end_18 > tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch205NameNumber_end_14=tomMatch205NameNumber_end_14 + 1;}} while(!(tomMatch205NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch205NameNumber_end_10=tomMatch205NameNumber_end_10 + 1;}} while(!(tomMatch205NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch205NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}

		}

		private void extractCombinedFragment(Node doc, Fragment owner) {	
			{{ Object tomMatch206NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch206NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch206NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch206NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch206NameNumber_freshVar_4=tomMatch206NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch206NameNumber_freshVar_4)) {{  String  tomMatch206NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch206NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch206NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch206NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch206NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch206NameNumber_freshVar_4);{  String  tomMatch206NameNumber_freshVar_5=tomMatch206NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch206NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch206NameNumber_freshVar_6=tomMatch206NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch206NameNumber_freshVar_6)) {{ int tomMatch206NameNumber_freshVar_7=0;{ int tomMatch206NameNumber_begin_9=tomMatch206NameNumber_freshVar_7;{ int tomMatch206NameNumber_end_10=tomMatch206NameNumber_freshVar_7;do {{{ int tomMatch206NameNumber_freshVar_8=tomMatch206NameNumber_end_10;if (!(tomMatch206NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch206NameNumber_freshVar_27=tom_get_element_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6,tomMatch206NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch206NameNumber_freshVar_27)) {{  String  tomMatch206NameNumber_freshVar_24=tom_get_slot_AttributeNode_Name(tomMatch206NameNumber_freshVar_27);{  String  tomMatch206NameNumber_freshVar_25=tom_get_slot_AttributeNode_Specified(tomMatch206NameNumber_freshVar_27);{  String  tomMatch206NameNumber_freshVar_26=tom_get_slot_AttributeNode_Value(tomMatch206NameNumber_freshVar_27);{  String  tomMatch206NameNumber_freshVar_28=tomMatch206NameNumber_freshVar_24;if (tom_equal_term_String("interactionOperator", tomMatch206NameNumber_freshVar_28)) {{  String  tom_o=tomMatch206NameNumber_freshVar_26;{ int tomMatch206NameNumber_freshVar_11=tomMatch206NameNumber_freshVar_8 + 1;{ int tomMatch206NameNumber_begin_13=tomMatch206NameNumber_freshVar_11;{ int tomMatch206NameNumber_end_14=tomMatch206NameNumber_freshVar_11;do {{{ int tomMatch206NameNumber_freshVar_12=tomMatch206NameNumber_end_14;if (!(tomMatch206NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch206NameNumber_freshVar_32=tom_get_element_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6,tomMatch206NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch206NameNumber_freshVar_32)) {{  String  tomMatch206NameNumber_freshVar_29=tom_get_slot_AttributeNode_Name(tomMatch206NameNumber_freshVar_32);{  String  tomMatch206NameNumber_freshVar_30=tom_get_slot_AttributeNode_Specified(tomMatch206NameNumber_freshVar_32);{  String  tomMatch206NameNumber_freshVar_31=tom_get_slot_AttributeNode_Value(tomMatch206NameNumber_freshVar_32);{  String  tomMatch206NameNumber_freshVar_33=tomMatch206NameNumber_freshVar_29;if (tom_equal_term_String("xmiid", tomMatch206NameNumber_freshVar_33)) {{  String  tom_i=tomMatch206NameNumber_freshVar_31;{ int tomMatch206NameNumber_freshVar_15=tomMatch206NameNumber_freshVar_12 + 1;{ int tomMatch206NameNumber_begin_17=tomMatch206NameNumber_freshVar_15;{ int tomMatch206NameNumber_end_18=tomMatch206NameNumber_freshVar_15;do {{{ int tomMatch206NameNumber_freshVar_16=tomMatch206NameNumber_end_18;if (!(tomMatch206NameNumber_freshVar_16 >= tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch206NameNumber_freshVar_37=tom_get_element_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6,tomMatch206NameNumber_freshVar_16);if (tom_is_fun_sym_AttributeNode(tomMatch206NameNumber_freshVar_37)) {{  String  tomMatch206NameNumber_freshVar_34=tom_get_slot_AttributeNode_Name(tomMatch206NameNumber_freshVar_37);{  String  tomMatch206NameNumber_freshVar_35=tom_get_slot_AttributeNode_Specified(tomMatch206NameNumber_freshVar_37);{  String  tomMatch206NameNumber_freshVar_36=tom_get_slot_AttributeNode_Value(tomMatch206NameNumber_freshVar_37);{  String  tomMatch206NameNumber_freshVar_38=tomMatch206NameNumber_freshVar_34;if (tom_equal_term_String("xmitype", tomMatch206NameNumber_freshVar_38)) {{  String  tom_t=tomMatch206NameNumber_freshVar_36;{ int tomMatch206NameNumber_freshVar_19=tomMatch206NameNumber_freshVar_16 + 1;{  java.util.ArrayList  tomMatch206NameNumber_freshVar_21=tomMatch206NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch206NameNumber_freshVar_21)) {{ int tomMatch206NameNumber_freshVar_22=0;if ( true ) {


					String name = (owner.name.isEmpty())?modelname:owner.name;
					CombinedFragment comfrag = new CombinedFragment(name, tom_t, tom_i, "`c", tom_o, owner);
					extractOperand(doc, comfrag);
					owner.addFragment(comfrag);					
				}}}}}}}}}}}}}}}tomMatch206NameNumber_end_18=tomMatch206NameNumber_end_18 + 1;}} while(!(tomMatch206NameNumber_end_18 > tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch206NameNumber_end_14=tomMatch206NameNumber_end_14 + 1;}} while(!(tomMatch206NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch206NameNumber_end_10=tomMatch206NameNumber_end_10 + 1;}} while(!(tomMatch206NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch206NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}

		}

		private Guard extractGuard(Node doc) {
			{{ Object tomMatch207NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch207NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch207NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch207NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_4=tomMatch207NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch207NameNumber_freshVar_4)) {{  String  tomMatch207NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch207NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch207NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch207NameNumber_freshVar_4);{  String  tomMatch207NameNumber_freshVar_5=tomMatch207NameNumber_freshVar_1;if (tom_equal_term_String("guard", tomMatch207NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch207NameNumber_freshVar_6=tomMatch207NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_6)) {{ int tomMatch207NameNumber_freshVar_7=0;{ int tomMatch207NameNumber_begin_9=tomMatch207NameNumber_freshVar_7;{ int tomMatch207NameNumber_end_10=tomMatch207NameNumber_freshVar_7;do {{{ int tomMatch207NameNumber_freshVar_8=tomMatch207NameNumber_end_10;if (!(tomMatch207NameNumber_freshVar_8 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_27=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6,tomMatch207NameNumber_freshVar_8);if (tom_is_fun_sym_AttributeNode(tomMatch207NameNumber_freshVar_27)) {{  String  tomMatch207NameNumber_freshVar_24=tom_get_slot_AttributeNode_Name(tomMatch207NameNumber_freshVar_27);{  String  tomMatch207NameNumber_freshVar_25=tom_get_slot_AttributeNode_Specified(tomMatch207NameNumber_freshVar_27);{  String  tomMatch207NameNumber_freshVar_26=tom_get_slot_AttributeNode_Value(tomMatch207NameNumber_freshVar_27);{  String  tomMatch207NameNumber_freshVar_28=tomMatch207NameNumber_freshVar_24;if (tom_equal_term_String("xmiid", tomMatch207NameNumber_freshVar_28)) {{  String  tom_gi=tomMatch207NameNumber_freshVar_26;{ int tomMatch207NameNumber_freshVar_11=tomMatch207NameNumber_freshVar_8 + 1;{ int tomMatch207NameNumber_begin_13=tomMatch207NameNumber_freshVar_11;{ int tomMatch207NameNumber_end_14=tomMatch207NameNumber_freshVar_11;do {{{ int tomMatch207NameNumber_freshVar_12=tomMatch207NameNumber_end_14;if (!(tomMatch207NameNumber_freshVar_12 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_32=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6,tomMatch207NameNumber_freshVar_12);if (tom_is_fun_sym_AttributeNode(tomMatch207NameNumber_freshVar_32)) {{  String  tomMatch207NameNumber_freshVar_29=tom_get_slot_AttributeNode_Name(tomMatch207NameNumber_freshVar_32);{  String  tomMatch207NameNumber_freshVar_30=tom_get_slot_AttributeNode_Specified(tomMatch207NameNumber_freshVar_32);{  String  tomMatch207NameNumber_freshVar_31=tom_get_slot_AttributeNode_Value(tomMatch207NameNumber_freshVar_32);{  String  tomMatch207NameNumber_freshVar_33=tomMatch207NameNumber_freshVar_29;if (tom_equal_term_String("xmitype", tomMatch207NameNumber_freshVar_33)) {{  String  tom_gt=tomMatch207NameNumber_freshVar_31;{ int tomMatch207NameNumber_freshVar_15=tomMatch207NameNumber_freshVar_12 + 1;{  java.util.ArrayList  tomMatch207NameNumber_freshVar_17=tomMatch207NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_17)) {{ int tomMatch207NameNumber_freshVar_18=0;{ int tomMatch207NameNumber_begin_20=tomMatch207NameNumber_freshVar_18;{ int tomMatch207NameNumber_end_21=tomMatch207NameNumber_freshVar_18;do {{{ int tomMatch207NameNumber_freshVar_19=tomMatch207NameNumber_end_21;if (!(tomMatch207NameNumber_freshVar_19 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_17))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_37=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_17,tomMatch207NameNumber_freshVar_19);if (tom_is_fun_sym_ElementNode(tomMatch207NameNumber_freshVar_37)) {{  String  tomMatch207NameNumber_freshVar_34=tom_get_slot_ElementNode_Name(tomMatch207NameNumber_freshVar_37);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_35=tom_get_slot_ElementNode_AttrList(tomMatch207NameNumber_freshVar_37);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_36=tom_get_slot_ElementNode_ChildList(tomMatch207NameNumber_freshVar_37);{  String  tomMatch207NameNumber_freshVar_38=tomMatch207NameNumber_freshVar_34;if (tom_equal_term_String("specification", tomMatch207NameNumber_freshVar_38)) {{  java.util.ArrayList  tomMatch207NameNumber_freshVar_39=tomMatch207NameNumber_freshVar_35;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_39)) {{ int tomMatch207NameNumber_freshVar_40=0;{ int tomMatch207NameNumber_begin_42=tomMatch207NameNumber_freshVar_40;{ int tomMatch207NameNumber_end_43=tomMatch207NameNumber_freshVar_40;do {{{ int tomMatch207NameNumber_freshVar_41=tomMatch207NameNumber_end_43;if (!(tomMatch207NameNumber_freshVar_41 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_60=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39,tomMatch207NameNumber_freshVar_41);if (tom_is_fun_sym_AttributeNode(tomMatch207NameNumber_freshVar_60)) {{  String  tomMatch207NameNumber_freshVar_57=tom_get_slot_AttributeNode_Name(tomMatch207NameNumber_freshVar_60);{  String  tomMatch207NameNumber_freshVar_58=tom_get_slot_AttributeNode_Specified(tomMatch207NameNumber_freshVar_60);{  String  tomMatch207NameNumber_freshVar_59=tom_get_slot_AttributeNode_Value(tomMatch207NameNumber_freshVar_60);{  String  tomMatch207NameNumber_freshVar_61=tomMatch207NameNumber_freshVar_57;if (tom_equal_term_String("xmiid", tomMatch207NameNumber_freshVar_61)) {{  String  tom_i=tomMatch207NameNumber_freshVar_59;{ int tomMatch207NameNumber_freshVar_44=tomMatch207NameNumber_freshVar_41 + 1;{ int tomMatch207NameNumber_begin_46=tomMatch207NameNumber_freshVar_44;{ int tomMatch207NameNumber_end_47=tomMatch207NameNumber_freshVar_44;do {{{ int tomMatch207NameNumber_freshVar_45=tomMatch207NameNumber_end_47;if (!(tomMatch207NameNumber_freshVar_45 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_65=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39,tomMatch207NameNumber_freshVar_45);if (tom_is_fun_sym_AttributeNode(tomMatch207NameNumber_freshVar_65)) {{  String  tomMatch207NameNumber_freshVar_62=tom_get_slot_AttributeNode_Name(tomMatch207NameNumber_freshVar_65);{  String  tomMatch207NameNumber_freshVar_63=tom_get_slot_AttributeNode_Specified(tomMatch207NameNumber_freshVar_65);{  String  tomMatch207NameNumber_freshVar_64=tom_get_slot_AttributeNode_Value(tomMatch207NameNumber_freshVar_65);{  String  tomMatch207NameNumber_freshVar_66=tomMatch207NameNumber_freshVar_62;if (tom_equal_term_String("xmitype", tomMatch207NameNumber_freshVar_66)) {{  String  tom_t=tomMatch207NameNumber_freshVar_64;{ int tomMatch207NameNumber_freshVar_48=tomMatch207NameNumber_freshVar_45 + 1;{  java.util.ArrayList  tomMatch207NameNumber_freshVar_50=tomMatch207NameNumber_freshVar_36;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_50)) {{ int tomMatch207NameNumber_freshVar_51=0;{ int tomMatch207NameNumber_begin_53=tomMatch207NameNumber_freshVar_51;{ int tomMatch207NameNumber_end_54=tomMatch207NameNumber_freshVar_51;do {{{ int tomMatch207NameNumber_freshVar_52=tomMatch207NameNumber_end_54;if (!(tomMatch207NameNumber_freshVar_52 >= tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_50))) {{ org.w3c.dom.Node tomMatch207NameNumber_freshVar_70=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_50,tomMatch207NameNumber_freshVar_52);if (tom_is_fun_sym_ElementNode(tomMatch207NameNumber_freshVar_70)) {{  String  tomMatch207NameNumber_freshVar_67=tom_get_slot_ElementNode_Name(tomMatch207NameNumber_freshVar_70);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_68=tom_get_slot_ElementNode_AttrList(tomMatch207NameNumber_freshVar_70);{  java.util.ArrayList  tomMatch207NameNumber_freshVar_69=tom_get_slot_ElementNode_ChildList(tomMatch207NameNumber_freshVar_70);{  String  tomMatch207NameNumber_freshVar_71=tomMatch207NameNumber_freshVar_67;if (tom_equal_term_String("body", tomMatch207NameNumber_freshVar_71)) {{  java.util.ArrayList  tomMatch207NameNumber_freshVar_72=tomMatch207NameNumber_freshVar_68;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_72)) {{ int tomMatch207NameNumber_freshVar_73=0;{  java.util.ArrayList  tomMatch207NameNumber_freshVar_75=tomMatch207NameNumber_freshVar_69;if (tom_is_fun_sym_concTNode(tomMatch207NameNumber_freshVar_75)) {{ int tomMatch207NameNumber_freshVar_76=0;{ org.w3c.dom.Node tom_b=tom_get_element_concTNode_TNodeList(tomMatch207NameNumber_freshVar_50,tomMatch207NameNumber_freshVar_52);{ int tomMatch207NameNumber_freshVar_55=tomMatch207NameNumber_freshVar_52 + 1;{ int tomMatch207NameNumber_freshVar_22=tomMatch207NameNumber_freshVar_19 + 1;if ( true ) {


					 String val = null;
					 Guard guard = new Guard(tom_gt, tom_gi);					
					 if (tom_gt.compareTo("umlInteractionConstraint") == 0) //*????????if (`t.copareTo("umlOpaqueExpression") == 0)
					 	val = ((Node)tom_b).getTextContent();
					 guard.spec = new Specification(tom_t, tom_i, val);
					 return guard;
				 }}}}}}}}}}}}}}}}}}}tomMatch207NameNumber_end_54=tomMatch207NameNumber_end_54 + 1;}} while(!(tomMatch207NameNumber_end_54 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_50)));}}}}}}}}}}}}}}}}tomMatch207NameNumber_end_47=tomMatch207NameNumber_end_47 + 1;}} while(!(tomMatch207NameNumber_end_47 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39)));}}}}}}}}}}}}}tomMatch207NameNumber_end_43=tomMatch207NameNumber_end_43 + 1;}} while(!(tomMatch207NameNumber_end_43 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_39)));}}}}}}}}}}}}}}tomMatch207NameNumber_end_21=tomMatch207NameNumber_end_21 + 1;}} while(!(tomMatch207NameNumber_end_21 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_17)));}}}}}}}}}}}}}}}}tomMatch207NameNumber_end_14=tomMatch207NameNumber_end_14 + 1;}} while(!(tomMatch207NameNumber_end_14 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6)));}}}}}}}}}}}}}tomMatch207NameNumber_end_10=tomMatch207NameNumber_end_10 + 1;}} while(!(tomMatch207NameNumber_end_10 > tom_get_size_concTNode_TNodeList(tomMatch207NameNumber_freshVar_6)));}}}}}}}}}}}}}}}}
  
			return null;
		}

		private void extractOperand(Node doc, CombinedFragment owner) {	
			Operand oprnd = null; 
			{{ Object tomMatch208NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch208NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch208NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch208NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch208NameNumber_freshVar_4=tomMatch208NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch208NameNumber_freshVar_4)) {{  String  tomMatch208NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch208NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch208NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch208NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch208NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch208NameNumber_freshVar_4);{  String  tomMatch208NameNumber_freshVar_5=tomMatch208NameNumber_freshVar_1;if (tom_equal_term_String("fragment", tomMatch208NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch208NameNumber_freshVar_6=tomMatch208NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch208NameNumber_freshVar_6)) {{ int tomMatch208NameNumber_freshVar_7=0;{  java.util.ArrayList  tomMatch208NameNumber_freshVar_9=tomMatch208NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch208NameNumber_freshVar_9)) {{ int tomMatch208NameNumber_freshVar_10=0;{ int tomMatch208NameNumber_begin_12=tomMatch208NameNumber_freshVar_10;{ int tomMatch208NameNumber_end_13=tomMatch208NameNumber_freshVar_10;do {{{ int tomMatch208NameNumber_freshVar_11=tomMatch208NameNumber_end_13;if (!(tomMatch208NameNumber_freshVar_11 >= tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_9))) {{ org.w3c.dom.Node tomMatch208NameNumber_freshVar_19=tom_get_element_concTNode_TNodeList(tomMatch208NameNumber_freshVar_9,tomMatch208NameNumber_freshVar_11);if (tom_is_fun_sym_ElementNode(tomMatch208NameNumber_freshVar_19)) {{  String  tomMatch208NameNumber_freshVar_16=tom_get_slot_ElementNode_Name(tomMatch208NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch208NameNumber_freshVar_17=tom_get_slot_ElementNode_AttrList(tomMatch208NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch208NameNumber_freshVar_18=tom_get_slot_ElementNode_ChildList(tomMatch208NameNumber_freshVar_19);{  String  tomMatch208NameNumber_freshVar_20=tomMatch208NameNumber_freshVar_16;if (tom_equal_term_String("operand", tomMatch208NameNumber_freshVar_20)) {{  java.util.ArrayList  tomMatch208NameNumber_freshVar_21=tomMatch208NameNumber_freshVar_17;if (tom_is_fun_sym_concTNode(tomMatch208NameNumber_freshVar_21)) {{ int tomMatch208NameNumber_freshVar_22=0;{ int tomMatch208NameNumber_begin_24=tomMatch208NameNumber_freshVar_22;{ int tomMatch208NameNumber_end_25=tomMatch208NameNumber_freshVar_22;do {{{ int tomMatch208NameNumber_freshVar_23=tomMatch208NameNumber_end_25;if (!(tomMatch208NameNumber_freshVar_23 >= tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch208NameNumber_freshVar_38=tom_get_element_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21,tomMatch208NameNumber_freshVar_23);if (tom_is_fun_sym_AttributeNode(tomMatch208NameNumber_freshVar_38)) {{  String  tomMatch208NameNumber_freshVar_35=tom_get_slot_AttributeNode_Name(tomMatch208NameNumber_freshVar_38);{  String  tomMatch208NameNumber_freshVar_36=tom_get_slot_AttributeNode_Specified(tomMatch208NameNumber_freshVar_38);{  String  tomMatch208NameNumber_freshVar_37=tom_get_slot_AttributeNode_Value(tomMatch208NameNumber_freshVar_38);{  String  tomMatch208NameNumber_freshVar_39=tomMatch208NameNumber_freshVar_35;if (tom_equal_term_String("xmiid", tomMatch208NameNumber_freshVar_39)) {{  String  tom_i=tomMatch208NameNumber_freshVar_37;{ int tomMatch208NameNumber_freshVar_26=tomMatch208NameNumber_freshVar_23 + 1;{ int tomMatch208NameNumber_begin_28=tomMatch208NameNumber_freshVar_26;{ int tomMatch208NameNumber_end_29=tomMatch208NameNumber_freshVar_26;do {{{ int tomMatch208NameNumber_freshVar_27=tomMatch208NameNumber_end_29;if (!(tomMatch208NameNumber_freshVar_27 >= tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch208NameNumber_freshVar_43=tom_get_element_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21,tomMatch208NameNumber_freshVar_27);if (tom_is_fun_sym_AttributeNode(tomMatch208NameNumber_freshVar_43)) {{  String  tomMatch208NameNumber_freshVar_40=tom_get_slot_AttributeNode_Name(tomMatch208NameNumber_freshVar_43);{  String  tomMatch208NameNumber_freshVar_41=tom_get_slot_AttributeNode_Specified(tomMatch208NameNumber_freshVar_43);{  String  tomMatch208NameNumber_freshVar_42=tom_get_slot_AttributeNode_Value(tomMatch208NameNumber_freshVar_43);{  String  tomMatch208NameNumber_freshVar_44=tomMatch208NameNumber_freshVar_40;if (tom_equal_term_String("xmitype", tomMatch208NameNumber_freshVar_44)) {{  String  tom_t=tomMatch208NameNumber_freshVar_42;{ int tomMatch208NameNumber_freshVar_30=tomMatch208NameNumber_freshVar_27 + 1;{  java.util.ArrayList  tomMatch208NameNumber_freshVar_32=tomMatch208NameNumber_freshVar_18;if (tom_is_fun_sym_concTNode(tomMatch208NameNumber_freshVar_32)) {{ int tomMatch208NameNumber_freshVar_33=0;{ org.w3c.dom.Node tom_op=tom_get_element_concTNode_TNodeList(tomMatch208NameNumber_freshVar_9,tomMatch208NameNumber_freshVar_11);{ int tomMatch208NameNumber_freshVar_14=tomMatch208NameNumber_freshVar_11 + 1;if ( true ) {


				 int indt = owner.Operands.size() + 1;
				 oprnd = new Operand(owner.name + "_" + Integer.toString(indt), tom_t, tom_i,  indt, owner);
				 Node node = (Node)tom_op;
				 NodeList list = node.getChildNodes();       
				 if (list.getLength() > 0) 
				 	 for(int i = 0 ; i<list.getLength() ; i++) 
					 	 if (list.item(i).getNodeName().compareTo("guard") == 0)
						 	 oprnd.guard = extractGuard(list.item(i));		
						 else if (list.item(i).getNodeName().compareTo("fragment") == 0)	
						 	 extractFragment(list.item(i), oprnd.name, oprnd);		
				owner.Operands.add(oprnd);
			}}}}}}}}}}}}}}}}}tomMatch208NameNumber_end_29=tomMatch208NameNumber_end_29 + 1;}} while(!(tomMatch208NameNumber_end_29 > tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21)));}}}}}}}}}}}}}tomMatch208NameNumber_end_25=tomMatch208NameNumber_end_25 + 1;}} while(!(tomMatch208NameNumber_end_25 > tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_21)));}}}}}}}}}}}}}}tomMatch208NameNumber_end_13=tomMatch208NameNumber_end_13 + 1;}} while(!(tomMatch208NameNumber_end_13 > tom_get_size_concTNode_TNodeList(tomMatch208NameNumber_freshVar_9)));}}}}}}}}}}}}}}}}}}}

		}

		private void extractOwnedBehavior(Node doc) {	
			{{ Object tomMatch209NameNumber_freshVar_0=doc;if (tom_is_sort_TNode(tomMatch209NameNumber_freshVar_0)) {{ org.w3c.dom.Node tomMatch209NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch209NameNumber_freshVar_0);{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_4=tomMatch209NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_4)) {{  String  tomMatch209NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_4);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_4);{  String  tomMatch209NameNumber_freshVar_5=tomMatch209NameNumber_freshVar_1;if (tom_equal_term_String("ownedBehavior", tomMatch209NameNumber_freshVar_5)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_6=tomMatch209NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_6)) {{ int tomMatch209NameNumber_freshVar_7=0;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_9=tomMatch209NameNumber_freshVar_3;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_9)) {{ int tomMatch209NameNumber_freshVar_10=0;{ int tomMatch209NameNumber_begin_12=tomMatch209NameNumber_freshVar_10;{ int tomMatch209NameNumber_end_13=tomMatch209NameNumber_freshVar_10;do {{{ int tomMatch209NameNumber_freshVar_11=tomMatch209NameNumber_end_13;if (!(tomMatch209NameNumber_freshVar_11 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_9))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_19=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_9,tomMatch209NameNumber_freshVar_11);if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_19)) {{  String  tomMatch209NameNumber_freshVar_16=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_17=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_19);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_18=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_19);{  String  tomMatch209NameNumber_freshVar_20=tomMatch209NameNumber_freshVar_16;if (tom_equal_term_String("lifeline", tomMatch209NameNumber_freshVar_20)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_21=tomMatch209NameNumber_freshVar_17;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_21)) {{ int tomMatch209NameNumber_freshVar_22=0;{ int tomMatch209NameNumber_begin_24=tomMatch209NameNumber_freshVar_22;{ int tomMatch209NameNumber_end_25=tomMatch209NameNumber_freshVar_22;do {{{ int tomMatch209NameNumber_freshVar_23=tomMatch209NameNumber_end_25;if (!(tomMatch209NameNumber_freshVar_23 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_45=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21,tomMatch209NameNumber_freshVar_23);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_45)) {{  String  tomMatch209NameNumber_freshVar_42=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_45);{  String  tomMatch209NameNumber_freshVar_43=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_45);{  String  tomMatch209NameNumber_freshVar_44=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_45);{  String  tomMatch209NameNumber_freshVar_46=tomMatch209NameNumber_freshVar_42;if (tom_equal_term_String("name", tomMatch209NameNumber_freshVar_46)) {{  String  tom_n=tomMatch209NameNumber_freshVar_44;{ int tomMatch209NameNumber_freshVar_26=tomMatch209NameNumber_freshVar_23 + 1;{ int tomMatch209NameNumber_begin_28=tomMatch209NameNumber_freshVar_26;{ int tomMatch209NameNumber_end_29=tomMatch209NameNumber_freshVar_26;do {{{ int tomMatch209NameNumber_freshVar_27=tomMatch209NameNumber_end_29;if (!(tomMatch209NameNumber_freshVar_27 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_50=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21,tomMatch209NameNumber_freshVar_27);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_50)) {{  String  tomMatch209NameNumber_freshVar_47=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_50);{  String  tomMatch209NameNumber_freshVar_48=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_50);{  String  tomMatch209NameNumber_freshVar_49=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_50);{  String  tomMatch209NameNumber_freshVar_51=tomMatch209NameNumber_freshVar_47;if (tom_equal_term_String("represents", tomMatch209NameNumber_freshVar_51)) {{  String  tom_r=tomMatch209NameNumber_freshVar_49;{ int tomMatch209NameNumber_freshVar_30=tomMatch209NameNumber_freshVar_27 + 1;{ int tomMatch209NameNumber_begin_32=tomMatch209NameNumber_freshVar_30;{ int tomMatch209NameNumber_end_33=tomMatch209NameNumber_freshVar_30;do {{{ int tomMatch209NameNumber_freshVar_31=tomMatch209NameNumber_end_33;if (!(tomMatch209NameNumber_freshVar_31 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_55=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21,tomMatch209NameNumber_freshVar_31);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_55)) {{  String  tomMatch209NameNumber_freshVar_52=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_55);{  String  tomMatch209NameNumber_freshVar_53=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_55);{  String  tomMatch209NameNumber_freshVar_54=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_55);{  String  tomMatch209NameNumber_freshVar_56=tomMatch209NameNumber_freshVar_52;if (tom_equal_term_String("xmiid", tomMatch209NameNumber_freshVar_56)) {{  String  tom_i=tomMatch209NameNumber_freshVar_54;{ int tomMatch209NameNumber_freshVar_34=tomMatch209NameNumber_freshVar_31 + 1;{ int tomMatch209NameNumber_begin_36=tomMatch209NameNumber_freshVar_34;{ int tomMatch209NameNumber_end_37=tomMatch209NameNumber_freshVar_34;do {{{ int tomMatch209NameNumber_freshVar_35=tomMatch209NameNumber_end_37;if (!(tomMatch209NameNumber_freshVar_35 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_60=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21,tomMatch209NameNumber_freshVar_35);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_60)) {{  String  tomMatch209NameNumber_freshVar_57=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_60);{  String  tomMatch209NameNumber_freshVar_58=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_60);{  String  tomMatch209NameNumber_freshVar_59=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_60);{  String  tomMatch209NameNumber_freshVar_61=tomMatch209NameNumber_freshVar_57;if (tom_equal_term_String("xmitype", tomMatch209NameNumber_freshVar_61)) {{  String  tom_t=tomMatch209NameNumber_freshVar_59;{ int tomMatch209NameNumber_freshVar_38=tomMatch209NameNumber_freshVar_35 + 1;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_40=tomMatch209NameNumber_freshVar_18;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_40)) {{ int tomMatch209NameNumber_freshVar_41=0;if (tomMatch209NameNumber_freshVar_41 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_40)) {{ int tomMatch209NameNumber_freshVar_14=tomMatch209NameNumber_freshVar_11 + 1;if ( true ) {

  extractLifeLine(tom_t, tom_i, tom_n, tom_r); }}}}}}}}}}}}}}}}}tomMatch209NameNumber_end_37=tomMatch209NameNumber_end_37 + 1;}} while(!(tomMatch209NameNumber_end_37 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21)));}}}}}}}}}}}}}tomMatch209NameNumber_end_33=tomMatch209NameNumber_end_33 + 1;}} while(!(tomMatch209NameNumber_end_33 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21)));}}}}}}}}}}}}}tomMatch209NameNumber_end_29=tomMatch209NameNumber_end_29 + 1;}} while(!(tomMatch209NameNumber_end_29 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21)));}}}}}}}}}}}}}tomMatch209NameNumber_end_25=tomMatch209NameNumber_end_25 + 1;}} while(!(tomMatch209NameNumber_end_25 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_21)));}}}}}}}}}}}}}}tomMatch209NameNumber_end_13=tomMatch209NameNumber_end_13 + 1;}} while(!(tomMatch209NameNumber_end_13 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_9)));}}}}}}}}}}}}}}}}}}{ Object tomMatch209NameNumber_freshVar_62=doc;if (tom_is_sort_TNode(tomMatch209NameNumber_freshVar_62)) {{ org.w3c.dom.Node tomMatch209NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch209NameNumber_freshVar_62);{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_66=tomMatch209NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_66)) {{  String  tomMatch209NameNumber_freshVar_63=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_66);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_64=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_66);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_65=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_66);{  String  tomMatch209NameNumber_freshVar_67=tomMatch209NameNumber_freshVar_63;if (tom_equal_term_String("ownedBehavior", tomMatch209NameNumber_freshVar_67)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_68=tomMatch209NameNumber_freshVar_64;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_68)) {{ int tomMatch209NameNumber_freshVar_69=0;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_71=tomMatch209NameNumber_freshVar_65;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_71)) {{ int tomMatch209NameNumber_freshVar_72=0;{ int tomMatch209NameNumber_begin_74=tomMatch209NameNumber_freshVar_72;{ int tomMatch209NameNumber_end_75=tomMatch209NameNumber_freshVar_72;do {{{ int tomMatch209NameNumber_freshVar_73=tomMatch209NameNumber_end_75;if (!(tomMatch209NameNumber_freshVar_73 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_71))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_81=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_71,tomMatch209NameNumber_freshVar_73);if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_81)) {{  String  tomMatch209NameNumber_freshVar_78=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_81);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_79=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_81);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_80=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_81);{  String  tomMatch209NameNumber_freshVar_82=tomMatch209NameNumber_freshVar_78;if (tom_equal_term_String("message", tomMatch209NameNumber_freshVar_82)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_83=tomMatch209NameNumber_freshVar_79;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_83)) {{ int tomMatch209NameNumber_freshVar_84=0;{ int tomMatch209NameNumber_begin_86=tomMatch209NameNumber_freshVar_84;{ int tomMatch209NameNumber_end_87=tomMatch209NameNumber_freshVar_84;do {{{ int tomMatch209NameNumber_freshVar_85=tomMatch209NameNumber_end_87;if (!(tomMatch209NameNumber_freshVar_85 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_116=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_85);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_116)) {{  String  tomMatch209NameNumber_freshVar_113=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_116);{  String  tomMatch209NameNumber_freshVar_114=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_116);{  String  tomMatch209NameNumber_freshVar_115=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_116);{  String  tomMatch209NameNumber_freshVar_117=tomMatch209NameNumber_freshVar_113;if (tom_equal_term_String("connector", tomMatch209NameNumber_freshVar_117)) {{  String  tom_c=tomMatch209NameNumber_freshVar_115;{ int tomMatch209NameNumber_freshVar_88=tomMatch209NameNumber_freshVar_85 + 1;{ int tomMatch209NameNumber_begin_90=tomMatch209NameNumber_freshVar_88;{ int tomMatch209NameNumber_end_91=tomMatch209NameNumber_freshVar_88;do {{{ int tomMatch209NameNumber_freshVar_89=tomMatch209NameNumber_end_91;if (!(tomMatch209NameNumber_freshVar_89 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_121=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_89);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_121)) {{  String  tomMatch209NameNumber_freshVar_118=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_121);{  String  tomMatch209NameNumber_freshVar_119=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_121);{  String  tomMatch209NameNumber_freshVar_120=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_121);{  String  tomMatch209NameNumber_freshVar_122=tomMatch209NameNumber_freshVar_118;if (tom_equal_term_String("messageSort", tomMatch209NameNumber_freshVar_122)) {{  String  tom_s=tomMatch209NameNumber_freshVar_120;{ int tomMatch209NameNumber_freshVar_92=tomMatch209NameNumber_freshVar_89 + 1;{ int tomMatch209NameNumber_begin_94=tomMatch209NameNumber_freshVar_92;{ int tomMatch209NameNumber_end_95=tomMatch209NameNumber_freshVar_92;do {{{ int tomMatch209NameNumber_freshVar_93=tomMatch209NameNumber_end_95;if (!(tomMatch209NameNumber_freshVar_93 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_126=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_93);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_126)) {{  String  tomMatch209NameNumber_freshVar_123=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_126);{  String  tomMatch209NameNumber_freshVar_124=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_126);{  String  tomMatch209NameNumber_freshVar_125=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_126);{  String  tomMatch209NameNumber_freshVar_127=tomMatch209NameNumber_freshVar_123;if (tom_equal_term_String("name", tomMatch209NameNumber_freshVar_127)) {{  String  tom_n=tomMatch209NameNumber_freshVar_125;{ int tomMatch209NameNumber_freshVar_96=tomMatch209NameNumber_freshVar_93 + 1;{ int tomMatch209NameNumber_begin_98=tomMatch209NameNumber_freshVar_96;{ int tomMatch209NameNumber_end_99=tomMatch209NameNumber_freshVar_96;do {{{ int tomMatch209NameNumber_freshVar_97=tomMatch209NameNumber_end_99;if (!(tomMatch209NameNumber_freshVar_97 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_131=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_97);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_131)) {{  String  tomMatch209NameNumber_freshVar_128=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_131);{  String  tomMatch209NameNumber_freshVar_129=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_131);{  String  tomMatch209NameNumber_freshVar_130=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_131);{  String  tomMatch209NameNumber_freshVar_132=tomMatch209NameNumber_freshVar_128;if (tom_equal_term_String("receiveEvent", tomMatch209NameNumber_freshVar_132)) {{  String  tom_r=tomMatch209NameNumber_freshVar_130;{ int tomMatch209NameNumber_freshVar_100=tomMatch209NameNumber_freshVar_97 + 1;{ int tomMatch209NameNumber_begin_102=tomMatch209NameNumber_freshVar_100;{ int tomMatch209NameNumber_end_103=tomMatch209NameNumber_freshVar_100;do {{{ int tomMatch209NameNumber_freshVar_101=tomMatch209NameNumber_end_103;if (!(tomMatch209NameNumber_freshVar_101 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_136=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_101);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_136)) {{  String  tomMatch209NameNumber_freshVar_133=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_136);{  String  tomMatch209NameNumber_freshVar_134=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_136);{  String  tomMatch209NameNumber_freshVar_135=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_136);{  String  tomMatch209NameNumber_freshVar_137=tomMatch209NameNumber_freshVar_133;if (tom_equal_term_String("sendEvent", tomMatch209NameNumber_freshVar_137)) {{  String  tom_d=tomMatch209NameNumber_freshVar_135;{ int tomMatch209NameNumber_freshVar_104=tomMatch209NameNumber_freshVar_101 + 1;{ int tomMatch209NameNumber_begin_106=tomMatch209NameNumber_freshVar_104;{ int tomMatch209NameNumber_end_107=tomMatch209NameNumber_freshVar_104;do {{{ int tomMatch209NameNumber_freshVar_105=tomMatch209NameNumber_end_107;if (!(tomMatch209NameNumber_freshVar_105 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_141=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83,tomMatch209NameNumber_freshVar_105);if (tom_is_fun_sym_AttributeNode(tomMatch209NameNumber_freshVar_141)) {{  String  tomMatch209NameNumber_freshVar_138=tom_get_slot_AttributeNode_Name(tomMatch209NameNumber_freshVar_141);{  String  tomMatch209NameNumber_freshVar_139=tom_get_slot_AttributeNode_Specified(tomMatch209NameNumber_freshVar_141);{  String  tomMatch209NameNumber_freshVar_140=tom_get_slot_AttributeNode_Value(tomMatch209NameNumber_freshVar_141);{  String  tomMatch209NameNumber_freshVar_142=tomMatch209NameNumber_freshVar_138;if (tom_equal_term_String("xmiid", tomMatch209NameNumber_freshVar_142)) {{  String  tom_i=tomMatch209NameNumber_freshVar_140;{ int tomMatch209NameNumber_freshVar_108=tomMatch209NameNumber_freshVar_105 + 1;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_110=tomMatch209NameNumber_freshVar_80;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_110)) {{ int tomMatch209NameNumber_freshVar_111=0;{ int tomMatch209NameNumber_freshVar_76=tomMatch209NameNumber_freshVar_73 + 1;if ( true ) {

  extractMessage(tom_i, tom_n, tom_s, tom_d, tom_r, tom_c);  }}}}}}}}}}}}}}}}tomMatch209NameNumber_end_107=tomMatch209NameNumber_end_107 + 1;}} while(!(tomMatch209NameNumber_end_107 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}tomMatch209NameNumber_end_103=tomMatch209NameNumber_end_103 + 1;}} while(!(tomMatch209NameNumber_end_103 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}tomMatch209NameNumber_end_99=tomMatch209NameNumber_end_99 + 1;}} while(!(tomMatch209NameNumber_end_99 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}tomMatch209NameNumber_end_95=tomMatch209NameNumber_end_95 + 1;}} while(!(tomMatch209NameNumber_end_95 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}tomMatch209NameNumber_end_91=tomMatch209NameNumber_end_91 + 1;}} while(!(tomMatch209NameNumber_end_91 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}tomMatch209NameNumber_end_87=tomMatch209NameNumber_end_87 + 1;}} while(!(tomMatch209NameNumber_end_87 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_83)));}}}}}}}}}}}}}}tomMatch209NameNumber_end_75=tomMatch209NameNumber_end_75 + 1;}} while(!(tomMatch209NameNumber_end_75 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_71)));}}}}}}}}}}}}}}}}}}{ Object tomMatch209NameNumber_freshVar_143=doc;if (tom_is_sort_TNode(tomMatch209NameNumber_freshVar_143)) {{ org.w3c.dom.Node tomMatch209NameNumberfreshSubject_1=((org.w3c.dom.Node)tomMatch209NameNumber_freshVar_143);{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_147=tomMatch209NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_147)) {{  String  tomMatch209NameNumber_freshVar_144=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_147);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_145=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_147);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_146=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_147);{  String  tomMatch209NameNumber_freshVar_148=tomMatch209NameNumber_freshVar_144;if (tom_equal_term_String("ownedBehavior", tomMatch209NameNumber_freshVar_148)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_149=tomMatch209NameNumber_freshVar_145;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_149)) {{ int tomMatch209NameNumber_freshVar_150=0;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_152=tomMatch209NameNumber_freshVar_146;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_152)) {{ int tomMatch209NameNumber_freshVar_153=0;{ int tomMatch209NameNumber_begin_155=tomMatch209NameNumber_freshVar_153;{ int tomMatch209NameNumber_end_156=tomMatch209NameNumber_freshVar_153;do {{{ int tomMatch209NameNumber_freshVar_154=tomMatch209NameNumber_end_156;if (!(tomMatch209NameNumber_freshVar_154 >= tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_152))) {{ org.w3c.dom.Node tomMatch209NameNumber_freshVar_162=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_152,tomMatch209NameNumber_freshVar_154);if (tom_is_fun_sym_ElementNode(tomMatch209NameNumber_freshVar_162)) {{  String  tomMatch209NameNumber_freshVar_159=tom_get_slot_ElementNode_Name(tomMatch209NameNumber_freshVar_162);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_160=tom_get_slot_ElementNode_AttrList(tomMatch209NameNumber_freshVar_162);{  java.util.ArrayList  tomMatch209NameNumber_freshVar_161=tom_get_slot_ElementNode_ChildList(tomMatch209NameNumber_freshVar_162);{  String  tomMatch209NameNumber_freshVar_163=tomMatch209NameNumber_freshVar_159;if (tom_equal_term_String("fragment", tomMatch209NameNumber_freshVar_163)) {{  java.util.ArrayList  tomMatch209NameNumber_freshVar_164=tomMatch209NameNumber_freshVar_160;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_164)) {{ int tomMatch209NameNumber_freshVar_165=0;{  java.util.ArrayList  tomMatch209NameNumber_freshVar_167=tomMatch209NameNumber_freshVar_161;if (tom_is_fun_sym_concTNode(tomMatch209NameNumber_freshVar_167)) {{ int tomMatch209NameNumber_freshVar_168=0;{ org.w3c.dom.Node tom_a=tom_get_element_concTNode_TNodeList(tomMatch209NameNumber_freshVar_152,tomMatch209NameNumber_freshVar_154);{ int tomMatch209NameNumber_freshVar_157=tomMatch209NameNumber_freshVar_154 + 1;if ( true ) {

  extractFragment(tom_a, modelname, sd); 	}}}}}}}}}}}}}}}}}}tomMatch209NameNumber_end_156=tomMatch209NameNumber_end_156 + 1;}} while(!(tomMatch209NameNumber_end_156 > tom_get_size_concTNode_TNodeList(tomMatch209NameNumber_freshVar_152)));}}}}}}}}}}}}}}}}}}}

		}
	}
