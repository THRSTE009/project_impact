package numberrangesummarizer;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImpactTaskTest {
    ImpactTask impactTask;

    @BeforeEach
    void setUp() {
        impactTask = new ImpactTask();  // Create an object of the ImpactTask class.
    }

//    @BeforeEach //check if method receives a String parameter
//    void collect(Object input) {
//        assertTrue(input instanceof String);
//    }

//    @BeforeEach //check if method receives a Collection parameter.
//    void summarizeCollection() {
//    }

    @Test
    void sampleTest() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String output ="1, 3, 6-8, 12-15, 21-24, 31";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

    @Test
    void NoConsecutiveNoDuplicates() {
        String input = "1,3,6,8,12,14,21,23,31";
        String output ="1, 3, 6, 8, 12, 14, 21, 23, 31";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

    @Test
    void DuplicatesOnly() {
        String input = "1,1,1,3,3,3";
        String output ="1, 1, 1, 3, 3, 3";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

    @Test
    void ConsecutiveAndDuplicates1() {
        String input = "1,2,3,3,5,6,7";
        String output ="1-3, 3, 5-7";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

    @Test
    void ConsecutiveAndDuplicates2() {
        String input = "0,1,1,1,2,3,3,5,6,7";
        String output ="0-1, 1, 1-3, 3, 5-7";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

    @Test
    void ConsecutiveNegativeNumbers() {
        String input = "-10,-9,-8,-6,-4,-2,-1,0";
        String output ="-10--8, -6, -4, -2-0";

        Collection<Integer> listOfIntegers = impactTask.collect(input);
        assertEquals(output, impactTask.summarizeCollection(listOfIntegers),
                "Given input has an unexpected result. ");
        System.out.println(output);
    }

}
