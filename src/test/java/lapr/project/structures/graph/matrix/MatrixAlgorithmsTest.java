package lapr.project.structures.graph.matrix;

import lapr.project.structures.graph.Algorithms;
import lapr.project.structures.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MatrixAlgorithmsTest {

    final Graph<String,Double> completeMap = new MatrixGraph<>(false);
    Graph<String,Double> incompleteMap = new MatrixGraph<>(false);

    public MatrixAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Porto");
        completeMap.addVertex("Braga");
        completeMap.addVertex("Vila Real");
        completeMap.addVertex("Aveiro");
        completeMap.addVertex("Coimbra");
        completeMap.addVertex("Leiria");

        completeMap.addVertex("Viseu");
        completeMap.addVertex("Guarda");
        completeMap.addVertex("Castelo Branco");
        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Faro");

        completeMap.addEdge("Porto", "Aveiro", 75.0);
        completeMap.addEdge("Porto", "Braga", 60.0);
        completeMap.addEdge("Porto", "Vila Real", 100.0);
        completeMap.addEdge("Viseu", "Guarda", 75.0);
        completeMap.addEdge("Guarda", "Castelo Branco", 100.0);
        completeMap.addEdge("Aveiro", "Coimbra", 60.0);
        completeMap.addEdge("Coimbra", "Lisboa", 200.0);
        completeMap.addEdge("Coimbra", "Leiria", 80.0);
        completeMap.addEdge("Aveiro", "Leiria", 120.0);
        completeMap.addEdge("Leiria", "Lisboa", 150.0);

        incompleteMap = completeMap.clone();

        completeMap.addEdge("Aveiro", "Viseu", 85.0);
        completeMap.addEdge("Leiria", "Castelo Branco", 170.0);
        completeMap.addEdge("Lisboa", "Faro", 280.0);
    }

    private void checkContentEquals(List<String> l1, List<String> l2, String msg) {
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(l1, l2, msg);
    }

    /**
     * Test of BreadthFirstSearch method, of class Algorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        Assertions.assertNull(Algorithms.BreadthFirstSearch(completeMap, "LX"), "Should be null if vertex does not exist");

        LinkedList<String> path = Algorithms.BreadthFirstSearch(incompleteMap, "Faro");

        assertEquals(1, path.size(), "Should be just one");

        assertEquals("Faro", path.peekFirst());

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Porto");
        assertEquals(7, path.size(), "Should give seven vertices");

        assertEquals("Porto", path.removeFirst(), "BreathFirst Porto");

        LinkedList<String> expected = new LinkedList<>(Arrays.asList("Aveiro", "Braga", "Vila Real"));
        checkContentEquals(expected, path.subList(0, 3), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Coimbra", "Leiria"));
        checkContentEquals(expected, path.subList(3, 5), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Lisboa"));
        checkContentEquals(expected, path.subList(5, 6), "BreathFirst Porto");

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        expected = new LinkedList<>(Arrays.asList("Viseu", "Guarda", "Castelo Branco"));
        assertEquals(expected, path, "BreathFirst Viseu");
    }

    /**
     * Test of DepthFirstSearch method, of class Algorithms.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("Test of DepthFirstSearch");

        assertNull(Algorithms.DepthFirstSearch(completeMap, "LX"), "Should be null if vertex does not exist");

        LinkedList<String> path = Algorithms.DepthFirstSearch(incompleteMap, "Faro");
        assertEquals(1, path.size(), "Should be just one");

        assertEquals("Faro", path.peekFirst());

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Porto");
        assertEquals(7, path.size(), "Should give seven vertices");

        assertEquals("Porto", path.removeFirst(), "DepthFirst Porto");
        assertTrue(new LinkedList<>(Arrays.asList("Aveiro", "Braga", "Vila Real")).contains(path.removeFirst()), "DepthFirst Porto");

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        List<String> expected = new LinkedList<>(Arrays.asList("Viseu", "Guarda", "Castelo Branco"));
        assertEquals(expected, path, "DepthFirst Viseu");
    }

    /**
     * Test of shortestPath method, of class Algorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<String>();
        double lenpath=0;
        lenpath=Algorithms.shortestPath(completeMap,"Porto","LX",shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if vertex does not exist");

        lenpath=Algorithms.shortestPath(incompleteMap,"Porto","Faro",shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if there is no path");

        lenpath=Algorithms.shortestPath(completeMap,"Porto","Porto",shortPath);
        assertTrue(shortPath.size() == 1, "Number of nodes should be 1 if source and vertex are the same");

        lenpath=Algorithms.shortestPath(incompleteMap,"Porto","Lisboa",shortPath);
        assertTrue(lenpath == 335, "Path between Porto and Lisboa should be 335 Km");

        Iterator<String> it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
        assertTrue(it.next().compareTo("Coimbra")==0, "then Coimbra");
        assertTrue(it.next().compareTo("Lisboa")==0, "then Lisboa");

        lenpath=Algorithms.shortestPath(incompleteMap,"Braga","Leiria",shortPath);
        assertTrue(lenpath == 255, "Path between Braga and Leiria should be 255 Km");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Braga")==0, "First in path should be Braga");
        assertTrue(it.next().compareTo("Porto")==0, "then Porto");
        assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
        assertTrue(it.next().compareTo("Leiria")==0, "then Leiria");

        shortPath.clear();
        lenpath=Algorithms.shortestPath(completeMap,"Porto","Castelo Branco",shortPath);
        assertTrue(lenpath == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertTrue(shortPath.size() == 5, "N. cities between Porto and Castelo Branco should be 5 ");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
        assertTrue(it.next().compareTo("Viseu")==0, "then Viseu");
        assertTrue(it.next().compareTo("Guarda")==0, "then Guarda");
        assertTrue(it.next().compareTo("Castelo Branco")==0, "then Castelo Branco");

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco
        //should change shortest path between Porto and Castelo Branco

        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.addEdge("Leiria","Castelo Branco",170.0);
        shortPath.clear();
        lenpath=Algorithms.shortestPath(completeMap,"Porto","Castelo Branco",shortPath);
        assertTrue(lenpath == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertTrue(shortPath.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
        assertTrue(it.next().compareTo("Leiria")==0, "then Leiria");
        assertTrue(it.next().compareTo("Castelo Branco")==0, "then Castelo Branco");

    }

    /**
     * Test of shortestPaths method, of class Algorithms.
     */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList <LinkedList<String>> paths = new ArrayList<>();
        ArrayList <Double> dists = new ArrayList<>();

        Algorithms.shortestPaths(completeMap,"Porto",paths,dists);

        assertEquals(paths.size(), dists.size());
        assertEquals(completeMap.numVertices(), paths.size());
        assertEquals(1, paths.get(completeMap.key("Porto")).size());
        assertEquals(Arrays.asList("Porto","Aveiro","Coimbra","Lisboa"), paths.get(completeMap.key("Lisboa")));
        assertEquals(Arrays.asList("Porto","Aveiro","Viseu","Guarda","Castelo Branco"), paths.get(completeMap.key("Castelo Branco")));
        assertEquals(335, dists.get(completeMap.key("Castelo Branco")),0.01);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.addEdge("Leiria","Castelo Branco",170.0);
        Algorithms.shortestPaths(completeMap,"Porto",paths,dists);
        assertEquals(365, dists.get(completeMap.key("Castelo Branco")),0.01);
        assertEquals(Arrays.asList("Porto","Aveiro","Leiria","Castelo Branco"), paths.get(completeMap.key("Castelo Branco")));



        Algorithms.shortestPaths(incompleteMap,"Porto",paths,dists);
        assertEquals(Double.MAX_VALUE, dists.get(completeMap.key("Faro")),0.01);
        assertEquals(335, dists.get(completeMap.key("Lisboa")),0.01);
        assertEquals(Arrays.asList("Porto","Aveiro","Coimbra","Lisboa"), paths.get(completeMap.key("Lisboa")));
        assertEquals(335, dists.get(completeMap.key("Lisboa")),0.01);

        Algorithms.shortestPaths(incompleteMap,"Braga",paths,dists);
        assertEquals(255, dists.get(completeMap.key("Leiria")),0.01);
        assertEquals(Arrays.asList("Braga", "Porto","Aveiro","Leiria"), paths.get(completeMap.key("Leiria")));
    }
}