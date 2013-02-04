package org.ect.ea.util.text.serialize;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.ect.ea.util.cacompat.CA;
import org.ect.ea.util.text.parse.CatLexer;
import org.ect.ea.util.text.parse.CatParser;


/**
 * StringTemplate-based rendering to CAT format
 * TODO: template doesn't handle automaton portnames well (spurious SEPARATORs)
 * @author maraikar
 *
 */
public class CA2Text {
	private static StringTemplateGroup ca2txt;
	static {
		FileReader groupFile;
		try {
			groupFile = new FileReader("templates/cat.stg");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		ca2txt = new StringTemplateGroup(groupFile);
	}
	
	public static String toCAT(CA ca) {
		StringTemplate top = ca2txt.getInstanceOf("automaton");
		top.setAttribute("ca", ca);
		return top.toString();
	}
	
	public static void main(String[] args) throws IOException, RecognitionException {		
		StringTemplate top = ca2txt.getInstanceOf("automaton");
		
		CatLexer lexer = new CatLexer(new ANTLRFileStream("testdata/valve.cat"));
		CatParser parser = new CatParser(new CommonTokenStream(lexer));
		CA ca = parser.automaton();
		System.err.println(ca);
		
		top.setAttribute("ca", ca);
		System.out.println(top.toString());
		
		lexer = new CatLexer(new ANTLRStringStream(top.toString()));
		parser = new CatParser(new CommonTokenStream(lexer));
		ca = parser.automaton();

	}
}
