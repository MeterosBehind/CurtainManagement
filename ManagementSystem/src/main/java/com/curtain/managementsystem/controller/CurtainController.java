package com.curtain.managementsystem.controller;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.service.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
