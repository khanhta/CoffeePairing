package app.services;

import app.model.Pair;
import app.model.Timeslot;
import app.model.User;
import app.repository.TimeslotRepository;
import app.repository.UserRepository;
import app.services.checkers.Checker;

import java.util.List;

public class PairGenerator {
    private UserRepository userRepository;

    private TimeslotRepository timeslotRepository;

    private TimeslotCalculator timeslotCalculator;

    private List<Checker> conditionCheckers;

    public PairGenerator(UserRepository userRepository,
                         TimeslotRepository timeslotRepository,
                         TimeslotCalculator timeslotCalculator,
                         List<Checker> conditionCheckers) {
        this.userRepository = userRepository;
        this.timeslotRepository = timeslotRepository;
        this.timeslotCalculator = timeslotCalculator;
        this.conditionCheckers = conditionCheckers;
    }

    public Pair getNewPair() {
        User user1 = userRepository.getFarthestMeetupUser();
        User user2 = userRepository.getFarthestMeetupUserWithSamePreferenceNotMeetBeforeWithUserId(user1.getId());
        if (user2 != null) {
            Pair pair = new Pair();
            pair.setUser1(user1);
            pair.setUser2(user2);
            Timeslot timeslot = timeslotRepository.getCommonTimeslot(user1.getId(), user2.getId());
            pair.setMeetupAt(timeslotCalculator.getDateByTimeslot(timeslot));

            for (Checker checker : conditionCheckers) {
                if (!checker.check(pair))
                    return null;
            }
            return pair;
        }
        return null;
    }

}
