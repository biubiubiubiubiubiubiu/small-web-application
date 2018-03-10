package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class StorageHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET Storage=? WHERE id=?";
    }
}
