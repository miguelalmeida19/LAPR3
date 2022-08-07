package lapr.project.structures;

public class KDNode {
    KDNode left;
    KDNode right;
    double []data;
    Object object;

    public KDNode(){
        left=null;
        right=null;
    }

    public KDNode(double []x, Object obj){
        left=null;
        right=null;
        data = new double[2];
        for (int k = 0; k < 2; k++)
            data[k]=x[k];
        this.object = obj;
    }

    public double[] getData() {
        return data;
    }

    public double getLat() {
        return data[0];
    }
    public double getLong() {
        return data[1];
    }
    public void setObject(KDNode node){
        data[0] = node.getLat();
        data[1] = node.getLong();
        object = node.getObject();
    }
    public Object getObject() {
        return object;
    }

}