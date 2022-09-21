package com.linghan.aliddns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.linghan.timetask"})
public class AliddnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliddnsApplication.class, args);
    }

}
