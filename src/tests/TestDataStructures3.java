package tests;

import org.junit.Test;
import ratings.DegreesOfSeparation;
import ratings.FileReader;
import ratings.Movie;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestDataStructures3 {

    //readMovies testing
    private void compareMovies(Movie expected, Movie actual){
        assertTrue(expected.getTitle().equalsIgnoreCase(actual.getTitle()));
        compareCast(expected.getCast(),actual.getCast());
    }
    private void compareCast(ArrayList<String> expected, ArrayList<String> actual){
        assertEquals(expected.size(),actual.size());
        for (int x = 0; x <expected.size();x++){
            assertTrue(expected.get(x).equalsIgnoreCase(actual.get(x)));}
    }
    private void compareMovieLists(ArrayList<Movie> expected, ArrayList<Movie> actual) {
        assertEquals(expected.size(), actual.size());
        for (Movie movie : expected) {
            for (Movie movie2 : actual) {
                if (movie.getTitle().equalsIgnoreCase(movie2.getTitle())) {
                    compareMovies(movie, movie2);
                }
            }
        }
    }


    @Test
    public void testSingleMovie(){
        String fileTested = "data/test_movies_1.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        String[] actors = {"Tom Hanks","Tim Allen","Don Rickles","Wallace Shawn","John Ratzenberger","Annie Potts","John Morris","Laurie Metcalf","R. Lee Ermey","Penn Jillette"};
        ArrayList<String> cast = new ArrayList<>();
        for (String star : actors){
            cast.add(star);}
        ArrayList<Movie> expected = new ArrayList<>(); expected.add(new Movie ("Toy Story", cast));
        compareMovieLists(expected,actual);
    }

    @Test
    public void testMultipleMovies(){
        String fileTested = "data/test_movies_2.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        String[] actors = {"Tom Hanks","Tim Allen","Don Rickles","Wallace Shawn","John Ratzenberger","Annie Potts","John Morris","Laurie Metcalf","R. Lee Ermey","Penn Jillette"};
        ArrayList<String> cast = new ArrayList<>();
        for (String star : actors){
            cast.add(star);}
        ArrayList<Movie> expected = new ArrayList<>(); expected.add(new Movie ("Toy Story", cast));
        String[] actors2 = {"Robin Williams","Jonathan Hyde","Kirsten Dunst","Bonnie Hunt","Bebe Neuwirth","David Alan Grier","Patricia Clarkson","James Handy","Malcolm Stewart","Darryl Henriques"};
        ArrayList<String> cast2 = new ArrayList<>();
        for (String star : actors2){
            cast2.add(star);}
        expected.add(new Movie ("Jumanji", cast2));
        compareMovieLists(expected,actual);
    }

    @Test
    public void testNoMovies(){
        String fileTested = "data/test_movies_3.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        ArrayList<Movie> expected = new ArrayList<>();
        compareMovieLists(expected,actual);
    }

    @Test
    public void testMultipleSameMovies(){
        String fileTested = "data/test_movies_4.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        String[] actors = {"Tom Hanks","Tim Allen","Don Rickles","Wallace Shawn","John Ratzenberger","Annie Potts","John Morris","Laurie Metcalf","R. Lee Ermey","Penn Jillette"};
        ArrayList<String> cast = new ArrayList<>();
        for (String star : actors){
            cast.add(star);}
        ArrayList<Movie> expected = new ArrayList<>(); expected.add(new Movie ("Toy Story", cast));
        String[] actors2 = {"Robin Williams","Jonathan Hyde","Kirsten Dunst","Bonnie Hunt","Bebe Neuwirth","David Alan Grier","Patricia Clarkson","James Handy","Malcolm Stewart","Darryl Henriques"};
        ArrayList<String> cast2 = new ArrayList<>();
        for (String star : actors2){
            cast2.add(star);}
        expected.add(new Movie ("Jumanji", cast2));
        expected.add(new Movie("Toy Story",cast));
        compareMovieLists(expected,actual);
    }
    @Test
    public void testNoFile(){
        String fileTested = "data/test_movies_44.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        ArrayList<Movie> expected = new ArrayList<>();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void badTitle(){
        String fileTested = "data/test_movies_1.csv";
        ArrayList<Movie> actual = FileReader.readMovies(fileTested);
        assertTrue(actual.get(0).getTitle().equalsIgnoreCase("Toy Story"));
    }

    //tests for degrees of separation

    @Test
    public void actedSameMovie(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Dwayne Johnson");
        assertEquals(1,actual,.001);
    }

    @Test
    public void actedOneMovieApart(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Jennie Finch");
        assertEquals(2,actual,.001);
    }

    @Test
    public void actedThreeMoviesApart(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Lebron James");
        assertEquals(3,actual,.001);
    }

    @Test
    public void actedFourMoviesApart(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Jennifer Aniston");
        assertEquals(4,actual,.001);
    }

    @Test
    public void actedFiveMoviesApart(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        Movie movie5 = new Movie("Test5", new ArrayList<>(Arrays.asList("Kenny Pickett","Me","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);movies.add(movie5);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Me");
        assertEquals(5,actual,.001);
    }

    @Test
    public void sameActor(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        Movie movie5 = new Movie("Test5", new ArrayList<>(Arrays.asList("Kenny Pickett","Me","Pete Davidson")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);movies.add(movie5);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Michael Jordan");
        assertEquals(0,actual,.001);
    }

    @Test
    public void noConnection(){
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Test1", new ArrayList<>(Arrays.asList("Michael Jordan","Dwayne Johnson","Brett Favre")));
        Movie movie2 = new Movie("Test2", new ArrayList<>(Arrays.asList("Dwayne Johnson","Jennie Finch","Mark Wahlberg")));
        Movie movie3 = new Movie("Test3", new ArrayList<>(Arrays.asList("Lebron James","Mark Wahlberg","Skylar Diggs")));
        Movie movie4 = new Movie("Test4", new ArrayList<>(Arrays.asList("Skylar Diggs","Jennifer Aniston","Pete Davidson")));
        Movie movie5 = new Movie("Test5", new ArrayList<>(Arrays.asList("Kenny Pickett","Me","Pete Davidson")));
        Movie movie6 = new Movie("Test5", new ArrayList<>(Arrays.asList("Sting","Hulk Hogan","Roman Reigns")));
        movies.add(movie1);movies.add(movie2);movies.add(movie3);movies.add(movie4);movies.add(movie5);
        DegreesOfSeparation movieList = new DegreesOfSeparation(movies);
        int actual = movieList.degreesOfSeparation("Michael Jordan","Sting");
        assertEquals(-1,actual,.001);
    }

}
