package org.ect.reo.animation.templates;

import org.ect.reo.animation.folders.*;

public class TitleFrameTemplate
{
  protected static String nl;
  public static synchronized TitleFrameTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    TitleFrameTemplate result = new TitleFrameTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<html>" + NL + "" + NL + "<head>" + NL + "\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />" + NL + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"";
  protected final String TEXT_3 = "\">" + NL + "</head>" + NL + "" + NL + "<h2 align=\"center\"><a href=\"";
  protected final String TEXT_4 = "\" target=\"animation\">";
  protected final String TEXT_5 = "</a></h2>" + NL + "" + NL + "</html>";
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

	BasicAnimationFolder folder = (BasicAnimationFolder) argument;


    stringBuffer.append(TEXT_1);
    stringBuffer.append( folder.getName() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( folder.getStyleSheet() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( folder.getMovieDescription(0).getHTML() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( folder.getName() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
