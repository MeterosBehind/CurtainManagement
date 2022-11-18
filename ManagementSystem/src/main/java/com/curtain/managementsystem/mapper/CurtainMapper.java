package com.curtain.managementsystem.mapper;

import com.curtain.managementsystem.domains.Curtain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurtainMapper {
    List<Curtain> getCurtainInfoMapper();
}
