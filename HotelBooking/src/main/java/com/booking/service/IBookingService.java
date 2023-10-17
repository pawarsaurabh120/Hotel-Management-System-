package com.booking.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.booking.entity.Booking;
import com.booking.exception.BookingNotFoundException;

@Service
public interface IBookingService {

	public Booking addBooking(Booking booking, int bookingId) throws BookingNotFoundException;

	public Booking updateBooking(Booking booking) throws BookingNotFoundException;

	public List<Booking> getAllBooking();

	public Booking getBookingById(int bookingId) throws BookingNotFoundException;

	public void deleteBooking(int bookingId) throws BookingNotFoundException;

}
