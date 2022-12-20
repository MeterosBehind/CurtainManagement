package com.curtain.managementsystem.mapper;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurtainMapper {
    List<Curtain> getCurtainInfoMapper();
    List<Resource> getCurtainResMapper(int curtainId);

    void addCurtain(Curtain curtain);
}
