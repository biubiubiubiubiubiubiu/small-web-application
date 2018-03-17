package com.neteasedemo.service;


import com.google.gson.Gson;
import com.neteasedemo.dao.BuyerDao;
import com.neteasedemo.model.PurchaseRecord;
import com.neteasedemo.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    BuyerDao buyerDao;
    private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    Gson gson = new Gson();

    public boolean addToCart(String buyItem) throws CustomException.BuyItemException {
        PurchaseRecord purchaseRecord = gson.fromJson(buyItem, PurchaseRecord.class);
        try {
            int currentStorage = buyerDao.checkItem(purchaseRecord.getItemId());
            if (currentStorage < purchaseRecord.getNum()) {
                throw new CustomException.BuyItemException("item not existed or run out of storage!");
            }
            return buyerDao.addToCart(purchaseRecord, currentStorage);
        } catch (Exception ex) {
            logger.error("BuyerService.buyItem: error occurred during buying item.");
            return false;
        }
    }
}
