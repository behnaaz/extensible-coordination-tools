package org.ect.reo.animation.templates;

import org.ect.reo.animation.folders.*;

public class MenuFrameTemplate
{
  protected static String nl;
  public static synchronized MenuFrameTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    MenuFrameTemplate result = new MenuFrameTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<html>" + NL + "" + NL + "<head>" + NL + "\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";
  protected final String TEXT_3 = NL + "\t<meta http-equiv=\"refresh\" content=\"2\">";
  protected final String TEXT_4 = NL + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"";
  protected final String TEXT_5 = "\">" + NL + "</head>" + NL + "" + NL + "<body>" + NL + "" + NL + "<h3>List of animations</h3>" + NL + "<div id=\"animationLinks\">";
  protected final String TEXT_6 = NL + "\t\t<a href=\"";
  protected final String TEXT_7 = "\" target=\"animation\">Animation&nbsp;";
  protected final String TEXT_8 = " (";
  protected final String TEXT_9 = "&nbsp;steps)</a>";
  protected final String TEXT_10 = NL + "\t\t<br> <i>loading...</i>";
  protected final String TEXT_11 = NL + "</div>" + NL + "" + NL + "</body>" + NL + "</html>";
  protected final String TEXT_12 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	
	BasicAnimationFolder folder = (BasicAnimationFolder) argument;
	

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
    stringBuffer.append( desc.getHTML() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( i );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( folder.getAnimation(i).size() );
    stringBuffer.append(TEXT_9);
    	} 
	if (!folder.isFinished()) { 
    stringBuffer.append(TEXT_10);
    	} 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
