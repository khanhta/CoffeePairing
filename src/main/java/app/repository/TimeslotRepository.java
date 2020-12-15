package app.repository;

import app.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {

    @Query(value=
            "SELECT TOP 1 T.* FROM PREFERENCES P INNER JOIN TIMESLOTS T ON (T.ID = P.TIMESLOT_ID) WHERE USER_ID = :user1Id "
            + "INTERSECT "
            + "SELECT T.* FROM PREFERENCES P INNER JOIN TIMESLOTS T ON (T.ID = P.TIMESLOT_ID) WHERE USER_ID = :user2Id"
            , nativeQuery = true)
    Timeslot getCommonTimeslot(Long user1Id, Long user2Id);
}
