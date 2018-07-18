package com.nearsoft.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class App {

    @GetMapping("/")
    public String home(){
        return "Welcome to Spring Boot REST Filter Example";
    }

    public static void main(String... args){
        SpringApplication.run(App.class, args);
    }
}
