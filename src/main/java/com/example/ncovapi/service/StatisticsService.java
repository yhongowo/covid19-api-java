package com.example.ncovapi.service;

import com.example.ncovapi.entity.Statistics;

import java.util.List;

public interface StatisticsService {
    Statistics getLatest();
    List<Statistics> getHistory();
    void insert(Statistics statistics);
}
