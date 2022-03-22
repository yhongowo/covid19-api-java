package com.example.ncovapi;

import com.example.ncovapi.service.InformationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.Resource;

@SpringBootTest
@EnableCaching
class NCovApiApplicationTests {
    @Resource
    InformationService informationService;
    @Test
    void contextLoads() {
        informationService.getNews();
    }

}
