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
}
