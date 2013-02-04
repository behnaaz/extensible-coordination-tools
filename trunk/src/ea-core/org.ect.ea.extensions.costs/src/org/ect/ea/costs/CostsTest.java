package org.ect.ea.costs;

import java.text.ParseException;

import org.ect.ea.costs.algebras.MemoryAlgebra;
import org.ect.ea.costs.types.StringCosts;


public class CostsTest {

	
	public static void main(String[] args) {
	
		// All type checking is done at run-time.
		
		CostsAlgebra memory = new MemoryAlgebra();
		CostsObject c1 = null, c2 = null;
		
		try {
		
			c1 = memory.parse("13k");
			c2 = memory.parse("27k");
		
		} catch (ParseException e1) {
			// occurs if the string is invalid
			e1.printStackTrace();
		}
		
		try {
			// Pretty printer is still missing.
			System.out.println("Choose:     " + memory.choose(c1, c2));
			System.out.println("Parallel:   " + memory.combineParallel(c1, c2));
			System.out.println("Sequential: " + memory.combineSequentially(c1, c2));
			
		} catch (UnsupportedCostsTypeException e) {
			// Occurs if the type of the CostsObject is invalid. 
			e.printStackTrace();
		}

		
		System.out.println();
		System.out.println("Trying illegal combination: ");
		
		
		try {
		
			CostsObject c3 = new StringCosts("hi!!");
			System.out.println( memory.choose(c1, c3) ); 
		
		} catch (UnsupportedCostsTypeException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
