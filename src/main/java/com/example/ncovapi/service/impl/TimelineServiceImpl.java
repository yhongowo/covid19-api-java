package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.TimelineMapper;
import com.example.ncovapi.entity.Timeline;
import com.example.ncovapi.service.TimelineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TimelineServiceImpl implements TimelineService {
    @Resource
    TimelineMapper timelineMapper;

    @Override
    public List<Timeline> getTimeline() {
        return timelineMapper.selectTimeline();
    }
}
