package app.services.checkers;

import app.model.Pair;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IsWithinAMonth implements Checker {
    @Override
    public boolean check(Pair pair) {
        Date planningMeetupTime = pair.getMeetupAt();
        Date lastUser1Meetup = pair.getUser1().getLastMeetupAt();
        if (getDateDiff(lastUser1Meetup, planningMeetupTime) < 30) {
            return false;
        }

        Date lastUser2Meetup = pair.getUser2().getLastMeetupAt();
        if (getDateDiff(lastUser2Meetup, planningMeetupTime) < 30) {
            return false;
        }
        return true;
    }

    private long getDateDiff(Date prev, Date curr) {
        long diffInMillies = Math.abs(curr.getTime() - prev.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
