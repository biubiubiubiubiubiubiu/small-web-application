package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class ImageUrlHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET ImageUrl=? WHERE id=?";
    }
}
