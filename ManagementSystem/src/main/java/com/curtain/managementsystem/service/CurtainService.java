package com.curtain.managementsystem.service;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CurtainService {
    List<Curtain> getCurtainList();

    List<Resource> getCurtainResList(int curtainId);

    void addCurtain(Curtain curtain, MultipartFile[] multipartFiles);
}
