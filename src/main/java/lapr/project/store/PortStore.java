package lapr.project.store;

import lapr.project.model.Port;
import lapr.project.structures.KDNode;
import lapr.project.structures.KDTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortStore {

    private final List<Port> portList;
    private final HashMap<double[], Object> portHashMap;
    private final KDTree kdt;

    /**
     * Constructor
     */
    public PortStore(){
        portList = new ArrayList<>();
        kdt = new KDTree();
        portHashMap = new HashMap<>();
    }

    /**
     * This method adds a port and puts it on an hashMap
     */
    public void addPort(Port port){
        portList.add(port);
        double[] coordinates = new double[2];
        coordinates[0] = port.getLatitude();
        coordinates[1] = port.getLongitude();
        portHashMap.put(coordinates, port);
    }

    /**
     * @return root node
     */
    public KDNode getRootNode() {
        return kdt.getRoot();
    }

    /**
     *  @return KDTree
     */
    public KDTree getKdt() {
        return kdt;
    }

    /**
     * This method gets a port on port list
     */
    public Port getPort(String port){
        for (Port p: portList){
            if (p.getPort().equals(port) || p.getCode().equals(port)){
                return p;
            }
        }
        return null;
    }

    /**
     * @return port list
     */
    public List<Port> getPortList() {
        return portList;
    }

    /**
     * @return the port hashmap
     */
    public Map<double[], Object> getPortHashMap() {
        return portHashMap;
    }
}