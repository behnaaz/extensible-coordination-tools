package org.ect.reo.animation.templates;

import org.ect.reo.animation.folders.*;

public class AnimationFrameTemplate
{
  protected static String nl;
  public static synchronized AnimationFrameTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    AnimationFrameTemplate result = new AnimationFrameTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<html>" + NL + "" + NL + "<head>" + NL + "\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />" + NL + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"";
  protected final String TEXT_3 = "\">" + NL + "</head>" + NL + "" + NL + "<body>" + NL + "" + NL + "<object width=\"";
  protected final String TEXT_4 = "\" height=\"";
  protected final String TEXT_5 = "\"" + NL + "\t\t  classid=\"CLSID:D27CDB6E-AE6D-11cf-96B8-444553540000\"" + NL + "\t\t  codebase=\"http://active.macromedia.com/flash2/cabs/swflash.cab#version=4,0,0,0\">" + NL + "\t<param name=\"movie\" value=\"../";
  protected final String TEXT_6 = "\">";
  protected final String TEXT_7 = "\t" + NL + "\t<param name=\"loop\" value=\"true\">" + NL + "\t";
  protected final String TEXT_8 = NL + "\t<param name=\"quality\" value=\"high\">" + NL + "\t<param name=\"scale\" value=\"exactfit\">" + NL + "\t<param name=\"menu\" value=\"true\">" + NL + "\t<embed src=\"../";
  protected final String TEXT_9 = "\" width=\"";
  protected final String TEXT_10 = "\" height=\"";
  protected final String TEXT_11 = "\"" + NL + "\t\t   type=\"application/x-shockwave-flash\"" + NL + "\t\t   pluginspage=\"http://www.macromedia.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\"" + NL + "\t\t   quality=\"high\" " + NL + "\t\t   scale=\"exactfit\" ";
  protected final String TEXT_12 = "\t" + NL + "\t\t\tloop=\"true\"" + NL + "\t";
  protected final String TEXT_13 = NL + "\t\t   menu=\"true\">" + NL + "\t</embed>" + NL + "</object>" + NL + "" + NL + "</body>" + NL + "</html>";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	
	BasicAnimationFolder folder = (BasicAnimationFolder) argument;
	MovieDescription desc = folder.getMovieDescription(folder.getLength()-1);
	

    stringBuffer.append(TEXT_1);
    stringBuffer.append( folder.getName() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( folder.getStyleSheet() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( desc.getWidth() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( desc.getHeight() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( desc.getSWF() );
    stringBuffer.append(TEXT_6);
    
	if (desc.isLoop()) { 
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
    stringBuffer.append( desc.getSWF() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append( desc.getWidth() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append( desc.getHeight() );
    stringBuffer.append(TEXT_11);
    
	if (desc.isLoop()) { 
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
