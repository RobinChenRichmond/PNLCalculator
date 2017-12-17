## README

This program is built in OOD to persue a high cohesion, low coupling coding style.

The program is written in Java 8, and is built based on the assumption:
A prices file with right format will have updates message for all the symbols in the file at each time the timestamp updates. Therefore, each timestamp will appear same number of times in the prices file.

```
To run the program, first open the command tool into the directory, 

compile the program:
javac CalculatorDriver.java

then run the file with path of two input parameters fills and prices:
java CalculatorDriver [path of fills file] [path of prices file]

If no parameters is added:
java CalculatorDriver

the program will by default find files with name “fills” and “prices” in the program folder.
```
