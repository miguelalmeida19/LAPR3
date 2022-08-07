package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import lapr.project.structures.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Test;

class ClosestPlacesInputControllerTest {
    @Test
    void testShortestPathUsingNplaces() {
        ClosestPlacesInputController closestPlacesInputController = new ClosestPlacesInputController();

        MatrixGraph<Object, Double> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Start Vertex");
        assertTrue(closestPlacesInputController
                .shortestPathUsingNplaces(matrixGraph, "Start Vertex", "End Vertex", new LinkedList<>(), true, true)
                .isEmpty());
    }
}

