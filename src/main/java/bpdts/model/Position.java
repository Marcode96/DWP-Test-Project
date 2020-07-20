package bpdts.model;

// Create the model (Business logic) for the position, i.e. the geographical coordinates

public class Position {

    public double latitude;
    public double longitude;

    public Position(double latitude, double longitude) {
        setPosition(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPosition(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    @Override
    public String toString() {
        return String.format("Position(%3.4f,%3.4f)", latitude, longitude);}

}
