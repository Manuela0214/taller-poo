package com.booking.visitor;

import com.booking.models.Apartment;
import com.booking.models.DayOfSun;
import com.booking.models.Farm;
import com.booking.models.Hotel;

public interface ReportVisitor {

    void visit(Hotel hotel);
    void visit(Apartment apartment);
    void visit(Farm farm);
    void visit(DayOfSun dayOfSun);
}
