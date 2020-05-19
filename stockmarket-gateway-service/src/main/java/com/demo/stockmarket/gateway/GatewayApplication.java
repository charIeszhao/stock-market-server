package com.demo.stockmarket.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.demo.stockmarket.gateway.filter.AccessFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EntityScan("com.demo.stockmarket.entity")
public class GatewayApplication {

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
