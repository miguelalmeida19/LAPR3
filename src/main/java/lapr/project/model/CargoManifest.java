package lapr.project.model;

public class CargoManifest {

    private final int id;
    private final String time;
    private final String type;
    private final String shipIMO;
    private final int portId;

    public CargoManifest(int id, String time, String type, String shipIMO, int portId){
        this.id=id;
        this.time=time;
        this.type=type;
        this.shipIMO=shipIMO;
        this.portId=portId;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getShipIMO() {
        return shipIMO;
    }

    public int getPortId() {
        return portId;
    }

}
