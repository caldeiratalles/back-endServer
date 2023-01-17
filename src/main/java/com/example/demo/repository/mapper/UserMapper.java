package com.example.demo.repository.mapper;

import com.example.demo.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserMapper {

    public UserMapper() {
    }

    public static User userMapper(final ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setStatus(resultSet.getString("status"));
        user.setSenha(resultSet.getString("senha"));
        return user;
    }
}
