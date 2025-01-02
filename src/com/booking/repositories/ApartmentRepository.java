package com.booking.repositories;

import com.booking.models.Apartment;
import com.booking.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRepository {
    private static ApartmentRepository instance;
    private List<Apartment> apartments;

    private ApartmentRepository() {
        apartments = new ArrayList<>();
        apartments.add(new Apartment("Torres Niza", 4.8, 120.0, "Bogota"));
        apartments.add(new Apartment("El Mirador", 4.1, 90.0, "Bogota"));
        apartments.add(new Apartment("Guaduales apartamentos",4.0, 95.0, "Medellin"));
        apartments.add(new Apartment("La Morada",4.3, 90.0, "Medellin"));
        apartments.add(new Apartment("Edificio La Montañita",4.7, 80.0, "Medellin"));
    }

    public static ApartmentRepository getInstance() {
        if(instance == null) {
            instance = new ApartmentRepository();
        }
        return instance;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }
}
