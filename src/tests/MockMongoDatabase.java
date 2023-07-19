package tests;

import java.util.ArrayList;
import java.util.HashMap;

public class MockMongoDatabase implements Database{
    private HashMap<String, Integer> players= new HashMap<>();

    public void recordScore(String player, int score){
        this.players.put(player,score);
    }

    public int getPlayerScore(String player){
        if (this.players.containsKey(player)){
            return (this.players.get(player));
        }return 0;
    }

    public ArrayList<String> getPlayerList(){
        ArrayList<String> retVal = new ArrayList<>();
        for (String player : this.players.keySet()){
            retVal.add(player);
        }return retVal;
    }
}
