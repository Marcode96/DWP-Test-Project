package bpdts.model;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetAddress;
import java.net.UnknownHostException;

// Create the model (Business Logic) for a person/user

@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    // Info obtained from https://www.latlong.net/place/london-the-uk-14153.html#:~:text=The%20latitude%20of%20London%2C%20the,%C2%B0%207'%205.1312''%20W.
    public static final double LONDON_LATITUDE = 51.5098;
    public static final double LONDON_LONGITUDE = -0.1180;

    private double id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;

    @JsonProperty("ip_address")
    private String ipAddress;

    private Double latitude;
    private Double longitude;

    // To get the Host Address of an IP address
    @JsonIgnore
    private InetAddress inetAddress;

    // Constructor
    public Person() {
    }

    public Person(Double id, String firstName, String lastName, double latitude, double longitude) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        try {
            this.inetAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            log.warn("Inet address from IP address {} failed to set", ipAddress);
        }
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", ipAddress=" + ipAddress +
                ", inetAddress=" + inetAddress +
                ", lat/lon=(" + latitude + "," + longitude + ")" +
                '}';
    }


}
