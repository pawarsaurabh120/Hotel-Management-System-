package com.booking.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.room.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Booking")
public class Booking {
	
	@Id
	private int bookingId;
	private String bookingEmail;
	private String bookingName;
	private String bookingAddress;
	private String bookingGovtId;
	private String bookingGovtIdNo;
	private int bookingAdult;
	private int bookingChild;
	private int bookingDays;
	private Date bookingCheckIn;
	private Date bookingCheckOut;
	private Room room;

}
