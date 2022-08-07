package lapr.project.structures.graph;
import lapr.project.structures.graph.map.MapGraph;
import lapr.project.structures.graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ColoredGraphTest {

    @Test
    void colorGraph() {

        ArrayList<String> vertices = new ArrayList<String>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");
        vertices.add("F");
        vertices.add("G");
        Integer[][] a = {
                //A  B  C  D  E  F   G
                {1, 1, 1, 1, null, null, null}, // A
                {1, null, null, null, null, null, null}, // B
                {1, null, 1, 1, null, 1, 1}, // c
                {1, null, 1, 1, null, null, null}, // D
                {null, null, null, null, 1, 1, null}, // E
                {null, null, 1, null, 1, 1, null}, // F
                {null, null, 1, null, null, null, 1} // G
        };
        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);


        ColoredGraph<String, Integer> cg = new ColoredGraph<>(mg);
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 1);
        test.put("B", 2);
        test.put("C", 2);
        test.put("D", 3);
        test.put("E", 1);
        test.put("F", 3);
        test.put("G", 1);
        assertTrue(cg.colorGraph().equals(test));
    }
    @Test
    void colorGraph2() {

        ArrayList<String> vertices = new ArrayList<String>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");
        vertices.add("F");
        Integer a[][] = {
                //A  B  C  D  E  F   G
                {1, 1, 1, 1, 1, 1}, // A
                {1, 1, 1, 1, 1, 1}, // B
                {1, 1, 1, 1, 1,  1}, // c
                {1, 1, 1, 1, 1,  1}, // D
                {1, 1, 1, 1, 1, 1}, // E
                {1, 1, 1, 1, 1, 1}, // F
        };
        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);

        //transitiveClosure(ab);

        ColoredGraph<String, Integer> cg = new ColoredGraph<>(mg);
        Map<String , Integer> resultado = cg.colorGraph();
        /*for(String os : resultado.keySet()){
            System.out.println(os + " -- "+resultado.get(os));
        }*/
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 1);
        test.put("B", 2);
        test.put("C", 3);
        test.put("D", 4);
        test.put("E", 5);
        test.put("F", 6);
        assertTrue(cg.colorGraph().equals(test));
    }
    @Test
    void colorGraph3() {

        ArrayList<String> vertices = new ArrayList<String>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");
        vertices.add("F");
        Integer a[][] = {
                //A  B  C  D  E  F
                {1, null,1,1,null,null}, // A
                {null, 1, 1, 1, 1, 1}, // B
                {1, 1, 1, null, null,  1}, // c
                {1, 1, null, 1, 1,  null}, // D
                {null, 1, null, 1, 1, null}, // E
                {null, 1, 1, null, null, 1}, // F
        };
        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);

        //transitiveClosure(ab);

        ColoredGraph<String, Integer> cg = new ColoredGraph<>(mg);
        Map<String , Integer> resultado = cg.colorGraph();
       /*for(String os : resultado.keySet()){
            System.out.println(os + " -- "+resultado.get(os));
        }*/
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 1);
        test.put("B", 1);
        test.put("C", 2);
        test.put("D", 2);
        test.put("E", 3);
       test.put("F", 3);
        assertTrue(cg.colorGraph().equals(test));
    }
    @Test
    void colorGraph4() {

        ArrayList<String> vertices = new ArrayList<String>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        vertices.add("E");
        vertices.add("F");
        Integer a[][] = {
                //A  B  C  D  E  F
                {1, null,1,1,null,null}, // A
                {null, 1, 1, 1, 1, 1}, // B
                {1, 1, 1, null, null,  1}, // c
                {1, 1, null, 1, 1,  null}, // D
                {null, 1, null, 1, 1, 1}, // E
                {null, 1, 1, null, 1, 1}, // F
        };
        MatrixGraph<String, Integer> ab = new MatrixGraph<>(false, vertices, a);
        MapGraph<String, Integer> mg = new MapGraph<>(ab);

        //transitiveClosure(ab);

        ColoredGraph<String, Integer> cg = new ColoredGraph<>(mg);
        Map<String , Integer> resultado = cg.colorGraph();
       /* for(String os : resultado.keySet()){
            System.out.println(os + " -- "+resultado.get(os));
        }*/
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 1);
        test.put("B", 1);
        test.put("C", 2);
        test.put("D", 2);
        test.put("E", 3);
       test.put("F", 4);
        assertTrue(cg.colorGraph().equals(test));
    }
}