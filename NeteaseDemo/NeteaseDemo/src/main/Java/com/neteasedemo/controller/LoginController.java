package com.neteasedemo.controller;

import com.neteasedemo.model.TestClass;
import com.neteasedemo.model.User;
import com.neteasedemo.service.LoginService;
import com.neteasedemo.util.CustomException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity loginRequest(@RequestBody String user) {
        try {
            User validateUser = loginService.login(user);
            JSONObject jsonObject = new JSONObject(validateUser);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (CustomException.EmptyUserException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
