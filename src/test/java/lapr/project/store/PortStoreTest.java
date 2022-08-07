package lapr.project.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lapr.project.model.Port;
import lapr.project.structures.KDNode;
import lapr.project.structures.KDTree;
import org.junit.jupiter.api.Test;

class PortStoreTest {
    @Test
    void testConstructor() {
        PortStore actualPortStore = new PortStore();
        assertNull(actualPortStore.getRootNode());
        KDTree kdt = actualPortStore.getKdt();
        assertTrue(kdt.isEmpty());
        assertNull(kdt.getRoot());
    }

    @Test
    void testConstructor2() {
        PortStore actualPortStore = new PortStore();
        assertNull(actualPortStore.getRootNode());
        assertTrue(actualPortStore.getKdt().isEmpty());
    }

    @Test
    void testAddPort() {
        PortStore portStore = new PortStore();
        Port port = new Port("Continent", "GB", "Code", "Port", 10.0, 10.0);

        portStore.addPort(port);
        portStore.getPortHashMap().put(new double[]{port.getLatitude(), port.getLongitude()}, port);
        portStore.getKdt().insertBalanced(portStore.getPortHashMap());
        assertFalse(portStore.getKdt().isEmpty());
        KDNode rootNode = portStore.getRootNode();
        assertEquals(10.0, rootNode.getLong());
        assertEquals(10.0, rootNode.getLat());
        assertEquals(2, rootNode.getData().length);
    }

    @Test
    void testGetRootNode() {
        assertNull((new PortStore()).getRootNode());
    }

    @Test
    void testGetPort() {
        assertNull((new PortStore()).getPort("Port"));
    }

    @Test
    void testGetPort2() {
        PortStore portStore = new PortStore();
        portStore.addPort(new Port("Continent", "GB", "Code", "Name", 10.0, 10.0));
        assertNull(portStore.getPort("Port"));
    }

    @Test
    void testGetPort3() {
        PortStore portStore = new PortStore();
        Port port = new Port("Continent", "GB", "Port", "Name", 10.0, 10.0);

        portStore.addPort(port);
        assertSame(port, portStore.getPort("Port"));
    }

    @Test
    void testGetPort4() {
        PortStore portStore = new PortStore();
        Port port = new Port("Continent", "GB", "Code", "Port", 10.0, 10.0);

        portStore.addPort(port);
        assertSame(port, portStore.getPort("Port"));
    }
}

