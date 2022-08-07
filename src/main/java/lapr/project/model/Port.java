package lapr.project.model;

public class Port {
    private final String continent;
    private final String country;
    private final String code;
    private final String name;
    private final double latitude;
    private final double longitude;

    /**
     * Constructor
     */
    public Port(String continent,String country,String code,String name, double latitude,double longitude){
        this.continent=continent;
        this.country=country;
        this.code=code;
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * @return longitude of a port
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return latitude of a port
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return continent of a port
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @return country of a port
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return name of a port
     */
    public String getPort() {
        return name;
    }

    /**
     * @return code of a port
     */
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getPort();
    }
}
