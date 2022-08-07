package lapr.project.structures.graph;

import lapr.project.model.Port;
import lapr.project.store.FreightNetworkStore;
import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;

import java.util.*;

/**
 * Class to calculate Betweenness Centrality
 */
public class BetweennessCentrality {

    private static MatrixGraph<Object, Double> ab;
    private static MapGraph<Object, Double> mg;

    /**
     * Constructor
     */
    public BetweennessCentrality() {
        ab = FreightNetworkStore.getAb();
        mg = FreightNetworkStore.getMg();
    }

    /**
     * This method computes the betweenness centrality
     * @param n
     * @return
     */
    public LinkedHashMap<Object, Integer> computeBetweennessCentrality(int n) {
        /*
        get all possible paths

        iterate through all vertices

        for each vertice, iterate through all paths except the paths that begin or end with the Vertex

        if the paths contains the vertex, count++

        make the top N of centrality and it's done!
         */

        List<LinkedList<Object>> allPathsCorrect = new ArrayList<>();

        ab.vertices().forEach(vertex -> {
            List<LinkedList<Object>> allPaths = getAllPathsBetweenness(vertex);
            for (LinkedList<Object> l : allPaths) {
                if (l.size() > 1 && checkListsEquals(l, allPathsCorrect)) {
                    allPathsCorrect.add(l);
                }
            }
        });

        LinkedHashMap<Object, Integer> map = new LinkedHashMap<>();

        ab.vertices().forEach(vertex -> {
            if (vertex instanceof Port) {
                int count = 0;
                for (LinkedList<Object> list : allPathsCorrect) {
                    if (list.getFirst() != vertex && list.getLast() != vertex && list.contains(vertex)) {
                        count++;
                    }
                }
                map.put(vertex, count);
            }
        });

        LinkedHashMap<Object, Integer> topNMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<Object, Integer>
                        comparingByValue().reversed()).
                limit(n).
                forEachOrdered(entry -> topNMap.put(entry.getKey(), entry.getValue()));

        return topNMap;
    }

    /**
     * This method gets all paths betweenness
     * @param vert
     * @return
     */
    public List<LinkedList<Object>> getAllPathsBetweenness(Object vert) {
        ArrayList<LinkedList<Object>> allPaths = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        Algorithms.shortestPaths(mg, vert, allPaths, doubles);
        return allPaths;
    }

    /**
     * This method checks list equals
     * @param l1
     * @param l2
     * @return
     */
    public boolean checkListsEquals(List<Object> l1, List<LinkedList<Object>> l2) {
        for (LinkedList<Object> list : l2) {
            if (list.size() == l1.size() && list.containsAll(l1)) {
                return false;
            }
        }
        return true;
    }

}