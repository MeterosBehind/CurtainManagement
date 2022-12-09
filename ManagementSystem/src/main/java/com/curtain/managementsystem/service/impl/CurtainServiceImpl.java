package com.curtain.managementsystem.service.impl;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import com.curtain.managementsystem.mapper.CurtainMapper;
import com.curtain.managementsystem.service.CurtainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurtainServiceImpl implements CurtainService {

    private static final Logger logger = LoggerFactory.getLogger(CurtainServiceImpl.class);

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
}
