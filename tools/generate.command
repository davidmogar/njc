#!/bin/bash
cd $(dirname $0)
java -cp jflex/JFlex.jar JFlex.Main -d ../src/com/davidmogar/njc/lexicon ../src/com/davidmogar/njc/lexicon/lexicon.flex
./byaccj/yacc.macosx -J -v -Jpackage=com.davidmogar.njc.syntactic -Jsemantic=Object ../src/com/davidmogar/njc/syntactic/syntactic.y
mv Parser.java ../src/com/davidmogar/njc/syntactic
mv y.output byaccj