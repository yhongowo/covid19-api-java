package com.example.ncovapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@NoArgsConstructor
public class GlobalStatistics {
    private int currentConfirmedCount;
    private int confirmedCount;
    private int curedCount;
    private int deadCount;
    private int currentConfirmedIncr;
    private int confirmedIncr;
    private int curedIncr;
    private int deadIncr;
    private int yesterdayConfirmedCountIncr;
    @JSONField(format="yyyy-MM-dd")
    private Date date;
}
//     "currentConfirmedCount": 198551035,
//    "confirmedCount": 494483327,
//    "curedCount": 289745069,
//    "deadCount": 6187223,
//    "currentConfirmedIncr": 982578,
//    "confirmedIncr": 1027641,
//    "curedIncr": 42120,
//    "deadIncr": 2943,
//    "yesterdayConfirmedCountIncr": 1027641