package app.services;

import app.model.Pair;
import app.model.User;
import app.repository.PairRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

public class MeetupScheduler {

    EmailSender emailSender;

    UserRepository userRepository;

    PairGenerator pairGenerator;

    PairRepository pairRepository;

    public MeetupScheduler(EmailSender emailSender,
                           UserRepository userRepository,
                           PairGenerator pairGenerator,
                           PairRepository pairRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.pairGenerator = pairGenerator;
        this.pairRepository = pairRepository;
    }

    private void updateLastMeetupTimeForUser(User user, Date meetupAt) {
        user.setLastMeetupAt(meetupAt);
        userRepository.save(user);
    }

    public void trigger() {
        // Get new pair
        Pair newPair = pairGenerator.getNewPair();
        if (newPair != null) {
            // Persist new pair into DB
            pairRepository.save(newPair);
            // send email to both users
            emailSender.sendEmails(newPair);
            // update last meetup time for both user
            updateLastMeetupTimeForUser(newPair.getUser1(), newPair.getMeetupAt());
            updateLastMeetupTimeForUser(newPair.getUser2(), newPair.getMeetupAt());
        }
    }
}
