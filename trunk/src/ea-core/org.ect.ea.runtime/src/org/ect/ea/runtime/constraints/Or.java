package org.ect.ea.runtime.constraints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.ect.ea.runtime.TransCon;


public class Or implements TransCon,Serializable {
	private static final long serialVersionUID = 1L;
	
	protected List<TransCon> conds = new ArrayList<TransCon>();
	private Random rand = new Random();
	
	public void add(TransCon con) {
		assert con instanceof Assignment ||  con instanceof And;

		conds.add(con);
	}
	
	/* (non-Javadoc)
	 * @see cwi.ea.runtime.composite.TransCondition#execute()
	 */
	public boolean execute() {
//		Durstenfeld's Fisher–Yates shuffle
	    for (int n = conds.size(); n > 1; n--) {
	        int k = rand.nextInt(n);
	        Collections.swap(conds, n-1, k);
	        if (conds.get(n-1).execute())
	        	return true;
	        
	    }
	    return false;
	}

	@Override
	public String toString() {
		if (conds.isEmpty())
			return "[]";

		StringBuilder sb = new StringBuilder();		
		for (TransCon a: conds)
			sb.append(a).append(" | ");
		
		sb.delete(sb.length()-3, sb.length());
		
		return sb.toString();
	}	
}