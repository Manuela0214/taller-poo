package com.booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class SystemReservations {

    private List<Accommodation> accommodations;
    private List<Reservation> reservations;

    public SystemReservations() {
        this.accommodations = new ArrayList();
        this.reservations = new ArrayList();
    }

    public void addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public List<Accommodation> searchAccommodations(String city, String typeAccommodation, LocalDate start, LocalDate end,
                                                int numAdults, int numChildren, int numRooms) {
        List<Accommodation> accommodationsAvailable = new ArrayList<>();
        int daysStay =  calculateDaysStatement(start, end);
        LocalDate lastDayMonth = start.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstLast5days = calculateFirstLast5DaysMonth(lastDayMonth);

        for (Accommodation accommodation : accommodations) {
            if (isAccommodationInCity(city, typeAccommodation, accommodation)) {
                getAccommodationsAndFeatures(start, end, numAdults, numChildren, numRooms, accommodation, daysStay, firstLast5days, lastDayMonth, accommodationsAvailable);
            }
        }
        if(accommodationsAvailable.isEmpty()){
            System.out.println("No existe ese tipo de alojamiento en la ciudad especificada.");
        }
        return accommodationsAvailable;
    }

    private void getAccommodationsAndFeatures(LocalDate start, LocalDate end, int numAdults, int numChildren, int numRooms, Accommodation accommodation, int daysStay, LocalDate firstLast5days, LocalDate lastDayMonth, List<Accommodation> accommodationsAvailable) {
        if (verifyRoomsAvailable(accommodation, start, end, numRooms, numAdults, numChildren) && isHotel(accommodation)) {
            getPricesHotel(start, end, numRooms, accommodation, daysStay, firstLast5days, lastDayMonth, accommodationsAvailable);
        }
        if (isDayOfSun(accommodation)){
            getPricesDayOfSun(accommodation, accommodationsAvailable);
        }
        if(isApartamentOrFarm(accommodation)){
            getPricesApartmentFarm(start, end, numRooms, accommodation, daysStay, firstLast5days, lastDayMonth, accommodationsAvailable);
        }
    }

    private static void getPricesHotel(LocalDate start, LocalDate end, int numRooms, Accommodation accommodation, int daysStay, LocalDate firstLast5days, LocalDate lastDayMonth, List<Accommodation> accommodationsAvailable) {
        Room MostEconomicalRoom = calculateRoomMostEconomic(accommodation);

        if (accommodationHaveRooms(accommodation)) return;
        double priceBase = calculateBasePriceRoomMostEconomic(numRooms, MostEconomicalRoom, daysStay);
        double increase = 0;
        double discount = 0;

        if(isDateBetween5and10(start, end)){
            discount = getDiscountDateBetween5and10(priceBase);
            priceBase *= 0.92;
        }
        if(isDateBetween10and15(start, end)){
            increase = getIncrementDateBetween10and15(priceBase);
            priceBase *= 1.10;
        }
        if(isDateBetweenLast5DaysMonth(start, end, firstLast5days, lastDayMonth)){
            increase = getIncreaseDateBetweenLast5DaysMonth(priceBase);
            priceBase *= 1.15;
        }
        accommodationsAvailable.add(accommodation);
        showPriceAdjustmentsHotel(numRooms, accommodation, daysStay, MostEconomicalRoom, increase, discount, priceBase);
    }

    private static double getIncreaseDateBetweenLast5DaysMonth(double priceBase) {
        return priceBase * 0.15;
    }

    private static double getIncrementDateBetween10and15(double priceBase) {
        return priceBase * 0.10;
    }

    private static double getDiscountDateBetween5and10(double priceBase) {
        return priceBase * 0.08;
    }

    private static void showPriceAdjustmentsHotel(int numRooms, Accommodation accommodation, int daysStay, Room mostEconomicalRoom, double increase, double discount, double priceBase) {
        System.out.println(
                accommodation.getType() + ": " + accommodation.getName() +
                        ", Calificación: " + accommodation.getRating() +
                        ", Precio Base: " + mostEconomicalRoom.getNightPrice() * daysStay * numRooms +
                        ", Aumento: " + Math.round(increase) +
                        ", Descuento: " + Math.round(discount) +
                        ", Precio Total: " + Math.round(priceBase) + "\n"
        );
    }

    private static void getPricesApartmentFarm(LocalDate start, LocalDate end, int numRooms, Accommodation accommodation, int daysStay, LocalDate firstLast5days, LocalDate lastDayMonth, List<Accommodation> accommodationsAvailable) {
        double priceBase = accommodation.getNightPrice() * daysStay;
        double increase = 0;
        double discount = 0;

        if(isDateBetween5and10(start, end)){
            discount = getDiscountDateBetween5and10(priceBase);
            priceBase *= 0.92;
        }
        if(isDateBetween10and15(start, end)){
            increase = getIncrementDateBetween10and15(priceBase);
            priceBase *= 1.10;
        }
        if(isDateBetweenLast5DaysMonth(start,end,firstLast5days,lastDayMonth)){
            increase = getIncreaseDateBetweenLast5DaysMonth(priceBase);
            priceBase *= 1.15;
        }
        showAdjustmentsPricesApartmentsFincas(numRooms, accommodation, daysStay, increase, discount, priceBase);
        accommodationsAvailable.add(accommodation);
    }

    private static void showAdjustmentsPricesApartmentsFincas(int numRooms, Accommodation accommodation, int daysStay, double increase, double discount, double priceBase) {
        System.out.println(
                accommodation.getType() + ": " + accommodation.getName() +
                        ", Calificación: " + accommodation.getRating() +
                        ", Precio Base: " + accommodation.getNightPrice() * daysStay * numRooms +
                        ", Aumento: " + Math.round(increase) +
                        ", Descuento: " + Math.round(discount) +
                        ", Precio Total: " + Math.round(priceBase) + "\n"
        );
    }

    private static void getPricesDayOfSun(Accommodation accommodation, List<Accommodation> accommodationsAvailable) {
        DayOfSun dayOfSun = (DayOfSun) accommodation;
        System.out.println(accommodation.getType() + ": " + accommodation.getName());
        System.out.println("Actividades: ");
        for (Activity activity : dayOfSun.getActivities()) {
            String includesFood = activity.isIncludesLunch() ? "SI incliye" : "NO incluye";
            System.out.println(
                    activity.getName() +
                            "- Comida/Refrigerio: " + includesFood +
                            "- Precio: " + activity.getPrice()
            );
        }
        accommodationsAvailable.add(accommodation);
        System.out.println("\n");
    }

    private static double calculateBasePriceRoomMostEconomic(int numRooms, Room MostEconomicalRoom, int daysStay) {
        return MostEconomicalRoom.getNightPrice() * daysStay * numRooms;
    }

    private static boolean accommodationHaveRooms(Accommodation accommodation) {
        return accommodation.getRooms() == null || accommodation.getRooms().isEmpty();
    }

    private static LocalDate calculateFirstLast5DaysMonth(LocalDate lastDayMonth) {
        return lastDayMonth.minusDays(4);
    }

    private static int calculateDaysStatement(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.DAYS.between(start, end);
    }

    public boolean isApartamentOrFarm(Accommodation accommodation) {
        return (accommodation instanceof Apartment) || (accommodation instanceof Farm);
    }

    public boolean isDayOfSun(Accommodation accommodation) {
        return accommodation instanceof DayOfSun;
    }

    public boolean isHotel(Accommodation accommodation) {
        return accommodation instanceof Hotel;
    }

    private static Room calculateRoomMostEconomic(Accommodation accommodation) {
        return accommodation.getRooms()
                .stream()
                .min(Comparator.comparingDouble(Room::getNightPrice))
                .orElse(null);
    }

    private static boolean isDateBetweenLast5DaysMonth(LocalDate start, LocalDate end, LocalDate fistLast5days, LocalDate lastDayMonth) {
        return start.getDayOfMonth() >= fistLast5days.getDayOfMonth()
                && end.getDayOfMonth() <= lastDayMonth.getDayOfMonth();
    }

    private static boolean isDateBetween10and15(LocalDate start, LocalDate end) {
        return start.getDayOfMonth() >= 10 && end.getDayOfMonth() <= 15;
    }

    private static boolean isDateBetween5and10(LocalDate start, LocalDate end) {
        return start.getDayOfMonth() >= 5 && end.getDayOfMonth() <= 10;
    }

    private static boolean isAccommodationInCity(String city, String typeAccommodation, Accommodation accommodation) {
        return accommodation.getCity().equalsIgnoreCase(city) && typeAccommodation.equals(accommodation.getType());
    }

    public void confirmRooms(String accommodationName, LocalDate start, LocalDate end, int numAdults, int numChildren, int numRooms){
        List<Room> roomsAvailable = new ArrayList();
        Accommodation accommodation = getAccommodationByName(accommodationName);
        for(Room room : accommodation.getRooms()){
            if(isRoomAvailable(accommodation,start,end,numAdults,numChildren,room,numRooms)){
                roomsAvailable.add(room);
            }
        }
        System.out.println("Habitaciones disponibles en " + accommodationName + ": ");
        showRoomsAndFeatures(roomsAvailable);
    }

    public void makeReservationHotel(Accommodation accommodation, LocalDate start, LocalDate end, int numAdults, int numChildren, Customer customer, Room room) {
        Reservation newReservation = new Reservation(start,end, room, customer, accommodation);
        boolean reserved = false;
        if (!accommodation.roomExists(room)) {
            System.out.println("El alojamiento no contiene la habitación seleccionada.");
            return;
        }
        reserved = isReserved(newReservation, reserved);
        if (!reserved && verifyAvailabilityHotel(accommodation, room, start, end)){
            if(room.getQuantityAvailable() <= 0){
                System.out.println("No hay habitaciones disponibles de este tipo.");
            }
            room.setQuantityAvailable(room.getQuantityAvailable() - 1);
            room.setStatusAvailability(false);
            reservations.add(newReservation);
            System.out.println("Se ha realizado la reserva con éxito.");
        }else {
            System.out.println("No se pudo realizar la reserva. Verifique la disponibilidad.");
        }
    }

    public void makeReservationDayOfSun(Accommodation accommodation, LocalDate start, LocalDate end, Customer customer, Activity activity) {
        Reservation newReservation = new Reservation(start,end, activity, customer, accommodation);
        reservations.add(newReservation);
        System.out.println("Se ha realizado la reserva con éxito.");
    }

    public void makeReservationApartmentFarm(Accommodation accommodation, LocalDate start, LocalDate end, Customer customer) {
        Reservation newReservation = new Reservation(start,end, customer, accommodation);
        boolean reserved = false;
        reserved = isReserved(newReservation, reserved);
        if (!reserved && verifyAvailabilityApartmentFarm(accommodation, start, end)){
            reservations.add(newReservation);
            System.out.println("Se ha realizado la reserva con éxito.");
        }else {
            System.out.println("No se pudo realizar la reserva. Verifique la disponibilidad.");
        }
    }

    private boolean isReserved(Reservation newReservation, boolean reserved) {
        for (Reservation reservation : reservations){
            if (reservation.equals(newReservation)){
                System.out.println("La habitacion ya se encuentra reservada en el alojamiento especificado.");
                reserved = true;
            }
        }
        return reserved;
    }

    public void formUpdateReservation(String email, LocalDate dateBirth){
        Customer customer = authentication(email, dateBirth);
        if(customer != null){
            if(getCurrentReservesByCustomer(customer).isEmpty()){
                System.out.println("El cliente no posee reservas actualmente.");
            }
            getCurrentReservesByCustomerEmail(email).stream().forEach(System.out::println);
            System.out.println("¿Cómo desea actualizar su reserva? (Ingrese una opción)");
            System.out.println("1. Cambio de habitación");
            System.out.println("2. Cambio de alojamiento");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            updateReservation(option, customer);
        }else{
            System.out.println("Ha ocurrido un error durante la autenticación. Email y/o fecha de nacimiento inválida");
        }
    }

    private void updateReservation(int option, Customer customer) {
        switch (option){
            case 1:
                System.out.println("CAMBIO DE HABITACION");
                changeRoom(customer);
                break;
            case 2:
                System.out.println("CAMBIO DE ALOJAMIENTO");
                changeAccommodation(customer);
                break;
            default:
                System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    public void changeRoom(Customer customer){
        Scanner scanner = new Scanner(System.in);
        List<Reservation> reservationsCustomer = getCurrentReservesByCustomer(customer);
        showRoomsReserved(customer);
        Integer option = selectRoomChange(scanner, reservationsCustomer);
        if (option == null) return;
        Reservation reservationSelected = reservationsCustomer.get(option - 1);
        Room roomActual = reservationSelected.getRoom();
        Accommodation accommodation = reservationsCustomer.getFirst().getAccommodation();
        List<Room> roomsAvailable = showOtherRoomsAvailableAtAccommodation(accommodation, roomActual);
        if (roomsAvailable == null) return;
        showRoomsAndFeatures(roomsAvailable);
        selectNewRoomChange(customer, scanner, roomsAvailable, reservationSelected, roomActual);
    }

    private static Integer selectRoomChange(Scanner scanner, List<Reservation> reservationsCustomer) {
        System.out.println("Selecciona el número de la habitación que quieres cambiar:");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option < 1 || option > reservationsCustomer.size()) {
            System.out.println("Opción inválida.");
            return null;
        }
        return option;
    }

    private void selectNewRoomChange(Customer customer, Scanner scanner, List<Room> roomsAvailable, Reservation reservationSelected, Room roomActual) {
        System.out.println("Selecciona el número de la nueva habitación:");
        int newRoomOption = scanner.nextInt();
        scanner.nextLine();

        if (newRoomOption < 1 || newRoomOption > roomsAvailable.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        changeRoom(customer, roomsAvailable, newRoomOption, reservationSelected, roomActual);
    }

    private static void showRoomsAndFeatures(List<Room> roomsAvailable) {
        int count = 1;
        for (Room room : roomsAvailable) {
            System.out.println(count + ". Habitación: " + room.getName() +
                    " - Características: " + room.getCharacteristics() +
                    " - Precio por noche: $" + room.getNightPrice());
            count++;
        }
    }

    private static List<Room> showOtherRoomsAvailableAtAccommodation(Accommodation accommodation, Room roomActual) {
        System.out.println("Estas son otras habitaciones disponibles en " + accommodation.getName() + ":");
        List<Room> roomsAvailable = accommodation.getRooms().stream()
                .filter(h -> h.isStatusAvailability() && !h.equals(roomActual))
                .collect(Collectors.toList());

        if (roomsAvailable.isEmpty()) {
            System.out.println("No hay más habitaciones disponibles en este alojamiento.");
            return null;
        }
        return roomsAvailable;
    }

    private void changeRoom(Customer cliente, List<Room> roomsAvailable, int newOptionRoom, Reservation reservationSelected, Room roomActual) {
        Room newRoom = roomsAvailable.get(newOptionRoom - 1);

        roomActual.setQuantityAvailable(roomActual.getQuantityAvailable() + 1);
        roomActual.setStatusAvailability(true);

        newRoom.setQuantityAvailable(newRoom.getQuantityAvailable() - 1);
        if (newRoom.getQuantityAvailable() <= 0) {
            newRoom.setStatusAvailability(false);
        }
        reservationSelected.setRoom(newRoom);
        System.out.println("Se ha cambiado tu reserva a la habitación " + newRoom.getName() + ".");
    }

    private void showRoomsReserved(Customer customer) {
        List<Reservation> reservationsCustomer = getCurrentReservesByCustomer(customer);
        System.out.println("Estas son tus habitaciones reservadas:");
        int count = 1;
        for(Reservation reservation : reservationsCustomer){
            if(isHotel(reservation.getAccommodation())){
                System.out.println(count + ": " + reservation.getAccommodation().getName() +" - "+ reservation.getRoom());
                count++;
            }
        }
    }

    public void changeAccommodation(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la fecha de inicio (formato YYYY-MM-DD): ");
        String startStr = scanner.nextLine();
        LocalDate start = LocalDate.parse(startStr);
        System.out.print("Ingrese la fecha de fin (formato YYYY-MM-DD): ");
        String endStr = scanner.nextLine();
        LocalDate end = LocalDate.parse(endStr);
        System.out.print("Ingrese el nombre del alojamiento: ");
        String accommodationName = scanner.nextLine();

        Accommodation accommodation = getAccommodationByName(accommodationName);
        if(isHotel(accommodation)){
            System.out.print("Ingrese el nombre de la habitación: ");
            String roomName = scanner.nextLine();
            Reservation reservationActual = getReservaHotel(customer, roomName, accommodationName,start,end);
            if (reservationActual == null) {
                System.out.println("No tienes una reserva activa para cambiar con la habitación especificada.");
                return;
            }
            deleteReservationHotel(reservationActual);
        }else{
            Reservation reservationActual = getReservaDiferentHotel(customer, accommodationName, start, end);
            if (reservationActual == null) {
                System.out.println("No tienes una reserva activa para cambiar en el alojamiento y fechas especificadas.");
                return;
            }
            reservations.remove(reservationActual);
        }
        System.out.println("Se ha eliminado tu reserva actual. Procede a crear una nueva reserva.");
    }

    private Reservation getReservaDiferentHotel(Customer customer, String accommodationName, LocalDate start, LocalDate end) {
        return getCurrentReservesByCustomer(customer)
                .stream()
                .filter(reservation -> reservation.getAccommodation().getName().equalsIgnoreCase(accommodationName) &&
                        reservation.getStart().equals(start) &&
                        reservation.getEnd().equals(end))
                .findFirst()
                .orElse(null);
    }

    private void deleteReservationHotel(Reservation reservationActual) {
        reservations.remove(reservationActual);
        Room roomReserved = reservationActual.getRoom();
        roomReserved.setQuantityAvailable(roomReserved.getQuantityAvailable() + 1);
        roomReserved.setStatusAvailability(true);
    }

    private Reservation getReservaHotel(Customer customer, String roomName, String accommodationName, LocalDate start, LocalDate end) {
        return reservations.stream()
                .filter(r -> isHotel(r.getAccommodation()) && r.getCustomer().equals(customer) &&
                        r.getRoom().getName().equalsIgnoreCase(roomName) &&
                        r.getAccommodation().getName().equalsIgnoreCase(accommodationName)&&
                        r.getStart().equals(start) &&
                        r.getEnd().equals(end))
                .findFirst()
                .orElse(null);
    }

    //métodos adicionales

    private boolean verifyRoomsAvailable(Accommodation accommodation, LocalDate start, LocalDate end, int numRooms, int numAdults, int numChildren) {
        int totalRoomsAvailable = 0;
        for (Room room : accommodation.getRooms()) {
            if (isRoomAvailable(accommodation, start, end, numAdults, numChildren, room, numRooms)) {
                totalRoomsAvailable += room.getQuantityAvailable();
                if (totalRoomsAvailable >= numRooms) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRoomAvailable(Accommodation accommodation, LocalDate start, LocalDate end, int numAdults, int numChildren, Room room, int numRooms) {
        return room.getQuantityAvailable() >= numRooms &&
                room.getCapacityMaximum() >= (numAdults + numChildren) &&
                verifyAvailabilityHotel(accommodation, room, start, end);

    }

    private boolean verifyAvailabilityHotel(Accommodation accommodation, Room room, LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (isHotelAvailable(accommodation, room, reservation))
                return isDateAvailable(start, end, reservation);
        }
        return true;
    }

    private boolean isHotelAvailable(Accommodation accommodation, Room room, Reservation reservation) {
        if(isHotel(accommodation)) {
            if (reservation.getRoom().equals(room) && reservation.getAccommodation().equals(accommodation)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyAvailabilityApartmentFarm(Accommodation accommodation, LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            isApartmentFarmAvailable(accommodation, start, end, reservation);
        }
        return true;
    }

    private void isApartmentFarmAvailable(Accommodation accommodation, LocalDate start, LocalDate end, Reservation reservation) {
        if (isApartamentOrFarm(accommodation)) {
            if (reservation.getAccommodation().equals(accommodation)){
                isDateAvailable(start, end, reservation);
            }
        }
    }

    private boolean isDateAvailable(LocalDate start, LocalDate end, Reservation reservation) {
        if(isStartDateInMedium(start, reservation) || isEndDateInMedium(end, reservation)
            || isDateBeforeStartAndAfterEnd(start, end, reservation) || isDateAlreadyRegistered(start, end, reservation)){
            return false;
        }
        return true;
    }

    private static boolean isDateAlreadyRegistered(LocalDate start, LocalDate end, Reservation reservation) {
        return start.equals(reservation.getStart()) || end.equals(reservation.getEnd());
    }

    private static boolean isDateBeforeStartAndAfterEnd(LocalDate start, LocalDate end, Reservation reservation) {
        return start.isBefore(reservation.getStart()) && end.isAfter(reservation.getEnd());
    }

    private static boolean isEndDateInMedium(LocalDate end, Reservation reservation) {
        return end.isBefore(reservation.getEnd()) && end.isAfter(reservation.getStart());
    }

    private static boolean isStartDateInMedium(LocalDate start, Reservation reservation) {
        return start.isBefore(reservation.getEnd()) && start.isAfter(reservation.getStart());
    }

    public Accommodation getAccommodationByName(String accommodationName) {
        return this.getAccommodations().stream()
                .filter(a -> a.getName().equals(accommodationName))
                .findFirst()
                .orElse(null);
    }

    public Room getRoomByName(String roomName) {
        for (Accommodation accommodation : this.getAccommodations()) {
            Room room = accommodation.getRooms().stream()
                    .filter(a -> a.getName().equals(roomName))
                    .findFirst()
                    .orElse(null);
            if (room != null) {
                return room;
            }
        }
        return null;
    }

    public Activity getActivityByName(String activityName) {
        for (Accommodation accommodation : this.getAccommodations()) {
            Activity activity = accommodation.getActivities().stream()
                    .filter(a -> a.getName().equals(activityName))
                    .findFirst()
                    .orElse(null);
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }

    public List<Customer> getRegisteredCustomers() {
        return reservations.stream()
                .map(Reservation::getCustomer)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Reservation> getCurrentReservesByCustomer(Customer customer) {
        return reservations.stream()
                .filter(reserva -> reserva.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public List<Reservation> getCurrentReservesByCustomerEmail(String email) {
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    private Customer authentication(String email, LocalDate dateBirth){
        for (Customer customer : this.getRegisteredCustomers()){
            if((customer.getDateBirth().equals(dateBirth) &&
                    (customer.getEmail().equalsIgnoreCase(email)))){
                System.out.println("Bienvenid@ " + customer.getName());
                return customer;
            }
        }
        System.out.println("No se encontró un cliente con los datos proporcionados.");
        return null;
    }

}
