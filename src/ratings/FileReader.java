package ratings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public static ArrayList<String> readFile(String filename){
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch(IOException e){
            ArrayList<String> retVal = new ArrayList<>();
            return retVal;
        }
    }

    public static ArrayList<Song> readSongs(String filename){
        HashMap<String, Song> songList = new HashMap<>();
        ArrayList<String> lines = readFile(filename);
        if (lines.isEmpty()){
            ArrayList<Song> retVal = new ArrayList<>();
            //System.out.println(retVal);
            return retVal;
        }else{
        for (String line : lines) {
            ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));
            //System.out.println(splits);
            String songID = splits.get(0);
            String artist = splits.get(1);
            String title = splits.get(2);
            String reviewerID = splits.get(3);
            int rating = Integer.parseInt(splits.get(4));
            if (songList.containsKey(songID)) {
                songList.get(songID).addRating(new Rating(reviewerID, rating));
            } else {
                Song song = new Song(title, artist, songID);
                song.addRating(new Rating(reviewerID, rating));
                songList.put(songID, song);
            }
            //System.out.println(songList);
        }
        ArrayList<Song> retVal = new ArrayList<>();
        for (Map.Entry<String, Song> songEntry : songList.entrySet()){
                Song add = songEntry.getValue();
                retVal.add(add);
            }
        //System.out.println(retVal);
        return retVal;
        }
}

    public static ArrayList<Movie> readMovies(String filename){
        ArrayList<Movie> movieList = new ArrayList<>();
        ArrayList<String> lines = readFile(filename);
        if (lines.isEmpty()){
             return new ArrayList<>();
        }else{
            for (String line : lines) {
            ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));
            //System.out.println(splits);
            String title = splits.get(0);
            ArrayList<String> movieCast = new ArrayList<>();
                for (int c = 1; c < splits.size();c++){
                    movieCast.add(splits.get(c));
        }
                movieList.add(new Movie(title,movieCast));
        }
        }
    //System.out.println(movieList);
    return movieList;
    }

    public static ArrayList<Movie> readMovieRatings(ArrayList<Movie> movies, String filename) {
        ArrayList<Movie> retVal = new ArrayList<>();
        ArrayList<String> lines = readFile(filename);
        for (Movie m : movies){
            for (String line : lines){
                ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));
                String title = splits.get(0);
                String reviewerID = splits.get(1);
                int rating = Integer.parseInt(splits.get(2));
                if (m.getTitle().equalsIgnoreCase(title)){
                    m.addRating(new Rating(reviewerID, rating));
                }
            }
        }
        for(Movie m : movies){
            if (m.getRatings()!=null){
                retVal.add(m);
            }
        }return retVal;
    }

    public static void main(String[] args) {
        ArrayList<Movie> test = readMovies("data/testMedia11.csv");
        ArrayList<Movie> help = readMovieRatings(test,"data/testMedia111.csv");

    }





}

