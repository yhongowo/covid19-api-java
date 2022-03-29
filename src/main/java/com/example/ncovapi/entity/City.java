package com.example.ncovapi.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class City {
    private String provinceName;
    private String cityName;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private long modifyTime;
}
