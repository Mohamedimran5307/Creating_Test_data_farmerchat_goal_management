package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserLocation {
    @JsonProperty("user_id")
    private String user_id;
    private String lat;
    @JsonProperty("long")
    private String longitude;

    // Default constructor
    public UpdateUserLocation() {
    }

    // Constructor with parameters
    public UpdateUserLocation(String user_id, String lat, String longitude) {
        this.user_id = user_id;
        this.lat = lat;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    // Use @JsonProperty to map to "long" in JSON
    @JsonProperty("long")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("long")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public String toString() {
        return "UpdateUserLocation{" +
                "user_id='" + user_id + '\'' +
                ", lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
