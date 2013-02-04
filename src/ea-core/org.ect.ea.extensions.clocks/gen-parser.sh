#!/bin/bash

ANTLR=libs/antlr-2.7.5.jar
TARGET=src/cwi/ea/extensions/clocks/parsers
GRAMMAR=$TARGET/clocks.g

java -classpath $ANTLR antlr.Tool -o $TARGET $GRAMMAR
