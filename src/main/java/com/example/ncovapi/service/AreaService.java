package com.example.ncovapi.service;

import com.example.ncovapi.entity.AreaStat;

import java.util.List;

public interface AreaService {
    List<AreaStat> getAllArea();
    void updateArea(List<AreaStat> areaStatsList);
}
