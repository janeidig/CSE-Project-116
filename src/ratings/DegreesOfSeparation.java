package ratings;

import ratings.datastructures.Graph;
import ratings.datastructures.Queue;

import java.util.*;

public class DegreesOfSeparation {
    private ArrayList<Movie> movieList;

    public DegreesOfSeparation(ArrayList<Movie> movies){
        this.movieList=movies;
    }

    public int degreesOfSeparation(String actor1, String actor2) {
        if (actor1.equalsIgnoreCase(actor2)){
            return 0;//same person
        }
        Graph actorGraph = new Graph(this.movieList);//create new graph of movies stored in state variable
        HashMap<String, ArrayList<String>> actorHash = actorGraph.getGraph();
        //System.out.println(actorHash.toString());
        Queue<String> toVisit = new Queue<>();
        HashMap<String, Integer> visited = new HashMap<>();
        toVisit.enqueue(actor1);//queue first actor who is central node
        visited.put(actor1,0); //0 since it would be 0 degrees to self
        while(toVisit.getFront()!=null) { //while there ar still actors to visit
            String actorSearching = toVisit.dequeue();
            int degreeOfSep = visited.get(actorSearching);
            ArrayList<String> costars = actorHash.get(actorSearching); //list of costars acted with the individual searching
            if (costars != null) {
                if (costars.contains(actor2)) { //if the names match, return the degree of separation plus 1 since starting at 0 for initial actor
                    return degreeOfSep+1;
                }
                for (String costar : costars) { //for each actor that costarred with first person, start loop to check their costars
                    if (!visited.containsKey(costar)) {
                        toVisit.enqueue(costar);
                        visited.put(costar, degreeOfSep + 1);
                    }
                }
            }
        }return -1; //return -1 if no connection found
    }
}

