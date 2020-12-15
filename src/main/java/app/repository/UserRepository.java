package app.repository;

import app.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value="SELECT TOP 1 * FROM Users ORDER BY LAST_MEETUP_AT ASC", nativeQuery = true)
    public User getFarthestMeetupUser();

    @Query(value="SELECT TOP 1 u.* FROM Users u INNER JOIN Preferences p ON (u.ID=p.USER_ID) " +
            "WHERE p.TIMESLOT_ID IN (SELECT TIMESLOT_ID FROM Preferences WHERE USER_ID = :userID)" +
            " AND p.USER_ID NOT IN (SELECT User1 FROM Pairs WHERE User2 = :userID) " +
            " AND p.USER_ID NOT IN (SELECT User2 FROM Pairs WHERE User1 = :userID)" +
            " AND u.ID != :userID" +
            " ORDER BY LAST_MEETUP_AT ASC", nativeQuery = true)
    public User getFarthestMeetupUserWithSamePreferenceNotMeetBeforeWithUserId(Long userID);
}
