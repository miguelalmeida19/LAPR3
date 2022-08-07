package lapr.project.store;

import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;

public class FreightNetworkStore {
    static MatrixGraph<Object, Double> ab;
    static MapGraph<Object, Double> mg;

    /**
     * This method sets the MatrixGraph
     * @param ab
     */
    public static void setAb(MatrixGraph<Object, Double> ab) {
        FreightNetworkStore.ab = ab;
    }

    /**
     * This method sets the MapGraph
     * @param mg
     */
    public static void setMg(MapGraph<Object, Double> mg) {
        FreightNetworkStore.mg = mg;
    }

    /**
     * This method gets the MapGraph
     * @return
     */
       public static MapGraph<Object, Double> getMg() {
        if(ab != null && mg == null){
            return new MapGraph<>(ab);
        }
        return mg;
    }

    /**
     * This method gets the MatrixGraph
     * @return
     */
    public static MatrixGraph<Object, Double> getAb() {
        if(mg != null && ab == null){
            return new MatrixGraph<>(mg);
        }
        return ab;
    }
}
