package com.example.demo.service;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserChangeSenha;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserCreator> createUser(UserCreator user) {
        LOGGER.info(user.toString());
        if(userRepository.createUser(user) == 0){
            throw new Exception("Usuario já existe");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<UserCreator> deleteUser(UserCreator user) {
        LOGGER.info(user.toString());
        if(userRepository.deleteUser(user) == 0){
            throw new Exception("Usuario nao cadastrado ou nao existe");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> login(UserDTO user) {

        if(userRepository.findUser(user) == 0 && userRepository.login(user) != null){
            throw new Exception("Senha incorreta");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<UserChangeSenha> changeSenha(UserChangeSenha user) {
        if(userRepository.validar(user) != 1){
            userRepository.trocarSenha(user);
        }
        throw new Exception("Ocorreu um erro");
    }
}
