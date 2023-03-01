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
import java.util.ArrayList;
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
        for(Curtain curtain:curtainList){
            if(curtain.getResList().size()!=0){
                for(Resource resource:curtain.getResList()){
                    resource.setPath(httpPath+resource.getPath());
                }
            }
        }
        return curtainList;
    }

    @Override
    public List<Curtain> getCurtainListShow() {
        List<Curtain> curtainList = curtainMapper.getCurtainShow();
        for(Curtain curtain:curtainList){
            if(curtain.getResList().size()!=0){
                for(Resource resource:curtain.getResList()){
                    resource.setPath(httpPath+resource.getPath());
                }
            }
        }
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
    public String addCurtain(Curtain curtain, MultipartFile[] multipartFiles) {
        int count = curtainMapper.getCurtainCountByName(curtain);
        if(count!=0){
            return "该窗帘名称："+curtain.getName()+"，已存在!";
        }
        curtainMapper.addCurtain(curtain);
        if(multipartFiles != null){
            handResources(curtain,multipartFiles);
        }
        return null;
    }

    @Override
    public String editCurtain(Curtain curtain, MultipartFile[] multipartFiles,String deletedIds) {
        int count = curtainMapper.getCurtainCountByName(curtain);
        if(count!=0){
            return "该窗帘名称："+curtain.getName()+"，已存在!";
        }
        if(deletedIds!=null && !"".equals(deletedIds)){
            String[] ids = deletedIds.split(",");
            for(String id:ids){
                int resId = Integer.parseInt(id);
                Resource resource = curtainMapper.getResById(resId);
                resource.setName(resource.getName()+"("+System.currentTimeMillis()+")");
                curtainMapper.deleteRes(resource);
            }
        }
        if(multipartFiles != null){
            handResources(curtain,multipartFiles);
        }
        curtainMapper.updateCurtain(curtain);
        return null;
    }

    @Override
    public String deleteCurtains(String curtainIds) {
        try{
            curtainMapper.deleteCurtains(curtainIds);
            curtainMapper.deleteCurtainRes(curtainIds);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    void handResources(Curtain curtain,MultipartFile[] multipartFiles){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR),month = now.get(Calendar.MONTH)+1,day=now.get(Calendar.DAY_OF_MONTH);
        String detailPath = "curtainRes" + File.separator + year+ File.separator + month+ File.separator + day;
        String curtainPath = FilenameUtils.concat(localPath,detailPath);
        List<Resource> resourceList = new ArrayList<>();
        for(MultipartFile multipartFile:multipartFiles){
            MultipartFileUtil.saveMultipartFile(multipartFile,curtainPath);
            String fileName = multipartFile.getOriginalFilename();
            Resource resource = new Resource();
            resource.setCurtainId(curtain.getId());
            resource.setPath(FilenameUtils.concat(detailPath,fileName));
            resource.setName(fileName);
            int type = 1;
            fileName = fileName.toLowerCase();
            if(fileName.indexOf(".mp4") != -1){
                type = 2;
            }
            resource.setType(type);
            resource.setSize(Integer.parseInt(multipartFile.getSize()+""));
            resourceList.add(resource);
        }
        curtainMapper.addResources(resourceList);
    }

}
