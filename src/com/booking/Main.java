package com.booking;

import java.time.LocalDate;
import java.time.LocalTime;

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
        apartamento1B.agregarHabitacion(habitacion1);
        apartamento1B.agregarHabitacion(habitacion2);
        apartamento2B.agregarHabitacion(habitacion1);
        apartamento2B.agregarHabitacion(habitacion2);
        apartamento2B.agregarHabitacion(habitacion1);
        //Medellin
        apartamento1M.agregarHabitacion(habitacion1);
        apartamento1M.agregarHabitacion(habitacion4);
        apartamento1M.agregarHabitacion(habitacion2);
        apartamento1M.agregarHabitacion(habitacion6);
        apartamento2M.agregarHabitacion(habitacion1);
        apartamento2M.agregarHabitacion(habitacion3);
        apartamento2M.agregarHabitacion(habitacion4);
        apartamento3M.agregarHabitacion(habitacion5);
        apartamento3M.agregarHabitacion(habitacion6);
        apartamento3M.agregarHabitacion(habitacion1);
        apartamento3M.agregarHabitacion(habitacion3);
        finca1M.agregarHabitacion(habitacion3);
        finca1M.agregarHabitacion(habitacion4);
        finca1M.agregarHabitacion(habitacion5);
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
        LocalDate fechaInicio = LocalDate.of(2024, 12, 27);
        LocalDate fechaFin = LocalDate.of(2024, 12, 29);
        //fechas solapadas
        LocalDate fechaInicioS = LocalDate.of(2024, 12, 26);
        LocalDate fechaFinS = LocalDate.of(2024, 12, 28);

//        sistema.buscarAlojamientos("Bogota", "Hotel", fechaInicio, fechaFin, 2, 1, 2);
//        sistema.buscarAlojamientos("Pereira", "Dia de Sol", fechaInicio, fechaFin, 2, 1, 2);
//        sistema.buscarAlojamientos("Bogota", "Apartamento", fechaInicio, fechaFin, 2, 1, 1);
//        sistema.buscarAlojamientos("Medellin", "Finca", fechaInicio, fechaFin, 2, 1, 1);
//        sistema.buscarAlojamientos("Medellin", "Dia de Sol", fechaInicio, fechaFin, 2, 1, 1);   //alojamiento que no existe

        //confirmacion caracteristicas de alojamientos
//        sistema.confirmarHabitaciones("Finca la maria", fechaInicio, fechaFin, 2, 0, 1);

        //reservas de alojamientos
        sistema.realizarReserva(hotel1B, fechaInicio, fechaFin, 2,2,cliente1,habitacion1);
        sistema.realizarReserva(finca1M, fechaInicio, fechaFin, 2,1,cliente2,habitacion5);
        sistema.realizarReserva(apartamento1M, fechaInicio, fechaFin, 3,1,cliente3,habitacion2);
        sistema.realizarReserva(apartamento1B, fechaInicio, fechaFin, 3,1,cliente1,habitacion1);
        sistema.realizarReserva(apartamento1B, fechaInicioS, fechaFinS, 2,1,cliente2,habitacion1);  //fecha se solapa
        sistema.realizarReserva(diaDeSol1P, fechaInicioS, fechaFinS, 2,1,cliente2,actividades1); //dia de sol

        sistema.getReservasActuales();
//
        //actualizar reserva
        sistema.actualizarReserva("anita@gmail.com","1976-06-15");
//
        sistema.getReservasActuales();
    }
}