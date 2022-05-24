package com.example.ncovapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Data
@NoArgsConstructor
public class AreaStat {
    private String provinceName;
    private String provinceShortName;
    private int confirmedCount;
    private int currentConfirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private String comment;
    private List<City> cities;
    private long modifyTime;
}
