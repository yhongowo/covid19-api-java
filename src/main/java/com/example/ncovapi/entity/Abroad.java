package com.example.ncovapi.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Abroad {
    private String continentName;
    private String provinceName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private Date date;
}
