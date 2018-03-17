package com.neteasedemo.model;
import java.sql.Timestamp;

public class PurchaseRecord {

    private int id;
    private int itemId;
    private float price;
    private int num;
    private String recordingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTimestamp() {
        return recordingTime;
    }

    public void setTimestamp(String recordingTime) {
        this.recordingTime = recordingTime;
    }
}
