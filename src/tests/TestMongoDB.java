package tests;

import mocks.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMongoDB {

    @Test
    public void testPlayerScores(){
        MockMongoDatabase db = new MockMongoDatabase();
        Game game = new Game(db);

        game.scorePoints("Jordan",4);
        game.scorePoints("Lebron",23);
        game.scorePoints("Kobe",8);

        String actual = game.getWinner();
        String expected = "Lebron";

        assertTrue(actual.equalsIgnoreCase(expected));
    }

    @Test
    public void testPlayerSameScores(){
        MockMongoDatabase db = new MockMongoDatabase();
        Game game = new Game(db);

        game.scorePoints("Jordan",4);
        game.scorePoints("Lebron",23);
        game.scorePoints("Kobe",23);

        String actual = game.getWinner();
        String expected = "Lebron";
        String expected2 = "Kobe";


        assertTrue(actual.equalsIgnoreCase(expected) || actual.equalsIgnoreCase(expected2));
    }

    @Test
    public void testConstructor(){
        MockMongoDatabase db = new MockMongoDatabase();
        Game game = new Game(db);

        game.scorePoints("Jordan",4);
        game.scorePoints("Lebron",23);
        game.scorePoints("Kobe",8);

        assertTrue(db==game.getDb());
    }

    @Test
    public void testPointsScored(){
        MockMongoDatabase db = new MockMongoDatabase();
        Game game = new Game(db);

        game.scorePoints("Jordan",4);
        game.scorePoints("Lebron",23);
        game.scorePoints("Kobe",8);
        game.scorePoints("Kobe",2);

        int actual = game.getDb().getPlayerScore("Jordan");
        int actual2 = game.getDb().getPlayerScore("Kobe");

        assertEquals(4,actual,.001);
        assertEquals(10,actual2,.001);
    }
}
