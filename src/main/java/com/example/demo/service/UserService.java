package com.example.demo.service;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreator createUser(UserCreator user) {
        LOGGER.info(user.toString());
        if(userRepository.createUser(user) == 0){
            return null;
        }
        return user;
    }

    public UserCreator deleteUser(UserCreator user) {
        if(userRepository.deleteUser(user) == 0){
            return null;
        }
        return user;
    }

    public UserDTO login(UserDTO user) {

        if(userRepository.login(user) == null){
            return null;
        }

        return user;
    }
}
