package com.booking.visitor;

import com.booking.models.Accommodation;

import java.util.List;

public class VisitorProcessor {

    public void processVisitors(List<Accommodation> accommodations, OccupancyReport visitor){
        for (Accommodation accommodation : accommodations) {
            accommodation.acceptVisitor(visitor);
        }
    }
}
