package com.example.ncovapi.crawler;

import com.example.ncovapi.service.InformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class ScheduleTask {
    public static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    @Resource
    InformationService informationService;

    //0点开始每4h爬取一次数据
    @Scheduled(cron = "0 0/1 * * * ?  ", zone = "")
    public void task(){
        logger.info("开始执行定时任务...");
        informationService.getNews();
        logger.info("定时任务执行完毕...");
    }
}
