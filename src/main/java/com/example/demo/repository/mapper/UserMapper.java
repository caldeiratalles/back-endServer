package com.example.demo.repository.mapper;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserMapper {

    public UserMapper() {
    }

    public static UserDTO userMapper(final ResultSet resultSet) throws SQLException{

        UserDTO user = new UserDTO();
        user.setUsername(resultSet.getString("login"));
        user.setSenha(resultSet.getString("senha"));
        return user;
    }
}
