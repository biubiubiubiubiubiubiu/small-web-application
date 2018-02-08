package com.neteasedemo.service;

import com.google.gson.Gson;
import com.neteasedemo.dao.LoginDao;
import com.neteasedemo.model.User;
import com.neteasedemo.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class LoginService implements Serializable {

    @Autowired
    LoginDao loginDao;

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    Gson gson = new Gson();

    public User login(String userRequest) throws CustomException.EmptyUserException {
        User user = gson.fromJson(userRequest, User.class);
        try {
            User validateUser = loginDao.validUser(user);
            if (validateUser == null) {
                throw new CustomException.EmptyUserException("invalidate user");
            }
            return validateUser;
        } catch (Exception e) {
            logger.error("LoginService: error during validating user. \n {}", e.getMessage());
            return null;
        }
    }

}


