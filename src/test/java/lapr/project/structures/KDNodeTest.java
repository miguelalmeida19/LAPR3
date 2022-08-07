package lapr.project.structures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class KDNodeTest {
    @Test
    void testConstructor() {
        KDNode actualKdNode = new KDNode();
        assertNull(actualKdNode.getData());
        assertNull(actualKdNode.getObject());
    }

    @Test
    void testConstructor2() {
        KDNode actualKdNode = new KDNode(new double[]{2.0, 2.0, 2.0, 2.0}, "Obj");

        assertEquals(2, actualKdNode.getData().length);
        assertNull(actualKdNode.right);
        assertNull(actualKdNode.left);
        assertEquals("Obj", actualKdNode.getObject());
        assertEquals(2.0, actualKdNode.getLong());
        assertEquals(2.0, actualKdNode.getLat());
    }

    @Test
    void testConstructor3() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new KDNode(new double[]{}, "Obj"));

    }

    @Test
    void testGetLat() {
        assertEquals(10.0, (new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj")).getLat());
    }

    @Test
    void testGetLong() {
        assertEquals(10.0, (new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj")).getLong());
    }

    @Test
    void testSetObject() {
        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");
        KDNode kdNode1 = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        kdNode.setObject(kdNode1);
        Object expectedObject = kdNode1.getObject();
        assertSame(expectedObject, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
    }
}

