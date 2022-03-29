package com.example.ncovapi.dao;

import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.City;
import org.apache.ibatis.annotations.Mapper;
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

    int addCity(City city);

    City selectCity(String city);

    ArrayList<City> selectCitiesFromProvince(String province);

}
