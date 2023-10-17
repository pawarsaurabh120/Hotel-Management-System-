package com.jwtauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.authrequest.AuthRequest;
import com.jwtauth.entity.User;
import com.jwtauth.jwt.JwtUtil;
import com.jwtauth.service.UserService;

@RestController
@RequestMapping("/hotel")
public class UserController {

	@Autowired
	private UserService userservice;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/user/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Username or Password.");
		}

		return jwtUtil.generateToken(authRequest.getUsername());
	}

	@GetMapping("/user/login/{username}/{password}")
	@PreAuthorize("hasAnyRole('OWNER','MANAGER','RECEPTION')")
	public ResponseEntity<?> Login(@PathVariable String username,@PathVariable String password) {
			User user = userservice.logIn(username,password);
			return new ResponseEntity<>(user,HttpStatus.OK);
	
	}

	@PostMapping("/user/add")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User newUser = userservice.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
//
//	@PutMapping("/user/update")
//	@PreAuthorize("hasRole('')")
//	public ResponseEntity<Object> updateUser(@RequestBody User user) {
//		User newUser = userservice.addUser(user);
//		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//	}
	
}
