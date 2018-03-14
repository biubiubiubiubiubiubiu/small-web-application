package com.neteasedemo.controller;


import com.google.gson.Gson;
import com.neteasedemo.model.Item;
import com.neteasedemo.service.LoginService;
import com.neteasedemo.service.SellerService;
import com.neteasedemo.util.CustomException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/seller/item")
public class SellerController {

    @Autowired
    SellerService sellerService;
    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
    private static Gson gson = new Gson();

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity createItem(@RequestParam(value = "item") String itemInfo,
                                                   @RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
        boolean res = false;
        try {
            // res = sellerService.createItem(item);
        } catch (Exception ex) {
            logger.error("SellerController.createItem: error in creating item");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/noFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity createItemWithoutFile(@RequestParam(value = "item") String itemInfo, HttpServletRequest request) {
        boolean res = false;
        try {
            res = sellerService.createItem(itemInfo);
        } catch (Exception ex) {
            logger.error("SellerController.createItem: error in creating item");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseEntity getAllItems() {
        List<Item> items;
        try {
            items = sellerService.getAllItems();
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String itemJson = gson.toJson(items);
        return new ResponseEntity<>(itemJson, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces="application/json; charset=utf-8")
    public @ResponseBody ResponseEntity getItem(@PathVariable(value="id") String id) {
        Item item;
        try {
            item = sellerService.getItem(id);
        } catch (CustomException.ItemNotExistException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONObject jsonObject = new JSONObject(item);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @RequestMapping(value="/editWithOutFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity editWithoutFile(@RequestParam(value = "item") String itemInfo, HttpServletRequest request) {
        boolean res;
        try {
            res = sellerService.updateItem(itemInfo);
        } catch (CustomException.ItemNotExistException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteItem(@PathVariable(value="id") String id) {
        boolean res;
        try {
            res = sellerService.deleteItem(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
