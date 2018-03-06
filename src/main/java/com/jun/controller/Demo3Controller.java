package com.jun.controller;

import com.jun.service.User3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo3")
public class Demo3Controller {
    @Autowired
    User3Service userService;
    @RequestMapping("/findall")
    @ResponseBody
    @Transactional
    public Map<String, Object> getUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findAll());
        return map;
    }
    @RequestMapping("/copy")
    @ResponseBody
    public Map<String, Object> copy(){
        userService.save( userService.findAllToCopy());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findAll());
        map.put("dataCopy", userService.findAllToCopy());
        return map;
    }
}
