package lapr.project.controller;

import com.opencsv.CSVWriter;
import lapr.project.data.ImportFromDataBase;
import lapr.project.model.Capital;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.store.FreightNetworkStore;
import lapr.project.structures.graph.Algorithms;
import lapr.project.structures.graph.matrix.MatrixGraph;
import lapr.project.structures.graph.map.*;
import lapr.project.utils.DistanceBetweenCoodinates;

import java.io.FileWriter;
import java.util.*;

import static lapr.project.data.ImportFromDataBase.wrapLinkedHashMap;

public class FreightNetworkController {

    private Double[][] matrix;
    private ArrayList<Object> vertices;

    /**
     * @return a list of all countries
     */
    public List<Country> getCountries() {
        return ImportFromDataBase.getAllCountries();
    }

    /**
     * Calculates the distance between a port and a capital
     * @return a double with the distance
     */
    public double getDistanceBetweenPortCapital(Port port, Country country) {
        return DistanceBetweenCoodinates.distanceBetweenCoordinates(port.getLatitude(), port.getLongitude(), country.getLatitude(), country.getLongitude());
    }

    /**
     * Calculates the distance between 2 capitals
     * @return a double with the distance
     */
    public double getDistanceBetweenCapitals(Country c1, Country c2) {
        return DistanceBetweenCoodinates.distanceBetweenCoordinates(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
    }

    /**
     * Gets the index of the object
     * @return an int with the index
     */
    public int indexOfObject(List<Object> vertices, Object o) {
        for (int i = 0; i < vertices.size(); i++) {
            if (o.getClass() == Port.class && vertices.get(i).getClass() == Port.class) {
                if (((Port) vertices.get(i)).getCode().equals(((Port) o).getCode())) {
                    return i;
                }
            }
            if (o.getClass() == Capital.class && vertices.get(i).getClass() == Capital.class) {
                if (((Capital) vertices.get(i)).getCountryName().equals(((Capital) o).getCountryName())) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * @return a list of vertices
     */
    public List<Object> getVertices() {
        List<Country> countryList = getCountries();
        List<Object> vertices = new ArrayList<>();

        for (Country c : countryList) {
            Capital countryCapital = new Capital(c.getCountryName(), c.getContinent(), c.getCapital(), c.getLatitude(), c.getLongitude());
            vertices.add(countryCapital);

            List<Port> ports = ImportFromDataBase.getPortFromCountry(c.getCountryName());
            vertices.addAll(ports);
        }

        return vertices;
    }

    /**
     * This method inserts in the matrix n vertices
     * @return number of vertices introduced
     */
    public int insertInMatrix(List<Object> vertices, Double[][] matrix, int n) {
        int counter = 0;
        List<Country> countryList = getCountries();

        for (Country c : countryList) {
            List<Country> borders = ImportFromDataBase.getCountryBorders(c, countryList);
            Capital countryCapital = new Capital(c.getCountryName(), c.getContinent(), c.getCapital(), c.getLatitude(), c.getLongitude());
            int column = indexOfObject(vertices, countryCapital);
            int line = 0;
            for (Country b : borders) {
                Capital borderCapital = new Capital(b.getCountryName(), b.getContinent(), b.getCapital(), b.getLatitude(), b.getLongitude());
                double distanceToCapital = getDistanceBetweenCapitals(c, b);
                line = indexOfObject(vertices, borderCapital);
                matrix[line][column] = distanceToCapital;
                matrix[column][line] = distanceToCapital;
                counter++;
            }
            List<Port> ports = ImportFromDataBase.getPortFromCountry(c.getCountryName());
            LinkedHashMap<Double, Port> distancePortCapitals = new LinkedHashMap<>();
            for (Port port : ports) {
                double distancePortCapital = getDistanceBetweenPortCapital(port, c);
                distancePortCapitals.put(distancePortCapital, port);

                connectPortToCountryPorts(port, matrix, vertices);
                connectPortToNClosestPorts(port, matrix, vertices, n);

            }
            if (!distancePortCapitals.keySet().isEmpty()) {
                List<Double> dist = new ArrayList<Double>(distancePortCapitals.keySet());
                Collections.sort(dist);
                double minDistance = dist.get(0);
                line = indexOfObject(vertices, distancePortCapitals.get(minDistance));
                matrix[line][column] = minDistance;
                matrix[column][line] = minDistance;
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method builds the matrix
     * @return length of matrix
     */
    public int build(int n) {
        vertices = (ArrayList<Object>) getVertices();
        matrix = new Double[vertices.size()][vertices.size()];
        insertInMatrix(vertices, matrix, n);
        print(matrix, vertices);

        MatrixGraph<Object, Double> ab = new MatrixGraph<Object, Double>(false, vertices, matrix);
        MapGraph<Object, Double> mg = new MapGraph<Object, Double>(ab);

        FreightNetworkStore.setAb(ab);
        FreightNetworkStore.setMg(mg);

        return matrix.length;
    }

    /**
     * Obtains the shortest path between 2 places
     * @return a double with the shortest path
     */
    public double shortestPathBetweenVertices(LinkedList<Object> l, String place1, String place2) {
        MatrixGraph<Object, Double> ab = new MatrixGraph<Object, Double>(false, vertices, matrix);
        MapGraph<Object, Double> mg = new MapGraph<Object, Double>(ab);
        return Algorithms.shortestPath(mg, getVertByName(place1), getVertByName(place2), l);
    }

    /**
     * Prints the matrix
     */
    public static int print(Double[][] puzzle, List<Object> vertices) {
        try {
            List<String[]> list = new ArrayList<>();

            String[] verts = new String[vertices.size() + 1];
            verts[0] = "";
            int j = 1;
            for (Object vert : vertices) {
                verts[j] = vert.toString();
                j++;
            }

            list.add(verts);

            int cont = 0;
            for (Double[] row : puzzle) {
                String[] s = new String[row.length + 1];
                s[0] = vertices.get(cont).toString();
                for (int i = 0; i < s.length - 1; i++)
                    s[i + 1] = String.valueOf(row[i]);
                list.add(s);
                cont++;
            }
            try (CSVWriter writer = new CSVWriter(new FileWriter("files/matrix.csv"))) {
                writer.writeAll(list);
            }
            return list.size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Obtains vertice by its name
     * @return the object from the vertice wanted
     */
    public Object getVertByName(String name) {
        for (Object v : vertices) {
            if (v.toString().toUpperCase(Locale.ROOT).equals(name.toUpperCase(Locale.ROOT))) {
                return v;
            }
        }
        return null;
    }

    /**
     * Connects each port to all the country ports
     * @return number of connections
     */
    public int connectPortToCountryPorts(Port port, Double[][] matrix, List<Object> vertices){
        int counter = 0;
        List<Port> countryPorts = ImportFromDataBase.getPortFromCountry(port.getCountry());
        int line = indexOfObject(vertices, port);
        for (Port p: countryPorts){
            int col = indexOfObject(vertices, p);
            double distance = ImportFromDataBase.getDistanceBetweenPorts(port, p);
            matrix[line][col] = distance;
            matrix[col][line] = distance;
            counter++;
        }
        return counter;
    }

    /**
     * Connects each port to a number of closest ports
     * @return number of connections
     */
    public int connectPortToNClosestPorts(Port port, Double[][] matrix, List<Object> vertices, int n){
        int counter = 0;
        LinkedHashMap<Port, Double> possibleConnections = ImportFromDataBase.getPossibleConnections(port);
        List<Double> listValues = new ArrayList<Double>(possibleConnections.values());
        Collections.sort(listValues);
        LinkedHashMap<Double, Port> listValuesWrap = wrapLinkedHashMap(possibleConnections);
        int line = indexOfObject(vertices, port);

        if (!listValues.isEmpty()){
            if (listValues.size()<n){
                n = listValues.size();
            }
            for (int i=0; i<n; i++){
                double correct = listValues.get(i);
                Port pt = listValuesWrap.get(correct);
                int col = indexOfObject(vertices, pt);
                matrix[line][col] = correct;
                matrix[col][line] = correct;
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return the matrix
     */
    public Double[][] getMatrix() {
        return matrix;
    }
}
