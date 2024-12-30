package com.booking.models;

import com.booking.visitor.ReportVisitor;

import java.util.ArrayList;
import java.util.List;

public class Farm extends Accommodation {

    private List<Reservation> reservations;

    public Farm(String name, Double rating, Double nightPrice, String city) {
        super(name, rating, nightPrice, city);
        this.reservations = new ArrayList<>();
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

    @Override
    public void acceptVisitor(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
