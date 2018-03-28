package com.neteasedemo.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neteasedemo.baseLib.UpdateHandler;
import com.neteasedemo.dao.SellerDao;
import com.neteasedemo.model.Item;
import com.neteasedemo.util.CustomException;
import com.neteasedemo.util.HandlerRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

import com.neteasedemo.util.UpdateHandlers.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public class SellerService {

    @Autowired
    SellerDao sellerDao;
    Gson gson = new Gson();

    //private static HashMap<String, UpdateHandler> handlers = HandlerRegister.registerHandlers();
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);


    public String saveFile(MultipartFile file, HttpServletRequest request) {
        try {
            String saveDirectory=request.getSession().getServletContext().getRealPath("/");
            String imageUrl = null;
            String ext = null;
            if (file != null && file.getOriginalFilename().length() > 0) {
                String fileName = file.getOriginalFilename();
                imageUrl = UUID.randomUUID() + "";
                ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                String newFileName =  imageUrl + fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                File newFile = new File(saveDirectory + newFileName);
                file.transferTo(newFile);
            }
            return imageUrl + "_" + ext;
        } catch (Exception ex) {
            logger.error("saveFile: error occurred during saving image");
            return null;
        }
    }

    public boolean createItem(String itemString, String filePath) throws CustomException.ItemFullException {
        Item item = gson.fromJson(itemString, Item.class);
        if (filePath != null) {
            item.setImageUrl(filePath);
        }
        try {
            return sellerDao.createItem(item);
        } catch (CustomException.ItemAlreadyExistedException ex) {
            logger.error("SellerService.createItem: item already existed.");
            return false;
        } catch (CustomException.ItemFullException ex) {
            logger.error("SellerService.createItem: item full.");
            throw ex;
        }
    }

    public Item getItem(String id) throws CustomException.ItemNotExistException{
        Item item;
        try {
            item = sellerDao.getItem(Integer.parseInt(id));
            if (item == null) {
                throw new CustomException.ItemNotExistException("Item not existed");
            }
        } catch (Exception ex) {
            logger.error("SellerService.getItem: error during fetching item.");
            return null;
        }
        return item;
    }

    public List<Item> getAllItems() {
        List<Item> items;
        try {
            items = sellerDao.getAllItems();
        } catch (Exception ex) {
            logger.error("SellerService.getAllItems: error during fetching all items");
            return null;
        }
        return items;
    }

    public boolean updateItem(String itemString, String filePath) throws CustomException.ItemNotExistException{
        // transfer json string to map
        Item item = gson.fromJson(itemString, Item.class);
        if (filePath != null) {
            item.setImageUrl(filePath);
        }
        if (!sellerDao.itemExist(item.getId())) {
            logger.error("SellerService.updateItem: item not existed!");
            throw new CustomException.ItemNotExistException("SellerService.updateItem: item not existed!");
        }
        try {
            sellerDao.updateItem(item);
        } catch(CustomException.UpdateItemException ex) {
            logger.error("SellerService.updateItem: error during updating");
            return false;
        }
        return true;
    }

    public boolean deleteItem(String id) {
        try {
            sellerDao.deleteItem(Integer.parseInt(id));
        } catch(Exception ex) {
            logger.error("SellerService.deleteItem: error during deleting item!");
            return false;
        }
        return true;
    }

}
