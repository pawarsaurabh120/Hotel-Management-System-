package com.room.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.room.entity.Room;
import com.room.exception.RoomNotFoundException;
import com.room.service.IRoomService;

class RoomControllerTest {

	@InjectMocks
	private RoomController roomController;

	@Mock
	private IRoomService iRoomService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddRoom() {
		Room room = new Room();
		when(iRoomService.addRoom(room)).thenReturn(room);
		Room newRoom = roomController.addRoom(room);
		assertEquals(room, newRoom);
	}

	@Test
	void testUpdateRoom() throws RoomNotFoundException {
		Room room = new Room();
		when(iRoomService.updateRoom(room)).thenReturn(room);
		Room updatedRoom = roomController.updateRoom(room);
		assertEquals(room, updatedRoom);
	}

	@Test
	void testUpdateRoomById() throws RoomNotFoundException {
		int roomNo = 101;
		String roomStatus = "Booked";
		Room updatedRoom = new Room();
		when(iRoomService.updateRoomById(roomNo, roomStatus)).thenReturn(updatedRoom);
		Room result = roomController.updateRoomById(roomNo, roomStatus);
		assertEquals(updatedRoom, result);
	}

	@Test
	void testGetAllRoom() {
		List<Room> roomList = new ArrayList<>();
		when(iRoomService.getAllRoom()).thenReturn(roomList);
		List<Room> roomList1 = roomController.getAllRoom();
		assertEquals(roomList, roomList1);
	}

	@Test
	void testGetRoomById() throws RoomNotFoundException {
		int roomNo = 101;
		Room room = new Room();
		when(iRoomService.getRoomById(roomNo)).thenReturn(room);
		Room room1 = roomController.getRoomById(roomNo);
		assertEquals(room, room1);
	}

	@Test
	void testDeleteRoom() throws RoomNotFoundException {
		int roomNo = 101;
		roomController.deleteRoom(roomNo);
	}
}