package org.ect.codegen.v2.core.rt.java.solver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class BooleanExpression {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a boolean expression.
	 */
	protected BooleanExpression() {
	}

	//
	// METHODS
	//

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>boolean</code>
	 *             parameters.
	 */
	public boolean evaluate(final boolean d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>byte</code> parameters.
	 */
	public boolean evaluate(final byte d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>char</code> parameters.
	 */
	public boolean evaluate(final char d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>double</code>
	 *             parameters.
	 */
	public boolean evaluate(final double d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>float</code> parameters.
	 */
	public boolean evaluate(final float d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>int</code> parameters.
	 */
	public boolean evaluate(final int d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>long</code> parameters.
	 */
	public boolean evaluate(final long d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>Object</code>
	 *             parameters.
	 */
	public boolean evaluate(final Object d) {
		if (d instanceof Boolean)
			return evaluate(((Boolean) d).booleanValue());
		if (d instanceof Byte)
			return evaluate(((Byte) d).byteValue());
		if (d instanceof Character)
			return evaluate(((Character) d).charValue());
		if (d instanceof Double)
			return evaluate(((Double) d).doubleValue());
		if (d instanceof Float)
			return evaluate(((Float) d).floatValue());
		if (d instanceof Integer)
			return evaluate(((Integer) d).intValue());
		if (d instanceof Long)
			return evaluate(((Long) d).longValue());
		if (d instanceof Short)
			return evaluate(((Short) d).shortValue());

		throw new UnsupportedOperationException();
	}

	/**
	 * Evaluates this boolean expression for the parameter <code>d</code>.
	 * 
	 * @param d
	 *            The parameter.
	 * @return <code>true</code> if this expression evaluates to
	 *         <code>true</code>; <code>false</code> otherwise.
	 * @throws UnsupportedOperationException
	 *             If this expression supports no <code>short</code> parameters.
	 */
	public boolean evaluate(final short d) {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The number of classes generated so far.
	 */
	private static AtomicInteger nClasses = new AtomicInteger(1);

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Constructs a boolean expression based on its textual representation
	 * <code>expressionText</code>.
	 * 
	 * @param expressionText
	 *            A textual representation of the boolean expression. Not
	 *            <code>null</code>.
	 * @return A boolean expression. Not <code>null</code>.
	 * @throws BooleanExpressionException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>expressionText==null</code>.
	 */
	public static BooleanExpression newInstance(final String expressionText)
			throws BooleanExpressionException {

		if (expressionText == null)
			throw new NullPointerException();

		if (!cachedBooleanExpression.containsKey(expressionText))

			try {
				/* Get a name for the class to generate. */
				final String className = "BooleanExpression"
						+ nClasses.getAndIncrement();

				/* Generate the header of the class to generate. */
				String classCode = "";
				classCode += "import "
						+ BooleanExpression.class.getCanonicalName() + ";\n";
				classCode += "public class " + className
						+ " extends BooleanExpression {\n";

				/*
				 * Check for which primitive data types overriding the method
				 * $evaluate makes sense.
				 */
				final List<String> compatibleTypes = new ArrayList<String>();
				for (final String s : new String[] { "boolean", "byte", "char",
						"double", "float", "int", "long", "Object", "short" }) {

					/* Generate a method. */
					String methodCode0 = "";
					methodCode0 += "	public boolean evaluate(final " + s
							+ " $_) {\n";
					methodCode0 += "		return " + expressionText + ";\n";
					methodCode0 += "	}\n";

					/* Generate a class. */
					String className0 = className + "Test"
							+ s.substring(0, 1).toUpperCase() + s.substring(1);

					String classCode0 = "";
					classCode0 += "public class " + className0 + " {\n";
					classCode0 += methodCode0;
					classCode0 += "}\n";

					/*
					 * Compile the class specified by $classCode0, and add the
					 * method specified by $methodCode0 to $classCode if
					 * compilation succeeds.
					 */
					final Compilable compilable0 = new Compilable(className0,
							classCode0);
					try {
						Compiler.compile(compilable0);
					} catch (final Exception e) {
						continue;
					}

					classCode += methodCode0;
					compatibleTypes.add(s);
				}

				/* Generate the footer of the class to generate. */
				classCode += "}";

				/* Compile and return. */
				if (!compatibleTypes.isEmpty()) {
					final Compilable compilable = new Compilable(className,
							classCode);
					try {
						final Object object = Compiler.compile(compilable);
						if (object instanceof BooleanExpression)
							cachedBooleanExpression.put(expressionText,
									(BooleanExpression) object);

						// return (BooleanExpression) object;

					} catch (final Exception e) {
						throw new BooleanExpressionException(
								BooleanExpressionException
										.NEW_INSTANCE(expressionText),
								e);
					}
				} else
					throw new BooleanExpressionException(
							BooleanExpressionException
									.NEW_INSTANCE(expressionText),
							"Type inference for the data item involved yields no supported type.");

			} catch (final Exception e) {
				if (e instanceof BooleanExpressionException)
					throw (BooleanExpressionException) e;

				throw new BooleanExpressionException(
						BooleanExpressionException.NEW_INSTANCE(expressionText),
						e);
			}

		return cachedBooleanExpression.get(expressionText);
	}

	private static Map<String, BooleanExpression> cachedBooleanExpression = new HashMap<String, BooleanExpression>();

	//
	// EXCEPTIONS
	//

	public static class BooleanExpressionException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		private BooleanExpressionException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		private BooleanExpressionException(final String message,
				final Throwable cause) {

			super(message, cause);
		}

		//
		// METHODS
		//

		private static String NEW_INSTANCE(final String expressionText) {
			return "The class "
					+ BooleanExpression.class.getCanonicalName()
					+ " failed to construct a boolean expression for the expression text \""
					+ expressionText + "\".";
		}
	}
}

class Compiler {

	//
	// CONSTRUCTORS
	//

	/**
	 * Deprecates the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private Compiler() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Compiles the compilable <code>compilable</code>.
	 * 
	 * @param compilable
	 *            The compilable. Not <code>null</code>.
	 * @return An instance of the class represented by <code>compilable</code>.
	 * @throws CompilerException
	 *             If something goes wrong before, while, or after compiling.
	 * @throws NullPointerException
	 *             If <code>compilable==null</code>.
	 */
	static Object compile(final Compilable compilable) throws CompilerException {
		if (compilable == null)
			throw new NullPointerException();

		try {
			/* Get the compiler. */
			final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			if (compiler == null)
				throw new CompilerException(
						CompilerException.COMPILE(compilable),
						"No Java compiler is available. (Is this program run with a JRE instead of a JDK?)");

			/* Prepare: get a file manager. */
			final JavaFileManager manager = new ClassFileManager(
					compiler.getStandardFileManager(null, null, null));

			/* Prepare: get the compilation classpath. */
			final String pathToBooleanExpressionClazz = new File(
					BooleanExpression.class.getProtectionDomain()
							.getCodeSource().getLocation().getPath())
					.getCanonicalPath();

			String classpath = System.getProperty("java.class.path", "")
					+ File.pathSeparator
					+ pathToBooleanExpressionClazz
					+ (pathToBooleanExpressionClazz.endsWith(".jar") ? ""
							: File.separator + "bin");

			final List<String> options = new ArrayList<String>();
			options.add("-classpath");
			options.add(classpath);

			/* Invoke the compiler. */
			boolean printDiagnostics = false;
			final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			if (!compiler.getTask(new StringWriter(), manager, diagnostics,
					options, null,
					Arrays.asList(new Compilable[] { compilable })).call()) {

				if (printDiagnostics)
					for (final Diagnostic<?> diagnostic : diagnostics
							.getDiagnostics())
						System.out.println(diagnostic.getMessage(null));

				throw new CompilerException(
						CompilerException.COMPILE(compilable),
						"The compiler silently failed.");
			}

			/* Load the compilable. */
			final ClassLoader loader = manager.getClassLoader(null);
			Class<?> clazz = loader.loadClass(compilable.getClassName());
			if (clazz == null)
				throw new CompilerException(
						CompilerException.COMPILE(compilable),
						"The class loader silently failed to load the compilable compiled.");

			/* Construct an instance. */
			final Object instance = clazz.newInstance();
			if (instance == null)
				throw new CompilerException(
						CompilerException.COMPILE(compilable),
						"The Java Reflection API silently failed to construct an instance of the compilable compiled and loaded.");

			/* Return. */
			return instance;

		} catch (final Exception e) {
			if (e instanceof CompilerException)
				throw (CompilerException) e;

			throw new CompilerException("[UNDOCUMENTED] "
					+ CompilerException.COMPILE(compilable), e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class CompilerException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		private CompilerException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		private CompilerException(final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		private static String COMPILE(final Compilable compilable) {
			return "The class "
					+ Compiler.class.getCanonicalName()
					+ " failed either to compile the compilable "
					+ compilable
					+ " or to construct an instance of the compilable compiled.";
		}
	}
}

class Compilable extends SimpleJavaFileObject {

	//
	// FIELDS
	//

	/**
	 * The name of this compilable.
	 */
	private final String name;

	/**
	 * The code to compile.
	 */
	private final CharSequence code;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a compilable named <code>name</code>, specified by the code
	 * <code>code</code>.
	 * 
	 * @param name
	 *            The name of the compilable. Not <code>null</code>.
	 * @param code
	 *            The code. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or <code>code==null</code> or
	 *             <code>uri==null</code>.
	 */
	Compilable(final String name, final CharSequence code) {
		super(URI.create("string:///" + name.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);

		if (code == null)
			throw new NullPointerException();

		this.code = code;
		this.name = name;
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}

	/**
	 * Gets the name of this compilable.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getClassName() {
		return name;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return code.toString();
	}
}

class ByteCodeBuffer extends SimpleJavaFileObject {

	//
	// FIELDS
	//

	/**
	 * A stream containing the byte code in this buffer.
	 */
	private final ByteArrayOutputStream stream = new ByteArrayOutputStream();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a byte code buffer containing the byte code of the compiled
	 * class named <code>className</code>.
	 * 
	 * @param className
	 *            The name of the class. Not <code>null</code>.
	 * @param kind
	 *            The kind of the date.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or <code>kind==null</code>.
	 */
	public ByteCodeBuffer(final String className, final Kind kind) {
		super(URI.create("string:///" + className.replace('.', '/')
				+ kind.extension), kind);
	}

	/**
	 * Gets the byte code in this buffer.
	 * 
	 * @return An array of bytes. Never <code>null</code>.
	 */
	public byte[] getBytes() {
		return stream.toByteArray();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream openOutputStream() throws IOException {
		return stream;
	}
}

class ClassFileManager extends ForwardingJavaFileManager<JavaFileManager> {

	//
	// FIELDS
	//

	/**
	 * A container holding the byte code of the compiled class.
	 */
	private ByteCodeBuffer byteCodeContainer;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a file manager based on the manager <code>manager</code>.
	 * 
	 * @param manager
	 *            The manager. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>manager==null</code>.
	 */
	public ClassFileManager(final StandardJavaFileManager manager) {
		super(manager);
		if (manager == null)
			throw new NullPointerException();
	}

	//
	// METHODS
	//

	/**
	 * Gets a class loader for the compiled class.
	 * 
	 * @param location
	 *            Unused.
	 */
	@Override
	public ClassLoader getClassLoader(final Location location) {
		return new SecureClassLoader(this.getClass().getClassLoader()) {
			@Override
			protected Class<?> findClass(final String name)
					throws ClassNotFoundException {

				byte[] b = byteCodeContainer.getBytes();
				return super.defineClass(name, byteCodeContainer.getBytes(), 0,
						b.length);
			}
		};
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(final Location location,
			final String className, final Kind kind, final FileObject sibling)
			throws IOException {

		byteCodeContainer = new ByteCodeBuffer(className, kind);
		return byteCodeContainer;
	}
}