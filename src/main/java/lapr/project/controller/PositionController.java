package lapr.project.controller;

import lapr.project.model.Message;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class PositionController {

    private static final ShipStore shipStore = App.getInstance().getCompany().getShipStore();

    /**
     * @param date - Date in format dd/mm/yyyy
     * @param hour - Hour in format HH:mm
     * @param s - MMSI/IMO/Call Sigh of the ship
     * @return - List where the positions of the ship are stored according to the input entered
     */
    public List<String> DatePosition(String date, String hour, String s) {

        checkDate(date);
        checkHour(hour);

        LocalDateTime localDateTime = dateTime(date + " " + hour);

        Ship ship = shipStore.getShip(s);
        if (ship == null) {
            throw new IllegalArgumentException("This ship does not exist. :((");
        }
        TreeSet<Message> messages = ship.getAllMessages();
        List<String> list = new ArrayList<>();
        for (Message objects1 : messages) {
            if (localDateTime.equals(objects1.getLocalDateTime())) {
                list.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                        ship.getName(), date, hour, objects1.getLatitude(), objects1.getLongitude()));
            }
        }

        return list;
    }

    /**
     * @param date - Date in format dd/mm/yyyy
     * @param hour - Hour in format HH:mm
     * @param date1 - Date in format dd/mm/yyyy
     * @param hour1 - Hour in format HH:mm
     * @param s - MMSI/IMO/Call Sigh of the ship
     * @return - List where the positions of the ship are stored according to the input entered
     */
    public List<String> PeriodPosition(String date, String hour, String date1, String hour1, String s) {

        checkDate(date);
        checkHour(hour);
        checkDate(date1);
        checkHour(hour1);

        LocalDateTime localDateTime = dateTime(date + " " + hour);
        LocalDateTime localDateTime1 = dateTime(date1 + " " + hour1);

        checkPeriod(localDateTime, localDateTime1);

        List<String> msgList = new ArrayList<>();

        Ship ship = shipStore.getShip(s);
        TreeSet<Message> messages = ship.getAllMessages();
        if (!messages.isEmpty()) {
            for (Message objects1 : messages) {
                if (objects1.getLocalDateTime().isEqual(localDateTime) || objects1.getLocalDateTime().isEqual(localDateTime1) || (objects1.getLocalDateTime().isAfter(localDateTime) && objects1.getLocalDateTime().isBefore(localDateTime1))) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    msgList.add(String.format("%-20s %-20s %-20s %-20s %-20s",
                            ship.getName(), objects1.getLocalDateTime().format(formatter).split(" ")[0], objects1.getLocalDateTime().format(formatter).split(" ")[1], objects1.getLatitude(), objects1.getLongitude()));
                }
            }
        } else {
            throw new IllegalArgumentException("This ship does not have messages :((");
        }
        return msgList;
    }

    /**
     * Transforms a date string into a LocalDateTime
     * @param date - Date in format dd/mm/yyyy
     * @return LocalDateTime
     */
    public LocalDateTime dateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Checks if the date entered is in the format dd:mm:yyyy
     * @param date  - Date in format dd/mm/yyyy
     */
    public void checkDate(String date) {

        if (StringUtils.isBlank(date)) {
            throw new IllegalArgumentException("Date cannot be blank.");
        }

        String[] dates = date.replace(" ", "").split("/");

        try {
            if (Integer.parseInt(dates[0]) < 1 || Integer.parseInt(dates[0]) > 31) {
                throw new IllegalArgumentException("Date day is invalid (1-31)");
            } else if (Integer.parseInt(dates[1]) < 1 || Integer.parseInt(dates[1]) > 12) {
                throw new IllegalArgumentException("Date month is invalid (1-12)");
            } else if (Integer.parseInt(dates[2]) < 2000 || Integer.parseInt(dates[2]) > 2022) {
                throw new IllegalArgumentException("Date year is invalid (2000-2022)");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Date format: dd/mm/yyyy");
        }
    }

    /**
     * Checks if the hour entered is in the format HH:mm
     * @param hour  - Date in format HH:mm
     */
    public void checkHour(String hour) {

        if (StringUtils.isBlank(hour)) {
            throw new IllegalArgumentException("Hour cannot be blank.");
        }

        String[] hours = hour.replace(" ", "").split(":");

        try {
            if (Integer.parseInt(hours[0]) < 0 || Integer.parseInt(hours[0]) > 23) {
                throw new IllegalArgumentException("Hour is invalid (00:00 - 23:59)");
            } else if (Integer.parseInt(hours[1]) < 0 || Integer.parseInt(hours[1]) > 59) {
                throw new IllegalArgumentException("Minutes are invalid (00:00 - 23:59)");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Hour format: HH:mm");
        }
    }

    /**
     * Checks if the first date is before the second date, otherwise it will produce an error
     * @param date - LocalDateTime in format dd:mm:yyyy HH:mm
     * @param date1 - LocalDateTime in format dd:mm:yyyy HH:mm
     */
    public void checkPeriod(LocalDateTime date, LocalDateTime date1) {
        if (date.isAfter(date1)) {
            throw new IllegalArgumentException("The first date is after the second one");
        }
    }
}