package com.booking;

import java.util.ArrayList;
import java.util.List;

public class Finca extends Alojamiento{

    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;

    public Finca(String nombre, double calificacion, double precioNoche, String ciudad) {
        super(nombre, calificacion, precioNoche, ciudad);
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    @Override
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @Override
    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String getTipo() {
        return "Finca";
    }

    @Override
    public boolean habitacionExiste(Habitacion habitacion) {
        return this.habitaciones.stream()
                .anyMatch(h -> h.getNombre().equalsIgnoreCase(habitacion.getNombre()));
    }
}
