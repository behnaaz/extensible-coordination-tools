package org.ect.codegen.reo2creol;

import java.util.*;
import org.ect.ea.automata.*;

public class CreolTemplate
{
  protected static String nl;
  public static synchronized CreolTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    CreolTemplate result = new CreolTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*Creol code automatically generated from the Constraint Automaton for the Reo citcuit ";
  protected final String TEXT_2 = ".*/" + NL + "class ";
  protected final String TEXT_3 = " implements";
  protected final String TEXT_4 = " WConnector";
  protected final String TEXT_5 = ",";
  protected final String TEXT_6 = " RConnector";
  protected final String TEXT_7 = ",";
  protected final String TEXT_8 = " SConnector";
  protected final String TEXT_9 = NL + "begin" + NL + "\tvar state : String = \"";
  protected final String TEXT_10 = "\"\t\t// initial state";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + "\top init ==";
  protected final String TEXT_13 = NL + "\t\t! transition";
  protected final String TEXT_14 = " ;";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = NL;
  protected final String TEXT_20 = NL;
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "\twith NetworkManager op create (out ce:List[ConnectorEnd]) ==" + NL + "\t\tce := empty_list;";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = " := new ";
  protected final String TEXT_25 = "(this);" + NL + "\t\tadd[ce,";
  protected final String TEXT_26 = "];";
  protected final String TEXT_27 = NL + NL + "end";
  protected final String TEXT_28 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

Automaton automaton = (Automaton) argument;
Connector cnt = new Connector(automaton);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(automaton.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append( automaton.getName() );
    stringBuffer.append(TEXT_3);
    
	if (cnt.hasSource) { 
    stringBuffer.append(TEXT_4);
     }
	if (cnt.hasSource && cnt.hasSink) { 
    stringBuffer.append(TEXT_5);
     }
	if (cnt.hasSink) { 
    stringBuffer.append(TEXT_6);
     }
	if ((cnt.hasSource || cnt.hasSink) && cnt.hasSignal) { 
    stringBuffer.append(TEXT_7);
     }
	if (cnt.hasSignal) { 
    stringBuffer.append(TEXT_8);
     } 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cnt.getInitialState());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cnt.varDecl);
    stringBuffer.append(cnt.transitions);
    stringBuffer.append(TEXT_12);
     for (int i=1; i<=cnt.transCount; i++) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append( i );
    stringBuffer.append(TEXT_14);
     	}
    stringBuffer.append(TEXT_15);
    if (cnt.hasSource) { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(Source.getOperation());
    stringBuffer.append(TEXT_17);
    }
if (cnt.hasSink) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(Sink.getOperation());
    stringBuffer.append(TEXT_19);
    }
if (cnt.hasSignal) { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(Signal.getOperation());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    	Iterator<?> ice = cnt.connectorEndList.iterator();
	while (ice.hasNext()){
		ConnectorEnd cce = (ConnectorEnd)ice.next();	
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cce.getPrefixedName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cce.getCreolTypeName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cce.getPrefixedName());
    stringBuffer.append(TEXT_26);
    	}
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
