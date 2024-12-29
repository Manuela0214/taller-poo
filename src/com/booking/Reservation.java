package com.booking;

import java.time.LocalDate;

public class Reservation {

    private LocalDate start;
    private LocalDate end;
    private Room room;
    private Customer customer;
    private String status;
    private Accommodation accommodation;
    private Activity activity;

    public Reservation(LocalDate start, LocalDate end, Room room, Customer customer, Accommodation accommodation) {
        this.start = start;
        this.end = end;
        this.room = room;
        this.customer = customer;
        this.status = "confirmed";
        this.accommodation = accommodation;
    }

    public Reservation(LocalDate start, LocalDate end, Customer customer, Accommodation accommodation) {
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.status = "confirmed";
        this.accommodation = accommodation;
    }

    public Reservation(LocalDate start, LocalDate end, Activity activity, Customer customer, Accommodation accommodation) {
        this.start = start;
        this.end = end;
        this.activity = activity;
        this.customer = customer;
        this.status = "confirmed";
        this.accommodation = accommodation;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                accommodation +
                ", inicio=" + start +
                ", fin=" + end +
                ", " + room +
                ", " + activity +
                ", estado='" + status + '\'' +
                ", " + customer +
                '}';
    }
}
