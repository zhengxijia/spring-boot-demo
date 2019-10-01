package com.vic.controller;

import com.vic.bean.User;
import com.vic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "Hello Spring Boot";
    }

    @PostMapping("/register")
    public String register(@Valid User user){
        String result = this.userService.register(user);
        return result;
    }
}
