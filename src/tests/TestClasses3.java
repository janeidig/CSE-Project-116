package tests;

import org.junit.Test;
import ratings.*;
import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static ratings.FileReader.readMovieRatings;
import static ratings.FileReader.readMovies;


public class TestClasses3 {

    //readSongs testing
    private void compareSingleSong(Song expected, Song actual){
        assertTrue(expected.getTitle().equalsIgnoreCase(actual.getTitle()));
        assertEquals(expected.getSongID(),actual.getSongID());
        assertTrue(expected.getArtist().equalsIgnoreCase(actual.getArtist()));
        compareSongRatings(expected.getRatings(),actual.getRatings());
        //assertEquals(expected.getRatings().getValue().getRating(),actual.getRatings().getValue().getRating(),.001);
        //assertTrue(expected.getRatings().getValue().getReviewerID().equalsIgnoreCase(actual.getRatings().getValue().getReviewerID()));
    }

    private void compareSongRatings(LinkedListNode<Rating> expected, LinkedListNode<Rating> actual){
        assertTrue(expected.size()== actual.size());
        while (expected.getNext()!=null){
            String expectedID = expected.getValue().getReviewerID();
            Integer expectedRating = actual.getValue().getRating();
            while (actual.getNext()!=null){
                String actualID = actual.getValue().getReviewerID();
                Integer actualRating = actual.getValue().getRating();
                if (expectedID==actualID){
                    assertTrue(expectedID.equalsIgnoreCase(actualID));
                    assertEquals(expectedRating,actualRating,.001);
                }
            }
        }
    }
    private void compareSongArrayLists(ArrayList<Song> expected, ArrayList<Song> actual){
        assertEquals(expected.size(),actual.size());
        for (Song song : expected){
            for (Song song2 : actual){
                if (song.equals(song2)){
                    compareSingleSong(song,song2);
                }
            }
        }
    }

