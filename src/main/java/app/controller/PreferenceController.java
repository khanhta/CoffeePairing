package app.controller;

import app.model.Preference;
import app.repository.PreferenceRepository;
import app.repository.UserRepository;
import app.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class PreferenceController {
    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/records/{userId}/preferences")
    public Page<Preference> getAllPreferencesByUserId(@PathVariable (value = "userId") Long userId,
                                                      Pageable pageable) {
        return preferenceRepository.findByUserId(userId, pageable);
    }

    @PutMapping("/records/{userId}/preferences/{preferenceId}")
    public Preference updatePreference(@PathVariable (value = "userId") Long userId,
                                       @PathVariable (value = "preferenceId") Long preferenceId,
                                       @RequestBody Preference preferenceRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserID "+ userId + " not found");
        }
        return preferenceRepository.findById(preferenceId)
                .map(preference -> {
                    preference.setTimeslot(preferenceRequest.getTimeslot());
                    return preferenceRepository.save(preference);
                })
                .orElseThrow(() -> new ResourceNotFoundException("PreferenceID " + preferenceId + " not found"));
    }
}
