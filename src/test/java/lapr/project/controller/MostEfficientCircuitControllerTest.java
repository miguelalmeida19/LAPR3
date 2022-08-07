package lapr.project.controller;

import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MostEfficientCircuitControllerTest {

    @Test
    void calculatetCircuit() {
        MostEfficientCircuitController c = new MostEfficientCircuitController();

        Double[][] a = {
                //a  b  c  d
                {null, 12.0, 1.0, 1.0},
                {12.0, null, 1.0, 24.0},
                {1.0, 1.0, null, 1.0},
                {1.0, 24.0, 1.0, null}
        };



        ArrayList<Object> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");


        MatrixGraph<Object, Double> ab = new MatrixGraph<Object, Double>(false, vertices, a);
        assertNotNull(c.calculatetCircuit("A",ab));
    }
}