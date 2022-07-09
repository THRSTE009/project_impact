package numberrangesummarizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImpactTaskTest {
    ImpactTask impactTask;

    @BeforeEach
    void setUp() {
        impactTask = new ImpactTask();  // Create an object of the ImpactTask class.
    }

    @Test
    void testExpectedNumberFormatException1() {
        String input = "One";
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.summarizeCollection(impactTask.collect(input));
        }, "NumberFormatException was expected");

        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
        System.out.println("Test 1: input value of \"one\" should throw a NumberFormatException.");
//        System.out.println("Only one value expected as the result. ");
    }

    @Test
    void testExpectedNumberFormatException2() {
        String input = "1,b,3,5,c,7";
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            impactTask.summarizeCollection(impactTask.collect(input));
        }, "NumberFormatException was expected");

        Assertions.assertEquals("For input string: \"b\"", thrown.getMessage());
        System.out.println("Test 2: input value of \"b\" should throw a NumberFormatException.");   //Fails first on 'b'.
    }

    @Test
    void InputSingleNumber() {
        String input = "1";
        String output = "1";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,"Given input has an unexpected result. ");
        System.out.println("Test 3: Only one value expected as the result. " + a);
    }

    @Test
    void InputNoChanges() {
        String input = "10,30,50,70,90";
        String output = "10, 30, 50, 70, 90";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 4: No Consecutive numbers found so the expected result just includes a comma " +
                "and single space between each number: " + a);
    }

    @Test
    void sampleTest() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String output ="1, 3, 6-8, 12-15, 21-24, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 5: Returns the following result using the given sample input : " + a);
    }

    @Test
    void NoConsecutiveNoDuplicates() {
        String input = "1,3,6,8,12,14,21,23,31";
        String output ="1, 3, 6, 8, 12, 14, 21, 23, 31";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 6: No consecutive and no duplicate test of ordered numbers: " + a);
    }

    @Test
    void DuplicatesOnly() {
        String input = "1,1,1,3,3,3";
        String output ="1, 1, 1, 3, 3, 3";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 7: Duplicates only test of ordered numbers: " + a);
    }

    @Test
    void ConsecutiveAndDuplicates1() {
        String input = "1,2,3,3,5,6,7";
        String output ="1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 8: Consecutive and duplicate numbers test 1 of ordered data: " + a);
    }

    @Test
    void ConsecutiveAndDuplicates2() {
        String input = "0,1,1,1,2,3,3,5,6,7";
        String output ="0-1, 1, 1-3, 3, 5-7";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 9: Consecutive and duplicate numbers test 2 of ordered data: " + a);
    }

    @Test
    void ConsecutiveNegativeNumbers() {
        String input = "-10,-9,-8,-6,-4,-2,-1,0";
        String output ="-10--8, -6, -4, -2-0";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 10: Consecutive and negative numbers test of ordered data: " + a);
    }

    @Test
    void IsOrderAscTest() {
        String input = "10,9,8,6,4,2,1,0";
        String output = "0-2, 4, 6, 8-10";

        String a  = impactTask.summarizeCollection(impactTask.collect(input));
        assertEquals(output, a,
                "Given input has an unexpected result. ");
        System.out.println("Test 11: Input given as a descending ordered string of numbers. " +
                "Output is an ascending ordered comma delimited list of numbers." + a);
    }
}
