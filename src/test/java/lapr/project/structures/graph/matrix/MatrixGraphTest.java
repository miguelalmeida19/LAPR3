package lapr.project.structures.graph.matrix;

import lapr.project.structures.graph.Edge;
import lapr.project.structures.graph.Graph;
import lapr.project.structures.graph.map.MapGraph;
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
public class MatrixGraphTest {
    final ArrayList<String> co = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "C", "D", "E", "E"));
    final ArrayList<String> cd = new ArrayList<>(Arrays.asList("B", "C", "D", "D", "E", "A", "D", "E"));
    final ArrayList<Integer> cw = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    final ArrayList<String> ov = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
    MatrixGraph<String, Integer> instance = null;

    @BeforeEach
    public void initializeGraph() {
        instance = new MatrixGraph<>(true);
    }

    @Test
    void testConstructor() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor2() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(
                new MapGraph<Object, Object>(false));
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor3() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(
                new MatrixGraph<Object, Object>(true));
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1 \n" + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n"
                        + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor7() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(false);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor8() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor9() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor10() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "2\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |     \n"
                        + " 2 |     |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor12() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(matrixGraph);
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |  X  |     |  X  \n" + " 2 |     |  X  |     \n"
                        + "\n" + "Edges:\n" + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 1 to 0-> V Dest -> 2\n"
                        + "Weight: Weight\n" + "From 1 to 2-> V Dest -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(4, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor13() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(true);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor14() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(true, 1);

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(1, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor15() {
        assertThrows(NegativeArraySizeException.class, () -> new MatrixGraph<Object, Object>(true, -1));

    }

    @Test
    void testConstructor16() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(true, objectList,
                new Object[][]{new Object[]{"42"}});

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertEquals(objectList, actualMatrixGraph.vertices());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor17() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<Object, Object>(true, objectList,
                new Object[][]{new Object[]{"42"}});

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(1, actualMatrixGraph.edgeMatrix.length);
        assertEquals(objectList, actualMatrixGraph.vertices());
        assertEquals("Vertices:\n42\n\nMatrix:\n   |  0 \n 0 |     \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor18() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add("42");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new MatrixGraph<Object, Object>(true, objectList, new Object[][]{new Object[]{"42"}}));

    }

    @Test
    void testConstructor19() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add("42");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new MatrixGraph<Object, Object>(true, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    @Test
    void testConstructor20() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add("42");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new MatrixGraph<Object, Object>(false, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    @Test
    void testConstructor21() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add(null);
        objectList.add("42");
        assertThrows(RuntimeException.class,
                () -> new MatrixGraph<Object, Object>(true, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    @Test
    void testConstructor22() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add(null);
        assertThrows(RuntimeException.class,
                () -> new MatrixGraph<Object, Object>(true, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    @Test
    void testConstructor23() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add("42");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new MatrixGraph<Object, Object>(true, objectList, new Object[][]{new Object[]{"42", null}}));

    }

    @Test
    void testConstructor24() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(new MapGraph<>(true));
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor25() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(new MapGraph<>(false));
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor26() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(new MatrixGraph<>(true));
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor27() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor28() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1 \n" + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor29() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(mapGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n"
                        + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor30() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(false);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(mapGraph);
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor31() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(2, actualMatrixGraph.edgeMatrix.length);
        assertEquals(2, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(1, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor32() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor33() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor34() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(matrixGraph);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals(
                "Vertices:\n" + "2\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |     \n"
                        + " 2 |     |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(2, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor35() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(matrixGraph);
        assertFalse(actualMatrixGraph.isDirected());
        assertEquals(3, actualMatrixGraph.edgeMatrix.length);
        assertEquals(3, actualMatrixGraph.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |  X  |     |  X  \n" + " 2 |     |  X  |     \n"
                        + "\n" + "Edges:\n" + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 1 to 0-> V Dest -> 2\n"
                        + "Weight: Weight\n" + "From 1 to 2-> V Dest -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualMatrixGraph.toString());
        assertEquals(4, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor36() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(true);
        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor37() {
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(true, 1);

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(1, actualMatrixGraph.edgeMatrix.length);
        assertTrue(actualMatrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor38() {
        assertThrows(NegativeArraySizeException.class, () -> new MatrixGraph<>(true, -1));

    }

    @Test
    void testConstructor39() {
        ArrayList<Object> objectList = new ArrayList<>();
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(true, objectList,
                new Object[][]{new Object[]{"42"}});

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(0, actualMatrixGraph.edgeMatrix.length);
        assertEquals(objectList, actualMatrixGraph.vertices());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor40() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        MatrixGraph<Object, Object> actualMatrixGraph = new MatrixGraph<>(true, objectList,
                new Object[][]{new Object[]{"42"}});

        assertTrue(actualMatrixGraph.isDirected());
        assertEquals(1, actualMatrixGraph.edgeMatrix.length);
        assertEquals(objectList, actualMatrixGraph.vertices());
        assertEquals("Vertices:\n42\n\nMatrix:\n   |  0 \n 0 |     \n\nEdges:\n\n", actualMatrixGraph.toString());
        assertEquals(0, actualMatrixGraph.numEdges());
    }

    @Test
    void testConstructor44() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add(null);
        objectList.add("42");
        assertThrows(IllegalArgumentException.class,
                () -> new MatrixGraph<>(true, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    @Test
    void testConstructor45() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        objectList.add(null);
        assertThrows(IllegalArgumentException.class,
                () -> new MatrixGraph<>(true, objectList, new Object[][]{new Object[]{"42", "42"}}));

    }

    /**
     * Test of copy constructor of class Graph.
     */
    @Test
    public void testCopyConstructor() {
        System.out.println("Test copy constructor");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Graph<String, Integer> g = new MatrixGraph<>(instance);
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
        instance = new MatrixGraph<>(false);
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
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.adjVertices("Vert").isEmpty());
    }

    @Test
    void testAdjVertices4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.adjVertices("Vert"));
    }

    @Test
    void testAdjVertices5() {
        assertTrue((new MatrixGraph<>(true)).adjVertices("Vert").isEmpty());
    }

    @Test
    void testAdjVertices6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.adjVertices("Vert").isEmpty());
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
        assertTrue((new MatrixGraph<Object, Object>(true)).edges().isEmpty());
    }

    @Test
    void testEdges3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(1, matrixGraph.edges().size());
    }

    @Test
    void testEdges4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdges7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges8() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdges9() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge(2, new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdges10() {
        assertTrue((new MatrixGraph<>(true)).edges().isEmpty());
    }

    @Test
    void testEdges11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(1, matrixGraph.edges().size());
    }

    @Test
    void testEdges12() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges13() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges14() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdges15() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(2, matrixGraph.edges().size());
    }

    @Test
    void testEdges16() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdges17() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge(2, new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        assertEquals(4, matrixGraph.edges().size());
    }

    @Test
    void testEdge() {
        assertNull((new MatrixGraph<Object, Object>(true)).edge(1, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new MatrixGraph<Object, Object>(true)).edge(-1, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new MatrixGraph<Object, Object>(true)).edge(1, -1));
        assertNull((new MatrixGraph<Object, Object>(true)).edge("V Orig", "V Dest"));
        assertNull((new MatrixGraph<>(true)).edge(1, 1));
        assertNull((new MatrixGraph<>(true)).edge("V Orig", "V Dest"));
    }

    @Test
    void testEdge2() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertNull(matrixGraph.edge(1, 1));
    }

    @Test
    void testEdge3() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by edge(Object, Object)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.edge("V Orig", "V Dest");
    }

    @Test
    void testEdge4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertNull(matrixGraph.edge("V Orig", "V Dest"));
    }

    @Test
    void testEdge5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertNull(matrixGraph.edge(1, 1));
    }

    @Test
    void testEdge8() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by edge(Object, Object)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.edge("V Orig", "V Dest");
    }

    @Test
    void testEdge9() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertNull(matrixGraph.edge("V Orig", "V Dest"));
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
        assertEquals(-1, (new MatrixGraph<Object, Object>(true)).outDegree("Vert"));
    }

    @Test
    void testOutDegree3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(0, matrixGraph.outDegree("Vert"));
    }

    @Test
    void testOutDegree4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.outDegree("Vert"));
    }

    @Test
    void testOutDegree5() {
        assertEquals(-1, (new MatrixGraph<>(true)).outDegree("Vert"));
    }

    @Test
    void testOutDegree6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(0, matrixGraph.outDegree("Vert"));
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
        assertEquals(-1, (new MatrixGraph<Object, Object>(true)).inDegree("Vert"));
    }

    @Test
    void testInDegree3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(0, matrixGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex(2);

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.inDegree("Vert"));
    }

    @Test
    void testInDegree6() {
        assertEquals(-1, (new MatrixGraph<>(true)).inDegree("Vert"));
    }

    @Test
    void testInDegree7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertEquals(0, matrixGraph.inDegree("Vert"));
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
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new MatrixGraph<Object, Object>(true)).outgoingEdges("Vert"));
    }

    @Test
    void testOutgoingEdges3() {
        assertTrue((new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true))).outgoingEdges("Vert").isEmpty());
    }

    @Test
    void testOutgoingEdges4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.outgoingEdges("Vert").isEmpty());
    }

    @Test
    void testOutgoingEdges6() {
        assertTrue((new MatrixGraph<>(new MapGraph<>(true))).outgoingEdges("Vert").isEmpty());
    }

    @Test
    void testOutgoingEdges7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.outgoingEdges("Vert").isEmpty());
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
        assertTrue((new MatrixGraph<Object, Object>(true)).incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.incomingEdges("Vert"));
    }

    @Test
    void testIncomingEdges5() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex(2);

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.incomingEdges("Vert"));
    }

    @Test
    void testIncomingEdges6() {
        assertTrue((new MatrixGraph<>(true)).incomingEdges("Vert").isEmpty());
    }

    @Test
    void testIncomingEdges7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.incomingEdges("Vert").isEmpty());
    }

    @Test
    void testAddVertex() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals("Vertices:\nVert\n\nMatrix:\n   |  0 \n 0 |     \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testAddVertex2() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals(0, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testAddVertex3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertFalse(matrixGraph.addVertex("Vert"));
    }

    @Test
    void testAddVertex4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals(3, matrixGraph.edgeMatrix.length);
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "Vert\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n", matrixGraph.toString());
    }

    @Test
    void testAddVertex5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals("Vertices:\nVert\n\nMatrix:\n   |  0 \n 0 |     \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testAddVertex6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(new MapGraph<>(true));
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals(0, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testAddVertex7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertFalse(matrixGraph.addVertex("Vert"));
    }

    @Test
    void testAddVertex8() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(mapGraph);
        assertTrue(matrixGraph.addVertex("Vert"));
        assertEquals(3, matrixGraph.edgeMatrix.length);
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "Vert\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n", matrixGraph.toString());
    }

    @Test
    void testResizeMatrix() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.resizeMatrix();
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testResizeMatrix2() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        matrixGraph.resizeMatrix();
        assertEquals(0, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testResizeMatrix3() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex(2);

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        matrixGraph.resizeMatrix();
        assertEquals(1, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testResizeMatrix4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.resizeMatrix();
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testResizeMatrix5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(new MapGraph<>(true));
        matrixGraph.addVertex("Vert");
        matrixGraph.resizeMatrix();
        assertEquals(0, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testResizeMatrix6() {
        MapGraph<Object, Object> mapGraph = new MapGraph<>(true);
        mapGraph.addVertex(2);

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(mapGraph);
        matrixGraph.addVertex("Vert");
        matrixGraph.resizeMatrix();
        assertEquals(1, matrixGraph.edgeMatrix.length);
    }

    @Test
    void testAddEdge() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
        assertEquals(1, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge2() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge3() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new MatrixGraph<Object, Object>(true, 1)).addEdge("V Orig", "V Dest", "Weight"));
    }

    @Test
    void testAddEdge4() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true))).addEdge("V Orig", "V Dest",
                        "Weight"));
    }

    @Test
    void testAddEdge5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertFalse(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
    }

    @Test
    void testAddEdge6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge8() {
        assertThrows(RuntimeException.class,
                () -> (new MatrixGraph<Object, Object>(true)).addEdge(null, "V Dest", "Weight"));
    }

    @Test
    void testAddEdge9() {
        assertThrows(RuntimeException.class,
                () -> (new MatrixGraph<Object, Object>(true)).addEdge("V Orig", null, "Weight"));
    }

    @Test
    void testAddEdge10() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
        assertEquals(1, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge14() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertFalse(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
    }

    @Test
    void testAddEdge15() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge16() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertTrue(matrixGraph.addEdge("V Orig", "V Dest", "Weight"));
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", matrixGraph.toString());
        assertEquals(2, matrixGraph.numEdges());
    }

    @Test
    void testAddEdge17() {
        assertThrows(IllegalArgumentException.class, () -> (new MatrixGraph<>(true)).addEdge(null, "V Dest", "Weight"));
    }

    @Test
    void testAddEdge18() {
        assertThrows(IllegalArgumentException.class, () -> (new MatrixGraph<>(true)).addEdge("V Orig", null, "Weight"));
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
        assertFalse((new MatrixGraph<Object, Object>(true)).removeVertex("Vert"));
    }

    @Test
    void testRemoveVertex3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testRemoveVertex4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.removeVertex("Vert"));
    }

    @Test
    void testRemoveVertex5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testRemoveVertex6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addVertex("Vert");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
    }

    @Test
    void testRemoveVertex7() {
        assertFalse((new MatrixGraph<>(true)).removeVertex("Vert"));
    }

    @Test
    void testRemoveVertex8() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testRemoveVertex10() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addVertex("Vert");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
    }

    @Test
    void testRemoveVertex11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addVertex("Vert");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeVertex("Vert"));
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
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
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.removeEdge(1, 1);
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.removeEdge(1, 1);
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge4() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new MatrixGraph<Object, Object>(true, 1)).removeEdge(1, 1));
    }

    @Test
    void testRemoveEdge5() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new MatrixGraph<Object, Object>(true)).removeEdge(1, -1));
    }

    @Test
    void testRemoveEdge6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.removeEdge(0, 1);
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.removeEdge(0, 1);
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge8() {
        assertFalse((new MatrixGraph<Object, Object>(true)).removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge9() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge10() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(matrixGraph.removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge12() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(matrixGraph.removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge13() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.removeEdge(1, 1);
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge14() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.removeEdge(1, 1);
        assertEquals(MatrixGraph.INITIAL_CAPACITY, matrixGraph.edgeMatrix.length);
        assertTrue(matrixGraph.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge17() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.removeEdge(0, 1);
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge18() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.removeEdge(0, 1);
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge19() {
        assertFalse((new MatrixGraph<>(true)).removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge20() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge21() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(matrixGraph.removeEdge("V Orig", "V Dest"));
    }

    @Test
    void testRemoveEdge22() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertTrue(matrixGraph.removeEdge("V Orig", "V Dest"));
        assertEquals("Vertices:\nV Orig\nV Dest\n\nMatrix:\n   |  0  |  1 \n 0 |     |     \n 1 |     |     \n\nEdges:\n\n",
                matrixGraph.toString());
        assertEquals(0, matrixGraph.numEdges());
    }

    @Test
    void testRemoveEdge23() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", 2, "Weight");
        assertFalse(matrixGraph.removeEdge("V Orig", "V Dest"));
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
        MatrixGraph<Object, Object> actualCloneResult = (new MatrixGraph<Object, Object>(true)).clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertTrue(actualCloneResult.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualCloneResult.toString());
        assertEquals(0, actualCloneResult.numEdges());
    }

    @Test
    void testClone3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone4() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1 \n" + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n"
                        + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone6() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone7() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone8() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertFalse(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone9() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |     \n"
                        + " 2 |     |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone10() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "Weight");
        matrixGraph.addEdge(new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n"
                + "Matrix:\n" + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |  X  \n"
                + " 2 |     |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Orig -> V Dest\n"
                + "Weight: Weight\n" + "Weight: Weight\n" + "From 1 to 2-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight\n" + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone11() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", new Edge<Object, Object>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n"
                        + " 2 |     |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                        + "From 0 to 2-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone12() {
        MatrixGraph<Object, Object> actualCloneResult = (new MatrixGraph<>(true)).clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertTrue(actualCloneResult.vertices().isEmpty());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", actualCloneResult.toString());
        assertEquals(0, actualCloneResult.numEdges());
    }

    @Test
    void testClone13() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone14() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1 \n" + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone15() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n"
                        + " 0 |     |  X  \n" + " 1 |     |     \n" + "\n" + "Edges:\n"
                        + "From 0 to 1-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(1, actualCloneResult.numEdges());
    }

    @Test
    void testClone16() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge(2, "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "2\n" + "V Dest\n" + "V Orig\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n" + " 1 |     |     |     \n" + " 2 |     |  X  |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> 2 -> V Dest\n" + "Weight: Weight\n" + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone17() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", 2, "Weight");
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "2\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n" + " 2 |     |     |     \n" + "\n" + "Edges:\n"
                + "From 0 to 1-> V Orig -> 2\n" + "Weight: Weight\n" + "From 0 to 2-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone18() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(false);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertFalse(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(2, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                + " 1 |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                + "From 1 to 0-> V Dest -> V Orig\n" + "Weight: Weight\n" + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone19() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |     \n"
                        + " 2 |     |  X  |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                        + "From 2 to 1-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone20() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        matrixGraph.addEdge(new Edge<>("V Orig", "V Dest", "Weight"), "V Dest", "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals("Vertices:\n" + "V Orig\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "V Dest\n" + "\n"
                + "Matrix:\n" + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |     \n" + " 1 |     |     |  X  \n"
                + " 2 |     |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Orig -> V Dest\n"
                + "Weight: Weight\n" + "Weight: Weight\n" + "From 1 to 2-> V Orig -> V Dest\n" + "Weight: Weight -> V Dest\n"
                + "Weight: Weight\n" + "\n", actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testClone21() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        matrixGraph.addEdge("V Orig", new Edge<>("V Orig", "V Dest", "Weight"), "Weight");
        MatrixGraph<Object, Object> actualCloneResult = matrixGraph.clone();
        assertTrue(actualCloneResult.isDirected());
        assertEquals(MatrixGraph.INITIAL_CAPACITY, actualCloneResult.edgeMatrix.length);
        assertEquals(3, actualCloneResult.vertices().size());
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "V Orig -> V Dest\n" + "Weight: Weight\n" + "\n" + "Matrix:\n"
                        + "   |  0  |  1  |  2 \n" + " 0 |     |  X  |  X  \n" + " 1 |     |     |     \n"
                        + " 2 |     |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n"
                        + "From 0 to 2-> V Orig -> V Orig -> V Dest\n" + "Weight: Weight\n" + "Weight: Weight\n" + "\n",
                actualCloneResult.toString());
        assertEquals(2, actualCloneResult.numEdges());
    }

    @Test
    void testToString() {
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", (new MatrixGraph<Object, Object>(true)).toString());
        assertEquals("Vertices:\n\nMatrix:\n  \n\nEdges:\n\n", (new MatrixGraph<>(true)).toString());
    }

    @Test
    void testToString2() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
    }

    @Test
    void testToString3() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(new MapGraph<Object, Object>(true));
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.toString());
    }

    @Test
    void testToString4() {
        MapGraph<Object, Object> mapGraph = new MapGraph<Object, Object>(true);
        mapGraph.addVertex(2);

        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<Object, Object>(mapGraph);
        matrixGraph.addVertex("Vert");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrixGraph.toString());
    }

    @Test
    void testToString5() {
        MatrixGraph<Object, Object> matrixGraph = new MatrixGraph<>(true);
        matrixGraph.addEdge("V Orig", "V Dest", "Weight");
        assertEquals(
                "Vertices:\n" + "V Orig\n" + "V Dest\n" + "\n" + "Matrix:\n" + "   |  0  |  1 \n" + " 0 |     |  X  \n"
                        + " 1 |     |     \n" + "\n" + "Edges:\n" + "From 0 to 1-> V Orig -> V Dest\n" + "Weight: Weight\n" + "\n",
                matrixGraph.toString());
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        MatrixGraph<String, Integer> otherInst = new MatrixGraph<>(true);
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
        instance = new MatrixGraph<>(false);

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