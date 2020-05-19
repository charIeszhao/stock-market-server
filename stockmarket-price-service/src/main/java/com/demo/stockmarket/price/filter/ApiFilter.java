package com.demo.stockmarket.price.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.entity.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * REST Interceptor
 */
@Data
public class ApiFilter implements Filter {

	private static final String INVALID_USER = "Invalid user, please input the valid user and retry.";
	private static final String NO_PERMISSION = "Do not have permission.";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// Pre-processing
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("Expected an HttpServletRequest but didn't get one");
		}
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String path = httpServletRequest.getRequestURI();
		System.out.println(path);

		// Skip option requests
		if ("OPTIONS".equals(httpServletRequest.getMethod())) {
			chain.doFilter(request, response);
			return;
		}
		
		// Fetch login information from request header
		String email = httpServletRequest.getHeader("user.email");
		String role = httpServletRequest.getHeader("user.role");

		if (email == null || role == null) {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			httpServletResponse.getWriter().write(buildInvalidUserResponseContent(INVALID_USER));
			return;
		}
		
		if (!"ADMIN".equals(role)) {
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			httpServletResponse.getWriter().write(buildInvalidUserResponseContent(NO_PERMISSION));
			return;
		}

		final RequestContext context = RequestContext.builder().email(email).role(role).build();

		RequestContextManager.setContext(context);

		// Do the rest of the chain
		chain.doFilter(request, response);

		// Post processing
		RequestContextManager.removeContext();
	}

	private String buildInvalidUserResponseContent(String message) {
		RestResponse<Object> reps = RestResponse.error(HttpServletResponse.SC_UNAUTHORIZED, message);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(reps);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
	}

}
