package debug1;

import java.util.ArrayList;

public class StandardDeviation {
    /***
     * Computes the standard deviation of a population
     */
    public static double standardDeviation(ArrayList<Double> dataSet) {
        if (dataSet.isEmpty()){
            return 0.0;
        }return Math.sqrt(variance(dataSet));
    }

    public static double variance(ArrayList<Double> dataSet) {
        double mean = mean(dataSet);
        double total = 0.0;
        for (double number : dataSet) {
            total += (number-mean) * (number-mean);
        }
        return total / dataSet.size();
    }

    public static double mean(ArrayList<Double> dataSet) {
        double sum = 0.0;
        for (double number : dataSet) {
            sum += number;
        }
        return sum / dataSet.size();
    }
}
