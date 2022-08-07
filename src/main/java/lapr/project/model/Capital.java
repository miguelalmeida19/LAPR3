package lapr.project.model;

public class Capital extends Country{
    public Capital(String countryName, String continent, String capital, double latitude, double longitude) {
        super(countryName, continent, capital, latitude, longitude);
    }

    @Override
    public double getLatitude() {
        return super.getLatitude();
    }

    @Override
    public double getLongitude() {
        return super.getLongitude();
    }

    @Override
    public String getCapital() {
        return super.getCapital();
    }

    @Override
    public String getContinent() {
        return super.getContinent();
    }

    @Override
    public String getCountryName() {
        return super.getCountryName();
    }

    @Override
    public String toString() {
        return getCapital();
    }
}
