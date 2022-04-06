package com.example.ncovapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
@Data
public class DomesticStat {
    private int currentConfirmedCount;
    private int currentConfirmedIncr;
    private int confirmedCount;
    private int confirmedIncr;
    private int suspectedCount;
    private int suspectedIncr;
    private int curedCount;
    private int curedIncr;
    private int deadCount;
    private int deadIncr;
    private int seriousCount;
    private int seriousIncr;
    private String countRemark;
    private String dailyPic;
    private String imgUrl;
    @JSONField(format="yyyy-MM-dd")
    private Date date;
}
