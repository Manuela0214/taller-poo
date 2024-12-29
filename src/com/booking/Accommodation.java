package com.booking;

import java.util.ArrayList;
import java.util.List;

public abstract class Accommodation {

    private String name;
    private Double rating;
    private Double nightPrice;
    private String city;
    private List<Room> rooms;
    private List<Activity> activities;

    Accommodation(String name, Double rating, Double nightPrice, String city) {
        this.name = name;
        this.rating = rating;
        this.nightPrice = nightPrice;
        this.city = city;
        this.rooms = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public abstract String getType();

    public abstract boolean roomExists(Room room);

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "nombre='" + name + '\'' +
                ", calificacion=" + rating +
                ", precioNoche=" + nightPrice +
                ", ciudad='" + city + '\'' +
                '}';
    }
}
