package lapr.project.structures.graph;

import java.util.Comparator;

public class EdgeWeightComparator implements Comparator {
    @Override
    public  int compare(Object o1, Object o2) {
        if(Double.parseDouble(String.valueOf(((Edge) o1).getWeight())) > Double.parseDouble(String.valueOf(((Edge)o2).getWeight()))){
            return 1;
        }
        if(Double.parseDouble(String.valueOf(((Edge) o1).getWeight())) < Double.parseDouble(String.valueOf(((Edge)o2).getWeight()))){
            return -1;
        }
        return 0;

    }
}
