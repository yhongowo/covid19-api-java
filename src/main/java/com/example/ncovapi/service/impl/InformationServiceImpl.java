package com.example.ncovapi.service.impl;

import com.example.ncovapi.crawler.Crawler;
import com.example.ncovapi.crawler.Parse;
import com.example.ncovapi.crawler.Tools;
import com.example.ncovapi.entity.Abroad;
import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.Stat;
import com.example.ncovapi.entity.Timeline;
import com.example.ncovapi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private AbroadService abroadService;
    @Resource
    private StatService statService;
    @Resource
    private TimelineService timelineService;
    @Resource
    private AreaService areaService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getNews() {
        //获取HTML数据
        Tools.getPageByJSoup(Crawler.URL);
        try{
            //从正则表达式中提取信息
            String staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_1, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE)
                    .replace("}catch(e){}","");
            String timelineServiceInformation = Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE, "id", Crawler.TIME_LINE_ATTRIBUTE);
            String areaInformation = Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE, "id", Crawler.AREA_INFORMATION_ATTRIBUTE);
            String abroadInformation = Tools.getInformation(Crawler.ABROAD_INFORMATION_REGEX_TEMPLATE,"id",Crawler.ABROAD_INFORMATION_ATTRIBUTE)
                    .replace("}catch(e){}","");
            //解析
            Stat statInformation = Parse.parseStatisticsInformation(staticInformation);
            List<Timeline> timeLineList = Parse.parseTimeLineInformation(timelineServiceInformation);
            List<AreaStat> areaStatList = Parse.parseAreaInformation(areaInformation);
            List<Abroad> abroadList = Parse.parseAbroadInformation(abroadInformation);
            //持久化
            statService.insert(statInformation);
            timelineService.insertTimeLine(timeLineList);
            areaService.updateArea(areaStatList);
            abroadService.insertAll(abroadList);
        } catch (Exception e){
            logger.error("数据持久化失败");
            e.printStackTrace();
            StackTraceElement stackTraceElement= e.getStackTrace()[0];
            System.out.println("File="+stackTraceElement.getFileName());
            System.out.println("Line="+stackTraceElement.getLineNumber());
            System.out.println("Method="+stackTraceElement.getMethodName());
        } finally {
            Tools.page = null;
        }
    }

}
