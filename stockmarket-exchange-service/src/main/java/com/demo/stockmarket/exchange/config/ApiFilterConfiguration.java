package com.demo.stockmarket.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.stockmarket.exchange.filter.ApiFilter;

@Configuration
public class ApiFilterConfiguration {

    @Bean
    public ApiFilter createApiFilter() {
    	ApiFilter apiFilter = new ApiFilter();
    	return apiFilter;
    }
}
