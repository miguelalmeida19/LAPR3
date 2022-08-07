package lapr.project.structures.graph;

import lapr.project.model.Capital;
import lapr.project.model.Port;

import java.util.*;

/**
 * @author DEI-ISEP
 */
public class Algorithms {

    private Algorithms(){

    }
    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qbfs = new LinkedList<>();
        ArrayList<V> qaux = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];
        qbfs.add(vert);
        qaux.add(vert);
        visited[g.key(vert)] = true;
        while (!qaux.isEmpty()) {
            V vInf = qaux.remove(0);
            for (V vAdj : g.adjVertices(vInf)) {
                if (!visited[g.key(vAdj)]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[g.key(vAdj)] = true;
                }
            }

        }
        return qbfs;
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    public static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.key(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (!visited[g.key(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qdfs = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];
        qdfs.add(vert);
        visited[g.key(vert)] = true;
        for (V vAdj : g.adjVertices(vert)) {
            if (!g.validVertex(vAdj)) {
                return null;
            }

            if (!visited[g.key(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    public static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                       LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        visited[g.key(vOrig)] = true;
        path.push(vOrig);
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.push(vDest);
                paths.add(revPath(path));
                path.pop();
            } else {
                if (!visited[g.key(vAdj)]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }
        }
        V vertex = path.pop();
        visited[g.key(vertex)] = false;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        LinkedList<V> path = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];
        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            allPaths(g, vOrig, vDest, visited, path, paths);
        }

        return paths;

    }


    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    public static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig,
                                                 boolean[] visited, int[] pathKeys, double[] dist) {

        dist[g.key(vOrig)] = 0;
        while (vOrig != null) {
            visited[g.key(vOrig)] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                Edge<V, E> e = g.edge(vOrig, vAdj);
                if (!visited[g.key(vAdj)] && dist[g.key(vAdj)] > (dist[g.key(vOrig)] + Double.parseDouble(String.valueOf(e.getWeight())))) {
                    dist[g.key(vAdj)] = dist[g.key(vOrig)] + Double.parseDouble(String.valueOf(e.getWeight()));
                    pathKeys[g.key(vAdj)] = g.key(vOrig);
                }
            }
            vOrig = null;
            double minDistance = Double.MAX_VALUE;
            for (V vert : g.vertices()) {
                if (!visited[g.key(vert)] && dist[g.key(vert)] < minDistance) {
                    vOrig = vert;
                    minDistance = dist[g.key(vert)];
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    public static <V, E> LinkedList<V> getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        path.push(vDest);
        int vKey = pathKeys[g.key(vDest)];
        if (vKey != -1) {
            vDest = verts[vKey];
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        }
        return path;
    }

    /**
     * shortest-path between vOrig and vDest
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return 0;
        shortPath.clear();
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = (V[]) g.vertices().toArray();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, visited, pathKeys, dist);
        if (dist[g.key(vDest)] != Double.MAX_VALUE) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
        }
        return shortPath.isEmpty() ? 0 : dist[g.key(vDest)];
    }

    /**
     * shortest-path between voInf and all other
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) return false;

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = (V[]) g.vertices().toArray();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE)
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Reverses the path
     * @param path stack with path
     */
    public static <V> LinkedList<V> revPath(List<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());

        return pathrev;
    }

    /**
     * This method calculates the shortest path using n places
     * @param g - Graph
     * @param vOrig - Origin vertex
     * @param vDest - Destination vertex
     * @param Places - Ways in which the user intends to pass
     * @param sea - With maritime Path
     * @param land - With land Path
     * @return - Shortest path from source to destination along n paths indicated by the user
     */
    public static <V,E> LinkedList<V> shortestPathUsingNplaces(Graph<V, E> g,  V vOrig, V vDest, LinkedList<V> Places, boolean sea, boolean land){
        LinkedList<V> shortPath = new LinkedList<>();
        String [] color = new String[g.numVertices()];

        List<V> vertices = g.vertices();

        for(V vertice: vertices){
            color[vertices.indexOf(vertice)] = "White";
        }
        shortPath.addFirst(vOrig);

        shortPath = function(g, vOrig, vDest, Places, shortPath, color, sea, land);

        if(shortPath.size() == 1){
            shortPath.removeLast();
        }

        return shortPath;
    }

    /**
     * Function that calculates the shortest path from source to destination along n paths indicated by the user
     * @param g - Graph
     * @param current - Current vertex
     * @param vDest - Destination vertex
     * @param Places - Ways in which the user intends to pass
     * @param Path - Current path
     * @param color - Colors
     * @param sea - With maritime Path
     * @param land - With land Path
     * @return - Shortest path from source to destination along n paths indicated by the user
     */
    public static <V,E> LinkedList<V> function(Graph<V, E> g,  V current, V vDest, LinkedList<V> Places, LinkedList<V> Path, String [] color,  boolean sea, boolean land){


        //Vertices adjacent to the current vertex
        List<V> adj = (List<V>) g.adjVertices(current);

        //If the last vertical is the destination vertical, the shortest path is returned
        if(current.equals(vDest)){
            return Path;
        }

        //If the current vertex has the destination vertex as adjacent vertex, this is added to the shortest path
        if(adj.contains(vDest)){
            Path.add(vDest);

            // Check to see if the path we have already obtained,
            // if it respects the previously imposed conditions of passing the paths indicated by the user
            if(checkPlaces(Places, Path) && validateRestrictions(vDest, current, vDest, sea, land)){
                return Path;
            } else {
                Path.removeLast();
            }
        }
        color[g.vertices().indexOf(current)] = "Gray";


        List<Edge<V, E>> edgesList = new ArrayList<>();

        //Adds the edges adjacent to the current vertex
        for (V v : adj) {
            edgesList.add(g.edge(current, v));
        }

        //Sorting by distance
        edgesList.sort(new EdgeWeightComparator());

        //For each vertex it checks if it complies with the intended conditions
        for(Edge<V,E> e: edgesList){
            V dest = e.getVDest();

            // Check to see if the path we have already obtained,
            // if it respects the previously imposed conditions of passing the paths indicated by the user
            if (validateRestrictions(dest, current, vDest, sea, land)){

                //If the vertex is painted white, it has never been visited
                if (color[g.vertices().indexOf(dest)].equals("White")) {

                    //Add the vertex to the path
                    Path.add(dest);

                    //Recursive call, visits the destination vertex
                    LinkedList<V> v = function(g, dest, vDest, Places, Path, color, sea, land);

                    // Check to see if the path we have already obtained,
                    // if it respects the previously imposed conditions of passing the paths indicated by the user
                    if(v.getLast().equals(vDest) && checkPlaces(Places, Path)){
                        return v;

                    } else {
                        v.removeLast();
                    }
                }
            }
        }
        color[g.vertices().indexOf(current)] = "Black";
        return Path;
    }

    /**
     * Checks if the path passes through the vertices that the user has indicated he wants to pass
     * @param Places - Vertices to path throw
     * @param Path - Current Path
     * @return - True or False
     */
    public static <V> boolean checkPlaces(LinkedList<V> Places, LinkedList<V> Path){
        boolean b = true;

        for(V v : Places){
            b = Path.contains(v);
        }

        return b;
    }

    /**
     * Method that validates all the constraints necessary to get the shortest path
     * @param dest - Adjacent Vertex
     * @param current - Current Vertex
     * @param vDest - Destination Vertex
     * @param sea - With maritime Path
     * @param land - With land Path
     * @return - True or False
     */
    public static  <V> boolean validateRestrictions(V dest, V current, V vDest, boolean sea, boolean land){

        return ((dest instanceof Port && current instanceof Port && sea)
                || (dest instanceof Port && vDest instanceof Port && sea)
                || (land && dest instanceof Port && current instanceof Capital)
                || (land && dest instanceof Capital && current instanceof Capital)
                || (land && dest instanceof Capital && current instanceof Port));
    }

    /**
     * Algorithm to find the shortest path using the maximum number of vertex possible, without repeating vertex.
     * @param g graph
     * @param current the starting vertex
     * @return returns the found path
     */
    public static <V,E> LinkedList<V> pathfinder(Graph<V, E> g, V current){
        String[] color = new String[g.numVertices()];
        List<V> vertices = g.vertices();
        for(V v : vertices){
            color[vertices.indexOf(v)] = "White";
        }
        LinkedList<V> path = new LinkedList<>();
        LinkedList<V> tempPath = new LinkedList<>();

        path = dfsVisit(g, current,color, path,tempPath, current);
        path.addFirst(current); //add at beginning of the list the starting point
        return path;

    }

    /**
     * This is the method that visits the whole graph.
     * Its a Depth search using the weight of the edges to decide the next vertex to visit
     * @param g the graph
     * @param current current vertex to visit
     * @param color array with the colors of the vertex. its ussed to check if the vertex has all adj vertex visited
     * @param path the shortest path using the maximum number of vertex
     * @param temppath temporary path that stores the current path that was visited by the algorithm
     * @param orig the starting vertex
     * @return linkedlist with the path
     */
    public static <V,E> LinkedList<V> dfsVisit(Graph<V, E> g, V current, String[] color, LinkedList<V> path,LinkedList<V> temppath, V orig){
        color[g.vertices().indexOf(current)] = "Gray";
        List<V> adj = (List<V>) g.adjVertices(current);
        if(adj.contains(orig) && temppath.size()+1 > path.size()){ //if the current vertex connects to the starting point and the path found is bigger than the previous path
            temppath.add(orig); //replaces the path to the new one
            path = new LinkedList<>(temppath);
            temppath.removeLast(); //removes the last vertex of the current path (the end point)

        }

        List<Edge<V, E>> edgesList = new ArrayList<>();
        for (V v : adj) {
            edgesList.add(g.edge(current, v)); //creates the list with the edges of the vertex
        }
        edgesList.sort(new EdgeWeightComparator()); //now we sort the edges by their weight. It assures we found first always the shortest path possible
        for(Edge<V,E> edge: edgesList) {

            V vDestinoTemp = edge.getVDest();
            if (color[g.vertices().indexOf(vDestinoTemp)].equals("White")) { //white = we didn't go to that vertex
                temppath.add(vDestinoTemp); //add to the temporary path
                path = dfsVisit(g, vDestinoTemp, color,path,temppath,orig); //visit the new vertex
                temppath.remove(vDestinoTemp); //removes the vertex we visited from the temporary path. It is no longer needed there because if a new path was found we have
                //it stored in the path linkedlist
            }
        }
        color[g.vertices().indexOf(current)] = "Black"; //black = we visited the vertex and all of the adj vertex.
        return path;
    }
}