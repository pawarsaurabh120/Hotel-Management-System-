package com.jwtauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwtauth.entity.User;

public interface UserRepository extends  MongoRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
