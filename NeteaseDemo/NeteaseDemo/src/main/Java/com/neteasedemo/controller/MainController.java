package com.neteasedemo.controller;

import com.neteasedemo.model.TestClass;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String test() {
        TestClass testClass = new TestClass("hello");
        JSONObject jsonObject = new JSONObject(testClass);
        return jsonObject.toString();
    }
}