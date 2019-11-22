# Spreedsheet-Calculator
This is intended to calculate the cells with RPN expression as well as cell reference calculations.

To run the code:
input:

3 3

1 -3 *

C3 C1 -

C1 B1 -

A1 B2 +

3 ++

5 2 -

A1 B3 +

4 5 * 2 /

4 --



Result:

-3.00000

3.00000

-1.00000

1.00000

4.00000

3.00000

0.00000

10.00000

3.00000

# to run use command

cat inputTestSheet.txt | java -classpath src/ main.Spreadsheet
