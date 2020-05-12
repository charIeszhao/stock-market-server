package com.demo.stock.conf;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Resource server configuration
 * @ EnableResourceServer
 * @ EnableWebSecurity
 * @ EnableGlobalMethodSecurity
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
			.authenticationEntryPoint((request, response, authException)-> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
			.and()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}

}
