package ratings.datastructures;

import ratings.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<String, ArrayList<String>> adjacencyList;

    public Graph(ArrayList<Movie> input ){
        this.adjacencyList= graphMaker(input);
    }

    /*private void addNode(String a){
        if (!this.adjacencyList.containsKey(a)){
            this.adjacencyList.put(a,new ArrayList<>());
        }
    }
    public void addEdge(String a,String b){
        this.addNode(a);
        this.addNode(b);
        this.adjacencyList.get(a).add(b);//bidirectional
        this.adjacencyList.get(b).add(a);
    }*/

    public HashMap<String, ArrayList<String>> getGraph(){
        return this.adjacencyList;
    }

    public HashMap<String, ArrayList<String>> graphMaker(ArrayList<Movie> movieList){
        HashMap<String, ArrayList<String>> retVal = new HashMap<>(); //create hashmap to store actor and arraylist of costars
        for (Movie movie : movieList){
            ArrayList<String> actors = movie.getCast();
            for (String actor : actors){
                if (!retVal.containsKey(actor)){ //if the actor is not in the hashmap, add them
                    retVal.put(actor,new ArrayList<>());//adding node
                }
                for (String costar : actors){// if they are, add the costars to the arraylist stored in the value of the actor key
                    if (!costar.equalsIgnoreCase(actor)){
                        retVal.get(actor).add(costar);//adding edges
                    }
                }
            }
        }
        return retVal;
    }


    @Override
    public String toString(){
        return this.adjacencyList.toString();
    }
}