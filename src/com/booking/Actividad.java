package com.booking;

public class Actividad {

    private String nombre;
    private boolean incluyeComida;
    private double precio;

    public Actividad(String nombre, boolean incluyeComida, double precio) {
        this.nombre = nombre;
        this.incluyeComida = incluyeComida;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isIncluyeComida() {
        return incluyeComida;
    }

    public void setIncluyeComida(boolean incluyeComida) {
        this.incluyeComida = incluyeComida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", incluyeComida=" + incluyeComida +
                ", precio=" + precio +
                '}';
    }
}
