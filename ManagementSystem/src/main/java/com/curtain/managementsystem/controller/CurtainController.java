package com.curtain.managementsystem.controller;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import com.curtain.managementsystem.service.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
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

    @GetMapping("/infoShow")
    @ResponseBody
    public List<Curtain> getCurtainInfoShow(){
        List<Curtain> curtainList = curtainService.getCurtainListShow();
        return curtainList;
    }

    @GetMapping("/test")
    @ResponseBody
    public void test(){
        System.out.println("十大阿斯顿请问请问111111");
    }
    @GetMapping("/infoDetail")
    @ResponseBody
    public List<Resource> getCurtainInfoDetail(int curtainId){
        List<Resource> resList = curtainService.getCurtainResList(curtainId);
        return resList;
    }

    @PostMapping("/add")
    @ResponseBody
    public HashMap<String,Object> addCurtainInfo(Curtain curtain, @RequestParam MultipartFile[] multipartFiles){
        System.out.println("新增接口调用！"+curtain.toString());
        String result = curtainService.addCurtain(curtain,multipartFiles);
        HashMap<String,Object> resultMap = new HashMap<>();
        if(result == null){
            resultMap.put("state",200);
            resultMap.put("result","窗帘新增成功！");
        }else {
            resultMap.put("state",500);
            resultMap.put("result",result);
        }
        return resultMap;
    }
    @PostMapping("/edit")
    @ResponseBody
    public HashMap<String,Object> editCurtainInfo(Curtain curtain, @Nullable @RequestParam MultipartFile[] multipartFiles, @RequestParam String deletedResIds){
        System.out.println("编辑接口调用！"+curtain.toString());
        String result = curtainService.editCurtain(curtain,multipartFiles,deletedResIds);
        HashMap<String,Object> resultMap = new HashMap<>();
        if(result==null){
            resultMap.put("state",200);
            resultMap.put("result","窗帘编辑成功！");
        }else {
            resultMap.put("state",500);
            resultMap.put("result",result);
        }
        return resultMap;
    }
    @GetMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> deleteCurtainInfo(@RequestParam String curtainIds){
        System.out.println("删除接口调用！"+curtainIds);
        String result = curtainService.deleteCurtains(curtainIds);
        HashMap<String,Object> resultMap = new HashMap<>();
        if(result==null){
            resultMap.put("state",200);
            resultMap.put("result","窗帘删除成功！");
        }else {
            resultMap.put("state",500);
            resultMap.put("result",result);
        }
        return resultMap;
    }

    @GetMapping("/deleteRes")
    @ResponseBody
    public HashMap<String,Object> deleteRes(int resId){
        String result = curtainService.deleteRes(resId);
        HashMap<String,Object> resultMap = new HashMap<>();
        if(result==null){
            resultMap.put("state",200);
            resultMap.put("result","单个资源删除成功！");
        }else {
            resultMap.put("state",500);
            resultMap.put("result",result);
        }
        return resultMap;
    }

}
