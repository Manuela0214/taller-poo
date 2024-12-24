package com.booking;

public class Habitacion {

    private String nombre;
    private String caracteristicas;
    private double precioNoche;
    private int cantidadDisponible;

    public Habitacion(String nombre, String caracteristicas, double precioNoche, int cantidadDisponible) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.precioNoche = precioNoche;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void disminuirDisponibilidad() {
        if (this.cantidadDisponible > 0) {
            this.cantidadDisponible--;
        } else {
            System.out.println("No hay habitaciones disponibles");
        }
    }

    public void aumentarDisponibilidad() {
        this.cantidadDisponible++;
    }
}
