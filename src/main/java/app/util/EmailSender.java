package app.util;

import app.model.Pair;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class EmailSender {

    private static final String EMAIL_TEMPLATE = "Your next coffee buddy is %s at %s";

    public void sendEmails(Pair pair) {
        sendEmail(pair.getUser1().getEmail(), getContent(pair.getUser2().getEmail(), pair.getMeetupAt()));
        sendEmail(pair.getUser2().getEmail(), getContent(pair.getUser1().getEmail(), pair.getMeetupAt()));
    }

    private boolean sendEmail(String email, String content) {
        System.out.println("Sending email to " + email + " with content " + content);
        return true;
    }

    private String getContent(String user, Date meetupTime) {
        return EMAIL_TEMPLATE.formatted(user, new SimpleDateFormat().format(meetupTime));
    }
}
