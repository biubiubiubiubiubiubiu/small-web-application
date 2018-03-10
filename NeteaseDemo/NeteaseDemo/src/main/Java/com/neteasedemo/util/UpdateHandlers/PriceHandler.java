package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class PriceHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET Price=? WHERE id=?";
    }
}
