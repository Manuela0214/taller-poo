package com.booking;

import java.util.List;

public class Apartment extends Accommodation {

    private List<Reservation> reservations;

    public Apartment(String name, Double rating, Double nightPrice, String city) {
        super(name, rating, nightPrice, city);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String getType() {
        return "Apartamento";
    }

    @Override
    public boolean roomExists(Room room) {
        return false;
    }
}
