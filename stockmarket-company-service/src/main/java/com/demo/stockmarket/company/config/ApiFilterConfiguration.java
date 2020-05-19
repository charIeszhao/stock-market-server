package com.demo.stockmarket.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.stockmarket.company.filter.ApiFilter;

@Configuration
public class ApiFilterConfiguration {

    @Bean
    public ApiFilter createApiFilter() {
    	ApiFilter apiFilter = new ApiFilter();
    	return apiFilter;
    }
}
