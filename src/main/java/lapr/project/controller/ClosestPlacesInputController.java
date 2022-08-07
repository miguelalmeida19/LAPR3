package lapr.project.controller;

import lapr.project.structures.graph.Algorithms;
import lapr.project.structures.graph.matrix.MatrixGraph;
import java.util.LinkedList;

public class ClosestPlacesInputController {


    //[US402] As a Traffic manager I wish to know the shortest path between two locals (city and/or port).
    //    o Acceptance criteria [ESINF]:
    //              Land path (only includes land routes, may start/end in port/city).
    //              Maritime path (only includes ports).
    //              Land or sea path (may include cities and ports).
    //              Obligatorily passing through n indicated places.


    /**
     * Finds the shortest path between the start point and the end point, going through the places you want and the type of path you want
     */

    public LinkedList<Object> shortestPathUsingNplaces(MatrixGraph<Object, Double>  mg, Object startVertex, Object endVertex, LinkedList<Object> objects, boolean sea, boolean land){
        return Algorithms.shortestPathUsingNplaces(mg, startVertex, endVertex, objects, sea, land);
    }
}
