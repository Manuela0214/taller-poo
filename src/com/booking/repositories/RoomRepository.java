package com.booking.repositories;

import com.booking.models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private static RoomRepository instance;
    private List<Room> rooms;

    private RoomRepository() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Habitación doble","2 camas dobles, Vista al mar, Aire acondicionado, Cafetera",130.0,5,4));
        rooms.add(new Room("Habitación Sencilla","1 cama sencilla, Escritorio, WiFi gratuito, Baño privado",80.0,6,2));
        rooms.add(new Room("Habitación Suite","Cama King, Jacuzzi, Vista panorámica, Minibar, TV de pantalla plana",200.0,3,2));
        rooms.add(new Room("Habitación Triple","3 camas individuales, Balcón privado, Aire acondicionado, TV, Mini nevera",150.0,5,9));
        rooms.add(new Room("Habitación Familiar","2 camas dobles, Sofá cama, Cocina completa, Baño privado, WiFi gratuito",180.0,6,12));

    }

    public static RoomRepository getInstance() {
        if(instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
