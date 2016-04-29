# grad-cs230-project
Class Project for CS230

The description of the project is in docs/CS230ProjectProposal.pdf

## Dependencies
FindBugs 3.0.1

## Environment
FINDBUGS_HOME = Root directory of FindBugs

## Example Test Run
There are two ways to use the provided test script.

####Specific java file:####
The following will run FindBugs on code/Sample/Sample.java
```bash
py test.py code/Sample/Sample.java
```

####All java files under target directory:####
The following will run FindBugs on all java files recursively found underneath code/
```bash
py test.py code
```
