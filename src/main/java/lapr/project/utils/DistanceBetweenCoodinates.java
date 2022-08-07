package lapr.project.utils;/*
  R = earthâ€™s radius https://en.wikipedia.org/wiki/Earth_radius
 Î”lat = lat2âˆ’ lat1
 Î”long = long2âˆ’ long1
 a = sinÂ²(Î”lat/2) + cos(lat1).cos(lat2).sinÂ²(Î”long/2)
 c = 2.atan2(âˆša, âˆš(1âˆ’a))
 d = R.c
 https://www.movable-type.co.uk/scripts/latlong.html
 https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4
 */

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DistanceBetweenCoodinates {
    /**
     * Calculates the distance in Km between 2 given coordinates.
     * @param lat1 latitude 1
     * @param lon1 longitude 1
     * @param lat2 latitude 2
     * @param lon2 longitude 2
     * @return The distance in Km
     */
    public static double distanceBetweenCoordinates(double lat1, double lon1, double lat2 , double lon2) {
        validateValues(lat1,lon1,lat2,lon2);
        final double R = 6371.0088; // Radious of the earth

        double latDistance = toRad(lat2-lat1);
        double lonDistance = toRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        DecimalFormat df = new DecimalFormat("#.#####"); //get rounded value
        df.setRoundingMode(RoundingMode.CEILING);


        return Double.parseDouble(df.format(R * c).replace(",","."));

    }

    /**
     * This method converts a value from degrees to radian.
     * @param value degree value
     * @return radian value
     */
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    /**
     * Validates the values of the coordinates.
     * @param lat1 latitude 1
     * @param lon1 longitude 1
     * @param lat2 latitude 2
     * @param lon2 longitude 2
     */
    private static void validateValues(double lat1, double lon1, double lat2, double lon2){
        //https://stackoverflow.com/questions/15965166/what-are-the-lengths-of-location-coordinates-latitude-and-longitude
        if(lat1 < -90 || lat1 > 90||lat2< -90 || lat2 > 90) {
            throw new IllegalArgumentException("The latitude cannot be outside of the range -90 - +90");
        }
        if(lon1 < -180 || lon1 > 180||lon2< -180 || lon2 > 180) {
            throw new IllegalArgumentException("The longitude cannot be outside of the range -180 - +180");
        }

    }
}