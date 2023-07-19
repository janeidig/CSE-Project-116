package tests;

import java.util.ArrayList;

public interface Database {
    /**
     * Stores the score for a player. If the player already has it score, it is overwritten with this new value
     */
    void recordScore(String player, int score);

    /**
     * Returns the score for the given player
     */
    int getPlayerScore(String name);

    /**
     * Returns a list of a players who have a recorded score
     */
    ArrayList<String> getPlayerList();
}
