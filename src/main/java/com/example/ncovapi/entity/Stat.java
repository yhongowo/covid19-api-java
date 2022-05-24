package com.example.ncovapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
@Data
public class Stat {
    DomesticStat domesticStat;
    GlobalStat globalStatistics;
    @JSONField(format="yyyy-MM-dd")
    private Date date;
}
