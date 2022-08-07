package lapr.project.utils;

import lapr.project.structures.graph.Edge;

import java.util.Comparator;

public class SortDistances implements Comparator<Edge<Object, Double>> {

    @Override
    public int compare(Edge a, Edge b) {

        return Double.compare((Double) a.getWeight(), (Double) b.getWeight());
    }
}

