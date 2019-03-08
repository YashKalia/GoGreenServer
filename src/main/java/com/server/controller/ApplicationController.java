package com.server.controller;

import com.server.entity.User;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ApplicationController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/process")
    public String process() {
        return "processing...";
    }

    @GetMapping(value = "/get")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
