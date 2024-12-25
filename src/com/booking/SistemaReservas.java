package com.booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Alojamiento> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate inicio, LocalDate fin,
                                                int numAdultos, int numNiños, int numHabitaciones) {
        List<Alojamiento> alojamientosDisponibles = new ArrayList<>();
        int diasEstadia = (int) ChronoUnit.DAYS.between(inicio, fin);
        LocalDate ultimoDiaMes = inicio.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate primerUltimos5Dias = ultimoDiaMes.minusDays(4);

        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) && tipoAlojamiento.equals(alojamiento.getTipo())) {
                if (verificarHabitacionesDisponibles(alojamiento, inicio, fin, numHabitaciones) && !(alojamiento instanceof DiaDeSol)) {
                    Habitacion habitacionMasEconomica = alojamiento.getHabitaciones()
                            .stream()
                            .min(Comparator.comparingDouble(Habitacion::getPrecioNoche))
                            .orElse(null);
                    if (alojamiento.getHabitaciones() == null || alojamiento.getHabitaciones().isEmpty()) continue;
                    double precioBase = habitacionMasEconomica.getPrecioNoche() * diasEstadia * numHabitaciones;
                    double aumento = 0;
                    double descuento = 0;


                    if(inicio.getDayOfMonth() >= 5 && fin.getDayOfMonth() <=  10){
                        descuento = precioBase * 0.08;
                        precioBase *= 0.92;
                    }else if(inicio.getDayOfMonth() >= 10 && fin.getDayOfMonth() <=  15){
                        aumento = precioBase * 0.10;
                        precioBase *= 1.10;
                    }if(inicio.getDayOfMonth() >= primerUltimos5Dias.getDayOfMonth()
                            && fin.getDayOfMonth() <=  ultimoDiaMes.getDayOfMonth()){
                        aumento = precioBase * 0.15;
                        precioBase *= 1.15;
                    }


                    alojamientosDisponibles.add(alojamiento);
                    System.out.println(
                            alojamiento.getTipo() + ": " + alojamiento.getNombre() +
                                    ", Calificación: " + alojamiento.getCalificacion() +
                                    ", Precio Base: " + alojamiento.getPrecioNoche() * diasEstadia * numHabitaciones +
                                    ", Aumento: " + Math.round(aumento) +
                                    ", Descuento: " + Math.round(descuento) +
                                    ", Precio Total: " + Math.round(precioBase) + "\n"
                    );
                }if (alojamiento instanceof DiaDeSol){
                    DiaDeSol diaDeSol = (DiaDeSol)alojamiento;
                    System.out.println(alojamiento.getTipo() + ": " + alojamiento.getNombre());
                    System.out.println("Actividades: ");
                    for (Actividad actividad : diaDeSol.getActividades()) {
                        String incluyeComida = actividad.isIncluyeComida() ? "SI incliye" : "NO incluye";
                        System.out.println(
                                actividad.getNombre() +
                                        "- Comida/Refrigerio: " + incluyeComida +
                                        "- Precio: " + actividad.getPrecio()
                        );
                    }
                    System.out.println("\n");
                }
            }
        }
        return alojamientosDisponibles;
    }



    //métodos adicionales

    private boolean verificarHabitacionesDisponibles(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numHabitaciones) {
        int totalHabitacionesDisponibles = 0;
        for (Habitacion habitacion : alojamiento.getHabitaciones()) {
            if (habitacion.getCantidadDisponible() > 0 && verificarDisponibilidad(habitacion, inicio, fin)) {
                totalHabitacionesDisponibles += habitacion.getCantidadDisponible();
                if (totalHabitacionesDisponibles >= numHabitaciones) {
                    return true;
                }
            }
        }
        return false;
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
