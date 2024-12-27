package com.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaReservas sistema = new SistemaReservas();

        //----------------------------Clientes
        Persona cliente1 = new Persona(
                "Pepito",
                "Perez",
                "pepito@gmail.com",
                "Colombiana",
                "3102254622",
                LocalTime.of(13,30),
                LocalDate.of(1998, 1, 27)
        );
        Persona cliente2 = new Persona(
                "Ana",
                "Josefina",
                "anita@gmail.com",
                "Mexicana",
                "3001457742",
                LocalTime.of(12,0),
                LocalDate.of(1976, 6, 15)
        );
        Persona cliente3 = new Persona(
                "Jose",
                "Buendia",
                "josebuen@gmail.com",
                "Español",
                "3145512688",
                LocalTime.of(1,0),
                LocalDate.of(1980, 7, 14)
        );

        //----------------------------Habitaciones
        Habitacion habitacion1 = new Habitacion(
                "Habitación doble",
                "2 camas dobles, Vista al mar, Aire acondicionado, Cafetera",
                130,
                5,
                4
        );
        Habitacion habitacion2 = new Habitacion(
                "Habitación Sencilla",
                "1 cama sencilla, Escritorio, WiFi gratuito, Baño privado",
                80,
                6,
                2
        );
        Habitacion habitacion3 = new Habitacion(
                "Habitación Suite",
                "Cama King, Jacuzzi, Vista panorámica, Minibar, TV de pantalla plana",
                200,
                3,
                2
        );
        Habitacion habitacion4 = new Habitacion(
                "Habitación Triple",
                "3 camas individuales, Balcón privado, Aire acondicionado, TV, Mini nevera",
                150,
                5,
                9
        );
        Habitacion habitacion5 = new Habitacion(
                "Habitación Familiar",
                "2 camas dobles, Sofá cama, Cocina completa, Baño privado, WiFi gratuito",
                180,
                6,
                20
        );
        Habitacion habitacion6 = new Habitacion(
                "Habitación Deluxe",
                "Cama King, Jacuzzi privado, Terraza con vista al mar, Minibar, Smart TV",
                250,
                2,
                2
        );

        //----------------------------Actividades
        Actividad actividades1 = new Actividad("Natación", true, 20);
        Actividad actividades2 = new Actividad("Paseo en bote", true, 25);
        Actividad actividades3 = new Actividad("Caminata", false, 15);
        Actividad actividades4 = new Actividad("Ciclo paseo", true, 10);
        Actividad actividades5 = new Actividad("Sendero ecológico", false, 30);

        //----------------------------Alojamientos
        //Bogotá
        Hotel hotel1B = new Hotel("Hotel Paraiso", 4.2, 100, "Bogota");
        Hotel hotel2B = new Hotel("Hotel la Colina", 4.5, 110, "Bogota");
        Hotel hotel3B = new Hotel("Hotel Guamal", 4.7, 114, "Bogota");
        Apartamento apartamento1B = new Apartamento("Torres Niza", 4.8, 120, "Bogota");
        Apartamento apartamento2B = new Apartamento("El Mirador", 4.1, 90, "Bogota");
        //Medellin
        Apartamento apartamento1M = new Apartamento("Guaduales apartamentos",4.0, 95, "Medellin");
        Apartamento apartamento2M = new Apartamento("La Morada",4.3, 90, "Medellin");
        Apartamento apartamento3M = new Apartamento("Edificio La Montañita",4.7, 80, "Medellin");
        Finca finca1M = new Finca("Finca la maria", 4.8, 120, "Medellin");
        //Pereira
        DiaDeSol diaDeSol1P = new DiaDeSol("La Rochela", 4.6, 50, "Pereira");
        DiaDeSol diaDeSol2P = new DiaDeSol("Santagueda", 4.3, 45, "Pereira");
        Hotel hotel1P = new Hotel("Hotel la Estacion", 4.7, 114, "Pereira");

        //----------------------------Creación de habitaciones y actividades
        //Bogota
        hotel1B.agregarHabitacion(habitacion1);
        hotel1B.agregarHabitacion(habitacion2);
        hotel1B.agregarHabitacion(habitacion3);
        hotel1B.agregarHabitacion(habitacion6);
        hotel2B.agregarHabitacion(habitacion4);
        hotel2B.agregarHabitacion(habitacion5);
        hotel2B.agregarHabitacion(habitacion1);
        hotel3B.agregarHabitacion(habitacion2);
        hotel3B.agregarHabitacion(habitacion3);
        hotel3B.agregarHabitacion(habitacion4);
        hotel3B.agregarHabitacion(habitacion5);
        //Pereira
        diaDeSol1P.agregarActividad(actividades1);
        diaDeSol1P.agregarActividad(actividades2);
        diaDeSol1P.agregarActividad(actividades3);
        diaDeSol2P.agregarActividad(actividades4);
        diaDeSol2P.agregarActividad(actividades5);
        hotel1P.agregarHabitacion(habitacion1);

        //----------------------------creación alojamientos en el sistema de reservas
        //Bogota
        sistema.agregarAlojamiento(hotel1B);
        sistema.agregarAlojamiento(hotel2B);
        sistema.agregarAlojamiento(hotel3B);
        sistema.agregarAlojamiento(apartamento1B);
        sistema.agregarAlojamiento(apartamento2B);
        //Medellin
        sistema.agregarAlojamiento(apartamento1M);
        sistema.agregarAlojamiento(apartamento2M);
        sistema.agregarAlojamiento(apartamento3M);
        sistema.agregarAlojamiento(finca1M);
        //Pereira
        sistema.agregarAlojamiento(diaDeSol1P);
        sistema.agregarAlojamiento(diaDeSol2P);
        sistema.agregarAlojamiento(hotel1P);

        //----------------------------busqueda de alojamientos por parametro
        LocalDate inicio = LocalDate.of(2024, 12, 27);
        LocalDate fin = LocalDate.of(2024, 12, 29);
