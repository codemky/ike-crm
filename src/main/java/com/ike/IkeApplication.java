package com.ike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ike.mapper"})
public class IkeApplication {
    public static void main(String[] args){
        SpringApplication.run(IkeApplication.class, args);
    }
}
