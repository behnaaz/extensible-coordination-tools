package org.ect.codegen.v2.core.descr;

import java.util.Arrays;

public class Namer {

	/**
	 * Converts the state names <code>stateName1</code> and
	 * <code>stateName2</code> to a name for the state resulting from joining
	 * the states named <code>stateName1</code> and <code>stateName2</code>.
	 * 
	 * @param stateName1
	 *            The name of a state. Not <code>null</code> or empty.
	 * @param stateName2
	 *            The name of another state. Not <code>null</code> or empty.
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>stateName1.isEmpty()</code> or
	 *             <code>stateName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>stateName1==null</code> or
	 *             <code>stateName2==null</code>.
	 */
	public static String toJointStateName(final String stateName1,
			final String stateName2) {

		if (stateName1 == null || stateName2 == null)
			throw new NullPointerException();
		if (stateName1.isEmpty() || stateName2.isEmpty())
			throw new IllegalArgumentException();

		return "<" + stateName1 + "," + stateName2 + ">";

		// StatePair pair;
		// if (stateName1.compareTo(stateName2) < 0)
		// pair = new StatePair(state1, state2);
		// else
		// pair = new StatePair(stateName2, stateName1);

		// if (!pairsToSerialNumbers.containsKey(pair))
		// pairsToSerialNumbers.put(pair, nextSerialNumber.getAndIncrement());
		//
		// return "State" + pairsToSerialNumbers.get(pair);

		// if (stateName1.compareTo(stateName2) < 0)
		// return "<" + stateName1.getSerialNumber() + ","
		// + stateName2.getSerialNumber() + ">";
		// else
		// return "<" + stateName2.getSerialNumber() + ","
		// + stateName1.getSerialNumber() + ">";
	}

	// private static AtomicInteger nextSerialNumber = new AtomicInteger(0);
	//
	// private static Map<StatePair, Integer> pairsToSerialNumbers = new
	// HashMap<AbstractVertexStateFactory.StatePair, Integer>();
	//
	// private static class StatePair {
	// private final State<?, ?, ?> state1;
	// private final State<?, ?, ?> state2;
	//
	// private StatePair(final State<?, ?, ?> state1,
	// final State<?, ?, ?> state2) {
	// this.state1 = state1;
	// this.state2 = state2;
	// }
	//
	// @Override
	// public boolean equals(final Object object) {
	// return object instanceof StatePair && equals((StatePair) object);
	// }
	//
	// public boolean equals(final StatePair pair) {
	// return state1.equals(pair.state1) && state2.equals(pair.state2);
	// }
	//
	// @Override
	// public int hashCode() {
	// return state1.hashCode();
	// }
	// }

	/**
	 * Converts the string representations of the transition components
	 * <code>components</code> to a transition name.
	 * 
	 * @param components
	 *            The string representations. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>components==null</code> or
	 *             <code>components[i]==null</code> for some <code>i</code>.
	 */
	public static String toTransitionName(final String... components) {
		if (components == null)
			throw new NullPointerException();
		for (final String s : components)
			if (s == null)
				throw new NullPointerException();

		return Arrays.toString(components).replaceAll(" ", "")
				.replaceAll("\\[", "<").replaceAll("\\]", ">");
	}
}
