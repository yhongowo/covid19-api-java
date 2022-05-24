package com.example.ncovapi.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ncovapi.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析器
 * @author Ticsmyc
 * @package fun.ticsmyc.crawler
 * @date 2020-01-26 18:05
 */
public class Parse {

    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    /**
     * 解析TimeLine的JSON数据
     * @param timelineServiceInformation
     * @return
     */
    public static List<Timeline> parseTimeLineInformation(String timelineServiceInformation){
        List<Timeline> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(timelineServiceInformation);
        for(Object jsonObj : jsonArray){
            Timeline timeline = JSON.toJavaObject((JSON) jsonObj,Timeline.class);
            timeline.setCreateTime(timeline.getCreateTime()/1000);
            timeline.setModifyTime(timeline.getModifyTime()/1000);
            timeline.setPubDate(timeline.getPubDate()/1000);
            list.add(timeline);
            //logger.info(String.valueOf(timeline));
        }
        return list;
    }

    /**
     * 解析StatisticsJSON数据
     * @param staticInformation
     * @return
     */
    public static Stat parseStatisticsInformation(String staticInformation){
        JSONObject jsonObj = JSON.parseObject(staticInformation);
        DomesticStat domesticStat = JSON.toJavaObject(jsonObj,DomesticStat.class);
        Stat stat = JSON.toJavaObject(jsonObj, Stat.class);
        stat.setDomesticStat(domesticStat);
        //logger.info(String.valueOf(statistics));
        return stat;
    }

    /**
     * 解析地区信息JSON数据
     * @param areaInformation
     * @return
     */
    public static List<AreaStat> parseAreaInformation(String areaInformation){
        List<AreaStat> list = new ArrayList<>();
        JSONArray jsonArray =JSON.parseArray(areaInformation);
        for(Object jsonObj : jsonArray){
            AreaStat areaStat = JSON.toJavaObject((JSON) jsonObj, AreaStat.class);
            list.add(areaStat);
            //logger.info(String.valueOf(areaStat));
        }
        return list;
    }

    public static List<Abroad> parseAbroadInformation(String abroadInformation){
        List<Abroad> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(abroadInformation);
        for(Object jsonObj : jsonArray) {
            Abroad abroad = JSON.toJavaObject((JSON) jsonObj, Abroad.class);
            list.add(abroad);
        }
        return list;
    }

}
