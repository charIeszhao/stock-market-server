package com.demo.stockmarket.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.demo.stockmarket.entity")
public class StockPriceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StockPriceApplication.class, args);
	}
}
