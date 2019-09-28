package com.demo.entrepreneur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EntrepreneurApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntrepreneurApplication.class, args);
        
    }
}