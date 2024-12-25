package com.booking;

public class Habitacion {

    private String nombre;
    private String caracteristicas;
    private double precioNoche;
    private int cantidadDisponible;
    private boolean estadoDisponibilidad;
    private int capacidadMaxima;

    public Habitacion(String nombre, String caracteristicas, double precioNoche, int cantidadDisponible, int capacidadMaxima) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.precioNoche = precioNoche;
        this.cantidadDisponible = cantidadDisponible;
        this.estadoDisponibilidad = true;
        this.capacidadMaxima = capacidadMaxima;
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

    public boolean isEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(boolean estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void disminuirDisponibilidad() {
        if (this.cantidadDisponible > 0) {
            this.cantidadDisponible--;
            if(this.cantidadDisponible == 0){
                this.estadoDisponibilidad = false;
            }
        } else {
            System.out.println("No hay habitaciones disponibles");
        }
    }

    public void aumentarDisponibilidad() {
        this.cantidadDisponible++;
        if(this.cantidadDisponible > 0 ){
            this.estadoDisponibilidad = true;
        }
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "nombre='" + nombre + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", precioNoche=" + precioNoche +
                ", cantidadDisponible=" + cantidadDisponible +
                ", estadoDisponibilidad=" + estadoDisponibilidad +
                ", capacidadMaxima=" + capacidadMaxima +
                '}';
    }
}
