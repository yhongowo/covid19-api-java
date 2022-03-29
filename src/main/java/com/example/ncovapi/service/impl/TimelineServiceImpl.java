package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.TimelineMapper;
import com.example.ncovapi.entity.Timeline;
import com.example.ncovapi.service.TimelineService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Timeline")
public class TimelineServiceImpl implements TimelineService {
    @Resource
    TimelineMapper timelineMapper;

    @Override
    @Cacheable()
    public List<Timeline> getTimeline() {
        return timelineMapper.selectTimeline();
    }

    @Override
    @CachePut
    public void insertTimeLine(List<Timeline> timeLineList) {
        for(Timeline item : timeLineList) {
            timelineMapper.addTimeLine(item);
        }
    }
}
