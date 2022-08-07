package lapr.project.controller;

import lapr.project.data.ImportFromDataBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OccupancyRateController {

    /**
     * This method returns the occupancy rate by cargo manifest
     * @return a double with the occupancy
     */
    public double getOccupancyRateByCargoManifest(String code, String cm) {
        return ImportFromDataBase.getOccupancyRateByCargoManifest(code, cm);
    }

    /**
     * This method returns the occupancy rate in a certain date
     * @return a double with the occupancy
     */
    public double getOccupancyRateByDate(String code, String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateParsed = LocalDateTime.parse(date, pattern);
        DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateParsed1 = dateParsed.format(pattern1);
        return ImportFromDataBase.getOccupancyRateByDate(code, dateParsed1);
    }
}