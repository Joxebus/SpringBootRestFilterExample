package com.nearsoft.challenge.service;

import com.nearsoft.challenge.entity.User;
import com.nearsoft.challenge.entity.UserAuthorization;
import com.nearsoft.challenge.repository.UserAuthorizationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthorizationService {

    private UserAuthorizationRepository userAuthorizationRepository;

    public UserAuthorizationService(UserAuthorizationRepository userAuthorizationRepository) {
        this.userAuthorizationRepository = userAuthorizationRepository;
    }

    public UserAuthorization create(User user){
        if(user == null){
            throw new IllegalArgumentException("Invalid user");
        }

        UserAuthorization userAuthorization = findByUsername(user.getUsername());
        if(userAuthorization == null){
            userAuthorization = new UserAuthorization();
        }
        userAuthorization.setUsername(user.getUsername());
        userAuthorization.setToken(UUID.randomUUID().toString());
        return userAuthorizationRepository.save(userAuthorization);
    }

    public Iterable<UserAuthorization> findAll(){
        return userAuthorizationRepository.findAll();
    }

    public UserAuthorization findByUsername(String username){
        return userAuthorizationRepository.findFirstByUsername(username);
    }

    public UserAuthorization findByUsernameAndToken(String username, String token){
        return userAuthorizationRepository.findFirstByUsernameAndToken(username, token);
    }

    public void delete(int id){
        userAuthorizationRepository.delete(id);
    }
}
