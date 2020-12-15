package app.model;

import javax.persistence.*;

@Entity
@Table(name="Timeslots")
public class Timeslot extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DAY_OF_WEEK", nullable = false)
    private int dayOfWeek;

    @Column(name = "hhmm", nullable = false)
    private String hhmm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Return DAY_OF_WEEK */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHour() {
        return hhmm.split(":")[0];
    }

    public String getMinute() {
        return hhmm.split(":")[1];
    }

    public void setHhmm(String hhmm) {
        if (!hhmm.contains(":")) {
            throw new IllegalArgumentException("Invalid format for time. Expected hh:mm, received " + hhmm);
        }
        this.hhmm = hhmm;
    }
}
