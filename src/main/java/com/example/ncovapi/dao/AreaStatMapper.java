package com.example.ncovapi.dao;

import com.example.ncovapi.entity.AreaStat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;

import java.util.ArrayList;

@Mapper
public interface AreaStatMapper {
    /***
     * get one province's data
     * @return
     */
    AreaStat selectProvince(String provinceName);

    ArrayList<AreaStat> selectAllProvince();

    int addProvince(AreaStat areaStat);

    int addCity(AreaStat.CitiesBean city);

    AreaStat.CitiesBean selectCity(String city);

}
