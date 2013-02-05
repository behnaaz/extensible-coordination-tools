package org.ect.ea.runtime.conditions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ect.ea.runtime.TransCon;
import org.ect.ea.runtime.TransactionalIO;
import org.ect.ea.runtime.primitives.TimeoutPort;


/**
 * Represents a boolean predicate
 * @author maraikar
 */
public class Predicate implements TransCon, Serializable {
	private static final long serialVersionUID = 1L;

	public static enum Rel {
		Less("<"), LEq("<="), Equal("=="), NEq("!="), GEq(">="), Greater(">");
		
		private String symbol;
		private Rel(String symbol) {
			this.symbol = symbol;
			syms.put(symbol, this);
		}
		@Override
		public String toString() {
			return symbol;
		}
		<U> boolean eval (Comparable<U> left, U	right) {			
			switch (this) {
			case Less:
				return left.compareTo(right) < 0;
			case LEq:
				return left.compareTo(right) <= 0;
			case Equal:
				return left.compareTo(right) == 0;
			case NEq:
				return left.compareTo(right) != 0;
			case GEq:
				return left.compareTo(right) >= 0;
			case Greater:
				return left.compareTo(right) > 0;
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	private static Map<String, Rel> syms = new HashMap<String, Rel>();
	public static Rel operator(String sym) {
		if (syms.isEmpty())
			Rel.values();
		return syms.get(sym);
	}

	protected TransactionalIO lhs, rhs;
	protected Rel op;
	
	public Predicate(TransactionalIO left, TransactionalIO right, Rel op) {
		lhs = left;
		rhs = right;
		this.op = op;
	}

	@Override 
	public String toString() {
		return new StringBuilder()
		.append(lhs)
		.append(op)
		.append(rhs)
		.toString();
	}

	public boolean execute() {		
		if (op.eval ((Comparable)lhs.engineTake() , rhs.engineTake()))
			return true;
		//else
		if (lhs instanceof TimeoutPort) lhs.endTxn();
		if (rhs instanceof TimeoutPort) rhs.endTxn();
		return false;
	}

}
