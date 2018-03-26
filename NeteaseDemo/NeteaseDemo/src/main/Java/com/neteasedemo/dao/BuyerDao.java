package com.neteasedemo.dao;

import com.neteasedemo.model.Item;
import com.neteasedemo.model.PurchaseRecord;
import com.neteasedemo.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Repository
public class BuyerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BuyerDao.class);


    public boolean addToCart(PurchaseRecord purchaseRecord) {
        String addRecord = "INSERT INTO CartRecord (itemId, num, price, recordingTime) VALUES(?, ?, ?, ?)";
//        String updateStorageSql = "UPDATE Item SET storage=? WHERE id=?";
        try {
            jdbcTemplate.update(addRecord, new Object[]{purchaseRecord.getItemId(),
                                                        purchaseRecord.getNum(),
                                                        purchaseRecord.getPrice(),
                                                        purchaseRecord.getRecordingTime()});
//            jdbcTemplate.update(updateStorageSql, new Object[]{currentStorage - purchaseRecord.getNum(), purchaseRecord.getItemId()});
            return true;
        } catch (Exception ex) {
            logger.error("SellerDao.buyItem: error occurred when updating records \n" + ex.getMessage());
            return false;
        }
    }

    public List<PurchaseRecord> getCartRecord() {
        String getRecordSql = "SELECT Item.title, itemId, CartRecord.num, CartRecord.price FROM CartRecord INNER JOIN Item ON CartRecord.itemId=Item.id";
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList =  jdbcTemplate.query(getRecordSql, new Object[]{}, (resultSet, i) -> {
                PurchaseRecord purchaseRecord = new PurchaseRecord();
                try {
                    purchaseRecord.setTitle(new String(resultSet.getBytes("title"), "UTF-8"));
                } catch (Exception ex) {
                    return null;
                }
                purchaseRecord.setItemId(resultSet.getInt("itemId"));
                purchaseRecord.setNum(resultSet.getInt("num"));
                purchaseRecord.setPrice(resultSet.getFloat("price"));
                return purchaseRecord;
            });
        } catch (Exception ex) {
            return null;
        }
        return purchaseRecordList;
    }

    public void clearCart() throws CustomException.ClearCartException{
        String clearSql = "TRUNCATE TABLE CartRecord";
        try {
            jdbcTemplate.update(clearSql);
        } catch (Exception ex) {
            logger.error("BuyerDao.clearCart: error occurred when clearing cart");
            throw new CustomException.ClearCartException("BuyerDao.clearCart: error occurred when clearing cart");
        }
    }

    public boolean buyItems(List<PurchaseRecord> purchaseRecords) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        String insertBuyRecordSql = "INSERT INTO BuyRecord (itemId, price, num, recordingTime)" +
                                    " VALUES (?, ?, ?, ?)";
        String updateItemSold = "UPDATE Item SET sold=1 WHERE id=?";
        try {
            jdbcTemplate.batchUpdate(insertBuyRecordSql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setInt(1, purchaseRecords.get(i).getItemId());
                    preparedStatement.setFloat(2, purchaseRecords.get(i).getPrice());
                    preparedStatement.setInt(3, purchaseRecords.get(i).getNum());
                    preparedStatement.setString(4, date);
                }

                @Override
                public int getBatchSize() {
                    return purchaseRecords.size();
                }
            });

            jdbcTemplate.batchUpdate(updateItemSold, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setInt(1, purchaseRecords.get(i).getItemId());
                }

                @Override
                public int getBatchSize() {
                    return purchaseRecords.size();
                }
            });

            return true;
        } catch (Exception ex) {
            logger.error("BuyerDao.buyItems: error occurred when buying items");
            return false;
        }
    }

    public List<PurchaseRecord> getBuyRecord() {
        String getRecordSql = "SELECT Item.title, Item.imageUrl, recordingTime, itemId, BuyRecord.num, BuyRecord.price FROM BuyRecord INNER JOIN Item ON BuyRecord.itemId=Item.id";
        List<PurchaseRecord> purchaseRecordList;
        try {
            purchaseRecordList =  jdbcTemplate.query(getRecordSql, new Object[]{}, (resultSet, i) -> {
                PurchaseRecord purchaseRecord = new PurchaseRecord();
                try {
                    purchaseRecord.setTitle(new String(resultSet.getBytes("title"), "UTF-8"));
                    purchaseRecord.setImageUrl(new String(resultSet.getBytes("imageUrl"), "UTF-8"));
                } catch (Exception ex) {
                    return null;
                }
                purchaseRecord.setRecordingTime(resultSet.getString("recordingTime"));
                purchaseRecord.setItemId(resultSet.getInt("itemId"));
                purchaseRecord.setNum(resultSet.getInt("num"));
                purchaseRecord.setPrice(resultSet.getFloat("price"));
                return purchaseRecord;
            });
        } catch (Exception ex) {
            return null;
        }
        return purchaseRecordList;
    }

    public Float getPrice(int id) throws CustomException.ItemNotExistException {
        String getPriceSql = "SELECT price FROM BuyRecord WHERE itemId=?";
        Float price;
        try {
            price = jdbcTemplate.queryForObject(getPriceSql, new Object[]{id}, Float.class);
        } catch (Exception ex) {
            return null;
        }
        if (price == null) {
            throw new CustomException.ItemNotExistException("Item not existed");
        }
        return price;
    }
}
