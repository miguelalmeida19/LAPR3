package lapr.project.structures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lapr.project.controller.FreightNetworkController;
import lapr.project.store.FreightNetworkStore;
import lapr.project.structures.graph.map.MapGraph;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetweennessCentralityTest {

    @Test
    void testGetAllPathsBetweenness() {
        ArrayList<LinkedList<Object>> allPaths = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        FreightNetworkController controller = new FreightNetworkController();
        controller.build(3);
        System.out.println(controller.getVertices().get(9));
        Algorithms.shortestPaths(FreightNetworkStore.getMg(), FreightNetworkStore.getMg().vertices.get(2), allPaths, doubles);
        assertEquals(146, allPaths.size());
    }

    @Test
    void testCheckListsEquals() {
        BetweennessCentrality betweennessCentrality = new BetweennessCentrality();
        LinkedList<Object> l1 = new LinkedList<>();
        assertTrue(betweennessCentrality.checkListsEquals(l1, new ArrayList<>()));
    }

    @Test
    void testCheckListsEquals2() {
        BetweennessCentrality betweennessCentrality = new BetweennessCentrality();
        LinkedList<Object> l1 = new LinkedList<>();

        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<>();
        linkedListList.add(new LinkedList<>());
        assertFalse(betweennessCentrality.checkListsEquals(l1, linkedListList));
    }

    @Test
    void testCheckListsEquals3() {
        BetweennessCentrality betweennessCentrality = new BetweennessCentrality();

        LinkedList<Object> objectList = new LinkedList<>();
        objectList.add("42");

        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<>();
        linkedListList.add(new LinkedList<>());
        assertTrue(betweennessCentrality.checkListsEquals(objectList, linkedListList));
    }

    @Test
    void testCheckListsEquals4() {
        BetweennessCentrality betweennessCentrality = new BetweennessCentrality();

        LinkedList<Object> objectList = new LinkedList<>();
        objectList.add("42");

        LinkedList<Object> objectList1 = new LinkedList<>();
        objectList1.add(2);

        ArrayList<LinkedList<Object>> linkedListList = new ArrayList<>();
        linkedListList.add(objectList1);
        assertTrue(betweennessCentrality.checkListsEquals(objectList, linkedListList));
    }
}