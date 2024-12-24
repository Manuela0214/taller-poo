package com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaReservas {

    private List<Alojamiento> alojamientos;
    private List<Reserva> reservas;

    public SistemaReservas() {
        this.alojamientos = new ArrayList();
        this.reservas = new ArrayList();
    }

    public void agregarAlojamiento(Alojamiento alojamiento) {
        this.alojamientos.add(alojamiento);
    }

    public String reservar(Persona persona, LocalDate inicio, LocalDate fin, Habitacion habitacion) {
        if(habitacion.getCantidadDisponible() <= 0){
            return "No hay habitaciones disponibles de este tipo.";
        }
        Reserva nuevaReserva = new Reserva(inicio, fin, habitacion, persona);
        habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - 1);
        reservas.add(nuevaReserva);
        return "Se ha realizado la reserva con éxito.";
    }

    public List<Habitacion> confirmarHabitaciones(String nombreAlojamiento, LocalDate inicio, LocalDate fin, int numAdultos, int numNiños, int numHabitaciones){
        List<Habitacion> habitacionesDisponibles = new ArrayList();
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)){
                for(Habitacion habitacion : alojamiento.getHabitaciones()){
                    if(habitacion.getCantidadDisponible() >= numHabitaciones){
                        if(verificarDisponibilidad(habitacion, inicio, fin)){
                            habitacionesDisponibles.add(habitacion);
                        }
                    }
                }
            }
        }
        return habitacionesDisponibles;
    }

    private boolean verificarDisponibilidad(Habitacion habitacion, LocalDate inicio, LocalDate fin) {
        for(Reserva reserva : reservas){
            if(reserva.getHabitacion().equals(habitacion)){
                if ((inicio.isBefore(reserva.getFin()) && inicio.isAfter(reserva.getInicio())) ||
                        (fin.isBefore(reserva.getFin()) && fin.isAfter(reserva.getInicio()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
