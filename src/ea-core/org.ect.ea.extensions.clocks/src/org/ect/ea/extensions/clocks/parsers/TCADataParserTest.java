package org.ect.ea.extensions.clocks.parsers;

import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.common.util.EList;

public class TCADataParserTest {

	public static void main(String[] args) {

		String[] tests = new String[] {
				"A=3",
				"3=A",
				"A=$s.a",
				"$t.g=B",
				"true",
				"A=3+4",
				"3+4=A",
				"B=(3-4)",
				"(3-4)=B",
				"A=(B-3)",
				"-3=A",
				"A=(5--3)",
				"A=(4-3)",
				"A=(3-C)",
				"A=-3 | B=-4",
				"A=B&C=D&E=F",
				"A<=B&C<=D&E<=F",
				"!(A=3)",
				"3=4",
				"!(A=3)&!(B=4)"
		};

		TCADataParser parser;
		
		for (String test : tests) {
			parser = new TCADataParser(test,true);
			//parser = new TCADataParser(test, true);
			
			try {
				//check for parseability
				System.out.println(test);
				String result = parser.data_constraint();
				System.out.println("          " + result);
				parser = new TCADataParser(result);
				result = parser.addIndexRemoveMemcellPrefix("1","2");
				System.out.println("          " + result);
//				if (!test.equals(result)) {
//					throw new Exception("'" + test + "' does not yield expected result: '" + result + "'");
//				}
//				System.out.println(test);
//				EList<String> result = parser.get_minmax_int_values();
  			} catch (RecognitionException e) {
  				System.out.println(e.getMessage());
  			} catch (Exception e) {
  				System.out.println(e.getMessage());
			}
		}
	}
}
