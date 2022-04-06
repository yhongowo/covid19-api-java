package com.example.ncovapi.service;

import com.example.ncovapi.entity.Stat;

import java.util.List;

public interface StatisticsService {
    List<Stat> selectLatest();
    List<Stat> selectAll();
    void insert(Stat stat);
}
