package com.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ConfigRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigRestApplication.class, args);
        log.info("ConfigRest service started...");
    }

}