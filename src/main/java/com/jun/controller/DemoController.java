package com.jun.controller;

import com.jun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DemoController {
    @Autowired
    UserService userService;
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    @RequestMapping("/getuser")
    @ResponseBody
    public String getUser(Integer id, String name){
        return userService.getUser(id, name).toString();
    }
    @RequestMapping("/getuserJson")
    @ResponseBody
    public Map<String, Object> getUserJson(Integer id, String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.getUser(id, name));
        return map;
    }
}
