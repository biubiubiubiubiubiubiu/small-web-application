package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class TitleHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET Title=? WHERE id=?";
    }
}