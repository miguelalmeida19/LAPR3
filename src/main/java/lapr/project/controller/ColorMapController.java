package lapr.project.controller;

import lapr.project.structures.graph.ColoredGraph;
import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;

import java.util.Map;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ColorMapController {
    /**
     * Colors the map
     * @param n needed to generate the graph
     * @param timeDebugVerbose time debugging
     * @return hashmap with the vertex and their colors
     */
    public Map<Object, Integer> colorMap(int n, boolean timeDebugVerbose){
        long startTime = System.currentTimeMillis();


        FreightNetworkController f = new FreightNetworkController();
        f.build(n);
        long endTime = System.currentTimeMillis();
        printDebuginfo(timeDebugVerbose,"Generating the graph took "+(endTime - startTime)+" milliseconds");

        MatrixGraph<Object, Double> ab = new MatrixGraph<>(false, f.getVertices(), f.getMatrix());
        MapGraph<Object, Double> mg = new MapGraph<>(ab);
        startTime = System.currentTimeMillis();

        ColoredGraph c = new ColoredGraph(mg);
         endTime = System.currentTimeMillis();
        printDebuginfo(timeDebugVerbose,"Coloring the graph took "+(endTime - startTime)+" milliseconds");

        return c.colorGraph();
    }
    private void printDebuginfo(boolean isdebuging, String text){
        if(isdebuging){
            print(text);
        }
    }
}
