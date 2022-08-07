package lapr.project.structures.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 * @author DEI-ISEP
 *
 */
public abstract class CommonGraph <V,E> implements Graph<V,E> {
    protected int numVerts;
    protected int numEdges;
    protected final boolean isDirected;
    protected ArrayList<V> vertices;       // Used to maintain a numeric key to each vertex

    protected CommonGraph(boolean directed) {
        numVerts = 0;
        numEdges = 0;
        isDirected = directed;
        vertices = new ArrayList<>();
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int numVertices() {
        return numVerts;
    }

    @Override
    public ArrayList<V> vertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public boolean validVertex(V vert) { return vertices.contains(vert);   }

    @Override
    public int key(V vert) {
        return vertices.indexOf(vert);
    }

    @Override
    public V vertex(int key) {
        if ((key < 0) || (key>=numVerts)) return null;
        return vertices.get(key);
    }

    @Override
    public V vertex(Predicate<V> p) {
        for (V v : vertices) {
            if (p.test(v)) return v;
        }
        return null;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    /** Copy graph from to graph to
     *
     * @param from graph from which to copy
     * @param to graph for which to copy
     */
    protected void copy(Graph <V,E> from, Graph <V,E> to) {
        //insert all vertices
        for (V v : from.vertices()) {
            to.addVertex(v);
        }

        //insert all edges
        for (Edge<V, E> e : from.edges()) {
            to.addEdge(e.getVOrig(), e.getVDest(), e.getWeight());
        }
    }

    /* equals implementation compares graphs, independently of their representation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (!(otherObj instanceof Graph<?, ?>))
            return false;

        @SuppressWarnings("unchecked") Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        if (numVerts != otherGraph.numVertices() || numEdges != otherGraph.numEdges() || isDirected() != otherGraph.isDirected())
            return false;

        // graph must have same vertices
        Collection<V> tvc = this.vertices();
        tvc.removeAll(otherGraph.vertices());
        if (!tvc.isEmpty() ) return false;

        // graph must have same edges
        Collection<Edge<V, E>> tec = this.edges();
        tec.removeAll(otherGraph.edges());
        return (tec.isEmpty());
    }

    public abstract Graph<V, E> clone();

    @Override
    public int hashCode() {
        return Objects.hash(numVerts, numEdges, isDirected, vertices);
    }
}
