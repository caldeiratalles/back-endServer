package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(User user) {
        return userRepository.findUser(user);
    }

    public Optional<User> createUser(User user) {
        return userRepository.createUser(user);
    }

    public ResponseEntity deleteUser(User user) {
        Integer status = userRepository.deleteUser(user);
        if(status<0){
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public Optional<User> login(User user) {
        return userRepository.login(user);
    }
}
