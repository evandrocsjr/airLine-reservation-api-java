package com.br.airsystem;

import com.br.airsystem.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AirSystemApiApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AirSystemApiApplication.class, args);
        applicationContext.getBean(DemoService.class).createUser();
    }

}
