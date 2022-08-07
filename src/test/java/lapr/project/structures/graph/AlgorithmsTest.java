package lapr.project.structures.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;

import lapr.project.model.Capital;
import lapr.project.model.Port;
import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Test;

class AlgorithmsTest {
    @Test
    void testBreadthFirstSearch() {
        assertNull(Algorithms.<Object, Object>BreadthFirstSearch(new MapGraph<Object, Object>(true), "Vert"));
        assertNull(Algorithms.<Object, Object>BreadthFirstSearch(new MatrixGraph<Object, Object>(true), "Vert"));
    }

    @Test
    void testBreadthFirstSearch2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>BreadthFirstSearch(mapGraph, "Vert").size());
    }

    @Test
    void testBreadthFirstSearch3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>BreadthFirstSearch(matrixGraph, "Vert").size());
    }

    @Test
    void testBreadthFirstSearch4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>BreadthFirstSearch(matrixGraph, "Vert").size());
    }

    @Test
    void testDepthFirstSearch() {
        assertNull(Algorithms.<Object, Object>DepthFirstSearch(new MapGraph<Object, Object>(true), "Vert"));
        assertNull(Algorithms.<Object, Object>DepthFirstSearch(new MatrixGraph<Object, Object>(true), "Vert"));
    }

    @Test
    void testDepthFirstSearch2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>DepthFirstSearch(mapGraph, "Vert").size());
    }

    @Test
    void testDepthFirstSearch3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>DepthFirstSearch(matrixGraph, "Vert").size());
    }

    @Test
    void testDepthFirstSearch4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        assertEquals(1, Algorithms.<Object, Object>DepthFirstSearch(matrixGraph, "Vert").size());
    }

    @Test
    void testDepthFirstSearch5() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>DepthFirstSearch(g, "V Orig",
                new boolean[]{true, true, true, true}, new LinkedList<Object>()));
    }

    @Test
    void testDepthFirstSearch6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>DepthFirstSearch(mapGraph, "V Orig", new boolean[]{true, true, true, true}, objectList);
        assertEquals(1, objectList.size());
    }

    @Test
    void testDepthFirstSearch7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>DepthFirstSearch(mapGraph, "V Orig", new boolean[]{true, false, true, true}, objectList);
        assertEquals(2, objectList.size());
    }

    @Test
    void testDepthFirstSearch8() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(false);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>DepthFirstSearch(g, "42",
                new boolean[]{true, true, true, true}, new LinkedList<Object>()));
    }

    @Test
    void testAllPaths() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        LinkedList<Object> path = new LinkedList<Object>();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>allPaths(g, "V Orig", "V Dest",
                new boolean[]{true, true, true, true}, path, new ArrayList<LinkedList<Object>>()));
    }

    @Test
    void testAllPaths2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<LinkedList<Object>>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, true, true, true}, objectList,
                linkedListList);
        assertTrue(objectList.isEmpty());
        assertEquals(1, linkedListList.size());
        assertEquals(2, linkedListList.get(0).size());
    }

    @Test
    void testAllPaths3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, true, true, true}, objectList,
                new ArrayList<LinkedList<Object>>());
        assertTrue(objectList.isEmpty());
    }

    @Test
    void testAllPaths4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, false, true, true},
                objectList, new ArrayList<LinkedList<Object>>());
        assertTrue(objectList.isEmpty());
    }

    @Test
    void testAllPaths5() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(false);
        LinkedList<Object> path = new LinkedList<Object>();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>allPaths(g, "42", "42",
                new boolean[]{true, true, true, true}, path, new ArrayList<LinkedList<Object>>()));
    }

    @Test
    void testAllPaths6() {
        assertTrue(Algorithms.<Object, Object>allPaths(new MapGraph<Object, Object>(true), "V Orig", "V Dest").isEmpty());
    }

    @Test
    void testAllPaths7() {
        assertTrue(
                Algorithms.<Object, Object>allPaths(new MatrixGraph<Object, Object>(true), "V Orig", "V Dest").isEmpty());
    }

    @Test
    void testAllPaths8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        ArrayList<LinkedList<Object>> actualAllPathsResult = Algorithms.<Object, Object>allPaths(mapGraph, "V Orig",
                "V Dest");
        assertEquals(1, actualAllPathsResult.size());
        assertEquals(2, actualAllPathsResult.get(0).size());
    }

    @Test
    void testAllPaths9() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        assertTrue(Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest").isEmpty());
    }

    @Test
    void testAllPaths10() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        ArrayList<LinkedList<Object>> actualAllPathsResult = Algorithms.<Object, Object>allPaths(mapGraph, "V Orig",
                "V Dest");
        assertEquals(1, actualAllPathsResult.size());
        assertEquals(2, actualAllPathsResult.get(0).size());
    }

    @Test
    void testAllPaths11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        ArrayList<LinkedList<Object>> actualAllPathsResult = Algorithms.<Object, Object>allPaths(matrixGraph, "V Orig",
                "V Dest");
        assertEquals(1, actualAllPathsResult.size());
        assertEquals(2, actualAllPathsResult.get(0).size());
    }

    @Test
    void testAllPaths12() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(false);
        mapGraph.addEdge("V Orig", 2, "Weight");
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        ArrayList<LinkedList<Object>> actualAllPathsResult = Algorithms.<Object, Object>allPaths(mapGraph, "V Orig",
                "V Dest");
        assertEquals(1, actualAllPathsResult.size());
        assertEquals(2, actualAllPathsResult.get(0).size());
    }

    @Test
    void testAllPaths13() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(new MapGraph<Object, Object>(true));
        mapGraph.addVertex("Vert");
        assertTrue(Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest").isEmpty());
    }

    @Test
    void testAllPaths14() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        LinkedList<Object> path = new LinkedList<Object>();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>allPaths(g, "V Orig", "V Dest",
                new boolean[]{true, true, true, true}, path, new ArrayList<LinkedList<Object>>()));
    }

    @Test
    void testAllPaths15() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<LinkedList<Object>>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, true, true, true}, objectList,
                linkedListList);
        assertTrue(objectList.isEmpty());
        assertEquals(1, linkedListList.size());
        assertEquals(2, linkedListList.get(0).size());
    }

    @Test
    void testAllPaths16() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, true, true, true}, objectList,
                new ArrayList<LinkedList<Object>>());
        assertTrue(objectList.isEmpty());
    }

    @Test
    void testAllPaths17() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", 2, "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>allPaths(mapGraph, "V Orig", "V Dest", new boolean[]{true, false, true, true},
                objectList, new ArrayList<LinkedList<Object>>());
        assertTrue(objectList.isEmpty());
    }

    @Test
    void testAllPaths18() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(false);
        LinkedList<Object> path = new LinkedList<Object>();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>allPaths(g, "42", "42",
                new boolean[]{true, true, true, true}, path, new ArrayList<LinkedList<Object>>()));
    }

    @Test
    void testShortestPathLength() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Algorithms.<Object, Object>shortestPathLength(new MapGraph<Object, Object>(true), "V Orig",
                        new boolean[]{true, true, true, true}, new int[]{1, 1, 1, 1}, new double[]{10.0, 10.0, 10.0, 10.0}));
    }

    @Test
    void testShortestPathLength2() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by shortestPathLength(Graph, Object, boolean[], int[], double[])
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        Algorithms.<Object, Object>shortestPathLength(mapGraph, "V Orig", new boolean[]{true, true, true, true},
                new int[]{1, 1, 1, 1}, new double[]{10.0, 10.0, 10.0, 10.0});
    }

    @Test
    void testShortestPathLength3() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by shortestPathLength(Graph, Object, boolean[], int[], double[])
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 0.0);
        Algorithms.<Object, Object>shortestPathLength(mapGraph, "V Orig", new boolean[]{true, false, true, true},
                new int[]{1, 1, 1, 1}, new double[]{10.0, 10.0, 10.0, 10.0});
    }

    @Test
    void testShortestPathLength4() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by shortestPathLength(Graph, Object, boolean[], int[], double[])
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 10.0);
        Algorithms.<Object, Object>shortestPathLength(mapGraph, "V Orig", new boolean[]{true, false, true, true},
                new int[]{1, 1, 1, 1}, new double[]{10.0, 10.0, 10.0, 10.0});
    }

    @Test
    void testShortestPathLength5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>shortestPathLength(mapGraph,
                "V Orig", new boolean[]{}, new int[]{1, 1, 1, 1}, new double[]{10.0, 10.0, 10.0, 10.0}));
    }

    @Test
    void testGetPath() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>getPath(g, "V Orig", "V Dest",
                new Object[]{"Verts"}, new int[]{1, 1, 1, 1}, new LinkedList<Object>()));
    }

    @Test
    void testGetPath2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>getPath(mapGraph, "V Orig",
                "V Dest", new Object[]{"Verts"}, new int[]{1, 1, 1, 1}, new LinkedList<Object>()));
    }

    @Test
    void testGetPath3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>getPath(mapGraph, "V Orig",
                "V Dest", new Object[]{"Verts", "Verts"}, new int[]{1, 1, 1, 1}, new LinkedList<Object>()));
    }

    @Test
    void testGetPath4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        LinkedList<Object> objectList = new LinkedList<Object>();
        Algorithms.<Object, Object>getPath(mapGraph, "V Orig", "V Dest", new Object[]{"Verts"}, new int[]{1, -1, 1, 1},
                objectList);
        assertEquals(1, objectList.size());
    }

    @Test
    void testGetPath5() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(false);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Algorithms.<Object, Object>getPath(g, "42", "42",
                new Object[]{"42"}, new int[]{1, 1, 1, 1}, new LinkedList<Object>()));
    }

    @Test
    void testShortestPath() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        assertEquals(0.0, Algorithms.<Object, Object>shortestPath(g, "V Orig", "V Dest", new LinkedList<Object>()));
    }

    @Test
    void testShortestPath2() {
        MatrixGraph<Object, Object> g = new MatrixGraph<Object, Object>(true);
        assertEquals(0.0, Algorithms.<Object, Object>shortestPath(g, "V Orig", "V Dest", new LinkedList<Object>()));
    }

    @Test
    void testShortestPath3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(0.0,
                Algorithms.<Object, Object>shortestPath(mapGraph, "V Orig", Double.MAX_VALUE, new LinkedList<Object>()));
    }

    @Test
    void testShortestPath4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", Double.MAX_VALUE);
        LinkedList<Object> objectList = new LinkedList<Object>();
        assertEquals(0.0, Algorithms.<Object, Object>shortestPath(mapGraph, "V Orig", "V Dest", objectList));
        assertTrue(objectList.isEmpty());
    }

    @Test
    void testShortestPath5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 0.0);
        LinkedList<Object> objectList = new LinkedList<Object>();
        assertEquals(0.0, Algorithms.<Object, Object>shortestPath(mapGraph, "V Orig", "V Dest", objectList));
        assertEquals(2, objectList.size());
    }

    @Test
    void testShortestPath6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 10.0);
        LinkedList<Object> objectList = new LinkedList<Object>();
        assertEquals(10.0, Algorithms.<Object, Object>shortestPath(mapGraph, "V Orig", "V Dest", objectList));
        assertEquals(2, objectList.size());
    }

    @Test
    void testShortestPaths() {
        MapGraph<Object, Object> g = new MapGraph<Object, Object>(true);
        ArrayList<LinkedList<Object>> paths = new ArrayList<LinkedList<Object>>();
        assertFalse(Algorithms.<Object, Object>shortestPaths(g, "V Orig", paths, new ArrayList<Double>()));
    }

    @Test
    void testShortestPaths2() {
        MatrixGraph<Object, Object> g = new MatrixGraph<Object, Object>(true);
        ArrayList<LinkedList<Object>> paths = new ArrayList<LinkedList<Object>>();
        assertFalse(Algorithms.<Object, Object>shortestPaths(g, "V Orig", paths, new ArrayList<Double>()));
    }

    @Test
    void testShortestPaths3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", Double.MAX_VALUE);
        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<LinkedList<Object>>();
        ArrayList<Double> resultDoubleList = new ArrayList<Double>();
        assertTrue(Algorithms.<Object, Object>shortestPaths(mapGraph, "V Orig", linkedListList, resultDoubleList));
        assertEquals(2, linkedListList.size());
        assertEquals(1, linkedListList.get(0).size());
        assertEquals(2, resultDoubleList.size());
        assertEquals(0.0, resultDoubleList.get(0));
        assertEquals(Double.MAX_VALUE, resultDoubleList.get(1));
    }

    @Test
    void testShortestPaths4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 0.0);
        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<LinkedList<Object>>();
        ArrayList<Double> resultDoubleList = new ArrayList<Double>();
        assertTrue(Algorithms.<Object, Object>shortestPaths(mapGraph, "V Orig", linkedListList, resultDoubleList));
        assertEquals(2, linkedListList.size());
        assertEquals(1, linkedListList.get(0).size());
        assertEquals(2, linkedListList.get(1).size());
        assertEquals(2, resultDoubleList.size());
        assertEquals(0.0, resultDoubleList.get(0));
        assertEquals(0.0, resultDoubleList.get(1));
    }

    @Test
    void testShortestPaths5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", 10.0);
        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<LinkedList<Object>>();
        ArrayList<Double> resultDoubleList = new ArrayList<Double>();
        assertTrue(Algorithms.<Object, Object>shortestPaths(mapGraph, "V Orig", linkedListList, resultDoubleList));
        assertEquals(2, linkedListList.size());
        assertEquals(1, linkedListList.get(0).size());
        assertEquals(2, linkedListList.get(1).size());
        assertEquals(2, resultDoubleList.size());
        assertEquals(0.0, resultDoubleList.get(0));
        assertEquals(10.0, resultDoubleList.get(1));
    }

    @Test
    void testRevPath() {
        assertTrue(Algorithms.<Object>revPath(new LinkedList<Object>()).isEmpty());
    }

    @Test
    void testRevPath2() {
        LinkedList<Object> objectList = new LinkedList<Object>();
        objectList.add("42");
        assertEquals(1, Algorithms.<Object>revPath(objectList).size());
    }

    @Test
    void pathfinder() {
        Integer[][] a = {
                // a  b  c  d  e  f  g
                {null, 1, 1, 3, null, 2, null},
                {1, null, null, 2, null, null, null},
                {1, null, null, 4, 1, 1, null},
                {3, 2, 4, null, null, null, null},
                {null, null, 1, null, null, null, null},
                {2, null, 1, null, null, null, 1},
                {null, null, null, null, null, 1, null}
        };


        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");
        vertices.add("F");
        vertices.add("G");

        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);

        LinkedList<String> expected = new LinkedList<>();
        expected.add("A");
        expected.add("B");
        expected.add("D");
        expected.add("C");
        expected.add("F");
        expected.add("A");
        assertEquals(Algorithms.pathfinder(mg, "A"), expected);
    }

    @Test
    void pathfinder2() {
        Integer[][] a = {
                //a  b  c  d
                {null, 12, 1, null},
                {12, null, 1, 2},
                {1, 1, null, null},
                {null, null, 1, null}
        };

        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");


        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);
        System.out.println(Algorithms.pathfinder(mg, "A"));
        LinkedList<String> expected = new LinkedList<>();
        expected.add("A");

        expected.add("C");
        expected.add("B");


        expected.add("A");
        assertEquals(Algorithms.pathfinder(mg, "A"), expected);
    }

    @Test
    void pathfinder3() {


        Integer[][] a = {
                //a  b  c  d
                {null, 12, 1, 1},
                {12, null, 1, 24},
                {1, 1, null, 1},
                {1, 24, 1, null}
        };


        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");


        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);
        System.out.println(Algorithms.pathfinder(mg, "A"));
        LinkedList<String> expected = new LinkedList<>();
        expected.add("A");

        expected.add("C");
        expected.add("B");
        expected.add("D");


        expected.add("A");
        System.out.println(Algorithms.pathfinder(mg, "A"));
        assertEquals(Algorithms.pathfinder(mg, "A"), expected);
    }

    @Test
    void shortestPathUsingNplaces() {

        Capital A = new Capital("Europe", "Europe", "A", 12.1, 12.1);
        Capital B = new Capital("Europe", "Europe", "B", 12.1, 12.1);
        Port C = new Port("Europe", "Europe", "C", "C", 12.1, 12.1);
        Capital D = new Capital("Europe", "Europe", "D", 12.1, 12.1);
        Port E = new Port("Europe", "Europe", "E", "E", 12.1, 12.1);


        Integer[][] a = {
                //a  b  c  d, e
                {null, 1, null, null, 1},
                {1, null, 20, 1, null},
                {null, 20, null, null, 10},
                {null, 1, null, null, null},
                {1, null, 10, null, null}
        };

        ArrayList<Object> vertices = new ArrayList<>();
        vertices.add(A);
        vertices.add(B);
        vertices.add(C);
        vertices.add(D);
        vertices.add(E);

        MatrixGraph<Object, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<Object, Integer> mg = new MapGraph<>(ab);

        LinkedList<Object> Places = new LinkedList<>();
        Places.add(D);


        System.out.println(Algorithms.shortestPathUsingNplaces(mg, A, E, Places, true, true));
    }

    @Test
    void testShortestPathUsingNplaces() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(
                Algorithms.shortestPathUsingNplaces(mapGraph, "V Orig", "V Dest", new LinkedList<>(), true, true).isEmpty());
    }

    @Test
    void testShortestPathUsingNplaces2() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Port("White", "GB", "White", "White", 10.0, 10.0), "Weight");
        assertTrue(
                Algorithms.shortestPathUsingNplaces(mapGraph, "V Orig", "V Dest", new LinkedList<>(), true, true).isEmpty());
    }

    @Test
    void testShortestPathUsingNplaces3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Capital("GB", "White", "White", 10.0, 10.0), "Weight");
        assertTrue(
                Algorithms.shortestPathUsingNplaces(mapGraph, "V Orig", "V Dest", new LinkedList<>(), true, true).isEmpty());
    }

    @Test
    void testShortestPathUsingNplaces4() {
        MatrixGraph<Object, Object> g = new MatrixGraph<>(true);
        assertTrue(Algorithms.shortestPathUsingNplaces(g, "V Dest", "V Dest", new LinkedList<>(), true, true).isEmpty());
    }

    @Test
    void testFunction() {
        MatrixGraph<Object, Object> g = new MatrixGraph<>(true);
        LinkedList<Object> Places = new LinkedList<>();
        assertTrue(
                Algorithms
                        .function(g, "V Dest", "V Dest", Places, new LinkedList<>(), new String[]{"Color"},
                                true, true)
                        .isEmpty());
    }

    @Test
    void testCheckPlaces() {
        LinkedList<Object> Places = new LinkedList<>();
        assertTrue(Algorithms.checkPlaces(Places, new LinkedList<>()));
    }

    @Test
    void testCheckPlaces2() {

        LinkedList<Object> objectList = new LinkedList<>();
        objectList.add("42");
        assertFalse(Algorithms.checkPlaces(objectList, new LinkedList<>()));
    }

    @Test
    void testValidateRestrictions() {
        assertFalse(Algorithms.validateRestrictions("Dest", "Current", "V Dest", true, true));
        assertFalse(Algorithms.validateRestrictions(new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), "Current",
                "V Dest", true, true));
        assertFalse(Algorithms.validateRestrictions(new Capital("GB", "Continent", "Capital", 10.0, 10.0), "Current",
                "V Dest", true, true));
        assertFalse(Algorithms.validateRestrictions("Dest", "Current", "V Dest", true, false));
    }

    @Test
    void testValidateRestrictions2() {
        Port dest = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertTrue(Algorithms.validateRestrictions(dest, new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), "V Dest",
                true, true));
    }

    @Test
    void testValidateRestrictions3() {
        Port dest = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertTrue(Algorithms.validateRestrictions(dest, new Capital("GB", "Continent", "Capital", 10.0, 10.0), "V Dest",
                true, true));
    }

    @Test
    void testValidateRestrictions4() {
        Port dest = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertTrue(Algorithms.validateRestrictions(dest, "Current", new Port("Continent", "GB", "Code", "Name", 10.0, 10.0),
                true, true));
    }

    @Test
    void testValidateRestrictions5() {
        Capital dest = new Capital("GB", "Continent", "Capital", 10.0, 10.0);

        assertTrue(Algorithms.validateRestrictions(dest, new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), "V Dest",
                true, true));
    }

    @Test
    void testValidateRestrictions6() {
        Capital dest = new Capital("GB", "Continent", "Capital", 10.0, 10.0);

        assertTrue(Algorithms.validateRestrictions(dest, new Capital("GB", "Continent", "Capital", 10.0, 10.0), "V Dest",
                true, true));
    }

    @Test
    void testValidateRestrictions7() {
        Port dest = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertFalse(Algorithms.validateRestrictions(dest, new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), "V Dest",
                false, true));
    }

    @Test
    void testValidateRestrictions8() {
        Port dest = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertFalse(Algorithms.validateRestrictions(dest, "Current",
                new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), false, true));
    }
}

