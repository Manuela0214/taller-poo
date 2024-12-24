package com.booking;

import java.time.LocalDate;

public abstract class Alojamiento {

    private String nombre;
    private double calificacion;
    private double precioNoche;
    private String ciudad;

    Alojamiento(String nombre, double calificacion, double precioNoche, String ciudad) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.precioNoche = precioNoche;
        this.ciudad = ciudad;
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

    public abstract String getTipo();
}
