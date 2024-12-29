package com.booking;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Accommodation {

    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(String name, Double rating, Double nightPrice, String city) {
        super(name, rating, nightPrice, city);
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void addReservation(Reservation reservationActual) {
        reservations.add(reservationActual);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String getType() {
        return "Hotel";
    }

    @Override
    public boolean roomExists(Room room) {
        return this.rooms.stream()
                .anyMatch(h -> h.getName().equalsIgnoreCase(room.getName()));
    }

}