package lapr.project.controller;

import lapr.project.data.ClosestPlaces;
import lapr.project.structures.graph.Edge;

import java.util.List;

public class ClosestPlacesController {

    private final ClosestPlaces c;

    public ClosestPlacesController(){
        c = new ClosestPlaces();
    }


    //[US303] As a Traffic manager I wish to know which places (cities or ports) are closest to all other places (closeness places).
    //      o Acceptance criteria [ESINF]:
    //              Return the n closeness locals by continent.
    //              The measure of proximity is calculated as the average of the shortest path length from the local to all other locals in the network.


    /**
     * Construction of the n-element matrix, I chose a high number so that all possible paths are introduced into the matrix.
     */
    public void build(){
        c.build(1000000);
    }

    /**
     * Method that returns a list of all edges of the matrix whose continent corresponds to the continent entered by the user
     */
    public  List<Edge<Object, Double>> getPlaces(String continent){
        return c.getPlaces(continent);
    }
}
