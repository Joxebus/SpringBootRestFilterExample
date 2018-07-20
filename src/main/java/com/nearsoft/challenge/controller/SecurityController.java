package com.nearsoft.challenge.controller;

import com.nearsoft.challenge.entity.User;
import com.nearsoft.challenge.entity.UserAuthorization;
import com.nearsoft.challenge.service.UserAuthorizationService;
import com.nearsoft.challenge.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private UserService userService;
    private UserAuthorizationService userAuthorizationService;

    public SecurityController(UserService userService,
            UserAuthorizationService userAuthorizationService) {
        this.userService = userService;
        this.userAuthorizationService = userAuthorizationService;
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserAuthorization login(@RequestBody User user){
        user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(user == null){
            throw new IllegalArgumentException("Invalid user");
        }
        return userAuthorizationService.create(user);
    }

    @PostMapping(value = "/logout", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void logout(@RequestBody UserAuthorization userAuthorization){
        userAuthorization = userAuthorizationService.findByUsernameAndToken(userAuthorization.getUsername(), userAuthorization.getToken());
        if(userAuthorization != null){
            userAuthorizationService.delete(userAuthorization.getId());
        }
    }
}
