package com.jwtauth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauth.entity.User;
import com.jwtauth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	public User addUser(User user) {
		return userRepo.save(user);
	}

	public User logIn(String username, String password) {

		User user = userRepo.findByUsername(username)

				.orElseThrow(() -> new RuntimeException("Invalid password or username"));

		if (!user.getPassword().equals(password)) {

			throw new RuntimeException("Invalid password or username");

		}

		return user;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username).get();

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),

				Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString())));

	}


}
