package lapr.project.structures.graph;

import java.util.Objects;

/**
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class Edge<V, E> {
    private final V vOrig;        // vertex origin
    private final V vDest;        // vertex destination
    private E weight;        // Edge weight


    public Edge(V vOrig, V vDest, E weight) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.weight = weight;
    }

    public V getVOrig() {
        return vOrig;
    }

    public V getVDest() {
        return vDest;
    }

    public E getWeight() {
        return weight;
    }
    public void setWeight(E weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.format("%s -> %s\nWeight: %s", vOrig, vDest, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") Edge<V, E> edge = (Edge<V, E>) o;
        return  vOrig.equals(edge.vOrig) &&
                vDest.equals(edge.vDest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vOrig, vDest);
    }
}

