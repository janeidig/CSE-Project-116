package debug2;

import java.util.ArrayList;

public class InsertionSort {

    public static <T> ArrayList<T> sort(ArrayList<T> input, Comparator<T> comparator) {
        ArrayList<T> output = new ArrayList<>();
        for (T valueToInsert : input) {
            int location = 0;
            for (T valueToCompare : output) {
                if (comparator.compare(valueToCompare, valueToInsert)) {
                    location ++;
                }
            }output.add(location, valueToInsert);
        }
        return output;
    }
}
