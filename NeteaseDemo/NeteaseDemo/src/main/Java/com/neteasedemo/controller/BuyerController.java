package com.neteasedemo.controller;

import com.neteasedemo.service.BuyerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/buyer")

public class BuyerController {

    @Autowired
    BuyerService buyerService;
    private static final Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @RequestMapping(value="/toCart", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity buyItem(@RequestBody String buyItem) {
        boolean res = false;
        try {
            res = buyerService.addToCart(buyItem);
        } catch (Exception ex) {
            logger.error("BuyerController.buyItem: error occurred during buying items" + ex.getMessage());
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
