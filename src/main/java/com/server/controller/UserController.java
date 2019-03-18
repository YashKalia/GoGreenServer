package com.server.controller;

import com.server.entity.User;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/getall")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/getone")
    public User getOneUser(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    /**
     * The authentication method.
     * Checks first if a user is in the database.
     * If it is, then it checks the password against the hashed version in the database.
     * If they match, return true.
     * Otherwise, return false.
     * @param user the user to authenticate. Must contain both username and password.
     * @return a boolean. True if the conditions are met, false otherwise.
     */
    @PostMapping(value = "/authenticate")
    public boolean verify(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User inDb = userRepository.findByUsername(user.getUsername());

        return userRepository.existsByUsername(user.getUsername())
                && encoder.matches(user.getPassword(), inDb.getPassword());
    }

    /**
     * Adds a new user to the DB if the username isn't already taken and if the password != null.
     *
     * @param user The user to be added (only username and password required)
     * @return the list of all users
     */
    @PostMapping(value = "/register")
    public List<User> addUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) == null
                && (!user.getPassword().equals(null))) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return userRepository.findAll();
        } else {
            return null;
        }
    }

    /**
     * Deletes an user.
     * !!! WIP - cannot delete once you add entries to the user...
     *
     * @param user The user to be deleted - only username is required.
     * @return the list of all users after deletion.
     */
    @DeleteMapping(value = "/delete")
    public List<User> deleteUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            userRepository.deleteById(userRepository.findByUsername(user.getUsername()).getId());
            return userRepository.findAll();
        } else {
            return null;
        }
    }

}
