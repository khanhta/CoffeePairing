package app.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Pairs")
public class Pair extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "meetup_at", nullable = false, updatable = true)
    private Date meetupAt;

    @ManyToOne
    @JoinColumn(name="user1")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user1;

    @ManyToOne
    @JoinColumn(name="user2")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMeetupAt() {
        return meetupAt;
    }

    public void setMeetupAt(Date meetupAt) {
        this.meetupAt = meetupAt;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
