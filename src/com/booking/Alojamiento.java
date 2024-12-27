package com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Alojamiento {

    private String nombre;
    private double calificacion;
    private double precioNoche;
    private String ciudad;
    private List<Habitacion> habitaciones;
    private List<Actividad> actividades;

    Alojamiento(String nombre, double calificacion, double precioNoche, String ciudad) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.precioNoche = precioNoche;
        this.ciudad = ciudad;
        this.habitaciones = new ArrayList<>();
        this.actividades = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public abstract String getTipo();

    public abstract boolean habitacionExiste(Habitacion habitacion);

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "nombre='" + nombre + '\'' +
                ", calificacion=" + calificacion +
                ", precioNoche=" + precioNoche +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }

//    public Habitacion obtenerHabitacionPorNombre(String nombreHabitacion) {
//        Habitacion habitacion = getHabitaciones().stream()
//                .filter(a -> a.getNombre().equals(nombreHabitacion))
//                .findFirst()
//                .orElse(null);
//        return habitacion;
//    }
}
