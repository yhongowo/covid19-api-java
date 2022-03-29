package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.entity.Statistics;
import com.example.ncovapi.service.StatisticsService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service

public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper mapper;

    @Override

    public Statistics getLatest() {
        return mapper.selectLatest();

    }

    @Override

    public List<Statistics> getHistory() {
        return mapper.selectAll();
    }

    @Override

    public void insert(Statistics statistics) {
        statistics.setCountRemark("累计确诊 " + statistics.getConfirmedCount() + "例，现存确诊" + statistics.getCurrentConfirmedCount() + "例，重症" +
                statistics.getSeriousCount() + "例，疑似 " + statistics.getSuspectedCount() + "例，死亡 " + statistics.getDeadCount() + "例，治愈 " +
                statistics.getCuredCount() + "例");

        mapper.addStatistics(statistics);
    }
}
