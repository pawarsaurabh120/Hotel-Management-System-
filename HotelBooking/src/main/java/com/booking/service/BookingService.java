package com.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.entity.Booking;
import com.booking.exception.BookingNotFoundException;
import com.booking.repository.BookingRepository;
import com.room.entity.Room;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Booking addBooking(Booking booking, int roomId) throws BookingNotFoundException {
		Room r = restTemplate.getForObject("http://Room/hotel/room/getByRoomNo/" + roomId, Room.class);
		if (r == null) {
			throw new BookingNotFoundException("Room not found");
		} else if (r.getRoomStatus().equalsIgnoreCase("Not Available")) {
			throw new BookingNotFoundException("Room not available");
		} else if (r.getRoomStatus().equalsIgnoreCase("Available")) {
			r.setRoomStatus("Not Avilable");
			restTemplate.put("http://Room/hotel/room/updateStatus/" + r.getId() + "/" + "Not Available",
					Room.class);
			booking.setRoom(r);
		}

		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Booking booking) throws BookingNotFoundException {
		bookingRepository.findById(booking.getBookingId())
				.orElseThrow(() -> new BookingNotFoundException("Booking not found"));
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking getBookingById(int bookingId) throws BookingNotFoundException {
		try {
			return bookingRepository.findById(bookingId).get();
		} catch (Exception e) {
			throw new BookingNotFoundException("Booking not found");
		}
	}

	@Override
	public void deleteBooking(int bookingId) throws BookingNotFoundException {
		bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found"));
		bookingRepository.deleteById(bookingId);
	}
}
