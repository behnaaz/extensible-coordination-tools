#*******************************************************************************
# <copyright>
# This file is part of the Extensible Coordination Tools (ECT).
# Copyright (c) 2013 ECT developers. 
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# </copyright>
#*******************************************************************************
#!/bin/bash

# Version.
VERSION=1.6.1

# Base folder / project name.
REO="reo-test"

# Required plug-ins.
PLUGINS_COMMON="org.eclipse.gmf.runtime.emf.type.core
	org.eclipse.gmf.runtime.notation
	org.eclipse.emf.ecore
	org.eclipse.emf.ecore.xmi
	org.eclipse.emf.common
	org.eclipse.core.expressions
	org.eclipse.core.resources
	org.eclipse.core.runtime
	org.eclipse.equinox.common"

PLUGINS_REO="org.ect.reo
	org.ect.reo.animation
	org.ect.reo.preferences
	org.ect.reo.generics
	org.ect.reo.generics.animation
	org.ect.reo.reconfiguration"

PLUGINS_EA="cwi.ea
	cwi.ea.extensions.startStates
	cwi.ea.extensions.portNames
	cwi.ea.extensions.stateMemory
	cwi.ea.extensions.constraints
	cwi.ea.extensions.costs
	cwi.ea.costs
	cwi.ea.casimulator"


function confirm()
{
	read input
	if [ "$input" = "y" -o "$input" = "" ]; then
		export $1=false
	else
		unset $1
	fi
}



function copy_plugins()
{

	for plugin in $@; do
	
		# Search the file.
		while true;	do
		
			path="$ECLIPSE/plugins"
		    files=`cd $path && ls -r ${plugin}_*.jar 2> /dev/null && cd - > /dev/null`
		
			# Use only the first file.
			for current in $files; do
				file="$current"
				break
			done
		
			# Check if it exists and is a regular file.
			if [ -f "$path/$file" ]; then
				break
			fi
		
			# Not found.
			echo
			echo "Plug-in $plugin not found.";
			echo -n "Enter the location of the Eclipse installation: "
			read ECLIPSE
		
    	done
    
    	# Now copy it.
		echo "Copying $file..."
    	cp $path/$file $REO/lib
    
	done

}


echo
echo "This script creates an example Reo stand-alone application in"
echo "the folder '$REO'. You need a valid Eclipse installation"
echo -n "with the Reo plug-ins (>=$VERSION) installed. Continue? (y/n) "
confirm PROCEED

if [ ! "$PROCEED" ]; then
	echo "Aborting..."; exit
fi

echo
echo -n "Do you want to use (constraint) automata? (y/n) "
confirm EA




# Find an Eclipse installation.
ECLIPSE_PATHS="~/eclipse
	/export/scratch1/$USER/eclipse
	/usr/local/share/eclipse
	/usr/share/eclipse
	/opt/eclipse"

for current in $ECLIPSE_PATHS
do    
    if [ -d "$current" ]
    then
		ECLIPSE="$current"
		echo
		echo "Using Eclipse installation in $ECLIPSE..."
		break
    fi
done


# Create directories.
echo
echo "Creating stand-alone application in '$REO'..."

mkdir $REO 2> /dev/null
mkdir $REO/lib 2> /dev/null
mkdir $REO/src 2> /dev/null


# Copy the Reo plug-ins.
echo
echo "Copying Reo plug-ins..."
copy_plugins $PLUGINS_REO


# Copy the automata plug-ins.
if [ "$EA" ]; then

	echo
	echo "Copying automata plug-ins..."
	copy_plugins $PLUGINS_EA
	
	# Unpack ANTLR.
	ANTLR=antlr-3.0.jar
	echo Extracting $ANTLR...
	cd $REO/lib
	jar xf cwi.ea.extensions.constraints_*.jar $ANTLR
	cd - > /dev/null

fi

# Copy the common plug-ins.
echo
echo "Copying dependencies..."
copy_plugins $PLUGINS_COMMON



# Create an example Reo main.

echo
echo "Creating example Reo program..."

MAIN_PACKAGE="myreo"
MAIN_CLASS_REO="ReoTest"

mkdir $REO/src/$MAIN_PACKAGE 2> /dev/null
cat > $REO/src/$MAIN_PACKAGE/$MAIN_CLASS_REO.java <<EOF_REO
package $MAIN_PACKAGE;

import java.io.IOException;

import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.primitives.*;


public class $MAIN_CLASS_REO {
    
    public static void main(String[] args) {
		
		System.out.println("Creating a new Reo connector...");
		
		// Initialize the Reo model. Could be skipped here.
		Reo.initStandalone();
		
		// Create a new connector.
		Connector connector = new Connector("My connector");
		
		// Add nodes.
		Node node1 = new Node("A");
		Node node2 = new Node("B");
		connector.getNodes().add(node1);
		connector.getNodes().add(node2);
		
		// Add a channel.
		Channel sync = new Sync(node1, node2);
		connector.getPrimitives().add(sync);
		
		System.out.println("Saving connector to 'test.reo'...");

		try {
			// Create a module.
			Module module = Reo.create("test.reo");
			module.getConnectors().add(connector);

			// Save the module.
			Reo.save(module);
			
		} catch (IOException e) {
			System.err.println(e);
		}
    }
    
}
EOF_REO



