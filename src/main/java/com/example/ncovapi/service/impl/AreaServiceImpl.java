package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.AreaStatMapper;
import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.service.AreaService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Area")
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaStatMapper areaStatMapper;

    @Override
    @Cacheable()
    public List<AreaStat> getAllArea() {
        return areaStatMapper.selectAllProvince();
    }
}
