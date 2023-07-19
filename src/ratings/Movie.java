package ratings;

import java.util.ArrayList;

public class Movie extends Ratable{
    private ArrayList<String> cast;

    public Movie(String title, ArrayList<String> cast){
        //super class called implicitly b/c no parameters in constructor
        this.setTitle(title);
        this.cast=cast;
    }

    public ArrayList<String> getCast(){
        if (this.cast==null){
        return null;}
        else{
            return this.cast;
        }
    }
}