# Create an example CA main.

if [ "$EA" ]; then

echo "Creating example CA program..."

MAIN_PACKAGE="myreo"
MAIN_CLASS_CA="CATest"

mkdir $REO/src/$MAIN_PACKAGE 2> /dev/null
cat > $REO/src/$MAIN_PACKAGE/$MAIN_CLASS_CA.java <<EOF_CA
package $MAIN_PACKAGE;

import java.io.IOException;
import java.text.ParseException;

import cwi.ea.automata.*;
import cwi.ea.extensions.*;
import cwi.ea.extensions.constraints.*;
import cwi.ea.extensions.constraints.parsers.*;
import cwi.ea.extensions.portNames.*;
import cwi.ea.extensions.stateMemory.*;


public class $MAIN_CLASS_CA {
    
    public static void main(String[] args) {

		System.out.println("Creating a new constraint automaton...");
		
		// Initialize the required automata extensions. Could be skipped here.
		CA.initStandalone();
		
		// First we create an automaton.
		Automaton automaton = new Automaton("Test");
		
		// We need to specify that it is a constraint automaton with memory. This needs to be done only once.
		CA.convertToConstraintAutomatonWithMemory(automaton);

		State q1 = new State("q1");
		State q2 = new State("q2");
		automaton.getStates().add(q1);
		automaton.getStates().add(q2);
		
		Transition t = new Transition(q1,q2);
		automaton.getTransitions().add(t);
		
		System.out.println("Created automaton " + CA.prettyPrint(automaton));
		
		// Setting the portnames, memory cells and constraints.
		try {
			CA.setPortNames(automaton, AutomatonPortNames.parse("A,B,C"));
			CA.setPortNames(t, TransitionPortNames.parse("A,B"));
			CA.setMemoryCells(q1, StringListExtension.parse("x,y"));
			CA.setConstraint(t, ConstraintParserHelper.parse("A=B | (A=1 & B=\$s.x)"));
			CA.setStartState(q1, true);
		} catch (ParseException e) {
			System.err.println(e);
		}
		
		System.out.println("Created automaton " + CA.prettyPrint(automaton));

		Automaton product = AutomataProduct.compute(automaton, automaton);
		System.out.println("Computed product " + CA.prettyPrint(product));
		
		System.out.println("Saving automata to 'test.ea'...");
		
		try {
			
			// Create a module.
			Module module = CA.create("test.ea");
			module.getAutomata().add(automaton);
			module.getAutomata().add(product);
			
			// Save the module.
			CA.save(module);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
    }
    
}
EOF_CA

fi


echo "Creating Ant build script..."

cat > $REO/build.xml <<EOF
<?xml version="1.0"?>
<project name="$REO" default="compile">

	<property name="src" value="src"/>
	<property name="bin" value="bin"/>
	<property name="lib" value="lib"/>
	<property name="main_reo" value="$MAIN_PACKAGE.$MAIN_CLASS_REO"/>
	<property name="main_ca" value="$MAIN_PACKAGE.$MAIN_CLASS_CA"/>

	<path id="classpath">
		<pathelement location="\${bin}"/>
		<fileset dir="\${lib}">
			<include name="*.jar"/>
		</fileset>
	</path>

	<target name="clean" description="Remove intermediate files.">
		<delete dir="\${bin}"/>
	</target>
	
	<target name="compile" description="Compile the Java source code to class files.">
		<mkdir dir="bin"/>
		<javac srcdir="\${src}" destdir="\${bin}" classpathref="classpath"/>
	</target>

	<target name="test_reo" depends="compile" description="Run the Reo test program.">
		<java classname="\${main_reo}" classpathref="classpath"/>
	</target>

	<target name="test_ca" depends="compile" description="Run the CA test program.">
		<java classname="\${main_ca}" classpathref="classpath"/>
	</target>
			
	<target name="test" depends="test_reo" />
	
	<target name="jar" depends="compile" description="Create a JAR file for the application.">
		<jar destfile="$REO.jar">
			<fileset dir="classes" includes="**/*.class"/>
			<manifest>
				<attribute name="Main-Class" value="\${main_reo}"/>
			</manifest>
		</jar>
	</target>
	
</project>
EOF


# Run Ant.

cd $REO

echo
echo "Running 'ant compile'..."
echo
ant compile

echo
echo "Running 'ant test'..."
echo
ant test

echo
echo "Running 'ant clean'..."
echo
ant clean

cd - > /dev/null

echo "done."
