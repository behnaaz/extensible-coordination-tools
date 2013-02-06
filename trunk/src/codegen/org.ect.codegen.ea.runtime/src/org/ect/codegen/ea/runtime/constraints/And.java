package org.ect.codegen.ea.runtime.constraints;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ect.codegen.ea.runtime.TransCon;
import org.ect.codegen.ea.runtime.conditions.Predicate;
import org.ect.codegen.ea.runtime.util.XOrder;


public class And implements Serializable, TransCon {
	private static final long serialVersionUID = 1L;

	protected SortedSet<TransCon> conds = new TreeSet<TransCon>(new XOrder());
	
	public void add(TransCon con) {
		assert con instanceof Assignment || con instanceof Predicate;
		conds.add(con);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.codegen.ea.runtime.TransCon#execute()
	 */
	@Override
	public boolean execute() {
		for (TransCon c: conds) 
			if (!c.execute())
				return false;
			
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (conds.isEmpty())
			return "()";

		StringBuilder sb = new StringBuilder();		
		for (TransCon c: conds) 
			sb.append(c).append(" & ");
		
		sb.delete(sb.length()-3, sb.length());
		
		return sb.toString();
	}	
}