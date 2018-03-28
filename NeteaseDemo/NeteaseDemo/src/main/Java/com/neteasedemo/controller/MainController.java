package com.neteasedemo.controller;

import com.neteasedemo.model.TestClass;
import com.neteasedemo.model.User;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Value("#{prop.imagePath}")
    private String imagePath;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value= "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String sellerPublic() {
        return "public";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailPage() {
        return "itemDetail";
    }

    @RequestMapping(value = "/itemEdit", method = RequestMethod.GET)
    public String editItem() {
        return "EditItem";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCart() {
        return "cart";
    }

    @RequestMapping(value = "/debt", method = RequestMethod.GET)
    public String getDebt() {
        return "debt";
    }
    @ResponseBody
    @RequestMapping(value = "/{imageInfo}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("imageInfo") String imageInfo, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("image/jpeg");
        String[] imageInfos = imageInfo.split("_");
        String saveDirectory=request.getSession().getServletContext().getRealPath("/");
        File file = new File(saveDirectory + imageInfos[0] + "." + imageInfos[1]);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String test() {
        TestClass testClass = new TestClass("hello");
        JSONObject jsonObject = new JSONObject(testClass);
        return jsonObject.toString();
    }

}
