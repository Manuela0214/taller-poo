package com.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SistemaReservas sistema = new SistemaReservas();

        //Clientes
        Persona cliente = new Persona(
                "Pepito",
                "Perez",
                "pepito@gmail.com",
                "Colombiana",
                "3102254622",
                LocalTime.of(13,30),
                LocalDate.of(2024, 12, 27)
        );

        //Habitaciones
        Habitacion habitacion1 = new Habitacion(
                "Habitación doble",
                "2 camas dobles, Vista al mar, Aire acondicionado, Cafetera",
                130,
                5
        );
        Habitacion habitacion2 = new Habitacion(
                "Habitación Sencilla",
                "1 cama sencilla, Escritorio, WiFi gratuito, Baño privado",
                80,
                6
        );
        Habitacion habitacion3 = new Habitacion(
                "Habitación Suite",
                "Cama King, Jacuzzi, Vista panorámica, Minibar, TV de pantalla plana",
                200,
                3
        );
        Habitacion habitacion4 = new Habitacion(
                "Habitación Triple",
                "3 camas individuales, Balcón privado, Aire acondicionado, TV, Mini nevera",
                150,
                5
        );
        Habitacion habitacion5 = new Habitacion(
                "Habitación Familiar",
                "2 camas dobles, Sofá cama, Cocina completa, Baño privado, WiFi gratuito",
                180,
                6
        );
        Habitacion habitacion6 = new Habitacion(
                "Habitación Deluxe",
                "Cama King, Jacuzzi privado, Terraza con vista al mar, Minibar, Smart TV",
                250,
                2
        );

        //Actividades
        Actividad actividades1 = new Actividad("Natación", true, 20);
        Actividad actividades2 = new Actividad("Paseo en bote", true, 25);
        Actividad actividades3 = new Actividad("Caminata", false, 15);
        Actividad actividades4 = new Actividad("Ciclo paseo", true, 10);
        Actividad actividades5 = new Actividad("Sendero ecológico", false, 30);

        //Alojamientos
        Hotel hotel = new Hotel("Hotel Paraiso", 4.2, 100, "Bogotá");
        Apartamento apartamento = new Apartamento("Guaduales apartamentos",4.0, 80, "Bogotá");
        Finca finca = new Finca("Finca la maria", 4.8, 120, "Medellin");
        DiaDeSol diaDeSol1 = new DiaDeSol("La rochela", 4.6, 50, "Manizales");
        DiaDeSol diaDeSol2 = new DiaDeSol("Santagueda", 4.3, 45, "Manizales");
        sistema.agregarAlojamiento(hotel);
        sistema.agregarAlojamiento(apartamento);
        sistema.agregarAlojamiento(finca);
        sistema.agregarAlojamiento(diaDeSol1);
        sistema.agregarAlojamiento(diaDeSol2);

        //Creación de habitaciones y actividades
        hotel.agregarHabitacion(habitacion1);
        hotel.agregarHabitacion(habitacion2);
        hotel.agregarHabitacion(habitacion3);
        hotel.agregarHabitacion(habitacion4);
        hotel.agregarHabitacion(habitacion5);
        hotel.agregarHabitacion(habitacion6);
        apartamento.agregarHabitacion(habitacion1);
        apartamento.agregarHabitacion(habitacion2);
        finca.agregarHabitacion(habitacion3);
        finca.agregarHabitacion(habitacion4);
        finca.agregarHabitacion(habitacion5);
        diaDeSol1.agregarActividad(actividades1);
        diaDeSol1.agregarActividad(actividades2);
        diaDeSol1.agregarActividad(actividades3);
        diaDeSol2.agregarActividad(actividades4);
        diaDeSol2.agregarActividad(actividades5);

        //busqueda de alojamientos por parametro
        LocalDate fechaInicio = LocalDate.of(2024, 12, 27);
        LocalDate fechaFin = LocalDate.of(2024, 12, 29);

//        sistema.buscarAlojamientos("Bogotá", "Hotel", fechaInicio, fechaFin, 2, 1, 2);
//        sistema.buscarAlojamientos("Manizales", "Dia de Sol", fechaInicio, fechaFin, 2, 1, 2);
//        sistema.buscarAlojamientos("Bogotá", "Apartamento", fechaInicio, fechaFin, 2, 1, 1);
//        sistema.buscarAlojamientos("Medellin", "Finca", fechaInicio, fechaFin, 2, 1, 1);

        //confirmacion caracteristicas de alojamientos
        sistema.confirmarHabitaciones("Finca la maria", fechaInicio, fechaFin, 2, 1, 1);

    }
}