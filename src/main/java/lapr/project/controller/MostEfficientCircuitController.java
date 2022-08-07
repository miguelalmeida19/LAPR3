package lapr.project.controller;

import lapr.project.structures.graph.Algorithms;
import lapr.project.structures.graph.Graph;

import java.util.LinkedList;

public class MostEfficientCircuitController {


    /**
     * Calculates the most efficient circuit
     * @param startingVertex start vertex
     * @param a the graph

     * @return path
     */
    public LinkedList<Object> calculatetCircuit(Object startingVertex, Graph<Object ,Double> a ){
        return Algorithms.pathfinder(a,startingVertex);
    }
}
