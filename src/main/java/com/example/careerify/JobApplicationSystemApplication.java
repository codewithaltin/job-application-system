package com.example.careerify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class JobApplicationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplicationSystemApplication.class, args);
    }

}
