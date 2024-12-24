package com.booking;

import java.time.LocalDate;

public class Reserva {

    private LocalDate inicio;
    private LocalDate fin;
    private Habitacion habitacion;
    private Persona persona;

    public Reserva(LocalDate inicio, LocalDate fin, Habitacion habitacion, Persona persona) {
        this.inicio = inicio;
        this.fin = fin;
        this.habitacion = habitacion;
        this.persona = persona;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean sobreponeFecha (LocalDate fechaInicio, LocalDate fechaFin) {
        return (fechaInicio.isBefore(fin) && fechaFin.isAfter(inicio)) || fechaInicio.equals(inicio) || fechaFin.equals(fin);
    }
}
