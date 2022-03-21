package com.example.ncovapi.service;

import com.example.ncovapi.entity.Statistics;

import java.util.List;

public interface StatisticsService {
    public Statistics today();
    public List<Statistics> history();
}
