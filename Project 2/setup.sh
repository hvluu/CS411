#!/bin/bash
printf "Generating lexer from toy.flex\n"
java -jar lib/JFlex.jar toy.flex > dump.no

printf "Generating parser from toy.cup\n"
java java_cup.Main -nowarn -nosummary -expect 50 toy.cup &> dump.no

printf "Compiling Driver.java\n"
javac Driver.java

printf "Usage:\n java Driver testfile\n"
