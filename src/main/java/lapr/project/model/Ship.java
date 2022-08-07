package lapr.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class Ship {
    private final int mmsi;
    private final String name;
    private final String imo;
    private final String callSign;
    private final String vesselType;
    private final double length;
    private final double width;
    private final String capacity;
    private final double draft;

    private final TreeSet<Message> messages;

    /**
     * Constructor
     */
    public Ship(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String capacity) {
        checkMMSI(mmsi);
        checkName(name);
        checkIMO(imo);
        checkVesselType(vesselType);
        checkLength(length);
        checkWidth(width);
        checkCapacity(capacity);
        this.mmsi = mmsi;
        this.name = name;
        this.imo = imo;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.capacity = capacity;
        this.messages = new TreeSet<>();
    }

    /**
     * This method adds a message
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * @return width of a ship
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return capacity of a ship
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * @return details of a ship
     */
    public String getDetails() {
        return "MMSI: " + mmsi + "\n" +
                "NAME: " + name + "\n" +
                "IMO: " + imo + "\n" +
                "CALLSIGN: " + callSign + "\n" +
                "VESSELTYPE: " + vesselType + "\n" +
                "LENGTH: " + length + "\n" +
                "WIDTH: " + width + "\n" +
                "CAPACITY: " + capacity + "\n" +
                "DRAFT: " + draft + "\n";
    }

    /**
     * @return draft of a ship
     */
    public double getDraft() {
        return draft;
    }

    /**
     * @return length of a ship
     */
    public double getLength() {
        return length;
    }

    /**
     * @return IMO of a ship
     */
    public String getIMO() {
        return imo;
    }

    /**
     * @return MMSI of a ship
     */
    public int getMMSI() {
        return mmsi;
    }

    /**
     * @return Call Sign of a ship
     */
    public String getCallsign() {
        return callSign;
    }

    /**
     * @return Name of a ship
     */
    public String getName() {
        return name;
    }

    /**
     * @return Vessel Type of a ship
     */
    public String getVesselType() {
        return vesselType;
    }

    /**
     * Checks if the length of MMSI is different of 9
     */
    public void checkMMSI(int mmsi) {
        if (String.valueOf(mmsi).length() != 9) {
            throw new IllegalArgumentException("Invalid MMSI! Must have 9 digits");
        }
    }

    /**
     * Checks if the name is empty
     */
    public void checkName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    /**
     * Checks if the IMO of a ship has different length of 10, and if it starts by IMO
     */
    public void checkIMO(String imo) {
        if (imo.length() != 10) {
            throw new IllegalArgumentException("Invalid IMO! Must have IMO followed by 7 digits");
        }
        if (!(imo.charAt(0) == 'I' && imo.charAt(1) == 'M' && imo.charAt(2) == 'O')) {
            throw new IllegalArgumentException("IMO number format is invalid");
        }
    }

    /**
     * Checks if the vessel type is null and if only contains numbers
     *
     */
    public void checkVesselType(String vesselType) {
        String regex = "\\d+";

        if (vesselType == null) {
            throw new IllegalArgumentException("Vessel Type cannot be null");
        }
        if (!vesselType.matches(regex)) {
            throw new IllegalArgumentException("Vessel Type must contain only numbers");
        }
    }

    /**
     * Checks if length is greater than 0
     *
     */
    public void checkLength(double length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be always greater than 0.");
        }

    }

    /**
     * Checks if width is greater than 0
     *
     */
    public void checkWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width must be always greater than 0.");
        }
    }

    /**
     * Checks if capacity is greater than 0
     *
     */
    public void checkCapacity(String cap) {
        String regex = "\\d+";
        if ((cap.matches(regex) || cap.contains("-")) && Double.parseDouble(cap) < 0) {
            throw new IllegalArgumentException("Capacity must be always greater than 0.");
        }

    }

    /**
     * @return All messages from a ship
     */
    public TreeSet<Message> getAllMessages() {
        return messages;
    }

    /**
     * Gets the message of a specific date.
     *
     * @param time datetime of the message
     * @return message
     */
    public Message findMessageByDateTime(LocalDateTime time) {
        for (Message m : messages) {
            if (m.getLocalDateTime().equals(time)) {
                return m;
            }
        }
        return null;
    }
}