package com.booking;

import java.time.LocalDate;
import java.util.List;

public class RoomParameters {

    private LocalDate start;
    private LocalDate end;
    private Integer numRooms;
    private Integer daysStay;
    private Accommodation accommodation;
    private LocalDate firstLast5days;
    private LocalDate lastDayMonth;
    private List<Accommodation> accommodationsAvailable;

    public RoomParameters(LocalDate start, LocalDate end, Integer numRooms, Integer daysStay, Accommodation accommodation, LocalDate firstLast5days, LocalDate lastDayMonth, List<Accommodation> accommodationsAvailable) {
        this.start = start;
        this.end = end;
        this.numRooms = numRooms;
        this.daysStay = daysStay;
        this.accommodation = accommodation;
        this.firstLast5days = firstLast5days;
        this.lastDayMonth = lastDayMonth;
        this.accommodationsAvailable = accommodationsAvailable;
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

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public Integer getDaysStay() {
        return daysStay;
    }

    public void setDaysStay(Integer daysStay) {
        this.daysStay = daysStay;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public LocalDate getFirstLast5days() {
        return firstLast5days;
    }

    public void setFirstLast5days(LocalDate firstLast5days) {
        this.firstLast5days = firstLast5days;
    }

    public LocalDate getLastDayMonth() {
        return lastDayMonth;
    }

    public void setLastDayMonth(LocalDate lastDayMonth) {
        this.lastDayMonth = lastDayMonth;
    }

    public List<Accommodation> getAccommodationsAvailable() {
        return accommodationsAvailable;
    }

    public void setAccommodationsAvailable(List<Accommodation> accommodationsAvailable) {
        this.accommodationsAvailable = accommodationsAvailable;
    }
}
