package org.ect.reo.animation.templates;

import org.ect.reo.animation.folders.*;

public class GuidedMenuFrameTemplate
{
  protected static String nl;
  public static synchronized GuidedMenuFrameTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    GuidedMenuFrameTemplate result = new GuidedMenuFrameTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<html>" + NL + "" + NL + "<head>" + NL + "\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";
  protected final String TEXT_3 = NL + "\t<meta http-equiv=\"refresh\" content=\"2\">";
  protected final String TEXT_4 = NL + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"";
  protected final String TEXT_5 = "\">" + NL + "</head>" + NL + "" + NL + "<body>" + NL + "" + NL + "<h3>Next step</h3>" + NL + "<div id=\"animationLinks\">" + NL + "<table border=0>";
  protected final String TEXT_6 = NL + "\t\t<tr>" + NL + "\t\t<td><a target=\"_parent\" href=\"next_";
  protected final String TEXT_7 = "\">";
  protected final String TEXT_8 = ") &nbsp; </a></td>" + NL + "\t\t<td>" + NL + "\t\t<object width=\"";
  protected final String TEXT_9 = "\" height=\"";
  protected final String TEXT_10 = "\"" + NL + "\t\t  classid=\"CLSID:D27CDB6E-AE6D-11cf-96B8-444553540000\"" + NL + "\t\t  codebase=\"http://active.macromedia.com/flash2/cabs/swflash.cab#version=4,0,0,0\">" + NL + "\t\t\t<param name=\"movie\" value=\"";
  protected final String TEXT_11 = "\">" + NL + "\t\t\t<param name=\"quality\" value=\\\"high\\\">" + NL + "\t\t\t<param name=\"scale\" value=\"exactfit\">" + NL + "\t\t\t<param name=\"menu\" value=\"true\">" + NL + "\t\t\t<embed src=\"";
  protected final String TEXT_12 = "\" width=\"";
  protected final String TEXT_13 = "\" height=\"";
  protected final String TEXT_14 = "\"" + NL + "\t\t   \t\t\ttype=\"application/x-shockwave-flash\"" + NL + "\t\t   \t\t\tpluginspage=\"http://www.macromedia.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\"" + NL + "\t\t   \t\t\tquality=\"high\" scale=\"exactfit\" menu=\"true\">" + NL + "\t\t\t</embed>" + NL + "\t\t</object>" + NL + "\t\t</td>" + NL + "\t\t</tr>";
  protected final String TEXT_15 = NL + "\t\t<tr> <td> <i>loading...</i> </td> </tr>";
  protected final String TEXT_16 = NL + "</table>" + NL + "</div>" + NL + "" + NL + "</body>" + NL + "</html>";
  protected final String TEXT_17 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	
	GuidedAnimationFolder folder = (GuidedAnimationFolder) argument;
	

    stringBuffer.append(TEXT_1);
    stringBuffer.append( folder.getName() );
    stringBuffer.append(TEXT_2);
     if (!folder.isFinished()) { 
    stringBuffer.append(TEXT_3);
     	} 
    stringBuffer.append(TEXT_4);
    stringBuffer.append( folder.getStyleSheet() );
    stringBuffer.append(TEXT_5);
    	for (int i=1; i<folder.getLength(); i++) { 
		MovieDescription desc = folder.getMovieDescription(i); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append( i );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( i );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( desc.getWidth() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append( desc.getHeight() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append( desc.getSWF() );
    stringBuffer.append(TEXT_11);
    stringBuffer.append( desc.getSWF() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( desc.getWidth() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( desc.getHeight() );
    stringBuffer.append(TEXT_14);
    	} 
	if (!folder.isFinished()) { 
    stringBuffer.append(TEXT_15);
    	} 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
