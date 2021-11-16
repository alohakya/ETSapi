package com.project.etsapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@CrossOrigin(origins = {"http://localhost:8888", "null"})
@SpringBootApplication
@MapperScan("com.project.etsapi.mapper")
public class ETSapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ETSapiApplication.class, args);
    }

}
