package app.util;

import app.model.Timeslot;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class TimeslotCalculator {

    public Date getDateByTimeslot(Timeslot timeslot) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, timeslot.getDayOfWeek());
        calendar.set(Calendar.HOUR, Integer.parseInt(timeslot.getHour()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeslot.getMinute()));
        return calendar.getTime();
    }
}
