package com.example.ncovapi.controller;

import com.example.ncovapi.entity.AreaStat;
import com.example.ncovapi.entity.Statistics;
import com.example.ncovapi.entity.Timeline;
import com.example.ncovapi.service.AreaService;
import com.example.ncovapi.service.StatisticsService;
import com.example.ncovapi.service.TimelineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("api")
public class MainController {
    @Resource
    StatisticsService statisticsservice;
    @Resource
    AreaService areaService;
    @Resource
    TimelineService timelineService;

    /***
     * @retyrn 返回最新统计数据
     */
    @GetMapping("statistics/latest")
    @ResponseBody
    public Statistics latest(){
        return statisticsservice.today();
    }

    /***
     * @return 返回历史统计数据
     */
    @GetMapping("statistics/history")
    @ResponseBody
    public List<Statistics> history() {
        return statisticsservice.history();
    }

    /***
     * @return 返回所有地区数据
     */
    @GetMapping("province/all")
    @ResponseBody
    public List<AreaStat> area(){
        return areaService.getAllArea();
    }

    @GetMapping("timeline")
    @ResponseBody
    public List<Timeline> timelines() {
        return timelineService.getTimeline();
    }

}
