package lapr.project.structures.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public interface Graph<V, E> extends Cloneable {

    /** Check if graph is directed
     * 
     * @return true if graph is directed, false otherwise
     */
    boolean isDirected();
    
    /** The total number of vertices in the graph
     * 
     * @return the number of vertices of the graph
     */
    int numVertices();

    /** All the vertices in the graph
     * 
     * @return all the vertices of the graph as an ArrayList. Each vertex is in its key position in the ArrayList.
     */
    ArrayList<V> vertices();
    
    /** Check is vertex is in graph
     * 
     * @param vert the vertex to check
     * @return true is vert exists in the graph, false otherwise
     */
    boolean validVertex(V vert);

    /** Check the numeric key for vert in the graph
     * 
     * @param vert the vertex to check
     * @return the numeric key associated with vert, -1 if vert is not in the graph
     */
    int key(V vert);

    /** Check the vertex associated with a numeric key in the graph
     * 
     * @param key the key to check
     * @return the vertex associated with key, null if key is not in the graph
     */
    V vertex(int key);

    /** Find the first vertex for which Predicate is true.
     *  An example to get the Person vertex with a particular name:
     *  <pre>
     *      Graph<Person, Integer> g = new ...;
     *      g.vertex( p -> p.getName().equals(name) );
     * </pre>
     * @param p predicate (should be given in lambda form)
     * @return the first vertex for which predicate p is true, null if not found
     */
    V vertex(Predicate<V> p);

    /** Find all adjacent vertices of a vertex
     * 
     * @param vert the vertex for which to find adjacent vertices
     * @return a collection of all the adjacent vertices of vert
     */
    Collection<V> adjVertices(V vert);

    /** The total number of edges of the graph
     *
     * @return the number of edges of the graph
     */
    int numEdges();

    /** All the edges of the graph
     *
     * @return a collection with all the edges of the graph.
     */
    Collection<Edge<V, E>> edges();

    /** Finds an edge in the graph given its end vertices
     *
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @return the edge from vOrig to vDest, or null if vertices are not adjacent
     */
    Edge<V, E> edge(V vOrig, V vDest);

    /** Finds an edge in the graph given its end vertex keys
     *
     * @param vOrigKey the key of vertex vOrig
     * @param vDestKey the key of vertex vDist
     * @return the edge from vOrig to vDest, or null if vertices are not adjacent
     */
    Edge<V, E> edge(int vOrigKey, int vDestKey);

    /** Finds the number of edges leaving a vertex
     * 
     * @param vert the vertex of interest
     * @return the number of edges leaving vert
     */
    int outDegree(V vert);
    
    /** Finds the number of edges for which a vertex is the destination
     * 
     * @param vert the vertex of interest
     * @return the number of edges for which vert is the destination
     */
    int inDegree(V vert);

    /** Finds the edges for which a vertex is the origin
     * 
     * @param vert the vertex of interest
     * @return a collection of edges for which vert is the origin
     */
    Collection<Edge<V, E>> outgoingEdges(V vert);

    /** Finds the edges for which a vertex is the destination
     * 
     * @param vert the vertex of interest
     * @return a collection of edges for which vert is the destination
     */
    Collection<Edge<V, E>> incomingEdges(V vert);

    /** Adds a new vertex into the graph
     * 
     * @param vert the vertex to add
     * @return true if vert is not already in the graph, false otherwise
     */
    boolean addVertex(V vert);

    /** Adds a new edge between two vertices. If the vertices are not already in the graph, they are added.
     * 
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param weight the weight of the edge
     * @return false if edge is already present, true otherwise
     */
    boolean addEdge(V vOrig, V vDest, E weight);

    /** Removes a vertex and all its incident edges from the graph
     *
     * @param vert the vertex to remove
     * @return true if vert was present in the graph, false otherwise
     */
    boolean removeVertex(V vert);

    /** Removes the edge between two vertices
     *
     * @param vOrig vertex origin of the edge
     * @param vDest vertex destination of the edge
     * @return  true if an edge between vOrig and vDest was present in the graph, false otherwise
     */
    boolean removeEdge(V vOrig, V vDest);

    /** Creates a deep copy clone of the graph
     *
     * @return a deep copy of graph
     */
    Graph<V, E> clone();
}