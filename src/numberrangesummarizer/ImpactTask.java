package numberrangesummarizer;

import java.util.*;
import java.util.stream.*;

//MY IMPLEMENTATIONS
public class ImpactTask implements NumberRangeSummarizer {

    public Collection<Integer> collect(String input) {
        //Split string input, based on commas and spaces, into a List of type Integer.
        List<Integer> integersList = new ArrayList<Integer>(
                Arrays.asList(input.split("\\s*,\\s*"))
                        .stream()
                        .map(s -> Integer.parseInt(s))
                        .collect(Collectors.toList()));

        Collections.sort(integersList);  // to ensure an ascending order of numbers.
        return integersList;
    }

   public String summarizeCollection(Collection<Integer> input) {
       //  converts input to a Stream<Integer> and returns it as a List.
       List<Integer> myList = input.stream().collect(Collectors.toList());

      int current, first = 0;
      boolean isConsecutive = false;
      String delimitedList = "";
      
      //Iterate through input list of numbers. If sequential create a range, else move on to the next number.
      for (int i = 0; i < myList.size(); i++) {
         current = myList.get(i);

         // if myList has next item
         if(i+1 < myList.size()) {
         // if next number is consecutive
            if(current+1 == myList.get(i+1)) {
               if(isConsecutive == false) {
                   isConsecutive = true;
                  first = current;
               }           
            }
            //next number is not consecutive but previous number was.     
            else if (isConsecutive == true) {
                isConsecutive = false;
                delimitedList += first + "-" + current + ", ";   //  "current" becomes "last" number in the range.
            }
            //the current number is not consecutive with previous number or the next number.
            else {
                delimitedList += current + ", ";
            }          
         }

         //last number in the list is consecutive.
         else if(isConsecutive == true) {
             isConsecutive = false;
             delimitedList += first + "-" + current;
         }

         //last number in the list is not consecutive.
         else {
             delimitedList += current;
         }
       }
      return delimitedList;
   }
   
   public static void main(String[] args) {
   }
}