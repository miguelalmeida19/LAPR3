package lapr.project.structures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import lapr.project.controller.App;
import lapr.project.controller.ClosestPortController;
import lapr.project.controller.PortController;
import lapr.project.controller.ShipController;
import lapr.project.data.Persistence;
import lapr.project.model.Message;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.store.PortStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class KDTreeTest {
    KDTree instance;

    KDNode arr[] = {
            new KDNode(new double[]{12.3, 13.2}, null),
            new KDNode(new double[]{20, 32}, null),
            new KDNode(new double[]{19.2, 23}, null),
            new KDNode(new double[]{18.7, 5.3}, null),
            new KDNode(new double[]{11.2, 14.2}, null),
            new KDNode(new double[]{21.5, 33.2}, null),
    };

    @Test
    void testConstructor() {
        KDTree actualKdTree = new KDTree();
        assertNull(actualKdTree.getRoot());
    }

    @Test
    void testConstructor2() {
        KDTree actualKdTree = new KDTree();
        assertNull(actualKdTree.getRoot());
        assertEquals(0, actualKdTree.cd);
        assertEquals(2, actualKdTree.DIM);
    }

    @Test
    void testIsEmpty() {
        assertTrue((new KDTree()).isEmpty());
    }

    @Test
    void testIsEmpty2() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
    }

    @Test
    void testInsertBalanced() {
        KDTree kdTree = new KDTree();
        kdTree.insertBalanced(new HashMap<>(1));
        assertEquals(0, kdTree.getDepth());
        assertEquals(0, kdTree.cd);
        assertEquals(2, kdTree.DIM);
        assertEquals(0, kdTree.size());
    }

    @Test
    void testInsertBalanced2() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(0, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        assertNull(root.left);
        assertEquals("Value", root.getObject());
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
    }

    @Test
    void testInsertBalanced3() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(0, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        assertNull(root.left);
        assertEquals("Value", root.getObject());
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
    }

    @Test
    void testInsertBalanced4() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(0, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        assertNull(root.left);
        assertEquals("Value", root.getObject());
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
    }

    @Test
    void testInsertBalanced5() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{0.5, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object object = root.getObject();
        assertEquals("Value", object);
        assertEquals(10.0, root.getLat());
        assertEquals(10.0, root.getLong());
        KDNode kdNode = root.left;
        assertEquals(object, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(0.5, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.left);
        assertNull(kdNode.right);
    }

    @Test
    void testInsertBalanced6() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 0.5, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.left);
        assertEquals(10.0, root.getLat());
        Object object = root.getObject();
        assertEquals("Value", object);
        KDNode kdNode = root.right;
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals(object, kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertBalanced7() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, -0.5, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.left);
        assertEquals(10.0, root.getLat());
        Object object = root.getObject();
        assertEquals("Value", object);
        KDNode kdNode = root.right;
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals(object, kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertBalanced8() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 0.5, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.left);
        assertEquals(10.0, root.getLat());
        Object object = root.getObject();
        assertEquals("Value", object);
        KDNode kdNode = root.right;
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals(object, kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertBalanced9() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 0.5, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.left);
        assertEquals(10.0, root.getLat());
        Object object = root.getObject();
        assertEquals("Value", object);
        KDNode kdNode = root.right;
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals(object, kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertBalanced10() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals("Value", kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertBalanced11() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 1.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(2, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertNull(kdNode1.left);
        assertEquals("Value", kdNode1.getObject());
        assertEquals(10.0, kdNode1.getLong());
        assertEquals(10.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
    }

    @Test
    void testInsertBalanced13() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 0.5, 3.0);

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.put(new double[]{0.5, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(2, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        Object object = kdNode.getObject();
        assertEquals("Value", object);
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertNull(kdNode1.left);
        assertEquals(object, kdNode1.getObject());
        assertEquals(10.0, kdNode1.getLong());
        assertEquals(0.5, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
    }

    @Test
    void testInsertBalanced14() {
        KDTree kdTree = new KDTree();

        HashMap<double[], Object> doubleArrayObjectMap = new HashMap<>(1);
        doubleArrayObjectMap.putIfAbsent(new double[]{0.5, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{0.5, 10.0, 10.0, 10.0}, "Value");
        doubleArrayObjectMap.put(new double[]{10.0, 10.0, 10.0, 10.0}, "Value");
        kdTree.insertBalanced(doubleArrayObjectMap);
        assertEquals(1, kdTree.getDepth());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(2, root.getData().length);
        assertNull(root.left);
        assertEquals(0.5, root.getLat());
        Object object = root.getObject();
        assertEquals("Value", object);
        assertEquals(10.0, root.getLong());
        KDNode kdNode = root.right;
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertEquals(object, kdNode.getObject());
        assertNull(kdNode.left);
    }

    @Test
    void testInsertInTree() {
        KDTree kdTree = new KDTree();
        kdTree.insertInTree(new ArrayList<>(), 1);
        assertEquals(0, kdTree.cd);
        assertEquals(2, kdTree.DIM);
    }

    @Test
    void testInsertInTree2() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
    }

    @Test
    void testInsertInTree3() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.left);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsertInTree4() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 10.0, 3.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.left);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsertInTree5() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 1.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        assertNull(kdNode1.left);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode1.getObject());
        assertEquals(10.0, kdNode1.getLong());
        assertEquals(10.0, kdNode1.getLat());
    }

    @Test
    void testInsertInTree6() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 0);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
    }

    @Test
    void testInsertInTree7() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(2, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object expectedObject = kdNodeList.get(1).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
    }

    @Test
    void testInsertInTree8() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{0.5, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().left;
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertNull(kdNode.left);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(0.5, kdNode.getLat());
    }

    @Test
    void testInsertInTree9() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 10.0, 10.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
    }

    @Test
    void testInsertInTree10() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 1.0, 3.0);
        kdTree.insert("Element", 2.0, 10.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        assertNull(kdNode1.left);
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode1.getObject());
        assertEquals(10.0, kdNode1.getLong());
        assertEquals(10.0, kdNode1.getLat());
    }

    @Test
    void testInsertInTree11() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 1.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 2.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(1, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode kdNode = kdTree.getRoot().right;
        assertNull(kdNode.right);
        KDNode kdNode1 = kdNode.left;
        assertEquals(10.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.left);
        assertNull(kdNode1.right);
        assertEquals(2.0, kdNode1.getLong());
        Object expectedObject = kdNodeList.get(0).getObject();
        assertSame(expectedObject, kdNode1.getObject());
    }

    @Test
    void testInsertInTree12() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{2.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(2, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object expectedObject = kdNodeList.get(1).getObject();
        assertSame(expectedObject, root.getObject());
        KDNode kdNode = root.left;
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertNull(kdNode.left);
        Object expectedObject1 = kdNodeList.get(0).getObject();
        assertSame(expectedObject1, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(2.0, kdNode.getLat());
    }

    @Test
    void testInsertInTree13() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 2.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(2, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        Object expectedObject = kdNodeList.get(1).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
        KDNode kdNode = root.right;
        assertNull(kdNode.left);
        Object expectedObject1 = kdNodeList.get(0).getObject();
        assertSame(expectedObject1, kdNode.getObject());
        assertEquals(2.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsertInTree14() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdNodeList.add(new KDNode(new double[]{2.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 1);
        assertEquals(2, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(2.0, root.getLat());
        assertEquals(2, root.getData().length);
        Object expectedObject = kdNodeList.get(1).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
        KDNode kdNode = root.right;
        assertNull(kdNode.left);
        Object expectedObject1 = kdNodeList.get(0).getObject();
        assertSame(expectedObject1, kdNode.getObject());
        assertEquals(10.0, kdNode.getLong());
        assertEquals(10.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsertInTree15() {
        KDTree kdTree = new KDTree();

        ArrayList<KDNode> kdNodeList = new ArrayList<>();
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdNodeList.add(new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj"));
        kdTree.insertInTree(kdNodeList, 0);
        assertEquals(2, kdNodeList.size());
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(10.0, root.getLong());
        assertEquals(10.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        Object expectedObject = kdNodeList.get(1).getObject();
        assertSame(expectedObject, root.getObject());
        assertNull(root.left);
    }

    @Test
    void testInsert() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        assertEquals(3.0, root.getLong());
        assertEquals(2.0, root.getLat());
        assertEquals(2, root.getData().length);
        assertNull(root.right);
        assertEquals("Element", root.getObject());
        assertNull(root.left);
    }

    @Test
    void testInsert2() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
    }

    @Test
    void testInsert3() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 3.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.left;
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
        assertNull(kdNode.left);
        Object expectedObject = root.getObject();
        assertEquals(expectedObject, kdNode.getObject());
        assertEquals(3.0, kdNode.getLong());
        assertEquals(2.0, kdNode.getLat());
    }

    @Test
    void testInsert4() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 1.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.right;
        assertNull(kdNode.left);
        Object expectedObject = root.getObject();
        assertEquals(expectedObject, kdNode.getObject());
        assertEquals(3.0, kdNode.getLong());
        assertEquals(2.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsert5() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 2.0, 2.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.right;
        assertNull(kdNode.left);
        Object expectedObject = root.getObject();
        assertEquals(expectedObject, kdNode.getObject());
        assertEquals(3.0, kdNode.getLong());
        assertEquals(2.0, kdNode.getLat());
        assertEquals(2, kdNode.getData().length);
        assertNull(kdNode.right);
    }

    @Test
    void testInsert6() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 10.0, 3.0);
        kdTree.insert("Element", 3.0, 3.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.left;
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        Object expectedObject = root.getObject();
        assertEquals(expectedObject, kdNode1.getObject());
        assertNull(kdNode1.left);
    }

    @Test
    void testInsert7() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 10.0, 3.0);
        kdTree.insert("Element", 2.0, 2.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.left;
        assertNull(kdNode.left);
        KDNode kdNode1 = kdNode.right;
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        Object expectedObject = root.getObject();
        assertEquals(expectedObject, kdNode1.getObject());
        assertNull(kdNode1.left);
    }

    @Test
    void testInsert8() {
        KDTree kdTree = new KDTree();
        kdTree.insert("Element", 10.0, 3.0);
        kdTree.insert("Element", 3.0, 10.0);
        kdTree.insert("Element", 2.0, 3.0);
        assertFalse(kdTree.isEmpty());
        KDNode root = kdTree.getRoot();
        KDNode kdNode = root.left;
        assertNull(kdNode.right);
        Object expectedObject = root.getObject();
        KDNode kdNode1 = kdNode.left;
        assertEquals(expectedObject, kdNode1.getObject());
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.left);
        assertNull(kdNode1.right);
    }

    @Test
    void testInsert9() {
        KDNode actualInsertResult = (new KDTree()).insert("Element", 2.0, 3.0, null);
        assertEquals(2, actualInsertResult.getData().length);
        assertNull(actualInsertResult.right);
        assertNull(actualInsertResult.left);
        assertEquals("Element", actualInsertResult.getObject());
        assertEquals(3.0, actualInsertResult.getLong());
        assertEquals(2.0, actualInsertResult.getLat());
    }

    @Test
    void testInsert10() {
        KDTree kdTree = new KDTree();
        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode actualInsertResult = kdTree.insert("Element", 2.0, 3.0, kdNode);
        assertSame(kdNode, actualInsertResult);
        KDNode kdNode1 = actualInsertResult.left;
        assertEquals("Element", kdNode1.getObject());
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.left);
        assertNull(kdNode1.right);
    }

    @Test
    void testInsert11() {
        KDTree kdTree = new KDTree();
        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode actualInsertResult = kdTree.insert("Element", 10.0, 3.0, kdNode);
        assertSame(kdNode, actualInsertResult);
        KDNode kdNode1 = actualInsertResult.right;
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(10.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        assertEquals("Element", kdNode1.getObject());
        assertNull(kdNode1.left);
    }

    @Test
    void testInsert12() {
        KDTree kdTree = new KDTree();
        KDNode kdNode = new KDNode(new double[]{0.5, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode actualInsertResult = kdTree.insert("Element", 2.0, 3.0, kdNode);
        assertSame(kdNode, actualInsertResult);
        KDNode kdNode1 = actualInsertResult.right;
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.right);
        assertEquals("Element", kdNode1.getObject());
        assertNull(kdNode1.left);
    }

    @Test
    void testInsert13() {
        KDTree kdTree = new KDTree();
        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode actualInsertResult = kdTree.insert("Element", 2.0, 3.0, kdNode);
        assertSame(kdNode, actualInsertResult);
        KDNode kdNode1 = actualInsertResult.left;
        assertEquals("Element", kdNode1.getObject());
        assertEquals(3.0, kdNode1.getLong());
        assertEquals(2.0, kdNode1.getLat());
        assertEquals(2, kdNode1.getData().length);
        assertNull(kdNode1.left);
        assertNull(kdNode1.right);
    }

    @Test
    void testInsert14() {
        KDTree kdTree = new KDTree();
        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        assertSame(kdNode, kdTree.insert("Element", 10.0, 10.0, kdNode));
    }


    @Test
    void testClosestPort() {
        KDTree kdTree = new KDTree();
        assertNull(kdTree.closestPort(null, 2.0, 3.0, new KDNode(), true));
    }

    @Test
    void testClosestPort2() {
        KDTree kdTree = new KDTree();
        KDNode node = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        assertSame(kdNode, kdTree.closestPort(node, 2.0, 3.0, kdNode, true));
    }

    @Test
    void testClosestPort3() {
        KDTree kdTree = new KDTree();
        KDNode node = new KDNode(new double[]{2.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode actualClosestPortResult = kdTree.closestPort(node, 2.0, 3.0, kdNode, true);
        assertSame(kdNode, actualClosestPortResult);
        assertEquals("Obj", actualClosestPortResult.getObject());
        assertEquals(10.0, actualClosestPortResult.getLong());
        assertEquals(2.0, actualClosestPortResult.getLat());
    }

    @Test
    void testClosestPort4() {
        KDTree kdTree = new KDTree();
        KDNode node = new KDNode(new double[]{-90.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        assertSame(kdNode, kdTree.closestPort(node, 2.0, 3.0, kdNode, true));
    }

    @Test
    void testClosestPort5() {
        KDTree kdTree = new KDTree();
        KDNode node = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        KDNode kdNode = new KDNode(new double[]{10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0}, "Obj");

        assertSame(kdNode, kdTree.closestPort(node, 2.0, 3.0, kdNode, false));
    }

    @Test
    void testClosestRight() {
        PortController controller = new PortController();
        controller.importPorts("files/sports.csv");
        ShipController controller2 = new ShipController();
        controller2.importShips("files/sships.csv");
        Ship ship = App.getInstance().getCompany().getShipStore().getShip("C4SQ2");
        String date = "31/12/2020 18:31";
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse(date, ft);
        Message lastMessage;
        lastMessage = ship.findMessageByDateTime(date1);

        double latitude = lastMessage.getLatitude();
        double longitude = lastMessage.getLongitude();
        KDNode root = App.getInstance().getCompany().getPortStore().getRootNode();
        Port p = (Port) App.getInstance().getCompany().getPortStore().getKdt().closestPort(root, latitude, longitude, root, true).getObject();
        assertEquals(p.getCode(), "22226");
    }

    @Test
    void closestPortWrong() {
        PortController controller = new PortController();
        controller.importPorts("files/sports.csv");
        ShipController controller2 = new ShipController();
        controller2.importShips("files/sships.csv");
        Ship ship = App.getInstance().getCompany().getShipStore().getShip("C4SQ2");

        String date = "31/12/2020 18:31";
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse(date, ft);
        Message lastMessage;
        lastMessage = ship.findMessageByDateTime(date1);

        double latitude = lastMessage.getLatitude();
        double longitude = lastMessage.getLongitude();
        KDNode root = App.getInstance().getCompany().getPortStore().getRootNode();


        Port p = (Port) App.getInstance().getCompany().getPortStore().getKdt().closestPort(root, latitude, longitude, root, true).getObject();
        assertNotEquals(p.getCode(), "22220");
    }

    @Test
    void closestPortDistanceComparation() {
        PortController controller = new PortController();
        controller.importPorts("files/sports.csv");
        ShipController controller2 = new ShipController();
        controller2.importShips("files/sships.csv");
        Port p = new Port("africa", "wakanda", "wakanda1", "wakanda royal port", 41.01, 41.01);
        KDNode closestNode = new KDNode(new double[]{40.01, 40.01}, p);
        KDTree kt = App.getInstance().getCompany().getPortStore().getKdt();
        assertTrue(((Port) kt.closestPort(kt.getRoot(), 40.00, 40.00, closestNode, true).getObject()).getCode().equals("wakanda1"));

    }

    @Test
    void closestPortDistanceComparationWrong() {
        PortController controller = new PortController();
        controller.importPorts("files/sports.csv");
        ShipController controller2 = new ShipController();
        controller2.importShips("files/sships.csv");
        Port p = new Port("africa", "wakanda", "wakanda1", "wakanda royal port", 41.01, 41.01);
        KDNode closestNode = new KDNode(new double[]{40.01, 40.01}, p);
        KDTree kt = App.getInstance().getCompany().getPortStore().getKdt();
        assertFalse(((Port) kt.closestPort(kt.getRoot(), 40.00, 40.00, closestNode, true).getObject()).getCode().equals("17941"));

    }

    @Test
    void testInsertKillMutant() {
        KDTree kdTree = new KDTree();
        KDNode root = new KDNode(new double[]{11.3, 11.6}, new Object());
        KDNode kdNode = new KDNode(new double[]{12.1, 13.2}, new Object());
        assertEquals(kdTree.getDepth(), 0);
        kdTree.insert(root, 11, 12, root);
        assertEquals(kdTree.getDepth(), 1);
        kdTree.insert(kdNode, 11, 12, root);
        assertNotEquals(kdTree.getDepth(), 1);
    }

    /**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {
        System.out.println("height");

        int[] height = {0, 1, 2, 3, 3, 3};

        instance = new KDTree();
        assertEquals(instance.height(), -1);
        for (int idx = 0; idx < arr.length; idx++) {
            instance.insert(arr[idx].getObject(), arr[idx].getLat(), arr[idx].getLong());
            assertEquals(instance.height(), height[idx]);
        }
        instance = new KDTree();
        assertEquals(instance.height(), -1);
    }

    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        instance = new KDTree();
        for (int idx = 0; idx < arr.length; idx++) {
            instance.insert(arr[idx].getObject(), arr[idx].getLat(), arr[idx].getLong());
        }
        System.out.println("size");
        assertEquals(instance.size(), arr.length);

        KDTree sInstance = new KDTree();
        assertEquals(sInstance.size(), 0);
        sInstance.insert(arr[0].getObject(), arr[0].getLat(), arr[0].getLong());
        assertEquals(sInstance.size(), 1);
        sInstance.insert(arr[1].getObject(), arr[1].getLat(), arr[1].getLong());
        assertEquals(sInstance.size(), 2);
        sInstance.insert(arr[0].getObject(), arr[0].getLat(), arr[0].getLong());
        assertEquals(sInstance.size(), 2);
    }
}