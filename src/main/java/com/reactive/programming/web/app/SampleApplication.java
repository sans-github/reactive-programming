package com.reactive.programming.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.reactive.programming.web.controller",
        "com.reactive.programming.web.util",
        "com.reactive.programming.web.service",
        "com.reactive.programming.web.transformer"
})
public class SampleApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
