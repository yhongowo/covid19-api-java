package com.example.ncovapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NCovApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NCovApiApplication.class, args);
    }

}
