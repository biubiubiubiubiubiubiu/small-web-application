package com.neteasedemo.dao;


import com.neteasedemo.model.Item;
import com.neteasedemo.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SellerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static RowMapper<Item> itemRowMapper = new RowMapper<Item>() {
        @Override
        public Item mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                Item curr = new Item();
                curr.setId(resultSet.getInt("id"));
                curr.setTitle(new String(resultSet.getBytes("title"), "UTF-8"));
                curr.setAbs(new String(resultSet.getBytes("abs"), "UTF-8"));
                curr.setIntroduction(new String(resultSet.getBytes("introduction"), "UTF-8"));
                curr.setImageUrl(new String(resultSet.getBytes("imageUrl"), "UTF-8"));
                curr.setPrice(resultSet.getFloat("price"));
                curr.setSold(resultSet.getInt("sold"));
                return curr;
            } catch (Exception ex) {
                return null;
            }
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(SellerDao.class);
    public boolean createItem(Item item) throws CustomException.ItemAlreadyExistedException, CustomException.ItemFullException {
        // check if existing same item
        String retrieveItemSql = "SELECT COUNT(*) FROM Item WHERE title=?";
        int num;
        try {
            num = jdbcTemplate.queryForObject(retrieveItemSql, new Object[]{item.getTitle().getBytes("UTF-8")}, Integer.class);
        } catch (Exception ex) {
            logger.error("SellerDao.createItem: error during finding item. \n {}", ex.getMessage());
            return false;
        }
        if (num > 0) {
            throw new CustomException.ItemAlreadyExistedException("SellerDao.createItem: item already existed!");
        }
        String countSql = "SELECT COUNT(*) FROM Item";
        try {
            int total = jdbcTemplate.queryForObject(countSql, new Object[]{}, Integer.class);
            if (total >= 1000) {
                throw new CustomException.ItemFullException("SellerDao.createItem: storage full!");
            }
        } catch (Exception ex) {
            logger.error("SellerDao.createItem: error during finding item. \n {}", ex.getMessage());
            return false;
        }
        // add new item to database
        String insertItemSql = "INSERT INTO Item (title, abs, introduction, price, imageUrl) " +
                "Values (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(insertItemSql, new Object[]{item.getTitle().getBytes("UTF-8"),
                    item.getAbs().getBytes("UTF-8"),
                    item.getIntroduction().getBytes("UTF-8"),
                    item.getPrice(),
                    item.getImageUrl().getBytes("UTF-8")});
        } catch (Exception ex) {
            logger.error("SellerDao.createItem: error during inserting new item. \n {}", ex.getMessage());
            return false;
        }
        // Successful insertion
        return true;
    }

    public List<Item> getAllItems() {
        String getAllItemsSql = "SELECT id, title, price, imageUrl, sold FROM Item";
        List<Item> items;
        try {
            items = jdbcTemplate.query(getAllItemsSql, (resultSet, i) -> {
                try {
                    Item curr = new Item();
                    curr.setId(resultSet.getInt("id"));
                    curr.setTitle(new String(resultSet.getBytes("title"), "UTF-8"));
                    curr.setImageUrl(new String(resultSet.getBytes("imageUrl"), "UTF-8"));
                    curr.setPrice(resultSet.getFloat("price"));
                    curr.setSold(resultSet.getInt("sold"));
                    return curr;
                } catch (Exception ex) {
                    return null;
                }
            });
        } catch (Exception ex) {
            logger.error("SellerDao.getAllItems: error during finding all items. \n {}", ex.getMessage());
            return null;
        }
        return items;
    }

    public boolean itemExist(int id) {
        String retrieveItemSql = "SELECT COUNT(id) FROM Item WHERE id=?";
        int num;
        try {
            num = jdbcTemplate.queryForObject(retrieveItemSql, new Object[]{id}, Integer.class);
        } catch (Exception ex) {
            logger.error("SellerDao.itemExist: error during finding item. \n {}", ex.getMessage());
            return false;
        }
        return num != 0;
    }

    public void updateItem(Item item) throws CustomException.UpdateItemException{
        try {
            String updateSql = "UPDATE Item SET title=?, abs=?, introduction=?, price=?, imageUrl=? WHERE id=?" ;
            jdbcTemplate.update(updateSql, new Object[]{item.getTitle().getBytes("UTF-8"),
                                                        item.getAbs().getBytes("UTF-8"),
                                                        item.getIntroduction().getBytes("UTF-8"),
                                                        item.getPrice(),
                                                        item.getImageUrl().getBytes("UTF-8"),
                                                        item.getId()});
        } catch (Exception ex) {
            logger.error("SellerDao.updateItem: error during updating item. \n {}", ex.getMessage());
            throw new CustomException.UpdateItemException("SellerDao.updateItem: error during updating item.");
        }
    }

    public Item getItem(int id) {
        String getItemSql= "SELECT * FROM Item WHERE id=?";
        Item item;
        try {
            item = jdbcTemplate.queryForObject(getItemSql, new Object[]{id}, itemRowMapper);
        } catch (Exception ex) {
            logger.error("SellerDao.getItem: error during getting item. \n {}", ex.getMessage());
            return null;
        }
        return item;
    }

    public boolean deleteItem(int id) {
        String deleteSql = "DELETE FROM Item WHERE id=?";
        try {
            jdbcTemplate.update(deleteSql, new Object[]{id});
        } catch (Exception ex) {
            logger.error("SellerDao.getItem: error during deleting item. \n {}", ex.getMessage());
            return false;
        }
        return true;
    }
}
