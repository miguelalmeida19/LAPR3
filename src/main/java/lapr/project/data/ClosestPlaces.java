package lapr.project.data;

import lapr.project.controller.FreightNetworkController;
import lapr.project.model.Capital;
import lapr.project.model.Port;
import lapr.project.structures.graph.Edge;
import lapr.project.structures.graph.matrix.MatrixGraph;

import java.util.*;

public class ClosestPlaces {

    private final FreightNetworkController controller = new FreightNetworkController();
    private Double[][] matrix;
    private List<Object> vertices;
    private Collection<Edge<Object,Double>> edgesList = new ArrayList<>();

    /**
     * Construction of the n-element matrix, I chose a high number so that all possible paths are introduced into the matrix.
     */

    public void build (int n){
        controller.build(n);
        getMatrix();
        getVertices();
        getEdges();
    }

    /**
     * Go get the ready matrix
     */
    public void getMatrix(){
        matrix = controller.getMatrix();
    }

    /**
     * Go get the vertices
     */
    public void getVertices(){
        vertices = controller.getVertices();
    }

    /**
     * Go get the edges
     */
    public void getEdges(){
        MatrixGraph<Object, Double> ab = new MatrixGraph<>(false, vertices, matrix);
        edgesList = ab.edges();

    }

    /**
     * Method that returns a list of all edges of the matrix whose continent corresponds to the continent entered by the user
     */
    public List<Edge<Object, Double>> getPlaces(String continent){

        String continente = capitalize(continent);

        List<Edge<Object, Double>> b = new ArrayList<>();

        for (Edge<Object, Double> a : edgesList) {

            boolean origemCapital = a.getVOrig() instanceof Capital;
            boolean origemPort = a.getVOrig() instanceof Port;
            boolean destinoCapital = a.getVDest() instanceof Capital;
            boolean destinoPort = a.getVDest() instanceof Port;

            //CAPITAL TO CAPITAL
            if (origemCapital && destinoCapital && ((Capital) a.getVOrig()).getContinent().equals(continente) && ((Capital) a.getVDest()).getContinent().equals(continente) && a.getWeight() != 0.0)
                b.add(a);

            //CAPITAL TO PORT
            if (origemCapital && destinoPort && ((Capital) a.getVOrig()).getContinent().equals(continente) && ((Port) a.getVDest()).getContinent().equals(continente) && a.getWeight() != 0.0)
                b.add(a);

            //PORT TO CAPITAL
            if (origemPort && destinoCapital && ((Port) a.getVOrig()).getContinent().equals(continente) && ((Capital) a.getVDest()).getContinent().equals(continente) && a.getWeight() != 0.0) {
                b.add(a);
            }

            //PORT TO PORT
            if (origemPort && destinoPort && ((Port) a.getVOrig()).getContinent().equals(continente) && ((Port) a.getVDest()).getContinent().equals(continente) && a.getWeight() != 0.0)
                b.add(a);
        }
        return b;
    }

    /**
     * Method that puts the continent string in the same way we have it in the database, with the first letter uppercase and the rest lowercase
     */
    public String capitalize (String data) {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }
}
