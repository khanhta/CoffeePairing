package app.configuration;

import app.repository.PairRepository;
import app.repository.TimeslotRepository;
import app.repository.UserRepository;
import app.services.EmailSender;
import app.services.PairGenerator;
import app.services.MeetupScheduler;
import app.services.TimeslotCalculator;
import app.services.checkers.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
public class AppConfiguration {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private List<Checker> conditionCheckers;
    @Autowired
    private PairRepository pairRepository;

    @Bean
    public PairGenerator getPairGenerator() {
        return new PairGenerator(userRepository, timeslotRepository, getTimeslotCalculator(), conditionCheckers);
    }

    @Bean
    public EmailSender getEmailSender() {
        return new EmailSender();
    }

    @Bean
    public MeetupScheduler getScheduler() {
        return new MeetupScheduler(getEmailSender(), userRepository, getPairGenerator(), pairRepository);
    }

    @Bean
    public TimeslotCalculator getTimeslotCalculator() {
        System.out.println("Get timeslot calculator");
        return new TimeslotCalculator();
    }

    @Scheduled(fixedRate = 5000)
    public void getNewMeetupPair() {
        System.out.println("Get new pair");
        getScheduler().trigger();
    }
}
