package numberrangesummarizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Steven Theron
 */

// Test naming convention: [MethodUnderTest]_[Scenario]_[ExpectedResult]

public class ImpactTaskTests {
    ImpactTask impactTask;

    @BeforeEach
    void setUp() {
        impactTask = new ImpactTask();  // Create an object of the ImpactTask class.
    }

    @Test
    void collect_emptyInput_expectNumberFormatException() {
        String input = "";
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \""+input+"\"", thrown.getMessage());
        System.out.println("Test: Empty string expected to fail when collect() tries to parse it to an Integer." +
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n");
    }

    @Test
    void collect_wordAsInput_expectNumberFormatException() {
        String input = "One";

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \""+input+"\"", thrown.getMessage());
        System.out.println("Test: Non-delimited string includes only letters." +
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n");
    }

    @Test
    void collect_mixedInput_expectNumberFormatException() {
        String input = "1,b,3,5,c,7";

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \"b\"", thrown.getMessage());
        System.out.println("Test: Comma delimited string includes letters and numbers." +
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n"); // Fails first on 'b'.
    }

    @Test
    void collect_inputDecimals_expectNumberFormatException() {
        String input = "0.0,1.0,1.1,1.0,2.0,3.0,3.1,5.0,6.0,7.0";

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \"0.0\"", thrown.getMessage());
        System.out.println("Test: Includes decimals in a comma delimited string " +
                "which contrasts my assumption that only Integer numbers are included in the input string." +
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n");
    }

    @Test
    void collect_inputFullstops_expectNumberFormatException() {
        String input = "0.1.2.3.4.5.6.7.0";

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \""+input+"\"", thrown.getMessage());
        System.out.println("Test: Includes a full stop regex which is not accounted for in the ImpactTask class " +
                "since it could be used to indicate decimal numbers." +
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n");
    }

    @Test
    void collect_allowedInputSeparators_expectCommaSeparator() {
        String input = "1,3 5?7@11!12#16^18&20*22$25";
        List<Integer> output = Arrays.asList(1,3,5,7,11,12,16,18,20,22,25);

        Collection<Integer> a = impactTask.collect(input);
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Sample Input includes regular expression patterns of the following: \"[, ?@!#^&*$]\". Result expected as a list of integers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void collect_notAllowedInputSeparators_expectCommaSeparator() {
        String input = "1-3.5%6";

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        assertEquals("For input string: \""+input+"\"", thrown.getMessage());
        System.out.println("Test: Includes regular expression patterns that are not allowed. E.g.: \"[-.%]\""+
                "\nSample Input: \""+input+"\"\nResult: "+thrown.getClass() +" "+ thrown.getMessage()+"\n");
    }

    @Test
    void summarizeCollection_inputSingleNumber_expectInput() {
        String input = "1";
        String output = "1";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should not be comma delimited nor should it display a range."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_givenSampleTest_expectGivenResult() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String output ="1, 3, 6-8, 12-15, 21-24, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return the expected result of Werner's sample test."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_noConsecutiveNoDuplicates_expectNoSequentialRanges() {
        String input = "1,3,6,8,12,14,21,23,31";
        String output ="1, 3, 6, 8, 12, 14, 21, 23, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return no consecutive and no duplicate numbers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_duplicateInputOnly_expectNoSequentialRanges() {
        String input = "1,1,1,3,3,3";
        String output ="1, 1, 1, 3, 3, 3";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return only duplicates not in a range of numbers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_simpleConsecutiveAndDuplicateInputs_expectRangesAndNumbers() {
        String input = "1,2,3,3,5,6,7";
        String output ="1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return duplicates numbers and consecutive in a range of numbers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_complexConsecutiveAndDuplicatesInputs_expectRangesAndNumbers() {
        String input = "0,1,1,1,2,3,3,5,6,7";
        String output = "0-1, 1, 1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return duplicates numbers and consecutive in a range of numbers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_consecutiveNegativeNumbers_expectNegativeRangesAndNumbers() {
        String input = "-10,-9,-8,-6,-4,-2,-1,0";
        String output ="-10--8, -6, -4, -2-0";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return negative consecutive numbers in a range."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }

    @Test
    void summarizeCollection_inputOrderDescending_expectAscendingOutput() {
        String input = "10,9,8,6,4,2,1,0";
        String output = "0-2, 4, 6, 8-10";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result.");
        System.out.println("Test: Result should return sorted list of numbers."+
                "\nSample Input: \""+input+"\"\nResult: "+a+"\n");
    }
}
