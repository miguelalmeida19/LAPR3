package lapr.project.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Comparable {
    private final double latitude;
    private final double longitude;
    private final double sog;
    private final double cog;
    private final double heading;
    private final String transceiver;
    private final LocalDateTime localDateTime;

    /**
     * Constructor
     */
    public Message(LocalDateTime localDateTime, double latitude, double longitude, double sog, double cog, double heading, String transceiver){
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.transceiver = transceiver;
        this.localDateTime = localDateTime;
    }
    /**
     * @return LocalDateTime of a ship
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    /**
     * @return Cog of a ship
     */
    public double getCog() {
        return cog;
    }
    /**
     * @return Heading of a ship
     */
    public double getHeading() {
        return heading;
    }
    /**
     * @return Latitude of a ship
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * @return Longitude of a ship
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * @return Sog of a ship
     */
    public double getSog() {
        return sog;
    }
    /**
     * @return transceiver class of a ship
     */
    public String getTransceiver() {
        return transceiver;
    }

    public String uploadToDatabase(String shipIMO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(getLocalDateTime());
        return "\nINSERT INTO MESSAGES (BASE_DATE_TIME, SHIPIMO, LATITUDE, LONGITUDE ,SOG, COG, HEADING, TRANSCEIVER_CLASS) VALUES('"+date+"', '"+shipIMO+"',"+latitude+","+longitude+","+sog+","+cog+","+heading+",'"+transceiver+"');";
    }

    /**
     * Compares local date time to a local date time from a message
     */
    @Override
    public int compareTo(Object o) {
        return localDateTime.compareTo(((Message)o).getLocalDateTime());
    }

    /**
     * @return a string with all information
     */
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s", latitude, longitude, sog, cog, heading, transceiver, localDateTime.toString().replace("T", " "));
    }
}