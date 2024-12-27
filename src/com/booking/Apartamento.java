package com.booking;

import java.util.ArrayList;
import java.util.List;

public class Apartamento extends Alojamiento{

    private List<Reserva> reservas;

    public Apartamento(String nombre, double calificacion, double precioNoche, String ciudad) {
        super(nombre, calificacion, precioNoche, ciudad);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String getTipo() {
        return "Apartamento";
    }

    @Override
    public boolean habitacionExiste(Habitacion habitacion) {
        return false;
    }
}