//        //fechas solapadas
//        LocalDate fechaInicioS = LocalDate.of(2024, 12, 26);
//        LocalDate fechaFinS = LocalDate.of(2024, 12, 28);

//        sistema.buscarAlojamientos("Bogota", "Hotel", inicio, fin, 2, 1, 2);
//        sistema.buscarAlojamientos("Pereira", "Dia de Sol", inicio, fin, 2, 1, 2);
//        sistema.buscarAlojamientos("Bogota", "Apartamento", inicio, fin, 2, 1, 1);
//        sistema.buscarAlojamientos("Medellin", "Finca", inicio, fin, 2, 1, 1);

        //confirmacion caracteristicas de alojamientos
//        sistema.confirmarHabitaciones("Hotel Paraiso", fechaInicio, fechaFin, 2, 1, 1);  //agregar 1 niño

        //reservas de alojamientos
        sistema.realizarReserva(hotel1B, inicio, fin, 2,2,cliente1,habitacion1);
        sistema.realizarReserva(hotel1B, inicio, fin, 2,2,cliente1,habitacion2);
//        sistema.realizarReserva(finca1M, fechaInicio, fechaFin, 2,1,cliente2,habitacion5);
//        sistema.realizarReserva(apartamento1M, fechaInicio, fechaFin, 3,1,cliente3,habitacion2);
//        sistema.realizarReserva(hotel1B, fechaInicioS, fechaFinS, 2,1,cliente2,habitacion1);  //fecha se solapa
//        sistema.realizarReserva(diaDeSol1P, fechaInicioS, fechaFinS, 2,1,cliente2,actividades1); //dia de sol

//        sistema.getReservasActuales();
//
        //actualizar reserva
