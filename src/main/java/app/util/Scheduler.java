package app.util;

import app.model.Pair;
import app.repository.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class Scheduler {

    @Autowired
    EmailSender emailSender;

    @Autowired
    PairGenerator pairGenerator;

    @Autowired
    PairRepository pairRepository;

    Executor executor = Executors.newFixedThreadPool(1);

    public void init() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                scheduledTask();
            }
        });
    }

    public Pair scheduledTask() {
        // Get new pair
        Pair newPair = pairGenerator.getNewPair();
        if (newPair != null) {
            // Persist new pair into DB
            pairRepository.save(newPair);
            // send email to both users
            emailSender.sendEmails(newPair);
        }
        return newPair;
    }
}