    @Test
    public void testOneSong(){
        String fileTested = "data/test_ratings_1.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        ArrayList<Song> expected = new ArrayList<>(); expected.add(new Song("You Belong With Me (Taylor's Version)","Taylor Swift","1qrpoAMXodY6895hGKoUpA"));
        expected.get(0).addRating(new Rating("417",5));
        compareSongArrayLists(expected,actual);
    }

    @Test
    public void testDifferentSongs(){
        String fileTested = "data/test_ratings_2.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        ArrayList<Song> expected = new ArrayList<>(); expected.add(new Song("You Belong With Me (Taylor's Version)","Taylor Swift","1qrpoAMXodY6895hGKoUpA"));
        expected.get(0).addRating(new Rating("417",5));
        expected.add(new Song("Pretty Girl","Clairo","0KyAGiNGUytG5JLxJu4F6l"));
        expected.get(1).addRating(new Rating("56",5));
        compareSongArrayLists(expected,actual);
    }

    @Test
    public void testNoSong(){
        String fileTested = "data/test_ratings_3.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        ArrayList<Song> expected = new ArrayList<>();
        compareSongArrayLists(expected,actual);
    }

    @Test
    public void testMultipleSameSongs(){
        String fileTested = "data/test_ratings_4.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        ArrayList<Song> expected = new ArrayList<>(); expected.add(new Song("You Belong With Me (Taylor's Version)","Taylor Swift","1qrpoAMXodY6895hGKoUpA"));
        expected.get(0).addRating(new Rating("417",5));
        expected.get(0).addRating(new Rating("474",5));
        expected.get(0).addRating(new Rating("47",2));
        compareSongArrayLists(expected,actual);
    }
    @Test
    public void noFileFound(){
        String fileTested = "data/test_ratings_44.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        ArrayList<Song> expected = new ArrayList<>();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void badArtistAndTitle(){
        String fileTested = "data/test_ratings_1.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        assertTrue(actual.get(0).getArtist().equalsIgnoreCase("Taylor Swift"));
        assertTrue(actual.get(0).getTitle().equalsIgnoreCase("You Belong With Me (Taylor's Version)"));
    }

    @Test
    public void oneRating(){
        String fileTested = "data/test_ratings_1.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        assertTrue(actual.get(0).getRatings().getValue().getRating()==5);
    }

    @Test
    public void comparingRatings(){
        String fileTested = "data/test_ratings_1.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        assertTrue(actual.get(0).getRatings().size()==1);
    }

    @Test
    public void reviewerID(){
        String fileTested = "data/test_ratings_1.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        assertTrue(actual.get(0).getRatings().getValue().getReviewerID().equalsIgnoreCase("417"));
    }

    @Test
    public void onlyOneRating(){
        String fileTested = "data/test_ratings_4.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        assertTrue(actual.get(0).getRatings().size()==3);
    }

    @Test
    public void ratingsWrongOrder(){
        String fileTested = "data/test_ratings_5.csv";
        ArrayList<Song> actual = FileReader.readSongs(fileTested);
        int expected = 1;
        LinkedListNode<Rating> head = actual.get(0).getRatings();
        while (head !=null){
            int songRating=head.getValue().getRating();
            assertEquals(songRating,expected,.001);
            head=head.getNext();
            expected++;
        }
    }
    //tests for movie ratings
    @Test
    public void movieRatedNotThere(){
        String moviesAndCast = "data/movies_to_rate_1.csv";
        String movieRatings = "data/test_movie_ratings_1.csv";
        int expected = 1;
        ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
        assertEquals(expected,actual.size(),.001);
    }

    @Test
    public void noMovieFileFound(){
        String moviesAndCast = "data/test_movies_1.csv";
        String movieRatings = "data/test_movie_ratings_100.csv";
        ArrayList<Movie> expected = new ArrayList<>();
        ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
        assertEquals(expected,actual);
        }

    @Test
    public void oneMovie(){
        String moviesAndCast = "data/movies_to_rate_1.csv";
        String movieRatings = "data/test_movie_ratings_1.csv";
        String title = "Toy Story";
        String reviewerID = "1";
        int rating = 2;
        ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
        for (Movie movie : actual){
            if (movie.getTitle().equalsIgnoreCase(title)){
                assertTrue(movie.getTitle().equalsIgnoreCase(title));
                assertTrue(movie.getRatings().getValue().getReviewerID().equalsIgnoreCase(reviewerID));
                assertEquals(movie.getRatings().getValue().getRating(),rating,.001);
            }
        }
        }

     @Test
    public void multipleMovieFile(){
         String moviesAndCast = "data/movies_to_rate_2.csv";
         String movieRatings = "data/test_movie_ratings_2.csv";
         ArrayList<Movie> expected = new ArrayList<>();
         ArrayList<String> fakeCast = new ArrayList<>(); fakeCast.add("Me");
         Movie movie1 = new Movie("Jumanji",fakeCast);
         movie1.addRating(new Rating("1",2));
         Movie movie2 = new Movie("Happy Gilmore",fakeCast);
         movie2.addRating(new Rating("4",4));
         ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
         for (Movie movie : actual){
             for (Movie m2 : expected){
                 if (m2.getTitle().equals(movie.getTitle())){
                 assertTrue(movie.getTitle().equalsIgnoreCase(m2.getTitle()));
                 assertTrue(movie.getRatings().getValue().getReviewerID().equalsIgnoreCase(m2.getRatings().getValue().getReviewerID()));
                 assertEquals(movie.getRatings().getValue().getRating(),m2.getRatings().getValue().getRating(),.001);
             }
             }
         }}

    @Test
    public void multipleMovies(){
        String moviesAndCast = "data/movies_to_rate_3.csv";
        String movieRatings = "data/test_movie_ratings_3.csv";
        String title = "Toy Story";
        String title2 = "Jumanji";
        String reviewerID1 = "1";
        String reviewerID2 = "2";
        String reviewerID3 = "3";
        int rating1 = 1;
        ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
        assertEquals(actual.size(),2,.001);
        for (Movie movie : actual){
            if (movie.getTitle().equalsIgnoreCase(title)){
                assertTrue(movie.getTitle().equalsIgnoreCase(title));
            }else{
                assertTrue(movie.getTitle().equalsIgnoreCase(title2));
            }
        }}

    @Test
    public void multipleRatings(){
        String moviesAndCast = "data/movies_to_rate_3.csv";
        String movieRatings = "data/test_movie_ratings_3.csv";
        String title = "Toy Story";
        String title2 = "Jumanji";
        String reviewerID1 = "1";
        String reviewerID2 = "2";
        String reviewerID3 = "3";
        int rating1 = 1;
        ArrayList<Movie> actual = readMovieRatings(readMovies(moviesAndCast),movieRatings);
        assertEquals(actual.size(),2,.001);
        for (Movie movie : actual){
            if (movie.getTitle().equalsIgnoreCase(title)){
                assertTrue(movie.getTitle().equalsIgnoreCase(title));
                assertEquals(movie.getRatings().size(),3,.001);
            }else{
                assertTrue(movie.getTitle().equalsIgnoreCase(title2));
                assertEquals(movie.getRatings().size(),3,.001);
            }
        }}


    //tests for MediaLibrary class

    @Test
    public void moviesAndSongs(){
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/testMedia1.csv","data/testMedia11.csv","data/testMedia111.csv");
        ArrayList<Ratable> actual = library.topKRatables(3);
        ArrayList<String> expected = new ArrayList<>();
        String expected1 = "You Belong with Me (Taylor's Version)";
        String expected2 = "Toy Story";
        String expected3 = "Jumanji";
        expected.add(expected1);expected.add(expected2);expected.add(expected3);
        for (int i = 0; i<actual.size();i++){
            assertTrue(actual.get(i).getTitle().equalsIgnoreCase(expected.get(i)));
        }
    }

    @Test
    public void moviesAndSongs2(){
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/testMedia3.csv","data/testMedia33.csv","data/testMedia333.csv");
        ArrayList<Ratable> actual = library.topKRatables(2);
        ArrayList<String> expected = new ArrayList<>();
        String expected1 = "Toy Story";
        String expected2 = "You Belong with Me (Taylor's Version)";
        expected.add(expected1);expected.add(expected2);
        for (int i = 0; i<actual.size();i++){
            assertTrue(actual.get(i).getTitle().equalsIgnoreCase(expected.get(i)));
        }
    }
    @Test
    public void moviesLibrary(){
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/testMedia100","data/testMedia11.csv","data/testMedia111.csv");
        ArrayList<Ratable> actual = library.topKRatables(2);
        ArrayList<String> expected = new ArrayList<>();
        String expected1 = "Toy Story";
        String expected2 = "Jumanji";
        expected.add(expected1);expected.add(expected2);
        System.out.println(actual);
        for (int i = 0; i<actual.size();i++){
            assertTrue(actual.get(i).getTitle().equalsIgnoreCase(expected.get(i)));
        }
    }

    @Test
    public void songsLibrary(){
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/testMedia2.csv","data/testMedia100.csv","data/testMedia100.csv");
        ArrayList<Ratable> actual = library.topKRatables(3);
        ArrayList<String> expected = new ArrayList<>();
        String expected1 = "You Belong with Me (Taylor's Version)";
        String expected2 = "Disturbia";
        String expected3 = "Chapstick";
        expected.add(expected1);expected.add(expected2);expected.add(expected3);
        for (int i = 0; i<actual.size();i++){
            assertTrue(actual.get(i).getTitle().equalsIgnoreCase(expected.get(i)));
        }
    }
    @Test
    public void returnsTopK(){
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/ratings.csv","data/movies.csv","data/movie_ratings.csv");
        ArrayList<Ratable> actual = library.topKRatables(3);
        assertEquals(actual.size(),3,.001);
    }
}