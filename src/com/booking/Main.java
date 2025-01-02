package com.booking;
import com.booking.repositories.*;
import com.booking.services.*;
import com.booking.utils.*;
import com.booking.models.*;
import com.booking.visitor.OccupancyReport;
import com.booking.visitor.VisitorProcessor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SystemReservations systemReservations = SystemReservations.getInstance();

        RoomRepository roomRepository = RoomRepository.getInstance();
        ActivityRepository activityRepository = ActivityRepository.getInstance();
        HotelRepository hotelRepository = HotelRepository.getInstance();
        ApartmentRepository apartmentRepository = ApartmentRepository.getInstance();
        FarmRepository farmRepository = FarmRepository.getInstance();
        DayOfSunRepository dayOfSunRepository = DayOfSunRepository.getInstance();

        addRoomsToHotels(hotelRepository, roomRepository);

        addActivitiesToDayOfSun(dayOfSunRepository, activityRepository);

        addAccommodationsToSystemReservation(systemReservations, hotelRepository, apartmentRepository, farmRepository, dayOfSunRepository);


        showMenu(systemReservations, hotelRepository, farmRepository, apartmentRepository, dayOfSunRepository, roomRepository);
    }

    private static void getNumReservationsByAccomodation(SystemReservations systemReservations, HotelRepository hotelRepository, FarmRepository farmRepository, ApartmentRepository apartmentRepository, DayOfSunRepository dayOfSunRepository, RoomRepository roomRepository) {
        makeReservations(systemReservations, hotelRepository, farmRepository, apartmentRepository, dayOfSunRepository, roomRepository);

        OccupancyReport visitor = new OccupancyReport(systemReservations);
        VisitorProcessor processor = new VisitorProcessor();
        processor.processVisitors(systemReservations.getAccommodations(), visitor);
        showMenu(systemReservations, hotelRepository, farmRepository, apartmentRepository, dayOfSunRepository, roomRepository);
    }

    private static void addActivitiesToDayOfSun(DayOfSunRepository dayOfSunRepository, ActivityRepository activityRepository) {
        List<DayOfSun> dayOfSuns = dayOfSunRepository.getDayOfSuns();
        List<Activity> activities = activityRepository.getActivities();

        dayOfSuns.get(0).addActivity(activities.get(0));
        dayOfSuns.get(0).addActivity(activities.get(1));
        dayOfSuns.get(0).addActivity(activities.get(2));
        dayOfSuns.get(1).addActivity(activities.get(3));
        dayOfSuns.get(1).addActivity(activities.get(4));
    }

    private static void addRoomsToHotels(HotelRepository hotelRepository, RoomRepository roomRepository) {
        List<Hotel> hotels = hotelRepository.getHotels();
        List<Room> rooms = roomRepository.getRooms();

        hotels.get(0).addRoom(rooms.get(0));
        hotels.get(0).addRoom(rooms.get(1));
        hotels.get(0).addRoom(rooms.get(2));
        hotels.get(1).addRoom(rooms.get(3));
        hotels.get(1).addRoom(rooms.get(4));
        hotels.get(1).addRoom(rooms.get(0));
        hotels.get(2).addRoom(rooms.get(1));
        hotels.get(2).addRoom(rooms.get(2));
        hotels.get(2).addRoom(rooms.get(3));
        hotels.get(2).addRoom(rooms.get(4));
        hotels.get(3).addRoom(rooms.get(0));
        hotels.get(3).addRoom(rooms.get(2));
        hotels.get(3).addRoom(rooms.get(4));
    }

    private static void addAccommodationsToSystemReservation(SystemReservations systemReservations, HotelRepository hotelRepository, ApartmentRepository apartmentRepository, FarmRepository farmRepository,DayOfSunRepository dayOfSunRepository) {
        hotelRepository.getHotels().forEach(systemReservations::addAccommodation);
        apartmentRepository.getApartments().forEach(systemReservations::addAccommodation);
        farmRepository.getFarms().forEach(systemReservations::addAccommodation);
        dayOfSunRepository.getDayOfSuns().forEach(systemReservations::addAccommodation);
    }

    private static void makeReservations(SystemReservations systemReservations, HotelRepository hotelRepository, FarmRepository farmRepository, ApartmentRepository apartmentRepository, DayOfSunRepository dayOfSunRepository, RoomRepository roomRepository) {
        Customer cliente = new Customer("Pepito","Perez","pepito@gmail.com","Colombiana","3102254622",
                LocalTime.of(13,30),
                LocalDate.of(1998, 1, 27)
        );
        LocalDate dateStart = LocalDate.of(2024, 12, 27);
        LocalDate dateEnd = LocalDate.of(2024, 12, 29);
        systemReservations.makeReservationHotel(hotelRepository.getHotels().get(0),dateStart, dateEnd, 2, 2, cliente, roomRepository.getRooms().get(1));
        systemReservations.makeReservationHotel(hotelRepository.getHotels().get(1), dateStart, dateEnd, 2, 2, cliente, roomRepository.getRooms().get(4));
        systemReservations.makeReservationHotel(hotelRepository.getHotels().get(1), dateStart, dateEnd, 2, 2, cliente, roomRepository.getRooms().get(0));
        systemReservations.makeReservationHotel(hotelRepository.getHotels().get(1), dateStart, dateEnd, 2, 2, cliente, roomRepository.getRooms().get(3));
        systemReservations.makeReservationApartmentFarm(farmRepository.getFarms().get(0), dateStart, dateEnd, cliente);
        systemReservations.makeReservationApartmentFarm(apartmentRepository.getApartments().get(0), dateStart, dateEnd, cliente);
        systemReservations.makeReservationDayOfSun(dayOfSunRepository.getDayOfSuns().get(0), dateStart, dateEnd, cliente, ActivityRepository.getInstance().getActivities().get(0));
    }



    public static void showMenu(SystemReservations systemReservations, HotelRepository hotelRepository, FarmRepository farmRepository, ApartmentRepository apartmentRepository, DayOfSunRepository dayOfSunRepository, RoomRepository roomRepository) {

        System.out.println("*** Menú principal ***");
        System.out.println("1. Buscar alojamiento");
        System.out.println("2. Ver habitaciones");
        System.out.println("3. Realizar reserva");
        System.out.println("4. Ver mis reservas");
        System.out.println("5. Actualizar Reserva");
        System.out.println("6. Ver número de reservas X alojamiento");  //aplicación patrón visitor
        System.out.println("7. Salir");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1 -> menuSearchAccommodation(systemReservations, scanner);
            case 2 -> menuShowRooms(systemReservations, scanner);
            case 3 -> menuMakeReservation(systemReservations, scanner);
            case 4 -> menuShowReservations(systemReservations, scanner);
            case 5 -> menuUpdateReserve(systemReservations, scanner);
            case 6 -> getNumReservationsByAccomodation(systemReservations, hotelRepository, farmRepository, apartmentRepository, dayOfSunRepository, roomRepository);
            case 7 -> System.out.println("Gracias por ultilizar el sistema");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
        if (option != 6) showMenu(systemReservations, hotelRepository, farmRepository, apartmentRepository, dayOfSunRepository, roomRepository);
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