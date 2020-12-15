package app.repository;

import app.model.Preference;
import app.model.Timeslot;
import app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Page<Preference> findByUserId(Long userId, Pageable pageable);
    Optional<Preference> findByIdAndUserId(Long preferenceId, Long userId);

    @Query(value="SELECT T.* FROM PREFERENCES P INNER JOIN TIMESLOTS T ON (T.ID = P.TIMESLOT_ID) WHERE USER_ID = :user1Id " +
            "INTERSECT " +
            "SELECT T.* FROM PREFERENCES P INNER JOIN TIMESLOTS T ON (T.ID = P.TIMESLOT_ID) WHERE USER_ID = :user2Id", nativeQuery = true)
    Timeslot findCommonTimeslot(Long user1Id, Long user2Id);
}
