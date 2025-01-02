package com.booking.repositories;

import com.booking.models.DayOfSun;
import com.booking.models.Room;

import java.util.ArrayList;
import java.util.List;

public class DayOfSunRepository {

    private static DayOfSunRepository instance;
    private List<DayOfSun> dayOfSuns;

    private DayOfSunRepository() {
        dayOfSuns = new ArrayList<>();
        dayOfSuns.add(new DayOfSun("La Rochela", 4.6, 50.0, "Pereira"));
        dayOfSuns.add(new DayOfSun("Santagueda", 4.3, 45.0, "Pereira"));
    }

    public static DayOfSunRepository getInstance() {
        if(instance == null) {
            instance = new DayOfSunRepository();
        }
        return instance;
    }

    public List<DayOfSun> getDayOfSuns() {
        return dayOfSuns;
    }

    public void addDayOfSun(DayOfSun dayOfSun) {
        dayOfSuns.add(dayOfSun);
    }
}
