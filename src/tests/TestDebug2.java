package tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static debug2.KeyFinder.findKeys;
import static org.junit.Assert.assertTrue;

public class TestDebug2 {

    @Test
    public void empty(){
        HashMap<String, Integer> hash1 = new HashMap<>();
        HashMap<String, Integer> hash2 = new HashMap<>();
        ArrayList<String> expected = new ArrayList<>(Arrays.asList());
        ArrayList<String> actual = findKeys(hash1,hash2,4);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void multipleSameNames(){
        HashMap<String, Integer> hash1 = new HashMap<>();hash1.put("Jacob",2);hash1.put("Bob",2);hash1.put("Lisa",2);
        HashMap<String, Integer> hash2 = new HashMap<>();hash2.put("Jacob",2);hash2.put("Tyler",1);hash2.put("Joseph",2);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Bob","Lisa","Jacob","Joseph"));
        ArrayList<String> actual = findKeys(hash1,hash2,2);
        for (int x = 0; x< actual.size();x++){
            assertTrue(actual.get(x).equalsIgnoreCase(expected.get(x)));
        }
    }

    @Test
    public void sameNameDifferentValue(){
        HashMap<String, Integer> hash1 = new HashMap<>();hash1.put("Jacob",2);hash1.put("Bob",2);hash1.put("Lisa",2);
        HashMap<String, Integer> hash2 = new HashMap<>();hash2.put("Jacob",5);hash2.put("Tyler",1);hash2.put("Joseph",2);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Bob","Lisa","Jacob","Joseph"));
        ArrayList<String> actual = findKeys(hash1,hash2,2);
        for (int x = 0; x< actual.size();x++){
            //System.out.println(actual.get(x));
            //System.out.println(expected.get(x));
            assertTrue(actual.get(x).equalsIgnoreCase(expected.get(x)));
        }
    }

    @Test
    public void onlyOneHashMap(){
        HashMap<String, Integer> hash1 = new HashMap<>();hash1.put("Jacob",2);hash1.put("Bob",2);hash1.put("Lisa",2);
        HashMap<String, Integer> hash2 = new HashMap<>();
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Bob","Lisa","Jacob"));
        ArrayList<String> actual = findKeys(hash1,hash2,2);
        for (int x = 0; x< actual.size();x++){
            assertTrue(actual.get(x).equalsIgnoreCase(expected.get(x)));
        }
    }

    @Test
    public void tieNames(){
        HashMap<String, Integer> hash1 = new HashMap<>();hash1.put("Jacob",2);hash1.put("Jared",2);hash1.put("Lisa",2);
        HashMap<String, Integer> hash2 = new HashMap<>();hash2.put("Lisa",5);hash2.put("Tyler",1);hash2.put("Akon",2);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Akon","Lisa","Jacob","Jared"));
        ArrayList<String> actual = findKeys(hash1,hash2,2);
        for (int x = 0; x< actual.size();x++){
            //System.out.println(actual.get(x));
            //System.out.println(expected.get(x));
            assertTrue(actual.get(x).equalsIgnoreCase(expected.get(x)));
        }
    }
}
