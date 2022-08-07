package lapr.project.structures.graph.map;


import lapr.project.structures.graph.Edge;

import java.util.*;

/**
 * @param <V>
 * @param <E>
 * @author DEI-ESINF
 */
public class MapVertex<V, E> {

    private final V element;                            // Vertex information
    private final Map<V, Edge<V, E>> outVerts;    // Adjacent vertices

    public MapVertex(V vert) {
        if (vert == null) throw new IllegalArgumentException("Vertice information cannot be null!");
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    public V getElement() {
        return element;
    }

    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return outVerts.size();
    }

    public Collection<V> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder(element + ": \n");
        if (!outVerts.isEmpty())
            for (V vert : outVerts.keySet())
                st.append(outVerts.get(vert));

        return st.toString();
    }
}
