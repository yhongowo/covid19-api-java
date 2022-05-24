package com.example.ncovapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Abroad {
    private String continents;
    private String provinceName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    //update
    private int deadCountRank;
    private float deadRate;
    @JSONField(format="yyyy-MM-dd")
    private Date date;
}
