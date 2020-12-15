package app.util;

import app.model.Pair;
import app.model.Timeslot;
import app.model.User;
import app.repository.PairRepository;
import app.repository.PreferenceRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PairGenerator {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PreferenceRepository preferenceRepository;

    @Autowired
    public TimeslotCalculator timeslotCalculator;

    public Pair getNewPair() {
        User user1 = userRepository.getFarthestMeetupUser();
        User user2 = userRepository.getFarthestMeetupUserWithSamePreferenceNotMeetBeforeWithUserId(user1.getId());
        Pair pair = new Pair();
        if (user2 != null) {
            pair.setUser1(user1);
            pair.setUser2(user2);
            Timeslot timeslot = preferenceRepository.findCommonTimeslot(user1.getId(), user2.getId());
            pair.setMeetupAt(timeslotCalculator.getDateByTimeslot(timeslot));
        }
        return null;
    }
}
