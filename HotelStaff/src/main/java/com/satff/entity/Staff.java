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
	private String staffEmailId;
	private String staffName;
	private String staffAddress;
	private String staffRole;
	private double staffSalary;

}
