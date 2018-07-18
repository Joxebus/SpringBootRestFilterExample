package com.nearsoft.challenge.service;

import com.nearsoft.challenge.entity.User;
import com.nearsoft.challenge.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUserName(String username){
        return userRepository.findFirstByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password){
        User user = findByUserName(username);
        if(user == null || !passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Invalid user");
        }
        return user;
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        return userRepository.findOne(id);
    }

    public void delete(int id){
        userRepository.delete(id);
    }

    public long count() {
        return userRepository.count();
    }

    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
