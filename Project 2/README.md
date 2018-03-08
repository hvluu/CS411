Toy Language Syntax Analyzer

### Compile

```
java -jar lib/JFlex.jar toy.flex > dump.no
java java_cup.Main -nowarn -nosummary -expect 50 toy.cup &> dump.no
javac Driver.java
```

or just run the script

```
sh setup.sh
```

The redirects to ``dump.no`` are used to filter the warning messages and garbage output from stdout (byproduct of altering the CUP files to print shifting). Feel free to remove them. 


## Run Tests

### Via Script

```
sh run.sh
```

### Manually

```
java Driver tests/test1.toy > results/test1.out
java Driver tests/test2.toy > results/test2.out
java Driver tests/test3.toy > results/test3.out
java Driver tests/test4.toy > results/test4.out
```