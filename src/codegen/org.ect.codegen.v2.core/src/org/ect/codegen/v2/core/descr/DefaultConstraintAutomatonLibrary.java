package org.ect.codegen.v2.core.descr;

import java.util.Arrays;

public class DefaultConstraintAutomatonLibrary implements
		BehaviorLibrary<DefaultConstraintAutomaton> {

	//
	// FIELDS
	//

	/**
	 * A counter for the number of cells produced so far for FIFO instances.
	 */
	private long cellCounter = 0;

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newAsyncDrain(final String inputName1,
			final String inputName2) {

		if (inputName1 == null || inputName2 == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName1);
		automaton.addVertex(inputName2);
		automaton.addState("ASYNCDRAIN", true);
		automaton.addOrGetTransition("ASYNCDRAIN", "ASYNCDRAIN",
				Arrays.asList(new String[] { inputName1 }), "true");
		automaton.addOrGetTransition("ASYNCDRAIN", "ASYNCDRAIN",
				Arrays.asList(new String[] { inputName2 }), "true");

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newAsyncSpout(final String outputName1,
			final String outputName2) {

		if (outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (outputName1.isEmpty() || outputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(outputName1);
		automaton.addVertex(outputName2);
		automaton.addState("ASYNCDRAIN", true);
		automaton.addOrGetTransition("ASYNCDRAIN", "ASYNCDRAIN",
				Arrays.asList(new String[] { outputName1 }), "$" + outputName1
						+ "==randomInt(0,1)");
		automaton.addOrGetTransition("ASYNCDRAIN", "ASYNCDRAIN",
				Arrays.asList(new String[] { outputName2 }), "$" + outputName2
						+ "==randomInt(0,1)");

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newFIFO(final String inputName,
			final String outputName, final boolean isFull) {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		/* Prepare. */
		final String cellName = "cell" + (cellCounter++);

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName);
		automaton.addVertex(cellName);
		automaton.addState("FIFO-E", !isFull);
		automaton.addState("FIFO-F", isFull);
		automaton.addOrGetTransition("FIFO-E", "FIFO-F",
				Arrays.asList(new String[] { inputName, cellName }), "$"
						+ cellName + " := $" + inputName);
		automaton.addOrGetTransition("FIFO-F", "FIFO-E",
				Arrays.asList(new String[] { outputName, cellName }), "$"
						+ outputName + "==$" + cellName);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newFIFOn(final String inputName,
			final String outputName, final int n) {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty() || n < 1)
			throw new IllegalArgumentException();

		/* Prepare. */
		int i = 1;
		String internalName1, internalName2;
		internalName2 = inputName + outputName + i;

		/* Construct. */
		final DefaultConstraintAutomaton automaton = newFIFO(inputName,
				n == 1 ? outputName : internalName2, false);

		/* Compose. */
		while (i < n) {
			internalName1 = internalName2;
			internalName2 = inputName + outputName + ++i;

			automaton.join(newFIFO(internalName1, i == n - 1 ? outputName
					: internalName2, false));

			automaton.hide(internalName1);
		}

		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newFilter(final String inputName,
			final String outputName, final String constraintText) {

		if (inputName == null || outputName == null || constraintText == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty()
				|| constraintText.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName);
		automaton.addState("FILTER", true);

		automaton.addOrGetTransition("FILTER", "FILTER",
				Arrays.asList(new String[] { inputName, outputName }), "$"
						+ inputName + "==$" + outputName + " && filter($"
						+ inputName + ", [[" + constraintText + "]])");

		automaton.addOrGetTransition("FILTER", "FILTER",
				Arrays.asList(new String[] { inputName }), "filter($"
						+ inputName + ", [[!(" + constraintText + ")]])");

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newLossyFIFO(final String inputName,
			String outputName) {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		/* Prepare. */
		final String internalName = inputName + outputName;

		/* Construct. */
		final DefaultConstraintAutomaton automaton = newLossySync(inputName,
				internalName);

		/* Compose. */
		automaton.join(newFIFO(internalName, outputName, false));
		automaton.hide(internalName);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newLossySync(final String inputName,
			String outputName) {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName);
		automaton.addState("LOSSYSYNC", true);
		automaton.addOrGetTransition("LOSSYSYNC", "LOSSYSYNC",
				Arrays.asList(new String[] { inputName }), "true");
		automaton.addOrGetTransition("LOSSYSYNC", "LOSSYSYNC",
				Arrays.asList(new String[] { inputName, outputName }), "$"
						+ inputName + "==$" + outputName);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newMerger(final String inputName1,
			String inputName2, final String outputName) {

		if (inputName1 == null || inputName2 == null || outputName == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty()
				|| outputName.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName1);
		automaton.addVertex(inputName2);
		automaton.addVertex(outputName);
		automaton.addState("MERGER", true);
		automaton.addOrGetTransition("MERGER", "MERGER",
				Arrays.asList(new String[] { inputName1, outputName }), "$"
						+ inputName1 + "==$" + outputName);
		automaton.addOrGetTransition("MERGER", "MERGER",
				Arrays.asList(new String[] { inputName2, outputName }), "$"
						+ inputName2 + "==$" + outputName);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newReplicator(final String inputName,
			String outputName1, final String outputName2) {

		if (inputName == null || outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName1.isEmpty()
				|| outputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName1);
		automaton.addVertex(outputName2);
		automaton.addState("REPLICATOR", true);
		automaton.addOrGetTransition(
				"REPLICATOR",
				"REPLICATOR",
				Arrays.asList(new String[] { inputName, outputName1,
						outputName2 }), "$" + inputName + "==$" + outputName1
						+ "==$" + outputName2);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newRouter(final String inputName,
			String outputName1, final String outputName2) {

		if (inputName == null || outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName1.isEmpty()
				|| outputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName1);
		automaton.addVertex(outputName2);
		automaton.addState("ROUTER", true);

		automaton.addOrGetTransition("ROUTER", "ROUTER",
				Arrays.asList(new String[] { inputName, outputName1, }), "$"
						+ inputName + "==$" + outputName1);

		automaton.addOrGetTransition("ROUTER", "ROUTER",
				Arrays.asList(new String[] { inputName, outputName2 }), "$"
						+ inputName + "==$" + outputName2);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newSync(final String inputName,
			final String outputName) {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName);
		automaton.addVertex(outputName);
		automaton.addState("SYNC", true);
		automaton.addOrGetTransition("SYNC", "SYNC",
				Arrays.asList(new String[] { inputName, outputName }), "$"
						+ inputName + "==$" + outputName);

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newSyncDrain(final String inputName1,
			final String inputName2) {

		if (inputName1 == null || inputName2 == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(inputName1);
		if (!inputName1.equals(inputName2))
			automaton.addVertex(inputName2);
		automaton.addState("SYNCDRAIN", true);
		automaton.addOrGetTransition("SYNCDRAIN", "SYNCDRAIN",
				Arrays.asList(new String[] { inputName1, inputName2 }), "true");

		/* Return. */
		return automaton;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public DefaultConstraintAutomaton newSyncSpout(final String outputName1,
			final String outputName2) {

		if (outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (outputName1.isEmpty() || outputName2.isEmpty())
			throw new IllegalArgumentException();

		/* Construct. */
		final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();

		/* Initialize. */
		automaton.addVertex(outputName1);
		automaton.addVertex(outputName2);
		automaton.addState("SYNCSPOUT", true);
		automaton.addOrGetTransition("SYNCSPOUT", "SYNCSPOUT",
				Arrays.asList(new String[] { outputName1, outputName2 }), "$"
						+ outputName1 + "==randomInt(0,1) && $" + outputName2
						+ "==randomInt(0,1)");

		/* Return. */
		return automaton;
	}
}
