package tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static debug1.StandardDeviation.standardDeviation;
import static org.junit.Assert.assertEquals;

public class TestDebug1 {

    @Test
    public void testStandardDeviationNoDecimal(){
        ArrayList<Double> testList = new ArrayList<>(Arrays.asList(10.00,11.00,12.00,13.00,15.00));
        Double expected = 1.720465;
        Double actual = standardDeviation(testList);
        assertEquals(expected,actual,.001);
    }
    @Test
    public void testStandardDeviationNoValues(){
        ArrayList<Double> testList = new ArrayList<>();
        Double expected = 0.0;
        Double actual = standardDeviation(testList);
        assertEquals(expected,actual,.001);
    }

    @Test
    public void testStandardDeviationDecimals(){
        ArrayList<Double> testList = new ArrayList<>(Arrays.asList(10.10,11.11,12.12,13.13,15.15));
        Double expected = 1.73766;
        Double actual = standardDeviation(testList);
        assertEquals(expected,actual,.001);
    }

    @Test
    public void testStandardDeviationZeroIncluded(){
        ArrayList<Double> testList = new ArrayList<>(Arrays.asList(00.00,10.00,20.00,30.00));
        Double expected = 11.180339;
        Double actual = standardDeviation(testList);
        assertEquals(expected,actual,.001);
    }

    @Test
    public void testStandardDeviationLargeNumbers(){
        ArrayList<Double> testList = new ArrayList<>(Arrays.asList(5000.00,6000.00,2000.00,3000.00));
        Double expected = 1581.1388;
        Double actual = standardDeviation(testList);
        assertEquals(expected,actual,.001);
    }
}
