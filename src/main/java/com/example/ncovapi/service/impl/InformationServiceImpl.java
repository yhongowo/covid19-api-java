package com.example.ncovapi.service.impl;

import com.example.ncovapi.crawler.Crawler;
import com.example.ncovapi.crawler.Parse;
import com.example.ncovapi.crawler.Tools;
import com.example.ncovapi.dao.AbroadMapper;
import com.example.ncovapi.dao.AreaStatMapper;
import com.example.ncovapi.dao.StatisticsMapper;
import com.example.ncovapi.dao.TimelineMapper;
import com.example.ncovapi.entity.Abroad;
import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.Statistics;
import com.example.ncovapi.entity.Timeline;
import com.example.ncovapi.service.AbroadService;
import com.example.ncovapi.service.InformationService;
import com.example.ncovapi.service.StatisticsService;
import com.example.ncovapi.util.InformationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private InformationUtil informationUtil;
    @Resource
    private AbroadService abroadService;
    @Resource
    private StatisticsService statisticsService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getNews() {
        //获取HTML数据
        Tools.getPageByJSoup(Crawler.URL);

        //提取static信息的json数据

        String staticInformation = null;
        //解析static信息的json数据
        Statistics statisticsInformation = null;
        try {
            staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_1, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
            //去掉static数据正则1匹配最后的 }catch(e){}串， 剩下的就是一个格式正确的json串了
            staticInformation = staticInformation.replace("}catch(e){}","");
            statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
        } catch (Exception e1) {
            logger.error("static信息正则1匹配失败，切换正则2");
            try {
                staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_2, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
                statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
            } catch (Exception e2) {
                logger.error("static信息正则2匹配失败，切换正则3");
                staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_3, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
                statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
            }
        }

        String abroadInformation = null;
        List<Abroad> abroadList = null;
        try {
            abroadInformation = Tools.getInformation(Crawler.ABROAD_INFORMATION_REGEX_TEMPLATE,"id",Crawler.ABROAD_INFORMATION_ATTRIBUTE);
            abroadInformation = abroadInformation.replace("}catch(e){}","");
            abroadList = Parse.parseAbroadInformation(abroadInformation);
            abroadService.insertAll(abroadList);
        } catch (Exception e) {
            logger.error("解析国外信息失败");
        }


        //数据持久化
        String timeLineNews = null;
        String provinceNews = null;

        String timelineServiceInformation = Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE, "id", Crawler.TIME_LINE_ATTRIBUTE);
        String areaInformation = Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE, "id", Crawler.AREA_INFORMATION_ATTRIBUTE);
        //解析
        List<Timeline> timeLineList = Parse.parseTimeLineInformation(timelineServiceInformation);
        List<AreaStat> areaStatList = Parse.parseAreaInformation(areaInformation);

        statisticsService.insert(statisticsInformation);
        timeLineNews = informationUtil.insertTimeLine(timeLineList);
        provinceNews = informationUtil.insertProvince(areaStatList);
    }

}
