package com.neteasedemo.controller;

import com.google.gson.Gson;
import com.neteasedemo.model.PurchaseRecord;
import com.neteasedemo.service.BuyerService;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/buyer")

public class BuyerController {

    @Autowired
    BuyerService buyerService;
    private static final Logger logger = LoggerFactory.getLogger(BuyerController.class);
    private static Gson gson = new Gson();

    @RequestMapping(value="/cart", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity addToCart(@RequestBody String buyItem) {
        boolean res = false;
        try {
            res = buyerService.addToCart(buyItem);
        } catch (Exception ex) {
            logger.error("BuyerController.addToCart: error occurred during buying items" + ex.getMessage());
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/cart", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseEntity getCartRecord() {
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList = buyerService.getCartRecord();
        } catch (Exception ex) {
            logger.error("BuyerController.getCartRecord: error occurred during fetching items" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String recordJson = gson.toJson(purchaseRecordList);
        return new ResponseEntity<>(recordJson, HttpStatus.OK);
    }

    @RequestMapping(value="/buyRecord", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseEntity buyItems(@RequestBody String itemList) {
        boolean res = false;
        try {
            res = buyerService.buyItems(itemList);
        } catch (Exception ex) {
            logger.error("BuyerController.buyItems: error occurred during buying items" + ex.getMessage());
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/buyRecord", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseEntity getBuyRecord() {
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList = buyerService.getBuyRecord();
        } catch (Exception ex) {
            logger.error("BuyerController.getBuyRecord: error occurred during fetching items" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String recordJson = gson.toJson(purchaseRecordList);
        return new ResponseEntity<>(recordJson, HttpStatus.OK);
    }

    @RequestMapping(value="/buyRecord/price/{id}", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseEntity getBuyPrice(@PathVariable(value="id") String id) {
        Float price = null;
        Map<String, Float> returnMap = new HashMap<>();
        try {
            price = buyerService.getPrice(id);
            returnMap.put("price", price);
        } catch (Exception ex) {
            logger.error("BuyerController.getBuyPrice: error occurred during fetching price" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (price == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String priceJson = gson.toJson(returnMap);
        return new ResponseEntity<>(priceJson, HttpStatus.OK);
    }


}
