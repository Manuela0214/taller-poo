package com.booking;

import java.util.ArrayList;
import java.util.List;

public class DayOfSun extends Accommodation {

    List<Activity> activities;

    public DayOfSun(String name, Double rating, Double nightPrice, String city) {
        super(name, rating, nightPrice, city);
        this.activities = new ArrayList<>();
    }

    @Override
    public String getType() {
        return "Dia de Sol";
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    @Override
    public boolean roomExists(Room room) {
        return false;
    }

    public Activity getActivityByName(String name) {
        return this.activities.stream()
                .filter(activity -> activity.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
