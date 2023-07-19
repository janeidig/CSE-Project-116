package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestClasses2 {

    //ratings.Song test
    @Test
    public void testBayesianAverageRating() {
        //no ratings or extra ratings
        Song song= new Song ("Test","A","123");
        double retVal = song.bayesianAverageRating(0,0);
        assertEquals(0.0,retVal,0.001);

        //no extra ratings
        Song song2= new Song ("Test","A","123");
        song2.addRating(new Rating ("Me",4));
        song2.addRating(new Rating("You",5));
        double retVal2 = song2.bayesianAverageRating(0,0);
        assertEquals(4.5,retVal2,0.001);

        //ratings lower than current average
        Song song3= new Song ("Test","A","123");
        song3.addRating(new Rating ("Me",4));
        song3.addRating(new Rating("You",5));
        double retVal3 = song3.bayesianAverageRating(2,3);
        assertEquals(3.75,retVal3,0.001);

        //ratings higher than current average
        Song song4= new Song ("Test","A","123");
        song4.addRating(new Rating ("Me",1));
        song4.addRating(new Rating("You2",1));
        double retVal4 = song4.bayesianAverageRating(2,4);
        assertEquals(2.5,retVal4,0.001);

        //no ratings of song
        Song song5= new Song ("Test","A","123");
        double retVal5 = song5.bayesianAverageRating(2,4);
        assertEquals(4.0,retVal5,0.001);
    }

    //ratings.Movie test
    @Test
    public void testMovieClassConstructor() {
        //testing constructor, getTitle, getCast
        ArrayList<String> cast = new ArrayList<>();
        cast.add("Me");
        cast.add("You");
        cast.add("Shark");
        Movie movie = new Movie("Jaws", cast);
        Rating jawsRating = new Rating("Me", 5);
        movie.addRating(jawsRating);

        ArrayList<String> expectedCast = new ArrayList<>();
        expectedCast.add("Me");
        expectedCast.add("You");
        expectedCast.add("Shark");

        assertTrue(movie.getTitle().equalsIgnoreCase("Jaws"));
        for (int i = 0; i < expectedCast.size(); i++) {
            assertTrue(expectedCast.get(i).equalsIgnoreCase(cast.get(i)));
        }

        //different cases
        ArrayList<String> casesCast = new ArrayList<>();
        casesCast.add("Me");
        casesCast.add("you");
        casesCast.add("Shark");

        assertTrue(movie.getTitle().equalsIgnoreCase("Jaws"));
        for (int i = 0; i < casesCast.size(); i++) {
            assertTrue(casesCast.get(i).equalsIgnoreCase(cast.get(i)));
        }

        //testing for same name in cast
        ArrayList<String> sameCast = new ArrayList<>();
        sameCast.add("Me");
        sameCast.add("You");
        sameCast.add("Shark");
        sameCast.add("Me 2");
        Movie sameMovie = new Movie("Jaws2",sameCast);
        ArrayList<String> theCast=sameMovie.getCast();
        assertEquals(sameCast.size(),theCast.size());
        for (int x = 0; x <sameCast.size();x++){
            for(int y = x+1;y<sameCast.size();y++){
                assertFalse(sameCast.get(x).equalsIgnoreCase(sameCast.get(y)));
            }
        }

        ArrayList<String> supportCast = new ArrayList<>();
        supportCast.add("Me");
        supportCast.add("You");
        supportCast.add("Shark");
        Movie movieFinale = new Movie("Ending",supportCast);
        assertTrue(supportCast.contains("Me"));
        assertTrue(supportCast.contains("You"));
        for (int x = 0; x <supportCast.size();x++){
                assertTrue(supportCast.get(x).equalsIgnoreCase(movieFinale.getCast().get(x)));}

        ArrayList<String> orderCast=new ArrayList<>();
        orderCast.add("Bob");orderCast.add("Carl");orderCast.add("Cindy");
        ArrayList<String> wrongOrder=new ArrayList<>();
        wrongOrder.add("Carl");wrongOrder.add("Cindy");wrongOrder.add("Bob");wrongOrder.add("Bob");
        Movie order=new Movie("Order",orderCast);
        assertNotEquals(order.getCast(),wrongOrder);

        ArrayList<String> multipleCast=new ArrayList<>();
        multipleCast.add("Carl Who");multipleCast.add("Cindy Who");multipleCast.add("Bob Who");
        ArrayList<String> incorrectCast=new ArrayList<>();
        incorrectCast.add("Carl Who");incorrectCast.add("Cindy Who");incorrectCast.add("Bob Who");
        Movie last=new Movie("Order",multipleCast);
        assertEquals(last.getCast(),incorrectCast);
    }

    @Test
    public void testAddMovieRatingAndAverage(){
        //testing addRating
        //single rating
        ArrayList<String> cast = new ArrayList<>();
        cast.add("Me");cast.add("You");cast.add("Shark");
        Movie movie3= new Movie ("Jaws",cast);
        Rating rating=new Rating("Jordan",5);
        LinkedListNode<Rating> expected=new LinkedListNode<>(rating,null);

        movie3.addRating(rating);
        assertEquals(1,movie3.getRatings().size());

        //multiple ratings
        Movie jawsmovie= new Movie ("Jaws2",cast);
        jawsmovie.addRating(new Rating("You",5));
        jawsmovie.addRating(new Rating("Her",4));
        assertEquals(2,jawsmovie.getRatings().size());

        // bayesianAverage

        //no movie ratings or extra ratings
        Movie movie= new Movie ("Jaws",cast);
        double movieAvg = movie.bayesianAverageRating(0,0);
        assertEquals(0.0,movieAvg,0.001);

        //no extra movie ratings
        Movie movie4= new Movie ("Jaws2",cast);
        movie4.addRating(new Rating ("Me",4));
        movie4.addRating(new Rating("You",5));
        double movieAvg2 = movie4.bayesianAverageRating(0,0);
        assertEquals(4.5,movieAvg2,0.001);

        //ratings lower than current average
        Movie movie5= new Movie ("Jaws3",cast);
        movie5.addRating(new Rating ("Me",4));
        movie5.addRating(new Rating("You",3));
        movie5.addRating(new Rating("You2",4));
        double retVal3 = movie5.bayesianAverageRating(2,2);
        //assertEquals(3.0,retVal3,0.001);

        //ratings higher than current average
        Movie movie6= new Movie ("Jaws4",cast);
        movie6.addRating(new Rating ("Me",2));
        movie6.addRating(new Rating("You",2));
        double retVal4 = movie6.bayesianAverageRating(2,4);
        assertEquals(3.0,retVal4,0.001);

        //no ratings of song
        Movie movie7= new Movie ("Jaws5",cast);
        double retVal5 = movie7.bayesianAverageRating(2,4);
        assertEquals(4.0,retVal5,0.001);
    }

    @Test
    public void testSongTitleComparator(){
        //first song before second
        SongTitleComparator compare1= new SongTitleComparator();
        Song song1=new Song("Alive","Me","1");
        Song song2=new Song("Not Alive","You","2");

        assertTrue(compare1.compare(song1, song2));

        //first song not before second
        SongTitleComparator compare2= new SongTitleComparator();
        Song song3=new Song("zzz","Sleepy","1");
        Song song4=new Song("abc","Tune","2");

        assertTrue(!compare2.compare(song3,song4));

        //prefix
        SongTitleComparator compare3= new SongTitleComparator();
        Song song5=new Song("aa","Me","1");
        Song song6=new Song("aaa","You","2");

        assertTrue(compare3.compare(song5,song6));

        //Case
        SongTitleComparator compare0= new SongTitleComparator();
        Song song0=new Song("A","Me","1");
        Song song00=new Song("a","You","2");

        assertTrue(!compare0.compare(song0,song00));

        //no title given for first and no title given for second
        SongTitleComparator compare4= new SongTitleComparator();
        Song song7=new Song("","Me","1");
        Song song8=new Song("Blank","You","2");

        assertTrue(compare4.compare(song7,song8));

        SongTitleComparator compare5= new SongTitleComparator();
        Song song9=new Song("Blank","Me","1");
        Song song10=new Song("","You","2");

        assertTrue(!compare5.compare(song9,song10));

        //title has numbers
        SongTitleComparator compare6= new SongTitleComparator();
        Song song11=new Song("1Allen","Me","1");
        Song song12=new Song("Allen","You","2");

        assertTrue(compare6.compare(song11,song12));

        SongTitleComparator compare7= new SongTitleComparator();
        Song song13=new Song("Allen","Me","1");
        Song song14=new Song("1Allen","You","2");

        assertTrue(!compare7.compare(song13,song14));

        SongTitleComparator compare8= new SongTitleComparator();
        Song song15=new Song("Allen1","Me","1");
        Song song16=new Song("Allen","You","2");

        assertTrue(!compare8.compare(song15,song16));

        SongTitleComparator compare9= new SongTitleComparator();
        Song song17=new Song("Allen","Me","1");
        Song song18=new Song("Allen1","You","2");

        assertTrue(compare9.compare(song17,song18));
    }

    @Test
    public void testSongBayesianRatingComparator() {
        SongBayesianRatingComparator compare1 = new SongBayesianRatingComparator();
        //no ratings
        Song song11 = new Song("First Song", "Artist 1", "1");
        Song song12 = new Song("Second Song", "Artist 2", "2");

        assertFalse(compare1.compare(song11, song12));


        //first song has no ratings
        Song song13 = new Song("First Song", "Artist 1", "1");
        Song song14 = new Song("Second Song", "Artist 2", "2");
        song14.addRating(new Rating("Me", 4));
        song14.addRating(new Rating("You", 5));

        assertFalse(compare1.compare(song13, song14));

        //second song has no ratings
        Song song15 = new Song("First Song", "Artist 1", "1");
        song15.addRating(new Rating("Me", 5));
        song15.addRating(new Rating("You", 4));
        Song song16 = new Song("Second Song", "Artist 2", "2");

        assertTrue(compare1.compare(song15, song16));

        //both songs have same rating
        Song song17 = new Song("First Song", "Artist 1", "1");
        song17.addRating(new Rating("Me", 5));
        song17.addRating(new Rating("You", 4));
        Song song18 = new Song("Second Song", "Artist 2", "2");
        song18.addRating(new Rating("Me", 4));
        song18.addRating(new Rating("You", 5));

        assertFalse(compare1.compare(song17, song18));

        //first song has higher rating
        Song song19 = new Song("First Song", "Artist 1", "1");
        song19.addRating(new Rating("Me", 5));
        song19.addRating(new Rating("You", 4));
        Song song20 = new Song("Second Song", "Artist 2", "2");
        song20.addRating(new Rating("Me", 2));
        song20.addRating(new Rating("You", 3));

        assertTrue(compare1.compare(song19, song20));

        //second song has higher rating
        Song song21 = new Song("First Song", "Artist 1", "1");
        song21.addRating(new Rating("Me", 1));
        song21.addRating(new Rating("You", 1));
        Song song22 = new Song("Second Song", "Artist 2", "2");
        song22.addRating(new Rating("Me", 2));
        song22.addRating(new Rating("You", 3));

        assertFalse(compare1.compare(song21, song22));


        Song song23 = new Song("Average", "Me", "1");
        song23.addRating(new Rating("Me",5));
        song23.addRating(new Rating("Me2",4));
        Song song24=new Song("Average2","Me2","2");
        song24.addRating(new Rating("Me",4));
        song24.addRating(new Rating("Me2",4));

        SongBayesianRatingComparator comparator2= new SongBayesianRatingComparator();
        boolean result1=comparator2.compare(song23,song24);
        song24.addRating(new Rating("Me3",5));
        boolean result2=comparator2.compare(song23,song24);

        assertTrue(result1);
        assertFalse(result2);

    }

    }