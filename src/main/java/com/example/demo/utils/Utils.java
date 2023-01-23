package com.example.demo.utils;

import com.example.demo.models.User;

import java.util.Base64;

public final class Utils {

    public User descriptografa(User user){

        User userCriptografia = new User();
        userCriptografia.setLogin(user.getLogin());
        String senhaBase64 = new String(Base64.getDecoder().decode(user.getSenha()));
        userCriptografia.setSenha(senhaBase64);
        userCriptografia.setStatus(user.getStatus());
        return userCriptografia;
    }
    public User criptografa(User user){

        User userCriptografia = new User();
        userCriptografia.setLogin(user.getLogin());
        String senhaBase64 = Base64.getEncoder().encodeToString(user.getSenha().getBytes());
        userCriptografia.setSenha(senhaBase64);
        userCriptografia.setStatus(user.getStatus());
        return userCriptografia;
    }
}
