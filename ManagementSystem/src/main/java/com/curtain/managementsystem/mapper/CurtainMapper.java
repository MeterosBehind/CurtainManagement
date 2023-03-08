package com.curtain.managementsystem.mapper;

import com.curtain.managementsystem.domains.Curtain;
import com.curtain.managementsystem.domains.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurtainMapper {
    List<Curtain> getCurtainInfoMapper();

    List<Curtain> getCurtainShow();

    List<Resource> getCurtainResMapper(int curtainId);

    void addCurtain(Curtain curtain);

    void addResources(List<Resource> resourceList);

    int getCurtainCountByName(Curtain curtain);

    Resource getResById(int resId);

    void deleteRes(Resource resource);

    void updateCurtain(Curtain curtain);

    void deleteCurtains(String curtainIds);

    void deleteCurtainRes(String curtainIds);

    void deleteResById(int resId);
}
