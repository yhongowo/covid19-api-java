package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.entity.Statistics;
import com.example.ncovapi.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper mapper;

    @Override
    public Statistics today() {
        return mapper.selectLatest();
    }

    @Override
    public ArrayList<Statistics> history() {
        return mapper.selectAll();
    }
}
