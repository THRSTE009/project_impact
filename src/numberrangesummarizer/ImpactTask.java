package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.*;
import java.util.function.*;

/* 
https://www.javatpoint.com/java-collection-toarray-method
https://www.geeksforgeeks.org/stream-in-java/
https://www.geeksforgeeks.org/program-to-convert-list-of-string-to-list-of-integer-in-java/
https://www.geeksforgeeks.org/initializing-a-list-in-java/
*/

//MY IMPLEMENTATIONS
public class ImpactTask implements NumberRangeSummarizer {

    public Collection<Integer> collect(String input) {
       //Split string input, based on commas and spaces, into a List of type String/.
       List<String> listOfString = new ArrayList<String>(Arrays.asList(input.split("\\s*,\\s*")));
       
       //Convert String list to Int List
       List<Integer> listOfIntegers = convertStringListToIntList(
             listOfString,
             Integer::parseInt);

       return listOfIntegers;
    }

    public static <T, U> List<U>
     convertStringListToIntList(List<T> listOfString,
                                Function<T, U> function)
     {
         return listOfString.stream()
             .map(function)
             .collect(Collectors.toList());
     }

   public String summarizeCollection(Collection<Integer> input) {
      Object[] array = input.toArray();     
      
      int current,last = 0;
      int first = 0;
      int n = array.length;
      boolean cons = false;
      String delimList = "";
      
      //Iterate through input list of numbers. If sequential create a range, else move on to the next number.
      for (int i = 0; i < n; i++) {       
         current = (int)array[i];
         // if array has next item
         if(i+1 < n) { 
         // if next number is consecutive
            if(current+1 == (int)array[i+1]) { 
               if(cons == false) {
                  cons = true;
                  first = current;
               }           
            }
            //next number is not consecutive but previous number was.     
            else if (cons == true){    
               cons = false;
               last = current;
               delimList += first + "-" + last + ", ";
            }
            //the current number is not consecutive with previous number or the next number.
            else {
               delimList += current + ", ";
            }          
         }
         //last number in the list is consecutive.
         else if(cons == true) {
            cons = false;
            last = current;
            delimList += first + "-" + last;
         }
         //last number in the list is not consecutive.
         else {
            delimList += current;
         }
       }
      // System.out.println("Testing toString of collection:" + input.toString());                   
      return delimList;
   }
   
   public static void main(String[] args) {
   }
}