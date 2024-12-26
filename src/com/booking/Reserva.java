package com.booking;

import java.time.LocalDate;

public class Reserva {

    private LocalDate inicio;
    private LocalDate fin;
    private Habitacion habitacion;
    private Persona cliente;
    private String estado;
    private Alojamiento alojamiento;

    public Reserva(LocalDate inicio, LocalDate fin, Habitacion habitacion, Persona cliente, Alojamiento alojamiento) {
        this.inicio = inicio;
        this.fin = fin;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.estado = "confirmada";
        this.alojamiento = alojamiento;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", habitacion=" + habitacion +
                ", cliente=" + cliente +
                ", estado='" + estado + '\'' +
                ", alojamiento=" + alojamiento +
                '}';
    }
}
