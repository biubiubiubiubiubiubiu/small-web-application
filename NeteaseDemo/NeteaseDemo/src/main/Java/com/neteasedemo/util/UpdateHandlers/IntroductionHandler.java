package com.neteasedemo.util.UpdateHandlers;

import com.neteasedemo.baseLib.UpdateHandler;

public class IntroductionHandler extends UpdateHandler {
    public String generateSql(){
        return "UPDATE Item SET Introduction=? WHERE id=?";
    }
}