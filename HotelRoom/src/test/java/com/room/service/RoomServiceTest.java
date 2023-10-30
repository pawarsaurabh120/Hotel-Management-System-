package com.room.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.room.entity.Room;
import com.room.exception.RoomNotFoundException;
import com.room.repository.RoomRepository;

class RoomServiceTest {

	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomService roomService;
	
	private static Room room;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		room = new Room();
		room.setId(101);
		room.setRoomCapacity(2);
		room.setRoomType("AC");
		room.setRoomStatus("Available");
	}

	@Test
	void testAddRoom() {
		when(roomRepository.save(room)).thenReturn(room);
		Room r = roomService.addRoom(room);
		assertEquals(r, room);
	}

	@Test
	void testUpdateRoom() throws RoomNotFoundException {
		Room updatedRoom = new Room();
		updatedRoom.setId(101);
		updatedRoom.setRoomCapacity(3);
		updatedRoom.setRoomType("Non AC");
		updatedRoom.setRoomStatus("Available");
		when(roomRepository.findById(101)).thenReturn(Optional.of(room));
		when(roomRepository.save(updatedRoom)).thenReturn(updatedRoom);
		Room r = roomService.updateRoom(updatedRoom);
		assertEquals(3, r.getRoomCapacity());
		assertEquals("Non AC", r.getRoomType());
	}

//	NOT WORKING...............
	@Test
	void testUpdateRoomById() throws RoomNotFoundException {
		when(roomRepository.findById(101)).thenReturn(Optional.of(room));
		when(roomRepository.save(room)).thenReturn(room);
		Room r = roomService.updateRoomById(101,"Not Available");
		assertEquals("Not Available", r.getRoomStatus());
	}

	@Test
	void testGetAllRoom() {
		List<Room> roomList = new ArrayList<>(List.of(room));
		when(roomRepository.findAll()).thenReturn(roomList);
		List<Room> r = roomService.getAllRoom();
		assertEquals(roomList.size(), r.size());
	}

	@Test
	void testGetRoomById() throws RoomNotFoundException {
		when(roomRepository.findById(101)).thenReturn(Optional.of(room));
		Room r = roomService.getRoomById(101);
		assertEquals(room, r);
	}

	@Test
	void testDeleteRoom() throws RoomNotFoundException {
		when(roomRepository.findById(101)).thenReturn(Optional.of(room));
		roomService.deleteRoom(101);
	}
	
	@Test
	void testGetRoomById_RoomNotFound() {
		when(roomRepository.findById(10)).thenReturn(null);
		assertThrows(RoomNotFoundException.class, ()-> roomService.getRoomById(10));
	}
	
	@Test
	void testDeleteRoom_RoomNotFound() {
		when(roomRepository.findById(10)).thenReturn(null);
		assertThrows(RoomNotFoundException.class, ()-> roomService.getRoomById(10));
	}

}
