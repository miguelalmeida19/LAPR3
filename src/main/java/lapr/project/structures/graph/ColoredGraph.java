package lapr.project.structures.graph;

import lapr.project.structures.graph.map.MapGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ColoredGraph<V,E>{

    private final HashMap<V,Integer> vertexColors = new HashMap<>();
    private final HashSet<Integer> colors = new HashSet<>();
    private final MapGraph<V,E> graph;
    public ColoredGraph(MapGraph<V,E> mg){
        graph = mg;
        setup();
    }
    //O(V)
    private void setup(){
        for(int n = 0; n< graph.vertices.size(); n++ ){
            colors.add(n+1);
        }
    }

    /**
     * Public method to color the graph
     * @return a map with the vertex - color
     */

    public Map<V, Integer> colorGraph(){
        //O(V^2) in worst case and O(V) in the best case (all vertex has only 1 connection)
        for(V v: graph.vertices){
            colorVertex(v, new HashSet<>(colors));
        }
        return vertexColors;
    }

    private void colorVertex(V vertexToColor, HashSet<Integer> colorsToUse){
        for(V v: graph.adjVertices(vertexToColor)){//O(V) in worst case (the vertex has connections to all other vertex)  and O(1) in the best case )connects to only 1 vertex)
            colorsToUse.remove(vertexColors.get(v)); //O(1)
        }
        vertexColors.put(vertexToColor, colorsToUse.iterator().next()); //O(1)
    }
}
