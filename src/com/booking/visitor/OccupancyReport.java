package com.booking.visitor;

import com.booking.models.Apartment;
import com.booking.models.DayOfSun;
import com.booking.models.Farm;
import com.booking.models.Hotel;
import com.booking.services.SystemReservations;

public class OccupancyReport implements ReportVisitor {

    private final SystemReservations systemReservations;

    public OccupancyReport(SystemReservations systemReservations) {
        this.systemReservations = systemReservations;
    }

    @Override
    public void visit(Hotel hotel) {
        int count = systemReservations.getReservationCountByAccommodation(hotel);
        System.out.println("Hotel: " + hotel.getName() + " - Número de reservas: " + count);
    }

    @Override
    public void visit(Apartment apartment) {
        int count = systemReservations.getReservationCountByAccommodation(apartment);
        System.out.println("Apartamento: " + apartment.getName() + " - Número de reservas: " + count);
    }

    @Override
    public void visit(Farm farm) {
        int count = systemReservations.getReservationCountByAccommodation(farm);
        System.out.println("Finca: " + farm.getName() + " - Número de reservas: " + count);
    }

    @Override
    public void visit(DayOfSun dayOfSun) {
        int count = systemReservations.getReservationCountByAccommodation(dayOfSun);
        System.out.println("Dia de Sol: " + dayOfSun.getName() + " - Número de reservas: " + count);
    }
}
