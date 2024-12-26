package com.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                if (verificarHabitacionesDisponibles(alojamiento, inicio, fin, numHabitaciones,numAdultos,numNiños) && !(alojamiento instanceof DiaDeSol)) {
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
                                    ", Precio Base: " + habitacionMasEconomica.getPrecioNoche() * diasEstadia * numHabitaciones +
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
        if(alojamientosDisponibles.isEmpty()){
            System.out.println("No existe ese tipo de alojamiento en la ciudad especificada.");
        }
        return alojamientosDisponibles;
    }

    public List<Habitacion> confirmarHabitaciones(String nombreAlojamiento, LocalDate inicio, LocalDate fin, int numAdultos, int numNiños, int numHabitaciones){
        List<Habitacion> habitacionesDisponibles = new ArrayList();
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)){
                for(Habitacion habitacion : alojamiento.getHabitaciones()){
                    if(habitacion.getCantidadDisponible() >= numHabitaciones && habitacion.getCapacidadMaxima() >= (numAdultos + numNiños)){
                        if(verificarDisponibilidadFecha(alojamiento,habitacion, inicio, fin)){
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

    public void realizarReserva(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numAdultos, int numNiños, Persona persona, Habitacion habitacion) {
        Reserva nuevaReserva = new Reserva(inicio,fin,habitacion,persona,alojamiento);
        boolean reservada = false;
        if (!alojamiento.habitacionExiste(habitacion)) {
            System.out.println("El alojamiento no contiene la habitación seleccionada.");
        }else{
            for (Reserva reserva : reservas){
                if (reserva.equals(nuevaReserva)){
                    System.out.println("La habitacion ya se encuentra reservada en el alojamiento especificado.");
                    reservada = true;
                }
            }
            if (!reservada && verificarDisponibilidadFecha(alojamiento,habitacion, inicio, fin)){
                if(habitacion.getCantidadDisponible() <= 0){
                    System.out.println("No hay habitaciones disponibles de este tipo.");
                }
                habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - 1);
                //habitacion.setEstadoDisponibilidad(false);
                reservas.add(nuevaReserva);
                System.out.println("Se ha realizado la reserva con éxito.");
            }else {
                System.out.println("No se pudo realizar la reserva. Verifique la disponibilidad.");
            }
        }
    }

    public void realizarReserva(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numAdultos, int numNiños, Persona persona, Actividad actividad) {
        Reserva nuevaReserva = new Reserva(inicio,fin,actividad,persona,alojamiento);
        reservas.add(nuevaReserva);
        System.out.println("Se ha realizado la reserva con éxito.");
    }


    public void actualizarReserva(String email, String fechaNacimiento){
        Persona cliente = autenticacion(email, LocalDate.parse(fechaNacimiento, DateTimeFormatter.ISO_LOCAL_DATE));
        if(cliente != null){
            if(getReservasActualesPorCliente(cliente).isEmpty()){
                System.out.println("El cliente no posee reservas actualmente.");
            }
            System.out.println(getReservasActualesPorCliente(cliente).toString());
            System.out.println("¿Cómo desea actualizar su reserva? (Ingrese una opción)");
            System.out.println("1. Cambio de habitación");
            System.out.println("2. Cambio de alojamiento");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("CAMBIO DE HABITACION");
                    cambiarHabitacion(cliente);
                    break;
                case 2:
                    System.out.println("CAMBIO DE ALOJAMIENTO");
                    cambiarAlojamiento(cliente);
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }else{
            System.out.println("Ha ocurrido un error durante la autenticación. Email y/o fecha de nacimiento inválida");
        }
    }

    public void cambiarHabitacion(Persona cliente){
        Scanner scanner = new Scanner(System.in);

        List<Reserva> reservasCliente = getReservasActualesPorCliente(cliente);
        System.out.println("Estas son tus habitaciones reservadas:");
        int count = 1;
        for(Reserva reserva : getReservasActualesPorCliente(cliente)){
            if(!(reserva.getAlojamiento() instanceof DiaDeSol)){
                System.out.println(count + ": " + reserva.getAlojamiento().getNombre() +" - "+ reserva.getHabitacion());
                count++;
            }
        }
        System.out.println("Selecciona el número de la habitación que quieres cambiar:");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion < 1 || opcion > reservasCliente.size()) {
            System.out.println("Opción inválida.");
            return;
        }
        Reserva reservaSeleccionada = reservasCliente.get(opcion - 1);
        Habitacion habitacionActual = reservaSeleccionada.getHabitacion();
        Alojamiento alojamiento = reservasCliente.getFirst().getAlojamiento();
        System.out.println("Estas son las habitaciones disponibles en " + alojamiento.getNombre() + ":");
        List<Habitacion> habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                .filter(h -> h.isEstadoDisponibilidad() && !h.equals(habitacionActual))
                .collect(Collectors.toList());

        if (habitacionesDisponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles en este alojamiento.");
            return;
        }
        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            Habitacion habitacion = habitacionesDisponibles.get(i);
            System.out.println((i + 1) + ". Habitación: " + habitacion.getNombre() +
                    " (Precio por noche: $" + habitacion.getPrecioNoche() + ")");
        }
        System.out.println("Selecciona el número de la nueva habitación:");
        int nuevaHabitacionOpcion = scanner.nextInt();
        scanner.nextLine();

        if (nuevaHabitacionOpcion < 1 || nuevaHabitacionOpcion > habitacionesDisponibles.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        Habitacion nuevaHabitacion = habitacionesDisponibles.get(nuevaHabitacionOpcion - 1);

        reservas.remove(reservaSeleccionada);
        habitacionActual.setCantidadDisponible(habitacionActual.getCantidadDisponible() + 1);
        habitacionActual.setEstadoDisponibilidad(true);

        nuevaHabitacion.setCantidadDisponible(nuevaHabitacion.getCantidadDisponible() - 1);
        if (nuevaHabitacion.getCantidadDisponible() <= 0) {
            nuevaHabitacion.setEstadoDisponibilidad(false);
        }

        Reserva nuevaReserva = new Reserva(reservaSeleccionada.getInicio(), reservaSeleccionada.getFin(),
                nuevaHabitacion, cliente, alojamiento);
        reservas.add(nuevaReserva);

        System.out.println("Se ha cambiado tu reserva a la habitación " + nuevaHabitacion.getNombre() + ".");
    }

    public void cambiarAlojamiento(Persona cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la fecha de inicio (formato YYYY-MM-DD):");
        String inicioStr = scanner.nextLine();
        LocalDate inicio = LocalDate.parse(inicioStr);
        System.out.println("Ingrese la fecha de fin (formato YYYY-MM-DD):");
        String finStr = scanner.nextLine();
        LocalDate fin = LocalDate.parse(finStr);
        System.out.println("Ingrese el nombre del alojamiento:");
        String nombreAlojamiento = scanner.nextLine();
        System.out.println("Ingrese el nombre de la habitación:");
        String nombreHabitacion = scanner.nextLine();

        Reserva reservaActual = reservas.stream()
                .filter(r -> !(r.getAlojamiento() instanceof DiaDeSol) && r.getCliente().equals(cliente) &&
                        r.getHabitacion().getNombre().equalsIgnoreCase(nombreHabitacion) &&
                        r.getAlojamiento().getNombre().equalsIgnoreCase(nombreAlojamiento))
                .findFirst()
                .orElse(null);

        if (reservaActual == null) {
            System.out.println("No tienes una reserva activa para cambiar con la habitación especificada.");
            return;
        }

        reservas.remove(reservaActual);
        Habitacion habitacionReservada = reservaActual.getHabitacion();
        habitacionReservada.setCantidadDisponible(habitacionReservada.getCantidadDisponible() + 1);
        habitacionReservada.setEstadoDisponibilidad(true);
        System.out.println("Se ha eliminado tu reserva actual. Procede a crear una nueva reserva.");
    }
    //métodos adicionales

    private boolean verificarHabitacionesDisponibles(Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int numHabitaciones, int numAdultos, int numNiños) {
        int totalHabitacionesDisponibles = 0;
        for (Habitacion habitacion : alojamiento.getHabitaciones()) {
            if (habitacion.getCantidadDisponible() > 0
                    && verificarDisponibilidadFecha(alojamiento,habitacion, inicio, fin) &&
                    habitacion.getCapacidadMaxima() > (numAdultos + numNiños)
            ) {
                totalHabitacionesDisponibles += habitacion.getCantidadDisponible();
                if (totalHabitacionesDisponibles >= numHabitaciones) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarDisponibilidadFecha(Alojamiento alojamiento, Habitacion habitacion, LocalDate inicio, LocalDate fin) {
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion) && reserva.getAlojamiento().equals(alojamiento)) {
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

    public Persona getClientePorEmail(String email) {
        return reservas.stream()
                .map(Reserva::getCliente)
                .filter(cliente -> cliente.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public void getReservasActuales(){
        System.out.println("----------Reservas actuales----------");
        for (Reserva reservas: this.getReservas()){
            if(!(reservas.getAlojamiento() instanceof DiaDeSol)){
                System.out.println("Cliente: " + reservas.getCliente().toString() +
                        " - Habitación: "+reservas.getHabitacion().toString() +
                        " - Llegada: " +  reservas.getInicio() +
                        "- -Salida: " + reservas.getFin() +
                        "- -Estado: " +reservas.getEstado()
                );
            }else{
                System.out.println("Cliente: " + reservas.getCliente().toString() +
                        " - Actividades: "+reservas.getActividad().toString() +
                        " - Llegada: " +  reservas.getInicio() +
                        "- -Salida: " + reservas.getFin() +
                        "- -Estado: " +reservas.getEstado()
                );
            }
        }
        System.out.println("-------------------------------------");
    }

    public List<Reserva> getReservasActualesPorCliente(Persona cliente) {
        return reservas.stream()
                .filter(reserva -> reserva.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }


    private Persona autenticacion(String email, LocalDate fechaNacimiento){
        for (Persona persona : this.getClientesRegistrados()){
            if((persona.getFechaNacimiento().equals(fechaNacimiento) &&
                    (persona.getEmail().equalsIgnoreCase(email)))){
                System.out.println("Bienvenid@ " + persona.getNombre());
                return persona;
            }
        }
        System.out.println("No se encontró un cliente con los datos proporcionados.");
        return null;
    }

}
