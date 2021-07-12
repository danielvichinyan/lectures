package com.knowit.lectures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LecturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LecturesApplication.class, args);
    }

}
