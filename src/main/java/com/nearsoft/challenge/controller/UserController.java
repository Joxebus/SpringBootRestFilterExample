package com.nearsoft.challenge.controller;

import com.nearsoft.challenge.entity.User;
import com.nearsoft.challenge.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Iterable<User> list() {
        return userService.findAll();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user){
        user = userService.findByUserName(user.getUsername());
        if(user == null){
            throw new IllegalArgumentException("Invalid user");
        }
        return userService.update(user);
    }

}
