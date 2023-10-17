package com.jwtauth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
	
	@Id
	private long id;
	private String username;
	private Role role;
	private String password;
	private String email;
	private String phoneno;

}
