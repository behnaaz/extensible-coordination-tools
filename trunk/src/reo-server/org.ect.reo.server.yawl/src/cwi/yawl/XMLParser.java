package cwi.yawl;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

public class XMLParser {
  public static Element getFirstElement(Element element,
                                        String name) {
    NodeList nl = element.getElementsByTagName(name);
    if (nl.getLength() < 1) {
      return null;
    }
    return (Element) nl.item(0);
  }

  public static String getSimpleElementText(Element node, String name) {
    Element nameEl = XMLParser.getFirstElement(node, name);
    Node textNode = nameEl.getFirstChild();
    if (textNode instanceof Text) {
      return textNode.getNodeValue();
    }
    else {
      return null;
    }
  }

  public static String toString(Node node) {
    try {
      StringWriter stringWriter = new StringWriter();
      Source source = new DOMSource(node);
      Result result = new StreamResult(stringWriter);

      transform(source, result);
      return stringWriter.toString();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return "blank";
    }
  }

  public static void toFile(Node node, String file) {
    try {
      File outputFile = new File(file);
      OutputStream ostream = new FileOutputStream(outputFile);

      Source source = new DOMSource(node);
      Result result = new StreamResult(ostream);

      transform(source, result);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Node fromString(String XML) {
    try {
      Source source = new StreamSource(new StringReader(XML));
      DOMResult result = new DOMResult();

      transform(source, result);
      return result.getNode();
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static void transform(Source source, Result result) {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = null;
    try {
      transformer = tf.newTransformer();
      transformer.transform(source, result);
    }
    catch (TransformerConfigurationException ex) {
      ex.printStackTrace();
    }
    catch (TransformerException ex1) {
      ex1.printStackTrace();
    }
  }

  public static void print(Node node){
    System.out.println(XMLParser.toString(node));
  }
}
