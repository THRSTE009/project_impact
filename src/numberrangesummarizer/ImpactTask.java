package numberrangesummarizer;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

//MY IMPLEMENTATIONS
public class ImpactTask implements NumberRangeSummarizer {

    public Collection<Integer> collect(String input) {
       //Split string input, based on commas and spaces, into a List of type String.
       List<String> stringList = new ArrayList<String>(Arrays.asList(input.split("\\s*,\\s*")));
       
       //Convert String list to Int List
       List<Integer> integersList = convertStringListToIntList(
               stringList,
             Integer::parseInt);

       Collections.sort(integersList);  // to ensure an ascending order of numbers.
       return integersList;
    }

    public <T, U> List<U>
     convertStringListToIntList(List<T> listOfString,
                                Function<T, U> function)
     {
         return listOfString.stream()
             .map(function)
             .collect(Collectors.toList());
     }


   public String summarizeCollection(Collection<Integer> input) {
    List<Integer> myList = Arrays.stream(
            input.stream()
                    .mapToInt(i->i)
                    .toArray()
    ).boxed().collect(Collectors.toList());

      int current, last, first = 0;
//      int n = myList.length; // commented to save memory.
      boolean cons = false;
      String delimList = "";
      
      //Iterate through input list of numbers. If sequential create a range, else move on to the next number.
      for (int i = 0; i < myList.size() ; i++) {
         current = myList.get(i);
         // if myList has next item
         if(i+1 < myList.size() ) {
         // if next number is consecutive
            if(current+1 == myList.get(i+1)) {
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
      return delimList;
   }
   
   public static void main(String[] args) {
   }
}