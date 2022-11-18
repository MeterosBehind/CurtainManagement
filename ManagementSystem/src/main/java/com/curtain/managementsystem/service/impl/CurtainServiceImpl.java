package com.curtain.managementsystem.service.impl;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.mapper.CurtainMapper;
import com.curtain.managementsystem.service.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurtainServiceImpl implements CurtainService {

    @Autowired
    CurtainMapper curtainMapper;

    @Override
    public List<Curtain> getCurtainList() {
        List<Curtain> curtainList = curtainMapper.getCurtainInfoMapper();
        return curtainList;
    }
}
