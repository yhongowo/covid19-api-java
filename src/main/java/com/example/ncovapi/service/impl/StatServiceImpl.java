package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.entity.DomesticStat;
import com.example.ncovapi.entity.GlobalStat;
import com.example.ncovapi.entity.Stat;
import com.example.ncovapi.service.StatService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Stat")
public class StatServiceImpl implements StatService {
    @Resource
    private StatisticsMapper mapper;

    @Override
    @Cacheable(cacheNames = "latest", key = "#root.methodName")
    public List<Stat> selectLatest() {
        //get data
        DomesticStat domesticStat = mapper.selectDomesticLatest();
        GlobalStat globalStat = mapper.selectGlobalLatest();
        //combine
        Stat stat = new Stat();
        stat.setGlobalStatistics(globalStat);
        stat.setDomesticStat(domesticStat);
        //store into list
        List<Stat> list = new ArrayList<>();
        list.add(stat);
        //return a list
        return list;
    }

    @Override
    @Cacheable(cacheNames = "domesticAll", key = "#root.methodName")
    public List<DomesticStat> selectDomesticAll() {
        return mapper.selectDomesticAll();
    }

    @Override
    @Cacheable(cacheNames = "globalAll", key = "#root.methodName")
    public List<GlobalStat> selectGlobalAll() {
        return mapper.selectGlobalAll();
    }

    @Override
    @CachePut
    public void insert(Stat stat) {
//        DomesticStat domesticStat = stat.getDomesticStat();
//        domesticStat.setCountRemark("累计确诊 " + domesticStat.getConfirmedCount() + "例，现存确诊" +
//                domesticStat.getCurrentConfirmedCount() + "例，重症" + domesticStat.getSeriousCount() + "例，疑似 " +
//                domesticStat.getSuspectedCount() + "例，死亡 " + domesticStat.getDeadCount() + "例，治愈 " +
//                domesticStat.getCuredCount() + "例");
//        stat.setDomesticStat(domesticStat);
        mapper.addDomestic(stat.getDomesticStat());
        mapper.addGlobal(stat.getGlobalStatistics());
    }
}
