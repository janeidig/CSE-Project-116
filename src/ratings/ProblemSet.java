package ratings;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSet {

    // TODO: Implement this method to return the average of all the numbers in the input ArrayList
    //       If the ArrayList is empty, return 0.0
    //
    // Examples
    // [1.0,2.0,3.0] returns 2.0
    // [-5.0,5.0] returns 0.0
    // [6.5,6.5,8.5,8.5] returns 7.5
    // [] returns 0.0

    public static double average(ArrayList<Double> numbers) {
        double value = 0;
        if (numbers.isEmpty()) {
            return 0.0;
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                value += numbers.get(i);
            }
            return value / numbers.size();
        }
    }

    // TODO: Write a public static method named sumOfDigits that takes an int as a parameter and
    //       returns the sum of the digits of the input as an int
    //
    // Examples
    // 123 returns 6
    // 57 returns 12
    // -36 returns 9

    public static int sumOfDigits(int num) {
        int sum = 0;
        if(num==0) {
         return 0;
        }else{
        while (num != 0) {
            sum += num % 10;
            num /= 10;}
        return Math.abs(sum);
    }}

    // TODO: Write a public static method named bestKey that takes a HashMap of String to Integer
    //       as a parameter and returns a key mapping to the largest Integer. Ties can be broken arbitrarily.
    //       If the HashMap is empty, return the empty String
    //
    // Examples
    // {"CSE": 100, "MTH": 90, "MGT": 10} returns "CSE"
    // {"cat": 5, "dog": 5, "fox": 4} can return either "cat" or "dog"
    // {} returns ""

    public static String bestKey(HashMap<String,Integer> map){
        if (map.isEmpty()){
            return "";
        }else{
             String retVal=null;
             for (String key: map.keySet()){
                 if(retVal == null || map.get(key)>map.get(retVal)){
                     retVal=key;
                 }
             }return retVal;
        }
    }
}
