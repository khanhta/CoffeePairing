package app.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_meetup_at", nullable = false, updatable = true)
    private Date lastMeetupAt = new Date(0);

    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getLastMeetupAt() {
        return lastMeetupAt;
    }

    public void setLastMeetupAt(Date lastMeetupAt) {
        this.lastMeetupAt = lastMeetupAt;
    }
}
