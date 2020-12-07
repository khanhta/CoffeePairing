package app.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Pairs")
public class Pair extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp meetupAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getMeetupAt() {
        return meetupAt;
    }

    public void setMeetupAt(Timestamp meetupAt) {
        this.meetupAt = meetupAt;
    }
}
