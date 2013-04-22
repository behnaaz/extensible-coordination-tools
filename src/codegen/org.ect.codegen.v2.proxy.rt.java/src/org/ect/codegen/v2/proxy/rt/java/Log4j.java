package org.ect.codegen.v2.proxy.rt.java;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4j {

	/**
	 * Disables Log4j.
	 */
	public static void disable() {
		final List<Logger> loggers = new ArrayList<Logger>();

		/* Get the current loggers. */
		final Enumeration<?> currentLoggers = LogManager.getCurrentLoggers();
		while (currentLoggers.hasMoreElements()) {
			Object object = currentLoggers.nextElement();
			if (object instanceof Logger)
				loggers.add((Logger) object);
		}

		/* Get the root logger. */
		loggers.add(LogManager.getRootLogger());

		/* Disable. */
		for (final Logger logger : loggers)
			logger.setLevel(Level.OFF);
	}
}
