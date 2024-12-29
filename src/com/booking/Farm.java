package com.booking;

import java.util.List;

public class Farm extends Accommodation {

    private List<Reservation> reservations;

    public Farm(String name, Double rating, Double nightPrice, String city) {
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
        return "Finca";
    }

    @Override
    public boolean roomExists(Room room) {
        return false;
    }
}
