package tests;

import org.junit.Test;
import ratings.ProblemSet;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

// TODO: Write testing for all 3 methods of the ratings.ProblemSet class
public class TestProblemSet {

    private final double EPSILON = 0.001;

    public void compareDoubles(double d1, double d2){
        assertTrue(Math.abs(d1-d2)<EPSILON);
    }
    @Test
    public void testAverage(){
        ArrayList<Double> al =new ArrayList<Double>(); al.add(1.0);al.add(2.0);al.add(3.0);
        double avg= ProblemSet.average(al);
        compareDoubles(avg,2.0);
        al =new ArrayList<Double>();
        avg= ProblemSet.average(al);
        compareDoubles(avg,0.0);
        al =new ArrayList<Double>();al.add(-5.0);al.add(0.0);al.add(5.0);
        avg= ProblemSet.average(al);
        compareDoubles(avg,0.0);;
        al =new ArrayList<Double>();al.add(6.5);al.add(6.5);al.add(8.5);al.add(8.5);
        avg= ProblemSet.average(al);
        compareDoubles(avg,7.5);;
        al =new ArrayList<Double>();al.add(-6.5);al.add(-6.5);al.add(-8.5);al.add(-8.5);
        avg= ProblemSet.average(al);
        compareDoubles(avg,-7.5);;
        al =new ArrayList<Double>();al.add(0.0);al.add(0.0);al.add(0.0);al.add(0.0);
        avg= ProblemSet.average(al);
        compareDoubles(avg,0.0);
        al =new ArrayList<Double>();al.add(2.5);
        avg= ProblemSet.average(al);
        compareDoubles(avg,2.5);
    }
    @Test
    public void testSumOfDigits(){
        int sum=ProblemSet.sumOfDigits(123);
        assertTrue("123 returns the sum: "+sum,sum==6);
        sum=ProblemSet.sumOfDigits(56);
        assertTrue("56 returns the sum: "+sum,sum==11);
        sum=ProblemSet.sumOfDigits(-456);
        assertTrue("123 returns the sum: "+sum,sum==15);
        sum=ProblemSet.sumOfDigits(0);
        assertTrue("0 returns the sum: "+sum,sum==0);
        sum=ProblemSet.sumOfDigits(-4455);
        assertTrue("045 returns the sum: "+sum,sum==18);
    }
    @Test
    public void testBestKey(){
        HashMap<String, Integer> hash1=new HashMap(); hash1.put("Bills", 10);hash1.put("Steelers", 11);hash1.put("Chiefs", 9);
        String retVal=ProblemSet.bestKey(hash1);
        assertTrue("values 10,11,9 return the key: "+retVal, retVal.equals("Steelers"));
        HashMap<String, Integer> hash2=new HashMap(); hash2.put("cat", 5);hash2.put("dog", 5);hash2.put("fox", 4);
        retVal=ProblemSet.bestKey(hash2);
        assertTrue("values 5,5,4 return the key: "+retVal, retVal.equals("cat") || retVal.equals("dog"));
        HashMap<String, Integer> hash3=new HashMap(); hash3.put("CSE", 100); hash3.put("MTH", 90); hash3.put("MGT", 10);
        retVal=ProblemSet.bestKey(hash3);
        assertTrue("values 100, 90, 10 return the key: "+retVal, retVal.equals("CSE"));
        HashMap<String, Integer> hash4=new HashMap();
        retVal=ProblemSet.bestKey(hash4);
        assertTrue("blank map returns the key: "+retVal, retVal.equals(""));
        HashMap<String, Integer> hash5=new HashMap(); hash5.put("Jim", -10);hash5.put("Bob", 0);hash5.put("Carl", -11);
        retVal=ProblemSet.bestKey(hash5);
        assertTrue("values -10,0,-11 return the key: "+retVal, retVal.equals("Bob"));
        HashMap<String, Integer> hash6=new HashMap(); hash6.put("Jim", -10);hash6.put("Bob", -40);hash6.put("Carl", -11);
        retVal=ProblemSet.bestKey(hash6);
        assertTrue("values -10,-40,-11 return the key: "+retVal, retVal.equals("Jim"));
    }}