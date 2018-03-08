#!/bin/bash

java Driver tests/test1.toy &> results/test1.out
java Driver tests/test2.toy &> results/test2.out
java Driver tests/test3.toy &> results/test3.out
java Driver tests/test4.toy &> results/test4.out

printf "Tests completed...See /results/ directory\n"