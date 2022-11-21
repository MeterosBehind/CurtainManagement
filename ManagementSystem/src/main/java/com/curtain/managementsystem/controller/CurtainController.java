package com.curtain.managementsystem.controller;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.service.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class CurtainController {

    @Autowired
    CurtainService curtainService;

    @GetMapping("/")
    public String toLogin(){
        return "/login.html";
    }

    @GetMapping("/info")
    @ResponseBody
    public List<Curtain> getCurtainInfo(){
        List<Curtain> curtainList = curtainService.getCurtainList();
        return curtainList;
    }
    @GetMapping("/add")
    @ResponseBody
    public HashMap<String,Object> addCurtainInfo(){
        System.out.println("新增接口调用！");
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("state",200);
        resultMap.put("result","窗帘新增成功！");
        return resultMap;
    }
}
