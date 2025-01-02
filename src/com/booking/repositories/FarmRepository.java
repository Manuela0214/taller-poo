package com.booking.repositories;

import com.booking.models.Farm;
import com.booking.models.Room;

import java.util.ArrayList;
import java.util.List;

public class FarmRepository {

    private static FarmRepository instance;
    private List<Farm> farms;

    private FarmRepository() {
        farms = new ArrayList<>();
        farms.add(new Farm("Finca la maria", 4.8, 120.0, "Medellin"));
        farms.add(new Farm("Finca el descanso", 4.8, 120.0, "Manizales"));
        farms.add(new Farm("Finca el cafetal", 4.8, 120.0, "Manizales"));
    }

    public static FarmRepository getInstance() {
        if(instance == null) {
            instance = new FarmRepository();
        }
        return instance;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void addFarm(Farm farm) {
        farms.add(farm);
    }
}
