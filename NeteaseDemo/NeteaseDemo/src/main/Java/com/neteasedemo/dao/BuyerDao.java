package com.neteasedemo.dao;

import com.neteasedemo.model.Item;
import com.neteasedemo.model.PurchaseRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BuyerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BuyerDao.class);

    public int checkItem(int itemId) {
        String getItemSql = "SELECT * FROM Item WHERE id=?";
        try {
            Item item = jdbcTemplate.queryForObject(getItemSql, new Object[]{itemId}, (resultSet, i) -> {
                if (resultSet == null) {
                    return null;
                }
                Item curr = new Item();
                curr.setStorage(resultSet.getInt("storage"));
                return curr;
            });
            return item == null ? -1 : item.getStorage();
        } catch (Exception ex) {
            logger.error("SellerDao.checkItem: error occurred during fetching item.\n" + ex.getMessage());
            return -1;
        }
    }

    public boolean addToCart(PurchaseRecord purchaseRecord, int currentStorage) {
        String addRecord = "INSERT INTO CartRecord (itemId, num, price, recordingTime) VALUES(?, ?, ?, ?)";
//        String updateStorageSql = "UPDATE Item SET storage=? WHERE id=?";
        try {
            jdbcTemplate.update(addRecord, new Object[]{purchaseRecord.getItemId(),
                                                        purchaseRecord.getNum(),
                                                        purchaseRecord.getPrice(),
                                                        purchaseRecord.getTimestamp()});
//            jdbcTemplate.update(updateStorageSql, new Object[]{currentStorage - purchaseRecord.getNum(), purchaseRecord.getItemId()});
            return true;
        } catch (Exception ex) {
            logger.error("SellerDao.buyItem: error occurred when updating records \n" + ex.getMessage());
            return false;
        }
    }
}
