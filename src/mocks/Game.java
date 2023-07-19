package mocks;

import tests.Database;

public class Game {
    private Database db;

    public Game(Database db){
        this.db = db;
    }

    /**
     * Adds points to a players total score
     */
    public void scorePoints(String player, int points){
        int currentScore = this.db.getPlayerScore(player);
        int newScore = currentScore + points;
        this.db.recordScore(player, newScore);
    }

    /**
     * Return the player with the highest score. If multiple players have the highest score, any one of them
     * will be returned
     */
    public String getWinner(){
        String winner = "";
        int highScore = Integer.MIN_VALUE;
        for(String player : this.db.getPlayerList()){
            int score = this.db.getPlayerScore(player);
            if(score > highScore){
                winner = player;
                highScore = score;
            }
        }
        return winner;
    }

    public Database getDb(){
        return this.db;
    }
}
