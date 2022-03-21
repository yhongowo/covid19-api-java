package com.example.ncovapi;

import com.example.ncovapi.service.InformationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NCovApiApplicationTests {
    @Resource
    InformationService informationService;
    @Test
    void contextLoads() {
        informationService.getNews();
    }

}