//        sistema.actualizarReserva("pepito@gmail.com","1998-01-27");

        mostrarMenu(sistema);
    }

    public static void mostrarMenu(SistemaReservas sistema){

        System.out.println("*** Menú principal ***");
        System.out.println("1. Buscar alojamiento");
        System.out.println("2. Ver habitaciones");
        System.out.println("3. Realizar reserva");
        System.out.println("4. Ver mis reservas");
        System.out.println("5. Actualizar Reserva");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion){
            case 1:
                try {
                    System.out.print("Ingrese la ubicación del alojamiento (ejemplo: Bogotá): ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Ingrese el tipo de alojamiento (ejemplo: Hotel): ");
                    String tipoAlojamiento = scanner.nextLine();
                    System.out.print("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
                    String fechaInicioStr = scanner.nextLine();
                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
                    String fechaFinStr = scanner.nextLine();
                    LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese el número de adultos: ");
                    int numAdultos = scanner.nextInt();
                    System.out.print("Ingrese el número de niños: ");
                    int numNinos = scanner.nextInt();
                    System.out.print("Ingrese el número de habitaciones: ");
                    int numHabitaciones = scanner.nextInt();
                    sistema.buscarAlojamientos(ubicacion, tipoAlojamiento, fechaInicio, fechaFin, numAdultos, numNinos, numHabitaciones);
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Ocurrió un error al procesar los datos. Verifique la información ingresada.");
                }
                mostrarMenu(sistema);
                break;
            case 2:
                try {
                    System.out.print("Ingrese el nombre del alojamiento: ");
                    String alojamiento = scanner.nextLine();
                    System.out.print("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
                    String fechaInicioStr = scanner.next();
                    LocalDate fecha_Inicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
                    String fechaFinStr = scanner.next();
                    LocalDate fecha_Fin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese el número de habitaciones que desea reservar: ");
                    int numHabitaciones = scanner.nextInt();
                    System.out.print("Ingrese el número de adultos: ");
                    int numAdultos = scanner.nextInt();
                    System.out.print("Ingrese el número de niños: ");
                    int numNinos = scanner.nextInt();
                    sistema.confirmarHabitaciones(alojamiento, fecha_Inicio, fecha_Fin, numHabitaciones, numAdultos, numNinos);
                    scanner.nextLine();
                }catch (Exception e){
                    System.out.println("Ocurrió un error al procesar los datos. Verifique la información ingresada.");
                    System.out.println(e.getMessage());
                }
                mostrarMenu(sistema);
                break;
            case 3:
                try {
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese su apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese su correo: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese su nacionalidad: ");
                    String nacionalidad = scanner.nextLine();
                    System.out.print("Ingrese su telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese su fecha de nacimiento (formato: yyyy-MM-dd): ");
                    String fecha = scanner.nextLine();
                    LocalDate fechaNacimiento = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese la hora aproximada de llegada (HH:MM): ");
                    String hora = scanner.nextLine();
                    LocalTime horaLlegada = LocalTime.parse(hora, DateTimeFormatter.ISO_LOCAL_TIME);
                    Persona cliente = new Persona(nombre, apellido,email,nacionalidad,telefono,horaLlegada,fechaNacimiento);
                    System.out.print("Ingrese el nombre del alojamiento: ");
                    String nombreAlojamiento = scanner.nextLine();
                    Alojamiento alojamiento = sistema.obtenerAlojamientoPorNombre(nombreAlojamiento);
                    if (alojamiento == null) {
                        System.out.println("El alojamiento ingresado no existe.");
                        return;
                    }
                    System.out.print("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
                    String fechaInicioStr = scanner.nextLine();
                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
                    String fechaFinStr = scanner.nextLine();
                    LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.print("Ingrese el número de adultos: ");
                    int numAdultos = scanner.nextInt();
                    System.out.print("Ingrese el número de niños: ");
                    int numNiños = scanner.nextInt();
                    if(alojamiento instanceof Hotel){
                        System.out.print("Ingrese el nombre de la habitación: ");
                        scanner.nextLine();
                        String nombreHabitacion = scanner.nextLine();
                        Habitacion habitacion = sistema.obtenerHabitacionPorNombre(nombreHabitacion);
                        if (habitacion == null) {
                            System.out.println("La habitación seleccionada no existe.");
                            return;
                        }
                        sistema.realizarReserva(alojamiento, fechaInicio, fechaFin, numAdultos, numNiños, cliente, habitacion);
                        mostrarMenu(sistema);
                        break;
                    }else if(alojamiento instanceof DiaDeSol){
                        System.out.print("Ingrese el nombre de la actividad: ");
                        scanner.nextLine();
                        String nombreActividad = scanner.nextLine();
                        Actividad actividad = sistema.obtenerActividadPorNombre(nombreActividad);
                        if (actividad == null) {
                            System.out.println("La actividad seleccionada no existe.");
                            return;
                        }
                        sistema.realizarReserva(alojamiento, fechaInicio, fechaFin, numAdultos, numNiños, cliente, actividad);
                        mostrarMenu(sistema);
                        break;
                    }else if((alojamiento instanceof Apartamento) || (alojamiento instanceof Finca)) {
                        sistema.realizarReserva(alojamiento, fechaInicio, fechaFin, numAdultos, numNiños, cliente);
                        mostrarMenu(sistema);
                        break;
                    }
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Ocurrió un error al procesar los datos. Verifique la información ingresada.");
                    e.printStackTrace();
                }
            case 4:
                System.out.print("Ingrese su correo: ");
                String email = scanner.nextLine();
                for (Reserva reserva : sistema.getReservasActualesPorEmailCliente(email)){
                    System.out.println(reserva.toString());
                }
                mostrarMenu(sistema);
                break;
            case 5:
                System.out.print("Ingrese su correo: ");
                String correo = scanner.nextLine();
                System.out.print("Ingrese su fecha de nacimiento(formato: yyyy-MM-dd): ");
                String fecha = scanner.nextLine();
                LocalDate fechaNacimiento = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
                sistema.actualizarReserva(correo,fecha);
                mostrarMenu(sistema);
                break;
            default:
                System.out.println("Opción no válida. Intenta de nuevo.");
                mostrarMenu(sistema);
        }
        mostrarMenu(sistema);

    }
}