package com.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SystemReservations sistema = new SystemReservations();

        //----------------------------Clientes
        Customer cliente1 = new Customer("Pepito","Perez","pepito@gmail.com","Colombiana","3102254622",
                LocalTime.of(13,30),
                LocalDate.of(1998, 1, 27)
        );

        //----------------------------Habitaciones
        Room room1 = new Room("Habitación doble","2 camas dobles, Vista al mar, Aire acondicionado, Cafetera",130.0,5,4);
        Room room2 = new Room("Habitación Sencilla","1 cama sencilla, Escritorio, WiFi gratuito, Baño privado",80.0,6,2);
        Room room3 = new Room("Habitación Suite","Cama King, Jacuzzi, Vista panorámica, Minibar, TV de pantalla plana",200.0,3,2);
        Room room4 = new Room("Habitación Triple","3 camas individuales, Balcón privado, Aire acondicionado, TV, Mini nevera",150.0,5,9);
        Room room5 = new Room("Habitación Familiar","2 camas dobles, Sofá cama, Cocina completa, Baño privado, WiFi gratuito",180.0,6,12);

        //----------------------------Actividades
        Activity actividades1 = new Activity("Natación", true, 20.0);
        Activity actividades2 = new Activity("Paseo en bote", true, 25.0);
        Activity actividades3 = new Activity("Caminata", false, 15.0);
        Activity actividades4 = new Activity("Ciclo paseo", true, 10.0);
        Activity actividades5 = new Activity("Sendero ecológico", false, 30.0);

        //----------------------------Alojamientos
        //Bogotá
        Hotel hotel1B = new Hotel("Hotel Paraiso", 4.2, 100.0, "Bogota");
        Hotel hotel2B = new Hotel("Hotel la Colina", 4.5, 110.0, "Bogota");
        Hotel hotel3B = new Hotel("Hotel Guamal", 4.7, 114.0, "Bogota");
        Apartment apartment1B = new Apartment("Torres Niza", 4.8, 120.0, "Bogota");
        Apartment apartment2B = new Apartment("El Mirador", 4.1, 90.0, "Bogota");
        //Medellin
        Apartment apartment1M = new Apartment("Guaduales apartamentos",4.0, 95.0, "Medellin");
        Apartment apartment2M = new Apartment("La Morada",4.3, 90.0, "Medellin");
        Apartment apartment3M = new Apartment("Edificio La Montañita",4.7, 80.0, "Medellin");
        Farm farm1M = new Farm("Finca la maria", 4.8, 120.0, "Medellin");
        //Pereira
        DayOfSun dayOfSun1P = new DayOfSun("La Rochela", 4.6, 50.0, "Pereira");
        DayOfSun dayOfSun2P = new DayOfSun("Santagueda", 4.3, 45.0, "Pereira");
        Hotel hotel1P = new Hotel("Hotel la Estacion", 4.7, 114.0, "Pereira");

        //----------------------------Creación de habitaciones y actividades
        //Bogota
        hotel1B.addRoom(room1);
        hotel1B.addRoom(room2);
        hotel1B.addRoom(room3);
        hotel2B.addRoom(room4);
        hotel2B.addRoom(room5);
        hotel2B.addRoom(room1);
        hotel3B.addRoom(room2);
        hotel3B.addRoom(room3);
        hotel3B.addRoom(room4);
        hotel3B.addRoom(room5);
        //Pereira
        dayOfSun1P.addActivity(actividades1);
        dayOfSun1P.addActivity(actividades2);
        dayOfSun1P.addActivity(actividades3);
        dayOfSun2P.addActivity(actividades4);
        dayOfSun2P.addActivity(actividades5);
        hotel1P.addRoom(room1);
        hotel1P.addRoom(room3);
        hotel1P.addRoom(room5);

        //----------------------------creación alojamientos en el sistema de reservas
        //Bogota
        sistema.addAccommodation(hotel1B);
        sistema.addAccommodation(hotel2B);
        sistema.addAccommodation(hotel3B);
        sistema.addAccommodation(apartment1B);
        sistema.addAccommodation(apartment2B);
        //Medellin
        sistema.addAccommodation(apartment1M);
        sistema.addAccommodation(apartment2M);
        sistema.addAccommodation(apartment3M);
        sistema.addAccommodation(farm1M);
        //Pereira
        sistema.addAccommodation(dayOfSun1P);
        sistema.addAccommodation(dayOfSun2P);
        sistema.addAccommodation(hotel1P);

        showMenu(sistema);
    }

    public static void showMenu(SystemReservations systemReservations){

        System.out.println("*** Menú principal ***");
        System.out.println("1. Buscar alojamiento");
        System.out.println("2. Ver habitaciones");
        System.out.println("3. Realizar reserva");
        System.out.println("4. Ver mis reservas");
        System.out.println("5. Actualizar Reserva");
        System.out.println("6. Salir");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1 -> menuSearchAccommodation(systemReservations, scanner);
            case 2 -> menuShowRooms(systemReservations, scanner);
            case 3 -> menuMakeReservation(systemReservations, scanner);
            case 4 -> menuShowReservations(systemReservations, scanner);
            case 5 -> menuUpdateReserve(systemReservations, scanner);
            case 6 -> System.out.println("Gracias por ultilizar el sistema");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
        if (option != 6) showMenu(systemReservations);
    }

    private static void menuMakeReservation(SystemReservations systemReservations, Scanner scanner) {
        try {
            Customer customer = captureCustomerData(scanner);
            Accommodation accommodation = captureAccommodationData(systemReservations, scanner);
            if (accommodation == null) return;
            LocalDate startDate = captureDate(scanner, Messages.DATE_START_PROMPT);
            LocalDate endDate = captureDate(scanner, Messages.DATE_END_PROMPT);
            int numAdults = captureInteger(Messages.NUM_ADULTS_PROMPT, scanner);
            int numChildren = captureInteger(Messages.NUM_CHILDREN_PROMPT, scanner);
            processReserve(systemReservations, accommodation, scanner, startDate, endDate, numAdults, numChildren, customer);
        } catch (Exception e) {
            System.out.println(Messages.DATA_ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static void processReserve(SystemReservations systemReservations, Accommodation accommodation, Scanner scanner, LocalDate start, LocalDate end, int numAdults, int numChildren, Customer customer) {
        if(systemReservations.isHotel(accommodation)){
            processReserveHotel(systemReservations, accommodation, scanner, start, end, numAdults, numChildren, customer);
        }else if(systemReservations.isDayOfSun(accommodation)){
            processReserveDayOfSun(systemReservations, accommodation, scanner, start, end, customer);
        }else if(systemReservations.isApartamentOrFarm(accommodation)) {
            systemReservations.makeReservationApartmentFarm(accommodation, start, end, customer);
        }
    }

    private static void processReserveDayOfSun(SystemReservations systemReservations, Accommodation accommodation, Scanner scanner, LocalDate start, LocalDate end, Customer customer) {
        System.out.print(Messages.NAME_ACTIVITY_PROMPT);
        scanner.nextLine();
        String activityName = scanner.nextLine();
        Activity activity = systemReservations.getActivityByName(activityName);
        if (activity == null) {
            System.out.println("La actividad seleccionada no existe.");
        }
        systemReservations.makeReservationDayOfSun(accommodation, start, end, customer, activity);
    }

    private static void processReserveHotel(SystemReservations systemReservations, Accommodation accommodation, Scanner scanner, LocalDate start, LocalDate end, int numAdults, int numChildren, Customer customer) {
        System.out.print(Messages.NAME_ROOMS_PROMPT);
        scanner.nextLine();
        String roomName = scanner.nextLine();
        Room room = systemReservations.getRoomByName(roomName);
        if (room == null) {
            System.out.println("La habitación seleccionada no existe.");
        }
        systemReservations.makeReservationHotel(accommodation, start, end, numAdults, numChildren, customer, room);
    }

    private static int captureInteger(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextInt();
    }

    private static LocalDate captureDate(Scanner scanner, String message) {
        System.out.print(message);
        String dateStr = scanner.nextLine();
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private static Accommodation captureAccommodationData(SystemReservations systemReservations, Scanner scanner) {
        System.out.print(Messages.NAME_ACCOMMODATION_PROMPT);
        String accommodationName = scanner.nextLine();
        Accommodation accommodation = systemReservations.getAccommodationByName(accommodationName);
        if (accommodation == null) {
            System.out.println("El alojamiento ingresado no existe.");
            return null;
        }
        return accommodation;
    }

    private static Customer captureCustomerData(Scanner scanner) {
        System.out.print(Messages.NAME_PROMPT);
        String name = scanner.nextLine();
        System.out.print(Messages.LAST_NAME_PROMPT);
        String lastName = scanner.nextLine();
        System.out.print(Messages.EMAIL_PROMPT);
        String email = scanner.nextLine();
        System.out.print(Messages.NATIONALITY_PROMPT);
        String nationality = scanner.nextLine();
        System.out.print(Messages.PHONE_PROMPT);
        String phone = scanner.nextLine();
        LocalDate dateBirth = captureDate(scanner, Messages.BIRTH_DATE_PROMPT);
        System.out.print(Messages.ARRIVAL_TIME_PROMPT);
        String time = scanner.nextLine();
        LocalTime timeArrival = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        return new Customer(name, lastName,email,nationality,phone,timeArrival,dateBirth);
    }

    private static void menuUpdateReserve(SystemReservations systemReservations, Scanner scanner) {
        System.out.print(Messages.EMAIL_PROMPT);
        String email = scanner.nextLine();
        LocalDate dateBirth = captureDate(scanner,Messages.BIRTH_DATE_PROMPT);
        systemReservations.formUpdateReservation(email,dateBirth);
    }

    private static void menuShowReservations(SystemReservations sys, Scanner scanner) {
        System.out.print(Messages.EMAIL_PROMPT);
        String email = scanner.nextLine();
        sys.getCurrentReservesByCustomerEmail(email).stream().forEach(System.out::println);
        if(sys.getCurrentReservesByCustomerEmail(email).isEmpty()) System.out.println("Usted no cuenta con reservas actualmente.");;
    }

    private static void menuShowRooms(SystemReservations systemReservations, Scanner scanner) {
        try {
            System.out.print(Messages.NAME_ACCOMMODATION_PROMPT);
            String accomodation = scanner.nextLine();
            LocalDate dateStart = captureDate(scanner,Messages.DATE_START_PROMPT);
            LocalDate dateEnd = captureDate(scanner,Messages.DATE_END_PROMPT);
            int numRooms = captureInteger(Messages.NUM_ROOMS_PROMPT, scanner);
            int numAdults = captureInteger(Messages.NUM_ADULTS_PROMPT, scanner);
            int numChildren = captureInteger(Messages.NUM_CHILDREN_PROMPT, scanner);
            systemReservations.confirmRooms(accomodation, dateStart, dateEnd, numRooms, numAdults, numChildren);
            scanner.nextLine();
        }catch (Exception e){
            System.out.println(Messages.DATA_ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private static void menuSearchAccommodation(SystemReservations systemReservations, Scanner scanner) {
        try {
            System.out.print("Ingrese la ubicación del alojamiento (ejemplo: Bogotá): ");
            String location = scanner.nextLine();
            System.out.print("Ingrese el tipo de alojamiento (ejemplo: Hotel): ");
            String accommodationType = scanner.nextLine();
            LocalDate dateStart = captureDate(scanner,Messages.DATE_START_PROMPT);
            LocalDate dateEnd = captureDate(scanner,Messages.DATE_END_PROMPT);
            int numAdults = captureInteger(Messages.NUM_ADULTS_PROMPT, scanner);
            int numChildren = captureInteger(Messages.NUM_CHILDREN_PROMPT, scanner);
            int numRooms = captureInteger(Messages.NUM_ROOMS_PROMPT, scanner);
            systemReservations.searchAccommodations(location, accommodationType, dateStart, dateEnd, numAdults, numChildren, numRooms);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(Messages.DATA_ERROR_MESSAGE);
        }
    }
}