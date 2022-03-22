package com.example.ncovapi.service;

import com.example.ncovapi.entity.Statistics;

import java.util.List;

public interface StatisticsService {
    Statistics today();
    List<Statistics> history();
    void insert(Statistics statistics);
}
