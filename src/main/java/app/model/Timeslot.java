package app.model;

import java.util.Map;

public enum Timeslot {
    WED_1630("Wed 1630 EST"),
    THU_1630("Thu 1630 EST");

    private String description;
    Timeslot(String description) {
        this.description = description;
    }

    public static Timeslot getTimeslotByString(int timeslotOrd) {
        return Timeslot.values()[timeslotOrd];
    }

    public String getDescription() {
        return description;
    }
}
