package com.satff.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Staff")

public class Staff {
	
    @Id
    private long id;
	private String username;
    private String password;
	private String name;
	private String address;
	private Role role;
	private double salary;

}
