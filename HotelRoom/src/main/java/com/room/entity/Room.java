package com.room.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Room")
public class Room {
	
	@Id
	private int id;
	private int roomCapacity;
	private String roomType;
	private String roomStatus;
	private int roomAmount;
	
}
