package lapr.project.model;

public class Country {

    private final String countryName;
    private final String continent;
    private final String capital;
    private final double latitude;
    private final double longitude;

    public Country(String countryName, String continent, String capital, double latitude, double longitude){
        this.countryName = countryName;
        this.continent = continent;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getContinent() {
        return continent;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCapital() {
        return capital;
    }

    public String getCountryName() {
        return countryName;
    }
}
