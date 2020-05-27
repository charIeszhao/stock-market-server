package com.demo.stockmarket.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.entity.RestResponse;
import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public RestResponse<?> findAllUsersWithPagination(@RequestParam int page, @RequestParam int pageSize) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		
		if (role.equals("ADMIN")) {
			Page<User> pageContent = userService.getUsers(page, pageSize);
			List<User> users = pageContent.getContent();
			long total = pageContent.getTotalElements();
			return RestResponse.ok(users, total, "Get users successfully.");
			
		} else {
			return RestResponse.error(HttpStatus.FORBIDDEN.value(), "Do not have permission to update current user.");
		}
	}
	
	@GetMapping("/info")
	public RestResponse<User> getUserInfo() {
		RequestContext requestContext = RequestContextManager.getContext();
		String email = requestContext.getEmail();
		
		User user = userService.getUserByEmail(email);
		return RestResponse.ok(user, "Get user info successfully.");
	}
	
	@GetMapping("/all")
	public RestResponse<?> findAllUsers() {
		List<User> users = userService.getUsers();
		long total = users.size();
		return RestResponse.ok(users, total, "Get users successfully.");
	}

	@PostMapping
	public RestResponse<User> register(@RequestBody User user) {
		user = userService.register(user);
		return RestResponse.created(user, "User created successfully.");
	}

	@PutMapping("/{id}")
	public RestResponse<User> update(@RequestBody User user, @PathVariable int id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String email = requestContext.getEmail();
		String role = requestContext.getRole();
		
		if (email.equals(user.getEmail()) || role.equals("ADMIN")) {
			user = userService.update(user, id);
			if (user != null) {
				return RestResponse.ok(user);
			} else {
				return RestResponse.error(HttpStatus.NOT_FOUND.value(), "User not found.");
			}
		} else {
			return RestResponse.error(HttpStatus.FORBIDDEN.value(), "Do not have permission to update current user.");
		}
	}

	@GetMapping("/activate/{id}")
	public RestResponse<String> activate(@PathVariable Integer id) {
		userService.activate(id);
		return RestResponse.ok("User activated successfully.");
	}

	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public RestResponse<String> delete(@PathVariable Integer id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		if (role.equals("ADMIN")) {
			userService.delete(id);
			return RestResponse.ok("Delete user successfully.");
		} else {
			return RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Do not have permission to delete the selected user.");
		}
	}
  
}