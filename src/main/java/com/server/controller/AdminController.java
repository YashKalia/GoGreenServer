package com.server.controller;

import com.server.entity.User;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "secure/")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/add")
    public String addUserByAdmin(@RequestBody User user) {
        String pwd = user.getPassword();
        pwd = passwordEncoder.encode(pwd);
        user.setPassword(pwd);
        userRepository.save(user);
        return "user added succesfully!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

    @PostMapping(value = "/test")
    public long whatsMyId(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername()).getPassword().equals(passwordEncoder.encode(user.getPassword())))
            return userRepository.findByUsername(user.getUsername()).getUser_id();

        return -1;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public String securedHello() {
        return "Secured Hello";
    }
}
