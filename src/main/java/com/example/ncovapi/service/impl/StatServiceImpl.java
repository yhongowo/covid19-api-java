package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.entity.Stat;
import com.example.ncovapi.service.StatisticsService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Stat")
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper mapper;

    @Override
    @Cacheable(cacheNames = "latest",key = "#root.methodName")
    public List<Stat> selectLatest() {
        List<Stat> latest = mapper.selectLatest();
        latest.get(0).setGlobalStatistics(mapper.selectGlobalLatest());
        return latest;
    }

    @Override
    @Cacheable(cacheNames = "all",key = "#root.methodName")
    public List<Stat> selectAll() {
        return mapper.selectAll();
    }

    @Override
    @CachePut
    public void insert(Stat stat) {
        stat.setCountRemark("累计确诊 " + stat.getConfirmedCount() + "例，现存确诊" + stat.getCurrentConfirmedCount() + "例，重症" +
                stat.getSeriousCount() + "例，疑似 " + stat.getSuspectedCount() + "例，死亡 " + stat.getDeadCount() + "例，治愈 " +
                stat.getCuredCount() + "例");

        mapper.addDomestic(stat);
        mapper.addGlobal(stat.getGlobalStatistics());
    }
}
