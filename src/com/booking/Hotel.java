package com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Alojamiento {

    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;

    public Hotel(String nombre, double calificacion, double precioNoche, String ciudad) {
        super(nombre, calificacion, precioNoche, ciudad);
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    public void agregarReserva(Reserva reservaActual) {
        reservas.add(reservaActual);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String getTipo() {
        return "Hotel";
    }
}