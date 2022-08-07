package lapr.project.model;

public class Trip {

    private final String shipIMO;
    private final String base_date_time;

    public Trip(String shipIMO, String base_date_time){
        this.shipIMO=shipIMO;
        this.base_date_time=base_date_time;
    }

    public String getShipIMO() {
        return shipIMO;
    }

    public String getBase_date_time(){
        return base_date_time;
    }
}
