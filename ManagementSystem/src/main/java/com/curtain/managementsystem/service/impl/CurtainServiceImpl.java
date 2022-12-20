package com.curtain.managementsystem.service.impl;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import com.curtain.managementsystem.mapper.CurtainMapper;
import com.curtain.managementsystem.service.CurtainService;
import com.curtain.managementsystem.utils.MultipartFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CurtainServiceImpl implements CurtainService {

    private static final Logger logger = LoggerFactory.getLogger(CurtainServiceImpl.class);

    @Value("${httpd.local.path}")
    private String localPath;

    @Autowired
    CurtainMapper curtainMapper;

    @Value("${httpd.http.path}")
    private String httpPath;

    @Override
    public List<Curtain> getCurtainList() {
        List<Curtain> curtainList = curtainMapper.getCurtainInfoMapper();
        return curtainList;
    }

    @Override
    public List<Resource> getCurtainResList(int curtainId) {
        List<Resource> resList = curtainMapper.getCurtainResMapper(curtainId);
        for(int i = 0;i<resList.size();i++){
            /*Resource resource = resList.get(i);
            resource.setPath(httpPath+resList.get(i).getPath());
            resList.set(i,resource);*/
            resList.get(i).setPath(httpPath+resList.get(i).getPath());
        }
        return resList;
    }

    @Override
    public void addCurtain(Curtain curtain, MultipartFile[] multipartFiles) {
        curtainMapper.addCurtain(curtain);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR),month = now.get(Calendar.MONTH),day=now.get(Calendar.DAY_OF_MONTH);
        String detailPath = "curtainRes" + File.separator + year+ File.separator + month+ File.separator + day;
        String curtainPath = FilenameUtils.concat(localPath,detailPath);
        for(MultipartFile multipartFile:multipartFiles){
            MultipartFileUtil.saveMultipartFile(multipartFile,curtainPath);
        }


    }

}
