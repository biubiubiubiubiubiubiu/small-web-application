package com.neteasedemo.dao;

import com.neteasedemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LoginDao implements Serializable{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginDao.class);

    public User validUser(User user) {
        String findUserSql = "SELECT * FROM User WHERE username=? AND password=?";
        try {
            User foundUser = jdbcTemplate.queryForObject(findUserSql, new Object[]{user.getUsername(), user.getPassword()}, (resultSet, rowNum) -> {
                User curr = new User();
                curr.setId(resultSet.getInt("id"));
                curr.setUsername(resultSet.getString("username"));
                curr.setType(resultSet.getInt("type"));
                return curr;
            });
            return foundUser;
        } catch (Exception e) {
            logger.error("LoginDao: error during finding user. \n {}", e.getMessage());
            return null;
        }
    }
}
