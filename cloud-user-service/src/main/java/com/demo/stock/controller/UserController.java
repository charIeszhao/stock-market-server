package com.demo.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stock.entity.UserEntity;
import com.demo.stock.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @return
	 */
	@GetMapping
	public List<UserEntity> findAllUsers() {

		return userService.getUsers();
	}

	@PostMapping
	public ResponseEntity<UserEntity> register(UserEntity user) {
		UserEntity userEntity = userService.register(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
	}

	@PutMapping
	public ResponseEntity<UserEntity> update(UserEntity user) {
		UserEntity userEntity = userService.update(user);
		return ResponseEntity.ok(userEntity);
	}

	@GetMapping("/activate/{id}")
	public ResponseEntity<UserEntity> activate(@PathVariable Integer id) {
		UserEntity user = userService.activate(id);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		userService.delete(id);
		return ResponseEntity.ok("Delete user successfully.");
	}
}
