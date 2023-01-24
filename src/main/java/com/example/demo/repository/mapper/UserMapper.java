package com.example.demo.repository.mapper;

import com.example.demo.models.UserCreator;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserMapper {

    public UserMapper() {
    }

    public static UserCreator userMapper(final ResultSet resultSet) throws SQLException{
        UserCreator user = new UserCreator();
        return user;
    }
}
