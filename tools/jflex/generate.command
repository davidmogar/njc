#!/bin/bash
cd $(dirname $0)
java -cp JFlex.jar JFlex.Main -d ../../src/com/davidmogar/njc/lexicon ../../src/com/davidmogar/njc/lexicon/lexicon.flex