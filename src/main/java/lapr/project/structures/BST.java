package lapr.project.structures;

import lapr.project.model.Ship;

public class BST {
    public Node root;
    public Node lastReturnedNode;

    public static class Node {
        Comparable key;
        Object value;
        Node left, right;
        Node(Comparable key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Search in a BST for an element
     */
    public Object search(Comparable key) {
        return search(root, key);
    }

    /**
     * Compares 2 elements of a BST
     */

    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) <0;
    }
    /**
     * Checks if 2 elements of a BST are equal
     */

    private boolean equals(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    /**
     * Search for an element of a BST
     */
    private Object search(Node h, Comparable key) {
        lastReturnedNode = h;
        if (h == null) return null;
        if (equals(key, h.key)) return h.value;
        if (less (key, h.key)) return search(h.left, key);
        else return search(h.right, key);
    }

    /**
     * Inserts an element in a BST
     */
    public void put(Comparable key, Object value) {
        root = insert(root, key, value);
    }

    /**
     * Inserts an element in a BST
     */
    private Node insert(Node h, Comparable key, Object value) {
        if (h == null) return new Node(key, value);
        if (less(key, h.key)) h.left = insert(h.left, key, value);
        else h.right = insert(h.right, key, value);
        lastReturnedNode = h;
        return h;
    }
}