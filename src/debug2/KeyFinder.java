package debug2;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyFinder {

    /***
     * Returns all the keys that map to the given value in either of the input HashMaps. The values will be returned
     * sorted by their length with ties broken alphabetically, ignoring case
     */
    public static ArrayList<String> findKeys(HashMap<String, Integer> things1, HashMap<String, Integer> things2, int targetValue) {
        //HashMap<String, Integer> things = new HashMap<>();
        //things.putAll(things1);
        //things.putAll(things2);
        ArrayList<String> foundKeys = new ArrayList<>();
        for (String key : things1.keySet()) {
            if (!foundKeys.contains(key)){
            int value = things1.get(key);
            if (value == targetValue) {
                foundKeys.add(key);
            }
            }
        }
        for (String key : things2.keySet()) {
            if (!foundKeys.contains(key)){
            int value = things2.get(key);
            if (value == targetValue) {
                foundKeys.add(key);
            }
            }
        }
        return InsertionSort.sort(foundKeys, new StringComparator());
    }
}
