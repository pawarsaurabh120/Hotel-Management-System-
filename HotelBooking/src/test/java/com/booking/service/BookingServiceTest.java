package com.booking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.booking.entity.Booking;
import com.booking.exception.BookingNotFoundException;
import com.booking.repository.BookingRepository;
import com.room.entity.Room;

class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private BookingService bookingService;

	private static Booking booking;
	private static Room room;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		booking = new Booking();
		booking.setBookingId(1);
		booking.setBookingEmail("saurabh@gmail.com");
		booking.setBookingName("Saurabh");
		booking.setBookingAddress("Airoli");
		booking.setBookingGovtId("Adhar Card");
		booking.setBookingGovtIdNo("123456789012");
		booking.setBookingAdult(2);
		booking.setBookingChild(1);
		booking.setBookingDays(1);
		Date checkIn = new Date("14/09/2022");
		Date checkOut = new Date("15/09/2022");
		booking.setBookingCheckIn(checkIn);
		booking.setBookingCheckIn(checkOut);
		room = new Room();
		room.setRoomNo(101);
		room.setRoomCapacity(2);
		room.setRoomType("AC");
		room.setRoomStatus("Available");
		booking.setRoom(room);
	}

	@Test
	void testAddBooking() {
	}

	@Test
	void testUpdateBooking() {

	}

	@Test
	void testGetAllBooking() {
		List<Booking> bookingList = new ArrayList<>();
		when(bookingRepository.findAll()).thenReturn(bookingList);
		List<Booking> result = bookingService.getAllBooking();
		assertEquals(bookingList, result);
	}

	@Test
	void testGetBookingById() throws BookingNotFoundException {
		 when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
	        Booking result = bookingService.getBookingById(booking.getBookingId());
	        assertEquals(booking, result);
		
	}

	@Test
	void testDeleteBooking() throws BookingNotFoundException {
		 when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
	        bookingService.deleteBooking(booking.getBookingId());	
	}

}
