Instructions:
-Implement code which has the ability to produce a comma delimited list of numbers, grouping the numbers into a range when they are sequential.
-Provided for you is an interface that you should implement as a solution to the exercise.

Requirements:
- Must be Java code. Minimum Java version 8. If you are not familiar with the changes in Java 8, you will need to learn them for this exercise.
- Must be in GitHub.
- Must implement provided interface
- Must have valid unit tests

The review will be based on: 
- your ability to understand / interpret a requirement.
- your ability to learn quickly (learning changes in Java 8)
- your coding ability.
- optimisation of your code.
- your ability to compile a structured solution.
- present your solution with valid unit tests.

***************
My Assumptions:
- Sample input is a single string that includes numbers separated by a comma.
- The input numbers are only Integers.
- Input string may contain duplicate numbers.
- Expected result is an ascending ordered list.
- No implicit sequential patterns are expected.
- No explicity sequential patterns using formulas are expected.
- Numbers will be grouped into a range when they are in an n+1 sequential pattern where n is the first number in the sequence and n+1 is the second. This sequential range continues until broken or the end of the string is reached.
-Final result is a single string that includes numbers separated by a comma. The numbers are grouped into a range when they are sequential. (Assumption based on the String return type used for NumberRangeSummarizer's summarizeCollection method as well as the sample result given in the interface.)
