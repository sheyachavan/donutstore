package com.donutstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DonutStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonutStoreApplication.class, args);
    }
}
