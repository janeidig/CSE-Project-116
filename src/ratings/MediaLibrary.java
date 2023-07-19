package ratings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static ratings.FileReader.*;

public class MediaLibrary {
    private ArrayList<Movie> movies;
    private ArrayList<Song> songs;

    public void MediaLibrary(){
        this.movies = new ArrayList<>();
        this.songs = new ArrayList<>();
    }

    public void populateLibrary(String songFile, String movieFile, String mRatingFile){
        ArrayList<Song> songs = readSongs(songFile);
        ArrayList<Movie> movie = readMovies(movieFile);
        ArrayList<Movie> movies = readMovieRatings(movie,mRatingFile);
        this.songs=songs;
        this.movies=movies;
    }

    public ArrayList<Ratable> topKRatables(int n){
        ArrayList<Ratable> retVal = new ArrayList<>();
        ArrayList<Ratable> finalList = new ArrayList<>();
        retVal.addAll(this.songs);
        retVal.addAll(this.movies);
        //how to sort these by rating now..  comparator? sort method? hashmap?
        Collections.sort(retVal,ratingsComparator); //sort arrayList
        for (int i = 0; i<n; i++){//get top k rated and add to new list
            finalList.add(retVal.get(i));}
        Collections.sort(finalList,ratingsComparator);//sort list again to display in order
        return finalList;
    }

    //creating comparator for ratables and overriding compare method to sort library by avg rating
    public static Comparator<Ratable> ratingsComparator = new Comparator<Ratable>() {
        @Override
        public int compare(Ratable a, Ratable b) {
            double aAvg = a.bayesianAverageRating(2,3);
            double bAvg = b.bayesianAverageRating(2,3);
            if (aAvg>bAvg){
                return -1;
            }else {
                return 1;
            }
        }
        };

    public static void main(String[] args) {
        MediaLibrary library = new MediaLibrary();
        library.populateLibrary("data/ratings.csv","data/movies.csv","data/movie_ratings.csv");
        //System.out.println(library.movies);
    }


}
