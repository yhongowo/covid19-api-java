package com.example.ncovapi.dao;

import com.example.ncovapi.entity.DomesticStat;
import com.example.ncovapi.entity.GlobalStat;
import com.example.ncovapi.entity.Stat;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface StatisticsMapper {
    DomesticStat selectDomesticLatest();

    GlobalStat selectGlobalLatest();

    ArrayList<DomesticStat> selectDomesticAll();

    ArrayList<GlobalStat> selectGlobalAll();

    int addDomestic(DomesticStat domestic);

    int addGlobal(GlobalStat global);
}
