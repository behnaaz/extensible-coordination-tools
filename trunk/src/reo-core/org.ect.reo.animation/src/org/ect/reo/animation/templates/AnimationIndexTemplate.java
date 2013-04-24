package org.ect.reo.animation.templates;

import org.ect.reo.animation.folders.*;

public class AnimationIndexTemplate
{
  protected static String nl;
  public static synchronized AnimationIndexTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    AnimationIndexTemplate result = new AnimationIndexTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<html>" + NL + "" + NL + "<head>" + NL + "\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />" + NL + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"";
  protected final String TEXT_3 = "\">" + NL + "</head>" + NL + "" + NL + "<frameset rows=\"60,*\">" + NL + "\t<frame src=\"";
  protected final String TEXT_4 = "\" name=\"title\" frameborder=\"0\">" + NL + "\t<frameset cols=\"165,*\">" + NL + "\t\t<frame src=\"";
  protected final String TEXT_5 = "\" name=\"menu\" frameborder=\"0\">" + NL + "\t\t<frame src=\"";
  protected final String TEXT_6 = "\" name=\"animation\" frameborder=\"0\">" + NL + "\t</frameset>" + NL + "</frameset>" + NL + "" + NL + "</html>";
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

	BasicAnimationFolder folder = (BasicAnimationFolder) argument;


    stringBuffer.append(TEXT_1);
    stringBuffer.append( folder.getName() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( folder.getStyleSheet() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( folder.getTitle() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( folder.getMenu() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( folder.getMovieDescription(0).getHTML() );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
