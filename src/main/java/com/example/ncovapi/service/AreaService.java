package com.example.ncovapi.service;

import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.City;

import java.util.List;

public interface AreaService {
    List<AreaStat> getProvinceAll();
    List<City> getCityAll();
    void updateArea(List<AreaStat> areaStatsList);
}
