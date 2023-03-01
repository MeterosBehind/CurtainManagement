package com.curtain.managementsystem.service;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CurtainService {
    List<Curtain> getCurtainList();

    List<Curtain> getCurtainListShow();

    List<Resource> getCurtainResList(int curtainId);

    String addCurtain(Curtain curtain, MultipartFile[] multipartFiles);

    String editCurtain(Curtain curtain, MultipartFile[] multipartFiles,String deletedIds);

    String deleteCurtains(String curtainIds);
}
