package com.example.ncovapi.service;

import com.example.ncovapi.entity.Timeline;

import java.util.List;

public interface TimelineService {
    List<Timeline> getTimeline();
    void insertTimeLine(List<Timeline> timeLineList);
}
