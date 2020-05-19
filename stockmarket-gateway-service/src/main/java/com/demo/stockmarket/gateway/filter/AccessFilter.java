package com.demo.stockmarket.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import com.demo.stockmarket.gateway.service.AuthenticationService;
import com.demo.stockmarket.gateway.service.TokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AccessFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationService authService;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println("Request received: " + requestURI);
        System.out.println("Method: " + request.getMethod());
        
        // SKIP OPTIONS
 		if ("OPTIONS".equals(request.getMethod())) {
 			return false;
 		}
        
        if ("/api/login".equals(requestURI)
        		|| ("/api/user".equals(requestURI) && "POST".equals(request.getMethod()))
        		|| "".equals(requestURI)) {
            return false;
        } else {
        	return true;
        }
	}

	@Override
	public Object run() throws ZuulException {
		// Access token evaluation
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		System.out.println("Evaluating access token...");
		
		String token = request.getHeader("Token");
		if (token == null || tokenService.isExpired(token)) {
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			requestContext.setResponseBody("Authorization token is empty or expired.");
		} else {
			String email = tokenService.getUserEmail(token);
			String role = tokenService.getUserRole(token);
//			User user = authService.getUserByEmail(email);
			requestContext.setSendZuulResponse(true);
			requestContext.addZuulRequestHeader("user.role", role);
			requestContext.addZuulRequestHeader("user.email", email);
			requestContext.setResponseStatusCode(HttpStatus.OK.value());
		}
		
		return null;
	}

}
