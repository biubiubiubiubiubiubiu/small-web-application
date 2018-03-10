package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class AbstractHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET Abstract=? WHERE id=?";
    }
}

