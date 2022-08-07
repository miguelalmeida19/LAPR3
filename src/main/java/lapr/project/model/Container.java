package lapr.project.model;


public class Container {
    private final int id;
    private final String payload;
    private final double tare;
    private final double gross;
    private final double cargoWeight;
    private final ISO_CODE isoCode;
    private final double temperature;
    private final String clientEmail;

    /**
     * Constructor
     */
    public Container(int id, String payload, double tare, double gross, double cargoWeight, ISO_CODE isoCode, double temperature, String clientEmail){
        this.id=id;
        this.payload=payload;
        this.tare=tare;
        this.gross=gross;
        this.cargoWeight=cargoWeight;
        this.isoCode=isoCode;
        this.temperature = temperature;
        this.clientEmail = clientEmail;
    }

    /**
     * @return id of container
     */
    public int getID() {
        return id;
    }
    /**
     * @return cargo weight of container
     */
    public double getCargoWeight() {
        return cargoWeight;
    }
    /**
     * @return gross of container
     */
    public double getGross() {
        return gross;
    }
    /**
     * @return ISO code of container
     */
    public ISO_CODE getIsoCode() {
        return isoCode;
    }
    /**
     * @return Tare of container
     */
    public double getTare() {
        return tare;
    }
    /**
     * @return payload of container
     */
    public String getPayload() {
        return payload;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}
