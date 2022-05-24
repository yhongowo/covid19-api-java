package com.example.ncovapi.controller;

import com.example.ncovapi.entity.*;
import com.example.ncovapi.service.AbroadService;
import com.example.ncovapi.service.AreaService;
import com.example.ncovapi.service.StatService;
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
    StatService statisticsservice;
    @Resource
    AreaService areaService;
    @Resource
    TimelineService timelineService;
    @Resource
    AbroadService abroadService;

    /***
     * @retyrn 返回最新统计数据
     */
    @GetMapping("statistics/latest")
    @ResponseBody
    public List<Stat> latest(){
        return statisticsservice.selectLatest();
    }

    /***
     * @return 返回历史统计数据
     */
    @GetMapping("statistics/domestic/all")
    @ResponseBody
    public List<DomesticStat> domesticAll() {
        return statisticsservice.selectDomesticAll();
    }

    @GetMapping("statistics/global/all")
    @ResponseBody
    public List<GlobalStat> globalAll() {
        return statisticsservice.selectGlobalAll();
    }

    /***
     * @return 返回所有省份数据
     */
    @GetMapping("province")
    @ResponseBody
    public List<AreaStat> provinceAll(){
        return areaService.getProvinceAll();
    }

    @GetMapping("city")
    @ResponseBody
    public List<City> cityAll(){
        return areaService.getCityAll();
    }

    @GetMapping("abroad")
    @ResponseBody
    public List<Abroad> abroad() {
        return abroadService.selectLatest();
    }

    @GetMapping("timeline")
    @ResponseBody
    public List<Timeline> timelines() {
        return timelineService.getTimeline();
    }

}
