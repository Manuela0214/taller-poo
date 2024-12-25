package com.booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Habitacion> confirmarHabitaciones(String nombreAlojamiento, LocalDate inicio, LocalDate fin, int numAdultos, int numNiños, int numHabitaciones){
        List<Habitacion> habitacionesDisponibles = new ArrayList();
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)){
                for(Habitacion habitacion : alojamiento.getHabitaciones()){
                    if(habitacion.getCantidadDisponible() >= numHabitaciones){
                        if(verificarDisponibilidadFecha(habitacion, inicio, fin)){
                            habitacionesDisponibles.add(habitacion);
                        }
                    }
                }
            }
        }
        System.out.println("Habitaciones disponibles en " + nombreAlojamiento + ": ");
        for (Habitacion habitacion : habitacionesDisponibles) {
            System.out.println(
                    habitacion.getNombre() +
                    " - Características : " + habitacion.getCaracteristicas() +
                    " - Precio por noche: " + habitacion.getPrecioNoche()
            );
        }
        return habitacionesDisponibles;
    }

    public Reserva realizarReserva(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numAdutos, int numNiños, Persona persona, Habitacion habitacion) {
        if(alojamiento.habitacionExiste(habitacion) && verificarDisponibilidadFecha(habitacion, inicio, fin)){
            if(habitacion.getCantidadDisponible() <= 0){
                System.out.println("No hay habitaciones disponibles de este tipo.");
            }
            Reserva nuevaReserva = new Reserva(inicio, fin, habitacion, persona);
            habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - 1);
            habitacion.setEstadoDisponibilidad(false);
            reservas.add(nuevaReserva);
            System.out.println("Se ha realizado la reserva con éxito.");
            return nuevaReserva;
        }else {
            System.out.println("No se pudo realizar la reserva. Verifique la disponibilidad.");
            return null;
        }
    }

    public void actualizarReserva(){

    }

    public void cambiarHabitacion(){

    }

    public void cambiarAlojamiento(LocalDate inicio, LocalDate fin, Persona persona, Habitacion habitacion){
        Reserva reservaActual = reservas.stream()
                .filter(
                        r -> r.getHabitacion().equals(habitacion) &&
                                r.getCliente().equals(persona)
                ).findFirst()
                .orElse(null);
        if (reservaActual == null) {
            System.out.println("No tienes una reserva activa para cambiar.");
            return;
        }
        reservas.remove(reservaActual);
        Habitacion habitacionReservada = reservaActual.getHabitacion();
        habitacionReservada.setCantidadDisponible(habitacion.getCantidadDisponible() + 1);
        habitacionReservada.setEstadoDisponibilidad(true);
        System.out.println("Se ha eliminado tu reserva actual. Procede a crear una nueva reserva.");
    }

    //métodos adicionales

    private boolean verificarHabitacionesDisponibles(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numHabitaciones) {
        int totalHabitacionesDisponibles = 0;
        for (Habitacion habitacion : alojamiento.getHabitaciones()) {
            if (habitacion.getCantidadDisponible() > 0 && verificarDisponibilidadFecha(habitacion, inicio, fin)) {
                totalHabitacionesDisponibles += habitacion.getCantidadDisponible();
                if (totalHabitacionesDisponibles >= numHabitaciones) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarDisponibilidadFecha(Habitacion habitacion, LocalDate inicio, LocalDate fin) {
        for(Reserva reserva : reservas){
            if(reserva.getHabitacion().equals(habitacion)){
                if ((inicio.isBefore(reserva.getFin()) && inicio.isAfter(reserva.getInicio())) ||
                        (fin.isBefore(reserva.getFin()) && fin.isAfter(reserva.getInicio())) ||
                        (inicio.isBefore(reserva.getInicio()) && fin.isAfter(reserva.getFin())) ||
                        (inicio.equals(reserva.getInicio()) || fin.equals(reserva.getFin()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Alojamiento obtenerAlojamientoPorNombre(String nombreAlojamiento) {
        Alojamiento alojamiento = alojamientos.stream()
                .filter(a -> a.getNombre().equals(nombreAlojamiento))
                .findFirst()
                .orElse(null);
        return alojamiento;
    }

    public List<Persona> getClientesRegistrados() {
        return reservas.stream()
                .map(Reserva::getCliente)
                .distinct()
                .collect(Collectors.toList());
    }

    public Persona getClientePorNombre(String nombre) {
        return reservas.stream()
                .map(Reserva::getCliente)
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void getReservasActuales(){
        System.out.println("----------Reservas actuales----------");
        for (Reserva reservas: this.getReservas()){
            System.out.println("Cliente: " + reservas.getCliente().toString() +
                    " - Habitación: "+reservas.getHabitacion().toString() +
                    " - Llegada: " +  reservas.getInicio() +
                    "- -Salida: " + reservas.getFin() +
                    "- -Estado: " +reservas.getEstado()
            );
        }
        System.out.println("-------------------------------------");
    }

}
