package app.controller;

import app.model.Preference;
import app.model.Timeslot;
import app.model.User;
import app.repository.PreferenceRepository;
import app.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import app.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @PostMapping(path="/users/add")
    public @ResponseBody
    User addNewRecord (@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String timeslots) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setActive(true);
        userRepository.save(user);

        for (String timeslotOrd : timeslots.split(",")) {
            Preference preference = new Preference();
            preference.setTimeslot(Timeslot.getTimeslotByString(Integer.valueOf(timeslotOrd)));
            preference.setUser(user);
            preferenceRepository.save(preference);
        }
        return user;
    }

    @GetMapping(path="/users/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId,
                           @RequestBody User requestedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setActive(requestedUser.isActive());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("UserId" + userId + " not found"));
    }
}
