package com.neteasedemo.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neteasedemo.dao.BuyerDao;
import com.neteasedemo.model.PurchaseRecord;
import com.neteasedemo.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    @Autowired
    BuyerDao buyerDao;
    private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    Gson gson = new Gson();

    public boolean addToCart(String buyItem) throws CustomException.BuyItemException {
        PurchaseRecord purchaseRecord = gson.fromJson(buyItem, PurchaseRecord.class);
        try {
            return buyerDao.addToCart(purchaseRecord);
        } catch (Exception ex) {
            logger.error("BuyerService.addToCart: error occurred during buying item.");
            return false;
        }
    }

    public List<PurchaseRecord> getCartRecord() {
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList = buyerDao.getCartRecord();
        } catch (Exception ex) {
            logger.error("BuyerService.getCartRecord: error during fetching all purchase records");
            return null;
        }
        return purchaseRecordList;
    }

    public boolean buyItems(String itemList) {
        List<PurchaseRecord> purchaseRecords = gson.fromJson(itemList, new TypeToken<List<PurchaseRecord>>(){}.getType());
        try {
            buyerDao.clearCart();
            return buyerDao.buyItems(purchaseRecords);
        } catch(CustomException.ClearCartException ex) {
            logger.error("BuyerService.buyItem: error occurred during clearing cart");
            return false;
        } catch (Exception ex) {
            logger.error("BuyerService.buyItem: error occurred during buying item.");
            return false;
        }
    }

    public List<PurchaseRecord> getBuyRecord() {
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList = buyerDao.getBuyRecord();
        } catch (Exception ex) {
            logger.error("BuyerService.getBuyRecord: error during fetching all purchase records");
            return null;
        }
        return purchaseRecordList;
    }

    public Float getPrice(String id) {
        Float price;
        try {
            price = buyerDao.getPrice(Integer.parseInt(id));
        } catch (CustomException.ItemNotExistException ex) {
            logger.error("BuyService.getPrice: item not exist in buy record.");
            return null;
        } catch (Exception ex) {
            logger.error("BuyService.getPrice: error occurred during fetching item price.");
            return null;
        }
        return price;
    }
}
