package com.demo.stockmarket.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.user.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Page<User> findAllUsers(@RequestParam int page, @RequestParam int pageSize) {

		System.out.println("find all users with pager...");
		return userService.getUsers(page, pageSize);
	}

	@PostMapping
	public ResponseEntity<User> register(@RequestBody User user) {
		user = userService.register(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable int id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String email = requestContext.getEmail();
		String role = requestContext.getRole();
		
		if (email.equals(user.getEmail()) || role.equals("ADMIN")) {
			user = userService.update(user, id);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Do not have permission to update current user.");
		}
	}

	@GetMapping("/activate/{id}")
	public ResponseEntity<String> activate(@PathVariable Integer id) {
		userService.activate(id);
		return ResponseEntity.ok("User activated successfully.");
	}

	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		if (role.equals("ADMIN")) {
			userService.delete(id);
			return ResponseEntity.ok("Delete user successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
  
}