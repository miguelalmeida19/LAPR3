package lapr.project.structures;

import lapr.project.utils.DistanceBetweenCoodinates;

import java.util.*;

public class KDTree {
    KDNode root;
    int cd = 0;
    int DIM = 2;
    int depth = 0;

    /**
     * Constructor
     */
    public KDTree() {
        root = null;
    }

    /**
     * Returns true if is empty
     *
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert the KDNode in a balanced way
     */
    public void insertBalanced(Map<double[], Object> hashMap) {
        List<KDNode> nodes = new ArrayList<>();
        for (Map.Entry<double[], Object> d : hashMap.entrySet()) {
            KDNode kdNode = new KDNode(d.getKey(), d.getValue());
            nodes.add(kdNode);
        }
        insertInTree(nodes, 0);
    }

    /**
     * ~Inserts a KDNode in tree
     */
    public void insertInTree(List<KDNode> list, int divx){

        List<KDNode> right = new ArrayList<>();
        List<KDNode> left = new ArrayList<>();

        if (!list.isEmpty()){
            KDNode median;
            if (divx%DIM==0){
                median = median(list, cmpX);
                insert(median.getObject(), median.data[0], median.data[1]);
                divx++;
                for (KDNode d : list){
                    if (d.getLat() < median.getLat()){
                        left.add(d);
                    }else if (d.getLat() > median.getLat()){
                        right.add(d);
                    }
                    else {
                        if (d.getLong() > median.getLong()){
                            right.add(d);
                        }
                        if (d.getLong() < median.getLong()){
                            right.add(d);
                        }
                    }
                }
            }
            else {
                median = median(list, cmpY);
                insert(median.getObject(), median.data[0], median.data[1]);
                divx++;
                for (KDNode d : list){
                    if (d.getLong() < median.getLong()){
                        left.add(d);
                    }else if (d.getLong() > median.getLong()){
                        right.add(d);
                    }
                    else {
                        if (d.getLat() > median.getLat()){
                            right.add(d);
                        }
                        if (d.getLat() < median.getLat()){
                            right.add(d);
                        }
                    }
                }
            }
            insertInTree(left, divx);
            insertInTree(right, divx);
        }
    }

    /**
     * Inserts in a KDtree
     */
    public void insert(Object element, double x, double y) {
        depth = 0;
        root = insert(element, x, y, root);
    }

    /**
     * Inserts in a KDtree
     * @return KDnode with the object
     */
    public KDNode insert(Object element, double x, double y, KDNode node) {
        if (node == null){
            return new KDNode(new double[]{x, y}, element);}
        if(node.getLat()==x && node.getLong()==y){
            return node;
        }else if (depth % 2 == 0) {
            if (node.getLat() > x) {
                depth+=1;
                node.left = (insert(element, x, y, node.left));
            }
            if (node.getLat() < x) {
                depth+=1;
                node.right = (insert(element, x, y, node.right));
            }
            if(node.getLat()==x){
                depth+=1;
                node.right = (insert(element, x, y, node.right));
            }
        } else {
            if (node.getLong() > y) {
                depth+=1;
                node.left = (insert(element, x, y, node.left));
            }
            if (node.getLong() < y) {
                depth+=1;
                node.right = (insert(element, x, y, node.right));
            }
            if(node.getLong()==y){
                depth+=1;
                node.right = (insert(element, x, y, node.right));
            }
        }
        return node;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Calculates the median in a list of KDNodes
     * @return the KDNode with the median value
     */
    private KDNode median(List<KDNode> values, Comparator<KDNode> comparator) {
        // sort array
        values.sort(comparator);

        KDNode median;
        // get count of scores
        int totalElements = values.size();
        // check if total number of scores is even
        median = values.get((totalElements / 2));
        return median;
    }

    /**
     * Comparator
     */
    private final Comparator<KDNode> cmpX = new Comparator<KDNode>() {


        /**
         * Compares 2 KDNodes by its latitude
         * @return 1 if bigger, 0 if equals and -1 is lower
         */
        @Override
        public int compare(KDNode o1, KDNode o2) {
            return Double.compare(o1.getLat(), o2.getLat());
        }
    };

    /**
     * Constructor
     */
    private final Comparator<KDNode> cmpY = new Comparator<KDNode>() {

        /**
         * Compares 2 KDNodes by its longitude
         * @return 1 if bigger, 0 if equals and -1 is lower
         */
        @Override
        public int compare(KDNode o1, KDNode o2) {
            return Double.compare(o1.getLong(), o2.getLong());
        }
    };

    /*
    The balance factor of a node is the height of its right subtree minus the height of its left
    subtree and a node with a balance factor 1, 0, or -1 is considered balanced.
     */
    private int balanceFactor(KDNode node) {
        return (height(node.right) - height(node.left));
    }

    /*
     * Returns the height of the tree
     * @return height
     */
    public int height(){
        return height(root);
    }

    /*
     * Returns the height of the subtree rooted at Node node.
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(KDNode node){
        if (node == null) {
            return -1;
        }

        int lefth = height(node.left);
        int righth = height(node.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size(){
        return size(root);
    }

    private int size(KDNode node){
        if (node == null)
            return 0;
        else
            return(size(node.left) + 1 + size(node.right));
    }

    /**
     * @param x           latitude
     * @param y           longitude
     */
    public KDNode closestPort(KDNode node, double x, double y, KDNode closestNode, boolean divX) {
        if (node == null)
            return null;
        //distance between the coordinates of the node and the coordinates given
        double d = DistanceBetweenCoodinates.distanceBetweenCoordinates(node.getLat(), node.getLong(), x, y);

        //distance between the closest node and the coordinates given
        double closestDist = DistanceBetweenCoodinates.distanceBetweenCoordinates(closestNode.getLat(),closestNode.getLong(), x, y);

        //if the distance of the closest point found so far is bigger than the distance of the current node, just replace the closest node data with the current node data
        if (closestDist > d) {
            closestNode.setObject(node);
        }
        //square distance between the 2 coordinates. the divx tells us if we use the x coordinates or the y coordinates
        double delta = divX ? x - node.getLat() : y - node.getLong();
        double delta2 = delta * delta;

        //select the node to search first. if delta <0 the point is at left
        KDNode node1 = delta < 0 ? node.left : node.right;
        KDNode node2 = delta < 0 ? node.right : node.left;
        closestPort(node1, x, y, closestNode, !divX);

        if (delta2 < closestDist) {
            closestPort(node2, x, y, closestNode, !divX);
            return closestNode;
        }

        return closestNode;

    }

    /**
     * @return the root of the tree
     */
    public KDNode getRoot() {
        return root;
    }

    /**
     * @return the depth of the Tree
     */
    public int getDepth() {
        return depth;
    }
}
