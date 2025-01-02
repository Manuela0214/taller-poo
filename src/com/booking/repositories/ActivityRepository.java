package com.booking.repositories;

import com.booking.models.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepository {

    private static ActivityRepository instance;
    private List<Activity> activities;

    private ActivityRepository() {
        activities = new ArrayList<>();
        activities.add(new Activity("Natación", true, 20.0));
        activities.add(new Activity("Paseo en bote", true, 25.0));
        activities.add(new Activity("Caminata", false, 15.0));
        activities.add(new Activity("Ciclo paseo", true, 10.0));
        activities.add(new Activity("Sendero ecológico", false, 30.0));
    }

    public static ActivityRepository getInstance() {
        if(instance == null) {
            instance = new ActivityRepository();
        }
        return instance;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}
