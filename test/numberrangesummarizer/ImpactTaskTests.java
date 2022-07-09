package numberrangesummarizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test naming convention: [MethodUnderTest]_[Scenario]_[ExpectedResult]

public class ImpactTaskTests {
    ImpactTask impactTask;

    @BeforeEach
    void setUp() {
        impactTask = new ImpactTask();  // Create an object of the ImpactTask class.
    }

    @Test
    void collect_wordAsInput_expectNumberFormatException() {
        String input = "One";
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
        System.out.println("Test: input value of \"one\" should throw a NumberFormatException.");
    }

    @Test
    void collect_MixedInput_expectNumberFormatException() {
        String input = "1,b,3,5,c,7";
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.collect(input);
        }, "NumberFormatException was expected");
        Assertions.assertEquals("For input string: \"b\"", thrown.getMessage());
        System.out.println("Test: input value of \"b\" should throw a NumberFormatException.");   //Fails first on 'b'.
    }

    @Test
    void summarizeCollection_inputSingleNumber_expectInput() {
        String input = "1";
        String output = "1";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result. ");
        System.out.println("Test: Only one value expected as the result. " + a);
    }

    @Test
    void summarizeCollection_GivenSampleTest_expectGivenResult() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String output ="1, 3, 6-8, 12-15, 21-24, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Returns the expected result of the given sample test: " + a);
    }

    @Test
    void summarizeCollection_noConsecutiveNoDuplicates_expectNoSequentialRanges() {
        String input = "1,3,6,8,12,14,21,23,31";
        String output ="1, 3, 6, 8, 12, 14, 21, 23, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: No consecutive and no duplicate numbers: " + a);
    }

    @Test
    void summarizeCollection_duplicateInputOnly_expectNoSequentialRanges() {
        String input = "1,1,1,3,3,3";
        String output ="1, 1, 1, 3, 3, 3";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Duplicates only test of ordered numbers: " + a);
    }

    @Test
    void summarizeCollection_simpleConsecutiveAndDuplicateInputs_expectRangesAndNumbers() {
        String input = "1,2,3,3,5,6,7";
        String output ="1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Simple consecutive and duplicate numbers: " + a);
    }

    @Test
    void summarizeCollection_complexConsecutiveAndDuplicatesInputs_expectRangesAndNumbers() {
        String input = "0,1,1,1,2,3,3,5,6,7";
        String output ="0-1, 1, 1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Complex consecutive and duplicate numbers: " + a);
    }

    @Test
    void summarizeCollection_consecutiveNegativeNumbers_expectNegativeRangesAndNumbers() {
        String input = "-10,-9,-8,-6,-4,-2,-1,0";
        String output ="-10--8, -6, -4, -2-0";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Consecutive and negative numbers: " + a);
    }

    @Test
    void summarizeCollection_inputOrderDescending_expectAscendingOutput() {
        String input = "10,9,8,6,4,2,1,0";
        String output = "0-2, 4, 6, 8-10";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test: Input given as a descending ordered string of numbers. " + a);
    }
}
