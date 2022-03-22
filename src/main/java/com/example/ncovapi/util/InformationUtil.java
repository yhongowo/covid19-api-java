package com.example.ncovapi.util;

import com.example.ncovapi.dao.AreaStatMapper;
import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.dao.TimelineMapper;
import com.example.ncovapi.entity.Abroad;
import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.Statistics;
import com.example.ncovapi.entity.Timeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class InformationUtil {

    public static final Logger logger = LoggerFactory.getLogger(InformationUtil.class);
    @Resource
    TimelineMapper timelineMapper;
    @Resource
    StatisticsMapper statisticsMapper;
    @Resource
    AreaStatMapper areaStatMapper;

    /**
     * 添加实时消息
     *
     * @param timeLineList
     */
    public String insertTimeLine(List<Timeline> timeLineList) {
        StringBuilder timeLineNews = new StringBuilder();
        StringBuilder log = new StringBuilder();
        for (Timeline timeLine : timeLineList) {
            int res = timelineMapper.addTimeLine(timeLine);
            if (res == 1) {
                //新消息
                timeLineNews.append(timeLine.getProvinceName() + "<br>" + timeLine.getTitle() + "<br>" + timeLine.getSummary() + "<br><br>");
            }
            log.append(res + " ");
        }
        logger.info(log.toString());
        return timeLineNews.toString();
    }

    /**
     * 添加总体数据
     *
     * @param statistics
     */
    public String insertStatistics(Statistics statistics) {
        StringBuilder statisticsNews = new StringBuilder();

        //数据库中最新的一条statistics数据
        Statistics oldStatistics = statisticsMapper.selectLatest();
        if (statistics.equals(oldStatistics)) {
            logger.info(0 + "");
            return null;
        } else {
            statistics.setCountRemark("累计确诊 " + statistics.getConfirmedCount() + "例，现存确诊" + statistics.getCurrentConfirmedCount() + "例，重症" + statistics.getSeriousCount() +
                    "例，疑似 " + statistics.getSuspectedCount() + "例，死亡 " + statistics.getDeadCount() + "例，治愈 " + statistics.getCuredCount() + "例");
            int res = statisticsMapper.addStatistics(statistics);
            logger.info(res + "");

            String increaseMessage = "较昨日增长: " + "<br/>累计确诊：" + statistics.getConfirmedIncr() + "<br/>现存确诊：" + statistics.getCurrentConfirmedIncr() + "<br/>重症：" + statistics.getSeriousIncr() +
                    "<br/>疑似：" + statistics.getSuspectedIncr() + "<br/>死亡：" + statistics.getDeadIncr() + "<br/>治愈：" + statistics.getCuredIncr();
            statisticsNews.append(statistics.getCountRemark() + "<br/><br/>" + increaseMessage + "<br/><br/>");

            return statisticsNews.toString();
        }


    }

    /**
     * 添加各省信息
     *
     * @param areaStatsList
     */
    public String insertProvince(List<AreaStat> areaStatsList) {
        StringBuilder provinceNews = new StringBuilder();
        StringBuilder log = new StringBuilder();

        for (AreaStat areaStat : areaStatsList) {
            AreaStat oldAreaStat = selectProvince(areaStat.getProvinceName());
            if (oldAreaStat != null) {
                //库中已经有了这个省份
                if (areaStat.equals(oldAreaStat)) {
                    //数据一致 不添加
                    log.append("+E0" + "  ");
                } else {
                    areaStat.setModifyTime(System.currentTimeMillis() / 1000);
                    int res = areaStatMapper.addProvince(areaStat);
                    log.append("+M" + res + "  ");
                    provinceNews.append("变动：" + areaStat.getProvinceName() + "<br/>");
                    provinceNews.append(getNumber(areaStat));
                    provinceNews.append(getNumberChange(oldAreaStat, areaStat) + "<br><br>");
                }
            } else {
                //新增省份
                areaStat.setModifyTime(System.currentTimeMillis() / 1000);
                int res = areaStatMapper.addProvince(areaStat);
                provinceNews.append("新增：" + areaStat.getProvinceName() + "<br/>");
                provinceNews.append(getNumber(areaStat) + "<br><br>");
                log.append("+N" + res + "  ");
            }
            List<AreaStat.CitiesBean> cityList = areaStat.getCities();
            for (AreaStat.CitiesBean city : cityList) {
                city.setProvinceName(areaStat.getProvinceName());
                AreaStat.CitiesBean oldCity = selectCity(city.getCityName());
                if (oldCity != null) {
                    //已有该城
                    if (oldCity.equals(city)) {
                        //数据一致
                        log.append("E0" + "  ");
                    } else {
                        city.setModifyTime(System.currentTimeMillis() / 1000);
                        int res = areaStatMapper.addCity(city);
                        log.append("M" + res + "  ");
                    }
                } else {
                    //新增城市
                    city.setModifyTime(System.currentTimeMillis() / 1000);
                    int res = areaStatMapper.addCity(city);
                    log.append("N" + res + "  ");
                }
            }
        }
        logger.info(log.toString());

        return provinceNews.toString();
    }


    public String getNumberChange(AreaStat oldAreaStat, AreaStat newAreaStat) {
        StringBuilder sb = new StringBuilder();

        sb.append("确诊人数变化：" + (newAreaStat.getConfirmedCount() - oldAreaStat.getConfirmedCount()) + "<br>");
        sb.append("死亡人数变化：" + (newAreaStat.getDeadCount() - oldAreaStat.getDeadCount()) + "<br>");
        sb.append("治愈人数变化：" + (newAreaStat.getCuredCount() - oldAreaStat.getCuredCount()) + "<br>");

        return sb.toString();

    }

    public String getNumber(AreaStat areaStat) {
        StringBuilder sb = new StringBuilder();

        sb.append("确诊人数：" + areaStat.getConfirmedCount() + "<br>");
        sb.append("死亡人数：" + areaStat.getDeadCount() + "<br>");
        sb.append("治愈人数：" + areaStat.getCuredCount() + "<br>");

        return sb.toString();

    }

    /**
     * 查询某省信息
     *
     * @param provinceName
     * @return
     */
    public AreaStat selectProvince(String provinceName) {
        AreaStat province = areaStatMapper.selectProvince(provinceName);
        return province;
    }

    /**
     * 查询某市信息
     *
     * @param cityName
     * @return
     */
    public AreaStat.CitiesBean selectCity(String cityName) {
        AreaStat.CitiesBean city = areaStatMapper.selectCity(cityName);
        return city;
    }


}
