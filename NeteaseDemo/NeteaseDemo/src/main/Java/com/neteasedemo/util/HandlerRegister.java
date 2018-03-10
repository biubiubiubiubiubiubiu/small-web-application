package com.neteasedemo.util;

import com.neteasedemo.baseLib.UpdateHandler;
import com.neteasedemo.util.UpdateHandlers.*;

import java.util.HashMap;

public class HandlerRegister {

    public static HashMap<String, UpdateHandler> registerHandlers() {

        // Handlers used for updating different columns

        HashMap<String, UpdateHandler> handlerHashMap = new HashMap<>();
        handlerHashMap.put("title", new TitleHandler());
        handlerHashMap.put("abstract", new AbstractHandler());
        handlerHashMap.put("introduction", new IntroductionHandler());
        handlerHashMap.put("storage", new StorageHandler());
        handlerHashMap.put("price", new PriceHandler());
        handlerHashMap.put("imageUrl", new ImageUrlHandler());
        return handlerHashMap;

    }

}
