package com.example.ncovapi.dao;

import com.example.ncovapi.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface StatisticsMapper {
    /***
     * get statistics
     * @return
     */
    //返回最新
    Statistics selectLatest();

    //最大返回365条数据
    ArrayList<Statistics> selectAll();

    int addStatistics(Statistics statistics);

}
