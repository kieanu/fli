package com.leepark.fli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.leepark.fli.model.dao")
@MapperScan(basePackages = "com.leepark.fli.model.firebase.dao")
public class FliApplication {  
	public static void main(String[] args) {
		SpringApplication.run(FliApplication.class, args);
	}	
}
