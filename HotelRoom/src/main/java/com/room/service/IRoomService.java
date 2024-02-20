package com.room.service;

import java.util.List;
import com.room.entity.Room;
import com.room.exception.RoomNotFoundException;

public interface IRoomService {

	public Room addRoom(Room room) throws RoomNotFoundException;

	public Room updateRoom(Room room) throws RoomNotFoundException;

	public Room updateRoomById(int roomNo, String status) throws RoomNotFoundException;

	public List<Room> getAllRoom();

	public Room getRoomById(int roomNo) throws RoomNotFoundException;

	public void deleteRoom(int roomNo) throws RoomNotFoundException;

	public List<Room> getAllAvailableRooms();
}
