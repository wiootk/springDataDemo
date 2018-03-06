package com.jun.controller;

import com.jun.service.User2Service;
import com.jun.util.AccessAllow;
import com.jun.util.Domain;
import com.jun.util.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo2")
//@Api(description="swagger版块", value = "swagger版块")

@Domain("domain")
@AccessAllow({"cc","dd","ee"})

public class Demo2Controller {
    @Autowired
    User2Service userService;
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    @RequestMapping("/info")
    public String info(){
        return "info";
    }
    @RequestMapping("/findall")
    @ResponseBody
    public Map<String, Object> getUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findAll());
        return map;
    }
    @RequestMapping("/findById")
    @ResponseBody
    public Map<String, Object> findById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findById(id));
        return map;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> save(String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.save(name));
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        userService.delete(id);
        map.put("state", "success");
        map.put("data","" );
        return map;
    }

    @RequestMapping("/recover")
    @ResponseBody
    public Map<String, Object> recover(String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.recover(id));
        return map;
    }


    @RequestMapping("/doLog")
    @ResponseBody
    @LogService(desc = "操作日志demo", oprateType = "oprateType", objId = "1",objType="12")
    public Map<String, Object> doLog(String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data","操作日志demo");
        return map;
    }

    @RequestMapping("/swagger")
    @ResponseBody
    @ApiOperation(value = "swagger 测试", httpMethod = "POST", notes = "测试 swagger")
    public Map<String, Object> swagger( @ApiParam(required = true, name = "id", value = "参数")@RequestParam("id")String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data","swagger demo");
        return map;
    }

    @RequestMapping("/AccessAllow")
    @ResponseBody
//    @AccessAllow({"aa","bb","cc"})
    public Map<String, Object> AccessAllow( String id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data","swagger demo");
        return map;
    }



}