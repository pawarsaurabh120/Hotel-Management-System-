package com.booking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.booking.entity.Booking;
import com.booking.exception.BookingNotFoundException;
import com.booking.service.IBookingService;

class BookingControllerTest {

	@InjectMocks
	private BookingController bookingController;

	@Mock
	private IBookingService iBookingService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddBooking() throws BookingNotFoundException {
		Booking booking = new Booking();
		int roomId = 101;
		String staffEmailId = "Saurabh@gmail.com";
		when(iBookingService.addBooking(booking, roomId)).thenReturn(booking);
		ResponseEntity<Booking> response = bookingController.addBooking(booking, roomId);
		assertEquals(booking, response.getBody());
	}

	@Test
	void testUpdateBooking() throws BookingNotFoundException {
		Booking booking = new Booking();
		when(iBookingService.updateBooking(booking)).thenReturn(booking);
		Booking updatedBooking = bookingController.updateBooking(booking);
		assertEquals(booking, updatedBooking);
	}

	@Test
	void testGetAllBooking() {
		List<Booking> bookingList = new ArrayList<>();
		when(iBookingService.getAllBooking()).thenReturn(bookingList);
		List<Booking> bookingList1 = bookingController.getAllBooking();
		assertEquals(bookingList, bookingList1);
	}

	@Test
	void testGetBookingById() throws BookingNotFoundException {
		int bookingId = 1;
		Booking booking = new Booking();
		when(iBookingService.getBookingById(bookingId)).thenReturn(booking);
		Booking booking1 = bookingController.getBookingById(bookingId);
		assertEquals(booking, booking1);
	}

	@Test
	void testDeleteBooking() throws BookingNotFoundException {
		int bookingId = 1;
		bookingController.deleteBooking(bookingId);
	}
}
