package com.demo.stockmarket.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.stockmarket.user.filter.ApiFilter;

@Configuration
public class ApiFilterConfiguration {

    @Bean
    public ApiFilter createApiFilter() {
    	ApiFilter apiFilter = new ApiFilter();
    	return apiFilter;
    }
}
