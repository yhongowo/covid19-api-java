package com.example.ncovapi.dao;
import com.example.ncovapi.entity.Timeline;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimelineMapper {
    /**
     * 查询所有
     * @return
     */
    List<Timeline> selectTimeline();

    /**
     * 添加实时新闻，如果id冲突则不添加。
     * @param timeLine
     * @return
     */
    int addTimeLine(Timeline timeLine);
}
