package lapr.project.structures.graph.map;

import lapr.project.structures.graph.Edge;
import lapr.project.structures.graph.Graph;
import lapr.project.structures.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author DEI-ISEP
 */
public class MapGraphTest {

    final ArrayList<String> co = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "C", "D", "E", "E"));
    final ArrayList<String> cd = new ArrayList<>(Arrays.asList("B", "C", "D", "D", "E", "A", "D", "E"));
    final ArrayList<Integer> cw = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    final ArrayList<String> ov = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
    MapGraph<String, Integer> instance = null;

    @BeforeEach
    public void initializeGraph() {
        instance = new MapGraph<>(true);
    }

    @Test
    void testConstructor() {
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(true);
        assertTrue(actualMapGraph.isDirected());
        assertTrue(actualMapGraph.vertices().isEmpty());
        assertEquals("\nGraph not defined!!", actualMapGraph.toString());
        assertEquals(0, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor2() {
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(new MapGraph<>(true));
        assertTrue(actualMapGraph.isDirected());
        assertTrue(actualMapGraph.vertices().isEmpty());
        assertEquals("\nGraph not defined!!", actualMapGraph.toString());
        assertEquals(0, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor3() {
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(new MapGraph<>(false));
        assertFalse(actualMapGraph.isDirected());
        assertTrue(actualMapGraph.vertices().isEmpty());
        assertEquals("\nGraph not defined!!", actualMapGraph.toString());
        assertEquals(0, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor4() {
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(new MatrixGraph<>(true));
        assertTrue(actualMapGraph.isDirected());
        assertTrue(actualMapGraph.vertices().isEmpty());
        assertEquals("\nGraph not defined!!", actualMapGraph.toString());
        assertEquals(0, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertTrue(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\nV Orig: \nV Orig -> V Dest\nWeight: Weight\nV Dest: \n\n",
                actualMapGraph.toString());
        assertEquals(1, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertTrue(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n"
                + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n", actualMapGraph.toString());
        assertEquals(1, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertTrue(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig: \n" + "V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "Weight: Weight\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "\n", actualMapGraph.toString());
        assertEquals(1, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertFalse(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals("Graph: 2 vertices, 2 edges\n" + "V Orig: \n" + "V Orig -> V Dest\n" + "Weight: Weight\n"
                + "V Dest: \n" + "V Dest -> V Orig\n" + "Weight: Weight\n", actualMapGraph.toString());
        assertEquals(2, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertTrue(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight: \n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight\n" + "V Dest: \n" + "\n", actualMapGraph.toString());
        assertEquals(1, actualMapGraph.numEdges());
    }

    @Test
    void testConstructor10() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        Edge<Object, Object> vOrig = new Edge<>("V Orig", "V Dest", "Weight");

        mapGraph.addEdge(new Edge<>(vOrig, new Edge<>("V Orig", "V Dest", "Weight"), "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualMapGraph = new MapGraph<>(mapGraph);
        assertTrue(actualMapGraph.isDirected());
        assertEquals(2, actualMapGraph.vertices().size());
        assertEquals(
                "Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Orig -> V Dest\n"
                        + "Weight: Weight\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Orig -> V Dest\n"
                        + "Weight: Weight\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n",
                actualMapGraph.toString());
        assertEquals(1, actualMapGraph.numEdges());
    }

    /**
     * Test of copy constructor of class Graph.
     */
    @Test
    public void testCopyConstructor() {
        System.out.println("Test copy constructor");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Graph<String, Integer> g = new MapGraph<>(instance);
        assertEquals(instance.getClass(), g.getClass(), "The graphs should be from the same class");
        assertEquals(instance, g, "The graphs should have equal contents");
    }

    /**
     * Test of isDirected method, of class Graph.
     */
    @Test
    public void testIsDirected() {
        System.out.println("Test isDirected");

        assertTrue(instance.isDirected(), "result should be true");
        instance = new MapGraph<>(false);
        assertFalse(instance.isDirected(), "result should be false");
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertEquals(0, instance.numVertices(), "result should be zero");
        instance.addVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.addVertex("B");
        assertEquals(2, instance.numVertices(), "result should be two");
        instance.removeVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.removeVertex("B");
        assertEquals(0, instance.numVertices(), "result should be zero");
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        assertEquals(0, instance.vertices().size(), "vertices should be empty");

        instance.addVertex("A");
        instance.addVertex("B");

        Collection<String> cs = instance.vertices();
        assertEquals(2, cs.size(), "Must have 2 vertices");
        cs.removeAll(Arrays.asList("A", "B"));
        assertEquals(0, cs.size(), "Vertices should be A and B");

        instance.removeVertex("A");

        cs = instance.vertices();
        assertEquals(1, cs.size(), "Must have 1 vertice1");
        cs.removeAll(Arrays.asList("B"));
        assertEquals(0, cs.size(), "Vertice should be B");

        instance.removeVertex("B");
        cs = instance.vertices();
        assertEquals(0, cs.size(), "Must not have any vertice");
    }

    /**
     * Test of validVertex method, of class Graph.
     */
    @Test
    public void testValidVertex() {
        System.out.println("Test validVertex");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (String v : co)
            assertTrue(instance.validVertex(v), "vertices should exist");


        assertFalse(instance.validVertex("Z"), "vertice should not exist");
    }

    @Test
    void testValidVertex2() {
        assertFalse((new MapGraph<>(true)).validVertex("Vert"));
    }

    @Test
    void testValidVertex3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.validVertex("Vert"));
    }

    /**
     * Test of key method, of class Graph.
     */
    @Test
    public void testKey() {
        System.out.println("Test key");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < ov.size(); i++)
            assertEquals(i, instance.key(ov.get(i)), "vertices should exist");

        assertEquals(-1, instance.key("Z"), "vertice should not exist");
    }

    /**
     * Test of testAdjVertices method, of class Graph.
     */
    @Test
    public void testAdjVertices() {
        System.out.println("Test adjVertices");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<String> cs = instance.adjVertices("A");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("B") || s.equals("C"));
        assertEquals(0, cs.size(), "Adjacents should be B and C");

        cs = instance.adjVertices("B");
        assertEquals(1, cs.size(), "Num adjacents should be 1");
        cs.removeIf(s -> s.equals("D"));
        assertEquals(0, cs.size(), "Adjacents should be S");

        cs = instance.adjVertices("E");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("D") || s.equals("E"));
        assertEquals(0, cs.size(), "Adjacents should be D and E");
    }
    
    @Test
    void testAdjVertices3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.adjVertices("Vert").isEmpty());
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertEquals(0, instance.numEdges(), "result should be zero");

        instance.addEdge("A", "B", 1);
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.addEdge("A", "C", 2);
        assertEquals(2, instance.numEdges(), "result should be two");

        instance.removeEdge("A", "B");
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.removeEdge("A", "C");
        assertEquals(0, instance.numEdges(), "result should be zero");
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        assertEquals(0, instance.edges().size(), "edges should be empty");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 8 edges");
        for (int i = 0; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A", "B");
        ced = instance.edges();
        assertEquals(7, ced.size(), "Must have 7 edges");
        for (int i = 1; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("E", "E");
        ced = instance.edges();
        assertEquals(6, ced.size(), "Must have 6 edges");
        for (int i = 1; i < co.size() - 1; i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A", "C");
        instance.removeEdge("B", "D");
        instance.removeEdge("C", "D");
        instance.removeEdge("C", "E");
        instance.removeEdge("D", "A");
        instance.removeEdge("E", "D");

        assertEquals(0, instance.edges().size(), "edges should be empty");
    }

    @Test
    void testEdges2() {
        assertTrue((new MapGraph<>(true)).edges().isEmpty());
    }

    @Test
    void testEdges3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(1, mapGraph.edges().size());
    }

    @Test
    void testEdge() {
        assertNull((new MapGraph<>(true)).edge(1, 1));
        assertNull((new MapGraph<>(true)).edge("V Orig", "V Dest"));
    }

    @Test
    void testEdge2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertNull(mapGraph.edge(1, 1));
    }

    @Test
    void testEdge3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        assertNull(mapGraph.edge(1, 1));
    }

    @Test
    void testEdge4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertNull(mapGraph.edge(1, 2));
    }

    @Test
    void testEdge5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "Weight");
        assertNull(mapGraph.edge(1, 1));
    }

    @Test
    void testEdge6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        Edge<Object, Object> vOrig = new Edge<>("V Orig", "V Dest", "Weight");

        mapGraph.addEdge("V Orig", new Edge<>(vOrig, new Edge<>("V Orig", "V Dest", "Weight"), "Weight"), "Weight");
        assertNull(mapGraph.edge(1, 1));
    }

    @Test
    void testEdge7() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by edge(Object, Object)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        mapGraph.edge("V Orig", "V Dest");
    }

    @Test
    void testEdge8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        assertNull(mapGraph.edge("V Orig", "V Dest"));
    }

    @Test
    void testEdge9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertNull(mapGraph.edge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest"));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(co.get(i), cd.get(i)).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge("A", "E"), "edge should be null");
        assertNull(instance.edge("D", "B"), "edge should be null");
        instance.removeEdge("D", "A");
        assertNull(instance.edge("D", "A"), "edge should be null");
    }

    /**
     * Test of getEdge by key method, of class Graph.
     */
    @Test
    public void testGetEdgeByKey() {
        System.out.println("Test getEdge");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(instance.key(co.get(i)), instance.key(cd.get(i))).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge(instance.key("A"), instance.key("E")), "edge should be null");
        assertNull(instance.edge(instance.key("D"), instance.key("B")), "edge should be null");
        instance.removeEdge("D", "A");
        assertNull(instance.edge(instance.key("D"), instance.key("A")), "edge should be null");
    }


    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.outDegree("G"), "degree should be -1");
        assertEquals(2, instance.outDegree("A"), "degree should be 2");
        assertEquals(1, instance.outDegree("B"), "degree should be 1");
        assertEquals(2, instance.outDegree("E"), "degree should be 2");
    }

    @Test
    void testOutDegree2() {
        assertEquals(-1, (new MapGraph<>(true)).outDegree("Vert"));
    }

    @Test
    void testOutDegree3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertEquals(0, mapGraph.outDegree("Vert"));
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.inDegree("G"), "degree should be -1");
        assertEquals(1, instance.inDegree("A"), "degree should be 1");
        assertEquals(3, instance.inDegree("D"), "degree should be 3");
        assertEquals(2, instance.inDegree("E"), "degree should be 2");
    }

    @Test
    void testInDegree2() {
        assertEquals(-1, (new MapGraph<>(true)).inDegree("Vert"));
    }

    @Test
    void testInDegree3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertEquals(0, mapGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertEquals(-1, mapGraph.inDegree(new Edge<>("V Orig", "V Dest", "Weight")));
    }

    @Test
    void testInDegree5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        mapGraph.addVertex(new Edge<>("V Orig", "V Dest", "Weight"));
        assertEquals(0, mapGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        mapGraph.addVertex(new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"));
        assertEquals(0, mapGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        mapGraph.addVertex(new Edge<>("V Orig", "V Dest", "Weight"));
        assertEquals(0, mapGraph.inDegree(new Edge<>("V Orig", "V Dest", "Weight")));
    }

    @Test
    void testInDegree8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        Edge<Object, Object> vOrig = new Edge<>("V Orig", "V Dest", "Weight");

        mapGraph.addVertex(new Edge<>(vOrig, new Edge<>("V Orig", "V Dest", "Weight"), "Weight"));
        assertEquals(0, mapGraph.inDegree("Vert"));
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> coe = instance.outgoingEdges("C");
        assertEquals(2, coe.size(), "Outgoing edges of vert C should be 2");
        coe.removeIf(e -> e.getWeight() == 4 || e.getWeight() == 5);
        assertEquals(0, coe.size(), "Outgoing edges of vert C should be 4 and 5");

        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be 2");
        coe.removeIf(e -> e.getWeight() == 7 || e.getWeight() == 8);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7 and 8");

        instance.removeEdge("E", "E");

        coe = instance.outgoingEdges("E");
        assertEquals(1, coe.size(), "Outgoing edges of vert E should be 1");
        coe.removeIf(e -> e.getWeight() == 7);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7");

        instance.removeEdge("E", "D");

        coe = instance.outgoingEdges("E");
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be empty");
    }

    @Test
    void testOutgoingEdges2() {
        assertTrue((new MapGraph<>(true)).outgoingEdges("Vert").isEmpty());
    }

    @Test
    void testOutgoingEdges3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.outgoingEdges("Vert").isEmpty());
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        System.out.println(" Test incomingEdges");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> cie = instance.incomingEdges("D");
        assertEquals(3, cie.size(), "Incoming edges of vert C should be 3");
        cie.removeIf(e -> e.getWeight() == 3 || e.getWeight() == 4 || e.getWeight() == 7);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 3, 4 and 7");

        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert E should be 2");
        cie.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 8);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5 and 8");

        instance.removeEdge("E", "E");

        cie = instance.incomingEdges("E");
        assertEquals(1, cie.size(), "Incoming edges of vert E should be 1");
        cie.removeIf(e -> e.getWeight() == 5);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5");

        instance.removeEdge("C", "E");

        cie = instance.incomingEdges("E");
        assertEquals(0, cie.size(), "Incoming edges of vert C should be empty");
    }

    @Test
    void testIncomingEdges2() {
        assertTrue((new MapGraph<>(true)).incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(mapGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertTrue(mapGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "Vert", "Weight");
        assertEquals(1, mapGraph.incomingEdges("Vert").size());
    }

    @Test
    void testIncomingEdges7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "V Dest", "Weight");
        assertTrue(mapGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        assertEquals(1, mapGraph.incomingEdges(new Edge<>("V Orig", "V Dest", "Weight")).size());
    }

    @Test
    void testIncomingEdges9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges(null).isEmpty());
    }

    @Test
    void testIncomingEdges10() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        Edge<Object, Object> vOrig = new Edge<>("V Orig", "V Dest", "Weight");

        mapGraph.addEdge(new Edge<>(vOrig, new Edge<>("V Orig", "V Dest", "Weight"), "Weight"), "V Dest", "Weight");
        assertTrue(mapGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges11() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>(0, "V Dest", "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges(new Edge<>("V Orig", "V Dest", "Weight")).isEmpty());
    }

    @Test
    void testIncomingEdges12() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges(new Edge<>("V Orig", "V Dest", "Weight")).isEmpty());
    }

    @Test
    void testIncomingEdges13() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", 0, "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges(new Edge<>("V Orig", "V Dest", "Weight")).isEmpty());
    }

    @Test
    void testIncomingEdges14() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight"), "Weight");
        assertTrue(mapGraph.incomingEdges(new Edge<>("V Orig", "V Dest", "Weight")).isEmpty());
    }

    @Test
    void testAddVertex() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        assertTrue(mapGraph.addVertex("Vert"));
        assertEquals("Graph: 1 vertices, 0 edges\nVert: \n\n", mapGraph.toString());
    }

    @Test
    void testAddVertex2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertFalse(mapGraph.addVertex("Vert"));
    }

    @Test
    void testAddVertex3() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).addVertex(null));
    }

    @Test
    void testAddEdge() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        assertTrue(mapGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Graph: 2 vertices, 1 edges\nV Orig: \nV Orig -> V Dest\nWeight: Weight\nV Dest: \n\n",
                mapGraph.toString());
        assertEquals(1, mapGraph.numEdges());
    }

    @Test
    void testAddEdge2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        assertTrue(mapGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Graph: 2 vertices, 2 edges\n" + "V Orig: \n" + "V Orig -> V Dest\n" + "Weight: Weight\n"
                + "V Dest: \n" + "V Dest -> V Orig\n" + "Weight: Weight\n", mapGraph.toString());
        assertEquals(2, mapGraph.numEdges());
    }

    @Test
    void testAddEdge3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertFalse(mapGraph.addEdge("V Orig", "V Dest", "Weight"));
    }

    @Test
    void testAddEdge4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(2, "V Dest", "Weight");
        assertTrue(mapGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Graph: 3 vertices, 2 edges\n" + "2: \n" + "2 -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n"
                + "V Orig: \n" + "V Orig -> V Dest\n" + "Weight: Weight\n", mapGraph.toString());
        assertEquals(2, mapGraph.numEdges());
    }

    @Test
    void testAddEdge5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        assertTrue(mapGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Graph: 3 vertices, 2 edges\n" + "V Orig: \n" + "V Orig -> 2\n" + "Weight: WeightV Orig -> V Dest\n"
                + "Weight: Weight\n" + "2: \n" + "\n" + "V Dest: \n" + "\n", mapGraph.toString());
        assertEquals(2, mapGraph.numEdges());
    }

    @Test
    void testAddEdge6() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).addEdge(null, "V Dest", "Weight"));
    }

    @Test
    void testAddEdge7() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).addEdge("V Orig", null, "Weight"));
    }

    @Test
    void testAddEdge8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        assertTrue(mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"));
        assertEquals("Graph: 2 vertices, 2 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n"
                + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "V Dest -> V Orig -> V Dest\n"
                + "Weight: Weight\n" + "Weight: Weight\n", mapGraph.toString());
        assertEquals(2, mapGraph.numEdges());
    }

    @Test
    void testAddEdge9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        assertTrue(mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight"));
        assertEquals("Graph: 2 vertices, 2 edges\n" + "V Orig: \n" + "V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "Weight: Weight\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n"
                + "Weight: Weight -> V Orig\n" + "Weight: Weight\n", mapGraph.toString());
        assertEquals(2, mapGraph.numEdges());
    }


    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));


        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");
        instance.removeVertex("A");
        assertEquals(4, instance.numVertices(), "Num vertices should be 4");
        assertEquals(5, instance.numEdges(), "Num vertices should be 5");
        instance.removeVertex("B");
        assertEquals(3, instance.numVertices(), "Num vertices should be 3");
        assertEquals(4, instance.numEdges(), "Num vertices should be 4");
        instance.removeVertex("C");
        assertEquals(2, instance.numVertices(), "Num vertices should be 2");
        assertEquals(2, instance.numEdges(), "Num vertices should be 2");
        instance.removeVertex("D");
        assertEquals(1, instance.numVertices(), "Num vertices should be 1");
        assertEquals(1, instance.numEdges(), "Num vertices should be 1");
        instance.removeVertex("E");
        assertEquals(0, instance.numVertices(), "Num vertices should be 0");
        assertEquals(0, instance.numEdges(), "Num vertices should be 0");
    }

    @Test
    void testRemoveVertex2() {
        assertFalse((new MapGraph<>(true)).removeVertex("Vert"));
    }

    @Test
    void testRemoveVertex3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("\nGraph not defined!!", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 2 vertices, 1 edges\nV Orig: \nV Orig -> V Dest\nWeight: Weight\nV Dest: \n\n",
                mapGraph.toString());
        assertEquals(1, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n"
                + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n", mapGraph.toString());
        assertEquals(1, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig: \n" + "V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "Weight: Weight\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "\n", mapGraph.toString());
        assertEquals(1, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "Vert", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 1 vertices, 0 edges\nV Orig: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex8() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).removeVertex(null));
    }

    @Test
    void testRemoveVertex9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        mapGraph.addVertex("Vert");
        assertFalse(mapGraph.removeVertex(new Edge<>("V Orig", "V Dest", "Weight")));
    }

    @Test
    void testRemoveVertex10() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "V Dest", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight: \n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight\n" + "V Dest: \n" + "\n", mapGraph.toString());
        assertEquals(1, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex11() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "Vert", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 1 vertices, 0 edges\nV Orig -> V Dest\nWeight: Weight: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex12() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex(new Edge<>("V Orig", "V Dest", "Weight")));
        assertEquals("Graph: 2 vertices, 0 edges\nV Dest: \n\nVert: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex13() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex(new Edge<>("V Orig", "V Dest", "Weight")));
        assertEquals("Graph: 2 vertices, 0 edges\nV Orig: \n\nVert: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveVertex14() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        mapGraph.addEdge("V Orig", "Vert", "Weight");
        mapGraph.addVertex("Vert");
        assertTrue(mapGraph.removeVertex("Vert"));
        assertEquals("Graph: 1 vertices, 0 edges\nV Orig: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("Test removeEdge");

        assertEquals(0, instance.numEdges(), "Num edges should be 0");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(5, instance.numVertices(), "Num edges should be 5");
        assertEquals(8, instance.numEdges(), "Num edges should be 8");

        for (int i = 0; i < co.size() - 1; i++) {
            instance.removeEdge(co.get(i), cd.get(i));
            Collection<Edge<String, Integer>> ced = instance.edges();
            int expected = co.size() - i - 1;
            assertEquals(expected, ced.size(), "Expected size is " + expected);
            for (int j = i + 1; j < co.size(); j++) {
                int finalJ = j;
                ced.removeIf(e -> e.getVOrig().equals(co.get(finalJ)) && e.getVDest().equals(cd.get(finalJ)) && e.getWeight().equals(cw.get(finalJ)));
            }
            assertEquals(0, ced.size(), "Expected size is 0");
        }
    }

    @Test
    void testRemoveEdge2() {
        assertFalse((new MapGraph<>(true)).removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(mapGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Graph: 2 vertices, 0 edges\nV Orig: \n\nV Dest: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveEdge4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(mapGraph.removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge5() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).removeEdge(null, "V Dest"));
    }

    @Test
    void testRemoveEdge6() {
        assertThrows(IllegalArgumentException.class, () -> (new MapGraph<>(true)).removeEdge("V Orig", null));
    }

    @Test
    void testRemoveEdge7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(mapGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Graph: 2 vertices, 0 edges\nV Orig: \n\nV Dest: \n\n", mapGraph.toString());
        assertEquals(0, mapGraph.numEdges());
    }

    @Test
    void testRemoveEdge8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertFalse(mapGraph.removeEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest"));
    }

    @Test
    void testRemoveEdge9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(2, "V Dest", "Weight");
        mapGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(mapGraph.removeEdge("V Orig", "V Dest"));
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");

        Graph<String, Integer> instClone = instance.clone();

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instClone.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
        }

        for (String v : co)
            instClone.removeVertex(v);

        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");
        assertEquals(0, instClone.numVertices(), "Num vertices should be 0");
        assertEquals(0, instClone.numEdges(), "Num vertices should be 0");
    }

    @Test
    void testClone2() {
        MapGraph<Object, Object> actualCloneResult = (new MapGraph<>(true)).clone();
        assertTrue(actualCloneResult.isDirected());
        assertTrue(actualCloneResult.vertices().isEmpty());
        assertEquals("\nGraph not defined!!", actualCloneResult.toString());
        assertEquals(0, actualCloneResult.numEdges());
    }

    @Test
    void testClone3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\nV Orig: \nV Orig -> V Dest\nWeight: Weight\nV Dest: \n\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n"
                + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n", actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig: \n" + "V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "Weight: Weight\n" + "V Orig -> V Dest\n" + "Weight: Weight: \n" + "\n", actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertFalse(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Graph: 2 vertices, 2 edges\n" + "V Orig: \n" + "V Orig -> V Dest\n" + "Weight: Weight\n"
                + "V Dest: \n" + "V Dest -> V Orig\n" + "Weight: Weight\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight: \n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight\n" + "V Dest: \n" + "\n", actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        Edge<Object, Object> vOrig = new Edge<>("V Orig", "V Dest", "Weight");

        mapGraph.addEdge(new Edge<>(vOrig, new Edge<>("V Orig", "V Dest", "Weight"), "Weight"), "V Dest", "Weight");
        MapGraph<Object, Object> actualCloneResult = mapGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals(
                "Graph: 2 vertices, 1 edges\n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Orig -> V Dest\n"
                        + "Weight: Weight\n" + "Weight: Weight: \n" + "V Orig -> V Dest\n" + "Weight: Weight -> V Orig -> V Dest\n"
                        + "Weight: Weight\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "V Dest: \n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testToString() {
        assertEquals("\nGraph not defined!!", (new MapGraph<>(true)).toString());
    }

    @Test
    void testToString2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals("Graph: 2 vertices, 1 edges\nV Orig: \nV Orig -> V Dest\nWeight: Weight\nV Dest: \n\n",
                mapGraph.toString());
    }

    @Test
    void testToString3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new MapVertex<>("Vert"), "V Dest", "Weight");
        assertEquals("Graph: 2 vertices, 1 edges\nVert: \n: \nVert: \n -> V Dest\nWeight: Weight\nV Dest: \n\n",
                mapGraph.toString());
    }

    @Test
    void testToString4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        MapVertex<Object, Object> vOrig = new MapVertex<>("Vert");
        mapGraph.addEdge(vOrig, new MapVertex<>("Vert"), "Weight");
        assertEquals("Graph: 2 vertices, 1 edges\nVert: \n: \nVert: \n -> Vert: \n\nWeight: Weight\nVert: \n: \n\n",
                mapGraph.toString());
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        MapGraph<String, Integer> otherInst = new MapGraph<>(true);
        for (int i = 0; i < co.size(); i++)
            otherInst.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeVertex("A");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeVertex("A");

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeEdge("C", "E");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeEdge("C", "E");

        assertEquals(instance, otherInst, "Graphs should be equal");
    }

    @Test
    public void testUnDirectedGraph() {
        instance = new MapGraph<>(false);

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }

        instance.removeEdge(co.get(0), cd.get(0));

        for (int i = 1; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }
    }
}

