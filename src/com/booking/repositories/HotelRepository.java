package com.booking.repositories;

import com.booking.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelRepository {
    private static HotelRepository instance;
    private List<Hotel> hotels;

    private HotelRepository() {
        hotels = new ArrayList<>();
        hotels.add(new Hotel("Hotel la Estacion", 4.7, 114.0, "Pereira"));
        hotels.add(new Hotel("Hotel Paraiso", 4.2, 100.0, "Bogota"));
        hotels.add(new Hotel("Hotel la Colina", 4.5, 110.0, "Bogota"));
        hotels.add(new Hotel("Hotel Guamal", 4.7, 114.0, "Bogota"));
    }

    public static HotelRepository getInstance() {
        if(instance == null) {
            instance = new HotelRepository();
        }
        return instance;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }
}
