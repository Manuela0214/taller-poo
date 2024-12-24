package com.booking;

import java.util.ArrayList;
import java.util.List;

public class Finca extends Alojamiento{

    private List<Habitacion> habitaciones;

    public Finca(String nombre, double calificacion, double precioNoche, String ciudad) {
        super(nombre, calificacion, precioNoche, ciudad);
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    @Override
    public String getTipo() {
        return "Finca";
    }
}
