package com.example.ncovapi.service;

import com.example.ncovapi.entity.DomesticStat;
import com.example.ncovapi.entity.GlobalStat;
import com.example.ncovapi.entity.Stat;

import java.util.List;

public interface StatService {
    List<Stat> selectLatest();
    List<DomesticStat> selectDomesticAll();
    List<GlobalStat> selectGlobalAll();
    void insert(Stat stat);
}
