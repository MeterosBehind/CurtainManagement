package com.curtain.managementsystem.controller;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import com.curtain.managementsystem.service.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/infoDetail")
    @ResponseBody
    public List<Resource> getCurtainInfoDetail(int curtainId){
        List<Resource> resList = curtainService.getCurtainResList(curtainId);
        return resList;
    }

    @PostMapping("/add")
    @ResponseBody
    public HashMap<String,Object> addCurtainInfo(Curtain curtain){
        System.out.println("新增接口调用！"+curtain.toString());
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("state",200);
        resultMap.put("result","窗帘新增成功！");
        return resultMap;
    }
    @PostMapping("/edit")
    @ResponseBody
    public HashMap<String,Object> editCurtainInfo(Curtain curtain){
        System.out.println("编辑接口调用！"+curtain.toString());
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("state",200);
        resultMap.put("result","窗帘编辑成功！");
        return resultMap;
    }
    @GetMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> deleteCurtainInfo(@RequestParam String curtainIds){
        System.out.println("删除接口调用！"+curtainIds);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("state",200);
        resultMap.put("result","窗帘删除成功！");
        return resultMap;
    }
}
